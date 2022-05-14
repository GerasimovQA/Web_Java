package ru.factorts.page;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigProperties;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DashboardApi extends Base {
    public void createScreenAPI(String Cookies, String url, String createDashboardScreen) {
        String Body = createDashboardScreen;

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/dashboard/saveScreens").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании экранов Dasboard: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }



}


