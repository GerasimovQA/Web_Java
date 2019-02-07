package Organization.EditOrganization;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class EditOrganizationLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditOrganizationPage p;

    private int u = 0;
    private int i = 0;
    private String StatusRus = "";
    private String NowDateCreate = "";
    private String NowDateCreateAddMin = "";
    private String NowDateEdit = "";
    private String NowDateEditAddMin = "";

    private ArrayList<String> ListServicesInProfile = new ArrayList<>();
    private ArrayList<String> ListNamePhone = new ArrayList<>();
    private ArrayList<String> ListNumberPhone = new ArrayList<>();

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new EditOrganizationPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            tlDriver.set(driver);
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditOrganization);
        }
    };

    public void dateCreateOrg() {
        NowDateCreate = GlobalPage.nowDate();
        System.out.println("NowDateCreate = " + NowDateCreate);

        NowDateCreateAddMin = GlobalPage.nowDateAdd1Min();
        System.out.println("NowDateAdd1Min = " + NowDateCreateAddMin);
    }

    public void editDataOrganization(String OrganizationID, String Name, String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                     String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                     String FIODirector, String NewHead, String ConditionsOrganization,
                                     String ProfileOrganization, String Abbr,
                                     String Help_conditions, String Org_profile, String Description, String Address,
                                     String Site,
                                     String Email, String Facebook, String Instagram, String Vk,
                                     String OtherName, String OtherValue, String NamePhone1, String NumberPhone1,
                                     String NamePhone2,
                                     String NumberPhone2, String Code, String Oms_number, String Pump,
                                     String Oms_id, String Status) {

        p.moveToProfileOrgID(OrganizationID);
        p.click(p.buttonEditOrganization);
        p.waitVisibility(p.nameOrganization);
        String Name1 = p.nameOrganization.getText();
        p.click(p.linkChangeNameOrganization);
        p.clickAndSendKeys(p.inputNameOrganization, NewName);
        p.click(p.buttonSave);
        p.waitVisibility(p.nameOrganization);
        String Name2 = p.nameOrganization.getText();
        System.out.println(Name1 + " != " + Name2 + " = " + NewName);
        Assert.assertTrue(!Name1.equals(Name2) && Name2.equals(NewName));

        String Abbr1 = p.abbrNameOrganization.getText();
        p.click(p.linkChangeAbbrNameOrganization);
        p.clickAndSendKeys(p.inputAbbrNameOrganization, NewAbbr);
        p.click(p.buttonSave);
        p.waitVisibility(p.abbrNameOrganization);
        String Abbr2 = p.abbrNameOrganization.getText();
        System.out.println(Abbr1 + " != " + Abbr2 + " = " + NewAbbr);
        Assert.assertTrue(!Abbr1.equals(Abbr2) && Abbr2.equals(NewAbbr));

        String Director1 = p.directorOrganization.getText();
        p.click(p.linkChangeDirectorOrganization);
        p.click(p.searchDirectorOrganization);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        p.clickAndSendKeys(p.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        p.clickAndSendKeys(p.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        p.click(p.buttonSearchDirector);
        p.preloader();
        int z = -1;
        int f = 0;
        for (WebElement FIO : p.listFIODirecor) {
            z = z + 1;
            p.moveMouse(FIO);
            System.out.println(FIO.getText());
            Assert.assertTrue(p.listPhotoDirecor.get(z).isEnabled());
            if (FIO.getText().equals(FIODirector)) {
                f = +1;
            }
        }
        if (f != 1) {
            System.out.println("f= " + f);
            throw new AssertionError("Директор не найденн в списке");
        }

        p.click(p.buttonSelectDirecorAkulin);
        p.waitTextPresent(p.newDirectorOrganization, FIODirector);
        String Director2 = p.newDirectorOrganization.getText();
        System.out.println(Director1 + " != " + Director2 + " = " + FIODirector);
        Assert.assertTrue(!Director1.equals(Director2) & Director2.equals(FIODirector));


        String Head1 = p.headOrganization.getText();
        p.click(p.linkChangeHeadOrganization);
        p.click(p.inputStructureOrganization);
        if (FirstNameDirectorOrganization.equals("Арис")) {
            p.click(p.NameHeadOrganization);
            p.click(p.nameKardio1);
            p.click(p.nameKardio2);
            p.click(p.nameKardio3);
            p.click(p.bulletKardio4);
        } else {
            p.click(p.bulletHeadOrganization);
        }
        p.click(p.userinfoname);
        p.click(p.userinfoname);
        p.click(p.buttonSaveHeadOrganization);
        p.waitVisibility(p.headOrganization);
        p.sleep(2000);
        p.moveMouse(p.headOrganization);
        String Head2 = p.headOrganization.getText();
        System.out.println(Head1 + " != " + Head2 + " и " + Head2 + "=" + NewHead);
        Assert.assertTrue(!Head1.equals(Head2) & NewHead.contains(Head2));

        p.moveMouse(p.conditionsOrganization);
        String Conditions1 = p.conditionsOrganization.getText();
        p.click(p.body);
        p.click(p.linkChangeConditionsOrganization);
        p.click(p.selectOrganizationConditions);
        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();
        p.click(p.buttonSave);
        p.waitVisibility(p.conditionsOrganization);
        String Conditions2 = p.conditionsOrganization.getText();
        System.out.println(Conditions1 + " != " + Conditions2 + " = " + ConditionsOrganization);
        Assert.assertTrue(!Conditions1.equals(Conditions2) & Conditions2.equals(ConditionsOrganization));

        p.moveMouse(p.profileOrganization);
        String Profile1 = p.profileOrganization.getText();
        p.click(p.body);
        p.click(p.linkChangeProfileOrganization);
        p.clickAndSendKeys(p.inputProfileOrganization, ProfileOrganization);
        p.click(p.userinfoname);
        p.click(p.buttonSave);
        System.out.println("NowDateEdit = " + GlobalPage.nowDate());
        NowDateEdit = GlobalPage.nowDate();
        System.out.println("NowDateEdit = " + GlobalPage.nowDate());
        NowDateEditAddMin = GlobalPage.nowDateAdd1Min();
        p.waitVisibility(p.profileOrganization);
        String Profile2 = p.profileOrganization.getText();
        System.out.println(Profile1 + " != " + Profile2 + " = " + ProfileOrganization);
        Assert.assertTrue(!Profile1.equals(Profile2) & Profile2.equals(ProfileOrganization));

        p.click(p.menuOrganizations);
        p.sleep(1000);
        p.moveToProfileOrgID(OrganizationID);
        p.waitVisibility(p.qrProfOrg);

        p.compareStringAndString(NewName, p.nameProfOrg.getText());
        p.compareStringAndString(NewAbbr, p.nameAbbrProfOrg.getText());
        p.compareStringAndString(NewHead, p.headOrganizationProfOrg.getText());
        p.compareStringAndString(ConditionsOrganization, p.termsProfOrg.getText());
        p.compareStringAndString(ProfileOrganization, p.mainStreamProfOrg.getText());

        p.compareStringAndString(FIODirector, p.directorFIOProfOrg.getText());
        p.compareStringAndString(Description, p.descriptionProfOrg.getText());
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        Assert.assertTrue(p.buttonEditProfOrg.isEnabled());
        p.compareStringAndString(Address, p.adressProfOrg.getText());

        ListNamePhone.add(NamePhone1);
        ListNamePhone.add(NamePhone2);
        ListNumberPhone.add(NumberPhone1);
        ListNumberPhone.add(NumberPhone2);

        for (WebElement NameProf : p.listNamePhoneProfOrg) {
            i = i + 1;
            for (String NamePh : ListNamePhone) {
                if (NameProf.getText().equals(NamePh)) {
                    u = u + 1;
                    System.out.println("!+Наименование телефона найдено+!");
                    break;
                } else {
                    System.out.println("Наименование телефона НЕ найдено");
                }
                assertEquals("Совпаение наименований телефонов не найдено", 1, u);
            }
        }

        for (WebElement NumberProf : p.listNumbersPhoneProfOrg) {
            i = i + 1;
            for (String NumberPh : ListNumberPhone) {
                if (NumberProf.getText().equals(NumberPh)) {
                    u = u + 1;
                    System.out.println("!+Номер телефона найден+!");
                    break;
                } else {
                    System.out.println("Номер телефона НЕ найден");
                }
                assertEquals("Совпаение номеров телефонов не найдено", 1, u);
            }
        }

        p.compareStringAndString(Site, p.siteProfOrg.getText());
        p.compareStringAndString(Email, p.emailProfOrg.getText());
        p.compareStringAndString(Vk, p.vkProfOrg.getText());
        p.compareStringAndString(Instagram, p.instagramProfOrg.getText());
        p.compareStringAndString(Facebook, p.facebookProfOrg.getText());
        p.compareStringAndString(OtherName, p.webElementOtherName(OtherName).getText());
        p.compareStringAndString(OtherValue, p.otherProfOrg.getText());
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareStringAndString(Code, p.codeProfOrg.getText());
        p.compareStringAndString(Oms_number, p.numberOmsProfOrg.getText());
        p.compareStringAndString(Oms_id, p.identifierOmsProfOrg.getText());
        p.compareStringAndString(Pump, p.identifierPumpProfOrg.getText());
        if (Status.contains("active")) {
            StatusRus = "Актуальное";
        }
        p.compareStringAndString(StatusRus, p.statusProfOrg.getText());

        p.moveMouse(p.dateCreateProfOrg);
        System.out.println("NowDateCreate = " + NowDateCreate);
        System.out.println("NowDateCreateAddMin = " + NowDateCreateAddMin);
        System.out.println("Дата создания в профиле = " + p.dateCreateProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));

        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле = " + p.lastChangeProfOrg.getText());
        Assert.assertTrue(NowDateEdit.equals(p.lastChangeProfOrg.getText()) ||
                NowDateEditAddMin.equals(p.lastChangeProfOrg.getText()));

        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.buttonEditOrganization);
        p.click(p.linkDeleteOrganization);
        WebElement NameDeleteOrganization2 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div/div[2]/div[1]/p[contains (text(), \"" + NewName + "\")]")));
        System.out.println(NameDeleteOrganization2.getText() + " = " + NewName);
        assertEquals(NameDeleteOrganization2.getText(), NewName);
        p.click(p.buttonDelete);
    }

    public void editDataOrganizationCancel(String OrganizationID, String Name, String NewName, String NewAbbr,
                                           String SecondNameDirectorOrganization,
                                           String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                           String FIODirector, String NewHead, String ConditionsOrganization,
                                           String ProfileOrganization, String Abbr,
                                           String Help_conditions, String Org_profile, String Description,
                                           String Address, String Site,
                                           String Email, String Facebook, String Instagram, String Vk,
                                           String OtherName, String OtherValue, String NamePhone1,
                                           String NumberPhone1, String NamePhone2,
                                           String NumberPhone2, String Code, String Oms_number, String Pump,
                                           String Oms_id, String Status) {
        p.moveToProfileOrgID(OrganizationID);
        p.waitVisibility(p.nameProfOrg);
        String Head1 = p.headOrganizationProfOrg.getText();
        p.click(p.buttonEditOrganization);
        p.waitVisibility(p.nameOrganization);
        String Name1 = p.nameOrganization.getText();
        p.click(p.linkChangeNameOrganization);
        p.clickAndSendKeys(p.inputNameOrganization, NewName);
        p.click(p.buttonCancel);
        p.waitVisibility(p.nameOrganization);
        String Name2 = p.nameOrganization.getText();
        System.out.println(Name1 + " = " + Name2);
        assertEquals(Name1, Name2);

        String Abbr1 = p.abbrNameOrganization.getText();
        p.click(p.linkChangeAbbrNameOrganization);
        p.clickAndSendKeys(p.inputAbbrNameOrganization, NewAbbr);
        p.click(p.buttonCancel);
        p.waitVisibility(p.abbrNameOrganization);
        String Abbr2 = p.abbrNameOrganization.getText();
        System.out.println(Abbr1 + " = " + Abbr2);
        assertEquals(Abbr1, Abbr2);

        String Director1 = p.directorOrganization.getText();
        p.click(p.linkChangeDirectorOrganization);
        p.click(p.searchDirectorOrganization);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        p.clickAndSendKeys(p.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        p.clickAndSendKeys(p.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        p.click(p.buttonCancel);
        p.waitVisibility(p.directorOrganization);
        String Director2 = p.directorOrganization.getText();
        System.out.println(Director1 + " = " + Director2);
        assertEquals(Director1, Director2);

        p.click(p.linkChangeHeadOrganization);
        p.click(p.inputStructureOrganization);
        p.click(p.NameHeadOrganization);
        p.buttonDown(10);
        p.click(p.NameStepashin);
        p.click(p.NameStepashin1);
        p.click(p.nameStepashin2);
        p.click(p.bulletStepashin3);
        p.click(p.buttonCancel);
        p.waitVisibility(p.headOrganization);
        String Head2 = p.headOrganization.getText();
        System.out.println(Head1 + " = " + Head2);
        Assert.assertTrue(Head1.contains(Head2));

        String Conditions1 = p.conditionsOrganization.getText();
        p.click(p.linkChangeConditionsOrganization);
        p.click(p.selectOrganizationConditions);
        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();
        p.click(p.buttonCancel);
        p.waitVisibility(p.conditionsOrganization);
        p.waitVisibility(p.profileOrganization);
        String Conditions2 = p.conditionsOrganization.getText();
        System.out.println(Conditions1 + " = " + Conditions2);
        assertEquals(Conditions1, Conditions2);

        String Profile1 = p.profileOrganization.getText();
        p.click(p.linkChangeProfileOrganization);
        p.clickAndSendKeys(p.inputProfileOrganization, ProfileOrganization);
        p.click(p.buttonCancel);
        p.waitVisibility(p.profileOrganization);
        String Profile2 = p.profileOrganization.getText();
        System.out.println(Profile1 + " = " + Profile2);
        assertEquals(Profile1, Profile2);

        p.click(p.linkDeleteOrganization);
        WebElement NameDeleteOrganization = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div/div[2]/div[1]/p[contains (text(), \"" + Name + "\")]")));
        System.out.println(NameDeleteOrganization.getText() + " = " + Name);
        assertEquals(NameDeleteOrganization.getText(), Name);
        p.click(p.buttonCancelDelete);
        p.waitVisibility(p.profileOrganization);

        p.click(p.menuOrganizations);
        p.sleep(1000);
        p.moveToProfileOrgID(OrganizationID);
        p.waitVisibility(p.qrProfOrg);

        p.compareStringAndString(Name2, p.nameProfOrg.getText());
        p.compareStringAndString(Abbr, p.nameAbbrProfOrg.getText());
        Assert.assertTrue(p.headOrganizationProfOrg.getText().contains(Head2));
        p.compareStringAndString(Help_conditions, p.termsProfOrg.getText());
        p.compareStringAndString(Org_profile, p.mainStreamProfOrg.getText());

        p.compareStringAndString(Director2, p.directorFIOProfOrg.getText());
        p.compareStringAndString(Description, p.descriptionProfOrg.getText());
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        Assert.assertTrue(p.buttonEditProfOrg.isEnabled());
        p.compareStringAndString(Address, p.adressProfOrg.getText());

        ListNamePhone.add(NamePhone1);
        ListNamePhone.add(NamePhone2);
        ListNumberPhone.add(NumberPhone1);
        ListNumberPhone.add(NumberPhone2);
        for (WebElement NameProf : p.listNamePhoneProfOrg) {
            i = i + 1;
            for (String NamePh : ListNamePhone) {
                if (NameProf.getText().equals(NamePh)) {
                    u = u + 1;
                    System.out.println("!+Наименование телефона найдено+!");
                    break;
                } else {
                    System.out.println("Наименование телефона НЕ найдено");
                }
                assertEquals("Совпаение наименований телефонов не найдено", 1, u);
            }
        }

        for (WebElement NumberProf : p.listNumbersPhoneProfOrg) {
            i = i + 1;
            for (String NumberPh : ListNumberPhone) {
                if (NumberProf.getText().equals(NumberPh)) {
                    u = u + 1;
                    System.out.println("!+Номер телефона найден+!");
                    break;
                } else {
                    System.out.println("Номер телефона НЕ найден");
                }
                assertEquals("Совпаение номеров телефонов не найдено", 1, u);
            }
        }

        p.compareStringAndString(Site, p.siteProfOrg.getText());
        p.compareStringAndString(Email, p.emailProfOrg.getText());
        p.compareStringAndString(Vk, p.vkProfOrg.getText());
        p.compareStringAndString(Instagram, p.instagramProfOrg.getText());
        p.compareStringAndString(Facebook, p.facebookProfOrg.getText());
        p.compareStringAndString(OtherName, p.webElementOtherName(OtherName).getText());
        p.compareStringAndString(OtherValue, p.otherProfOrg.getText());
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareStringAndString(Code, p.codeProfOrg.getText());
        p.compareStringAndString(Oms_number, p.numberOmsProfOrg.getText());
        p.compareStringAndString(Oms_id, p.identifierOmsProfOrg.getText());
        p.compareStringAndString(Pump, p.identifierPumpProfOrg.getText());
        if (Status.contains("active")) {
            StatusRus = "Актуальное";
        }
        p.compareStringAndString(StatusRus, p.statusProfOrg.getText());
        p.moveMouse(p.dateCreateProfOrg);
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));

        p.moveMouse(p.lastChangeProfOrg);
        Assert.assertTrue(NowDateCreate.equals(p.lastChangeProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.lastChangeProfOrg.getText()));

        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }

    public void editServicesOrganization(String OrganizationID, String Name, String Abbr, String CheckedAndFocus, String Checkeds,
                                         String Indeterminate, String Empty, String Help_conditions,
                                         String Org_profile, String Description, String Address, String Site,
                                         String Email,
                                         String Facebook, String Instagram, String Vk, String OtherName,
                                         String OtherValue, String NamePhone1,
                                         String NumberPhone1, String NamePhone2, String NumberPhone2, String Code,
                                         String Oms_number, String Pump, String Oms_id, String Status, String Head,
                                         String FIODirector) {
        System.out.println("NowDateCreate = " + GlobalPage.nowDate());
        NowDateCreate = GlobalPage.nowDate();
        System.out.println("NowDateCreateAddMin = " + GlobalPage.nowDateAdd1Min());
        NowDateCreateAddMin = GlobalPage.nowDateAdd1Min();
        p.moveToProfileOrgID(OrganizationID);
        p.click(p.buttonEditOrganization);
        p.click(p.linkServices);
        p.click(p.linkChangeServices);
        p.servicess(Abbr, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        System.out.println("NowDateEdit = " + GlobalPage.nowDate());
        NowDateEdit = GlobalPage.nowDate();
        System.out.println("NowDateEdit = " + GlobalPage.nowDateAdd1Min());
        NowDateEditAddMin = GlobalPage.nowDateAdd1Min();

        p.scrollHomePage();
        p.click(p.linkNameOrgEditOrg);

        p.waitVisibility(p.qrProfOrg);
        p.compareStringAndString(Name, p.nameProfOrg.getText());
        p.compareStringAndString(Abbr, p.nameAbbrProfOrg.getText());
        p.compareStringAndString(Head, p.headOrganizationProfOrg.getText());
        p.compareStringAndString(Help_conditions, p.termsProfOrg.getText());

        p.compareStringAndString(FIODirector, p.directorFIOProfOrg.getText());
        p.compareStringAndString(Description, p.descriptionProfOrg.getText());
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());
        p.sleep(2000);

        int x = -1;
        for (WebElement Service : p.listNamesServicesProfOrg) {
            x = x + 1;
            p.moveMouse(p.listCostsServicesProfOrg.get(x));
            p.buttonDown(2);
            ListServicesInProfile.add(p.listNamesServicesProfOrg.get(x).getText() + " " +
                    p.listVendorsServicesProfOrg.get(x).getText() + " " + p.listCostsServicesProfOrg.get(x).getText());
        }
        System.out.println(p.SelectedServices);
        System.out.println(ListServicesInProfile);
        p.compareArrayListAndArrayList(p.SelectedServices, ListServicesInProfile, ListServicesInProfile.size());
        p.moveMouse(p.headerExecutorsProfOrg);
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        p.moveMouse(p.mapProfOrg);
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        p.moveMouse(p.buttonEditProfOrg);
        Assert.assertTrue(p.buttonEditProfOrg.isEnabled());
        p.moveMouse(p.adressProfOrg);
        p.compareStringAndString(Address, p.adressProfOrg.getText());

        ListNamePhone.add(NamePhone1);
        ListNamePhone.add(NamePhone2);
        ListNumberPhone.add(NumberPhone1);
        ListNumberPhone.add(NumberPhone2);
        for (WebElement NameProf : p.listNamePhoneProfOrg) {
            i = i + 1;
            for (String NamePh : ListNamePhone) {
                if (NameProf.getText().equals(NamePh)) {
                    u = u + 1;
                    System.out.println("!+Наименование телефона найдено+!");
                    break;
                } else {
                    System.out.println("Наименование телефона НЕ найдено");
                }
                assertEquals("Совпаение наименований телефонов не найдено", 1, u);
            }
        }

        for (WebElement NumberProf : p.listNumbersPhoneProfOrg) {
            i = i + 1;
            for (String NumberPh : ListNumberPhone) {
                if (NumberProf.getText().equals(NumberPh)) {
                    u = u + 1;
                    System.out.println("!+Номер телефона найден+!");
                    break;
                } else {
                    System.out.println("Номер телефона НЕ найден");
                }
                assertEquals("Совпаение номеров телефонов не найдено", 1, u);
            }
        }

        p.compareStringAndWebelement(Site, p.siteProfOrg);
        p.compareStringAndWebelement(Email, p.emailProfOrg);
        p.compareStringAndWebelement(Vk, p.vkProfOrg);
        p.compareStringAndWebelement(Instagram, p.instagramProfOrg);
        p.compareStringAndWebelement(Facebook, p.facebookProfOrg);
        p.compareStringAndWebelement(OtherName, p.webElementOtherName(OtherName));
        p.compareStringAndWebelement(OtherValue, p.otherProfOrg);
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareStringAndWebelement(Code, p.codeProfOrg);
        p.compareStringAndWebelement(Oms_number, p.numberOmsProfOrg);
        p.compareStringAndWebelement(Oms_id, p.identifierOmsProfOrg);
        p.compareStringAndWebelement(Pump, p.identifierPumpProfOrg);
        if (Status.contains("active")) {
            StatusRus = "Актуальное";
        }
        p.compareStringAndWebelement(StatusRus, p.statusProfOrg);

        p.moveMouse(p.dateCreateProfOrg);
        System.out.println("Дата создания в профиле: " + p.dateCreateProfOrg.getText());
        System.out.println("Фактическая дата создания: " + NowDateCreate);
        System.out.println("Фактическая дата создания + 1 мин: " + NowDateCreateAddMin);
        Assert.assertTrue("Дата создания не совпала",
                NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));

        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле: " + p.lastChangeProfOrg.getText());
        System.out.println("Фактическая дата редактирования: " + NowDateEdit);
        System.out.println("Фактическая дата редактирования + 1 мин: " + NowDateEditAddMin);
        Assert.assertTrue("Дата редактирования не совпала",
                NowDateEdit.equals(p.lastChangeProfOrg.getText()) ||
                NowDateEditAddMin.equals(p.lastChangeProfOrg.getText()));

        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }


    public void editAdditionalInformationOrganization(String OrganizationID, String Name, String DescriptionOrganization,
                                                      String NewAppointmentPhone1, String NewNumberPhone1,
                                                      String NewAdress, String NewSite, String NewEmail, String NewVk,
                                                      String NewFacebook, String NewInstagram, String NewOtherName,
                                                      String NewOtherValue,
                                                      String NewCode, String NewRegistryNumber,
                                                      String NewIdentifierPUMP, String NewIdentifierOMS, String Abbr,
                                                      String Chief, String Help_conditions, String Org_profile,
                                                      String Description, String Address, String Site, String Email,
                                                      String Facebook,
                                                      String Instagram, String Vk, String OtherName,
                                                      String OtherValue, String Code,
                                                      String Oms_number, String Pump, String Oms_id, String Status,
                                                      String Parent, String Head, String FIODirector) {
        p.moveToProfileOrgID(OrganizationID);
        p.click(p.buttonEditOrganization);
        p.click(p.linkAdditionalInformation);

        p.preloader();
        String Description1 = p.descriptionOrganization.getText();
        p.click(p.linkChangeDescriptionOrganization);
        p.clickAndSendKeys(p.inputDescriptionOrganization, DescriptionOrganization);
        p.click(p.buttonSave);
        p.waitTextPresent(p.descriptionOrganization, DescriptionOrganization);
        String Description2 = p.descriptionOrganization.getText();
        System.out.println(Description1 + " != " + Description2 + " = " + DescriptionOrganization);
        Assert.assertTrue(!Description1.equals(Description2) & Description2.equals(DescriptionOrganization));

        String AppointmentPhone1 = p.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone1 = p.numberPhoneOrganization.get(0).getText();
        p.click(p.linkChangePhoneOrganization);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(0), NewAppointmentPhone1);
        p.clickAndSendKeys(p.inputNumberPhone.get(0), NewNumberPhone1);
        p.click(p.buttonSave);
        p.sleep(1000);
        p.moveMouse(p.appointmentPhoneOrganization.get(0));
        p.waitVisibility(p.appointmentPhoneOrganization.get(0));
        p.moveMouse(p.appointmentPhoneOrganization.get(0));
        String AppointmentPhone2 = p.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone2 = p.numberPhoneOrganization.get(0).getText();
        System.out.println(AppointmentPhone1 + " != " + AppointmentPhone2 + " = " + NewAppointmentPhone1);
        System.out.println(NumberPhone1 + " != " + NumberPhone2 + " = " + "+ 9 (" + NewNumberPhone1.charAt(0) +
                NewNumberPhone1.charAt(1) + NewNumberPhone1.charAt(2) + ") " + NewNumberPhone1.charAt(3) +
                NewNumberPhone1.charAt(4) + NewNumberPhone1.charAt(5) + "-" + NewNumberPhone1.charAt(6) +
                NewNumberPhone1.charAt(7) + "-" +
                NewNumberPhone1.charAt(8) + NewNumberPhone1.charAt(9));
        Assert.assertTrue(!AppointmentPhone1.equals(AppointmentPhone2) &
                AppointmentPhone2.equals(NewAppointmentPhone1));
        Assert.assertTrue(!NumberPhone1.equals(NumberPhone2) & NumberPhone2.equals("+ 9 (" +
                NewNumberPhone1.charAt(0) + NewNumberPhone1.charAt(1) + NewNumberPhone1.charAt(2) + ") " +
                NewNumberPhone1.charAt(3) + NewNumberPhone1.charAt(4) + NewNumberPhone1.charAt(5) + "-" +
                NewNumberPhone1.charAt(6) + NewNumberPhone1.charAt(7) + "-" + NewNumberPhone1.charAt(8) +
                NewNumberPhone1.charAt(9)));

        p.moveMouse(p.adressOrganization);
        String Adress1 = p.adressOrganization.getText();
        p.click(p.linkChangeAdressOrganization);
        p.clickAndSendKeys(p.inputAdressOrganization, NewAdress);
        p.sleep(5000);
        p.waitTextPresent(p.buttonSaveAdressOrganization, "Сохранить");
        p.click(p.buttonSaveAdressOrganization);
        p.waitVisibility(p.adressOrganization);
        p.sleep(1000);
        p.moveMouse(p.adressOrganization);
        String Adress2 = p.adressOrganization.getText();
        System.out.println(Adress1 + " != " + Adress2 + " = " + NewAdress);
        Assert.assertTrue(!Adress1.equals(Adress2) & Adress2.equals(NewAdress));
        String Site1 = p.siteOrganization.getText();
        String Email1 = p.emailOrganization.getText();
        String Vk1 = p.vkOrganization.getText();
        String Facebook1 = p.facebookOrganization.getText();
        String Instagram1 = p.instagramOrganization.getText();

        String OtherName1 = p.webElementOtherName(OtherName).getText();
        String OtherValue1 = p.webElementOtherValue(OtherValue).getText();

        p.click(p.linkChangeContactsOrganization);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputSite, NewSite);
        p.clickAndSendKeys(p.inputVk, NewVk);
        p.clickAndSendKeys(p.inputFacebook, NewFacebook);
        p.clickAndSendKeys(p.inputInstagram, NewInstagram);
        p.clickAndSendKeys(p.inputOtherName, NewOtherName);
        p.clickAndSendKeys(p.inputOtherValue, NewOtherValue);
        p.click(p.buttonSaveContactsOrganization);

        p.waitVisibility(p.emailOrganization);
        String Site2 = p.siteOrganization.getText();
        String Email2 = p.emailOrganization.getText();
        String Vk2 = p.vkOrganization.getText();
        String Facebook2 = p.facebookOrganization.getText();
        String Instagram2 = p.instagramOrganization.getText();

        String OtherName2 = p.webElementOtherName(NewOtherName).getText();
        String OtherValue2 = p.webElementOtherValue(NewOtherValue).getText();

        System.out.println(Site1 + " != " + Site2 + " = " + NewSite);
        System.out.println(Email1 + " != " + Email2 + " = " + NewEmail);
        System.out.println(Vk1 + " != " + Vk2 + " = " + NewVk);
        System.out.println(Facebook1 + " != " + Facebook2 + " = " + NewFacebook);
        System.out.println(Instagram1 + " != " + Instagram2 + " = " + NewInstagram);
        System.out.println(OtherName1 + " != " + OtherName2 + " = " + NewOtherName);
        System.out.println(OtherValue1 + " != " + OtherValue2 + " = " + NewOtherValue);

        Assert.assertTrue(!Email1.equals(Email2) & Email2.equals(NewEmail));
        Assert.assertTrue(!Vk1.equals(Vk2) & Vk2.equals(NewVk));
        Assert.assertTrue(!Facebook1.equals(Facebook2) & Facebook2.equals(NewFacebook));
        Assert.assertTrue(!Instagram1.equals(Instagram2) & Instagram2.equals(NewInstagram));
        Assert.assertTrue(!OtherName1.equals(OtherName2) & OtherName2.equals(NewOtherName));
        Assert.assertTrue(!OtherValue.equals(OtherValue2) & OtherValue2.equals(NewOtherValue));

        String Code1 = p.codeOrganization.getText();
        String RegistrationOMS1 = p.registrationOMSOrganization.getText();
        String IdentifikatorPUMP1 = p.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS1 = p.identifikatorOMSrganization.getText();
        p.click(p.linkChangeSystemInformationOrganization);
        p.clickAndSendKeys(p.inputCode, NewCode);
        p.clickAndSendKeys(p.inputRegistryNumber, NewRegistryNumber);
        p.clickAndSendKeys(p.inputIdentifierPUMP, NewIdentifierPUMP);
        p.clickAndSendKeys(p.inputIdentifierOMS, NewIdentifierOMS);
        p.click(p.buttonSaveSystemInformationOrganization);

        System.out.println("NowDateEdit = " + GlobalPage.nowDate());
        NowDateEdit = GlobalPage.nowDate();
        System.out.println("NowDateEditAddMin = " + GlobalPage.nowDateAdd1Min());
        NowDateEditAddMin = GlobalPage.nowDateAdd1Min();

        p.waitVisibility(p.emailOrganization);
        String Code2 = p.codeOrganization.getText();
        String RegistrationOMS2 = p.registrationOMSOrganization.getText();
        String IdentifikatorPUMP2 = p.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS2 = p.identifikatorOMSrganization.getText();
        System.out.println(Code1 + " != " + Code2 + " = " + NewCode);
        System.out.println(RegistrationOMS1 + " != " + RegistrationOMS2 + " = " + NewRegistryNumber);
        System.out.println(IdentifikatorPUMP1 + " != " + IdentifikatorPUMP2 + " = " + NewIdentifierPUMP);
        System.out.println(IdentifikatorOMS1 + " != " + IdentifikatorOMS2 + " = " + NewIdentifierOMS);
        Assert.assertTrue(!Code1.equals(Code2) & Code2.equals(NewCode));
        Assert.assertTrue(!RegistrationOMS1.equals(RegistrationOMS2) & RegistrationOMS2.equals(NewRegistryNumber));
        Assert.assertTrue(!IdentifikatorPUMP1.equals(IdentifikatorPUMP2) & IdentifikatorPUMP2.equals(NewIdentifierPUMP));
        Assert.assertTrue(!IdentifikatorOMS1.equals(IdentifikatorOMS2) & IdentifikatorOMS2.equals(NewIdentifierOMS));

        p.click(p.body);
        p.scrollHomePage();
        p.waitE_ClickableAndClickAndUp(p.linkNameOrgEditOrg);

        p.waitTextPresent(p.nameProfOrg, Name);
        p.compareStringAndString(Name, p.nameProfOrg.getText());
        p.compareStringAndString(Abbr, p.nameAbbrProfOrg.getText());
        p.compareStringAndString(Head, p.headOrganizationProfOrg.getText());
        p.compareStringAndString(Help_conditions, p.termsProfOrg.getText());
        p.compareStringAndString(Org_profile, p.mainStreamProfOrg.getText());
        p.compareStringAndString(FIODirector, p.directorFIOProfOrg.getText());
        p.compareStringAndString(DescriptionOrganization, p.descriptionProfOrg.getText());
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        p.moveMouse(p.headerStructureProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        p.moveMouse(p.headerServicesProfOrg);
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());
        p.moveMouse(p.headerExecutorsProfOrg);
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        p.moveMouse(p.mapProfOrg);
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        p.moveMouse(p.buttonEditProfOrg);
        Assert.assertTrue(p.buttonEditProfOrg.isEnabled());
        p.compareStringAndString(NewAdress, p.adressProfOrg.getText());

        p.compareStringAndString(NewAppointmentPhone1, p.listNamePhoneProfOrg.get(0).getText());
        p.waitTextPresent(p.listNumbersPhoneProfOrg.get(0), "+ 9 (" + NewNumberPhone1.charAt(0) +
                NewNumberPhone1.charAt(1) + NewNumberPhone1.charAt(2) + ") " + NewNumberPhone1.charAt(3) +
                NewNumberPhone1.charAt(4) + NewNumberPhone1.charAt(5) + "-" + NewNumberPhone1.charAt(6) +
                NewNumberPhone1.charAt(7) + "-" + NewNumberPhone1.charAt(8) + NewNumberPhone1.charAt(9));

        p.compareStringAndString(NewSite, p.siteProfOrg.getText());
        p.compareStringAndString(NewEmail, p.emailProfOrg.getText());
        p.compareStringAndString(NewVk, p.vkProfOrg.getText());
        p.compareStringAndString(NewInstagram, p.instagramProfOrg.getText());
        p.compareStringAndString(NewFacebook, p.facebookProfOrg.getText());
        p.compareStringAndWebelement(NewOtherName, p.webElementOtherName(NewOtherName));
        p.compareStringAndWebelement(NewOtherValue, p.webElementOtherValue(NewOtherValue));
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareStringAndString(NewCode, p.codeProfOrg.getText());
        p.compareStringAndString(NewRegistryNumber, p.numberOmsProfOrg.getText());
        p.compareStringAndString(NewIdentifierOMS, p.identifierOmsProfOrg.getText());
        p.compareStringAndString(NewIdentifierPUMP, p.identifierPumpProfOrg.getText());
        if (Status.contains("active")) {
            StatusRus = "Актуальное";
        }
        p.compareStringAndWebelement(StatusRus, p.statusProfOrg);

        p.moveMouse(p.dateCreateProfOrg);
        System.out.println("Дата создания в профиле: " + p.dateCreateProfOrg.getText());
        System.out.println("Фактическая дата создания: " + NowDateCreate);
        System.out.println("Фактическая дата создания + 1 мин: " + NowDateCreateAddMin);
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));

//        p.compareStringAndString(NowDateCreate, p.dateCreateProfOrg.getText());
        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле: " + p.lastChangeProfOrg.getText());
        System.out.println("Фактическая дата редактирования: " + NowDateEdit);
        System.out.println("Фактическая дата редактирования + 1 мин: " + NowDateEditAddMin);
        Assert.assertTrue(NowDateEdit.equals(p.lastChangeProfOrg.getText()) ||
                NowDateEditAddMin.equals(p.lastChangeProfOrg.getText()));

        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }


    public void editAdditionalInformationOrganizationCancel(String OrganizationID, String Name, String Abbr, String DescriptionOrganization,
                                                            String NewAppointmentPhone1, String NewNumberPhone1,
                                                            String NewAdress, String NewSite, String NewEmail,
                                                            String NewVk,
                                                            String NewFacebook, String NewInstagram,
                                                            String NewOtherName, String NewOtherValue,
                                                            String NewCode, String NewRegistryNumber,
                                                            String NewIdentifierPUMP, String NewIdentifierOMS,
                                                            String Status, String Head, String Help_conditions,
                                                            String Org_profile, String FIODirector, String Address,
                                                            String Site,
                                                            String Email, String Facebook, String Instagram, String Vk,
                                                            String OtherName, String OtherValue, String Code,
                                                            String Oms_number, String Pump,
                                                            String Oms_id, String Description) {
        p.moveToProfileOrgID(OrganizationID);
        p.click(p.buttonEditOrganization);
        p.click(p.linkAdditionalInformation);

        p.preloader();
        String Description1 = p.descriptionOrganization.getText();
        p.click(p.linkChangeDescriptionOrganization);
        p.clickAndSendKeys(p.inputDescriptionOrganization, DescriptionOrganization);
        p.click(p.buttonCancel);
        p.waitVisibility(p.descriptionOrganization);

        p.compareStringAndWebelement(Description1, p.descriptionOrganization);

        String AppointmentPhone1 = p.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone1 = p.numberPhoneOrganization.get(0).getText();
        p.click(p.linkChangePhoneOrganization);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(0), NewAppointmentPhone1);
        p.clickAndSendKeys(p.inputNumberPhone.get(0), NewNumberPhone1);
        p.click(p.buttonCancel);
        p.sleep(1000);
        p.waitVisibility(p.appointmentPhoneOrganization.get(0));

        p.compareStringAndWebelement(AppointmentPhone1, p.appointmentPhoneOrganization.get(0));
        p.compareStringAndWebelement(NumberPhone1, p.numberPhoneOrganization.get(0));

        String Adress1 = p.adressOrganization.getText();
        p.click(p.linkChangeAdressOrganization);
        p.clickAndSendKeys(p.inputAdressOrganization, NewAdress);
        p.sleep(3000);
        p.waitTextPresent(p.buttonDoNotSaveAdressOrganization, "Отмена");
        p.click(p.buttonDoNotSaveAdressOrganization);
        p.waitVisibility(p.adressOrganization);
        String Adress2 = p.adressOrganization.getText();

        p.compareStringAndWebelement(Adress1, p.adressOrganization);

        String Site1 = p.siteOrganization.getText();
        String Email1 = p.emailOrganization.getText();
        String Vk1 = p.vkOrganization.getText();
        String Facebook1 = p.facebookOrganization.getText();
        String Instagram1 = p.instagramOrganization.getText();
        String Other1 = p.otherNameOrganization.getText();
        p.click(p.linkChangeContactsOrganization);
        p.clickAndSendKeys(p.inputSite, NewSite);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputVk, NewVk);
        p.clickAndSendKeys(p.inputFacebook, NewFacebook);
        p.clickAndSendKeys(p.inputInstagram, NewInstagram);
        p.clickAndSendKeys(p.inputOtherName, NewOtherName);
        p.clickAndSendKeys(p.inputOtherValue, NewOtherValue);
        p.click(p.buttonDoNotSaveContactsOrganization);

        p.waitVisibility(p.emailOrganization);
        p.compareStringAndWebelement(Site1, p.siteOrganization);
        p.compareStringAndWebelement(Email1, p.emailOrganization);
        p.compareStringAndWebelement(Vk1, p.vkOrganization);
        p.compareStringAndWebelement(Facebook1, p.facebookOrganization);
        p.compareStringAndWebelement(Instagram1, p.instagramOrganization);
        p.compareStringAndWebelement(Other1, p.otherNameOrganization);


        p.moveMouse(p.codeOrganization);
        String Code1 = p.codeOrganization.getText();
        p.moveMouse(p.registrationOMSOrganization);
        String RegistrationOMS1 = p.registrationOMSOrganization.getText();
        p.moveMouse(p.identifikatorPUMPOrganization);
        String IdentifikatorPUMP1 = p.identifikatorPUMPOrganization.getText();
        p.moveMouse(p.identifikatorOMSrganization);
        String IdentifikatorOMS1 = p.identifikatorOMSrganization.getText();
        p.click(p.linkChangeSystemInformationOrganization);
        p.clickAndSendKeys(p.inputCode, NewCode);
        p.clickAndSendKeys(p.inputRegistryNumber, NewRegistryNumber);
        p.clickAndSendKeys(p.inputIdentifierPUMP, NewIdentifierPUMP);
        p.clickAndSendKeys(p.inputIdentifierOMS, NewIdentifierOMS);
        p.click(p.buttonDoNotSaveSystemInformationOrganization);
        p.waitVisibility(p.codeOrganization);

        p.compareStringAndWebelement(Code1, p.codeOrganization);
        p.compareStringAndWebelement(RegistrationOMS1, p.registrationOMSOrganization);
        p.compareStringAndWebelement(IdentifikatorPUMP1, p.identifikatorPUMPOrganization);
        p.compareStringAndWebelement(IdentifikatorOMS1, p.identifikatorOMSrganization);

        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameOrgEditOrg);

        p.waitTextPresent(p.nameProfOrg, Name);
        p.compareStringAndWebelement(Name, p.nameProfOrg);
        p.compareStringAndWebelement(Abbr, p.nameAbbrProfOrg);
        p.compareStringAndWebelement(Head, p.headOrganizationProfOrg);
        p.compareStringAndWebelement(Help_conditions, p.termsProfOrg);
        p.compareStringAndWebelement(Org_profile, p.mainStreamProfOrg);
        p.compareStringAndWebelement(FIODirector, p.directorFIOProfOrg);
        p.compareStringAndWebelement(Description, p.descriptionProfOrg);
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        Assert.assertTrue(p.buttonEditProfOrg.isEnabled());
        p.compareStringAndWebelement(Address, p.adressProfOrg);

        p.compareStringAndWebelement(AppointmentPhone1, p.listNamePhoneProfOrg.get(0));
        p.compareStringAndWebelement(NumberPhone1, p.listNumbersPhoneProfOrg.get(0));

        p.compareStringAndWebelement(Site, p.siteProfOrg);
        p.compareStringAndWebelement(Email, p.emailProfOrg);
        p.compareStringAndWebelement(Vk, p.vkProfOrg);
        p.compareStringAndWebelement(Instagram, p.instagramProfOrg);
        p.compareStringAndWebelement(Facebook, p.facebookProfOrg);
        p.compareStringAndWebelement(OtherName, p.webElementOtherName(OtherName));
        p.compareStringAndWebelement(OtherValue, p.otherProfOrg);
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareStringAndWebelement(Code, p.codeProfOrg);
        p.compareStringAndWebelement(Oms_number, p.numberOmsProfOrg);
        p.compareStringAndWebelement(Oms_id, p.identifierOmsProfOrg);
        p.compareStringAndWebelement(Pump, p.identifierPumpProfOrg);
        if (Status.contains("active")) {
            StatusRus = "Актуальное";
        }
        p.compareStringAndWebelement(StatusRus, p.statusProfOrg);

        System.out.println("NowDateCreate = " + NowDateCreate);
        System.out.println("NowDateCreateAddMin = " + NowDateCreateAddMin);
        System.out.println("Дата создания в профиле = " + p.dateCreateProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));

        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле = " + p.lastChangeProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.lastChangeProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.lastChangeProfOrg.getText()));

        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }


    public void editPhonesOrganization(String OrganizationID, String Name, String NamePhone1, String NumberPhone1,
                                       String NewAppointmentPhone2, String NewNumberPhone2) {
        p.moveToProfileOrgID(OrganizationID);
        p.click(p.buttonEditOrganization);
        p.click(p.linkAdditionalInformation);
        p.click(p.linkChangePhoneOrganization);
        p.click(p.linkAddPhone);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(1), NewAppointmentPhone2);
        p.clickAndSendKeys(p.inputNumberPhone.get(1), NewNumberPhone2);
        p.click(p.buttonSave);
        p.sleep(1000);
        p.waitVisibility(p.appointmentPhoneOrganization.get(0));
        String AppointmentPhoneSite1 = p.appointmentPhoneOrganization.get(0).getText();
        String NumberPhoneSite1 = p.numberPhoneOrganization.get(0).getText();
        String AppointmentPhoneSite2 = p.appointmentPhoneOrganization.get(1).getText();
        String NumberPhoneSite2 = p.numberPhoneOrganization.get(1).getText();

        System.out.println(AppointmentPhoneSite1 + " = " + NamePhone1);
        System.out.println(NumberPhoneSite1 + " = " + NumberPhone1);
        assertEquals(AppointmentPhoneSite1, NamePhone1);
        assertEquals(NumberPhoneSite1, NumberPhone1);

        System.out.println(AppointmentPhoneSite2 + " = " + NewAppointmentPhone2);
        System.out.println(NumberPhoneSite2 + " = " + NewNumberPhone2);
        assertEquals(AppointmentPhoneSite2, NewAppointmentPhone2);
        assertEquals(NumberPhoneSite2, "+ 9 (" + NewNumberPhone2.charAt(0) +
                NewNumberPhone2.charAt(1) + NewNumberPhone2.charAt(2) + ") " + NewNumberPhone2.charAt(3) +
                NewNumberPhone2.charAt(4) + NewNumberPhone2.charAt(5) + "-" + NewNumberPhone2.charAt(6) + NewNumberPhone2.charAt(7) + "-" +
                NewNumberPhone2.charAt(8) + NewNumberPhone2.charAt(9));

        p.click(p.linkChangePhoneOrganization);
        p.click(p.linkDeletePhone.get(0));
        p.click(p.linkDeletePhone.get(0));
        p.click(p.buttonSave);
        p.sleep(1000);
        try {
            Assert.assertTrue(p.appointmentPhoneOrganization.get(0).isEnabled() |
                    p.numberPhoneOrganization.get(0).isEnabled() | p.appointmentPhoneOrganization.get(1).isEnabled() |
                    p.numberPhoneOrganization.get(1).isEnabled());

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Все хорошо - телефоны не найдены");
        }
    }
}