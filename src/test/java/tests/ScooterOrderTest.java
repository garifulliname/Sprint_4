package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTest {
    // Данные для теста
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String color;
    private String rentalPeriod;
    private String comment;
    private boolean useBottomButton;

    public ScooterOrderTest(String name, String surname, String address, String metroStation,
                            String phone, String date, String color, String rentalPeriod,
                            String comment, boolean useBottomButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
        this.useBottomButton = useBottomButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {

                {"Андрей", "Иванов", "Русаковская улица, 31", "Сокольники", "+79999999999",
                        "10.06.2026", "серая безысходность", "двое суток", "Позвонить за полчаса", true},

                {"Иван", "Андреев", "Ломоносовский проспект, 23", "Университет", "+79111111111",
                        "10.06.2026", "чёрный жемчуг", "сутки", "", false}
        });
    }

    @Test
    public void testScooterOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Открываем главную страницу
        driver.get(MainPage.BASE_URL);

        // Выбираем точку входа
        if (useBottomButton) {
            mainPage.clickBottomOrderButton();
        } else {
            mainPage.clickTopOrderButton();
        }

        // Заполняем первую форму
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.setPhone(phone);
        orderPage.clickNextButton();

        // Заполняем вторую форму
        orderPage.setDate(date);
        orderPage.selectRentalPeriod(rentalPeriod);
        orderPage.selectColor(color);
        orderPage.setComment(comment);
        orderPage.clickOrderButton();

        // Подтверждаем заказ в всплывающем окне
        orderPage.confirmOrder();

        // Проверяем итоговый статус (сообщение об успехе)
        String successText = orderPage.getConfirmationText();
        assertTrue("Заказ не был успешно оформлен",
                successText.contains("Спасибо за ваш заказ!") ||
                        successText.contains("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии драйвера: " + e.getMessage());
            }
        }
    }
}
