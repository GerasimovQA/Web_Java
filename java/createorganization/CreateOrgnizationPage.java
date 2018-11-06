package createorganization;

import global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateOrgnizationPage extends GlobalPage {

    public CreateOrgnizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int u = 0;

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
    @FindBy(xpath = "//div[1]/button/span[contains (text(), \"Добавить подразделение\")]")
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

    // Сохраняемые Услуги
    @FindBy(xpath = "//div[@class=\"dep-create-form__section-table-row\"]")
    public List<WebElement> saveServices;

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


    public void stepTwo(String OrganizationNameAbbr, String CheckedAndFocus, String Checkeds, String Indeterminate,
                        String Empty) {
        int i = -1;

        switch (OrganizationNameAbbr) {
            case "Больница №1":
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
                System.out.println(SelectedServices);

                System.out.println("Icon DELETE is enabled and size = 1");
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() & listIconsDeleteSelectServicesAddUser.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                break;

            case "Больница №2":
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

            case "Больница №3":
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

            case "Больница №4":
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
                waitE_ClickableAndClick(userinfoname);
                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + listStringSelectedServicesInTree.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &&
                        listNameSelectServicesAddUser.size() == listStringSelectedServicesInTree.size());
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

            case "Мед.орг. №5":
                waitE_ClickableAndClick(checkServices1.get(0));
                sleep(1000);
                for (WebElement Service : stringTreeServices3) {
                    i = i + 1;
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
                    System.out.println(checkServices1.get(0).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices1.get(0).getAttribute("class"), CheckedAndFocus);

                    System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
                    System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Checkeds);

                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Checkeds);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
        }


//        for (WebElement Str : listStringSelectServicesAddUser) {
//            SelectedServices.add(Str.getText().replace("\n", " "));
//        }
        waitE_ClickableAndClick(buttonAddOrganizationStep2);
    }
}




