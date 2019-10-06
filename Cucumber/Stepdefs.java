package Cucum;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.open;

public class Stepdefs {
    String openedUrl = null;
    String reqest = null;

    Page p = new Page();

    @Given("^Url \"([^\"]*)\" and Request \"([^\"]*)\"$")
    public void url_and_Sum(String url, String req) {
        openedUrl = url;
        reqest = req;
    }

    @When("^I go to Url and input Request$")
    public void i_go_to_Url_and_watch_input_reqest() {
        open(openedUrl);
        p.input.val(reqest);
        p.buttonSearch.click();
    }

    @Then("^Sum answers should be \"([^\"]*)\" \"([^\"]*)\"$")
    public void sum_answers_should_be(String compare, String sum) throws Exception {
        String[] res = p.result.getText().split("\\(");
        int intResult = Integer.parseInt(res[0].replaceAll("[a-zA-Zа-яА-Я]", "").replace(":", "").replace(" ", ""));
        System.out.println(intResult);

        if (compare.equals("more")) {
            Assert.assertTrue(intResult > Integer.parseInt(sum));
        }
        if (compare.equals("smaller")) {
            Assert.assertTrue(intResult < Integer.parseInt(sum));
        }
    }
}
