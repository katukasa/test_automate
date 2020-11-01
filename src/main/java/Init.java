import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Init {

    private static WebDriver driver;
    Properties props = new Properties();

    public static void main(String[] args) throws IOException {
        Init init = new Init();
        init.startChromeDriver();
        setImplicitTimeout();
        runTests(1);
    }

    private static void runTests(int testNumber) throws IOException{
        Tests tests = new Tests();
        switch (testNumber){
            case 1:
                tests.test1();
                break;
            case 2:
                tests.test2();
                break;
            default:
                tests.test1();
                break;
        }
    }

    private static void setImplicitTimeout(){
        driver.manage().timeouts().implicitlyWait(Waits.medium_wait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Waits.medium_wait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Waits.medium_wait, TimeUnit.SECONDS);
    }

    Init() throws IOException {
        props.load(new FileInputStream("src/main/resources/configuration/app.properties"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("multiple-automatic-downloads", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        prefs.put("download.prompt_for_download", false);

        prefs.put("download.default_directory", props.getProperty("download.default_directory"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(options);
    }

}
