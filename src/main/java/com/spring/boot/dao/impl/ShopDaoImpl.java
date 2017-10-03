package com.spring.boot.dao.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
	
	@Override
	public Map<String,String> getLatLongFromShopAddress(String address,String postCode,String googleGeoUrl,String googleApiKey) throws Exception
	{
		Map<String,String> latlng=new HashMap<>();
		String trimAddress="";
		for(char ch:address.toCharArray())
		{
			trimAddress+=(ch==' ')?"":ch;
		}
		String googleApiLink=googleGeoUrl+"address="+trimAddress+"&components=postal_code:"+postCode+"&key="+googleApiKey;
		
		URL url=new URL(googleApiLink);
		HttpURLConnection huc=(HttpURLConnection)url.openConnection();
		huc.connect();
		String msg="";
		if(huc.getResponseCode()==200)
		{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder=factory.newDocumentBuilder();
			Document document=documentBuilder.parse(huc.getInputStream());
			
			XPathFactory xPathFactory=XPathFactory.newInstance();
			XPath xPath=xPathFactory.newXPath();
			
			XPathExpression xPathExp=xPath.compile("/GeocodeResponse/status");
			String status=xPathExp.evaluate(document,XPathConstants.STRING).toString();
			if(status.equals("OK"))
			{
				xPathExp=xPath.compile("//geometry/location/lat");
				String lat=xPathExp.evaluate(document,XPathConstants.STRING).toString();
				xPathExp=xPath.compile("//geometry/location/lng");
				String lng=xPathExp.evaluate(document,XPathConstants.STRING).toString();
			
				latlng.put("latitude", lat);
				latlng.put("longitude", lng);
				
				msg=lat+"_"+lng;
			}			
		}
		return latlng;
	}

}
