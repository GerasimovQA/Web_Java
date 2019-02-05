package GlobalSearch;

import Global.GlobalPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GlobalSearchPage extends GlobalPage {

    public GlobalSearchPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //    Поле ввода быстрого глобального поиска
    @FindBy(xpath = "//nav//div[@class=\"inlineSearch\"]//input")
    public WebElement inputQuickGlobalSearch;

    //    Иконка "Лупа" быстрого глобального поиска
    @FindBy(xpath = "//nav//div[@class=\"inlineSearch\"]//i")
    public WebElement iconEnterQuickGlobalSearch;

    //    Иконка результата в быстром поиске
    @FindBy(xpath = "//div[@class=\"resultList__item\"]//img[@alt=\"Icon\"]")
    public List<WebElement> listIconResultQuickGlobalSearch;

    //    Название результата в быстром поиске
    @FindBy(xpath = "//div[@class=\"documentName\"]")
    public List<WebElement> listNamResulteQuickGlobalSearch;

    //    Сниппет результата в быстром поиске
    @FindBy(xpath = "//div[@class=\"documentDescription\"]")
    public List<WebElement> listDescriptionResulteQuickGlobalSearch;

    //    Количество всего найденных результатов в быстром поиске
    @FindBy(xpath = "//nav//div[@class=\"resultListFooter\"]/span")
    public WebElement sumFoundResulteQuickGlobalSearch;

    //    Ссылка "Смотреть все результаты" в быстром поиске
    @FindBy(xpath = "//nav//a[contains(text(),\"Смотреть все результаты\")]")
    public WebElement linkShowAllResulteQuickGlobalSearch;

    //    Поле ввода в расширенном поиске
    @FindBy(xpath = "//div[@class=\"searchResultPage\"]//div[@class=\"inlineSearch\"]//input")
    public WebElement inputFullGlobalSearch;

    //   Иконка "Лупа" расширенного глобального поиска
    @FindBy(xpath = "//div[@class=\"searchResultPage\"]//div[@class=\"inlineSearch\"]//i")
    public WebElement iconEnterFullGlobalSearch;

    //   Показано результатов расширенного глобального поиска
    @FindBy(xpath = "//span[text()=\"Показано результатов:\"]/following-sibling::span")
    public WebElement showResult;

    //   Всего результатов расширенного глобального поиска
    @FindBy(xpath = "//span[text()=\"Всего результатов:\"]/following-sibling::span")
    public WebElement allResult;

    //    Иконка результата в расширенном поиске
    @FindBy(xpath = "//div[@class=\"searchResult\"]//img[@alt=\"Icon\"]")
    public List<WebElement> listIconResulFullGlobalSearch;

    //    Название результата в расширенном поиске
    @FindBy(xpath = "//div[@class=\"searchResult\"]//div[@class=\"documentName\"]")
    public List<WebElement> listNameResultFullGlobalSearch;

    //    Сниппет результата в расширенном поиске
    @FindBy(xpath = "//div[@class=\"searchResult\"]//div[@class=\"documentDescription\"]")
    public List<WebElement> listDescriptionResultFullGlobalSearch;

}



