package edituser;

import global.GlobalPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserPage extends GlobalPage {

    public EditUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }



    //--------------------------------------------------------------------------
    //  Учетная запись


    //    Фамилия
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(1)")
    public WebElement secondNameProfile;



    //    Отчество
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(3)")
    public WebElement middleNameProfile;

    //    Ссылка редактирования ФИО
    @FindBy(xpath = "//div[contains (text(), \"ФИО\")]/following-sibling::div[2]")
    public WebElement linkEditfioProfile;

    //    Поле ввода ФАМИЛИЯ
    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
    public WebElement inputSecondName;

    //    Поле ввода ИМЯ
    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
    public WebElement inputFirstName;

    //    Поле ввода Отчество
    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
    public WebElement inputMiddleName;


    //    Кнопка СОХРАНИТЬ ФИО юзера
    @FindBy(xpath = "//div[3]//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveProfileFIO;
//-----------------------------------------------------------------------------


    //    Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement emailProfile;

    //    Phone
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement phoneProfile;


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

    //   Иконка "Глаз" - скрыть пароль
    @FindBy(css = ".show-password.fa.fa-eye-slash")
    public WebElement notShowPassword;

    //    Кнопка СОХРАНИТЬ ПАРОЛЬ
    @FindBy(xpath = "//div[3]//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSavePassword;

    //    Кнопка ОТМЕНА ПАРОЛЯ
    @FindBy(xpath = "//div[3]//span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelPassword;




    //----------------------------------------------------------------------------------
    //    Текущий статус
    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/..//following-sibling::div/span[contains (text(), \"к\")]")
    public WebElement statusProfile;

    //    Ссылка изменения статуса
    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/following-sibling::div[2]")
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

    //    Кнопка СОХРАНИТЬ СТАТУС
    @FindBy(xpath = "//div[4]//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveStatus;

    //    Кнопка ОТМЕНА СТАТУСА
    @FindBy(xpath = "//div[4]//span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelStatus;


//-----------------------------------------------------------------------------------------------------------
// Место работы и специальность

    //    Ссылка "изменить" место работы
    @FindBy(xpath = "//div[@class=\"edit-workplace__link-container\"]/div[contains (text(), \"Изменить\")]")
    public WebElement linkEditWorkplaces;

    //    Ссылка "добавить" место работы
    @FindBy(xpath = "//div[contains (text(), \"Добавить место работы\")]")
    public WebElement linkAddWorkplaces;

    //    Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(), \"Структура организации\")]")
    public WebElement structureOrganizations;

    //   Пункт головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]")
    public WebElement headOrganization;

    //   Буллет головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]/../label")
    public WebElement bulletHeadOrganization;

    //   Поле ввода "Должность"
    @FindBy(xpath = "//label[text()=\"Должность\"]")
    public WebElement inputPosition;

    //   Поле ввода "Роль сотрудника"
    @FindBy(xpath = "//input[@placeholder=\"Роль сотрудника\"]")
    public WebElement inputRoleEmployee;

    //   Пункт "Специалист" в поле ввода "Роль сотрудника"
    @FindBy(xpath = "//span[text() = \"Специалист\"]")
    public WebElement RoleEmployeeSpecialist;

    //   Кнопка "Сохранить" место работы сотрудника
    @FindBy(xpath = "//span[text() = \"Сохранить\"]")
    public WebElement buttonSaveWorkplaces;



//-----------------------------------------------------------------------------------------------------------
// Данные специалиста


    //    ФОТО юзера
    @FindBy(xpath = "//div[contains (text(), \"Фото\")]/following-sibling::div[1]//img")
    public WebElement photoProfile;

    //    Ссылка ИЗМЕНИТЬ ФОТО
    @FindBy(xpath = "//div[@class='profile-userpic__photo userpic userpic--medium']/..//following-sibling::div/div[contains (text(), \"Изменить\")]")
    public WebElement linkEditPhotoProfile;

    //    Кнопка СОХРАНИТЬ ФОТО
    @FindBy(xpath = "//div[2]/div/div/button/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSavePhotoProfile;

    //    Кнопка ОТМЕНИТЬ СОХРАНИЕ ФОТО
    @FindBy(xpath = "//div[contains (text(), \"Отмена\")]")
    public WebElement buttonSavePhotoCancelProfile;

    //    Образование, достижения, регалии
    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[1]")
    public WebElement educationProfile;

    //    Ссылка ИЗМЕНИТЬ Образование, достижения, регалии
    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[2]")
    public WebElement linkEditEducationProfile;

    //    Поле ввода Образование, достижения, регалии
    @FindBy(xpath = "//div[2]/div[2]/div/textarea")
    public WebElement inputEducation;

    //    Кнопка СОХРАНИТЬ ОБРАЗОВАНИЕ
    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveEducation;

    //    Кнопка ОТМЕНА СОХРАНЕНИЯ ОБРАЗОВАНИЯ
    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelEducation;

    //    Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
    public WebElement emailContactProfile;

    //    Поле ввода Email
    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Email\"]")
    public WebElement inputEmailContactProfile;

    //    Телефон
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
    public WebElement phoneContactProfile;

    //    Поле ввода Телефон
    @FindBy(css = "div:nth-child(2) > input[placeholder=\"Телефон\"]")
    public WebElement inputPhoneContactProfile;

    //    Instagram
    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
    public WebElement instagramContactProfile;

    //    Поле ввода Instagram
    @FindBy(css = "div:nth-child(3) > input[placeholder=\"Instagram\"]")
    public WebElement inputInstagramContactProfile;

    //    Vk
    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
    public WebElement vkContactProfile;

    //    Поле ввода Vk
    @FindBy(css = "div:nth-child(4) > input[placeholder=\"Vk\"]")
    public WebElement inputVkContactProfile;

    //    Whatsapp
    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
    public WebElement whatsappContactProfile;

    //    Поле ввода Whatsapp
    @FindBy(css = "div:nth-child(5) > input[placeholder=\"Whatsapp\"]")
    public WebElement inputWhatsappContactProfile;

    //    Viber
    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
    public WebElement viberContactProfile;

    //    Поле ввода Viber
    @FindBy(css = "div:nth-child(6) > input[placeholder=\"Viber\"]")
    public WebElement inputViberContactProfile;

    //    Facebook
    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
    public WebElement facebookContactProfile;

    //    Поле ввода Facebook
    @FindBy(css = "div:nth-child(7) > input[placeholder=\"Facebook\"]")
    public WebElement inputFacebookContactProfile;

    //    Другое
    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
    public WebElement otherContactProfile;

    //    Поле ввода Другое
    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Другое...\"]")
    public WebElement inputOtherContactProfile;

    //    Ссылка ИЗМЕНИТЬ Способы связи
    @FindBy(xpath = "//div[contains (text(), \"Способы связи\")]/following-sibling::div[contains (text(), \"Изменить\")]")
    public WebElement linkEditCommunicationMethodsProfile;

    //    Кнопка СОХРАНИТЬ Способы связи
    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveCommunicationMethodsProfile;

    //    Кнопка ОТМЕНИТЬ СОХРАНЕНИЕ Способов связи
    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelCommunicationMethodsProfile;







}



