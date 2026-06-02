package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Шаг 1: заполняем все данные на первом экране
    public void fillFirstStep(String name, String surname, String address, String metro, String phone) {
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys(surname);
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(address);
        driver.findElement(By.xpath("//input[@placeholder='* Станция метро']")).sendKeys(metro);
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phone);
    }

    // Нажимаем кнопку «Далее»
    public void clickNext() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Далее']")));
        nextButton.click();
    }

    // Шаг 2: заполняем данные на втором экране
    public void fillSecondStep(String date, String color, String rentalPeriod, String comment) {
        // Вводим дату доставки
        driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']")).sendKeys(date);

        // Выбираем срок аренды
        driver.findElement(By.xpath("//div[@class='Dropdown-placeholder']")).click();
        By periodOption = By.xpath("//div[text()='" + rentalPeriod + "']");
        WebElement periodElement = wait.until(ExpectedConditions.elementToBeClickable(periodOption));
        periodElement.click();

        // Выбираем цвет
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(By.id("black")).click();
        } else if (color.equals("серая безысходность")) {
            driver.findElement(By.id("grey")).click();
        }

        // Добавляем комментарий (при необходимости)
        if (comment != null && !comment.isEmpty()) {
            driver.findElement(By.xpath("//textarea[@placeholder='Комментарий для курьера']")).sendKeys(comment);
        }
    }

    // Подтверждаем заказ
    public void submitOrder() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Заказать']")));
        orderButton.click();
    }

    // Подтверждаем во всплывающем окне («Да»)
    public void confirmOrder() {
        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Да']")));
        yesButton.click();
    }

    // Проверяем, что заказ успешно оформлен
    public boolean isOrderSuccessful() {
        WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("Order_ModalHeader__3FDaJ")));
        return successElement.isDisplayed();
    }
}
