package Permissions.WorkerProfile;

import Global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WorkerProfilePage extends GlobalPage {

    public WorkerProfilePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    int u = 0;

    //    Ссылка "Забыли пароль?"
//    @FindBy(xpath = "//a[contains(text(),\"Забыли пароль?\")]")
//    public WebElement linkForgotPassword;


    public String loginAPI(String Login, String Password, String Response) {
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
        System.out.println("Ответ при получении токена: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
        return Token;
    }

    public void editUserNamesAPI(String Token, String UserID, String NewSecondName, String NewFirstName,
                                 String NewMiddleName, String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("first_name", NewFirstName);
        jsonAsMapProfile.put("last_name", NewSecondName);
        jsonAsMapProfile.put("middle_name", NewMiddleName);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/set_names").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении ФИО: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserAuthAPI(String Token, String UserID, String NewEmail, String NewPhone, String NewLogin,
                                String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("email", NewEmail);
        jsonAsMapProfile.put("loginAPI", NewLogin);
        jsonAsMapProfile.put("phone", NewPhone);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/set_auth").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении Auth юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserPasswordAPI(String Token, String UserID, String Password, String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("dropsess", true);
        jsonAsMapProfile.put("password", Password);


        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/set_auth").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении Auth юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserAddWorkplaceAPI(String Token, String UserID, String NewDepart, String NewPost,
                                        String NewRole, String NewStatus, String Public, String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        String[] PostsList = {NewPost};
        String[] RolesList = {NewRole};

        jsonAsMapProfile.put("posts", PostsList);
        jsonAsMapProfile.put("roles", RolesList);
        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("org_id", NewDepart);
        jsonAsMapProfile.put("status", NewStatus);
        jsonAsMapProfile.put("public", "true".equals(Public));

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при редактировании рабочего места юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserSpecialtyAPI(String Token, String UserID, String Speciality1, String Speciality2,
                                     String Speciality3, String Speciality4, String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality3 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality4 = new HashMap<>();

        List<Map> SpecialitysList = new ArrayList<Map>();
        SpecialitysList.add(jsonAsMapProfileSpeciality1);
        if (Speciality2 != null) {
            SpecialitysList.add(jsonAsMapProfileSpeciality2);
        }
        if (Speciality3 != null) {
            SpecialitysList.add(jsonAsMapProfileSpeciality3);
        }
        if (Speciality4 != null) {
            SpecialitysList.add(jsonAsMapProfileSpeciality4);
        }

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfileSpeciality1.put("id", Speciality1);

        if (Speciality2 != null) {
            jsonAsMapProfileSpeciality2.put("id", Speciality2);
        }
        if (Speciality3 != null) {
            jsonAsMapProfileSpeciality3.put("id", Speciality3);
        }
        if (Speciality4 != null) {
            jsonAsMapProfileSpeciality4.put("id", Speciality4);
        }

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при редактировании специальностей юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserEducationAPI(String Token, String UserID, String Education, String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("regalia", Education);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ после изменения образования у юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editUserContactsAPI(String Token, String UserID, String EmailCont, String PhoneCont, String Instagram,
                                    String Vk, String Whatsapp, String Viber, String Facebook, String Other,
                                    String Response) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
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
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ после изменения контактов юзера: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }

    public void editPhotoAPI(String Token, String UserID, String Photo, String Response) {
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

        System.out.println("Ответ после загрузки фото: " + response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(Response));
    }
}



