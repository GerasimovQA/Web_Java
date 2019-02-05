package Service.EditService;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditServicePage extends GlobalPage {

    public EditServicePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //-------------------------------------------- Редактирование услуги -----------------------------------------------
    //  Ссылка "Изменить Название услуги"
    @FindBy(xpath = "(//div[contains(text(),\"Название\")]//following-sibling::div//span[contains(text(),\"Изменить\")])[1]")
    public WebElement linkChangeNameService;

    //   Поле ввода "Название услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::input")
    public WebElement inputNameServiceEdit;

    //   Кнопка "Сохранить Название услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNameServiceEdit;

    //   Кнопка "Отмена Сохрания Название услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNameServiceEdit;

    //  Ссылка "Изменить Название для печати"
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeNameForPrintService;

    //   Поле ввода "Название для печати" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::input")
    public WebElement inputNameForPrintServiceEdit;

    //   Кнопка "Сохранить Название для печати услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveNameForPrintServiceEdit;

    //    Кнопка "Отмена Сохрания Названия для печати услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Название для печати\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveNameForPrintServiceEdit;

    //  Ссылка "Изменить Код услуги"
    @FindBy(xpath = "//div[contains(text(),\"Код услуги\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeCodeService;

    //   Поле ввода "Код услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Код услуги\")]/following-sibling::input")
    public WebElement inputCodeServiceEdit;

    //   Кнопка "Сохранить Код услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Код услуги\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveCodeServiceEdit;

    //    Кнопка "Отмена Сохрания Код услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Код услуги\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveCodeServiceEdit;

    //  Ссылка "Изменить Артикул услуги"
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeVendorService;

    //   Поле ввода "Артикул услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::input")
    public WebElement inputVendorrServiceEdit;

    //   Кнопка "Сохранить Артикул услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveVendorServiceEdit;

    //    Кнопка "Отмена Сохрания Артикул услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Артикул\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveVendorServiceEdit;

    //  Ссылка "Изменить Раздел услуги"
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeSectionService;

    //   Название главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div//div[@style=\"padding-left: 0px;\"]//span[@class=\"el-tree-node__label\"]")
    public WebElement mainSectionServiceNameEdit;

    //   Буллет главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div//label/span/span")
    public WebElement bulletSectionServiceEdit;

    //   Названия подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div/div//div[@role=\"group\"]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> nameSubsectionServiceEdit;

    //   Буллеты подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div/div//div[@role=\"group\"]//label/span/span")
    public List<WebElement> bulletSubsectionServiceEdit;

    //   Кнопка "Сохранить Раздел услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveSectionServiceEdit;

    //    Кнопка "Отмена Сохрания Раздела услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Раздел услуги\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveSectionServiceEdit;

    //  Ссылка "Изменить Стоимость услуги"
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeCostService;

    //   Поле ввода "Стоимсть ОМС услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"ОМС\")]/following-sibling::input")
    public WebElement inputCostOMSServiceEdit;

    //   Поле ввода ""Стоимсть ДМС услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"ДМС\")]/following-sibling::input")
    public WebElement inputCostDMSServiceEdit;

    //   Поле ввода ""Стоимсть ПМУ услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"ПМУ\")]/following-sibling::input")
    public WebElement inputCostPMUServiceEdit;

    //   Ссылка"Добавить стоимость" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Добавить стоимость\")]")
    public WebElement linkAddCostEdit;

    //   Поле ввода "Название дополнительной стоимости услуги" при редактировании
    @FindBy(xpath = "//input[@placeholder=\"Название\"]")
    public WebElement inputNameAdditionalCostServiceEdit;

    //   Поле ввода "Цена дополнительной стоимости услуги" при редактировании
    @FindBy(xpath = "//input[@placeholder=\"Цена\"]")
    public WebElement inputCostAdditionalCostServiceEdit;

    //    Ссылка "Удалить дополнительную стоимость услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]//following-sibling::div//span[contains(text(),\"Удалить\")]")
    public WebElement linkDeleteAdditionalCostServiceEdit;

    //   Кнопка "Сохранить Стоимость услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveCostServiceEdit;

    //    Кнопка "Отмена Сохрания Стоимости услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Стоимость\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveCostServiceEdit;

    //  Ссылка "Изменить Характер услуги"
    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeCharacterService;

    // Выпадающий список "Характер услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]/following-sibling::div//input[@placeholder=\"Характер\"]")
    public WebElement characterServicEdit;

    // Список "Характеров" услуги при редактировании
    @FindBy(xpath = "//li[@class=\"el-select-dropdown__item\"]/span")
    public List<WebElement> listCharacterServiceEdit;

    //   Кнопка "Сохранить Характер услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveCharacterServiceEdit;

    //    Кнопка "Отмена Сохрания Характероа услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Характер услуги\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveCharacterServiceEdit;

    //  Ссылка "Изменить Описание услуги"
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeDesriptionService;

    //   Поле ввода "Описание" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div//textarea")
    public WebElement inputDescriptionServiceEdit;

    //   Кнопка "Сохранить Описание услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveDescriptionServiceEdit;

    //    Кнопка "Отмена Сохрания Описания услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Описание\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveDescriptionServiceEdit;

    //  Ссылка "Изменить Предусловия услуги"
    @FindBy(xpath = "//div[contains(text(),\"Предусловия\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangePreconditionService;

    //   Поле ввода "Предусловия" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Предусловия\")]/following-sibling::div//textarea")
    public WebElement inputPreconditionServiceEdit;

    //   Кнопка "Сохранить Предусловия услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Предусловия\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSavePreconditionServiceEdit;

    //    Кнопка "Отмена Сохрания Предусловия услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Предусловия\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSavePreconditionServiceEdit;

    //  Ссылка "Изменить Противопоказания услуги"
    @FindBy(xpath = "//div[contains(text(),\"Противопоказания\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeContraindicationService;

    //   Поле ввода "Противопоказания" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Противопоказания\")]/following-sibling::div//textarea")
    public WebElement inputContraindicationServiceEdit;

    //   Кнопка "Сохранить Противопоказания услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Противопоказания\")]/following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveContraindicationServiceEdit;

    //    Кнопка "Отмена Сохрания Противопоказания услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Противопоказания\")]/following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveContraindicationServiceEdit;

    //  Ссылка "Изменить Возможность записи услуги"
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента\")]" +
            "//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeRecordService;

    //   Свитчер "Возможность записи" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента\")]/../..//div[@role=\"switch\"]")
    public WebElement switchRecordServiceEdit;

    //   Кнопка "Сохранить Возможность записи услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента\")]/" +
            "following-sibling::div/button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveRecordServiceEdit;

    //    Кнопка "Отмена Сохрания Возможности записи услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Возможность записи из интерфейса пациента\")]/" +
            "following-sibling::div/button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveRecordServiceEdit;

    //  Ссылка "Изменить Избранность услуги"
    @FindBy(xpath = "//div[contains(text(),\"Избранная услуга\")]//following-sibling::div//span[contains(text(),\"Изменить\")]")
    public WebElement linkChangeChosenRecordServiceEdit;

    //   Чек-бокс "Избраннвя услуга" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Избранная услуга\")]/..//span[@class=\"el-checkbox__input\"]")
    public WebElement checkBoxChosenServiceEdit;

    //   Кнопка "Сохранить Возможность записи услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Избранная услуга\")]/..//button/span[contains(text(),\"Сохранить\")]")
    public WebElement buttonSaveChosenServiceEdit;

    //    Кнопка "Отмена Сохрания Возможности записи услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),\"Избранная услуга\")]/..//button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelSaveChosenServiceEdit;

    //    Кнопка "Отмена Сохрания Возможности записи услуги" при редактировании
    @FindBy(xpath = "//span[contains(text(),\"Редактировать услугу:\")]/following-sibling::a")
    public WebElement linkNameService;

    //   Ссылка "Удалить слугу"
    @FindBy(xpath = "//span[text()=\"Удалить услугу\"]")
    public WebElement linkDeleteSrvice;

    //   Кнопка "Удалить"
    @FindBy(xpath = "//span[contains(text(),\"Удалить услугу?\")]/../../following-sibling::div//span[contains(text(),\"Удалить\")]")
    public WebElement buttonDeleteService;

    //   Кнопка "Омена Удаления"
    @FindBy(xpath = "//span[contains(text(),\"Удалить услугу?\")]/../../following-sibling::div//span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelDeleteService;

    //-------------------------------------------- Редаетирование раздела услуг -------------------------------------------
    //  Ссылка "Изменить Название раздела услуг"
    @FindBy(xpath = "(//div[contains(text(),'Название')]//following-sibling::div//span[contains(text(),'Изменить')])[1]")
    public WebElement linkChangeNameSectionService;

    //   Поле ввода "Название раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название')]/following-sibling::div//input")
    public WebElement inputNameSectionServiceEdit;

    //   Кнопка "Сохранить Название раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название')]/following-sibling::div/button/span[contains(text(),'Сохранить')]")
    public WebElement buttonSaveNameSectionServiceEdit;

    //   Кнопка "Отмена Сохрания Названия раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название')]/following-sibling::div/button/span[contains(text(),'Отмена')]")
    public WebElement buttonCancelSaveNameSectionServiceEdit;

    //  Ссылка "Изменить Название раздела услуг для печати"
    @FindBy(xpath = "//div[contains(text(),'Название для печати')]//following-sibling::div//span[contains(text(),'Изменить')]")
    public WebElement linkChangeNameForPrintSectionService;

    //   Поле ввода "Название раздела услуг для печати" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название для печати')]/following-sibling::div//input")
    public WebElement inputNameSectionForPrintServiceEdit;

    //   Кнопка "Сохранить Название раздела услуг для печати" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название для печати')]/following-sibling::div/button/span[contains(text(),'Сохранить')]")
    public WebElement buttonSaveNameSectionForPrintServiceEdit;

    //   Кнопка "Отмена Сохрания Название раздела услуг для печати" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Название для печати')]/following-sibling::div/button/span[contains(text(),'Отмена')]")
    public WebElement buttonCancelSaveNameForPrintSectionServiceEdit;

    //  Ссылка "Изменить Код раздела услуг"
    @FindBy(xpath = "//div[contains(text(),'Код раздела услуг')]//following-sibling::div//span[contains(text(),'Изменить')]")
    public WebElement linkChangeCodeSectionService;

    //   Поле ввода "Код раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Код раздела услуг')]/following-sibling::div//input")
    public WebElement inputCodeSectionServiceEdit;

    //   Кнопка "Сохранить Код раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Код раздела услуг')]/following-sibling::div/button/span[contains(text(),'Сохранить')]")
    public WebElement buttonSaveCodeSectionServiceEdit;

    //    Кнопка "Отмена Сохрания Кода раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Код раздела услуг')]/following-sibling::div/button/span[contains(text(),'Отмена')]")
    public WebElement buttonCancelSaveCodeSectionServiceEdit;

    //  Ссылка "Изменить Артикул раздела услуг"
    @FindBy(xpath = "//div[contains(text(),'Артикул')]//following-sibling::div//span[contains(text(),'Изменить')]")
    public WebElement linkChangeVendorSectionService;

    //   Поле ввода "Артикул раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Артикул')]/following-sibling::div//input")
    public WebElement inputVendorSectionServiceEdit;

    //   Кнопка "Сохранить Артикул раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Артикул')]/following-sibling::div/button/span[contains(text(),'Сохранить')]")
    public WebElement buttonSaveVendorSectionServiceEdit;

    //    Кнопка "Отмена Сохрания Артикул раздела услуг" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Артикул')]/following-sibling::div/button/span[contains(text(),'Отмена')]")
    public WebElement buttonCancelSaveVendorSectionServiceEdit;

    //  Ссылка "Изменить Головной Раздел раздела услуги"
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]//following-sibling::div//span[contains(text(),'Изменить')]")
    public WebElement linkChangeHeadSectionForSectionService;

    //   Название главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div//div[@style='padding-left: 0px;']//span[@class='el-tree-node__label']")
    public WebElement mainSectionServiceNameEditForSectionService;

    //   Буллет главного раздела услуг
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div//label/span/span")
    public WebElement bulletSectionServiceEdittForSectionService;

    //   Названия подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div/div//div[@role='group']//span[@class='el-tree-node__label']")
    public List<WebElement> nameSubsectionServiceEditForSectionService;

    //   Буллеты подразделов усдуг
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div/div//div[@role='group']//label/span/span")
    public List<WebElement> bulletSubsectionServiceEditForSectionService;

    //   Кнопка "Сохранить Раздел услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div/button/span[contains(text(),'Сохранить')]")
    public WebElement buttonSaveSectionServiceEditForSectionService;

    //    Кнопка "Отмена Сохрания Раздела услуги" при редактировании
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]/following-sibling::div/button/span[contains(text(),'Отмена')]")
    public WebElement buttonCancelSaveSectionServiceEditForSectionService;

    //---------------------------------------Проверка сохраненных данных-------------------------------------------------
    //  Название раздела услуг
    @FindBy(xpath = "(//div[contains(text(),'Название')]//following-sibling::div)[1]")
    public WebElement nameSectionServiceEdit;

    //  Название раздела услуг для печати
    @FindBy(xpath = "//div[contains(text(),'Название для печати')]//following-sibling::div[1]")
    public WebElement nameForPrintSectionServiceEdit;

    //  Код раздела услуг
    @FindBy(xpath = "//div[contains(text(),'Код раздела услуг')]//following-sibling::div[1]")
    public WebElement codeSectionServiceEdit;

    //  Артикул раздела услуг
    @FindBy(xpath = "//div[contains(text(),'Артикул')]//following-sibling::div[1]")
    public WebElement vendorSectionServiceEdit;

    //  Головной Раздел услуги для раздела услуг
    @FindBy(xpath = "//div[contains(text(),'Раздел услуги')]//following-sibling::div[1]")
    public WebElement headSectionServiceForSectionServiceEdit;
}