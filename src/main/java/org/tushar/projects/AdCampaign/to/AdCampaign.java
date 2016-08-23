package org.tushar.projects.AdCampaign.to;

import java.io.Serializable;
import java.util.Date;

public class AdCampaign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8546594321297015524L;
	private String partnerId;
	private int duration;
	private String adContent;
	private Date created;
	
	public AdCampaign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdCampaign(String partnerId, int duration, String adContent, Date created) {
		super();
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
		this.created = created;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "AdCampaign [partnerId=" + partnerId + ", duration=" + duration + ", adContent=" + adContent
				+ ", created=" + created + "]";
	}
}

