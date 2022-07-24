/**
 * 
 */
package com.troika.aggregator.services.impl;

import com.troika.cache.CachingService;

/**
 * Super class for all Services implementation. <br>
 * All the common or shared implementation across all Online Services, to be
 * part of this class
 * 
 */
public class BaseCacheServiceImpl {

	/**
	 * Reference to the Caching Service.
	 */
	private CachingService cachingService;

	/**
	 * Returns the reference to the CachingService instance.
	 * 
	 * @return the cachingService
	 */
	public CachingService getCachingService() {
		return cachingService;
	}

	/**
	 * Sets the reference to the CachingService instance.
	 * 
	 * @param cachingService
	 *            the cachingService to set
	 */
	public void setCachingService(final CachingService cachingService) {
		this.cachingService = cachingService;
	}

}
