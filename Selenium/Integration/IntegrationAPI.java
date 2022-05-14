package Integration;

import Global.GlobalPage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class IntegrationAPI extends GlobalPage {

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

    // API для создания сотрудника ->
    public String createUserAPI(String Token, String Login, String Password, String Email, String Phone, String Status,
                                String SecondName, String FirstName, String MiddleName, String Superuser,
                                String SendEmail, String Depart, String Post, String Role) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        String[] PostsList = {Post};
        String[] RolesList = {Role};

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
        jsonAsMapPassport.put("org_id", Depart);
        jsonAsMapPassport.put("org_posts", PostsList);
        jsonAsMapPassport.put("org_roles", RolesList);
        jsonAsMapPassport.put("org_status", "active");

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

    public void profileUserAPInew(String Token, String UserID, String ServiceID, String Depart, String Post, String Ref, String Role, String Status,
                                  String Spec1, String Spec2, String Regalia, String EmailCont, String PhoneCont,
                                  String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
                                  String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map ServicesList[] = {jsonAsMapProfileService1};
        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("services", ServicesList);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);

        jsonAsMapProfileService1.put("id", ServiceID);
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

        System.out.println("Ответ после заполнения профиля юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    // API для создания сотрудника -<
    // API для проверки Услуг в профиле сотрудника ->
    public String createServicesSectionAPI(String Token, String CodeSection, String NameSection, String ParentSection,
                                           String PrintNameSection, String VendorSection) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("code", CodeSection);
        jsonAsMapPassport.put("name", NameSection);
        jsonAsMapPassport.put("parent_id", ParentSection);
        jsonAsMapPassport.put("print_name", PrintNameSection);
        jsonAsMapPassport.put("vendor_code", VendorSection);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/new").
                        when().post().
                        then().extract().response();

        String ServicesSectionID = response.path("_id");
        System.out.println("Ответ при создании раздела услуг: " + response.getBody().asString());
        System.out.println("Создали раздел услуг с _id: " + ServicesSectionID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return ServicesSectionID;
    }

    public void editServicesSectionAPI(String Token, String ServicesSectionID, String NewCodeSection, String NewNameSectionme,
                                       String NewPrintNameSection,
                                       String NewVendorSection) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("node_id", ServicesSectionID);
        jsonAsMapPassport.put("code", NewCodeSection);
        jsonAsMapPassport.put("name", NewNameSectionme);
        jsonAsMapPassport.put("print_name", NewPrintNameSection);
        jsonAsMapPassport.put("vendor_code", NewVendorSection);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при редактировании раздела услуг: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public String createServiceAPI(String Token, String ServicesSectionID, String CodeService, String NameService,
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
        jsonAsMapServices.put("parent_id", ServicesSectionID);
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

        String ServiceID1 = response.path("_id");
        System.out.println("Ответ при создании услуи: " + response.getBody().asString());
        System.out.println("Создали услугу с _id: " + ServiceID1);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return ServiceID1;
    }

    public void editServiceAPI(String Token, String ServiceID, String NewCodeService, String NewNameService,
                               String NewPrintNameService, String NewVendorService,
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
//        jsonAsMapServices.put("parent_id", NewParent);
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

    public void editServiceParentAPI(String Token, String ServiceID, String NewParent) {
        Map<String, Object> jsonAsMapServices = new HashMap<>();

        jsonAsMapServices.put("item_id", ServiceID);
        jsonAsMapServices.put("parent_id", NewParent);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapServices).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/item/parent").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении родителя услуги: " + response.getBody().asString());
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

        System.out.println("Ответ при удалении услуи: " + response.getBody().asString());
        System.out.println("Удалили услугу с _id: " + ServiceID);
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
                                       String Oms_id, String Status, String Service1, String Service2,
                                       String Service3) {
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
        jsonAsMapService2.put("id", Service2);
        jsonAsMapService3.put("id", Service3);
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


    public void editNameOrganizationAPI(String Token, String OrganizationID, String NewNameOrg) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapNewName = new HashMap<>();
        Map<String, Object> jsonAsMapInfo = new HashMap<>();

        jsonAsMapNewName.put("name", NewNameOrg);
        jsonAsMapInfo.put("info", jsonAsMapNewName);
        jsonAsMapProfile.put("doc_id", OrganizationID);
        jsonAsMapProfile.put("blocks", jsonAsMapInfo);

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

    public void editParentOrganizationAPI(String Token, String OrganizationID, String NewParentOrg) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("doc_id", OrganizationID);
        jsonAsMapProfile.put("parent", "org:" + NewParentOrg);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/org/dbf/parent_ord").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при изменении Parent организации: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void deleteOrganizationAPI(String Token, String OrganizationID) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("doc_id", OrganizationID);

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

    // API для проверки Организации в профиле сотрудника <-
    // API для проверки Специальностей в профиле сотрудника ->
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

        String SpecialtyID1 = response.path("_id");
        System.out.println("Ответ при создании специальности: " + response.getBody().asString());
        System.out.println("Создали специальность с OrgID: " + SpecialtyID1);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return SpecialtyID1;
    }

    public void editSpecialtyAPI(String Token, String SpecialtyID, String NewName, String NewDescription) {
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

        System.out.println("Ответ послле изменения специальности: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editParentSpecialtyAPI(String Token, String SpecialtyID, String NewIcon, String NewParent) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("item_id", SpecialtyID);
        jsonAsMapProfile.put("parent_id", NewParent);
        jsonAsMapProfile.put("icon", NewIcon);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/speciality/item" +
                        "/parent").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ после редаетирования раздела услуги: " + response.getBody().asString());
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

    public void editUserSpecialtyAPI(String Token, String UserID, String Spec1, String Spec2, String Spec3, String Spec4) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality3 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality4 = new HashMap<>();

        Map SpecialitysList[] = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2,
                jsonAsMapProfileSpeciality3, jsonAsMapProfileSpeciality4};

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("specialities", SpecialitysList);

        jsonAsMapProfileSpeciality1.put("id", Spec1);
        jsonAsMapProfileSpeciality2.put("id", Spec2);
        jsonAsMapProfileSpeciality3.put("id", Spec3);
        jsonAsMapProfileSpeciality4.put("id", Spec4);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ после редактироания специальностей юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }
    // API для проверки Специальностей в профиле сотрудника <-

//    -------------------------------------------------------------------------------------------------------------

    // API для проверки Услуг в профиле организации ->
    public String createServiceOrgAPI(String Token, String CodeService, String NameService,
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
        System.out.println("Ответ при создании услуги : " + response.getBody().asString());
        System.out.println("Создали услугу с _id: " + ServiceID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return ServiceID;
    }

    public void profileOrganizationOrgAPI(String Token, String OrganizationID1, String Name, String Abbr, String Chief, String Help_conditions,
                                          String Org_profile, String Description, String Address,
                                          String GeodataX,
                                          String GeodataY, String Site, String Email, String Facebook,
                                          String Instagram, String Vk, String OtherName, String OtherValue,
                                          String NamePhone1, String NumberPhone1, String NamePhone2,
                                          String NumberPhone2, String Code, String Oms_number, String Pump,
                                          String Oms_id, String Status, String Service1, String Service2,
                                          String Service3) {
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

        jsonAsMapProfile.put("doc_id", OrganizationID1);
        jsonAsMapProfile.put("blocks", jsonAsMapBlocks);

        jsonAsMapAnother.put("name", OtherName);
        jsonAsMapAnother.put("value", OtherValue);

        jsonAsMapBlocks.put("contacts", jsonAsMapContacts);
        jsonAsMapBlocks.put("description", jsonAsMapDescription);
        jsonAsMapBlocks.put("geodata", jsonAsMapGeodata);
        jsonAsMapBlocks.put("info", jsonAsMapInfo);
        jsonAsMapBlocks.put("services", jsonAsMapListServices);
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
        jsonAsMapService2.put("id", Service2);
        jsonAsMapService3.put("id", Service3);
        jsonAsMapListServices.put("list", ListServices);

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
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public String createUserOrgAPI(String Token, String OrganizationID1, String Login, String Password, String Email, String Phone, String Status,
                                   String SecondName, String FirstName, String MiddleName, String Superuser,
                                   String SendEmail, String Depart, String Post, String Role) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        String[] PostsList = {Post};
        String[] RolesList = {Role};

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
        jsonAsMapPassport.put("org_id", OrganizationID1);
        jsonAsMapPassport.put("org_posts", PostsList);
        jsonAsMapPassport.put("org_roles", RolesList);
        jsonAsMapPassport.put("org_status", "active");

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

    public void profileUserOrg1APInew(String Token, String UserID, String ServiceID, String Depart, String Post,
                                      String Ref, String Role, String Status, String Spec1, String Spec2,
                                      String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                                      String Whatsapp, String Viber, String Facebook, String Other) {
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

        jsonAsMapProfileService1.put("id", ServiceID);
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

    public void profileUserOrg2APInew(String Token, String UserID2, String ServiceID1, String ServiceID2, String Depart, String Post, String Ref, String Role, String Status,
                                      String Spec1, String Spec2, String Regalia, String EmailCont,
                                      String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                      String Viber, String Facebook, String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map[] ServicesList = {jsonAsMapProfileService1, jsonAsMapProfileService2};
        Map[] SpecialitysList = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};

        jsonAsMapProfile.put("user_id", UserID2);
        jsonAsMapProfile.put("services", ServicesList);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);

        jsonAsMapProfileService1.put("id", ServiceID1);
        jsonAsMapProfileService2.put("id", ServiceID2);
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

    public void profileUserOrg3APInew(String Token, String UserID3, String ServiceID1, String ServiceID2, String ServiceID3, String Depart, String Post, String Ref, String Role, String Status,
                                      String Spec1, String Spec2, String Regalia, String EmailCont,
                                      String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                      String Viber, String Facebook, String Other) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileService3 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality1 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileSpeciality2 = new HashMap<>();
        Map<String, Object> jsonAsMapProfileContact = new HashMap<>();
        Map[] ServicesList = {jsonAsMapProfileService1, jsonAsMapProfileService2, jsonAsMapProfileService3};
        Map[] SpecialitysList = {jsonAsMapProfileSpeciality1, jsonAsMapProfileSpeciality2};

        jsonAsMapProfile.put("user_id", UserID3);
        jsonAsMapProfile.put("services", ServicesList);
        jsonAsMapProfile.put("specialities", SpecialitysList);
        jsonAsMapProfile.put("regalia", Regalia);
        jsonAsMapProfile.put("contacts", jsonAsMapProfileContact);

        jsonAsMapProfileService1.put("id", ServiceID1);
        jsonAsMapProfileService2.put("id", ServiceID2);
        jsonAsMapProfileService3.put("id", ServiceID3);
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

    public void editUserAuthAPI(String Token, String UserID, String NewEmail, String NewPhone, String NewLogin) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("email", NewEmail);
        jsonAsMapProfile.put("login", NewLogin);
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
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserDeleteWorkplaceAPI(String Token, String UserID, String Depart) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("org_id", Depart);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_remove").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при удалении организации у юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserChangeWorkplaceAPI(String Token, String UserID, String OrganizationID1, String Depart,
                                           String NewPost, String NewRole, String NewStatus) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        String[] PostsList = {NewPost};
        String[] RolesList = {NewRole};

        jsonAsMapProfile.put("posts", PostsList);
        jsonAsMapProfile.put("roles", RolesList);
        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("org_id", OrganizationID1);
        jsonAsMapProfile.put("status", NewStatus);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserAddWorkplaceAPI(String Token, String UserID, String NewDepart, String NewPost, String NewRole,
                                        String NewStatus) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();
        String[] PostsList = {NewPost};
        String[] RolesList = {NewRole};

        jsonAsMapProfile.put("posts", PostsList);
        jsonAsMapProfile.put("roles", RolesList);
        jsonAsMapProfile.put("user_id", UserID);
        jsonAsMapProfile.put("org_id", NewDepart);
        jsonAsMapProfile.put("status", NewStatus);

        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/profile/admin/user/org_add").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserStatusInSystemAPI(String Token, String UserID, String NewStatus) {
        Map<String, Object> jsonAsMapProfile = new HashMap<>();

        jsonAsMapProfile.put("status", NewStatus);
        jsonAsMapProfile.put("user_id", UserID);


        System.out.println(jsonAsMapProfile);
        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapProfile).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/status").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при создании организации у юзера: " + response.getBody().asString());
        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void editUserGlobalRoleAPI(String Token, String UserID) {
        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("user_id", UserID);
        jsonAsMapPassport.put("superadmin", "true");

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/passport/admin/user/superadmin").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ изменения глобальной роли: " + response.getBody().asString());
    }

    public void loginAuth(String Login, String Password) {
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

        Assert.assertTrue(!response.getBody().asString().contains("error"));
    }

    public void logoutAllUserSessionsAPI(String Token, String UserID) {
        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("user_id", UserID);

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