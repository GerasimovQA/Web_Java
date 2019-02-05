package Global;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.io.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

public class GlobalPage {

    public ArrayList<String> SelectedServices = new ArrayList<>();
    String CurrentURL = "";

    int i = -1;
    int u = -1;
    private static String resourcesImagesDir = ConfigProperties.getTestProperty("Screen");
    public static String expectedDir = resourcesImagesDir + ConfigProperties.getTestProperty("expected");
    public static String actualDir = resourcesImagesDir + ConfigProperties.getTestProperty("actual");
    public static String diffDir = resourcesImagesDir + ConfigProperties.getTestProperty("diff");
    public static String failedTests = resourcesImagesDir + ConfigProperties.getTestProperty("failedtests");
    public static String failedTestsAutorization = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_autorization");
    public static String failedTestsCreateOrgnization = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_createorgnization");
    public static String failedTestsCreateUser = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_createuser");
    public static String failedTestsEditOrganization = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editOrganization");
    public static String failedTestsEditPassword = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editPassword");
    public static String failedTestsEditUser = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editUser");
    public static String failedTestsHistoryChanges = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_historyChanges");
    public static String failedTestsIntegration = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_integration");
    public static String failedTestsListOrganization = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_listOrganization");
    public static String failedTestsSystem = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_system");
    public static String failedTestsGlobalSearch = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_globalsearch");
    public static String failedTestsListUser = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_listUser");
    public static String failedTestsCreateSpecialty = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_createspecialty");
    public static String failedTestsEditSpecialty = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editspecialty");
    public static String failedTestsCreateService = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_createservice");
    public static String failedTestsEditService = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editservice");
    public static String failedTestsCreateCabinet = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_createCabinet");
    public static String failedTestsEditCabinet = resourcesImagesDir + ConfigProperties.getTestProperty(
            "failedtests_editCabinet");

    public static String ScreenshotAshot = "Ashot";

    public static String LoginAUT_SA = "ioanner";
    public static String PasswordAUT_SA = "12345678";
    public static String LoginAUT_A = "Akulov";
    public static String PasswordAUT_A = "Akulov";
    public static String LoginAUT_S = "Akulev";
    public static String PasswordAUT_S = "Akulev";

//    public static String LoginAUT_SA = "gerasimov";
//    public static String PasswordAUT_SA = "gerasimov";
//    public static String LoginAUT_A = "kovalev";
//    public static String PasswordAUT_A = "kovalev";
//    public static String LoginAUT_S = "kovalchuk";
//    public static String PasswordAUT_S = "kovalchuk";


    // Body
    @FindBy(xpath = "//html/body")
    public WebElement body;

    // Preload
    @FindBy(xpath = "//div[@class=\"loader-bar\"]")
    public WebElement preload;

    // Logo all pages
    @FindBy(xpath = "//a[@class=\"sidebar-logo-container\"]//*[text()=\"Банк Здоровья\"]")
    public WebElement logoAll;

    // Logo page Login
    @FindBy(xpath = "//div[@class=\"login-page-logo\"]//*[text()=\"Банк Здоровья\"]")
    public WebElement logoLogin;

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

    //    Ссылка изменения пароля
    @FindBy(xpath = "//div[contains (text(), \"Пароль\")]/following-sibling::div/span[contains (text(), \"Изменить\")]")
    public WebElement linkEditPasswordProfile;

    //    Поле ввода ПАРОЛЬ
    @FindBy(css = ".el-input__inner[placeholder=\"Новый пароль\"]")
    public WebElement inputNewPassword;

    //    Поле ввода ПОВТОРИТЕ ПАРОЛЬ
    @FindBy(css = ".el-input__inner[placeholder=\"Повторите пароль\"]")
    public WebElement inputRepeatNewPassword;

    //    Поле ввода Текущий ПАРОЛЬ
    @FindBy(css = ".el-input__inner[placeholder=\"Текущий пароль\"]")
    public WebElement inputCurrentPassword;

    //   Иконка "Глаз" - скрыть пароль
    @FindBy(css = ".Password__toggle--show-password.fa.fa-eye-slash")
    public WebElement notShowPassword;

    //    Кнопка СОХРАНИТЬ ПАРОЛЬ
    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSavePassword;

    //    Кнопка ОТМЕНА ПАРОЛЯ
    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelPassword;


    //ГЛАВНАЯ СТРАНИЦА
    //Пункт меню Сотрудники
    @FindBy(xpath = "//p[contains (text(), \"Сотрудники\")]")
    public WebElement menuWorkers;

    //Подпункт меню Создать сотрудника
    @FindBy(xpath = "//span[contains (text(), \"Создать сотрудника\")]")
    public WebElement menuCreateWorker;

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


    //   Кнопка список сотрудников с проверкой на текст "Список сотрудников"
    @FindBy(xpath = "//Span[text()= \"Список сотрудников\"]")
    public WebElement buttonUserList;

    //   Кнопка загрузить еще с проверкой на текст "Загрузить еще"
    @FindBy(xpath = "//span[contains (text(), \"Загрузить еще\")]")
    public WebElement buttonLoadMore;

    //   Выпадающий список "Количество сотрудников на странице"
    @FindBy(css = ".el-input__inner[placeholder=\"Выбрать\"]")
    public WebElement sumWorker;

    //   Ссылка "Назад" в хлебных крошках
    @FindBy(xpath = "//a[contains(text(),\"Назад\")]")
    public WebElement linkBack;


//    //  Блок с вариантами количества показанных юзеров
//    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/ul[@class=\"el-scrollbar__view el-select-dropdown__list\"]")
//    public WebElement listSumWorker;


    //   Выпадающий пункт "100 на странице" в списке "Количество сотрудников на странице"
    @FindBy(css = "body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap.el-scrollbar__wrap > ul > li:nth-child(4) > span")
    public WebElement workers100;

    //   Список фамилий сотрудников
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr/td[4]/div[contains(text(),\"Акулин\")]")
    public WebElement waitListUsers;

    //   Выпадающий список Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//div[@class=\"user-info navbar-right\"]")
    public WebElement listActionProfile;

    //  Пункт ВЫЙТИ  в выпадающем списке Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//ul[@class=\"dropdown-menu\"]//span[contains (text(), \"Выйти\")]")
    public WebElement listActionProfileExit;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(xpath = "//h2[contains (text(), \"Профиль обновлен\")]")
    public WebElement helperProfileChange;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(css = ".el-notification__closeBtn.el-icon-close")
    public WebElement helperProfileChangeClose;

    //    Поле ввода EMAIL
    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
    public WebElement inputEmail;

    //    Поле ввода ТЕЛЕФОН
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
    public WebElement inputPhone;

    //Глобальные объекты
    //    Кнопка СОХРАНИТЬ все кроме пароля и статуса
    @FindBy(xpath = "//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveProfile;

    //    Кнопка СОХРАНИТЬ все кроме пароля и статуса
    @FindBy(xpath = "//span[contains (text(), \"Сохранить изменения\")]")
    public WebElement buttonSaveChangesProfile;

    //    Кнопка ОТМЕНА всего кроме пароля и статуса
    @FindBy(xpath = "//button/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelProfile;

    //    Вкладка "Место работы и специальность"
    @FindBy(xpath = "//div[contains (text(), \"Место работы и специальность\")]")
    public WebElement specialistWorkpaleces;

    //    Вкладка "Услуги"
    @FindBy(xpath = "//div[contains (text(), \"Услуги\")]")
    public WebElement specialistServices;

    //    Вкладка "Данные специалиста"
    @FindBy(xpath = "//div[contains (text(), \"Данные специалиста\")]")
    public WebElement specialistData;

    //    Логин
    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
    public WebElement loginProfile;

    //    Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement emailProfile;

    //    Phone
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement phoneProfile;
    //-----------------------------------------------Организации------------------------------------------------------------
    //Пункт меню Организации
    @FindBy(xpath = "//p[contains (text(), \"Организация\")]")
    public WebElement menuOrganizations;

    //   Кнопка список организаций с проверкой на текст "Список сотрудников"
    @FindBy(xpath = "//Span[text()= \"Список подразделений\"]")
    public WebElement buttonOrganizationsList;

    //   Список Названий организаци
    @FindBy(xpath = "//*[@id=\"pane-list\"]//div[1][@class=\"dep-tree__cell\"]")
    public WebElement listNameOrganizations;

    // Кнопка "Ред. подразделение"
    @FindBy(xpath = "//span[contains (text(),\"Ред. подразделение\")]")
    public WebElement buttonEditOrganization;

    //   Кнопка "Поиск сотрудника"
    @FindBy(xpath = "//button/span[text()= \"Поиск сотрудника\"]")
    public WebElement buttonEmployeeSearch;

    //Поле ввода "Фамилия" в поиске руководителя организации
    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
    public WebElement inputSecondNameDirectorOrganization;

    //Поле ввода "Имя" в поиске руководителя организации
    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
    public WebElement inputFirstNameDirectorOrganization;

    //Поле ввода "Отчество" в поиске руководителя организации
    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
    public WebElement inputMiddleNameDirectorOrganization;

    //   Кнопка "Поиск" в поиске руководителя организации
    @FindBy(xpath = "//button/span[text()= \"Поиск сотрудника\"]")
    public WebElement buttonSearchDirector;

    //   Кнопка "Очистить" в поиске руководителя организации
    @FindBy(xpath = "//Span[contains (text(), \"Очистить\")]")
    public WebElement buttonClear;

    //   ФИО найденных руководителей организации
    @FindBy(xpath = "//span[@class=\"chief__name\"]")
    public List<WebElement> listFIODirecor;

    //   Фотографии руководителей организации
    @FindBy(xpath = "//div[@class=\"chief el-row\"]//div[@class=\"userpic userpic--ic\"]")
    public List<WebElement> listPhotoDirecor;

    //   Кнопка "Выбрать руководителя"
    @FindBy(xpath = "//button/span[text()= \"Выбрать руководителя\"]")
    public List<WebElement> buttonSelectDirecor;

    //   Кнопка "Выбрать руководителя" Акулина
    @FindBy(xpath = "//span[contains (text(),\"Акулин\")]/../../..//button/span[text()= \"Выбрать руководителя\"]")
    public WebElement buttonSelectDirecorAkulin;

    //    Поле ввода "Структура организации"
    @FindBy(xpath = "//div[contains(text(), \"Структура организации\")]/following-sibling::i")
    public WebElement inputStructureOrganization;

    //   Буллет головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]/../label/span")
    public WebElement bulletHeadOrganization;

    //   Наименование головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский
    // Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]")
    public WebElement NameHeadOrganization;

    //   Наименование "Клиника имени Степашина"
    @FindBy(xpath = "//span[contains (text(), \"Клиника имени Степашина\")]")
    public WebElement NameStepashin;

    //   Наименование "1 подразделение Степашина"
    @FindBy(xpath = "//span[contains (text(), \"1 подразделение Степашина\")]")
    public WebElement NameStepashin1;

    //   Название "2 подразделение Степашина"
    @FindBy(xpath = "//span[contains (text(), \"2 подразделение Степашина\")]")
    public WebElement nameStepashin2;

    //   Буллет "2 подразделение Степашина"
    @FindBy(xpath = "//span[contains (text(), \"2 подразделение Степашина\")]/../label")
    public WebElement bulletStepashin2;

    //   Буллет "3 подразделение Степашина"
    @FindBy(xpath = "//span[contains (text(), \"3 подразделение Степашина\")]/../label")
    public WebElement bulletStepashin3;

    //   Наименование "Кардиоцентр №1"
    @FindBy(xpath = "//span[contains (text(), \"Кардиоцентр №1\")]")
    public WebElement nameKardio1;

    //   Наименование "Кардиоцентр №2"
    @FindBy(xpath = "//span[contains (text(), \"Кардиоцентр №2\")]")
    public WebElement nameKardio2;

    //   Наименование "Кардиоцентр №3"
    @FindBy(xpath = "//span[contains (text(), \"Кардиоцентр №3\")]")
    public WebElement nameKardio3;

    //   Название "Кардиоцентр №4"
    @FindBy(xpath = "//span[contains (text(), \"Кардиоцентр №4\")]")
    public WebElement nameKardio4;

    //   Буллет "Кардиоцентр №4
    @FindBy(xpath = "//span[contains (text(), \"Кардиоцентр №4\")]/../label")
    public WebElement bulletKardio4;


    //   Название "Кардиоцентр №1" в списке организаций
    @FindBy(xpath = "//div[1][@class=\"dep-tree__cell\"][contains (text(), \"Кардиоцентр №1\")]")
    public WebElement openKardio1;

    //   Название "Кардиоцентр №2" в списке организаций
    @FindBy(xpath = "//div[1][@class=\"dep-tree__cell\"][contains (text(), \"Кардиоцентр №2\")]")
    public WebElement openKardio2;

    //   Название "Кардиоцентр №3" в списке организаций
    @FindBy(xpath = "//div[1][@class=\"dep-tree__cell\"][contains (text(), \"Кардиоцентр №3\")]")
    public WebElement openKardio3;

    //   Название "Кардиоцентр №4" в списке организаций
    @FindBy(xpath = "//div[1][@class=\"dep-tree__cell\"][contains (text(), \"Кардиоцентр №4\")]")
    public WebElement openKardio4;

    //   Чек-бокс "Создать организацию верхнего уровня"
    @FindBy(xpath = "//span[contains(text(),\"Организация верхнего уровня\")]/preceding-sibling::span")
    public WebElement chekboxCreateHeadOrg;

    //Выпадающий список "Условия оказания помощи" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Условия\"]")
    public WebElement selectOrganizationConditions;

    //  Поле ввода "Сайт" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Сайт\"]")
    public WebElement inputSite;

    //  Поле ввода "Vk" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Vk\"]")
    public WebElement inputVk;

    //  Поле ввода "Facebook" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Facebook\"]")
    public WebElement inputFacebook;

    //  Поле ввода "Instagram" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Instagram\"]")
    public WebElement inputInstagram;

    //  Поле ввода "Другое" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Другое\"]")
    public WebElement inputOther;

    //  Поле ввода "Контакт"  дополнительного контакта организации
    @FindBy(css = ".el-input__inner[placeholder=\"Контакт\"]")
    public WebElement inputOtherValue;

    //  Поле ввода "Название"  дополнительного контакта организации
    @FindBy(css = ".el-input__inner[placeholder=\"Название\"]")
    public WebElement inputOtherName;

    //  Поле ввода "Код подразделения" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Код подразделения\"]")
    public WebElement inputCode;

    //  Поле ввода "Реестровый номер ОМС" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Реестровый номер ОМС\"]")
    public WebElement inputRegistryNumber;

    //  Поле ввода "Идентификатор ПУМП" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Идентификатор ПУМП\"]")
    public WebElement inputIdentifierPUMP;

    //  Поле ввода "Идентификатор ОМС" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Идентификатор ОМС\"]")
    public WebElement inputIdentifierOMS;

    //------------------------------------------------Раздел "Место работы"-----------------------------------------
    //Выбранные должности
    @FindBy(xpath = "//div[@class=\"el-select create-user-form-workplaces__posts " +
            "showLabel\"]//span[@class=\"el-select__tags-text\"]")
    public List<WebElement> selectedWorkerPosition;

    //Cписок найденных Должностей
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item hover\"]")
    public List<WebElement> listFindWorkerPosition;

    //Выпадающий список "Роль сотрудника"
    @FindBy(xpath = "//input[@placeholder=\"Роль сотрудника *\"]")
    public WebElement selectWorkerRole;

    //Cписок найденных Ролей
    @FindBy(xpath = "//ul[@class=\"el-scrollbar__view el-select-dropdown__list\"]//span")
    public List<WebElement> listFindWorkerRole;

    //Пункт "Специалист" в выпадающем списке "Роль сотрудника"
    @FindBy(xpath = "//div[@class=\"el-scrollbar\"]//span[contains (text(),\"Специалист\")]")
    public WebElement selectWorkerRoleSpecialist;

    //Пункт "Администратор" в выпадающем списке "Роль сотрудника"
    @FindBy(xpath = "//div[@class=\"el-scrollbar\"]//span[contains (text(),\"Администратор\")]")
    public WebElement selectWorkerRoleAdmin;

    //Список выбранных ролей
    @FindBy(xpath = "//div[@class=\"el-select create-user-form-workplaces__roles " +
            "showLabel\"]//*[@class=\"el-select__tags-text\"]")
    public List<WebElement> selectedWorkerRole;

    //Чек-бокс "Ведет амбулаторный приём"
    @FindBy(xpath = "//span[contains (text(),\"Ведет амбулаторный приём\")]/../span/span")
    public WebElement checkBoxAmbulance;

    //Список классов Чек-боксов "Ведет амбулаторный приём"
    @FindBy(xpath = "//span[contains (text(),\"Ведет амбулаторный приём\")]/../span")
    public List<WebElement> listCheckBoxAmbulanceClass;

    //Список выбранных Чек-боксов "Ведет амбулаторный приём"
    @FindBy(xpath = "//span[contains (text(), \"Ведет амбулаторный приём\")]" +
            "/../span[@class=\"el-checkbox__input is-checked\"]/span[@class=\"el-checkbox__inner\"]")
    public List<WebElement> listSelectedCheckBoxAmbulance;

    //Список НЕвыбранных Чек-боксов "Ведет амбулаторный приём"
    @FindBy(xpath = "//span[contains (text(), \"Ведет амбулаторный приём\")]" +
            "/../span[@class=\"el-checkbox__input\"]/span[@class=\"el-checkbox__inner\"]")
    public List<WebElement> listSelectedCheckBoxNotAmbulance;

    //Ссылка "Добавить место работы"
    @FindBy(xpath = "//div[contains (text(),\"Добавить место работы\")]")
    public WebElement linkAddWorplace;

    //Ссылка "Удалить место работы"
    @FindBy(xpath = "//span[contains (text(),\"Удалить место работы\")]")
    public WebElement linkDeleteWorkplace;

    //Триггер вызова специальностей
    @FindBy(xpath = "//div[contains (text(),\"Специальности\")]/../i[@class=\"tree-select__caret el-icon-arrow-up\"]")
    public WebElement triggerWorkerSpeciality;

    //Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(),\"Место работы\")]/../div[@class=\"create-user-form-workplaces__tree el-input el-input--prefix\"]//i[@class=\"tree-select__caret el-icon-arrow-up\"]")
    public WebElement selectStructureOrganization;

    // Поле ввода "Поиск организации"
    @FindBy(xpath = "(//input[@placeholder=\"Поиск\"])[last()]")
    public WebElement inputSearch;

    //Чек-бокс головной организации в выпадающем списке "Структура организации"
    @FindBy(xpath = "(//*[@class=\"el-popover el-popper\"]//span[@class=\"el-checkbox__input\"]/span[@class=\"el-checkbox__inner\"])[1]")
    public WebElement selectMainOrganization;

    //Название головной организации в выпадающем списке "Структура организации"
    @FindBy(xpath = "//div[@aria-hidden=\"false\"]//div[@style=\"padding-left: 0px;\"]")
    public List<WebElement> selectNameMainOrganization;

    //Буллет организации второго уровня в выпадающем списке "Структура организации"
    @FindBy(xpath = "//div[@aria-hidden=\"false\"]//div[@style=\"padding-left: 16px;\"] /../div/label")
    public List<WebElement> selectBulletOrganizationTwoLevel;

    //Выделенный Буллет организации в выпадающем списке "Структура организации"
    @FindBy(xpath = "//div[@class=\"el-tree-node is-focusable is-checked\"]//label")
    public WebElement selectBulletOrganization;


    //Название выбранной организации в поле ввода "Структура организации"
    @FindBy(xpath = "//div[@class=\"tree-select\"]//*[@class=\"tree-select__checked-org\"]")
    public List<WebElement> selectedOrganization;

    //Выпадающий список "Специальности"
    @FindBy(xpath = "//div[contains (text(),\"Спец\")]/following-sibling::div//div[contains (text()," +
            "\"Специальности\")]")
    public WebElement selectWorkerSpecialty;

    //Список разделов "Специальностей"
    @FindBy(xpath = "//span[contains (text(),\"специальности\")]")
    public List<WebElement> selectSectionSpecialty;

    //Выавдающий список "Медицинские специальности" в  выпадающем списке "Специальноси"
    @FindBy(xpath = "//span[contains (text(),\"Медицинские специальности\")]/..")
    public WebElement listWorkerSpecialtyMed;

    //Выавдающий список "НЕмедицинские специальности" в  выпадающем списке "Специальноси"
    @FindBy(xpath = "//span[contains (text(),\"Немедицинские специальности\")]/..")
    public WebElement listWorkerSpecialtyNotMed;

    //Чек-боксы в Спиcке Специальностей
    @FindBy(xpath = "//div[@style=\"padding-left: 1px;\"]/span[@class=\"el-tree-node__label\"]/../label/span")
    public List<WebElement> listworkerSpecialty;

    //Чек-боксы специальностей в разделе Медицинские специальности
    @FindBy(xpath = "//span[contains (text(),\"Медицинские специальности\")]/../following-sibling::div//div[@style=\"padding-left: 1px;\"]/span[@class=\"el-tree-node__label\"]/../label/span/span")
    public List<WebElement> listMedSpecialty;

    //Чек-боксы специальностей в разделе НЕмедицинские специальности
    @FindBy(xpath = "//span[contains (text(),\"Немедицинские специальности\")]/../following-sibling::div//div[@style=\"padding-left: 1px;\"]/span[@class=\"el-tree-node__label\"]/../label/span/span")
    public List<WebElement> listNotMedSpecialty;

    //Специальность "Лор" в  выпадающем списке "Медицинские специальности"
    @FindBy(xpath = "//span[contains (text(),\"Лор\")]/../label/span")
    public WebElement workerSpecialtyLor;

    //Специальность "Стоматолог" в  выпадающем списке "Медицинские специальности"
    @FindBy(xpath = "//span[contains (text(),\"Стоматолог\")]/../label/span")
    public WebElement workerSpecialtyDentist;

    //Специальность "Кардиолог" в  выпадающем списке "Медицинские специальности"
    @FindBy(xpath = "//span[contains (text(),\"Кардиолог\")]/../label/span")
    public WebElement workerSpecialtyCardiologist;

    //Список выбранных чек-боксов в выпадающем списке "Специальноси"
    @FindBy(xpath = "//span[contains (text(),\"Немедицинские специальности\")]/../../..//div[@aria-checked=\"true\"]")
    public List<WebElement> listSelectedCheckBoxdWorkerSpecialty;

    //Выбранные специальности в выпадающем списке "Специальноси"
    @FindBy(xpath = "//div[@class=\"tree-select tree-select--muliselect\"]//div[@class=\"tree-select__checked-org\"]")
    public List<WebElement> selectedWorkerSpecialty;

    //Сохраненные специальности при редактировании
    @FindBy(xpath = "//div[contains (text(), \"Специальност\")]/following-sibling::div[1]//span")
    public List<WebElement> savedWorkerSpecialty;

    //Поле ввода "Укажите Должность"
    @FindBy(xpath = "//label[text()='Структура организации']/../../..//input[@class=\"el-select__input\"]")
    public List<WebElement> inputWorkerPosition;

    //Кнопка "Добавить" место работы
    @FindBy(xpath = "//div[contains(text(),\"Добавить место работы\")]/following-sibling::div/button/Span[contains " +
            "(text(), \"Добавить\")]")
    public WebElement buttonAddWorkplace;

    //Кнопка "Сохранить" место работы
    @FindBy(xpath = "//div[@class=\"edit-workplace__buttons-container\"]//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveWorkplace;

    //Ссылка "Изменить" специальности
    @FindBy(xpath = "//div[contains(text(),\"Специальности\")]/following-sibling::div//Span[contains (text(), " +
            "\"Изменить\")]")
    public WebElement linkChangeSpeciality;

    //------------------------------------------------Раздел "УСЛУГИ"-------------------------------------------------
    //Раздел услуг 1 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 24px;\"]//span[@class=\"services-tree__node-name\"]")
    public List<WebElement> textServices1;

    //Чек-бокс раздела услуг 1 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 24px;\"]//label/span")
    public List<WebElement> checkServices1;

    //Раздел услуг 2 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 48px;\"]//span[@class=\"services-tree__node-name\"]")
    public List<WebElement> textServices2;

    //Чек-бокс раздела услуг 2уровня
    @FindBy(xpath = "//div[@class=\"info-block__content\"]//div[@style=\"padding-left: 48px;\"]/label/span")
    public List<WebElement> checkServices2;

    //Названия Услуг 3 уровня
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../..//div[@class=\"services-tree__cell\"]/span")
    public List<WebElement> textServices3;

    //Строки Услуги 3 уровня
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../../div[@class=\"services-tree__row name vendor_code price\"]")
    public List<WebElement> stringTreeServices3;

    //Чек-боксы услуг 3 уровня
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../..//span[@class=\"el-checkbox__inner\"]/..")
    public List<WebElement> checkServices3;

    //Артикулы услуг 3 уровня
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../..//div[@class=\"services-tree__cell\"][2]")
    public List<WebElement> vendorServices3;

    //Стоимость услуг 3 уровня
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../..//div[@class=\"services-tree__cell\"][3]")
    public List<WebElement> costServices3;

    //Список чек-боксов выделенных услуг 3 уровня в дереве услуг
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../../../div[@style=\"padding-left: 72px;\"]//label/span[@class=\"el-checkbox__input is-checked\"]")
    public List<WebElement> listSelectedCheckBoxInTree;

    //Список выделенных услуг 3 уровня в дереве услуг
    @FindBy(xpath = "//i[@class=\"fa fa-rub\"]/../../../../..//label/span[@class=\"el-checkbox__input is-checked\"]/../../..//div[@class=\"el-tree-node__content\"]")
    public List<WebElement> listStringSelectedServicesInTree;

    //Список строк выбранных услуг ЮЗЕРА
    @FindBy(xpath = "//div[@class=\"select-list__row el-row\"]")
    public List<WebElement> listStringSelectServicesAddUser;

    //Список названий выбранных услуг ЮЗЕРА
    @FindBy(xpath = "//div[@class=\"select-list__body\"]//div[@class=\"select-list__row el-row\"]/div[1]")
    public List<WebElement> listNameSelectServicesAddUser;

    //Список артикулов выбранных услуг ЮЗЕРА
    @FindBy(xpath = "//div[@class=\"select-list__body\"]//div[@class=\"select-list__row el-row\"]/div[2]")
    public List<WebElement> listVendorsSelectServicesAddUser;

    //Список стоимостей выбранных услуг ЮЗЕРА
    @FindBy(xpath = "//div[@class=\"select-list__row el-row\"]//div[@class=\"serv-price\"]")
    public List<WebElement> listCostSelectServicesAddUser;

    //Список иконок удаления выбранных услуг ЮЗЕРА
    @FindBy(xpath = "//img[@class=\"removeService\"]")
    public List<WebElement> listIconsDeleteSelectServicesAddUser;

    //Название удаляемой услуги в списке выбранных услуг
    @FindBy(xpath = "//img[@class=\"removeService\"]/../../div[@class=\"el-col el-col-13\"]")
    public List<WebElement> nameServiceDeletedInListServices;

    //Строка удаляемой услуги в списке выбранных услуг
    @FindBy(xpath = "//img[@class=\"removeService\"]/../..")
    public List<WebElement> stringServiceDeletedInListServices;

    // Сохраненные услуги на странице редактирования услуг
    @FindBy(xpath = "//div[@class=\"select-list__row el-row\"]")
    public List<WebElement> listSavedServicesEditPage;

    // Кнопка "Изменить" услуги
    @FindBy(xpath = "//div[@sticky-side=\"bottom\"]//span[contains (text(), \"Изменить\")]")
    public WebElement buttonChangeServices;

    // Ссылка ФИО для перехода на карточку сотрудника
    @FindBy(xpath = "//h1[contains (text(), \"Редактировать профиль\")]/a")
    public WebElement linkFIO;

    // ------------------------------------------------------------------------------------------------------------------

    //    Вкладка "Сессии"
    @FindBy(xpath = "//span[text()= \"Сессии\"]")
    public WebElement tabSessions;

    //    Вкладка "История действий"
    @FindBy(xpath = "//span[text()= \"История действий\"]")
    public WebElement tabHistoryActions;

    //    Вкладка "История изменений"
    @FindBy(xpath = "//span[text()= \"История изменений\"]")
    public WebElement tabHistoryChanges;
//----------------------------------------------------------------------------------------------------------------
    //Карточка сотрудника
    //   Метка "Суперадминистратор"
    @FindBy(xpath = "//div/div[2]/div[1]/div[1]/div[1]/div[2]/span[contains (text(), \"Суперадминистратор\")]")
    public WebElement profLabelSuperadministrator;

    //   ФИО
    @FindBy(xpath = "(//*[@class=\"profile-name__name-text\"])[2]")
    public WebElement profFio;

    //   Метка "online/ofline"
    @FindBy(xpath = "(//div[contains(@class,\"online-status\")])[2]")
    public WebElement profLabelOnline;

    //   Метка "offline"
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[1]/div[@class=\"online-status " +
            "online-status--offline\"][contains (text(), \"offline\")]")
    public WebElement profLabelOffline;

    //   Должность сотрудника
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[@class=\"profile-name__post\"]//span")
    public List<WebElement> profPosition;

    //   Должность сотрудника отсутствует
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[@class=\"profile-name__post\"]")
    public WebElement absentProfPosition;

    //  Сохраненные специальности сотрудника
    @FindBy(xpath = "//div[@class=\"profile-block profile-name hidden-xs " +
            "user-summary__item\"]//div[@class=\"specialty-in-row__item-name\"]")
    public List<WebElement> listProfSpeciality;

    //   Список наименований мест работы
    @FindBy(xpath = "//div[@class=\"profile-block__row\"]/div[contains (text(), \"Место\")]/following-sibling::div")
    public List<WebElement> listProfNameWorkspace;

    //   Отсутствующее место работы наименований мест работы
    @FindBy(xpath = "//div[@class=\"profile-block profile-workplace\"]")
    public WebElement absentProfNameWorkspace;

    //   Список должностей в местах работы
    @FindBy(xpath = "//div[contains (text(), \"Должность\")]/following-sibling::div")
    public List<WebElement> listProfPosition;

    //   Список ролей в местах работы
    @FindBy(xpath = "//div[contains (text(), \"Роль\")]/following-sibling::div//span")
    public List<WebElement> listProfRole;

    //   Список меток "Амбулаторный приём ДА" в местах работы
    @FindBy(xpath = "//div[contains (text(),\"Амбулаторный приём\")]/following-sibling::div/span[text()=\"Да \"]")
    public List<WebElement> listProfAmbulanceYes;

    //   Список меток "Амбулаторный приём Нет" в местах работы
    @FindBy(xpath = "//div[contains (text(),\"Амбулаторный приём\")]/following-sibling::div/span[text()=\"Нет \"]")
    public List<WebElement> listProfAmbulanceNot;

    //   Список статусов в местах работы
    @FindBy(xpath = "//div[@class=\"profile-workplace__status active\"]/span")
    public List<WebElement> listProfStatus;

    // Список строк с услугами в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list user\"]//tr[@class=\"el-table__row\"]")
    public List<WebElement> listStringServicesProfUser;

    // Список названий услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list user\"]//tr[@class=\"el-table__row\"]/td[1]/div")
    public List<WebElement> listNamesServicesProfUser;

    // Список артикулов услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list user\"]//tr[@class=\"el-table__row\"]/td[2]/div")
    public List<WebElement> listVendorsServicesProfUser;

    // Список стоимостей услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list user\"]//tr[@class=\"el-table__row\"]/td[3]/div")
    public List<WebElement> listCostsServicesProfUser;

    // Фотография в карточке сотрудника
    @FindBy(xpath = "//div[@class=\"profile-userpic__photo userpic userpic--medium\"]//img[@alt=\"Фото\"]")
    public WebElement photoProfUser;

    //  Логин для входа в аккаунт
    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
    public WebElement profAccLogin;

    //  Email для входа в аккаунт
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement profAccEmail;

    //  Телефон для входа в аккаунт
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement profAccPhone;

    // Контакт Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
    public WebElement profEmail;

    // Контакт Телефон
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
    public WebElement profContPhone;

    // Контакт Instagram
    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
    public WebElement profInstagram;

    // Контакт Vk
    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
    public WebElement profVk;

    // Контакт Whatsapp
    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
    public WebElement profWhatsapp;

    // Контакт Viber
    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
    public WebElement profViber;

    // Контакт Facebook
    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
    public WebElement profFacebook;

    // Контакт Other
    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
    public WebElement profOther;

    // Образование, регалии, достижения
    @FindBy(xpath = "//span[contains (text(), \"Образование, регалии, достижения\")]/../../../following-sibling::div")
    public WebElement profEducation;

    //   Список  услуг
    @FindBy(xpath = "//span[contains (text(), \"Услуги\")]/../../../following-sibling::div/div/div/div/div[contains" +
            "(@class,\"el-table__body-wrapper\")]//tr")
    public List<WebElement> listProfServices;

    // Профиль ID сотрудника
    @FindBy(xpath = "//div[contains (text(), \"ID сотрудника\")]/following-sibling::div")
    public WebElement profSystemID;

    // Профиль Статус сотрудника
    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/following-sibling::div")
    public WebElement profSystemStatus;

    // Профиль QR-code
    @FindBy(xpath = "//span[contains(text(),\"QR\")]/../../../../div//img")
    public WebElement profQR;
    //    --------------------------------------------------------------------------------------------------------------
//    Редактирование сотрудника
//Иконка для загрузки фото при создании и редактировании пользователя
    @FindBy(xpath = ".//*[@id='avatarEditorCanvas']/following-sibling::input")
    public WebElement uploadPhoto;

    //    Кнопка РЕДАКТИРОВАТЬ ПРОФИЛЬ
    @FindBy(xpath = "//div[2]/a/span[contains (text(), \"Редактировать профиль\")]")
    public WebElement buttonChangeProfile;

    //    Имя
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(2)")
    public WebElement firstNameProfile;

    //    Фамилия
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(1)")
    public WebElement secondNameProfile;

    //    Отчество
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(3)")
    public WebElement middleNameProfile;

    //    Ссылка редактирования логин/email/телефон
    @FindBy(xpath = "//div[contains (text(), \"Данные для входа\")]/following-sibling::div[4]")
    public WebElement linkEditAuthProfile;

    //    Поле ввода ЛОГИН
    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
    public WebElement inputLogin;

    //    Ссылка "Удалить пользователя"
    @FindBy(xpath = "//span[text()=\"Удалить пользователя\"]")
    public WebElement linkDeleteUser;


    //    Ссылка "изменить" место работы
    @FindBy(xpath = "//div[@class=\"edit-workplace__link-container\"]/div[contains (text(), \"Изменить\")]")
    public List<WebElement> linkEditWorkplaces;

    //    Ссылка "изменить" специальность
    @FindBy(xpath = "//div[contains (text(), \"Специальности\")]/following-sibling::div[2]")
    public WebElement linkEditSpecialty;

    //    Ссылка ИЗМЕНИТЬ ФОТО
    @FindBy(xpath = "//div[@class='profile-userpic__photo userpic userpic--medium']/." +
            ".//following-sibling::div/div[contains (text(), \"Изменить\")]")
    public WebElement linkEditPhotoProfile;

    //    Ссылка ИЗМЕНИТЬ Образование, достижения, регалии
    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[2]")
    public WebElement linkEditEducationProfile;

    //    Ссылка ИЗМЕНИТЬ Способы связи
    @FindBy(xpath = "//div[contains (text(), \"Способы связи\")]/following-sibling::div[contains (text(), " +
            "\"Изменить\")]")
    public WebElement linkEditCommunicationMethodsProfile;
    // ------------------------------------------------------------------------------------------------------------------
    //Карточка организации
    // Название организации
    @FindBy(xpath = "(//div[@class=\"department-name__name-text\"]/span)[2]")
    public WebElement nameProfOrg;

    //     Сокращенное Название организации
    @FindBy(xpath = "(//span[contains (text(), \"Сокращенное наименование\")]/following-sibling::span)[2]")
    public WebElement nameAbbrProfOrg;

    // Входит в состав
    @FindBy(xpath = "(//span[contains (text(), \"Входит в состав\")]/following-sibling::div)[2]")
    public WebElement headOrganizationProfOrg;

    // Условия оказания помощи
    @FindBy(xpath = "(//span[contains (text(), \"Условия оказания помощи:\")]/following-sibling::span)[2]")
    public WebElement termsProfOrg;

    // Основной профиль организации
    @FindBy(xpath = "(//span[contains (text(), \"Основной профиль:\")]/following-sibling::span)[2]")
    public WebElement mainStreamProfOrg;

    // ФИО Руководителя организации
    @FindBy(xpath = "//div[@class=\"profile-block department-name hidden-xs\"]//span[contains (text(), " +
            "\"Руководитель:\")]/following-sibling::a")
    public WebElement directorFIOProfOrg;

    // Фамилия Руководителя организации
    @FindBy(xpath = "(//div[@class=\"profile-block department-name hidden-xs\"]//span[contains (text(), " +
            "\"Руководитель\")]/following-sibling::span)[1]")
    public WebElement directorFirstNameProfOrg;

    // Имя Руководителя организации
    @FindBy(xpath = "(//div[@class=\"profile-block department-name hidden-xs\"]//span[contains (text(), " +
            "\"Руководитель\")]/following-sibling::span)[2]")
    public WebElement directorSecondNameProfOrg;

    // Отчество Руководителя организации
    @FindBy(xpath = "(//div[@class=\"profile-block department-name hidden-xs\"]//span[contains (text(), " +
            "\"Руководитель\")]/following-sibling::span)[3]")
    public WebElement directorMiddleNameProfOrg;

    // Ссылка "Запись на прием"
    @FindBy(xpath = "(//span[contains (text(), \"Запись на приём\")])[last()]")
    public WebElement linkAppointmentServicesProfOrg;

    // Описание организации
    @FindBy(xpath = "//span[contains (text(), \"Описание\")]/../../../following-sibling::div//span")
    public WebElement descriptionProfOrg;

    // Ссылка "Подробнее" в Описании организации
    @FindBy(xpath = "//span[contains (text(), \"Описание\")]/../../../following-sibling::div//span[contains (text(), " +
            "\"Подробнее\")]")
    public WebElement linkDescriptionAdditionalProfOrg;

    // Полное Описание организации
    @FindBy(xpath = "//span[contains (text(), \"Описание\")]/../../." +
            "./following-sibling::div//div[@class=\"dep-description__text\"]/span")
    public WebElement fullDescriptionProfOrg;

    // Ссылка "Скрыть" в полном Описании организации
    @FindBy(xpath = "//span[contains (text(), \"Описание\")]/../../../following-sibling::div//span[contains (text()," +
            "\"Скрыть\")]")
    public WebElement linkFullDescriptionHideProfOrg;

    // Заголовок "Структура"
    @FindBy(xpath = "//span[contains(text(),\"Структура\")]")
    public WebElement headerStructureProfOrg;

    // Список Названий организаций в структуре
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> listNameUnitStructureProfOrg;

    // Список ссылок "Подробнее" о организации в структуре
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"]/a/span[contains (text(), \"Подробнее\")]")
    public List<WebElement> listLinksAdditionalProfOrg;

    // Заголовок "Услуги"
    @FindBy(xpath = "//span[contains(text(),\"Услуги\")]")
    public WebElement headerServicesProfOrg;

    // Список названий услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[1]/div")
    public List<WebElement> listNamesServicesProfOrg;

    // Список триггеров в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[2]/div")
    public List<WebElement> listTriggerServicesProfOrg;

    // Список артикулов услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[3]/div")
    public List<WebElement> listVendorsServicesProfOrg;

    // Список стоимостей услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[4]/div")
    public List<WebElement> listCostsServicesProfOrg;

    // Список Исполнителей услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[5]/div")
    public List<WebElement> listExecutorsServicesProfOrg;

    // Список иконок "Действие" в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[6]/div")
    public List<WebElement> listActionsServicesProfOrg;

    // Ссылка "Подробнее об услуге"
    @FindBy(xpath = "(//span[contains (text(), \"Подробнее об услуге\")])[last()]")
    public WebElement linkAdditionalServicesProfOrg;

    // Список "Порядковый номер" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[1]/div")
    public List<WebElement> listNumberExecutorProfOrg;

    // Список "Аватара" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[2]/div//img")
    public List<WebElement> listAvatarExecutorProfOrg;

//    // Список "Триггер" в списке исполнителей услуги
//    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[3]/div")
//    public List<WebElement> listTriggerExecutorProfOrg;

    // Список "Фамилия" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[3]/div")
    public List<WebElement> listSecondNameExecutorProfOrg;

    // Список "Имя" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[4]/div")
    public List<WebElement> listFirstNameExecutorProfOrg;

    // Список "Отчество" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[5]/div")
    public List<WebElement> listMiddleNameExecutorProfOrg;

    // Список "Должность" в списке исполнителей услуги
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//tr[@class=\"el-table__row\"]/td[6]/div")
    public List<WebElement> listPostExecutorProfOrg;

    // Заголовок "Сотрудники"
    @FindBy(xpath = "//span[contains(text(),\"Сотрудники\")]")
    public WebElement headerExecutorsProfOrg;

    // Строка исполнителя в списке сотрудников
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]")
    public List<WebElement> listStringWorker;

    // Список порядковых номеров в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[1]/div")
    public List<WebElement> listNumbersExecutorsProfOrg;

    // Список фотографий в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[2]/div")
    public List<WebElement> listPhotoExecutorsProfOrg;

    // Список триггер в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[3]/div")
    public List<WebElement> listTriggersExecutorsProfOrg;

    // Список фамилий в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[4]/div")
    public List<WebElement> listFirstNamesExecutorsProfOrg;

    // Список имен в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[5]/div")
    public List<WebElement> listSecondNamesExecutorsProfOrg;

    // Список Отчеств в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[6]/div")
    public List<WebElement> listMiddleNamesExecutorsProfOrg;

    // Список Должностей в списке исполнителей
    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[7]/div")
    public List<WebElement> listPositionsExecutorsProfOrg;

    // Карта
    @FindBy(xpath = "//img[@class=\"dep-mini-map__map-image\"]")
    public WebElement mapProfOrg;

    // Кнопка "Редактировать подразделение"
    @FindBy(xpath = "//span[contains(text(),\"Ред. подразделение\")]")
    public WebElement buttonEditProfOrg;

    //Заголовок "Редактировать организацию" + название организации на странице редактирования
    @FindBy(xpath = "//div[@class=\"page-title\"]/h1[contains(text(),\"Редактировать организацию:\")]")
    public WebElement headerEditOrg;

    //Ссылка Название организации на странице редактирования
    @FindBy(xpath = "//div[@class=\"page-title\"]/h1[contains(text(),\"Редактировать организацию:\")]/a")
    public WebElement linkNameOrgEditOrg;

    // Адрес организации
    @FindBy(xpath = "//div[contains (text(), \"Адрес\")]/following-sibling::div")
    public WebElement adressProfOrg;

    // Список названий телефонов
    @FindBy(xpath = "//a[contains(text(),\"+\")]/preceding-sibling::div")
    public List<WebElement> listNamePhoneProfOrg;

    // Список номеров телефонов
    @FindBy(xpath = "//a[contains(text(),\"+\")]")
    public List<WebElement> listNumbersPhoneProfOrg;

    // Сайт
    @FindBy(xpath = "//div[contains (text(), \"Сайт\")]/following-sibling::a")
    public WebElement siteProfOrg;

    // Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
    public WebElement emailProfOrg;

    // Vk
    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
    public WebElement vkProfOrg;

    // Instagram
    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
    public WebElement instagramProfOrg;

    // Facebook
    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
    public WebElement facebookProfOrg;

    // Другое
    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
    public WebElement otherProfOrg;

    // ID подразделения
    @FindBy(xpath = "//div[contains (text(), \"ID подразделения\")]/following-sibling::div")
    public WebElement idProfOrg;

    // Код подразделения
    @FindBy(xpath = "//div[contains (text(), \"Код подразделения\")]/following-sibling::div")
    public WebElement codeProfOrg;

    // Реестровый номер ОМС
    @FindBy(xpath = "//div[contains (text(), \"Реестровый номер ОМС\")]/following-sibling::div")
    public WebElement numberOmsProfOrg;

    // Идентификатор ОМС
    @FindBy(xpath = "//div[contains (text(), \"Идентификатор ОМС\")]/following-sibling::div")
    public WebElement identifierOmsProfOrg;

    // Идентификатор ПУМП
    @FindBy(xpath = "//div[contains (text(), \"Идентификатор ПУМП\")]/following-sibling::div")
    public WebElement identifierPumpProfOrg;

    // Статус подразделения
    @FindBy(xpath = "//div[contains (text(), \"Статус подразделения\")]/following-sibling::div")
    public WebElement statusProfOrg;

    // Дата создания
    @FindBy(xpath = "//div[contains (text(), \"Дата создания\")]/following-sibling::div")
    public WebElement dateCreateProfOrg;

    // Последнее изменение
    @FindBy(xpath = "//div[contains (text(), \"Последнее изменение\")]/following-sibling::div")
    public WebElement lastChangeProfOrg;

    // QR код
    @FindBy(xpath = "//span[contains (text(), \"QR код\")]/../../..//following-sibling::div//img")
    public WebElement qrProfOrg;

    // --------------------------Список подразделений--------------------------------------------------------
    //Список названий организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> listNameOrg;

    //Список сокращенных названий организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][2]")
    public List<WebElement> listNameAbbrOrg;

    //   Наименование "Кардиоцентр №1"
    @FindBy(xpath = "//div[contains (text(), \"Кардиоцентр №1\")]")
    public WebElement Kardio1;


    // --------------------------Список сотрудников--------------------------------------------------------
    //Кнопка "Поиск сотрудника" в списке сотрудников
    @FindBy(xpath = "//div[@class=\"user-list\"]//span[text()=\"Поиск сотрудника\"]")
    public WebElement buttonSearchWorker;

    //Поле ввода "Фамилия" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Фамилия\"]")
    public WebElement inputSearchSecondName;

    //Поле ввода "Имя" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Имя\"]")
    public WebElement inputSearchFirstName;

    //Поле ввода "Отчество" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Отчество\"]")
    public WebElement inputSearchMiddleName;

    //Поле ввода "Email" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Email\"]")
    public WebElement inputSearchEmail;

    //Поле ввода "Телефон" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Телефон\"]")
    public WebElement inputSearchPhone;

    //Поле ввода "Логин" в блоке поиска сотрудников
    @FindBy(xpath = "//input[@placeholder=\"Логин\"]")
    public WebElement inputSearchLogin;

    //Кнопка "Поиск" в блоке поиска сотрудников
    @FindBy(xpath = "//span[text()=\"Поиск\"]")
    public WebElement buttonSearch;

    //Кнопка "Сбросить" в блоке поиска сотрудников
    @FindBy(xpath = "//span[text()=\"Сбросить\"]")
    public WebElement buttonReset;

    //Фильтр "Структура организации"
    @FindBy(xpath = "//div[contains(text(),\"Структура организации\")]/following-sibling::div//div[contains(text(),\"Организации\")]")
    public WebElement filterOrganization;

    //Подразделение "lionsdigital.pro"
    @FindBy(xpath = "//span[contains(text(),\"Lionsdigital.pro\")]/../label//span[@class=\"el-checkbox__inner\"]")
    public WebElement lionsdigital;

    //Подразделение "ФГАО"
    @FindBy(xpath = "//span[contains(text(),\"ФГАО\")]/../label//span[@class=\"el-checkbox__inner\"]")
    public WebElement FGAO;

    //Фильтр "Должность"
    @FindBy(xpath = "//div[contains(text(),\"Должность\")]/following-sibling::div//div[contains(text(),\"Должности\")]")
    public WebElement filterPost;

    //Должность "Врач лечащий людей"
    @FindBy(xpath = "//span[@class=\"el-tree-node__label\"][contains(text(),\"Дизайнер\")]/../label/span/span")
    public WebElement postDisignerFilter;

    //Должность "Главный по анализам"
    @FindBy(xpath = "//span[@class=\"el-tree-node__label\"][contains(text(),\"Главный по анализам\")]/../label/span/span")
    public WebElement postChiefAnalizFilter;

    //Фильтр "Статус работы"
    @FindBy(xpath = "//div[contains(text(),\"Статус работы\")]/following-sibling::div//input[@placeholder=\"Статус работы\"]")
    public WebElement filterStatusWork;

    //Статус работы "Работает"
    @FindBy(xpath = "//span[text()=\"Работает\"]")
    public WebElement StatusWorkInFilter;

    //Статус работы "На больничном"
    @FindBy(xpath = "//span[text()=\"На больничном\"]")
    public WebElement StatusSickInFilter;

    //Фильтр "Статус в системе"
    @FindBy(xpath = "//div[contains(text(),\"Статус в системе\")]/following-sibling::div//input[@placeholder=\"Статус в системе\"]")
    public WebElement filterStatusInSystem;

    //Статус "Активен"
    @FindBy(xpath = "//span[text()=\"Активен\"]")
    public WebElement statusActiveInSystemFilter;

    //Статус "Отключен"
    @FindBy(xpath = "//span[text()=\"Отключен\"]")
    public WebElement statusDisabledInSystemFilter;

    //Фильтр "Статус Online/Offline"
    @FindBy(xpath = "//div[contains(text(),\"Статус Online/Offline\")]/following-sibling::div//input[@placeholder=\"Статус\"]")
    public WebElement filterStatusOnline;

    //Статус "Online"
    @FindBy(xpath = "//span[text()=\"Онлайн\"]")
    public WebElement statusOnlineInSystemFilter;

    //Статус "Offline"
    @FindBy(xpath = "//span[text()=\"Оффлайн\"]")
    public WebElement statusOfflineInSystemFilter;

    //Фильтр "Роли"
    @FindBy(xpath = "//div[contains(text(),\"Роль\")]/following-sibling::div//input[@placeholder=\"Роли\"]")
    public WebElement filterRole;

    //Роль "Специалист"
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item\"]//span[contains(text(),\"Спец\")]")
    public WebElement roleSpecInFilter;

    //Роль "Администратор"
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item\"]//span[contains(text(),\"Администратор\")]")
    public WebElement roleAdminInFilter;

    //Фильтр "Глобальная роль"
    @FindBy(xpath = "//div[contains(text(),\"Глобальная роль\")]/following-sibling::div//input[@placeholder=\"Глобальная роль\"]")
    public WebElement filterGlobalRole;

    //Глобальная роль "Суперадминистратор"
    @FindBy(xpath = "//span[text()=\"Суперадминистратор\"]")
    public WebElement GlobalRoleSuperAdminInFilter;

    //Список фамилий
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[4]")
    public List<WebElement> listSecondNameInUserList;

    //Список имен
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[5]")
    public List<WebElement> listFirstNameInUserList;

    //Список отчеств
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[6]")
    public List<WebElement> listMiddleNameInUserList;

    //Список должностей
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[7]")
    public List<WebElement> listPostInUserList;

    //Список статусов
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[8]/div/div")
    public List<WebElement> listStatusInUserList;

    //Список меток "Суперадминистратор"
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[9]")
    public List<WebElement> listLabelSuperAdminInUserList;

    // --------------------------Список специальностей--------------------------------------------------------
    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[contains(text(),\"Медицинские специальности\")]")
    public WebElement sectionMedSpecialtyInListSpecialty;

    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[contains(text(),\"Немедицинские специальности\")]")
    public WebElement sectionNotMedSpecialtyInListSpecialty;

    //   Название существующей специальности
    @FindBy(xpath = "//div[@class=\"special-tree__name\"]/span")
    public List<WebElement> nameSpecialtyInListSpecialty;

    //   Описание существующей специальности
    @FindBy(xpath = "//div[@class=\"special-tree__description\"]")
    public List<WebElement> descriptionSpecialtyInListSpecialty;

    // --------------------------Карточка специальности--------------------------------------------------------
    //   Кнопка "Ред. специальность"
    @FindBy(xpath = "//span[text()=\"Ред. специальность\"]")
    public WebElement buttonEditSpecialty;

    //   Название специальности
    @FindBy(xpath = "//div[@class=\"info-box__title\"]")
    public WebElement nameSpecialtyProfile;

    //   Описание специальности
    @FindBy(xpath = "//div[@class=\"info-box__title\"]/following-sibling::div")
    public WebElement descriptionSpecialtyProfile;

    //   Раздел специальности
    @FindBy(xpath = "//div[contains(text(),\"Специальность:\")]/span")
    public WebElement sectionSpecialtyProfile;

    //   Иконка специальности
    @FindBy(xpath = "//img[contains(@src,\"/img/specialties/\")]")
    public WebElement iconSpecialtyProfile;

    //-------------------------------------------- Карточка услуги-----------------------------------------------
    // Название услуги
    @FindBy(xpath = "//div[@class=\"info-box__title\"]")
    public WebElement nameServiceProf;

    // Название услуги для печати
    @FindBy(xpath = "//div[contains(text(),\"Наименование для печати:\")]/span")
    public WebElement nameServicePrintProf;

    // Возможность записи
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента:\")]/span")
    public WebElement recordServiceProf;

    // Характер услуги
    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]/span")
    public WebElement characterServiceProf;

    // Противопоказания к услуге
    @FindBy(xpath = "//span[contains(text(),\"Противопоказания\")]/../../../following-sibling::div//" +
            "div[@class=\"contraindications-list\"]")
    public WebElement contraindicationServiceProf;

    // Предусловия к услуге
    @FindBy(xpath = "//span[contains(text(),\"Предусловия\")]/../../../following-sibling::div//" +
            "div[@class=\"preconditions-list\"]")
    public WebElement preconditionServiceProf;

    // Описание услуги
    @FindBy(xpath = "//span[contains(text(),\"Описание услуги\")]/../../../following-sibling::div" +
            "//div[@class=\"dep-description__text\"]")
    public WebElement descriptionServiceProf;

    // Стоимость ОМС
    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ОМС\")]/div)[1]")
    public WebElement costOMSProf;

    // Стоимость ДМС
    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ДМС\")]/div)[1]")
    public WebElement costDMSProf;

    // Стоимость ПМУ
    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ПМУ\")]/div)[1]")
    public WebElement costPMUProf;

    // Кнопка "Редактировать услугу"
    @FindBy(xpath = "(//div[@class=\"service-edit__button\"])[1]/a//span")
    public WebElement buttonEditServiceProf;

    // Код услуги
    @FindBy(xpath = "//div[contains(text(),\"Код(ы) услуги\")]/div")
    public WebElement сodeServiceProf;

    // Артикул услуги
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/div")
    public WebElement vendorServiceProf;

    //-------------------------------------------- Список услуг-----------------------------------------------
    // Главный раздел услуг
    @FindBy(xpath = "//div[@class=\"el-tree-node__content\"][@style=\"padding-left: 18px;\"]//span[@class=\"services-tree__node-name\"]")
    public WebElement mainSectionServiceInList;

    // Подраздел услуг "Степашина"
    @FindBy(xpath = "//span[contains(text(),'01.03 Услуги Степашина.')]")
    public WebElement subSectionServiceStepashinInList;

    // Услуги второго уровня
    @FindBy(xpath = "//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span")
    public List<WebElement> sectionService2LevInList;

    // Услуги третьего уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 54px;\"]//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div[1]")
    public List<WebElement> sectionService3LevInList;

    // Кнопка  "Удалить" в гамбургере
    @FindBy(xpath = "(//span[contains(text(),'Удалить')])[last()]")
    public WebElement buttonDeleteSectionServiceInGamburger;

    //-------------------------------------Список кабинетов----------------------------------------------------------------
    //Названия кабинетов в списке
    @FindBy(xpath = "//div[@class=\"cabinets-tree__name isNode\"]/span")
    public List<WebElement> listNameCabinet;

    //Названия рабочих мест в списке
    @FindBy(xpath = "//div[@class=\"cabinets-tree__name\"]/span")
    public List<WebElement> listNameWorkplace;

    //Кнопка "редактировать кабинет" в списке
    @FindBy(xpath = "(//span[text()='Редактировать'])[last()]")
    public WebElement buttonEditCabinetInList;

    //Кнопка "удалить" в списке
    @FindBy(xpath = "(//span[text()='Удалить'])[last()]")
    public WebElement buttonDeleteInList;

    //Кнопка "Подтвердить Удаление кабинета" при редактировании
    @FindBy(xpath = "//span[contains(text(),\"Удалить кабинет?\")]/../../following-sibling::div//span[contains(text(),\"Удалить\")]")
    public WebElement buttonConfirmationDeleteCabinet;

    //Кнопка "Отменить удаление кабинета" при редактировании
    @FindBy(xpath = "//span[contains(text(),\"Удалить кабинет?\")]/../../following-sibling::div//span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelDeleteCabinet;

    //Кнопка "Удалить рабочее место" при редактировании
    @FindBy(xpath = "//span[contains(text(),\"Удалить рабочее место?\")]/../../following-sibling::div//span[contains(text(),\"Удалить\")]")
    public WebElement buttonConfirmationDeleteWorkplace;

    //Кнопка "Отменить удаление рабочего места" при редактировании
    @FindBy(xpath = "//span[contains(text(),\"Удалить рабочее место?\")]/../../following-sibling::div//span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelDeleteWorkplace;

    public RemoteWebDriver driver;

    public void preloader() {
        sleep(200);
        preloader0();
    }

    public void preloader0() {
        WebDriverWait iWait = new WebDriverWait(driver, 6);

        if (!driver.getCurrentUrl().equals(CurrentURL)) {
            CurrentURL = driver.getCurrentUrl();
            pr("Текущий URL: " + driver.getCurrentUrl());
        }
        if (driver.getCurrentUrl().contains(ConfigProperties.getTestProperty("baseurl")) &&
                !driver.getCurrentUrl().contains("login")) {
            try {
                iWait.until(ExpectedConditions.textToBePresentInElement(logoAll, "Банк Здоровья"));
            } catch (StaleElementReferenceException e) {
                pr("Logo is not found");
                iWait.until(ExpectedConditions.textToBePresentInElement(logoAll, "Банк Здоровья"));
            }

            try {
                for (i = 0; i < 50; i++) {
                    if (preload.isEnabled()) {
//                        sleep(500);
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }

        if (driver.getCurrentUrl().contains(ConfigProperties.getTestProperty("baseurl")) &&
                driver.getCurrentUrl().contains("login")) {
            try {
                iWait.until(ExpectedConditions.textToBePresentInElement(logoLogin, "Банк Здоровья"));
            } catch (StaleElementReferenceException e) {
                pr("Logo is not found");
                iWait.until(ExpectedConditions.textToBePresentInElement(logoLogin, "Банк Здоровья"));
            }

            try {
                for (i = 0; i < 50; i++) {
                    if (preload.isEnabled()) {
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }
    }

    public void auth(String Login, String Password) {
        CurrentURL = driver.getCurrentUrl();
        pr("Текущий URL: " + CurrentURL);
        try {
            if (!driver.getCurrentUrl().contains("login")) {
                click(body);
                scrollHomePage();
                click(listActionProfile);
                click(listActionProfileExit);
            }
        } catch (NoSuchElementException e) {
            pr("Разлогинивание не требуется");
        }
        pr("Autorization begin");
        clickAndSendKeys(authInputLogin, Login);
        clickAndSendKeys(authInputPassword, Password);
        click(authButtonEnter);
        pr("click Enter");
        sleep(4000);
        preloader();
        if (!Login.equals("adkajslk@ndex.ru") && !Login.equals("number1@mil.ru") && !Login.equals("")) {
            waitTextPresent(logoAll, "Банк Здоровья");
        }

    }

    public void click(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
        moveMouse(element);
        sleep(300);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
        if (driver.getCurrentUrl().contains("login")) {
            sleep(2000);
        }

        String Locator = element.toString();
//        System.out.println(Locator);
        if (Locator.contains("RemoteWebDriver: chrome on LINUX")) {
            Locator = Locator.substring(Locator.indexOf(" ->"));
        }
        if (Locator.contains("Proxy element for")) {
            Locator = Locator.substring(Locator.indexOf(" '"));
        }
        pr("click " + Locator);
        preloader();
    }

    public void waitE_ClickableAndClickAndUp(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        moveMouse(element);
        buttonUp(1);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void clickAndSendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
        moveMouse(element);
        element.click();
        element.clear();
        String Locator = element.toString();
        if (Locator.contains("RemoteWebDriver: chrome on LINUX")) {
            Locator = Locator.substring(element.toString().indexOf("->"));
        }
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
//        pr("Написали: \"" + text + "\" В локатор: " + element.toString().substring(71));
        pr("Написали: \"" + text + "\" в: " + Locator);
    }

    public void clickAndSendKeysAndClick(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        moveMouse(element);
        element.click();
        element.clear();
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
        element.click();
    }

    public void clickAndSendKeysAndEnter(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        moveMouse(element);
        element.click();
        element.clear();
        pr("Ищем: " + text);
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    public void waitVisibility(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        preloader();
        moveMouse(element);
        iWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitE_invisibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        try {

            iWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep(500);
    }

    public String waitE_visibilityOfAndGettext(WebElement element) {
        sleep(800);
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    public String waitTextPresent(WebElement element, String phrase) {
        waitVisibility(element);
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        sleep(100);
        moveMouse(element);
        pr(element.getText());
        iWait.until(ExpectedConditions.textToBePresentInElement(element, phrase));
        String text = element.getText();
        pr(element.getText() + " ====== " + phrase);
        return text;
    }


    public void logout() {
        if (!driver.getCurrentUrl().contains("login")) {
            System.out.println("Принудительное разлогинивание");
            click(body);
            scrollHomePage();
            click(listActionProfile);
            click(listActionProfileExit);
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pr(String text) {
        System.out.println(nowHourMinSec() + " - " + text);
    }

    public static String nameTest(String Name) {
        String delimeter = "\\|"; // Разделитель
        String[] subStr = Name.split(delimeter);
        String nameTest = (subStr[0] + ".png");
        return nameTest;
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] screenFailedTest(String Name, String Directory) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String delimeter = "\\|"; // Разделитель
        String[] subStr = Name.split(delimeter);
        File destination = new File(Directory + subStr[0] + ".png");
        String Screen = Directory + subStr[0] + ".png";
        pr(Screen);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Скриншот не сохранился");
        }
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public void moveMouse(WebElement element) {
        Actions actions = new Actions(driver);

        if (driver.getCurrentUrl().contains(ConfigProperties.getTestProperty("baseurl"))) {
            if (element.getLocation().getY() > 501 && element.getLocation().getY() < 700) {
                actions.moveToElement(element).build().perform();
                buttonDown(4);
            }
            if (element.getLocation().getY() > 700) {
                actions.moveToElement(element).build().perform();
                buttonDown(5);
            }
            if (element.getLocation().getY() < 200 && element.getLocation().getY() >= 0) {
                actions.moveToElement(element).build().perform();
                buttonUp(2);
            }
            if (element.getLocation().getY() <= -1) {
                actions.moveToElement(element).build().perform();
                buttonUp(3);
            }
        }
    }

    public void mouseHoverOn(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void scrollWithOffset(WebElement webElement, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";
        js.executeScript(code, webElement, x, y);
    }

    public void scrollWhell(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String code = "window.scroll(" + (x) + "," + (y) + ");";
        js.executeScript(code, x, y);
    }


    public void buttonDown(int x) {
        sleep(100);
        for (int i = 0; i < x; i++) {
            driver.findElement(By.xpath("//html/body")).sendKeys(Keys.DOWN);
            sleep(100);
        }
    }

    public void buttonUp(int x) {
        sleep(100);
        for (int i = 0; i < x; i++) {
            driver.findElement(By.xpath("//html/body")).sendKeys(Keys.UP);
            sleep(100);
        }
    }

    public void scrollHomePage() {
        sleep(100);
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.HOME);
        sleep(100);
    }


    public void scrollEndPage() {
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.END);
    }

    public void scrollPageUp() {
        driver.findElement(By.xpath("//html/body")).click();
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_UP);
        sleep(100);
    }

    public void scrollPageDown() {
        driver.findElement(By.xpath("//html/body")).click();
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_DOWN);
        sleep(100);
    }

    public void captureScreenshot(String Name, String Directory) {
        screenFailedTest(Name, Directory);
    }

    public void moveToProfileUserIDChange(String UserID) {
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        sleep(1000);
        preloader();
        waitTextPresent(buttonChangeProfile, "Редактировать профиль");
        click(buttonChangeProfile);
        waitVisibility(firstNameProfile);
    }

//    public void moveToProfileUserIDChange(String UserID) {
//        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
//        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
//        sleep(1000);
//        preloader();
//        waitTextPresent(buttonChangeProfile, "Редактировать профиль");
//        click(buttonChangeProfile);
//        waitVisibility(firstNameProfile);
//    }

    public void moveToProfileUserID(String UserID) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        preloader();
        waitVisibility(profFio);
    }

//    public void moveToProfileUserID(String UserID) {
//        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
//        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
//        preloader();
//        waitVisibility(profFio);
//    }

    public void moveToProfileUser(String SecondName) {
        try {
            click(buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            click(menuWorkers);
            click(buttonUserList);
        }
        waitVisibility(listStatusInUserList.get(0));
        preloader();
        click(sumWorker);
        click(workers100);
        sleep(1000);
        preloader();
        click(driver.findElement(By.xpath("//tr/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
    }

    public void moveToProfileOrgID(String OrganizationID1) {
        if (!EnvironmentIntegration.OrganizationID1.equals("")) {
            System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/departments/" + OrganizationID1);
            driver.get(ConfigProperties.getTestProperty("baseurl") + "#/departments/" + OrganizationID1);
            preloader();
            waitTextPresent(buttonEditOrganization, "Ред. подразделение");
        }

        if (!OrganizationID1.equals("")) {
            System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/departments/" + OrganizationID1);
            driver.get(ConfigProperties.getTestProperty("baseurl") + "#/departments/" + OrganizationID1);
            preloader();
            waitTextPresent(buttonEditOrganization, "Ред. подразделение");
        }
    }

    public void moveToProfileOrg(String NameOrganization) {
        try {
            click(buttonOrganizationsList);
        } catch (TimeoutException e) {
            System.out.println(e);
            click(menuOrganizations);
            click(buttonOrganizationsList);
            sleep(2000);
        }

        if (NameOrganization.contains("Название отделения") | NameOrganization.contains("Услуги") |
                NameOrganization.contains("Медицинская организация")) {
            click(openKardio1);
            click(openKardio2);
            click(openKardio3);
            click(openKardio4);
            buttonDown(5);
            WebElement findToNameOrganization =
                    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[1][@class=\"dep-tree__cell\"][contains (text(), \"" + NameOrganization +
                                    "\")]/../div/a/span[contains (text(),\"Подробнее\")]")));
            scrollWithOffset(findToNameOrganization, 0, 30);
            click(findToNameOrganization);
        } else {
            WebElement findToNameOrganization =
                    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[1][@class=\"dep-tree__cell\"][contains (text(), \"" + NameOrganization +
                                    "\")]/../div/a/span[contains (text(),\"Подробнее\")]")));
            scrollWithOffset(findToNameOrganization, 0, 30);
            click(findToNameOrganization);
        }
    }

    public void writeCookiesToFile(WebDriver driver) {
        System.out.println(driver.manage().getCookies());
        File file = new File("browser.dat");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write((cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - " + e.getLocalizedMessage());
        }
    }

    public Cookie readCookiesFromFile() {
        Cookie cookie = new Cookie("", "");
        try {
            File file = new File("browser.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String date = str.nextToken();
                    if (!(date).equals("null")) {
                        expiry = new Date(System.currentTimeMillis() * 2);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - " + ex.getLocalizedMessage());
        }
        return cookie;
    }

    // метод возвращает размер файла в килобайтах
    public static String getFileSizeKiloBytes(File file) {
        return (double) file.length() / 1024 + " kb";
    }

    public void compareStringAndString(String Expected, String Actual) {
        preloader();
        pr(Expected + " = " + Actual);
        Assert.assertEquals(Expected, Actual);
    }

    public void compareStringAndWebelement(String Expected, WebElement Actual) {
        preloader();
        moveMouse(Actual);
        buttonDown(2);
        pr(Expected + " = " + Actual.getText());
        Assert.assertEquals(Expected, Actual.getText());
    }

    public void compareWebelementAndString(WebElement Expected, String Actual) {
        preloader();
        moveMouse(Expected);
        buttonDown(2);
        pr(Expected.getText() + " = " + Actual);
        Assert.assertEquals(Expected.getText(), Actual);
    }

    public void compareWebelementsListAndArrayList(List<WebElement> Webelements, ArrayList<String> Stringlist,
                                                   int Sum) {
        preloader();
        sleep(1000);
        for (WebElement Act : Webelements) {
            moveMouse(Act);
            pr(Act.getText());
        }
        System.out.println(Stringlist);

        for (WebElement Act : Webelements) {
            i = i + 1;
            for (String Exp : Stringlist) {
                u = 0;
                pr(Act.getText().replace(",", "") + " = " + Exp);


                if (Exp.equals(Act.getText().replace(",", ""))) {
                    u = u + 1;
                    System.out.println("!+Match finded+!");
                    break;
                } else {
                    pr("Элементы не совпали");
                }
            }
            Assert.assertEquals("Совпадение элементов не найдено", 1, u);
        }
        Assert.assertEquals(Sum, Webelements.size());
        Assert.assertEquals(Webelements.size(), Stringlist.size());
    }

    public void compareArrayListAndWebelementsList(ArrayList<String> Stringlist, List<WebElement> Webelements,
                                                   int Sum) {
        preloader();
        sleep(1000);
        System.out.println(Stringlist);
        soutListWebelements(Webelements);
        Assert.assertEquals(Sum, Webelements.size());
        Assert.assertEquals(Webelements.size(), Stringlist.size());
        for (WebElement Act : Webelements) {
            i = i + 1;
            for (String Exp : Stringlist) {
                u = 0;
                pr(Exp + " = " + Act.getText().replace(",", ""));


                if (Exp.equals(Act.getText().replace(",", ""))) {
                    u = u + 1;
                    pr("!+Match finded+!");
                    break;
                } else {
                    pr("Элементы не совпали");
                }
            }
            Assert.assertEquals("Совпадение элементов не найдено", 1, u);
        }
    }

    public void compareArrayListAndArrayList(ArrayList<String> Stringlist, ArrayList<String> Stringlist2,
                                             int Sum) {
        preloader();
        sleep(1000);
        System.out.println(Stringlist);
        System.out.println(Stringlist2);
        Assert.assertEquals(Sum, Stringlist.size());
        Assert.assertEquals(Stringlist.size(), Stringlist2.size());
        for (String Act : Stringlist2) {
            i = i + 1;
            for (String Exp : Stringlist) {
                u = 0;
                pr(Act.replace(",", "") + " = " + Exp.replace(",", ""));


                if (Act.replace(",", "").contains(Exp.replace(",", ""))) {
                    u = u + 1;
                    pr("!+Match finded+!");
                    break;
                } else {
                    pr("Элементы не совпали");
                }
            }
            Assert.assertEquals("Совпадение элементов не найдено", 1, u);
        }
    }

    public void soutListWebelements(List<WebElement> Webelements) {
        for (WebElement Webelement : Webelements) {
            System.out.println(Webelement.getText());
        }
    }

    public void elementIsDisabled(WebElement Element) {
        try {
            System.out.println("Проверяем отсутствие элемента: " + Element.toString());
            System.out.println("Проверяем отсутствие элемента: " + Element.getText());
            Assert.assertTrue(!Element.isEnabled());
        } catch (NoSuchElementException E) {
            System.out.println("Element not found and it is cool");
        }
    }

    public void closeTabSwitchTab(WebDriver Driver) {
        Set<String> winSet = Driver.getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String newTab = winList.get(winList.size() - 1);
        Driver.close(); // close the original tab
        Driver.switchTo().window(newTab); // switch to new tab
    }

    public void openNewTab(String url) {
        String a = "window.open('" + url + "','_blank');";
        System.out.println(a);
        ((JavascriptExecutor) driver).executeScript(a);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public void changeTab(WebDriver Driver, int Tab) {
        Set<String> winSet = Driver.getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String newTab = winList.get(winList.size() - Tab);
        Driver.switchTo().window(newTab); // switch to new tab
        sleep(1000);
    }

    public void switchTab(WebDriver Driver) {
        Set<String> winSet = Driver.getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String newTab = winList.get(winList.size() - 1);
        Driver.switchTo().window(newTab); // switch to new tab
    }

    public int random(int Max) {
        int Min = 0; // Начальное значение диапазона - "от"
        int randomNumber = Min + (int) (Math.random() * Max);
        System.out.println("Случайное число: " + randomNumber);
        return randomNumber;
    }

    public static String nowDate() {
        Date currentDate = new Date();
        Locale local = new Locale("ru", "RU");
        String[] russianMonat =
                {"янв.", "февр.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.", "окт.", "нояб.", "дек."};
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm", russSymbol);
        currentDate = new Date();
//        System.out.println("currentDateTime = " + sdf.format(currentDate));
        return sdf.format(currentDate);
    }

    public static String nowDateAdd1Min() {
        Date currentDate = new Date();
        long time = currentDate.getTime();
        Locale local = new Locale("ru", "RU");
        String[] russianMonat =
                {"янв.", "февр.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.", "окт.", "нояб.", "дек."};
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm", russSymbol);
        time = time + (60000);
//        System.out.println("time = " + sdf.format(time));
        return sdf.format(time);
    }

//    public static String nowDate() {
//        String months[] = {"янв.", "фев.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.",
//                "окт.", "нояб.", "дек."};
//        String NowDate = "";
//        GregorianCalendar gcalendar = new GregorianCalendar();
//        gcalendar.getTime();
//
//        if (gcalendar.get(Calendar.DATE) < 10) {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " 0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " " + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " 0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " " + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                }
//            }
//            System.out.println("Искомая дата:" + NowDate);
//        } else {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " 0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " " + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " 0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " +
//                            gcalendar.get(Calendar.YEAR) + " " + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                }
//            }
//            System.out.println("Искомая дата:" + NowDate);
//
//        }
//        return NowDate;
//    }

    public String todayDate() {
        Date currentDate = new Date();
        Locale local = new Locale("ru", "RU");
        String[] russianMonat =
                {"янв.", "февр.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.", "окт.", "нояб.", "дек."};
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", russSymbol);
        currentDate = new Date();
//        System.out.println("currentDateTime = " + sdf.format(currentDate));
        return sdf.format(currentDate);
    }

//    public String todayDate() {
//        String months[] = {"янв.", "фев.", "мар.", "апр.", "май", "июн.", "июл.", "авг.", "сент.",
//                "окт.", "нояб.", "дек."};
//        String NowDate = "";
//        GregorianCalendar gcalendar = new GregorianCalendar();
//
//        if (gcalendar.get(Calendar.DATE) < 10) {
//            NowDate =
//                    "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
//        } else {
//            if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                NowDate =
//                        "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " 0" +
//                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                System.out.println("Искомая дата:" + NowDate);
//            }
//            NowDate =
//                    gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
//        }
//        return NowDate;
//    }

    public String nowHourMin() {
        Date currentDate = new Date();
        Locale local = new Locale("ru", "RU");
        String[] russianMonat =
                {"янв.", "февр.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.", "окт.", "нояб.", "дек."};
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", russSymbol);
        currentDate = new Date();
//        System.out.println("currentDateTime = " + sdf.format(currentDate));
        return sdf.format(currentDate);
    }

//    public static String nowHourMin() {
//        String NowDate = "";
//        GregorianCalendar gcalendar = new GregorianCalendar();
//
//        if (gcalendar.get(Calendar.DATE) < 10) {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                }
//            }
//            System.out.println("Искомая дата:" + NowDate);
//        } else {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                }
//            }
//            System.out.println("Искомая дата:" + NowDate);
//        }
//        return NowDate;
//    }

    public String nowHourMinSec() {
        Date currentDate = new Date();
        Locale local = new Locale("ru", "RU");
        String[] russianMonat =
                {"янв.", "февр.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.", "окт.", "нояб.", "дек."};
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", russSymbol);
        currentDate = new Date();
//        System.out.println("currentDateTime = " + sdf.format(currentDate));
        return sdf.format(currentDate);
    }

//    public static String nowHourMinSec() {
//        String NowDate = "";
//        GregorianCalendar gcalendar = new GregorianCalendar();
//
//        if (gcalendar.get(Calendar.DATE) < 10) {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                }
//            }
//        } else {
//            if (gcalendar.get(Calendar.MINUTE) < 10) {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                }
//            } else {
//                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
//                    NowDate = "0" + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                } else {
//                    NowDate = gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE) + ":" +
//                            gcalendar.get(Calendar.SECOND);
//                }
//            }
//        }
//        return NowDate;
//    }
}