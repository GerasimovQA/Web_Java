package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionMonitorPage extends BasePage {
    String infoAboutCellEvent = "(//tbody//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")])[position() > 1][%s]/td";
    public SelenideElement connectionToDbTab = $x(".//div[text()='Подключение к БД']");

    public SelenideElement processingTimeHeader = $x(".//span[text()='Время обработки']");
    public SelenideElement expandRowAfterSearchButton = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[@class=\"ant-table-cell ant-table-row-expand-icon-cell\"]/button");

    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));
    public ElementsCollection infoAboutEvent = $$(By.xpath("(//tbody//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")])[position() > 1]/td"));



}


