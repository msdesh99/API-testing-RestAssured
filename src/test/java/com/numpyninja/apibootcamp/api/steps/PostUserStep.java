package com.numpyninja.apibootcamp.api.steps;

import org.junit.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numpyninja.apibootcamp.api.endpoints.Endpoints;
import com.numpyninja.apibootcamp.api.payload.ResponseModel;
import com.numpyninja.apibootcamp.api.payload.User;
import com.numpyninja.apibootcamp.api.requestbody.UserRequestBody;
import com.numpyninja.apibootcamp.api.utils.LoggerLoad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PostUserStep {
	RequestSpecification request;
	Response response;
	ValidatableResponse validResponse;
	int userId, testcaseNo;
	User user = new User();
	String authStatus, scenarioType;
	ResponseModel responseModel = new ResponseModel();
	Object[] obj;

	@Given("User creates POST  Request for UserAPI module {string} with testcases")
	public void user_creates_post_request_for_user_api_module_with_testcases(String authStatus) {
		this.request = CommonForAllGivenStep.GivenMethod(authStatus);
		this.authStatus = authStatus;
	}

	@When("user sends request Body with details for {string} scenario from excel for {string}.")
	public void user_sends_request_body_with_details_for_scenario_from_excel_for(String scenarioType, String rowNo) {
		this.scenarioType = scenarioType;
		this.testcaseNo = Integer.valueOf(rowNo);
		String sheetName;
		if (this.scenarioType.contentEquals("negative")) {
			sheetName = Endpoints.postandputNegativeScenarioSheet;
		} else
			sheetName = Endpoints.postPositiveScenarioSheet;
		try {
			obj = UserRequestBody.GetPostRequestBody(sheetName, Endpoints.dataFile, this.testcaseNo);
			user = (User) obj[0];
			responseModel = (ResponseModel) obj[1];
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			this.response = request.when().body(json)
					// .formParam("userInfo",json)
					.post(Endpoints.postUserEndpoint);
			System.out.println(
					"ExpectedResponse-->>>" + responseModel.getStatus() + " " + responseModel.getStatusMessage());
			System.out.println("ActualResponse-->>>" + response.asString());
			LoggerLoad.logInfo("TestCase:" + testcaseNo + " " + authStatus + "-Create user POST Request with  "
					+ scenarioType + " Scenario");

		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("user receives Status with response body.")
	public void user_receives_status_with_response_body() {
		try {
			if (this.authStatus.contentEquals("NoAuth")) {
				response.then().log().all().assertThat().statusCode(401).header("Content-type", "application/json");
			} else {
				String jsonString = response.asString();
				Assert.assertNotEquals(jsonString, null);
				int actualResponseCode = response.then().extract().statusCode();
				
				userId = (int) (this.scenarioType.contentEquals("positive")?response.body().path("user_id")
						:0);
				System.out.println("In Assertion: Actual " + actualResponseCode + " Expected:"
						+ Integer.valueOf(responseModel.getStatus()));
				System.out.println("User Details--->>>>>" + jsonString);
				Assert.assertTrue((actualResponseCode == Integer.valueOf(responseModel.getStatus())));
			}
		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}

	}

}
