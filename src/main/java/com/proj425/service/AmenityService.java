package com.proj425.service;

import java.util.List;

import com.proj425.domain.Amenity;

public interface AmenityService {

	List<Amenity> findAllAmenitys();

	Amenity findAmenityById(String id);

	Amenity findAmenityByName(String am_nm);

	List<Amenity> findAmenityFuzzy(String am_nm);

	void addAmenity(Amenity amenity);

}
