package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы для раздела «Вопросы о важном»
    public static By costAndPaymentArrow = By.id("accordion__heading-0");
    public static By costAndPaymentAnswer = By.xpath("(//div[@class='accordion__panel'])[1]");
    public static By severalScootersArrow = By.id("accordion__heading-1");
    public static By severalScootersAnswer = By.xpath("(//div[@class='accordion__panel'])[2]");
    public static By rentalTimeCalculationArrow = By.id("accordion__heading-2");
    public static By rentalTimeCalculationAnswer = By.xpath("(//div[@class='accordion__panel'])[3]");
    public static By orderTodayArrow = By.id("accordion__heading-3");
    public static By orderTodayAnswer = By.xpath("(//div[@class='accordion__panel'])[4]");
    public static By extendOrReturnArrow = By.id("accordion__heading-4");
    public static By extendOrReturnAnswer = By.xpath("(//div[@class='accordion__panel'])[5]");
    public static By chargingWithScooterArrow = By.id("accordion__heading-5");
    public static By chargingWithScooterAnswer = By.xpath("(//div[@class='accordion__panel'])[6]");
    public static By cancelOrderArrow = By.id("accordion__heading-6");
    public static By cancelOrderAnswer = By.xpath("(//div[@class='accordion__panel'])[7]");
    public static By deliveryOutsideMKADArrow = By.id("accordion__heading-7");
    public static By deliveryOutsideMKADAnswer = By.xpath("(//div[@class='accordion__panel'])[8]");

    // Локаторы для кнопок «Заказать»
    private By topOrderButton = By.className("Button_Button__ra12g");
    private By bottomOrderButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");

    // Локатор для проверки загрузки страницы
    private By pageHeader = By.className("Home_Header__Z23ro");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    //Открываем главную страницу
    public void open() {
        driver.get(BASE_URL);
        System.out.println("Главная страница открыта: " + BASE_URL);

        // Ждём загрузки заголовка страницы
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
            System.out.println("Страница успешно загружена");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Заголовок страницы не загрузился");
            throw e;
        }
    }

    public String getAnswerText(By answerLocator) {
        // Ждём, пока элемент станет видимым, и возвращаем его текст
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return element.getText();
    }

    public void clickQuestionArrow(By arrowLocator) {
        // Ждём, пока элемент можно будет кликнуть
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(arrowLocator));

        // Прокручиваем элемент в видимую область страницы и кликаем
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    //Кликаем по верхней кнопке «Заказать»
    public void clickTopOrderButton() {
        acceptCookiesIfPresent();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(topOrderButton));
        button.click();
        System.out.println("Клик по верхней кнопке «Заказать» выполнен");
    }

    //Кликаем по нижней кнопке «Заказать»
    public void clickBottomOrderButton() {
        acceptCookiesIfPresent();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
        button.click();
        System.out.println("Клик по нижней кнопке «Заказать» выполнен");
    }

    //Принимаем куки
    public void acceptCookiesIfPresent() {
        try {
            By cookieBanner = By.cssSelector("[class*='CookieConsent']");
            By cookieAcceptButton = By.xpath("//button[text()='да все привыкли']");

            if (driver.findElement(cookieBanner).isDisplayed()) {
                driver.findElement(cookieAcceptButton).click();
                Thread.sleep(500);
                System.out.println("Баннер с куки принят");
            }
        } catch (Exception e) {
            // Баннера нет — продолжаем
            System.out.println("Баннер с куки не обнаружен, продолжаем выполнение");
        }
    }
}

