package Integration;

import Global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class IntegrationPage extends GlobalPage {

    public IntegrationPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int u = 0;

    public ArrayList<String> ListServices = new ArrayList<>();

    // Кнопка "Сохранить"
    @FindBy(xpath = "//span[text()=\"Сохранить\"]")
    public WebElement buttonSave;

    // Кнопка "Отмена"
    @FindBy(xpath = "//span[text()=\"Отмена\"]")
    public WebElement buttonCancel;

    // Ссылка "Услуги"
    @FindBy(xpath = "//*[@id=\"tab-second\"][contains (text(),\"Услуги\")]")
    public WebElement linkServices;

    // Ссылка "Дополнительная информация"
    @FindBy(xpath = "//*[@id=\"tab-third\"][contains (text(),\"Дополнительная информация\")]")
    public WebElement linkAdditionalInformation;


    //    ---------------------------------------------Данные организации--------------------------------------------
    // Название организации
    @FindBy(xpath = "//div[contains (text(),\"Название организации\")]/following-sibling::div[1]")
    public WebElement nameOrganization;

    // Ссылка "Изменить" Название организации
    @FindBy(xpath = "//div[contains (text(),\"Название организации\")]/following-sibling::div[2]")
    public WebElement linkChangeNameOrganization;

    // Поле ввода "Название организации"
    @FindBy(css = "input[placeholder=\"Название организации\"]")
    public WebElement inputNameOrganization;

    // Сокращенное название организации
    @FindBy(xpath = "//div[contains (text(),\"Сокращенное название\")]/following-sibling::div[1]")
    public WebElement abbrNameOrganization;

    // Ссылка "Изменить" сокращенное название организации
    @FindBy(xpath = "//div[contains (text(),\"Сокращенное название\")]/following-sibling::div[2]")
    public WebElement linkChangeAbbrNameOrganization;

    // Поле ввода "Сокр. Название организации"
    @FindBy(css = "input[placeholder=\"Название организации\"]")
    public WebElement inputAbbrNameOrganization;

    // Руководитель организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div[1]")
    public WebElement directorOrganization;

    // Ссылка "Изменить" руководителя организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div[2]")
    public WebElement linkChangeDirectorOrganization;

    // Ссылка "Поиск сотрудника"
    @FindBy(xpath = "//span[contains (text(),\"Поиск сотрудника\")]")
    public WebElement searchDirectorOrganization;

    // Измененный руководитель организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div/span")
    public WebElement newDirectorOrganization;

    // Организация входит в состав
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div[1]")
    public WebElement headOrganization;

    // Ссылка "Изменить" организация входит в состав
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div[2]")
    public WebElement linkChangeHeadOrganization;

    // Кнопка "Сохранить" головную организацию
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div/button/span[contains (text()" +
            ",\"Сохранить\")]")
    public WebElement buttonSaveHeadOrganization;

    // Условия оказания помощи
    @FindBy(xpath = "//div[contains (text(),\"Условия оказания помощи\")]/following-sibling::div[1]")
    public WebElement conditionsOrganization;

    // Ссылка "Изменить" Условия оказания помощи организации
    @FindBy(xpath = "//div[contains (text(),\"Условия оказания помощи\")]/following-sibling::div[2]")
    public WebElement linkChangeConditionsOrganization;

    // Основной профиль организации
    @FindBy(xpath = "//div[contains (text(),\"Основной профиль\")]/following-sibling::div[1]")
    public WebElement profileOrganization;

    // Ссылка "Изменить" основной профиль организации
    @FindBy(xpath = "//div[contains (text(),\"Основной профиль\")]/following-sibling::div[2]")
    public WebElement linkChangeProfileOrganization;

    // Поле ввода "Основной профиль" организации
    @FindBy(css = "input[placeholder=\"Основной профиль\"]")
    public WebElement inputProfileOrganization;

    // Ссылка "Удалить" подразделеие
    @FindBy(xpath = "//span[contains (text(),\"Удалить подразделение\")]")
    public WebElement linkDeleteOrganization;

    // Кнока "Удалить"
    @FindBy(xpath = "//button[2]/span[contains (text(),\"Удалить\")]")
    public WebElement buttonDelete;

    // Кнока "Отмена"
    @FindBy(xpath = "//button[1]/span[contains (text(),\"Отмена\")]")
    public WebElement buttonCancelDelete;

    //-------------------------___--Услуги------------------------------------------------------------------

    // Значение услуги (Не указано)
    @FindBy(xpath = "//div[@class=\"edit-user-block__title\"][contains (text(),\"Услуги\")]/following-sibling::div[1]")
    public WebElement services1;

    // Ссылка "Изменить" услуги (Не указано)
    @FindBy(xpath = "//div[@class=\"info-block__title\"][contains (text(),\"Оказываемые услуги \")]/../../../." +
            "./div/button/span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeServices;

    // Кнопка "Сохранить изменения"
    @FindBy(xpath = "//span[text()=\"Сохранить изменения\"]")
    public WebElement buttonSaveChanges;


    // Ссылка на карточку организации(название организации)
    @FindBy(xpath = "//div[@class=\"page-title\"]/h1[contains(text(),\"Редактировать организацию:\")]/a")
    public WebElement linkToProfileOrg;


    //-------------------------___--Дополнительная информация-----------------------------------------------
    // Описание организации
    @FindBy(xpath = "//div[contains (text(),\"Описание подразделения\")]/following-sibling::div[1]")
    public WebElement descriptionOrganization;

    // Ссылка "Изменить" описание организации
    @FindBy(xpath = "//div[contains (text(),\"Описание подразделения\")]/following-sibling::div[2]")
    public WebElement linkChangeDescriptionOrganization;

    // Поле ввода "Описание организации"
    @FindBy(css = "div>[placeholder=\"Описание подразделения\"]")
    public WebElement inputDescriptionOrganization;

    // Назначение телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div[1]")
    public List<WebElement> appointmentPhoneOrganization;

    // Номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div[2]")
    public List<WebElement> numberPhoneOrganization;

    // Ссылка "Изменить" телефон
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/span[contains (text(),\"Изменить\")]")
    public WebElement linkChangePhoneOrganization;

    // Поле ввода "Назначение телефона"
    @FindBy(css = "div>[placeholder=\"Назначение телефона\"]")
    public List<WebElement> inputAppointmentPhone;

    // Поле ввода "Номер телефона"
    @FindBy(css = "div>[placeholder=\"Телефон\"]")
    public List<WebElement> inputNumberPhone;

    // Ссылка "Добавить" номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div//span[contains (text()," +
            "\"Добавить\")]")
    public WebElement linkAddPhone;

    // Ссылка "Удалить" номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div//span[contains (text(),\"Удалить\")]")
    public List<WebElement> linkDeletePhone;


    // Адрес организации
    @FindBy(xpath = "//div[contains (text(),\"Адрес\")]/following-sibling::div[1]")
    public WebElement adressOrganization;

    // Ссылка "Изменить" адрес организации
    @FindBy(xpath = "//div[contains (text(),\"Адрес\")]/following-sibling::div[2]")
    public WebElement linkChangeAdressOrganization;

    // Поле ввода "Адрес подразделения"
    @FindBy(css = "div>[placeholder=\"Адрес подразделения\"]")
    public WebElement inputAdressOrganization;

    // Кнопка "Сохранить" адрес подразделения
    @FindBy(xpath = "//div[2]/following-sibling::button/span[text()=\"Сохранить\"]")
    public WebElement buttonSaveAdressOrganization;

    // Кнопка "Отмена" сохранения адреса подразделения
    @FindBy(xpath = "//div[2]/following-sibling::button/span[text()=\"Отмена\"]")
    public WebElement buttonDoNotSaveAdressOrganization;

    // Email организации
    @FindBy(xpath = "//div[contains (text(),\"Email\")]/following-sibling::div[1]")
    public WebElement emailOrganization;

    // Vk организации
    @FindBy(xpath = "//div[contains (text(),\"Vk\")]/following-sibling::div[1]")
    public WebElement vkOrganization;

    // Facebook организации
    @FindBy(xpath = "//div[contains (text(),\"Facebook\")]/following-sibling::div[1]")
    public WebElement facebookOrganization;

    // Instagram организации
    @FindBy(xpath = "//div[contains (text(),\"Instagram\")]/following-sibling::div[1]")
    public WebElement instagramOrganization;

    // Другие контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Другие\")]/following-sibling::div[1]")
    public WebElement otherOrganization;

    // Ссылка "Изменить" контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/span[contains (text(),\"Изменить\")]")
    public WebElement linkChangeContactsOrganization;

    // Кнопка "Сохранить" контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/button/span[text()=\"Сохранить\"]")
    public WebElement buttonSaveContactsOrganization;

    // Кнопка "Отмена" сохранения контактов организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/button/span[text()=\"Отмена\"]")
    public WebElement buttonDoNotSaveContactsOrganization;

    // Код подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Код подразделения\")]/following-sibling::div[1]")
    public WebElement codeOrganization;

    // Реестровый номер ОМС подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Реестровый номер ОМС\")]/following-sibling::div[1]")
    public WebElement registrationOMSOrganization;

    // Идентификатор ПУМП подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Идентификатор ПУМП\")]/following-sibling::div[1]")
    public WebElement identifikatorPUMPOrganization;

    // Идентификатор ОМС подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Идентификатор ОМС\")]/following-sibling::div[1]")
    public WebElement identifikatorOMSrganization;

    // Ссылка "Изменить" системную информацию организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/span[contains (text()," +
            "\"Изменить\")]")
    public WebElement linkChangeSystemInformationOrganization;

    // Кнопка "Сохранить" системную информацию организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/button/span[text()" +
            "=\"Сохранить\"]")
    public WebElement buttonSaveSystemInformationOrganization;

    // Кнопка "Отмена" сохранения системной информации организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/button/span[text()" +
            "=\"Отмена\"]")
    public WebElement buttonDoNotSaveSystemInformationOrganization;


    public void servicess(String Abbr, String CheckedAndFocus, String Checkeds, String Indeterminate,
                          String Empty) {
        int i = -1;
        sleep(2000);
        switch (Abbr) {
            case "1.Услуги":
                click(textServices1.get(0));
                sleep(1000);
                click(textServices2.get(0));
                sleep(1000);
                for (WebElement Service : checkServices3) {
                    i = i + 1;
                    if (i == 0) {
                        click(checkServices3.get(i));
                        System.out.println("Add to array = " + listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));

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
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.get(0).isEnabled() & listIconsDeleteSelectServicesAddUser.size() == 1);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Indeterminate);
                click(buttonSaveChangesProfile);
                break;

            case "2.Услуги":
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
                        } else {System.out.println("Услуги не совпали"); }
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
                System.out.println("SelectedServices: " + SelectedServices);
                sleep(1000);

                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
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
                click(buttonSaveChangesProfile);
                break;

            case "3.Услуги":
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
                        } else {System.out.println("Услуги не совпали");}
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
                sleep(1000);
                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);
                click(userinfoname);
                sleep(1000);
                System.out.println(textServices3.get(i).getText() + " " + costServices3.get(i).getText());
                System.out.println("Delete one service");
                SelectedServices.remove(stringServiceDeletedInListServices.get(i).getText().replace("\n", " "));
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
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
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
                click(buttonSaveChangesProfile);
                break;

            case "4.Услуги":
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
                        } else {System.out.println("Услуги не совпали");}
                    }
                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + CheckedAndFocus);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), CheckedAndFocus);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }
                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
                sleep(1000);
                System.out.println(listIconsDeleteSelectServicesAddUser.size() + " = " + listNameSelectServicesAddUser.size() +
                        " = " + textServices3.size() + " = " + checkServices3.size());
                Assert.assertTrue(listIconsDeleteSelectServicesAddUser.size() == listNameSelectServicesAddUser.size() &
                        listNameSelectServicesAddUser.size() == textServices3.size() &
                        textServices3.size() == checkServices3.size());
                click(userinfoname);

                System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Indeterminate);

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Checkeds);
                click(userinfoname);
                sleep(1000);

                System.out.println("Delete one service");
                SelectedServices.remove(listStringSelectServicesAddUser.get(i).getText().replace("\n", " "));
                String DeletedService2 = nameServiceDeletedInListServices.get(i).getText();
                System.out.println(DeletedService2);
                click(listIconsDeleteSelectServicesAddUser.get(i));
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

                System.out.println(checkServices2.get(0).getAttribute("class") + " = " + Checkeds);
                Assert.assertEquals(checkServices2.get(0).getAttribute("class"), Checkeds);

                System.out.println(checkServices2.get(1).getAttribute("class") + " = " + Indeterminate);
                Assert.assertEquals(checkServices2.get(1).getAttribute("class"), Indeterminate);

                System.out.println("Delete all service");
                try {
                    for (int b = listSelectedCheckBoxInTree.size() - 1; b > -1; b--) {
                        SelectedServices.remove(listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        System.out.println(SelectedServices);
                        System.out.println("Сняли выделение с: " + listStringSelectedServicesInTree.get(b).getText().replace("\n", " "));
                        click(listSelectedCheckBoxInTree.get(b));
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

                    System.out.println(listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ") + " = " +
                            SelectService.getText().replace("\n", " "));
                    if (listSelectedCheckBoxInTree.get(i).getText().replace("\n", " ").equals(
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
                click(buttonSaveChangesProfile);
                break;

            case "5.Услуги":
                click(textServices1.get(0));
                sleep(1000);
                for (WebElement check2 : checkServices2) {
                    click(check2);
                }
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
                    click(userinfoname);
                    sleep(1000);
                    System.out.println(checkServices1.get(0).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices1.get(0).getAttribute("class"), Checkeds);

                    for (WebElement check2 : checkServices2) {

                        System.out.println(check2.getAttribute("class") + " = " + Checkeds);
                        Assert.assertEquals(check2.getAttribute("class"), Checkeds);


                    }

                    System.out.println(checkServices3.get(i).getAttribute("class") + " = " + Checkeds);
                    Assert.assertEquals(checkServices3.get(i).getAttribute("class"), Checkeds);
                    Assert.assertEquals("Совпаение услуг не найдено", 1, u);
                }

                for (WebElement Str : listStringSelectServicesAddUser) {
                    SelectedServices.add(Str.getText().replace("\n", " "));
                }
                System.out.println(SelectedServices);
                Assert.assertEquals("Количество выбранных и сохраненных услуг не совпало",
                        listSavedServicesEditPage.size(),
                        SelectedServices.size());
                click(buttonSaveChanges);

                System.out.println(listSavedServicesEditPage.size());
                for (int x = 0; x < listSavedServicesEditPage.size(); x++) {
                    System.out.println(SelectedServices.get(x) + " = " + listSavedServicesEditPage.get(x).getText().
                            replace("\n", " "));
                    Assert.assertEquals("Услуги неверно сохранены", SelectedServices.get(x),
                            listSavedServicesEditPage.get(x).getText().replace("\n", " "));
                }
//                click(linkToProfileOrg);
//                sleep(1000);
//
//                Assert.assertEquals("Количество выбранных и услуг в карточке не совпало", SelectedServices.size(),
//                        listNamesServicesProfOrg.size());
//                for (int a = 0; a < listNamesServicesProfOrg.size(); a++) {
//                    System.out.println(listNamesServicesProfOrg.get(a).getText() + " " + listCostsServicesProfOrg
// .get(a).getText()
//                            + " = " + SelectedServices.get(a).
//                            replace("\n", " "));
//                    Assert.assertEquals("Услуги неверно сохранены", listNamesServicesProfOrg.get(a).getText()
//                                    + " " + listVendorsServicesProfOrg.get(a).getText() + " " +
// listCostsServicesProfOrg.get(a).getText(),
//                            SelectedServices.get(a));
//                }
        }
    }
}