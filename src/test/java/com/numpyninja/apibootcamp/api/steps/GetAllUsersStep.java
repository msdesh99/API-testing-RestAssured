package com.numpyninja.apibootcamp.api.steps;

import org.junit.Assert;

import com.numpyninja.apibootcamp.api.endpoints.Endpoints;
import com.numpyninja.apibootcamp.api.payload.User;
import com.numpyninja.apibootcamp.api.utils.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetAllUsersStep {
	ValidatableResponse validResponse;
	RequestSpecification request;
	Response response;
	int userId;
	String scenarioType,userName,endpointType;
	String authStatus;
	
	@Given("User creates GET Request for UserAPI module {string}")
	public void user_creates_get_request_for_user_api_module(String authStatus) {
		this.request = CommonForAllGivenStep.GivenMethod(authStatus);
		this.authStatus = authStatus;
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		this.response = request.when().get(Endpoints.getAllUsersEndpoint);	    
	}
	
	@Given("User creates GET Request for UserAPI module {string} {string} {string}")
	public void user_creates_get_request_for_user_api_module(String authStatus, String endpointType, String scenarioType) {
		this.request = CommonForAllGivenStep.GivenMethod(authStatus);
		this.scenarioType = scenarioType;
		this.authStatus = authStatus;
		this.endpointType = endpointType;
	}
	@When("User sends GET Request with {string}")
	public void user_sends_get_request_with(String input) {
		if(endpointType.contentEquals("UserId"))
			 this.response = request.when().get(Endpoints.getUserByUserIdEndpoint+Integer.valueOf(input));	  
			else 
			 this.response = request.when().get(Endpoints.getUserByFirsttNameEndpoint+input);	  
	}
/*	@When("User sends HTTPS Request with {string}")
	public void user_sends_https_request_with(String input) {
		if(endpointType.contentEquals("UserId"))
		 this.response = request.when().get(Routes.getUserByUserIdEndpoint+Integer.valueOf(input));	  
		else 
		 this.response = request.when().get(Routes.getUserByFirsttNameEndpoint+input);	  
	}*/
	@Then("User receives {string} Status with response body.")
	public void user_receives_status_with_response_body(String statusCode) {
		try {
			if (this.authStatus.contentEquals("NoAuth")) {
				response.then().log().all()
				.assertThat().statusCode(401)
				.header("Content-type", "application/json");
			} else {
				response.then().log().all()
				.header("Content-type", "application/json");
				String jsonString = response.asString();
				int actualResponseCode = response.then().extract().statusCode();
				System.out.println("In Assertion: Actual " + actualResponseCode + " Expected:"
						+ statusCode);
				System.out.println("User Details--->>>>>" + jsonString);
				Assert.assertTrue(actualResponseCode == Integer.valueOf(statusCode));
			}
		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(int statusCd) {
           CommonForAllGivenStep.ThenMethod(statusCd,"GET",this.response);
		   validResponse = response.then().log().all()
		 		.assertThat().statusCode(200).contentType(ContentType.JSON);
		         User[] result = response.then().extract()
		    	      .as(User[].class);
		 		System.out.println("Total USers: "+ result.length);
	    
	}

}
