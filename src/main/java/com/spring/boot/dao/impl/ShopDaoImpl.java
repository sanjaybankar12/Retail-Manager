package com.spring.boot.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.spring.boot.dao.ShopDao;
import com.spring.boot.model.Shop;

@Repository
public class ShopDaoImpl implements ShopDao{

	public Map<String,Shop> shopMap = new ConcurrentHashMap<>();
	
	@Override
	public Map addShop(Shop shop) {
		
		return null;
	}

}
