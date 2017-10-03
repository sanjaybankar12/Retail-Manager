package com.spring.boot.model;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Required;

@XmlRootElement
public class Shop {

	private String shopName;
	private ShopAddress shopAddress;
	private String latitude;
	private String longitude;
	
	public String getShopName() {
		return shopName;
	}
	@Required
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	@Required
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	 
	 
}
