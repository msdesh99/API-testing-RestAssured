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

public class PutUserRequestStep {
	RequestSpecification request;
	Response response;
	ValidatableResponse validResponse;
	int userId, testcaseNo;
	User user = new User();
	String authStatus, scenarioType;
	ResponseModel responseModel = new ResponseModel();
	Object[] obj;

	@Given("User creates PUT Request for UserAPI module {string} with testcases")
	public void user_creates_put_request_for_user_api_module_with_testcases(String authStatus) {
		this.request = CommonForAllGivenStep.GivenMethod(authStatus);
		this.authStatus = authStatus;
	}

	@When("user sends request Body with details for {string} {string} scenario from excel for {string}.")
	public void user_sends_request_body_with_details_for_scenario_from_excel_for(String user_Id, String scenarioType,
			String rowNo) {
		this.scenarioType = scenarioType;
		this.testcaseNo = Integer.valueOf(rowNo);
		String sheetName;
		this.userId = Integer.valueOf(user_Id);

		sheetName = this.scenarioType.contentEquals("negative") ? Endpoints.postandputNegativeScenarioSheet
				: Endpoints.putPositiveScenarioSheet;
		try {
			obj = UserRequestBody.GetPostRequestBody(sheetName, Endpoints.dataFile, this.testcaseNo);
			user = (User) obj[0];
			responseModel = (ResponseModel) obj[1];
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			this.response = request.when().body(json)
					// .formParam("userInfo",json)
					.put(Endpoints.putUserByUserIdEndpoint + this.userId);
			System.out.println(
					"ExpectedResponse-->>>" + responseModel.getStatus() + " " + responseModel.getStatusMessage());
			System.out.println("ActualResponse-->>>" + response.asString());

			LoggerLoad.logInfo("TestCase:" + testcaseNo +"-Create user POST Request with  "
					+ scenarioType + " Scenario");

		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}

	}

	@Then("user receives Status with PUT response body.")
	public void user_receives_status_with_put_response_body() {
		try {
			if (this.authStatus.contentEquals("NoAuth")) {
				response.then().log().all().assertThat().statusCode(401).header("Content-type", "application/json");
			} else {
				response.then().log().all()
						.assertThat().statusCode(Integer.valueOf(responseModel.getStatus()))
						.header("Content-type", "application/json");
				// .and().body(JsonSchemaValidator.matchesJsonSchema(new
				// File("./src/test/resources/201_PostPatientSucessfulSchema.json")));

				/*
				 * User result = response.then().extract() .as(User.class);
				 * Assert.assertEquals(result,user);
				 */
				String jsonString = response.asString();
				Assert.assertNotEquals(jsonString, null);
				int actualResponseCode = response.then().extract().statusCode();
				System.out.println("User Details--->>>>>" + jsonString);

				System.out.println("In Assertion: Actual " + actualResponseCode + " Expected:"
						+ Integer.valueOf(responseModel.getStatus()));
				System.out.println("Updated User ByUserId --->>>>>" + user.getUser_id() + " with Row: "
						+ this.testcaseNo + " from XL sheet");
			}
		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
