package driver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.PropertyManager;

public class BaseTest {

    protected static WebDriver driver;
    protected static Actions actions;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    public Logger logger = Logger.getLogger(getClass());
    String browserName;
    static PropertyManager propertyManager = new PropertyManager();

    public static WebDriver getWebDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        logger.info("************************************  BeforeScenario  ************************************");

        // Tarayıcı bilgisi sistem parametresinden alınıyor, belirtilmemişse varsayılan olarak Chrome kullanılıyor.
        browserName = System.getProperty("browser");
        if (browserName == null || browserName.trim().isEmpty()) {
            browserName = "chrome";
        }

        if (browserName.equalsIgnoreCase("chrome")) {
            // Mobil emülasyon ayarlarını yapılandırma (örneğin, iPhone X)
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "iPhone X");

            //
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("disable-translate");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--start-fullscreen");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

            Map<String, Object> prefs = new HashMap<>();
            chromeOptions.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(chromeOptions);

        } else if (browserName.equalsIgnoreCase("mobile")) {
            // Mobil emülasyon ayarlarını yapılandırma (örneğin, iPhone X)
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "iPhone X");

            //
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("disable-translate");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--start-fullscreen");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

            Map<String, Object> prefs = new HashMap<>();
            chromeOptions.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(chromeOptions);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");

            driver = new FirefoxDriver(firefoxOptions);

        } else {
            logger.warn("Desteklenmeyen tarayıcı: " + browserName + ". Varsayılan olarak Chrome kullanılıyor.");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("w3c", false);
            chromeOptions.addArguments("disable-translate");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--start-fullscreen");
            Map<String, Object> prefs = new HashMap<>();
            chromeOptions.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(chromeOptions);
        }

        actions = new Actions(driver);
    }


    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            driver.quit();
        }

        // Raporu dosyaya yaz (flush)
        if (extent != null) {
            extent.flush();
        }
    }

}
