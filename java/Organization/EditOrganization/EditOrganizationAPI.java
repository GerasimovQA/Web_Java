package Organization.EditOrganization;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static Global.EnvironmentGlobalSearch.OrganizationID;

import static io.restassured.RestAssured.given;

public class EditOrganizationAPI {

    public String loginAPI( String Login, String Password) {
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

    public String createOrganizationAPI(String Token, String Parent) {
        Map<String, Object> jsonAsMapParent = new HashMap<>();
        jsonAsMapParent.put("parent_id", Parent);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapParent).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/create").
                        when().post().
                        then().extract().response();

       String  OrganizationID = response.path("doc_id");
        System.out.println("Ответ при создании организации: " + response.getBody().asString());
        System.out.println("Создали организациб с OrgID: " + OrganizationID);
        return OrganizationID;
    }

    public void profileOrganizationAPI(String Token, String OrganizationID, String Name, String Abbr, String Chief, String Help_conditions,
                                              String Org_profile, String Description, String Address, String GeodataX,
                                              String GeodataY, String Site, String Email, String Facebook,
                                              String Instagram, String Vk, String OtherName, String OtherValue,
                                              String NamePhone1,
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
        Map<String, Object> jsonAsMapAnother = new HashMap<>();
        String Geodata[] = {GeodataX, GeodataY};
        Map MapcontactsPhones[] = {jsonAsMapcontactsPhones};
        Map MapcontactsAnother[] = {jsonAsMapAnother};

        jsonAsMapProfile.put("doc_id", OrganizationID);
        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);

        jsonAsMapAnother.put("name", OtherName);
        jsonAsMapAnother.put("value", OtherValue);

        jsonAsMapBlocks.put("contacts", jsonAsMapContacts);
        jsonAsMapBlocks.put("description", jsonAsMapDescription);
        jsonAsMapBlocks.put("geodata", jsonAsMapGeodata);
        jsonAsMapBlocks.put("info", jsonAsMapInfo);
        jsonAsMapBlocks.put("services", jsonAsMapServices);
        jsonAsMapBlocks.put("system_info", jsonAsMapSystem_info);

        jsonAsMapContacts.put("site", Site);
        jsonAsMapContacts.put("address", Address);
        jsonAsMapContacts.put("another", MapcontactsAnother);
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


        System.out.println("Ответ после заполнения профиля организации: " + response.getBody().asString());
    }

    public String createCabinetAPI(String Token, String Organization, String NameCabinet, String DescriptionCabinet) {
        Map<String, Object> jsonAsMapParent = new HashMap<>();
        jsonAsMapParent.put("org_id", Organization);
        jsonAsMapParent.put("name", NameCabinet);
        jsonAsMapParent.put("descr", DescriptionCabinet);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapParent).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/cabinet/add").
                        when().post().
                        then().extract().response();

        String CabinetID = response.path("cabinet_id");
        System.out.println("Ответ при создании кабинета: " + response.getBody().asString());
        System.out.println("Создали Кабинет с cabinet_id: " + OrganizationID);
        return CabinetID;
    }

    public String createWorkplaceAPI(String Token, String CabinetID, String NameWorkplace, String DescriptionWorkplace) {
        Map<String, Object> jsonAsMapParent = new HashMap<>();
        jsonAsMapParent.put("cabinet_id", CabinetID);
        jsonAsMapParent.put("name", NameWorkplace);
        jsonAsMapParent.put("descr", DescriptionWorkplace);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapParent).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/cabinet/workplace/add").
                        when().post().
                        then().extract().response();

       String WorkplaceID = response.path("workspace_id");
        System.out.println("Ответ при создании рабочего места: " + response.getBody().asString());
        System.out.println("Создали Кабинет с workspace_id: " + WorkplaceID);
        return WorkplaceID;
    }
}