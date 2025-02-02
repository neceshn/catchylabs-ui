package steps;

import driver.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ByHelper;
import utils.FormHelper;
import utils.PropertyManager;

import java.time.Duration;

public class CreateAccountPopupSteps {
    public WebDriverWait webDriverWait;
    private final int timeOut = 10;
    private final int sleepTime = 10;
    static PropertyManager propertyManager = new PropertyManager();
    private final FormHelper _formHelper;

    public CreateAccountPopupSteps() {
        this.webDriverWait = new WebDriverWait(BaseTest.getWebDriver(), Duration.ofSeconds(timeOut), Duration.ofSeconds(sleepTime));
        _formHelper = new FormHelper(BaseTest.getWebDriver());
    }

    private final By bySelectAccountType = By.cssSelector("div.css-175oi2r.r-1777fci>select");
    private final By byLabelPopupTitle = By.xpath("//div[contains(text(),'Create account')]");
    private final By byInputAccountName = By.cssSelector("div.css-175oi2r.r-13qz1uu>input");
    private final By byButtonCreateAccount = By.cssSelector("div.r-1p0dtai.r-1d2f490.r-1xcajam.r-zchlnj.r-ipm5af.r-sfbmgh.r-1ielgck.r-1uypc71.r-xx3c9p.r-6dt33c div.css-175oi2r:nth-child(2) div.css-175oi2r.r-1p0dtai.r-1d2f490.r-1xcajam.r-zchlnj.r-ipm5af.r-1niwhzg div.css-175oi2r.r-13awgt0.r-ipm5af div.css-175oi2r.r-1awozwy.r-13awgt0.r-1777fci.r-1ygmrgt div.css-175oi2r.r-1awozwy.r-14lw9ot.r-1xfd6ze.r-1rr9as1.r-129l8ac.r-1ik5qf4.r-e7q0ms div.css-175oi2r.r-1awozwy.r-13awgt0.r-1777fci.r-nsbfu8.r-13qz1uu:nth-child(2) div.css-175oi2r.r-150rngu.r-eqz5dr.r-16y2uox.r-1wbh5a2.r-11yh6sk.r-1rnoaur.r-agouwx.r-1pi2tsx.r-13qz1uu div.css-175oi2r div.css-175oi2r.r-1i6wzkk.r-lrvibr.r-1loqt21.r-1otgn73.r-1awozwy.r-169ebfh.r-z2wwpe.r-h3s6tt.r-1777fci.r-tsynxw.r-13qz1uu:nth-child(9) > div.css-146c3p1.r-jwli3a.r-1b43r93");



    @When("User Select Account Type {string}")
    public void userSelectAccountType(String optionText) {
        _formHelper.selectOptionByVisibleText(bySelectAccountType, optionText);
    }


    @Then("System Open The Popup {string}")
    public void systemOpenThePopup(String popupTitle) {
        Assert.assertEquals("Create Account popup cannot be opened", _formHelper.findElement(byLabelPopupTitle).getText(), popupTitle);
    }

    @And("User check the {string} button is disabled")
    public void userCheckTheButtonIsDisabled(String arg0) {
    Assert.assertTrue("Create button is enabled",_formHelper.findElement(ByHelper.getDynamicButton(arg0)).isEnabled());
    }

    @And("User fill the Account Name {string}")
    public void userFillTheAccountName(String accountName) {
        _formHelper.findElement(byInputAccountName).sendKeys(accountName);
    }

    @And("User check the {string} button is enabled")
    public void userCheckTheButtonIsEnabled(String arg0) {
        Assert.assertFalse("Create button is disabled",!_formHelper.findElement(ByHelper.getDynamicButton(arg0)).isEnabled());
    }

    @And("Click Account Create button")
    public void clickAccountCreateButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(byButtonCreateAccount));
        _formHelper.findElement(byButtonCreateAccount).click();
    }
}
