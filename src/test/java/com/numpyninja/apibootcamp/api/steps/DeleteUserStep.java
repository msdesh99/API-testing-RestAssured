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

public class DeleteUserStep {
	ValidatableResponse validResponse;
	RequestSpecification request;
	Response response;
	int userId;
	String scenarioType,userName,endpointType;
	String authStatus;
	
	@Given("User creates DELETE Request for UserAPI module {string} {string} {string}")
	public void user_creates_delete_request_for_user_api_module(String authStatus, String endpointType, String scenarioType) {
		this.request = CommonForAllGivenStep.GivenMethod(authStatus);
		this.scenarioType = scenarioType;
		this.authStatus = authStatus;
		this.endpointType = endpointType;
	}

	@When("User sends DELETE Request with {string}")
	public void user_sends_delete_request_with(String input) {
		if(this.endpointType.contentEquals("Id")) {
			System.out.println("in Id delete");
			 this.response = request.when().delete(Endpoints.DeleteUserByUserIdEndpoint+Integer.valueOf(input));	  
		}
			 else 
			 this.response = request.when().delete(Endpoints.DeleteUserByUserFirstNameEndpoint+input); 
	}
	
	@Then("User receives {string} Status with response body for Delete Request.")
	public void user_receives_status_with_response_body_for_delete_request(String statusCode) {
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


}
