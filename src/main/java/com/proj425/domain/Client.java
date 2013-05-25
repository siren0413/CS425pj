package com.proj425.domain;

import java.util.Date;

public class Client {
	private String client_id;
	private String phone_number;
	private String email;
	private String first_nm;
	private String last_nm;
	private Date dob;
	private String zip;
	private String gender;
	
	public Client() {
		super();
	}

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_nm() {
        return first_nm;
    }

    public void setFirst_nm(String first_nm) {
        this.first_nm = first_nm;
    }

    public String getLast_nm() {
        return last_nm;
    }

    public void setLast_nm(String last_nm) {
        this.last_nm = last_nm;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
