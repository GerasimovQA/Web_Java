package Specialty.EditSpecialty;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditSpecialtyPage extends GlobalPage {

    public EditSpecialtyPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//-------------------------------------------- Создание специальности -----------------------------------------------
    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[text()=\"Медицинские специальности\"]")
    public WebElement sectionMedSpecialty;

    //   Раздел "НЕмедицинские специальности"
    @FindBy(xpath = "//span[text()=\"Немедицинские специальности\"]")
    public WebElement sectionNotMedSpecialty;

       //   Значок специальности
    @FindBy(xpath = "//img[contains(@src,\"/img/specialties/\")]")
    public List<WebElement> iconSpecialty;

    //-------------------------------------------- Редактирование специальности----------------------------------------

    //   Ссылка "Изменить название"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/span[contains(text(),\"Изменить\")]")
    public WebElement linkEditNameSpecialty;

    //   Поле ввода "название"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/div/input")
    public WebElement inputNewNameSpecialty;

    //   Кнопка "Сохранить название"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNewNameSpecialty;

    //   Кнопка "Отменить сохранение название"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNewNameSpecialty;

    //   Ссылка "Изменить раздел"
    @FindBy(xpath = "//div[contains(text(),\"Раздел\")]/following-sibling::div/span[contains(text(),\"Изменить\")]")
    public WebElement linkEditNameSectionSpecialty;

    //   Выпадающий список "Раздел специальностей"
    @FindBy(xpath = "//div[contains(text(),\"Раздел\")]/following-sibling::div/div/div")
    public WebElement fieldEditSectionSpecialty;

    //   Кнопка "Сохранить раздел"
    @FindBy(xpath = "//div[contains(text(),\"Раздел\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNewectionSpecialty;

    //   Кнопка "Отменить сохранение название"
    @FindBy(xpath = "//div[contains(text(),\"Раздел\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNewSectionSpecialty;

    //   Ссылка "Изменить описание"
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/span[contains(text(),\"Изменить\")]")
    public WebElement linkEditNameDescriptionSpecialty;

    //   Поле ввода "описание"
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/div/textarea")
    public WebElement inputDescriptionSpecialty;

    //   Кнопка "Сохранить описание"
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNewDescriptionSpecialty;

    //   Кнопка "Отменить сохранение описания"
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNewDescriptionSpecialty;

    //   Ссылка "Изменить значок"
    @FindBy(xpath = "//div[contains(text(),\"Значок специальности\")]/following-sibling::div/" +
            "span[contains(text(),\"Изменить\")]")
    public WebElement linkEditIconSpecialty;

    //   Поле выбора значка"
    @FindBy(xpath = "//div[contains(text(),\"Значок специальности\")]/following-sibling::div//" +
            "div[contains(text(),\"Выберите значок\")]")
    public WebElement inputNewDescriptionSpecialty;

    //   Кнопка "Сохранить значок"
    @FindBy(xpath = "//div[contains(text(),\"Значок специальности\")]/following-sibling::div/" +
            "button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNewIconSpecialty;

    //   Кнопка "Отменить сохранение значка"
    @FindBy(xpath = "//div[contains(text(),\"Значок специальности\")]/following-sibling::div/" +
            "button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNewIconSpecialty;

    //   Ссылка "Удалить специальность"
    @FindBy(xpath = "//span[text()=\"Удалить специальность\"]")
    public WebElement linkDeleteSpecialty;

    //   Кнопка "Удалить"
    @FindBy(xpath = "//span[contains(text(),\"Удалить специальность?\")]/../../" +
            "following-sibling::div//span[contains(text(),\"Удалить\")]")
    public WebElement buttonDeleteSpecialty;

    //   Кнопка "Омена Удаления"
    @FindBy(xpath = "//span[contains(text(),\"Удалить специальность?\")]/../../" +
            "following-sibling::div//span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelDeleteSpecialty;
}



