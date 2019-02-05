package Organization.CreateOrganization;

import Global.Ashot;
import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
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

@RunWith(JUnitParamsRunner.class)
public class CreateOrgnizationLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private CreateOrgnizationPage p;

    int u = 0;
    int i = 0;

    String NowDateCreate = "";
    String NowDateCreateAddMin = "";

    private ArrayList<String> ListSaveServices = new ArrayList<>();
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
            p = new CreateOrgnizationPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsCreateOrgnization);
        }
    };

    public void dateCreateOrg() {
        NowDateCreate = GlobalPage.nowDate();
        System.out.println("NowDateCreate = " + NowDateCreate);

        NowDateCreateAddMin = GlobalPage.nowDateAdd1Min();
        System.out.println("NowDateAdd1Min = " + NowDateCreateAddMin);
    }

    public void stepOneCreateOrganization(String OrganizationName, String OrganizationNameAbbr,
                                          String SecondNameDirectorOrganization, String FirstNameDirectorOrganization,
                                          String MiddleNameDirectorOrganization, String FIODirector,
                                          String OrganizationProfile, String ConditionsOrganization) {
        try {
            p.click(p.buttonAddOrganization);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.buttonOrganization);
            p.click(p.buttonAddOrganization);
        }

        p.clickAndSendKeys(p.inputOrganizationName, OrganizationName);
        p.clickAndSendKeys(p.inputOrganizationNameAbbr, OrganizationNameAbbr);
//        p.click(p.buttonEmployeeSearch);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        p.clickAndSendKeys(p.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        p.clickAndSendKeys(p.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        p.click(p.buttonSearchDirector);
        p.sleep(2000);
        p.waitVisibility(p.listFIODirecor.get(0));

        for (WebElement DirectorPhoto : p.listPhotoDirecor) {
            Assert.assertTrue(DirectorPhoto.isEnabled());
        }
        for (WebElement DirectorFIO : p.listFIODirecor) {
            Assert.assertTrue(DirectorFIO.isEnabled());
        }

        WebElement buttonSelectDirector = driver.findElement(By.xpath("//span[text()=\"Акулин\"]/../../." +
                ".//button/span[text()=\"Выбрать руководителя\"]"));


        p.click(buttonSelectDirector);
        Assert.assertTrue(p.photoAssignedDirecor.isEnabled() & p.FIOAssignedDirecor.getText().equals(FIODirector));
        p.click(p.inputStructureOrganization);
        p.click(p.bulletHeadOrganization);
        p.click(p.userinfoname);
        p.click(p.selectOrganizationConditions);

        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();

        p.clickAndSendKeys(p.inputOrganizationProfile, OrganizationProfile);
        p.click(p.buttonAddOrganizationStep1);
    }

    public void stepTwoCreateOrganization(String OrganizationNameAbbr, String CheckedAndFocus, String Checkeds,
                                          String Indeterminate, String Empty) {
        p.stepTwo(OrganizationNameAbbr, CheckedAndFocus, Checkeds, Indeterminate, Empty);
    }


    public void stepThreeCreateOrganization(String DescriptionOrgnization, String AppointmentPhone1,
                                            String PhoneNumber1, String AppointmentPhone2, String PhoneNumber2,
                                            String Adress, String Site, String Email, String Vk,
                                            String Facebook, String Instagram, String OtherName, String OtherValue,
                                            String Code,
                                            String IdentifierOMS, String RegistrationNumber, String IdentifierPUMP) {
        p.clickAndSendKeys(p.inputDescriptionOrgnization, DescriptionOrgnization);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(0), AppointmentPhone1);
        p.clickAndSendKeys(p.inputPhoneNumber.get(0), PhoneNumber1);

        p.click(p.addPhoneNumber);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(1), AppointmentPhone2);
        p.clickAndSendKeys(p.inputPhoneNumber.get(1), PhoneNumber2);
        p.click(p.deletePhoneNumber);

        p.clickAndSendKeys(p.inputAdress, Adress);
        p.sleep(10000);
        p.waitVisibility(p.map);
        p.waitVisibility(p.confirmAdress);
        p.click(p.confirmAdress);
        p.clickAndSendKeys(p.inputSite, Site);
        p.clickAndSendKeys(p.inputEmail, Email);
        p.clickAndSendKeys(p.inputVk, Vk);
        p.clickAndSendKeys(p.inputFacebook, Facebook);
        p.clickAndSendKeys(p.inputInstagram, Instagram);
        p.clickAndSendKeys(p.inputOther, OtherName);
        p.clickAndSendKeys(p.inputOtherValue, OtherValue);
        p.clickAndSendKeys(p.inputCode, Code);
        p.clickAndSendKeys(p.inputRegistryNumber, RegistrationNumber);
        p.clickAndSendKeys(p.inputIdentifierPUMP, IdentifierPUMP);
        p.clickAndSendKeys(p.inputIdentifierOMS, IdentifierOMS);
        p.click(p.buttonAddOrganizationStep3);
    }

    public void stepFourCreateOrganization(String OrganizationName, String OrganizationNameAbbr,
                                           String FIODirector,
                                           String OrganizationProfile, String DescriptionOrgnization,
                                           String AppointmentPhone1, String PhoneNumber1, String AppointmentPhone2,
                                           String PhoneNumber2, String Adress, String Site, String Email, String Vk,
                                           String Facebook, String Instagram, String OtherName, String OtherValue,
                                           String Code,
                                           String IdentifierOMS, String RegistrationNumber, String IdentifierPUMP,
                                           String HeadOrganization, String ConditionsOrganization) {
        p.waitVisibility(p.checkNameOrganization);

        p.compareWebelementAndString(p.checkNameOrganization, OrganizationName);
//        System.out.println(p.checkNameOrganization.getText() + " = " + OrganizationName);
//        Assert.assertEquals(p.checkNameOrganization.getText(), OrganizationName);

        p.compareWebelementAndString(p.checkNameAbbrOrganization, OrganizationNameAbbr);
//        System.out.println(p.checkNameAbbrOrganization.getText() + " = " + OrganizationNameAbbr);
//        Assert.assertEquals(p.checkNameAbbrOrganization.getText(), OrganizationNameAbbr);

        p.compareWebelementAndString(p.checkDirectorOrganization, FIODirector);
//        System.out.println(p.checkDirectorOrganization.getText() + " = " + FIODirector);
//        Assert.assertEquals(p.checkDirectorOrganization.getText(), FIODirector);

        p.compareWebelementAndString(p.checkHeadOrganization, HeadOrganization);
//        System.out.println(p.checkHeadOrganization.getText() + " = " + HeadOrganization);
//        Assert.assertEquals(p.checkHeadOrganization.getText(), HeadOrganization);

        p.compareWebelementAndString(p.checkConditionsOrganization, ConditionsOrganization);
//        System.out.println(p.checkConditionsOrganization.getText() + " = " + ConditionsOrganization);
//        Assert.assertEquals(p.checkConditionsOrganization.getText(), ConditionsOrganization);

        p.compareWebelementAndString(p.checkProfileOrganization, OrganizationProfile);
//        System.out.println(p.checkProfileOrganization.getText() + " = " + OrganizationProfile);
//        Assert.assertEquals(p.checkProfileOrganization.getText(), OrganizationProfile);

        for (WebElement Service : p.saveServices) {
            p.moveMouse(Service);
            ListSaveServices.add(Service.getText().replace("\n", " "));
        }


        p.compareArrayListAndArrayList(p.SelectedServices, ListSaveServices, ListSaveServices.size());
//        Assert.assertEquals(p.SelectedServices, ListSaveServices);

        p.compareWebelementAndString(p.checkDescriptionOrganization, DescriptionOrgnization);
//        System.out.println(p.checkDescriptionOrganization.getText() + " = " + DescriptionOrgnization);
//        Assert.assertEquals(p.checkDescriptionOrganization.getText(), DescriptionOrgnization);

        p.compareWebelementAndString(p.checkAdressOrganization, Adress);
//        System.out.println(p.checkAdressOrganization.getText() + " = " + Adress);
//        Assert.assertEquals(p.checkAdressOrganization.getText(), Adress);

        p.compareWebelementAndString(p.checkSiteOrganization, Site);
//        System.out.println(p.checkSiteOrganization.getText() + " = " + Site);
//        Assert.assertEquals(p.checkSiteOrganization.getText(), Site);

        p.compareWebelementAndString(p.checkEmailOrganization, Email);
//        System.out.println(p.checkEmailOrganization.getText() + " = " + Email);
//        Assert.assertEquals(p.checkEmailOrganization.getText(), Email);

        p.compareWebelementAndString(p.checkVkOrganization, Vk);
//        System.out.println(p.checkVkOrganization.getText() + " = " + Vk);
//        Assert.assertEquals(p.checkVkOrganization.getText(), Vk);

        p.compareWebelementAndString(p.checkFacebookOrganization, Facebook);
//        System.out.println(p.checkFacebookOrganization.getText() + " = " + Facebook);
//        Assert.assertEquals(p.checkFacebookOrganization.getText(), Facebook);

        p.compareWebelementAndString(p.checkInstagramOrganization, Instagram);
//        System.out.println(p.checkInstagramOrganization.getText() + " = " + Instagram);
//        Assert.assertEquals(p.checkInstagramOrganization.getText(), Instagram);

        p.compareWebelementAndString(p.webElementOtherName(OtherName), OtherName);
//        p.compareStringAndString(OtherName, p.webElementOtherName(OtherName).getText());
        p.compareWebelementAndString(p.webElementOtherName(OtherValue), OtherValue);
//        p.compareStringAndString(OtherValue, p.webElementOtherName(OtherValue).getText());

        p.compareWebelementAndString(p.checkAppointmentPhone1.get(0), AppointmentPhone1);
//        System.out.println(p.checkAppointmentPhone1.get(0).getText() + " = " + AppointmentPhone1);
//        Assert.assertEquals(p.checkAppointmentPhone1.get(0).getText(), AppointmentPhone1);

        p.compareWebelementAndString(p.checkPhoneNumber1.get(0), "+ " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) + PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" + PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));
//        System.out.println(p.checkPhoneNumber1.get(0).getText() + " = + " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) +
//                PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" +
//                PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));
//        Assert.assertEquals(p.checkPhoneNumber1.get(0).getText(),
//                "+ " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) +
//                        PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" +
//                        PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));

        p.click(p.buttonFinish);
        dateCreateOrg();
//        NowDate = p.nowDate();
        p.click(p.menuOrganizations);
        p.sleep(1000);
    }

    public void stepOneCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                      String SecondNameDirectorOrganization,
                                                      String FirstNameDirectorOrganization,
                                                      String MiddleNameDirectorOrganization, String FIODirector,
                                                      String OrganizationProfile, String ConditionsOrganization) {
        p.waitTextPresent(p.buttonOrganization, "Организация");
        p.click(p.buttonOrganization);
        p.click(p.buttonAddOrganization);
        p.clickAndSendKeys(p.inputOrganizationName, OrganizationName);
        p.clickAndSendKeys(p.inputOrganizationNameAbbr, OrganizationNameAbbr);
        p.click(p.buttonEmployeeSearch);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        p.clickAndSendKeys(p.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        p.clickAndSendKeys(p.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);

        System.out.println("Введенные значения: " + p.inputSecondNameDirectorOrganization.getAttribute("value")
                + " " + p.inputFirstNameDirectorOrganization.getAttribute("value")
                + " " + p.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertEquals(p.inputSecondNameDirectorOrganization.getAttribute("value"),
                SecondNameDirectorOrganization);
        Assert.assertEquals(p.inputFirstNameDirectorOrganization.getAttribute("value"),
                FirstNameDirectorOrganization);
        Assert.assertEquals(p.inputMiddleNameDirectorOrganization.getAttribute("value"),
                MiddleNameDirectorOrganization);

        p.click(p.buttonClear);
        System.out.println("Значения сброшены: " + p.inputSecondNameDirectorOrganization.getAttribute("value") +
                p.inputFirstNameDirectorOrganization.getAttribute("value") + p.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertEquals("", p.inputSecondNameDirectorOrganization.getAttribute("value"));
        Assert.assertEquals("", p.inputFirstNameDirectorOrganization.getAttribute("value"));
        Assert.assertEquals("", p.inputMiddleNameDirectorOrganization.getAttribute("value"));
        p.click(p.buttonEmployeeSearch);
        p.sleep(1000);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !p.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо");
        }
        p.click(p.buttonEmployeeSearch);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
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

//        p.sleep(2000);
//        p.waitVisibility(p.listFIODirecor.get(0));
//        Assert.assertTrue(p.listPhotoDirecor.get(0).isEnabled() & p.listFIODirecor.get(1).getText().equals(FIODirector));
        p.click(p.buttonClear);
        p.sleep(1000);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !p.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо-2");
        }

        p.click(p.buttonEmployeeSearch);
        p.clickAndSendKeys(p.inputFirstNameDirectorOrganization, "вас");
        p.click(p.buttonSearchDirector);
        p.sleep(2000);
        p.waitVisibility(p.listFIODirecor.get(0));
        Assert.assertTrue(p.listPhotoDirecor.get(1).isEnabled() & p.listFIODirecor.get(1).isEnabled());
        p.click(p.buttonSelectDirecor.get(1));
        p.click(p.changeDirecor);
        p.clickAndSendKeys(p.inputSecondNameDirectorOrganization, "а");
        p.click(p.buttonSearchDirector);
        p.sleep(2000);
        p.waitVisibility(p.listFIODirecor.get(0));
        p.click(p.buttonSelectDirecor.get(0));
        p.click(p.changeDirecor);
//        p.click(p.userinfoname);
        p.waitE_ClickableAndClickAndUp(p.buttonClear);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !p.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо-возращение");
        }

        if (OrganizationName.equals("2 Больница Ордена красного знамени")) {
            p.click(p.chekboxCreateHeadOrg);
        } else {

            p.click(p.inputStructureOrganization);
            p.click(p.NameHeadOrganization);
            p.click(p.nameKardio1);
            p.click(p.nameKardio2);
            p.click(p.nameKardio3);
            p.click(p.bulletKardio4);
            p.click(p.buttonAddOrganizationStep1);
        }
    }

    public void stepThreeCreateOrganizationTestDirector(String DescriptionOrgnization, String AppointmentPhone1,
                                                        String PhoneNumber1, String AppointmentPhone2,
                                                        String PhoneNumber2,
                                                        String Adress, String Site, String Email, String Vk,
                                                        String Facebook, String Instagram, String OtherName,
                                                        String OtherValue, String Code,
                                                        String IdentifierOMS, String RegistrationNumber,
                                                        String IdentifierPUMP) {
        p.clickAndSendKeys(p.inputDescriptionOrgnization, DescriptionOrgnization);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(0), AppointmentPhone1);
        p.clickAndSendKeys(p.inputPhoneNumber.get(0), PhoneNumber1);

        p.click(p.addPhoneNumber);
        p.clickAndSendKeys(p.inputAppointmentPhone.get(1), AppointmentPhone2);
        p.clickAndSendKeys(p.inputPhoneNumber.get(1), PhoneNumber2);
        p.scrollHomePage();
        p.waitVisibility(p.inputSite);
        Ashot.screenshot(driver, "stepThreeCreateOrganizationTestDirector", 100000, 150000);
        p.clickAndSendKeys(p.inputAdress, Adress);
        p.sleep(10000);
        p.waitVisibility(p.map);
        p.waitVisibility(p.confirmAdress);
        p.click(p.confirmAdress);
        p.scrollHomePage();
        p.waitVisibility(p.inputSite);
        p.sleep(500);
        Ashot.screenshot(driver, "stepThreeCreateOrganizationTestDirector", 100000, 150000);
        p.clickAndSendKeys(p.inputSite, Site);
        p.clickAndSendKeys(p.inputEmail, Email);
        p.clickAndSendKeys(p.inputVk, Vk);
        p.clickAndSendKeys(p.inputFacebook, Facebook);
        p.clickAndSendKeys(p.inputInstagram, Instagram);
        p.clickAndSendKeys(p.inputOther, OtherName);
        p.clickAndSendKeys(p.inputOtherValue, OtherValue);
        p.clickAndSendKeys(p.inputCode, Code);
        p.clickAndSendKeys(p.inputRegistryNumber, RegistrationNumber);
        p.clickAndSendKeys(p.inputIdentifierPUMP, IdentifierPUMP);
        p.clickAndSendKeys(p.inputIdentifierOMS, IdentifierOMS);
        p.click(p.buttonAddOrganizationStep3);
    }

    public void stepFourCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                       String FIODirector, String OrganizationProfile,
                                                       String DescriptionOrgnization, String AppointmentPhone1,
                                                       String PhoneNumber1, String AppointmentPhone2,
                                                       String PhoneNumber2, String Adress, String Site, String Email,
                                                       String Vk,
                                                       String Facebook, String Instagram, String OtherName,
                                                       String OtherValue, String Code,
                                                       String IdentifierOMS, String RegistrationNumber,
                                                       String IdentifierPUMP, String HeadOrganization,
                                                       String ConditionsOrganization, String Status) {
        p.waitVisibility(p.checkNameOrganization);

        System.out.println(p.checkNameOrganization.getText() + " = " + OrganizationName);
        Assert.assertEquals(p.checkNameOrganization.getText(), OrganizationName);

        System.out.println(p.checkNameAbbrOrganization.getText() + " = " + OrganizationNameAbbr);
        Assert.assertEquals(p.checkNameAbbrOrganization.getText(), OrganizationNameAbbr);

        try {
            Assert.assertTrue("Найден руководитель", !p.checkDirectorOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Руководитель не назначен и это хорошо");
        }

        p.compareWebelementAndString(p.checkHeadOrganization, HeadOrganization);
//        System.out.println(p.checkHeadOrganization.getText() + " = " + HeadOrganization);
//        Assert.assertEquals(p.checkHeadOrganization.getText(), HeadOrganization);

        try {
            Assert.assertTrue("Найдены условия оказания помощи", !p.checkConditionsOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Условия оказания помощи не найдены и это хорошо");
        }


        try {
            Assert.assertTrue("Найдены профиль организации", !p.checkProfileOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Профиль организации не найден и это хорошо");
        }

        for (WebElement Service : p.saveServices) {
            p.moveMouse(Service);
            p.buttonDown(2);
            ListSaveServices.add(Service.getText().replace("\n", " "));
        }

        p.compareArrayListAndArrayList(p.SelectedServices, ListSaveServices, ListSaveServices.size());
//        System.out.println(p.SelectedServices);
//        System.out.println(ListSaveServices);
//        Assert.assertEquals(p.SelectedServices, ListSaveServices);

        p.compareWebelementAndString(p.checkDescriptionOrganization, DescriptionOrgnization);
//        System.out.println(p.checkDescriptionOrganization.getText() + " = " + DescriptionOrgnization);
//        Assert.assertEquals(p.checkDescriptionOrganization.getText(), DescriptionOrgnization);

        p.compareWebelementAndString(p.checkAdressOrganization, Adress);
//        System.out.println(p.checkAdressOrganization.getText() + " = " + Adress);
//        Assert.assertEquals(p.checkAdressOrganization.getText(), Adress);

        p.compareWebelementAndString(p.checkSiteOrganization, Site);
//        System.out.println(p.checkSiteOrganization.getText() + " = " + Site);
//        Assert.assertEquals(p.checkSiteOrganization.getText(), Site);

        p.compareWebelementAndString(p.checkEmailOrganization, Email);
//        System.out.println(p.checkEmailOrganization.getText() + " = " + Email);
//        Assert.assertEquals(p.checkEmailOrganization.getText(), Email);

        p.compareWebelementAndString(p.checkVkOrganization, Vk);
//        System.out.println(p.checkVkOrganization.getText() + " = " + Vk);
//        Assert.assertEquals(p.checkVkOrganization.getText(), Vk);

        p.compareWebelementAndString(p.checkFacebookOrganization, Facebook);
//        System.out.println(p.checkFacebookOrganization.getText() + " = " + Facebook);
//        Assert.assertEquals(p.checkFacebookOrganization.getText(), Facebook);

        p.compareWebelementAndString(p.checkInstagramOrganization, Instagram);
//        System.out.println(p.checkInstagramOrganization.getText() + " = " + Instagram);
//        Assert.assertEquals(p.checkInstagramOrganization.getText(), Instagram);

        p.compareWebelementAndString(p.webElementOtherName(OtherName), OtherName);
//        p.compareStringAndString(OtherName, p.webElementOtherName(OtherName).getText());
        p.compareWebelementAndString(p.webElementOtherName(OtherValue), OtherValue);
//        p.compareStringAndString(OtherValue, p.webElementOtherName(OtherValue).getText());

        p.compareWebelementAndString(p.checkAppointmentPhone1.get(0), AppointmentPhone1);
//        System.out.println(p.checkAppointmentPhone1.get(0).getText() + " = " + AppointmentPhone1);
//        Assert.assertEquals(p.checkAppointmentPhone1.get(0).getText(), AppointmentPhone1);

        p.compareWebelementAndString(p.checkPhoneNumber1.get(0), "+ " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) + PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" + PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));
        ;
//        System.out.println(p.checkPhoneNumber1.get(0).getText() + " = + " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) + PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" + PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));
//        Assert.assertEquals(p.checkPhoneNumber1.get(0).getText(),
//                "+ " + PhoneNumber1.charAt(0) + " (" + PhoneNumber1.charAt(1) + PhoneNumber1.charAt(2) +
//                        PhoneNumber1.charAt(3) + ") " + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + PhoneNumber1.charAt(6) + "-" +
//                        PhoneNumber1.charAt(7) + PhoneNumber1.charAt(8) + "-" + PhoneNumber1.charAt(9) + PhoneNumber1.charAt(10));

        p.compareWebelementAndString(p.checkAppointmentPhone1.get(1), AppointmentPhone2);
//        System.out.println(p.checkAppointmentPhone1.get(1).getText() + " = " + AppointmentPhone2);
//        Assert.assertEquals(p.checkAppointmentPhone1.get(1).getText(), AppointmentPhone2);

        p.compareWebelementAndString(p.checkPhoneNumber1.get(1), "+ " + PhoneNumber2.charAt(0) + " (" + PhoneNumber2.charAt(1) + PhoneNumber2.charAt(2) + PhoneNumber2.charAt(3) + ") " + PhoneNumber2.charAt(4) + PhoneNumber2.charAt(5) + PhoneNumber2.charAt(6) + "-" + PhoneNumber2.charAt(7) + PhoneNumber2.charAt(8) + "-" + PhoneNumber2.charAt(9) + PhoneNumber2.charAt(10));
//        System.out.println(p.checkPhoneNumber1.get(1).getText() + " = + " + PhoneNumber2.charAt(0) + " (" + PhoneNumber2.charAt(1) + PhoneNumber2.charAt(2) +
//                PhoneNumber2.charAt(3) + ") " + PhoneNumber2.charAt(4) + PhoneNumber2.charAt(5) + PhoneNumber2.charAt(6) + "-" +
//                PhoneNumber2.charAt(7) + PhoneNumber2.charAt(8) + "-" + PhoneNumber2.charAt(9) + PhoneNumber2.charAt(10));
//        Assert.assertEquals(p.checkPhoneNumber1.get(1).getText(),
//                "+ " + PhoneNumber2.charAt(0) + " (" + PhoneNumber2.charAt(1) + PhoneNumber2.charAt(2) +
//                        PhoneNumber2.charAt(3) + ") " + PhoneNumber2.charAt(4) + PhoneNumber2.charAt(5) + PhoneNumber2.charAt(6) + "-" +
//                        PhoneNumber2.charAt(7) + PhoneNumber2.charAt(8) + "-" + PhoneNumber2.charAt(9) + PhoneNumber2.charAt(10));

        p.click(p.buttonFinish);
        dateCreateOrg();
//        NowDate = p.nowDate();
        p.click(p.menuOrganizations);
        p.sleep(1000);
    }

    public void stepFiveCreateOrganization(String OrganizationName, String OrganizationNameAbbr, String FIODirector,
                                           String OrganizationProfile, String DescriptionOrgnization,
                                           String NamePhone1, String NumberPhone1, String NamePhone2,
                                           String NumberPhone2, String Adress, String Site, String Email, String Vk,
                                           String Facebook,
                                           String Instagram, String OtherName, String OtherValue, String Code,
                                           String IdentifierOMS,
                                           String RegistrationNumber, String IdentifierPUMP, String HeadOrganization,
                                           String ConditionsOrganization, String Status) {
        p.moveToProfileOrg(OrganizationName);
        p.sleep(2000);
        p.waitVisibility(p.qrProfOrg);


        p.compareWebelementAndString(p.nameProfOrg, OrganizationName);
        p.compareWebelementAndString(p.nameAbbrProfOrg, OrganizationNameAbbr);
        p.compareWebelementAndString(p.headOrganizationProfOrg, HeadOrganization);
        p.compareWebelementAndString(p.termsProfOrg, ConditionsOrganization);
        p.compareWebelementAndString(p.mainStreamProfOrg, OrganizationProfile);
//        Assert.assertTrue(p.linkAppointmentServicesProfOrg.isEnabled());
        p.compareWebelementAndString(p.directorFIOProfOrg, FIODirector);
        p.compareWebelementAndString(p.descriptionProfOrg, DescriptionOrgnization);
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());

        int x = -1;
        for (WebElement Service : p.listNamesServicesProfOrg) {
            p.moveMouse(Service);
            p.buttonDown(2);
            x = x + 1;
            ListServicesInProfile.add(p.listNamesServicesProfOrg.get(x).getText() + " " +
                    p.listVendorsServicesProfOrg.get(x).getText() + " " + p.listCostsServicesProfOrg.get(x).getText());
        }
//        System.out.println(p.SelectedServices);
//        System.out.println(ListServicesInProfile);
        p.compareArrayListAndArrayList(p.SelectedServices, ListServicesInProfile, p.listNamesServicesProfOrg.size());
//        p.compareWebelementsListAndArrayList(p.listNamesServicesProfOrg, p.SelectedServices, 1);
//        Assert.assertEquals(p.SelectedServices, ListServicesInProfile);
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        driver.navigate().refresh();
        p.preloader();
        p.waitVisibility(p.mapProfOrg);
//        Assert.assertTrue(p.mapProfOrg.isEnabled());
        p.waitTextPresent(p.buttonEditProfOrg, "Ред. подразделение");
        p.click(p.buttonEditProfOrg);
        p.waitVisibility(p.headerEditOrg);
        Assert.assertEquals("Редактировать организацию: " + OrganizationName, p.headerEditOrg.getText());
        driver.navigate().back();
        p.waitVisibility(p.adressProfOrg);
        p.compareWebelementAndString(p.adressProfOrg, Adress);

        ListNamePhone.add(NamePhone1);
//        ListNamePhone.add(NamePhone2);
        ListNumberPhone.add("+ " + NumberPhone1.charAt(0) + " (" + NumberPhone1.charAt(1) + NumberPhone1.charAt(2) + NumberPhone1.charAt(3) + ") " + NumberPhone1.charAt(4) + NumberPhone1.charAt(5) + NumberPhone1.charAt(6) + "-" + NumberPhone1.charAt(7) + NumberPhone1.charAt(8) + "-" + NumberPhone1.charAt(9) + NumberPhone1.charAt(10));
//        ListNumberPhone.add(NumberPhone2);

        p.compareWebelementsListAndArrayList(p.listNamePhoneProfOrg, ListNamePhone, 1);

//        for (WebElement NameProf : p.listNamePhoneProfOrg) {
//            i = i + 1;
//            for (String NamePh : ListNamePhone) {
//                if (NameProf.getText().equals(NamePh)) {
//                    u = u + 1;
//                    System.out.println("!+Наименование телефона найдено+!");
//                    break;
//                } else {
//                    System.out.println("Наименование телефона НЕ найдено");
//                }
//                Assert.assertEquals("Совпаение наименований телефонов не найдено", 1, u);
//            }
//        }
        p.compareWebelementsListAndArrayList(p.listNumbersPhoneProfOrg, ListNumberPhone, 1);

//        for (WebElement NumberProf : p.listNumbersPhoneProfOrg) {
//            i = i + 1;
//            for (String NumberPh : ListNumberPhone) {
//                if (NumberProf.getText().equals(NumberPh)) {
//                    u = u + 1;
//                    System.out.println("!+Номер телефона найден+!");
//                    break;
//                } else {
//                    System.out.println("Номер телефона НЕ найден");
//                }
//                Assert.assertEquals("Совпаение номеров телефонов не найдено", 1, u);
//            }
//        }
        p.compareWebelementAndString(p.siteProfOrg, Site);
        p.compareWebelementAndString(p.emailProfOrg, Email);
        p.compareWebelementAndString(p.vkProfOrg, Vk);
        p.compareWebelementAndString(p.instagramProfOrg, Instagram);
        p.compareWebelementAndString(p.facebookProfOrg, Facebook);
        p.compareWebelementAndString(p.webElementOtherName(OtherName), OtherName);
        p.compareWebelementAndString(p.otherProfOrg, OtherValue);
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareWebelementAndString(p.codeProfOrg, Code);
        p.compareWebelementAndString(p.numberOmsProfOrg, RegistrationNumber);
        p.compareWebelementAndString(p.identifierOmsProfOrg, IdentifierOMS);
        p.compareWebelementAndString(p.identifierPumpProfOrg, IdentifierPUMP);
        p.compareWebelementAndString(p.statusProfOrg, Status);

        System.out.println("NowDateCreate = " + NowDateCreate);
        System.out.println("NowDateCreateAddMin = " + NowDateCreateAddMin);
        System.out.println("Дата создания в профиле = " + p.dateCreateProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));
//        p.compareStringAndString(NowDateCreate, p.dateCreateProfOrg.getText());
        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле = " + p.lastChangeProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.lastChangeProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.lastChangeProfOrg.getText()));

//        p.compareWebelementAndString(p.dateCreateProfOrg, NowDate);
//        p.compareWebelementAndString(p.lastChangeProfOrg, NowDate);
        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }

    public void stepFiveCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                       String FIODirector,
                                                       String OrganizationProfile, String DescriptionOrgnization,
                                                       String NamePhone1, String NumberPhone1,
                                                       String NamePhone2,
                                                       String NumberPhone2, String Adress, String Site, String Email,
                                                       String Vk,
                                                       String Facebook,
                                                       String Instagram, String OtherName, String OtherValue,
                                                       String Code,
                                                       String IdentifierOMS,
                                                       String RegistrationNumber, String IdentifierPUMP,
                                                       String HeadOrganization,
                                                       String ConditionsOrganization, String Status) {
        p.moveToProfileOrg(OrganizationName);
        p.waitVisibility(p.qrProfOrg);

        p.compareWebelementAndString(p.nameProfOrg, OrganizationName);
        p.compareStringAndString(OrganizationName, p.nameProfOrg.getText());
        p.compareWebelementAndString(p.nameAbbrProfOrg, OrganizationNameAbbr);
        p.compareWebelementAndString(p.headOrganizationProfOrg, HeadOrganization);
        p.compareWebelementAndString(p.termsProfOrg, "Не указано");
        p.compareWebelementAndString(p.mainStreamProfOrg, "Не указан");
        driver.navigate().refresh();
//        Assert.assertTrue(p.linkAppointmentServicesProfOrg.isEnabled());
        p.compareWebelementAndString(p.directorFirstNameProfOrg, "Не указан");
        p.compareWebelementAndString(p.descriptionProfOrg, "Описание длиной более 180 символов 11111111111" +
                " 111111111111 111111111111111111111111111111111 1111111111111111111111 111111111111111111 1111111111111...");
        p.click(p.linkDescriptionAdditionalProfOrg);
        p.compareWebelementAndString(p.fullDescriptionProfOrg, DescriptionOrgnization);
        p.elementIsDisabled(p.linkDescriptionAdditionalProfOrg);
        p.click(p.linkFullDescriptionHideProfOrg);
        p.compareWebelementAndString(p.descriptionProfOrg, "Описание длиной более 180 символов 11111111111" +
                " 111111111111 111111111111111111111111111111111 1111111111111111111111 111111111111111111 1111111111111...");
        p.waitVisibility(p.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(p.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(p.headerServicesProfOrg.isEnabled());

        int x = -1;
        for (WebElement Service : p.listNamesServicesProfOrg) {
            x = x + 1;
            p.moveMouse(Service);
            p.buttonDown(2);
            ListServicesInProfile.add(p.listNamesServicesProfOrg.get(x).getText() + " " +
                    p.listVendorsServicesProfOrg.get(x).getText() + " " + p.listCostsServicesProfOrg.get(x).getText());
        }
        System.out.println(p.SelectedServices);
        System.out.println(ListServicesInProfile);

        Assert.assertEquals(p.SelectedServices, ListServicesInProfile);
        Assert.assertTrue(p.headerExecutorsProfOrg.isEnabled());
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();
        p.moveMouse(p.mapProfOrg);
        Assert.assertTrue(p.mapProfOrg.isEnabled());
        p.click(p.buttonEditProfOrg);
        p.waitVisibility(p.headerEditOrg);
        Assert.assertEquals("Редактировать организацию: " + OrganizationName, p.headerEditOrg.getText());
        driver.navigate().back();
        p.waitVisibility(p.adressProfOrg);
        p.compareStringAndString(Adress, p.adressProfOrg.getText());

        ListNamePhone.add(NamePhone1);
        ListNamePhone.add(NamePhone2);
        ListNumberPhone.add("+ " + NumberPhone1.charAt(0) + " (" + NumberPhone1.charAt(1) + NumberPhone1.charAt(2) + NumberPhone1.charAt(3) + ") " + NumberPhone1.charAt(4) + NumberPhone1.charAt(5) + NumberPhone1.charAt(6) + "-" + NumberPhone1.charAt(7) + NumberPhone1.charAt(8) + "-" + NumberPhone1.charAt(9) + NumberPhone1.charAt(10));
        ListNumberPhone.add("+ " + NumberPhone2.charAt(0) + " (" + NumberPhone2.charAt(1) + NumberPhone2.charAt(2) + NumberPhone2.charAt(3) + ") " + NumberPhone2.charAt(4) + NumberPhone2.charAt(5) + NumberPhone2.charAt(6) + "-" + NumberPhone2.charAt(7) + NumberPhone2.charAt(8) + "-" + NumberPhone2.charAt(9) + NumberPhone2.charAt(10));

        p.compareArrayListAndWebelementsList(ListNamePhone, p.listNamePhoneProfOrg, 2);

//        for (WebElement NameProf : p.listNamePhoneProfOrg) {
//            i = i + 1;
//            for (String NamePh : ListNamePhone) {
//                if (NameProf.getText().equals(NamePh)) {
//                    u = u + 1;
//                    System.out.println("!+Наименование телефона найдено+!");
//                    break;
//                } else {
//                    System.out.println("Наименование телефона НЕ найдено");
//                }
//                Assert.assertEquals("Совпаение наименований телефонов не найдено", 1, u);
//            }
//        }

        p.compareArrayListAndWebelementsList(ListNumberPhone, p.listNumbersPhoneProfOrg, 2);

//        for (WebElement NumberProf : p.listNumbersPhoneProfOrg) {
//            i = i + 1;
//            for (String NumberPh : ListNumberPhone) {
//                if (NumberProf.getText().equals(NumberPh)) {
//                    u = u + 1;
//                    System.out.println("!+Номер телефона найден+!");
//                    break;
//                } else {
//                    System.out.println("Номер телефона НЕ найден");
//                }
//                Assert.assertEquals("Совпаение номеров телефонов не найдено", 2, u);
//            }
//        }
        p.compareWebelementAndString(p.emailProfOrg, Email);
        p.compareWebelementAndString(p.vkProfOrg, Vk);
        p.compareWebelementAndString(p.instagramProfOrg, Instagram);
        p.compareWebelementAndString(p.facebookProfOrg, Facebook);
        p.compareWebelementAndString(p.webElementOtherName(OtherName), OtherName);
        p.compareWebelementAndString(p.otherProfOrg, OtherValue);
        Assert.assertTrue(p.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(p.idProfOrg.getText()));
        p.compareWebelementAndString(p.codeProfOrg, Code);
        p.compareWebelementAndString(p.numberOmsProfOrg, RegistrationNumber);
        p.compareWebelementAndString(p.identifierOmsProfOrg, IdentifierOMS);
        p.compareWebelementAndString(p.identifierPumpProfOrg, IdentifierPUMP);
        p.compareWebelementAndString(p.statusProfOrg, Status);

        System.out.println("NowDateCreate = " + NowDateCreate);
        System.out.println("NowDateCreateAddMin = " + NowDateCreateAddMin);
        System.out.println("Дата создания в профиле = " + p.dateCreateProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.dateCreateProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.dateCreateProfOrg.getText()));
//        p.compareStringAndString(NowDateCreate, p.dateCreateProfOrg.getText());
        p.moveMouse(p.lastChangeProfOrg);
        System.out.println("Дата редактирования в профиле = " + p.lastChangeProfOrg.getText());
        Assert.assertTrue(NowDateCreate.equals(p.lastChangeProfOrg.getText()) ||
                NowDateCreateAddMin.equals(p.lastChangeProfOrg.getText()));

//        p.compareWebelementAndString(p.dateCreateProfOrg, NowDate);
//        p.compareWebelementAndString(p.lastChangeProfOrg, NowDate);
        p.moveMouse(p.qrProfOrg);
        Assert.assertTrue(p.qrProfOrg.isEnabled());
    }
}