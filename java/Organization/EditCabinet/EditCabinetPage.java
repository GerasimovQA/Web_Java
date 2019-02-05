package Organization.EditCabinet;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditCabinetPage extends GlobalPage {

    public EditCabinetPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int u = 0;

    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
    //Вкладка "Кабинеты и рабочие места"
    @FindBy(xpath = "//span[text()='Кабинеты и рабочие места']")
    public WebElement tabCabinetsndWorkplacesEdit;

    //    -------------------------------------------Редактирование кабинета------------------------------------------------
    //Ссылка "Изменить" название кабинета
    @FindBy(xpath = "//div[text()='Название']/following-sibling::div/span[text()='Изменить']")
    public WebElement linkEditNameCabinet;

    //   Поле ввода "Название кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::input")
    public WebElement inputNameCabinetEdit;

    //   Кнопка "Сохранить Название кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNameCabinetEdit;

    //   Кнопка "Отмена Сохрания Название кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNameCabinetEdit;

    //Ссылка "Изменить" описание кабинета
    @FindBy(xpath = "//div[text()='Описание']/following-sibling::div/span[text()='Изменить']")
    public WebElement linkEditDescriptionCabinet;

    //   Поле ввода "Описание кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div//textarea")
    public WebElement inputDescriptionCabinetEdit;

    //   Кнопка "Сохранить описание кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveDescriptionCabinetEdit;

    //   Кнопка "Отмена Сохрания описания кабинета" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveDescriptionCabinetEdit;

    //Иконка "Изменить" название рабочего места
    @FindBy(xpath = "//img[contains(@src,\"icon_dropmenu_edit\")]")
    public List<WebElement> iconEditNameWorkplace;

    //Иконка "Удалить" рабочее место
    @FindBy(xpath = "//img[contains(@src,\"icon_dropmenu_trash\")]")
    public List<WebElement> iconDeleteWorkplaceEdit;



    //Ссылка "Добавить" рабочее место
    @FindBy(xpath = "//div[contains(text(),\"Рабочие места\")]/following-sibling::div//span[contains(text(),\"Добавить\")]")
    public List<WebElement> iconAddWorkplaceEdit;

    //   Поле ввода "Название рабочего места" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Рабочие места\")]/following-sibling::div//input")
    public List<WebElement> inputNameWorkplaceEdit;

    //   Кнопка "Сохранить рабочее место" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Рабочие места\")]/following-sibling::div//span[contains(text(),\"Сохранить\")]")
    public List<WebElement> buttonSaveWorkplaceEdit;

    //   Кнопка "Отмена Сохрания рабочего места" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Рабочие места\")]/following-sibling::div//span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveWorkplaceEdit;

    //   Ссылка "Удалить кабинет" при редактировании
    @FindBy(xpath = "//div[@class=\"room-edit__container\"]//span[contains(text(),\"Удалить кабинет\")]")
    public WebElement linkDeleteCabinetEdit;


//    //Кнопка "Добавить кабинет"
//    @FindBy(xpath = "//span[contains (text(), 'Добавить кабинет')]")
//    public WebElement buttonAddCabinetInList;
//
//    //Поле ввода "Название/номер"
//    @FindBy(xpath = "//div[contains (text(), 'Название/номер *')]/../input")
//    public WebElement inputNameNumberCabinet;
//
//    //Поле ввода "Описание"
//    @FindBy(xpath = "//div[contains (text(), 'Описание')]/following-sibling::div//textarea")
//    public WebElement inputDescriptionCabinet;
//
//    //Ссылка "Добавить рабочее место"
//    @FindBy(xpath = "//div[contains (text(), 'Добавить рабочее место')]")
//    public WebElement linkAddWorplaceCabinet;
//
//    //Поле ввода "Название" рабочего места
//    @FindBy(xpath = "//div[contains (text(), \"Рабочие места\")]/following-sibling::div//input")
//    public List<WebElement> inputNameWorplaceCabinet;
//
//    //Кнопка "Добавить"
//    @FindBy(xpath = "//button/span[contains (text(), 'Добавить')]")
//    public WebElement buttonAddCabinet;
//

//    //Название тестов
//    @FindBy(xpath = "//div[@class=\"session-cap session-cap__name\"]")
//    public List <WebElement> ListTest;


}





