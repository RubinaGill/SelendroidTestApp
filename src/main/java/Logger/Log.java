package Logger;

import org.apache.log4j.Logger;

public class Log {
    static final Logger Log = Logger.getLogger(Log.class.getName());

    public Log() {
    }

    public static void startTestCase(String testCaseName) {
        Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$$$           " + testCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
    }

    public static void endTestCase(String testCaseName, String testCaseStatus) {
        Log.info("**********************             -E---N---D-                   ***********************");
        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$     " + testCaseName + " : " + testCaseStatus + "   $$$$$$$$$$$$$$$$$");
        Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error("*****************             -E---R---R---O---R                  ********************");
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
