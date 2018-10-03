package editorganization;

import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

@RunWith(JUnitParamsRunner.class)
public class EditOrganizationLogic {

    private static WebDriver driver;
    private static EditOrganizationPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new EditOrganizationPage(driver);
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

    public void editDataOrganization(String Name, String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                     String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                     String FIODirector, String NewHead, String ConditionsOrganization,
                                     String ProfileOrganization) {
        page.moveToProfileOrg(Name);
        page.waitE_visibilityOf(page.nameOrganization);
        String Name1 = page.nameOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeNameOrganization);
        page.waitE_ClickableAndSendKeys(page.inputNameOrganization, NewName);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.waitE_visibilityOf(page.nameOrganization);
        String Name2 = page.nameOrganization.getText();
        System.out.println(Name1 + " != " + Name2 + " = " + NewName);
        Assert.assertTrue(!Name1.equals(Name2) & Name2.equals(NewName));

        String Abbr1 = page.abbrNameOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeAbbrNameOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAbbrNameOrganization, NewAbbr);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.waitE_visibilityOf(page.abbrNameOrganization);
        String Abbr2 = page.abbrNameOrganization.getText();
        System.out.println(Abbr1 + " != " + Abbr2 + " = " + NewAbbr);
        Assert.assertTrue(!Abbr1.equals(Abbr2) & Abbr2.equals(NewAbbr));

        String Director1 = page.directorOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeDirectorOrganization);
        page.waitE_ClickableAndClick(page.searchDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        page.waitE_ClickableAndClick(page.buttonSearch);
        page.waitE_visibilityOf(page.FIODirecor);
        Assert.assertTrue(page.photoDirecor.isEnabled() & page.FIODirecor.getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.FIODirecor);
        page.waitE_visibilityOf(page.newDirectorOrganization);
        String Director2 = page.newDirectorOrganization.getText();
        System.out.println(Director1 + " != " + Director2 + " = " + FIODirector);
        Assert.assertTrue(!Director1.equals(Director2) & Director2.equals(FIODirector));

        String Head1 = page.headOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeHeadOrganization);
        if (FirstNameDirectorOrganization.equals("Арис")) {
            page.waitE_ClickableAndClick(page.NameHeadOrganization);
            page.waitE_ClickableAndClick(page.NameDentalCenter);
            page.waitE_ClickableAndClick(page.NameDepartmentTherapeuticStomatology);
            page.waitE_ClickableAndClick(page.NameTechnicalDepartment);
            page.waitE_ClickableAndClick(page.bulletSecondNeurologicalDepartment);
        } else {
            page.waitE_ClickableAndClick(page.bulletHeadOrganization);
        }
        page.waitE_ClickableAndClick(page.buttonSaveHeadOrganization);
        page.waitE_visibilityOf(page.headOrganization);
        String Head2 = page.headOrganization.getText();
        System.out.println(Head1 + " != " + Head2 + " = " + NewHead);
        Assert.assertTrue(!Head1.equals(Head2) & Head2.equals(NewHead));

        String Conditions1 = page.conditionsOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeConditionsOrganization);
        page.waitE_ClickableAndClick(page.selectOrganizationConditions);
        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();
        page.waitE_ClickableAndClick(page.buttonSave);
        page.waitE_visibilityOf(page.conditionsOrganization);
        String Conditions2 = page.conditionsOrganization.getText();
        System.out.println(Conditions1 + " != " + Conditions2 + " = " + ConditionsOrganization);
        Assert.assertTrue(!Conditions1.equals(Conditions2) & Conditions2.equals(ConditionsOrganization));

        String Profile1 = page.profileOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeProfileOrganization);
        page.waitE_ClickableAndSendKeys(page.inputProfileOrganization, ProfileOrganization);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.waitE_visibilityOf(page.profileOrganization);
        String Profile2 = page.profileOrganization.getText();
        System.out.println(Profile1 + " != " + Profile2 + " = " + ProfileOrganization);
        Assert.assertTrue(!Profile1.equals(Profile2) & Profile2.equals(ProfileOrganization));

        page.waitE_ClickableAndClick(page.linkDeleteOrganization);
        WebElement NameDeleteOrganization2 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div/div[2]/div[1]/p[contains (text(), \"" + NewName + "\")]")));
        System.out.println(NameDeleteOrganization2.getText() + " = " + NewName);
        Assert.assertEquals(NameDeleteOrganization2.getText(), NewName);
        page.waitE_ClickableAndClick(page.buttonDelete);
        page.waitE_visibilityOf(page.listNameOrganizations);
    }

    public void editDataOrganizationCancel(String Name, String NewName, String NewAbbr,
                                           String SecondNameDirectorOrganization,
                                           String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                           String FIODirector, String NewHead, String ConditionsOrganization,
                                           String ProfileOrganization) {
        page.moveToProfileOrg(Name);
        page.waitE_visibilityOf(page.nameOrganization);
        String Name1 = page.nameOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeNameOrganization);
        page.waitE_ClickableAndSendKeys(page.inputNameOrganization, NewName);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.nameOrganization);
        String Name2 = page.nameOrganization.getText();
        System.out.println(Name1 + " = " + Name2);
        Assert.assertEquals(Name1, Name2);

        String Abbr1 = page.abbrNameOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeAbbrNameOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAbbrNameOrganization, NewAbbr);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.abbrNameOrganization);
        String Abbr2 = page.abbrNameOrganization.getText();
        System.out.println(Abbr1 + " = " + Abbr2);
        Assert.assertEquals(Abbr1, Abbr2);

        String Director1 = page.directorOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeDirectorOrganization);
        page.waitE_ClickableAndClick(page.searchDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.directorOrganization);
        String Director2 = page.directorOrganization.getText();
        System.out.println(Director1 + " = " + Director2);
        Assert.assertEquals(Director1, Director2);

        page.waitE_ClickableAndClick(page.linkChangeHeadOrganization);
        page.waitE_ClickableAndClick(page.bulletHeadOrganization);
        page.waitE_ClickableAndClick(page.buttonSaveHeadOrganization);
        page.waitE_visibilityOf(page.headOrganization);
        String Head1 = page.headOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeHeadOrganization);
        page.waitE_ClickableAndClick(page.NameHeadOrganization);
        page.waitE_ClickableAndClick(page.NameDentalCenter);
        page.waitE_ClickableAndClick(page.NameDepartmentTherapeuticStomatology);
        page.waitE_ClickableAndClick(page.NameTechnicalDepartment);
        page.waitE_ClickableAndClick(page.bulletSecondNeurologicalDepartment);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.headOrganization);
        String Head2 = page.headOrganization.getText();
        System.out.println(Head1 + " = " + Head2);
        Assert.assertEquals(Head1, Head2);

        String Conditions1 = page.conditionsOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeConditionsOrganization);
        page.waitE_ClickableAndClick(page.selectOrganizationConditions);
        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.conditionsOrganization);
        String Conditions2 = page.conditionsOrganization.getText();
        System.out.println(Conditions1 + " = " + Conditions2);
        Assert.assertEquals(Conditions1, Conditions2);

        String Profile1 = page.profileOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeProfileOrganization);
        page.waitE_ClickableAndSendKeys(page.inputProfileOrganization, ProfileOrganization);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.profileOrganization);
        String Profile2 = page.profileOrganization.getText();
        System.out.println(Profile1 + " = " + Profile2);
        Assert.assertTrue(Profile1.equals(Profile2));

        page.waitE_ClickableAndClick(page.linkDeleteOrganization);
        WebElement NameDeleteOrganization = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div/div[2]/div[1]/p[contains (text(), \"" + Name + "\")]")));
        System.out.println(NameDeleteOrganization.getText() + " = " + Name);
        Assert.assertEquals(NameDeleteOrganization.getText(), Name);
        page.waitE_ClickableAndClick(page.buttonCancelDelete);
        page.waitE_visibilityOf(page.profileOrganization);
    }


    public void editAdditionalInformationOrganization(String Name, String DescriptionOrganization,
                                                      String NewAppointmentPhone1, String NewNumberPhone1,
                                                      String NewAdress, String NewEmail, String NewVk,
                                                      String NewFacebook, String NewInstagram, String NewOther,
                                                      String NewCode, String NewRegistryNumber,
                                                      String NewIdentifierPUMP, String NewIdentifierOMS) {
        page.moveToProfileOrg(Name);
        page.waitE_ClickableAndClick(page.linkAdditionalInformation);

        String Description1 = page.descriptionOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeDescriptionOrganization);
        page.waitE_ClickableAndSendKeys(page.inputDescriptionOrganization, DescriptionOrganization);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.waitE_visibilityOf(page.descriptionOrganization);
        String Description2 = page.descriptionOrganization.getText();
        System.out.println(Description1 + " != " + Description2 + " = " + DescriptionOrganization);
        Assert.assertTrue(!Description1.equals(Description2) & Description2.equals(DescriptionOrganization));

        String AppointmentPhone1 = page.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone1 = page.numberPhoneOrganization.get(0).getText();
        page.waitE_ClickableAndClick(page.linkChangePhoneOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(0), NewAppointmentPhone1);
        page.waitE_ClickableAndSendKeys(page.inputNumberPhone.get(0), NewNumberPhone1);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.sleep(1000);
        page.waitE_visibilityOf(page.appointmentPhoneOrganization.get(0));
        String AppointmentPhone2 = page.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone2 = page.numberPhoneOrganization.get(0).getText();
        System.out.println(AppointmentPhone1 + " != " + AppointmentPhone2 + " = " + NewAppointmentPhone1);
        System.out.println(NumberPhone1 + " != " + NumberPhone2 + " = " + "+7 (" + NewNumberPhone1.charAt(0) +
                NewNumberPhone1.charAt(1) + NewNumberPhone1.charAt(2) + ") " + NewNumberPhone1.charAt(3) +
                NewNumberPhone1.charAt(4) + NewNumberPhone1.charAt(5) + "-" + NewNumberPhone1.charAt(6) + NewNumberPhone1.charAt(7) + "-" +
                NewNumberPhone1.charAt(8) + NewNumberPhone1.charAt(9));
        Assert.assertTrue(!AppointmentPhone1.equals(AppointmentPhone2) & AppointmentPhone2.equals(NewAppointmentPhone1));
        Assert.assertTrue(!NumberPhone1.equals(NumberPhone2) & NumberPhone2.equals("+7 (" + NewNumberPhone1.charAt(0) +
                NewNumberPhone1.charAt(1) + NewNumberPhone1.charAt(2) + ") " + NewNumberPhone1.charAt(3) +
                NewNumberPhone1.charAt(4) + NewNumberPhone1.charAt(5) + "-" + NewNumberPhone1.charAt(6) + NewNumberPhone1.charAt(7) + "-" +
                NewNumberPhone1.charAt(8) + NewNumberPhone1.charAt(9)));

        String Adress1 = page.adressOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeAdressOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAdressOrganization, NewAdress);
        page.waitE_ClickableAndClick(page.buttonSaveAdressOrganization);
        page.waitE_visibilityOf(page.adressOrganization);
        String Adress2 = page.adressOrganization.getText();
        System.out.println(Adress1 + " != " + Adress2 + " = " + NewAdress);
        Assert.assertTrue(!Adress1.equals(Adress2) & Adress2.equals(NewAdress));

        String Email1 = page.emailOrganization.getText();
        String Vk1 = page.vkOrganization.getText();
        String Facebook1 = page.facebookOrganization.getText();
        String Instagram1 = page.instagramOrganization.getText();
        String Other1 = page.otherOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeContactsOrganization);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputVk, NewVk);
        page.waitE_ClickableAndSendKeys(page.inputFacebook, NewFacebook);
        page.waitE_ClickableAndSendKeys(page.inputInstagram, NewInstagram);
        page.waitE_ClickableAndSendKeys(page.inputOthers, NewOther);
        page.waitE_ClickableAndClick(page.buttonSaveContactsOrganization);
        page.waitE_visibilityOf(page.emailOrganization);
        String Email2 = page.emailOrganization.getText();
        String Vk2 = page.vkOrganization.getText();
        String Facebook2 = page.facebookOrganization.getText();
        String Instagram2 = page.instagramOrganization.getText();
        String Other2 = page.otherOrganization.getText();
        System.out.println(Email1 + " != " + Email2 + " = " + NewEmail);
        System.out.println(Vk1 + " != " + Vk2 + " = " + NewVk);
        System.out.println(Facebook1 + " != " + Facebook2 + " = " + NewFacebook);
        System.out.println(Instagram1 + " != " + Instagram2 + " = " + NewInstagram);
        System.out.println(Other1 + " != " + Other2 + " = " + NewOther);
        Assert.assertTrue(!Email1.equals(Email2) & Email2.equals(NewEmail));
        Assert.assertTrue(!Vk1.equals(Vk2) & Vk2.equals(NewVk));
        Assert.assertTrue(!Facebook1.equals(Facebook2) & Facebook2.equals(NewFacebook));
        Assert.assertTrue(!Instagram1.equals(Instagram2) & Instagram2.equals(NewInstagram));
        Assert.assertTrue(!Other1.equals(Other2) & Other2.equals(NewOther));

        String Code1 = page.codeOrganization.getText();
        String RegistrationOMS1 = page.registrationOMSOrganization.getText();
        String IdentifikatorPUMP1 = page.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS1 = page.identifikatorOMSrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeSystemInformationOrganization);
        page.waitE_ClickableAndSendKeys(page.inputCode, NewCode);
        page.waitE_ClickableAndSendKeys(page.inputRegistryNumber, NewRegistryNumber);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierPUMP, NewIdentifierPUMP);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierOMS, NewIdentifierOMS);
        page.waitE_ClickableAndClick(page.buttonSaveSystemInformationOrganization);
        page.waitE_visibilityOf(page.emailOrganization);
        String Code2 = page.codeOrganization.getText();
        String RegistrationOMS2 = page.registrationOMSOrganization.getText();
        String IdentifikatorPUMP2 = page.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS2 = page.identifikatorOMSrganization.getText();
        System.out.println(Code1 + " != " + Code2 + " = " + NewCode);
        System.out.println(RegistrationOMS1 + " != " + RegistrationOMS2 + " = " + NewRegistryNumber);
        System.out.println(IdentifikatorPUMP1 + " != " + IdentifikatorPUMP2 + " = " + NewIdentifierPUMP);
        System.out.println(IdentifikatorOMS1 + " != " + IdentifikatorOMS2 + " = " + NewIdentifierOMS);
        Assert.assertTrue(!Code1.equals(Code2) & Code2.equals(NewCode));
        Assert.assertTrue(!RegistrationOMS1.equals(RegistrationOMS2) & RegistrationOMS2.equals(NewRegistryNumber));
        Assert.assertTrue(!IdentifikatorPUMP1.equals(IdentifikatorPUMP2) & IdentifikatorPUMP2.equals(NewIdentifierPUMP));
        Assert.assertTrue(!IdentifikatorOMS1.equals(IdentifikatorOMS2) & IdentifikatorOMS2.equals(NewIdentifierOMS));
    }

    public void editAdditionalInformationOrganizationCancel(String Name, String DescriptionOrganization,
                                                      String NewAppointmentPhone1, String NewNumberPhone1,
                                                      String NewAdress, String NewEmail, String NewVk,
                                                      String NewFacebook, String NewInstagram, String NewOther,
                                                      String NewCode, String NewRegistryNumber,
                                                      String NewIdentifierPUMP, String NewIdentifierOMS) {
        page.moveToProfileOrg(Name);
        page.waitE_ClickableAndClick(page.linkAdditionalInformation);

        String Description1 = page.descriptionOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeDescriptionOrganization);
        page.waitE_ClickableAndSendKeys(page.inputDescriptionOrganization, DescriptionOrganization);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.waitE_visibilityOf(page.descriptionOrganization);
        String Description2 = page.descriptionOrganization.getText();
        System.out.println(Description1 + " = " + Description2);
        Assert.assertEquals(Description1, Description2);

        String AppointmentPhone1 = page.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone1 = page.numberPhoneOrganization.get(0).getText();
        page.waitE_ClickableAndClick(page.linkChangePhoneOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(0), NewAppointmentPhone1);
        page.waitE_ClickableAndSendKeys(page.inputNumberPhone.get(0), NewNumberPhone1);
        page.waitE_ClickableAndClick(page.buttonCancel);
        page.sleep(1000);
        page.waitE_visibilityOf(page.appointmentPhoneOrganization.get(0));
        String AppointmentPhone2 = page.appointmentPhoneOrganization.get(0).getText();
        String NumberPhone2 = page.numberPhoneOrganization.get(0).getText();
        System.out.println(AppointmentPhone1 + " = " + AppointmentPhone2);
        System.out.println(NumberPhone1 + " != " + NumberPhone2);
        Assert.assertEquals(AppointmentPhone1, AppointmentPhone2);
        Assert.assertEquals(NumberPhone1, NumberPhone2);

        String Adress1 = page.adressOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeAdressOrganization);
        page.waitE_ClickableAndSendKeys(page.inputAdressOrganization, NewAdress);
        page.waitE_ClickableAndClick(page.buttonDoNotSaveAdressOrganization);
        page.waitE_visibilityOf(page.adressOrganization);
        String Adress2 = page.adressOrganization.getText();
        System.out.println(Adress1 + " = " + Adress2);
        Assert.assertEquals(Adress1, Adress2);

        String Email1 = page.emailOrganization.getText();
        String Vk1 = page.vkOrganization.getText();
        String Facebook1 = page.facebookOrganization.getText();
        String Instagram1 = page.instagramOrganization.getText();
        String Other1 = page.otherOrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeContactsOrganization);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputVk, NewVk);
        page.waitE_ClickableAndSendKeys(page.inputFacebook, NewFacebook);
        page.waitE_ClickableAndSendKeys(page.inputInstagram, NewInstagram);
        page.waitE_ClickableAndSendKeys(page.inputOthers, NewOther);
        page.waitE_ClickableAndClick(page.buttonDoNotSaveContactsOrganization);
        page.waitE_visibilityOf(page.emailOrganization);
        String Email2 = page.emailOrganization.getText();
        String Vk2 = page.vkOrganization.getText();
        String Facebook2 = page.facebookOrganization.getText();
        String Instagram2 = page.instagramOrganization.getText();
        String Other2 = page.otherOrganization.getText();
        System.out.println(Email1 + " = " + Email2);
        System.out.println(Vk1 + " = " + Vk2);
        System.out.println(Facebook1 + " = " + Facebook2);
        System.out.println(Instagram1 + " = " + Instagram2);
        System.out.println(Other1 + " = " + Other2);
        Assert.assertEquals(Email1, Email2);
        Assert.assertEquals(Vk1, Vk2);
        Assert.assertEquals(Facebook1, Facebook2);
        Assert.assertEquals(Instagram1, Instagram2);
        Assert.assertEquals(Other1, Other2);

        String Code1 = page.codeOrganization.getText();
        String RegistrationOMS1 = page.registrationOMSOrganization.getText();
        String IdentifikatorPUMP1 = page.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS1 = page.identifikatorOMSrganization.getText();
        page.waitE_ClickableAndClick(page.linkChangeSystemInformationOrganization);
        page.waitE_ClickableAndSendKeys(page.inputCode, NewCode);
        page.waitE_ClickableAndSendKeys(page.inputRegistryNumber, NewRegistryNumber);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierPUMP, NewIdentifierPUMP);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierOMS, NewIdentifierOMS);
        page.waitE_ClickableAndClick(page.buttonDoNotSaveSystemInformationOrganization);
        page.waitE_visibilityOf(page.codeOrganization);
        String Code2 = page.codeOrganization.getText();
        String RegistrationOMS2 = page.registrationOMSOrganization.getText();
        String IdentifikatorPUMP2 = page.identifikatorPUMPOrganization.getText();
        String IdentifikatorOMS2 = page.identifikatorOMSrganization.getText();
        System.out.println(Code1 + " = " + Code2);
        System.out.println(RegistrationOMS1 + " = " + RegistrationOMS2);
        System.out.println(IdentifikatorPUMP1 + " = " + IdentifikatorPUMP2);
        System.out.println(IdentifikatorOMS1 + " = " + IdentifikatorOMS2);
        Assert.assertEquals(Code1, Code2);
        Assert.assertEquals(RegistrationOMS1, RegistrationOMS2);
        Assert.assertEquals(IdentifikatorPUMP1, IdentifikatorPUMP2);
        Assert.assertEquals(IdentifikatorOMS1, IdentifikatorOMS2);
    }

    public void editPhonesOrganization(String Name, String NamePhone1, String NumberPhone1,
                                       String NewAppointmentPhone2, String NewNumberPhone2) {
        page.moveToProfileOrg(Name);
        page.waitE_ClickableAndClick(page.linkAdditionalInformation);
        page.waitE_ClickableAndClick(page.linkChangePhoneOrganization);
        page.waitE_ClickableAndClick(page.linkAddPhone);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(1), NewAppointmentPhone2);
        page.waitE_ClickableAndSendKeys(page.inputNumberPhone.get(1), NewNumberPhone2);
        page.waitE_ClickableAndClick(page.buttonSave);
        page.sleep(1000);
        page.waitE_visibilityOf(page.appointmentPhoneOrganization.get(0));
        String AppointmentPhoneSite1 = page.appointmentPhoneOrganization.get(0).getText();
        String NumberPhoneSite1 = page.numberPhoneOrganization.get(0).getText();
        String AppointmentPhoneSite2 = page.appointmentPhoneOrganization.get(1).getText();
        String NumberPhoneSite2 = page.numberPhoneOrganization.get(1).getText();

        System.out.println(AppointmentPhoneSite1 + " = " + NamePhone1);
        System.out.println(NumberPhoneSite1 + " = " + NumberPhone1);
        Assert.assertEquals(AppointmentPhoneSite1, NamePhone1);
        Assert.assertEquals(NumberPhoneSite1, NumberPhone1);

        System.out.println(AppointmentPhoneSite2 + " = " + NewAppointmentPhone2);
        System.out.println(NumberPhoneSite2 + " = " + NewNumberPhone2);
        Assert.assertEquals(AppointmentPhoneSite2, NewAppointmentPhone2);
        Assert.assertEquals(NumberPhoneSite2, "+7 (" + NewNumberPhone2.charAt(0) +
                NewNumberPhone2.charAt(1) + NewNumberPhone2.charAt(2) + ") " + NewNumberPhone2.charAt(3) +
                NewNumberPhone2.charAt(4) + NewNumberPhone2.charAt(5) + "-" + NewNumberPhone2.charAt(6) + NewNumberPhone2.charAt(7) + "-" +
                NewNumberPhone2.charAt(8) + NewNumberPhone2.charAt(9));

        page.waitE_ClickableAndClick(page.linkChangePhoneOrganization);
        page.waitE_ClickableAndClick(page.linkDeletePhone.get(0));
        page.waitE_ClickableAndClick(page.linkDeletePhone.get(0));
        page.waitE_ClickableAndClick(page.buttonSave);
        page.sleep(1000);
        try {
            Assert.assertTrue(page.appointmentPhoneOrganization.get(0).isEnabled() |
                    page.numberPhoneOrganization.get(0).isEnabled() | page.appointmentPhoneOrganization.get(1).isEnabled() |
                    page.numberPhoneOrganization.get(1).isEnabled());

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Все хорошо - телефоны не найдены");
        }
    }
}