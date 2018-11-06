package edituser;

import global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EditUserPage extends GlobalPage {

    public EditUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int u = 0;

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
    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSavePassword;

    //    Кнопка ОТМЕНА ПАРОЛЯ
    @FindBy(xpath = "//div[contains(text(),\"Пароль\")]/..//span[contains (text(), \"Отмена\")]")
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
    @FindBy(xpath = "//span[contains (text(), \"Добавить место работы\")]")
    public WebElement linkAddWorkplaces;

    //    Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(), \"Структура организации\")]")
    public WebElement structureOrganizations;

    //   Пункт головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]")
    public WebElement headOrganization;

    //       Буллет головной организации "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский
    // Университет)"
    @FindBy(xpath = "//span[contains (text(), \"ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
            "Университет)\")]/../label/span/span")
    public WebElement bulletHeadOrganization;

    //   Поле ввода "Должность"
    @FindBy(xpath = "//input[@placeholder=\"Должность\"]")
    public WebElement inputPosition;

    //   Поле ввода "Роль сотрудника"
    @FindBy(xpath = "//input[@placeholder=\"Роль сотрудника\"]")
    public WebElement inputRoleEmployee;

    //   Пункт "Специалист" в поле ввода "Роль сотрудника"
    @FindBy(xpath = "//span[text() = \"Специалист\"]")
    public WebElement roleEmployeeSpecialist;

    //   Кнопка "Сохранить" место работы сотрудника
    @FindBy(xpath = "//div[contains (text(), \"Добавить место работы\")]/following-sibling::div/button/span[contains " +
            "(text(), \"Добавить\")]")
    public WebElement buttonSaveWorkplace;

    //   Сохраненное значение "Место работы"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Место\")" +
            "]/following-sibling::div")
    public WebElement workplace;

    //   Сохраненное значение "Должность"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Должность\")" +
            "]/following-sibling::div")
    public WebElement post;

    //   Сохраненное значение "Роль"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Роль\")" +
            "]/following-sibling::div")
    public WebElement role;

    //    Ссылка "изменить" специальность
    @FindBy(xpath = "//div[contains (text(), \"Специальности\")]/following-sibling::div[2]")
    public WebElement linkEditSpecialty;

    //   Выпадающий список "Специальность"
    @FindBy(xpath = "//div[contains (text(), \"Специальности\")][@class=\"tree-select__placeholder\"]")
    public WebElement listSpecialty;

    //  Пункт "Медицинские специальности" в выпадающем списке "Специальности"
    @FindBy(xpath = "//span[contains (text(), \"Медицинские специальности\")]")
    public WebElement medicalSpecialty;

    //  Чек-бокс "Стоматолог" в выпадающем списке "Специальность"
    @FindBy(xpath = "//span[contains (text(),\"Стоматолог\")]/../label")
    public WebElement specialtyDentist;

    //   Кнопка "Сохранить" место работы сотрудника
    @FindBy(xpath = "//div[@class=\"edit-specialities__item\"]/following-sibling::button/span[contains (text(), " +
            "\"Сохранить\")]")
    public WebElement buttonSaveSpecialty;

    //   Сохраненное значение "Специальности"
    @FindBy(xpath = "//div[@class=\"edit-user-block__title\"][contains (text(), \"Специальности\")" +
            "]/following-sibling::div/div[@class=\"specialty-in-row\"]/span")
    public List<WebElement> specialty;

    //   Кнопка "Сохранить" специальности при редактировании
    @FindBy(xpath = "//div[@class=\"tree-select__input el-popover__reference\"]/../../../../button/span[contains " +
            "(text(),\"Сохранить\")]")
    public WebElement linkSaveSpeciality;
//-----------------------------------------------------------------------------------------------------------
// Данные специалиста


    //    ФОТО юзера
    @FindBy(xpath = "//div[contains (text(), \"Фото\")]/following-sibling::div[1]//img")
    public WebElement photoProfile;

    //    Ссылка ИЗМЕНИТЬ ФОТО
    @FindBy(xpath = "//div[@class='profile-userpic__photo userpic userpic--medium']/." +
            ".//following-sibling::div/div[contains (text(), \"Изменить\")]")
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
    @FindBy(xpath = "//*[@class=\"el-textarea__inner\"]")
    public WebElement inputEducation;

    //    Кнопка СОХРАНИТЬ ОБРАЗОВАНИЕ
    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveEducation;

    //    Кнопка ОТМЕНА СОХРАНЕНИЯ ОБРАЗОВАНИЯ
    @FindBy(xpath = "//div[contains(text(),\"Образование, достижения, регалии\")" +
            "]/following-sibling::div//span[contains(text(),\"Отмена\")]")
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
    @FindBy(xpath = "//div[contains (text(), \"Способы связи\")]/following-sibling::div[contains (text(), " +
            "\"Изменить\")]")
    public WebElement linkEditCommunicationMethodsProfile;

    //    Кнопка СОХРАНИТЬ Способы связи
    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveCommunicationMethodsProfile;

    //    Кнопка ОТМЕНИТЬ СОХРАНЕНИЕ Способов связи
    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelCommunicationMethodsProfile;

    public void editServices(String Login, String Password, String Email, String Phone, String Status,
                             String SecondName, String FirstName, String MiddleName, String Superuser,
                             String Depart, String Ref, String Post, String Role, String Specialities,
                             String Label, String ID, String LabelChild, String Cost, String IDchild,
                             String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                             String Whatsapp, String Viber, String Facebook, String Other,
                             String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        int i = -1;
        sleep(4000);

        switch (SecondName) {
            case "Кардашьян":
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

            case "Киров":
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
                        } else {System.out.println("Услуги не совпали");                       }
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

            case "Ковалев":
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

            case "Кондртьев":
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

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
                break;

            case "Курочкин":
                waitE_ClickableAndClick(textServices1.get(0));
                sleep(1000);
                waitE_ClickableAndClick(textServices2.get(0));
                waitE_ClickableAndClick(textServices2.get(1));
                sleep(1000);
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

        waitE_ClickableAndClick(buttonSaveChangesProfile);
        waitE_ClickableAndClick(linkFIO);

        waitE_visibilityOf(profQR);
        waitE_visibilityOf(profFio);
        String Fio = SecondName + " " + FirstName + " " + MiddleName;
        System.out.println(Fio + " = " + profFio.getText());
        if (MiddleName.equals("")) {
            Assert.assertEquals(Fio, profFio.getText() + " ");
        } else {
            Assert.assertEquals(Fio, profFio.getText());
        }

        System.out.println(Regalia + " = " + profEducation.getText());
        Assert.assertEquals(Regalia, profEducation.getText());

        ArrayList<String> ListProfServices = new ArrayList<>();
        for (
                WebElement Service : listProfServices) {
            ListProfServices.add(Service.getText().replace("\n", " "));
        }
        System.out.println("Сравниваем услуги :" + SelectedServices.equals(ListProfServices));
        Assert.assertEquals(SelectedServices, ListProfServices);

        System.out.println(EmailCont + " = " + profEmail.getText());
        Assert.assertEquals(EmailCont, profEmail.getText());

        System.out.println(PhoneCont + " = " + profContPhone.getText());
        Assert.assertEquals(profContPhone.getText(), profContPhone.getText());

        Assert.assertEquals(Instagram, profInstagram.getText());
        System.out.println(Instagram + " = " + profInstagram.getText());

        Assert.assertEquals(Vk, profVk.getText());
        System.out.println(Vk + " = " + profVk.getText());

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

        System.out.println(Phone + " = " + profAccPhone.getText());
        Assert.assertEquals(Phone, profAccPhone.getText());

        Assert.assertTrue(profSystemID.isEnabled());
        Assert.assertTrue(profSystemStatus.isEnabled());
        Assert.assertTrue(profQR.isEnabled());
    }


}



