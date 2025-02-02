package steps;

import driver.BaseTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FormHelper;

import java.time.Duration;

public class EditAccountPopupSteps {

    private final WebDriverWait wait;
    private final FormHelper formHelper;
    private final int timeOut = 10;

    public EditAccountPopupSteps() {
        this.wait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut));
        this.formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    // Locator'lar
    private final By byPopupTitle = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
    private final By byAccountNameInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/input[1]");
    private final By byUpdateButton = By.xpath("//div[contains(text(),'UPDATE')]");

    @Then("The edit account popup title should be {string}")
    public void theEditAccountPopupTitleShouldBe(String expectedTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPopupTitle));
        String actualTitle = formHelper.findElement(byPopupTitle).getText().trim();
        Assert.assertEquals("Popup title does not match", expectedTitle, actualTitle);
    }

    @Then("The account name input field should be displayed")
    public void theAccountNameInputFieldShouldBeDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byAccountNameInput));
        Assert.assertTrue("Account name input is not displayed", formHelper.findElement(byAccountNameInput).isDisplayed());
    }

    @Then("The {string} button should be {word}")
    public void theButtonShouldBe(String buttonText, String status) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byUpdateButton));
        WebElement saveButton = formHelper.findElement(byUpdateButton);
        if(status.equalsIgnoreCase("enabled")){
            Assert.assertTrue(buttonText + " button is not enabled", saveButton.isEnabled());
        } else if(status.equalsIgnoreCase("disabled")){
            Assert.assertFalse(buttonText + " button is not disabled", saveButton.isEnabled());
        } else {
            Assert.fail("Invalid status provided: " + status);
        }
    }

    // --- Account Name Field Steps ---
    @When("User clears the account name input field")
    public void userClearsTheAccountNameInputField() {
        WebElement input = formHelper.findElement(byAccountNameInput);
        input.clear();
    }

    @When("User enters {string} field")
    public void userEntersIntoField(String inputText) {
        WebElement input = formHelper.findElement(byAccountNameInput);
        input.clear();
        input.sendKeys(inputText);
    }

    @When("User moves focus away from the account name input field")
    public void userMovesFocusAwayFromTheAccountNameInputField() {
        formHelper.findElement(By.tagName("body")).click();
    }

}
