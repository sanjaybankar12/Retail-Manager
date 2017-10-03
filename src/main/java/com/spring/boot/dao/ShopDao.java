package com.spring.boot.dao;

import java.util.Map;

import com.spring.boot.model.Shop;

public interface ShopDao {

	public Map addShop(Shop shop);
	public Map<String,String> getLatLongFromShopAddress(String address,String postCode,String googleGeoUrl,String googleApiKey) throws Exception;
}
