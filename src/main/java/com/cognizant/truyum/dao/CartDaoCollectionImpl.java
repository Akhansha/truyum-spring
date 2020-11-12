package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Component
@ImportResource("classpath:spring-config.xml")
public class CartDaoCollectionImpl implements CartDao{
	@Autowired
	private HashMap<Long, Cart> userCarts;

	public HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}
	public void setUserCarts(HashMap<Long, Cart> userCarts) {
		this.userCarts = userCarts;
	}
	
	public void addCartItem(long userId, long menuItemId) {
		List<MenuItem> lst;
		MenuItemDaoCollectionImpl menuItemImpl = new MenuItemDaoCollectionImpl();
		MenuItemDao menuItemDao = menuItemImpl;
		
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if(userCarts.containsKey(userId)) {
			Cart C = userCarts.get(userId);
			lst = C.getMenuItemList();
			lst.add(menuItem);
			C.setMenuItemList(lst);
			C.setTotal(C.getTotal()+menuItem.getPrice());
			userCarts.put(userId, C);
		}
		else {
			lst = new ArrayList<MenuItem>();
			lst.add(menuItem);
			
			Cart C =new Cart(lst, menuItem.getPrice());
			userCarts.put(userId, C);
		}
		
	}
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException{
		
		Cart C = userCarts.get(userId);
		List<MenuItem> lst = C.getMenuItemList();
		if(lst == null || lst.size()==0) {
			throw new CartEmptyException("Cart is Empty");
		}
		double total = 0.0;
		for(MenuItem m: lst) {
			total = total + m.getPrice();
		}
		C.setTotal(total);
		return lst;
	}
	public void removeCartItem(long userId, long menuItemId) {
		
		Cart C = userCarts.get(userId);
		ListIterator<MenuItem> iterator = C.getMenuItemList().listIterator();
		while(iterator.hasNext()) {
			if(iterator.next().getId()== menuItemId) {
				iterator.remove();
			}
		}
	}
}
