package org.tushar.projects.AdCampaign.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eval.adcampaign.to.AdCampaign;

public class AdCampaignInMemoryFile {
	private String dataFileName = "c:\\temp\\AdCampaigns.dat"; 
	
	private final static Logger logger = Logger.getLogger(AdCampaignInMemoryFile.class);

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public List<AdCampaign> getAllAdCampaigns() {
		logger.debug("entered getAllAdCampaigns");
		logger.debug("Your size this.dataFileName:"+this.dataFileName);
		List<AdCampaign> adCampaignList = new ArrayList<AdCampaign>();
		try {
			File file = new File(this.dataFileName);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				adCampaignList = (List<AdCampaign>) ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return adCampaignList;
	}

	public List<AdCampaign> findAllAdCampaign(String id) {
		logger.debug("entered findAllAdCampaign id:"+id);
		List<AdCampaign> adCampaigns = getAllAdCampaigns();
		List<AdCampaign> partnerAdCampaigns = new ArrayList<AdCampaign>();

		for (AdCampaign adCampaign : adCampaigns) {
			if (adCampaign.getPartnerId().equals(id)) {				
				partnerAdCampaigns.add(adCampaign);
			}
		}
		return partnerAdCampaigns;
	}

	public int create(AdCampaign newAdCampaign) {
		logger.debug("entered addAdCampaign(AdCampaign newAdCampaign)");
		List<AdCampaign> adCampaignList = getAllAdCampaigns();		
		adCampaignList.add(newAdCampaign);
		saveAdCampaignList(adCampaignList);		
		logger.debug("Your data is saved in :"+this.getDataFileName());
		logger.debug("Your data saved size :"+adCampaignList.size());
		logger.debug("Your size :"+getAllAdCampaigns().size());
		return 0;
	}

	public int updateAdCampaign(AdCampaign pAdCampaign) {
		logger.debug("entered updateAdCampaign(AdCampaign newAdCampaign)");
		List<AdCampaign> adCampaignList = getAllAdCampaigns();

		for (AdCampaign adCampaign : adCampaignList) {
			if (adCampaign.getPartnerId().equals(pAdCampaign.getPartnerId())) {
				int index = adCampaignList.indexOf(adCampaign);
				adCampaignList.set(index, pAdCampaign);
				saveAdCampaignList(adCampaignList);
				return 1;
			}
		}
		return 0;
	}

	public int deleteAdCampaign(String id) {
		logger.debug("entered deleteAdCampaign(String id)");
		List<AdCampaign> adCampaignList = getAllAdCampaigns();
		logger.debug("entered deleteAdCampaign after before :"+adCampaignList.size());

		for (AdCampaign adCampaign : adCampaignList) {
			if (adCampaign.getPartnerId().equals(id)) {
				logger.debug("entered deleteAdCampaign found :"+id);
				int index = adCampaignList.indexOf(adCampaign);
				adCampaignList.remove(index);
				logger.debug("entered deleteAdCampaign after delete :"+adCampaignList.size());
				saveAdCampaignList(adCampaignList);
				return 1;
			}
		}
		return 0;
	}

	private void saveAdCampaignList(List<AdCampaign> adCampaignList) {
		logger.debug("saveAdCampaignList(List<AdCampaign> adCampaignList)");
		try {
			File file = new File("c:\\temp\\AdCampaigns.dat");
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(adCampaignList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}