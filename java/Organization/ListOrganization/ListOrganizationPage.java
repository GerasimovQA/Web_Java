package Organization.ListOrganization;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ListOrganizationPage extends GlobalPage {

    public ListOrganizationPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

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
    @FindBy(xpath = "//div[contains(text(),\"Всего подразделений:\")]/span")
    public WebElement allOrg;

    //Список неразвернутых триггеров организации
    @FindBy(xpath = "//div[@class=\"el-tree\"]//span[@class=\"el-tree-node__expand-icon el-icon-caret-right\"]")
    public List<WebElement> listTriggerOrg;

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
    @FindBy(xpath = "(//*[@class=\"el-dropdown-menu el-popper\"]//span[contains(text(),\"Удалить\")])[last()]")
    public WebElement listLinkDeleteOrg;

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
    @FindBy(xpath = "//span[contains (text(),\"ФГАО\")] ")
    public WebElement filterNameHeadOrg;

    //Чек-бокс "Клиника имени Степашина" в фильтре "Структура организации"
    @FindBy(xpath = "//span[contains(text(),\"Клиника имени Степашина\")]/../label/span/span")
    public WebElement checkBoxStepashin;

    //Ветвь "Клиника имени Степашина" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[contains(text(),\"Клиника имени Степашина\")]/../../." +
            ".//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> branchStepashin;

    //"Клиника имени Степашина" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"Клиника имени Степашина\")]")
    public WebElement Stepashin0;

    //"1 подразделение Степашина" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"1 подразделение Степашина\")]")
    public WebElement Stepashin1;

    //"2 подразделение Степашина" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"2 подразделение Степашина\")]")
    public WebElement Stepashin2;

    //"3 подразделение Степашина младшего" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"3 подразделение Степашина младшего\")]")
    public WebElement Stepashin3;

    //"Cтепашин для удаления" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"Cтепашин для удаления\")]")
    public WebElement StepashinForDelete;

    //Триггер"3 подразделение Степашина младшего" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"3 подразделение Степашина младшего\")]/" +
            "../..//*[@class=\"dep-tree-dropdown-menu__drop-icon el-icon-more\"]")
    public WebElement trigStepashin3;

    //Триггер "Cтепашин для удаления" в списке подразделений до выделения в структуре организаций
    @FindBy(xpath = "//div[@class=\"dep-tree__row\"]//div[contains (text(), \"Cтепашин для удаления\")]/" +
            "../..//*[@class=\"dep-tree-dropdown-menu__drop-icon el-icon-more\"]")
    public WebElement trigStepashinDelete;

    //Ветвь "Клиника имени Степашина" в фильтре по структуре организации
    @FindBy(xpath = "//span[contains (text(),\"Клиника имени Степашина\")]/../." +
            ".//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> filterBranchStepashin;

    //Полное дерево в структуре организаций
    @FindBy(xpath = "//div[@class=\"el-tree\"]//div[@class=\"dep-tree__cell\"][1]")
    public List<WebElement> treeOrg;
}



