package org.tushar.projects.AdCampaign.dao;

import java.util.List;

import com.eval.adcampaign.persistence.AdCampaignInMemoryFile;
import com.eval.adcampaign.to.AdCampaign;

public interface AdCampaignDAO {
	public AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
	
    public int create(AdCampaign adCampaign); 
    
    public List<AdCampaign> findByPartnerId(String id);
    
    public List<AdCampaign> getAllAdCampaings();
    
    
    public int update(AdCampaign adCampaign);
    
    public int delete(String id) ;
    
}
