package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;


public class CreationUserApi extends Base {


    public void createUserAPI(String Cookies, String Role, String Login, String Password) {
        String Body = "{" +
                "\"domains\":[]," +
                "\"selectedRole\":\"" + Role + "\"," +
                "\"authorities\":[{\"authority\":\"" + Role + "\"}],\"username\":\"" + Login + "\",\"password\":\"" + Password + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/security/local/user").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании пользователя: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void lockUserAPI(String Cookies, String Login) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/security/local/user/" + Login + "/toggle").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при блокировании пользователя: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createJmxUserApi(String Cookies, String Login, String Password, String Role) {
        String Body = "{\"login\":\"" + Login + "\",\"password\":\"" + Password + "\",\"role\":\"" + Role + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/web/jmx/insert").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании пользователя JMX: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("{\"login\":\"" + Login + "\",\"password\":\"" + Password + "\",\"role\":\"" + Role + "\"}", response.getBody().asString());
    }

    public void changePasswordOfRootAPI(String Cookies, String CurrentPassword, String NewPassword) {
        String Body = "{\"currentPassword\":\"" + CurrentPassword + "\",\"password\":\"" + NewPassword + "\",\"passwordBind\":\"" + NewPassword + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BUrl).basePath("/manager/api/security/local/user/passroot").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при изменении пароля Рута: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("\"code\":100,\"message\":\"Ошибка на стороне сервера: Access is denied\""));

//        Assert.assertEquals("{[\"code\":100,\"message\":\"Ошибка на стороне сервера: Access is denied\",]", response.getBody().asString().split("\"timestamp\"")[0]);
    }
}
