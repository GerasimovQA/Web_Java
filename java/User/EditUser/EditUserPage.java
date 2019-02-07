package User.EditUser;

import Global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EditUserPage extends GlobalPage {

    EditUserPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private int u = 0;

    //--------------------------------Учетная запись------------------------------------------
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
    @FindBy(xpath = "//div[2]/div[2]/button/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveStatus;

    //    Кнопка ОТМЕНА СТАТУСА
    @FindBy(xpath = "//div[2]/button/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelStatus;

    //-----------------------------------------------------------------------------------------------------------
// Место работы и специальность
    //    Ссылка "добавить" место работы
    @FindBy(xpath = "//span[contains (text(), \"Добавить место работы\")]")
    public WebElement linkAddWorkplaces;

    //    Ссылка "Удалить" место работы
    @FindBy(xpath = "//span[contains (text(), \"Удалить место работы\")]")
    public WebElement linkDeleteWorkplaces;

    //    Кнопка подтверждения удаления места работы
    @FindBy(xpath = "//span[contains (text(), \"Удалить место работы?\")]/../../..//" +
            "button//span[contains (text(), \"Удалить\")]")
    public WebElement linkConfirmDeleteWorkplaces;

    //    Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(), \"Структура организации\")]")
    public WebElement structureOrganizations;

    //    Поле поиска организации
    @FindBy(xpath = "//div[@class=\"tree-select__search el-input el-input--prefix\"]/input[@placeholder=\"Поиск\"]")
    public WebElement inputSearchOrganizations;

    //   Буллет Найденной в поле поиска организация "3 подразделение Степашина младшего"
    @FindBy(xpath = "//span[contains (text(), \"3 подразделение Степашина младшего\")]/../label/span")
    public WebElement bulletStepashin3;

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
    @FindBy(xpath = "//label[text()='Выберите или впишите должность *']/../../..//input")
    public WebElement inputPosition;

    //   Поле ввода "Роль сотрудника"
    @FindBy(xpath = "//label[text()='Роль *']/../../..//span[@class=\"el-input__suffix-inner\"]")
    public WebElement inputRoleEmployee;

    //   Пункт "Специалист" в поле ввода "Роль сотрудника"
    @FindBy(xpath = "//span[text() = \"Специалист\"]")
    public WebElement roleEmployeeSpecialist;

    //   Крестик удаления выбранной роли
    @FindBy(xpath = "//label[contains (text(),\"Роль *\")]/../../..//span[@class=\"el-tag el-tag--info el-tag--small\"]//i")
    public List<WebElement> iconDeleteRole;

    //   Кнопка "Добавить" место работы сотрудника
    @FindBy(xpath = "//span[contains (text(), 'Добавить место работы')]")
    public WebElement buttonAddWorkplace;

    //   Кнопка "Сохранить" место работы сотрудника
    @FindBy(xpath = "//span[contains (text(), \"Сохранить\")]")
    public List<WebElement> buttonSaveWorkplace;

    //   Кнопка "Добавить" введенное место работы сотрудника
    @FindBy(xpath = "//div[@class=\"edit-workplace__section\"]//span[contains (text(), \"Добавить\")]")
    public WebElement buttonAddNewWorkplace;

    //   Сохраненное значение "Место работы"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Место\")" +
            "]/following-sibling::div")
    public List<WebElement> workplace;

    //   Сохраненное значение "Должность"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Должность\")" +
            "]/following-sibling::div")
    public List<WebElement> post;

    //   Сохраненное значение "Роль"
    @FindBy(xpath = "//div[@class=\"edit-user-block__data-row-label\"][contains (text(), \"Роль\")" +
            "]/following-sibling::div")
    public List<WebElement> role;

    //   Выпадающий список "Специальность"
    @FindBy(xpath = "//div[contains (text(), \"Специальности\")]/..//i[@class=\"tree-select__caret el-icon-arrow-up\"]")
    public WebElement listSpecialty;

    //  Пункт "Медицинские специальности" в выпадающем списке "Специальности"
    @FindBy(xpath = "//span[contains (text(), \"Медицинские специальности\")]")
    public WebElement medicalSpecialty;

    //  Чек-бокс "Стоматолог" в выпадающем списке "Специальность"
    @FindBy(xpath = "//span[contains (text(),\"Стоматолог\")]/../label")
    public WebElement specialtyDentist;

    //  Чек-бокс "Ведет амбулаторный приём""
    @FindBy(xpath = "//span[contains (text(),\"Ведет амбулаторный приём\")]/../" +
            "span[@aria-checked=\"mixed\"]/span[@class=\"el-checkbox__inner\"]")
    public WebElement checkBoxAmbulance;

    //   Сохраненное значение "Амбулаторный прием"
    @FindBy(xpath = "//div[contains (text(),\"Амбулаторный приём\")]/following-sibling::div/span")
    public WebElement ambulance;

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

    //    Кнопка СОХРАНИТЬ ФОТО
    @FindBy(xpath = "//div[2]/div/div/button/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSavePhotoProfile;

    //    Кнопка ОТМЕНИТЬ СОХРАНИЕ ФОТО
    @FindBy(xpath = "//div[contains (text(), \"Отмена\")]")
    public WebElement buttonSavePhotoCancelProfile;

    //    Образование, достижения, регалии
    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[1]")
    public WebElement educationProfile;

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

    //    Кнопка СОХРАНИТЬ Способы связи
    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveCommunicationMethodsProfile;

    //    Кнопка ОТМЕНИТЬ СОХРАНЕНИЕ Способов связи
    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelCommunicationMethodsProfile;

    public void editServices(String Login, String Password, String Email, String Phone, String Status,
                             String SecondName, String FirstName, String MiddleName, String Superuser,
                             String Depart, String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                             String Whatsapp, String Viber, String Facebook, String Other,
                             String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        int i = -1;
        sleep(4000);

        switch (SecondName) {
            case "Кардашьян":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(2000);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        click(checkServices3.get(i));
                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().
                                replace("\n", " "));

                        waitVisibility(listCostSelectServicesAddUser.get(i));
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
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() &
                        listIconsDeleteSelectServicesAddUser.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                break;

            case "Киров":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(2000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
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
                        } else {
                            System.out.println("Услуги не совпали");
                        }
                    }
                    click(userinfoname);
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds +
                            " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
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

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                click(userinfoname);
                sleep(1000);
                System.out.println((listStringSelectedServicesInTree.get(i).getText().replace("\n", " ")));
                System.out.println("Unselect last service");
                SelectedServices.remove(listStringSelectedServicesInTree.get(i).getText().replace("\n", " "));
                click(checkServices3.get(i));
                click(userinfoname);
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
                        } else {
                            System.out.println("Услуги не совпали");
                        }
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
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(2000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
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
                        } else {
                            System.out.println("Услуги не совпали");
                        }
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

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                click(userinfoname);
                click(userinfoname);
                sleep(1000);
                System.out.println(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
                System.out.println("Delete one service");
                SelectedServices.remove(stringServiceDeletedInListServices.get(i).getText().replace("\n", " "));
                String DeletedService = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService);
                waitE_ClickableAndClickAndUp(listIconsDeleteSelectServicesAddUser.get(i));
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
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        click(listSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                click(checkServices3.get(0));
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
                        } else {
                            System.out.println("Услуги не совпали");
                        }
                    }
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                Assert.assertEquals(listNameSelectServicesAddUser.size(), listIconsDeleteSelectServicesAddUser.size());

                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(textServices3.size(), checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Empty);
                break;

            case "Кондртьев":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                click(textServices2.get(1));
                sleep(2000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    click(checkServices3.get(i));
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
                        } else {
                            System.out.println("Услуги не совпали");
                        }
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

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds +
                        " or " + CheckedAndFocus);
                Assert.assertTrue(checkServices2.get(1).getAttribute("class").equals(Checkeds) ||
                        checkServices2.get(1).getAttribute("class").equals(CheckedAndFocus));
                click(userinfoname);
                click(userinfoname);
                sleep(1000);

                System.out.println("Delete one service");
                SelectedServices.remove(listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService2);
                waitE_ClickableAndClickAndUp(listIconsDeleteSelectServicesAddUser.get(i));
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
                click(userinfoname);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().
                                replace("\n", " "));
                        waitE_ClickableAndClickAndUp(listSelectedCheckBoxInTree.get(b));
                        sleep(1000);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Удалили все услуги в списке");
                }

                click(checkServices3.get(5));
                click(userinfoname);
                sleep(1000);
                SelectedServices.add(listStringSelectedServicesInTree.get(0).getText().replace("\n", " "));

                for (WebElement SelectService : listStringSelectServicesAddUser) {
                    i = 0;
                    u = 0;
                    System.out.println("Service №" + i);

                    System.out.println(listSelectedCheckBoxInTree.get(i).getText().
                            replace("\n", " ") + " = " + SelectService.getText().
                            replace("\n", " "));
                    if (listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ").equals(
                            SelectService.getText().replace("\n", " "))) {
                        u = u + 1;
                        System.out.println("!+Service find in list selected services+!");
                        break;
                    } else {
                        System.out.println("Услуги не совпали");
                    }
                }

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size());
                Assert.assertEquals(listNameSelectServicesAddUser.size(), listIconsDeleteSelectServicesAddUser.size());

                System.out.println(textServices3.size() + " = " + checkServices3.size());
                Assert.assertEquals(textServices3.size(), checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Empty);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Empty);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);
                break;

            case "Курочкин":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                click(textServices2.get(1));
                sleep(2000);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        click(Service);
                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).
                                getText().replace("\n", " "));

                        waitVisibility(listCostSelectServicesAddUser.get(i));
                        System.out.println(stringTreeServices3.get(i).getText().replace("\n", " ") + " = " +
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                        Assert.assertEquals(stringTreeServices3.get(i).getText().replace("\n", " "),
                                listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds +
                                " or " + CheckedAndFocus);
                        Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                                checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    } else {
                        try {
                            System.out.println("Check-box " + i + " = " + checkServices3.get(i).getAttribute("class") +
                                    " = " + Empty);
                            Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Empty);

                            System.out.println(textServices3.get(i).getText() + " = " +
                                    listNameSelectServicesAddUser.get(i).getText() +
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
        click(buttonSaveChangesProfile);
//Проверка сохраненных данных
        waitE_ClickableAndClickAndUp(linkFIO);
        waitVisibility(profQR);
        waitVisibility(profFio);
        waitTextPresent(profWhatsapp, Whatsapp);
        preloader();
        String Fio = SecondName + " " + FirstName + " " + MiddleName;
        System.out.println(Fio + " = " + profFio.getText());
        if (MiddleName.equals("")) {
            Assert.assertEquals(Fio, profFio.getText() + " ");
        } else {
            Assert.assertEquals(Fio, profFio.getText());
        }

        compareWebelementAndString(profEducation, Regalia);
        ArrayList<String> ListProfServices = new ArrayList<>();
        for (
                WebElement Service : listProfServices) {
            moveMouse(Service);
            ListProfServices.add(Service.getText().replace("\n", " "));
        }

        compareArrayListAndArrayList(SelectedServices, ListProfServices, SelectedServices.size());
        compareStringAndWebelement(EmailCont, profEmail);
        compareStringAndWebelement(PhoneCont, profContPhone);
        compareStringAndWebelement(Instagram, profInstagram);
        compareStringAndWebelement(Vk, profVk);
        compareStringAndWebelement(Whatsapp, profWhatsapp);
        compareStringAndWebelement(Viber, profViber);
        compareStringAndWebelement(Facebook, profFacebook);
        compareStringAndWebelement(Other, profOther);
        compareStringAndWebelement(Login, profAccLogin);
        compareStringAndWebelement(Email, profAccEmail);
        compareStringAndWebelement(Phone, profAccPhone);

        Assert.assertTrue(profSystemID.isEnabled());
        Assert.assertTrue(profSystemStatus.isEnabled());
        Assert.assertTrue(profQR.isEnabled());
    }


}



