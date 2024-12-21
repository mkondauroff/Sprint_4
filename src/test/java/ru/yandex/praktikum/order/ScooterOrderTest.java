package ru.yandex.praktikum.order;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.junit.runners.Parameterized;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pages.HomePageScooter;
import ru.yandex.praktikum.pages.OrderPageScooter;

import static ru.yandex.praktikum.pages.OrderPageScooter.TEXT_ORDER_APPROVED;

@RunWith(Parameterized.class)

public class ScooterOrderTest {
    private final String name;
    private final String surName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String comment;

    private WebDriver driver;
    private String browser;

    public ScooterOrderTest(String browser, String name, String surName, String address, String metro, String phone, String date, String period, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;
        this.browser = browser;

    }

    @Parameterized.Parameters
    public static Object[][] setStepsOfOrders() {
        return new Object[][]{
                {"FireFox", "Петр", "Петров", "Тверь, ул. Ленина д.1, стр.1, кв.1", "Цветной бульвар", "89998887766", "25.12.24", "сутки", "Комментарий для курьера"},
                {"FireFox", "Иван", "Иванов", "Саратов, ул. Сталина д.2, стр.2, кв.2", "Чистые пруды", "89998887755", "25.12.2025", "пятеро суток", "2"},
                {"ChromeDriver", "Петр", "Петров", "Тверь, ул. Ленина д.1, стр.1, кв.1", "Цветной бульвар", "89998887766", "25.12.24", "сутки", "Комментарий для курьера"},
                {"ChromeDriver", "Иван", "Иванов", "Саратов, ул. Сталина д.2, стр.2, кв.2", "Чистые пруды", "89998887755", "25.12.2025", "пятеро суток", "2"},
        };
    }

    @Before
    public void before() {
        if (browser.equals("FireFox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    @Test
    public void ordersTestHeaderOrderButton() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);

        // проверка заголовка страницы, переход на форму оформления заказа
        homePageScooter.openPage();
        homePageScooter.clickHeaderOrderButton();
        Assert.assertTrue(orderPageScooter.isOrderTitleTextHeaderDisplayed());

        // заполнение первого шага заказа
        orderPageScooter.setFieldsInFirstStepOrderFilling(name, surName, address, metro, phone);
        orderPageScooter.clickNext();
        Assert.assertTrue(orderPageScooter.isRentTitleTextHeaderDisplayed());

        // заполнение второго шага заказа
        orderPageScooter.setFieldsInSecondStepOrderFilling(date, period, comment);
        orderPageScooter.clickOrder();
        Assert.assertTrue(orderPageScooter.isModalTitleTextHeaderDisplayed());

        // клик по кнопке ДА в модальном окне подтверждения заказа
        orderPageScooter.clickYes(); // для Chrome тут фейл

        // проверка отображения модального окна
        Assert.assertTrue(orderPageScooter.isModalTitleOrderPlacedDisplayed());

        // проверка, что заказ оформлен
        String actualText = driver.findElement(TEXT_ORDER_APPROVED).getText();

        Assert.assertTrue(actualText.contains("Заказ оформлен"));
        Assert.assertTrue(actualText.contains("Номер заказа:"));
        Assert.assertTrue(actualText.contains("Запишите его:"));
        Assert.assertTrue(actualText.contains("пригодится, чтобы отслеживать статус"));

    }
    @Test
    public void ordersTestMiddleOrderButton() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);

        // проверка заголовка страницы, переход на форму оформления заказа
        homePageScooter.openPage();
        homePageScooter.clickMiddleOrderButton();
        Assert.assertTrue(orderPageScooter.isOrderTitleTextHeaderDisplayed());

        // заполнение первого шага заказа
        orderPageScooter.setFieldsInFirstStepOrderFilling(name, surName, address, metro, phone);
        orderPageScooter.clickNext();
        Assert.assertTrue(orderPageScooter.isRentTitleTextHeaderDisplayed());

        // заполнение второго шага заказа
        orderPageScooter.setFieldsInSecondStepOrderFilling(date, period, comment);
        orderPageScooter.clickOrder();
        Assert.assertTrue(orderPageScooter.isModalTitleTextHeaderDisplayed());

        // клик по кнопке ДА в модальном окне подтверждения заказа
        orderPageScooter.clickYes(); // для Chrome тут фейл

        // проверка отображения модального окна
        Assert.assertTrue(orderPageScooter.isModalTitleOrderPlacedDisplayed());

        String actualText = driver.findElement(TEXT_ORDER_APPROVED).getText();

        // проверка, что заказ оформлен
        Assert.assertTrue(actualText.contains("Заказ оформлен"));
        Assert.assertTrue(actualText.contains("Номер заказа:"));
        Assert.assertTrue(actualText.contains("Запишите его:"));
        Assert.assertTrue(actualText.contains("пригодится, чтобы отслеживать статус"));
    }

    @After
    public void after() {
        driver.quit();
    }
}
