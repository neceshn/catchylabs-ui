package steps;

import driver.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DoubleHelper;
import utils.FormHelper;

import java.time.Duration;
import java.util.HashMap;

public class TransferMoneyPopupSteps {
    private final WebDriverWait webDriverWait;
    private final FormHelper formHelper;
    private final int timeOut = 10;
    private final int sleepTime = 3;
    private final HashMap<String, Object> localDataStore = new HashMap<String, Object>();

    public TransferMoneyPopupSteps() {
        this.webDriverWait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut), Duration.ofSeconds(sleepTime));
        this.formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    private final By byPopupTitle = By.xpath("//div[@dir='auto' and contains(@class,'r-ubezar') and text()='Transfer money']");
    private final By bySenderAccountSelect = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/select[1]");
    private final By byReceiverAccountSelect = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[7]/select[1]");
    private final By byAmountInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[11]/input[1]");
    private final By bySendButton = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[13]/div[1]");
    private final By byAccountAmount = By.xpath("//div[div[text()='Amount']]/div/div[1]");

    @Then("The transfer money popup title should be {string}")
    public void theTransferMoneyPopupTitleShouldBe(String expectedTitle) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byPopupTitle));
        String actualTitle = formHelper.findElement(byPopupTitle).getText();
        Assert.assertEquals("Popup title does not match", expectedTitle, actualTitle);
    }

    @Then("The sender account dropdown should be displayed")
    public void theSenderAccountDropdownShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySenderAccountSelect));
        Assert.assertTrue("Sender account dropdown is not displayed", formHelper.findElement(bySenderAccountSelect).isDisplayed());
    }

    @Then("The receiver account dropdown should be displayed")
    public void theReceiverAccountDropdownShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byReceiverAccountSelect));
        Assert.assertTrue("Receiver account dropdown is not displayed", formHelper.findElement(byReceiverAccountSelect).isDisplayed());
    }

    @Then("The amount input field should be displayed")
    public void theAmountInputFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byAmountInput));
        Assert.assertTrue("Amount input field is not displayed", formHelper.findElement(byAmountInput).isDisplayed());
    }

    @Then("The {string} button should be disabled")
    public void theButtonShouldBeDisabled(String buttonText) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySendButton));
        WebElement sendButton = formHelper.findElement(bySendButton);
        String ariaDisabled = sendButton.getAttribute("aria-disabled");
        Assert.assertTrue("Send button is not disabled", "true".equals(ariaDisabled));
    }


    @When("User enters {string} amount field")
    public void userEntersIntoField(String inputText) {
        WebElement input = formHelper.findElement(byAmountInput);
        input.clear();
        input.sendKeys(inputText);
    }


    @Then("Click transfer Send button")
    public void clickTransferSendButton() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySendButton));
        formHelper.findElement(bySendButton).click();
    }
}
