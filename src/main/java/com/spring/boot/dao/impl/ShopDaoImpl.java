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
		
		Shop oldMap=shopMap.putIfAbsent(shop.getShopName(), shop);
		
		if(oldMap==null)
		{
			Map responseMap = new HashMap();
            responseMap.put("message", "New shop is Created");
            responseMap.put("shop", shop);
            return responseMap;
		}
		
		if(shopMap.replace(shop.getShopName(), oldMap, shop))
		{
			 return null;
		}
		return null;
	}

}
