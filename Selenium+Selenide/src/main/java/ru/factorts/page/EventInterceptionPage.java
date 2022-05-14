package ru.factorts.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$x;

public class EventInterceptionPage extends BasePage {
    QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();

    SelenideElement addEventInterceptionButton = $x(".//span[text()='Добавить перехват событий']/..");
    SelenideElement typeInterceptionSelect = $x(".//label[text()='Тип перехватчика событий']/../following-sibling::div");
    SelenideElement typeInterceptionSelectInput = $x(".//label[text()='Тип перехватчика событий']/../following-sibling::div//input");

    SelenideElement deleteEventForInterceptionButton = $x(".//label[text()='События для перехвата']/../following-sibling::div//span[@class=\"ant-select-clear\"]");
    SelenideElement eventForInterceptionSelect = $x(".//label[text()='События для перехвата']/../following-sibling::div//input/..");
    SelenideElement eventForInterceptionInput = $x(".//label[text()='События для перехвата']/../following-sibling::div//input");

    SelenideElement autostartInterceptionCheckBox = $x(".//span[text()='Запускать при создании перехватчика и старте Менеджера очередей']/..//input/..");
    SelenideElement queueForInterceptionSelectActivate = $x(".//label[text()='Очереди для перехвата']/../following-sibling::div//input/..");
    SelenideElement queueForInterceptionSelectInput = $x(".//label[text()='Очереди для перехвата']/../following-sibling::div//input");
    SelenideElement queueForMailingSelectActivate = $x(".//label[text()='Очереди для рассылки']/../following-sibling::div//input/..");
    SelenideElement queueForMailingSelectInput = $x(".//label[text()='Очереди для рассылки']/../following-sibling::div//input");
    SelenideElement linkToEnterPointSopsSelect = $x(".//label[text()='Ссылка на точку входа СОПСа']/../following-sibling::div");
    SelenideElement linkToEnterPointSopsSelectInput = $x(".//label[text()='Ссылка на точку входа СОПСа']/../following-sibling::div//input");
    SelenideElement patternNameFileInput = $x(".//label[text()='Шаблон имени файла']/../following-sibling::div//input");
    SelenideElement nameFileInput = $x(".//label[text()='Имя файла']/../following-sibling::div//input");
    SelenideElement patternRecordInput = $x(".//label[text()='Шаблон записи']/../following-sibling::div//input");
    SelenideElement maximumSizeInput = $x(".//label[text()='Максимальный размер']/../following-sibling::div//input[@name=\"fileAppenderDto.size\"]");
    SelenideElement maximumSizeMultyInput = $x(".//label[text()='Максимальный размер']/../following-sibling::div//input[@name=\"maxFileSize\"]");
    SelenideElement unitSizeInput = $x(".//label[text()='Максимальный размер']/../following-sibling::div//input[@role=\"combobox\"]/../following-sibling::span");
    SelenideElement rotationInput = $x(".//label[text()='Ротация']/../following-sibling::div//input");

    @Step
    public void createEventInterception(String moduleName, String managerName, String nameEventInterception, ArrayList nameQueueForInterception, String nameAcceptor, String filePath) {
        if (moduleName.equals("Мультименеджер очередей")) {
            queueManagerMultyPage.goToManager(managerName);
            click(eventInterceptionMultyTab);
            click(addEventInterceptionButton);
            click(autostartInterceptionCheckBox);
        } else {
            queueManagerPage = new QueueManagerPage();
            click(eventInterceptionTab);
            click(addEventInterceptionButton);
        }


        click(typeInterceptionSelect);
        valAndSelectElement(typeInterceptionSelectInput, nameEventInterception);
        click(queueForInterceptionSelectActivate);
        valAndSelectElement(queueForInterceptionSelectInput, nameEventInterception.replace("в ", "в."));
        click(modalWindow);


        switch (nameEventInterception) {
            case "Записать событие в СОПС":
                click(linkToEnterPointSopsSelect);
                valAndSelectElement(linkToEnterPointSopsSelectInput, nameAcceptor);
                click(saveButton);
//                restartModule();
                break;

            case "Записать событие в монитор транзакций":
                eventForInterceptionSelect.hover();
                click(deleteEventForInterceptionButton);
                click(eventForInterceptionSelect);
                valAndSelectElement(eventForInterceptionInput, "GET");
                valAndSelectElement(eventForInterceptionInput, "PUT");
                valAndSelectElement(eventForInterceptionInput, "EXPIRE");
                valAndSelectElement(eventForInterceptionInput, "DISCARD");
                click(modalWindow);
                click(saveButton);
//                restartModule();
                break;

            case "Записать событие в очередь":
                click(queueForMailingSelectActivate);
                valAndSelectElement(queueForMailingSelectInput, nameAcceptor);
                click(modalWindow);
                click(saveButton);
//                restartModule();
                break;

            case "Записать событие в файл":
                if (moduleName.equals("Мультименеджер очередей")) {
                    compareStringAndElementValue("${FESB_DATA}/logs/intercepting.log", nameFileInput);
                    val(nameFileInput, filePath);
                } else {
                    compareStringAndElementValue("${FESB_DATA}/logs/intercepting-1.log", nameFileInput);
                    val(nameFileInput, filePath);
                    nameFileInput.sendKeys(Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT,
                            Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.BACK_SPACE);
                }

                if (moduleName.equals("Мультименеджер очередей")) {
                    compareStringAndElementValue("${FESB_DATA}/logs/intercepting.%d{yyyy-MM-dd}.%i.log", patternNameFileInput);
                    val(patternNameFileInput, "${FESB_DATA}/fesb/tests/uploadMulty?/intercepting.%d{yyyy-MM-dd}.%i.log");
                    compareStringAndElementValue("%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n", patternRecordInput);
                    compareStringAndElementValue("100", maximumSizeMultyInput);
                } else {
                    compareStringAndElementValue("${FESB_DATA}/logs/intercepting-1.%d{yyyy-MM-dd}.%i.log", patternNameFileInput);
                    val(patternNameFileInput, "${FESB_DATA}/fesb/tests/upload?/intercepting.%d{yyyy-MM-dd}.%i.log");
                    compareStringAndElementValue("%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n", patternRecordInput);
                    compareStringAndElementValue("100", maximumSizeInput);
                }


                compareStringAndElementText("MB", unitSizeInput);
                compareStringAndElementValue("5", rotationInput);
                click(saveButton);
                break;
            default:
                throw new AssertionError("Пропущен выбор типа перехвата");
        }

    }

}
