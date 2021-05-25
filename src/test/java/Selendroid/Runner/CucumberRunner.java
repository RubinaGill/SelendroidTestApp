package Selendroid.Runner;

import io.cucumber.testng.*;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/java/Selendroid/Features",
        glue = "Selendroid/Steps",
        tags = "@Web or @API or @Mobile",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"
        }
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
}
    