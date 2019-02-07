package Permissions.EditUser;

import Global.GlobalPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class EditUserPage extends GlobalPage {

    EditUserPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}



