package editorganization;

import global.GlobalPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditOrganizationPage extends GlobalPage {

    public EditOrganizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Кнопка "Сохранить"
    @FindBy(xpath = "//span[text()=\"Сохранить\"]")
    public WebElement buttonSave;

        // Кнопка "Отмена"
    @FindBy(xpath = "//span[text()=\"Отмена\"]")
    public WebElement buttonCancel;

    // Ссылка "Услуги"
    @FindBy(xpath = "//*[@id=\"tab-second\"][contains (text(),\"Услуги\")]")
    public WebElement linkServices;

    // Ссылка "Дополнительная информация"
    @FindBy(xpath = "//*[@id=\"tab-third\"][contains (text(),\"Дополнительная информация\")]")
    public WebElement linkAdditionalInformation;


    //    ---------------------------------------------Данные организации--------------------------------------------
    // Название организации
    @FindBy(xpath = "//div[contains (text(),\"Название организации\")]/following-sibling::div[1]")
    public WebElement nameOrganization;

    // Ссылка "Изменить" Название организации
    @FindBy(xpath = "//div[contains (text(),\"Название организации\")]/following-sibling::div[2]")
    public WebElement linkChangeNameOrganization;

    // Поле ввода "Название организации"
    @FindBy(css = "input[placeholder=\"Название организации\"]")
    public WebElement inputNameOrganization;

    // Сокращенное название организации
    @FindBy(xpath = "//div[contains (text(),\"Сокращенное название\")]/following-sibling::div[1]")
    public WebElement abbrNameOrganization;

    // Ссылка "Изменить" сокращенное название организации
    @FindBy(xpath = "//div[contains (text(),\"Сокращенное название\")]/following-sibling::div[2]")
    public WebElement linkChangeAbbrNameOrganization;

    // Поле ввода "Сокр. Название организации"
    @FindBy(css = "input[placeholder=\"Название организации\"]")
    public WebElement inputAbbrNameOrganization;

    // Руководитель организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div[1]")
    public WebElement directorOrganization;

    // Ссылка "Изменить" руководителя организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div[2]")
    public WebElement linkChangeDirectorOrganization;

    // Ссылка "Поиск сотрудника"
    @FindBy(xpath = "//span[contains (text(),\"Поиск сотрудника\")]")
    public WebElement searchDirectorOrganization;

    // Измененный руководитель организации
    @FindBy(xpath = "//div[contains (text(),\"Руководитель\")]/following-sibling::div/div/div/div")
    public WebElement newDirectorOrganization;

    // Организация входит в состав
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div[1]")
    public WebElement headOrganization;

    // Ссылка "Изменить" организация входит в состав
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div[2]")
    public WebElement linkChangeHeadOrganization;

    // Кнопка "Сохранить" головную организацию
    @FindBy(xpath = "//div[contains (text(),\"Входит в состав\")]/following-sibling::div/button/span[contains (text()" +
            ",\"Сохранить\")]")
    public WebElement buttonSaveHeadOrganization;

    // Условия оказания помощи
    @FindBy(xpath = "//div[contains (text(),\"Условия оказания помощи\")]/following-sibling::div[1]")
    public WebElement conditionsOrganization;

    // Ссылка "Изменить" Условия оказания помощи организации
    @FindBy(xpath = "//div[contains (text(),\"Условия оказания помощи\")]/following-sibling::div[2]")
    public WebElement linkChangeConditionsOrganization;

    // Основной профиль организации
    @FindBy(xpath = "//div[contains (text(),\"Основной профиль\")]/following-sibling::div[1]")
    public WebElement profileOrganization;

    // Ссылка "Изменить" основной профиль организации
    @FindBy(xpath = "//div[contains (text(),\"Основной профиль\")]/following-sibling::div[2]")
    public WebElement linkChangeProfileOrganization;

    // Поле ввода "Основной профиль" организации
    @FindBy(css = "input[placeholder=\"Основной профиль\"]")
    public WebElement inputProfileOrganization;

    // Ссылка "Удалить" подразделеие
    @FindBy(xpath = "//span[contains (text(),\"Удалить подразделение\")]")
    public WebElement linkDeleteOrganization;

    // Кнока "Удалить"
    @FindBy(xpath = "//button[2]/span[contains (text(),\"Удалить\")]")
    public WebElement buttonDelete;

    // Кнока "Отмена"
    @FindBy(xpath = "//button[1]/span[contains (text(),\"Отмена\")]")
    public WebElement buttonCancelDelete;


    //-------------------------___--Дополнительная информация-----------------------------------------------
    // Описание организации
    @FindBy(xpath = "//div[contains (text(),\"Описание подразделения\")]/following-sibling::div[1]")
    public WebElement descriptionOrganization;

    // Ссылка "Изменить" описание организации
    @FindBy(xpath = "//div[contains (text(),\"Описание подразделения\")]/following-sibling::div[2]")
    public WebElement linkChangeDescriptionOrganization;

    // Поле ввода "Описание организации"
    @FindBy(css = "div>[placeholder=\"Описание подразделения\"]")
    public WebElement inputDescriptionOrganization;

    // Назначение телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div[1]")
    public List<WebElement> appointmentPhoneOrganization;

    // Номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div[2]")
    public List<WebElement> numberPhoneOrganization;

    // Ссылка "Изменить" телефон
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/span[contains (text(),\"Изменить\")]")
    public WebElement linkChangePhoneOrganization;

    // Поле ввода "Назначение телефона"
    @FindBy(css = "div>[placeholder=\"Назначение телефона\"]")
    public List <WebElement> inputAppointmentPhone;

    // Поле ввода "Номер телефона"
    @FindBy(css = "div>[placeholder=\"Телефон\"]")
    public List <WebElement> inputNumberPhone;

    // Ссылка "Добавить" номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div/div//span[contains (text()," +
            "\"Добавить\")]")
    public WebElement linkAddPhone;

    // Ссылка "Удалить" номер телефона
    @FindBy(xpath = "//div[contains (text(),\"Телефоны\")]/following-sibling::div//span[contains (text(),\"Удалить\")]")
    public List <WebElement> linkDeletePhone;



    // Адрес организации
    @FindBy(xpath = "//div[contains (text(),\"Адрес\")]/following-sibling::div[1]")
    public WebElement adressOrganization;

    // Ссылка "Изменить" адрес организации
    @FindBy(xpath = "//div[contains (text(),\"Адрес\")]/following-sibling::div[2]")
    public WebElement linkChangeAdressOrganization;

    // Поле ввода "Адрес подразделения"
    @FindBy(css = "div>[placeholder=\"Адрес подразделения\"]")
    public WebElement inputAdressOrganization;

    // Кнопка "Сохранить" адрес подразделения
    @FindBy(xpath = "//div[2]/following-sibling::button/span[text()=\"Сохранить\"]")
    public WebElement buttonSaveAdressOrganization;

    // Кнопка "Отмена" сохранения адреса подразделения
    @FindBy(xpath = "//div[2]/following-sibling::button/span[text()=\"Отмена\"]")
    public WebElement buttonDoNotSaveAdressOrganization;

    // Email организации
    @FindBy(xpath = "//div[contains (text(),\"Email\")]/following-sibling::div[1]")
    public WebElement emailOrganization;

    // Vk организации
    @FindBy(xpath = "//div[contains (text(),\"Vk\")]/following-sibling::div[1]")
    public WebElement vkOrganization;

    // Facebook организации
    @FindBy(xpath = "//div[contains (text(),\"Facebook\")]/following-sibling::div[1]")
    public WebElement facebookOrganization;

    // Instagram организации
    @FindBy(xpath = "//div[contains (text(),\"Instagram\")]/following-sibling::div[1]")
    public WebElement instagramOrganization;

    // Другие контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Другие\")]/following-sibling::div[1]")
    public WebElement otherOrganization;

    // Ссылка "Изменить" контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/span[contains (text(),\"Изменить\")]")
    public WebElement linkChangeContactsOrganization;

    // Кнопка "Сохранить" контакты организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/button/span[text()=\"Сохранить\"]")
    public WebElement buttonSaveContactsOrganization;

    // Кнопка "Отмена" сохранения контактов организации
    @FindBy(xpath = "//div[contains (text(),\"Контакты\")]/following-sibling::div/button/span[text()=\"Отмена\"]")
    public WebElement buttonDoNotSaveContactsOrganization;

    // Код подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Код подразделения\")]/following-sibling::div[1]")
    public WebElement codeOrganization;

    // Реестровый номер ОМС подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Реестровый номер ОМС\")]/following-sibling::div[1]")
    public WebElement registrationOMSOrganization;

    // Идентификатор ПУМП подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Идентификатор ПУМП\")]/following-sibling::div[1]")
    public WebElement identifikatorPUMPOrganization;

    // Идентификатор ОМС подразделения организации
    @FindBy(xpath = "//div[contains (text(),\"Идентификатор ОМС\")]/following-sibling::div[1]")
    public WebElement identifikatorOMSrganization;

    // Ссылка "Изменить" системную информацию организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/span[contains (text()," +
            "\"Изменить\")]")
    public WebElement linkChangeSystemInformationOrganization;

    // Кнопка "Сохранить" системную информацию организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/button/span[text()" +
            "=\"Сохранить\"]")
    public WebElement buttonSaveSystemInformationOrganization;

    // Кнопка "Отмена" сохранения системной информации организации
    @FindBy(xpath = "//div[contains (text(),\"Системная информация\")]/following-sibling::div/button/span[text()" +
            "=\"Отмена\"]")
    public WebElement buttonDoNotSaveSystemInformationOrganization;
}



