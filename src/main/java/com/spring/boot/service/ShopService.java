package com.spring.boot.service;

import java.util.Map;

import com.spring.boot.model.Shop;

public interface ShopService {

	public Map addShop(Shop shop);
	public Map<String,String> getLatLongFromShopAddress(String address,String postCode,String googleGeoUrl,String googleApiKey) throws Exception;
	public Shop getNearByShops(double latitude,double longitude);
}
