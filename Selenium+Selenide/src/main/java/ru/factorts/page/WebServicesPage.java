package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class WebServicesPage extends BasePage {


    public SelenideElement addServiceButton = $x(".//span[text()='Добавить Веб-Сервис']/..");

    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));


}


