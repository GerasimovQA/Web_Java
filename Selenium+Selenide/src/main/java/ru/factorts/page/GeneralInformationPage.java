package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class GeneralInformationPage extends BasePage {

    public SelenideElement nameQm = $x(".//td[text()='Имя менеджера очередей']/following-sibling::th");
    public SelenideElement identificationQm = $x(".//td[text()='Идентификатор менеджера очередей']/following-sibling::th");
    public SelenideElement modeQm = $x(".//td[text()='Режим работы менеджера очередей']/following-sibling::th");
    public SelenideElement productVersion = $x(".//td[text()='Версия продукта']/following-sibling::th");

//    public void checkModeQM(String ExpactedModeQm) {
//        switch (ExpactedModeQm) {
//            case ("Master"):
//                nameQm.waitUntil(Condition.not(Condition.empty),120000);
//                identificationQm.waitUntil(Condition.not(Condition.empty),120000);
//                nameQm.shouldNotBe(Condition.empty);
//                identificationQm.shouldNotBe(Condition.empty);
//                break;
//            case ("Slave"):
//                nameQm.waitUntil(Condition.empty,120000);
//                identificationQm.waitUntil(Condition.empty,120000);
//                nameQm.shouldBe(Condition.empty);
//                identificationQm.shouldBe(Condition.empty);
//                break;
//            default:
//                throw new AssertionError("Пропущена проверка общей информации");
//        }
//        go
//        modeQm.shouldHave(Condition.exactText(ExpactedModeQm));
//    }
}


