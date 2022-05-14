package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class IndexPage extends BasePage {

    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));
    public ElementsCollection listIpAdresses = $$(By.xpath(".//td[text()='IP адреса']/following-sibling::th/ul/li"));
    public SelenideElement headerGeneralInformation = $x(".//div[text()='Общая информация']"),
            pathToFESB = $x(".//td[text()='Путь установки']/following-sibling::th"),
            version = $x(".//td[contains(text(),'Версия продукта')]/following-sibling::th"),
            header2 = headers.get(1),
            header3 = headers.get(2);

    public IndexPage() {
        click(generalInformationTab);
        headerGeneralInformation.shouldHave(Condition.text("Общая информация"));
    }

    public IndexPage(String Empty) {
    }

    public String getPathFESB() {
        return pathToFESB.getText();
    }

    public String getVersionFESB() {
        return version.getText().split(" ")[0];
    }

    public void checkVersionFESB(String Ver) {
        compareStringAndElementText(Ver + " Проверить КС",version);
    }

    public void checkIpAdressInListAdresses(String adress) {
        int x = 0;
        sout(listIpAdresses.toString());
        for (SelenideElement oneOfAdress : listIpAdresses) {
            if (oneOfAdress.getText().contains(adress)) {
                x = 1;
            }
        }
        if (x > 0) {
            sout("Адрес найден в списке");
        } else {
            throw new AssertionError("Адрес " + adress + " НЕнайден в списке");
        }
    }
}


