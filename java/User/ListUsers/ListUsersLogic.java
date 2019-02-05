package User.ListUsers;

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

@RunWith(JUnitParamsRunner.class)
public class ListUsersLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private ListUsersPage p;
    int i = 0;
    int u = -1;

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
            p = new ListUsersPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
            driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
            p.sleep(2000);
        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsListUser);
        }
    };

    @BeforeClass
    public static void beforClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    public void sumUsers() {
        p.sleep(1000);
        p.click(p.sumWorker);
        p.click(p.workers100);
        p.sleep(1000);
        p.waitVisibility(p.showWorkers);
        System.out.println(p.showWorkers.getText() + " = " + p.allWorkers.getText());
        Assert.assertEquals(p.showWorkers.getText(), p.allWorkers.getText());
        Assert.assertEquals(Integer.parseInt(p.allWorkers.getText()), (p.listSecondNameInUserList.size()));
    }

    public void viewUserProfile() {
        String Fio = "";
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);
        int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
        p.moveMouse(p.listSecondNameInUserList.get(Random));
        if (p.listMiddleNameInUserList.get(Random).getText().equals("")) {
            Fio = p.listSecondNameInUserList.get(Random).getText() + " " + p.listFirstNameInUserList.get(Random).getText();
        } else {
            Fio = p.listSecondNameInUserList.get(Random).getText() + " " + p.listFirstNameInUserList.get(Random).getText() + " " +
                    p.listMiddleNameInUserList.get(Random).getText();
        }
        p.click(p.listSecondNameInUserList.get(Random));
        if (Fio.equals("Акулин")) {
            p.waitTextPresent(p.headerMyProfileUser, "Мой профиль");
        } else {
            p.waitTextPresent(p.headerInfoUser, "Информация о сотруднике");
        }
        p.waitTextPresent(p.profFio, Fio);
        p.click(p.linkBack);
    }

    public void listElements() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        for (WebElement Number : p.listNumber) {
            i = i + 1;
            u = u + 1;
            p.compareWebelementAndString(Number, Integer.toString(i));
            Assert.assertTrue(p.listAvatar.get(u).isEnabled());
            Assert.assertTrue(p.listTrigger.get(u).isEnabled());
            Assert.assertTrue(p.listSecondNameInUserList.get(u).isEnabled());
            Assert.assertTrue(p.listFirstNameInUserList.get(u).isEnabled());
            Assert.assertTrue(p.listPostInUserList.get(u).isEnabled());
            Assert.assertTrue(p.listStatusInUserList.get(u).isEnabled());
        }
    }

    public void blockInfoUser() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);
        int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
        p.moveMouse(p.listSecondNameInUserList.get(Random));
        String Fio = p.listSecondNameInUserList.get(Random).getText() + " " + p.listFirstNameInUserList.get(Random).getText() + " " +
                p.listMiddleNameInUserList.get(Random).getText();
        p.click(p.listTrigger.get(Random));
        p.waitTextPresent(p.blockInfoUser, Fio);

        p.click(p.buttonCreateUserInListUser);
        p.sleep(2000);
        p.preloader();
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/create"));
    }

    public void filterOrg() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterOrganization);
        p.click(p.lionsdigital);
        p.click(p.userinfoname);

        String Org = p.lionsdigital.getText();

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.showWorkers);
            p.preloader();
            p.sleep(1000);
//            p.click(p.sumWorker);
//            p.click(p.workers100);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            u = 0;
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            for (WebElement NameWork : p.listProfNameWorkspace) {
                p.moveMouse(NameWork);
                System.out.println(NameWork.getText());
                if (NameWork.getText().contains(Org)) {
                    u = u + 1;
                }
            }
            Assert.assertTrue(u >= 1);
            System.out.println("Проверка № " + x + " завершена");
            driver.navigate().back();
            p.click(p.filterOrganization);
            p.click(p.lionsdigital);
            p.click(p.userinfoname);
        }
    }

    public void filterPost() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterPost);
        p.sleep(1000);
        int RandomPost = p.random(p.listCheckBoxPostInFilter.size() - 1);
        String NameRandomPost = p.listPostInFilter.get(RandomPost).getText();
        p.moveMouse(p.listCheckBoxPostInFilter.get(RandomPost));
        System.out.println("Выбранная должность: " + NameRandomPost);
        p.click(p.listCheckBoxPostInFilter.get(RandomPost));
        p.click(p.userinfoname);


        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(500);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            p.moveMouse(p.profPosition.get(0));
            if (p.profPosition.size() == 0) {
                throw new AssertionError("В карточке не найдены должности");
            }
            u = 0;
            for (WebElement Post : p.profPosition) {
                p.moveMouse(Post);
                System.out.println("Должность в карточке: " + Post.getText());
                if (Post.getText().replace(",", "").contains(NameRandomPost)) {
                    u = u + 1;
                }
            }
            System.out.println("Проверка № " + x + " завершена, U = " + u);
            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            p.click(p.filterPost);
            p.sleep(1000);
            RandomPost = p.random(p.listCheckBoxPostInFilter.size() - 1);
            NameRandomPost = p.listPostInFilter.get(RandomPost).getText();
            p.moveMouse(p.listCheckBoxPostInFilter.get(RandomPost));
            System.out.println("Выбранная должность: " + NameRandomPost);
            p.click(p.listCheckBoxPostInFilter.get(RandomPost));
            p.click(p.userinfoname);
        }
    }

    public void filterRole() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterRole);
        p.sleep(1000);
        int RandomRole = p.random(p.listRoleInFilter.size() - 1);
        String NameRandomPost = p.listRoleInFilter.get(RandomRole).getText();
        p.moveMouse(p.listRoleInFilter.get(RandomRole));
        System.out.println("Выбранная роль: " + NameRandomPost);
        p.click(p.listRoleInFilter.get(RandomRole));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(500);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            p.moveMouse(p.listProfRole.get(0));
            if (p.listProfRole.size() == 0) {
                throw new AssertionError("В карточке не найдены роли");
            }
            u = 0;
            for (WebElement Role : p.listProfRole) {
                p.moveMouse(Role);
                System.out.println("Роль в карточке: " + Role.getText());
                if (Role.getText().replace(",", "").contains(NameRandomPost)) {
                    u = u + 1;
                }
            }
            System.out.println("Проверка № " + x + " завершена, U = " + u);
            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            p.click(p.filterRole);
            p.sleep(1000);
            RandomRole = p.random(p.listRoleInFilter.size() - 1);
            NameRandomPost = p.listRoleInFilter.get(RandomRole).getText();
            p.moveMouse(p.listRoleInFilter.get(RandomRole));
            System.out.println("Выбранная роль: " + NameRandomPost);
            p.click(p.listRoleInFilter.get(RandomRole));
            p.click(p.userinfoname);
        }
    }

    public void filterSpecialty() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterSpecialty);
        p.sleep(1000);
        int RandomSpecialtyDepartment = p.random(p.listSpecialtyDepartment.size() - 1);
        p.click(p.listSpecialtyDepartment.get(RandomSpecialtyDepartment));
        p.sleep(1000);
        int RandomSpecialty = p.random(p.listNameSpecialty.size() - 1);
        String NameRandomSpecialty = p.listNameSpecialty.get(RandomSpecialty).getText();
        p.moveMouse(p.listNameSpecialty.get(RandomSpecialty));
        System.out.println("Выбранная специальность: " + NameRandomSpecialty);
        p.click(p.getListCheckBoxSpecialty.get(RandomSpecialty));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(1000);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            p.moveMouse(p.listProfRole.get(0));
            if (p.listProfRole.size() == 0) {
                throw new AssertionError("В карточке не найдены специальности");
            }
            u = 0;
            for (WebElement Spec : p.listProfSpeciality) {
                p.moveMouse(Spec);
                System.out.println("Специальность в карточке: " + Spec.getText());
                if (Spec.getText().replace(",", "").contains(NameRandomSpecialty)) {
                    u = u + 1;
                }
            }
            System.out.println("Проверка № " + x + " завершена, U = " + u);
            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            p.click(p.filterSpecialty);
            p.sleep(1000);
            RandomSpecialtyDepartment = p.random(p.listSpecialtyDepartment.size() - 1);
            p.click(p.listSpecialtyDepartment.get(RandomSpecialtyDepartment));
            p.sleep(1000);
            RandomSpecialty = p.random(p.listNameSpecialty.size() - 1);
            NameRandomSpecialty = p.listNameSpecialty.get(RandomSpecialty).getText();
            p.moveMouse(p.listNameSpecialty.get(RandomSpecialty));
            System.out.println("Выбранная специальность: " + NameRandomSpecialty);
            p.click(p.getListCheckBoxSpecialty.get(RandomSpecialty));
            p.click(p.userinfoname);
        }
    }

    public void filterStatusWork() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterStatusWork);
        p.sleep(1000);
        int RandomStatusWork = p.random(p.listStatusWork.size() - 1);
        String NameRandomStatusWork = p.listStatusWork.get(RandomStatusWork).getText();
        p.moveMouse(p.listStatusWork.get(RandomStatusWork));
        System.out.println("Выбраннй статус работы: " + NameRandomStatusWork);
        p.click(p.listStatusWork.get(RandomStatusWork));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(1000);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            p.moveMouse(p.listProfStatus.get(0));
            if (p.listProfStatus.size() == 0) {
                throw new AssertionError("В карточке не найдены статусы работы");
            }
            u = 0;
            for (WebElement StatusWork : p.listProfStatus) {
                p.moveMouse(StatusWork);
                System.out.println("Статус работы в карточке: " + StatusWork.getText());
                if (StatusWork.getText().replace(",", "").contains(NameRandomStatusWork)) {
                    u = u + 1;
                }
            }
            System.out.println("Проверка № " + x + " завершена, U = " + u);
            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            p.click(p.filterSpecialty);
            p.sleep(1000);
            p.click(p.filterStatusWork);
            p.sleep(1000);
            RandomStatusWork = p.random(p.listStatusWork.size() - 1);
            NameRandomStatusWork = p.listNameSpecialty.get(RandomStatusWork).getText();
            p.moveMouse(p.listNameSpecialty.get(RandomStatusWork));
            System.out.println("Выбраннй статус работы: " + NameRandomStatusWork);
            p.click(p.getListCheckBoxSpecialty.get(RandomStatusWork));
            p.click(p.userinfoname);

        }
    }

    public void filterStatusSystem() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterStatusInSystem);
        p.sleep(1000);
        int RandomStatusSystem = p.random(p.listStatusSystem.size() - 1);
        String NameRandomStatusSystem = p.listStatusSystem.get(RandomStatusSystem).getText();
        p.moveMouse(p.listStatusSystem.get(RandomStatusSystem));
        System.out.println("Выбраннй статус в системе: " + NameRandomStatusSystem);
        p.click(p.listStatusSystem.get(RandomStatusSystem));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(1000);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
//            p.moveMouse(System.out.printlnofSystemStatus);
//            if (!System.out.printlnofSystemStatus.isEnabled()) {
//                throw new AssertionError("В карточке не найден статус в системе");
//            }
//            u = 0;

            p.compareWebelementAndString(p.profSystemStatus, NameRandomStatusSystem);

//            System.out.println("Проверка № " + x + " завершена, U = " + u);
//            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            RandomStatusSystem = p.random(p.listStatusSystem.size() - 1);
            NameRandomStatusSystem = p.listStatusSystem.get(RandomStatusSystem).getText();
            p.moveMouse(p.listStatusSystem.get(RandomStatusSystem));
            System.out.println("Выбраннй статус в системе: " + NameRandomStatusSystem);
            p.click(p.listStatusSystem.get(RandomStatusSystem));
            p.click(p.userinfoname);

        }
    }

    public void filterStatusOnline() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterStatusOnline);
        p.sleep(1000);
        int RandomStatusOnline = p.random(p.listStatusOnline.size() - 1);
        String NameRandomStatusOnline = p.listStatusOnline.get(RandomStatusOnline).getText();
        p.moveMouse(p.listStatusOnline.get(RandomStatusOnline));
        System.out.println("Выбраннй статус Online: " + NameRandomStatusOnline);
        p.click(p.listStatusOnline.get(RandomStatusOnline));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(1000);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
//            p.moveMouse(System.out.printlnofSystemStatus);
//            if (!System.out.printlnofSystemStatus.isEnabled()) {
//                throw new AssertionError("В карточке не найден статус в системе");
//            }
//            u = 0;

            p.compareWebelementAndString(p.profLabelOnline, NameRandomStatusOnline);

//            System.out.println("Проверка № " + x + " завершена, U = " + u);
//            Assert.assertTrue(u >= 1);
            driver.navigate().back();
            p.click(p.filterStatusOnline);
            p.sleep(1000);
            RandomStatusOnline = p.random(p.listStatusOnline.size() - 1);
            NameRandomStatusOnline = p.listStatusOnline.get(RandomStatusOnline).getText();
            p.moveMouse(p.listStatusOnline.get(RandomStatusOnline));
            System.out.println("Выбраннй статус Online: " + NameRandomStatusOnline);
            p.click(p.listStatusOnline.get(RandomStatusOnline));
            p.click(p.userinfoname);
        }
    }

    public void filterGlobalRole() {
        p.sleep(1000);
        p.preloader();
        p.waitVisibility(p.showWorkers);

        p.click(p.filterGlobalRole);
        p.sleep(1000);
        int RandomGlobalRole = p.random(p.listInFilter.size() - 1);
        String NameRandomGlobalRole = p.listInFilter.get(RandomGlobalRole).getText();
        p.moveMouse(p.listInFilter.get(RandomGlobalRole));
        System.out.println("Выбранная глобальная роль: " + NameRandomGlobalRole);
        p.click(p.listInFilter.get(RandomGlobalRole));
        p.click(p.userinfoname);

        for (int x = 0; x < 3; x++) {
            p.waitVisibility(p.buttonCreateUserInListUser);
            p.preloader();
            p.sleep(1000);
            p.moveMouse(p.showWorkers);
            int Random = p.random(Integer.parseInt(p.showWorkers.getText()) - 1);
            p.click(p.listSecondNameInUserList.get(Random));
            p.sleep(1000);
            p.preloader();
            System.out.println("Выбранный сотрудник: " + p.profFio.getText());
            p.compareWebelementAndString(p.profLabelSuperadministrator, NameRandomGlobalRole);
            driver.navigate().back();
            p.click(p.filterGlobalRole);
            p.sleep(1000);
            RandomGlobalRole = p.random(p.listInFilter.size() - 1);
            NameRandomGlobalRole = p.listInFilter.get(RandomGlobalRole).getText();
            p.moveMouse(p.listInFilter.get(RandomGlobalRole));
            System.out.println("Выбранная глобальная роль: " + NameRandomGlobalRole);
            p.click(p.listInFilter.get(RandomGlobalRole));
            p.click(p.userinfoname);
        }
    }

    public void searchSecondName() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchSecondName, "Акулин");
        p.click(p.buttonSearch);
        Assert.assertTrue(p.listSecondNameInUserList.size() >= 1);
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Акулин")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут фамилия: " + p.inputSearchSecondName.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchSecondName.getAttribute("value"));
    }

    public void searchFirstName() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchFirstName, "Аристарх");
        p.click(p.buttonSearch);
        Assert.assertTrue(p.listSecondNameInUserList.size() >= 1);
        for (WebElement Worker : p.listFirstNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Аристарх")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут имя: " + p.inputSearchSecondName.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchSecondName.getAttribute("value"));
    }

    public void searchMiddleName() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchMiddleName, "Анатольевич");
        p.click(p.buttonSearch);
        Assert.assertTrue(p.listSecondNameInUserList.size() >= 1);
        for (WebElement Worker : p.listMiddleNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Анатольевич")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут отчество: " + p.inputSearchMiddleName.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchMiddleName.getAttribute("value"));
    }

    public void searchEmail() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchEmail, "ioanner@mail.ru");
        p.click(p.buttonSearch);
        Assert.assertEquals(1, p.listSecondNameInUserList.size());
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Акулин")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут mail: " + p.inputSearchEmail.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchSecondName.getAttribute("value"));
    }

    public void searchPhone() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchPhone, "+ 7 (789) 785-64-54");
        p.click(p.buttonSearch);
        Assert.assertEquals(1, p.listSecondNameInUserList.size());
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Акулин")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут телефон: " + p.inputSearchPhone.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchSecondName.getAttribute("value"));
    }

    public void searchLogin() {
        int u = 0;
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonSearchWorker);
        p.clickAndSendKeys(p.inputSearchLogin, "ioanner");
        p.click(p.buttonSearch);
        Assert.assertEquals(1, p.listSecondNameInUserList.size());
        for (WebElement Worker : p.listSecondNameInUserList) {
            p.moveMouse(Worker);
            if (Worker.getText().equals("Акулин")) {
                u = u + 1;
            }
            Assert.assertTrue(u >= 1);
        }
        p.click(p.body);
        p.click(p.linkResetSearchAndFilter);
        Assert.assertEquals(10, p.listSecondNameInUserList.size());
        p.click(p.buttonSearchWorker);
        System.out.println("Атрибут логин: " + p.inputEmail.getAttribute("value"));
        Assert.assertEquals("", p.inputSearchSecondName.getAttribute("value"));
    }
}
