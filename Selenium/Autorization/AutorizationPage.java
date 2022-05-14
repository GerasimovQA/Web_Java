package Autorization;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutorizationPage extends GlobalPage {

    public AutorizationPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Текст ошибки при не заполненной паре логин/пароль
    @FindBy(css = ".login-page-form__error")
    public WebElement emptyloginpassword;
    // Текст ошибки при не неправильной паре логин/пароль
    @FindBy(xpath = "//p[contains(text(), \"Неверный логин или пароль\")]")
    public WebElement errorloginpassword;


    public void autorization(WebElement element, String phrase) {
        waitTextPresent(element, phrase);
        if (element.getText().equals(phrase)) {
            System.out.println("Проверка пройдена");
            return;
        }
        throw new Error("Проверка НЕ пройдена");
    }
}
