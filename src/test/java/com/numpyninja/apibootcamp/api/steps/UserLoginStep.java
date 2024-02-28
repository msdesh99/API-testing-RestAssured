package com.numpyninja.apibootcamp.api.steps;

import com.numpyninja.apibootcamp.api.endpoints.Routes;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;

public class UserLoginStep {
	RequestSpecification  request;
	
	@Given("User passing credentials for authentication.")
	public void user_passing_credentials_for_authentication() {
	       RestAssured.baseURI = Routes.base_url;
	       this.request = RestAssured.given().auth().preemptive().basic("Numpy@gmail.com", "userAPI");

	}

	@When("User sends username and  password with {string} and {string} from excel.")
	public void user_sends_username_and_password_with_and_from_excel(String string, String string2) {
	}

	@Then("User will be Authorized")
	public void user_will_be_authorized() {
	}
	
}
	
