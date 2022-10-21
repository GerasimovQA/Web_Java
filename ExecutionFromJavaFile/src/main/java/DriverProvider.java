import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.net.MalformedURLException;
import java.net.URL;

@ParametersAreNonnullByDefault
public class DriverProvider implements WebDriverProvider {
    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        WebDriver driver = null;
        String platform = ConfigProperties.getTestProperty("platform");
        switch (platform) {
            case "IOS_SIMULATED":
                XCUITestOptions options = new XCUITestOptions();
                options.merge(capabilities);
                options.setAutomationName("XCUITest");
                options.setPlatformName("iOS");
                options.setDeviceName("iPhone 11");
                options.setPlatformVersion("14.1");
                options.setApp("/Users/alekseigerasimov/Mobile/Web_Android_Ios_Selenide/src/main/resources/UIKitCatalog.app");
                try {
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "IOS_REAL":
                XCUITestOptions optionsReal = new XCUITestOptions();
                optionsReal.merge(capabilities);
                optionsReal.setCapability("xcodeOrgId","<Team ID>");
                optionsReal.setCapability("xcodeSigningId","iPhone Developer");
                optionsReal.setAutomationName("XCUITest");
                optionsReal.setPlatformName("iOS");
                optionsReal.setDeviceName("iPhone Xr");
                optionsReal.setPlatformVersion("15.5");
                optionsReal.setApp("/Users/alekseigerasimov/Mobile/Web_Android_Ios_Selenide/src/main/resources/UIKitCatalog.app");
                try {
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), optionsReal);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "ANDROID_SIMULATED":
                UiAutomator2Options optionsAndroid = new UiAutomator2Options();
                optionsAndroid.merge(capabilities);
                optionsAndroid.setPlatformName("Android");
                optionsAndroid.setDeviceName("AndroidTestDevice");
                optionsAndroid.setPlatformVersion("7.0");
                optionsAndroid.setAutomationName("Appium");
                optionsAndroid.setApp("/Users/alekseigerasimov/Mobile/Web_Android_Ios_Selenide/src/main/resources/tor-browser-11.0.15-android-x86-multi.apk");
                optionsAndroid.setAppPackage("org.torproject.torbrowser");
                optionsAndroid.setAppActivity("org.mozilla.fenix.HomeActivity");
                try {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), optionsAndroid);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "ANDROID_REAL":
                UiAutomator2Options optionsAndroidReal = new UiAutomator2Options();
                optionsAndroidReal.merge(capabilities);
                optionsAndroidReal.setPlatformName("Android");
                optionsAndroidReal.setDeviceName("AndroidTestDevice");
                optionsAndroidReal.setPlatformVersion("7.0");
                optionsAndroidReal.setAutomationName("Appium");
                optionsAndroidReal.setApp("/Users/alekseigerasimov/Mobile/Web_Android_Ios_Selenide/src/main/resources/tor-browser-11.0.15-android-armv7-multi.apk");
                optionsAndroidReal.setAppPackage("org.torproject.torbrowser");
                optionsAndroidReal.setAppActivity("org.mozilla.fenix.HomeActivity");
                try {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), optionsAndroidReal);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new AssertionError("This platform doesn't exist");
        }
        return driver;
    }
}