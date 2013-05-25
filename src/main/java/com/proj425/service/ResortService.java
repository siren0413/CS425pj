package com.proj425.service;

import java.util.List;

import com.proj425.domain.Amenity;
import com.proj425.domain.Page;
import com.proj425.domain.Resort;

public interface ResortService {
	

    List<Resort> findAllResorts();
    
    Resort findResortById(String id);
    
    List<Resort> findResortByCondition(Resort resort);
    
    void addResort(Resort resort);
    
    void deleteResort(String resort_id);
    
    void deleteResortSet(String resort_id_set);
    
    void updateResort(Resort resort);
	
    List<Resort> findResortByAmenity(List<Amenity> amenity_list);
    
    List<Amenity> findAllAmenities();
    
    List<Amenity> findAmenityByResort(String resort_id);
    
}
