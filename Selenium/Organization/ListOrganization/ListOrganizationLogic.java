package Organization.ListOrganization;

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
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class ListOrganizationLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private ListOrganizationPage p;

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
            p = new ListOrganizationPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
            p.click(p.menuOrganizations);
            p.click(p.buttonListOrganizations);
        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsListOrganization);
        }
    };

    public void nameOrg() {
        p.sleep(3000);
        int AllOrg = Integer.parseInt(p.allOrg.getText());
        p.sleep(2000);
        p.waitVisibility(p.listNameOrg.get(0));
        Assert.assertTrue(p.listNameOrg.size() > 5);

        p.sleep(1000);
        int i = -1;
        for (WebElement Name : p.listNameOrg) {
            p.moveMouse(Name);
            i = i + 1;
            System.out.println("Название организации: " + p.listNameOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое название организации", !p.listNameOrg.get(i).getText().equals(""));
        }
        System.out.println("Количество орг всего: " + AllOrg);
        System.out.println("Количество строк с орг: " + p.listNameOrg.size());
    }

    public void nameAbbrOrg() {
        p.sleep(2000);
        p.waitVisibility(p.listNameAbbrOrg.get(0));
        p.sleep(1000);
        int i = -1;
        for (WebElement Name : p.listNameAbbrOrg) {
            p.moveMouse(Name);
            i = i + 1;
            System.out.println("Сокращенное название организации: " + p.listNameAbbrOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое сокращенное название", !p.listNameAbbrOrg.get(i).getText().equals(""));
        }
    }

    public void directorOrg() {
        p.preloader();
        p.waitVisibility(p.listDirectorOrg.get(0));

        try {
            for (int x = 0; x < 100; x++) {
                if (p.listTriggerOrg.get(0).isEnabled()) {
                    p.click(p.listTriggerOrg.get(0));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Нераскрытые подразделения закончились");
        }

        int i = -1;
        for (WebElement Name : p.listDirectorOrg) {
            p.moveMouse(Name);
            i = i + 1;
            System.out.println("Руководитель: " + p.listDirectorOrg.get(i).getText());
            Assert.assertTrue("Найдено пустое поле Руководитель", !p.listDirectorOrg.get(i).getText().equals(""));
        }
    }

    public void buttonActionsOrg() {
        p.sleep(2000);
        p.waitVisibility(p.listActionOrg.get(0));
        p.sleep(1000);
        int i = -1;
        for (WebElement Name : p.listActionOrg) {
            p.moveMouse(Name);
            i = i + 1;
            System.out.println("Search Action: " + i);
            Assert.assertTrue("Отсутствует сокращенное название", p.listActionOrg.get(i).isEnabled());
        }
    }

    public void additionalOrg() {
        p.preloader();
        p.waitVisibility(p.listAdditionalOrg.get(0));

        try {
            for (int x = 0; x < 100; x++) {
                if (p.listTriggerOrg.get(0).isEnabled()) {
                    p.click(p.listTriggerOrg.get(0));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Нераскрытые подразделения закончились");
        }
        int i = -1;
        for (WebElement Name : p.listAdditionalOrg) {
            i = i + 1;
            p.moveMouse(Name);
            System.out.println("Нашли кнопку: " + p.listAdditionalOrg.get(i).getText());
            Assert.assertEquals("Найдено пустое поле Подробнее", "Подробнее", p.listAdditionalOrg.get(i).getText());
        }
    }

    public void menuActionEditlOrg() {
        int i = 5;
        p.sleep(3000);
        Assert.assertTrue(p.listNameOrg.size() > 0);
        p.waitVisibility(p.listNameOrg.get(i));
        String NameOrg = p.listNameOrg.get(i).getText();
        p.click(p.listActionOrg.get(i));
        p.sleep(1000);
        p.click(p.listLinkEditOrg.get(p.listLinkEditOrg.size() - 1));
        p.waitVisibility(p.headerEditOrg);
        Assert.assertTrue("Заголовок \"Редактирование организации\" отсутствует", p.headerEditOrg.isEnabled());
        Assert.assertEquals("Редактируем не выбранную организацию", NameOrg, p.nameEditOrg.getText());
    }

    public void menuActionDeletelOrg() {
        int a = -1;
        p.sleep(3000);
        Assert.assertTrue(p.listNameOrg.size() > 0);
        System.out.println("Количество подразделений: " + p.listNameOrg.size());
        System.out.println("Количество ссылок \"Удалить\": " + p.listLinkEditOrg.size());

        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.click(p.trigStepashinDelete);
        p.click(p.listLinkDeleteOrg);
        p.waitVisibility(p.headerDeleteOrg);
        Assert.assertTrue("Заголовок \"Удалить подразделение?\" отсутствует",
                p.headerDeleteOrg.isEnabled());
        Assert.assertEquals("Удаляем не выбранную организацию", p.StepashinForDelete.getText(),
                p.nameDeleteOrg.getText());
        p.click(p.buttonDeleteOrg);
        p.preloader();
        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);

        try {
            for (WebElement Name2 : p.listNameOrg) {
                p.moveMouse(Name2);
                a = a + 1;
                System.out.println("Ищем удаленную орг: " + p.StepashinForDelete.getText());
                Assert.assertTrue("Найдена удаленная организация", !Name2.getText().equals(p.StepashinForDelete.getText()));
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все хорошо, организация ненайдена");
        }
    }

    public void menuActionCancelDeletelOrg() {
        int a = -1;
        p.sleep(2000);
        Assert.assertTrue(p.listNameOrg.size() > 0);

        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.click(p.trigStepashin3);

        p.click(p.listLinkDeleteOrg);
        p.waitVisibility(p.headerDeleteOrg);
        Assert.assertTrue("Заголовок \"Удалить подразделение?\" отсутствует", p.headerDeleteOrg.isEnabled());
        Assert.assertEquals("Удаляем не выбранную организацию", p.Stepashin3.getText(),
                p.nameDeleteOrg.getText());
        p.click(p.buttonCancelDeleteOrg);

        for (WebElement Name2 : p.listNameOrg) {
            p.moveMouse(Name2);
            a = a + 1;
            System.out.println(Name2.getText());
            System.out.println("Ищем НЕудаленную орг: " + p.Stepashin3.getText());
            if (Name2.getText().equals(p.Stepashin3.getText())) {
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
        p.sleep(3000);
        Assert.assertTrue(p.listNameOrg.size() > 0);
        for (WebElement Name : p.listNameOrg) {
            p.moveMouse(Name);
            i = i + 1;
            System.out.println("Орг: " + Name.getText());
            WebElement trigger = driver.findElement(By.xpath("//div[contains(text()," + "\"" + Name.getText() +
                    "\"" +
                    ")]/../../span[@class=\"el-tree-node__expand-icon el-icon-caret-right expanded\"]"));
            if (trigger.isEnabled()) {
                NameOrg = Name.getText();
                p.click(p.listActionOrg.get(i));
                p.sleep(1000);
                p.click(p.listLinkDeleteOrg);
                p.waitVisibility(p.popverCanNotDeleteOrg);
                break;
            }
        }
        for (WebElement Name2 : p.listNameOrg) {
            p.moveMouse(Name2);
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
        String NameOrg = p.listNameOrg.get(i).getText();
        p.click(p.listAdditionalOrg.get(i));
        p.waitVisibility(p.headerViewOrg);

        Assert.assertTrue("Заголовок \"Информация о подразделении\" отсутствует", p.headerViewOrg.isEnabled());
        Assert.assertEquals("Просматриваем не выбранную организацию", NameOrg, p.nameViewOrg.getText());
    }

    public void filterStructurelOrg() {
        int i = -1;
        ArrayList<String> Branch1 = new ArrayList<>();
        ArrayList<String> Branch2 = new ArrayList<>();
        ArrayList<String> Branch3 = new ArrayList<>();
        p.sleep(2000);

        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.moveMouse(p.Stepashin3);

        for (WebElement Name : p.branchStepashin) {
            Branch1.add(Name.getText());
            System.out.println("Branch 1: " + Name.getText());
        }

        p.click(p.body);
        p.scrollHomePage();

        p.click(p.listStructureOrg);
        p.click(p.filterNameHeadOrg);
        p.buttonDown(2);
        p.click(p.checkBoxStepashin);

        for (i = 0; i < p.filterBranchStepashin.size(); i++) {
            Branch2.add(p.filterBranchStepashin.get(i).getText());
            p.click(p.filterBranchStepashin.get(i));
            System.out.println("Branch 2: " + p.filterBranchStepashin.get(i).getText());
        }
        p.click(p.userinfoname);
        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.moveMouse(p.Stepashin3);

        for (WebElement Name : p.branchStepashin) {
            Branch3.add(Name.getText());
            System.out.println("Branch 3: " + Name.getText());
        }

        System.out.println(Branch1.equals(Branch2) && Branch2.equals(Branch3));
        Assert.assertTrue(Branch1.equals(Branch2) && Branch2.equals(Branch3));
    }

    public void filterSearchOrg() {
        ArrayList<String> Branch1 = new ArrayList<>();
        ArrayList<String> Branch2 = new ArrayList<>();
        Branch1.clear();
        Branch2.clear();
        p.sleep(4000);
        p.waitVisibility(p.branchStepashin.get(p.branchStepashin.size() - 1));

        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.moveMouse(p.Stepashin3);

        for (WebElement Name : p.branchStepashin) {
            p.moveMouse(Name);
            Branch1.add(Name.getText());
            System.out.println("Branch 1: " + Name.getText());
        }
        p.click(p.body);
        p.scrollHomePage();
        p.clickAndSendKeysAndClick(p.fieldSearchOrg, "Степа");
        p.sleep(1000);

        p.click(p.Stepashin0);
        p.click(p.Stepashin1);
        p.click(p.Stepashin2);
        p.moveMouse(p.Stepashin3);

        for (WebElement Name : p.treeOrg) {
            p.moveMouse(Name);
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
        p.click(p.buttonAddOrgStick);
        p.waitTextPresent(p.headerAddOrg, "Добавить подразделение");
        Assert.assertTrue("Заголовок \"Добавить подразделение не найден\"", p.headerAddOrg.isEnabled());

    }

}
