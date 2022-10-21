import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class TestClass extends BaseClass {

    @BeforeEach
    public void before(TestReporter testReporter, TestInfo testInfo) {
        this.testReporter = testReporter;
        this.testInfo = testInfo;
        sout("Test \"" + testInfo.getDisplayName() + "\" is running");
    }

    @AfterEach
    public void after() {
        Selenide.closeWebDriver();
    }

    @ParameterizedTest
    @CsvSource({"a,1,1", "b,2,2", "c,3,3", "d,4,4"})
    public void parallel(String a, int b, int c) {
        sout(a + ": " + (b + c));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Test.csv", numLinesToSkip = 1)
    public void parallelFromCsv(String a, int b, int c) {
        sout(a + ": " + (b + c));
    }

    @org.junit.jupiter.api.Test
    public void a() {
        Selenide.sleep(5000);
        sout("aaa");
    }

    @org.junit.jupiter.api.Test
    public void b() {
        Selenide.sleep(5000);
        sout("bbb");
    }

    @org.junit.jupiter.api.Test
    public void c() {
        Selenide.sleep(5000);
        sout("666");
    }
}

