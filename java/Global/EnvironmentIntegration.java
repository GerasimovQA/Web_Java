package Global;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import utils.ConfigProperties;

import java.util.*;

import static io.restassured.RestAssured.given;

@RunWith(JUnitParamsRunner.class)
public class EnvironmentIntegration {

    static String Token = "";
    static String ServicesSectionID = "";
    static String ServiceID1 = "";
    static String ServiceID2 = "";
    static String ServiceID3 = "";
    public static String UserID1 = "";
    static String UserID2 = "";
    static String UserID3 = "";
    public static String OrganizationID1 = "";
    public static String SpecialtyID1 = "";
    static String SpecialtyID2 = "";

//    public static void login() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("auth", GlobalPage.LoginAUT_SA);
//        jsonAsMap.put("password", GlobalPage.PasswordAUT_SA);
//
//        Response response =
//                given().log().all().
//                        contentType(ContentType.JSON).
//                        body(jsonAsMap).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/sess/login").
//                        when().post().
//                        then().extract().response();
//
//        Token = response.path("sid_hash");
//        System.out.println("Получили токен: " + Token);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    // API для создания сотрудника ->
//    public static void createUserAPI(String Login, String Password, String Email, String Phone, String Status,
//                                     String SecondName, String FirstName, String MiddleName, String Superuser,
//                                     String SendEmail, String Depart, String Post, String Role) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        String[] PostsList = {Post};
//        String[] RolesList = {Role};
//
//        jsonAsMapPassport.put("login", Login);
//        jsonAsMapPassport.put("email", Email);
//        jsonAsMapPassport.put("phone", Phone);
//        jsonAsMapPassport.put("password", Password);
//        jsonAsMapPassport.put("status", Status);
//        jsonAsMapPassport.put("first_name", FirstName);
//        jsonAsMapPassport.put("middle_name", MiddleName);
//        jsonAsMapPassport.put("last_name", SecondName);
//        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
//        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
//        jsonAsMapPassport.put("org_id", Depart);
//        jsonAsMapPassport.put("org_posts", PostsList);
//        jsonAsMapPassport.put("org_roles", RolesList);
//        jsonAsMapPassport.put("org_status", "active");
//
//        System.out.println(jsonAsMapPassport);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
//                        when().post().
//                        then().extract().response();
//
//        UserID1 = response.path("user_id");
//        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
//        System.out.println("Создали пользователя с ServicesSectionID: " + UserID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileUserAPInew(String Depart, String Post, String Ref, String Role, String Status,
//                                         String Spec1, String Spec2, String Regalia, String EmailCont, String PhoneCont,
//                                         String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
//                                         String Other) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
//        Map ServicesList[] = {jsonAsMapProfileService1};
//        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("services", ServicesList);
//        jsonAsMapProfile.put("specialities", SpecialitysList);
//        jsonAsMapProfile.put("regalia", Regalia);
//        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);
//
//        jsonAsMapProfileService1.put("id", ServiceID1);
//        jsonAsMapProfileSpeciality1.put("id", Spec1);
//        jsonAsMapProfileSpeciality2.put("id", Spec2);
//
//        jsonAsMapProfileContact.put("email", EmailCont);
//        jsonAsMapProfileContact.put("phone", PhoneCont);
//        jsonAsMapProfileContact.put("instagram", Instagram);
//        jsonAsMapProfileContact.put("vk", Vk);
//        jsonAsMapProfileContact.put("whatsapp", Whatsapp);
//        jsonAsMapProfileContact.put("viber", Viber);
//        jsonAsMapProfileContact.put("facebook", Facebook);
//        jsonAsMapProfileContact.put("another", Other);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    // API для создания сотрудника -<
//    // API для проверки Услуг в профиле сотрудника ->
//    public static void createServicesSectionAPI(String CodeSection, String NameSection, String ParentSection,
//                                                String PrintNameSection, String VendorSection) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        jsonAsMapPassport.put("code", CodeSection);
//        jsonAsMapPassport.put("name", NameSection);
//        jsonAsMapPassport.put("parent_id", ParentSection);
//        jsonAsMapPassport.put("print_name", PrintNameSection);
//        jsonAsMapPassport.put("vendor_code", VendorSection);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/new").
//                        when().post().
//                        then().extract().response();
//
//        ServicesSectionID = response.path("_id");
//        System.out.println("Ответ при создании раздела услуг: " + response.getBody().asString());
//        System.out.println("Создали раздел услуг с _id: " + ServicesSectionID);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editServicesSectionAPI(String NewCodeSection, String NewNameSectionme,
//                                              String NewPrintNameSection,
//                                              String NewVendorSection) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        jsonAsMapPassport.put("node_id", ServicesSectionID);
//        jsonAsMapPassport.put("code", NewCodeSection);
//        jsonAsMapPassport.put("name", NewNameSectionme);
//        jsonAsMapPassport.put("print_name", NewPrintNameSection);
//        jsonAsMapPassport.put("vendor_code", NewVendorSection);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при редактировании раздела услуг: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createServiceAPI(String CodeService, String NameService,
//                                        String PrintNameService, String Parent, String VendorService,
//                                        String ContraindicationsService, String CreatorService,
//                                        String DescriptionService, String PreconditionService, String TypeService,
//                                        String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
//                                        String Favorit, String Record) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
//        String[] Other = {OtherCosts};
//
//        jsonAsMapServicesCost.put("other", Other);
//        jsonAsMapServicesCost.put("dms", DmsCost);
//        jsonAsMapServicesCost.put("oms", OmsCost);
//        jsonAsMapServicesCost.put("pmu", PmuCost);
//        jsonAsMapServices.put("price", jsonAsMapServicesCost);
//
//        jsonAsMapServices.put("code", CodeService);
//        jsonAsMapServices.put("name", NameService);
//        jsonAsMapServices.put("parent_id", ServicesSectionID);
//        jsonAsMapServices.put("print_name", PrintNameService);
//        jsonAsMapServices.put("contraindications", ContraindicationsService);
//        jsonAsMapServices.put("creator", CreatorService);
//        jsonAsMapServices.put("description", DescriptionService);
//        jsonAsMapServices.put("preconditions", PreconditionService);
//        jsonAsMapServices.put("type", TypeService);
//        jsonAsMapServices.put("vendor_code", VendorService);
//        jsonAsMapServices.put("is_favorite:", ("true".equals(Favorit)));
//        jsonAsMapServices.put("patint_record", ("true".equals(Record)));
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/new").
//                        when().post().
//                        then().extract().response();
//
//        ServiceID1 = response.path("_id");
//        System.out.println("Ответ при создании услуи: " + response.getBody().asString());
//        System.out.println("Создали услугу с _id: " + ServiceID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editServiceAPI(String NewCodeService, String NewNameService,
//                                      String NewPrintNameService, String NewVendorService,
//                                      String NewContraindicationsService, String NewCreatorService,
//                                      String NewDescriptionService, String NewPreconditionService,
//                                      String NewTypeService, String NewDmsCost, String NewOmsCost, String NewPmuCost,
//                                      String NewOtherCosts, String NewFavorit, String NewRecord) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
//        String[] Other = {NewOtherCosts};
//
//        jsonAsMapServicesCost.put("other", Other);
//        jsonAsMapServicesCost.put("dms", NewDmsCost);
//        jsonAsMapServicesCost.put("oms", NewOmsCost);
//        jsonAsMapServicesCost.put("pmu", NewPmuCost);
//        jsonAsMapServices.put("price", jsonAsMapServicesCost);
//
//        jsonAsMapServices.put("item_id", ServiceID1);
//        jsonAsMapServices.put("code", NewCodeService);
//        jsonAsMapServices.put("name", NewNameService);
////        jsonAsMapServices.put("parent_id", NewParent);
//        jsonAsMapServices.put("print_name", NewPrintNameService);
//        jsonAsMapServices.put("contraindications", NewContraindicationsService);
//        jsonAsMapServices.put("creator", NewCreatorService);
//        jsonAsMapServices.put("description", NewDescriptionService);
//        jsonAsMapServices.put("preconditions", NewPreconditionService);
//        jsonAsMapServices.put("type", NewTypeService);
//        jsonAsMapServices.put("vendor_code", NewVendorService);
//        jsonAsMapServices.put("is_favorite:", ("true".equals(NewFavorit)));
//        jsonAsMapServices.put("patint_record", ("true".equals(NewRecord)));
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при изменении услуи: " + response.getBody().asString());
//        System.out.println("Изменили услугу с _id: " + ServiceID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editServiceParentAPI(String NewParent) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//
//        jsonAsMapServices.put("item_id", ServiceID1);
//        jsonAsMapServices.put("parent_id", NewParent);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/parent").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при изменении родителя услуги: " + response.getBody().asString());
//        System.out.println("Изменили услугу с _id: " + ServiceID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void deleteServiceAPI() {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//
//        jsonAsMapServices.put("item_id", ServiceID1);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/delete").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при удалении услуи: " + response.getBody().asString());
//        System.out.println("Удалили услугу с _id: " + ServiceID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    // API для проверки Услуг в профиле сотрудника <-
//// API для проверки Организации в профиле сотрудника ->
//    public static void createUserVariableDepartAPI(String Login, String Password, String Email, String Phone,
//                                                   String Status,
//                                                   String SecondName, String FirstName, String MiddleName,
//                                                   String Superuser,
//                                                   String SendEmail, String Depart, String Post, String Role) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        String[] PostsList = {Post};
//        String[] RolesList = {Role};
//
//        jsonAsMapPassport.put("login", Login);
//        jsonAsMapPassport.put("email", Email);
//        jsonAsMapPassport.put("phone", Phone);
//        jsonAsMapPassport.put("password", Password);
//        jsonAsMapPassport.put("status", Status);
//        jsonAsMapPassport.put("first_name", FirstName);
//        jsonAsMapPassport.put("middle_name", MiddleName);
//        jsonAsMapPassport.put("last_name", SecondName);
//        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
//        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
//        jsonAsMapPassport.put("org_id", OrganizationID1);
//        jsonAsMapPassport.put("org_posts", PostsList);
//        jsonAsMapPassport.put("org_roles", RolesList);
//        jsonAsMapPassport.put("org_status", "active");
//
//        System.out.println(jsonAsMapPassport);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
//                        when().post().
//                        then().extract().response();
//
//        UserID1 = response.path("user_id");
//        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
//        System.out.println("Создали пользователя с ServicesSectionID: " + UserID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createOrganizationAPI(String Parent) {
//        Map<String, Object> jsonAsMapParent = new HashMap<>();
//        jsonAsMapParent.put("parent_id", Parent);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapParent).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/create").
//                        when().post().
//                        then().extract().response();
//
//        OrganizationID1 = response.path("doc_id");
//        System.out.println("Ответ при создании организации: " + response.getBody().asString());
//        System.out.println("Создали организацию с OrgID: " + OrganizationID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileOrganizationAPI(String Name, String Abbr, String Chief, String Help_conditions,
//                                              String Org_profile, String Description, String Address, String GeodataX,
//                                              String GeodataY, String Site, String Email, String Facebook,
//                                              String Instagram, String Vk, String OtherName, String OtherValue,
//                                              String NamePhone1, String NumberPhone1, String NamePhone2,
//                                              String NumberPhone2, String Code, String Oms_number, String Pump,
//                                              String Oms_id, String Status, String Service1, String Service2,
//                                              String Service3) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapBlocks = new HashMap<>();
//        Map<String, Object> jsonAsMapContacts = new HashMap<>();
//        Map<String, Object> jsonAsMapService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapService2 = new HashMap<>();
//        Map<String, Object> jsonAsMapService3 = new HashMap<>();
//        Map<String, Object> jsonAsMapListServices = new HashMap<>();
//        Map<String, Object> jsonAsMapcontactsPhones = new HashMap<>();
//        Map<String, Object> jsonAsMapDescription = new HashMap<>();
//        Map<String, Object> jsonAsMapGeodata = new HashMap<>();
//        Map<String, Object> jsonAsMapInfo = new HashMap<>();
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapSystem_info = new HashMap<>();
//        Map<String, Object> jsonAsMapAnother = new HashMap<>();
//        String Geodata[] = {GeodataX, GeodataY};
//        Object ListServices[] = {jsonAsMapService1, jsonAsMapService2, jsonAsMapService3};
//        Map MapcontactsPhones[] = {jsonAsMapcontactsPhones};
//        Map MapcontactsAnother[] = {jsonAsMapAnother};
//
//        jsonAsMapProfile.put("doc_id", OrganizationID1);
//        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);
//
//        jsonAsMapAnother.put("name", OtherName);
//        jsonAsMapAnother.put("value", OtherValue);
//
//        jsonAsMapBlocks.put("contacts", jsonAsMapContacts);
//        jsonAsMapBlocks.put("description", jsonAsMapDescription);
//        jsonAsMapBlocks.put("geodata", jsonAsMapGeodata);
//        jsonAsMapBlocks.put("info", jsonAsMapInfo);
//        jsonAsMapBlocks.put("services", jsonAsMapServices);
//        jsonAsMapBlocks.put("system_info", jsonAsMapSystem_info);
//
//        jsonAsMapContacts.put("site", Site);
//        jsonAsMapContacts.put("address", Address);
//        jsonAsMapContacts.put("another", MapcontactsAnother);
//        jsonAsMapContacts.put("email", Email);
//        jsonAsMapContacts.put("facebook", Facebook);
//        jsonAsMapContacts.put("instagram", Instagram);
//        jsonAsMapContacts.put("vk", Vk);
//        jsonAsMapContacts.put("phones", MapcontactsPhones);
//
//        jsonAsMapcontactsPhones.put("name", NamePhone1);
//        jsonAsMapcontactsPhones.put("number", NumberPhone1);
//
//        jsonAsMapDescription.put("text", Description);
//
//        jsonAsMapGeodata.put("coords", Geodata);
//
//        jsonAsMapInfo.put("abbr", Abbr);
//        jsonAsMapInfo.put("chief", Chief);
//        jsonAsMapInfo.put("help_conditions", Help_conditions);
//        jsonAsMapInfo.put("name", Name);
//        jsonAsMapInfo.put("org_profile", Org_profile);
//
//        jsonAsMapService1.put("id", Service1);
//        jsonAsMapService2.put("id", Service2);
//        jsonAsMapService3.put("id", Service3);
//        jsonAsMapListServices.put("id", ListServices);
//
//        jsonAsMapSystem_info.put("code", Code);
//        jsonAsMapSystem_info.put("oms_number", Oms_number);
//        jsonAsMapSystem_info.put("pump", Pump);
//        jsonAsMapSystem_info.put("oms_id", Oms_id);
//        jsonAsMapSystem_info.put("status", Status);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//
//    public static void editNameOrganizationAPI(String NewNameOrg) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapNewName = new HashMap<>();
//        Map<String, Object> jsonAsMapInfo = new HashMap<>();
//
//        jsonAsMapNewName.put("name", NewNameOrg);
//        jsonAsMapInfo.put("info", jsonAsMapNewName);
//        jsonAsMapProfile.put("doc_id", OrganizationID1);
//        jsonAsMapProfile.put("blocks", jsonAsMapInfo);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при редактировании организации: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editParentOrganizationAPI(String NewParentOrg) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("doc_id", OrganizationID1);
//        jsonAsMapProfile.put("parent", "org:" + NewParentOrg);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/parent_ord").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при изменении Parent организации: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void deleteOrganizationAPI() {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("doc_id", OrganizationID1);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/delete").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при удалении организации: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    // API для проверки Организации в профиле сотрудника <-
//    // API для проверки Специальностей в профиле сотрудника ->
//    public static void createSpecialtyAPI(String Name, String Description, String Icon, String Parent) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("description", Description);
//        jsonAsMapProfile.put("icon", Icon);
//        jsonAsMapProfile.put("name", Name);
//        jsonAsMapProfile.put("parent_id", Parent);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item/new").
//                        when().post().
//                        then().extract().response();
//
//        SpecialtyID1 = response.path("_id");
//        System.out.println("Ответ при создании специальности: " + response.getBody().asString());
//        System.out.println("Создали специальность с OrgID: " + SpecialtyID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createSpecialty2API(String Name, String Description, String Icon, String Parent) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("description", Description);
//        jsonAsMapProfile.put("icon", Icon);
//        jsonAsMapProfile.put("name", Name);
//        jsonAsMapProfile.put("parent_id", Parent);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item/new").
//                        when().post().
//                        then().extract().response();
//
//        SpecialtyID2 = response.path("_id");
//        System.out.println("Ответ при создании организации: " + response.getBody().asString());
//        System.out.println("Создали организациб с OrgID: " + SpecialtyID2);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editSpecialtyAPI(String NewName, String NewDescription) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("item_id", SpecialtyID1);
//        jsonAsMapProfile.put("description", NewDescription);
//        jsonAsMapProfile.put("name", NewName);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
//                        "/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editParentSpecialtyAPI(String NewIcon, String NewParent) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("item_id", SpecialtyID1);
//        jsonAsMapProfile.put("parent_id", NewParent);
//        jsonAsMapProfile.put("icon", NewIcon);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
//                        "/parent").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void deleteSpecialtyAPI() {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("item_id", SpecialtyID1);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
//                        "/delete").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserSpecialtyAPI(String Spec1, String Spec2) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality3 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality4 = new HashMap<>();
//
//        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2,
//                jsonAsMapProfileSpeciality3, jsonAsMapProfileSpeciality4};
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("specialities", SpecialitysList);
//
//        jsonAsMapProfileSpeciality1.put("id", Spec1);
//        jsonAsMapProfileSpeciality2.put("id", Spec2);
//        jsonAsMapProfileSpeciality3.put("id", SpecialtyID1);
//        jsonAsMapProfileSpeciality4.put("id", SpecialtyID2);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//    // API для проверки Специальностей в профиле сотрудника <-
//
////    -------------------------------------------------------------------------------------------------------------
//
//    // API для проверки Услуг в профиле организации ->
//    public static void createService1OrgAPI(String CodeService, String NameService,
//                                            String PrintNameService, String Parent, String VendorService,
//                                            String ContraindicationsService, String CreatorService,
//                                            String DescriptionService, String PreconditionService, String TypeService,
//                                            String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
//                                            String Favorit, String Record) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
//        String[] Other = {OtherCosts};
//
//        jsonAsMapServicesCost.put("other", Other);
//        jsonAsMapServicesCost.put("dms", DmsCost);
//        jsonAsMapServicesCost.put("oms", OmsCost);
//        jsonAsMapServicesCost.put("pmu", PmuCost);
//        jsonAsMapServices.put("price", jsonAsMapServicesCost);
//
//        jsonAsMapServices.put("code", CodeService);
//        jsonAsMapServices.put("name", NameService);
//        jsonAsMapServices.put("parent_id", Parent);
//        jsonAsMapServices.put("print_name", PrintNameService);
//        jsonAsMapServices.put("contraindications", ContraindicationsService);
//        jsonAsMapServices.put("creator", CreatorService);
//        jsonAsMapServices.put("description", DescriptionService);
//        jsonAsMapServices.put("preconditions", PreconditionService);
//        jsonAsMapServices.put("type", TypeService);
//        jsonAsMapServices.put("vendor_code", VendorService);
//        jsonAsMapServices.put("is_favorite:", ("true".equals(Favorit)));
//        jsonAsMapServices.put("patint_record", ("true".equals(Record)));
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/new").
//                        when().post().
//                        then().extract().response();
//
//        ServiceID1 = response.path("_id");
//        System.out.println("Ответ при создании услуи 1 : " + response.getBody().asString());
//        System.out.println("Создали услугу с _id: " + ServiceID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createService2OrgAPI(String CodeService, String NameService,
//                                            String PrintNameService, String Parent, String VendorService,
//                                            String ContraindicationsService, String CreatorService,
//                                            String DescriptionService, String PreconditionService, String TypeService,
//                                            String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
//                                            String Favorit, String Record) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
//        String[] Other = {OtherCosts};
//
//        jsonAsMapServicesCost.put("other", Other);
//        jsonAsMapServicesCost.put("dms", DmsCost);
//        jsonAsMapServicesCost.put("oms", OmsCost);
//        jsonAsMapServicesCost.put("pmu", PmuCost);
//        jsonAsMapServices.put("price", jsonAsMapServicesCost);
//
//        jsonAsMapServices.put("code", CodeService);
//        jsonAsMapServices.put("name", NameService);
//        jsonAsMapServices.put("parent_id", Parent);
//        jsonAsMapServices.put("print_name", PrintNameService);
//        jsonAsMapServices.put("contraindications", ContraindicationsService);
//        jsonAsMapServices.put("creator", CreatorService);
//        jsonAsMapServices.put("description", DescriptionService);
//        jsonAsMapServices.put("preconditions", PreconditionService);
//        jsonAsMapServices.put("type", TypeService);
//        jsonAsMapServices.put("vendor_code", VendorService);
//        jsonAsMapServices.put("is_favorite:", ("true".equals(Favorit)));
//        jsonAsMapServices.put("patint_record", ("true".equals(Record)));
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/new").
//                        when().post().
//                        then().extract().response();
//
//        ServiceID2 = response.path("_id");
//        System.out.println("Ответ при создании услуи 2 : " + response.getBody().asString());
//        System.out.println("Создали услугу с _id: " + ServiceID2);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createService3OrgAPI(String CodeService, String NameService,
//                                            String PrintNameService, String Parent, String VendorService,
//                                            String ContraindicationsService, String CreatorService,
//                                            String DescriptionService, String PreconditionService, String TypeService,
//                                            String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
//                                            String Favorit, String Record) {
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
//        String[] Other = {OtherCosts};
//
//        jsonAsMapServicesCost.put("other", Other);
//        jsonAsMapServicesCost.put("dms", DmsCost);
//        jsonAsMapServicesCost.put("oms", OmsCost);
//        jsonAsMapServicesCost.put("pmu", PmuCost);
//        jsonAsMapServices.put("price", jsonAsMapServicesCost);
//
//        jsonAsMapServices.put("code", CodeService);
//        jsonAsMapServices.put("name", NameService);
//        jsonAsMapServices.put("parent_id", Parent);
//        jsonAsMapServices.put("print_name", PrintNameService);
//        jsonAsMapServices.put("contraindications", ContraindicationsService);
//        jsonAsMapServices.put("creator", CreatorService);
//        jsonAsMapServices.put("description", DescriptionService);
//        jsonAsMapServices.put("preconditions", PreconditionService);
//        jsonAsMapServices.put("type", TypeService);
//        jsonAsMapServices.put("vendor_code", VendorService);
//        jsonAsMapServices.put("is_favorite:", ("true".equals(Favorit)));
//        jsonAsMapServices.put("patint_record", ("true".equals(Record)));
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapServices).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/new").
//                        when().post().
//                        then().extract().response();
//
//        ServiceID3 = response.path("_id");
//        System.out.println("Ответ при создании услуи 3 : " + response.getBody().asString());
//        System.out.println("Создали услугу с _id: " + ServiceID3);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileOrganizationOrgAPI(String Name, String Abbr, String Chief, String Help_conditions,
//                                                 String Org_profile, String Description, String Address,
//                                                 String GeodataX,
//                                                 String GeodataY, String Site, String Email, String Facebook,
//                                                 String Instagram, String Vk, String OtherName, String OtherValue,
//                                                 String NamePhone1, String NumberPhone1, String NamePhone2,
//                                                 String NumberPhone2, String Code, String Oms_number, String Pump,
//                                                 String Oms_id, String Status, String Service1, String Service2,
//                                                 String Service3) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapBlocks = new HashMap<>();
//        Map<String, Object> jsonAsMapContacts = new HashMap<>();
//        Map<String, Object> jsonAsMapService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapService2 = new HashMap<>();
//        Map<String, Object> jsonAsMapService3 = new HashMap<>();
//        Map<String, Object> jsonAsMapListServices = new HashMap<>();
//        Map<String, Object> jsonAsMapcontactsPhones = new HashMap<>();
//        Map<String, Object> jsonAsMapDescription = new HashMap<>();
//        Map<String, Object> jsonAsMapGeodata = new HashMap<>();
//        Map<String, Object> jsonAsMapInfo = new HashMap<>();
//        Map<String, Object> jsonAsMapServices = new HashMap<>();
//        Map<String, Object> jsonAsMapSystem_info = new HashMap<>();
//        Map<String, Object> jsonAsMapAnother = new HashMap<>();
//        String Geodata[] = {GeodataX, GeodataY};
//        Object ListServices[] = {jsonAsMapService1, jsonAsMapService2, jsonAsMapService3};
//        Map MapcontactsPhones[] = {jsonAsMapcontactsPhones};
//        Map MapcontactsAnother[] = {jsonAsMapAnother};
//
//        jsonAsMapProfile.put("doc_id", OrganizationID1);
//        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);
//
//        jsonAsMapAnother.put("name", OtherName);
//        jsonAsMapAnother.put("value", OtherValue);
//
//        jsonAsMapBlocks.put("contacts", jsonAsMapContacts);
//        jsonAsMapBlocks.put("description", jsonAsMapDescription);
//        jsonAsMapBlocks.put("geodata", jsonAsMapGeodata);
//        jsonAsMapBlocks.put("info", jsonAsMapInfo);
//        jsonAsMapBlocks.put("services", jsonAsMapListServices);
//        jsonAsMapBlocks.put("system_info", jsonAsMapSystem_info);
//
//        jsonAsMapContacts.put("site", Site);
//        jsonAsMapContacts.put("address", Address);
//        jsonAsMapContacts.put("another", MapcontactsAnother);
//        jsonAsMapContacts.put("email", Email);
//        jsonAsMapContacts.put("facebook", Facebook);
//        jsonAsMapContacts.put("instagram", Instagram);
//        jsonAsMapContacts.put("vk", Vk);
//        jsonAsMapContacts.put("phones", MapcontactsPhones);
//
//        jsonAsMapcontactsPhones.put("name", NamePhone1);
//        jsonAsMapcontactsPhones.put("number", NumberPhone1);
//
//        jsonAsMapDescription.put("text", Description);
//
//        jsonAsMapGeodata.put("coords", Geodata);
//
//        jsonAsMapInfo.put("abbr", Abbr);
//        jsonAsMapInfo.put("chief", Chief);
//        jsonAsMapInfo.put("help_conditions", Help_conditions);
//        jsonAsMapInfo.put("name", Name);
//        jsonAsMapInfo.put("org_profile", Org_profile);
//
//        jsonAsMapService1.put("id", ServiceID1);
//        jsonAsMapService2.put("id", ServiceID2);
//        jsonAsMapService3.put("id", ServiceID3);
//        jsonAsMapListServices.put("list", ListServices);
//
//        jsonAsMapSystem_info.put("code", Code);
//        jsonAsMapSystem_info.put("oms_number", Oms_number);
//        jsonAsMapSystem_info.put("pump", Pump);
//        jsonAsMapSystem_info.put("oms_id", Oms_id);
//        jsonAsMapSystem_info.put("status", Status);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createUserOrg1API(String Login, String Password, String Email, String Phone, String Status,
//                                         String SecondName, String FirstName, String MiddleName, String Superuser,
//                                         String SendEmail, String Depart, String Post, String Role) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        String[] PostsList = {Post};
//        String[] RolesList = {Role};
//
//        jsonAsMapPassport.put("login", Login);
//        jsonAsMapPassport.put("email", Email);
//        jsonAsMapPassport.put("phone", Phone);
//        jsonAsMapPassport.put("password", Password);
//        jsonAsMapPassport.put("status", Status);
//        jsonAsMapPassport.put("first_name", FirstName);
//        jsonAsMapPassport.put("middle_name", MiddleName);
//        jsonAsMapPassport.put("last_name", SecondName);
//        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
//        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
//        jsonAsMapPassport.put("org_id", OrganizationID1);
//        jsonAsMapPassport.put("org_posts", PostsList);
//        jsonAsMapPassport.put("org_roles", RolesList);
//        jsonAsMapPassport.put("org_status", "active");
//
//        System.out.println(jsonAsMapPassport);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
//                        when().post().
//                        then().extract().response();
//
//        UserID1 = response.path("user_id");
//        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
//        System.out.println("Создали пользователя с ServicesSectionID: " + UserID1);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createUserOrg2API(String Login, String Password, String Email, String Phone, String Status,
//                                         String SecondName, String FirstName, String MiddleName, String Superuser,
//                                         String SendEmail, String Depart, String Post, String Role) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        String[] PostsList = {Post};
//        String[] RolesList = {Role};
//
//        jsonAsMapPassport.put("login", Login);
//        jsonAsMapPassport.put("email", Email);
//        jsonAsMapPassport.put("phone", Phone);
//        jsonAsMapPassport.put("password", Password);
//        jsonAsMapPassport.put("status", Status);
//        jsonAsMapPassport.put("first_name", FirstName);
//        jsonAsMapPassport.put("middle_name", MiddleName);
//        jsonAsMapPassport.put("last_name", SecondName);
//        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
//        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
//        jsonAsMapPassport.put("org_id", OrganizationID1);
//        jsonAsMapPassport.put("org_posts", PostsList);
//        jsonAsMapPassport.put("org_roles", RolesList);
//        jsonAsMapPassport.put("org_status", "active");
//
//        System.out.println(jsonAsMapPassport);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
//                        when().post().
//                        then().extract().response();
//
//        UserID2 = response.path("user_id");
//        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
//        System.out.println("Создали пользователя с ServicesSectionID: " + UserID2);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void createUserOrg3API(String Login, String Password, String Email, String Phone, String Status,
//                                         String SecondName, String FirstName, String MiddleName, String Superuser,
//                                         String SendEmail, String Depart, String Post, String Role) {
//
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        String[] PostsList = {Post};
//        String[] RolesList = {Role};
//
//        jsonAsMapPassport.put("login", Login);
//        jsonAsMapPassport.put("email", Email);
//        jsonAsMapPassport.put("phone", Phone);
//        jsonAsMapPassport.put("password", Password);
//        jsonAsMapPassport.put("status", Status);
//        jsonAsMapPassport.put("first_name", FirstName);
//        jsonAsMapPassport.put("middle_name", MiddleName);
//        jsonAsMapPassport.put("last_name", SecondName);
//        jsonAsMapPassport.put("superuser", ("true".equals(Superuser)));
//        jsonAsMapPassport.put("send_to_email", ("true".equals(SendEmail)));
//        jsonAsMapPassport.put("org_id", OrganizationID1);
//        jsonAsMapPassport.put("org_posts", PostsList);
//        jsonAsMapPassport.put("org_roles", RolesList);
//        jsonAsMapPassport.put("org_status", "active");
//
//        System.out.println(jsonAsMapPassport);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/create").
//                        when().post().
//                        then().extract().response();
//
//        UserID3 = response.path("user_id");
//        System.out.println("Ответ при создании пользователя: " + response.getBody().asString());
//        System.out.println("Создали пользователя с ServicesSectionID: " + UserID3);
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileUserOrg1APInew(String Depart, String Post, String Ref, String Role, String Status,
//                                             String Spec1, String Spec2, String Regalia, String EmailCont,
//                                             String PhoneCont, String Instagram, String Vk, String Whatsapp,
//                                             String Viber, String Facebook, String Other) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
//        Map ServicesList[] = {jsonAsMapProfileService1};
//        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("services", ServicesList);
//        jsonAsMapProfile.put("specialities", SpecialitysList);
//        jsonAsMapProfile.put("regalia", Regalia);
//        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);
//
//        jsonAsMapProfileService1.put("id", ServiceID1);
//        jsonAsMapProfileSpeciality1.put("id", Spec1);
//        jsonAsMapProfileSpeciality2.put("id", Spec2);
//
//        jsonAsMapProfileContact.put("email", EmailCont);
//        jsonAsMapProfileContact.put("phone", PhoneCont);
//        jsonAsMapProfileContact.put("instagram", Instagram);
//        jsonAsMapProfileContact.put("vk", Vk);
//        jsonAsMapProfileContact.put("whatsapp", Whatsapp);
//        jsonAsMapProfileContact.put("viber", Viber);
//        jsonAsMapProfileContact.put("facebook", Facebook);
//        jsonAsMapProfileContact.put("another", Other);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileUserOrg2APInew(String Depart, String Post, String Ref, String Role, String Status,
//                                             String Spec1, String Spec2, String Regalia, String EmailCont,
//                                             String PhoneCont, String Instagram, String Vk, String Whatsapp,
//                                             String Viber, String Facebook, String Other) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
//        Map ServicesList[] = {jsonAsMapProfileService1, jsonAsMapProfileService2};
//        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};
//
//        jsonAsMapProfile.put("user_id", UserID2);
//        jsonAsMapProfile.put("services", ServicesList);
//        jsonAsMapProfile.put("specialities", SpecialitysList);
//        jsonAsMapProfile.put("regalia", Regalia);
//        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);
//
//        jsonAsMapProfileService1.put("id", ServiceID1);
//        jsonAsMapProfileService2.put("id", ServiceID2);
//        jsonAsMapProfileSpeciality1.put("id", Spec1);
//        jsonAsMapProfileSpeciality2.put("id", Spec2);
//
//        jsonAsMapProfileContact.put("email", EmailCont);
//        jsonAsMapProfileContact.put("phone", PhoneCont);
//        jsonAsMapProfileContact.put("instagram", Instagram);
//        jsonAsMapProfileContact.put("vk", Vk);
//        jsonAsMapProfileContact.put("whatsapp", Whatsapp);
//        jsonAsMapProfileContact.put("viber", Viber);
//        jsonAsMapProfileContact.put("facebook", Facebook);
//        jsonAsMapProfileContact.put("another", Other);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void profileUserOrg3APInew(String Depart, String Post, String Ref, String Role, String Status,
//                                             String Spec1, String Spec2, String Regalia, String EmailCont,
//                                             String PhoneCont, String Instagram, String Vk, String Whatsapp,
//                                             String Viber, String Facebook, String Other) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileService3 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
//        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
//        Map ServicesList[] = {jsonAsMapProfileService1, jsonAsMapProfileService2, jsonAsMapProfileService3};
//        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};
//
//        jsonAsMapProfile.put("user_id", UserID3);
//        jsonAsMapProfile.put("services", ServicesList);
//        jsonAsMapProfile.put("specialities", SpecialitysList);
//        jsonAsMapProfile.put("regalia", Regalia);
//        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);
//
//        jsonAsMapProfileService1.put("id", ServiceID1);
//        jsonAsMapProfileService2.put("id", ServiceID2);
//        jsonAsMapProfileService3.put("id", ServiceID3);
//        jsonAsMapProfileSpeciality1.put("id", Spec1);
//        jsonAsMapProfileSpeciality2.put("id", Spec2);
//
//        jsonAsMapProfileContact.put("email", EmailCont);
//        jsonAsMapProfileContact.put("phone", PhoneCont);
//        jsonAsMapProfileContact.put("instagram", Instagram);
//        jsonAsMapProfileContact.put("vk", Vk);
//        jsonAsMapProfileContact.put("whatsapp", Whatsapp);
//        jsonAsMapProfileContact.put("viber", Viber);
//        jsonAsMapProfileContact.put("facebook", Facebook);
//        jsonAsMapProfileContact.put("another", Other);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserNamesAPI(String NewSecondName, String NewFirstName, String NewMiddleName) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("first_name", NewFirstName);
//        jsonAsMapProfile.put("last_name", NewSecondName);
//        jsonAsMapProfile.put("middle_name", NewMiddleName);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/set_names").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при изменении ФИО: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserAuthAPI(String NewEmail, String NewPhone, String NewLogin) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("email", NewEmail);
//        jsonAsMapProfile.put("login", NewLogin);
//        jsonAsMapProfile.put("phone", NewPhone);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/set_auth").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при изменении Auth юзера: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserDeleteWorkplaceAPI(String Depart) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("org_id", Depart);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_remove").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при удалении организации у юзера: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserChangeWorkplaceAPI(String Depart, String NewPost, String NewRole, String NewStatus) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        String[] PostsList = {NewPost};
//        String[] RolesList = {NewRole};
//
//        jsonAsMapProfile.put("posts", PostsList);
//        jsonAsMapProfile.put("roles", RolesList);
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("org_id", OrganizationID1);
//        jsonAsMapProfile.put("status", NewStatus);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_update").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserAddWorkplaceAPI(String NewDepart, String NewPost, String NewRole, String NewStatus) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//        String[] PostsList = {NewPost};
//        String[] RolesList = {NewRole};
//
//        jsonAsMapProfile.put("posts", PostsList);
//        jsonAsMapProfile.put("roles", RolesList);
//        jsonAsMapProfile.put("user_id", UserID1);
//        jsonAsMapProfile.put("org_id", NewDepart);
//        jsonAsMapProfile.put("status", NewStatus);
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_add").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserStatusInSystemAPI(String NewStatus) {
//        Map<String, Object> jsonAsMapProfile = new HashMap<>();
//
//        jsonAsMapProfile.put("status", NewStatus);
//        jsonAsMapProfile.put("user_id", UserID1);
//
//
//        System.out.println(jsonAsMapProfile);
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapProfile).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/status").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void editUserGlobalRoleAPI() {
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        jsonAsMapPassport.put("user_id", UserID1);
//        jsonAsMapPassport.put("superadmin", "true");
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/superadmin").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ изменения глобальной роли: " + response.getBody().asString());
//    }
//
//    public static void loginAuth(String Login, String Password) {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        jsonAsMap.put("auth", Login);
//        jsonAsMap.put("password", Password);
//
//        Response response =
//                given().log().all().
//                        contentType(ContentType.JSON).
//                        body(jsonAsMap).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/sess/login").
//                        when().post().
//                        then().extract().response();
//
//        Assert.assertTrue(!response.getBody().asString().contains("error"));
//    }
//
//    public static void logoutAllUserSessionsAPI() {
//        Map<String, Object> jsonAsMapPassport = new HashMap<>();
//        jsonAsMapPassport.put("user_id", UserID1);
//
//        Response response =
//                given().log().all().
//                        header("Authorization", "Bearer " + Token).
//                        contentType(ContentType.JSON).
//                        body(jsonAsMapPassport).
//                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/logout").
//                        when().post().
//                        then().extract().response();
//
//        System.out.println("Ответ после разлогинивания: " + response.getBody().asString());
//        System.out.println("Пользователь разлогинен");
//    }

}
