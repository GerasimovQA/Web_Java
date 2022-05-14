package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class UserApi extends Base {

    public void deleteLocalUserAPI(String Cookies, String url, String login) {
        String body = "";
        String basePath = "/manager/api/security/local/user/" + login;

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении локального пользователя: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void deleteJmxUserAPI(String Cookies, String url, String login) {
        String body = "{login: \"" + login + "\"}";
        String basePath = "/manager/api/web/jmx/delete";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при удалении JMX пользователя: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

}




