package com.numpyninja.apibootcamp.api.runners;
import org.junit.runner.RunWith;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			tags =("@createAPIUser"), // or @getAllUsers"),
			features = {"src/test/resources/features"}, //location of feature files		
			glue= {"com.numpyninja.apibootcamp.api.steps"},
			plugin = {"pretty","html:target/userAPI_Cucumber.html",
					"json:target/cucumber.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					}, 
						
			monochrome = false
			)

	public class TestRunner {


}
