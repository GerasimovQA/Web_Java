import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.appium.ScreenObject.screen;

public class StartTestFromFile extends BaseTest{
    static Page page = new Page();

    @BeforeEach
    public void before() {
        page = screen(Page.class);
        open("http://www.google.com");
    }

    @AfterEach
    public void after() {
        Selenide.closeWebDriver();
    }

    @Test
    public static void main(String[] args) {
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1500x1000";
        Configuration.headless = false;
        open();
        page = screen(Page.class);
        dmv();
    }
    @Test
    public static void dmv() {
        Selenide.open("https://vtdmv.cxmflow.com/Appointment/Index/57479cc4-a999-4eee-a392-0a7a474a17aa");

        BaseTest.clickElement(page.buttonScheduleAnAppointment);
        BaseTest.clickElement(page.buttonExam);
        BaseTest.clickElement(page.buttonRoadExam);
        BaseTest.clickElement(page.buttonMontpelier);

        page.arrayAvailableDates.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        ArrayList<String> dates = new ArrayList<>(page.arrayAvailableDates.texts());
        BaseTest.sout(dates.toString());

        for (int x = 0; true; ) {
            if (dates.contains("3") || dates.contains("24")) {
                try {
                    BaseTest.sendSimpleMessage(dates.toString());
                } catch (UnirestException e) {
                    throw new RuntimeException(e);
                }
            }
            sleep(300000);
            refresh();
            dates.clear();
            dates.addAll(page.arrayAvailableDates.texts());
            BaseTest.sout(dates.toString());
        }
    }
}

