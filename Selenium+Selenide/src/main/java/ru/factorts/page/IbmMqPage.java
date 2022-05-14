package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.*;

public class IbmMqPage extends BasePage {

    BasePage basePage = new BasePage();


    private static String queueDepth = "//div[text()='%s']/following-sibling::div[2]";
    private static String queueName = "//div[text()='%s']";
    private static String chanelStatusLamp = ".//h1[text()='Channels on QM1']/../../../following-sibling::div//div[text()='%s']/following-sibling::div[last()]/span[1]";
    private static String chanelStatusText = ".//h1[text()='Channels on QM1']/../../../following-sibling::div//div[text()='%s']/following-sibling::div[last()]/span[2]";

    public SelenideElement usernameInput = $("#j_username");
    public SelenideElement passwordInput = $("#j_password");
    public SelenideElement loginButton = $x(".//button[text()='Вход в систему']");
    public SelenideElement searchInput = $x(".//h1[text()='Queues on QM1']/../../../following-sibling::div//input");
    public SelenideElement createQueueButton = $x(".//h1[text()='Queues on QM1']/../../../..//div[text()='Create']");
    public SelenideElement queueNameInputForCreate = $x(".//input[@id=\"queuename\"]");
    public SelenideElement confirmCreateQueueButton = $x(".//div[text()='Create a Queue']/../..//div[text()='Create']/../..");
    public SelenideElement cancelSelectQueueButton = $x(".//div[text()='Create a Queue']/../..//div[text()='Cancel']/../..");
    public SelenideElement browseMessagesTab = $x(".//div[text()='Browse messages']");
    public SelenideElement cancelTab = $x(".//button[text()='Cancel']");
    public SelenideElement closeButton = $x(".//div[text()='Close']");
    public SelenideElement refreshQueueInMQ1Icon = $x(".//h1[text()='Queues on QM1']/..//mqc-svg-button[@icon-name=\"restart\"]");
    public SelenideElement depth = $x(".//h1[text()='Queues on QM1']/../../../following-sibling::div//div[@class=\"ag-body-container\"]//div[3]");
    public SelenideElement refreshChannelsListButton = $x(".//h1[text()='Channels on QM1']/following-sibling::div//*[@icon-name=\"restart\"]/*");

    public ElementsCollection bodyMessagesList = $$x(".//div[@translate=\"browseDialog.title\"]/../..//div[@class=\"ag-body-container\"]/div/div[2]");

    public enum Compare {Pass, Fail}

    public enum StatusChannel {Active, Inactive}

    @Step("Autorization in IBM")
    public void autorizationInIBM(String URL) {
        open(URL);
        val(usernameInput, "admin");
        val(passwordInput, "passw0rd");
        click(loginButton);
    }

    @Step("Create Local Queue In IBM")
    public void createLocalQueueInIBM(String name) {
        click(createQueueButton);
        val(queueNameInputForCreate, name);
        basePage.sout(confirmCreateQueueButton.getAttribute("is-disabled"));
        if (confirmCreateQueueButton.getAttribute("is-disabled").equals("false")) {
            click(confirmCreateQueueButton);
        } else {
            click(cancelSelectQueueButton);
        }
    }

    @Step("Save values in queues")
    public int saveValuesDepthQueues(String Queue) {
        val(searchInput, Queue);
        return Integer.parseInt($x(String.format(queueDepth, Queue)).getText());
    }

    @Step("Compare queues before and after")
    public void compareQueuesBeforeAndAfter(int Before, int After, int Difference) {
        sleep(2000);
        basePage.sout(Difference + " = " + After + " - " + Before);
        Assert.assertEquals(Difference, After - Before);
    }

    @Step("Compare Last Message In Queue")
    public void compareLastMessageInQueue(Compare status, String queue, String text) {
        click($x(String.format(queueName, queue)));
        click(browseMessagesTab);

        if (status.equals(Compare.Pass))
            Assert.assertEquals(text, bodyMessagesList.get(bodyMessagesList.size() - 1).getText());
        else Assert.assertNotEquals(text, bodyMessagesList.get(bodyMessagesList.size() - 1).getText());

        click(closeButton);
    }

    @Step("Refresh Queue In MQ1")
    public void refreshQueueWMQ(String queue) {
        val(searchInput, queue);
        click($x(String.format(queueName, queue)));
        click(browseMessagesTab);
        click(closeButton);
        click(refreshQueueInMQ1Icon);
    }

    @Step("Return Num messages In Browse MQ1")
    public int returnNumMessagesInBrowseQueueWMQ(String queue) {
        val(searchInput, queue);
        click($x(String.format(queueName, queue)));
        click(browseMessagesTab);
        int num = bodyMessagesList.size();
        click(closeButton);
        click(cancelTab);
        return num;
    }

    @Step("Return Num messages In Ibm Queue")
    public int depthQueueWMQ(String queue) {
        val(searchInput, queue);
        return Integer.parseInt(depth.getText());
    }

    public void checkStatusChanel(String chanelName, StatusChannel statusChannel) {
        click(refreshChannelsListButton);
        if (statusChannel.equals(StatusChannel.Active)) {
//            Assert.assertEquals("Лампочка должна гореть, но потушена", "circleIcon mq-icon-running", $x(String.format(chanelStatusLamp, chanelName)).getClass().toString());
            Assert.assertEquals("Лампочка должен быть Active, но Inactive", "Active", $x(String.format(chanelStatusLamp, chanelName)).getText());
        } else {
//            Assert.assertEquals("Лампочка должна должна быть потушена, но горит", "circleIcon mq-icon-stopped", $x(String.format(chanelStatusText, chanelName)).getClass().toString());
            Assert.assertEquals("Лампочка должен быть Inactive, но Active", "Inactive", $x(String.format(chanelStatusText, chanelName)).getText());
        }
    }
}


