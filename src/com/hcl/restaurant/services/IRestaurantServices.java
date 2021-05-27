package com.hcl.restaurant.services;

import java.util.List;

import com.hcl.restaurant.beans.Restaurant;
import com.hcl.restaurant.exception.RestaurantException;


public interface IRestaurantServices {
	
	public Restaurant addRestaurant(Restaurant res) throws RestaurantException;
	
	public void updateRestaurant(Restaurant r);

	public void deleteRestaurant(int id);

	public List<Restaurant> searchRestaurant(int id);
	
	public List<Restaurant> displayRestaurant();


}
