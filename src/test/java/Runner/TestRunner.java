package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="FeatureFiles", glue="StepDefination", plugin = {"pretty", "html:target/cucumber-html-report"})
public class TestRunner {
	

}
