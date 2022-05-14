package ru.factorts.page;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.ConfigProperties;

import java.io.File;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class IbmMqApi extends Base {
    public enum StatusChannel {Active, Inactive}

    public String loginToWmqAPI(String username, String password) {
        Response response = RestAssured.given().log().all().
                contentType("application/x-www-form-urlencoded").relaxedHTTPSValidation().
                given().
                param("j_username", username).
                param("j_password", password).
                baseUri(ConfigProperties.getTestProperty("IbmUrl")).basePath("/ibmmq/console/j_security_check").
                when().post().
                then().extract().response();

        Headers allHeaders = response.getHeaders();
        basePage.sout("allHeaders: " + allHeaders);

        Map<String, String> allCookies = response.getCookies();
        String Cookies = allCookies.toString().replaceAll("[{}]", "");
        basePage.sout("allCookies: " + Cookies);

        basePage.sout("Ответ при авторизации в WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertNotEquals(" ", response.getBody().asString());
        return Cookies;
    }

    public void sendMessageToQueueWmqAPI(String Cookies, String nameQueue, String QM, String message) {
        String body = "{\"message\":\"" + message + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        body(body).
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).basePath("/ibmmq/console/internal/ibmmq/qmgr/" + QM + "/queue/" + nameQueue + "/messages").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при отправке сообщения в очередь " + nameQueue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public void createQueueToWmqAPI(String Cookies, String nameQueue, String QM) {
        String body = "{\"type\":\"qlocal\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        body(body).
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).basePath("/ibmmq/console/internal/ibmmq/qmgr/" + QM + "/queue/" + nameQueue + "?preventCache=1574768921069").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании очереди WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertNotEquals(" ", response.getBody().asString());
    }

    public void deleteQueueToWmqAPI(String Cookies, String nameQueue, String QM) {
        String body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        body(body).
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).basePath("/ibmmq/console/internal/ibmmq/qmgr/" + QM + "/queue/" + nameQueue + "?preventCache=1574768921069").
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении очереди WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertNotEquals(" ", response.getBody().asString());
    }

    public void setMaxQueueDepthWMQ(String Cookies, String nameQueue, String num, String QM) {
        String body = "{\"attributes\":{\"MQIA_MAX_Q_DEPTH\":\"" + num + "\"},\"type\":\"MQQT_LOCAL\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        body(body).
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).basePath("/ibmmq/console/internal/ibmmq/qmgr/" + QM + "/queue/" + nameQueue + "?preventCache=1574768921069").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при изменении максимальной глубины очереди WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertNotEquals(" ", response.getBody().asString());
    }

    public int getQueueDepthWMQ(String Cookies, String nameQueue) {
        Response response =
                given().log().all().
                        param("attributes", "*").
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).
                        basePath("/ibmmq/console/internal/ibmmq/qmgr/QM1/queue/" + nameQueue).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении атрибутов очереди WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        System.out.println("Текущая глубина очереди " + nameQueue + ": " + response.path("currentdepth").toString().replaceAll("[^0-9\\+]", ""));
        return Integer.parseInt(response.path("currentdepth").toString().replaceAll("[^0-9\\+]", ""));
//        return Integer.parseInt(response.path("currentdepth").toString().replaceAll("[, $]", ""));
    }

    public void checkStatusChannelWmqApi(String Cookies, String nameChannel, StatusChannel statusChannel) {
        Response response =
                given().log().all().
                        param("attributes", "*").
                        param("showStatusAs", "MQIACH_CHANNEL_STATUS").
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        baseUri(ConfigProperties.getTestProperty("IbmUrl")).
                        basePath("/ibmmq/console/internal/ibmmq/qmgr/QM1/channel/" + nameChannel).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении статуса канала WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        System.out.println("Статус канала " + nameChannel + ": " + response.path("status"));

        if (statusChannel.equals(StatusChannel.Active))
            basePage.compareStringAndString("[MQCHS_RUNNING]", response.path("status").toString());
//            Assert.assertEquals("[MQCHS_RUNNING]", response.path("status"));
        else if (statusChannel.equals(StatusChannel.Inactive))
            basePage.compareStringAndString("[MQCHS_INACTIVE]", response.path("status").toString());
//            Assert.assertEquals("[MQCHS_INACTIVE]", response.path("status"));
    }

    public void configClusterAPI(String Cookies, String baseUrl, String userForAutorization, String
            pathToStorageOfQueue, ArrayList<String> adressesMQList, ArrayList<String> portList, ArrayList<String> jmxPortList) {
        String body = "{\"addressList\":[{\"host\":\"" + adressesMQList.get(0) + "\",\"port\":\"" + portList.get(0) +
                "\",\"jmxPort\":\"" + jmxPortList.get(0) + "\"},{\"host\":\"" + adressesMQList.get(0) + "\",\"port\":\"" +
                portList.get(0) + "\",\"jmxPort\":\"" + jmxPortList.get(0) + "\"}],\"username\":\"" + userForAutorization +
                "\",\"camelType\":\"FAILOVER\",\"kahaDBDirectory\":\"" + pathToStorageOfQueue + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).header("ibm-mq-csrf-token", "value").
                        contentType(ContentType.JSON).relaxedHTTPSValidation().
                        body(body).
                        baseUri(baseUrl).basePath("/manager/api/settings/cluster/config").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при конфигурации кластера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertNotEquals(" ", response.getBody().asString());
    }

    public void restartJvmAPI(String cookies, String baseUrl) throws ConnectException {
        given().log().all().
                header("Cookie", cookies).
                contentType(ContentType.JSON).
                baseUri(baseUrl).basePath("/manager/api/server/restartJVM").
                when().get();

//        basePage.sout("Ответ при перезапуске JVM: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals("", response.getBody().asString());
    }

}


