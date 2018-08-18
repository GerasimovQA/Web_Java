package autorization;

import global.GlobalPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutorizationPage extends GlobalPage {

    public AutorizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //    Вход в аккаунт
    //Поле ввода логина с проверкой на текст "Введите логин, email или телефон"


    // Текст ошибки при не заполненной паре логин/пароль
    @FindBy(css = ".login-page-form__error")
    public WebElement emptyloginpassword;
    // Текст ошибки при не неправильной паре логин/пароль
    @FindBy(css = ".el-notification__content>p")
    public WebElement errorloginpassword;


    public void autorization(WebElement element, String phrase) {

        waitE_TextPresent(element, phrase);
        if (element.getText().equals(phrase)) {
            System.out.println("Проверка пройдена");
            return;
        }
        throw new Error("Проверка НЕ пройдена");
    }
}
