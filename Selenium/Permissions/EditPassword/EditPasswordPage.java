package Permissions.EditPassword;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPasswordPage extends GlobalPage {

    public EditPasswordPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //    Ссылка "Забыли пароль?"
    @FindBy(xpath = "//a[contains(text(),\"Забыли пароль?\")]")
    public WebElement linkForgotPassword;

    //    Поле ввода email с проверкой на текст "Введите email для восстановления пароля"
    @FindBy(css = ".el-input__inner[placeholder=\"Введите email для восстановления пароля\"]")
    public WebElement recoveryInputEmail;

    //    Кнопка "Восстановить пароль"
    @FindBy(xpath = "//button[contains(text(),\"Восстановить пароль\")]")
    public WebElement buttonRecoveryPassword;

    //Поле ввода нового пароля при восстановлении пароля
    @FindBy(xpath = "//div[@class=\"Password\"]//input[@placeholder=\"Введите новый пароль\"]")
    public WebElement inputRecoveryNewPassword;

    //Поле ввода повтора нового пароля при восстановлении пароля
    @FindBy(xpath = "//input[@placeholder=\"Повторите ввод пароля\"]")
    public WebElement inputRecoveryRepeatNewPasswordi;

    //Кнопка "Установить новый пароль"
    @FindBy(xpath = "//button[text()=\"Установить новый пароль\"]")
    public WebElement buttonSetNewPassword;

    //Поле ввода нового пароля при изменении пароля
    @FindBy(xpath = "//div[@class=\"Password\"]//input[@placeholder=\"Новый пароль\"]")
    public WebElement inputNewPassword;

    //Поле ввода повтора нового пароля при изменении пароля
    @FindBy(xpath = "//input[@placeholder=\"Повторите пароль\"]")
    public WebElement inputRepeatNewPassword;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    Проверка письма в Яндексе
    @FindBy(xpath = "//span[text()=\"Войти в почту\"]/..")
    public WebElement enterToPostYandex;

    @FindBy(name = "login")
    public WebElement loginFormYandex;

    @FindBy(name = "passwd")
    public WebElement passwordFormYandex;

    @FindBy(xpath = "//button//span[text()=\"Войти\"]/../..")
    public WebElement enterButtonYandex;

    @FindBy(xpath = "//span[text()=\"Банк здоровья\"]")
    public WebElement nameLetterYandex;

    @FindBy(xpath = "//span[@class=\"mail-MessageSnippet-Item mail-MessageSnippet-Item_subject\"]/span")
    public WebElement descriptionLetterYandex;

    @FindBy(xpath = "//span[@class=\"mail-MessageSnippet-Item_dateText\"]")
    public WebElement dateLetterYandex;

    @FindBy(xpath = "//div[1]/label[@class=\"mail-Toolbar-Item-Checkbox\"]/span")
    public WebElement checkboxAllLettersYandex;

    @FindBy(xpath = "//span[text()=\"Удалить\"]")
    public WebElement iconDeleteAllLettersYandex;

    // Заголовок письма в Яндексе
    @FindBy(xpath = "//body/div[2]/div[5]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div[3]/div/div/table/tbody/tr[2]/td/table/tbody/tr[1]/td[@style=\"padding:25px 20px;\"]")
    public WebElement headerLetterYandex;

    // Тело письма в Яндексе
    @FindBy(xpath = "//body/div[2]/div[5]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div[3]/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[@style=\"padding:0 20px 30px;\"]")
    public WebElement bodyLetterYandex;

    // Код восстановления в Яндексе
    @FindBy(xpath = "//span[text()=\"Код восстановления\"]/following-sibling::span")
    public WebElement codeLetterYandex;

    /////////////////////////////////////////////////////////////////////////////////////////
    // Проверка письма в Google
    @FindBy(xpath = "//input[@type=\"email\"]")
    public WebElement loginFormGoogleMail;

    @FindBy(xpath = "//input[@type=\"password\"]")
    public WebElement passwordFormGoogleMail;

    @FindBy(xpath = "//span[text()=\"Далее\"]")
    public WebElement buttonFurtherGoogleMail;

    @FindBy(xpath = "(//span[text()=\"Банк здоровья\"])[last()]")
    public WebElement nameLetterGoogleMail;

    @FindBy(xpath = "//span[@class=\"bog\"]")
    public WebElement descriptionLetterGoogleMail;

    @FindBy(xpath = "//tr[@jsmodel=\"nXDxbd\"]//td[9]/span")
    public WebElement dateLetterGoogleMail;

    @FindBy(xpath = "//div[@data-tooltip=\"Выбрать\"]/div[1]/span")
    public WebElement checkboxAllLettersGoogleMail;

    @FindBy(xpath = "//div[@data-tooltip=\"Удалить\"]/div")
    public WebElement iconDeleteAllLettersGoogleMail;

    // Проверка письма в Mail
    @FindBy(name = "login")
    public WebElement loginFormMailRu;

    @FindBy(name = "password")
    public WebElement passwordFormMailRu;

    @FindBy(xpath = "//label[text()=\"Войти\"]")
    public WebElement enterButtonMailRu;

    @FindBy(xpath = "//div[@class=\"b-datalist__item__addr\"]")
    public WebElement nameLetterMailRu;

    @FindBy(xpath = "//div[@class=\"b-datalist__item__subj\"]")
    public WebElement descriptionLetterMailRu;

    @FindBy(xpath = "//div[@class=\"b-datalist__item__date\"]/span")
    public WebElement dateLetterMailRu;

    @FindBy(xpath = "(//div[@class=\"b-checkbox__box\"])[1]")
    public WebElement checkboxAllLettersMailRu;

    @FindBy(xpath = "//*[@id=\"b-toolbar__right\"]/div[2]/div/div[2]/div[2]/div/div[1]")
    public WebElement iconDeleteAllLettersMailRu;

    //--------------------------------------------------------------------------
//    Письмо глобадьные
    // Картинка Логотипа
    @FindBy(xpath = "//div[1]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/img[contains (@src,\"logo-sechenov-dark.png\")]")
    public WebElement imgLogoLetter;

    // Имя Логотипа
    @FindBy(xpath = "//div[1]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/span[text()=\"Банк здоровья\"]")
    public WebElement nameLogoLetter;

    // Заголовок письма
    @FindBy(xpath = "//div[1]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[@style=\"padding:25px 20px\"]")
    public WebElement headerLetter;

    // Тело письма
    @FindBy(xpath = "//div[1]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody")
    public WebElement bodyLetter;

    // Кнопка "Войти"
    @FindBy(xpath = "//a[text()=\"Войти\"]")
    public WebElement buttonEnterLetter;

    // Логотип "Сеченовский Университет"
    @FindBy(xpath = "//div[1]/table/tbody/tr[4]/td/span[1]/table/tbody/tr/td/img[contains (@src,\"logo-sechenov-full-dark.png\")]")
    public WebElement imgLogoSechenovLetter;

    // Логотип "Lions"
    @FindBy(xpath = "//div[1]/table/tbody/tr[4]/td/span[2]/table/tbody/tr/td[2]/img[contains (@src,\"ldp-logo-dark-job-full.png\")]")
    public WebElement imgLogoLionsLetter;

    //  Письмо MailRu
    // Картинка Логотипа
    @FindBy(xpath = "//div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/img[@alt=\"Банк Здоровья\"]")
    public WebElement imgLogoLetterMailRu;

    // Имя Логотипа
    @FindBy(xpath = "//div[1]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/span[text()=\"Банк здоровья\"]")
    public WebElement nameLogoLetterMailRu;

    // Заголовок письма
    @FindBy(xpath = "//div/table/tbody/tr[2]/td/table/tbody/tr[1]/td")
    public WebElement headerLetterMailRu;

    // Тело письма
    @FindBy(xpath = "//div[1]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody")
    public WebElement bodyLetterMailRu;

    // Кнопка "Войти"
    @FindBy(xpath = "//a[text()=\"Войти\"]")
    public WebElement buttonEnterLetterMailRu;

    // Логотип "Сеченовский Университет"
    @FindBy(xpath = "//div/table/tbody/tr[4]/td/span[1]/table/tbody/tr/td/img[@alt=\"Сеченовский университет\"]")
    public WebElement imgLogoSechenovLetterMailRu;

    // Логотип "Lions"
    @FindBy(xpath = "//div/table/tbody/tr[4]/td/span[2]/table/tbody/tr/td[2]/img[@alt='Lions Digital Pro']")
    public WebElement imgLogoLionsLetterMailRu;
}



