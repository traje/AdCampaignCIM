package org.tushar.projects.AdCampaign.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.eval.adcampaign.dao.AdCampaignDAOImpl;
import com.eval.adcampaign.service.AdCampaignService;
import com.eval.adcampaign.to.AdCampaign;
import com.eval.adcampaign.utils.CommonUtils;

@Path("/")
public class AdCampaignWebService {
	private final static Logger logger = Logger.getLogger(AdCampaignWebService.class);
	
	protected AdCampaignService adCampaignService;	
	
	public AdCampaignWebService(AdCampaignService adCampaignService) {
		super();
		this.adCampaignService = adCampaignService;
	}

	public AdCampaignWebService() {
		super();
	}	
	
	public AdCampaignService getAdCampaignService() {
		return (null == adCampaignService ?  new AdCampaignService(new AdCampaignDAOImpl()) : adCampaignService);
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String input) {
		logger.debug("entered into create(String input) ");
		AdCampaign adCampaign = CommonUtils.convertStringToJson(input);		
		int result = this.getAdCampaignService().addAdCampaign(adCampaign);
		String response = (result == 0 ? "SUCCESSFULL" : "Partner already had a AdCampaign"); 
		
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/findActive/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findActive(@PathParam("id") final String id) {
		logger.debug("entered into findActive(String input) :"+id);
		
		AdCampaign adCampaign = this.getAdCampaignService().findActiveAdCampaign(id);
		String response = (null == adCampaign ? "No Active Campaign for :"+id : adCampaign.toString());
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") final String id) {
		logger.debug("entered into find(String input) :"+id);
		List<AdCampaign> listOfAdds = this.getAdCampaignService().findAllAdCampaign(id);
		JSONArray jsArray = new JSONArray(listOfAdds);
		
		String response = (listOfAdds.size() == 0 ? "No Campaign Records found for partner :"+id : jsArray.toString());		
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		logger.debug("entered into findAll() ");

		List<AdCampaign> listOfAdds = this.getAdCampaignService().getAllAdCampaigns();
		JSONArray jsArray = new JSONArray(listOfAdds);
		
		String response = (listOfAdds.size() == 0 ? "No Campaign Records found" : jsArray.toString());		
		return Response.status(200).entity(response).build();
	}	
	
	@DELETE
	@Path("/deleteAd/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAd(@PathParam("id") final String input) {
		logger.debug("entered into create(String input) ");
		int result = this.getAdCampaignService().deleteAdCampaign(input);
		String response = (result == 0 ? "SUCCESSFULL" : "Partner does not have a AdCampaign"); 
		
		return Response.status(200).entity(response).build();
	}	
	
	@POST
	@Path("/updateAd/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAd(String input) {
		logger.debug("entered into create(String input) ");
		AdCampaign adCampaign = CommonUtils.convertStringToJson(input);		
		int result = this.getAdCampaignService().updateAdCampaign(adCampaign);
		String response = (result == 0 ? "SUCCESSFULL" : "Partner does not have AdCampaign"); 
		
		return Response.status(200).entity(response).build();
	}
}
