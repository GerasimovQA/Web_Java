package Organization.CreateCabinet;

import Global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateCabinetPage extends GlobalPage {

    public CreateCabinetPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int u = 0;

    // ГЛОБАЛЬНЫЕ ЭЛЕМЕНТЫ
    //Вкладка "Кабинеты и рабочие места"
    @FindBy(xpath = "//span[text()='Кабинеты и рабочие места']")
    public WebElement tabCabinetsndWorkplacesEdit;

//    -------------------------------------------Список кабинетов--------------------------------------------------
    //Кнопка "Добавить кабинет"
    @FindBy(xpath = "//span[contains (text(), 'Добавить кабинет')]")
    public WebElement buttonAddCabinetInList;

    //Поле ввода "Название/номер"
    @FindBy(xpath = "//div[contains (text(), 'Название/номер *')]/../input")
    public WebElement inputNameNumberCabinet;

    //Поле ввода "Описание"
    @FindBy(xpath = "//div[contains (text(), 'Описание')]/following-sibling::div//textarea")
    public WebElement inputDescriptionCabinet;

    //Ссылка "Добавить рабочее место"
    @FindBy(xpath = "//div[contains (text(), 'Добавить рабочее место')]")
    public WebElement linkAddWorplaceCabinet;

    //Поле ввода "Название" рабочего места
    @FindBy(xpath = "//div[contains (text(), \"Рабочие места\")]/following-sibling::div//input")
    public List <WebElement> inputNameWorplaceCabinet;

    //Кнопка "Добавить"
    @FindBy(xpath = "//button/span[contains (text(), 'Добавить')]")
    public WebElement buttonAddCabinet;

    //Названия кабинетов в списке
    @FindBy(xpath = "//div[@class=\"cabinets-tree__name isNode\"]/span")
    public List <WebElement> listNameCabinet;

    //Названия рабочих мест в списке
    @FindBy(xpath = "//div[@class=\"cabinets-tree__name\"]/span")
    public List <WebElement> listNameWorkplace;

//    //Название тестов
//    @FindBy(xpath = "//div[@class=\"session-cap session-cap__name\"]")
//    public List <WebElement> ListTest;




























}





