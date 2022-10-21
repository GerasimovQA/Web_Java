import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class Page {
    SelenideElement buttonScheduleAnAppointment = $x(".//div[text()='Schedule an Appointment']");
    SelenideElement buttonExam = $x(".//div[text()='Exams (Road and Written Tests)']");
    SelenideElement buttonRoadExam = $x(".//div[text()='Road Test with or without an Interpreter']");
    SelenideElement buttonMontpelier = $x(".//div[text()='Montpelier']");
    ElementsCollection arrayAvailableDates = $$(By.xpath(".//a[@class=\"ui-state-default ui-state-active\" or @class=\"ui-state-default\"]"));
}
