/**
 */
package com.troika.cache;

/**
 * Interface for the platform caching service, it abstracts complexity of
 * underlying cache providers.
 * 
 */
public interface CachingService {

	/**
	 * Places an Object into the cache for a given cacheKey.
	 *
	 * @param cacheKey
	 *            key for which new cache entry to be created
	 * @param cacheValue
	 *            the value to be cached for given cacheKey
	 * @param <K>
	 *            class type the Cache key
	 * @param <V>
	 *            class type the Cache value
	 * @return the value successfully put in cache for given cacheKey
	 */
	<K, V> V putInCache(K cacheKey, V cacheValue);

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 *
	 * @param cacheKey
	 *            key for which cache entry to be retrieved
	 * @param cacheValueType
	 *            the class type for the value in cache
	 * @param <K>
	 *            class type the Cache key
	 * @param <V>
	 *            class type the Cache value
	 * @return the value to be retrieved for given cacheKey
	 */
	<K, V> V getFromCache(K cacheKey, Class<V> cacheValueType);

	/**
	 * Removes an Object from the cache for a given cacheKey.
	 *
	 * @param cacheKey
	 *            cacheKey key for which cache entry to be removed
	 * @param cacheValueType
	 *            the class type for the value in cache
	 * @param <K>
	 *            class type the Cache key
	 * @param <V>
	 *            class type the Cache value
	 * @return the value removed from the cache for given cacheKey
	 */
	<K, V> Boolean removeFromCache(K cacheKey, Class<V> cacheValueType);

	public <K, V> V putInCache(K cacheKey, V cacheValue, String cacheName);

	public <K, V> V getFromCache(final K cacheKey, final Class<V> cacheValueType, String cacheName);

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
	 * @param cacheName
	 *            Cache name from where to remove the Entry for given key
	 * @return the value removed from the cache for given cacheKey
	 */
	public <K, V> Boolean removeFromCache(final K cacheKey, final Class<V> cacheValueType, String cacheName);

	/**
	 * Places an Object into the cache for a given cacheKey of String type.
	 *
	 * @param cacheKey
	 *            key for which new cache entry to be created
	 * @param cacheValue
	 *            the value to be cached for given cacheKey
	 * @param String
	 *            Cache key
	 * @param Object
	 *            cache value
	 */
	void putInCache(String cacheKey, Object value, String cacheName);

	/**
	 * Retrieves an Object from the cache for a given cacheKey.
	 *
	 * @param cacheKey
	 *            key for which cache entry to be retrieved
	 * @param cacheName
	 *            Name of the cache
	 * @return the value to be retrieved for given cacheKey
	 */
	public Object getFromCache(final String cacheKey, String cacheName);

	/**
	 * Removes an Object from the cache for a given cacheKey.
	 * 
	 * @param cacheKey
	 *            cacheKey key for which cache entry to be removed
	 * @param cacheName
	 *            Cache name from where to remove the Entry for given key
	 * @return the value removed from the cache for given cacheKey
	 */
	public Boolean removeFromCache(final String cacheKey, String cacheName);

}
