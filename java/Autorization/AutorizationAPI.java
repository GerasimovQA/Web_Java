package Autorization;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigProperties;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AutorizationAPI{

    public String loginAPI(String Login, String Password) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("auth", Login);
        jsonAsMap.put("password", Password);

        Response response =
                given().log().all().
                        contentType(ContentType.JSON).
                        body(jsonAsMap).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/sess/login").
                        when().post().
                        then().extract().response();

       String Token = response.path("sid_hash");
        System.out.println("Получили токен: " + Token);
        return Token;
    }

    public String createUserAPI(String Token, String Login, String Password, String Email, String Phone, String Status,
                                String SecondName, String FirstName, String MiddleName, String Superuser,
                                String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                String Role1, String Role2) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        String[] PostsList = {Post1};
        String[] RolesList = {Role1, Role2};

        jsonAsMapPassport.put("login", Login);
        jsonAsMapPassport.put("email", Email);
        jsonAsMapPassport.put("phone", Phone);
        jsonAsMapPassport.put("password", Password);
        jsonAsMapPassport.put("status", Status);
        jsonAsMapPassport.put("first_name", FirstName);
        jsonAsMapPassport.put("middle_name", MiddleName);
        jsonAsMapPassport.put("last_name", SecondName);
        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
        jsonAsMapPassport.put("org_id", OrgID);
        jsonAsMapPassport.put("org_posts", PostsList);
        jsonAsMapPassport.put("org_roles", RolesList);
        jsonAsMapPassport.put("org_status", OrgStatus);

        System.out.println(jsonAsMapPassport);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
                        when().post().
                        then().extract().response();

        String UserID = response.path("user_id");
        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
        System.out.println("Создали пользователя с ID: " + UserID);
        return UserID;
    }
}
