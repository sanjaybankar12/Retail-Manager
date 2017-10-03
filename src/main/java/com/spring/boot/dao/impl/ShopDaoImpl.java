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
		for(;;)
		{
			Shop oldShop=shopMap.putIfAbsent(shop.getShopName(), shop);
			
			if(oldShop==null)
			{
				Map responseMap = new HashMap();
	            responseMap.put("message", "New shop is Created");
	            responseMap.put("shop", shop);
	            return responseMap;
			}
			
			if(shopMap.replace(shop.getShopName(), oldShop, shop))
			{
				Map responseMap = new HashMap();
	            responseMap.put("message", "Shop details are Updated");
	            Map newShop = new HashMap();
	            newShop.put("shop", shop);
	
	            responseMap.put("NewShopDetails", newShop);
	            Map prevShop = new HashMap();
	            prevShop.put("shop", oldShop);
	            responseMap.put("OldShopDetails",prevShop);
	            return responseMap;
			}
		}
	}

}
