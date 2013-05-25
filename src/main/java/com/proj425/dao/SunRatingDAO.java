package com.proj425.dao;

import java.util.List;

import com.proj425.domain.SunRating;

public interface SunRatingDAO {
	
	List<SunRating> queryAllSunRatings();

    SunRating querySunRatingById(String sunRating_id);
    
    void addSunRating(SunRating sunRating);

    void updateSunRating(SunRating sunRating);
    
}
