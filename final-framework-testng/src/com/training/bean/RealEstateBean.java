package com.training.bean;

public class RealEstateBean {	
	
	private String regionName;
	private String regionSlug;
	private String parentRegion;
	private String regionDescription;
	
	public RealEstateBean() {
		
	}
	
	public RealEstateBean(String regionName, String regionSlug, String parentRegion, String regionDescription) {
		super();
		this.regionName = regionName;
		this.regionSlug = regionSlug;
		this.parentRegion = parentRegion;
		this.regionDescription = regionDescription;
	}
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionSlug() {
		return regionSlug;
	}
	public void setRegionSlug(String regionSlug) {
		this.regionSlug = regionSlug;
	}
	public String getParentRegion() {
		return parentRegion;
	}
	public void setParentRegion(String parentRegion) {
		this.parentRegion = parentRegion;
	}
	public String getRegionDescription() {
		return regionDescription;
	}
	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

	@Override
	public String toString() {
		return "RealEstateBean [regionName=" + regionName + ", regionSlug=" + regionSlug + ", parentRegion="
				+ parentRegion + ", regionDescription=" + regionDescription + "]";
	}
	
	
	
}
