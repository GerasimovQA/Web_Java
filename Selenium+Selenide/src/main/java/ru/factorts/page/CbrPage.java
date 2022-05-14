package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import utils.ConfigProperties;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CbrPage extends BasePage {
    String managerName = "QM";

    SOPSPage sopsPage;
    QueuesPage queuesPage;
    //    QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    BasePage basePage = new BasePage();
    CreationSOPSPage creationSOPSPage;
    MessagePage messagePage = new MessagePage();
    CommonComponents commonComponents = new CommonComponents();


    public SelenideElement exitToRouteTableTab = $x(".//span[text()='Выход в таблицу маршрутов']");
    public SelenideElement ignoreRepeatedRoutesCheckBox = $x(".//span[text()='Игнорировать повторные маршруты']/preceding-sibling::span");


    @Step("Login to FESB")
    public void loginToFESB() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
    }

    @Step("Ignore repeated routes")
    public void ignoreRepeatedRoutes(String Type, String domainName, String sopsName) {
        sopsPage = new SOPSPage();
        sopsPage.goToSops(domainName, sopsName);
        click(exitToRouteTableTab);

        switch (Type) {
            case ("НЕ игнорировать повторные маршруты"):
                if ((ignoreRepeatedRoutesCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked"))) {
                    click(ignoreRepeatedRoutesCheckBox);
                }
                break;
            case ("Игнорировать повторные маршруты"):
                if ((!ignoreRepeatedRoutesCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked"))) {
                    click(ignoreRepeatedRoutesCheckBox);
                }
                break;
            default:
                throw new AssertionError("Пропущен выбор чек-бокса \"Игнорировать повторные маршруты\"");
        }

        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.saveIcon);
        click(creationSOPSPage.saveCommentButton);
        commonComponents.sopsSavedMessage.waitUntil(visible, 10000);
    }

    @Step("Send the saved message to IBM")
    public void sendTheSavedMessageToIbmAndCheckQueue() {
//        queueManagerPage = new QueueManagerPage();
//        click(basePage.queuelistTab);
//        queuesPage = new QueuesPage();
        queueManagerMultyPage.searchQueue(managerName,"TO.IBM");
        int SumSentMessage = Integer.parseInt(queueManagerMultyPage.afterSearchQueueSentToQueue.getText());
        int SumTakenMessage = Integer.parseInt(queueManagerMultyPage.afterSearchQueueTakenFromQueue.getText());
        queueManagerMultyPage.queueCheckAllParameters(managerName, "TO.IBM", "0", "1",  Integer.toString(SumSentMessage),  Integer.toString(SumSentMessage), "Локальная", "", "");

//        queueManagerPage.queueCheckAllParameters("TO.IBM", "0", "1", Integer.toString(SumSentMessage), Integer.toString(SumSentMessage),
//                "Локальная", "");
        queueManagerMultyPage.sendNSavedMessagesWithoutCheck(managerName,"TO.IBM", 1);
        queueManagerMultyPage.queueCheckAllParameters(managerName, "TO.IBM", "0", "1",  Integer.toString(SumSentMessage+1),  Integer.toString(SumSentMessage+1), "Локальная", "", "");

//        queueManagerPage.queueCheckAllParameters("TO.IBM", "0", "1",
//                Integer.toString(SumSentMessage + 1), Integer.toString(SumSentMessage + 1), "Локальная", "");
    }

    @Step("Send the saved message to IBM")
    public void send_100000_MessageToIbmAndCheckQueue(String queueName, int numMessage) {
//        queueManagerPage = new QueueManagerPage();
//        click(basePage.queuelistTab);
//        queuesPage = new QueuesPage();
        queueManagerMultyPage.searchQueue(managerName,queueName);
        int SumSentMessage = Integer.parseInt(queueManagerMultyPage.afterSearchQueueSentToQueue.getText());
        int SumTakenMessage = Integer.parseInt(queueManagerMultyPage.afterSearchQueueTakenFromQueue.getText());
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueName, "0", "20",  Integer.toString(SumSentMessage),  Integer.toString(SumSentMessage), "Локальная", "", "");

//        queueManagerPage.queueCheckAllParameters(queueName, "0", "20",
//                Integer.toString(SumSentMessage), Integer.toString(SumTakenMessage), "Локальная", "");

        contextClick(queueManagerMultyPage.afterSearchQueueName);
        click(queueManagerMultyPage.contextSendMessage);
        messagePage.sendMessageInQueueNTimesAtOnceWithoutText(queueName, numMessage);

//        queueManagerPage = new QueueManagerPage();
//        click(queueManagerPage.queuesList);
        queueManagerMultyPage.searchQueue(managerName,queueName);
//        queuesPage.search(queueName);
        basePage.autoUpdateOn();
        queueManagerMultyPage.afterSearchQueueTakenFromQueue.waitUntil(Condition.exactText(Integer.toString(SumTakenMessage + numMessage)), 80000);
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueName, "0", "20",   Integer.toString(SumSentMessage + numMessage),  Integer.toString(SumSentMessage + numMessage), "Локальная", "", "");
//        queueManagerPage.queueCheckAllParameters(queueName, "0", "20",
//                Integer.toString(SumSentMessage + numMessage), Integer.toString(SumSentMessage + numMessage), "Локальная", "");
    }
}


