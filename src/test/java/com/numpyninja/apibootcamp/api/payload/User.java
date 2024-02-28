package com.numpyninja.apibootcamp.api.payload;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSetter;

public class User {
	
	    //POJO Plain old java object
	  String user_first_name;
	  String user_last_name;       
	  String user_contact_number;
	  String user_email_id;
	
	  Map<String, String> userAddress  = new HashMap<String, String>();
	
	public String getUser_first_name() {
		return user_first_name;
	}
	@JsonSetter("user_first_name")

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}
	@JsonSetter("user_last_name")
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_contact_number() {
		return user_contact_number;
	}
	
	@JsonSetter("user_contact_number")
	public void setUser_contact_number(String user_contact_number) {
		this.user_contact_number = user_contact_number;
	}
	public String getUser_email_id() {
		return user_email_id;
	}
	@JsonSetter("user_email_id")
	public void setUser_email_id(String user_email_id) {
		this.user_email_id = user_email_id;
	}
	public Map<String, String> getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Map<String, String> userAddress) {
		this.userAddress = userAddress;
	}
	

}
