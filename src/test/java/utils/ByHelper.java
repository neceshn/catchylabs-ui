package utils;

import org.openqa.selenium.By;

public  class ByHelper {
    public static By getDynamicButton(String buttonText) {
        return By.xpath("//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + buttonText.toLowerCase() + "')]");
    }
}
