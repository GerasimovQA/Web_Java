package time;

import global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import utils.ConfigProperties;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

@RunWith(JUnitParamsRunner.class)
public class TimeLogic {

    String Token = "";
    String UserID = "";

    @Before
    public void login() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("auth", GlobalPage.LoginAUT);
        jsonAsMap.put("password", GlobalPage.PasswordAUT);

        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(jsonAsMap).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/sess/login").
                        when().post().
                        then().extract().response();

        Token = response.path("sid_hash");
        System.out.println(Token);
    }

    public void createUserAPI(String Login, String Email, String Phone, String Password, String Status, String SecondName, String FirstName, String MiddleName, String Superuser) {

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
                given().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
                        when().post().
                        then().extract().response();

        UserID = response.path("user_id");
        System.out.println("Ответ: " + response.getBody().asString());
        System.out.println("UserID= " + UserID);
    }


    public void profileUserAPI(String Depart, String Post, String Role, String Specialities, String Label, String ID, String LabelChild, String Cost, String IDchild, String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk, String Whatsapp, String Viber, String Facebook, String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileworkplaces = new HashMap<>();
        Map<String, Object> jsonAsMapProfileservices = new HashMap<>();
        Map<String, Object> jsonAsMapProfilechildren = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        String SpecList[] = {Specialities};

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("workplaces", jsonAsMapProfileworkplaces);
        jsonAsMapProfileworkplaces.put("depart", Depart);
        jsonAsMapProfileworkplaces.put("post", Post);
        jsonAsMapProfileworkplaces.put("role", Role);
        jsonAsMapProfile.put("specialities", SpecList);
        jsonAsMapProfile.put("services", jsonAsMapProfileservices);
        jsonAsMapProfileservices.put("label", Label);
        jsonAsMapProfileservices.put("id", ID);
        jsonAsMapProfileservices.put("children", jsonAsMapProfilechildren);
        jsonAsMapProfilechildren.put("label", LabelChild);
        jsonAsMapProfilechildren.put("cost", Cost);
        jsonAsMapProfilechildren.put("id", IDchild);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);
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
                given().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();


        System.out.println("Ответ: " + response.getBody().asString());
    }

    public void uploadPhotoUserAPI(String Photo) {
        Response response =
                given().
                        header("Authorization", "Bearer " + Token).
                        contentType("multipart/form-data").
                        accept("application/json").
                        multiPart(new File(ConfigProperties.getTestProperty("Files") + Photo)).
                        multiPart("user_id", UserID).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/userpic/").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ: " + response.getBody().asString());

    }

}
