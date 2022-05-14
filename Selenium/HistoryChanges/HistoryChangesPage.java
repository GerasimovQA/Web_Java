package HistoryChanges;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HistoryChangesPage extends GlobalPage {

    public HistoryChangesPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Глобальные объекты
    //    ФИО юзера
    @FindBy(xpath = "//*[@id=\"pane-user\"]/div/div[2]/div[1]/div[1]/div[1]/div[3]")
    public WebElement fioUser;

    // Маркер загрузки списка - 10 строка в истории
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr[10]/td[3]/div")
    public WebElement marker10History;

    // Маркер загрузки списка - 10 строка в списке пользователей
    @FindBy(xpath = "//div/div[3]/div/div[3]/table/tbody/tr[10]/td[4]/div")
    public WebElement marker10UserList;

    // Столбец Время
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[1]/div")
    public List<WebElement> columnTime;

    // Столбец Триггер
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[2]/div")
    public List<WebElement> columnTrigger;

    //    Столбец Инициатор
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[3]/div/div")
    public List<WebElement> columnInitiator;

    //    Столбец Действие
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[4]/div")
    public List<WebElement> columnAction;

    //    Столбец Объект
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[5]/div")
    public List<WebElement> columnObject;

    //    Номер телефона во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > div:nth-child(1)")
    public WebElement popoverPhone;

    //    Email во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > div:nth-child(2)")
    public WebElement popoverEmail;

    //    Email во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > a")
    public WebElement popoverMore;

    //    Знак вопроса справа от инициатора
    @FindBy(xpath = "//span[text()=\"Инициатор\"]/following-sibling::span")
    public WebElement questionInitiator;

    //    Всплывающая подсказка инициатора
    @FindBy(xpath = "//div[text()=\"Тот кто совершает действие.\"]")
    public WebElement popoverInitiator;

    //    Знак вопрос справа от объекта
    @FindBy(xpath = "//span[text()=\"Объект\"]/following-sibling::span")
    public WebElement questionObject;

    //    Всплывающая подсказка объекта
    @FindBy(xpath = "//div[text()=\"Над кем совершают действие.\"]")
    public WebElement popoverObject;

    //    Поле ввода Действие
    @FindBy(xpath = "//div/div[1]/div[2]/div/div[2]/span/span/i")
    public WebElement inputAction;

    //   Список действий в фильтре
    @FindBy(xpath = "//body/div/div/div/ul/li/span")
    public List<WebElement> filterActionsList;

    //   Календарь
    @FindBy(css = "div.user-history-header > div:nth-child(1) > div")
    public WebElement calendar;

    //   Календарь - Сегодня
    @FindBy(xpath = "//td[@class=\"available today\"]")
    public WebElement calendarToday;

    //   Календарь - Сегодня выделенный день
    @FindBy(css = ".available.today.in-range.start-date")
    public WebElement calendarTodaySelect;

    //    Блок Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//*[@class=\"col-md-6\"]")
    public WebElement blockMoreInfoContext;

    //    Дата события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Дата\"]/..//div[2]")
    public WebElement blocInfoAboutEventDate;

    //   Инициатор события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Инициатор\"]/..//div[2]")
    public WebElement blocInfoAboutEventInitiator;

    //   Действие события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Действие\"]/..//div[2]")
    public WebElement blocInfoAboutEventAction;

    //   Объект события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Объект\"]/..//div[2]")
    public WebElement blocInfoAboutEventObject;

    //    Значение  ID события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"ID события\"]/..//div[2]")
    public WebElement blockMoreInfoContextIDValue;

    //    Значение Устройство события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Устройство\"]/..//div[2]")
    public WebElement blockMoreInfoContextDeviceValue;

    //    Значение IP события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"IP\"]/..//div[2]")
    public WebElement blockMoreInfoContextIPValue;

    //    Значение Логин события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Логин\"]/..//div[2]")
    public WebElement blockMoreInfoContextLoginValue;

    //    Значение Email события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Email\"]/..//div[2]")
    public WebElement blockMoreInfoContextEmailValue;

    //    Значение Телефон события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Телефон\"]/..//div[2]")
    public WebElement blockMoreInfoContextPhoneValue;

    //    Значение Фамилия события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Фамилия\"]/..//div[2]")
    public WebElement blockMoreInfoContextFirstnameValue;

    //    Значение Имя события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Имя\"]/..//div[2]")
    public WebElement blockMoreInfoContextSecondnameValue;

    //    Значение Отчество события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Отчество\"]/..//div[2]")
    public WebElement blockMoreInfoContextMidlenameValue;

    //    Значение Статус события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Статус\"]/..//div[2]")
    public WebElement blockMoreInfoContextStatusValue;

    //    Кнопка Подробнее в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[2]/div/div[1]/a[text()= \"Подробнее\"]")
    public WebElement blockMoreInfoContextButtonMoreInfo;

    //    Кнопка Скопировать ссылку в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//span[text()= \"Скопировать ссылку\"]")
    public WebElement blockMoreInfoContextButtonCopyLink;

    //    Всплывающее сообщение "Ссылка скопирована в буфер обмена"
    @FindBy(xpath = "//div[3]/p[text()=\"Ссылка скопирована в буфер обмена\"]")
    public WebElement popoverLinkIsCopy;
  }





