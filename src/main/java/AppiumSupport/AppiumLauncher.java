package AppiumSupport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppiumLauncher {
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
}