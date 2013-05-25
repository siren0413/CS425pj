package com.proj425.service;

import java.util.List;

import com.proj425.domain.SunRating;

public interface SunRatingService {


    List<SunRating> findAllSunRatings();
    
    SunRating findSunRatingById(String id);
    
    
    void addSunRating(SunRating sunRating);
    
    
    void updateSunRating(SunRating sunRating);
}
