package com.msb.ngs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Some thing went wrong. Please try again !!").type(MediaType.TEXT_PLAIN).build();
	}

	
	
}
