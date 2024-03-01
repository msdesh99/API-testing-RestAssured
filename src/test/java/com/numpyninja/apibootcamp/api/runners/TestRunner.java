package com.numpyninja.apibootcamp.api.runners;
import org.junit.runner.RunWith;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			tags = (  "@getAllUsers"
					+ " or  @createAPIUserPositive or @createAPIUserNegative"
					+ " or @getUserByUserId"
					+ " or @updateAPIUserPositive or @updateAPIUserNegative"
                    + " or @getUserByUserName"
					+ " or @deleteUserByUserName"
				    + " or @deleteUserByUserId"
					),
			features = {"src/test/resources/features"}, 	
			glue= {"com.numpyninja.apibootcamp.api.steps"},
			plugin = {"pretty","html:target/userAPI_Cucumber.html",
					"json:target/cucumber.json",
					//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					}, 
						
			monochrome = false
			)

	public class TestRunner {


}
