package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.CityDAO;
import com.proj425.dao.impl.CityDAO_Impl;
import com.proj425.domain.City;
import com.proj425.service.CityService;
import com.proj425.utils.CommUtils;

public class CityServiceImpl implements CityService {

	private CityDAO dao = new CityDAO_Impl();

	public List<City> findAllCities() {

		return dao.queryAllCities();

	}

	public City findCityById(String city_id) {

		return dao.queryCityById(city_id);
	}

	public List<City> findCityByCondition(City city) {

		return dao.queryCityByCondition(city);

	}

	public void addCity(City city) {

		city.setCity_id(CommUtils.getId());
		dao.addCity(city);

	}

	public void deleteCity(String city_id) {
		dao.deleteCity(city_id);

	}
	
	
	

	public void deleteCitySet(String city_id_set) {
		dao.deleteCitySet(city_id_set);
		
	}

	public void updateCity(City city) {

		dao.updateCity(city);
	}

}
