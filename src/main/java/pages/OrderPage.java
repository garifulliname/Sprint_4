package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для первой формы заказа
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы для второй формы заказа
    private By dateField = By.cssSelector("div.react-datepicker__input-container input[placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.cssSelector("div.Dropdown-placeholder:not([style*='display: none'])");
    private By greyColorCheckbox = By.id("grey");
    private By blackColorCheckbox = By.id("black");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)");

    // Локаторы окна подтверждения заказа
    private By confirmationText = By.className("Order_ModalHeader__3FDaJ");
    private By yesButton = By.xpath("//button[text()='Да']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    // Методы для заполнения первой формы
    public void setName(String name) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        element.clear();
        element.sendKeys(name);
    }

    public void setSurname(String surname) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(surnameField));
        element.clear();
        element.sendKeys(surname);
    }

    public void setAddress(String address) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addressField));
        element.clear();
        element.sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        wait.until(ExpectedConditions.elementToBeClickable(metroStationInput)).click();
        By stationLocator = By.xpath("//div[contains(@class, 'select-search__select')]//*[contains(., '" + stationName + "')]");
        WebElement stationElement = wait.until(ExpectedConditions.elementToBeClickable(stationLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(stationElement).build().perform();
        stationElement.click();
    }

    public void setPhone(String phone) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(phoneField));
        element.clear();
        element.sendKeys(phone);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    // Методы для заполнения второй формы
    public void setDate(String date) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        element.clear();
        element.sendKeys(date);

        // Ждём закрытия всего календаря после ввода даты
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.className("react-datepicker")));
            System.out.println("Календарь закрыт после ввода даты");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Календарь не закрылся автоматически, пытаемся закрыть вручную");
            // Попытка закрыть календарь кликом вне поля
            Actions actions = new Actions(driver);
            actions.moveByOffset(0, 0).click().build().perform();

        }
    }

    public void selectRentalPeriod(String period) {
        // Ждём, пока выпадающий список станет кликабельным, и кликаем
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        dropdown.click();

        // Формируем XPath для поиска нужного периода
        String xpath = "//div[contains(text(), '" + period + "')]";
        By periodOption = By.xpath(xpath);

        // Ждём появления опции в выпадающем списке
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(periodOption));

        // Прокручиваем к элементу, чтобы он стал видимым
        Actions actions = new Actions(driver);
        actions.moveToElement(option).build().perform();

        // Пытаемся кликнуть через JavaScriptExecutor, если обычный клик не работает
        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Обычный клик не работает, используем JavaScriptExecutor");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
    }

    public void selectColor(String color) {
        By colorCheckbox = color.equals("серая безысходность") ? greyColorCheckbox : blackColorCheckbox;
        wait.until(ExpectedConditions.elementToBeClickable(colorCheckbox)).click();
    }

    public void setComment(String comment) {
        if (!comment.isEmpty()) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
            element.clear();
            element.sendKeys(comment);
        }
    }

    // Метод клика по кнопке с ожиданием кликабельности
    public void clickOrderButton() {
        WebElement orderButtonElement = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        orderButtonElement.click();
    }

    // Методы для работы с окном подтверждения
    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationText)).getText();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }
}
