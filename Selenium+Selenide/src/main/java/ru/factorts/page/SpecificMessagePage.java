package ru.factorts.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SpecificMessagePage {


    BasePage basePage = new BasePage();

    SelenideElement returnInQueueButton = $x(".//span[text()='Вернуться в очередь']/..");
    SelenideElement returnInQueueMultyButton = $x(".//span[@class=\"anticon anticon-arrow-left\"]/..");
    SelenideElement downloadButton = $x(".//a[text()='Скачать']");
    SelenideElement copyButton = $x(".//button[text()='Скопировать в...']");
    SelenideElement copyConfirmButton = $x(".//button[text()='Копировать']");
    SelenideElement nameQueueForCopyMessageActivate = $x(".//label[text()='Имя очереди']/../following-sibling::div");
    SelenideElement nameQueueForCopyMessageInput = $x(".//label[text()='Имя очереди']/../following-sibling::div//input");
    SelenideElement moveButton = $x(".//button[text()='Переместить в...']");
    SelenideElement deleteButton = $x(".//button[text()='Удалить']");
    SelenideElement dispatchTime = $x(".//span[text()='Время отправки']/../../following-sibling::div//input");
    SelenideElement idMessage = $x(".//span[text()='ID Сообщения']/../../following-sibling::div//input");
    SelenideElement corellationIdMessage = $x(".//span[text()='ID корреляции']/../../following-sibling::div//input");
    SelenideElement groupIdMessage = $x(".//span[text()='ID группы']/../../following-sibling::div//input");
    SelenideElement persistentMessage = $x(".//span[text()='Стойкое']/../../following-sibling::div//input");
    SelenideElement replyToJmsRecipientMessage = $x(".//span[text()='Ответить в']/../../following-sibling::div//input");
    SelenideElement numberInGroupMessage = $x(".//span[text()='Номер сообщения в группе']/../../following-sibling::div//input");
    SelenideElement validityMessage = $x(".//span[text()='Время жизни']/../../following-sibling::div//input");
    SelenideElement priorityMessage = $x(".//span[text()='Приоритет']/../../following-sibling::div//input");
    SelenideElement redirectedMessage = $x(".//span[text()='Переотправленное']/../../following-sibling::div//input");
    SelenideElement classMessage = $x(".//label[text()='Класс сообщения']/../following-sibling::div//input");
    SelenideElement sizeFullMessage = $x(".//label[text()='Размер сообщения']/../following-sibling::div//input");
    SelenideElement sizeBodyMessage = $x(".//label[text()='Размер тела сообщения']/../following-sibling::div//input");
    SelenideElement nameFileMessage = $x(".//label[text()='Имя файла']/../following-sibling::div//input");
    SelenideElement typeMessage = $x(".//span[text()='Тип']/../../following-sibling::div//input");
    SelenideElement messageBody = $x(".//div[text()='Тело сообщения']/following-sibling::div//textarea");
    SelenideElement emptyMessageBody = $x(".//div[text()='Тело сообщения']/following-sibling::div//span");

    public SpecificMessagePage() {
        returnInQueueButton.shouldBe(visible);
    }

    public SpecificMessagePage(String empty) {
    }


    @Step
    public void checkParametersMessageInTableAndInfo(SpecificQueuePage object, String valueDispatchTime, String valueIdMessageInTable, String valueIdCorrelationMessageInTable, String valueGroupIdMessage, String valuePersistenceMessageInTable, String valueReplyToJmsRecipientMessage, String valueNumberInGroupMessage, String valueValidityMessage, String valuePriorityMessageInTable, String valueRedirectedMessage, String valueClassMessage, String valueSizeFullMessage, String valueSizeBodyMessage, String valueNameFileMessage, String valuetypeMessage, String valueMessageBody) {

        basePage.sout("Проверка: " + valueDispatchTime + " = " + dispatchTime.getValue());
//        Assert.assertEquals(valueDispatchTime, dispatchTime.getValue().split("\\.")[0]);
        Assert.assertEquals(valueDispatchTime, dispatchTime.getValue());
        basePage.sout("Проверка: " + valueIdMessageInTable + " = " + idMessage.getValue());
        Assert.assertEquals(valueIdMessageInTable, idMessage.getValue());
        basePage.sout("Проверка: " + valueIdCorrelationMessageInTable + " = " + corellationIdMessage.getValue());
        Assert.assertEquals(valueIdCorrelationMessageInTable, corellationIdMessage.getValue());
        basePage.sout("Проверка: " + valueGroupIdMessage + " = " + groupIdMessage.getValue());
        Assert.assertEquals(valueGroupIdMessage, groupIdMessage.getValue());

        basePage.sout("Проверка: " + valuePersistenceMessageInTable + " = " + persistentMessage.getValue());
        Assert.assertEquals(valuePersistenceMessageInTable, persistentMessage.getValue());

        if (valueReplyToJmsRecipientMessage.equals("")) {
            basePage.sout("Проверка: " + valueReplyToJmsRecipientMessage + " = " + replyToJmsRecipientMessage.getValue());
            Assert.assertEquals(valueReplyToJmsRecipientMessage, replyToJmsRecipientMessage.getValue());
        } else {
            basePage.sout("Проверка: queue://" + valueReplyToJmsRecipientMessage + " = " + replyToJmsRecipientMessage.getValue());
            Assert.assertEquals("queue://" + valueReplyToJmsRecipientMessage, replyToJmsRecipientMessage.getValue());
        }

        basePage.sout("Проверка: " + valueNumberInGroupMessage + " = " + numberInGroupMessage.getValue());
        Assert.assertEquals(valueNumberInGroupMessage, numberInGroupMessage.getValue());

        if (valueValidityMessage.equals("")) {
            basePage.sout(valueValidityMessage);
            basePage.sout("Проверка: " + valueValidityMessage + " = " + "");
            Assert.assertEquals(valueValidityMessage, validityMessage.getValue());
        } else {
            basePage.sout(object.valueDispatchTimeMessageInTable);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS").withLocale(Locale.US);
            LocalDateTime parsedDateDispatche = LocalDateTime.parse(object.valueDispatchTimeMessageInTable, formatter);
            basePage.sout(parsedDateDispatche.toString());
            basePage.sout("Проверка: " + basePage.addTimeForDateAndTime(parsedDateDispatche, valueValidityMessage) + " GMT+0000 (Coordinated Universal Time) = " + validityMessage.getValue());
            Assert.assertEquals(basePage.addTimeForDateAndTime(parsedDateDispatche, valueValidityMessage) + " GMT+0000 (Coordinated Universal Time)", validityMessage.getValue());
        }

        basePage.sout("Проверка: " + valuePriorityMessageInTable + " = " + priorityMessage.getValue());
        Assert.assertEquals(valuePriorityMessageInTable, priorityMessage.getValue());

        basePage.sout("Проверка: " + valueRedirectedMessage + " = " + redirectedMessage.getValue());
        Assert.assertEquals(valueRedirectedMessage, redirectedMessage.getValue());

        basePage.sout("Проверка: " + valueClassMessage + " = " + classMessage.getValue());
        Assert.assertEquals(valueClassMessage, classMessage.getValue());
        basePage.sout("Проверка: " + valueSizeFullMessage + " = " + sizeFullMessage.getValue());
        Assert.assertEquals(valueSizeFullMessage, sizeFullMessage.getValue());
        basePage.sout("Проверка: " + valueSizeBodyMessage + " = " + sizeBodyMessage.getValue());
        Assert.assertEquals(valueSizeBodyMessage, sizeBodyMessage.getValue());
        basePage.sout("Проверка: " + valueNameFileMessage + " = " + nameFileMessage.getValue());
        Assert.assertEquals(valueNameFileMessage, nameFileMessage.getValue());
        basePage.sout("Проверка: " + valuetypeMessage + " = " + typeMessage.getValue());
        Assert.assertEquals(valuetypeMessage, typeMessage.getValue());
        basePage.sout("Проверка: " + valueMessageBody + " = " + messageBody.getValue());
        Assert.assertEquals(valueMessageBody, messageBody.getText());
    }

    @Step
    public void checkParametersMessagePersistanceAndBody(String valuePersistenceMessageInTable, String valueMessageBody) {
        basePage.compareStringAndElementValue(valuePersistenceMessageInTable, persistentMessage);
        basePage.compareStringAndElementValue(valueMessageBody, messageBody);
    }

    public String getIdMessage() {
        String getIdMessage = idMessage.getValue();
        System.out.println(getIdMessage);
        return getIdMessage;
    }
}
