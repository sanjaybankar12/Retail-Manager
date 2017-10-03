package com.spring.boot.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dao.*;
import com.spring.boot.model.Shop;
import com.spring.boot.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao ShopDao;
	
	public ShopDao getShopDao() {
		return ShopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		ShopDao = shopDao;
	}

	@Override
	public Map addShop(Shop shop) {
		return ShopDao.addShop(shop);
	}

	@Override
	public Map<String,String> getLatLongFromShopAddress(String address, String postCode,String googleGeoUrl,String googleApiKey) throws Exception {
		// TODO Auto-generated method stub
		return ShopDao.getLatLongFromShopAddress(address, postCode,googleGeoUrl,googleApiKey);
	}

}
