package com.hcl.restaurant.services;

import java.util.List;

import com.hcl.restaurant.beans.Restaurant;
import com.hcl.restaurant.dao.RestaurantDAO;
import com.hcl.restaurant.exception.RestaurantException;

public class RestaurantServicesImpl implements IRestaurantServices {

	@Override
	public Restaurant addRestaurant(Restaurant res) throws RestaurantException {
		RestaurantDAO resDAO=new RestaurantDAO();
		return resDAO.addRestaurant(res);
	}

	@Override
	public void updateRestaurant(Restaurant r){
		// TODO Auto-generated method stub
		RestaurantDAO resDAO=new RestaurantDAO();
		resDAO.updateRestaurant(r);
		
	}

	@Override
	public void deleteRestaurant(int id) {
		RestaurantDAO resDAO=new RestaurantDAO();
		resDAO.deleteRestaurant(id);
		
	}

	@Override
	public List<Restaurant> searchRestaurant(int id) {
		RestaurantDAO resDAO=new RestaurantDAO();
		return resDAO.searchRestaurant(id);
	}

	@Override
	public List<Restaurant> displayRestaurant() {
		RestaurantDAO resDAO=new RestaurantDAO();
		return resDAO.displayRestaurant();
	}
	

}
