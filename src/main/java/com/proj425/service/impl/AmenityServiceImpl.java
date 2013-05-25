package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.AmenityDAO;
import com.proj425.dao.impl.AmenityDAO_Impl;
import com.proj425.domain.Amenity;
import com.proj425.service.AmenityService;
import com.proj425.utils.CommUtils;

public class AmenityServiceImpl implements AmenityService {

	private AmenityDAO dao = new AmenityDAO_Impl();
	
	
	public List<Amenity> findAllAmenitys() {
		return dao.queryAllAmenitys();
	}

	public Amenity findAmenityById(String id) {
		return dao.queryAmenityById(id);
	}
	
	

	public Amenity findAmenityByName(String am_nm) {
		return dao.queryAmenityByName(am_nm);
	}

	public List<Amenity> findAmenityFuzzy(String am_nm) {
		return dao.queryAmenityFuzzy(am_nm);
	}

	public void addAmenity(Amenity amenity) {
		amenity.setAm_id(CommUtils.getId());
		dao.addAmenity(amenity);
	}

}
