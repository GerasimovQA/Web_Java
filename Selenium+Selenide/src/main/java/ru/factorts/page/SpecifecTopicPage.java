package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SpecifecTopicPage {

    SelenideElement returnInTopicsList = $x(".//a[@href=\"#/queue-manager/topics/topic\"]"),
            sendMessage = $x(".//span[text()='Отправить сообщение']/..");

    public SpecifecTopicPage() {
        returnInTopicsList.shouldBe(Condition.visible);
    }

    public SpecifecTopicPage(String empty) {
    }

}
