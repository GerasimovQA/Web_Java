package global;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class GlobalPage {

    private static String resourcesImagesDir = ConfigProperties.getTestProperty("Screen");
    public static String expectedDir = resourcesImagesDir + ConfigProperties.getTestProperty("expected");
    public static String actualDir = resourcesImagesDir + ConfigProperties.getTestProperty("actual");
    public static String diffDir = resourcesImagesDir + ConfigProperties.getTestProperty("diff");
    public static String ScreenshotAshot = "Ashot";


    public static String LoginAUT = "ioanner";
    public static String PasswordAUT = "12345678";


    @FindBy(css = ".el-input__inner[placeholder=\"Введите логин, email или телефон\"]")
    public WebElement authInputLogin;

    //    Поле ввода пароля с проверкой на текст "Введите пароль"
    @FindBy(css = ".el-input__inner[placeholder=\"Введите пароль\"]")
    public WebElement authInputPassword;

    // Кнопка Войти
    @FindBy(xpath = "//button[contains (text(), \"Войти\")]")
    public WebElement authButtonEnter;

    // Проверочный блок Главная страница - Информация о пользователе
    @FindBy(css = ".user-info__name")
    public WebElement userinfoname;

    //ГЛАВНАЯ СТРАНИЦА
    //Пункт меню Сотрудники
    @FindBy(xpath = "//p[contains (text(), \"Сотрудники\")]")
    public WebElement menuWorkers;

    //Подпункт меню Создать сотрудника
    @FindBy(xpath = "//span[contains (text(), \"Создать сотрудника\")]")
    public WebElement menuCreateWorker;

    //   Кнопка список сотрудников с проверкой на текст "Список сотрудников"
    @FindBy(xpath = "//Span[text()= \"Список сотрудников\"]")
    public WebElement buttonUserList;

    //   Кнопка загрузить еще с проверкой на текст "Загрузить еще"
    @FindBy(xpath = "//span[contains (text(), \"Загрузить еще\")]")
    public WebElement buttonLoadMore;

    //   Выпадающий список "Количество сотрудников на странице"
    @FindBy(css = ".el-input__inner[placeholder=\"Выбрать\"]")
    public WebElement sumWorker;

    //   Выпадающий пункт "50 на странице" в списке "Количество сотрудников на странице"
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/ul/li[2]/span[contains (text(), \"50 на странице\")]")
    public WebElement workers50;

    //   Список фамилий сотрудников
    @FindBy(xpath = "//tr/td[4]/div")
    public WebElement listSecondName;

    //   Выпадающий список Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//html//div[3]/div[2]/div[3]")
    public WebElement listActionProfile;

    //  Пункт ВЫЙТИ  в выпадающем списке Профиль/Редактирование/Выход в правом верхнем углу
    @FindBy(xpath = "//div[2]/div[3]//span[contains (text(), \"Выйти\")]")
    public WebElement listActionProfileExit;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(xpath = "//h2[contains (text(), \"Профиль обновлен\")]")
    public WebElement helperProfileChange;

    //  Всплывающая подсказка "Профиль изменен"
    @FindBy(css = ".el-notification__closeBtn.el-icon-close")
    public WebElement helperProfileChangeClose;

    //Иконка для загрузки фото при создании и редактировании пользователя
    @FindBy(xpath = ".//*[@id='avatarEditorCanvas']/following-sibling::input")
    public WebElement uploadPhoto;

    //    Кнопка РЕДАКТИРОВАТЬ ПРОФИЛЬ
    @FindBy(xpath = "//div[2]/a/span[contains (text(), \"Редактировать профиль\")]")
    public WebElement buttonChangeProfile;

    //    Имя
    @FindBy(css = ".edit-user-block__full-name > span:nth-child(2)")
    public WebElement firstNameProfile;

    //    Ссылка редактирования логин/email/телефон
    @FindBy(xpath = "//div[contains (text(), \"Данные для входа\")]/following-sibling::div[4]")
    public WebElement linkEditAuthProfile;

    //    Поле ввода ЛОГИН
    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
    public WebElement inputLogin;

    //    Поле ввода EMAIL
    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
    public WebElement inputEmail;

    //    Поле ввода ТЕЛЕФОН
    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
    public WebElement inputPhone;

    //Глобальные объекты
    //    Кнопка СОХРАНИТЬ все кроме пароля и статуса
    @FindBy(xpath = "//span[contains (text(), \"Сохранить\")]")
    public WebElement buttonSaveProfile;

    //    Кнопка ОТМЕНА всего кроме пароля и статуса
    @FindBy(xpath = "//button/span[contains (text(), \"Отмена\")]")
    public WebElement buttonCancelProfile;

    //    Вкладка "Место работы и специальность"
    @FindBy(xpath = "//div[contains (text(), \"Место работы и специальность\")]")
    public WebElement specialistWorkpaleces;

    //    Вкладка "Услуги"
    @FindBy(xpath = "//div[contains (text(), \"Услуги\")]")
    public WebElement specialistServices;

    //    Вкладка "Данные специалиста"
    @FindBy(xpath = "//div[contains (text(), \"Данные специалиста\")]")
    public WebElement specialistData;

    //    Логин
    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
    public WebElement loginProfile;


    public WebDriver driver;


    public void waitE_ClickableAndClick(WebElement element) {
        sleep(500);
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void waitE_ClickableAndSendKeys(WebElement element, String text) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        element.clear();
        iWait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }

    public void waitE_visibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
        sleep(500);
    }

    public void waitE_invisibilityOf(WebElement element) {
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        try {

            iWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep(500);
    }

    public String waitE_visibilityOfAndGettext(WebElement element) {
        sleep(500);
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getText();
        return text;
    }

    public String waitE_TextPresent(WebElement element, String phrase) {
        sleep(500);
        WebDriverWait iWait = new WebDriverWait(driver, 15);
        iWait.until(ExpectedConditions.textToBePresentInElement(element, phrase));
        String text = element.getText();
        System.out.println(element.getText() + " ====== " + phrase);
        return text;
    }

    public void auth(String Login, String Password) {
        waitE_ClickableAndSendKeys(authInputLogin, Login);
        waitE_ClickableAndSendKeys(authInputPassword, Password);
        waitE_ClickableAndClick(authButtonEnter);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void moveMouse(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void screenFailedTest(String Name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
//        String dest = diffDir + diffDir + Name + ".png";
        String delimeter = "\\|"; // Разделитель
        String[] subStr = Name.split(delimeter);
        File destination = new File(resourcesImagesDir + "/" + subStr[0] + ".png");
        System.out.println(resourcesImagesDir + "/" + subStr[0] + ".png");

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Скриншот не сохранился");
        }
    }



    public void scrollWithOffset(WebElement webElement, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        js.executeScript(code, webElement, x, y);
    }

    public void scrollUpPage() {
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_UP);
    }


    public void scrollDownPage() {
        driver.findElement(By.xpath("//html/body")).sendKeys(Keys.PAGE_DOWN);
    }


    public void captureScreenshot(String Name) {
        screenFailedTest(Name);
    }

    public void moveToProfileChange(String SecondName) {
        waitE_ClickableAndClick(menuWorkers);
        waitE_ClickableAndClick(buttonUserList);
        waitE_visibilityOf(listSecondName);
        waitE_ClickableAndClick(sumWorker);
        waitE_ClickableAndClick(workers50);
        WebElement findToSecondName = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
        waitE_ClickableAndClick(buttonChangeProfile);
        waitE_visibilityOf(firstNameProfile);
    }

    public void moveToProfile(String SecondName) {
        waitE_ClickableAndClick(menuWorkers);
        waitE_ClickableAndClick(buttonUserList);
        waitE_visibilityOf(listSecondName);
        waitE_ClickableAndClick(sumWorker);
        waitE_ClickableAndClick(workers50);
        WebElement findToSecondName = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
        scrollWithOffset(findToSecondName, 0, 30);
        waitE_ClickableAndClick(findToSecondName);
    }

    public void writeCookiesToFile(WebDriver driver) {
        System.out.println(driver.manage().getCookies());
        File file = new File("browser.dat");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write((cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - "+ e.getLocalizedMessage());
        }
    }

    public Cookie readCookiesFromFile() {
        Cookie cookie = new Cookie("","");
        try {
            File file = new File("browser.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String date= str.nextToken();
                    if (!(date).equals("null")) {
                        expiry = new Date(System.currentTimeMillis()*2);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - "+ ex.getLocalizedMessage());
        }
        return cookie;
    }


}