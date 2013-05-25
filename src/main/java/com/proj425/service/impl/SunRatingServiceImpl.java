package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.SunRatingDAO;
import com.proj425.dao.impl.SunRatingDAO_Impl;
import com.proj425.domain.SunRating;
import com.proj425.service.SunRatingService;

public class SunRatingServiceImpl implements SunRatingService {

	private SunRatingDAO dao = new SunRatingDAO_Impl();
	
	public List<SunRating> findAllSunRatings() {
		return dao.queryAllSunRatings();
	}

	public SunRating findSunRatingById(String id) {
		return dao.querySunRatingById(id);
	}

	public void addSunRating(SunRating sunRating) {
		dao.addSunRating(sunRating);
	}

	public void updateSunRating(SunRating sunRating) {
		dao.updateSunRating(sunRating);
	}

}
