package com.manish.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.entity.MenuItem;
import com.manish.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo oRepo;
	
	
	public void placeOrder(MenuItem mItem) {
		oRepo.save(mItem);
		
	}


	@Override
	public List<MenuItem> getAllOrder() {
		return oRepo.findAll();
		
	}


	@Override
	public MenuItem updateById(MenuItem menuItem) {	
		return oRepo.save(menuItem);
	}


	@Override
	public MenuItem getById(int id) {
		return oRepo.findById(id).get();
	}


	@Override
	public void deleteById(int id) {
		oRepo.deleteById(id);
	}

	


}
