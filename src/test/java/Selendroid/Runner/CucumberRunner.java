package Selendroid.Runner;

import io.cucumber.testng.*;

@CucumberOptions(
        features = "src/test/java/Selendroid/Features",
        glue = "Selendroid/Steps",
        tags = "@Web or @API or @Mobile",
        plugin = {
                "pretty"
        }
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}
    