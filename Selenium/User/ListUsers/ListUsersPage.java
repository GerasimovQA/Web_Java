package User.ListUsers;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListUsersPage extends GlobalPage {

    public ListUsersPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Ссылка "Очистить поиск и фильтры"
    @FindBy(xpath = "//span[contains(text(),\"Очистить поиск и фильтры\")]")
    public WebElement linkResetSearchAndFilter;

    //Кнопка "Создать сотрудника" в списке сотрудников
    @FindBy(xpath = "//div[@class=\"user-list\"]//span[text()=\"Создать сотрудника\"]")
    public WebElement buttonCreateUserInListUser;

    //Список Должностей
    @FindBy(xpath = "(//div[@class=\"tree-select__popover\"])[3]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> listPostInFilter;

    //Список Чек-боксов Должностей
    @FindBy(xpath = "(//div[@class=\"tree-select__popover\"])[3]//span[@class=\"el-checkbox__inner\"]")
    public List<WebElement> listCheckBoxPostInFilter;

    //Список ролей
    @FindBy(xpath = "(//div[@class=\"el-scrollbar\"])[7]//li/span")
    public List<WebElement> listRoleInFilter;

    //Фильтр "Специальности"
    @FindBy(xpath = "//div[contains(text(),\"Специальность\")]/..//div[contains(text(),\"Специальности\")]")
    public WebElement filterSpecialty;

    //Разделы специальностей
    @FindBy(xpath = "(//div[@class=\"tree-select__popover\"])[3]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> listSpecialtyDepartment;

    //Список названий специальностей
    @FindBy(xpath = "(//div[@class=\"tree-select__popover\"])[3]//div[@class=\"el-tree-node__children\"]//span[@class=\"el-tree-node__label\"]")
    public List<WebElement> listNameSpecialty;

    //Список чек-боксов специальностей
    @FindBy(xpath = "(//div[@class=\"tree-select__popover\"])[3]//div[@class=\"el-tree-node__children\"]//span[@class=\"el-checkbox__inner\"]")
    public List<WebElement> getListCheckBoxSpecialty;

    //Список статусов работы
    @FindBy(xpath = "(//div[@class=\"el-scrollbar\"])[7]//li/span")
    public List<WebElement> listStatusWork;

    //Список статусов в системе
    @FindBy(xpath = "(//div[@class=\"el-scrollbar\"])[7]//li/span")
    public List<WebElement> listStatusSystem;

    //Фильтр "Статус Online/Offline"
    @FindBy(xpath = "//div[contains(text(),\"Статус Online/Offline\")]/following-sibling::div//input[@placeholder=\"Статус\"]")
    public WebElement filterStatusOnline;

    //Список статусов Online/Offline
    @FindBy(xpath = "(//div[@class=\"el-scrollbar\"])[7]//li/span")
    public List<WebElement> listStatusOnline;

    //Фильтр "Статус Глобальная роль"
    @FindBy(xpath = "//div[contains(text(),\"Глобальная роль\")]/following-sibling::div//input[@placeholder=\"Глобальная роль\"]")
    public WebElement filterGlobalRole;

    //Выпадающие списки в фильтрах
    @FindBy(xpath = "(//div[@class=\"el-scrollbar\"])[7]//li/span")
    public List<WebElement> listInFilter;

    //Показано сотрудников
    @FindBy(xpath = "//span[text()=\"Показано сотрудников:\"]/following-sibling::span")
    public WebElement showWorkers;

    //Всего сотрудников
    @FindBy(xpath = "//span[text()=\"Всего сотрудников:\"]/following-sibling::span")
    public WebElement allWorkers;

    //Список Порядковых номеров
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[1]")
    public List<WebElement> listNumber;

    //Список аватарок
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[2]//img")
    public List<WebElement> listAvatar;

    //Список триггеров
    @FindBy(xpath = "//div[@class=\"user-list-table\"]//td[3]")
    public List<WebElement> listTrigger;

    //Фио в блоке расширинной информации
    @FindBy(xpath = "//div[@class=\"user-list-detail__names\"]")
    public WebElement blockInfoUser;

    //Заголовок мой профиль в профиле сотрудника
    @FindBy(xpath = "//h1[text()=\"Мой профиль\"]")
    public WebElement headerMyProfileUser;

    //Заголовок информация о сотруднике в карточке сотрудника
    @FindBy(xpath = "//h1[text()=\"Информация о сотруднике\"]")
    public WebElement headerInfoUser;
}



