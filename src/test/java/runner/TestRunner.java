package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/",
				 glue={"stepdefinitions","hooks"},
				 publish=true,
				 monochrome = true,
				 plugin={"pretty","html:target/CucumberReports/CucumberReport.html",
						 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class TestRunner {

}