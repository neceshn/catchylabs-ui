package steps;


import driver.BaseTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DoubleHelper;
import utils.FormHelper;
import java.time.Duration;
import java.util.HashMap;

public class AddMoneyPopupSteps {
    private final WebDriverWait webDriverWait;
    private final FormHelper formHelper;
    private final int timeOut = 10;
    private final int sleepTime = 3;
    private final HashMap<String, Object> localDataStore = new HashMap<String, Object>();

    public AddMoneyPopupSteps() {
        this.webDriverWait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut), Duration.ofSeconds(sleepTime));
        this.formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    private final By byPopupTitle = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
    private final By byCardNumberInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/input[1]");
    private final By byCardHolderInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/input[1]");
    private final By byExpiryDateInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[6]/div[1]/div[1]/input[1]");
    private final By byCVVInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[6]/div[2]/div[1]/input[1]");
    private final By byAmountInput = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[8]/input[1]");
    private final By byAddButton = By.xpath("//body/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[10]");
    private final By byAccountAmount = By.xpath("//div[div[text()='Amount']]/div/div[1]");

    private By getErrorMessageLocator(String fieldLabel) {
        formHelper.findElement(By.cssSelector("body")).click();
        return By.xpath("//div[contains(text(),'" + fieldLabel + "')]");
    }

    @Then("The add money popup title should be {string}")
    public void theAddMoneyPopupTitleShouldBe(String expectedTitle) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byPopupTitle));
        String actualTitle = formHelper.findElement(byPopupTitle).getText();
        Assert.assertEquals("Popup title does not match", expectedTitle, actualTitle);
    }

    @Then("The card number field should be displayed")
    public void theCardNumberFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byCardNumberInput));
        Assert.assertTrue("Card number input is not displayed", formHelper.findElement(byCardNumberInput).isDisplayed());
    }

    @Then("The card holder field should be displayed")
    public void theCardHolderFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byCardHolderInput));
        Assert.assertTrue("Card holder input is not displayed", formHelper.findElement(byCardHolderInput).isDisplayed());
    }

    @Then("The expiry date field should be displayed")
    public void theExpiryDateFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byExpiryDateInput));
        Assert.assertTrue("Expiry date input is not displayed", formHelper.findElement(byExpiryDateInput).isDisplayed());
    }

    @Then("The CVV field should be displayed")
    public void theCVVFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byCVVInput));
        Assert.assertTrue("CVV input is not displayed", formHelper.findElement(byCVVInput).isDisplayed());
    }

    @Then("The amount field should be displayed")
    public void theAmountFieldShouldBeDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byAmountInput));
        Assert.assertTrue("Amount input is not displayed", formHelper.findElement(byAmountInput).isDisplayed());
    }

    @Then("The {string} button should be disabled in add money popup")
    public void theAddButtonShouldBeDisabled(String buttonText) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byAddButton));
        WebElement addButton = formHelper.findElement(byAddButton);
        // Örnekte aria-disabled attribute kullanılıyor
        String ariaDisabled = addButton.getAttribute("aria-disabled");
        Assert.assertTrue(buttonText + " button is not disabled", "true".equals(ariaDisabled));
    }

    @When("User enters {string} into {string} field")
    public void userEntersIntoField(String inputText, String fieldLabel) {
        By locator;
        switch (fieldLabel) {
            case "Card number":
                locator = byCardNumberInput;
                break;
            case "Card holder":
                locator = byCardHolderInput;
                break;
            case "Expiry date":
                locator = byExpiryDateInput;
                break;
            case "CVV":
                locator = byCVVInput;
                break;
            case "Amount":
                locator = byAmountInput;
                break;
            default:
                throw new IllegalArgumentException("Invalid field label: " + fieldLabel);
        }
        WebElement inputField = formHelper.findElement(locator);
        inputField.clear();
        inputField.sendKeys(inputText);
        // Eğer validasyon için blur (odak kaybı) tetiklenmesi gerekiyorsa ek bir click veya JavaScript tetiklenebilir.
    }

    @Then("An error message {string} should be displayed for {string} field")
    public void anErrorMessageShouldBeDisplayedForField(String expectedError, String fieldLabel) {
        By errorLocator = getErrorMessageLocator(expectedError);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        String actualError = formHelper.findElement(errorLocator).getText();
        Assert.assertEquals("Error message for " + fieldLabel + " does not match", expectedError, actualError);
    }

    @When("User clicks on {string} button in add money popup")
    public void userClicksOnButton(String buttonText) {
        By buttonLocator = By.xpath("//div[@tabindex='0' and .//div[text()='" + buttonText + "']]");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
        formHelper.findElement(buttonLocator).click();
    }

    @When("Store Amount")
    public void storeAmount() {
        localDataStore.put(byAccountAmount.toString(), formHelper.findElement(byAccountAmount).getText());
    }

    @Then("The money should be added {string} successfully")
    public void theMoneyShouldBeAddedSuccessfully(String addedAmount) throws InterruptedException {
        String oldAmountString = localDataStore.get(byAccountAmount.toString()).toString();
        Double oldAmount = DoubleHelper.priceToDouble(oldAmountString);
        Thread.sleep(3000);
        String newAmountString = formHelper.findElement(byAccountAmount).getText();
        Double newAmount = DoubleHelper.priceToDouble(newAmountString);
        Assert.assertEquals("Double değerler beklenen toleransta eşit değil!", oldAmount, newAmount,Double.parseDouble(addedAmount));
    }

    @Then("The money should be mined {string} successfully")
    public void theMoneyShouldBeMinedSuccessfully(String addedAmount) throws InterruptedException {
        String oldAmountString = localDataStore.get(byAccountAmount.toString()).toString();
        Double oldAmount = DoubleHelper.priceToDouble(oldAmountString);
        Thread.sleep(3000);
        String newAmountString = formHelper.findElement(byAccountAmount).getText();
        Double newAmount = DoubleHelper.priceToDouble(newAmountString);
        Assert.assertEquals("Double değerler beklenen toleransta eşit değil!", oldAmount-Double.parseDouble(addedAmount), newAmount,Double.parseDouble(addedAmount));
    }

}