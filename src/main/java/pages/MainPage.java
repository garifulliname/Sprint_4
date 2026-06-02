package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для раздела «Вопросы о важном»
    public By question1Arrow = By.id("accordion__heading-0");
    public By answer1 = By.xpath("(//div[@class='accordion__panel'])[1]");

    public By question2Arrow = By.id("accordion__heading-1");
    public By answer2 = By.xpath("(//div[@class='accordion__panel'])[2]");

    public By question3Arrow = By.id("accordion__heading-2");
    public By answer3 = By.xpath("(//div[@class='accordion__panel'])[3]");

    public By question4Arrow = By.id("accordion__heading-3");
    public By answer4 = By.xpath("(//div[@class='accordion__panel'])[4]");

    public By question5Arrow = By.id("accordion__heading-4");
    public By answer5 = By.xpath("(//div[@class='accordion__panel'])[5]");

    public By question6Arrow = By.id("accordion__heading-5");
    public By answer6 = By.xpath("(//div[@class='accordion__panel'])[6]");

    public By question7Arrow = By.id("accordion__heading-6");
    public By answer7 = By.xpath("(//div[@class='accordion__panel'])[7]");

    public By question8Arrow = By.id("accordion__heading-7");
    public By answer8 = By.xpath("(//div[@class='accordion__panel'])[8]");

    // Кнопки «Заказать»
    private By topOrderButton = By.className("Button_Button__ra12g");
    private By bottomOrderButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Методы для работы с вопросами
    public String getAnswerText(By answerLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();
    }

    public void clickQuestionArrow(By questionArrow) {
        wait.until(ExpectedConditions.elementToBeClickable(questionArrow)).click();
    }

    // Методы для кнопок заказа
    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void clickBottomOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton)).click();
    }
}
