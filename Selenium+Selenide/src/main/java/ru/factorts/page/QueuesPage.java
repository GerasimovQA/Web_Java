package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.factorts.page.CommonComponents.ContextMenuElementXpath;

public class QueuesPage extends BasePage {

    private SpecificQueuePage specificQueuePage;

    private SpecificMessagePage specificMessagePage;

    CommonComponents commonComponents = new CommonComponents();

    //QueueForm
    private SelenideElement addQueueButton = $x(".//*[contains(text(), 'Добавить')]"),
            queueNameInput = $("[name=name]"),
            queueNameInputForVirtualQueue = $x(".//div[label[text()='Имя очереди']]/div/input"),
            redirectionQueues = $x(".//div[label[text()='Перенаправления']]//input"),
            saveQueueButton = $x(".//*[contains(text(), 'Сохранить')]"),
            queueTypeSelect = $x(".//div[label[text()='Тип очереди']]/select"),
            deliveryQueue = $x(".//div[label[text()='Очередь поставщика']]/div/div/span/div/input"),
            getDeliveryQueueSelect = $x(".//div[label[text()='Очередь поставщика']]/div/div/span/div[@class=\"Select-input\"]"),
            destinationQueue = $x(".//div[label[text()='Очередь назначения']]/div/div/span/div/input"),
            getDestinationQueueSelect = $x(".//div[label[text()='Очередь назначения']]/div/div/span/div[@class=\"Select-input\"]"),
            deliveryQueueText = $x(".//div[label[text()='Очередь поставщика']]/div/div/span/div/span"),
            destinationQueueText = $x(".//div[label[text()='Очередь назначения']]/div/div/span/div/span"),

    //Segregation queue
    fragmentSize = $x(".//label[text()='Размер фрагментов (в байтах)']/following-sibling::input"),

    //TABLE
    table = $x(".//table[thead[tr[th[text()='Имя']]]]"),
            tableHeaderName = table.$x(".//tr/th[text()='Имя']"),
            searchInput = $x(".//div/label[text()='Поиск ']/input"),
            afterSearchQueueRow = $x(".//table/tbody/tr[@role=\"row\"]"),
            afterSearchQueueDepth = afterSearchQueueRow.$x(".//td[2]"),
            afterSearchNumberOfConsumers = afterSearchQueueRow.$x(".//td[3]"),
            afterSearchSentToQueue = afterSearchQueueRow.$x(".//td[4]"),
            afterSearchTakenFromQueue = afterSearchQueueRow.$x(".//td[5]"),
            afterSearchQueueTipe = afterSearchQueueRow.$x(".//td[6]"),
            queueRowAfterSearch = $("tbody [role=row]"),
            refreshSearchButton = $x(".//div[label[text()='Поиск:']]/button[span[contains(@class,'refresh')]]"),

    //Context queue menu
    deleteQueue = $$x(String.format(ContextMenuElementXpath, "Удалить очередь")).find(visible),
            sendSavedMessage = $$x(String.format(ContextMenuElementXpath, "Отправить сохраненное сообщение")).find(visible),
            clearQueue = $$x(String.format(ContextMenuElementXpath, "Очистить очередь")).find(visible),
            contentQueue = $$x(String.format(ContextMenuElementXpath, "Содержимое")).find(visible),
            editQueue = $$x(String.format(ContextMenuElementXpath, "Редактировать")).find(visible),

    //Confinrmation menu
    deleteButton = $x(".//button[@title='Удалить']"),
            clearButton = $x(".//span[text()='Очистить']"),
            confirmationClearMenuHeader = $x(".//h4[text()='Подтверждение удаления']"),
    //    Options
    autoUpdatePage = $(".auto-reload>label>input"),
            refreshConfigAfterChangeButton = $x(".//div[div[text()=\"Измените конфигурацию и перезапустите сервер приложений.\"]]//button");


    private ElementsCollection tableRows = table.$$x("/tbody/tr"),
            outerQueuesList = $$x(".//div[@class='Select-menu-outer']/div/div"),
            afterSearchTableRows = $$x(".//table/tbody/tr[@role=\"row\"]").filterBy(visible);


    public void createLocalQueue(String queueName) {
//        sleep(1000);
        click(addQueueButton);
//        addQueueButton.shouldBe(visible).click();
//        sleep(500);
//        queueNameInput.val(queueName);
        val(queueNameInput, queueName);
        click(saveButton);
//        saveQueueButton.shouldBe(visible).click();
        search(queueName);
        afterSearchQueueTipe.shouldHave(text("Локальная очередь"));
    }

    /**
     * Search and filter by Name
     *
     * @param queueName
     */
    @Step("Searching {0}")
    public void search(String queueName) {
        autoUpdateOff();
        val(searchInput, queueName);
    }

    @Step("Deleting {0}")
    public void deleteQueue(String queueName) {
        search(queueName);
        queueRowAfterSearch.contextClick();
        deleteQueue.click();
        deleteButton.click();
        search(queueName);
        afterSearchTableRows.shouldHave(size(0));
    }

    @Step("Creation segregation queue - {0} with destination - {1} and fragment size - {2} bit")
    public void createSegregQueue(String deliveryQueueName, String destinationQueueName, Integer size) {
        addQueueButton.click();
        queueTypeSelect.selectOption("Очередь сегментации");
//        sleep(500);
//        deliveryQueue.val(deliveryQueueName).pressEnter();
        valAndSelectElement(deliveryQueue, deliveryQueueName);
//        sleep(500);
//        destinationQueue.val(destinationQueueName).pressEnter();
        valAndSelectElement(destinationQueue, destinationQueueName);
        destinationQueueText.shouldHave(text(destinationQueueName));
//        fragmentSize.val(size.toString());
        val(fragmentSize, size.toString());
//        saveQueueButton.click();
        click(saveQueueButton);
        elementShouldNotBeVisible(saveQueueButton);
        search(deliveryQueueName);
        afterSearchQueueTipe.shouldHave(text("Очередь сегментации"));
    }

    @Step("Creation agregation queue - {0} with destination - {1}")
    public void createAgregQueue(String deliveryQueueName, String destinationQueueName) {
        addQueueButton.click();
        queueTypeSelect.selectOption("Очередь агрегации");
//        sleep(500);
//        deliveryQueue.val(deliveryQueueName).pressEnter();
        valAndSelectElement(deliveryQueue, deliveryQueueName);
        deliveryQueueText.shouldHave(text(deliveryQueueName));
//        sleep(500);
//        destinationQueue.val(destinationQueueName).pressEnter();
        valAndSelectElement(destinationQueue, destinationQueueName);
        destinationQueueText.shouldHave(text(destinationQueueName));
//        saveQueueButton.click();
        click(saveQueueButton);
        elementShouldNotBeVisible(saveQueueButton);
        search(deliveryQueueName);
        afterSearchQueueTipe.shouldHave(text("Очередь агрегации"));
    }

    @Step("Creation virtual queue - {0} with redirection - {1} ")
    public void createVirtualQueue(String queueName, String redirectionQueueName) {
        addQueueButton.click();
        queueTypeSelect.selectOption("Виртуальная очередь");
//        sleep(500);
//        redirectionQueues.val(redirectionQueueName).pressEnter();
        valAndSelectElement(redirectionQueues, redirectionQueueName);
//        sleep(500);
//        queueNameInputForVirtualQueue.val(queueName).pressEnter();
        valAndSelectElement(queueNameInputForVirtualQueue, queueName);
//        refreshConfigAfterChangeButton.click();
        click(refreshConfigAfterChangeButton);
        addQueueButton.waitUntil(enabled, 10000);
        search(queueName);
        afterSearchQueueTipe.shouldHave(text("Виртуальная очередь"));

    }

    @Step("Creation virtual queue - {0} with redirection - {1}, {2} ")
    public void createVirtualQueue(String queueName, String redirectionQueueName1, String redirectionQueueName2) {
        addQueueButton.click();
        queueTypeSelect.selectOption("Виртуальная очередь");
//        sleep(500);
//        redirectionQueues.val(redirectionQueueName1).pressEnter();
        valAndSelectElement(redirectionQueues, redirectionQueueName1);
//        redirectionQueues.val(redirectionQueueName2).pressEnter();
        valAndSelectElement(redirectionQueues, redirectionQueueName2);
//        sleep(500);
//        queueNameInputForVirtualQueue.val(queueName).pressEnter();
        valAndSelectElement(queueNameInputForVirtualQueue, queueName);
//        refreshConfigAfterChangeButton.click();
        click(refreshConfigAfterChangeButton);
        addQueueButton.waitUntil(enabled, 10000);
        search(queueName);
        afterSearchQueueTipe.shouldHave(text("Виртуальная очередь"));

    }

    /**
     * Check a number of messages in a queue
     */
    @Step("Queue {0} should has {1} massages in depth")
    public void queueShouldHaveNMessagesInDepth(String queueName, Integer messagesInDepth, int waitingTime) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Get a number of messages in a queue
     */
    @Step(" Get a number of messages in a queue {0}")
    public String queueGetMessagesInDepth(String queueName) {
        search(queueName);
        return afterSearchQueueDepth.getText();

    }

    @Step("Queue {0} should has {1} massages in depth")
    public void queueShouldHaveNMessagesInDepth(String queueName, Integer messagesInDepth) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), 5000);
        autoUpdatePage.click();
    }

    /**
     * Check a number of messages in sent in from queue
     */
    @Step("Queue {0} should has {1} massages in sent")
    public void queueShouldHaveNMessagesInSent(String queueName, Integer messagesInSent, int waitingTime) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in sent and {2} in taken")
    public void queueShouldHaveNMessagesInSentAndTaken(String queueName, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Check a number of messages in a queue, in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in depth, {2} in sent and {3} in taken")
    public void queueShouldHaveNMessagesInDepthAndSentAndTaken(String queueName, Integer messagesInDepth, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), waitingTime);
        afterSearchSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in depth, {2} in sent and {3} in taken")
    public void queueShouldHaveNConsumersAndNMessagesInSentAndTaken(String queueName, Integer numberOfConsumers, Integer messagesInSent, Integer messagesInTaken) {
        autoUpdatePage.click();
        search(queueName);
        int waitingTime = 5000;
        afterSearchSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        afterSearchNumberOfConsumers.waitUntil(text(numberOfConsumers.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in depth, {2} in sent and {3} in taken, waiting time - {4}")
    public void queueShouldHaveNConsumersAndNMessagesInSentAndTaken(String queueName, Integer numberOfConsumers, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        autoUpdatePage.click();
        search(queueName);
        afterSearchSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        afterSearchNumberOfConsumers.waitUntil(text(numberOfConsumers.toString()), waitingTime);
        autoUpdatePage.click();
    }

    /**
     * Send saved messages, after context click on queue
     */
    @Step("Send {1} saved messsages in {0}")
    public void sendNSavedMessages(String queueName, Integer messagesNumber) {
        search(queueName);
        for (int i = 0; i < messagesNumber; i++) {
            queueRowAfterSearch.contextClick();
            sendSavedMessage.click();
            commonComponents.closeNotification();
        }
        afterSearchSentToQueue.shouldHave(text(messagesNumber.toString()));
    }

    /**
     * Send saved messages, after context click on queue without check
     */
    @Step("Send {1} saved messsages in {0} without check")
    public void sendNSavedMessagesWithoutCheck(String queueName, Integer messagesNumber) {
        search(queueName);
        for (int i = 0; i < messagesNumber; i++) {
            queueRowAfterSearch.contextClick();
            sendSavedMessage.click();
            commonComponents.closeNotification();
        }
    }

    @Step("Double click in {0}")
    public void doubleClickInSpecificQueue(String queueName) {
        search(queueName);
        queueRowAfterSearch.doubleClick();
    }

    @Step("Clear {0}")
    public void clearQueue(String queueName) {
        autoUpdatePage.click();
        search(queueName);
        queueRowAfterSearch.contextClick();
        clearQueue.click();
        clearButton.click();
        confirmationClearMenuHeader.waitWhile(visible, 10000);
        afterSearchQueueDepth.shouldHave(text("0"));
        autoUpdatePage.click();
    }

    @Step("Content {0}")
    public void contentSpecificQueue(String queueName) {
        search(queueName);
        queueRowAfterSearch.contextClick();
        contentQueue.click();
    }

    /**
     * Method for edit queues type in local queue
     */
    @Step("Edit {0} type in local queue")
    public void editQueueTypeInLocalQueue(String queueName) {
        search(queueName);
        queueRowAfterSearch.contextClick();
        editQueue.click();
        queueTypeSelect.selectOption("Локальная очередь");
        saveQueueButton.click();
        search(queueName);
        afterSearchQueueTipe.shouldHave(text("Локальная очередь"));
    }

    /**
     * Method for check that queue has specific message
     */
    @Step("{0} should has specific message")
    public void queueShouldHaveSpecificMessage(String queueName, String message) {
        contentSpecificQueue(queueName);
        specificQueuePage = new SpecificQueuePage();
        specificQueuePage.openFirstMessage();
        specificMessagePage = new SpecificMessagePage();
        specificMessagePage.messageBody.shouldHave(text(message));
        specificMessagePage.returnInQueueButton.click();
//        sleep(300);
        specificQueuePage.returnInQueuesListPage.click();
//        sleep(300);
    }

    /**
     * Method for deleting an arbitrary number of queues
     */
    @Step("Deleting queues")
    public void deleteSeveralQueues(String... queueNames) {
        for (String queue : queueNames)
            deleteQueue(queue);
    }

}
