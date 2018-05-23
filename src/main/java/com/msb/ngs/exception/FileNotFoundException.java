package com.msb.ngs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FileNotFoundException extends Exception implements ExceptionMapper<FileNotFoundException> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FileNotFoundException(String msg) {
		super(msg);
	}
	
	
	public FileNotFoundException() {
		super("File with given name not found !!");
	}


	@Override
	public Response toResponse(FileNotFoundException ex) {
		return Response.status(Status.NOT_FOUND).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}
	
	
}
