import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.*;

public class TestClass extends Base {

    SoftAssert softAssert = new SoftAssert();
    PageClass pageClass = new PageClass();
    BaseClass baseClass = new BaseClass();


    @BeforeClass
    public void BC() {
        System.out.println("BC");
    }

    @AfterClass
    public void AC() {
        System.out.println("AC");
    }

    @BeforeSuite
    public void BS() {
        System.out.println("BS");
    }

    @AfterSuite
    public void AS() {
        System.out.println("AS");
    }

    @BeforeMethod
    public void BM(Method method) {
        if (method.getName().contains("testGoogle")) {
            open("https://www.google.com");
        }
        System.out.println("BM");
    }

    @BeforeTest
    public void BT() {
        System.out.println("BT");
    }

    @AfterTest
    public void AT() {
        System.out.println("AT");
    }

    @AfterMethod
    public void AM(Method method) {
        softAssert.assertAll("Very bad:" + this.getClass().getName() + "/" + method.getName());
        closeWebDriver();
    }

    @Test
    public void testGoogle1() {
        System.out.println(Selenide.title());
        baseClass.enter_text(pageClass.searchInput, "I'm working");
        baseClass.click(pageClass.popUpList);
        sleep(3);
    }

    @Test(groups = "sss")
    public void testGoogle2() {
        System.out.println(Selenide.title());
        baseClass.enter_text(pageClass.searchInput, "I'm working");
        baseClass.click(pageClass.popUpList);
        sleep(3);
    }

    @DataProvider
    public Object[][] dataProvider1() {
        return new Object[][]{{"a", 1, 1}, {"b", 2, 2}, {"c", 3, 3}, {"d", 4, 4}};
    }

    @Test(dataProvider = "dataProvider1", groups = "dcf")
    public void parallel(String a, int b, int c) {
        baseClass.sout(a + ": " + (b + c));
    }

    @Test(priority = 5, groups = "abc")
    public void alpha() {
        baseClass.sout("aaa");
        softAssert.assertEquals("aaa", "bbb", "Bad 1");
        softAssert.assertEquals("aaa", "ccc", "Bad 2");
        Assert.assertEquals("aaa", "aaa", "Good");
    }

    @Test(priority = 4, groups = "abc")
    public void b() {
        baseClass.sout("bbb");
        Assert.assertEquals("bbb", "aaa", "Bad");
    }

    @Test(priority = 4, groups = "abc", dependsOnMethods = "alpha", expectedExceptions = NullPointerException.class)
    public void d() {
        baseClass.sout("ddd");
    }

    @Test(priority = 3, groups = "dcf", invocationCount = 3)
    public void c() {
        baseClass.sout("555");
    }

    @Test(priority = 3, groups = "dcf", invocationCount = 3)
    public void looser() {
        baseClass.sout("666");
    }

    @Test(priority = 3, groups = "dcf", invocationCount = 3, enabled = false)
    public void looser2() {
        baseClass.sout("666");
    }

}

