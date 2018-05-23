package com.msb.ngs.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value="/ngs")
@Path("/ngs")
public class MainResource {

	@RolesAllowed("TECH")
	@GET
	@ApiOperation(
			value = "Set a cookie for 10 secs", 
			notes = "Set a cookie for 10 secs"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successful retrieval of employees")

	})
	@Produces(MediaType.TEXT_PLAIN)
	public Response getIt(@CookieParam("name") String cName) {
		if(cName != null) {
			return Response.ok("Cookie is "+cName).build();
		}else {
			NewCookie nc = new NewCookie(new Cookie("name", cName), "testComment", 10, false);
			//nc.
			return Response.ok("No Cookie is set").cookie(nc).build();
		}

	}
	@Path("/user")
	public UserResource getUserResource() {
		return new UserResource();
	}
	@Path("/liftover")
	public LiftoverResource getLiftoverResource() {
		return new LiftoverResource();
	}


	@Path("/file")
	@RolesAllowed("TECH")
	public FileResource getFileResource() {
		return new FileResource();
	}

	@Path("/vcf")
	@RolesAllowed("SCIENTIST")
	public VcfResource getVcfResource() {
		return new VcfResource();
	}


}
