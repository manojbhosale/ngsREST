package com.msb.ngs.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.msb.ngs.model.VcfStats;
import com.msb.ngs.model.vcf.ComparisonResult;
import com.msb.ngs.model.vcf.VcfEntry;
import com.msb.ngs.service.VcfService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/")
@Path("/")
public class VcfResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//Implementation with UriInfo
	@Path("/vcfutil")
	@RolesAllowed({"SCIENTIST","TECH"})
	@ApiOperation(
			value = "Get Ts/Tv stats for a VCF", 
			notes = "Get Ts/Tv stats for a VCF"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Stats generated successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!"),
			@ApiResponse(code = 404, message = "VCF not found !!")
	})
	public Response getTsTvStats(@Context UriInfo info) {
		String filename = info.getQueryParameters().get("name").get(0);
		String type = info.getQueryParameters().get("type").get(0);
		File filePath = new File(FileResource.uploadedFileLocationPrefix+filename);
		
		if(!filePath.exists()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		VcfStats stats = VcfService.getTsTvStats(new File(FileResource.uploadedFileLocationPrefix+filename), type.toUpperCase());
	
		return Response.status(Status.OK).entity(stats).build();
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vcfcompare")
	//public Response compareVcf(@QueryParam("leftFile") String leftName, @QueryParam("rightFile") String rightName) {
	//Use of Bean param
	@ApiOperation(
			value = "Compare 2 VCFs", 
			notes = "Compare 2 VCFs"
			)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "VCFs compared successfully !!"),
			@ApiResponse(code = 500, message = "Something went wrong !!"),
			@ApiResponse(code = 404, message = "VCF/s not found !!")
	})
	public Response compareVcf(@BeanParam FileCompareBean files) {
		File leftPath = new File(FileResource.uploadedFileLocationPrefix+files.getLeftName());
		File rightPath = new File(FileResource.uploadedFileLocationPrefix+files.getRightName());
		
		if(!leftPath.exists() || !rightPath.exists()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		ComparisonResult stats = null;
		try {
			stats = VcfService.getComparisonResults(leftPath, rightPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Response.status(Status.NOT_FOUND).build();
		}
	
		return Response.status(Status.OK).entity(stats).build();
		
	}

	
	// Save a previously uploaded VCF
		@POST
		@Path("/savevcf")
		@ApiOperation(
				value = "Save a VCF", 
				notes = "Save a VCF"
				)
		@ApiResponses(value= {
				@ApiResponse(code = 201, message = "VCFs saved successfully !!"),
				@ApiResponse(code = 500, message = "Something went wrong !!"),
				@ApiResponse(code = 404, message = "VCF/s not found !!")
		})
		public Response saveVcf(@QueryParam("name") String name) {
			VcfService.saveUploadedVcf(name);
			
			return Response.status(Status.CREATED).build();
		}
		
		@GET
		@Path("/searchvcf/{quality}/{threshold}")
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				value = "Search a VCF with a threshold ", 
				notes = "Save a VCF"
				)
		@ApiResponses(value= {
				@ApiResponse(code = 201, message = "VCFs saved successfully !!"),
				@ApiResponse(code = 500, message = "Something went wrong !!"),
				@ApiResponse(code = 404, message = "VCF/s not found !!")
		})
		public Response getVariants(@PathParam("quality") String column, @PathParam("threshold") String threshold) {
			List<VcfEntry> filteredVariants = VcfService.getFilteredVariants(column, threshold);
			GenericEntity<List<VcfEntry>> entity = new GenericEntity<List<VcfEntry>>(filteredVariants) {};
			return Response.status(Status.FOUND).entity(entity).build();	
		}


}
