package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.dao.CartDaoCollectionImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

@Component
public class CartService {
    @Autowired
	private CartDaoCollectionImpl carts;
    
    
    public CartDaoCollectionImpl getCarts() {
		return carts;
	}

	public void setCarts(CartDaoCollectionImpl carts) {
		this.carts = carts;
	}

	public void addCartItem(long userId, long menuItemId){
    	carts.addCartItem(userId, menuItemId);
    }
  
    public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException{
    	return carts.getAllCartItems(userId);
    }
    
    public void removeCartItem(long userId, long menuItemId){
    	carts.removeCartItem(userId, menuItemId);
    }
	 
}
