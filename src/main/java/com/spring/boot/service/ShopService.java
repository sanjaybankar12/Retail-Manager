package com.spring.boot.service;

import java.util.Map;

import com.spring.boot.model.Shop;

public interface ShopService {

	public Map addShop(Shop shop);
	public String getLatLongFromShopAddress(String address,String postCode,String googleGeoUrl,String googleApiKey);
}
