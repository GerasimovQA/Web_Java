package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;

public class SOPSApi extends Base {

    CommonComponents commonComponents = new CommonComponents();

    public String createDomainAPI(String Cookies, String DomainName) {
        String Body = "{\"name\":\"" + DomainName + "\",\"type\":\"standard\",\"group\":\"\",\"localMQSettings\":{\"maxConnections\":10,\"connectionPerSession\":100,\"concurrentConsumers\":1},\"redeliverySettings\":{\"className\":\"org.apache.camel.processor.RedeliveryPolicy\",\"destroyMethod\":\"\",\"beanType\":\"REDELIVERY_POLICY\",\"constructorArgs\":{},\"properties\":{},\"maximumRedeliveries\":0,\"redeliveryDelay\":1000,\"maximumRedeliveryDelay\":60000,\"backOffMultiplier\":2,\"asyncDelayedRedelivery\":false,\"useCollisionAvoidance\":false,\"retryAttemptedLogLevel\":\"DEBUG\",\"useExponentialBackOff\":false,\"collisionAvoidanceFactor\":0.15,\"allowRedeliveryWhileStopping\":true},\"errorHandlerSettings\":{\"className\":\"org.apache.camel.builder.DeadLetterChannelBuilder\",\"beanType\":\"ERROR_HANDLER\",\"constructorArgs\":{},\"properties\":{},\"deadLetterRouteName\":null,\"deadLetterUri\":\"direct-vm://myDLC\",\"redeliveryPolicy\":\"myRedeliveryPolicyConfig\",\"useOriginalMessage\":false},\"threadProfilerSettings\":{\"poolSize\":10,\"maxPoolSize\":20,\"keepAliveTime\":60,\"maxQueueSize\":1000,\"rejectedPolicy\":\"CallerRuns\"},\"properties\":{}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/domain").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(!response.getBody().asString().contains("message"));
        return response.getBody().asString();
    }

    public String createDomainAPI(String url, String Cookies, String DomainName) {
        String Body = "{\"name\":\"" + DomainName + "\",\"type\":\"standard\",\"group\":\"\",\"localMQSettings\":{\"maxConnections\":10,\"connectionPerSession\":100,\"concurrentConsumers\":1},\"redeliverySettings\":{\"className\":\"org.apache.camel.processor.RedeliveryPolicy\",\"destroyMethod\":\"\",\"beanType\":\"REDELIVERY_POLICY\",\"constructorArgs\":{},\"properties\":{},\"maximumRedeliveries\":0,\"redeliveryDelay\":1000,\"maximumRedeliveryDelay\":60000,\"backOffMultiplier\":2,\"asyncDelayedRedelivery\":false,\"useCollisionAvoidance\":false,\"retryAttemptedLogLevel\":\"DEBUG\",\"useExponentialBackOff\":false,\"collisionAvoidanceFactor\":0.15,\"allowRedeliveryWhileStopping\":true},\"errorHandlerSettings\":{\"className\":\"org.apache.camel.builder.DeadLetterChannelBuilder\",\"beanType\":\"ERROR_HANDLER\",\"constructorArgs\":{},\"properties\":{},\"deadLetterRouteName\":null,\"deadLetterUri\":\"direct-vm://myDLC\",\"redeliveryPolicy\":\"myRedeliveryPolicyConfig\",\"useOriginalMessage\":false},\"threadProfilerSettings\":{\"poolSize\":10,\"maxPoolSize\":20,\"keepAliveTime\":60,\"maxQueueSize\":1000,\"rejectedPolicy\":\"CallerRuns\"},\"properties\":{}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/domain").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(!response.getBody().asString().contains("message"));
        return response.getBody().asString();
    }

    public String startDomainAPI(String Cookies, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/domain/" + DomainID + "/start").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при запуске домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public String startDomainAPI(String Cookies, String baseUrl, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/domain/" + DomainID + "/start").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при запуске домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public String startDomainWithoutCheckResponseAPI(String Cookies, String baseUrl, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/domain/" + DomainID + "/start").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при запуске домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return response.getBody().asString();
    }

    public String getStatusOfDomainAPI(String Cookies, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/domain/" + DomainID + "/status").
                        when().get().
                        then().extract().response();

        basePage.sout("Статус домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public void checkStatusOfDomainAPI(String Cookies, String DomainID, String status) {
        sleep(10000);
        int x = 0;
        String statusOfDomain = getStatusOfDomainAPI(Cookies, DomainID);
        while (!statusOfDomain.equals(status) && x < 10) {
            x++;
            statusOfDomain = getStatusOfDomainAPI(Cookies, DomainID);
            sleep(1000);
        }
    }

    public String stopDomainAPI(String Cookies, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/domain/" + DomainID + "/stop").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при остановке домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public String stopDomainAPI(String Cookies, String baseUrl, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/domain/" + DomainID + "/stop").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при остановке домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public void deleteDomainAPI(String Cookies, String url, String DomainID) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/domain/" + DomainID).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public String startSopsAPI(String Cookies, String baseUrl, String DomainID, String sopsId) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/route-" + sopsId + "/start").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при запуске СОПС: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
        return response.getBody().asString();
    }

    public String createBinAPI(String Cookies, String binName, String domainName, String domainId, String login, String password,
                               String appName, String sizeOfPoolThread, String ipAdress, String port, String manager, String canalName, String timeout) {

        String Body = "{\"beanType\":\"WMQ_SESSION_FACTORY\",\"id\":\"" + binName + "\",\"name\":\"" + binName + "\",\"className\":\"ru.factorts.wmq.session.WmqSessionFactory\"," +
                "\"destroyMethod\":\"closeConnection\",\"factoryMethod\":null,\"xml\":null,\"beanType\":\"WMQ_SESSION_FACTORY\",\"properties\":" +
                "[{\"name\":\"pool\",\"type\":\"value\",\"value\":\"" + sizeOfPoolThread + "\"},{\"name\":\"timeout\",\"type\":\"value\",\"value\":\"" + timeout + "\"}," +
                "{\"name\":\"localBind\",\"type\":\"value\",\"value\":\"false\"},{\"name\":\"brokerURI\",\"type\":\"value\"," +
                "\"value\":\"" + ipAdress + ":" + port + "/" + manager + "/" + canalName + "\"},{\"name\":\"userId\",\"type\":\"value\"," +
                "\"value\":\"" + login + "\"},{\"name\":\"password\",\"type\":\"value\",\"value\":\"" + password + "\"},{\"name\":\"appname\"," +
                "\"type\":\"value\",\"value\":\"" + appName + "\"},{\"name\":\"useMQCSPAuthentication\",\"type\":\"value\",\"value\":\"false\"}]," +
                "\"constructorArgs\":[],\"parentBeanId\":null,\"childBeans\":[],\"ip\":\"" + ipAdress + "\",\"localBind\":false," +
                "\"useMQCSPAuthentication\":false,\"port\":" + port + ",\"manager\":\"" + manager + "\",\"channel\":\"" + canalName + "\"," +
                "\"userId\":\"" + login + "\",\"password\":\"" + password + "\",\"appname\":\"" + appName + "\",\"pool\":" + sizeOfPoolThread + ",\"timeout\":" + timeout + "," +
                "\"propagationBehaviorName\":\"PROPAGATION_REQUIRED\",\"entityXMLTag\":\"bean\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/beans/" + domainId + "/bean").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании бина: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("beanType"));
        return response.getBody().asString();
    }

    public void deleteBinAPI(String Cookies, String url, String DomainID, String binID) {
        String Body = "{\"id\":\"" + binID + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/beans/" + DomainID + "/bean").
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении бина: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public String generateSopsIdAPI(String Cookies, String DomainID, String SopsName) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/" + SopsName).
                        when().get().
                        then().extract().response();

        String SopsID = response.path("id");
        basePage.sout("Ответ при создании СОПСа: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
        return SopsID;
    }

    public String generateSopsIdAPI(String Cookies, String url, String DomainID, String SopsName) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/broker/domain/" + DomainID + "/route/" + SopsName).
                        when().get().
                        then().extract().response();

        String SopsID = response.path("id");
        basePage.sout("Ответ при создании СОПСа: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
        return SopsID;
    }

    public void createSopsAPI(String Cookies, String url, String DomainID, String SopsID, String SopsName, String[] allPointsIn,
                              String[] allPointsOut, String settingDomain) {
        String pointsIn = "";
        for (String pointOut : allPointsIn) {
            pointsIn = pointsIn + pointOut + ",";
        }
        pointsIn = pointsIn.replaceAll(",$", "");

        String pointsOut = "";
        for (String pointOut : allPointsOut) {
            pointsOut = pointsOut + pointOut + ",";
        }
        pointsOut = pointsOut.replaceAll(",$", "");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"name\":\"" + SopsName + "\",\"version\":\"32\",\"stage\":\"ENDPOINT_SETTINGS\",\"domain\":{\"guid\":\"" + DomainID + "\"},\"in\":[" +

                pointsIn + "],\"processes\":[" + pointsOut + settingDomain;

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWith2LocalQueueAPI(String Cookies, String url, String DomainID, String SopsID, String DomainName, String SopsName, String NameLocalQueue1, String NameLocalQueue2) {
        String BrokerGuid = commonComponents.createIndividualName("guid-1");
        String LocalInGuid1 = commonComponents.createIndividualName("guid-1");
        String LocalOutGuid1 = commonComponents.createIndividualName("guid-2");


        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ENDPOINT_SETTINGS\"," +
                "\"domain\":{\"guid\":\"" + DomainID + "\"},\"in\":[{\"name\":\"Локальная очередь\"," +
                "\"icon\":\"fa fa-envelope\",\"group\":\"Брокеры сообщений\",\"guid\":\"" + BrokerGuid + "\"," +

                "\"componentName\":\"InLocalMQEndpoint\",\"properties\":[],\"domain\":\"" + DomainID + "\"," +
                "\"customName\":\"Локальная очередь\",\"uri\":\"" + NameLocalQueue1 + "\"}],\"processes\":[{\"name\":\"Локальная очередь\"," +
                "\"icon\":\"fa fa-envelope\",\"group\":\"Точки выхода\",\"guid\":\"" + LocalInGuid1 + "\"," +

                "\"componentName\":\"OutLocalMQEndpoint\",\"properties\":[],\"domain\":\"" + DomainID + "\"," +
                "\"customName\":\"Локальная очередь\",\"sla\":{},\"uri\":\"" + NameLocalQueue2 + "\"}],\"newEndpointType\":\"PROCESS\"," +
                "\"selectedEndpoint\":{\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"group\":\"Брокеры сообщений\"," +
                "\"guid\":\"" + LocalOutGuid1 + "\",\"componentName\":\"InLocalMQEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"Локальная очередь\",\"uri\":\"" + NameLocalQueue1 + "\"}," +
                "\"name\":\"" + SopsName + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС с 2 локальными очередями: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWith2LocalQueueMultyAPI(String Cookies, String url, String DomainID, String SopsID, String SopsName, String managerName1, String NameLocalQueue1, String managerName2, String NameLocalQueue2) {
        String BrokerGuid = commonComponents.createIndividualName("guid-1");
        String LocalInGuid1 = commonComponents.createIndividualName("guid-1");
        String LocalOutGuid1 = commonComponents.createIndividualName("guid-2");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"name\":\"" + SopsName + "\",\"description\":null,\"domainName\":null,\"domainGuid\":\"" + DomainID + "\",\"routeState\":null,\"ref\":null,\"in\":[{" +

                "\"componentName\":\"InLocalMQEndpoint\",\"guid\":\"" + LocalInGuid1 + "\",\"customName\":\"Локальная очередь\",\"properties\":[],\"uri\":\"" + NameLocalQueue1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"isNonPooled\":\"\",\"isXA\":\"\",\"module\":\"QMS\",\"brokerId\":\"" + managerName1 + "\",\"group\":\"Менеджеры очередей\"}],\"processes\":[{\"" +

                "componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + LocalOutGuid1 + "\",\"customName\":\"Локальная очередь\",\"properties\":[],\"hasCondition\":\"\",\"useCache\":\"\",\"uri\":\"" + NameLocalQueue2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"sla\":{\"isActive\":\"\"},\"useVariables\":\"\",\"isWireTap\":\"\",\"isTransacted\":\"\",\"isNonPooled\":\"\",\"isXA\":\"\",\"module\":\"QMS\",\"brokerId\":\"" + managerName2 + "\",\"group\":\"Точки выхода\"}],\"exceptionHandlerEndpoints\":[],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"isRouteErrorHandlerEnabled\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"route-daa89ce7-92fb-4a26-8fc3-650a1829136c\",\"rate\":0,\"domain\":{\"guid\":\"main\"}},\"comment\":\"\"}";


        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС с 2 локальными очередями: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWith2LocalQueueAndLoggerAPI(String Cookies, String DomainID, String SopsID, String SopsName,
                                                      String NameLocalQueue1, String NameLocalQueue2, String NameLogger,
                                                      String loggerLevel, String loggerMessage, String loggerName) {
        String LocalInGuid1 = commonComponents.createIndividualName("guid-1");
        String LocalOutGuid1 = commonComponents.createIndividualName("guid-2");
        String LoggerGuid = commonComponents.createIndividualName("logger-1");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ENDPOINT_SETTINGS\"," +
                "\"domain\":{\"guid\":\"" + DomainID + "\"},\"in\":[{" +

                "\"componentName\":\"InLocalMQEndpoint\",\"guid\":\"" + LocalInGuid1 + "\",\"customName\":\"Локальная очередь\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameLocalQueue1 + "\",\"fullUri\":\"localmq://1\",\"isNonPooled\":false,\"prefix\":\"localmq\"," +
                "\"endpointTag\":\"from\",\"xml\":\"<from uri=\\\"localmq://1\\\"/>\\n\",\"name\":\"Локальная очередь\"," +
                "\"icon\":\"fa fa-envelope\"}]," +

                "\"processes\":[{" +
                "\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + LocalOutGuid1 + "\",\"customName\":\"Локальная очередь\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameLocalQueue2 + "\",\"fullUri\":\"localmq://2\",\"sla\":null,\"isWireTap\":false,\"useVariables\":false," +
                "\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\"," +
                "\"endpointTag\":\"to\",\"xml\":\"<to uri=\\\"localmq://2\\\"/>\\n\",\"name\":\"Локальная очередь\"," +
                "\"icon\":\"fa fa-envelope\"}," +

                "{\"name\":\"Логирование\",\"icon\":\"fa fa-comment\",\"group\":\"Обработка\",\"guid\":\"" + LoggerGuid + "\"," +
                "\"componentName\":\"LogEndpoint\",\"properties\":[],\"domain\":\"" + DomainID + "\",\"customName\":\"" + NameLogger + "\"," +
                "\"loggingLevel\":\"" + loggerLevel + "\",\"message\":\"" + loggerMessage + "\",\"logName\":\"" + loggerName + "\"}]," +
                "\"newEndpointType\":\"PROCESS\",\"selectedEndpoint\":{\"name\":\"Логирование\",\"icon\":\"fa fa-comment\"," +
                "\"group\":\"Обработка\",\"guid\":\"" + LoggerGuid + "\",\"componentName\":\"LogEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"Имя компонента\",\"loggingLevel\":\"" + loggerLevel + "\"," +
                "\"message\":\"" + loggerMessage + "\",\"logName\":\"" + loggerName + "\"},\"name\":\"" + SopsName + "\"," +
                "\"transacted\":false,\"ref\":null,\"streamCache\":false},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС с 2 локальными очередями и логгером: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWithLocalQueueAndWmqAPI(String Cookies, String DomainID, String SopsID, String SopsName,
                                                  String NameLocalQueue1, String NameWmqQueue, String transactionPolicy) {
        String LocalInGuid1 = commonComponents.createIndividualName("guid-1");
        String WmqGuid = commonComponents.createIndividualName("logger-1");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ENDPOINT_SETTINGS\"," +
                "\"domain\":{\"guid\":\"" + DomainID + "\"},\"in\":[{" +

                "\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"group\":\"Брокеры сообщений\"," +
                "\"guid\":\"" + LocalInGuid1 + "\",\"componentName\":\"InLocalMQEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"Локальная очередь\",\"uri\":\"" + NameLocalQueue1 + "\"}]," +

                "\"processes\":[{\"name\":\"WMQ\",\"icon\":\"fa fa-envelope\",\"group\":\"Точки выхода\"," +
                "\"guid\":\"" + WmqGuid + "\",\"componentName\":\"OutWmqEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"WMQ\",\"sla\":{},\"uri\":\"" + NameWmqQueue + "\"}]," +
                "\"newEndpointType\":\"PROCESS\",\"selectedEndpoint\":{\"name\":\"WMQ\",\"icon\":\"fa fa-envelope\"," +
                "\"group\":\"Точки выхода\",\"guid\":\"" + WmqGuid + "\",\"componentName\":\"OutWmqEndpoint\"," +
                "\"properties\":[],\"domain\":\"" + DomainID + "\",\"customName\":\"WMQ\",\"sla\":{},\"uri\":\"" + NameWmqQueue + "\"}," +

                "\"name\":\"" + SopsName + "\",\"transacted\":true,\"ref\":\"" + transactionPolicy + "\",\"streamCache\":false},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС локальной очередью и WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWithWmqAndLocalQueueAPI(String Cookies, String DomainID, String SopsID, String SopsName,
                                                  String NameLocalQueue1, String NameWmqQueue, String transactionPolicy) {
        String LocalInGuid1 = commonComponents.createIndividualName("guid-1");
        String LocalInGuid2 = commonComponents.createIndividualName("guid-2");
        String WmqGuid = commonComponents.createIndividualName("logger-1");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ENDPOINT_SETTINGS\"," +
                "\"domain\":{\"guid\":\"" + DomainID + "\"},\"in\":[{" +

                "\"name\":\"WMQ\",\"icon\":\"fa fa-envelope\",\"group\":\"Брокеры сообщений\"," +
                "\"guid\":\"" + WmqGuid + "\",\"componentName\":\"InWmqEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"WMQ\",\"uri\":\"" + NameWmqQueue + "\"}]," +
                "" +
                "\"processes\":[{\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"group\":\"Точки выхода\"," +
                "\"guid\":\"" + LocalInGuid1 + "\",\"componentName\":\"OutLocalMQEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"Локальная очередь\",\"sla\":{},\"uri\":\"" + NameLocalQueue1 + "\"}]," +
                "\"selectedEndpoint\":{\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"group\":\"Точки выхода\"," +
                "\"guid\":\"" + LocalInGuid2 + "\",\"componentName\":\"OutLocalMQEndpoint\",\"properties\":[]," +
                "\"domain\":\"" + DomainID + "\",\"customName\":\"Локальная очередь\",\"sla\":{},\"uri\":\"" + NameLocalQueue1 + "\"}," +
                "\"newEndpointType\":\"PROCESS\",\"name\":\"" + SopsName + "\",\"transacted\":true,\"ref\":\"" + transactionPolicy + "\",\"streamCache\":false},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС с WMQ и локальной очередью: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWithWmqAndWmqAPI(String Cookies, String DomainID, String SopsID, String SopsName,
                                           String NameWmqQueue1, String NameWmqQueue2, String transactionPolicy) {
        String WmqGuid = commonComponents.createIndividualName("Guid-1");
        String WmqGuid2 = commonComponents.createIndividualName("Guid-2");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ROUTE_SETTINGS\",\"domain\":{\"guid\":\"" + DomainID + "\"}," +

                "\"in\":[{\"componentName\":\"InWmqEndpoint\",\"guid\":\"" + WmqGuid + "\",\"customName\":\"IBM MQ\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameWmqQueue1 + "\",\"fullUri\":\"wmq://" + NameWmqQueue1 + "\",\"prefix\":\"wmq\",\"endpointTag\":\"from\"," +
                "\"xml\":\"<from uri=\\\"wmq://" + NameWmqQueue1 + "\\\"/>\\n\",\"name\":\"IBM MQ\",\"icon\":\"fa fa-envelope\"}]," +

                "\"processes\":[{\"componentName\":\"OutWmqEndpoint\",\"guid\":\"" + WmqGuid2 + "\",\"customName\":\"WMQ\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameWmqQueue2 + "\",\"fullUri\":\"wmq://" + NameWmqQueue2 + " \",\"sla\":null,\"isWireTap\":false," +
                "\"useVariables\":false,\"isTransacted\":false,\"prefix\":\"wmq\",\"endpointTag\":\"to\"," +
                "\"xml\":\"<to uri=\\\"wmq://" + NameWmqQueue2 + "\\\"/>\\n\",\"name\":\"IBM MQ\",\"icon\":\"fa fa-envelope\"}]," +
                "\"name\":\"" + SopsName + "\",\"transacted\":true,\"ref\":\"" + transactionPolicy + "\",\"streamCache\":false," +
                "\"autoStartup\":true},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС с WMQ и WMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public void createSopsWithLinkToSopsAndLocalQueueAPI(String Cookies, String DomainID, String SopsID, String SopsName,
                                                         String NameLink, String NameLocalQueue, String transactionPolicy) {
        String guid = commonComponents.createIndividualName("Guid-1");
        String guid2 = commonComponents.createIndividualName("Guid-2");

        String Body = "{\"data\":{\"id\":\"" + SopsID + "\",\"stage\":\"ROUTE_SETTINGS\",\"domain\":{\"guid\":\"" + DomainID + "\"}," +

                "\"in\":[{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameLink + "\",\"fullUri\":\"direct-vm://" + NameLink + "\",\"isGlobal\":true,\"routeName\":null," +
                "\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"xml\":\"<from uri=\\\"direct-vm://" + NameLink + "\\\"/>\\n\"," +
                "\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}]," +

                "\"processes\":[{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid2 + "\",\"customName\":\"Локальная очередь\"," +
                "\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false," +
                "\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false," +
                "\"uri\":\"" + NameLocalQueue + "\",\"fullUri\":\"localmq://" + NameLocalQueue + "\",\"sla\":null,\"isWireTap\":false," +
                "\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\"," +
                "\"xml\":\"<to uri=\\\"localmq://" + NameLocalQueue + "\\\"/>\\n\",\"name\":\"Локальная очередь\"," +
                "\"icon\":\"fa fa-envelope\"}]," +

                "\"name\":\"" + SopsName + "\",\"transacted\":false,\"ref\":\"" + transactionPolicy + "\"," +
                "\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/broker/domain/" + DomainID + "/route/saveRoute").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании СОПС со ссылкой на СОПС и локальной очередью: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(SopsName));
    }

    public String uploadModeleAPI(String Cookies, String filePath) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType("multipart/form-data").
                        accept("application/json").
                        multiPart(new File(filePath)).
                        baseUri(BaseUrl).basePath("/manager/api/uploads?asZip=true").
                        when().post().
                        then().extract().response();

        String fileID = response.path("id");
        basePage.sout("Ответ при загрузке модели: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
        return fileID;
    }

    public void createDirectoryAPI(String Cookies, String directoryName) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(BaseUrl).basePath("/manager/api/resources").
                        queryParam("path", "/").
                        queryParam("directory", directoryName).
                        queryParam("mode", "FAIL").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании директории: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(directoryName));
    }

    public String uploadFileAPI(String Cookies, String filePath) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType("multipart/form-data").
                        accept("application/json").
                        multiPart(new File(filePath)).
                        baseUri(BaseUrl).basePath("/manager/api/uploads").
                        when().post().
                        then().extract().response();

        String fileID = response.path("id");
        basePage.sout("Ответ при загрузке файла: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
        return fileID;
    }

    public void setDirectoryForFileAPI(String Cookies, String directoryName, String fileID, String fileName) {
        String Body = " {\"id\":\"" + fileID + "\",\"filename\":\"" + fileName + "\",\"contentType\":\"application/json\",\"size\":20}";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/resources").
                        queryParam("path", "/" + directoryName).queryParam("mode", "OVERRIDE").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при установки файлу директории: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(fileName));
    }

    public void deleteFileAPI(String Cookies, String url, String filePath, String fileName) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        queryParam("path", "/" + filePath + "/" + fileName).
                        baseUri(url).basePath("/manager/api/resources").
                        when().delete().
                        then().extract().response();
        basePage.sout("Ответ при удалении файла: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void deleteDirectoryAPI(String Cookies, String url, String filePath) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        queryParam("path", "/" + filePath).
                        baseUri(url).basePath("/manager/api/resources").
                        when().delete().
                        then().extract().response();
        basePage.sout("Ответ при удалении директории: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void createModelDataAPI(String Cookies, String DomainID, String typeModelData, String nameModelData, String classNameModelData, boolean globalModelData, String idFileModelData) {
        String Body = "{\"type\":\"" + typeModelData + "\",\"name\":\"" + nameModelData + "\",\"className\":\"" + classNameModelData + "\",\"packageName\":\"\",\"global\":\"" + globalModelData + "\",\"serializable\":true,\"apiModel\":true,\"apiModelPrefix\":\"\",\"file\":{\"entries\":{},\"contentType\":\"application/json\",\"filename\":\"schemaForRest.json\",\"id\":\"" + idFileModelData + "\",\"size\":20}}";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/domains/" + DomainID + "/models").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании модели данных: " + response.getBody().

                asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    public void createAddConfigurationAPI(String Cookies, String url, String[] addConfig) {
        String body = null;
        String typeConfig = addConfig[0];
        String DomainID = addConfig[1];
        String NameAddConfig = addConfig[2];

        switch (typeConfig) {
            case "Пользовательская":
                String className = addConfig[3];
                String beanType = addConfig[4];
                body = "{\"name\":\"" + NameAddConfig + "\",\"className\":\"" + className + "\",\"beanType\":\"" + beanType + "\",\"domain\":\"" + DomainID + "\",\"constructorArgs\":[],\"properties\":[]}";
                break;
            case "Фабрика соединений Расширенного МО":
                String userName = addConfig[3];
                String userPassword = addConfig[4];
                String scheme = addConfig[5].split("~")[0];
                String host = addConfig[5].split("~")[1];
                String port = addConfig[5].split("~")[2];
                body = "{\"name\":\"" + NameAddConfig + "\",\"beanType\":\"QME_CONNECTION_FACTORY\",\"domain\":\"" + DomainID + "\",\"constructorArgs\":[],\"properties\":[],\"user\":\"" + userName + "\",\"password\":\"" + userPassword + "\",\"parametersList\":[{\"name\":\"reconnectAttempts\",\"value\":\"100\"}],\"hosts\":[{\"scheme\":\"" + scheme + "\",\"host\":\"" + host + "\",\"port\":\"" + port + "\"}]}";
                break;
            default:
                throw new AssertionError("Пропущено формирование тела запроса");
        }

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/beans/" + DomainID + "/bean").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании доп.конфигурации: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");

    }

    public void editAddConfigurationAPI(String Cookies, String url, String[] oldConfig, String[] newConfig) {
        String body = null;
        String typeConfig = oldConfig[0];
        String domainId = oldConfig[1];

        switch (typeConfig) {
            case "Пользовательская":
                break;
            case "Фабрика соединений Расширенного МО":
                String NameAddConfigOld = oldConfig[2];
                String userNameOld = oldConfig[3];
                String userPasswordOld = oldConfig[4];
                String schemeOld = oldConfig[5].split("~")[0];
                String hostOld = oldConfig[5].split("~")[1];
                String portOld = oldConfig[5].split("~")[2];

                String NameAddConfigNew = newConfig[2];
                String userNameNew = newConfig[3];
                String userPasswordNew = newConfig[4];
                String schemeNew = newConfig[5].split("~")[0];
                String hostNew = newConfig[5].split("~")[1];
                String portNew = newConfig[5].split("~")[2];


                body = "{\"id\":\"" + NameAddConfigOld + "\",\"oldId\":\"" + NameAddConfigOld + "\",\"name\":\"" + NameAddConfigNew + "\",\"parentBeanId\":null,\"className\":\"org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory\",\"destroyMethod\":null,\"factoryMethod\":null,\"beanType\":\"QME_CONNECTION_FACTORY\",\"domain\":\"" + domainId + "\",\"status\":\"ACTIVE\",\"constructorArgs\":[],\"properties\":[{\"name\":\"brokerURL\",\"type\":\"value\",\"value\":\"(" + schemeOld + "://" + hostOld + ":" + portOld + ")\"},{\"name\":\"user\",\"type\":\"value\",\"value\":\"" + userNameOld + "\"},{\"name\":\"password\",\"type\":\"value\",\"value\":\"" + userPasswordOld + "\"}],\"user\":\"" + userNameNew + "\",\"password\":\"" + userPasswordNew + "\",\"parametersList\":[],\"hosts\":[{\"scheme\":\"" + schemeNew + "\",\"host\":\"" + hostNew + "\",\"port\":\"" + portNew + "\"}]}";

                break;
            default:
                throw new AssertionError("Пропущено формирование тела запроса");
        }

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/beans/" + domainId + "/bean").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании доп.конфигурации: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");

    }

    public void createConstantsOfDomainAPI(String Cookies, String DomainID, Object[][] allConstants) {
        String startBody = "{\"data\":[";
        String body = "";
        String endBody = "]}";

        System.out.println(Arrays.deepToString(allConstants));

        for (Object[] constant : allConstants) {
            body = body + "{\"key\": \"" + constant[0] + "\", \"value\": \"" + constant[1] + "\", \"secured\" :" + constant[2] + "}, ";
        }

        String Body = startBody + body.replaceAll(", $", "") + endBody;

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/properties/" + DomainID + "/saveProperties").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании констант домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals((200), response.then().extract().statusCode());
    }

    public void createConstantsOfDomainAPI(String Cookies, String url, String DomainID, Object[][] allConstants) {
        String startBody = "{\"data\":[";
        String body = "";
        String endBody = "]}";

        System.out.println(Arrays.deepToString(allConstants));

        for (Object[] constant : allConstants) {
            body = body + "{\"key\": \"" + constant[0] + "\", \"value\": \"" + constant[1] + "\", \"secured\" :" + constant[2] + "}, ";
        }

        String Body = startBody + body.replaceAll(", $", "") + endBody;

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/properties/" + DomainID + "/saveProperties").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании констант домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(response.then().extract().statusCode(), (200));
    }

    public void createConstantsOfBrokerAPI(String Cookies, String url, String key, String value) {
        String body = "{\"data\":[{\"key\":\"" + key + "\",\"value\":\"" + value + "\",\"secured\":false}]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/properties/broker").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании константы Брокера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(response.then().extract().statusCode(), (200));
    }

    public void createConstantsOfBroker199API(String Cookies, String url, String key, String value) {
        String body = "{\"" + key + "\":\"" + value + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/properties/application").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании константы Брокера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(response.then().extract().statusCode(), (200));
    }

    public void deleteAllConstantsOfBrokerAPI(String Cookies, String url) {
        String body = "{\"data\":[]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/properties/broker").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при удалении всех констант Брокера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(response.then().extract().statusCode(), (200));
    }

    public Response getPerfomnceOfBrokerAPI(String Cookies, String baseUrl, String domainID, String interval) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        param("interval", interval).
                        body(baseUrl).
                        baseUri(baseUrl).basePath(" /manager/api/broker/domain/" + domainID + "/monitoring/route").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ на запрос производительности домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return response;
    }

    public void clearStatisticOfBrokerAPI(String Cookies, String baseUrl, String domainID) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(baseUrl).
                        baseUri(baseUrl).basePath(" /manager/api/broker/domain/" + domainID + "/monitoring/route").
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ на запрос очистки статистики домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public Response getStatisticOfDomainAPI(String Cookies, String baseUrl, String domainID) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(baseUrl).
                        baseUri(baseUrl).basePath(" /manager/api/broker/domain/" + domainID + "/route").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ на запрос получения статистики домена: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return response;
    }
}


