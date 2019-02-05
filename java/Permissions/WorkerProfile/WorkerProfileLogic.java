package Permissions.WorkerProfile;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class WorkerProfileLogic {

    String NowDate = null;
    String Token = null;
    int u = 0;

    private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private WorkerProfilePage p;

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
            p = new WorkerProfilePage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditPassword);
        }
    };

    public void viewWorkerProfile(String Role) {
        switch (Role) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5b58130f0faa2638b94fc4d7");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                p.waitVisibility(p.profAccLogin);
                p.waitVisibility(p.profAccEmail);
                p.waitVisibility(p.profAccPhone);
                p.waitVisibility(p.profSystemID);
                p.waitVisibility(p.profSystemStatus);
                p.waitVisibility(p.profQR);
                p.waitVisibility(p.profLabelSuperadministrator);
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5c04d3bc7d05c82a53ec8d07");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                p.waitVisibility(p.profAccLogin);
                p.waitVisibility(p.profAccEmail);
                p.waitVisibility(p.profAccPhone);
                p.waitVisibility(p.profSystemID);
                p.waitVisibility(p.profSystemStatus);
                p.waitVisibility(p.profQR);
                try {
                    p.waitVisibility(p.profLabelSuperadministrator);
                } catch (NoSuchElementException e) {
                    System.out.println("Метка 'Супрадминистратор' не найдена и это хорошо");
                }
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5b58130f0faa2638b94fc4d7");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                try {
                    p.waitVisibility(p.profAccLogin);
                } catch (NoSuchElementException e) {
                    System.out.println("'Логин' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profAccEmail);
                } catch (NoSuchElementException e) {
                    System.out.println("'Емейл' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profAccPhone);
                } catch (NoSuchElementException e) {
                    System.out.println("'Телефон' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profSystemID);
                } catch (NoSuchElementException e) {
                    System.out.println("'ID' не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profSystemStatus);
                } catch (NoSuchElementException e) {
                    System.out.println("'Статус' не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profLabelSuperadministrator);
                } catch (NoSuchElementException e) {
                    System.out.println("Метка 'Супрадминистратор' не найдена и это хорошо");
                }
                p.waitVisibility(p.profQR);
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            default:
                throw new Error("Тест пропущен");
        }
    }

//    public void editMyProfile(String Role) {
//        switch (Role) {
//            case ("SuperAdmin"):
//                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
//                p.click(p.buttonChangeProfile);
//                p.sleep(5000);
//                p.preloader();
//                p.waitTextPresent(p.secondNameProfile, "Акулин");
//                p.waitTextPresent(p.firstNameProfile, "Аристарх");
//                p.waitTextPresent(p.middleNameProfile, "Анатольевич");
//
//                p.moveMouse(p.loginProfile);
//                Assert.assertTrue(p.loginProfile.isEnabled());
//                p.moveMouse(p.emailProfile);
//                Assert.assertTrue(p.emailProfile.isEnabled());
//                p.moveMouse(p.phoneProfile);
//                Assert.assertTrue(p.phoneProfile.isEnabled());
//
//                p.click(p.linkEditPasswordProfile);
//                try {
//                    Assert.assertTrue("Супер-администратор видит поле ввода 'Текущий пароль' ",
//                            p.inputCurrentPassword.isEnabled());
//                } catch (NoSuchElementException e) {
//                    p.pr("Супер-администратор не видит поле ввода 'Текущий пароль' и это хорошо");
//                }
//
//                p.moveMouse(p.inputNewPassword);
//                Assert.assertTrue("Супер-администратор не видит поле ввода 'Новый пароль' ",
//                        p.inputNewPassword.isEnabled());
//
//                try {
//                    Assert.assertTrue("Супер-администратор видит ссылку 'Удалить пользователя' ",
//                            !p.linkDeleteUser.isEnabled());
//                } catch (NoSuchElementException e) {
//                    p.pr("Супер-администратор не видит ссылку 'Удалить пользователя' и это хорошо");
//                }
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
//                p.editUserNamesAPI(Token, "5bf3be3422ee2270923814af", "Акулин",
//                        "Аристарх", "Анатольевич","ok");
//                p.editUserAuthAPI(Token, "5bf3be3422ee2270923814af", "ioanner@mail.ru",
//                        "+ 7 (123) 456-78-99", "ioanner","ok");
//                p.editUserPasswordAPI(Token, "5bf3be3422ee2270923814af", "12345678","ok");
//
//                break;
//
//            case ("Admin"):
//                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
//                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
//                p.click(p.buttonChangeProfile);
//                p.sleep(5000);
//                p.preloader();
//                p.waitTextPresent(p.secondNameProfile, "Акулов");
//                p.waitTextPresent(p.firstNameProfile, "Игорь");
//                p.waitTextPresent(p.middleNameProfile, "Иванович");
//
//                p.moveMouse(p.loginProfile);
//                Assert.assertTrue(p.loginProfile.isEnabled());
//                p.moveMouse(p.emailProfile);
//                Assert.assertTrue(p.emailProfile.isEnabled());
//                p.moveMouse(p.phoneProfile);
//                Assert.assertTrue(p.phoneProfile.isEnabled());
//
//                p.click(p.linkEditPasswordProfile);
//                try {
//                    Assert.assertTrue("Администратор видит поле ввода 'Текущий пароль' ",
//                            p.inputCurrentPassword.isEnabled());
//                } catch (NoSuchElementException e) {
//                    p.pr("Администратор не видит поле ввода 'Текущий пароль' и это хорошо");
//                }
//
//                p.moveMouse(p.inputNewPassword);
//                Assert.assertTrue("Администратор не видит поле ввода 'Новый пароль' ",
//                        p.inputNewPassword.isEnabled());
//
//                try {
//                    Assert.assertTrue("Администратор видит ссылку 'Удалить пользователя' ",
//                            !p.linkDeleteUser.isEnabled());
//                } catch (NoSuchElementException e) {
//                    p.pr("Администратор не видит ссылку 'Удалить пользователя' и это хорошо");
//                }
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A, "ok");
//                p.editUserNamesAPI(Token, "5c123c7c0fb83b44189263bc", "Акулов ",
//                        "Игорь", "Иванович","ok");
//                p.editUserAuthAPI(Token, "5c123c7c0fb83b44189263bc", "Akulovkjhkhkljp9890@m.ru",
//                        "+ 8 (769) 876-87-68", "Akulov","ok");
//                p.editUserPasswordAPI(Token, "5c123c7c0fb83b44189263bc", "Akulov","ok");
//                break;
//
//            case ("Spec"):
//                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
//                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
//                p.click(p.buttonChangeProfile);
//                p.sleep(5000);
//                p.preloader();
//                try {
//                    Assert.assertTrue("Специалист видит ФИО при редактировании",
//                            !p.secondNameProfile.isEnabled() || !p.firstNameProfile.isEnabled() ||
//                                    !p.middleNameProfile.isEnabled());
//                } catch (NoSuchElementException e) {
//                    System.out.println("Специалист не видит ФИО при редактировании самого себя и это хорошо");
//                }
//
//                try {
//                    Assert.assertTrue("Специалист видит Логин/Емейл/Телефон при редактировании",
//                            !p.loginProfile.isEnabled() || !p.emailProfile.isEnabled() ||
//                                    !p.phoneProfile.isEnabled());
//                } catch (NoSuchElementException e) {
//                    System.out.println("Специалист не видит Логин/Емейл/Телефон  при редактировании самого себя" +
//                            " и это хорошо");
//                }
//
//                p.click(p.linkEditPasswordProfile);
//                p.moveMouse(p.inputCurrentPassword);
//                Assert.assertTrue("Специалист не видит поле ввода 'Текущий пароль' ",
//                        p.inputCurrentPassword.isEnabled());
//
//                p.moveMouse(p.inputNewPassword);
//                Assert.assertTrue("Специалист не видит поле ввода 'Новый пароль' ",
//                        p.inputNewPassword.isEnabled());
//
//                try {
//                    Assert.assertTrue("Специалист видит ссылку 'Удалить пользователя' ",
//                            !p.linkDeleteUser.isEnabled());
//                } catch (NoSuchElementException e) {
//                    p.pr("Специалист не видит ссылку 'Удалить пользователя' и это хорошо");
//                }
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S, "ok");
//                p.editUserNamesAPI(Token, "5c04d3bc7d05c82a53ec8d07", "Акульев",
//                        "Илья", "Петрович","access denied");
//                p.editUserAuthAPI(Token, "5c04d3bc7d05c82a53ec8d07", "Akulev57598769@il.ru",
//                        "+ 1 (846) 132-08-19", "Akulev","access denied");
//                p.editUserPasswordAPI(Token, "5c04d3bc7d05c82a53ec8d07", "Akulev","access denied");
//
//                break;
//
//            default:
//                throw new Error("Тест пропущен");
//        }
//    }
//
//    public void editMyWorkplace(String Role) {
//        switch (Role) {
//            case ("SuperAdmin"):
//                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistWorkpaleces);
//                p.waitTextPresent(p.linkEditWorkplaces.get(0), "Изменить");
//                p.waitTextPresent(p.linkEditSpecialty, "Изменить");
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
//                p.editUserAddWorkplaceAPI(Token, "5bf3be3422ee2270923814af", "5b7d08aa068d7552d904d8be",
//                        "Врач", "admin", "active", "true","ok");
//                p.editUserSpecialtyAPI(Token, "5bf3be3422ee2270923814af", "5bc0a2c043aef1053d0ada80",
//                        "5bc308a243aef1053d0adab0", "5bc0a51d43aef1053d0ada8f",
//                        "5be5a47fdd89ae51736ecb0d","ok");
//
//                break;
//
//            case ("Admin"):
//                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
//                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistWorkpaleces);
//                p.waitTextPresent(p.linkEditWorkplaces.get(0), "Изменить");
//                p.waitTextPresent(p.linkEditSpecialty, "Изменить");
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A, "ok");
//                p.editUserAddWorkplaceAPI(Token, "5c123c7c0fb83b44189263bc", "5b7d08aa068d7552d904d8be",
//                        "Врач", "admin", "active", "false","ok");
//                p.editUserSpecialtyAPI(Token, "5c123c7c0fb83b44189263bc", "5c123c7c0fb83b44189263bc",
//                        "", "", "","ok");
//                break;
//
//            case ("Spec"):
//                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
//                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
//                p.click(p.buttonChangeProfile);
//                try {
//                    p.click(p.specialistWorkpaleces);
//                } catch (TimeoutException e) {
//                    System.out.println("Специалист не видит вкладку 'Место работы и специальность' и это хорошо");
//                }
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S, "ok");
//                p.editUserAddWorkplaceAPI(Token, "5c04d3bc7d05c82a53ec8d07", "5b7d08aa068d7552d904d8be",
//                        "Врач", "doctor", "active", "true","ok");
//                p.editUserSpecialtyAPI(Token, "5c04d3bc7d05c82a53ec8d07", "5c123c7c0fb83b44189263bc",
//                        "", "", "","ok");
//
//                break;
//
//            default:
//                throw new Error("Тест пропущен");
//
//        }
//    }
//
//    public void editMyServices(String Role) {
//        switch (Role) {
//            case ("SuperAdmin"):
//                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistServices);
//                p.waitTextPresent(p.buttonChangeServices, "Изменить");
//
//                break;
//
//            case ("Admin"):
//                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
//                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistServices);
//                p.waitTextPresent(p.buttonChangeServices, "Изменить");
//                break;
//
//            case ("Spec"):
//                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
//                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
//                p.click(p.buttonChangeProfile);
//                try {
//                    p.click(p.specialistServices);
//                } catch (TimeoutException e) {
//                    System.out.println("Специалист не видит вкладку 'Услуги' и это хорошо");
//                }
//                break;
//
//            default:
//                throw new Error("Тест пропущен");
//
//        }
//    }
//
//    public void editMyData(String Role) {
//        switch (Role) {
//            case ("SuperAdmin"):
//                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistData);
//                p.waitTextPresent(p.linkEditPhotoProfile, "Изменить");
//                p.waitTextPresent(p.linkEditEducationProfile, "Изменить");
//                p.waitTextPresent(p.linkEditCommunicationMethodsProfile, "Изменить");
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
//                p.editPhotoAPI(Token, "5bf3be3422ee2270923814af", "2002.png","ok");
//                p.editUserEducationAPI(Token, "5bf3be3422ee2270923814af", "Высшее специальное","ok");
//                p.editUserContactsAPI(Token, "5bf3be3422ee2270923814af", "email",
//                        "+ 7 (789) 785-64-54", "Instagram", "Vk", "Whatsapp",
//                        "Viber", "Facebook", "Other2","ok");
//
//                break;
//
//            case ("Admin"):
//                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
//                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistData);
//                p.waitTextPresent(p.linkEditPhotoProfile, "Изменить");
//                p.waitTextPresent(p.linkEditEducationProfile, "Изменить");
//                p.waitTextPresent(p.linkEditCommunicationMethodsProfile, "Изменить");
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A, "ok");
//                p.editPhotoAPI(Token, "5c123c7c0fb83b44189263bc", "doctor16.jpg","ok");
//                p.editUserEducationAPI(Token, "5c123c7c0fb83b44189263bc", "Регалия","ok");
//                p.editUserContactsAPI(Token, "5c123c7c0fb83b44189263bc", "Akulovkjhkhkljp9890@m.ru",
//                        "+ 8 (769) 876-87-68", null, null, null,
//                        null, null, null,"ok");
//
//                break;
//
//            case ("Spec"):
//                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
//                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
//                p.click(p.buttonChangeProfile);
//                p.click(p.specialistData);
//                p.waitTextPresent(p.linkEditPhotoProfile, "Изменить");
//                p.waitTextPresent(p.linkEditEducationProfile, "Изменить");
//                p.waitTextPresent(p.linkEditCommunicationMethodsProfile, "Изменить");
//
//                Token =  p.loginAPI(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S, "ok");
//                p.editPhotoAPI(Token, "5c04d3bc7d05c82a53ec8d07", "DoctorFluconazole.jpg","ok");
//                p.editUserEducationAPI(Token, "5c04d3bc7d05c82a53ec8d07", null,"ok");
//                p.editUserContactsAPI(Token, "5c04d3bc7d05c82a53ec8d07", null,
//                        null, null, null, null, null, null,
//                        null,"ok");
//
//                break;
//
//            default:
//                throw new Error("Тест пропущен");
//
//        }
//    }
}



