package com.spring.boot.dao;

import java.util.Map;

import com.spring.boot.model.Shop;

public interface ShopDao {

	public Map addShop(Shop shop);
	public String getLatLongFromShopAddress(String address,String postCode);
}
