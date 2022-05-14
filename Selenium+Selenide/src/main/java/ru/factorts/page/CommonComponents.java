package ru.factorts.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CommonComponents extends BasePage {

    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();

    static String ContextMenuElementXpath = "//div[contains(text(),'%s')]",
            moduleActiveStatusXpath = "//td[text()='%s']/following-sibling::td[1]//span[text()]",
            moduleStateXpath = "//td[text()='%s']/following-sibling::td[2]//span[text()]",
            moduleStopXpath = "//td[text()='%s']/..//span[text()=\"Остановить\"]/..",
            moduleTurnOffXpath = "//td[text()='%s']/..//span[text()=\"Отключить\"]/..",
            moduleStartXpath = "//td[text()='%s']/..//span[text()=\"Запустить\"]/..",
            moduleRestartXpath = "//td[text()='%s']/..//span[text()=\"Перезапуск\"]/..",
            selectOption = "//div[@class='Select-option' and contains(text(),'%s')]";

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";

    public SelenideElement reloadButton = $x("//span[text()='Перезапустить FESB']/.."),
            closeNotificationButton = $x(".//a[text()='Close Notification']"),
            closeNotificationBody = $x(".//div[@class=\"gritter-without-image\"]"),
            sopsSavedMessage = $x(".//*[text()='Сохранено']"),
            saveButton = $$x(".//button[@type='submit']").find(visible);

    void closeNotificationIfExist() {
        if (closeNotificationBody.exists()) {
            closeNotificationBody.hover();
//            closeNotificationButton.shouldBe(visible).click();
            click(closeNotificationButton);
            closeNotificationBody.waitWhile(exist, 2000);
            basePage.sout("Error message closed!!!!!!!!!!!!!!");
        }
    }

    void closeNotification() {
        if ($$x(".//div[@class=\"gritter-without-image\"]").size() == 1) {
            elementShouldBeVisible(closeNotificationBody);
            closeNotificationBody.hover();
            click(closeNotificationButton);
            closeNotificationBody.waitWhile(exist, 2000);
        } else if ($$x(".//div[@class=\"gritter-without-image\"]").size() > 1) {
            click($x(".//*[contains(text(), \"Закрыть все\")]"));
            $x(".//*[contains(text(), \"Закрыть все\")]").waitWhile(exist, 2000);
        }
    }

    /**
     * Method for avoid errors with same names when searching
     *
     * @param firstName
     * @return
     */
    @Step("Creation individual name - {0} for avoid errors with same names when searching")
    public String createIndividualName(String firstName) {
        return firstName + +ThreadLocalRandom.current().nextInt(100000, 10000000);
    }

    /**
     * Clear element
     */
    @Step("Clear of text {0} element")
    public void clearOfText(SelenideElement element) {
        element.clear();
    }

    @Step("Execution HTTP POST request to {0}")
    public String[] httpRequestPost(String url, String body) throws Exception {

        String[] resultArray = new String[2];

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/xml");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(body);
        wr.flush();
        wr.close();

        resultArray[0] = ((Integer) con.getResponseCode()).toString();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        resultArray[1] = response.toString();
        basePage.sout("Респонс: " + response.toString());
        return resultArray;
    }

    /**
     * Check equality
     */
    @Step("Check equality two string values")
    public void checkEquality(String firstValue, String valueEqualsToFirst) {
        if (!firstValue.equals(valueEqualsToFirst))
            throw new AssertionError();
    }

    /**
     * Check equality
     */
    @Step("Check equality four string values")
    public void checkEquality(String firstValue, String valueEqualsToFirst, String secondValue, String valueEqualsToSecond) {
        compareStringAndString(firstValue, valueEqualsToFirst);
        compareStringAndString(secondValue, valueEqualsToSecond);
    }

    /**
     * Sleep
     */
    @Step("Sleep {0}")
    public void sleepTime(Integer msec) {
        sleep(msec);
    }

    /**
     * Check equality
     */
    @Step("Check equality that two strings equals and third strings contains fours")
    public void checkEqualityAndContains(String firstValue, String valueEqualsToFirst, String secondValue, String valueEqualsToSecond) {
        if (!firstValue.equals(valueEqualsToFirst) || !secondValue.contains(valueEqualsToSecond))
            throw new AssertionError();
    }

    /**
     * Check file existing
     */
    @Step("Check file existing in {0}")
    public void checkFileExisting(File file) {
        if (!file.exists())
            throw new AssertionError("This file does not exist");
    }

    /**
     * Zip file should has these files
     */
    @Step("Zip file should has these files")
    public static void zipShouldHas(File myFile, String[] files) {
        ArrayList<String> zipList = new ArrayList<String>();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(myFile))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName();
                zipList.add(name);
                System.out.println(name);

            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        for (String file : files) {
            if (!zipList.contains(file))
                throw new AssertionError(String.format("This file:(%s) does not exist", file));
        }

    }

    /**
     * Matches some substring in string
     */
    @Step("Matches some substring in string")
    public String findString(String line, String pattern) {

//        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><S:Header><wsa:MessageID>ID:gregPC-38674-1523978822592-10:2:1:1:1</wsa:MessageID><wsa:To>localmq://EXAMPLE.XSL.OUT</wsa:To></S:Header><S:Body><AddPreson><name>Vova</name></AddPreson></S:Body></S:Envelope>";
//        String pattern = "ID:.*?<";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        m.find();
        return m.group(0).replaceFirst(".$", "");
    }

    @Step("Matches some substring in string")
    public void reloadConfiguration() {
        click(restartFesbButton.find(visible));
        loginPage.loginInput.waitUntil(visible, 300000);
    }

}
