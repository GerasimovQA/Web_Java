package global;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import utils.ConfigProperties;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;

@RunWith(JUnitParamsRunner.class)
public class EnvironmentLogic {

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

        UserID = response.path("user_id");
        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
        System.out.println("Создали пользователя с UserID: " + UserID);
    }


    public static void profileUserAPI(String Depart, String Post, String Ref, String Role, String Status,
                                      String Specialities, String Regalia, String EmailCont, String PhoneCont,
                                      String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
                                      String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileworkplaces = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileservices = new HashMap<>();
//        Map<String, Object> jsonAsMapProfilechildren = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpecialities = new HashMap<>();
        String SpecList[] = {Specialities};
//        ArrayList arrayList = jsonAsMapProfileworkplaces;
//        String Workplaces[] = {jsonAsMapProfileworkplaces};
        Map Workplaces[] = {jsonAsMapProfileworkplaces};

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("workplaces", Workplaces);
        jsonAsMapProfile.put("specialities", jsonAsMapProfileSpecialities);


        jsonAsMapProfileworkplaces.put("depart", Depart);
        jsonAsMapProfileworkplaces.put("post", Post);
        jsonAsMapProfileworkplaces.put("ref", Ref);
        jsonAsMapProfileworkplaces.put("role", Role);
        jsonAsMapProfileworkplaces.put("status", Status);
        jsonAsMapProfileSpecialities.put("specialities", SpecList);
//        jsonAsMapProfile.put("services", jsonAsMapProfileservices);
//        jsonAsMapProfileservices.put("label", Label);
//        jsonAsMapProfileservices.put("id", ID);
//        jsonAsMapProfileservices.put("children", jsonAsMapProfilechildren);
//        jsonAsMapProfilechildren.put("label", LabelChild);
//        jsonAsMapProfilechildren.put("cost", Cost);
//        jsonAsMapProfilechildren.put("id", IDchild);
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
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();


        System.out.println("Ответ: " + response.getBody().asString());
    }

    public static void uploadPhotoUserAPI(String Photo) {
        Response response =
                given().log().all().
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


    public static void BigDataAPI() {

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        queryParam("limit", "10000").
                        queryParam("offset", "0").
                        queryParam("obj_id", "5b58130f0faa2638b94fc4d7").
                        queryParam("t_beg", "2017-08-31T20:00:00.000Z").
                        queryParam("t_end", "2018-09-01T19:59:59.000Z").
                        contentType(ContentType.JSON).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("passport/admin/hist").
                        when().get().
                        then().extract().response();

        System.out.println("История изменений за указанный срок: " + response.getBody().asString());

        ArrayList ListId = new ArrayList<>();
        List<String> listdata = new ArrayList<>();

        ListId.add(response.path("hist.ts"));
        String[] subStr;
        String delimeter = ","; // Разделитель
        subStr = ListId.get(0).toString().split(delimeter);

        for (int i = 0; i < subStr.length; i++) {
            listdata.add(subStr[i]);

            int index = subStr[i].indexOf("T");
            String StringDate = subStr[i].substring(0, index).replace(" ", "")
                    .replace("[", "").replace("]", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(StringDate, formatter);
            System.out.println(date);

            LocalDate StartDate = LocalDate.of(2017, 8, 31);
            LocalDate EndtDate = LocalDate.of(2018, 9, 1);
            System.out.println(date + " больше " + StartDate + " и меньше " + EndtDate);
            Assert.assertTrue(date.isAfter(StartDate) & date.isBefore(EndtDate));
        }
        System.out.println("Количество записей: " + listdata.size());
        System.out.println(listdata);
    }
}
