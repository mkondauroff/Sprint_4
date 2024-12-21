package ru.yandex.praktikum.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {

    public static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);
    private static final By TEXT_HOME_HEADER = By.xpath(".//div[@class='Home_Header__iJKdX' and text()='Самокат ']");
    private static final By QUESTIONS_ABOUT_IMPORTANT = By.xpath(".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']");
    private static final By HEADER_BUTTON_ORDER = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By MIDDLE_BUTTON_ORDER = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By HOME_ROAD_MAP = By.xpath(".//div[@class='Home_RoadMap__2tal_']");
    public static final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private String BUTTON_QUESTION = ".//div[text()[contains(.,'%s')]]";
    private String TEXT_QUESTION = ".//p[text()[contains(.,'%s')]]";

    private WebDriver driver;

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(HOME_PAGE_URL);
    }

    public boolean isTitleTextHeaderDisplayed() {
        WebElement textHomeHeader = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_HOME_HEADER));
        return textHomeHeader.isDisplayed();
    }

    public boolean scrollToFaq() {
        WebElement findElement = driver.findElement(QUESTIONS_ABOUT_IMPORTANT);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findElement);
        return findElement.isDisplayed();
    }

    public void clickHeaderOrderButton() {
        WebElement headerOrderButton =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(HEADER_BUTTON_ORDER));
        headerOrderButton.click();
    }

    public void clickMiddleOrderButton() {
        WebElement findElement = driver.findElement(HOME_ROAD_MAP);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findElement);
        WebElement middleOrderButton =
                new WebDriverWait(driver, DEFAULT_TIMEOUT)
                        .until(ExpectedConditions.elementToBeClickable(MIDDLE_BUTTON_ORDER));
        middleOrderButton.click();
    }

    public void clickButtonByQuestion(String question) {
        driver.findElement(By.xpath(String.format(BUTTON_QUESTION, question))).click();

    }

    public boolean isAnsweredDisplayed(String answer) {
        return driver.findElement(By.xpath(String.format(TEXT_QUESTION, answer))).isDisplayed();
    }

}