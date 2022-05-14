package Organization.CreateCabinet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCabinetAPI {

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
        System.out.println("Создали Кабинет с cabinet_id: " + CabinetID);
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
        System.out.println("Ответ при создании кабинета: " + response.getBody().asString());
        System.out.println("Создали Кабинет с workspace_id: " + WorkplaceID);
        return WorkplaceID;
    }
}





