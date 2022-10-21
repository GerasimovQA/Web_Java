import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

public class BaseClass {
    TestReporter testReporter;
    TestInfo testInfo;

    public void sout(String text) {
        testReporter.publishEntry(text);
    }
}
