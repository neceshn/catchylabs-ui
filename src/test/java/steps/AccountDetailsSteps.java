package steps;

import driver.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FormHelper;

import java.time.Duration;
import java.util.List;

public class AccountDetailsSteps {
    private final WebDriverWait webDriverWait;
    private final FormHelper formHelper;
    private final int timeOut = 10;
    private final int sleepTime = 3;

    public AccountDetailsSteps() {
        this.webDriverWait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut), Duration.ofSeconds(sleepTime));
        this.formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    private final By byAccountTitle = By.cssSelector("div.css-175oi2r.r-1pi2tsx.r-1ik5qf4.r-edyy15.r-13qz1uu>div:nth-child(1)");
    private final By byAccountName = By.xpath("//div[div[text()='Account name']]/div[2]");
    private final By byAccountType = By.xpath("//div[div[text()='Account type']]/div[2]");
    private final By byCreationTime = By.xpath("//div[div[text()='Creation time']]/div[2]");
    private final By byAccountAmount = By.xpath("//div[div[text()='Amount']]/div/div[1]");
    private final By byTransferMoneyTitle = By.cssSelector("div.css-146c3p1.r-ubezar.r-vw2c0b");
    private final By byAddMoneyTitle = By.cssSelector("div.css-146c3p1.r-ubezar.r-vw2c0b");
    private final By byEditAccountTitle = By.cssSelector("div.css-146c3p1.r-ubezar.r-vw2c0b");
    private final By byTransactionsMessage = By.xpath("//div[contains(text(),'You don't have any transaction yet.')]");
    private final By byTransactionList = By.cssSelector("div.r-1777fci.r-13qz1uu.r-14lw9ot");

    private By getDynamicButton(String buttonText) {
        return By.xpath("//div[@tabindex='0' and .//div[text()='" + buttonText + "']]");
    }

    @Then("The my account screen is displayed")
    public void theMyAccountScreenIsDisplayed() {
        String accountName = formHelper.findElement(byAccountTitle).getText();
        Assert.assertEquals("Account name does not match", "My account", accountName);
    }

    @Then("The account name should be {string}")
    public void theAccountNameShouldBe(String expectedAccountName) throws InterruptedException {
        Thread.sleep(3000);
        String actualAccountName = formHelper.findElement(byAccountName).getText();
        Assert.assertEquals("Account name does not match", expectedAccountName, actualAccountName);
    }

    @Then("The account type should be {string}")
    public void theAccountTypeShouldBe(String expectedAccountType) {
        String actualAccountType = formHelper.findElement(byAccountType).getText();
        Assert.assertEquals("Account type does not match", expectedAccountType, actualAccountType);
    }

    @Then("The creation time should be {string}")
    public void theCreationTimeShouldBe(String expectedCreationTime) {
        String actualCreationTime = formHelper.findElement(byCreationTime).getText();
        Assert.assertEquals("Creation time does not match", expectedCreationTime, actualCreationTime);
    }

    @Then("The account amount should be {string}")
    public void theAccountAmountShouldBe(String expectedAmount) {
        String actualAmount = formHelper.findElement(byAccountAmount).getText();
        Assert.assertEquals("Account amount does not match", expectedAmount, actualAmount);
    }


    @When("User clicks on {string} button")
    public void userClicksOnButton(String buttonText) {
        By buttonLocator = getDynamicButton(buttonText);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
        formHelper.findElement(buttonLocator).click();
    }

    @Then("The transfer money screen is displayed")
    public void theTransferMoneyScreenIsDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byTransferMoneyTitle));
        String actualTitle = formHelper.findElement(byTransferMoneyTitle).getText();
        Assert.assertEquals("Transfer Money popup does not open", "Transfer Money", actualTitle);
    }

    @Then("The add money screen is displayed")
    public void theAddMoneyScreenIsDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byAddMoneyTitle));
        String actualTitle = formHelper.findElement(byAddMoneyTitle).getText();
        Assert.assertEquals("Add Money popup does not open", "Add money", actualTitle);
    }

    @Then("The edit account screen is displayed")
    public void theEditAccountScreenIsDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byEditAccountTitle));
        String actualTitle = formHelper.findElement(byEditAccountTitle).getText();
        Assert.assertEquals("Edit Account popup does not open", "Edit account", actualTitle);
    }
}
