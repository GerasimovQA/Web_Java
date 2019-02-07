package Integration;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class IntegrationLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private IntegrationPage p;

    private int i = -1;
    private int u = -1;
    private ArrayList<String> ListSpecialtysInProfile = new ArrayList<>();
    private ArrayList<String> ListNamesServices = new ArrayList<>();
    private ArrayList<String> ListVendorsServices = new ArrayList<>();
    private ArrayList<String> ListCostsServices = new ArrayList<>();
    private ArrayList<String> ListEmployersServices = new ArrayList<>();
    private ArrayList<String> ListWorkers = new ArrayList<>();

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("\n\n\n");
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new IntegrationPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsIntegration);
        }
    };

    public void compareServiceUserOne(String UserID, String SecondName, String NameService, String VendorService, String DmsCost,
                                      String OmsCost, String PmuCost, String OtherCosts) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        p.sleep(4000);
        driver.navigate().refresh();
        p.waitTextPresent(p.buttonChangeProfile, "Редактировать профиль");
        p.preloader();
        p.waitVisibility(p.listNamesServicesProfUser.get(0));
        p.compareStringAndWebelement(NameService, p.listNamesServicesProfUser.get(0));
        p.compareStringAndWebelement(VendorService, p.listVendorsServicesProfUser.get(0));
        String Cost = "ОМС " + OmsCost + " ДМС " + DmsCost + " ПМУ " + PmuCost + OtherCosts;
        p.compareStringAndString(Cost, p.listCostsServicesProfUser.get(0).getText());
    }

    public void compareServiceUserTwo(String NewNameService, String NewVendorService, String NewDmsCost,
                                      String NewOmsCost, String NewPmuCost, String NewOtherCosts) {
        driver.navigate().refresh();
        p.preloader();
        p.waitTextPresent(p.listNamesServicesProfUser.get(0), "Новое Название услуги");
        p.compareStringAndWebelement(NewNameService, p.listNamesServicesProfUser.get(0));
        p.compareStringAndWebelement(NewVendorService, p.listVendorsServicesProfUser.get(0));
        String NewCost = "ОМС " + NewOmsCost + " ДМС " + NewDmsCost + " ПМУ " + NewPmuCost + NewOtherCosts;
        p.compareStringAndString(NewCost, p.listCostsServicesProfUser.get(0).getText());
    }

    public void compareServiceUserThree() {
        driver.navigate().refresh();
        p.preloader();
        try {
            Assert.assertTrue(p.listStringServicesProfUser.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Services not found and it is cool");
        }
    }

    public void compareSpecialtyUserOne(String UserID, String SecondName, String FirstName, String MiddleName) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        p.preloader();
        p.waitTextPresent(p.buttonChangeProfile, "Редактировать профиль");
        p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);
        p.sleep(1000);
        ListSpecialtysInProfile.add("Лор");
        ListSpecialtysInProfile.add("Охранник");
        ListSpecialtysInProfile.add("Название специальности");
        ListSpecialtysInProfile.add("Название специальности 2");
        p.compareWebelementsListAndArrayList(p.listProfSpeciality, ListSpecialtysInProfile, 4);

    }

    public void compareSpecialtyUserTwo(String SecondName, String FirstName, String MiddleName) {
        driver.navigate().refresh();
        p.preloader();
        p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);
        p.sleep(1000);
        ListSpecialtysInProfile.clear();
        ListSpecialtysInProfile.add("Лор");
        ListSpecialtysInProfile.add("Охранник");
        ListSpecialtysInProfile.add("Новое Назв. спец.");
        ListSpecialtysInProfile.add("Название специальности 2");
        p.compareWebelementsListAndArrayList(p.listProfSpeciality, ListSpecialtysInProfile, 4);
    }

    public void compareSpecialtyUserThree(String SecondName, String FirstName, String MiddleName) {
        driver.navigate().refresh();
        p.preloader();
        p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);
        p.sleep(1000);
        ListSpecialtysInProfile.clear();
        ListSpecialtysInProfile.add("Лор");
        ListSpecialtysInProfile.add("Охранник");
        ListSpecialtysInProfile.add("Название специальности 2");
        p.compareWebelementsListAndArrayList(p.listProfSpeciality, ListSpecialtysInProfile, 3);
    }

    public void compareOrganizationUserOne(String UserID, String SecondName, String NameOrg) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "#/users/" + UserID);
        p.preloader();
        p.waitTextPresent(p.buttonChangeProfile, "Редактировать профиль");

        p.compareWebelementAndString(p.listProfNameWorkspace.get(0), "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\n >\n" + NameOrg);
        System.out.println("");
    }

    public void compareOrganizationUserTwo(String NewNameOrg) {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        p.compareWebelementAndString(p.listProfNameWorkspace.get(0), "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                "Минздрава России (Сеченовский Университет)\n >\n" + NewNameOrg);
    }

    public void compareOrganizationUserThree(String NewNameOrg) {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        p.compareWebelementAndString(p.listProfNameWorkspace.get(0), "Lionsdigital.pro\n >\n" + NewNameOrg);
    }

    public void compareOrganizationUserFour() {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        p.waitTextPresent(p.absentProfPosition, "Должность не указана");
        p.waitTextPresent(p.absentProfNameWorkspace, "Не указано");
    }

    public void compareServiceOrgOne(String OrganizationID, String NameOrg, String NameService1, String VendorService1, String DmsCost1,
                                     String OmsCost1, String PmuCost1, String NameService2, String VendorService2,
                                     String DmsCost2, String OmsCost2, String PmuCost2, String NameService3,
                                     String VendorService3, String DmsCost3, String OmsCost3, String PmuCost3) {
        p.moveToProfileOrgID(OrganizationID);
        ListNamesServices.add(NameService1);
        ListNamesServices.add(NameService2);
        ListNamesServices.add(NameService3);
        p.compareWebelementsListAndArrayList(p.listNamesServicesProfOrg, ListNamesServices, 3);

        ListVendorsServices.add(VendorService1);
        ListVendorsServices.add(VendorService2);
        ListVendorsServices.add(VendorService3);
        p.compareWebelementsListAndArrayList(p.listVendorsServicesProfOrg, ListVendorsServices, 3);

        ListCostsServices.add("ОМС " + OmsCost1 + " ДМС " + DmsCost1 + " ПМУ " + PmuCost1);
        ListCostsServices.add("ОМС " + OmsCost2 + " ДМС " + DmsCost2 + " ПМУ " + PmuCost2);
        ListCostsServices.add("ОМС " + OmsCost3 + " ДМС " + DmsCost3 + " ПМУ " + PmuCost3);
        p.compareWebelementsListAndArrayList(p.listCostsServicesProfOrg, ListCostsServices, 3);

    }

    public void compareServiceOrgTwo(String NameOrg, String NameService1, String VendorService1, String DmsCost1,
                                     String OmsCost1, String PmuCost1, String NameService2, String VendorService2,
                                     String DmsCost2, String OmsCost2, String PmuCost2, String NameService3,
                                     String VendorService3, String DmsCost3, String OmsCost3, String PmuCost3,
                                     String SecondName1, String FirstName1, String MiddleName1, String Post1,
                                     String SecondName2, String FirstName2, String MiddleName2, String Post2,
                                     String SecondName3, String FirstName3, String MiddleName3, String Post3) {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        ListNamesServices.clear();
        ListNamesServices.add(NameService1);
        ListNamesServices.add(NameService2);
        ListNamesServices.add(NameService3);
        p.compareWebelementsListAndArrayList(p.listNamesServicesProfOrg, ListNamesServices, 3);

        ListVendorsServices.clear();
        ListVendorsServices.add(VendorService1);
        ListVendorsServices.add(VendorService2);
        ListVendorsServices.add(VendorService3);
        p.compareWebelementsListAndArrayList(p.listVendorsServicesProfOrg, ListVendorsServices, 3);

        ListCostsServices.clear();
        ListCostsServices.add("ОМС " + OmsCost1 + " ДМС " + DmsCost1 + " ПМУ " + PmuCost1);
        ListCostsServices.add("ОМС " + OmsCost2 + " ДМС " + DmsCost2 + " ПМУ " + PmuCost2);
        ListCostsServices.add("ОМС " + OmsCost3 + " ДМС " + DmsCost3 + " ПМУ " + PmuCost3);
        p.compareWebelementsListAndArrayList(p.listCostsServicesProfOrg, ListCostsServices, 3);

        ListEmployersServices.add(SecondName1 + " " + FirstName1.charAt(0) + ". " + MiddleName1.charAt(0) + ". \n" +
                SecondName2 + " " + FirstName2.charAt(0) + ". " + MiddleName2.charAt(0) + ". \n" + "...");
        ListEmployersServices.add(SecondName2 + " " + FirstName2.charAt(0) + ". " + MiddleName2.charAt(0) + ". \n" +
                SecondName3 + " " + FirstName3.charAt(0) + ". " + MiddleName3.charAt(0) + ".");
        ListEmployersServices.add(SecondName3 + " " + FirstName3.charAt(0) + ". " + MiddleName3.charAt(0) + ".");

        p.compareWebelementsListAndArrayList(p.listExecutorsServicesProfOrg, ListEmployersServices, 3);

        for (WebElement trig : p.listTriggerServicesProfOrg) {
            p.click(trig);
            i = i + 1;
            String it = Integer.toString(i);
            switch (it) {
                case "0":
                    p.compareStringAndWebelement("1", p.listNumberExecutorProfOrg.get(0));
                    System.out.println("Фото = " + p.listAvatarExecutorProfOrg.get(0).getAttribute("alt"));
                    Assert.assertEquals("Фото", p.listAvatarExecutorProfOrg.get(0).getAttribute("alt"));
//                    p.compareStringAndWebelement("Фото", p.listAvatarExecutorProfOrg.get(0).getAttribute("alt"));
                    p.compareStringAndWebelement(SecondName1, p.listSecondNameExecutorProfOrg.get(0));
                    p.compareStringAndWebelement(FirstName1, p.listFirstNameExecutorProfOrg.get(0));
                    p.compareStringAndWebelement(MiddleName1, p.listMiddleNameExecutorProfOrg.get(0));
                    p.compareStringAndWebelement(Post1, p.listPostExecutorProfOrg.get(0));
                    break;

                case "1":
                    p.compareStringAndWebelement("2", p.listNumberExecutorProfOrg.get(1));
                    System.out.println("Фото = " + p.listAvatarExecutorProfOrg.get(1).getAttribute("alt"));
                    Assert.assertEquals("Фото", p.listAvatarExecutorProfOrg.get(1).getAttribute("alt"));
                    p.compareStringAndWebelement(SecondName2, p.listSecondNameExecutorProfOrg.get(1));
                    p.compareStringAndWebelement(FirstName2, p.listFirstNameExecutorProfOrg.get(1));
                    p.compareStringAndWebelement(MiddleName2, p.listMiddleNameExecutorProfOrg.get(1));
                    p.compareStringAndWebelement(Post2, p.listPostExecutorProfOrg.get(1));
                    break;

                case "2":
                    p.compareStringAndWebelement("3", p.listNumberExecutorProfOrg.get(2));
                    System.out.println("Фото = " + p.listAvatarExecutorProfOrg.get(2).getAttribute("alt"));
                    Assert.assertEquals("Фото", p.listAvatarExecutorProfOrg.get(2).getAttribute("alt"));
                    p.compareStringAndWebelement(SecondName3, p.listSecondNameExecutorProfOrg.get(2));
                    p.compareStringAndWebelement(FirstName3, p.listFirstNameExecutorProfOrg.get(2));
                    p.compareStringAndWebelement(MiddleName3, p.listMiddleNameExecutorProfOrg.get(2));
                    p.compareStringAndWebelement(Post3, p.listPostExecutorProfOrg.get(2));
                    break;
            }
        }

        ListWorkers.add("2\n" + SecondName1 + "\n" + FirstName1 + "\n" + MiddleName1 + "\n" + Post1);
        ListWorkers.add("1\n" + SecondName2 + "\n" + FirstName2 + "\n" + MiddleName2 + "\n" + Post2);
        ListWorkers.add("3\n" + SecondName3 + "\n" + FirstName3 + "\n" + MiddleName3 + "\n" + Post3);

        p.compareWebelementsListAndArrayList(p.listStringWorker, ListWorkers, 3);
    }

    public void compareServiceOrgThree(String NewNameService1, String NewVendorService1, String NewDmsCost1,
                                       String NewOmsCost1, String NewPmuCost1, String NameService2, String VendorService2,
                                       String DmsCost2, String OmsCost2, String PmuCost2, String NameService3,
                                       String VendorService3, String DmsCost3, String OmsCost3, String PmuCost3,
                                       String SecondName1, String FirstName1, String MiddleName1, String Post1,
                                       String SecondName2, String FirstName2, String MiddleName2, String Post2,
                                       String SecondName3, String FirstName3, String MiddleName3, String Post3) {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        ListNamesServices.clear();
        ListNamesServices.add(NewNameService1);
        ListNamesServices.add(NameService2);
        ListNamesServices.add(NameService3);
        p.compareWebelementsListAndArrayList(p.listNamesServicesProfOrg, ListNamesServices, 3);

        ListVendorsServices.clear();
        ListVendorsServices.add(NewVendorService1);
        ListVendorsServices.add(VendorService2);
        ListVendorsServices.add(VendorService3);
        p.compareWebelementsListAndArrayList(p.listVendorsServicesProfOrg, ListVendorsServices, 3);

        ListCostsServices.clear();
        ListCostsServices.add("ОМС " + NewOmsCost1 + " ДМС " + NewDmsCost1 + " ПМУ " + NewPmuCost1);
        ListCostsServices.add("ОМС " + OmsCost2 + " ДМС " + DmsCost2 + " ПМУ " + PmuCost2);
        ListCostsServices.add("ОМС " + OmsCost3 + " ДМС " + DmsCost3 + " ПМУ " + PmuCost3);
        p.compareWebelementsListAndArrayList(p.listCostsServicesProfOrg, ListCostsServices, 3);

        ListWorkers.clear();
        ListWorkers.add("3\n" + SecondName1 + "\n" + FirstName1 + "\n" + MiddleName1 + "\n" + Post1);
        ListWorkers.add("1\n" + SecondName2 + "\n" + FirstName2 + "\n" + MiddleName2 + "\n" + Post2);
        ListWorkers.add("2\n" + SecondName3 + "\n" + FirstName3 + "\n" + MiddleName3 + "\n" + Post3);

        p.compareWebelementsListAndArrayList(p.listStringWorker, ListWorkers, 3);
    }

    public void compareServiceOrgFour(String NameService2, String VendorService2, String DmsCost2, String OmsCost2,
                                      String PmuCost2, String NameService3, String VendorService3, String DmsCost3,
                                      String OmsCost3, String PmuCost3) {
        driver.navigate().refresh();
        p.sleep(2000);
        p.preloader();
        ListNamesServices.clear();
//        ListNamesServices.add(NewNameService1);
        ListNamesServices.add(NameService2);
        ListNamesServices.add(NameService3);
        p.compareWebelementsListAndArrayList(p.listNamesServicesProfOrg, ListNamesServices, 2);

        ListVendorsServices.clear();
//        ListVendorsServices.add(NewVendorService1);
        ListVendorsServices.add(VendorService2);
        ListVendorsServices.add(VendorService3);
        p.compareWebelementsListAndArrayList(p.listVendorsServicesProfOrg, ListVendorsServices, 2);

        ListCostsServices.clear();
//        ListCostsServices.add("ОМС " + NewOmsCost1 + " ДМС " + NewDmsCost1 + " ПМУ " + NewPmuCost1);
        ListCostsServices.add("ОМС " + OmsCost2 + " ДМС " + DmsCost2 + " ПМУ " + PmuCost2);
        ListCostsServices.add("ОМС " + OmsCost3 + " ДМС " + DmsCost3 + " ПМУ " + PmuCost3);
        p.compareWebelementsListAndArrayList(p.listCostsServicesProfOrg, ListCostsServices, 2);
    }

    public void changeUserSecondFistMiddleName(String Login, String Email, String Phone, String SecondName, String FirstName,
                                               String MiddleName, String NewSecondName, String NewFirstName, String NewMiddleName,
                                               String NewEmail, String NewPhone, String NewLogin) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(2000);
        p.preloader();
        int u = 0;
        p.preloader();
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.buttonSearchWorker);
        switch (Login) {
            case "1rieurwoei":
                p.clickAndSendKeys(p.inputSearchSecondName, NewSecondName);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров пуст", p.listSecondNameInUserList.size() >= 1);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewSecondName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новой фамилии", u == 1);
                }

                p.click(p.body);
                p.scrollHomePage();
                p.click(p.buttonSearchWorker);
                p.clickAndSendKeys(p.inputSearchSecondName, SecondName);
                p.click(p.buttonSearch);
                p.sleep(2000);
                System.out.println("Размер массива: " + p.listSecondNameInUserList.size());
                if (p.listSecondNameInUserList.size() > 0) {
                    System.out.println("Список юзеров: ");
                    p.soutListWebelements(p.listSecondNameInUserList);
                    throw new AssertionError("Сотрудник НАЙДЕН по старой фамилии");
                } else {
                    System.out.println("Сотрудник не найден по старой фамилии");
                }
                break;

            case "2uytwoei":
                p.clickAndSendKeys(p.inputSearchFirstName, NewFirstName);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров пуст", p.listFirstNameInUserList.size() >= 1);
                for (WebElement Worker : p.listFirstNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewFirstName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новому имени", u == 1);
                }

                p.click(p.body);
                p.scrollHomePage();
                p.click(p.buttonSearchWorker);
                p.clickAndSendKeys(p.inputSearchFirstName, FirstName);
                p.click(p.buttonSearch);
                p.sleep(2000);
                if (p.listFirstNameInUserList.size() > 0) {
                    System.out.println("Список юзеров: ");
                    p.soutListWebelements(p.listFirstNameInUserList);
                    throw new AssertionError("Сотрудник НАЙДЕН по старому имени");
                } else {
                    System.out.println("Сотрудник не найден по старому имени");
                }
                break;

            case "3hgg47uytwoei":
                p.clickAndSendKeys(p.inputSearchMiddleName, NewMiddleName);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров пуст", p.listMiddleNameInUserList.size() >= 1);
                for (WebElement Worker : p.listMiddleNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewMiddleName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новому отчеству", u == 1);
                }

                p.click(p.body);
                p.scrollHomePage();
                p.click(p.buttonSearchWorker);
                p.clickAndSendKeys(p.inputSearchMiddleName, MiddleName);
                p.click(p.buttonSearch);
                p.sleep(2000);
                if (p.listMiddleNameInUserList.size() > 0) {
                    System.out.println("Список юзеров: ");
                    p.soutListWebelements(p.listMiddleNameInUserList);
                    throw new AssertionError("Сотрудник НАЙДЕН по старому отчеству");
                } else {
                    System.out.println("Сотрудник не найден по старому отчеству");
                }
                break;

            case "4hgрорoei":
                p.clickAndSendKeys(p.inputSearchEmail, NewEmail);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров !=1", p.listSecondNameInUserList.size() == 1);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewSecondName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новому Email", u == 1);
                }

                p.click(p.body);
                p.scrollHomePage();
                p.click(p.buttonSearchWorker);
                p.clickAndSendKeys(p.inputSearchEmail, Email);
                p.click(p.buttonSearch);
                p.sleep(2000);
                if (p.listSecondNameInUserList.size() > 0) {
                    System.out.println("Список юзеров: ");
                    p.soutListWebelements(p.listSecondNameInUserList);
                    throw new AssertionError("Сотрудник НАЙДЕН по старому Email");
                } else {
                    System.out.println("Сотрудник не найден по старому Email");
                }
                break;

            case "5hgр8jhei":
                p.clickAndSendKeys(p.inputSearchPhone, NewPhone);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров !=1", p.listSecondNameInUserList.size() == 1);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewSecondName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новому отчеству", u == 1);
                }

                p.click(p.body);
                p.scrollHomePage();
                p.click(p.buttonSearchWorker);
                p.clickAndSendKeys(p.inputSearchPhone, Phone);
                p.click(p.buttonSearch);
                p.sleep(2000);
                if (p.listSecondNameInUserList.size() > 0) {
                    System.out.println("Список юзеров: ");
                    p.soutListWebelements(p.listSecondNameInUserList);
                    throw new AssertionError("Сотрудник НАЙДЕН по старому отчеству");
                } else {
                    System.out.println("Сотрудник не найден по старому отчеству");
                }
                break;

            case "6ddsjhei":
                p.clickAndSendKeys(p.inputSearchLogin, NewLogin);
                p.click(p.buttonSearch);
                Assert.assertTrue("Список юзеров !=1", p.listSecondNameInUserList.size() == 1);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(NewSecondName)) {
                        u = u + 1;
                    }
                    Assert.assertTrue("Сотрудник не найден по новому логину", u == 1);

                    p.click(p.body);
                    p.scrollHomePage();
                    p.click(p.buttonSearchWorker);
                    p.clickAndSendKeys(p.inputSearchLogin, Login);
                    p.click(p.buttonSearch);
                    p.sleep(2000);
                    if (p.listSecondNameInUserList.size() > 0) {
                        System.out.println("Список юзеров: ");
                        p.soutListWebelements(p.listSecondNameInUserList);
                        throw new AssertionError("Сотрудник НАЙДЕН по старому логину");
                    } else {
                        System.out.println("Сотрудник не найден по старому логину");
                    }
                }
        }
    }

    public void changeUserOrgPostRole(String Login, String NewDepart, String NewPost, String NewRole,
                                      String SecondName, String FirstName, String MiddleName) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(2000);
        p.preloader();
        int u = 0;
        p.preloader();
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);
        switch (Login) {
            case "7rie8678ei":
                p.click(p.filterOrganization);
                p.sleep(500);
                p.click(p.FGAO);
                p.click(p.userinfoname);

                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    System.out.println(Worker.getText());
                    if (Worker.getText().equals(SecondName)) {
                        u = u + 1;
                        System.out.println("Сотрудник найден");
                        break;
                    }
                }
                if (u != 1) {
                    throw new AssertionError("Сотрудник НЕ найден по новой организации");
                }
                driver.navigate().refresh();
                p.sleep(2000);
                p.preloader();
                p.click(p.filterOrganization);
                p.sleep(500);
                p.click(p.lionsdigital);
                p.click(p.sumWorker);
                p.click(p.workers100);
                p.sleep(1000);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(SecondName)) {
                        throw new AssertionError("Сотрудник Найден по старой организации");
                    }
                }
                break;

            case "8riedsgehei":
                p.click(p.filterPost);
                p.sleep(500);
                p.click(p.postChiefAnalizFilter);
                p.click(p.userinfoname);

                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    System.out.println(Worker.getText());
                    if (Worker.getText().equals(SecondName)) {
                        u = u + 1;
                        System.out.println("Сотрудник найден");
                        break;
                    }
                }
                if (u != 1) {
                    throw new AssertionError("Сотрудник НЕ найден по новой должности");
                }
                driver.navigate().refresh();
                p.sleep(2000);
                p.preloader();
                p.click(p.filterPost);
                p.sleep(500);
                p.click(p.postDisignerFilter);
                p.click(p.sumWorker);
                p.click(p.workers100);
                p.sleep(1000);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(SecondName)) {
                        throw new AssertionError("Сотрудник Найден по старой должности");
                    }
                }
                break;

            case "9riefgtoei":
                p.click(p.filterRole);
                p.sleep(500);
                p.click(p.roleAdminInFilter);
                p.click(p.userinfoname);

                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    System.out.println(Worker.getText());
                    if (Worker.getText().equals(SecondName)) {
                        u = u + 1;
                        System.out.println("Сотрудник найден");
                        break;
                    }
                }
                if (u != 1) {
                    throw new AssertionError("Сотрудник НЕ найден по новой роли");
                }

                driver.navigate().refresh();
                p.sleep(2000);
                p.preloader();
                p.click(p.sumWorker);
                p.click(p.workers100);
                p.sleep(1000);
                p.click(p.filterRole);
                p.sleep(500);
                p.click(p.roleSpecInFilter);
                p.sleep(1000);
                for (WebElement Worker : p.listSecondNameInUserList) {
                    p.moveMouse(Worker);
                    if (Worker.getText().equals(SecondName)) {
                        throw new AssertionError("Сотрудник Найден по старой роли");
                    }
                }
        }
    }

    public void changeUserStatusWork(String Login, String SecondName, String FirstName, String MiddleName) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(1000);
        int u = 0;
        p.preloader();
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);

        p.click(p.filterStatusWork);
        p.sleep(500);
        p.click(p.StatusSickInFilter);
        p.click(p.userinfoname);

        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            System.out.println(Worker.getText());
            if (Worker.getText().equals(SecondName)) {
                u = u + 1;
                System.out.println("Сотрудник найден");
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError("Сотрудник НЕ найден по новому статусу работы");
        }
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();
        p.click(p.filterStatusWork);
        p.sleep(500);
        p.click(p.StatusWorkInFilter);
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals(SecondName)) {
                throw new AssertionError("Сотрудник Найден по старому статусу работы");
            }
        }
    }


    public void changeUserStatusInSystem(String Login, String Status, String NewStatus,
                                         String SecondName, String FirstName, String MiddleName) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(1000);
        int u = 0;
        p.preloader();
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);

        p.click(p.filterStatusInSystem);
        p.sleep(500);
        p.click(p.statusDisabledInSystemFilter);
        p.click(p.userinfoname);

        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            System.out.println(Worker.getText());
            if (Worker.getText().equals(SecondName)) {
                u = u + 1;
                System.out.println("Сотрудник найден");
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError("Сотрудник НЕ найден по новому статусу в системе");
        }
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();
        p.click(p.filterStatusInSystem);
        p.sleep(500);
        p.click(p.statusActiveInSystemFilter);
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals(SecondName)) {
                throw new AssertionError("Сотрудник Найден по старому статусу в системе");
            }
        }
    }

    public void changeUserStatusOnOff(String Login, String ActualStatus, String NotActualStatus,
                                      String SecondName, String FirstName, String MiddleName) {
        driver.navigate().refresh();
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(1000);
        int u = 0;
        p.preloader();
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);

        p.click(p.filterStatusOnline);
        p.sleep(500);
        p.click(driver.findElement(By.xpath("//span[text()=\"" + ActualStatus + "\"]")));
        p.click(p.userinfoname);

        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            System.out.println(Worker.getText());
            if (Worker.getText().equals(SecondName)) {
                u = u + 1;
                System.out.println("Сотрудник найден");
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError("Сотрудник НЕ найден по текущему статусу Onn/Off");
        }
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();
        p.click(p.filterStatusOnline);
        p.sleep(500);
        p.click(driver.findElement(By.xpath("//span[text()=\"" + NotActualStatus + "\"]")));
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals(SecondName)) {
                throw new AssertionError("Сотрудник Найден по старому статусу  On/Off");
            }
        }
    }

    public void changeUserGlobalRole(String Login, String SecondName, String FirstName, String MiddleName) {
        driver.navigate().refresh();
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        p.sleep(1000);
        int u = 0;
        p.preloader();
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);

        p.click(p.filterGlobalRole);
        p.sleep(500);
        p.click(p.GlobalRoleSuperAdminInFilter);
        p.click(p.userinfoname);

        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            System.out.println(Worker.getText());
            if (Worker.getText().equals(SecondName)) {
                u = u + 1;
                System.out.println("Сотрудник найден");
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError("Сотрудник НЕ найден по глобальной роли Суперадминистратор");
        }
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();

        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals(SecondName)) {
                throw new AssertionError("Сотрудник Найден по старому статусу  On/Off");
            }
        }
    }

    public void compareTreeOrgOne(String NameOrg, String AbbrOrg) {
        u = 0;
        driver.get(ConfigProperties.getTestProperty("baseurl") + "#/organization/departments");
        p.preloader();
        p.waitTextPresent(p.listNameOrg.get(0), "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России" +
                " (Сеченовский Университет)");

        int i = -1;
        for (WebElement OneNameOrg : p.listNameOrg) {
            i = i + 1;
            p.moveMouse(OneNameOrg);
            System.out.println(OneNameOrg.getText());
            if (OneNameOrg.getText().equals(NameOrg)) {
                u = u + 1;
                System.out.println("Созданная организайия найдена");
                Assert.assertEquals(p.listNameAbbrOrg.get(i).getText(), AbbrOrg);
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError(" Созданная организация НЕ найдена в списке");
        }
    }

    public void compareTreeOrgTwo(String NameOrg, String NewNameOrg) {
        u = 0;
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();

        for (WebElement OneNameOrg : p.listNameOrg) {
            p.moveMouse(OneNameOrg);
            System.out.println(OneNameOrg.getText());
            if (OneNameOrg.getText().equals(NameOrg)) {
                throw new AssertionError(" Найдена перемещенная организация");
            }
        }

        p.click(p.Kardio1);
        int i = -1;
        for (WebElement OneNameOrg : p.listNameOrg) {
            i = i + 1;
            p.moveMouse(OneNameOrg);
            System.out.println(OneNameOrg.getText());
            if (OneNameOrg.getText().equals(NewNameOrg)) {
                u = u + 1;
                System.out.println("Переименованная и перемещенная организайия найдена");
                break;
            }
        }
        if (u != 1) {
            throw new AssertionError("Переименованная и перемещенная организация НЕ найдена в списке");
        }
    }
}

