package com.troika.helper.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.troika.helper.TroikaThreadLocal;

/**
 * This filter verify the access permissions for a user based on username and
 * passowrd provided in request
 */
@Provider
public class TroikaOutFilter implements ContainerResponseFilter {

	@Context
	private ResourceInfo resourceInfo;

	private static final Logger LOGGER = Logger.getLogger(TroikaOutFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		Long startTime = TroikaThreadLocal.getTimerThreadLocal();
		
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
	

		if (startTime != null) {
		
			long endTime = System.currentTimeMillis() - startTime;

			String path = requestContext.getUriInfo().getAbsolutePath().getPath();

			LOGGER.debug("The API: " + path + " took " + endTime + " ms to execute.");
		}
		
		TroikaThreadLocal.unset();

	}

}