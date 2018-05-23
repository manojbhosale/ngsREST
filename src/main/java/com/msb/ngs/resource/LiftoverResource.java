package com.msb.ngs.resource;

import java.io.IOException;
import java.util.SortedSet;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.msb.ngs.model.liftover.BedInterval;
import com.msb.ngs.model.liftover.ChainException;
import com.msb.ngs.service.LiftOverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/")
@Path("/")
public class LiftoverResource {


	//For without strand information
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/liftover")
	@ApiOperation(
			value = "Liftover chromosome cordinates from hg19 to hg38", 
			notes = "Liftover chromosome cordinates from hg19 to hg38"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Converted coordinates successfullly !!"),
			@ApiResponse(code = 500, message = "Failed conversion !!")
	})
	@RolesAllowed("SCIENTIST")
	public Response liftOverCoordinate(@QueryParam("chr") String chromosome, @QueryParam("start") String start, @QueryParam("stop") String stop) {
		SortedSet<BedInterval> res = null;
		GenericEntity<SortedSet<BedInterval>> entity = null;		 
		try {
			res = LiftOverService.convertCoordinate(chromosome, Integer.parseInt(start), Integer.parseInt(stop));
			entity = new GenericEntity<SortedSet<BedInterval>>(res){};
		} catch (NumberFormatException | ChainException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity(entity).build();
	}


	// For strand information
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/liftoverstr")
	@ApiOperation(
			value = "Liftover chromosome cordinates from hg19 to hg38 with strand", 
			notes = "Liftover chromosome cordinates from hg19 to hg38 with strand"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Converted coordinates successfullly !!"),
			@ApiResponse(code = 500, message = "Failed conversion !!")
	})
	public Response liftOverCoordinateStrand(@QueryParam("chr") String chromosome, @QueryParam("start") String start, @QueryParam("stop") String stop, @QueryParam("strand") String strand) {
		SortedSet<BedInterval> res = null;
		GenericEntity<SortedSet<BedInterval>> entity = null;
		try {
			res = LiftOverService.convertCoordinate(chromosome, Integer.parseInt(start), Integer.parseInt(stop), strand);
			entity= new GenericEntity<SortedSet<BedInterval>>(res) {};

		} catch (NumberFormatException | ChainException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.OK).entity(entity).build();
	}


}

