package com.numpyninja.apibootcamp.api.steps;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numpyninja.apibootcamp.api.endpoints.Routes;
import com.numpyninja.apibootcamp.api.payload.User;
import com.numpyninja.apibootcamp.api.requestbody.UserRequestBody;
import com.numpyninja.apibootcamp.api.utils.LoggerLoad;
import com.numpyninja.apibootcamp.api.utils.XLUtility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class CreateUserStep {
	RequestSpecification request;
	Response response;
	ValidatableResponse validResponse;
	int userId;
	User user = new User();
	File file1 = new File(System.getProperty("user.dir") + "/src/test/resources/testData/UserPostData.xlsx");


	
	@Given("User creates POST Request to create a user")
	public void user_creates_post_request_to_create_a_user() {		

		try {		
			 System.out.println("file: "+file1);
			 RestAssured.baseURI = Routes.base_url;
		     this.request = RestAssured
			 			        .given()
			 			        //.multiPart("file",file1,"multipart/form-data")
			 			        .auth().preemptive().basic("Numpy@gmail.com", "userAPI")
			 			       // .header("Content-Type", "multipart/json")
		        				 .header("Content-Type","application/json; charset=UTF-8")
		        				.log().all();

		     LoggerLoad.logInfo("Create User With Basic Auth");
			}catch (Exception ex) {
			      LoggerLoad.logInfo(ex.getMessage());
			      ex.printStackTrace();
			} 
		}

	@When("user sends request Body with details from excel.")
	public void user_sends_request_body_with_details_from_excel() {
		String file = System.getProperty("user.dir") + "/src/test/resources/testData/UserPostData.xlsx";
		String sheet = "UserPositivePostScenario";

		try {
			 int totalPositiveScenarios = XLUtility.getPositiveScenarioCount(sheet, file);
			 for(int i=1;i<=totalPositiveScenarios;i++) {
			   user = UserRequestBody.GetPostRequestBody(sheet,file,i);
			ObjectMapper mapper = new ObjectMapper();
		    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);  
			this.response =  request
								.when()
								.body(json)
								//.formParam("userInfo",json)
					   			.post(Routes.PostUser_Url);
			System.out.println("Post User Valid Body-->>>"+response.asString());
			LoggerLoad.logInfo("Create user Request with  Request Body send");
			 }	
	
	}catch (Exception ex) {
	      LoggerLoad.logInfo(ex.getMessage());
	      ex.printStackTrace();
	}	   		   
	   
	}
	@Then("user receives Status with response body.")
	public void user_receives_status_with_response_body() {
		try {
				validResponse = response.then().log().all()
				 	.assertThat().statusCode(201);
				 	//.and().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/201_PostPatientSucessfulSchema.json")));
				
				userId = response.body().path("user_id");
				String jsonString = response.asString();
				System.out.println("User Details--->>>>>"+jsonString);
				
				System.out.println("User created with --->>>>>"+userId +" User Id");
			
		}catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
		}		    
	   
	}

}
