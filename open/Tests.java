import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class Tests {

    Page p = new Page();

    @Test()
    public void numberOne() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println(response.getBody().toString());
        List<People> people = jsonPathEvaluator.getList("data", People.class);

        for (People men : people) {
            Assert.assertNotNull(men.id);
            Assert.assertNotNull(men.email);
            Assert.assertNotNull(men.first_name);
            Assert.assertNotNull(men.last_name);
            Assert.assertNotNull(men.avatar);
        }
    }


    //    Не понял зачем мапить на объект, если цель проверить, что в ответе те же свмые значения, что и в запросе
    @Test
    public void numberTwo() {
        String name = "Neo";
        String job = "leader";
        String Body = "{\"name\": \"" + name + "\", \"job\": \"" + job + "\"}";

        Response response =
                given().log().all().
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri("https://reqres.in").basePath("/api/users/").
                        when().post().
                        then().extract().response();

        Assert.assertEquals(name, response.path("name"));
        Assert.assertEquals(job, response.path("job"));
    }

    @Test
    public void numberThree() {
        open("https://www.google.com/");
        p.input.val("Открытие");
        p.buttonSearch.click();

        String[] res = p.result.getText().split("\\(");
        int intResult = Integer.parseInt(res[0].replaceAll("[a-zA-Zа-яА-Я]", "").replace(":", "").replace(" ", ""));
        System.out.println(intResult);
        Assert.assertTrue(intResult > 10000);
    }
}

