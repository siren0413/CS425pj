package com.proj425.domain;

public class Resort {
	
	private String resort_id;
	private String resort_nm;
	private City city;
	private String phone_number;
	private String rating_id;
	private String address;
	private SunRating rating;
	
	public Resort() {
		super();
	}

	public String getResort_id() {
		return resort_id;
	}

	public void setResort_id(String resort_id) {
		this.resort_id = resort_id;
	}

	public String getResort_nm() {
		return resort_nm;
	}

	public void setResort_nm(String resort_nm) {
		this.resort_nm = resort_nm;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getRating_id() {
		return rating_id;
	}

	public void setRating_id(String rating_id) {
		this.rating_id = rating_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SunRating getRating() {
		return rating;
	}

	public void setRating(SunRating rating) {
		this.rating = rating;
	}

	
	
	
	
	
}
