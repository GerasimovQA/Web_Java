package createorganization;

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

import java.util.List;

public class CreateOrgnizationPage extends GlobalPage {

    public CreateOrgnizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
    //   Кнопка Организация с проверкой на текст "Организация"
    @FindBy(xpath = "//li/a/p[contains (text(), \"Организация\")]")
    public WebElement buttonOrganization;

    //   Кнопка Список подразделений с проверкой на текст "Список подразделений"
    @FindBy(xpath = "//Span[contains (text(), \"Список подразделений\")]")
    public WebElement buttonListOrganizations;

    //   Кнопка Добавить подразделение с проверкой на текст "Добавить подразделение"
    @FindBy(xpath = "//Span[contains (text(), \"Добавить подразделение\")]")
    public WebElement buttonAddOrganization;

    //   Список существующих организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> listOrganizations;


//    ----------------------------------------------------------------------------------------------------------------

    //ШАГ 1 - "Данные подразделения"
    //Поле ввода "Название" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Название\"]")
    public WebElement inputOrganizationName;

    //Поле ввода "Сокращенное название" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Сокращенное название\"]")
    public WebElement inputOrganizationNameAbbr;


    //   СПИСОК найденных руководителей организации
    @FindBy(xpath = "//tr/td[2]/div")
    public List<WebElement> ListFIODirecor;

    //   Ссылка "Очистить" список найденных руководителей организации
    @FindBy(xpath = "//div[contains (text(), \"Очистить\")]")
    public WebElement clearListDirecors;

    //   Ссылка "Изменить" установленного руководителя организации
    @FindBy(xpath = "//div[text()= \"Изменить\"]")
    public WebElement changeDirecor;

    //   Фотография Назначенного руководителя организации
    @FindBy(xpath = "//div[5]/div[1]/img")
    public WebElement photoAssignedDirecor;

    //   ФИО назначенного руководителя организации
    @FindBy(xpath = "//div[5]/div[2]")
    public WebElement FIOAssignedDirecor;





    //Поле ввода "Профиль" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Профиль\"]")
    public WebElement inputOrganizationProfile;

    //Кнопка "Добавить подразделение" Шаг1
    @FindBy(xpath = "//div[1]/button/span[contains (text(), \"Добавить подразделение\")]")
    public WebElement buttonAddOrganizationStep1;

//-------------------------------------------------------------------------------------------------------------------

    // Шаг 2 - Услуги
    //Услуга "Консультации врачей, отдельные манипуляции"
    @FindBy(xpath = "//span[contains (text(),\"Консультации врачей, отдельные манипуляции\")]")
    public WebElement checkServices1;

    //Услуга "Услуги оказываемые в отделениях"
    @FindBy(xpath = "//span[contains (text(),\"Услуги оказываемые в отделениях\")]")
    public WebElement checkServices2;

    //Услуга "Стоимость койко-дня в отделениях"
    @FindBy(xpath = "//span[contains (text(),\"Стоимость койко-дня в отделениях\")]")
    public WebElement checkServices3;

    //Чек-бокс Услуги "Койко-день в общей палате, 1650 руб"
    @FindBy(xpath = "//span[contains (text(),\"Койко-день в общей палате, 1650 руб\")]/../label")
    public WebElement checkServices4;

    //Кнопка "Добавить подразделение" Шаг2
    @FindBy(xpath = "//div[2]/button[3]/span[contains (text(), \"Добавить подразделение\")]")
    public WebElement buttonAddOrganizationStep2;

//-------------------------------------------------------------------------------------------------------------------

    // Шаг 3 - Дополнительная информация
    // Поле ввода "Описание" организации
    @FindBy(xpath = "//textarea[@placeholder=\"Описание\"]")
    public WebElement inputDescriptionOrgnization;

    // Поле ввода "Назначение телефона" №1 организации
    @FindBy(css = ".el-input__inner[placeholder=\"Назначение телефона\"]")
    public List<WebElement> inputAppointmentPhone;

    // Поле ввода "Телефон" №1 организации
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
    public List<WebElement> inputPhoneNumber;

    // Ссылка "Добавить" телефон организации
    @FindBy(xpath = "//div[@class=\"dep-create-form-phones\"]//span[contains (text(), \"Добавить\")]")
    public WebElement addPhoneNumber;

    // Ссылка "Удалить" телефон организации
    @FindBy(xpath = "//div[@class=\"dep-create-form-phones\"]//span[contains (text(), \"Удалить\")]")
    public WebElement deletePhoneNumber;


    // Поле ввода "Адрес" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Адрес\"]")
    public WebElement inputAdress;

    // Кнопка "Подтвердить Адрес" организации
    @FindBy(xpath = "//span[contains (text(), \"Подтвердить адрес\")]")
    public WebElement confirmAdress;

    // Карта с адресом организации
    @FindBy(css = ".ymap-container[style=\"width: 100%; height: 300px;\"]")
    public WebElement map;

    // Кнопка "Уточнить" адрес организации
    @FindBy(xpath = "//span[contains (text(), \"Уточнить\")]")
    public WebElement clarifyAdress;


    //Кнопка "Пропустить" Шаг3
    @FindBy(xpath = "//div[3]/button[2]/span[contains (text(), \"Пропустить\")]")
    public WebElement buttonSkipStep3;

    //Кнопка "Добавить подразделение" Шаг3
    @FindBy(xpath = "//div[3]/button[3]/span[contains (text(), \"Добавить подразделение\")]")
    public WebElement buttonAddOrganizationStep3;

//-------------------------------------------------------------------------------------------------------------------

    //Шаг 4 - Подтверждение данных
    //Проверяем название организации
    @FindBy(xpath = "//div[contains (text(), \"Название:\")]/following-sibling::div")
    public WebElement checkNameOrganization;

    //Проверяем сокращенное название организации
    @FindBy(xpath = "//div[contains (text(), \"Сокращенное название:\")]/following-sibling::div")
    public WebElement checkNameAbbrOrganization;

    //Проверяем руковдителя организации
    @FindBy(xpath = "//div[contains (text(), \"Руководитель:\")]/following-sibling::div")
    public WebElement checkDirectorOrganization;

    //Проверяем сокращенную головную организацию
    @FindBy(xpath = "//div[contains (text(), \"Входит в состав:\")]/following-sibling::div")
    public WebElement checkHeadOrganization;

    //Проверяем условия оказания помощи организацию
    @FindBy(xpath = "//div[contains (text(), \"Условия окозания помощи:\")]/following-sibling::div")
    public WebElement checkConditionsOrganization;

    //Проверяем условия оказания помощи организации
    @FindBy(xpath = "//div[contains (text(), \"Основной профиль:\")]/following-sibling::div")
    public WebElement checkProfileOrganization;

    //Проверяем описание организации
    @FindBy(xpath = "//div[contains (text(), \"Описание\")]/following-sibling::div[@class=\"dep-create-form__section" +
            "-paragraph\"]")
    public WebElement checkDescriptionOrganization;

    //Проверяем адрес организации
    @FindBy(xpath = "//div[contains (text(), \"Адрес\")]/following-sibling::div")
    public WebElement checkAdressOrganization;

    //Проверяем Email организации
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement checkEmailOrganization;

    //Проверяем Vk организации
    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
    public WebElement checkVkOrganization;

    //Проверяем Facebook организации
    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
    public WebElement checkFacebookOrganization;

    //Проверяем Instagram организации
    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
    public WebElement checkInstagramOrganization;

    //Проверяем Другое организации
    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
    public WebElement checkOtherOrganization;

    //Проверяем Назначение телефона организации
    @FindBy(xpath = "//div[contains (text(), \"Телефоны\")]/../div/div[@class=\"dep-create-form__section-row-label\"]")
    public List<WebElement> checkAppointmentPhone1;

    //Проверяем Номер телефона организации
    @FindBy(xpath = "//div[contains (text(), \"Телефоны\")]/../div/div[@class=\"dep-create-form__section-row-value\"]")
    public List<WebElement> checkPhoneNumber1;

    //   Кнопка Завершить с проверкой на текст "Завершить"
    @FindBy(xpath = "//Span[contains (text(), \"Завершить\")]")
    public WebElement buttonFinish;



//    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
//    //   Кнопка добавления сотрудника с проверкой на текст "Добавить сотрудника"
//    @FindBy(xpath = "//Span[contains (text(), \"Добавить сотрудника\")]")
//    public WebElement buttonAddUser;
//
//    //   Кнопка Назад
//    @FindBy(xpath = "//Span[contains (text(), \"Назад\")]")
//    public WebElement buttonBack;
//
//    //   Кнопка Поиск сотрудника
//    @FindBy(xpath = "//Span[contains (text(), \"Поиск сотрудника\")]")
//    public WebElement buttonSearchWorker;
//
//    //   Кнопка Поиск
//    @FindBy(xpath = "//Span[text() = \"Поиск\"]")
//    public WebElement buttonSearch;
//
//
////-----------------------------------------------------
////Профиль юзера
//
//    //   Метка "Суперадминистратор"
//    @FindBy(xpath = "//div/div[2]/div[1]/div[1]/div[1]/div[2]/span[contains (text(), \"Суперадминистратор\")]")
//    public WebElement labelSuperadministrator;
//
//    //   ФИО
//    @FindBy(xpath = ".//*[@class='profile-block profile-name hidden-xs']//*[@class='profile-name__name-text']")
//    public WebElement fio;
//
//    //  Телефон для входа в аккаунт
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
//    public WebElement profAccPhone;
//
//    // контакт Телефон
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
//    public WebElement profContPhone;
//
//    // Профиль Образование, регалии, достижения
//    @FindBy(xpath = "//div[contains (text(), \"Образование, регалии, достижения\")]/following-sibling::div")
//    public WebElement profEducation;
//
//    // Профиль Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
//    public WebElement profEmail;
//
//    // Профиль Whatsapp
//    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
//    public WebElement profWhatsapp;
//
//    // Профиль Viber
//    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
//    public WebElement profViber;
//
//    // Профиль Facebook
//    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
//    public WebElement profFacebook;
//
//    // Профиль Other
//    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
//    public WebElement profOther;
//
////-----------------------------------------------------
//
//    //ШАГ №1
//    //    Поле ввода логина с проверкой на текст "Логин"
//    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
//    public WebElement inputlogin;
//
//    //    Поле ввода Email с проверкой на текст "Email"
//    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
//    public WebElement inputEmail;
//
//    //    Поле ввода Телефон с проверкой на текст "Телефон"
//    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
//    public WebElement inputPhone;
//
//    //    Поле ввода Пароль с проверкой на текст "Пароль"
//    @FindBy(css = ".el-input__inner[placeholder=\"Пароль\"]")
//    public WebElement inputPassword;
//
//    //    Поле ввода Фамилия с проверкой на текст "Фамилия"
//    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
//    public WebElement inputSecondName;
//
//    //    Поле ввода Имя с проверкой на текст "Имя"
//    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
//    public WebElement inputFirstName;
//
//    //    Поле ввода Отчество с проверкой на текст "Отчество"
//    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
//    public WebElement inputMiddleName;
//
//    //    Ссылка автоматической генерации пароля, содержащей текст "Автоматически сгенерировать пароль"
//    @FindBy(xpath = "//div[contains (text(), \"Автоматически сгенерировать пароль\")]")
//    public WebElement generatePassword;
//
//    //   Иконка "Глаз" - показать пароль
//    @FindBy(css = ".create-user-form-show-password.fa.fa-eye-slash")
//    public WebElement showPassword;
//
//    //   Иконка "Глаз" - скрыть пароль
//    @FindBy(css = ".create-user-form-show-password.fa.fa-eye")
//    public WebElement notShowPassword;
//
//    //    Переключатель Суперадмин
//    @FindBy(css = ".el-switch__core")
//    public WebElement switchSuperadmin;
//
//    //    Чек-бокс "Отправить данные для входа, на email сотрудника"
//    @FindBy(css = ".el-checkbox__inner")
//    public WebElement checkboxInfoToEmail;
//
//
//    //   Проверка успешного перехода на ШАГ №2 фразой содержащей текст "Место работы"
//    @FindBy(xpath = "//div[contains (text(), \"Место работы\")]")
//    public WebElement successSecondStep;
//
//    //-------------------------------------------------------
//
//    //ШАГ 2 - Специальность и место работы
//    //Выпадающий список "Структура организации"
//    @FindBy(xpath = "//div[contains (text(),\"Структура организации\")]")
//    public WebElement selectStructureOrganization;
//
//    //Головная организация в выпадающем списке "Структура организации"
//    @FindBy(xpath = "//div[1]/div[2]/div[1]/div/label/span/span")
//    public WebElement selectMainOrganization;
//
//    //Поле ввода "Должность"
//    @FindBy(css = ".el-input__inner[placeholder=\"Должность\"]")
//    public WebElement inputWorkerPosition;
//
//    //Выпадающий список "Роль сотрудника"
//    @FindBy(css = ".el-input__inner[placeholder=\"Роль сотрудника\"]")
//    public WebElement selectWorkerRole;
//
//    //Пункт "Специалист" в выпадающем списке "Роль сотрудника"
//    @FindBy(xpath = "//span[contains (text(),\"Специалист\")]")
//    public WebElement selectWorkerRoleSpecialist;
//
//    //Выпадающий список "Специальность"
//    @FindBy(css = ".el-input__inner[placeholder=\"Специальность\"]")
//    public WebElement selectWorkerSpecialty;
//
//    //Пункт "Хирург" в  выпадающем списке "Специальность"
//    @FindBy(xpath = ".//span[contains (text(),\"Хирург\")]")
//    public WebElement selectWorkerSpecialtyMed;
//
//    //   Проверка успешного перехода на ШАГ №3 фразой содержащей текст "Оказываемые услуги "
//    @FindBy(xpath = "//div[contains (text(), \"Оказываемые услуги\")]")
//    public WebElement successSecondThree;
//
//    //-------------------------------------------------------
//
//    //ШАГ 3 - Оказываемые услуги
//    //Чек-бокс "Укажите оказываемые услуги "
//    @FindBy(xpath = "//span[contains (text(), \"Консультации\")]/../../div[1]/label/span/span")
//    public WebElement checkBoxServices;
//
//    //   Проверка успешного перехода на ШАГ №4 фразой содержащей текст "Добавить фото"
//    @FindBy(xpath = "//div[contains (text(), \"Добавить фото\")]")
//    public WebElement successSecondFour;
//
//    //-------------------------------------------------------
//
//    //ШАГ 4  - Данные специалиста
//    //Иконка для загрузки фото
//    @FindBy(xpath = ".//*[@id='avatarEditorCanvas']/following-sibling::input")
//    public WebElement uploadPhoto;
//
//    //Кнопка сохранения фото
//    @FindBy(xpath = "//span[text()=\"Сохранить\"]")
//    public WebElement buttonSavePhoto;
//
//    //Поле ввода "Образование, достижения, регалии "
//    @FindBy(css = ".el-textarea__inner[placeholder=\"Образование, достижения, регалии\"]")
//    public WebElement inputEducation;
//
//    //Поле ввода "Email"
//    @FindBy(xpath = "//input[@placeholder=\"Email \"]")
//    public WebElement inputEmailInfo;
//
//    //Поле ввода "Телефон"
//    @FindBy(xpath = "//div[3]/input[@placeholder=\"Телефон\"]")
//    public WebElement inputPhoneInfo;
//
//    //Поле ввода "Instagram"
//    @FindBy(xpath = "//input[@placeholder=\"Instagram\"]")
//    public WebElement inputInstagram;
//
//    //Поле ввода "VK"
//    @FindBy(xpath = "//input[@placeholder=\"Vk\"]")
//    public WebElement inputVk;
//
//    //Поле ввода "Whatsapp"
//    @FindBy(xpath = "//input[@placeholder=\"Whatsapp\"]")
//    public WebElement inputWhatsapp;
//
//    //Поле ввода "Viber"
//    @FindBy(xpath = "//input[@placeholder=\"Viber\"]")
//    public WebElement inputViber;
//
//    //Поле ввода "Facebook"
//    @FindBy(xpath = "//input[@placeholder=\"Facebook\"]")
//    public WebElement inputFacebook;
//
//    //Поле ввода "Другое"
//    @FindBy(xpath = "//input[@placeholder=\"Другое...\"]")
//    public WebElement inputOther;
//
//    //   Ссылка "Добавить"
//    @FindBy(xpath = ".user-form-another-contact__add>span")
//    public WebElement linkAdd;
//
//    //   Кнопка "Пропустить"
//    @FindBy(xpath = "//Span[contains (text(), \"Пропустить\")]")
//    public WebElement buttonSkip;
//
//    //-------------------------------------------------------
//
//    //ШАГ 5  - Подтверждение данных
//    //Сохраняемый логин
//    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
//    public WebElement saveLogin;
//
//    //Сохраняемый Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
//    public WebElement saveEmail;
//
//    //Сохраняемый Телефон
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
//    public WebElement savePhone;
//
//    //Сохраняемая Фамилия
//    @FindBy(xpath = "//div[contains (text(), \"Фамилия\")]/following-sibling::div")
//    public WebElement saveSecondName;
//
//    //Сохраняемое Имя
//    @FindBy(xpath = "//div[contains (text(), \"Имя\")]/following-sibling::div")
//    public WebElement saveFirstName;
//
//    //Сохраняемое Отчество
//    @FindBy(xpath = "//div[contains (text(), \"Отчество\")]/following-sibling::div")
//    public WebElement saveMiddleName;
//
//    //Проверка созданного юзера
//
////    //Сохраняемое место работы
////    @FindBy(xpath = "//div[contains (text(), \"Место:\")]/following-sibling::div")
////    public WebElement saveWorkSpace;
////
////    //Сохраняемая должность
////    @FindBy(xpath = "//div[contains (text(), \"Должность:\")]/following-sibling::div")
////    public WebElement saveWorkerPosition;
////
////    //Сохраняемая роль пользователя
////    @FindBy(xpath = "//div[contains (text(), \"Роль пользователя:\")]/following-sibling::div")
////    public WebElement saveWorkerRole;
////
////    //Сохраняемая специальность пользователя
////    @FindBy(xpath = "//div[contains (text(), \"Специальност\")]/following-sibling::ul/li")
////    public WebElement saveWorkerSpecialty;
//
//    // Сохраняемые Образование, достижения, регалии
//    @FindBy(xpath = "//div[text()=\"Образование, достижения, регалии\"]/following-sibling::div")
//    public WebElement saveEducation;
//
//    // Сохраняемый контакт Email
//    @FindBy(css = ".create-user-form-finish__contacts-row-data")
//    public WebElement saveEmailInfo;
//
//    // Сохраняемый контакт Телефон
//    @FindBy(xpath = "//div[3]/div[contains (text(), \"Телефон\")]/following-sibling::div")
//    public WebElement savePhoneInfo;
//
//    // Сохраняемый контакт Instagram
//    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
//    public WebElement saveInstagram;
//
//    // Сохраняемый контакт Vk
//    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
//    public WebElement saveVk;
//
//    // Сохраняемый контакт  Whatsapp
//    @FindBy(xpath = "//div[contains (text(), \" Whatsapp\")]/following-sibling::div")
//    public WebElement saveWhatsapp;
//
//    // Сохраняемый контакт  Viber
//    @FindBy(xpath = "//div[contains (text(), \" Viber\")]/following-sibling::div")
//    public WebElement saveViber;
//
//    // Сохраняемый контакт  Facebook
//    @FindBy(xpath = "//div[contains (text(), \" Facebook\")]/following-sibling::div")
//    public WebElement saveFacebook;
//
//    // Сохраняемый контакт  Другое
//    @FindBy(xpath = "//div[contains (text(), \" Другое\")]/following-sibling::div")
//    public WebElement saveOther;
//
//    //   Кнопка "Завершить"
//    @FindBy(xpath = "//Span[contains (text(), \"Завершить\")]")
//    public WebElement buttonFinish;
//
//    //   Кнопка "Завершить"
//    @FindBy(xpath = "//h2[contains (text(), \"Успешно\")]")
//    public WebElement successCreate;
//
//
//    public void createUserStepOneBase(String Login, String Email, String Phone, String Password, String SecondName,
// String FirstName, String MiddleName) {
//        waitE_ClickableAndClick(menuWorkers);
//        waitE_ClickableAndClick(menuCreateWorker);
//        waitE_ClickableAndSendKeys(inputlogin, Login);
//        waitE_ClickableAndSendKeys(inputEmail, Email);
//        waitE_ClickableAndSendKeys(inputPhone, Phone);
//        waitE_ClickableAndSendKeys(inputPassword, Password);
//        waitE_ClickableAndSendKeys(inputSecondName, SecondName);
//        waitE_ClickableAndSendKeys(inputFirstName, FirstName);
//        waitE_ClickableAndSendKeys(inputMiddleName, MiddleName);
//    }
//
//    public void createUserStepOneBaseWithoutMiddleName(String Login, String Email, String Phone, String Password,
// String SecondName, String FirstName) {
//        waitE_ClickableAndClick(menuWorkers);
//        waitE_ClickableAndClick(menuCreateWorker);
//        waitE_ClickableAndSendKeys(inputlogin, Login);
//        waitE_ClickableAndSendKeys(inputEmail, Email);
//        waitE_ClickableAndSendKeys(inputPhone, Phone);
//        waitE_ClickableAndSendKeys(inputPassword, Password);
//        waitE_ClickableAndSendKeys(inputSecondName, SecondName);
//        waitE_ClickableAndSendKeys(inputFirstName, FirstName);
//    }
//
//    public void stepTwo() {
//
////        Select selectWC = new Select(selectWorkspace);
//
////        Select selectWR = new Select(selectWorkspace);
////        Select selectWS = new Select(selectWorkspace);
////
////        selectWC.selectByVisibleText("Больница 1")
////        selectWR.selectByVisibleText("Специалист");
////        selectWS.selectByVisibleText("Лор");
//
//
//        waitE_ClickableAndClick(selectStructureOrganization);
//        waitE_ClickableAndClick(selectMainOrganization);
//        waitE_ClickableAndSendKeys(inputWorkerPosition, "Докторелло");
//        waitE_ClickableAndClick(selectWorkerRole);
//        waitE_ClickableAndClick(selectWorkerRoleSpecialist);
//        waitE_ClickableAndClick(selectWorkerSpecialty);
//        waitE_ClickableAndClick(selectWorkerSpecialtyMed);
//        waitE_ClickableAndClick(buttonAddUser);
//        waitE_visibilityOf(successSecondThree);
//    }
//
//    public void stepThree() {
//        waitE_ClickableAndClick(checkBoxServices);
//        waitE_ClickableAndClick(buttonAddUser);
//    }
//
//    public void createUserStepFourBase(String Education, String Email, String Phone, String Instagram, String Vk,
// String Whatapp, String Viber, String Facebook, String Other, String Photo) {
////        WebElement upload = uploadPhoto;
////        ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", uploadPhoto);
//        uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
//        waitE_ClickableAndClick(buttonSavePhoto);
//        waitE_ClickableAndSendKeys(inputEducation, Education);
//        waitE_ClickableAndSendKeys(inputEmailInfo, Email);
//        waitE_ClickableAndSendKeys(inputPhoneInfo, Phone);
//        waitE_ClickableAndSendKeys(inputInstagram, Instagram);
//        waitE_ClickableAndSendKeys(inputVk, Vk);
//        waitE_ClickableAndSendKeys(inputWhatsapp, Whatapp);
//        waitE_ClickableAndSendKeys(inputViber, Viber);
//        waitE_ClickableAndSendKeys(inputFacebook, Facebook);
//        waitE_ClickableAndSendKeys(inputOther, Other);
//        waitE_ClickableAndClick(buttonAddUser);
//    }
//
//    public void createUserStepFive(String Login, String Email, String Phone, String Password, String SecondName,
// String FirstName, String MiddleName, String Education, String EmailInfo, String PhoneInfo, String Instagram,
// String Vk, String Whatsapp, String Viber, String Facebook, String Other) {
//        waitE_visibilityOfAndGettext(savePhone);
//        String SavePhone = savePhone.getText();
//        String SavePhoneInfo = savePhoneInfo.getText();
//
//        Assert.assertTrue(Login.equals(saveLogin.getText()));
//        System.out.println(Login + " = " + saveLogin.getText());
//
//        Assert.assertTrue(Email.equals(saveEmail.getText()));
//        System.out.println(Email + " = " + saveEmail.getText());
//
//        Assert.assertTrue(SavePhone.equals("+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " +
// Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt
// (8) + Phone.charAt(9)));
//        System.out.println(SavePhone + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") "
// + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone
// .charAt(8) + Phone.charAt(9));
//
//
//        Assert.assertTrue(SecondName.equals(saveSecondName.getText()));
//        System.out.println(SecondName + " = " + saveSecondName.getText());
//
//        Assert.assertTrue(FirstName.equals(saveFirstName.getText()));
//        System.out.println(FirstName + " = " + saveFirstName.getText());
//
//        Assert.assertTrue(MiddleName.equals(saveMiddleName.getText()));
//        System.out.println(MiddleName + " = " + saveMiddleName.getText());
//
//        Assert.assertTrue(Education.equals(saveEducation.getText()));
//        System.out.println(Education + " = " + saveEducation.getText());
//
//
//        Assert.assertTrue(EmailInfo.equals(saveEmailInfo.getText()));
//        System.out.println(EmailInfo + " = " + saveEmailInfo.getText());
//
//        Assert.assertTrue(SavePhoneInfo.equals("+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo
// .charAt(2) + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) +
// PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9)));
//        System.out.println(SavePhoneInfo + " = " + "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo
// .charAt(2) + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) +
// PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));
//
//        Assert.assertTrue(Instagram.equals(saveInstagram.getText()));
//        System.out.println(Instagram + " = " + saveInstagram.getText());
//
//        Assert.assertTrue(Vk.equals(saveVk.getText()));
//        System.out.println(Vk + " = " + saveVk.getText());
//
//        Assert.assertTrue(Whatsapp.equals(saveWhatsapp.getText()));
//        System.out.println(Whatsapp + " = " + saveWhatsapp.getText());
//
//        Assert.assertTrue(Viber.equals(saveViber.getText()));
//        System.out.println(Viber + " = " + saveViber.getText());
//
//        Assert.assertTrue(Facebook.equals(saveFacebook.getText()));
//        System.out.println(Facebook + " = " + saveFacebook.getText());
//
//        Assert.assertTrue(Other.equals(saveOther.getText()));
//        System.out.println(Other + " = " + saveOther.getText());
//
//        waitE_ClickableAndClick(buttonFinish);
//        waitE_visibilityOf(successCreate);
//    }
//
//    public void createUserStepSix(String Login, String Email, String Phone, String Password, String SecondName,
// String FirstName, String MiddleName, String Education, String EmailInfo, String PhoneInfo, String Instagram,
// String Vk, String Whatsapp, String Viber, String Facebook, String Other) {
//        waitE_ClickableAndClick(buttonUserList);
//        waitE_visibilityOf(listSecondName);
////        sleep(2000);
////        scrollWithOffset(sumWorker, 0, -50);
////        waitE_ClickableAndClick(sumWorker);
////        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sumWorker);
////        moveMouse(sumWorker);
////        waitE_visibilityOf(workers50);
////        moveMouse(workers50);
////        waitE_ClickableAndClick(workers50);
//        WebElement findToSecondName = new WebDriverWait(driver, 10).until(ExpectedConditions
// .visibilityOfElementLocated(By.xpath("//tr/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
//        scrollWithOffset(findToSecondName, 0, 30);
//        waitE_ClickableAndClick(findToSecondName);
//        waitE_visibilityOfAndGettext(savePhone);
//        String AccPhone = profAccPhone.getText();
//        String ContPhone = profContPhone.getText();
//
//        System.out.println(Login + " = " + saveLogin.getText());
//        Assert.assertTrue(Login.equals(saveLogin.getText()));
//
//        Assert.assertTrue(Email.equals(saveEmail.getText()));
//        System.out.println(Email + " = " + saveEmail.getText());
//
//        Assert.assertTrue(AccPhone.equals("+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " +
// Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt
// (8) + Phone.charAt(9)));
//        System.out.println(AccPhone + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " +
// Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt
// (8) + Phone.charAt(9));
//
//        String Fio = SecondName + " " + FirstName + " " + MiddleName;
//        System.out.println(Fio + " = " + fio.getText());
//       if (MiddleName.equals("")){
//           Assert.assertTrue(Fio.equals(fio.getText() + " "));
//       }
//       else {
//        Assert.assertTrue(Fio.equals(fio.getText()));}
//
//
//        Assert.assertTrue(Education.equals(profEducation.getText()));
//        System.out.println(Education + " = " + profEducation.getText());
//
//        Assert.assertTrue(EmailInfo.equals(profEmail.getText()));
//        System.out.println(EmailInfo + " = " + profEmail.getText());
//
//        Assert.assertTrue(ContPhone.equals("+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2)
// + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) + PhoneInfo
// .charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9)));
//        System.out.println(ContPhone + " = " + "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo
// .charAt(2) + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) +
// PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));
//
//        Assert.assertTrue(Instagram.equals(saveInstagram.getText()));
//        System.out.println(Instagram + " = " + saveInstagram.getText());
//
//        Assert.assertTrue(Vk.equals(saveVk.getText()));
//        System.out.println(Vk + " = " + saveVk.getText());
//
//        Assert.assertTrue(Whatsapp.equals(profWhatsapp.getText()));
//        System.out.println(Whatsapp + " = " + profWhatsapp.getText());
//
//        Assert.assertTrue(Viber.equals(profViber.getText()));
//        System.out.println(Viber + " = " + profViber.getText());
//
//        Assert.assertTrue(Facebook.equals(profFacebook.getText()));
//        System.out.println(Facebook + " = " + profFacebook.getText());
//
//        Assert.assertTrue(Other.equals(profOther.getText()));
//        System.out.println(Other + " = " + profOther.getText());
//    }
}



