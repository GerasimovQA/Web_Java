package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PolicyDeliveryPage extends BasePage {
    QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();

    SelenideElement addPoliceDeliveryButton = $x(".//span[text()='Добавить политику доставки']/..");
    SelenideElement policyTypeActivate = $x(".//label[text()='Тип']/../following-sibling::div");
    SelenideElement policyTypeInput = $x(".//label[text()='Тип']/../following-sibling::div//input");
    SelenideElement queueNameInput = $x(".//label[text()=\"Имя или маска\"]/../..//input");
    SelenideElement queueNameMultyInput = $x(".//label[text()=\"Имя очереди или маска\"]/../..//input");
    SelenideElement interceptReceiveEventCheckBox = $x(".//span[text()=\"Перехватывать событие получения\"]/../..//input/..");
    SelenideElement interceptSendEventCheckBox = $x(".//span[text()=\"Перехватывать событие отправки\"]/../..//input/..");
    SelenideElement interceptRemoveEventCheckBox = $x(".//span[text()=\"Перехватывать событие удаления\"]/../..//input/..");
    SelenideElement interceptFastSenderCheckBox = $x(".//span[text()=\"Перехватывать события быстрых отправителей\"]/../..//input/..");

    public enum receiveEvent {Yes, No}

    public enum sendEvent {Yes, No}

    public enum removeEvent {Yes, No}

    public enum fastSenderEvent {Yes, No}

    @Step
    public void createPoliceDelivery(String moduleName, String managerName, String policyType, String[] queueName, Enum[] interceptCheckBox) {
        if (moduleName.equals("Мультименеджер очередей")) {
            queueManagerMultyPage.goToManager(managerName);
            if (!deliveryPolicyMultyTab.attr("class").contains("active")) click(deliveryPolicyMultyTab);
        } else {
            queueManagerPage = new QueueManagerPage();
            if (!deliveryPolicyTab.attr("class").contains("active")) click(deliveryPolicyTab);
        }


        click(addPoliceDeliveryButton);
        click(policyTypeActivate);
        valAndSelectElement(policyTypeInput, policyType);
        val(queueNameInput, queueName[0]);
        if (interceptCheckBox[0].equals(receiveEvent.Yes)) click(interceptReceiveEventCheckBox);
        if (interceptCheckBox[1].equals(sendEvent.Yes)) click(interceptSendEventCheckBox);
        if (moduleName.equals("Менеджер очередей")) {
            if (interceptCheckBox[2].equals(removeEvent.Yes)) click(interceptRemoveEventCheckBox);
        }
        if (interceptCheckBox[3].equals(fastSenderEvent.Yes)) click(interceptFastSenderCheckBox);
        click(saveButton);
    }
}
