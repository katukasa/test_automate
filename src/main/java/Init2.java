import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

public class Init2 {
    Properties props = new Properties();

    private static WebDriver driver;

    private Init2() throws IOException {
        props.load(new FileInputStream("src/main/resources/configuration/app.properties"));
        startChromeDriver();
//        startFirefoxDriver();
//        startIEDriver();
    }

    public static void main(String[] args) throws IOException {
        Init2 init2 = new Init2();
    }

    private void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("multiple-automatic-downloads", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        prefs.put("download.prompt_for_download", false);

        prefs.put("download.default_directory", props.getProperty("download.default_directory"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

//        if (props.getProperty("window.size").equals("1")){
//            options.addArguments("--start-maximized");
//        }

        if (props.getProperty("scale").equals("0.7")){
            options.addArguments("--high-dpi-support=0.3");
            options.addArguments("--force-device-scale-factor=0.5");
        }

//        options.addArguments("--headless");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("my_proxy:8080");
//        options.setCapability("proxy", proxy);

        driver = new ChromeDriver(options);
        driver.navigate().to("https://geekbrains.ru/");
    }

    private void startFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile geekBrainsATprofile = profile.getProfile("selenium");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(geekBrainsATprofile);
        driver = new FirefoxDriver();
    }

    private void startIEDriver() {
        System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }
}
