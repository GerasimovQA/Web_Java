package edituser;

import global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

public class EditUserPage extends GlobalPage {

    public EditUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Глобальные объекты
    //    Кнопка СОХРАНИТЬ
    @FindBy(xpath = "//button/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveProfile;

    //    Кнопка ОТМЕНА
    @FindBy(xpath = "//button/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelProfile;

    //--------------------------------------------------------------------------
    //  Учетная запись
    //    Фамилия
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(1)")
    public WebElement firstNameProfile;

    //    Имя
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(2)")
    public WebElement secondNameProfile;

    //    Отчество
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(3)")
    public WebElement middleNameProfile;

    //    Ссылка редактирования ФИО
    @FindBy(xpath = "//div[contains (text(), \"ФИО\")]/following-sibling::div[2]")
    public WebElement linkEditfioProfile;

    //    Поле ввода ФАМИЛИЯ
    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
    public WebElement inputFirstName;

    //    Поле ввода ИМЯ
    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
    public WebElement inputSecondName;

    //    Поле ввода Отчество
    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
    public WebElement inputMiddleName;
//-----------------------------------------------------------------------------
    //    Логин
    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
    public WebElement loginProfile;

    //    Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement emailProfile;

    //    Phone
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement phoneProfile;

    //    Ссылка редактирования логин/email/телефон
    @FindBy(xpath = "//div[contains (text(), \"Данные для входа\")]/following-sibling::div[4]")
    public WebElement linkEditAuthProfile;

    //    Поле ввода ЛОГИН
    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
    public WebElement inputLogin;

    //    Поле ввода EMAIL
    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
    public WebElement inputEmail;

    //    Поле ввода ТЕЛЕФОН
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
    public WebElement inputPhone;
//--------------------------------------------------------------------------------
    //    Ссылка изменения пароля
    @FindBy(xpath = "//div[contains (text(), \"Пароль\")]/following-sibling::div")
    public WebElement linkEditPasswordProfile;

    //    Поле ввода ПАРОЛЬ
    @FindBy(css = ".el-input__inner[placeholder=\"Новый пароль\"]")
    public WebElement inputNewPassword;

    //    Поле ввода ПОВТОРИТЕ ПАРОЛЬ
    @FindBy(css = ".el-input__inner[placeholder=\"Повторите пароль\"]")
    public WebElement inputRepeatNewPassword;
//----------------------------------------------------------------------------------
    //    Текущий статус
    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/..//following-sibling::div/span[@class='el-tag el-tag--success']")
    public WebElement statusProfile;

    //    Ссылка изменения статуса
    @FindBy(xpath = "//span[contains (text(), \"Активный\")]")
    public WebElement linkEditStatusProfile;

    //    Выпадающий список СТАТУС
    @FindBy(css = ".el-input__inner[placeholder=\"Статус\"]")
    public WebElement listStatus;

    //    Пункт АКТИВЕН в выпадающем списке СТАТУС
    @FindBy(xpath = "//li/span[contains (text(), \"Активен\")]")
    public WebElement activeStatus;

    //    Пункт ОТКЛЮЧЕН в выпадающем списке СТАТУС
    @FindBy(xpath = "//li/span[contains (text(), \"Отключен\")]")
    public WebElement disbleStatus;
}



