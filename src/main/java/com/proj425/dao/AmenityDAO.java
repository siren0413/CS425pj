package com.proj425.dao;

import java.util.List;

import com.proj425.domain.Amenity;

public interface AmenityDAO {
	

	List<Amenity> queryAllAmenitys();

    Amenity queryAmenityById(String amenity_id);
    
    Amenity queryAmenityByName(String am_nm);
    
    List<Amenity> queryAmenityFuzzy(String am_nm);
    
    void addAmenity(Amenity amenity);

    
}
