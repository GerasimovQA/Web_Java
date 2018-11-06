package createuser;

import global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

    int u = 0;

    ArrayList<String> SelectedSpecialtys = new ArrayList<>();
    ArrayList<String> SelectedOrg = new ArrayList<>();
    ArrayList<String> SelectedPositions = new ArrayList<>();
    ArrayList<String> SelectedRoles = new ArrayList<>();

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

    //ШАГ №1
    //    Поле ввода логина с проверкой на текст "Логин"
    @FindBy(css = ".el-input__inner[placeholder=\"Логин *\"]")
    public WebElement inputlogin;

    //    Поле ввода Email с проверкой на текст "Email"
    @FindBy(css = ".el-input__inner[placeholder=\"Email *\"]")
    public WebElement inputEmail;

    //    Поле ввода Телефон с проверкой на текст "Телефон"
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон *\"]")
    public WebElement inputPhone;

    //    Поле ввода Пароль с проверкой на текст "Пароль"
    @FindBy(css = ".el-input__inner[placeholder=\"Пароль *\"]")
    public WebElement inputPassword;

    //    Поле ввода Фамилия с проверкой на текст "Фамилия"
    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия *\"]")
    public WebElement inputSecondName;

    //    Поле ввода Имя с проверкой на текст "Имя"
    @FindBy(css = ".el-input__inner[placeholder=\"Имя *\"]")
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

    //Чек-бокс головной организации в выпадающем списке "Структура организации"
    @FindBy(xpath = "(//*[@class=\"el-popover el-popper\"])" +
            "[2]/div[1]/div[2]/div[1]/div/label/span[@class=\"el-checkbox__input\"]")
    public WebElement selectMainOrganization;

    //Название головной организации в выпадающем списке "Структура организации"
    @FindBy(xpath = "//div[@class=\"tree-select\"]//*[@class=\"tree-select__checked-org\"]")
    public List<WebElement> selectedOrganization;


    //   Проверка успешного перехода на ШАГ №3 фразой содержащей текст "Оказываемые услуги "
    @FindBy(xpath = "//div[contains (text(), \"Оказываемые услуги\")]")
    public WebElement successSecondThree;

    //-------------------------------------------------------

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
    @FindBy(xpath = "//div[@class=\"create-user-form-finish__services-row\"]")
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


    public void createUserStepOneBase(String Login, String Password, String Email, String Phone, String SecondName,
                                      String FirstName, String MiddleName) {
        try {
            waitE_ClickableAndClick(menuCreateWorker);
        } catch (TimeoutException e) {
            System.out.println(e);
            waitE_ClickableAndClick(menuWorkers);
            waitE_ClickableAndClick(menuCreateWorker);
        }

        waitE_ClickableAndSendKeys(inputlogin, Login);
        waitE_ClickableAndSendKeys(inputEmail, Email);
        waitE_ClickableAndSendKeys(inputPhone, Phone);
        waitE_ClickableAndSendKeys(inputPassword, Password);
        waitE_ClickableAndSendKeys(inputSecondName, SecondName);
        waitE_ClickableAndSendKeys(inputFirstName, FirstName);
        waitE_ClickableAndSendKeys(inputMiddleName, MiddleName);
    }

    public void createUserStepOneBaseWithoutMiddleName(String Login, String Password, String Email, String Phone,
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

    public void stepTwo(String Position) {
        waitE_ClickableAndClick(selectStructureOrganization);
        waitE_ClickableAndClick(selectMainOrganization);
        sleep(500);
        waitE_ClickableAndClick(inputWorkerPosition.get(0));
        sleep(1000);
        WebElement SelectedPosition = driver.findElement(By.xpath("//div[@x-placement=\"bottom-start\"]//li[@class" +
                "=\"el-select-dropdown__item\"]/span[contains(text(),\"" + Position + "\")]"));
        waitE_ClickableAndClick(SelectedPosition);
        waitE_ClickableAndClick(userinfoname);
        waitE_ClickableAndClick(selectWorkerRole);
        waitE_ClickableAndClick(selectWorkerRoleSpecialist);
        waitE_ClickableAndClick(selectWorkerSpecialty);
        waitE_ClickableAndClick(selectWorkerSpecialtyMed);
        waitE_ClickableAndClick(userinfoname);
        for (WebElement Org : selectedOrganization) {
            SelectedOrg.add(Org.getText());
        }
        for (WebElement Speciality : selectedWorkerSpecialty) {
            SelectedSpecialtys.add(Speciality.getText().replace(",", ""));
        }
        for (WebElement OnePosition : selectedPosition) {
            System.out.println("Добавляем в список выбранную должность: " + OnePosition);
            SelectedPositions.add(OnePosition.getText());
        }
        for (WebElement Role : selectedWorkerrole) {
            SelectedRoles.add(Role.getText());
        }


        waitE_ClickableAndClick(buttonAddUser);
        waitE_visibilityOf(successSecondThree);
    }

    public void stepThree(String SecondName, String CheckedAndFocus, String Checkeds, String Indeterminate,
                          String Empty) {
        int i = -1;

        switch (SecondName) {
            case "Аничкин":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(1000);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        waitE_ClickableAndClick(checkServices3.get(i));
                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        waitE_visibilityOf(listCostSelectServicesAddUser.get(i));
                        System.out.println(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                        Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    } else {
                        try {
                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                            System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
                                    ", " + listCostSelectServicesAddUser.get(i).getText());
                            Assert.assertEquals("", textServices3.get(i).getText());
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Невыделенные услуги не найдены");
                        }
                    }
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                System.out.println("Icon DELETE is enabled and size = 1");
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() & listIconsDeleteSelectServicesAddUser.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                break;

            case "Аниськин":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(1000);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    waitE_ClickableAndClick(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        System.out.println("Service №" + i);

                        System.out.println(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            break;
                        } else {System.out.println("Услуги не совпали"); }
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
                waitE_ClickableAndClick(userinfoname);
                sleep(1000);
                System.out.println((listStringSelectedServicesInTree.get(i).getText().replace("\n", " ")));
                System.out.println("Unselect last service");
                SelectedServices.remove(listStringSelectedServicesInTree.get(i).getText().replace("\n", " "));
                waitE_ClickableAndClick(checkServices3.get(i));
                waitE_ClickableAndClick(userinfoname);
                try {
                    System.out.println("Сняли выделение с: " + textServices3.get(i).getText());
                    System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }

                for (WebElement Service : listStringSelectedServicesInTree) {
                    i = -1;
                    i = i + 1;
                    System.out.println("Выделенные услуги в дереве" + listStringSelectedServicesInTree.size());
                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
                        u = 0;
                        System.out.println("Service №" + i);
                        System.out.println("Повтороно сравниваем услуги: " + textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText());
                        if ((textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            break;
                        } else {System.out.println("Услуги не совпали");}
                    }
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println("ListString= " + SelectedServices);
                break;

            case "Авитисян":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(1000);
                waitE_ClickableAndClick(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    waitE_ClickableAndClick(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        System.out.println("Service №" + i);

                        System.out.println(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            break;
                        } else {System.out.println("Услуги не совпали");}
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
                waitE_ClickableAndClick(userinfoname);
                sleep(1000);
                System.out.println(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
                System.out.println("Delete one service");
                SelectedServices.remove(stringServiceDeletedInListServices.get(i).getText().replace("\n", " "));
                String DeletedService = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService);
                waitE_ClickableAndClick(listIconsDeleteSelectServicesAddUser.get(i));
                sleep(1000);
                try {
                    System.out.println("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span");
                    System.out.println("Сняли выделение с: " + DeletedService);
                    System.out.println("Check-box " + i + " = " + driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
                            .getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
                            .getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }


                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (int b = listStringSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        waitE_ClickableAndClick(listStringSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                waitE_ClickableAndClick(checkServices3.get(0));
                SelectedServices.add(listStringSelectServicesAddUser.get(0).getText().replace("\n", " "));


                for (WebElement Service : listStringSelectedServicesInTree) {
                    i = -1;
                    i = i + 1;
                    System.out.println("Выделенные услуги в дереве: " + listStringSelectedServicesInTree.size());
                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
                        u = 0;
                        System.out.println("Service №" + i);
                        System.out.println("Повторно сравниваем услуги: " + textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText());
                        if ((textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            break;
                        } else {System.out.println("Услуги не совпали");}
                    }
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(1, listIconsDeleteSelectServicesAddUser.size());
                Assert.assertEquals(1, listNameSelectServicesAddUser.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Empty);
                break;

            case "Аннуфриев":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(1000);
                waitE_ClickableAndClick(textServices2.get(0));
                waitE_ClickableAndClick(textServices2.get(1));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    waitE_ClickableAndClick(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        System.out.println("Service №" + i);

                        System.out.println(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            break;
                        } else {System.out.println("Услуги не совпали");}
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());
                sleep(1000);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Checkeds);
                waitE_ClickableAndClick(userinfoname);
                sleep(1000);

                System.out.println("Delete one service");
                SelectedServices.remove(listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService2);
                waitE_ClickableAndClick(listIconsDeleteSelectServicesAddUser.get(i));
                sleep(1000);
                try {
                    System.out.println("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span");
                    System.out.println("Сняли выделение с: " + DeletedService2);
                    System.out.println("Check-box " + i + " = " + driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
                            .getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
                            .getAttribute("class"), Empty);

                    System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Невыделенные услуги не найдены");
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));
                waitE_ClickableAndClick(userinfoname);
                sleep(1000);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (int b = listStringSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        waitE_ClickableAndClick(listStringSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                waitE_ClickableAndClick(checkServices3.get(5));
                waitE_ClickableAndClick(userinfoname);
                sleep(1000);
                SelectedServices.add(listStringSelectedServicesInTree.get(0).getText().replace("\n", " "));

                for (WebElement SelectService : listStringSelectServicesAddUser) {
                    i = 0;
                    u = 0;
                    System.out.println("Service №" + i);

                    System.out.println(listStringSelectedCheckBoxInTree.get(i).getText().replace("\n", " ") + " = " +
                            SelectService.getText().replace("\n", " "));
                    if (listStringSelectedCheckBoxInTree.get(i).getText().replace("\n", " ").equals(
                            SelectService.getText().replace("\n", " "))) {
                        u = u + 1;
                        System.out.println("!+Service find in list selected services+!");
                        break;
                    } else {System.out.println("Услуги не совпали");}
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(1, listIconsDeleteSelectServicesAddUser.size());
                Assert.assertEquals(1, listNameSelectServicesAddUser.size());
                sleep(1000);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
                break;

            case "Djon":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(500);
                waitE_ClickableAndClick(textServices2.get(0));
                waitE_ClickableAndClick(textServices2.get(1));
                sleep(500);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        waitE_ClickableAndClick(Service);
                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        waitE_visibilityOf(listCostSelectServicesAddUser.get(i));
                        System.out.println(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                        Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    } else {
                        try {
                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                            System.out.println(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
                                    ", " + listCostSelectServicesAddUser.get(i).getText());
                            Assert.assertEquals("", textServices3.get(i).getText());
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Невыделенные услуги не найдены");
                        }
                    }
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
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

    public void createUserStepFive(String Login,  String Password, String Email, String Phone, String SecondName,
                                   String FirstName, String MiddleName, String Education, String EmailInfo,
                                   String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber,
                                   String Facebook, String Other) {
        waitE_visibilityOfAndGettext(savePhone);
        String SavePhone = savePhone.getText();
        String SavePhoneInfo = savePhoneInfo.getText();

        Assert.assertEquals(Login, saveLogin.getText());
        System.out.println(Login + " = " + saveLogin.getText());

        Assert.assertEquals(Email, saveEmail.getText());
        System.out.println(Email + " = " + saveEmail.getText());

        Assert.assertEquals(SavePhone,
                "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") " + Phone.charAt(3) +
                        Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) + "-" +
                        Phone.charAt(8) + Phone.charAt(9));
        System.out.println(SavePhone + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") "
                + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) +
                "-" + Phone.charAt(8) + Phone.charAt(9));

        Assert.assertEquals(SecondName, saveSecondName.getText());
        System.out.println(SecondName + " = " + saveSecondName.getText());

        Assert.assertEquals(FirstName, saveFirstName.getText());
        System.out.println(FirstName + " = " + saveFirstName.getText());

        Assert.assertEquals(MiddleName, saveMiddleName.getText());
        System.out.println(MiddleName + " = " + saveMiddleName.getText());

        int x = -1;
        for (WebElement Service : saveServices) {
            x = x + 1;
            System.out.println(SelectedServices.get(x) + " = " + saveServices.get(x).getText().
                    replace("\n", " "));
            Assert.assertEquals("Selected and saved services are not equal",
                    SelectedServices.get(x), Service.getText().replace("\n", " "));
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

    public void createUserStepSix(String Login, String Password, String Email, String Phone, String SecondName,
                                  String FirstName, String MiddleName, String Position, String Education,
                                  String EmailInfo, String PhoneInfo, String Instagram, String Vk, String Whatsapp,
                                  String Viber, String Facebook, String Other) {
        waitE_ClickableAndClick(buttonUserList);
        waitE_visibilityOf(listSecondName);
        WebElement findToSecondName =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr" +
                        "/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
        waitE_visibilityOfAndGettext(profPosition);
        waitE_visibilityOf(listProfSpeciality.get(0));
//        String AccPhone = profAccPhone.getText();
//        String ContPhone = profContPhone.getText();

        String Fio = SecondName + " " + FirstName + " " + MiddleName;
        System.out.println(Fio + " = " + profFio.getText());
        if (MiddleName.equals("")) {
            Assert.assertEquals(Fio, profFio.getText() + " ");
        } else {
            Assert.assertEquals(Fio, profFio.getText());
        }

        System.out.println(Position + " = " + profPosition.getText());
        Assert.assertEquals(Position, profPosition.getText());

        ArrayList<String> ListProfSpeciality = new ArrayList<>();
        for (WebElement profSpeciality : listProfSpeciality) {
            System.out.println(profSpeciality.getText());
            if (!profSpeciality.getText().equals("")) {
                ListProfSpeciality.add(profSpeciality.getText().replace(",", ""));
            }
        }
        System.out.println("Специальности при создании: " + SelectedSpecialtys);
        System.out.println("Специальности в профиле: " + ListProfSpeciality);
        int i = -1;
        if (ListProfSpeciality.size() == 0) {
            throw new AssertionError("Список специальностей пуст");
        }
        for (String Spec : ListProfSpeciality) {
            i = i + 1;
            for (int x = 0; x < SelectedSpecialtys.size(); x++) {
                u = 0;
                System.out.println("Специальность №" + x);
                System.out.println("Сравниваем специальности: " + SelectedSpecialtys.get(x) + " = " +
                        Spec);
                if (SelectedSpecialtys.get(x).equals(Spec)) {
                    u = u + 1;
                    System.out.println("!+Speciality finded+!");
                    break;
                } else {System.out.println("Специальности не совпали");}
            }
            Assert.assertEquals("Специальности различаются", 1, u);
        }


        ArrayList<String> ListProfNameWorkspace = new ArrayList<>();
        for (WebElement Workspace : listProfNameWorkspace) {
            ListProfNameWorkspace.add(Workspace.getText());
        }
        System.out.println("Сравнивам места работы :" + SelectedOrg.equals(ListProfNameWorkspace));
        Assert.assertEquals(SelectedOrg, ListProfNameWorkspace);

        ArrayList<String> ListProfPositions = new ArrayList<>();
        for (WebElement OnePosition : listProfPosition) {
            ListProfPositions.add(OnePosition.getText());
        }
        System.out.println("Сравнивам должности в местах работы :" + SelectedPositions.equals(ListProfPositions));
        Assert.assertEquals(SelectedPositions, ListProfPositions);

        ArrayList<String> ListProfRoles = new ArrayList<>();
        for (
                WebElement Role : listProfRole) {
            System.out.println("Добавляем в список ролей в карточке: " + Role.getText());
            ListProfRoles.add(Role.getText());
        }
        System.out.println("Сравнивам роли в местах работ :" + SelectedRoles.equals(ListProfRoles));
        Assert.assertEquals(SelectedRoles, ListProfRoles);

        Assert.assertEquals(Education, profEducation.getText());
        System.out.println(Education + " = " + profEducation.getText());

        ArrayList<String> ListProfServices = new ArrayList<>();
        for (
                WebElement Service : listProfServices) {
            ListProfServices.add(Service.getText().replace("\n", " "));
        }
        System.out.println("Сравнивам услуги :" + SelectedServices.equals(ListProfServices));
        Assert.assertEquals(SelectedServices, ListProfServices);

        System.out.println(EmailInfo + " = " + profEmail.getText());
        Assert.assertEquals(EmailInfo, profEmail.getText());

        System.out.println(profContPhone.getText() + " = " + "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) + ")" +
                " " + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) +
                "-" + PhoneInfo.charAt(6) + PhoneInfo.charAt(7) + "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));
        Assert.assertEquals(profContPhone.getText(),
                "+7 (" + PhoneInfo.charAt(0) + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) + ") "
                        + PhoneInfo.charAt(3) + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + "-" + PhoneInfo.charAt(6) + PhoneInfo.charAt(7) +
                        "-" + PhoneInfo.charAt(8) + PhoneInfo.charAt(9));

        Assert.assertEquals(Instagram, profInstagram.getText());
        System.out.println(Instagram + " = " + saveInstagram.getText());

        Assert.assertEquals(Vk, saveVk.getText());
        System.out.println(Vk + " = " + saveVk.getText());

        Assert.assertEquals(Whatsapp, profWhatsapp.getText());
        System.out.println(Whatsapp + " = " + profWhatsapp.getText());

        Assert.assertEquals(Viber, profViber.getText());
        System.out.println(Viber + " = " + profViber.getText());

        Assert.assertEquals(Facebook, profFacebook.getText());
        System.out.println(Facebook + " = " + profFacebook.getText());

        Assert.assertEquals(Other, profOther.getText());
        System.out.println(Other + " = " + profOther.getText());

        System.out.println(Login + " = " + profAccLogin.getText());
        Assert.assertEquals(Login, profAccLogin.getText());

        System.out.println(Email + " = " + profAccEmail.getText());
        Assert.assertEquals(Email, profAccEmail.getText());

        System.out.println(profAccPhone.getText() + " = " + "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") "
                + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) +
                "-" + Phone.charAt(8) + Phone.charAt(9));
        Assert.assertEquals(profAccPhone.getText(),
                "+7 (" + Phone.charAt(0) + Phone.charAt(1) + Phone.charAt(2) + ") "
                        + Phone.charAt(3) + Phone.charAt(4) + Phone.charAt(5) + "-" + Phone.charAt(6) + Phone.charAt(7) +
                        "-" + Phone.charAt(8) + Phone.charAt(9));

        Assert.assertTrue(profSystemID.isEnabled());
        Assert.assertTrue(profSystemStatus.isEnabled());
        Assert.assertTrue(profQR.isEnabled());

    }
}



