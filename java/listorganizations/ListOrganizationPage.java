package listorganizations;

import global.GlobalPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

public class ListOrganizationPage extends GlobalPage {

    public ListOrganizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private ArrayList<String> ListNameOrg = new ArrayList<>();
    private ArrayList<String> ListNameAbbrOrg = new ArrayList<>();
    private ArrayList<String> ListDirectorOrg = new ArrayList<>();
    private ArrayList<String> ListActionOrg = new ArrayList<>();
    private ArrayList<String> ListAdditionalOrg = new ArrayList<>();

    //Кнопка "Добавить подразделение" в хеадере
    @FindBy(xpath = "//div/a/span[contains (text(),\"Добавить подразделение\")]")
    public WebElement buttonAddOrg;

    //Заголовок "Добавить подразделение" на странице Добавления организации
    @FindBy(xpath = "//h1[contains(text(),\"Добавить подразделение\")]")
    public WebElement headerAddOrg;

    //Кнопка "Редактировать"
    @FindBy(xpath = "//button/span[contains (text(),\"Редактировать\")]")
    public WebElement buttonEditListOrg;


    //Кнопка "Добавить подразделение" в стике
    @FindBy(xpath = "//div[@class=\"dep-list-header__head-buttons\"]//span[contains (text(),\"Добавить " +
            "подразделение\")]")
    public WebElement buttonAddOrgStick;

    //Кнопка "Сохранить" в стике
    @FindBy(xpath = "//span[contains (text(),\"Сохранить\")]")
    public WebElement buttonSaveListOrgStick;

    //Кнопка "Отмена" в стике
    @FindBy(xpath = "//span[contains (text(),\"Отмена\")]")
    public WebElement buttonCancelListOrgStick;

    //Выпадающий список "Структура организации"
    @FindBy(xpath = "//div[contains (text(),\"Организации\")]")
    public WebElement listStructureOrg;

    //Поле ввода "Поиск организации"
    @FindBy(xpath = "//div[contains (text(),\"Поиск по названию\")]/following-sibling::div/input")
    public WebElement fieldSearchOrg;

    //"Показанно подразделений:"
    @FindBy(xpath = "//div[contains(text(),\"Показано подразделений:\")]/span")
    public WebElement showOrg;

    //"Всего актуальных подразделений:"
    @FindBy(xpath = "//div[contains(text(),\"Всего актуальных подразделений:\")]/span")
    public WebElement allOrg;

    //Список названий организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> listNameOrg;

    //Список сокращенных названий организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][2]")
    public List<WebElement> listNameAbbrOrg;

    //Список директоров организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][3]")
    public List<WebElement> listDirectorOrg;

    //Список действий организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][4]/div")
    public List<WebElement> listActionOrg;

    //Редактировать подразделение
    @FindBy(xpath = "//*[@class=\"el-dropdown-menu el-popper\"]//span[contains(text(),\"Редактировать\")]")
    public List<WebElement> listLinkEditOrg;

    //Заголовок "Редактировать организацию" на странице редактирования организации
    @FindBy(xpath = "//h1[contains(text(),\"Редактировать организацию\")]")
    public WebElement headerEditOrg;

    //Название редактируемой организации на странице редактирования организации
    @FindBy(xpath = "//*[@id=\"pane-first\"]/div/div[1]/div[contains(text(),\"Название организации\")]/" +
            "following-sibling::div")
    public WebElement nameEditOrg;


    //Удалить подразделение
    @FindBy(xpath = "//*[@class=\"el-dropdown-menu el-popper\"]//span[contains(text(),\"Удалить\")]")
    public List<WebElement> listLinkDeleteOrg;

    //Заголовок "Удалить подразделение?" в окне удаления организации
    @FindBy(xpath = "//span[contains(text(),\"Удалить подразделение?\")]")
    public WebElement headerDeleteOrg;

    //Название удаляемого подразделения в окне удаления организации
    @FindBy(xpath = "//span[contains(text(),\"Удалить подразделение?\")]/../../following-sibling::div/div")
    public WebElement nameDeleteOrg;

    //Кнопка"Отмена" в окне удаления организации
    @FindBy(xpath = "//button/span[contains(text(),\"Отмена\")]")
    public WebElement buttonCancelDeleteOrg;

    //Кнопка"Удалить" в окне удаления организации
    @FindBy(xpath = "//button/span[contains(text(),\"Удалить\")]")
    public WebElement buttonDeleteOrg;

    //Поповер"УНельзя удалить организацию имеющую дочерние подразделения."
    @FindBy(xpath = "//p[contains (text(),\"Нельзя удалить организацию имеющую дочерние подразделения.\")]")
    public WebElement popverCanNotDeleteOrg;

    //Список "Подробнее" организации
    @FindBy(xpath = "//div[@class=\"dep-tree__cell\"][5]")
    public List<WebElement> listAdditionalOrg;

    //Заголовок "Информация о подразделении" на карточке организации
    @FindBy(xpath = "//h1[contains (text(),\"Информация о подразделении\")]")
    public WebElement headerViewOrg;

    //Название "Подразделения" на карточке организации
    @FindBy(xpath = "//*[@id=\"pane-dep\"]//div[1]/div/div[1]/div[@class=\"department-name__name-text\"]/span")
    public WebElement nameViewOrg;

    //Название головной организации "ФГАО" в фильтре "Структура организации"
    @FindBy(xpath = "//div[1]/div[2]/div[1]/div[1]/span[contains (text(),\"ФГАО\")]")
    public WebElement filterNameHeadOrg;

    //Чек-бокс "Клиника имени Степашина" в фильтре "Структура организации"
    @FindBy(xpath = "//span[contains(text(),\"Клиника имени Степашина\")]/../label")
    public WebElement checkBoxStepashin;

    //Ветвь "Клиника имени Степашина" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[contains(text(),\"Клиника имени Степашина\")]/../../." +
            ".//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> branchStepashin;

    //Ветвь "Университетская клиническая больница №1" в фильтре по структуре организации
    @FindBy(xpath = "//span[contains (text(),\"Клиника имени Степашина\")]/../." +
            ".//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> filterBranchStepashin;

    //Полное дерево в структуре организаций
    @FindBy(xpath = "//div[@class=\"el-tree\"]//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> treeOrg;


}



