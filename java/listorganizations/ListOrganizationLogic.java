package listorganizations;

import global.GlobalPage;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.util.ArrayList;
import java.util.logging.Level;

@RunWith(JUnitParamsRunner.class)
public class ListOrganizationLogic {

    private static WebDriver driver;
    private static ListOrganizationPage page;
    int SumOrg = 0;

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new ListOrganizationPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
////            driver.manage().addCookie(page.readCookiesFromFile());
////            driver.get("http://localhost:8000/#/");
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
            try {
                page.waitE_ClickableAndClick(page.buttonListOrganizations);
            } catch (TimeoutException e) {
                System.out.println(e);
                page.waitE_ClickableAndClick(page.menuOrganizations);
                page.waitE_ClickableAndClick(page.buttonListOrganizations);
            }
        }

        @Override
        protected void finished(Description description) {
            driver.navigate().refresh();
//            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

    @BeforeClass
    public static void beforClass() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(ConfigProperties.getTestProperty("head"));
        options.addArguments("window-size=1200,800");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(options);
        page = new ListOrganizationPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void nameOrg() {
        int ShowOrg = Integer.parseInt(page.showOrg.getText());
        int AllOrg = Integer.parseInt(page.allOrg.getText());

        page.waitE_visibilityOf(page.listNameOrg.get(0));
        page.sleep(1000);
        int i = -1;
        for (WebElement Name : page.listNameOrg) {
            i = i + 1;
            System.out.println("Название организации: " + page.listNameOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое название организации", !page.listNameOrg.get(i).getText().equals(""));
        }
        System.out.println("Количество орг всего: " + AllOrg);
        System.out.println("Количество показанных орг: " + ShowOrg);
        System.out.println("Количество строк с орг: " + page.listNameOrg.size());

        Assert.assertTrue(ShowOrg == AllOrg & ShowOrg == page.listNameOrg.size());
    }

    public void nameAbbrOrg() {
        page.waitE_visibilityOf(page.listNameAbbrOrg.get(0));
        page.sleep(1000);
        int i = -1;
        for (WebElement Name : page.listNameAbbrOrg) {
            i = i + 1;
            System.out.println("Сокращенное название организации: " + page.listNameAbbrOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое сокращенное название", !page.listNameAbbrOrg.get(i).getText().equals(""));
        }
    }

    public void directorOrg() {
        page.waitE_visibilityOf(page.listDirectorOrg.get(0));
        page.sleep(1000);
        int i = -1;
        for (WebElement Name : page.listDirectorOrg) {
            i = i + 1;
            System.out.println("Руководитель: " + page.listDirectorOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое поле Руководитель", !page.listDirectorOrg.get(i).getText().equals(""));
        }
    }

    public void buttonActionsOrg() {
        page.waitE_visibilityOf(page.listActionOrg.get(0));
        page.sleep(1000);
        int i = -1;
        for (WebElement Name : page.listActionOrg) {
            i = i + 1;
            System.out.println("Search Action: " + i);
            Assert.assertTrue("Отсутствует сокращенное название", page.listActionOrg.get(i).isEnabled());
        }
    }

    public void additionalOrg() {
        page.waitE_visibilityOf(page.listAdditionalOrg.get(0));
        page.sleep(1000);
        int i = -1;
        for (WebElement Name : page.listAdditionalOrg) {
            i = i + 1;
            System.out.println("Руководитель: " + page.listAdditionalOrg.get(i).getText());
            Assert.assertEquals("Найдено пустое поле Подробнее", "Подробнее", page.listAdditionalOrg.get(i).getText());
        }
    }

    public void menuActionEditlOrg() {
        int i = 5;
        page.waitE_visibilityOf(page.listNameOrg.get(i));
        String NameOrg = page.listNameOrg.get(i).getText();
        page.waitE_ClickableAndClick(page.listActionOrg.get(i));
        page.waitE_ClickableAndClick(page.listLinkEditOrg.get(page.listLinkEditOrg.size() - 1));
        page.waitE_visibilityOf(page.headerEditOrg);
        Assert.assertTrue("Заголовок \"Редактирование организации\" отсутствует", page.headerEditOrg.isEnabled());
        Assert.assertEquals("Редактируем не выбранную организацию", NameOrg, page.nameEditOrg.getText());
    }

    public void menuActionDeletelOrg() {

        String NameOrg = "";
        int i = -1;
        int a = -1;
        for (WebElement Name : page.listNameOrg) {
            i = i + 1;
            System.out.println("Орг: " + Name.getText());

            try {
                WebElement trigger = driver.findElement(By.xpath("//div[contains(text()," + "\"" + Name.getText() +
                        "\"" +
                        ")]/../../span[@class=\"el-tree-node__expand-icon el-icon-caret-right expanded\"]"));
                System.out.println(trigger);

            } catch (NoSuchElementException e) {
                page.waitE_visibilityOf(Name);
                NameOrg = Name.getText();
                page.waitE_ClickableAndClick(page.listActionOrg.get(i));
                page.waitE_ClickableAndClick(page.listLinkDeleteOrg.get(page.listLinkEditOrg.size() - 1));
                page.waitE_visibilityOf(page.headerDeleteOrg);
                Assert.assertTrue("Заголовок \"Удалить подразделение?\" отсутствует", page.headerDeleteOrg.isEnabled());
                Assert.assertEquals("Удаляем не выбранную организацию", NameOrg, page.nameDeleteOrg.getText());
                page.waitE_ClickableAndClick(page.buttonDeleteOrg);
                break;
            }
        }
        for (WebElement Name2 : page.listNameOrg) {
            a = a + 1;
            System.out.println("Ищем удаленную орг: " + NameOrg);
            Assert.assertTrue("Найдена удаленная организация", !Name2.equals(NameOrg));
        }
    }

    public void menuActionCancelDeletelOrg() {

        String NameOrg = "";
        int i = -1;
        int a = -1;
        for (WebElement Name : page.listNameOrg) {
            i = i + 1;
            System.out.println("Орг: " + Name.getText());

            try {
                WebElement trigger = driver.findElement(By.xpath("//div[contains(text()," + "\"" + Name.getText() +
                        "\"" +
                        ")]/../../span[@class=\"el-tree-node__expand-icon el-icon-caret-right expanded\"]"));
                System.out.println(trigger);

            } catch (NoSuchElementException e) {
                page.waitE_visibilityOf(Name);
                NameOrg = Name.getText();
                page.waitE_ClickableAndClick(page.listActionOrg.get(i));
                page.waitE_ClickableAndClick(page.listLinkDeleteOrg.get(page.listLinkEditOrg.size() - 1));
                page.waitE_visibilityOf(page.headerDeleteOrg);
                Assert.assertTrue("Заголовок \"Удалить подразделение?\" отсутствует", page.headerDeleteOrg.isEnabled());
                Assert.assertEquals("Удаляем не выбранную организацию", NameOrg, page.nameDeleteOrg.getText());
                page.waitE_ClickableAndClick(page.buttonCancelDeleteOrg);
                break;
            }
        }
        for (WebElement Name2 : page.listNameOrg) {
            a = a + 1;
            System.out.println(Name2.getText());
            System.out.println("Ищем удаленную орг: " + NameOrg);
            if (Name2.getText().equals(NameOrg)) {
                System.out.println("Organization not deleted");
                return;
            }
        }
        throw new AssertionError("Organization deleted");
    }

    public void menuActionDeletelOrgWithChild() {

        String NameOrg = "";
        int i = -1;
        int a = -1;
        page.sleep(1000);
        for (WebElement Name : page.listNameOrg) {
            i = i + 1;
            System.out.println("Орг: " + Name.getText());
            WebElement trigger = driver.findElement(By.xpath("//div[contains(text()," + "\"" + Name.getText() +
                    "\"" +
                    ")]/../../span[@class=\"el-tree-node__expand-icon el-icon-caret-right expanded\"]"));
            if (trigger.isEnabled()) {
//                page.waitE_visibilityOf(Name);
                NameOrg = Name.getText();
                page.waitE_ClickableAndClick(page.listActionOrg.get(i));
                page.waitE_ClickableAndClick(page.listLinkDeleteOrg.get(page.listLinkEditOrg.size() - 1));
                page.waitE_visibilityOf(page.popverCanNotDeleteOrg);
                break;
            }
        }
        for (WebElement Name2 : page.listNameOrg) {
            a = a + 1;
            System.out.println(Name2.getText());
            System.out.println("Ищем удаленную орг: " + NameOrg);
            if (Name2.getText().equals(NameOrg)) {
                System.out.println("Organization not deleted");
                return;
            }
        }
        throw new AssertionError("Organization deleted");
    }

    public void additionaViewOrg() {
        int i = 4;
        String NameOrg = page.listNameOrg.get(i).getText();
        page.waitE_ClickableAndClick(page.listAdditionalOrg.get(i));
        page.waitE_visibilityOf(page.headerViewOrg);

        Assert.assertTrue("Заголовок \"Информация о подразделении\" отсутствует", page.headerViewOrg.isEnabled());
        Assert.assertEquals("Просматриваем не выбранную организацию", NameOrg, page.nameViewOrg.getText());
    }

    public void filterStructurelOrg() {
        int i = -1;
        ArrayList<String> Branch1 = new ArrayList<>();
        ArrayList<String> Branch2 = new ArrayList<>();
        ArrayList<String> Branch3 = new ArrayList<>();
        page.waitE_visibilityOf(page.branchStepashin.get(page.branchStepashin.size() - 1));
        page.sleep(1000);

        for (WebElement Name : page.branchStepashin) {
            Branch1.add(Name.getText());
            System.out.println("Branch 1: " + Name.getText());
        }

        page.waitE_ClickableAndClick(page.listStructureOrg);
        page.waitE_ClickableAndClick(page.filterNameHeadOrg);
        page.waitE_ClickableAndClick(page.checkBoxStepashin);

        for (i = 0; i < page.filterBranchStepashin.size(); i++) {
            Branch2.add(page.filterBranchStepashin.get(i).getText());
            page.waitE_ClickableAndClick(page.filterBranchStepashin.get(i));
            System.out.println("Branch 2: " + page.filterBranchStepashin.get(i).getText());
        }

        for (WebElement Name : page.branchStepashin) {
            Branch3.add(Name.getText());
            System.out.println("Branch 3: " + Name.getText());
        }
        System.out.println(Branch1.equals(Branch2) && Branch2.equals(Branch3));
        Assert.assertTrue(Branch1.equals(Branch2) && Branch2.equals(Branch3));
    }

    public void filterSearchlOrg() {
        ArrayList<String> Branch1 = new ArrayList<>();
        ArrayList<String> Branch2 = new ArrayList<>();
        page.waitE_visibilityOf(page.branchStepashin.get(page.branchStepashin.size() - 1));
        page.sleep(1000);

        for (WebElement Name : page.branchStepashin) {
            Branch1.add(Name.getText());
            System.out.println("Branch 1: " + Name.getText());
        }
        page.waitE_ClickableSendKeysAndClick(page.fieldSearchOrg, "млад");
        page.sleep(1000);
        for (WebElement Name : page.treeOrg) {
            Branch2.add(Name.getText());
            System.out.println("Branch 2: " + Name.getText());
        }

        Assert.assertEquals(Branch2.get(0), "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский" +
                " Университет)");
        Branch2.remove(0);

        System.out.println(Branch1.equals(Branch2));
        Assert.assertEquals(Branch1, Branch2);
    }

    public void buttonAddOrg() {
        page.waitE_ClickableAndClick(page.buttonAddOrgStick);
        Assert.assertTrue("Заголовок \"Добавть подразделение не найден\"", page.headerAddOrg.isEnabled());

    }

}
