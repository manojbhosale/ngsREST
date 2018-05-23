package com.msb.ngs.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.msb.ngs.exception.UserNotFoundException;
import com.msb.ngs.model.user.User;
import com.msb.ngs.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/")
@Path("/")
public class UserResource {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ADMIN")
	@ApiOperation(
			value = "Create an user with a role", 
			notes = "Create an user with a role"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "User created successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!")
	})
	public Response createUser(User user){
		//System.out.println(user);
		UserService.createUser(user);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/update_password")	
	@Consumes(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ADMIN")
/*	@ApiOperation(
			value = "Update the users password !!", 
			notes = "Update the users password !!"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Password updated successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!")
	})
*/	public Response updatePassword(User user){
		UserService.updateUser(user.getId(), user.getPassword());
		
		return Response.ok().build();
	}

	@PUT
	@Path("/update_role")
	@Consumes(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ADMIN")
/*	@ApiOperation(
			value = "Update the user role !!", 
			notes = "Update the user role !!"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "User role updated successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!")
	})
*/	public Response updateRole(User user){
		UserService.updateRole(user.getId(), user.getRole());
		return Response.ok().build();
	}

	
	@Path("/delete/{user}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ADMIN")
/*	@ApiOperation(
			value = "Delete a user !!", 
			notes = "Delete a user !!"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 202, message = "User deleted successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!")
	})
*/	public Response deleteUser(@PathParam("user") String name) throws UserNotFoundException{
		UserService.deleteUser(name);
		return Response.status(Status.ACCEPTED).build();
	}
	
	
	
}
