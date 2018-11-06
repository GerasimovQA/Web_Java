package global;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(JUnitParamsRunner.class)
public class EnvironmentSystem {

    static String Token = "";
    static String UserID = "";


    public static void login() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("auth", GlobalPage.LoginAUT);
        jsonAsMap.put("password", GlobalPage.PasswordAUT);

        Response response =
                given().log().all().
                        contentType(ContentType.JSON).
                        body(jsonAsMap).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/sess/login").
                        when().post().
                        then().extract().response();

        System.out.println("Response: " + response.body().print());
        Token = response.path("sid_hash");
        System.out.println("Получили токен: " + Token);
    }

    public static void createUserAPI(String Login, String Email, String Phone, String Password, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("login", Login);
        jsonAsMapPassport.put("email", Email);
        jsonAsMapPassport.put("phone", Phone);
        jsonAsMapPassport.put("password", Password);
        jsonAsMapPassport.put("status", Status);
        jsonAsMapPassport.put("first_name", FirstName);
        jsonAsMapPassport.put("middle_name", MiddleName);
        jsonAsMapPassport.put("last_name", SecondName);
        jsonAsMapPassport.put("superuser", Superuser);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
        UserID = response.path("user_id");
        System.out.println("Создали пользователя с UserID: " + UserID);
    }

    public static void logoutAllUserSessionsAPI() {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("user_id", "5bbb863a04a40f37b17433f1");

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/logout").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ после разлогинивания: " + response.getBody().asString());
        System.out.println("Пользователь разлогинен");
    }


}
