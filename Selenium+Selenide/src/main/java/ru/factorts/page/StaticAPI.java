package ru.factorts.page;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class StaticAPI {

    static BasePage basePage = new BasePage();

    static String loginAPI(String BaseUrl, String username, String password) {
        Response response =
                RestAssured.given().log().all().
                        contentType("application/x-www-form-urlencoded").
                        given().
                        param("username", username).
                        param("password", password).
                        baseUri(BaseUrl).basePath("/manager/login/").
                        when().post().
                        then().extract().response();

        Headers allHeaders = response.getHeaders();
        System.out.println("allHeaders: " + allHeaders);

        Map<String, String> allCookies = response.getCookies();
        String Cookies = allCookies.toString().replaceAll("[{}]", "");
        System.out.println("allCookies: " + Cookies);

        basePage.sout("Ответ при авторизации: " + response.getBody().asString() + "\n\n");
        Assert.assertNotEquals(" ", response.getBody().asString());
        return Cookies;
    }
}
