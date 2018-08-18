package global;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GlobalPage {

    private static String resourcesImagesDir = ConfigProperties.getTestProperty("Screen");
    public static String expectedDir = resourcesImagesDir + ConfigProperties.getTestProperty("expected");
    public static String actualDir = resourcesImagesDir + ConfigProperties.getTestProperty("actual");
    public static String diffDir = resourcesImagesDir + ConfigProperties.getTestProperty("diff");
    public static String NameFileShowPassword = "ShowPassword";


    public static String LoginAUT = "admin";
    public static String PasswordAUT = "qwe123";


    @FindBy(css = ".el-input__inner[placeholder=\"Введите логин, email или телефон\"]")
    public WebElement authInputLogin;
    //    Поле ввода пароля с проверкой на текст "Введите пароль"
    @FindBy(css = ".el-input__inner[placeholder=\"Введите пароль\"]")
    public WebElement authInputPassword;
    // Кнопка Войти
    @FindBy(xpath = "//button[contains (text(), \"Войти\")]")
    public WebElement authButtonEnter;

    // Проверочный блок Главная страница - Информация о пользователе
    @FindBy(css = ".user-info__name")
    public WebElement userinfoname;


    public WebDriver driver;


    public void waitE_ClickableAndClick(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void waitE_ClickableAndSendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
//        ((Locatable) element).getCoordinates();
        iWait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public void waitE_evisibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitE_invisibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        try {

            iWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String waitE_visibilityOfAndGettext(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    public String waitE_TextPresent(WebElement element, String phrase) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.textToBePresentInElement(element, phrase));
        String text = element.getText();
        System.out.println(element.getText() + " ====== " + phrase);
        return text;
    }

    public void auth(String Login, String Password) {
        waitE_ClickableAndSendKeys(authInputLogin, Login);
        waitE_ClickableAndSendKeys(authInputPassword, Password);
        waitE_ClickableAndClick(authButtonEnter);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void moveMouse(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void screenFailedTest(String Name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
//        String dest = diffDir + diffDir + Name + ".png";
        String delimeter = "\\|"; // Разделитель
        String[] subStr = Name.split(delimeter);
        File destination = new File(diffDir + subStr[0] + ".png");
        System.out.println(diffDir + subStr[0] + ".png");

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Скриншот не сохранился");
        }
    }

    public void scrollWithOffset(WebElement webElement, int x, int y) {

        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor) driver).executeScript(code, webElement, x, y);
    }
}