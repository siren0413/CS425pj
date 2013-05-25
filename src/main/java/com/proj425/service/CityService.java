package com.proj425.service;

import java.util.List;

import com.proj425.domain.City;

public interface CityService {
	 List<City> findAllCities();
	    
	    City findCityById(String city_id);
	    
	    List<City> findCityByCondition(City city);
	    
	    void addCity(City city);
	    
	    void deleteCity(String city_id);
	    
	    void deleteCitySet(String city_id_set);
	    
	    void updateCity(City city);
}
