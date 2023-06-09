package com.stackspot.cucumber.integration.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty", "junit:src/test/resources/report/report-integration-test.xml"})
public class CucumberApplication {
}
