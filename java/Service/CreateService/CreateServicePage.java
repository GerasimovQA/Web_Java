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

//    int u = 0;
//    int SumSpecialty = 0;
//    int SumAmbulance = 0;
//    int RandomWork1 = 0;
//    int RandomWork2 = 0;

//    ArrayList<String> SelectedSpecialtys = new ArrayList<>();
//    ArrayList<String> SelectedOrg = new ArrayList<>();
//    ArrayList<String> SelectedPositions = new ArrayList<>();
//    ArrayList<String> SelectedRoles = new ArrayList<>();
//    ArrayList<String> SelectedAmbulance = new ArrayList<>();

    //-------------------------------------------- Создание услуги -----------------------------------------------
    //   Поле ввода "Название услуги"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::input[@placeholder=\"Название услуги *\"]")
    public WebElement inputNameService;

    //   Поле ввода услуги "Название для печати"
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::input[@placeholder=\"Название для печати\"]")
    public WebElement inputNameServiceForPrint;

    //   Поле ввода услуги "Код"
    @FindBy(xpath = "//div[contains(text(),\"Код\")]/following-sibling::input[@placeholder=\"Код *\"]")
    public WebElement inputCodeService;

    //   Поле ввода услуги "Артикул"
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::input[@placeholder=\"Артикул *\"]")
    public WebElement inputVendorService;

    //   Название главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//div[@style=\"padding-left: 0px;\"]//span[@class=\"el-tree-node__label\"]")
    public WebElement mainSectionServiceName;

    //   Чек-бокс главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div//label/span/span")
    public WebElement checkBoxSectionService;

    //   Чек-боксы подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/div//div[@role=\"group\"]//label/span/span")
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
    @FindBy(xpath = "//div[contains(text(),\"Характер медуслуги\")]/following-sibling::div//input[@placeholder=\"Характер\"]")
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


//    //-------------------------------------------- Карточка услуги-----------------------------------------------
//    // Название услуги
//    @FindBy(xpath = "//div[@class=\"info-box__title\"]")
//    public WebElement nameServiceProf;
//
//    // Название услуги для печати
//    @FindBy(xpath = "//div[contains(text(),\"Наименование для печати:\")]/span")
//    public WebElement nameServicePrintProf;
//
//    // Возможность записи
//    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента:\")]/span")
//    public WebElement recordServiceProf;
//
//    // Характер услуги
//    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]/span")
//    public WebElement characterServiceProf;
//
//    // Противопоказания к услуге
//    @FindBy(xpath = "//span[contains(text(),\"Противопоказания\")]/../../../following-sibling::div//" +
//            "div[@class=\"contraindications-list\"]")
//    public WebElement contraindicationServiceProf;
//
//    // Предусловия к услуге
//    @FindBy(xpath = "//span[contains(text(),\"Предусловия\")]/../../../following-sibling::div//" +
//            "div[@class=\"preconditions-list\"]")
//    public WebElement preconditionServiceProf;
//
//    // Описание услуги
//    @FindBy(xpath = "//span[contains(text(),\"Описание услуги\")]/../../../following-sibling::div" +
//            "//div[@class=\"dep-description__text\"]")
//    public WebElement descriptionServiceProf;
//
//    // Стоимость ОМС
//    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ОМС\")]/div)[1]")
//    public WebElement costOMSProf;
//
//    // Стоимость ДМС
//    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ДМС\")]/div)[1]")
//    public WebElement costDMSProf;
//
//    // Стоимость ПМУ
//    @FindBy(xpath = "(//div[contains(text(),\"Стоимость ПМУ\")]/div)[1]")
//    public WebElement costPMUProf;
//
//    // Кнопка "Редактировать услугу"
//    @FindBy(xpath = "(//div[@class=\"service-edit__button\"])[1]")
//    public WebElement buttonEditServiceProf;
//
//    // Код услуги
//    @FindBy(xpath = "//div[contains(text(),\"Код(ы) услуги\")]/div")
//    public WebElement сodeServiceProf;
//
//    // Артикул услуги
//    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/div")
//    public WebElement vendorServiceProf;
//
//    //-------------------------------------------- Список услуг-----------------------------------------------
//    // Главный раздел услуг
//    @FindBy(xpath = "//div[@class=\"el-tree-node__content\"][@style=\"padding-left: 18px;\"]//span[@class=\"services-tree__node-name\"]")
//    public WebElement mainSectionServiceInList;
//
//    // Услуги второго уровня
//    @FindBy(xpath = "//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span")
//    public List<WebElement> sectionService2LevInList;
//
//    // Услуги третьего уровня
//    @FindBy(xpath = "//div[@style=\"padding-left: 54px;\"]//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div[1]")
//    public List<WebElement> sectionService3LevInList;

    //-------------------------------------------- Создание раздела услуг-----------------------------------------------
    // Поле ввода раздела услуг "Название раздела"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div//input[@placeholder=\"Название раздела\"]")
    public WebElement inputNameSectionService;

    // Поле ввода раздела услуг "Название для печати"
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::div//input[@placeholder=\"Название для печати\"]")
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
    @FindBy(xpath = "//div[contains(text(),\" Выберите раздел услуг\")]/following-sibling::div/div//div[@role=\"group\"]//label/span/span")
    public List<WebElement> bulletSubsectionServiceForSection;

    // Кнопка добавить раздел услуг
    @FindBy(xpath = "//button/span[contains(text(),\"Добавить\")]")
    public WebElement buttonAddSectionService;
}



