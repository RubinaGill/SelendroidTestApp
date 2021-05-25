package AppiumSupport;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumLauncher {

    public static AppiumDriverLocalService startServer(String nodePath, String appiumJsPath, String device) {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withArgument(() -> "--avd", device)
                .usingDriverExecutable(new File(nodePath))
                .withAppiumJS(new File(appiumJsPath))
                .withLogFile(new File("target/\"+deviceUnderExecution+\".log")));
    }
}