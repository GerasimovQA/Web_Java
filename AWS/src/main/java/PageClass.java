import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PageClass {

    SelenideElement searchInput = $x("//input[@name='q']");
    SelenideElement popUpList = $x(".//div[@role='option']/../..");
}
