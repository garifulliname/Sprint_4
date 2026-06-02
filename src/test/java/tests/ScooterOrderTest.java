package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    // Данные для параметризации
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;

    public ScooterOrderTest(String name, String surname, String address, String metro, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Андрей", "Иванов", "Русаковская улица, 31", "Сокольники", "+79999999999"},
                {"Иван", "Андреев", "Ломоносовский проспект, 23", "Университет", "+79111111111"}
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderViaTopButton() {
        // Сценарий через верхнюю кнопку «Заказать»
        mainPage.clickTopOrderButton();
        orderPage.fillFirstStep(name, surname, address, metro, phone);
        orderPage.clickNext();
        orderPage.fillSecondStep("10.06.2026", "чёрный жемчуг", "сутки", "");
        orderPage.submitOrder();
        orderPage.confirmOrder();
        assertTrue("Всплывающее окно об успешном заказе не появилось", orderPage.isOrderSuccessful());
    }

    @Test
    public void testOrderViaBottomButton() {
        // Сценарий через нижнюю кнопку «Заказать»
        mainPage.clickBottomOrderButton();
        orderPage.fillFirstStep(name, surname, address, metro, phone);
        orderPage.clickNext();
        orderPage.fillSecondStep("10.06.2026", "серая безысходность", "двое суток", "Позвонить за полчаса");
        orderPage.submitOrder();
        orderPage.confirmOrder();
        assertTrue("Всплывающее окно об успешном заказе не появилось", orderPage.isOrderSuccessful());
    }

    @After
    public void tearDown() {
            driver.quit();
        }
    }

