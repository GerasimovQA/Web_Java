package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PerevodPage extends BasePage {


    public SelenideElement nameClassInput = $$x(".//input[@name=\"query\"]").find(visible);
    public SelenideElement nameClassButton = $$x(".//input[@type=\"submit\"]").find(visible);
    public SelenideElement description = $$x(".//td[text()='Description:']/following-sibling::td").find(visible);

    public SelenideElement translaterInput = $$x(".//*[@id=\"source\"]").find(visible);
    public SelenideElement translaterOutput = $$x(".//span[@class=\"tlid-translation translation\"]/span").find(visible);

    public SelenideElement vendorResponse = $$x(".//td[text()='Vendor:']").find(visible);
    public SelenideElement titleResponse = $$x(".//td[text()='Title:']").find(visible);
    public SelenideElement artifactResponse = $$x(".//td[text()='Artifact:']").find(visible);
    public SelenideElement versionResponse = $$x(".//td[text()='Version:']").find(visible);
    public SelenideElement nameResponse = $$x(".//td[text()='Name:']").find(visible);
    public SelenideElement urlResponse = $$x(".//td[text()='URL:']").find(visible);
    public SelenideElement downloadResponse = $$x(".//td[text()='Download:']").find(visible);
    public SelenideElement sizeResponse = $$x(".//td[text()='Size:']").find(visible);
    public SelenideElement someContainedClassesResponse = $$x(".//td[text()='Some contained classes:']").find(visible);

    public ElementsCollection resultList = $$(By.xpath(".//span[text()='[JAR]']/..//a"));


}


