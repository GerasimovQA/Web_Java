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

//    int u = 0;

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


//
//
//    //    Фамилия
//    @FindBy(css = ".edit-user-block__full-name > span:nth-child(1)")
//    public WebElement secondNameProfile;
//
//
//    //    Отчество
//    @FindBy(css = ".edit-user-block__full-name > span:nth-child(3)")
//    public WebElement middleNameProfile;
//
//    //    Ссылка редактирования ФИО
//    @FindBy(xpath = "//div[contains (text(), \"ФИО\")]/following-sibling::div[2]")
//    public WebElement linkEditfioProfile;
//
//    //    Поле ввода ФАМИЛИЯ
//    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
//    public WebElement inputSecondName;
//
//    //    Поле ввода ИМЯ
//    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
//    public WebElement inputFirstName;
//
//    //    Поле ввода Отчество
//    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
//    public WebElement inputMiddleName;
//
//
//    //    Кнопка СОХРАНИТЬ ФИО юзера
//    @FindBy(xpath = "//div[3]//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveProfileFIO;
////-----------------------------------------------------------------------------
//
//
//    //    Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
//    public WebElement emailProfile;
//
//    //    Phone
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
//    public WebElement phoneProfile;
//
//
//    //--------------------------------------------------------------------------------
//    //    Ссылка изменения пароля
//    @FindBy(xpath = "//div[contains (text(), \"Пароль\")]/following-sibling::div")
//    public WebElement linkEditPasswordProfile;
//
//    //    Поле ввода ПАРОЛЬ
//    @FindBy(css = ".el-input__inner[placeholder=\"Новый пароль\"]")
//    public WebElement inputNewPassword;
//
//    //    Поле ввода ПОВТОРИТЕ ПАРОЛЬ
//    @FindBy(css = ".el-input__inner[placeholder=\"Повторите пароль\"]")
//    public WebElement inputRepeatNewPassword;
//
//    //   Иконка "Глаз" - скрыть пароль
//    @FindBy(css = ".Password__toggle--show-password.fa.fa-eye-slash")
//    public WebElement notShowPassword;
//
//    //    Кнопка СОХРАНИТЬ ПАРОЛЬ
//    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSavePassword;
//
//    //    Кнопка ОТМЕНА ПАРОЛЯ
//    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelPassword;
//
//
//    //----------------------------------------------------------------------------------
//    //    Текущий статус
//    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/..//following-sibling::div/span[contains (text(), \"к\")]")
//    public WebElement statusProfile;
//
//    //    Ссылка изменения статуса
//    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/following-sibling::div[2]")
//    public WebElement linkEditStatusProfile;
//
//    //    Выпадающий список СТАТУС
//    @FindBy(css = ".el-input__inner[placeholder=\"Статус\"]")
//    public WebElement listStatusInUserList;
//
//    //    Пункт АКТИВЕН в выпадающем списке СТАТУС
//    @FindBy(xpath = "//li/span[contains (text(), \"Активен\")]")
//    public WebElement activeStatus;
//
//    //    Пункт ОТКЛЮЧЕН в выпадающем списке СТАТУС
//    @FindBy(xpath = "//li/span[contains (text(), \"Отключен\")]")
//    public WebElement disbleStatus;
//
//    //    Кнопка СОХРАНИТЬ СТАТУС
//    @FindBy(xpath = "//div[4]//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveStatus;
//
//    //    Кнопка ОТМЕНА СТАТУСА
//    @FindBy(xpath = "//div[4]//span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelStatus;
//
//
////-----------------------------------------------------------------------------------------------------------
//// Место работы и специальность
//
//    //    Ссылка "изменить" место работы
//    @FindBy(xpath = "//div[@class=\"edit-workplace__link-container\"]/div[contains (text(), \"Изменить\")]")
//    public WebElement linkEditWorkplaces;
//
//    //    Ссылка "добавить" место работы
//    @FindBy(xpath = "//span[contains (text(), \"Добавить место работы\")]")
//    public WebElement linkAddWorkplaces;
//
//    //    Выпадающий список "Структура организации"
//    @FindBy(xpath = "//div[contains (text(), \"Структура организации\")]")
//    public WebElement structureOrganizations;
//
//    //   Пункт головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
//    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
//            "Университет)\")]")
//    public WebElement headOrganization;
//
//    //       Буллет головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский
//    // Университет)"
//    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
//            "Университет)\")]/../label/span/span")
//    public WebElement bulletHeadOrganization;
//
//    //   Поле ввода "Должность"
//    @FindBy(xpath = "//input[@placeholder=\"Должность\"]")
//    public WebElement inputPosition;
//
//    //   Поле ввода "Роль сотрудника"
//    @FindBy(xpath = "//input[@placeholder=\"Роль сотрудника\"]")
//    public WebElement inputRoleEmployee;
//
//    //   Пункт "Специалист" в поле ввода "Роль сотрудника"
//    @FindBy(xpath = "//span[text() = \"Специалист\"]")
//    public WebElement roleEmployeeSpecialist;
//
//    //   Кнопка "Добавить" место работы сотрудника
//    @FindBy(xpath = "//div[contains (text(), \"Добавить место работы\")]/following-sibling::div/button/span[contains " +
//            "(text(), \"Добавить\")]")
//    public WebElement buttonAddWorkplace;
//
//    //   Кнопка "Сохранить" место работы сотрудника
//    @FindBy(xpath = "//div[@class=\"edit-workplace__buttons-container\"]//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveWorkplace;
//
//    //   Сохраненное значение "Место работы"
//    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Место\")" +
//            "]/following-sibling::div")
//    public WebElement workplace;
//
//    //   Сохраненное значение "Должность"
//    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Должность\")" +
//            "]/following-sibling::div")
//    public WebElement post;
//
//    //   Сохраненное значение "Роль"
//    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Роль\")" +
//            "]/following-sibling::div")
//    public WebElement role;
//
//    //    Ссылка "изменить" специальность
//    @FindBy(xpath = "//div[contains (text(), \"Специальности\")]/following-sibling::div[2]")
//    public WebElement linkEditSpecialty;
//
//    //   Выпадающий список "Специальность"
//    @FindBy(xpath = "//div[contains (text(), \"Специальности\")][@class=\"tree-select__placeholder\"]")
//    public WebElement listNameSpecialty;
//
//    //  Пункт "Медицинские специальности" в выпадающем списке "Специальности"
//    @FindBy(xpath = "//span[contains (text(), \"Медицинские специальности\")]")
//    public WebElement medicalSpecialty;
//
//    //  Чек-бокс "Стоматолог" в выпадающем списке "Специальность"
//    @FindBy(xpath = "//span[contains (text(),\"Стоматолог\")]/../label")
//    public WebElement specialtyDentist;
//
//    //   Кнопка "Сохранить" место работы сотрудника
//    @FindBy(xpath = "//div[@class=\"edit-specialities__item\"]/following-sibling::button/span[contains (text(), " +
//            "\"Сохранить\")]")
//    public WebElement buttonSaveSpecialty;
//
//    //   Сохраненное значение "Специальности"
//    @FindBy(xpath = "//div[@class=\"edit-user-block__title\"][contains (text(), \"Специальности\")" +
//            "]/following-sibling::div/div[@class=\"specialty-in-row\"]/span")
//    public List<WebElement> specialty;
//
//    //   Кнопка "Сохранить" специальности при редактировании
//    @FindBy(xpath = "//div[@class=\"tree-select__input el-popover__reference\"]/../../../../button/span[contains " +
//            "(text(),\"Сохранить\")]")
//    public WebElement linkSaveSpeciality;
////-----------------------------------------------------------------------------------------------------------
//// Данные специалиста
//
//
//    //    ФОТО юзера
//    @FindBy(xpath = "//div[contains (text(), \"Фото\")]/following-sibling::div[1]//img")
//    public WebElement photoProfile;
//
//    //    Ссылка ИЗМЕНИТЬ ФОТО
//    @FindBy(xpath = "//div[@class='profile-userpic__photo userpic userpic--medium']/." +
//            ".//following-sibling::div/div[contains (text(), \"Изменить\")]")
//    public WebElement linkEditPhotoProfile;
//
//    //    Кнопка СОХРАНИТЬ ФОТО
//    @FindBy(xpath = "//div[2]/div/div/button/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSavePhotoProfile;
//
//    //    Кнопка ОТМЕНИТЬ СОХРАНИЕ ФОТО
//    @FindBy(xpath = "//div[contains (text(), \"Отмена\")]")
//    public WebElement buttonSavePhotoCancelProfile;
//
//    //    Образование, достижения, регалии
//    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[1]")
//    public WebElement educationProfile;
//
//    //    Ссылка ИЗМЕНИТЬ Образование, достижения, регалии
//    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[2]")
//    public WebElement linkEditEducationProfile;
//
//    //    Поле ввода Образование, достижения, регалии
//    @FindBy(xpath = "//*[@class=\"el-textarea__inner\"]")
//    public WebElement inputEducation;
//
//    //    Кнопка СОХРАНИТЬ ОБРАЗОВАНИЕ
//    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveEducation;
//
//    //    Кнопка ОТМЕНА СОХРАНЕНИЯ ОБРАЗОВАНИЯ
//    @FindBy(xpath = "//div[contains(text(),\"Образование, достижения, регалии\")" +
//            "]/following-sibling::div//span[contains(text(),\"Отмена\")]")
//    public WebElement buttonCancelEducation;
//
//    //    Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
//    public WebElement emailContactProfile;
//
//    //    Поле ввода Email
//    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Email\"]")
//    public WebElement inputEmailContactProfile;
//
//    //    Телефон
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
//    public WebElement phoneContactProfile;
//
//    //    Поле ввода Телефон
//    @FindBy(css = "div:nth-child(2) > input[placeholder=\"Телефон\"]")
//    public WebElement inputPhoneContactProfile;
//
//    //    Instagram
//    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
//    public WebElement instagramContactProfile;
//
//    //    Поле ввода Instagram
//    @FindBy(css = "div:nth-child(3) > input[placeholder=\"Instagram\"]")
//    public WebElement inputInstagramContactProfile;
//
//    //    Vk
//    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
//    public WebElement vkContactProfile;
//
//    //    Поле ввода Vk
//    @FindBy(css = "div:nth-child(4) > input[placeholder=\"Vk\"]")
//    public WebElement inputVkContactProfile;
//
//    //    Whatsapp
//    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
//    public WebElement whatsappContactProfile;
//
//    //    Поле ввода Whatsapp
//    @FindBy(css = "div:nth-child(5) > input[placeholder=\"Whatsapp\"]")
//    public WebElement inputWhatsappContactProfile;
//
//    //    Viber
//    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
//    public WebElement viberContactProfile;
//
//    //    Поле ввода Viber
//    @FindBy(css = "div:nth-child(6) > input[placeholder=\"Viber\"]")
//    public WebElement inputViberContactProfile;
//
//    //    Facebook
//    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
//    public WebElement facebookContactProfile;
//
//    //    Поле ввода Facebook
//    @FindBy(css = "div:nth-child(7) > input[placeholder=\"Facebook\"]")
//    public WebElement inputFacebookContactProfile;
//
//    //    Другое
//    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
//    public WebElement otherContactProfile;
//
//    //    Поле ввода Другое
//    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Другое...\"]")
//    public WebElement inputOtherContactProfile;
//
//    //    Ссылка ИЗМЕНИТЬ Способы связи
//    @FindBy(xpath = "//div[contains (text(), \"Способы связи\")]/following-sibling::div[contains (text(), " +
//            "\"Изменить\")]")
//    public WebElement linkEditCommunicationMethodsProfile;
//
//    //    Кнопка СОХРАНИТЬ Способы связи
//    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveCommunicationMethodsProfile;
//
//    //    Кнопка ОТМЕНИТЬ СОХРАНЕНИЕ Способов связи
//    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelCommunicationMethodsProfile;
//
//    public void editServices(String Login, String Password, String Email, String Phone, String Status,
//                             String SecondName, String FirstName, String MiddleName, String Superuser,
//                             String Depart,
//                             String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
//                             String Whatsapp, String Viber, String Facebook, String Other,
//                             String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
//        int i = -1;
//        sleep(4000);
//
//        switch (SecondName) {
//            case "Кардашьян":
//                click(textServices1.get(0));
//                sleep(1000);
//                click(textServices2.get(0));
//                sleep(2000);
//                for (WebElement Service : checkServices3) {
//                    i = i + 1;
//                    if (i == 0) {
//                        click(checkServices3.get(i));
//                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//
//                        waitVisibility(listCostSelectServicesAddUser.get(i));
//                        System.out.println(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
//                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
//                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
//                        Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
//                    } else {
//                        try {
//                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
//                                    " = " + Empty);
//                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);
//
//                            System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
//                                    ", " + listCostSelectServicesAddUser.get(i).getText());
//                            Assert.assertEquals("", textServices3.get(i).getText());
//                        } catch (IndexOutOfBoundsException e) {
//                            System.out.println("Невыделенные услуги не найдены");
//                        }
//                    }
//                }
//                for (WebElement Str : listStringSelectServicesAddUser) {
//                    SelectedServices.add(Str.getText().replace("\n", " "));
//                }
//
//                System.out.println("Icon DELETE is enabled and size = 1");
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() & listIconsDeleteSelectServicesAddUser.size() == 1);
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
//                break;
//
//            case "Киров":
//                click(textServices1.get(0));
//                sleep(1000);
//                click(textServices2.get(0));
//                sleep(2000);
//                for (WebElement Service : stringTreeServices3) {
//                    i = i + 1;
//                    click(checkServices3.get(i));
//                    for (WebElement SelectService : listStringSelectServicesAddUser) {
//                        u = 0;
//                        System.out.println("Service №" + i);
//
//                        System.out.println(Service.getText().replace("\n", " ") + " = " +
//                                SelectService.getText().replace("\n", " "));
//                        if (Service.getText().replace("\n", " ").equals(
//                                SelectService.getText().replace("\n", " "))) {
//                            u = u + 1;
//                            System.out.println("!+Service find in list selected services+!");
//                            break;
//                        } else {System.out.println("Услуги не совпали");                       }
//                    }
//                    click(userinfoname);
//                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds);
//                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Checkeds);
//                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
//                }
//                for (WebElement Str : listStringSelectServicesAddUser) {
//                    SelectedServices.add(Str.getText().replace("\n", " "));
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + textServices3.size() + " = " + checkServices3.size());
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == textServices3.size() &
//                        textServices3.size() == checkServices3.size());
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
//                click(userinfoname);
//                sleep(1000);
//                System.out.println((listStringSelectedServicesInTree.get(i).getText().replace("\n", " ")));
//                System.out.println("Unselect last service");
//                SelectedServices.remove(listStringSelectedServicesInTree.get(i).getText().replace("\n", " "));
//                click(checkServices3.get(i));
//                click(userinfoname);
//                try {
//                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
//                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
//                            "class") + " = " + Empty);
//                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);
//
//                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
//                            listCostSelectServicesAddUser.get(i).getText());
//                    Assert.assertEquals("", textServices3.get(i).getText());
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Невыделенные услуги не найдены");
//                }
//
//                for (WebElement Service : listStringSelectedServicesInTree) {
//                    i = -1;
//                    i = i + 1;
//                    System.out.println("Выделенные услуги в дереве" + listStringSelectedServicesInTree.size());
//                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
//                        u = 0;
//                        System.out.println("Service №" + i);
//                        System.out.println("Повтороно сравниваем услуги: " + textServices3.get(i).getText() + " " +
//                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
//                                + " " + listCostSelectServicesAddUser.get(x).getText());
//                        if ((textServices3.get(i).getText() + " " +
//                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
//                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
//                            u = u + 1;
//                            System.out.println("!+Service find in list selected services+!");
//                            break;
//                        } else {System.out.println("Услуги не совпали");}
//                    }
//                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
//                        (textServices3.size() - 1) == (checkServices3.size() - 1));
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println("ListString= " + SelectedServices);
//                break;
//
//            case "Ковалев":
//                click(textServices1.get(0));
//                sleep(1000);
//                click(textServices2.get(0));
//                sleep(2000);
//                for (WebElement Service : stringTreeServices3) {
//                    i = i + 1;
//                    click(checkServices3.get(i));
//                    for (WebElement SelectService : listStringSelectServicesAddUser) {
//                        u = 0;
//                        System.out.println("Service №" + i);
//
//                        System.out.println(Service.getText().replace("\n", " ") + " = " +
//                                SelectService.getText().replace("\n", " "));
//                        if (Service.getText().replace("\n", " ").equals(
//                                SelectService.getText().replace("\n", " "))) {
//                            u = u + 1;
//                            System.out.println("!+Service find in list selected services+!");
//                            break;
//                        } else {System.out.println("Услуги не совпали");}
//                    }
//                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
//                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
//                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
//                }
//                for (WebElement Str : listStringSelectServicesAddUser) {
//                    SelectedServices.add(Str.getText().replace("\n", " "));
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + textServices3.size() + " = " + checkServices3.size());
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == textServices3.size() &
//                        textServices3.size() == checkServices3.size());
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
//                click(userinfoname);
//                click(userinfoname);
//                sleep(1000);
//                System.out.println(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
//                System.out.println("Delete one service");
//                SelectedServices.remove(stringServiceDeletedInListServices.get(i).getText().replace("\n", " "));
//                String DeletedService = nameServiceDeletedInListServices.get(i).getText();
//                System.out.println(DeletedService);
//                waitE_ClickableAndClickAndUp(listIconsDeleteSelectServicesAddUser.get(i));
//                sleep(1000);
//                try {
//                    System.out.println("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span");
//                    System.out.println("Сняли выделение с: " + DeletedService);
//                    System.out.println("Check-box " + i + " = " + driver.findElement(By.xpath
//                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
//                            .getAttribute("class") + " = " + Empty);
//                    Assert.assertEquals(driver.findElement(By.xpath
//                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
//                            .getAttribute("class"), Empty);
//
//                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
//                            listCostSelectServicesAddUser.get(i).getText());
//                    Assert.assertEquals("", textServices3.get(i).getText());
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Невыделенные услуги не найдены");
//                }
//
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
//                        (textServices3.size() - 1) == (checkServices3.size() - 1));
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println("Delete all service");
//                try {
//                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
//                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
//                        System.out.println(SelectedServices);
//                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
//                        click(listSelectedCheckBoxInTree.get(b));
//                        sleep(1000);
//                    }
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Удалили все услуги в списке");
//                }
//
//                click(checkServices3.get(0));
//                SelectedServices.add(listStringSelectServicesAddUser.get(0).getText().replace("\n", " "));
//
//
//                for (WebElement Service : listStringSelectedServicesInTree) {
//                    i = -1;
//                    i = i + 1;
//                    System.out.println("Выделенные услуги в дереве: " + listStringSelectedServicesInTree.size());
//                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
//                        u = 0;
//                        System.out.println("Service №" + i);
//                        System.out.println("Повторно сравниваем услуги: " + textServices3.get(i).getText() + " " +
//                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
//                                + " " + listCostSelectServicesAddUser.get(x).getText());
//                        if ((textServices3.get(i).getText() + " " +
//                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
//                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
//                            u = u + 1;
//                            System.out.println("!+Service find in list selected services+!");
//                            break;
//                        } else {System.out.println("Услуги не совпали");}
//                    }
//                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
//                System.out.println(textServices3.size() + " = " + checkServices3.size());
//                Assert.assertEquals(1, listIconsDeleteSelectServicesAddUser.size());
//                Assert.assertEquals(1, listNameSelectServicesAddUser.size());
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Empty);
//                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Empty);
//                break;
//
//            case "Кондртьев":
//                click(textServices1.get(0));
//                sleep(1000);
//                click(textServices2.get(0));
//                click(textServices2.get(1));
//                sleep(2000);
//                for (WebElement Service : stringTreeServices3) {
//                    i = i + 1;
//                    click(checkServices3.get(i));
//                    for (WebElement SelectService : listStringSelectServicesAddUser) {
//                        u = 0;
//                        System.out.println("Service №" + i);
//
//                        System.out.println(Service.getText().replace("\n", " ") + " = " +
//                                SelectService.getText().replace("\n", " "));
//                        if (Service.getText().replace("\n", " ").equals(
//                                SelectService.getText().replace("\n", " "))) {
//                            u = u + 1;
//                            System.out.println("!+Service find in list selected services+!");
//                            break;
//                        } else {System.out.println("Услуги не совпали");}
//                    }
//                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
//                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
//                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
//                }
//                for (WebElement Str : listStringSelectServicesAddUser) {
//                    SelectedServices.add(Str.getText().replace("\n", " "));
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + textServices3.size() + " = " + checkServices3.size());
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == textServices3.size() &
//                        textServices3.size() == checkServices3.size());
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
//
//                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds);
//                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Checkeds);
//                click(userinfoname);
//                click(userinfoname);
//                sleep(1000);
//
//                System.out.println("Delete one service");
//                SelectedServices.remove(listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
//                System.out.println(DeletedService2);
//                waitE_ClickableAndClickAndUp(listIconsDeleteSelectServicesAddUser.get(i));
//                sleep(1000);
//                try {
//                    System.out.println("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span");
//                    System.out.println("Сняли выделение с: " + DeletedService2);
//                    System.out.println("Check-box " + i + " = " + driver.findElement(By.xpath
//                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
//                            .getAttribute("class") + " = " + Empty);
//                    Assert.assertEquals(driver.findElement(By.xpath
//                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
//                            .getAttribute("class"), Empty);
//
//                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
//                            listCostSelectServicesAddUser.get(i).getText());
//                    Assert.assertEquals("", textServices3.get(i).getText());
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Невыделенные услуги не найдены");
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
//                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
//                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
//                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
//                        (textServices3.size() - 1) == (checkServices3.size() - 1));
//                click(userinfoname);
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
//
//                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
//
//                System.out.println("Delete all service");
//                try {
//                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
//                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
//                        System.out.println(SelectedServices);
//                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
//                        waitE_ClickableAndClickAndUp(listSelectedCheckBoxInTree.get(b));
//                        sleep(1000);
//                    }
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Удалили все услуги в списке");
//                }
//
//                click(checkServices3.get(5));
//                click(userinfoname);
//                sleep(1000);
//                SelectedServices.add(listStringSelectedServicesInTree.get(0).getText().replace("\n", " "));
//
//                for (WebElement SelectService : listStringSelectServicesAddUser) {
//                    i = 0;
//                    u = 0;
//                    System.out.println("Service №" + i);
//
//                    System.out.println(listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ") + " = " +
//                            SelectService.getText().replace("\n", " "));
//                    if (listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ").equals(
//                            SelectService.getText().replace("\n", " "))) {
//                        u = u + 1;
//                        System.out.println("!+Service find in list selected services+!");
//                        break;
//                    } else {System.out.println("Услуги не совпали");}
//                }
//
//                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
//                System.out.println(textServices3.size() + " = " + checkServices3.size());
//                Assert.assertEquals(1, listIconsDeleteSelectServicesAddUser.size());
//                Assert.assertEquals(1, listNameSelectServicesAddUser.size());
//
//                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);
//
//                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
//                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);
//
//                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
//                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
//                break;
//
//            case "Курочкин":
//                click(textServices1.get(0));
//                sleep(1000);
//                click(textServices2.get(0));
//                click(textServices2.get(1));
//                sleep(2000);
//                for (WebElement Service : checkServices3) {
//                    i = i + 1;
//                    if (i == 0) {
//                        click(Service);
//                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//
//                        waitVisibility(listCostSelectServicesAddUser.get(i));
//                        System.out.println(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
//                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
//                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
//                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
//                        Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
//                    } else {
//                        try {
//                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
//                                    " = " + Empty);
//                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);
//
//                            System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
//                                    ", " + listCostSelectServicesAddUser.get(i).getText());
//                            Assert.assertEquals("", textServices3.get(i).getText());
//                        } catch (IndexOutOfBoundsException e) {
//                            System.out.println("Невыделенные услуги не найдены");
//                        }
//                    }
//                }
//                for (WebElement Str : listStringSelectServicesAddUser) {
//                    SelectedServices.add(Str.getText().replace("\n", " "));
//                }
//        }
//
//        click(buttonSaveChangesProfile);
//        waitE_ClickableAndClickAndUp(linkFIO);
//
//        waitVisibility(profQR);
//        waitVisibility(profFio);
//         waitTextPresent(profWhatsapp, Whatsapp);
//        sleep(1000);
//        String Fio = SecondName + " " + FirstName + " " + MiddleName;
//        System.out.println(Fio + " = " + profFio.getText());
//        if (MiddleName.equals("")) {
//            Assert.assertEquals(Fio, profFio.getText() + " ");
//        } else {
//            Assert.assertEquals(Fio, profFio.getText());
//        }
//
//        System.out.println(Regalia + " = " + profEducation.getText());
//        Assert.assertEquals(Regalia, profEducation.getText());
//        sleep(1000);
//        ArrayList<String> ListProfServices = new ArrayList<>();
//        for (
//                WebElement Service : listProfServices) {
//            ListProfServices.add(Service.getText().replace("\n", " "));
//        }
//
//        System.out.println("Сравниваем услуги :" + SelectedServices.equals(ListProfServices));
//        Assert.assertEquals(SelectedServices, ListProfServices);
//
//       compareStringAndWebelement(EmailCont, profEmail);
////        System.out.println(EmailCont + " = " + profEmail.getText());
////        Assert.assertEquals(EmailCont, profEmail.getText());
//
//        compareStringAndWebelement(PhoneCont, profContPhone);
////        System.out.println(PhoneCont + " = " + profContPhone.getText());
////        Assert.assertEquals(profContPhone.getText(), profContPhone.getText());
//
//        compareStringAndWebelement(Instagram, profInstagram);
////        Assert.assertEquals(Instagram, profInstagram.getText());
////        System.out.println(Instagram + " = " + profInstagram.getText());
//
//        compareStringAndWebelement(Vk, profVk);
////        Assert.assertEquals(Vk, profVk.getText());
////        System.out.println(Vk + " = " + profVk.getText());
//
//        compareStringAndWebelement(Whatsapp, profWhatsapp);
////        Assert.assertEquals(Whatsapp, profWhatsapp.getText());
////        System.out.println(Whatsapp + " = " + profWhatsapp.getText());
//
//        compareStringAndWebelement(Viber, profViber);
////        Assert.assertEquals(Viber, profViber.getText());
////        System.out.println(Viber + " = " + profViber.getText());
//
//        compareStringAndWebelement(Facebook, profFacebook);
////        Assert.assertEquals(Facebook, profFacebook.getText());
////        System.out.println(Facebook + " = " + profFacebook.getText());
//
//        compareStringAndWebelement(Other, profOther);
////        Assert.assertEquals(Other, profOther.getText());
////        System.out.println(Other + " = " + profOther.getText());
//
//        compareStringAndWebelement(Login, profAccLogin);
////        System.out.println(Login + " = " + profAccLogin.getText());
////        Assert.assertEquals(Login, profAccLogin.getText());
//
//        compareStringAndWebelement(Email, profAccEmail);
////        System.out.println(Email + " = " + profAccEmail.getText());
////        Assert.assertEquals(Email, profAccEmail.getText());
//
//        compareStringAndWebelement(Phone, profAccPhone);
////        System.out.println(Phone + " = " + profAccPhone.getText());
////        Assert.assertEquals(Phone, profAccPhone.getText());
//
//        Assert.assertTrue(profSystemID.isEnabled());
//        Assert.assertTrue(profSystemStatus.isEnabled());
//        Assert.assertTrue(profQR.isEnabled());
//    }


}



