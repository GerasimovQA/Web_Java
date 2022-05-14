package ru.factorts.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.$x;

public class ResourceMonitor extends BasePage {

    SelenideElement maxSizeMemory = $x(".//*[text()='Максимум']/following-sibling::div/span");


    @Step
    public void checkMaxSizeMemoryInResourceMonitor(String mem) {
        click(resourceMonitorTab);
        Assert.assertEquals(mem, maxSizeMemory.getText());
    }
}
