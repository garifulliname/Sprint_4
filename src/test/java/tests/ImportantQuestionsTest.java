package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import static org.junit.Assert.assertEquals;

public class ImportantQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/"); // Исправлено: https:// → https://
        mainPage = new MainPage(driver);
    }

    @Test
    public void testImportantQuestions() {
        // Проверяем раскрытие вопросов и соответствие ответов

        // Вопрос 1: «Сколько это стоит? И как оплатить?»
        mainPage.clickQuestionArrow(mainPage.question1Arrow);
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                mainPage.getAnswerText(mainPage.answer1));

        // Вопрос 2: «Хочу сразу несколько самокатов! Так можно?»
        mainPage.clickQuestionArrow(mainPage.question2Arrow);
        assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                mainPage.getAnswerText(mainPage.answer2));

        // Вопрос 3: «Как рассчитывается время аренды?»
        mainPage.clickQuestionArrow(mainPage.question3Arrow);
        assertEquals("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                mainPage.getAnswerText(mainPage.answer3));

        // Вопрос 4: «Можно ли заказать самокат прямо на сегодня?»
        mainPage.clickQuestionArrow(mainPage.question4Arrow);
        assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                mainPage.getAnswerText(mainPage.answer4));


        // Вопрос 5: «Можно ли продлить заказ или вернуть самокат раньше?»
        mainPage.clickQuestionArrow(mainPage.question5Arrow);
        assertEquals("Пока что нет! Но если что‑то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                mainPage.getAnswerText(mainPage.answer5));

        // Вопрос 6: «Вы привозите зарядку вместе с самокатом?»
        mainPage.clickQuestionArrow(mainPage.question6Arrow);
        assertEquals("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                mainPage.getAnswerText(mainPage.answer6));

        // Вопрос 7: «Можно ли отменить заказ?»
        mainPage.clickQuestionArrow(mainPage.question7Arrow);
        assertEquals("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                mainPage.getAnswerText(mainPage.answer7));


        // Вопрос 8: «Я живу за МКАДом, привезёте?»
        mainPage.clickQuestionArrow(mainPage.question8Arrow);
        assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                mainPage.getAnswerText(mainPage.answer8));
    }


    @After
    public void tearDown() {
            driver.quit();
        }
    }

