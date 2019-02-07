package User.CreateUser;

import Global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

public class CreateUserPage extends GlobalPage {

    CreateUserPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private int u = 0;
    private int SumAmbulance = 0;

    private ArrayList<String> SelectedSpecialtys = new ArrayList<>();
    private ArrayList<String> SelectedOrg = new ArrayList<>();
    private ArrayList<String> SelectedPositions = new ArrayList<>();
    private ArrayList<String> SelectedRoles = new ArrayList<>();

    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
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
    @FindBy(css = ".Password__toggle--show-password.fa.fa-eye-slash")
    public WebElement showPassword;

    //   Иконка "Глаз" - скрыть пароль
    @FindBy(css = ".Password__toggle--show-password.fa.fa-eye")
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
    //   Проверка успешного перехода на ШАГ №3 фразой содержащей текст "Оказываемые услуги "
    @FindBy(xpath = "//div[contains (text(), \"Оказываемые услуги\")]")
    public WebElement successSecondThree;

    //-------------------------------------------------------
    //ШАГ 3 - Услуги
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

    //Сохраняемое место работы
    @FindBy(xpath = "//div[contains (text(), \"Место:\")]/following-sibling::div")
    public List<WebElement> listSaveWorkSpace;

    //Сохраняемая должность
    @FindBy(xpath = "//div[contains (text(), \"Должность:\")]/following-sibling::div")
    public List<WebElement> listSaveWorkerPosition;

    //Сохраняемая роль пользователя
    @FindBy(xpath = "//div[contains (text(), \"Роль пользователя:\")]/following-sibling::div")
    public List<WebElement> listSaveWorkerRole;

    //Сохраняемая специальность пользователя
    @FindBy(xpath = "//div[contains (text(), \"Специальност\")]/following-sibling::ul/li")
    public List<WebElement> listSaveWorkerSpecialty;

    // метка "Ведет амбулаторный приём ДА":
    @FindBy(xpath = "//div[contains (text(), \"Ведет амбулаторный приём:\")]/following-sibling::div/span[text()= \"Да\"]")
    public List<WebElement> listSaveAmbulanceYes;

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

    //   Всплывающее сообщение "Успешно"
    @FindBy(xpath = "//h2[contains (text(), \"Успешно\")]")
    public WebElement successCreate;


    public void createUserStepOneBase(String Login, String Password, String Email, String Phone, String SecondName,
                                      String FirstName, String MiddleName) {
        try {
            click(menuCreateWorker);
        } catch (TimeoutException e) {
            pr("Раскрываем меню");
            click(menuWorkers);
            click(menuCreateWorker);
        }

        clickAndSendKeys(inputlogin, Login);
        clickAndSendKeys(inputEmail, Email);
        clickAndSendKeys(inputPhone, Phone);
        clickAndSendKeys(inputPassword, Password);
        clickAndSendKeys(inputSecondName, SecondName);
        clickAndSendKeys(inputFirstName, FirstName);
        clickAndSendKeys(inputMiddleName, MiddleName);
    }

    public void createUserStepOneBaseWithoutMiddleName(String Login, String Password, String Email, String Phone,
                                                       String SecondName, String FirstName) {
        click(menuWorkers);
        click(menuCreateWorker);
        clickAndSendKeys(inputlogin, Login);
        clickAndSendKeys(inputEmail, Email);
        clickAndSendKeys(inputPhone, Phone);
        clickAndSendKeys(inputPassword, Password);
        clickAndSendKeys(inputSecondName, SecondName);
        clickAndSendKeys(inputFirstName, FirstName);
    }

    public void stepTwo(String Position, String SecondName) {
        int s = 0;
        int x = 0;
        click(selectStructureOrganization);
        sleep(500);

        switch (SecondName) {
            case "Аничкин":

                click(selectMainOrganization);
                sleep(500);
                break;

            case "Аниськин":
                do {
                    s = 0;
                    int randomWork1 = random(selectNameMainOrganization.size() - 1);
                    click(selectNameMainOrganization.get(randomWork1));
                    try {
                        click(selectBulletOrganizationTwoLevel.get(random(selectBulletOrganizationTwoLevel.size())));
                    } catch (IndexOutOfBoundsException e) {
                        s = 1;
                        pr("Организация второго уровня не найдена");
                    } catch (TimeoutException e) {
                        s = 1;
                        pr("Не могу прокрутить до выбранной организации");
                    }
                    pr("s = " + s);
                } while (s == 1);
                click(userinfoname);
                break;
            default:
                clickAndSendKeys(inputSearch, "Степа");
                click(driver.findElements(By.xpath("//span[contains(text(),\"Степа\")]/../label/span/span")).get
                        (random(driver.findElements(By.xpath("//span[contains(text(),\"Степа\")]/../label/span/span")).size())));
        }
        pr("Организация 1 выбрана");

        clickAndSendKeys(inputWorkerPosition.get(0), Position);
        inputWorkerPosition.get(0).sendKeys(Keys.ENTER);
        if (SecondName.equals("Аничкин")) {
            click(selectWorkerRole);
            click(selectWorkerRoleSpecialist);
            click(userinfoname);
            click(checkBoxAmbulance);
        } else {
            click(selectWorkerRole);
            click(listFindWorkerRole.get(random(listFindWorkerRole.size())));
            click(userinfoname);
        }

        click(triggerWorkerSpeciality);
        switch (SecondName) {
            case "Аниськин":
                click(listWorkerSpecialtyMed);
                preloader();
                sleep(500);
                do {
                    for (int i = 0; i < random(2); i++) {
                        int RandomSpecialty = random(listMedSpecialty.size() - 1);
                        click(listMedSpecialty.get(RandomSpecialty));
                        pr("Выбрано специальностей: " + listSelectedCheckBoxdWorkerSpecialty.size());
                    }
                }
                while (listSelectedCheckBoxdWorkerSpecialty.size() < 1);
                click(userinfoname);
                break;

            case "Авитисян":
                click(listWorkerSpecialtyMed);
                sleep(500);
                preloader();
                do {
                    for (int i = -1; i < random(2); i++) {
                        pr("Количество медицинских специальностей: " + listMedSpecialty.size());
                        int RandomSpecialty = random(listMedSpecialty.size() - 1);
                        click(listMedSpecialty.get(RandomSpecialty));
                        pr("Выбрано специальностей: " + listSelectedCheckBoxdWorkerSpecialty.size());
                    }
                }
                while (listSelectedCheckBoxdWorkerSpecialty.size() < 1);

                click(listWorkerSpecialtyMed);
                click(listWorkerSpecialtyNotMed);
                sleep(500);
                do {
                    for (int i = -1; i < random(2); i++) {
                        int RandomSpecialty = random(listNotMedSpecialty.size() - 1);
                        click(listNotMedSpecialty.get(RandomSpecialty));
                        pr("Выбрано специальностей: " + listSelectedCheckBoxdWorkerSpecialty.size());
                    }
                }
                while (listSelectedCheckBoxdWorkerSpecialty.size() < 2);
                click(userinfoname);
                break;

            case "Аннуфриев":
                clickAndSendKeys(inputSearch, "Лор");
                click(workerSpecialtyLor);
                click(userinfoname);
                break;

            case "Бандин":
                click(listWorkerSpecialtyNotMed);
                sleep(500);
                do {
                    for (int i = -1; i < random(2); i++) {
                        int RandomSpecialty = random(listNotMedSpecialty.size() - 1);
                        click(listNotMedSpecialty.get(RandomSpecialty));
                        sleep(500);
                        pr("Выбрано специальностей: " + listSelectedCheckBoxdWorkerSpecialty.size());
                    }
                }
                while (listSelectedCheckBoxdWorkerSpecialty.size() < 1);
                pr("Количество выбранных специальностей: " + listSelectedCheckBoxdWorkerSpecialty.size());
                click(userinfoname);
                break;

            default:
                clickAndSendKeys(inputSearch, "Лор");
                click(workerSpecialtyLor);
                click(userinfoname);
        }
        pr("Специальность выбрана");

        switch (SecondName) {
            case "Аниськин":
                click(linkAddWorplace);
                sleep(1000);
                waitVisibility(selectStructureOrganization);
                click(selectStructureOrganization);
                sleep(1000);
                clickAndSendKeys(inputSearch, "Степа");
                click(bulletStepashin2);
                clickAndSendKeys(inputWorkerPosition.get(1), "Лаборант");
                inputWorkerPosition.get(1).sendKeys(Keys.ENTER);

                click(selectWorkerRole);
                click(driver.findElements(By.xpath
                        ("(//ul[@class=\"el-scrollbar__view el-select-dropdown__list\"])[4]//span")).get
                        (random(driver.findElements(By.xpath
                                ("(//ul[@class=\"el-scrollbar__view el-select-dropdown__list\"])[4]//span")).size())));
                click(userinfoname);

                sleep(500);
                Assert.assertEquals(2, selectedOrganization.size());
                break;

            case "Авитисян":
                click(linkAddWorplace);
                sleep(500);
                click(selectStructureOrganization);
                sleep(1000);

                click(selectNameMainOrganization.get(0));
                sleep(1000);
                click(selectBulletOrganizationTwoLevel.get(2));
                clickAndSendKeys(inputWorkerPosition.get(1), "Лаборант");
                inputWorkerPosition.get(1).sendKeys(Keys.ENTER);

                click(selectWorkerRole);
                click(driver.findElements(By.xpath
                        ("(//ul[@class=\"el-scrollbar__view el-select-dropdown__list\"])[4]//span")).get
                        (random(driver.findElements(By.xpath
                                ("(//ul[@class=\"el-scrollbar__view el-select-dropdown__list\"])[4]//span")).size())));
                click(userinfoname);
                scrollHomePage();
                click(linkDeleteWorkplace);
                sleep(500);
                Assert.assertEquals(1, selectedOrganization.size());
                break;
        }

        for (
                WebElement Org : selectedOrganization) {
            moveMouse(Org);
            SelectedOrg.add(Org.getText());
        }
        for (
                WebElement Speciality : selectedWorkerSpecialty) {
            moveMouse(Speciality);
            SelectedSpecialtys.add(Speciality.getText().replace(",", ""));
        }
        for (
                WebElement OnePosition : selectedWorkerPosition) {
            moveMouse(OnePosition);
            pr("Добавляем в список выбранную должность: " + OnePosition.getText());
            SelectedPositions.add(OnePosition.getText());
        }
        for (
                WebElement Role : selectedWorkerRole) {
            moveMouse(Role);
            SelectedRoles.add(Role.getText());
        }

        for (WebElement Ambulance : listCheckBoxAmbulanceClass) {
            moveMouse(Ambulance);
            if (Ambulance.getAttribute("class").equals("el-checkbox__input is-checked")) {
                SumAmbulance = SumAmbulance + 1;
            }
        }
        pr("SumAmbulance = " + SumAmbulance);
        click(buttonAddUser);
        sleep(2000);
        preloader();
    }

    public void stepThree(String SecondName, String CheckedAndFocus, String Checkeds, String Indeterminate,
                          String Empty) {
        int i = -1;

        switch (SecondName) {
            case "Аничкин":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        click(checkServices3.get(i));
                        pr("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        waitVisibility(listCostSelectServicesAddUser.get(i));
                        pr(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        pr(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                        Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                                checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    } else {
                        try {
                            pr("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                            pr(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
                                    ", " + listCostSelectServicesAddUser.get(i).getText());
                            Assert.assertEquals("", textServices3.get(i).getText());
                        } catch (IndexOutOfBoundsException e) {
                            pr("Невыделенные услуги не найдены");
                        }
                    }
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                pr("Icon DELETE is enabled and size = 1");
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() &
                        listIconsDeleteSelectServicesAddUser.size() == 1);

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                break;

            case "Аниськин":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        pr("Service №" + i);

                        pr(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            pr("!+Service find in list selected services+!");
                            break;
                        } else {
                            pr("Услуги не совпали");
                        }
                    }
                    pr(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                click(userinfoname);
                sleep(1000);
                pr((listStringSelectedServicesInTree.get(i).getText().replace("\n", " ")));
                pr("Unselect last service");
                SelectedServices.remove(listStringSelectedServicesInTree.get(i).getText().replace("\n", " "));
                click(checkServices3.get(i));
                click(userinfoname);
                try {
                    pr("Сняли выделение с: " + textServices3.get(i).getText());
                    pr("Check-box " + i + " = " + checkServices3.get(i).getAttribute(
                            "class") + " = " + Empty);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                    pr(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    pr("Невыделенные услуги не найдены");
                }

                for (WebElement Service : listStringSelectedServicesInTree) {
                    i = -1;
                    i = i + 1;
                    pr("Выделенные услуги в дереве" + listStringSelectedServicesInTree.size());
                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
                        u = 0;
                        pr("Service №" + i);
                        pr("Повтороно сравниваем услуги: " + textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText());
                        if ((textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
                            u = u + 1;
                            pr("!+Service find in list selected services+!");
                            break;
                        } else {
                            pr("Услуги не совпали");
                        }
                    }
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                pr("ListString= " + SelectedServices);
                break;

            case "Авитисян":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        pr("Service №" + i);

                        pr(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            pr("!+Service find in list selected services+!");
                            break;
                        } else {
                            pr("Услуги не совпали");
                        }
                    }

                    pr(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                click(userinfoname);
                sleep(1000);
                pr(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
                pr("Delete one service");
                SelectedServices.remove(stringServiceDeletedInListServices.get(i).getText().replace("\n", " "));
                String DeletedService = nameServiceDeletedInListServices.get(i).getText();
                pr(DeletedService);
                click(listIconsDeleteSelectServicesAddUser.get(i));
                sleep(1000);
                try {
                    pr("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span");
                    pr("Сняли выделение с: " + DeletedService);
                    pr("Check-box " + i + " = " + driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
                            .getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService + "\")]/../../../label/span"))
                            .getAttribute("class"), Empty);

                    pr(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    pr("Невыделенные услуги не найдены");
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                pr("Delete all service");
                try {
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        System.out.println(SelectedServices);
                        pr("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        click(listSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    pr("Удалили все услуги в списке");
                }

                click(checkServices3.get(0));
                SelectedServices.add(listStringSelectServicesAddUser.get(0).getText().replace("\n", " "));

                for (WebElement Service : listStringSelectedServicesInTree) {
                    i = -1;
                    i = i + 1;
                    pr("Выделенные услуги в дереве: " + listStringSelectedServicesInTree.size());
                    for (int x = 0; x < listStringSelectedServicesInTree.size(); x++) {
                        u = 0;
                        pr("Service №" + i);
                        pr("Повторно сравниваем услуги: " + textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText());
                        if ((textServices3.get(i).getText() + " " +
                                costServices3.get(i).getText()).equals(listNameSelectServicesAddUser.get(x).getText()
                                + " " + listCostSelectServicesAddUser.get(x).getText())) {
                            u = u + 1;
                            pr("!+Service find in list selected services+!");
                            break;
                        } else {
                            pr("Услуги не совпали");
                        }
                    }
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                Assert.assertEquals(listNameSelectServicesAddUser.size(), listIconsDeleteSelectServicesAddUser.size());

                pr(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(textServices3.size(), checkServices3.size());

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(1).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Empty);
                break;

            case "Аннуфриев":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                click(textServices2.get(1));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        pr("Service №" + i);

                        pr(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            pr("!+Service find in list selected services+!");
                            break;
                        } else {
                            pr("Услуги не совпали");
                        }
                    }
                    click(textServices3.get(i));
                    sleep(200);

                    pr(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " и " + listNameSelectServicesAddUser.size() + " = " + textServices3.size() +
                        " и " + textServices3.size() + "= " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());
                sleep(1000);

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(1).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                Assert.assertTrue(checkServices2.get(1).getAttribute("class").equals(Checkeds) ||
                        checkServices2.get(1).getAttribute("class").equals(CheckedAndFocus));

                click(userinfoname);
                click(userinfoname);
                sleep(1000);

                pr("Delete one service");
                SelectedServices.remove(listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
                pr(DeletedService2);
                click(listIconsDeleteSelectServicesAddUser.get(i));
                sleep(1000);
                try {
                    pr("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span");
                    pr("Сняли выделение с: " + DeletedService2);
                    pr("Check-box " + i + " = " + driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
                            .getAttribute("class") + " = " + Empty);
                    Assert.assertEquals(driver.findElement(By.xpath
                            ("//span[contains(text(),\"" + DeletedService2 + "\")]/../../../label/span"))
                            .getAttribute("class"), Empty);

                    pr(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() + ", " +
                            listCostSelectServicesAddUser.get(i).getText());
                    Assert.assertEquals("", textServices3.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    pr("Невыделенные услуги не найдены");
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + (textServices3.size() - 1) + " = " + (checkServices3.size() - 1));
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == (textServices3.size() - 1) &
                        (textServices3.size() - 1) == (checkServices3.size() - 1));
                click(userinfoname);
                sleep(1000);

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                pr("Delete all service");
                try {
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        System.out.println(SelectedServices);
                        pr("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).
                                getText().replace("\n", " "));
                        waitE_ClickableAndClickAndUp(listSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    pr("Удалили все услуги в списке");
                }

                click(checkServices3.get(5));
                click(userinfoname);
                sleep(1000);
                SelectedServices.add(listStringSelectedServicesInTree.get(0).getText().replace("\n", " "));

                for (WebElement SelectService : listStringSelectServicesAddUser) {
                    i = 0;
                    u = 0;
                    pr("Service №" + i);

                    pr(listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ") + " = " +
                            SelectService.getText().replace("\n", " "));
                    if (listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ").equals(
                            SelectService.getText().replace("\n", " "))) {
                        u = u + 1;
                        pr("!+Service find in list selected services+!");
                        break;
                    } else {
                        pr("Услуги не совпали");
                    }
                }

                pr(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                Assert.assertEquals(listNameSelectServicesAddUser.size(), listIconsDeleteSelectServicesAddUser.size());

                pr(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(textServices3.size(), checkServices3.size());
                sleep(1000);

                pr(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                pr(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);

                pr(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
                break;

            case "Djon":
                click(textServices1.get(0));
                sleep(500);
                click(textServices2.get(0));
                click(textServices2.get(1));
                sleep(500);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        click(Service);
                        pr("Add to array = " + listStringSelectServicesAddUser.get(i).getText().
                                replace("\n", " "));
                        click(userinfoname);

                        waitVisibility(listCostSelectServicesAddUser.get(i));
                        pr(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        pr(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                        Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                                checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    } else {
                        try {
                            pr("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                            pr(textServices3.get(i).getText() + " = " + listNameSelectServicesAddUser.get(i).getText() +
                                    ", " + listCostSelectServicesAddUser.get(i).getText());
                            Assert.assertEquals("", textServices3.get(i).getText());
                        } catch (IndexOutOfBoundsException e) {
                            pr("Невыделенные услуги не найдены");
                        }
                    }
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
        }
        if (SecondName.equals("Бандин")) {
            pr("Пропускаем шаг и это нормально");
        } else {
            click(buttonAddUser);
        }
    }

    public void createUserStepFourBase(String Education, String Email, String Phone, String Instagram, String Vk,
                                       String Whatapp, String Viber, String Facebook, String Other, String Photo) {
        waitVisibility(buttonAddUser);
        uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        click(buttonSavePhoto);
        clickAndSendKeys(inputEducation, Education);
        clickAndSendKeys(inputEmailInfo, Email);
        clickAndSendKeys(inputPhoneInfo, Phone);
        clickAndSendKeys(inputInstagram, Instagram);
        clickAndSendKeys(inputVk, Vk);
        clickAndSendKeys(inputWhatsapp, Whatapp);
        clickAndSendKeys(inputViber, Viber);
        clickAndSendKeys(inputFacebook, Facebook);
        clickAndSendKeys(inputOther, Other);
        click(buttonAddUser);
    }


    public void createUserStepFive(String Login, String Password, String Email, String Phone, String SecondName,
                                   String FirstName, String MiddleName, String Education, String EmailInfo,
                                   String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber,
                                   String Facebook, String Other) {
        waitE_visibilityOfAndGettext(savePhone);
        String SavePhone = savePhone.getText();
        String SavePhoneInfo = savePhoneInfo.getText();

        compareWebelementAndString(saveLogin, Login);
        compareWebelementAndString(saveEmail, Email);

        moveMouse(savePhone);
        compareStringAndString("+ " + Phone.charAt(0) + " (" + Phone.charAt(1) + Phone.charAt(2) + Phone.charAt(3) + ") " + Phone.charAt(4) + Phone.charAt(5) + Phone.charAt(6) + "-" + Phone.charAt(7) + Phone.charAt(8) + "-" + Phone.charAt(9) + Phone.charAt(10), SavePhone);
        compareWebelementAndString(saveSecondName, SecondName);
        compareWebelementAndString(saveFirstName, FirstName);
        compareWebelementAndString(saveMiddleName, MiddleName);

        System.out.println(SelectedOrg);
        soutListWebelements(listSaveWorkSpace);
        Assert.assertEquals(SelectedOrg.size(), listSaveWorkSpace.size());
        Assert.assertEquals(listSaveWorkSpace.size(), SelectedOrg.size());
        int i = 0;
        for (WebElement Act : listSaveWorkSpace) {
            i = i + 1;
            for (String Exp : SelectedOrg) {
                u = 0;
                pr(Act.getText().replace(",", "") + " = " + Exp);


                if (Act.getText().replace(",", "").contains(Exp)) {
                    u = u + 1;
                    pr("!+Match finded+!");
                    break;
                } else {
                    pr("Элементы не совпали");
                }
            }
            Assert.assertEquals("Совпадение элементов не найдено", 1, u);
        }

        compareArrayListAndWebelementsList(SelectedPositions, listSaveWorkerPosition, SelectedPositions.size());

        compareArrayListAndWebelementsList(SelectedRoles, listSaveWorkerRole, SelectedRoles.size());

        pr(SumAmbulance + "==" + listSaveAmbulanceYes.size());
        Assert.assertEquals(SumAmbulance, listSaveAmbulanceYes.size());

        ArrayList<String> ServicesStep5 = new ArrayList<>();
        for (WebElement Service : saveServices) {
            moveMouse(Service);
            ServicesStep5.add(Service.getText().replace("\n", " "));
        }
        compareArrayListAndArrayList(SelectedServices, ServicesStep5, SelectedServices.size());

        compareWebelementAndString(saveEducation, Education);
        compareWebelementAndString(saveEmailInfo, EmailInfo);

        moveMouse(savePhoneInfo);
        compareStringAndString("+ " + PhoneInfo.charAt(0) + " (" + PhoneInfo.charAt(1) + PhoneInfo.charAt(2) +
                PhoneInfo.charAt(3) + ") " + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) + PhoneInfo.charAt(6) + "-" +
                PhoneInfo.charAt(7) + PhoneInfo.charAt(8) + "-" + PhoneInfo.charAt(9) + PhoneInfo.charAt(10), SavePhoneInfo);

        compareWebelementAndString(saveInstagram, Instagram);
        compareWebelementAndString(saveVk, Vk);
        compareWebelementAndString(saveWhatsapp, Whatsapp);
        compareWebelementAndString(saveViber, Viber);
        compareWebelementAndString(saveFacebook, Facebook);
        compareWebelementAndString(saveOther, Other);
        click(buttonFinish);
    }

    public void createUserStepSix(String Login, String Password, String Email, String Phone, String SecondName,
                                  String FirstName, String MiddleName, String Position, String Education,
                                  String EmailInfo, String PhoneInfo, String Instagram, String Vk, String Whatsapp,
                                  String Viber, String Facebook, String Other) {
        click(buttonUserList);
        preloader();
        moveToProfileUser(SecondName);
        waitE_visibilityOfAndGettext(profLabelOnline);
        waitE_visibilityOfAndGettext(profPosition.get(0));
        waitVisibility(listProfSpeciality.get(0));

        String Fio = SecondName + " " + FirstName + " " + MiddleName;
        pr(Fio + " = " + profFio.getText());
        if (MiddleName.equals("")) {
            Assert.assertEquals(Fio, profFio.getText() + " ");
        } else {
            Assert.assertEquals(Fio, profFio.getText());
        }

        ArrayList<String> ListProfSpeciality = new ArrayList<>();
        for (WebElement profSpeciality : listProfSpeciality) {
            pr(profSpeciality.getText());
            if (!profSpeciality.getText().equals("") || !profSpeciality.getText().equals(" ") ||
                    !profSpeciality.getText().equals(",")) {
                ListProfSpeciality.add(profSpeciality.getText().replace(",", ""));
            }
        }

        compareArrayListAndArrayList(SelectedSpecialtys, ListProfSpeciality, SelectedSpecialtys.size());

        ArrayList<String> ListProfNameWorkspace = new ArrayList<>();
        for (WebElement Workspace : listProfNameWorkspace) {
            moveMouse(Workspace);
            ListProfNameWorkspace.add(Workspace.getText());
        }

        sleep(1000);
        compareArrayListAndArrayList(SelectedOrg, ListProfNameWorkspace, SelectedOrg.size());

        ArrayList<String> ListProfPositions = new ArrayList<>();
        for (WebElement OnePosition : listProfPosition) {
            moveMouse(OnePosition);
            ListProfPositions.add(OnePosition.getText());
        }
        compareArrayListAndArrayList(SelectedPositions, ListProfPositions, SelectedPositions.size());

        ArrayList<String> ListProfRoles = new ArrayList<>();
        for (
                WebElement Role : listProfRole) {
            moveMouse(Role);
            pr("Добавляем в список ролей в карточке: " + Role.getText());
            ListProfRoles.add(Role.getText());
        }
        compareArrayListAndArrayList(SelectedRoles, ListProfRoles, ListProfRoles.size());

        pr(SumAmbulance + "==" + listProfAmbulanceYes.size());
        Assert.assertTrue(SumAmbulance == listProfAmbulanceYes.size());

        waitVisibility(listProfStatus.get(0));
        compareWebelementAndString(profEducation, Education);
        ArrayList<String> ListProfServices = new ArrayList<>();
        for (
                WebElement Service : listProfServices) {
            moveMouse(Service);
            buttonDown(2);
            ListProfServices.add(Service.getText().replace("\n", " "));
        }
        compareArrayListAndArrayList(SelectedServices, ListProfServices, ListProfServices.size());

        click(userinfoname);
        scrollHomePage();
        waitVisibility(photoProfUser);
        compareWebelementAndString(profEmail, EmailInfo);
        compareWebelementAndString(profContPhone, "+ " + PhoneInfo.charAt(0) + " (" + PhoneInfo.charAt(1) +
                PhoneInfo.charAt(2) + PhoneInfo.charAt(3) + ") " + PhoneInfo.charAt(4) + PhoneInfo.charAt(5) +
                PhoneInfo.charAt(6) + "-" + PhoneInfo.charAt(7) + PhoneInfo.charAt(8) + "-" + PhoneInfo.charAt(9) +
                PhoneInfo.charAt(10));

        compareWebelementAndString(profInstagram, Instagram);
        compareWebelementAndString(saveVk, Vk);
        compareWebelementAndString(profWhatsapp, Whatsapp);
        compareWebelementAndString(profViber, Viber);
        compareWebelementAndString(profFacebook, Facebook);
        compareWebelementAndString(profOther, Other);
        compareWebelementAndString(profAccLogin, Login);
        compareWebelementAndString(profAccEmail, Email);
        compareWebelementAndString(profAccPhone, "+ " + Phone.charAt(0) + " (" + Phone.charAt(1) +
                Phone.charAt(2) + Phone.charAt(3) + ") " + Phone.charAt(4) + Phone.charAt(5) + Phone.charAt(6) +
                "-" + Phone.charAt(7) + Phone.charAt(8) + "-" + Phone.charAt(9) + Phone.charAt(10));

        moveMouse(profSystemID);
        Assert.assertTrue(profSystemID.isEnabled());
        moveMouse(profSystemStatus);
        Assert.assertTrue(profSystemStatus.isEnabled());
        moveMouse(profQR);
        Assert.assertTrue(profQR.isEnabled());
    }
}



