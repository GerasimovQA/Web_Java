import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.sleep;


public class Base {

    BaseClass baseClass = new BaseClass();
    String idInstance = null;

    @Parameters({"browser"})
    @BeforeTest
    public void setupBeforeClass(@Optional("chrome") String browser) {
        idInstance = baseClass.getIDofInstanceWAS();
        baseClass.startWAS(idInstance);
        sleep(60000);
        Configuration.browser = browser;
        Configuration.browserCapabilities.setCapability("version", "107.0");
        Configuration.browserSize = "600x1600";
        Configuration.remote = "http://"+baseClass.getIPofInstanceAWS(idInstance)+":4444/wd/hub";
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", true);
        System.out.println("Use - " + Configuration.browser);

    }

    @AfterTest (alwaysRun = true)
    public void afterClass(){
        baseClass.stopWAS(idInstance);
    }


}
