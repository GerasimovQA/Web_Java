package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class RestApi extends Base {
    public String createDomainRest(String Cookies, String domainName, String domainPath, String host, String port, boolean httpsScheme) {
        String Body = "{\"name\":\"" + domainName + "\",\"configuration\":{\"contextPath\":\"" + domainPath + "\",\"host\":\"" + host + "\",\"port\":\"" + port + "\",\"httpsScheme\":" + httpsScheme + ",\"properties\":[]}}";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/rest/domain").
                when().post().
                then().extract().response();

        String domainID = response.path("guid");
        basePage.sout("Ответ при создании домена Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return domainID;
    }

    public String startDomainRestAPI(String Cookies, String baseUrl, String DomainID) {
        String Body = "";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(baseUrl).basePath("/manager/api/rest/domain/" + DomainID + "/start").
                when().put().
                then().extract().response();

        basePage.sout("Ответ при запуске домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public String stopDomainRestAPI(String Cookies, String baseUrl, String DomainID) {
        String Body = "";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(baseUrl).basePath("/manager/api/rest/domain/" + DomainID + "/stop").
                when().put().
                then().extract().response();

        basePage.sout("Ответ при остановке домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public void createService(String Cookies, String domainID, String serviceName, String servicePath, String serviceDescription, String serviceTypeDataRequest, String serviceTypeDataAnswer, String bindingMode) {
        String Body = "{\"name\":\"" + serviceName + "\",\"path\":\"/" + servicePath + "\",\"description\":\"" + serviceDescription + "\",\"consumes\":\"" + serviceTypeDataRequest + "\",\"produces\":\"" + serviceTypeDataAnswer + "\", \"bindingMode\":\"" + bindingMode + "\"}";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/rest/domain/" + domainID + "/group/").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при создании группы методов Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public void createMethodGetWithTwoParameters(String domainID, String Cookies, String methodName, String serviceName, String method, String methodPath, String methodDescription, String methodTypeDataRequest, String methodTypeDataAnswer, String linkToSops, String[] parameter1, String[] parameter2) {
        String Body = "{\"name\":\"" + methodName + "\",\"uri\":\"" + methodPath + "\",\"description\":\"" + methodDescription + "\",\"type\":\"" + method + "\",\"direct\":\"direct-vm://" + linkToSops + "\",\"produces\":\"" + methodTypeDataAnswer + "\",\"consumes\":\"" + methodTypeDataRequest + "\",\"responseClassName\":\"\",\"parameters\":[{\"name\":\"" + parameter1[0] + "\",\"type\":\"" + parameter1[1] + "\",\"dataType\":\"" + parameter1[2] + "\",\"description\":\"" + parameter1[3] + "\",\"required\":true},{\"name\":\"" + parameter2[0] + "\",\"type\":\"" + parameter2[1] + "\",\"dataType\":\"" + parameter2[2] + "\",\"description\":\"" + parameter2[3] + "\",\"required\":true}]}";


        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/rest/domain/" + domainID + "/group/" + serviceName + "/method").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при создании GET метода Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals("true", response.getBody().asString());
    }

    public void createMethod(String domainID, String Cookies, String methodName, String serviceName, String method, String methodPath, String methodDescription, String methodTypeDataRequest, String methodTypeDataAnswer, String linkToSops) {
        String Body = null;
        switch (method) {
            case "GET":
                Body = "{\"name\":\"" + methodName + "\",\"uri\":\"" + methodPath + "\",\"description\":\"" + methodDescription + "\",\"type\":\"" + method + "\",\"direct\":\"direct-vm://" + linkToSops + "\",\"produces\":\"" + methodTypeDataAnswer + "\",\"consumes\":\"" + methodTypeDataRequest + "\",\"responseClassName\":\"\",\"parameters\":[{\"name\":\"qqq\",\"type\":\"query\",\"dataType\":\"string\",\"description\":\"параметр qqq\",\"required\":true},{\"name\":\"a2\",\"type\":\"path\",\"dataType\":\"integer\",\"description\":\"a2\",\"required\":true}]}";
                break;
            case "DELETE":
                Body = "{\"name\":\"" + methodName + "\",\"uri\":\"" + methodPath + "\",\"description\":\"" + methodDescription + "\",\"type\":\"" + method + "\",\"direct\":\"direct-vm://" + linkToSops + "\",\"produces\":\"" + methodTypeDataAnswer + "\",\"consumes\":\"" + methodTypeDataRequest + "\",\"responseClassName\":\"\",\"parameters\":[]}";
                break;
            case "POST":
            case "PUT":
                break;
            default:
                throw new AssertionError("Пропущено формирование Body метода");
        }

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/rest/domain/" + domainID + "/group/" + serviceName + "/method").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при создании метода Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals("true", response.getBody().asString());
    }

    public void createAddConfiguration(String Cookies, String domainID, String addConfName, String addConfType, String[][] users, String[][] roles) {


        String Body1 = "{\"name\":\"" + addConfName + "\",\"beanType\":\"" + addConfType + "\",\"domain\":\"" + domainID + "\",\"constructorArgs\":[]," + "\"properties\":[],\"authType\":\"BASIC\",";
        String Body2 = "\"users\":[";
        for (int x = 0; x < users.length; x++) {
            String BodyUser = "{\"login\":\"" + users[x][0] + "\",\"password\":\"" + users[x][1] + "\",\"roles\":\"" + users[x][2] + "\"},";
            Body2 = Body2 + BodyUser;

        }
        Body2 = Body2.replaceAll(",$", "") + "],";
        System.out.println(Body2);

        String Body3 = "\"pathList\":[";
        for (int x = 0; x < roles.length; x++) {
            String BodyUser = null;
            if (roles[x].length == 3) {
                BodyUser = "{\"method\":\"" + roles[x][0] + "\",\"path\":\"" + roles[x][1] + "\",\"auth\":" + Boolean.parseBoolean(roles[x][2]) + "},";
            } else if (roles[x].length == 4) {
                BodyUser = "{\"method\":\"" + roles[x][0] + "\",\"path\":\"" + roles[x][1] + "\",\"auth\":" + Boolean.parseBoolean(roles[x][2]) + ",\"roles\":\"" + roles[x][3] + "\"},";
            }
            Body3 = Body3 + BodyUser;

        }
        Body3 = Body3.replaceAll(",$", "") + "]}";
        System.out.println(Body3);


        String Body = Body1 + Body2 + Body3;
        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/beans/" + domainID + "/bean").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при создании дополнительной конфигурации Rest: " + response.getBody().

                asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals("true", response.getBody().asString());
    }

    public void restartRest(String Cookies) {
        String Body = "";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(BaseUrl).basePath("/manager/api/module/factor-rest/restart").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при перезапуске Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("true", response.getBody().asString());
    }

    public void changeSettingRestAPI(String Cookies, String url, String domainID, String domainName, String contextPath, String host, int port, String apiContextPath, String apiVersion, String apiTitle, Boolean httpsScheme, String auth, String cors) {
        String Body = "{\"guid\":\"" + domainID + "\",\"name\":\"" + domainName + "\",\"active\":true,\"configuration\":{\"contextPath\":\"" + contextPath + "\",\"host\":\"" + host + "\",\"port\":" + port + ",\"apiContextPath\":\"" + apiContextPath + "\",\"apiVersion\":\"" + apiVersion + "\",\"apiTitle\":\"" + apiTitle + "\",\"httpsScheme\":false,\"properties\":[],\"auth\":\"" + auth + "\",\"cors\":\"" + cors + "\"}}";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(url).basePath("/manager/api/rest/domain/" + domainID).
                when().put().
                then().extract().response();

        basePage.sout("Ответ при редактировании настроек Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void changeSettingRest199API(String Cookies, String url, String contextPath, String host, int port, String apiContextPath, String apiVersion, String apiTitle, Boolean httpsScheme, String auth, String cors) {
        String Body = "{\"contextPath\":\"" + contextPath + "\",\"host\":\"" + host + "\",\"port\":\"" + port + "\",\"apiContextPath\":\"" + apiContextPath + "\",\"apiVersion\":\"" + apiVersion + "\",\"apiTitle\":\"" + apiTitle + "\",\"httpsScheme\":\"" + httpsScheme + "\",\"properties\":[],\"auth\":\"" + auth + "\",\"cors\":\"" + cors + "\"}";

        Response response = given().log().all().
                header("Cookie", Cookies).
                contentType(ContentType.JSON).
                body(Body).
                baseUri(url).basePath("/manager/api/rest/config").
                when().post().
                then().extract().response();

        basePage.sout("Ответ при редактировании настроек Rest: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

}




