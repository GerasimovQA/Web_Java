package GlobalSearch;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class GlobalSearchFunctionalLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private GlobalSearchPage p;

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("\n\n\n");
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new GlobalSearchPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsGlobalSearch);
        }
    };

    public void sumResult(String Request) {
        p.clickAndSendKeys(p.inputQuickGlobalSearch, Request);
        p.sleep(1000);
        p.preloader();
        System.out.println("Эапрос: " + Request);
        int sumQuickSearch = Integer.parseInt(p.sumFoundResulteQuickGlobalSearch.getText());
        if (Integer.parseInt(p.sumFoundResulteQuickGlobalSearch.getText()) >= 10) {
            Assert.assertEquals(10, p.listNamResulteQuickGlobalSearch.size());
        } else {
            Assert.assertEquals(p.listNamResulteQuickGlobalSearch.size(), sumQuickSearch);
        }
        Assert.assertEquals(p.listNamResulteQuickGlobalSearch.size(), p.listDescriptionResulteQuickGlobalSearch.size());
        p.click(p.linkShowAllResulteQuickGlobalSearch);
        int sumFullSearch = Integer.parseInt(p.allResult.getText());
        Assert.assertEquals(sumQuickSearch, sumFullSearch);
        Assert.assertEquals(p.listNameResultFullGlobalSearch.size(), p.listDescriptionResultFullGlobalSearch.size());
    }
}



