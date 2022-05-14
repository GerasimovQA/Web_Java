package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class WebServcecApi extends Base {

    private Object asd;
    private Object object;

    public void createWebServiceAPI(String Cookies, String url, String type, String name, String wsdlURL) {
        String body = "{\"type\":\"" + type + "\",\"name\":\"" + name + "\",\"serviceName\":\"ws1:Test\",\"endpointName\":\"ws1:ServicePort\"," +
                "\"address\":\"http://localhost:9003/Test\",\"wsdlURL\":\"" + wsdlURL + "\",\"interceptors\":[]," +
                "\"nsList\":[{\"url\":\"urn://x-artefacts-integration/test/1.0.0\",\"prefix\":\"ws1\"}],\"operations\":[{\"name\":\"send\"," +
                "\"elementName\":\"sendRequest\"},{\"name\":\"update\",\"elementName\":\"updateRequest\"}],\"useOneEndpoint\":false," +
                "\"showInterceptors\":\"\",\"interceptor\":{\"in\":[],\"out\":[]}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/ws/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании веб сервиса: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void deleteWebServiceAPI(String Cookies, String url, String webServiceId) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body("").
                        baseUri(url).basePath("/manager/api/ws/delete/" + webServiceId).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении веб сервиса: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public String getListWebServiceAPI(String Cookies, String url, String nameWebService) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body("").
                        baseUri(url).basePath("/manager/api/ws/").
                        when().get().
                        then().extract().response();

        HashMap obj = response.jsonPath().get("find {it.name == '" + nameWebService + "'}");
        System.out.printf(String.valueOf(obj));
        String id = String.valueOf(obj.get("id"));
        System.out.println(id);
        basePage.sout("Ответ при получении списка веб сервисов: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return id;
    }
}




