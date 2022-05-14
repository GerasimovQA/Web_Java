package Service.EditService;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class EditServiceAPI{

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


    public String createServicesSectionAPI(String Token, String Code, String Name, String Parent, String PrintName,
                                                  String Vendor) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("code", Code);
        jsonAsMapPassport.put("name", Name);
        jsonAsMapPassport.put("parent_id", Parent);
        jsonAsMapPassport.put("print_name", PrintName);
        jsonAsMapPassport.put("vendor_code", Vendor);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/new").
                        when().post().
                        then().extract().response();
       String SectionServiceID = response.path("_id");
        System.out.println("Ответ при создании раздела услуг: " + response.getBody().asString());
        System.out.println("Создали раздел услуг с _id: " + SectionServiceID);
        return SectionServiceID;
    }

    public void editServicesSectionAPI(String Token, String SectionServiceID,  String NewCode, String NewName, String EditParent, String NewPrintName,
                                              String NewVendor) {

        Map<String, Object> jsonAsMapPassport = new HashMap<>();
        jsonAsMapPassport.put("node_id", SectionServiceID);
        jsonAsMapPassport.put("code", NewCode);
        jsonAsMapPassport.put("name", NewName);
        jsonAsMapPassport.put("print_name", NewPrintName);
        jsonAsMapPassport.put("vendor_code", NewVendor);

        Response response =
                given().log().all().
                        header("Authorization", "Bearer " + Token).
                        contentType(ContentType.JSON).
                        body(jsonAsMapPassport).
                        baseUri(ConfigProperties.getTestProperty("baseurl")).basePath("/servlist/node/update").
                        when().post().
                        then().extract().response();

        System.out.println("Ответ при редактировании раздела услуг: " + response.getBody().asString());
    }

    public String createServiceAPI(String Token, String CodeService, String NameService,
                                   String PrintNameService, String Parent, String VendorService,
                                   String ContraindicationsService, String CreatorService,
                                   String DescriptionService, String PreconditionService, String TypeService,
                                   String DmsCost, String OmsCost, String PmuCost, String NameOtherCosts,
                                   String OtherCosts, String Favorit, String Record) {
        Map<String, Object> jsonAsMapServices = new HashMap<>();
        Map<String, Object> jsonAsMapServicesCost = new HashMap<>();
        Map<String, Object> jsonAsMapServicesOtherCost = new HashMap<>();
        Map[] Other = {jsonAsMapServicesOtherCost};

        jsonAsMapServicesOtherCost.put("name", NameOtherCosts);
        jsonAsMapServicesOtherCost.put("price", OtherCosts);

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

       String  ServiceID = response.path("_id");
        System.out.println("Ответ при создании услуи: " + response.getBody().asString());
        System.out.println("Создали услугу с _id: " + ServiceID);
        Assert.assertTrue(!response.getBody().asString().contains("error"));
        return ServiceID;
    }
}