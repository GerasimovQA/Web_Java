package Permissions.WorkerProfile;

import Global.GlobalPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class WorkerProfilePage extends GlobalPage {

    public WorkerProfilePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}



