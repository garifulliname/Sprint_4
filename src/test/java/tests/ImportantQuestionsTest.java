package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest extends BaseTest {
    // Данные для теста: локатор стрелки, локатор ответа, ожидаемый текст
    private By arrowLocator;
    private By answerLocator;
    private String expectedAnswer;

    public ImportantQuestionsTest(By arrowLocator, By answerLocator, String expectedAnswer) {
        this.arrowLocator = arrowLocator;
        this.answerLocator = answerLocator;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Вопрос 1: Стоимость и оплата
                {MainPage.costAndPaymentArrow, MainPage.costAndPaymentAnswer,
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},

                // Вопрос 2: Несколько самокатов
                {MainPage.severalScootersArrow, MainPage.severalScootersAnswer,
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},

                // Вопрос 3: Расчёт времени аренды
                {MainPage.rentalTimeCalculationArrow, MainPage.rentalTimeCalculationAnswer,
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},

                // Вопрос 4: Заказать сегодня
                {MainPage.orderTodayArrow, MainPage.orderTodayAnswer,
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},

                // Вопрос 5: Продлить или вернуть
                {MainPage.extendOrReturnArrow, MainPage.extendOrReturnAnswer,
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},

                // Вопрос 6: Зарядка с самокатом
                {MainPage.chargingWithScooterArrow, MainPage.chargingWithScooterAnswer,
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},

                // Вопрос 7: Отмена заказа
                {MainPage.cancelOrderArrow, MainPage.cancelOrderAnswer,
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},

                // Вопрос 8: Доставка за МКАД
                {MainPage.deliveryOutsideMKADArrow, MainPage.deliveryOutsideMKADAnswer,
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void testFaqQuestion() {
        // Кликаем на стрелку вопроса
        mainPage.clickQuestionArrow(arrowLocator);

        // Получаем текст ответа и сравниваем с ожидаемым
        assertEquals(expectedAnswer, mainPage.getAnswerText(answerLocator));
    }
}