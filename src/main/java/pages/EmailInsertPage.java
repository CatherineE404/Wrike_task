package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailInsertPage {

    WebDriver driver;
    private By buttonForFree = By.xpath("(//button[contains(@class,'free-trial-button wg-btn wg-btn--green')])[3]");
    private By emailPath = By.xpath("//input[@class='wg-input modal-form-trial__input']");
    private By buttonCreateAcc = By.xpath("//button[@class='wg-btn wg-btn--blue modal-form-trial__submit']");

    public EmailInsertPage(WebDriver driver){
        this.driver = driver;
    }

    //Click on start for free
    private void PressStartForFree () {
        WebElement loginButtonId = driver.findElement(buttonForFree);
        loginButtonId.click();
    }

    // Set the email Address
    private void setEmail (String emailStart) {
        String fullEmail = new StringBuilder().append(emailStart).append("+wpt@wriketask.qaa").toString();
        driver.findElement(emailPath).sendKeys(fullEmail);
    }

    //Click on login button
    private void clickCreateAcc(){
        driver.findElement(buttonCreateAcc).click();
    }

    /**
     * This POM method will be exposed in test case to login in the application
     * @param emailStart
     * @return
     */
    public void loginToAutoQA(String emailStart){
        //press Get for free button
        this.PressStartForFree();
        //Fill user name
        this.setEmail(emailStart);
        //Click Login button
        this.clickCreateAcc();
    }

}

