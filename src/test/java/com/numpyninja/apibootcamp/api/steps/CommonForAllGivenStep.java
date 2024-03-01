package com.numpyninja.apibootcamp.api.steps;

import com.numpyninja.apibootcamp.api.endpoints.Endpoints;
import com.numpyninja.apibootcamp.api.utils.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CommonForAllGivenStep {
	String authStatus;
	public static RequestSpecification GivenMethod(String authStatus) {
		RequestSpecification  request = null;
	    try {		
			System.out.println("authstat: "+authStatus);

			 RestAssured.baseURI = Endpoints.baseUrl;
			if(authStatus.contentEquals("NoAuth")) {
				System.out.println("authstat in if: "+authStatus);
				  request = RestAssured
		 			        .given()
	        				.log().all();

			}else {
		     request = RestAssured
			 			        .given()
			 			        .auth().preemptive().basic("Numpy@gmail.com", "userAPI")
			 			        //.multiPart("file",file1,"multipart/form-data")
		        				 .header("Content-Type","application/json; charset=UTF-8")
		        				.log().all();
			}
		     LoggerLoad.logInfo("Update User With "+authStatus);
				return request;

			}catch (Exception ex) {
			      LoggerLoad.logInfo(ex.getMessage());
			      ex.printStackTrace();
			} 		
		return request;

	} 
     public static void ThenMethod(int StatusCode,String method, Response response) {
    	 ValidatableResponse validResponse;
    	 validResponse = response.then().log().all()
 		 		.assertThat().statusCode(200).contentType(ContentType.JSON);
    	 System.out.println("ValidatableResponse "+ validResponse);
    	 
     }
}
	
