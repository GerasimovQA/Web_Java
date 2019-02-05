package Specialty.CreateSpecialty;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateSpecialtyPage extends GlobalPage {

    public CreateSpecialtyPage(RemoteWebDriver driver) {
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

//-------------------------------------------- Создание специальности -----------------------------------------------
    //   Поле ввода "Название специальности"
    @FindBy(xpath = "//div[contains(text(),\"Название\")]/following-sibling::input[@placeholder=\"Название специальности *\"]")
    public WebElement inputNameSpecialty;

    //   Поле ввода "Пояснение для пациентов"
    @FindBy(xpath = "//div[contains(text(),\"Пояснение для пациентов\")]/following-sibling::div/textarea[@placeholder=\"Пояснение для пациентов\"]")
    public WebElement inputNameDescription;

    //   Выпадающий список "Раздел специальностей"
    @FindBy(xpath = "//div[contains(text(),\"Выберите раздел \")]/following-sibling::div//input[@placeholder=\"Раздел\"]")
    public WebElement fieldSectionSpecialty;

    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[text()=\"Медицинские специальности\"]")
    public WebElement sectionMedSpecialty;

    //   Раздел "НЕмедицинские специальности"
    @FindBy(xpath = "//span[text()=\"Немедицинские специальности\"]")
    public WebElement sectionNotMedSpecialty;

    //   Выпадающий список "Выберите значок"
    @FindBy(xpath = "//div[contains(text(),\"Значок специальности\")]/following-sibling::div//div[contains(text(),\"Выберите значок\")]")
    public WebElement fieldIconSpecialty;

    //   Значок специальности
    @FindBy(xpath = "//img[contains(@src,\"/img/specialties/\")]")
    public List<WebElement> iconSpecialty;

    //   Кнопка "Добавить специальность"
    @FindBy(xpath = " //button/span[text()=\"Добавить специальность\"]")
    public WebElement buttonCreateSpecialty;

    //-------------------------------------------- Список специальностей-----------------------------------------------

    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[contains(text(),\"Медицинские специальности\")]")
    public WebElement sectionMedSpecialtyInListSpecialty;

    //   Раздел "Медицинские специальности"
    @FindBy(xpath = "//span[contains(text(),\"Немедицинские специальности\")]")
    public WebElement sectionNotMedSpecialtyInListSpecialty;

    //   Название существующей специальности
    @FindBy(xpath = "//div[@class=\"special-tree__name\"]/span")
    public List<WebElement> nameSpecialtyInListSpecialty;

    //   Описание существующей специальности
    @FindBy(xpath = "//div[@class=\"special-tree__description\"]")
    public List<WebElement> descriptionSpecialtyInListSpecialty;
}



