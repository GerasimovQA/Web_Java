package GlobalSearch;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;
import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class GlobalSearchCreateLogic {
    private static GlobalSearchAPI globalSearchAPI = new GlobalSearchAPI();

    private static String Token = null;
    public static String UserID = "";
    public static String OrgID = null;
    public static String ServiceID = null;
    public static String SpecialtyID = null;

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private GlobalSearchPage p;

    static String SecondName = "Кондоренкова";
    static String FirstName = "Анастасия";
    static String MiddleName = "Венеаминовна";

    static String NameOrg = "Главный корпус лекарей от психо-невро-гастро-что-то-там хворей при дворе короля Артура";
    static String AbbrOrg = "ПсНеГа Арт.№1";

    static String NameService = "Растопыривание пальцев при помощи средств малой автоматизации";
    static String VendorService = "арт.112-585";

    static String NameSpecialty = "Сниматель швов";

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
            p = new GlobalSearchPage(driver);
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
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsGlobalSearch);
        }
    };

    @BeforeClass
    public static void beforClass() {
        createService();
        createSpecialty();
        createOrg();
        createUser();
    }

    @AfterClass
    public static void afterClass() {
    }

    public static void createUser() {
        String Login = "1SLxfggrdGI";
        String Password = "1nber2334hj78wds";
        String Email = "kondor@sech.lionsdigital.pro";
        String Phone = "+ 7 (927) 111-67-81";
        String Status = "active";
        String Superuser = "true";
        String SendEmail = "false";
        String OrgID = "5b7d08aa068d7552d904d8be";
        String Depart = "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)";
        String OrgStatus = "active";
        String Post1 = "Психоневролог-Патологоанатом";
        String Post2 = "Дерматолог-венеролог";
        String Role1 = "doctor";
        String Role2 = "Spec";
        String Spec1 = "5bc0a53043aef1053d0ada91";
        String Spec2 = "5bc44ce943aef1053d0adabc";
        String Service1 = "5bd6d7d21c7030734863e054";
        String Service2 = "5be2fc49371a1c6b17fbe71f";
        String Regalia = "Какое уж есть";
        String EmailCont = "qua@ma.ru";
        String PhoneCont = "7954357543";
        String Instagram = "INSTA";
        String Vk = "VK";
        String Whatsapp = "WATSAPP";
        String Viber = "VIBER";
        String Facebook = "FACEBOOK";
        String Other = "OTHER";

        Token = globalSearchAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = globalSearchAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        globalSearchAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
    }


    public static void createOrg() {
        String Chief = "5b58130f0faa2638b94fc4d7";
        String Help_conditions = "Поликлиника";
        String Org_profile = "Левое ухо";
        String Description = "";
        String Address = "Москва";
        String GeodataX = "55.753215";
        String GeodataY = "37.622504";
        String Site = "";
        String Email = "";
        String Facebook = "";
        String Instagram = "";
        String Vk = "";
        String OtherName = "";
        String OtherValue = "";
        String NamePhone1 = "";
        String NumberPhone1 = "";
        String NamePhone2 = "";
        String NumberPhone2 = "";
        String Code = "";
        String Oms_number = "";
        String Pump = "";
        String Oms_id = "";
        String Status = "";
        String Parent = "5be2dc17371a1c6b17fbe6fa";
        String Service1 = "5c1e32d770a62a6ef48d76b6";

        Token = globalSearchAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID = globalSearchAPI.createOrganizationAPI(Token, Parent);
        globalSearchAPI.profileOrganizationAPI(Token, OrgID, NameOrg, AbbrOrg, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status, Service1);
    }

    public static void createService() {
        String CodeService = "01.005.14";
        String PrintNameService = "Растопыривание пальцев";
        String Parent = "5bd6d8011c7030734863e056";
        String ContraindicationsService = "Желание жить";
        String CreatorService = "Кто-то";
        String DescriptionService = "Здесь могло бы быть описание";
        String PreconditionService = "Не ешь, не молись, не люби";
        String TypeService = "Операция";
        String DmsCost = "111";
        String OmsCost = "222";
        String PmuCost = "333";
        String OtherCosts = "";
        String Favorit = "false";
        String Record = "false";

        Token = globalSearchAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        ServiceID = globalSearchAPI.createServiceAPI(Token, CodeService, NameService, PrintNameService, Parent,
                VendorService, ContraindicationsService, CreatorService, DescriptionService, PreconditionService,
                TypeService, DmsCost, OmsCost, PmuCost, OtherCosts, Favorit, Record);
    }

    public static void createSpecialty() {

        String Description1 = "Профессионально снимаю";
        String Icon1 = "";
        String Parent1 = "000000000000000000000001";

        Token = globalSearchAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID = globalSearchAPI.createSpecialtyAPI(Token, NameSpecialty, Description1, Icon1, Parent1);
    }


    public void searchUser(String Request) {
        p.clickAndSendKeys(p.inputQuickGlobalSearch, Request);
        p.sleep(1000);
        p.preloader();
        System.out.println("Эапрос: " + Request);
        p.waitTextPresent(p.listNamResulteQuickGlobalSearch.get(0), SecondName + " " + FirstName + " " + MiddleName);
    }

    public void searchOrg(String Request) {
        p.clickAndSendKeysAndEnter(p.inputQuickGlobalSearch, Request);
        p.sleep(1000);
        p.preloader();
        System.out.println("Эапрос: " + Request);

        for (WebElement Result : p.listNameResultFullGlobalSearch) {
            p.moveMouse(Result);
            System.out.println(Result.getText());
            if (Result.getText().equals(NameOrg)) {
                System.out.println("Организация найдена");
                return;
            }
        }
        throw new AssertionError("Организация не найдена");
    }

    public void searchService(String Request) {
        p.clickAndSendKeysAndEnter(p.inputQuickGlobalSearch, Request);
        p.sleep(1000);
        p.preloader();
        System.out.println("Эапрос: " + Request);

        for (WebElement Result : p.listNameResultFullGlobalSearch) {
            p.moveMouse(Result);
            System.out.println(Result.getText());
            if (Result.getText().equals(NameService)) {
                System.out.println("Услуга найдена");
                return;
            }
        }
        throw new AssertionError("Услуга не найдена");
    }

    public void searchSpecialty(String Request) {
        p.clickAndSendKeysAndEnter(p.inputQuickGlobalSearch, Request);
        p.sleep(1000);
        p.preloader();
        System.out.println("Эапрос: " + Request);

        for (WebElement Result : p.listNameResultFullGlobalSearch) {
            p.moveMouse(Result);
            System.out.println(Result.getText());
            if (Result.getText().equals(NameSpecialty)) {
                System.out.println("Специальность найдена");
                return;
            }
        }
        throw new AssertionError("Специальность не найдена");
    }
}



