import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:CheckoutWorkFlow.feature"},
        plugin = {"html:target/cucumber-html-report", "pretty:target/cucumber-json-report.json"},
        strict = false
)
public class SampleCucumberTesting {

}