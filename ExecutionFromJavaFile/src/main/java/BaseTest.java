import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.Duration;
import java.time.LocalTime;

public class BaseTest {

    public static void clickElement(SelenideElement e) {
        String Locator = e.toString();
        e.shouldBe(Condition.visible, Duration.ofSeconds(60));
        e.click();
        sout("Clicked " + Locator);
    }

    public static void sout(String text) {
        System.out.println(currentTime() + " - " + text);
    }

    public static String currentTime() {
        LocalTime currentDayAndTime = LocalTime.now();
        return currentDayAndTime.toString();
    }

    public static JsonNode sendSimpleMessage(String message) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "sandbox1b34395e88d149f0a24e6dcfa454f0e9.mailgun.org" +
                        "/messages")
                .basicAuth("api", "d1c38f93936e47214fb49539bfb6d88d-835621cf-904349a3")
                .queryString("from", "appoint <USER@YOURDOMAIN.COM>")
                .queryString("to", "GerasimovQA@gmail.com")
                .queryString("subject", "Dates")
                .queryString("text", "Available dates: " + message)
                .asJson();
        System.out.println("The message is sent. " + message);
        return request.getBody();
    }
}

