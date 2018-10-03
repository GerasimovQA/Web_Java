package createuser;

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

import java.util.ArrayList;
import java.util.List;

public class CreateUserPage extends GlobalPage {

    public CreateUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private ArrayList<String> ListServices = new ArrayList<>();


    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
    //   Кнопка добавления сотрудника с проверкой на текст "Добавить сотрудника"
    @FindBy(xpath = "//Span[contains (text(), \"Добавить сотрудника\")]")
    public WebElement buttonAddUser;

    //   Кнопка Назад
    @FindBy(xpath = "//Span[contains (text(), \"Назад\")]")
    public WebElement buttonBack;

    //   Кнопка Поиск сотрудника
    @FindBy(xpath = "//Span[contains (text(), \"Поиск сотрудника\")]")
    public WebElement buttonSearchWorker;

    //   Кнопка Поиск
    @FindBy(xpath = "//Span[text() = \"Поиск\"]")
    public WebElement buttonSearch;


//-----------------------------------------------------
//Профиль юзера

    //   Метка "Суперадминистратор"
    @FindBy(xpath = "//div/div[2]/div[1]/div[1]/div[1]/div[2]/span[contains (text(), \"Суперадминистратор\")]")
    public WebElement labelSuperadministrator;

    //   ФИО
    @FindBy(xpath = ".//*[@class='profile-block profile-name hidden-xs']//*[@class='profile-name__name-text']")
    public WebElement fio;

    //  Телефон для входа в аккаунт
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement profAccPhone;

    // контакт Телефон
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
    public WebElement profContPhone;

    // Профиль Образование, регалии, достижения
    @FindBy(xpath = "//div[contains (text(), \"Образование, регалии, достижения\")]/following-sibling::div")
    public WebElement profEducation;

    // Профиль Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
    public WebElement profEmail;

    // Профиль Whatsapp
    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
    public WebElement profWhatsapp;

    // Профиль Viber
    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
    public WebElement profViber;

    // Профиль Facebook
    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
    public WebElement profFacebook;

    // Профиль Other
    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
    public WebElement profOther;

//-----------------------------------------------------

    //ШАГ №1
    //    Поле ввода логина с проверкой на текст "Логин"
    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
    public WebElement inputlogin;

    //    Поле ввода Email с проверкой на текст "Email"
    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
    public WebElement inputEmail;

    //    Поле ввода Телефон с проверкой на текст "Телефон"
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
    public WebElement inputPhone;

    //    Поле ввода Пароль с проверкой на текст "Пароль"
    @FindBy(css = ".el-input__inner[placeholder=\"Пароль\"]")
    public WebElement inputPassword;

    //    Поле ввода Фамилия с проверкой на текст "Фамилия"
    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
    public WebElement inputSecondName;

    //    Поле ввода Имя с проверкой на текст "Имя"
    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
    public WebElement inputFirstName;

    //    Поле ввода Отчество с проверкой на текст "Отчество"
    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
    public WebElement inputMiddleName;

    //    Ссылка автоматической генерации пароля, содержащей текст "Автоматически сгенерировать пароль"
    @FindBy(xpath = "//div[contains (text(), \"Автоматически сгенерировать пароль\")]")
    public WebElement generatePassword;

    //   Иконка "Глаз" - показать пароль
    @FindBy(css = ".create-user-form-show-password.fa.fa-eye-slash")
    public WebElement showPassword;

    //   Иконка "Глаз" - скрыть пароль
    @FindBy(css = ".create-user-form-show-password.fa.fa-eye")
    public WebElement notShowPassword;

    //    Переключатель Суперадмин
    @FindBy(css = ".el-switch__core")
    public WebElement switchSuperadmin;

    //    Чек-бокс "Отправить данные для входа, на email сотрудника"
    @FindBy(css = ".el-checkbox__inner")
    public WebElement checkboxInfoToEmail;


    //   Проверка успешного перехода на ШАГ №2 фразой содержащей текст "Место работы"
    @FindBy(xpath = "//div[contains (text(), \"Место работы\")]")
    public WebElement successSecondStep;

    //-------------------------------------------------------

    //ШАГ 2 - Специальность и место работы
    //Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(),\"Структура организации\")]")
    public WebElement selectStructureOrganization;

    //Головная организация в выпадающем списке "Структура организации"
    @FindBy(xpath = "(//*[@class=\"el-popover el-popper\"])" +
            "[2]/div[1]/div[2]/div[1]/div/label/span[@class=\"el-checkbox__input\"]")
    public WebElement selectMainOrganization;

    //Поле ввода "Должность"
    @FindBy(css = ".el-input__inner[placeholder=\"Должность\"]")
    public WebElement inputWorkerPosition;

    //Выпадающий список "Роль сотрудника"
    @FindBy(css = ".el-input__inner[placeholder=\"Роль сотрудника\"]")
    public WebElement selectWorkerRole;

    //Пункт "Специалист" в выпадающем списке "Роль сотрудника"
    @FindBy(xpath = "//span[contains (text(),\"Специалист\")]")
    public WebElement selectWorkerRoleSpecialist;

    //Выпадающий список "Специальности"
    @FindBy(xpath = "//div[contains (text(),\"Специальности\")]")
    public WebElement selectWorkerSpecialty;

    //Пункт "Медицинские специальности" в  выпадающем списке "Специальноси"
    @FindBy(xpath = "//span[contains (text(),\"Медицинские специальности\")]/../label")
    public WebElement selectWorkerSpecialtyMed;

    //   Проверка успешного перехода на ШАГ №3 фразой содержащей текст "Оказываемые услуги "
    @FindBy(xpath = "//div[contains (text(), \"Оказываемые услуги\")]")
    public WebElement successSecondThree;

    //-------------------------------------------------------

    //ШАГ 3 - Оказываемые услуги
    //Раздел услуг 1 уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[1]/span[2]")
    public List<WebElement> textServices1;

    //Чек-бокс раздела услуг 1 уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[1]/span[2]/../label/span")
    public List<WebElement> checkServices1;

    //Раздел услуг 2 уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[2]/div/div/span[2]")
    public List<WebElement> textServices2;

    //Чек-бокс раздела услуг 2уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[2]/div/div/span[2]/../label/span")
    public List<WebElement> checkServices2;

    //Услуги 3 уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[2]/div/div[2]/div/div/span[2]")
    public List<WebElement> textServices3;

    //Чек-бокс услуг 3 уровня
    @FindBy(xpath = "//div[@class=\"el-tree default-tree\"]/div/div[2]/div/div[2]/div/div/span[2]/../label/span")
    public List<WebElement> checkServices3;


    //    //Услуга "Консультации врачей, отдельные манипуляции"
//    @FindBy(xpath = "//span[contains (text(),\"Консультации врачей, отдельные манипуляции\")]")
//    public WebElement textServices1;
//
//    //Чек-бокс раздела услуг "Консультации врачей, отдельные манипуляции"
//    @FindBy(xpath = "//span[contains (text(),\"Консультации врачей, отдельные манипуляции\")]/../label")
//    public WebElement checkServices1;
//
//    //Подраздел услуг "Услуги оказываемые в отделениях"
//    @FindBy(xpath = "//span[contains (text(),\"Услуги оказываемые в отделениях\")]")
//    public WebElement textServices2;
//
//    //Чек-бокс Подраздела Услуг "Услуги оказываемые в отделениях"
//    @FindBy(xpath = "//span[contains (text(),\"Услуги оказываемые в отделениях\")]/../label")
//    public WebElement checkServices2;
//
//    //Подраздел услуг "Стоимость койко-дня в отделениях"
//    @FindBy(xpath = "//span[contains (text(),\"Стоимость койко-дня в отделениях\")]")
//    public WebElement textServices3;
//
//    //Чек-бокс Подраздела Услуг "Стоимость койко-дня в отделениях"
//    @FindBy(xpath = "//span[contains (text(),\"Стоимость койко-дня в отделениях\")]/../label")
//    public WebElement checkServices3;
//
//    //Услуга "Койко-день в общей палате, 1650 руб"
//    @FindBy(xpath = "//span[contains (text(),\"Койко-день в общей палате, 1650 руб\")]")
//    public WebElement textServices4;
//
//    //Чек-бокс Услуги "Койко-день в общей палате, 1650 руб"
//    @FindBy(xpath = "//span[contains (text(),\"Койко-день в общей палате, 1650 руб\")]/../label")
//    public WebElement checkServices4;
//
    //Список строк выбранных услуг
    @FindBy(xpath = "//div[@class=\"create-user-form-services-cost__table-row\"]")
    public List<WebElement> listStringSelectServices;

    //Список названий выбранных услуг
    @FindBy(xpath = "//div[@class=\"create-user-form-services-cost__table-label\"]")
    public List<WebElement> listTextSelectServices;

    //Список стоимостей выбранных услуг
    @FindBy(xpath = "//div[@class=\"create-user-form-services-cost__table-cost\"]")
    public List<WebElement> listCostSelectServices;

    //Список иконок удаления выбранных услуг
    @FindBy(xpath = "//i[@class=\"create-user-form-services-cost__table-delete el-icon-close\"]")
    public List<WebElement> listIconsDeleteSelectServices;

    //   Проверка успешного перехода на ШАГ №4 фразой содержащей текст "Добавить фото"
    @FindBy(xpath = "//div[contains (text(), \"Добавить фото\")]")
    public WebElement successSecondFour;

    //-------------------------------------------------------

    //ШАГ 4  - Данные специалиста
    //Иконка для загрузки фото
    @FindBy(xpath = ".//*[@id='avatarEditorCanvas']/following-sibling::input")
    public WebElement uploadPhoto;

    //Кнопка сохранения фото
    @FindBy(xpath = "//span[text()=\"Сохранить\"]")
    public WebElement buttonSavePhoto;

    //Поле ввода "Образование, достижения, регалии "
    @FindBy(css = ".el-textarea__inner[placeholder=\"Образование, достижения, регалии\"]")
    public WebElement inputEducation;

    //Поле ввода "Email"
    @FindBy(xpath = "//input[@placeholder=\"Email \"]")
    public WebElement inputEmailInfo;

    //Поле ввода "Телефон"
    @FindBy(xpath = "//div[3]/input[@placeholder=\"Телефон\"]")
    public WebElement inputPhoneInfo;

    //Поле ввода "Instagram"
    @FindBy(xpath = "//input[@placeholder=\"Instagram\"]")
    public WebElement inputInstagram;

    //Поле ввода "VK"
    @FindBy(xpath = "//input[@placeholder=\"Vk\"]")
    public WebElement inputVk;

    //Поле ввода "Whatsapp"
    @FindBy(xpath = "//input[@placeholder=\"Whatsapp\"]")
    public WebElement inputWhatsapp;

    //Поле ввода "Viber"
    @FindBy(xpath = "//input[@placeholder=\"Viber\"]")
    public WebElement inputViber;

    //Поле ввода "Facebook"
    @FindBy(xpath = "//input[@placeholder=\"Facebook\"]")
    public WebElement inputFacebook;

    //Поле ввода "Другое"
    @FindBy(xpath = "//input[@placeholder=\"Другое...\"]")
    public WebElement inputOther;

    //   Ссылка "Добавить"
    @FindBy(xpath = ".user-form-another-contact__add>span")
    public WebElement linkAdd;

    //   Кнопка "Пропустить"
    @FindBy(xpath = "//Span[contains (text(), \"Пропустить\")]")
    public WebElement buttonSkip;

    //-------------------------------------------------------

    //ШАГ 5  - Подтверждение данных
    //Сохраняемый логин
    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
    public WebElement saveLogin;

    //Сохраняемый Email
    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
    public WebElement saveEmail;

    //Сохраняемый Телефон
    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement savePhone;

    //Сохраняемая Фамилия
    @FindBy(xpath = "//div[contains (text(), \"Фамилия\")]/following-sibling::div")
    public WebElement saveSecondName;

    //Сохраняемое Имя
    @FindBy(xpath = "//div[contains (text(), \"Имя\")]/following-sibling::div")
    public WebElement saveFirstName;

    //Сохраняемое Отчество
    @FindBy(xpath = "//div[contains (text(), \"Отчество\")]/following-sibling::div")
    public WebElement saveMiddleName;

    //Проверка созданного юзера

//    //Сохраняемое место работы
//    @FindBy(xpath = "//div[contains (text(), \"Место:\")]/following-sibling::div")
//    public WebElement saveWorkSpace;
//
//    //Сохраняемая должность
//    @FindBy(xpath = "//div[contains (text(), \"Должность:\")]/following-sibling::div")
//    public WebElement saveWorkerPosition;
//
//    //Сохраняемая роль пользователя
//    @FindBy(xpath = "//div[contains (text(), \"Роль пользователя:\")]/following-sibling::div")
//    public WebElement saveWorkerRole;
//
//    //Сохраняемая специальность пользователя
//    @FindBy(xpath = "//div[contains (text(), \"Специальност\")]/following-sibling::ul/li")
//    public WebElement saveWorkerSpecialty;


    // Сохраняемые Услуги
    @FindBy(xpath = "//div[contains (text(), \"Услуги\")]/following-sibling::div/div")
    public List<WebElement> saveServices;

    // Сохраняемые Образование, достижения, регалии
    @FindBy(xpath = "//div[text()=\"Образование, достижения, регалии\"]/following-sibling::div")
    public WebElement saveEducation;

    // Сохраняемый контакт Email
    @FindBy(css = ".create-user-form-finish__contacts-row-data")
    public WebElement saveEmailInfo;

    // Сохраняемый контакт Телефон
    @FindBy(xpath = "//div[3]/div[contains (text(), \"Телефон\")]/following-sibling::div")
    public WebElement savePhoneInfo;

    // Сохраняемый контакт Instagram
    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
    public WebElement saveInstagram;

    // Сохраняемый контакт Vk
    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
    public WebElement saveVk;

    // Сохраняемый контакт  Whatsapp
    @FindBy(xpath = "//div[contains (text(), \" Whatsapp\")]/following-sibling::div")
    public WebElement saveWhatsapp;

    // Сохраняемый контакт  Viber
    @FindBy(xpath = "//div[contains (text(), \" Viber\")]/following-sibling::div")
    public WebElement saveViber;

    // Сохраняемый контакт  Facebook
    @FindBy(xpath = "//div[contains (text(), \" Facebook\")]/following-sibling::div")
    public WebElement saveFacebook;

    // Сохраняемый контакт  Другое
    @FindBy(xpath = "//div[contains (text(), \" Другое\")]/following-sibling::div")
    public WebElement saveOther;

    //   Кнопка "Завершить"
    @FindBy(xpath = "//Span[contains (text(), \"Завершить\")]")
    public WebElement buttonFinish;

    //   Кнопка "Завершить"
    @FindBy(xpath = "//h2[contains (text(), \"Успешно\")]")
    public WebElement successCreate;


    public void createUserStepOneBase(String Login, String Email, String Phone, String Password, String SecondName,
                                      String FirstName, String MiddleName) {
        waitE_ClickableAndClick(menuWorkers);
        waitE_ClickableAndClick(menuCreateWorker);
        waitE_ClickableAndSendKeys(inputlogin, Login);
        waitE_ClickableAndSendKeys(inputEmail, Email);
        waitE_ClickableAndSendKeys(inputPhone, Phone);
        waitE_ClickableAndSendKeys(inputPassword, Password);
        waitE_ClickableAndSendKeys(inputSecondName, SecondName);
        waitE_ClickableAndSendKeys(inputFirstName, FirstName);
        waitE_ClickableAndSendKeys(inputMiddleName, MiddleName);
    }

    public void createUserStepOneBaseWithoutMiddleName(String Login, String Email, String Phone, String Password,
                                                       String SecondName, String FirstName) {
        waitE_ClickableAndClick(menuWorkers);
        waitE_ClickableAndClick(menuCreateWorker);
        waitE_ClickableAndSendKeys(inputlogin, Login);
        waitE_ClickableAndSendKeys(inputEmail, Email);
        waitE_ClickableAndSendKeys(inputPhone, Phone);
        waitE_ClickableAndSendKeys(inputPassword, Password);
        waitE_ClickableAndSendKeys(inputSecondName, SecondName);
        waitE_ClickableAndSendKeys(inputFirstName, FirstName);
    }

    public void stepTwo() {

//        Select selectWC = new Select(selectWorkspace);

//        Select selectWR = new Select(selectWorkspace);
//        Select selectWS = new Select(selectWorkspace);
//
//        selectWC.selectByVisibleText("Больница 1")
//        selectWR.selectByVisibleText("Специалист");
//        selectWS.selectByVisibleText("Лор");


        waitE_ClickableAndClick(selectStructureOrganization);
        waitE_ClickableAndClick(selectMainOrganization);
        waitE_ClickableAndSendKeys(inputWorkerPosition, "Докторелло");
        waitE_ClickableAndClick(selectWorkerRole);
        waitE_ClickableAndClick(selectWorkerRoleSpecialist);
        waitE_ClickableAndClick(selectWorkerSpecialty);
        waitE_ClickableAndClick(selectWorkerSpecialtyMed);
        waitE_ClickableAndClick(body);
        waitE_ClickableAndClick(buttonAddUser);
        waitE_visibilityOf(successSecondThree);
    }

    public void stepThree(String SecondName, String CheckedAndFocus, String Checkeds, String Indeterminate,
                          String Empty) {
        int i = -1;

        switch (SecondName) {
            case "Аничкин":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(500);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(500);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        waitE_ClickableAndClick(checkServices3.get(i));
                        System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                                listCostSelectServices.get(i).getText());
                        Assert.assertTrue(textServices3.get(i).getText().equals(listTextSelectServices.get(i).getText() + ", " +
                                listCostSelectServices.get(i).getText()));
                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                        Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                    } else {
                        try {
                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Empty));

                            System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() +
                                    ", " + listCostSelectServices.get(i).getText());
                            Assert.assertTrue(textServices3.get(i).getText().equals(""));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Невыделенные услуги не найдены");
                        }
                    }
                }
                System.out.println("Icon DELETE is enabled and size = 1");
                Assert.assertTrue(listIconsDeleteSelectServices.get(0).isEnabled() & listIconsDeleteSelectServices.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Indeterminate));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices2.get(0).getAttribute("class").equals(Indeterminate));


                for (WebElement String : listStringSelectServices) {
                    System.out.println(String.getText());
                }
                break;

            case "Аниськин":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(500);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(500);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    waitE_ClickableAndClick(checkServices3.get(i));
                    ListServices.add(listStringSelectServices.get(i).getText().replace("\n", " "));
                    System.out.println("1234 = " + ListServices);
                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertTrue(textServices3.get(i).getText().equals(listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText()));
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Indeterminate));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertTrue(checkServices2.get(0).getAttribute("class").equals(Checkeds));


                System.out.println("Unselect last service");
                ListServices.remove(listStringSelectServices.get(i).getText());
                waitE_ClickableAndClick(checkServices3.get(i));
                waitE_ClickableAndClick(body);
                try {
                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Empty));

                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertTrue(textServices3.get(i).getText().equals(""));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }


                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Indeterminate));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices2.get(0).getAttribute("class").equals(Indeterminate));

                System.out.println("ListString= " + ListServices);

                break;

            case "Авитисян":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(500);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(500);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    waitE_ClickableAndClick(checkServices3.get(i));
                    Assert.assertTrue(textServices3.get(i).getText().contains(" руб."));
                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertTrue(textServices3.get(i).getText().equals(listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText()));
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Indeterminate));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);


                System.out.println("Delete one service");
                Assert.assertTrue(listCostSelectServices.get(i).getText().contains(" руб."));
                waitE_ClickableAndClick(listIconsDeleteSelectServices.get(i));
                sleep(1000);
                try {
                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }


                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (WebElement Service : checkServices3) {
                        System.out.println("Сняли выделение с: " + listTextSelectServices.get(0).getText());
                        waitE_ClickableAndClick(listIconsDeleteSelectServices.get(0));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size());
                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(0, listIconsDeleteSelectServices.size());
                Assert.assertEquals(0, listTextSelectServices.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);
                break;

            case "Аннуфриев":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(500);
                int a = -1;
                for (WebElement Service2 : textServices2) {
                    a = a + 1;
                    waitE_ClickableAndClick(textServices2.get(a));
                    sleep(500);
                }
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    System.out.println("Size = " + checkServices3.size());
                    System.out.println("Click i = " + i);
                    waitE_ClickableAndClick(checkServices3.get(i));
                    Assert.assertTrue(textServices3.get(i).getText().contains(" руб."));
                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertTrue(textServices3.get(i).getText().equals(listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText()));
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Checkeds));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Checkeds);

                System.out.println("Delete one service");
                Assert.assertTrue(listCostSelectServices.get(i).getText().contains(" руб."));
                waitE_ClickableAndClick(listIconsDeleteSelectServices.get(i));
                sleep(1000);
                try {
                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (WebElement Service : checkServices3) {
                        System.out.println("Сняли выделение с: " + listTextSelectServices.get(0).getText());
                        waitE_ClickableAndClick(listIconsDeleteSelectServices.get(0));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size());
                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(0, listIconsDeleteSelectServices.size());
                Assert.assertEquals(0, listTextSelectServices.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Empty);
                break;

            case "Djon":
                int c = -1;
                for (WebElement Service1 : textServices1) {
                    c = c + 1;
                    if (c < textServices1.size() - 1) {
                        waitE_ClickableAndClick(textServices1.get(c));
                        sleep(500);
                    }
                }
                int b = -1;
                for (WebElement Service2 : textServices2) {
                    b = b + 1;
                    waitE_ClickableAndClick(textServices2.get(b));
                    sleep(500);
                }
                for (WebElement Service3 : checkServices3) {
                    i = i + 1;
                    System.out.println("Size = " + checkServices3.size());
                    System.out.println("Click i = " + i);
                    waitE_ClickableAndClick(checkServices3.get(i));
                    sleep(500);
                    Assert.assertTrue(textServices3.get(i).getText().contains(" руб."));
                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertTrue(textServices3.get(i).getText().equals(listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText()));
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                for (int x = 0; x < checkServices1.size() - 1; x++) {
                    System.out.println("Lev1 - " + x + " = " + checkServices1.get(x).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices1.get(x).getAttribute("class"), Checkeds);
                }
                for (int x = 0; x < checkServices2.size(); x++) {
                    System.out.println(checkServices2.get(x).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices2.get(x).getAttribute("class"), Checkeds);
                }
                for (int x = 0; x < checkServices3.size(); x++) {
                    if (x < checkServices3.size() - 1) {
                        System.out.println(checkServices3.get(x).getAttribute("class") + " = " + Checkeds);
                        Assert.assertEquals(checkServices3.get(x).getAttribute("class"), Checkeds);
                    } else {
                        System.out.println(checkServices3.get(x).getAttribute("class") + " = " + CheckedAndFocus);
                        Assert.assertEquals(checkServices3.get(x).getAttribute("class"), CheckedAndFocus);
                    }
                }
                System.out.println("Delete one service");
                Assert.assertTrue(listCostSelectServices.get(i).getText().contains(" руб."));
                waitE_ClickableAndClick(listIconsDeleteSelectServices.get(i));
                sleep(1000);
                try {
                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listTextSelectServices.get(i).getText() + ", " +
                            listCostSelectServices.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServices.size() == listTextSelectServices.size() &
                        listTextSelectServices.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                for (int x = 0; x < checkServices1.size(); x++) {
                    if (x < checkServices1.size() - 2) {
                        System.out.println(checkServices1.get(x).getAttribute("class") + " = " + Checkeds);
                        Assert.assertEquals(checkServices1.get(x).getAttribute("class"), Checkeds);
                    } else {
                        System.out.println(checkServices1.get(x).getAttribute("class") + " = " + Empty);
                        Assert.assertEquals(checkServices1.get(x).getAttribute("class"), Empty);
                    }
                }
                for (int x = 0; x < checkServices2.size(); x++) {
                    if (x < checkServices2.size() - 1) {
                        System.out.println(checkServices2.get(x).getAttribute("class") + " = " + Checkeds);
                        Assert.assertEquals(checkServices2.get(x).getAttribute("class"), Checkeds);
                    } else {
                        System.out.println(checkServices2.get(x).getAttribute("class") + " = " + Empty);
                        Assert.assertEquals(checkServices2.get(x).getAttribute("class"), Empty);
                    }
                }
                for (int x = 0; x < checkServices3.size(); x++) {
                    if (x < checkServices3.size() - 1) {
                        System.out.println(checkServices3.get(x).getAttribute("class") + " = " + Checkeds);
                        Assert.assertEquals(checkServices3.get(x).getAttribute("class"), Checkeds);
                    } else {
                        System.out.println(checkServices3.get(x).getAttribute("class") + " = " + Empty);
                        Assert.assertEquals(checkServices3.get(x).getAttribute("class"), Empty);
                    }
                }
                System.out.println("Delete all service");
                try {
                    for (WebElement Service : checkServices3) {
                        System.out.println("Сняли выделение с: " + listTextSelectServices.get(0).getText());
                        waitE_ClickableAndClick(listIconsDeleteSelectServices.get(0));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                System.out.println(listIconsDeleteSelectServices.size() + " = " + listTextSelectServices.size());
                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(0, listIconsDeleteSelectServices.size());
                Assert.assertEquals(0, listTextSelectServices.size());
                for (WebElement CheckServices1 : checkServices1) {
                    System.out.println(CheckServices1.getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(CheckServices1.getAttribute("class"), Empty);
                }
                for (WebElement CheckServices2 : checkServices2) {
                    System.out.println(CheckServices2.getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(CheckServices2.getAttribute("class"), Empty);
                }
                for (WebElement CheckServices3 : checkServices3) {
                    System.out.println(CheckServices3.getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(CheckServices3.getAttribute("class"), Empty);
                }
        }
        waitE_ClickableAndClick(buttonAddUser);
    }

    public void createUserStepFourBase(String Education, String Email, String Phone, String Instagram, String Vk,
                                       String Whatapp, String Viber, String Facebook, String Other, String Photo) {
        uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        waitE_ClickableAndClick(buttonSavePhoto); sleep(5000);
        waitE_ClickableAndSendKeys(inputEducation, Education);
        waitE_ClickableAndSendKeys(inputEmailInfo, Email);
        waitE_ClickableAndSendKeys(inputPhoneInfo, Phone);
        waitE_ClickableAndSendKeys(inputInstagram, Instagram);
        waitE_ClickableAndSendKeys(inputVk, Vk);
        waitE_ClickableAndSendKeys(inputWhatsapp, Whatapp);
        waitE_ClickableAndSendKeys(inputViber, Viber);
        waitE_ClickableAndSendKeys(inputFacebook, Facebook);
        waitE_ClickableAndSendKeys(inputOther, Other);
        waitE_ClickableAndClick(buttonAddUser);
    }

    public void createUserStepFive(String Login, String Email, String Phone, String Password, String SecondName,
                                   String FirstName, String MiddleName, String Education, String EmailInfo,
                                   String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber,
                                   String Facebook, String Other) {
        waitE_visibilityOfAndGettext(savePhone);
        String SavePhone = savePhone.getText();
        String SavePhoneInfo = savePhoneInfo.getText();

        Assert.assertTrue(Login.equals(saveLogin.getText()));
        System.out.println(Login + " = " + saveLogin.getText());

        Assert.assertTrue(Email.equals(saveEmail.getText()));
        System.out.println(Email + " = " + saveEmail.getText());

        Assert.assertTrue(SavePhone.equals("+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt(8) + Phone.charAt(9)));
        System.out.println(SavePhone + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") "
                + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt(8) + Phone.charAt(9));


        Assert.assertTrue(SecondName.equals(saveSecondName.getText()));
        System.out.println(SecondName + " = " + saveSecondName.getText());

        Assert.assertTrue(FirstName.equals(saveFirstName.getText()));
        System.out.println(FirstName + " = " + saveFirstName.getText());

        Assert.assertTrue(MiddleName.equals(saveMiddleName.getText()));
        System.out.println(MiddleName + " = " + saveMiddleName.getText());

        int x = -1;
        for (WebElement Service : saveServices) {
            x = x + 1;
            System.out.println(ListServices.get(x) + " = " + saveServices.get(x).getText().
                    replace("\n", " ") + " руб.");
            Assert.assertEquals("Selected and saved services are not equal",
                    ListServices.get(x), saveServices.get(x).getText().replace("\n", " ") + " руб.");
        }

        Assert.assertEquals(Education, saveEducation.getText());
        System.out.println(Education + " = " + saveEducation.getText());


        Assert.assertEquals(EmailInfo, saveEmailInfo.getText());
        System.out.println(EmailInfo + " = " + saveEmailInfo.getText());

        Assert.assertEquals(SavePhoneInfo, "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) +
                ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) +
                PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));
        System.out.println(SavePhoneInfo + " = " + "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) +
                ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) +
                PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));

        Assert.assertEquals(Instagram, saveInstagram.getText());
        System.out.println(Instagram + " = " + saveInstagram.getText());

        Assert.assertEquals(Vk, saveVk.getText());
        System.out.println(Vk + " = " + saveVk.getText());

        Assert.assertEquals(Whatsapp, saveWhatsapp.getText());
        System.out.println(Whatsapp + " = " + saveWhatsapp.getText());

        Assert.assertEquals(Viber, saveViber.getText());
        System.out.println(Viber + " = " + saveViber.getText());

        Assert.assertEquals(Facebook, saveFacebook.getText());
        System.out.println(Facebook + " = " + saveFacebook.getText());

        Assert.assertEquals(Other, saveOther.getText());
        System.out.println(Other + " = " + saveOther.getText());

        waitE_ClickableAndClick(buttonFinish);
        waitE_visibilityOf(successCreate);
    }

    public void createUserStepSix(String Login, String Email, String Phone, String Password, String SecondName,
                                  String FirstName, String MiddleName, String Education, String EmailInfo,
                                  String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber,
                                  String Facebook, String Other) {
        waitE_ClickableAndClick(buttonUserList);
        waitE_visibilityOf(listSecondName);
        WebElement findToSecondName =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr" +
                        "/td[4" +
                        "]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
        waitE_visibilityOfAndGettext(savePhone);
        String AccPhone = profAccPhone.getText();
        String ContPhone = profContPhone.getText();

        System.out.println(Login + " = " + saveLogin.getText());
        Assert.assertTrue(Login.equals(saveLogin.getText()));

        Assert.assertTrue(Email.equals(saveEmail.getText()));
        System.out.println(Email + " = " + saveEmail.getText());

        Assert.assertTrue(AccPhone.equals("+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt(8) + Phone.charAt(9)));
        System.out.println(AccPhone + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" + Phone.charAt(8) + Phone.charAt(9));

        String Fio = SecondName + " " + FirstName + " " + MiddleName;
        System.out.println(Fio + " = " + fio.getText());
        if (MiddleName.equals("")) {
            Assert.assertTrue(Fio.equals(fio.getText() + " "));
        } else {
            Assert.assertTrue(Fio.equals(fio.getText()));
        }


        Assert.assertTrue(Education.equals(profEducation.getText()));
        System.out.println(Education + " = " + profEducation.getText());

        Assert.assertTrue(EmailInfo.equals(profEmail.getText()));
        System.out.println(EmailInfo + " = " + profEmail.getText());

        Assert.assertTrue(ContPhone.equals("+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) + PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9)));
        System.out.println(ContPhone + " = " + "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) + ") " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) + PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));

        Assert.assertTrue(Instagram.equals(saveInstagram.getText()));
        System.out.println(Instagram + " = " + saveInstagram.getText());

        Assert.assertTrue(Vk.equals(saveVk.getText()));
        System.out.println(Vk + " = " + saveVk.getText());

        Assert.assertTrue(Whatsapp.equals(profWhatsapp.getText()));
        System.out.println(Whatsapp + " = " + profWhatsapp.getText());

        Assert.assertTrue(Viber.equals(profViber.getText()));
        System.out.println(Viber + " = " + profViber.getText());

        Assert.assertTrue(Facebook.equals(profFacebook.getText()));
        System.out.println(Facebook + " = " + profFacebook.getText());

        Assert.assertTrue(Other.equals(profOther.getText()));
        System.out.println(Other + " = " + profOther.getText());
    }
}



