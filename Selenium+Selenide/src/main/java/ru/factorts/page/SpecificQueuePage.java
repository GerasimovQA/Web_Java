package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SpecificQueuePage extends BasePage {
    String valueIdMessageInTable = null;
    String valueIdCorrelationMessageInTable = null;
    String valuePersistenceMessageInTable = null;
    String valuePriorityMessageInTable = null;
    String valueRedirectedMessage = null;
    String valueIntroducedSizeMessage = null;
    String valueTypeMessage = null;
    String valueRepeatDeliveryMessageInTable = null;
    String valueDispatchTimeMessageInTable = null;
    String valueSizeMessageInTable = null;
    String valueTypeMessageInTable = null;
    String valueFileNameMessageInTable = null;

    //Table
    SelenideElement propertiesButton = $x(".//*[text()='Свойства очереди']"),
            valueNameIhInPropertiesQueue = $x(".//th/span[text()='Имя']/../following-sibling::td"),
            valueDepthInPropertiesQueue = $x(".//th/span[text()='Глубина очереди']/../following-sibling::td"),
            valueCustomersInPropertiesQueue = $x(".//th/span[text()='Количество потребителей']/../following-sibling::td"),
            valueSentInPropertiesQueue = $x(".//th/span[text()='Отправлено в очередь']/../following-sibling::td"),
            valueTakenInPropertiesQueue = $x(".//th/span[text()='Изъято из очереди']/../following-sibling::td"),
            valueTypeInPropertiesQueue = $x(".//th/span[text()='Тип очереди']/../following-sibling::td"),
            valueCanalInPropertiesQueue = $x(".//th/span[text()='Канал / Назначение']/../following-sibling::td"),
            valueCanalInPropertiesQueueMulty = $x(".//th/span[text()='Канал']/../following-sibling::td"),
            valueRedirectionsInPropertiesQueueMulty = $x(".//th/span[text()='Перенаправления']/../following-sibling::td"),
            valueMinInPropertiesQueue = $x(".//th/span[text()='Минимальный размер сообщения']/../following-sibling::td"),
            valueMaxInPropertiesQueue = $x(".//th/span[text()='Максимальный размер сообщения']/../following-sibling::td"),
            valueAllInPropertiesQueue = $x(".//th/span[text()='Общий размер сообщений']/../following-sibling::td"),
            valueDestinationQueue = $x(".//th/span[text()='Очередь назначения']/../following-sibling::td"),
            valueDestinationQueueMulty = $x(".//th/span[text()='Очереди назначения']/../following-sibling::td"),
            valueDestinationTopicMulty = $x(".//th/span[text()='Разделы назначения']/../following-sibling::td"),

    propertiesTopicButton = $x(".//*[text()='Свойства раздела']"),
            valueNameIhInPropertiesTopic = $x(".//th/span[text()='Имя']/../following-sibling::td"),
            valueTypeInPropertiesTopic = $x(".//th/span[text()='Тип раздела']/../following-sibling::td"),
            valueCustomersInPropertiesTopic = $x(".//th/span[text()='Количество потребителей']/../following-sibling::td"),
            valueSentInPropertiesTopic = $x(".//th/span[text()='Отправлено в раздел']/../following-sibling::td"),
            valueTakenInPropertiesTopic = $x(".//th/span[text()='Изъято из раздела']/../following-sibling::td"),
            valueCanalInPropertiesTopic = $x(".//th/span[text()='Канал']/../following-sibling::td"),
            valueRedirectionsInPropertiesTopic = $x(".//th/span[text()='Перенаправления']/../following-sibling::td"),
            valueMinInPropertiesTopic = $x(".//th/span[text()='Минимальный размер сообщения']/../following-sibling::td"),
            valueMaxInPropertiesTopic = $x(".//th/span[text()='Максимальный размер сообщения']/../following-sibling::td"),
            valueAllInPropertiesTopic = $x(".//th/span[text()='Общий размер сообщений']/../following-sibling::td"),


    messagesButton = $x(".//*[text()='Сообщения']"),
            selectMessagesCheckBoxInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[1]"),
            idMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]"),
            idCorrelationMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[3]"),
            dispatchTimeMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[4]"),
            persistenceMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[5]"),
            priorityMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[6]"),
            repeatDeliveryMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[7]"),
            sizeMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[8]"),
            typeMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[9]"),
            fileNameMessageInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[10]"),

    activeCustomersButton = $x(".//*[text()='Активные потребители']"),
            idConnectionInTable = $x("(//tr[@class]/td)[1]"),
            idCustomerInTable = $x("(//tr[@class]/td)[2]"),
            idSessionInTable = $x("(//tr[@class]/td)[3]"),
            selectorInTable = $x("(//tr[@class]/td)[4]"),
            placedInTable = $x("(//tr[@class]/td)[5]"),
            takenInTable = $x("(//tr[@class]/td)[6]"),
            routedInTable = $x("(//tr[@class]/td)[7]"),
            routedQueueInTable = $x("(//tr[@class]/td)[8]"),
            cachingAndMaximumWaitingInTable = $x("(//tr[@class]/td)[9]"),
            exceptionalRetroactiveInTable = $x("(//tr[@class]/td)[10]"),

    activeProducersButton = $x(".//*[text()='Активные производители']");

    ElementsCollection tableRowsIDCollon = $$x(".//a[contains(text(),'ID:')]");
    ElementsCollection fileName = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[10]");
    ElementsCollection fileID = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]/a");
    ElementsCollection dispatchTimeMessageInTableList = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[4]");
    ElementsCollection fileCheckBox = $$x(".//tbody[@class=\"ant-table-tbody\"]//span[@class=\"ant-checkbox\"]");
    ElementsCollection priorityMessageListInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[6]");

    //Context menu
    SelenideElement deleteMessage = $$x(".//div[text()=' Удалить']").find(visible),
            openMessage = $$x(".//div[text()=' Открыть']").find(visible),
            downloadMessage = $$x(".//div[text()=' Скачать']").find(visible),
            moveMessage = $$x(".//div[text()=' Переместить']").find(visible),
            copyMessage = $$x(".//div[text()=' Скопировать']").find(visible),

    allFilesCheckBox = $x(".//span[text()='ID Сообщения']/ancestor::thead//th//span[@class=\"ant-checkbox\"]"),
            sentMessageButton = $x(".//span[text()='Отправить сообщение']/.."),

    //Confirmation menu
    deleteMessageButton = $x(".//div[contains(text(),'сообщен')]/../..//span[text()='Удалить']/..");

    //Options
    SelenideElement returnInQueuesListPage = $$x(".//a[@href=\"#/queue-manager/queues\"]").find(visible);
    SelenideElement returnInQueuesListPageMultyButton = $x(".//span[@class=\"anticon anticon-arrow-left\"]/..");

    //Action menu
    SelenideElement actionButton = $x(".//span[text()='Действия ']/..");
    SelenideElement eventInterceptorButton = $x(".//span[text()='События']");
    SelenideElement editButton = $x(".//span[text()='Редактировать']");
    SelenideElement importInput = $x(".//div[text()='Импорт']/..//input");
    SelenideElement exportButton = $x(".//span/following-sibling::span[text()='Экспорт']");
    SelenideElement cleanOutButton = $x(".//span[text()='Очистить']");
    SelenideElement deleteButton = $x(".//span[text()='Удалить']");

    SelenideElement addEventInterceptorButton = $x(".//button//span[text()='Добавить перехват событий']/..");
    SelenideElement nameQueueInEditForm = $x(".//input[@name='name']");
    SelenideElement confirmCleanOutButton = $$x(".//div[text()=\"Подтверждение очистки\"]/../..//span[text()='Очистить']/..").find(visible);
    SelenideElement confirmDeleteButtonInInfo = $$x(".//div[text()='Подтверждение удаления']/../..//span[text()='Удалить']/..").find(visible);

    public SpecificQueuePage() {
        returnInQueuesListPage.shouldBe(visible);
    }

    public SpecificQueuePage(String empty) {
    }

    @Step("Deleteng {0} messages")
    public void deleteNMessagesWhithoutSave(Integer messagesNumber) {
        contextClick(tableRowsIDCollon.get(0));
        click(deleteMessage);
    }

    @Step()
    public void successDeleteNMessages(Integer messagesNumber) {
        for (int i = 0; i < messagesNumber; i++) {
            deleteNMessagesWhithoutSave(messagesNumber);
            click(deleteMessageButton);
        }
    }

    @Step()
    public void cancelDeleteNMessages(Integer messagesNumber) {
        for (int i = 0; i < messagesNumber; i++) {
            deleteNMessagesWhithoutSave(messagesNumber);
            click(closeWindowIcon);
        }
    }

    @Step("Open first message")
    public void openFirstMessage() {
        contextClick(tableRowsIDCollon.get(0));
        click(openMessage);
    }

    @Step("Open first message")
    public void openMessageWithNumber(int messageNamber) {
        contextClick(tableRowsIDCollon.get(messageNamber));
        click(openMessage);
    }

    @Step
    public void saveParametersMessageInTable() {
        valueIdMessageInTable = idMessageInTable.getText();
        sout("ID ообщения - " + valueIdMessageInTable);
        valueIdCorrelationMessageInTable = idCorrelationMessageInTable.getText();
        sout("ID корреляции - " + valueIdCorrelationMessageInTable);
        valuePersistenceMessageInTable = persistenceMessageInTable.getText();
        sout("Стойкость - " + valuePersistenceMessageInTable);
        valuePriorityMessageInTable = priorityMessageInTable.getText();
        sout("Приоритет - " + valuePriorityMessageInTable);
        valueRedirectedMessage = repeatDeliveryMessageInTable.getText();
        sout("Переотправляемое - " + valueRedirectedMessage);
        valueRepeatDeliveryMessageInTable = repeatDeliveryMessageInTable.getText();
        sout("Повторная доставка - " + valueRepeatDeliveryMessageInTable);
        valueTypeMessage = typeMessageInTable.getText();
        sout("Тип сообщения - " + valueTypeMessage);
        valueDispatchTimeMessageInTable = dispatchTimeMessageInTable.getText();
        sout("Время отправки - " + valueDispatchTimeMessageInTable);
        valueSizeMessageInTable = sizeMessageInTable.getText();
        sout("Размер сообщения - " + valueSizeMessageInTable);
        valueTypeMessageInTable = typeMessageInTable.getText();
        sout("Тип сообщения - " + valueTypeMessageInTable);
        valueFileNameMessageInTable = fileNameMessageInTable.getText();
        sout("Имя файла - " + valueTypeMessageInTable);
    }

    @Step
    public void messageCheckAllParameters(String introducedIdCorrelationMessageInTable, MessagePage.Persistant persistant,
                                          String introducedPriorityMessageInTable, String introducedRedirectedMessage,
                                          String introducedSizeMessage, String introducedTypeMessage, String introducedNameFileMessage) {
        saveParametersMessageInTable();

        System.out.println("Проверка ID корреляции: " + introducedIdCorrelationMessageInTable + " = " + valueIdCorrelationMessageInTable);
        Assert.assertEquals(introducedIdCorrelationMessageInTable, valueIdCorrelationMessageInTable);

        if (persistant.equals(MessagePage.Persistant.Yes)) {
            sout("Проверка стойкости: Да" + " = " + valuePersistenceMessageInTable);
            Assert.assertEquals("Да", valuePersistenceMessageInTable);
        } else {
            sout("Проверка стойкости: Нет" + " = " + valuePersistenceMessageInTable);
            Assert.assertEquals("Нет", valuePersistenceMessageInTable);
        }

        sout("Проверка приоритета: " + introducedPriorityMessageInTable + " = " + valuePriorityMessageInTable);
        Assert.assertEquals(introducedPriorityMessageInTable, valuePriorityMessageInTable);

        sout("Проверка переотправленности: " + introducedRedirectedMessage + " = " + valueRedirectedMessage);
        Assert.assertEquals(introducedRedirectedMessage, valueRedirectedMessage);

        sout("Проверка размера: " + introducedSizeMessage + " = " + valueSizeMessageInTable);
        Assert.assertEquals(introducedSizeMessage, valueSizeMessageInTable);

        sout("Проверка типа сообщения: " + introducedTypeMessage + " = " + valueTypeMessage);
        Assert.assertEquals(introducedTypeMessage, valueTypeMessage);

        sout("Проверка имени сообщения: " + introducedNameFileMessage + " = " + valueFileNameMessageInTable);
        Assert.assertEquals(introducedNameFileMessage, valueFileNameMessageInTable);
    }


}
