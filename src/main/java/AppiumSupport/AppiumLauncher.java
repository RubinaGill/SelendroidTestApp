package AppiumSupport;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppiumLauncher {
    private static AppiumDriverLocalService server;
    private static final String[] WIN_RUNTIME = { "cmd.exe", "/C" };
    private static final String[] OS_LINUX_RUNTIME = { "/bin/bash", "-c" };

    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void runProcess(boolean isWin, String... command) {
        String[] allCommand;
        try {
            if (isWin) {
                allCommand = concat(WIN_RUNTIME, command);
            } else {
                allCommand = concat(OS_LINUX_RUNTIME, command);
            }
            ProcessBuilder pb = new ProcessBuilder(allCommand);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor(10000, TimeUnit.MILLISECONDS);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (true) {
                if (in.readLine().contains("Appium REST http interface listener started")){
                    System.out.println("Appium launched successfully");
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startServer(String nodePath, String appiumJsPath, String appiumIp, String appiumPort) {
//        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .withAppiumJS(new File(nodePath))
//                .usingPort(4723).withIPAddress("127.0.0.1"));
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
//        serviceBuilder.usingDriverExecutable(new File(nodePath));
//        serviceBuilder.withAppiumJS(new File(appiumJsPath));
//        serviceBuilder.usingPort(Integer.parseInt(appiumPort));
        serviceBuilder.withIPAddress("0.0.0.0");
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
    }
}