package Selendroid.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Selendroid/Features",
        glue = "Selendroid/Steps",
        tags = "@Web",
        plugin = {
                "pretty"
        }
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

}
    