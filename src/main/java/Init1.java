import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.Map;

public class Init1 {
    private static WebDriver driver;

    private Init1(){
        startChromeDriver();
//        startFirefoxDriver();
//        startIEDriver();
    }

    public static void main(String[] args) {
        Init1 init1 = new Init1();
    }

    private void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        prefs.put("multiple-automatic-downloads", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", "C:\\Users\\zwr6sn\\Downloads\\Study_test");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
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
