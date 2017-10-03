package com.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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

import com.spring.boot.model.Customer;
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
		Map<String,String> latlng=new HashMap<>();
		try{
			 String shopDetails=shop.getShopAddress().getNumber();
			 String postCode=shop.getShopAddress().getPostCode();
			 latlng=shopService.getLatLongFromShopAddress(shopDetails, postCode,googleGeoUrl,googleApiKey);
		}catch(Exception e){
			Logger.getLogger(ShopController.class).log(Level.ERROR,"Exception occur while getting lat & long :"+e);
		}
		
		shop.setLatitude(latlng.getOrDefault("latitude","No Latitude Found"));
		shop.setLongitude(latlng.getOrDefault("longitude","No Longitude Found"));
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
	
	@GetMapping(value="/nearestshop")
	public @ResponseBody ResponseEntity getNearestShop(@Valid Customer customer)
	{
		Shop shop=shopService.getNearByShops(customer.getCustomerLatitude(),customer.getCustomerLongitude());
		
		if(shop==null){
			return new ResponseEntity(shop,HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity(shop,HttpStatus.FOUND);
		}
	}
	
}
