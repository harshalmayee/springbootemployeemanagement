package com.harshalmayee.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber",
                 monochrome = false,
                 glue = "com.harshalmayee.cucumber",
                 tags={"~@ignore"})
public class TestRunner {
}
