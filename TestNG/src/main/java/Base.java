import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class Base {

    @Parameters({"browser"})
    @BeforeTest
    public void setupBeforeClass(@Optional("Chrome") String browser) {
        Configuration.browser = browser;
        Configuration.browserSize = "600x1600";
        System.out.println("Use - "+Configuration.browser);
    }
}
