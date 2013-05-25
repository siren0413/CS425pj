package com.proj425.dao;

import java.util.List;

import com.proj425.domain.City;

public interface CityDAO {
	
	List<City> queryAllCities();
	
	City queryCityById(String city_id);
	
	List<City> queryCityByCondition(City city);
	
	void addCity(City city);
	
	void deleteCity(String city_id);
	
	void deleteCitySet(String city_id_set);
	
	void updateCity(City city);
	

}
