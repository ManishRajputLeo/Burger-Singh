package com.manish.service;

import java.util.List;

import com.manish.entity.MenuItem;

public interface OrderService {

	public void placeOrder(MenuItem mItem);

	public List<MenuItem> getAllOrder();
	
	public MenuItem updateById(MenuItem menuItem);
	
	public MenuItem getById(int id);
	
	public void deleteById(int id);

}
