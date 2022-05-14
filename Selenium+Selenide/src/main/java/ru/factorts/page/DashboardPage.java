package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage extends BasePage {
    String specificMonitor = "//a[text()='%s']";


    public SelenideElement addGroupButton = $x(".//span[text()=' Добавить группу']");
    public SelenideElement addNewDashboardButton = $x(".//span[text()='Создать экран']");
    public SelenideElement nameDashbordButton = $x(".//label[text()='Имя']/../following-sibling::div//input");

    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));

    public void createDashboard(String nameDashboard) {
        click(dashboardTab);
        click(addNewDashboardButton);
        val(nameDashbordButton, nameDashboard);
        click(createButton);
    }


}


