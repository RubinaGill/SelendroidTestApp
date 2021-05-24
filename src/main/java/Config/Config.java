package Config;

import Logger.Log;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    static Properties conf = new Properties();

    public static Properties getAllProperties() {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties")) {
            conf.load(fis);
        } catch (Exception e) {
            Log.warn(e.getMessage());
        }
        return conf;
    }
}
