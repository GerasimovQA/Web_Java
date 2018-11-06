package global;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.io.*;
import java.util.*;

public class GlobalPage {

    public ArrayList<String> SelectedServices = new ArrayList<>();

    private static String resourcesImagesDir = ConfigProperties.getTestProperty("Screen");
    public static String expectedDir = resourcesImagesDir + ConfigProperties.getTestProperty("expected");
    public static String actualDir = resourcesImagesDir + ConfigProperties.getTestProperty("actual");
    public static String diffDir = resourcesImagesDir + ConfigProperties.getTestProperty("diff");
    public static String failedTests = resourcesImagesDir + ConfigProperties.getTestProperty("failedtests");
    public static String ScreenshotAshot = "Ashot";


    public static String LoginAUT = "ioanner";
    public static String PasswordAUT = "12345678";


    // Body
    @FindBy(xpath = "  //html/body")
    public WebElement body;


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


//    //  Блок с вариантами количества показанных юзеров
//    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/ul[@class=\"el-scrollbar__view el-select-dropdown__list\"]")
//    public WebElement listSumWorker;


    //   Выпадающий пункт "100 на странице" в списке "Количество сотрудников на странице"
    @FindBy(css = "body > div.el-select-dropdown.el-popper > div.el-scrollbar > div.el-select-dropdown__wrap" +
            ".el-scrollbar__wrap > ul > li:nth-child(4)")
    public WebElement workers100;

    //   Список фамилий сотрудников
    @FindBy(xpath = "//tr/td[4]/div")
    public WebElement listSecondName;

    //   Выпадающий список Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//html//div[3]/div[2]/div[3]")
    public WebElement listActionProfile;

    //  Пункт ВЫЙТИ  в выпадающем списке Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//div[2]/div[3]//span[contains (text(), \"Выйти\")]")
    public WebElement listActionProfileExit;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(xpath = "//h2[contains (text(), \"Профиль обновлен\")]")
    public WebElement helperProfileChange;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(css = ".el-notification__closeBtn.el-icon-close")
    public WebElement helperProfileChangeClose;

    //Иконка для загрузки фото при создании и редактировании пользователя
    @FindBy(xpath = ".//*[@id='avatarEditorCanvas']/following-sibling::input")
    public WebElement uploadPhoto;

    //    Кнопка РЕДАКТИРОВАТЬ ПРОФИЛЬ
    @FindBy(xpath = "//div[2]/a/span[contains (text(), \"Редактировать профиль\")]")
    public WebElement buttonChangeProfile;

    //    Имя
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(2)")
    public WebElement firstNameProfile;

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

    //    Поле ввода "Структура организации"
    @FindBy(xpath = "//div[contains(text(), \"Структура организации\")]")
    public WebElement inputStructureOrganization;

    //   Буллет головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]/../label")
    public WebElement bulletHeadOrganization;

    //   Наименование головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский
    // Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]")
    public WebElement NameHeadOrganization;

    //   Наименование "Стоматологический центр"
    @FindBy(xpath = "//span[contains (text(), \"Стоматологический центр\")]")
    public WebElement NameDentalCenter;

    //   Наименование "Отделение терапевтической стоматологии"
    @FindBy(xpath = "//span[contains (text(), \"Отделение терапевтической стоматологии\")]")
    public WebElement NameDepartmentTherapeuticStomatology;

    //   Наименование "Технический отдел"
    @FindBy(xpath = "//span[contains (text(), \"Технический отдел\")]")
    public WebElement NameTechnicalDepartment;

    //   Буллет "Второе неврологическое отделение"
    @FindBy(xpath = "//span[contains (text(), \"Второе неврологическое отделение\")]/../label")
    public WebElement bulletSecondNeurologicalDepartment;

    //Выпадающий список "Условия оказания помощи" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Условия\"]")
    public WebElement selectOrganizationConditions;

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

    //  Поле ввода "Другие" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Другие\"]")
    public WebElement inputOthers;

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

    //------------------------------------------------Раздел "Место
    // работы"-------------------------------------------------
    //Выбранные должности
    @FindBy(xpath = "//div[@class=\"el-select create-user-form-workplaces__posts " +
            "showLabel\"]//span[@class=\"el-select__tags-text\"]")
    public List<WebElement> selectedPosition;

    //Cписок найденных Должностей
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item hover\"]")
    public List<WebElement> listFindWorkerPosition;

    //Выпадающий список "Роль сотрудника"
    @FindBy(css = ".el-input__inner[placeholder=\"Роль сотрудника\"]")
    public WebElement selectWorkerRole;

    //Пункт "Специалист" в выпадающем списке "Роль сотрудника"
    @FindBy(xpath = "(//span[contains (text(),\"Специалист\")])[2]")
    public WebElement selectWorkerRoleSpecialist;

    //Список выбранных ролей
    @FindBy(xpath = "//div[@class=\"el-select create-user-form-workplaces__roles " +
            "showLabel\"]//*[@class=\"el-select__tags-text\"]")
    public List<WebElement> selectedWorkerrole;

    //Выпадающий список "Специальности"
    @FindBy(xpath = "//div[contains (text(),\"Спец\")]/following-sibling::div//div[contains (text()," +
            "\"Специальности\")]")
    public WebElement selectWorkerSpecialty;

    //Пункт "Медицинские специальности" в  выпадающем списке "Специальноси"
    @FindBy(xpath = "//span[contains (text(),\"Медицинские специальности\")]/../label")
    public WebElement selectWorkerSpecialtyMed;

    //Выбранные специальности в выпадающем списке "Специальноси"
    @FindBy(xpath = "//div[@class=\"tree-select tree-select--muliselect\"]//div[@class=\"tree-select__checked-org\"]")
    public List<WebElement> selectedWorkerSpecialty;

    //Сохраненные специальности при редактировании
    @FindBy(xpath = "//div[contains (text(), \"Специальност\")]/following-sibling::div[1]//span")
    public List<WebElement> savedWorkerSpecialty;

    //Поле ввода "Укажите Должность"
    @FindBy(css = ".el-input__inner[placeholder=\"Выберите или впишите должность\"]")
    public List<WebElement> inputWorkerPosition;

    //Кнопка "Добавить" место работы
    @FindBy(xpath = "//div[contains(text(),\"Добавить место работы\")]/following-sibling::div/button/Span[contains " +
            "(text(), \"Добавить\")]")
    public WebElement buttonAddWorkplace;

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
    @FindBy(xpath = "//div[@style=\"padding-left: 48px;\"]//label/span")
    public List<WebElement> checkServices2;

    //Названия Услуг 3 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]//div[@class=\"services-tree__cell\"]/span")
    public List<WebElement> textServices3;

    //Строки Услуги 3 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]")
    public List<WebElement> stringTreeServices3;

    //Чек-боксы услуг 3 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]//label/span")
    public List<WebElement> checkServices3;

    //Артикулы услуг 3 уровня
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]//div[@class=\"services-tree__cell\"][2]")
    public List<WebElement> vendorServices3;

    //Стоимость услуг 3 уровня
    @FindBy(xpath = "//div[@class=\"services-tree__cell\"]/div[@class=\"serv-price\"]")
    public List<WebElement> costServices3;

    //Список чек-боксов выделенных услуг 3 уровня в дереве услуг
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]//label/span[@class=\"el-checkbox__input is-checked\"]")
    public List<WebElement> listStringSelectedCheckBoxInTree;

    //Список выделенных услуг 3 уровня в дереве услуг
    @FindBy(xpath = "//div[@style=\"padding-left: 72px;\"]//label/span[@class=\"el-checkbox__input is-checked\"]" +
            "/../../..//div[@class=\"el-tree-node__content\"]")
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
    @FindBy(xpath = "//h1[contains (text(), \"Редактировать профиль:\")]/a")
    public WebElement linkFIO;

    // ------------------------------------------------------------------------------------------------------------------
    //Карточка сотрудника
    //   Метка "Суперадминистратор"
    @FindBy(xpath = "//div/div[2]/div[1]/div[1]/div[1]/div[2]/span[contains (text(), \"Суперадминистратор\")]")
    public WebElement profLabelSuperadministrator;

    //   ФИО
    @FindBy(xpath = "(//*[@class=\"profile-name__name-text\"])[2]")
    public WebElement profFio;

    //   Метка "online"
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[1]/div[@class=\"online-status " +
            "online-status--online\"][contains (text(), \"online\")]")
    public WebElement profLabelOnline;

    //   Метка "offline"
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[1]/div[@class=\"online-status " +
            "online-status--offline\"][contains (text(), \"offline\")]")
    public WebElement profLabelOffline;

    //   Должность сотрудника
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[@class=\"profile-name__post\"]//span")
    public WebElement profPosition;

    //  Сохраненные специальности сотрудника
    @FindBy(xpath = "//div[@class=\"profile-block profile-name hidden-xs user-summary__item\"]//span[contains (text()" +
            ", \"Специальност\")]/following-sibling::div/span")
    public List<WebElement> listProfSpeciality;

    //   Список наименований мест работы
    @FindBy(xpath = "//div[@class=\"profile-block__row\"]/div[contains (text(), \"Место\")]/following-sibling::div")
    public List<WebElement> listProfNameWorkspace;

    //   Список должностей в местах работы
    @FindBy(xpath = "//div[contains (text(), \"Должность\")]/following-sibling::div")
    public List<WebElement> listProfPosition;

    //   Список ролей в местах работы
    @FindBy(xpath = "//div[contains (text(), \"Роль\")]/following-sibling::div//span")
    public List<WebElement> listProfRole;

    //   Список статусов в местах работы
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[2]/div/div[5]/div/div/button[1]/span")
    public List<WebElement> listProfStatus;

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
    @FindBy(xpath = "//div[contains (text(), \"Образование, регалии, достижения\")]/following-sibling::div")
    public WebElement profEducation;

    //   Список  услуг
    @FindBy(xpath = "//div[contains (text(), \"Услуги\")" +
            "]/following-sibling::div/div/div/div/div[@class=\"el-table__body-wrapper is-scrolling-left\"]//tr")
    public List<WebElement> listProfServices;

    // Профиль ID сотрудника
    @FindBy(xpath = "//div[contains (text(), \"ID сотрудника\")]/following-sibling::div")
    public WebElement profSystemID;

    // Профиль Статус сотрудника
    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/following-sibling::div")
    public WebElement profSystemStatus;

    // Профиль QR-code
    @FindBy(xpath = "//div[contains(text(),\"QR\")]/../div//img")
    public WebElement profQR;
    // ------------------------------------------------------------------------------------------------------------------
    //Карточка организации
    // Название организации
    @FindBy(xpath = "(//div[@class=\"department-name__name-text\"]/span)[2]")
    public WebElement nameProfOrg;

    // Сокращенное Название организации
//    @FindBy(xpath = "")
//    public WebElement nameAbbrProfOrg;

    // Входит в состав
    @FindBy(xpath = "(//span[contains (text(), \"Входит в состав\")]/following-sibling::div)[2]")
    public WebElement headOrganizationProfOrg;

    // Условия оказания помощи
//    @FindBy(xpath = "")
//    public WebElement termsProfOrg;

    // Основной профиль организации
//    @FindBy(xpath = "")
//    public WebElement mainStreamProfOrg;

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
    @FindBy(xpath = "//div[contains (text(), \"Описание\")]/following-sibling::div//span")
    public WebElement descriptionProfOrg;

    // Ссылка "Подробнее" в Описании организации
    @FindBy(xpath = "//div[contains (text(), \"Описание\")]/following-sibling::div//span[contains (text(), " +
            "\"Подробнее\")]")
    public WebElement linkDescriptionAdditionalProfOrg;

    // Полное Описание организации
    @FindBy(xpath = "//div[contains (text(), \"Описание\")]/following-sibling::div//" +
            "div[@class=\"dep-description__text\"]/span")
    public WebElement fullDescriptionProfOrg;

    // Ссылка "Скрыть" в полном Описании организации
    @FindBy(xpath = "//div[contains (text(), \"Описание\")]/following-sibling::div//span[contains (text(), " +
            "\"Скрыть\")]")
    public WebElement linkFullDescriptionHideProfOrg;

    // Заголовок "Структура"
    @FindBy(xpath = "//div[contains(text(),\"Структура\")]")
    public WebElement headerStructureProfOrg;

    // Список Названий организаций в структуре
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> listNameUnitStructureProfOrg;

    // Список ссылок "Подробнее" о организации в структуре
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"]/a/span[contains (text(), \"Подробнее\")]")
    public List<WebElement> listLinksAdditionalProfOrg;

    // Заголовок "Услуги"
    @FindBy(xpath = "//div[contains(text(),\"Услуги\")]")
    public WebElement headerServicesProfOrg;

    // Список названий услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[1]/div")
    public List<WebElement> listNamesServicesProfOrg;

    // Список артикулов услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[2]/div")
    public List<WebElement> listVendorsServicesProfOrg;

    // Список стоимостей услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[3]/div")
    public List<WebElement> listCostsServicesProfOrg;

    // Список Исполнителей услуг в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[4]/div")
    public List<WebElement> listExecutorsServicesProfOrg;

    // Список иконок "Действие" в списке услуг
    @FindBy(xpath = "//div[@class=\"services-list department\"]//tr[@class=\"el-table__row\"]/td[5]/div")
    public List<WebElement> listActionsServicesProfOrg;

    // Ссылка "Подробнее об услуге"
    @FindBy(xpath = "(//span[contains (text(), \"Подробнее об услуге\")])[last()]")
    public WebElement linkAdditionalServicesProfOrg;

    // Заголовок "Сотрудники"
    @FindBy(xpath = "//div[contains(text(),\"Сотрудники\")]")
    public WebElement headerExecutorsProfOrg;

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
//    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[7]/div")
//    public List<WebElement> listNamePhoneProfOrg;

    // Список номеров телефонов
//    @FindBy(xpath = "//div[@class=\"dep-staff department-summary__item\"]//tr[@class=\"el-table__row\"]/td[7]/div")
//    public List<WebElement> listNumbersPhoneProfOrg;

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
    @FindBy(xpath = "//div[contains (text(), \"QR код\")]/following-sibling::div//img")
    public WebElement qrProfOrg;


    public WebDriver driver;

    public void waitE_ClickableAndClick(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        iWait.until(ExpectedConditions.visibilityOf(element));
        sleep(800);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
        sleep(500);
    }

    public void waitE_ClickableAndSendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        iWait.until(ExpectedConditions.visibilityOf(element));
        sleep(800);
        element.clear();
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }

    public void waitE_ClickableSendKeysAndClick(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        element.clear();
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
        element.click();
    }


    public void waitE_visibilityOf(WebElement element) {
        sleep(1000);
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        iWait.until(ExpectedConditions.visibilityOf(element));
        sleep(500);
    }

    public void waitE_invisibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        try {

            iWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep(500);
    }

    public String waitE_visibilityOfAndGettext(WebElement element) {
        sleep(800);
        WebDriverWait iWait = new WebDriverWait(driver, 10);
        iWait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    public String waitE_TextPresent(WebElement element, String phrase) {
        sleep(800);
        WebDriverWait iWait = new WebDriverWait(driver, 10);
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
        File destination = new File(failedTests + subStr[0] + ".png");
        System.out.println(failedTests + subStr[0] + ".png");

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Скриншот не сохранился");
        }
    }


    public void scrollWithOffset(WebElement webElement, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        js.executeScript(code, webElement, x, y);
    }

    public void scrollUpPage() {
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_UP);
    }


    public void scrollDownPage() {
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_DOWN);
    }


    public void captureScreenshot(String Name) {
        screenFailedTest(Name);
    }

    public void moveToProfileUserChange(String SecondName) {
        try {
            waitE_ClickableAndClick(buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            waitE_ClickableAndClick(menuWorkers);
            waitE_ClickableAndClick(buttonUserList);
        }
        waitE_visibilityOf(listSecondName);
        waitE_ClickableAndClick(sumWorker);
//        waitE_visibilityOf(listSumWorker);
//        waitE_ClickableAndClick(listSumWorker);
        waitE_ClickableAndClick(workers100);
        WebElement findToSecondName =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[4" +
                        "]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
        waitE_ClickableAndClick(buttonChangeProfile);
        waitE_visibilityOf(firstNameProfile);
    }

    public void moveToProfileUser(String SecondName) {
        try {
            waitE_ClickableAndClick(buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            waitE_ClickableAndClick(menuWorkers);
            waitE_ClickableAndClick(buttonUserList);
        }
        waitE_visibilityOf(listSecondName);
        waitE_ClickableAndClick(sumWorker);
        waitE_ClickableAndClick(workers100);
        WebElement findToSecondName =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[4" +
                        "]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
    }

    public void moveToProfileOrg(String NameOrganization) {
        try {
            waitE_ClickableAndClick(buttonOrganizationsList);
        } catch (TimeoutException e) {
            System.out.println(e);
            waitE_ClickableAndClick(menuOrganizations);
            waitE_ClickableAndClick(buttonOrganizationsList);
        }
        driver.navigate().refresh();
        waitE_visibilityOf(listNameOrganizations);
        WebElement findToNameOrganization =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1" +
                        "][@class=\"dep-tree__cell\"][contains (text(), \"" + NameOrganization + "\")]/../div/a/span" +
                        "[contains (text(),\"Подробнее\")]")));
        scrollWithOffset(findToNameOrganization, 0, 30);
        waitE_ClickableAndClick(findToNameOrganization);
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

    public void compareStringAndWebelement(String Expected, WebElement Actual) {
        System.out.println(Expected + " = " + Actual.getText());
        Assert.assertEquals(Expected, Actual.getText());
    }

    public void compareStringAndString(String Expected, String Actual) {
        System.out.println(Expected + " = " + Actual);
        Assert.assertEquals(Expected, Actual);
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

    public static String nowDate() {

        String months[] = {"янв.", "фев.", "мар.", "апр.", "май", "июнь", "июль", "авг.", "сент.",
                "окт.", "нояб.", "дек."};

        String NowDate = "";
        GregorianCalendar gcalendar = new GregorianCalendar();

        if (gcalendar.get(Calendar.DATE) < 10) {
            if (gcalendar.get(Calendar.MINUTE) < 10) {
                NowDate =
                        "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            } else {
                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
                    NowDate =
                            "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " 0" +
                                    gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                    System.out.println("Искомая дата:" + NowDate);
                } else {
                    NowDate =
                            "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                    gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                    System.out.println("Искомая дата:" + NowDate);
                }
            }
        }
        if (gcalendar.get(Calendar.DATE) >= 10) {
            if (gcalendar.get(Calendar.MINUTE) < 10) {
                NowDate =
                        gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            } else {
                if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
                    NowDate =
                            "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " 0" +
                                    gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                    System.out.println("Искомая дата:" + NowDate);
                } else {
                    NowDate =
                            "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                    gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                    System.out.println("Искомая дата:" + NowDate);
                }
            }
        }

        return NowDate;
    }

    public String todayDate() {

        String months[] = {"янв.", "фев.", "мар.", "апр.", "май", "июн.", "июл.", "авг.", "сент.",
                "окт.", "нояб.", "дек."};

        String NowDate = "";
        GregorianCalendar gcalendar = new GregorianCalendar();

        if (gcalendar.get(Calendar.DATE) < 10) {
            NowDate =
                    "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
        } else {
            if (gcalendar.get(Calendar.HOUR_OF_DAY) < 10) {
                NowDate =
                        "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " 0" +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            }
            NowDate =
                    gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
        }
        return NowDate;
    }
}