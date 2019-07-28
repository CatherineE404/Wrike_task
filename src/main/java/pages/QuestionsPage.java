package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class QuestionsPage {
    WebDriver driver;
    private By firstQuest1 = By.xpath("//*[contains(text(),'Very interested')]");
    private By firstQuest2 = By.xpath("//*[contains(text(),'Just looking')]");
    private By secondQuest1 = By.xpath("//*[contains(text(),'1–5')]");
    private By secondQuest2 = By.xpath("//*[contains(text(),'6–15')]");
    private By secondQuest3 = By.xpath("//*[contains(text(),'16–25')]");
    private By secondQuest4 = By.xpath("//*[contains(text(),'26–50')]");
    private By secondQuest5 = By.xpath("//*[contains(text(),'50+')]");
    private By thirdQuest1 = By.xpath("//span[contains(.,'Yes')]");
    private By thirdQuest2 = By.xpath("//*[@class='survey-question-radio__button' and contains(text(),'No')]");
    private By thirdQuest3 = By.xpath("//span[contains(.,'Other')]");
    private By thirdQuest3Add = By.xpath("//span[@class='survey-question-radio__additional']");
    private By submitRes = By.xpath("//button[@class='submit survey__submit wg-btn wg-btn--navy']");

    private By twitLink = By.xpath("//a[contains(@href, 'twitter.com')]");
    private By twitImage = By.xpath("//a[contains(@href, 'twitter')]/*[local-name() = 'svg' ]/*");


    public QuestionsPage(WebDriver driver){
        this.driver = driver;
    }

    private void AnswerQuestionsRandomly() {
        int i= (int)(2*Math.random());
        //first Question answer
        if (i==0){
            driver.findElement(firstQuest1).click();
        } else {
            driver.findElement(firstQuest2).click();
        }
        i = (int)(5*Math.random());
        switch (i) {
            case 0: driver.findElement(secondQuest1).click(); break;
            case 1: driver.findElement(secondQuest2).click(); break;
            case 2: driver.findElement(secondQuest3).click(); break;
            case 3: driver.findElement(secondQuest4).click(); break;
            case 4: driver.findElement(secondQuest5).click(); break;
        }
        i = (int)(3*Math.random());
        switch (i) {
            case 0:
                driver.findElement(thirdQuest1).click(); break;
            case 1:
                driver.findElement(thirdQuest2).click(); break;
            case 2: {
                driver.findElement(thirdQuest3).click();
                driver.findElement(thirdQuest3Add).sendKeys("Sometimes they do");
                break;
            }
        }
    }

    private void clickCSubmitResults(){
        driver.findElement(submitRes).click();
    }

    public void PassTest(){
        //answer all questions
        this.AnswerQuestionsRandomly();
        //wait for button to become clickable
        //Press the button
        this.clickCSubmitResults();
    }


    public String insertTwitterLink(){
        return driver.findElement(twitLink).getAttribute("href");
    }

    public String insertTwitterImage(){
        return driver.findElement(twitImage).getAttribute("xlink:href");
    }
}

