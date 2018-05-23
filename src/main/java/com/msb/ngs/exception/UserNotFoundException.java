package com.msb.ngs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundException extends Exception implements ExceptionMapper<UserNotFoundException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException() {
		super("User with given name not found !!");
	}
	
	
	
	@Override
	public Response toResponse(UserNotFoundException ex) {
		
		return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

	
	
}
