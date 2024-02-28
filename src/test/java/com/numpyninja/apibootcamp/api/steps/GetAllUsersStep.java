package com.numpyninja.apibootcamp.api.steps;

import com.numpyninja.apibootcamp.api.endpoints.Routes;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetAllUsersStep {
	ValidatableResponse valid_response;
	RequestSpecification request;
	Response response;
	
	@Given("User creates GET  Request for  All  all users")
	public void user_creates_get_request_for_all_all_users() {
        RestAssured.baseURI = Routes.base_url;
        this.request = RestAssured
        	           .given().auth().preemptive().basic("Numpy@gmail.com", "userAPI");

		               
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		this.response = request.when().get(Routes.GetAllUsers_Url);

	    
	}

	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(Integer int1) {

		valid_response = response.then().log().all()
		 		.assertThat().statusCode(200).contentType(ContentType.JSON);
	    
	}

}
