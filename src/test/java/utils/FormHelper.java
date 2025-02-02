package utils;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

public class FormHelper {

    public static WebDriver webDriver;
    public Actions actions;
    public WebDriverWait webDriverWait;
    static final String JDBC_DRIVER = "example.jdbc.driver";
    static final String DB_URL = "example.db.url";
    static final String USER = "example.user";
    static final String PASS = "example.pass";
    Connection connection = null;
    Statement statement = null;
    private final int timeOut = 30;
    private final int sleepTime = 150;
    public int DEFAULT_MAX_ITERATION_COUNT = 150;
    public int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;
    protected Logger logger = Logger.getLogger(getClass());
    private static final String DEFAULT_DIRECTORY_PATH = "elementValues";
    static PropertyManager propertyManager = new PropertyManager();

    static ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();


    public FormHelper(WebDriver driver) {
        webDriver = driver;
    }

    public void get(String uri){
        webDriver.get(uri);
    }

    public void getUrl(){
        webDriver.get(propertyManager.getProperty("BASE_URL"));
    }

    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }

    public String getTitle(){
        return webDriver.getTitle();
    }


    Map<String, String> users = new HashMap<>();

    public void javascriptclicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    public WebElement findElement(By key) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(key));
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public List<WebElement> findElements(By key) {
        return webDriver.findElements(key);
    }


    public File[] getFileList() {
        File[] fileList = new File(
                this.getClass().getClassLoader().getResource(DEFAULT_DIRECTORY_PATH).getFile())
                .listFiles(pathname -> !pathname.isDirectory() && pathname.getName().endsWith(".json"));
        if (fileList == null) {
            logger.warn(
                    "File Directory Is Not Found! Please Check Directory Location. Default Directory Path = {}" +
                            DEFAULT_DIRECTORY_PATH);
            throw new NullPointerException();
        }
        return fileList;
    }

    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
    }

    public String getValue(String key) {
        return elementMapList.get(key).toString();
    }

    public WebElement findElementWithKey(By key) {
        return findElement(key);
    }

    public void queryExecute(String q) {
        try {
            logger.info(q + " query execute");

            if (q.contains("CREATE") || q.contains("UPDATE") || q.contains("INSERT") || q.contains("DROP")) {
                statement.executeUpdate(q);
            } else {
                ResultSet resultSet = statement.executeQuery(q);
                resultSet.first();
                users.put("mail", resultSet.getString("mail"));
                users.put("password", resultSet.getString("password"));
                resultSet.close();
            }
            logger.info("EXECUTED QUERY: " + q);
        } catch (SQLException throwables) {
            logger.info(throwables.getMessage());
        }

    }

    public void closeJdbcConnection() {
        try {
            connection.close();
            statement.close();
            logger.info("close JDBC connection");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writeUsers() {
        logger.info("Mail: " + users.get("mail") + " Password: " + users.get("password"));
    }

    public void initializeJdbcConnection() {
        try {
            logger.info("************************************  Initializing JDBC_DRIVER  ************************************");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writeValueToElement(String text, By key) {
        findElement(key).sendKeys(text);
    }

    public void writeSavedValueToField(By key, String text) {
        findElement(key).sendKeys(users.get(text));
    }

    public void checkExistanceOfElement(By key) {
        Assert.assertTrue("Element is not visible", findElement(key).isDisplayed());
    }
    //
//        public void elementineTıkla(By key) {
//            if (findElement(key).isDisplayed()) {
//                hoverElement(findElement(key));
//                clickElement(findElement(key));
//            }
//        }
    public void waitSeconds(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getElementWithKeyIfExists(By key) {
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            try {
                return findElementWithKey(key);
            } catch (WebDriverException e) {
                // Beklemeye devam ediyoruz.
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + key + "' doesn't exist after " + DEFAULT_MAX_ITERATION_COUNT + " attempts.");
        return null; // Bu satır unreachable, ancak derleyici için gerekli.
    }


    public void switchTo() {
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }

    private void hoverElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    private void clickElement(WebElement element) {
        element.click();
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectOptionByVisibleText(By selectLocator, String optionText) {
        WebElement selectElement = findElement(selectLocator);
        Select select = new Select(selectElement);

        // Seçenekler arasında verilen metni içeren bir seçenek olup olmadığını kontrol et
        List<WebElement> options = select.getOptions();
        boolean optionExists = options.stream().anyMatch(option -> option.getText().trim().equals(optionText));

        Assert.assertTrue("Seçenek bulunamadı: " + optionText, optionExists);

        // Seçeneği seç
        select.selectByVisibleText(optionText);

        // Seçimin doğru yapıldığını doğrula
        String selectedText = select.getFirstSelectedOption().getText().trim();
        Assert.assertEquals("Seçilen seçenek yanlış!", optionText, selectedText);
    }

}
