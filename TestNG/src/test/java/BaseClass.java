import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class BaseClass {
    public void sout(String text) {
        System.out.println(text);
    }

    public void click(SelenideElement element) {
        element.shouldBe(visible);
        String Locator = element.toString();
        element.click();
        sout("click " + Locator);
    }

    public void enter_text(SelenideElement element, String text) {
        element.shouldBe(visible);
        String Locator = element.toString();
        element.clear();
        element.sendKeys(text);
        sout("entered: " + text + " в: " + Locator);
    }
}
