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
public class EnvironmentOrganization {

    static String Token = "";
    static String OrganizationID = "";

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

    public static void createOrganizationAPI() {

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/create").
                        when().post().
                        then().extract().response();

        OrganizationID = response.path("doc_id");
        System.out.println("Ответ при создании организации: " + response.getBody().asString());
        System.out.println("Создали организациб с OrgID: " + OrganizationID);
    }

    public static void profileOrganizationAPI(String Name, String Abbr, String Chief, String Help_conditions,
                                              String Org_profile, String Description, String Address, String GeodataX,
                                              String GeodataY, String Email, String Facebook,
                                              String Instagram, String Vk, String Other, String NamePhone1,
                                              String NumberPhone1, String NamePhone2, String NumberPhone2,
                                              String Code, String Oms_number, String Pump, String Oms_id,
                                              String Status) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapBlocks = new HashMap<>();
        Map<String, Object> jsonAsMapContacts = new HashMap<>();
        Map<String, Object> jsonAsMapcontactsPhones = new HashMap<>();
        Map<String, Object> jsonAsMapDescription = new HashMap<>();
        Map<String, Object> jsonAsMapGeodata = new HashMap<>();
        Map<String, Object> jsonAsMapInfo = new HashMap<>();
        Map<String, Object> jsonAsMapServices = new HashMap<>();
        Map<String, Object> jsonAsMapSystem_info = new HashMap<>();
        String Geodata[] = {GeodataX, GeodataY};
        Map MapcontactsPhones[] = {jsonAsMapcontactsPhones};

        jsonAsMapProfile.put("doc_id", OrganizationID);
        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);

        jsonAsMapBlocks.put("contacts", jsonAsMapContacts);
        jsonAsMapBlocks.put("description", jsonAsMapDescription);
        jsonAsMapBlocks.put("geodata", jsonAsMapGeodata);
        jsonAsMapBlocks.put("info", jsonAsMapInfo);
        jsonAsMapBlocks.put("services", jsonAsMapServices);
        jsonAsMapBlocks.put("system_info", jsonAsMapSystem_info);

        jsonAsMapContacts.put("address", Address);
        jsonAsMapContacts.put("another", Other);
        jsonAsMapContacts.put("email", Email);
        jsonAsMapContacts.put("facebook", Facebook);
        jsonAsMapContacts.put("instagram", Instagram);
        jsonAsMapContacts.put("vk", Vk);
        jsonAsMapContacts.put("phones", MapcontactsPhones);

        jsonAsMapcontactsPhones.put("name", NamePhone1);
        jsonAsMapcontactsPhones.put("number", NumberPhone1);

        jsonAsMapDescription.put("text", Description);

        jsonAsMapGeodata.put("coords", Geodata);

        jsonAsMapInfo.put("abbr", Abbr);
        jsonAsMapInfo.put("chief", Chief);
        jsonAsMapInfo.put("help_conditions", Help_conditions);
        jsonAsMapInfo.put("name", Name);
        jsonAsMapInfo.put("org_profile", Org_profile);

        jsonAsMapServices.put("list", "");

        jsonAsMapSystem_info.put("code", Code);
        jsonAsMapSystem_info.put("oms_number", Oms_number);
        jsonAsMapSystem_info.put("pump", Pump);
        jsonAsMapSystem_info.put("oms_id", Oms_id);
        jsonAsMapSystem_info.put("status", Status);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/update").
                        when().post().
                        then().extract().response();


        System.out.println("Ответ: " + response.getBody().asString());
    }

    public static void createOrganizationParent(String Parent) {
        Map<String, Object> jsonAsMapParent = new HashMap<>();
        jsonAsMapParent.put("doc_id", OrganizationID);
        jsonAsMapParent.put("parent", Parent);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapParent).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/parent_ord").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при указании родителя: " + response.getBody().asString());

    }

}