package com.cognizant.truyum.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

public class CartItemServiceTest {
	
	CartService cartService;
	
	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.cognizant.truyum");
		context.refresh();
		cartService = context.getBean(CartService.class);
		
	}
	@Test(expected = CartEmptyException.class)
	public void testGetAllCartItems() throws CartEmptyException {
		List<MenuItem> menuItemList = cartService.getAllCartItems(1);
		assertTrue(!menuItemList.isEmpty());
	}
	
	@Test(expected = CartEmptyException.class)
	public void testAddCartItem() throws CartEmptyException {
		boolean added = false;
		List<MenuItem> menuList = cartService.getAllCartItems(1);
 
        cartService.addCartItem(1, 1);
        for(MenuItem item : cartService.getAllCartItems(1)) {
            if(item.getName().equals("Sandwich")) {
                added = true;
            }
        }
        assertTrue(added);
	}
	
	@Test(expected = CartEmptyException.class)
	public void testRemoveCartItem() throws CartEmptyException {
		boolean added = false;
		List<MenuItem> menuList = cartService.getAllCartItems(1);
 
        cartService.removeCartItem(1, 1);
        for(MenuItem item : cartService.getAllCartItems(1)) {
            if(item.getName().equals("Sandwich")) {
                added = true;
            }
        }
        assertFalse(added);
	}
}
