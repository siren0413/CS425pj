package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.ResortDAO;
import com.proj425.dao.impl.ResortDAO_Impl;
import com.proj425.domain.Amenity;
import com.proj425.domain.City;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.service.ResortService;
import com.proj425.service.SunRatingService;
import com.proj425.utils.CommUtils;

public class ResortServiceImpl implements ResortService {

	private ResortDAO dao = new ResortDAO_Impl();

	public List<Resort> findAllResorts() {

		return dao.queryAllResorts();
	}

	public Resort findResortById(String id) {
		return dao.queryResortById(id);
	}

	public List<Resort> findResortByCondition(Resort resort) {

		return dao.queryResortByCondition(resort);
	}

	public void addResort(Resort resort) {
		
		resort.setResort_id(CommUtils.getId()); // set ID.

		City city = resort.getCity();

		CityServiceImpl cityService = new CityServiceImpl();
		List<City> city_list = cityService.findCityByCondition(city);
		if (city_list == null || city_list.size() == 0) {
			cityService.addCity(city);
		}else {
			city.setCity_id(city_list.get(0).getCity_id());
		}

		dao.addResort(resort);
	
	}

	public void deleteResort(String resort_id) {

		dao.deleteResort(resort_id);
	}

	public void deleteResortSet(String resort_id_set) {

		dao.deleteResortSet(resort_id_set);

	}

	public void updateResort(Resort resort) {
		
		City city = resort.getCity();

		CityServiceImpl cityService = new CityServiceImpl();
		List<City> city_list = cityService.findCityByCondition(city);
		if (city_list == null || city_list.size() == 0) {
			cityService.addCity(city);
		}else {
			city.setCity_id(city_list.get(0).getCity_id());
		}
		
		dao.updateResort(resort);
		
	}

	public List<Resort> findResortByAmenity(List<Amenity> amenity_list) {
		return dao.queryResortByAmenity(amenity_list);
	}

	public List<Amenity> findAllAmenities() {
		return dao.queryAllAmenities();
	}

	public List<Amenity> findAmenityByResort(String resort_id) {
		return dao.queryAmenitiyByResort(resort_id);
	}
	
	
	

}
