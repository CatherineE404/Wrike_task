import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EmailInsertPage;
import pages.QuestionsPage;

public class MainTest {
    private static ChromeDriverService service;
    public static WebDriver driver;
    private EmailInsertPage objLogin;
    private QuestionsPage objQuestPage;

    @Before
    public void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\Ekaterina\\Desktop\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new ChromeDriver(service);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void simpleTest() {
        String URL1 = "https://wrike.com/";
        driver.get(URL1);
        //Create Login Page object
        objLogin = new EmailInsertPage(driver);
        //login to application
        String email = RandomStringUtils.random(7, true, false);
        objLogin.loginToAutoQA(email);
        // go the next page
        objQuestPage = new QuestionsPage(driver);
        new WebDriverWait(driver, 10000).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String URL2 = driver.getCurrentUrl();
        Assert.assertNotEquals(URL1, URL2);

        objQuestPage.PassTest();
        new WebDriverWait(driver, 10000).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //check that answers was submitted
        Assert.assertEquals((long) driver.findElements(By.xpath("//div[contains(@style, 'block;')]")).size(), (long) 1);

        //Check twitter link
        Assert.assertEquals(objQuestPage.insertTwitterLink(), "https://twitter.com/wrike");

        //Check twitter logo
        Assert.assertEquals(objQuestPage.insertTwitterImage(),"/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter");

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }
}