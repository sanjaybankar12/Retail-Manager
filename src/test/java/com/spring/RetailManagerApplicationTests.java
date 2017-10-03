package com.spring;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.model.Shop;
import com.spring.boot.model.ShopAddress;
import com.spring.boot.service.ShopService;
import com.spring.boot.service.impl.ShopServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailManagerApplicationTests {	
	
	@Autowired	
	private ShopService shopService;
	
	@Test
	public void contextLoads() {
		
		ShopAddress shopAdr=new ShopAddress();
		shopAdr.setNumber("Relience Mart,Kharadi");
		shopAdr.setPostCode("411014");
		
		Shop shop = new Shop();
		shop.setShopName("RelienceMart");
		shop.setShopAddress(shopAdr);
		
		Map firstShop = shopService.addShop(shop);
		
		assertEquals(firstShop.containsKey("OldShopDetails"),false);

	}

}
