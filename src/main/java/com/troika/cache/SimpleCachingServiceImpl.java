package com.troika.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple concrete implementation for the CachingService which manages the cache
 * in Local Memory Map(s). <br>
 * It manages different Maps for different Key-Value types for type safety for
 * the client API TODO: This is just a Dummy implementation and needs to be
 * replaced with implementation leveraging <br>
 * cache providers such as EHCache or Coherence where it internally manages
 * different Caches for <br>
 * Different Key-Value types and also Cache Names.<br>
 * Note: For named caches access to cachingService API the API needs to be
 * enhanced
 * 
 */
public class SimpleCachingServiceImpl implements CachingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCachingServiceImpl.class);

	private Map<CacheRegistryKey<?, ?>, Map<?, ?>> cacheRegistryForTypes = new HashMap<CacheRegistryKey<?, ?>, Map<?, ?>>();

	/**
	 * Places an Object into the cache for a given cacheKey.
	 * 
	 * @param cacheKey
	 *            key for which new cache entry to be created
	 * @param cacheValue
	 *            the value to be cached for given cacheKey
	 * @param <K>
	 *            class type of the Cache Key
	 * @param <V>
	 *            class type of the Cache value
	 * @return the value successfully put in cache for given cacheKey
	 */
	public <K, V> V putInCache(final K cacheKey, final V cacheValue) {
		if (cacheKey == null || cacheValue == null) {
			throw new IllegalArgumentException(
					"Attempt to put null key or null value in Cache that is not allowed, key=" + cacheKey + " value="
							+ cacheValue);
		}
		Map<K, V> cache = getTargetCache(getClass(cacheKey), getClass(cacheValue));
		LOGGER.debug("Putting into cache with key: " + cacheKey + " value: " + cacheValue);
		return cache.put(cacheKey, cacheValue);

	}

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey
	 *            key for which cache entry to be retrieved
	 * @param cacheValueType
	 *            class type of the value to be removed from cache
	 * @param <K>
	 *            class type of the Cache Key
	 * @param <V>
	 *            class type of the Cache value
	 * @return the value to be retrieved for given cacheKey
	 */
	public <K, V> V getFromCache(final K cacheKey, final Class<V> cacheValueType) {
		if (cacheKey == null) {
			throw new IllegalArgumentException("Attempt to retrieve for null key from the Cache, that is not allowed");
		}
		Map<K, V> cache = getTargetCache(getClass(cacheKey), cacheValueType);

		V response = cache.get(cacheKey);
		if (response == null) {
			LOGGER.debug("cache miss for key: " + cacheKey);
		} else {
			LOGGER.debug("cache hit for key: " + cacheKey);
		}
		return response;
	}

	/**
	 * Removes an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey
	 *            cacheKey key for which cache entry to be removed
	 * @param cacheValueType
	 *            class type of the value to be removed from cache
	 * @param <K>
	 *            class type of the Cache Key
	 * @param <V>
	 *            class type of the Cache value
	 * @return the value removed from the cache for given cacheKey
	 */
	/*
	 * public <K, V> Boolean removeFromCache(final K cacheKey, final Class<V>
	 * cacheValueType) { if (cacheKey == null) { throw new
	 * IllegalArgumentException(
	 * "Attempt to remove for null key from the Cache, that is not allowed"); }
	 * Map<K, V> cache = getTargetCache(getClass(cacheKey), cacheValueType);
	 * return cache.remove(cacheKey); }
	 */

	public <K, V> Boolean removeFromCache(final K cacheKey, final Class<V> cacheValueType) {

		return null;
	}

	/**
	 * Returns the Target Cache which is for now a Map, for given Key Value
	 * Types.
	 * 
	 * @param keyType
	 *            class type for the Key stored in the target Cache.
	 * @param valueType
	 *            the type for the value stored in the target Cache.
	 * @param <K>
	 *            class type of the Cache Key
	 * @param <V>
	 *            class type of the Cache value
	 * @return the target Cache for the specific Key-Value Type
	 */
	// In the below method there are two type casting, which is not at all good
	// practice,
	// however assuming this will never result in ClassCastException
	@SuppressWarnings("unchecked")
	protected <K, V> Map<K, V> getTargetCache(final Class<K> keyType, final Class<V> valueType) {
		CacheRegistryKey<K, V> cacheRegistryKey = new CacheRegistryKey<K, V>(keyType, valueType);
		Map<K, V> targetCache = (Map<K, V>) cacheRegistryForTypes.get(cacheRegistryKey);
		if (targetCache == null) {
			targetCache = new ConcurrentHashMap<K, V>();
			cacheRegistryForTypes.put(cacheRegistryKey, targetCache);
		}

		return targetCache;
	}

	private <T> Class<T> getClass(final T value) {
		// Below statement is doing type casting, which is not at all good
		// practice,
		// however assuming this will never result in ClassCastException
		@SuppressWarnings("unchecked")
		Class<T> clazzT = (Class<T>) value.getClass();
		return clazzT;
	}

	/**
	 * Inner class for Key to be used for accessing CacheRegistry of Type
	 * specific caches.
	 * 
	 * @param<KE> the
	 *                type for the Key stored in the target Cache.
	 * @param<VA> the
	 *                type for the value stored in the target Cache.
	 */
	public static class CacheRegistryKey<KE, VA> {
		private String key;

		/**
		 * Constructs the instance of CacheRegistryKey.
		 *
		 * @param clazzKE
		 *            class type for the Key stored in the target Cache.
		 * @param clazzVA
		 *            class type for the value stored in the target Cache.
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
		 * OVerrides the super class toString() method by returning the Key
		 * value.
		 *
		 * @return the Key value,
		 */
		@Override
		public String toString() {
			return key;
		}

		/**
		 * Overrides the equals method for ensuring keys are matched during
		 * lookup.
		 *
		 * @param obj
		 *            the instance with which to compare.
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
	}

	public <K, V> V putInCache(K cacheKey, V cacheValue, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public <K, V> V getFromCache(K cacheKey, Class<V> cacheValueType, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public <K, V> Boolean removeFromCache(K cacheKey, Class<V> cacheValueType, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public <K, V> V putInCache(K cacheKey, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public <K, V> V getFromCache(K cacheKey, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putInCache(String cacheKey, Object value, String cacheName) {
		// TODO Auto-generated method stub

	}

	public Object getFromCache(String cacheKey, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean removeFromCache(String cacheKey, String cacheName) {
		// TODO Auto-generated method stub
		return null;
	}

}
