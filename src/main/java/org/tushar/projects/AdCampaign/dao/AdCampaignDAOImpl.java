package org.tushar.projects.AdCampaign.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eval.adcampaign.persistence.AdCampaignInMemoryFile;
import com.eval.adcampaign.to.AdCampaign;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class AdCampaignDAOImpl implements AdCampaignDAO {
	private final Logger logger = Logger.getLogger(AdCampaignDAOImpl.class); 
	public AdCampaignInMemoryFile inMemory = new AdCampaignInMemoryFile();
	public CacheManager cm = CacheManager.getInstance();
	
    public int create(AdCampaign adCampaign) {
    	logger.debug("entered newAdCampaign(AdCampaign adCampaign)");    	
    	Cache cache = cm.getCache("cacheAds");
    	if(null == cache){
    		cm.addCache("cacheAds");	
    	}
    	cache = cm.getCache("cacheAds");   	
    	List<AdCampaign> list = new ArrayList<AdCampaign>();
    	Element element = cache.get(adCampaign.getPartnerId());
    	if(null != element) {
    		list = (ArrayList<AdCampaign>)element.getObjectValue();
    		list.add(adCampaign);    		    		
    	} else {
    		list.add(adCampaign);
    	}		
		cache.put(new Element(adCampaign.getPartnerId(), list));		
		return 0;
	}
    
    public List<AdCampaign> findByPartnerId(String id) {
    	logger.debug("entered findAdCampaign(String id)");
    	
    	List<AdCampaign> returnList = new ArrayList<AdCampaign>();    	
    	Cache cache = cm.getCache("cacheAds");
    	if(null != cache) {
	    	Element element = cache.get(id);
	    	if(null != element) {
	    		returnList = (ArrayList<AdCampaign>)element.getObjectValue();    		
	    	}    	
    	}
    	return returnList;    	
		//return inMemory.findAllAdCampaign(id);
	}
    
    public List<AdCampaign> getAllAdCampaings() {
    	logger.debug("entered getAllAdCampaings");
    	List<AdCampaign> allList = new ArrayList<AdCampaign>();
    	
    	Cache cache = cm.getCache("cacheAds");
    	if(null != cache) {
        	List<String> keyList  = cache.getKeys();
        	
        	for(String key : keyList){
        		Element element = cache.get(key);
        		allList.addAll((ArrayList<AdCampaign>)element.getObjectValue());
        	}
    	}
    	return allList;
		//return inMemory.getAllAdCampaigns();
	} 
    
    
    public int update(AdCampaign adCampaign) {	
    	logger.debug("entered updateAdCampaign(AdCampaign adCampaign)");    	
    	Cache cache = cm.getCache("cacheAds");
    	List<AdCampaign> list = new ArrayList<AdCampaign>();
    	Element element = cache.get(adCampaign.getPartnerId());
    	if(null != element) {
    		list = (ArrayList<AdCampaign>)element.getObjectValue();
    		list.add(adCampaign);    		    		
    	} else {
    		list.add(adCampaign);
    	}		
		cache.put(new Element(adCampaign.getPartnerId(), list));		
		return 0;
	} 
    
    public int delete(String id) {
    	logger.debug("entered delete(AdCampaign adCampaign)");
    	Cache cache = cm.getCache("cacheAds");
    	List<AdCampaign> list = new ArrayList<AdCampaign>();
    	Element element = cache.get(id);    		
		cache.put(new Element(id, list));		
		return 0;
	}
    
}
