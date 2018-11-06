package system;

import global.GlobalPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemPage extends GlobalPage {

    public SystemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


}



