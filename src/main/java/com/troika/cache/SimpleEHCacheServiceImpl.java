package com.troika.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *
 */
@Service
public class SimpleEHCacheServiceImpl implements CachingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEHCacheServiceImpl.class);

	private CacheManager cm = CacheManager.newInstance();
	

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey key for which cache entry to be retrieved
	 * @param cacheValueType class type of the value to be removed from cache
	 * @param <K> class type of the Cache Key
	 * @param <V> class type of the Cache value
	 * @return the value to be retrieved for given cacheKey
	 */
	public <K, V> V getFromCache(final K cacheKey, final Class<V> cacheValueType) {
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to retrieve for null key from the Cache, that is not allowed");
		}
		CacheRegistryKey<K, V> cacheTypeRegistryKey = getCacheRegistryKey(getClass(cacheKey), cacheValueType);
		return this.getFromCache(cacheKey, cacheValueType, cacheTypeRegistryKey.getKey());
	}

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey key for which cache entry to be retrieved
	 * @param cacheValueType class type of the value to be removed from cache
	 * @param <K> class type of the Cache Key
	 * @param <V> class type of the Cache value
	 * @param cacheName name of cache from which to retrieve cache key 
	 * @return the value to be retrieved for given cacheKey
	 */
	public <K, V> V getFromCache(final K cacheKey, final Class<V> cacheValueType, String cacheName) {
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to retrieve for null key from the Cache, that is not allowed");
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		V response = null;
		Element elem = cache.get(cacheKey);
		if(elem != null){
			response = (V)elem.getObjectValue();
		}
		if (response == null) {
			LOGGER.trace("cache miss for key: " + cacheKey);
		} else {
			LOGGER.trace("cache hit for key: " + cacheKey);
		}
		return response;
	}

	/**
	 * Places an Object into the cache (EhCache provider in this case) for a given cacheKey.
	 *
	 * @param cacheKey key for which new cache entry to be created
	 * @param cacheValue the value to be cached for given cacheKey
	 * @param <K> class type the Cache key
	 * @param <V> class type the Cache value
	 * @return the value successfully put in cache for given cacheKey
	 */
	public <K, V> V putInCache(K cacheKey, V cacheValue) {
		if (cacheKey == null || cacheValue == null) {
			throw new IllegalArgumentException("Attempt to put null key or null value in Cache that is not allowed, key=" + cacheKey + " value="
					+ cacheValue);
		}
		
		//TODO Empty String value should be warning rather than Rejecting them and treating it as Error
		if (cacheKey instanceof String && ((String)cacheKey).isEmpty()){ //instanceof is a slightly costly operation
			//Do NOT throw Exception Just and Log message and avoid Creating in Entry
			LOGGER.error("Attempting to create a cache Entry with an Empty String value that is NOT allowed, key=" + cacheKey + " value="
					+ cacheValue);
		}
		
		CacheRegistryKey<K, V> cacheTypeRegistryKey = getCacheRegistryKey(getClass(cacheKey), getClass(cacheValue));
		return (V) putInCache(cacheKey, cacheValue, cacheTypeRegistryKey.getKey());
	}

	/**
	 * Places an Object into the cache (EhCache provider in this case) for a given cacheKey.
	 *
	 * @param cacheKey key for which new cache entry to be created
	 * @param cacheValue the value to be cached for given cacheKey
	 * @param <K> class type the Cache key
	 * @param <V> class type the Cache value
	 * @param cacheName Cache name in which to create/put the cache entry
	 * @return the value successfully put in cache for given cacheKey
	 */
	public <K, V> V putInCache(K cacheKey, V cacheValue, String cacheName) {
		if (cacheKey == null || cacheValue == null) {
			throw new IllegalArgumentException("Attempt to put null key or null value in Cache that is not allowed, key=" + cacheKey + " value="
					+ cacheValue);
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		LOGGER.trace("Putting into cache with key: " + cacheKey + " value: " + cacheValue);
		Element element = new Element(cacheKey,cacheValue);
		cache.put(element);
		return (V) cache;
	}
	
	/**
	 * Removes an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey cacheKey key for which cache entry to be removed
	 * @param cacheValueType class type of the value to be removed from cache
	 * @param <K> class type of the Cache Key
	 * @param <V> class type of the Cache value
	 * @return the value removed from the cache for given cacheKey
	 */
	public <K, V> Boolean removeFromCache(final K cacheKey, final Class<V> cacheValueType) {
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to remove for null key from the Cache, that is not allowed");
		}
		CacheRegistryKey<K, V> cacheTypeRegistryKey = getCacheRegistryKey(getClass(cacheKey), cacheValueType);
		return removeFromCache(cacheKey, cacheValueType, cacheTypeRegistryKey.getKey());
	}
	
	/**
	 * Removes an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey cacheKey key for which cache entry to be removed
	 * @param cacheValueType class type of the value to be removed from cache
	 * @param <K> class type of the Cache Key
	 * @param <V> class type of the Cache value
	 * @param cacheName Cache name from where to remove the Entry for given key
	 * @return the value removed from the cache for given cacheKey
	 */
	public <K, V> Boolean removeFromCache(final K cacheKey, final Class<V> cacheValueType, String cacheName) {
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to remove for null key from the Cache, that is not allowed");
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		LOGGER.trace("Removing Entry from cache with key: " + cacheKey);
		return cache.remove(cacheKey);
	}
	
	/**
	 * Places an Object into the cache for a given cacheKey of String type.
	 *
	 * @param cacheKey key for which new cache entry to be created
	 * @param cacheValue the value to be cached for given cacheKey
	 * @param String Cache key
	 * @param Object cache value
	 */
	public void putInCache(String cacheKey, Object cacheValue, String cacheName){
		if (cacheKey == null || cacheValue == null) {
			throw new IllegalArgumentException("Attempt to put null key or null value in Cache that is not allowed, key=" + cacheKey + " value="
					+ cacheValue);
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		LOGGER.trace("Putting into cache with key: " + cacheKey + " value: " + cacheValue);
		Element element = new Element(cacheKey,cacheValue);
		cache.put(element);
	}

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 *
	 * @param cacheKey key for which cache entry to be retrieved
	 * @param cacheName Name of the cache
	 * @return the value to be retrieved for given cacheKey
	 */
	public Object getFromCache(final String cacheKey, String cacheName){
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to retrieve for null key from the Cache, that is not allowed");
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		Object response = null;
		Element elem = cache.get(cacheKey);
		if(elem != null){
			response = elem.getObjectValue();
		}
		if (response == null) {
			LOGGER.trace("cache miss for key: " + cacheKey);
		} else {
			LOGGER.trace("cache hit for key: " + cacheKey);
		}
		return response;
	}
	
	
	/**
	 * Removes an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey cacheKey key for which cache entry to be removed
	 * @param cacheName Cache name from where to remove the Entry for given key
	 * @return the value removed from the cache for given cacheKey
	 */
	public Boolean removeFromCache(final String cacheKey, String cacheName){
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to remove for null key from the Cache, that is not allowed");
		}
		LOGGER.trace("CacheType Name : "+cacheName);
		Cache cache = getTargetCache(cacheName);
		LOGGER.trace("Removing Entry from cache with key: " + cacheKey);
		return cache.remove(cacheKey);
	}

	
	/**
	 * Returns the RegistryKey for specific Type of Entries (key & value) to be used in cache
	 * @param keyType
	 * @param valueType
	 * @return The Registry Key
	 */
	protected <K,V> CacheRegistryKey<K, V> getCacheRegistryKey(final Class<K> keyType, final Class<V> valueType){
		return new CacheRegistryKey<K, V>(keyType, valueType);
	}

	/**
	 * Returns the Target Cache for the given name
	 * @param cacheName
	 * @return
	 */
	protected Cache getTargetCache(String cacheName){
		Cache targetCache = cm.getCache(cacheName);
		if(targetCache == null && !cm.cacheExists(cacheName)){
			cm.addCache(cacheName);
			targetCache = cm.getCache(cacheName);
		}
		LOGGER.trace(":: Cache with name : "+cacheName);
		if(targetCache ==null){
			LOGGER.error("Cannot find or create Cache with Name : "+cacheName);
			throw new IllegalStateException("Cannot find or create Cache with Name : "+cacheName);
		}
		
		return targetCache;
	}
	
	private <T> Class<T> getClass(final T value) {
		// Below statement is doing type casting, which is not at all good practice,
		// however assuming this will never result in ClassCastException
		@SuppressWarnings("unchecked")
		Class<T> clazzT = (Class<T>) value.getClass();
		return clazzT;
	}

	/**
	 * Inner class for Key to be used for accessing CacheRegistry of Type specific caches.
	 * 
	 * @param<KE> the type for the Key stored in the target Cache.
	 * @param<VA> the type for the value stored in the target Cache.
	 */
	public static class CacheRegistryKey<KE, VA> {
		private String key;

		/**
		 * Constructs the instance of CacheRegistryKey.
		 *
		 * @param clazzKE class type for the Key stored in the target Cache.
		 * @param clazzVA class type for the value stored in the target Cache.
		 */
		public CacheRegistryKey(final Class<KE> clazzKE, final Class<VA> clazzVA) {
			key = clazzKE.getName() + ":" + clazzVA.getName();
		}

		/**
		 * Returns the String Key value for the current instance.
		 *
		 * @return String key value
		 */
		public String getKey() {
			return key;
		}

		/**
		 * OVerrides the super class toString() method by returning the Key value.
		 *
		 * @return the Key value,
		 */
		@Override
		public String toString() {
			return key;
		}

		/**
		 * Overrides the equals method for ensuring keys are matched during lookup.
		 *
		 * @param obj the instance with which to compare.
		 * @return boolean whether equals or not
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(final Object obj) {
			if (obj != null && obj.getClass().equals(this.getClass())) {
				return key.equals(((CacheRegistryKey<KE, VA>) obj).getKey());
			}
			return false;
		}

		/**
		 * Overrides the hashCode() method of super class.
		 *
		 * @return hasCode value for this instance.
		 */
		@Override
		public int hashCode() {
			if (key != null) {
				return key.hashCode();
			}
			return super.hashCode();
		}
	}//End of Inner Class
	
}
