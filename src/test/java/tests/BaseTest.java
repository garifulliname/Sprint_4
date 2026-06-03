package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MainPage.BASE_URL);
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


