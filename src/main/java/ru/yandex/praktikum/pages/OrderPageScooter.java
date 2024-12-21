package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.pages.HomePageScooter.DEFAULT_TIMEOUT;

public class OrderPageScooter {

    private static final By ORDER_TEXT_HOME_HEADER = By.xpath(".//div[contains(text(),'Для кого самокат')]");
    private static final By RENT_TEXT_HOME_HEADER = By.xpath(".//div[contains(text(),'Про аренду')]");
    private static final By FIELD_NAME = By.xpath(".//input[@placeholder='* Имя']");
    private static final By FIELD_SURNAME = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By FIELD_ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By FIELD_STATION_OF_METRO = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By FIELD_PHONE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By BUTTON_APPROVE = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    private static final By BUTTON_APPROVE_ORDER= By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and (text()='Заказать')]");
    private static final By BUTTON_YES = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and (text()='Да')]");
    private static final By DATE_OF_RECEIVED = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD = By.xpath(".//*[@class= 'Dropdown-arrow']");
    private static final By BLACK_COLOR = By.id("black");
    private static final By GREY_COLOR = By.id("grey");
    private static final By COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By ORDER_MODAL = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and (text()='Хотите оформить заказ?')]");
    public static final By FINAL_ORDER_MODAL = By.xpath(".//div[@class='Order_Text__2broi']");

    public static final By TEXT_ORDER_APPROVED = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    private WebDriver driver;
    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOrderTitleTextHeaderDisplayed () {
        WebElement orderTextHomeHeader = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_TEXT_HOME_HEADER));
        return orderTextHomeHeader.isDisplayed();
    }

    public boolean isRentTitleTextHeaderDisplayed () {
        WebElement orderTextHomeHeader = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(RENT_TEXT_HOME_HEADER));
        return orderTextHomeHeader.isDisplayed();
    }

    public void setName (String name) {
        WebElement fieldName =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(FIELD_NAME));
        fieldName.sendKeys(name);
    }

    public void setSurname (String surname) {
        WebElement fieldSurname =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(FIELD_SURNAME));
        fieldSurname.sendKeys(surname);
    }

    public void setAddress (String address) {
        WebElement fieldAddress =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(FIELD_ADDRESS));
        fieldAddress.sendKeys(address);
    }
    public void setNameOfStationMetro (String metro) {
        WebElement fieldStationOfMetro =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(FIELD_STATION_OF_METRO));
        fieldStationOfMetro.sendKeys(metro);
        WebElement selectStation =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='Order_Text__2broi' and text() = '" + metro + "']")));
        selectStation.click();
        }

    public void setPhone (String phone) {
        WebElement fieldPhone =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(FIELD_PHONE));
        fieldPhone.sendKeys(phone);
    }

    public void chooseDate(String date) {
        WebElement fieldDate =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(DATE_OF_RECEIVED));
        fieldDate.sendKeys(date);
    }

    public void chooseRent(String period){
        driver.findElement(RENTAL_PERIOD).click();
        WebElement fieldRent = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class= 'Dropdown-option' and text() = '" + period +"']")));
        fieldRent.click();
    }

    public void chooseColorOfScooter() {
        driver.findElement(BLACK_COLOR).click();
        driver.findElement(GREY_COLOR).click();
    }
    public void sentComment (String comment) {
        driver.findElement(COMMENT)
                .sendKeys(comment);
    }

    public void clickNext () {
        driver.findElement(BUTTON_APPROVE).click();
    }
    public void clickOrder () {
        driver.findElement(BUTTON_APPROVE_ORDER).click();
    }
    public void clickYes () {
        driver.findElement(BUTTON_YES).click();
    }

    public void setFieldsInFirstStepOrderFilling (String name, String surname, String address, String metro, String phone) {
        setName(name);
        setSurname (surname);
        setAddress (address);
        setNameOfStationMetro (metro);
        setPhone (phone);
    }

    public void setFieldsInSecondStepOrderFilling (String date, String period, String comment) {
        chooseDate(date);
        chooseRent(period);
        chooseColorOfScooter();
        sentComment(comment);

    }

    public boolean isModalTitleTextHeaderDisplayed () {
        WebElement textOrderModal = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_MODAL));
        return textOrderModal.isDisplayed();
    }

    public boolean isModalTitleOrderPlacedDisplayed () {
        WebElement textOrderModal = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(FINAL_ORDER_MODAL));
        return textOrderModal.isDisplayed();
    }
}
