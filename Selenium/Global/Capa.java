package Global;

import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.URL;

public class Capa {
    public static RemoteWebDriver getRemouteDriver(String sys, URL serverUrl, Description description) {
        return sys.equals("ChromeSelenoid") ? getChromeDriver(serverUrl, description) : getChromeDriverSeleniumGrid(serverUrl, description);
    }

    public static RemoteWebDriver getChromeDriver(URL serverUrl, Description description) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(ConfigProperties.getTestProperty("browserChrome"));
        capabilities.setVersion(ConfigProperties.getTestProperty("browserChromeVersion"));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", ConfigProperties.getTestProperty("screenResolution"));
        capabilities.setCapability("name", Global.GlobalPage.nameTest(description.getClassName().substring(description.getClassName().indexOf("."), description.getClassName().length()-1)
                + "." + description.getMethodName()));
        capabilities.setCapability("videoName", Global.GlobalPage.nameTest(description.getClassName()
                + "." + description.getMethodName()));
        capabilities.setCapability("timeZone", "Europe/Moscow");

        return new RemoteWebDriver(serverUrl, capabilities);
    }

    public static RemoteWebDriver getFirefoxDriver(URL serverUrl, Description description) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(ConfigProperties.getTestProperty("browserFirefox"));
        capabilities.setVersion(ConfigProperties.getTestProperty("browserFirefoxVersion"));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", ConfigProperties.getTestProperty("screenResolution"));
        capabilities.setCapability("name", Global.GlobalPage.nameTest(description.getMethodName()));
        capabilities.setCapability("videoName", Global.GlobalPage.nameTest(description.getMethodName()));
        capabilities.setCapability("timeZone", "Europe/Moscow");

        return new RemoteWebDriver(serverUrl, capabilities);
    }

    public static Dimension getDimension() {
        Dimension d = new Dimension(Integer.parseInt(ConfigProperties.getTestProperty("dimensionX")),
                Integer.parseInt(ConfigProperties.getTestProperty("dimensionY")));
        return d;
    }

    public static RemoteWebDriver getChromeDriverSeleniumGrid(URL serverUrl, Description description) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(ConfigProperties.getTestProperty("browserChrome"));
//        capabilities.setVersion(ConfigProperties.getTestProperty("browserChromeVersion"));
//        capabilities.setCapability("screenResolution", ConfigProperties.getTestProperty("screenResolution"));
//        capabilities.setCapability("name", Global.GlobalPage.nameTest(description.getClassName().substring(description.getClassName().indexOf("."), description.getClassName().length()-1)
//                + "." + description.getMethodName()));
//        capabilities.setCapability("videoName", Global.GlobalPage.nameTest(description.getClassName()
//                + "." + description.getMethodName()));
//        capabilities.setCapability("timeZone", "Europe/Moscow");

        return new RemoteWebDriver(serverUrl, capabilities);
    }
}
