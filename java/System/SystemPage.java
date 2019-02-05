package System;

import Global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SystemPage extends GlobalPage {

    public SystemPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}



