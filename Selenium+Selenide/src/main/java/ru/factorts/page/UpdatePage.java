package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class UpdatePage extends BasePage {

    CreationSOPSPage creationSOPSPage;
    CommonComponents commonComponents = new CommonComponents();

    SelenideElement systemManagementTab = $x(".//a[text()='Управление системой']");
    SelenideElement updateButton = $x(".//span[text()='Обновление FESB']/..");
    SelenideElement updateButtonOld = $x(".//label[text()=' Обновить FESB']");
    SelenideElement createRestorePointCheckBox = $x(".//span[text()='Создать точку восстановления перед обновлением']/..//span[@class=\"ant-checkbox\"]");

    SelenideElement uploadUpdateButton = $x(".//span[text()='Обновить']/..");
    SelenideElement uploadUpdateInput = $x(".//p[text()='Архив обновления']/../../..//span[@class=\"ant-upload ant-upload-btn\"]/input");
    SelenideElement updateESBButton = $x(".//span[text()='Обновить FESB']/..");
    SelenideElement restartESBButton = $x(".//div[text()='Обновление FESB']/../..//span[text()='Перезапустить FESB']/..");
    SelenideElement restartESB199Button = $x(".//span[text()='Перезагрузить FESB']/..");
    SelenideElement fullRestartFesbButton = $x(".//button[text()='Полный перезапуск FESB']");

    public enum RecoveryPoint {Yes, No}

    public void update(String oldVer, String lastVer, RecoveryPoint point) {
        sout(oldVer);
        if (!oldVer.equals("4.3.288") && !oldVer.equals("5.0.199") && !oldVer.contains("5.1.850")) {
            click(systemManagementTab);
            click(updateButton);
            if (point.equals(RecoveryPoint.Yes)) {
                click(createRestorePointCheckBox);
            }
            uploadUpdateInput.sendKeys("/share/fesb-" + lastVer + ".tar.gz");
            updateESBButton.waitUntil(Condition.enabled, 180000);
            click(updateESBButton);
            restartESBButton.waitUntil(Condition.enabled, 60000);
            click(restartESBButton);
        } else {
            click(systemManagementTab);
            if (oldVer.contains("5.1.850")) {
                click(updateButton);
            } else {
                click(updateButtonOld);
            }
            if (point.equals(RecoveryPoint.Yes)) {
                click(createRestorePointCheckBox);
            }
            uploadUpdateInput.sendKeys("/share/fesb-" + lastVer + ".tar.gz");
            updateESBButton.waitUntil(Condition.enabled, 180000);
            click(updateESBButton);
            restartESB199Button.waitUntil(Condition.enabled, 60000);
            click(restartESB199Button);
        }


    }


}
