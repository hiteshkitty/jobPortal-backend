package com.troika.exceptionhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.troika.dao.impl.CompanyDaoImpl;
import com.troika.domain.view.RestEntity;
import com.troika.domain.view.RestResponse;
import com.troika.domain.view.StatusEnum;

@Provider
@Component
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Logger LOGGER = Logger.getLogger(GenericExceptionHandler.class);
	
	@Override
	public Response toResponse(Throwable exception) {
		
		exception.printStackTrace();
		
		LOGGER.error("ERROR: handling exception in handler" + exception.getMessage());
		
		RestResponse restResponse = new RestResponse();
		
		restResponse.getResultStatus().setStatus(StatusEnum.FAILURE);
		
		restResponse.getResultStatus().setDescription(exception.getMessage());

		RestEntity<String> entity = new RestEntity<>();

		entity.setEntity(exception.getMessage());

		restResponse.setResponseEntity(entity);
		
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse).build();

		return response;

	}

}
