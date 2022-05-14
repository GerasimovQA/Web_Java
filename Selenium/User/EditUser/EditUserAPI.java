package User.EditUser;

import Global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigProperties;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class EditUserAPI extends GlobalPage {

    public String loginAPI(String Login, String Password) {
        String Token = null;
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

        Token = response.path("sid_hash");
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

    public void profileUserAPI(String Token, String UserID, String Spec1, String Spec2, String Service1, String Service2, String Regalia,
                               String EmailCont, String PhoneCont,
                               String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
                               String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map[] ServicesList = {jsonAsMapProfileService1};
        Map[] SpecialitysList = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("services", ServicesList);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);

        jsonAsMapProfileService1.put("id", Service1);
        jsonAsMapProfileSpeciality1.put("id", Spec1);
        jsonAsMapProfileSpeciality2.put("id", Spec2);

        jsonAsMapProfileContact.put("email", EmailCont);
        jsonAsMapProfileContact.put("phone", PhoneCont);
        jsonAsMapProfileContact.put("instagram", Instagram);
        jsonAsMapProfileContact.put("vk", Vk);
        jsonAsMapProfileContact.put("whatsapp", Whatsapp);
        jsonAsMapProfileContact.put("viber", Viber);
        jsonAsMapProfileContact.put("facebook", Facebook);
        jsonAsMapProfileContact.put("another", Other);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ: " + response.getBody().asString());
    }

    public static void uploadPhotoUserAPI(String Token, String UserID, String Photo) {
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType("multipart/form-data").
                        accept("application/json").
                        multiPart(new File(ConfigProperties.getTestProperty("FilesAPI") + Photo)).
                        multiPart("user_id", UserID).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/userpic/").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ: " + response.getBody().asString());
    }
}



