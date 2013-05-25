package com.proj425.dao;

import java.util.List;

import com.proj425.domain.Amenity;
import com.proj425.domain.Page;
import com.proj425.domain.Resort;

public interface ResortDAO {
	
	List<Resort> queryAllResorts();

    Resort queryResortById(String resort_id);
    
    List<Resort> queryResortByCondition(Resort resort);
    
    void addResort(Resort resort);

    void deleteResort(String resort_id);
    
    void deleteResortSet(String resort_id_set);

    void updateResort(Resort resort);
	
    List<Resort> queryResortByAmenity(List<Amenity> amenity_list);
    
    List<Amenity> queryAllAmenities();
    
    List<Amenity> queryAmenitiyByResort(String resort_id);
    
    

}
