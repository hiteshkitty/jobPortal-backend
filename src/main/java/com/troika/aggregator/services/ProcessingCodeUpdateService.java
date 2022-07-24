package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST service interface for pcodeUpdate
 * 
 * 
 */
@Path("update")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
public interface ProcessingCodeUpdateService {

	@GET
	@Path("/pcode/{pcode}")
	String pcodeUpdate(@PathParam("pcode") String pCode);

	@GET
	@Path("/pCode/{pcode}")
	String pcodeUpdateNew(@PathParam("pcode") String pCode);

}
