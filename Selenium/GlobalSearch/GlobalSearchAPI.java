package GlobalSearch;

import Global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigProperties;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class GlobalSearchAPI extends GlobalPage {

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

    // API для создания сотрудника ->
    public String createUserAPI(String Token, String Login, String Password, String Email, String Phone, String Status,
                                String SecondName, String FirstName, String MiddleName, String Superuser,
                                String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                String Role1, String Role2) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        String[] PostsList = {Post1, Post2};
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
        System.out.println("Создали пользователя с ServicesSectionID: " + UserID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return UserID;
    }

    public void profileUserAPI(String Token, String UserID1, String Spec1, String Spec2, String Service1, String Service2, String Regalia,
                                      String EmailCont, String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                      String Viber, String Facebook, String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map[] ServicesList = {jsonAsMapProfileService1};
        Map[] SpecialitysList = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};

        jsonAsMapProfile.put("user_id", UserID1);
        jsonAsMapProfile.put("services", ServicesList);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);

        jsonAsMapProfileService1.put("id", Service1);
        jsonAsMapProfileService1.put("id", Service2);
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
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserNamesAPI(String Token, String UserID, String NewSecondName, String NewFirstName, String NewMiddleName) {
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
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void deleteUserAPI(String Token, String UserID1) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID1);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/delete").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при удалении юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
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

        String OrganizationID = response.path("doc_id");
        System.out.println("Ответ при создании организации: " + response.getBody().asString());
        System.out.println("Создали организацию с OrgID: " + OrganizationID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return OrganizationID;
    }

    public void profileOrganizationAPI(String Token, String OrganizationID, String Name, String Abbr, String Chief, String Help_conditions,
                                              String Org_profile, String Description, String Address, String GeodataX,
                                              String GeodataY, String Site, String Email, String Facebook,
                                              String Instagram, String Vk, String OtherName, String OtherValue,
                                              String NamePhone1, String NumberPhone1, String NamePhone2,
                                              String NumberPhone2, String Code, String Oms_number, String Pump,
                                              String Oms_id, String Status, String Service1) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapBlocks = new HashMap<>();
        Map<String, Object> jsonAsMapContacts = new HashMap<>();
        Map<String, Object> jsonAsMapService1 = new HashMap<>();
        Map<String, Object> jsonAsMapService2 = new HashMap<>();
        Map<String, Object> jsonAsMapService3 = new HashMap<>();
        Map<String, Object> jsonAsMapListServices = new HashMap<>();
        Map<String, Object> jsonAsMapcontactsPhones = new HashMap<>();
        Map<String, Object> jsonAsMapDescription = new HashMap<>();
        Map<String, Object> jsonAsMapGeodata = new HashMap<>();
        Map<String, Object> jsonAsMapInfo = new HashMap<>();
        Map<String, Object> jsonAsMapServices = new HashMap<>();
        Map<String, Object> jsonAsMapSystem_info = new HashMap<>();
        Map<String, Object> jsonAsMapAnother = new HashMap<>();
        String[] Geodata = {GeodataX, GeodataY};
        Object[] ListServices = {jsonAsMapService1, jsonAsMapService2, jsonAsMapService3};
        Map[] MapcontactsPhones = {jsonAsMapcontactsPhones};
        Map[] MapcontactsAnother = {jsonAsMapAnother};

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

        jsonAsMapService1.put("id", Service1);
        jsonAsMapListServices.put("id", ListServices);

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
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editInfoOrganizationAPI(String Token, String OrganizationID, String NewNameOrg, String NewAbbrOrg, String NewService1) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapNewInfoOrg = new HashMap<>();
        Map<String, Object> jsonAsMapNewServiceOrg = new HashMap<>();
        Map<String, Object> jsonAsMapInfo = new HashMap<>();
        Map<String, Object> jsonAsMapOneServices = new HashMap<>();
        Map<String, Object> jsonAsMapListNewServices = new HashMap<>();
        Map<String, Object> jsonAsMapBlocks = new HashMap<>();
        Object[] ListServices = {jsonAsMapNewServiceOrg};


        jsonAsMapNewInfoOrg.put("name", NewNameOrg);
        jsonAsMapNewInfoOrg.put("abbr", NewAbbrOrg);
        jsonAsMapNewServiceOrg.put("id", NewService1);
        jsonAsMapListNewServices.put("list", ListServices);

        jsonAsMapBlocks.put("info", jsonAsMapNewInfoOrg);
        jsonAsMapBlocks.put("services", jsonAsMapListNewServices);

        jsonAsMapProfile.put("doc_id", OrganizationID);
        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при редактировании организации: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void deletOrganizationAPI(String Token, String OrganizationID) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("doc_id", OrganizationID);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/delete").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при удалении организации: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public String createServiceAPI(String Token, String CodeService, String NameService,
                                   String PrintNameService, String Parent, String VendorService,
                                   String ContraindicationsService, String CreatorService,
                                   String DescriptionService, String PreconditionService, String TypeService,
                                   String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
                                   String Favorit, String Record) {
        Map<String, Object> jsonAsMapServices = new HashMap<>();
        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
        String[] Other = {OtherCosts};

        jsonAsMapServicesCost.put("other", Other);
        jsonAsMapServicesCost.put("dms", DmsCost);
        jsonAsMapServicesCost.put("oms", OmsCost);
        jsonAsMapServicesCost.put("pmu", PmuCost);
        jsonAsMapServices.put("price", jsonAsMapServicesCost);

        jsonAsMapServices.put("code", CodeService);
        jsonAsMapServices.put("name", NameService);
        jsonAsMapServices.put("parent_id", Parent);
        jsonAsMapServices.put("print_name", PrintNameService);
        jsonAsMapServices.put("contraindications", ContraindicationsService);
        jsonAsMapServices.put("creator", CreatorService);
        jsonAsMapServices.put("description", DescriptionService);
        jsonAsMapServices.put("preconditions", PreconditionService);
        jsonAsMapServices.put("type", TypeService);
        jsonAsMapServices.put("vendor_code", VendorService);
        jsonAsMapServices.put("is_favorite:", ("true".equals(Favorit)));
        jsonAsMapServices.put("patint_record", ("true".equals(Record)));

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapServices).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/new").
                        when().post().
                        then().extract().response();

        String ServiceID = response.path("_id");
        System.out.println("Ответ при создании услуи: " + response.getBody().asString());
        System.out.println("Создали услугу с _id: " + ServiceID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return ServiceID;
    }

    public void editServiceAPI(String Token, String ServiceID, String NewCodeService, String NewNameService,
                                      String NewPrintNameService, String NewParent, String NewVendorService,
                                      String NewContraindicationsService, String NewCreatorService,
                                      String NewDescriptionService, String NewPreconditionService,
                                      String NewTypeService, String NewDmsCost, String NewOmsCost, String NewPmuCost,
                                      String NewOtherCosts, String NewFavorit, String NewRecord) {
        Map<String, Object> jsonAsMapServices = new HashMap<>();
        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
        String[] Other = {NewOtherCosts};

        jsonAsMapServicesCost.put("other", Other);
        jsonAsMapServicesCost.put("dms", NewDmsCost);
        jsonAsMapServicesCost.put("oms", NewOmsCost);
        jsonAsMapServicesCost.put("pmu", NewPmuCost);
        jsonAsMapServices.put("price", jsonAsMapServicesCost);

        jsonAsMapServices.put("item_id", ServiceID);
        jsonAsMapServices.put("code", NewCodeService);
        jsonAsMapServices.put("name", NewNameService);
        jsonAsMapServices.put("parent_id", NewParent);
        jsonAsMapServices.put("print_name", NewPrintNameService);
        jsonAsMapServices.put("contraindications", NewContraindicationsService);
        jsonAsMapServices.put("creator", NewCreatorService);
        jsonAsMapServices.put("description", NewDescriptionService);
        jsonAsMapServices.put("preconditions", NewPreconditionService);
        jsonAsMapServices.put("type", NewTypeService);
        jsonAsMapServices.put("vendor_code", NewVendorService);
        jsonAsMapServices.put("is_favorite:", ("true".equals(NewFavorit)));
        jsonAsMapServices.put("patint_record", ("true".equals(NewRecord)));

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapServices).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении услуи: " + response.getBody().asString());
        System.out.println("Изменили услугу с _id: " + ServiceID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void deleteServiceAPI(String Token, String ServiceID) {
        Map<String, Object> jsonAsMapServices = new HashMap<>();

        jsonAsMapServices.put("item_id", ServiceID);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapServices).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/delete").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении родителя услуи: " + response.getBody().asString());
        System.out.println("Изменили услугу с _id: " + ServiceID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public String createSpecialtyAPI(String Token, String Name, String Description, String Icon, String Parent) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("description", Description);
        jsonAsMapProfile.put("icon", Icon);
        jsonAsMapProfile.put("name", Name);
        jsonAsMapProfile.put("parent_id", Parent);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item/new").
                        when().post().
                        then().extract().response();

        String SpecialtyID = response.path("_id");
        System.out.println("Ответ при создании специальности: " + response.getBody().asString());
        System.out.println("Создали специальность с OrgID: " + SpecialtyID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return SpecialtyID;
    }

    public void editSpecialtyAPI(String Token, String SpecialtyID, String NewName, String NewDescription, String Icon1, String Parent1) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("item_id", SpecialtyID);
        jsonAsMapProfile.put("description", NewDescription);
        jsonAsMapProfile.put("name", NewName);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
                        "/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void deleteSpecialtyAPI(String Token, String SpecialtyID) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("item_id", SpecialtyID);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
                        "/delete").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }
}



