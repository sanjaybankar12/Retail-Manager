package com.spring.boot.controller;

import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.model.Shop;
import com.spring.boot.service.ShopService;

@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	public ShopService getShopService() {
		return shopService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	
	@Value("${google.geo.url}")
	private String googleGeoUrl;
	
	@Value("${google.api.key}")
	private String googleApiKey;
	
	@GetMapping(value="/getshops")
	public @ResponseBody String getShops()
	{
		return "sanjay";
	}
	
	@PostMapping(value="/shops")
	public @ResponseBody ResponseEntity addShop(@RequestBody Shop shop)
	{
		try{
			 String shopDetails=shop.getShopAddress().getNumber();
			 String postCode=shop.getShopAddress().getPostCode();
			 String latlong=shopService.getLatLongFromShopAddress(shopDetails, postCode,googleGeoUrl,googleApiKey);
		}catch(Exception e){
			Logger.getLogger(ShopController.class).log(Level.ERROR,"Exception occur while getting lat & long :"+e);
		}
		 Map responseMap=shopService.addShop(shop);
		 if(responseMap.containsKey("OldShopDetails"))
		 {
			 return new ResponseEntity(responseMap,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity(responseMap,HttpStatus.CREATED);
		 }
		 
	}
	
}
