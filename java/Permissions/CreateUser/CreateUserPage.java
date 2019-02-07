package Permissions.CreateUser;

import Global.GlobalPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateUserPage extends GlobalPage {

    CreateUserPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}



