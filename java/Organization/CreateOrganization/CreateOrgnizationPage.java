package Organization.CreateOrganization;

import Global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateOrgnizationPage extends GlobalPage {

    public CreateOrgnizationPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private int u = 0;

    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
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
    @FindBy(xpath = "//div[@class=\"userpic userpic--small\"]")
    public WebElement photoAssignedDirecor;

    //   ФИО назначенного руководителя организации
    @FindBy(xpath = "//div[@class=\"dep-create-form-chief__name\"]")
    public WebElement FIOAssignedDirecor;


    //Поле ввода "Профиль" организации
    @FindBy(css = ".el-input__inner[placeholder=\"Профиль\"]")
    public WebElement inputOrganizationProfile;

    //Кнопка "Добавить подразделение" Шаг1
    @FindBy(xpath = "(//div[@class=\"dep-create__nav-buttons\"]/button/span[contains (text(), \"Добавить " +
            "подразделение\")])[1]")
    public WebElement buttonAddOrganizationStep1;

//-------------------------------------------------------------------------------------------------------------------

    //Кнопка "Добавить подразделение" Шаг2
    @FindBy(xpath = "//div[2]/button[3]/span[contains (text(), \"Добавить подразделение\")]")
    public WebElement buttonAddOrganizationStep2;

    //Кнопка "Пропустить" Шаг2
    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Пропустить\")]")
    public WebElement buttonSkipStep2;

//-------------------------------------------------------------------------------------------------------------------

    // Шаг 3 - Дополнительная информация
    // Поле ввода "Описание" организации
    @FindBy(xpath = "//div[@class=\"el-textarea\"]/*[@placeholder=\"Описание\"]")
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
    @FindBy(css = ".el-input__inner[placeholder=\"Адрес *\"]")
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
    @FindBy(xpath = "(//div[@class=\"dep-create__nav-buttons\"]//span[contains (text(), \"Добавить подразделение\")])[2]")
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

    // Сохраняемые Услуги
    @FindBy(xpath = "//div[@class=\"dep-create-form__section-table-row\"]")
    public List<WebElement> saveServices;

    //Проверяем адрес организации
    @FindBy(xpath = "//div[contains (text(), \"Адрес\")]/following-sibling::div")
    public WebElement checkAdressOrganization;

    //Проверяем Сайт организации
    @FindBy(xpath = "//div[contains (text(), \"Сайт\")]/following-sibling::div")
    public WebElement checkSiteOrganization;

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

    public void stepTwo(String OrganizationNameAbbr, String CheckedAndFocus, String Checkeds, String Indeterminate,
                        String Empty) {
        sleep(2000);
        int i = -1;

        switch (OrganizationNameAbbr) {
            case "Больница №1":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
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

                        System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                        Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                                checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
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
                System.out.println("Icon DELETE is enabled and size = 1");
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() &
                        listIconsDeleteSelectServicesAddUser.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                break;

            case "Больница №2":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
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

                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
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

            case "Больница №3":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
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

                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));

                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
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
                System.out.println(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
                System.out.println("Delete one service");
                String DeletedService = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService);
                click(listIconsDeleteSelectServicesAddUser.get(i));
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

            case "Больница №4":
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

                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds + " or " +
                            "" + CheckedAndFocus);
                    Assert.assertTrue(checkServices3.get(i).getAttribute("class").equals(Checkeds) ||
                            checkServices3.get(i).getAttribute("class").equals(CheckedAndFocus));
                }
                click(userinfoname);
                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + listStringSelectedServicesInTree.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &&
                        listNameSelectServicesAddUser.size() == listStringSelectedServicesInTree.size());
                sleep(1000);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds + " or " + CheckedAndFocus);
                Assert.assertTrue(checkServices2.get(1).getAttribute("class").equals(Checkeds) ||
                        checkServices2.get(1).getAttribute("class").equals(CheckedAndFocus));

                click(userinfoname);
                sleep(1000);

                System.out.println("Delete one service");
                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService2);
                click(listIconsDeleteSelectServicesAddUser.get(i));
                sleep(1000);
                try {
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
                sleep(300);
                moveMouse(checkServices2.get(0));

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
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

                for (WebElement SelectService : listStringSelectServicesAddUser) {
                    i = 0;
                    u = 0;
                    System.out.println("Service №" + i);

                    System.out.println(listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ")
                            + " = " + SelectService.getText().replace("\n", " "));
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

            case "Мед.орг. №5":
                click(checkServices1.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
                    for (WebElement SelectService : listStringSelectServicesAddUser) {
                        u = 0;
                        moveMouse(Service);
                        System.out.println("Service №" + i);

                        System.out.println(Service.getText().replace("\n", " ") + " = " +
                                SelectService.getText().replace("\n", " "));
                        if (Service.getText().replace("\n", " ").equals(
                                SelectService.getText().replace("\n", " "))) {
                            u = u + 1;
                            System.out.println("!+Service find in list selected services+!");
                            Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                            break;
                        } else {
                            System.out.println("Услуги не совпали");
                        }
                    }
                }
                scrollHomePage();

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Checkeds + " or " +
                        "" + CheckedAndFocus);
                Assert.assertTrue(checkServices1.get(0).getAttribute("class").equals(Checkeds) ||
                        checkServices1.get(0).getAttribute("class").equals(CheckedAndFocus));

                System.out.println("Проверяем чекбоксы второго уровня = " + checkServices2.size());
                for (WebElement Services : checkServices2) {
                    System.out.println(Services.getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(Services.getAttribute("class"), Checkeds);
                }
                System.out.println("Проверяем чекбоксы третьего уровня");
                for (WebElement Services : checkServices3) {
                    System.out.println(Services.getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(Services.getAttribute("class"), Checkeds);
                }
        }

        for (WebElement Str : listStringSelectServicesAddUser) {
            moveMouse(Str);
            buttonDown(2);
            SelectedServices.add(Str.getText().replace("\n", " "));
        }

        click(buttonAddOrganizationStep2);
    }

    public WebElement webElementOtherName(String OtherName) {
        WebElement OtherN = driver.findElement(By.xpath("//div[contains (text(), \"" + OtherName + "\")]"));
        return OtherN;
    }

    public WebElement webElementOtherValue(String OtherValue) {
        WebElement OtherV = driver.findElement(By.xpath("//div[contains (text(), \"" + OtherValue + "\")]"));
        return OtherV;
    }
}





