package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.factorts.page.CommonComponents.ContextMenuElementXpath;

public class TopicsPage extends BasePage {

    SelenideElement topics = $x(".//div/a[text()='Разделы' and @href='#/topics/topic']"),
            subscribers = $x(".//div/a[text()='Подписчики' and @href='#/topics/subscribers']"),
            addTopicButton = $x(".//*[contains(text(), 'Добавить раздел')]"),
    //Topicform
    topicNameInput = $x(".//div[label[text()='Имя*']]//input"),
            confirmationAddTopicButton = $$x(".//div/button[text()='Добавить']").find(visible),
    //TABLE
    table = $x(".//table[thead[tr[th[text()='Имя']]]]"),
            tableHeaderName = table.$x(".//tr/th[text()='Имя']"),
            searchInput = $x(".//div/label[text()='Поиск:']/input"),
            afterSearchTopicRow = $x(".//table/tbody/tr[@role=\"row\"]"),
            afterSearchNumberOfConsumers = afterSearchTopicRow.$x(".//td[2]"),
            afterSearchSentToTopic = afterSearchTopicRow.$x(".//td[3]"),
            afterSearchTakenFromTopic = afterSearchTopicRow.$x(".//td[4]"),
            refreshSearchButton = $x(".//div[label[text()='Поиск:']]/button[span[contains(@class,'refresh')]]"),
    //Context Menu
    deleteTopic = $$x(String.format(ContextMenuElementXpath, "Удалить раздел")).find(visible),

    //Confirmation Menu
    deleteButton = $$x(".//button[span[text()='Удалить']]").find(visible),

    //    Options
    autoUpdatePage = $(".auto-reload>label>input"),
            refreshConfigAfterChangeButton = $x(".//div[div[text()=\"Измените конфигурацию и перезапустите сервер приложений.\"]]//button");

    private ElementsCollection afterSearchTableRows = $$x(".//table/tbody/tr[@role=\"row\"]");


    public TopicsPage() {
        topics.shouldBe(visible);
    }

    public void createTopic(String topicName) {
//        addTopicButton.click();
        click(addTopicButton);
//        sleep(500);
//        topicNameInput.val(topicName);
        val(topicNameInput, topicName);
//        confirmationAddTopicButton.shouldBe(enabled).click();
        click(confirmationAddTopicButton);
        search(topicName);
    }

    /**
     * Search and filter by Name
     *
     * @param topicName
     */
    @Step("Searching {0} Topic")
    public void search(String topicName) {
//        int i=0;
//        while ($x(".//span[text()=\"Перезапустить\"]/..").isDisplayed() && i < 5) {
//            i++;
//            refresh();
//            sleep(10000);
//        }
        val(searchInput, topicName);
        if (tableHeaderName.getAttribute("aria-label").equals("Имя: активировать для сортировки столбца по убыванию")) {
//            tableHeaderName.click();
            click(tableHeaderName);
        }
    }

    @Step("Deleting {0} Topic")
    public void deleteTopic(String topicName) {
        search(topicName);
//        afterSearchTopicRow.contextClick();
        contextClick(afterSearchTopicRow);
//        deleteTopic.click();
        click(deleteTopic);
//        sleep(500);
//        deleteButton.shouldBe(enabled).click();
        click(deleteButton);
        search(topicName);
        afterSearchTableRows.shouldHave(size(0));
    }

    /**
     * Check a number of messages in sent
     */
    @Step("Topic {0} should has {1} messages")
    public void topicShouldHaveNMessagesInSent(String topicName, Integer messagesInSent) {
//        autoUpdatePage.click();
        click(autoUpdatePage);
        search(topicName);
        afterSearchSentToTopic.waitUntil(text(messagesInSent.toString()), 5000);
//        autoUpdatePage.click();
        click(autoUpdatePage);
    }

    @Step("Click {1} option in context menu {0} Topic")
    public void clickOptionInContextMenu(String topicName, String optionName) {
        search(topicName);
//        afterSearchTopicRow.contextClick();
        contextClick(afterSearchTopicRow);
//        $$x(String.format(ContextMenuElementXpath, optionName)).find(visible).click();
        click($$x(String.format(ContextMenuElementXpath, optionName)).find(visible));

    }
}
