package steps;

import driver.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ByHelper;
import utils.FormHelper;
import utils.PropertyManager;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginSteps {
    public WebDriverWait webDriverWait;
    private final int timeOut = 10;
    private final int sleepTime = 3;
    static PropertyManager propertyManager = new PropertyManager();
    private final FormHelper _formHelper;

    public LoginSteps() {
        this.webDriverWait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut), Duration.ofSeconds(sleepTime));
        _formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    private final By byLabelWarningMessage = By.cssSelector("div.css-146c3p1.r-howw7u.r-1b43r93");
    private final By byInputUsername = By.cssSelector("input[placeholder='Username']");
    private final By byInputPassword = By.cssSelector("input[placeholder='Password']");
    private final By byButtonLogin = By.xpath("//div[@tabindex='0' and .//div[text()='Login']]");


    @Given("Go to catchylabs")
    public void goToCatchylabs() {
        _formHelper.getUrl();
        webDriverWait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @When("Enter the username")
    public void enterTheUsername() {
        String username = propertyManager.getProperty("USER_NAME");
        WebElement userElem = _formHelper.findElement(byInputUsername);
        userElem.sendKeys(username);
    }

    @And("Enter the password")
    public void enterThePassword() {
        String password = propertyManager.getProperty("USER_PASSWORD");
        WebElement passElem = _formHelper.findElement(byInputPassword);
        passElem.clear();
        passElem.sendKeys(password);
    }

    @And("Click on the login")
    public void clickOnTheLogin() {
        _formHelper.findElement(byButtonLogin).click();
    }

    @Then("The title will contains the {string}")
    public void theTitleWillContainsTheMessage(String title) {
        webDriverWait.until(ExpectedConditions.titleContains(title));
        Assert.assertTrue("Title does not contain " + title, _formHelper.getTitle().contains(title));
    }


    @When("Enter the wrong password")
    public void enterTheUsernameWithoutDomain() {
        String username = propertyManager.getProperty("INVALID_PASSWORD");
        WebElement userElem = _formHelper.findElement(byInputUsername);
        userElem.clear();
        userElem.sendKeys(username);
    }

    @Then("The error message is displayed")
    public void theErrorMessageIsDisplayed() {
        String validationMessage = _formHelper.findElement(byInputUsername).getAttribute("validationMessage");
        assertEquals("Please enter a part following '@'. 'esahin@' is incomplete.", validationMessage);
    }

    @When("Enter the wrong username")
    public void enterTheWrongUsername() {
        String username = propertyManager.getProperty("WRONG_USER_NAME");
        WebElement userElem = _formHelper.findElement(byInputUsername);
        userElem.clear();
        userElem.sendKeys(username);
    }

    @Then("The error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        String actualErrorMessage = _formHelper.findElement(byLabelWarningMessage).getText();
        assertEquals(errorMessage, actualErrorMessage);
    }


    @When("Click {string} button")
    public void clickButton(String buttonText) {
        _formHelper.findElement(ByHelper.getDynamicButton(buttonText)).click();
    }

}