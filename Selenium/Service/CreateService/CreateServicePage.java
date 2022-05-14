package Service.CreateService;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateServicePage extends GlobalPage {

    public CreateServicePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //-------------------------------------------- Создание услуги -----------------------------------------------
    //   Поле ввода "Название услуги"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::input[@placeholder=\"Название услуги *\"]")
    public WebElement inputNameService;

    //   Поле ввода услуги "Название для печати"
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::" +
            "input[@placeholder=\"Название для печати\"]")
    public WebElement inputNameServiceForPrint;

    //   Поле ввода услуги "Код"
    @FindBy(xpath = "//div[contains(text(),\"Код\")]/following-sibling::input[@placeholder=\"Код *\"]")
    public WebElement inputCodeService;

    //   Поле ввода услуги "Артикул"
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::input[@placeholder=\"Артикул *\"]")
    public WebElement inputVendorService;

    //   Название главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//" +
            "div[@style=\"padding-left: 0px;\"]//span[@class=\"el-tree-node__label\"]")
    public WebElement mainSectionServiceName;

    //   Чек-бокс главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//label/span/span")
    public WebElement checkBoxSectionService;

    //   Чек-боксы подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/div//" +
            "div[@role=\"group\"]//label/span/span")
    public List<WebElement> checkBoxSubsectionService;

    //   Названия подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/div//div[@role=\"group\"]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> nameSubsectionService;

    //   Поле ввода стоимости "ОМС"
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div/input[@placeholder=\"ОМС\"]")
    public WebElement inputCostOMS;

    //   Поле ввода стоимости "ДМС"
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div/input[@placeholder=\"ДМС\"]")
    public WebElement inputCostDMS;

    //   Поле ввода стоимости "ПМУ"
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div/input[@placeholder=\"ПМУ\"]")
    public WebElement inputCostPMU;

    //   Ссылка"Добавить стоимость"
    @FindBy(xpath = "//div[contains(text(),\"Добавить стоимость\")]")
    public WebElement linkAddCost;

    //   Поле ввода "Название" добавляемой стоимости
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div//input[@placeholder=\"Название\"]")
    public WebElement inputNewCostName;

    //   Поле ввода "Цена" добавляемой стоимости
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div//input[@placeholder=\"Цена\"]")
    public WebElement inputNewCostSum;

    //   Ссылка"Удалить стоимость"
    @FindBy(xpath = "//span[contains(text(),\"Удалить\")]")
    public WebElement linkDeleteCost;

    // Выпадающий список "Характер услуги"
    @FindBy(xpath = "//div[contains(text(),\"Характер медуслуги\")]/following-sibling::div//" +
            "input[@placeholder=\"Характер\"]")
    public WebElement characterService;

    // Список "Характеров" услуги
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item\"]/span")
    public List<WebElement> listCharacterService;

    // Поле ввода Описание услуги
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/textarea")
    public WebElement inputDescriptionService;

    // Поле ввода Противопоказания
    @FindBy(xpath = "//div[contains(text(),\"Противопоказания\")]/following-sibling::div/textarea")
    public WebElement inputContraindicationService;

    // Поле ввода Предусловия
    @FindBy(xpath = "//div[contains(text(),\"Предусловия\")]/following-sibling::div/textarea")
    public WebElement inputPreconditionService;

    // Преключатель "Возможность записи из интерфейса пациента"
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента\")]/../..//div[@role=\"switch\"]")
    public WebElement switchRecordService;

    // Преключатель "Избранная услуга"
    @FindBy(xpath = "//div[contains(text(),\"Избранная услуга\")]/../..//div[@role=\"switch\"]")
    public WebElement switchChosenRecordService;

    // Кнопка "Добавить"
    @FindBy(xpath = "//button/span[contains(text(),\"Добавить\")]")
    public WebElement buttonAddService;

    //-------------------------------------------- Создание раздела услуг-----------------------------------------------
    // Поле ввода раздела услуг "Название раздела"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div//input[@placeholder=\"Название раздела\"]")
    public WebElement inputNameSectionService;

    // Поле ввода раздела услуг "Название для печати"
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::div//" +
            "input[@placeholder=\"Название для печати\"]")
    public WebElement inputNameSectionServiceForPrint;

    //   Поле ввода раздела услуг "Код"
    @FindBy(xpath = "//div[contains(text(),\"Код раздела\")]/following-sibling::div//input[@placeholder=\"Код\"]")
    public WebElement inputCodeSectionService;

    //   Поле ввода раздела услуг "Артикул"
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::div//input[@placeholder=\"Артикул\"]")
    public WebElement inputVendorSectionService;

    //   Название главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//div[@style=\"padding-left: 0px;\"]//span[@class=\"el-tree-node__label\"]")
    public WebElement mainSectionServiceNameForSection;

    //   Буллет главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//label/span/span")
    public WebElement bulletSectionServiceForSection;

    //   Названия подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/div//div[@role=\"group\"]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> nameSubsectionServiceForSection;

    //   Буллеты подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/" +
            "div//div[@role=\"group\"]//label/span/span")
    public List<WebElement> bulletSubsectionServiceForSection;

    // Кнопка добавить раздел услуг
    @FindBy(xpath = "//button/span[contains(text(),\"Добавить\")]")
    public WebElement buttonAddSectionService;
}



