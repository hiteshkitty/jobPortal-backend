package com.troika.aggregator.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.troika.domain.model.City;
import com.troika.domain.model.EducationalDegree;
import com.troika.domain.model.SkillSet;
import com.troika.domain.view.RestResponse;

@Path("masterdata")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface ManageMasterDataAggService {

	@POST
	@Path("/skillset/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse addSkillSet(SkillSet skillSet);

	@POST
	@Path("/educationaldegree/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse addEducationalDegree(EducationalDegree educationalDegree);

	@POST
	@Path("/city/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse addCity(City city);

	@DELETE
	@Path("/skillset/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse deleteSkillSet(SkillSet skillSet);

	@DELETE
	@Path("/educationaldegree/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse deleteEducationalDegree(EducationalDegree educationalDegree);

	@DELETE
	@Path("/city/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse deleteCity(City city);

	@PUT
	@Path("/skillset/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse changeStatusSkillSet(SkillSet skillSet);

	@PUT
	@Path("/educationaldegree/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse changeStatusEducationalDegree(EducationalDegree educationalDegree);

	@PUT
	@Path("/city/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse changeStatusCity(City city);

	@PUT
	@Path("/update/skillset/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse updateSkillSet(SkillSet skillSet);

	@PUT
	@Path("/update/educationaldegree/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse updateEducationalDegree(EducationalDegree educationalDegree);

	@PUT
	@Path("/update/city/")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	RestResponse updateCity(City city);

}
