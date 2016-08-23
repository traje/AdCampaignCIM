package org.tushar.projects.AdCampaign.service;

import java.util.List;
import org.apache.log4j.Logger;

import com.eval.adcampaign.dao.AdCampaignDAO;
import com.eval.adcampaign.dao.AdCampaignDAOImpl;
import com.eval.adcampaign.to.AdCampaign;
import com.eval.adcampaign.utils.CommonUtils;

public class AdCampaignService {
    private final Logger logger = Logger.getLogger(AdCampaignService.class);   
    
    //@Autowired
	protected AdCampaignDAO adCampaignDAO;	
	
	public AdCampaignService(AdCampaignDAO adCampaignDAO) {
		super();
		this.adCampaignDAO = adCampaignDAO;
	}

	public AdCampaignDAO getAdCampaignDAO() {
		return (null != adCampaignDAO ? adCampaignDAO : new AdCampaignDAOImpl());
	}

	public List<AdCampaign> getAllAdCampaigns() {
		logger.debug("entered getAllAdCampaigns");		
		return getAdCampaignDAO().getAllAdCampaings();
	}

	public List<AdCampaign> findAllAdCampaign(String id) {
		logger.debug("entered findAllAdCampaign id:"+id);
		return getAdCampaignDAO().findByPartnerId(id);
	}
	
	public AdCampaign findActiveAdCampaign(String id) {
		logger.debug("entered findActiveAdCampaign");
		List<AdCampaign> adCampaigns = getAllAdCampaigns();
		for (AdCampaign adCampaign : adCampaigns) {
			if (adCampaign.getPartnerId().equals(id) 
					&& !CommonUtils.isExpired(adCampaign.getCreated().getTime() + (1000 * adCampaign.getDuration())) ) {
				return adCampaign;
			}
		}
		return null;
	}	

	public int addAdCampaign(AdCampaign newAdCampaign) {
		logger.debug("entered addAdCampaign(AdCampaign newAdCampaign)");

		if (null == findActiveAdCampaign(newAdCampaign.getPartnerId())) {
			return getAdCampaignDAO().create(newAdCampaign);
		}
		return 1;
	}

	public int updateAdCampaign(AdCampaign adCampaign) {
		logger.debug("entered updateAdCampaign(AdCampaign newAdCampaign)");
		return getAdCampaignDAO().update(adCampaign);
	}

	public int deleteAdCampaign(String id) {
		logger.debug("entered deleteAdCampaign(String id)");
		return adCampaignDAO.delete(id);
	}    
}
