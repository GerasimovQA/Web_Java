import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.*;

public class TestClass2 extends Base {
    SoftAssert softAssert = new SoftAssert();
    BaseClass baseClass = new BaseClass();
    PageClass pageClass = new PageClass();

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
        System.out.println("AM");
    }

    @Test(priority = 5, groups = "abc")
    public void a() {
        baseClass.sout("aaa");
        Assert.assertEquals("aaa", "aaa", "Good");
    }

    @Test(priority = 4, groups = "abc")
    public void b() {
        baseClass.sout("bbb");
        Assert.assertEquals("bbb", "aaa", "Bad");
    }

    @Test(priority = 4, groups = "abc", dependsOnMethods = "a", expectedExceptions = NullPointerException.class)
    public void d() {
        baseClass.sout("ddd");
    }

    @Test(priority = 3, groups = "dcf", invocationCount = 3)
    public void c() {
        baseClass.sout("666");
    }

    @Test
    public void testGoogle1() {
        System.out.println(Selenide.title());
        baseClass.enter_text(pageClass.searchInput, "I'm working");
        baseClass.click(pageClass.popUpList);
        sleep(3);
    }
}

