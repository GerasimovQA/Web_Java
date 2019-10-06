package Cucum;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Page {
    public SelenideElement input = $x("//input[@name='q']");
    public SelenideElement result = $x("//div[@id=\"resultStats\"]");
    public SelenideElement buttonSearch = $$x("//input[@value=\"Поиск в Google\"]").find(Condition.visible);
}
