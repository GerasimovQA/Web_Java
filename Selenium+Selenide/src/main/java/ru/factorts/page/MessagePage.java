package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Collections;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.stream.IntStream.range;

public class MessagePage extends BasePage {
    QueueManagerPage queueManagerPage;
    CommonComponents commonComponents = new CommonComponents();

    SelenideElement makePersistentCheckBox = $x(".//span[text()='Сделать стойким']/preceding-sibling::span");
    SelenideElement sumMessagesInSendFormInput = $x(".//label[text()='Количество']/../following-sibling::div//input");
    SelenideElement corellationIdMessageInSendFormInput = $x(".//span[text()='ID корреляции']/../../following-sibling::div//input");
    SelenideElement replyToJmsRecipientMessageInSendFormList = $x(".//span[text()='Ответить в']/../../following-sibling::div//div");
    SelenideElement replyToJmsRecipientMessageInSendFormInput = $x(".//span[text()='Ответить в']/../../following-sibling::div//input");


    SelenideElement additionalSendingOptions = $x(".//div[text()='Дополнительные параметры отправки']/following-sibling::div[@class=\"ant-card-extra\"]");
    SelenideElement addAdditionalSendingOptionsButton = $x(".//div[text()='Дополнительные параметры отправки']/../../..//span[text()=' Добавить']/..");
    SelenideElement additionalSendingOptionsActivate = $x(".//div[text()='Параметр отправки сообщения']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");
    SelenideElement additionalSendingOptionsInput = $x(".//div[text()='Параметр отправки сообщения']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]/..//input");
    SelenideElement additionalSendingOptionsNameInput = $x(".//div[text()='Параметр']/..//input[@name=\"name\"]");
    SelenideElement additionalSendingOptionsValueInput = $x(".//div[text()='Значение']/..//input[@name=\"value\"]");


    SelenideElement typeJmsMessageInSendFormInput = $x(".//span[text()='Тип']/../../following-sibling::div//input");
    SelenideElement groupIdMessageInSendFormInput = $x(".//span[text()='ID группы']/../../following-sibling::div//input");
    SelenideElement numberInGroupMessageInSendFormInput = $x(".//span[text()='Номер сообщения в группе']/../../following-sibling::div//input");
    SelenideElement priorityMessageInSendFormInput = $x(".//span[text()='Приоритет']/../../following-sibling::div//input");
    SelenideElement validityMessageInSendFormInput = $x(".//span[text()='Время жизни (мс)']/../../following-sibling::div//input");
    SelenideElement nameFileMessageInSendFormInput = $x(".//span[text()='Имя файла']/../../following-sibling::div//input");
    SelenideElement headerOfCounerMessageInSendFormInput = $x(".//label[text()='Заголовок счетчика']/../following-sibling::div//input");
    SelenideElement expandSchedulerPropertiesButton = $x(".//div[text()='Свойства планировщика']/following-sibling::div");
    SelenideElement initialDelayInSendFormInput = $x(".//span[text()='Изначальная задержка (мс)']/../../following-sibling::div//input");
    SelenideElement timeToResendMessageInSendFormInput = $x(".//span[text()='Период между повторами (мс)']/../../following-sibling::div//input");
    SelenideElement numberOfResendsMessageInSendFormInput = $x(".//span[text()='Количество повторов']/../../following-sibling::div//input");
    SelenideElement cronMessageInSendFormInput = $x(".//span[text()='CRON-строка планировщика']/../../following-sibling::div//input");
    SelenideElement messageBodyInSendFormInput = $x(".//div[text()='Тело сообщения']/following-sibling::div//textarea");


    String messageWithAllSimbols = "`12334567890-=~!@#$%^&*()_+qwertyuiop[]\\asdfghjkl;'zxcvbnm,./QWERTYUIOP{} |ASDFGHJKL:\"ZXCVBNM<>?йцукеёнгшщзхъфывапролджэячсмитьбюЙЦУКЕЁНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
    String typeMessageInList = "//div[@class='Select-menu-outer']/div/div[text()='%s']";
    static String nameLocalQueue_2 = ".//div[@class=\"ant-select-dropdown ant-select-dropdown-placement-bottomLeft \"]//div[@class=\"ant-select-item-option-content\"]//div[text()='%s']";
    static String nameLocalQueue_1 = "//*[text()='Локальные пользователи: %s']";
    static String nameLocalQueue0 = "//*[text()='Добавить статическую связь %s']";
    static String nameLocalQueue1 = "//*[text()='Создать локальную очередь %s']";
    static String nameLocalQueue2 = "//*[text()='Создать новую таблицу маршрутов %s']";
    static String nameLocalQueue3 = "//*[text()='Создать домен %s']";
    static String nameLocalQueue4 = "//div[text()='Добавить %s к списку.']";
    static String nameLocalQueue5 = "//div[@class=\"ant-select-item-option-content\"]/div/span[text()='%s']";
    static String nameLocalQueue6 = "//div[@class=\"ant-select-item-option-content\"][contains(text(),'schemaXSD.xsd')]";
    static String nameLocalQueue7 = "//div[@class=\"ant-select-item ant-select-item-option ant-select-item-option-active\"]//div[@class=\"ant-select-item-option-content\"][text()='%s']";
    static String nameLocalQueue8 = ".//div[@class=\"ant-select-item-option-content\"][text()='%s']";
    static String nameLocalQueue9 = "//div[@aria-label='%s'][@role=\"option\"]";
    static String nameLocalQueue10 = "//div[@aria-label='%s'][@role=\"option\"]/span";
    static String nameLocalQueue11 = "//li/div/span[text()='%s']";
    static String nameLocalQueue12 = "//div[@class=\"Select-menu-outer\"]//span[contains(text(),'%s')]";
    static String nameLocalQueue13 = "//div[contains(text(),'%s')][@role=\"option\"]";
    static String nameLocalQueue14 = "//li[@label='%s']";
    static String nameLocalQueue15 = "//span[text()='%s']";
    static String nameLocalQueue16 = "//*[text()='%s']";
    static String nameLocalQueue17 = "//*[contains(text(),'%s')]";


    SelenideElement expandDestinationNames = $x(".//label[contains(text(),'Адресат')]/../following-sibling::div//div[@class=\"Select-control\"]"),
            moduleSelect = $x(".//label[text()='Модуль']/../following-sibling::div"),
            moduleInput = $x(".//label[text()='Модуль']/../following-sibling::div//input"),
            mqSelect = $x(".//label[text()='Менеджер очередей']/../following-sibling::div"),
            mqInput = $x(".//label[text()='Менеджер очередей']/../following-sibling::div//input"),
            addresseSelect = $x(".//label[text()='Адресат']/../following-sibling::div"),
            addresseInput = $x(".//label[text()='Адресат']/../following-sibling::div//input"),
            kindOfContentMessageSelect = $x(".//label[text()='Содержание сообщения']/../following-sibling::div//div"),
            kindOfContentMessageInput = $x(".//label[text()='Содержание сообщения']/../following-sibling::div//input"),
            sizeOfBodyMessageInput = $x(".//input[@name=\"generatedMinSizeString\"]"),
            unitOfSizeOfBodyMessageGenerateRangeActivate = $x(".//input[@name=\"sizeString\"]/..//span[@title]"),
            unitOfSizeOfBodyMessageGenerateActivate = $x(".//input[@name=\"generatedMinSizeString\"]/..//span[@title]"),

    messageTextAria = $x(".//textarea[@name='text']"),
            fileAria = $x(".//p[text()='Загрузить...']/../..//input"),
            messageTextAriaForHttp = $x(".//textarea[@name=\"message\"]"),
            messageTextAriaForHttp3 = $$x("//span[text()='Сообщение']/../../..//span[@cm-text]").find(visible),
    //            messageTextAriaForHttp2 = $$x(".//span[@class=\"ace_text ace_xml\"]").find(visible),
    sendMessageButton = $x(".//span[contains(text(),'Отправить сообщение')]/../../button"),
            sendMessageButton2 = $x(".//span[contains(text(),'Отправить сообщение')]/.."),
            saveDefaultMessageButton = $x(".//span[contains(text(),'Сохранить по умолчанию для очереди')]/.."),
            sendMessageInModalFormButton = $x(".//div[text()='Отправка сообщения']/../following-sibling::div//span[contains(text(),'Отправить сообщение')]/.."),
            fileInput = $x(".//input[@name='file']"),
            messageTypeArea = $x(".//label[contains(text(),'Содержание сообщения')]/../following-sibling::div"),
            deleteFileIcon = $x(".//span[@aria-label=\"delete\"]"),
            panelHeading1 = $x(".//div[contains(text(),'Отправка сообщения')]"),
            sortByID = $x(".//span[text()=\"ID Сообщения\"]"),

    howManyMessagesToSend = $x(".//label[text()='Количество']/../following-sibling::div//input");

    ElementsCollection typeAdressInListOfSendPage = $$x(".//label[text()='Адресат']/../../../../../../../../../../..//div[@class=\"ant-select-item ant-select-item-option\"]//span[2]");
    ElementsCollection sizeOfBodyMessageInGenerateRangeInput = $$x(".//input[@name=\"sizeString\"]");
    ElementsCollection numberOfBodyMessageInGenerateRangeInput = $$x(".//input[@name=\"count\"]");
//    public MessagePage() {
////        panelHeading1.shouldBe(visible);
//    }

    public enum Persistant {Yes, No}

    public enum ContentOfMessage {TEXT, FILE, GENERATE, GENERATE_IN_RANGE}

    public enum additionalSendingOptions {TYPE, GROUP_ID_MESSAGE, NUMBER_IN_GROUP_MESSAGE, PRYORITY, VALIDITY_MESSAGE, NAME_FILE, HEADER_OF_COUNTER}

    @Step("Creation message with size {0} bite")
    public String createMessageWithBiteSize(Integer size) {
        String s = "s";
        return String.join("", Collections.nCopies(size, s));
    }

    @Step("Sending {2} messages in {0}")
    public void sendMessageInQueueNTimes(String moduleName, String managerName, String queueName, String message, int times) {
        click(moduleSelect);
        valAndSelectElement(moduleInput, moduleName);

        if (moduleName.equals("Мультименеджер очередей")) {
            click(mqSelect);
            valAndSelectElement(mqInput, managerName);
        }

        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        val(messageTextAria, message);
        for (int i : range(0, times).toArray()) {
            click(sendMessageButton);
            commonComponents.closeNotification();
        }
    }

    @Step("Sending {2} messages in {0}")
    public void sendMessageInTopicNTimes(String moduleName, String managerName, String topicName, String message, int times) {
        click(moduleSelect);
        valAndSelectElement(moduleInput, moduleName);

        if (moduleName.equals("Мультименеджер очередей")) {
            click(mqSelect);
            valAndSelectElement(mqInput, managerName);
        }
        click(addresseSelect);
        valAndSelectElement(addresseInput, topicName);
        val(messageTextAria, message);
        for (int i : range(0, times).toArray()) {
            click(sendMessageButton);
            commonComponents.closeNotification();
        }
    }

    @Step("Sending {2} files in {0}")
    public void sendFileInQueueNTimes(String queueName, String file, int times) {
        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        click(kindOfContentMessageSelect);
        valAndSelectElement(kindOfContentMessageInput, "Файл");
//        click(messageTypeArea.$x(".//div/div/ul/li[text()='Файлы']"));
        fileAria.sendKeys(file);
        deleteFileIcon.shouldBe(visible);
        for (int i : range(0, times).toArray()) {
            click(sendMessageButton);
            commonComponents.closeNotification();
        }
    }

    @Step("Sending {2} files in {0}")
    public void sendFileInQueueNTimesAtOnce(String moduleName, String managerName, String queueName, String nameFile, Integer times) {
        click(moduleSelect);
        valAndSelectElement(moduleInput, moduleName);

        if (moduleName.equals("Мультименеджер очередей")) {
            click(mqSelect);
            valAndSelectElement(mqInput, managerName);
        }

        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        click(kindOfContentMessageSelect);
        valAndSelectElement(kindOfContentMessageInput, "Файл");
//        click(messageTypeArea);
//        click($x(String.format(selectOption, "Файл")));
        fileAria.sendKeys(nameFile);
        sout("Загрузили файл: " + nameFile);
        deleteFileIcon.shouldBe(visible);
        val(howManyMessagesToSend, times.toString());
        click(sendMessageButton);
        commonComponents.closeNotification();

    }

    @Step("Sending {1} messages without checking receiving")
    public void sendMessageWithOutCheck(String message, int times) {
        val(messageTextAria, message);
        for (int i : range(0, times).toArray()) {
            click(sendMessageButton);
            commonComponents.closeNotification();
        }
    }

    @Step("Sending {2} messages in {0} at once")
    public void sendMessageInQueueNTimesAtOnce(String moduleName, String managerName, String queueName, String message, Integer times) {
        click(moduleSelect);
        valAndSelectElement(moduleInput, moduleName);

        if (moduleName.equals("Мультименеджер очередей")) {
            click(mqSelect);
            valAndSelectElement(mqInput, managerName);
        }

        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        val(howManyMessagesToSend, times.toString());
        val(messageTextAria, message);
        click(sendMessageButton);
        commonComponents.closeNotification();
    }

    @Step("Sending {2} messages in {0} at once")
    public void sendPersistantMessageInQueueNTimesAtOnce(String queueName, String message, Integer times) {
        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        val(howManyMessagesToSend, times.toString());
        val(messageTextAria, message);
        click(makePersistentCheckBox);
        click(sendMessageButton);
        commonComponents.closeNotification();
    }

    @Step("Sending {2} messages in {0} at once")
    public void sendMessageInQueueNTimesAtOnceWithoutText(String queueName, Integer times) {
        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        val(howManyMessagesToSend, times.toString());
        click(sendMessageButton);
//        commonComponents.closeNotification();
    }

    @Step("Sending {2} generated messages in {0} at once")
    public void sendGeneratedMessageInQueueNTimesAtOnce(String queueName, String Size, Integer times) {
        queueManagerPage = new QueueManagerPage();
        queueManagerPage.goToMessagePage();
        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        click(kindOfContentMessageInput);
        click($x(String.format(typeMessageInList, "Сгенерировать")));
        val(sizeOfBodyMessageInput, Size);
        val(howManyMessagesToSend, times.toString());
        click(sendMessageButton);
        commonComponents.closeNotification();
    }

    public void checkEnableTypeOfQueueInDropList(String typeQueue, ElementsCollection List) {
        int i = 0;
        for (SelenideElement type : List) {
            if (type.getText().equals(typeQueue)) {
                i = i + 1;
                sout(type.getText());
                sout("Тип очереди найден в списке: " + typeQueue);
                break;
            }
        }
        if (i == 0) {
            throw new AssertionError("В выпадающем списке очередей не найден тип " + typeQueue);
        }
    }

    @Step
    public void sendMessageThroughForm(String queueName, String managerName, String moduleName, Object[] parametersOfMessage, Object[] parametersOfSent) {
        click(sendMessageTab);
        if (moduleName != null) {
            click(moduleSelect);
            valAndSelectElement(moduleInput, moduleName);
        }
        if (managerName!= null) {
            click(mqSelect);
            valAndSelectElement(mqInput, managerName);
        }
        click(addresseSelect);
        valAndSelectElement(addresseInput, queueName);
        sout(parametersOfMessage[0].toString());
        sout(ContentOfMessage.GENERATE.toString());
        click(kindOfContentMessageSelect);
        if (parametersOfMessage[0].equals(ContentOfMessage.TEXT)) {
            valAndSelectElement(kindOfContentMessageInput, "Текст");
            val(messageTextAria, (String) parametersOfMessage[1]);
        } else if (parametersOfMessage[0].equals(ContentOfMessage.GENERATE)) {
            valAndSelectElement(kindOfContentMessageInput, "Сгенерировать");
            val(sizeOfBodyMessageInput, (String) parametersOfMessage[1]);
            compareStringAndElementText("B", unitOfSizeOfBodyMessageGenerateActivate);
        } else if (parametersOfMessage[0].equals(ContentOfMessage.GENERATE_IN_RANGE)) {
            valAndSelectElement(kindOfContentMessageInput, "Сгенерировать в диапазоне");
            int x = 0;
            for (String oneSize : parametersOfMessage[1].toString().split("-")) {
                queueManagerPage = new QueueManagerPage("Empty");
                if (x > 0) click(queueManagerPage.addHostButton);
                val(sizeOfBodyMessageInGenerateRangeInput.get(x), oneSize.split("\\|")[0]);
                compareStringAndElementText("B", unitOfSizeOfBodyMessageGenerateRangeActivate);
                val(numberOfBodyMessageInGenerateRangeInput.get(x), oneSize.split("\\|")[1]);
                x++;
            }
        }
        val(howManyMessagesToSend, (String) parametersOfSent[0]);
        if (parametersOfMessage.length > 2 && parametersOfMessage[2].toString().equals("стойкое")) {
            click(makePersistentCheckBox);
        }

        if (parametersOfSent.length > 1) {
            for (int x = 1; x < parametersOfSent.length; x++) {
                fillAdditionalSendingOptions(((String) parametersOfSent[x]).split("-")[0], ((String) parametersOfSent[x]).split("-")[1]);
            }
        }

        click(sendMessageButton);
    }

    public void fillInAdditionalFields(Persistant valuePersistant, String valueNumberSendMessage, String valueCorellationIdMessage,
                                       String valueReplyToJmsRecipientMessage, String valueTypeJmsMessage, String valueGroupIdMessage,
                                       String valueNumberInGroupMessage, String valuePriorityMessage,
                                       String valueValidityMessage, String valueinitialDelay, String valueTimeToResendMessage, String valueHeaderOfCounerMessage,
                                       String valueNumberOfResendsMessage, String valueCronMessage, String valueNameFileMessage) {
        if (valuePersistant.equals(Persistant.Yes)) click(makePersistentCheckBox);
        val(sumMessagesInSendFormInput, valueNumberSendMessage);
        val(corellationIdMessageInSendFormInput, valueCorellationIdMessage);
        if (!valueReplyToJmsRecipientMessage.equals("null")) {
            click(replyToJmsRecipientMessageInSendFormList);
            valAndSelectElement(replyToJmsRecipientMessageInSendFormInput, valueReplyToJmsRecipientMessage);
        }
        val(priorityMessageInSendFormInput, valuePriorityMessage);
        val(nameFileMessageInSendFormInput, valueNameFileMessage);

        fillAdditionalSendingOptions("Тип", valueTypeJmsMessage);
        fillAdditionalSendingOptions("ID Группы", valueGroupIdMessage);
        fillAdditionalSendingOptions("Номер сообщения в группе", valueNumberInGroupMessage);
//        fillAdditionalSendingOptions("Приоритет", valuePriorityMessage);
        fillAdditionalSendingOptions("Время жизни (мс)", valueValidityMessage + "000");
//        fillAdditionalSendingOptions("Имя файла", valueNameFileMessage);
        fillAdditionalSendingOptions("Заголовок счетчика", valueHeaderOfCounerMessage);
        fillAdditionalSendingOptions("Изначальная задержка (мс)", valueinitialDelay);
        fillAdditionalSendingOptions("Период между повторами (мс)", valueTimeToResendMessage);
//        fillAdditionalSendingOptions("Количество повторов", valueNumberOfResendsMessage);
//        fillAdditionalSendingOptions("CRON-строка планировщика", valueCronMessage);


//        val(typeJmsMessageInSendFormInput, valueTypeJmsMessage);
//        val(groupIdMessageInSendFormInput, valueGroupIdMessage);
//        val(numberInGroupMessageInSendFormInput, valueNumberInGroupMessage);
//        val(priorityMessageInSendFormInput, valuePriorityMessage);
//        val(validityMessageInSendFormInput, valuevalidityMessage + "000");
//        val(nameFileMessageInSendFormInput, valueNameFileMessage);
//        val(headerOfCounerMessageInSendFormInput, valueHeaderOfCounerMessage);


//        click(expandSchedulerPropertiesButton);
//        val(initialDelayInSendFormInput, valueinitialDelay);
//        val(timeToResendMessageInSendFormInput, valueTimeToResendMessage);
//        val(numberOfResendsMessageInSendFormInput, valueNumberOfResendsMessage);
//        val(cronMessageInSendFormInput, valueCronMessage);
    }

    public void fillAdditionalSendingOptions(String nameOption, String optionValue) {
        if (!addAdditionalSendingOptionsButton.isDisplayed()) {
            click(additionalSendingOptions);
        }
        click(addAdditionalSendingOptionsButton);
        click(additionalSendingOptionsActivate);
        valAndSelectElement(additionalSendingOptionsInput, nameOption);
        switch (nameOption) {
            case "Тип":
            case "ID Группы":
            case "Номер сообщения в группе":
            case "Приоритет":
            case "Время жизни (мс)":
            case "Имя файла":
            case "Заголовок счетчика":
            case "Изначальная задержка (мс)":
            case "Период между повторами (мс)":
            case "Количество повторов":
            case "CRON-строка планировщика":
                val(additionalSendingOptionsValueInput, optionValue);
                break;
            default:
                throw new AssertionError("Пропущен выбор параметра отправки");
        }
        click(saveButton);
    }
}
