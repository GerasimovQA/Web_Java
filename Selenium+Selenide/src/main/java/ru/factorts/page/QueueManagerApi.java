package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;

public class QueueManagerApi extends Base {

    CommonComponents commonComponents;

    public enum typeQueue {local, virtual, fragment, aggregate, TRANSPORT}

    public enum queueCharacteristic {queueSize, consumersCount, producersCount, enqueued, dequeued, queueType, channel}

    public void createQueueLocalAPI(String Cookies, typeQueue type, String nameQueue, String url) {
        String body = "{\"icon\":\"inbox\",\"name\":\"" + nameQueue + "\",\"queueType\":\"" + type + "\",\"destinations\":[]," +
                "\"consumersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000}," +
                "\"workersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000},\"size\":100}";
        String basePath = "/manager/api/queue/";
        String waitingResponse = "{\"id\":\"" + nameQueue + "\",\"name\":\"" + nameQueue + "\",\"queueSize\":0,\"consumersCount\":0," +
                "\"producersCount\":0,\"enqueued\":0,\"dequeued\":0,\"queueType\":\"local\",\"channel\":null," +
                "\"minMessageSize\":0,\"maxMessageSize\":0,\"storeMessageSize\":0,\"destinations\":null}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании " + type + " очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(waitingResponse, response.getBody().asString());
    }

    public void createQueueTransportAPI(String Cookies, typeQueue type, String nameQueue, String nameCanal, String url) {
        String body = "{\"created\":false,\"name\":\"" + nameCanal + "\",\"uri\":\"static:(tcp://" + url + ")\"," +
                "\"staticMappings\":[{\"name\":\"" + nameQueue + "\",\"type\":\"QUEUE\",\"value\":\"QUEUE:" + nameQueue + "\"}]," +
                "\"properties\":{\"protocol\":\"static\",\"networkTTL\":\"6\",\"consumerTTL\":\"6\",\"messageTTL\":\"6\"," +
                "\"backOffMultiplier\":\"2\",\"initialReconnectDelay\":\"1000\",\"prefetchSize\":\"1000\"," +
                "\"maxReconnectDelay\":\"30000\",\"userName\":\"\",\"password\":\"\",\"useExponentialBackOff\":true," +
                "\"dynamicOnly\":false,\"decreaseNetworkConsumerPriority\":false,\"conduitSubscriptions\":false," +
                "\"duplex\":false,\"suppressDuplicateQueueSubscriptions\":false,\"bridgeTempDestinations\":false,\"alwaysSyncSend\":false,\"staticBridge\":true}}";
        String basePath = "/manager/api/mq/connector/network/";
        String waitingResponse = "id";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(BaseUrl).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании " + type + " очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(waitingResponse));
    }

    public void createQueueVirtualSegmentationAggregationAPI(String Cookies, typeQueue type, String name, String name2) {
        String body = null;
        String basePath = null;
        String waitingResponse = null;
        switch (type) {
            case virtual:
                body = "{\"icon\":\"inbox\",\"name\":\"" + name + "\",\"queueType\":\"" + type + "\",\"destinations\":[{\"name\":\"" + name2 + "\"," +
                        "\"className\":\"Select-create-option-placeholder\"}],\"consumersConfiguration\":{\"coreThreads\":1," +
                        "\"maxThreads\":20,\"queueSize\":5000},\"workersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000},\"size\":100}";
                basePath = "/manager/api/mq/destination/virtual/";
                waitingResponse = "id";
                break;
            case fragment:
                body = "{\"target\":\"" + name + "\",\"segmentationTarget\":\"" + name2 + "\",\"consumersConfiguration\":{\"coreThreads\":1," +
                        "\"maxThreads\":20,\"queueSize\":5000},\"workersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000},\"size\":100}";
                basePath = "/manager/api/queue/fragment/insert";
                waitingResponse = "id";
                break;
            case aggregate:
                body = "{\"target\":\"" + name + "\",\"destination\":\"" + name2 + "\"}";
                basePath = "/manager/api/queue/aggregate/insert";
                waitingResponse = "id";
                break;
            default:
                throw new AssertionError("Настройка очереди пропущена");
        }

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(BaseUrl).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании " + type + " очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public void deleteQueueAPI(String Cookies, String url, String nameQueue) {
        String body = "{\"name\":\"" + nameQueue + "\"}";
        String basePath = "/manager/api/queue/";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void createTopicAPI(String Cookies, String nameTopic) {
        String body = "{\"name\":\"" + nameTopic + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/topic/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании раздела:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(nameTopic));
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public Response getListOfTopicAPI(String Cookies, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/topic/").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при запросе списков разделов:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public void createSubscraberAPI(String Cookies, String nameTopic, String nameDescraber, String idOfDescraber) {
        String body = "{\"isNew\":true,\"clientId\":\"" + idOfDescraber + "\",\"name\":\"" + nameDescraber + "\",\"topic\":\"" + nameTopic + "\",\"selector\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/mq/subscription/durable/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании подписчика:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public Response getListOfSubscriberAPI(String Cookies, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/mq/subscription/durable/").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при запросе списков подписчиков:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public void restartMqAPI(String cookies) {
        String Body = "";
        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/module/factor-mq/restart").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при перезапуске MQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("true", response.getBody().asString());
    }

    public void restartMqAPI(String cookies, String url) {
        String Body = "";
        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/module/factor-mq/restart").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при перезапуске MQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("true", response.getBody().asString());
    }

    public void clearQueueAPI(String Cookies, String queue) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/queue/" + queue + "/clear").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при очистке очереди " + queue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(queue));
    }

    public void configMqAPI(String cookies, Boolean schedulerSupportValue, Boolean anonymousAccessValue, int jvmHeapUsageValue,
                            String memoryLimitValue, String tempLimitValue) {
        String body = "{\"schedulerSupport\":" + schedulerSupportValue + ",\"anonymousAccess\":" + anonymousAccessValue + ",\"systemUsage\":{\"jvmHeapUsage\":" + jvmHeapUsageValue + ",\"memoryLimit\":\"" + memoryLimitValue + "\",\"tempLimit\":\"" + tempLimitValue + "\"}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(BaseUrl).basePath("/manager/api/mq/config/saveMqConfig").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при конфигурации MQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createChanelAPI(String Cookies, Boolean channelExist, String chanelName, String chanelProtocol, String chanelUri, String chanelPort,
                                String chanelStaticMappingsName, Boolean chanelDuplex, String url) {
        String body = "{\"created\":" + channelExist + ",\"name\":\"" + chanelName + "\",\"uri\":\"static:(" + chanelProtocol + "://" + chanelUri + ":" + chanelPort + ")\",\"staticMappings\":[{\"name\":\"" + chanelStaticMappingsName + "\",\"type\":\"QUEUE\",\"value\":\"QUEUE:line\"}],\"properties\":{\"protocol\":\"static\",\"networkTTL\":\"6\",\"consumerTTL\":\"6\",\"messageTTL\":\"6\",\"backOffMultiplier\":\"2\",\"initialReconnectDelay\":\"1000\",\"prefetchSize\":\"1000\",\"maxReconnectDelay\":\"30000\",\"userName\":\"\",\"password\":\"\",\"useExponentialBackOff\":true,\"dynamicOnly\":false,\"decreaseNetworkConsumerPriority\":false,\"conduitSubscriptions\":false,\"duplex\":" + chanelDuplex + ",\"suppressDuplicateQueueSubscriptions\":false,\"bridgeTempDestinations\":false,\"alwaysSyncSend\":false,\"staticBridge\":true}}";
        String basePath = "/manager/api/mq/connector/network/";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании канала: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }


    public void startChannelAPI(String Cookies, String chanelName, String url) {
        String body = "";
        String basePath = "/manager/api/mq/connector/network/" + chanelName + "/start";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при старте канала: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    public void stopChannelAPI(String Cookies, String chanelName, String url) {
        String body = "";
        String basePath = "/manager/api/mq/connector/network/" + chanelName + "/stop";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при остановке канала: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public Response getListOfChannelAPI(String Cookies, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/mq/connector/network/").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при запросе списка каналов: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public Response getListOfQueueAPI(String Cookies, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/queue/").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при запросе списка очередей: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public Response returnResponseWithParametersOfQueue(String Cookies, String managerName, String queueNameValue, String url) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/queues/" + queueNameValue).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении характеристик очереди " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");

        return response;
    }

    public Response getListOfMessagesInQueue(String Cookies, String queueNameValue, String url) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/queue/" + queueNameValue + "/browse").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при просмотре очереди " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return response;
    }

    public String returnSpecificParameterOfQueue(String Cookies, String queueNameValue, String specificParameterName, String url) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/queue/" + queueNameValue).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении характеристик очереди " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");

        return response.path(specificParameterName).toString();
    }

    public void queueCheckAllParametersAPI(String Cookies, String managerName, String queueNameValue, int messagesInDepthValue, int numberOfConsumersValue, int numberOfProducersValue, int messagesInSentValue, int messagesInTakenValue, String queueTypeValue, String chanalNameValue, String url) {
        sleep(2000);
        Response response = returnResponseWithParametersOfQueue(Cookies, managerName, queueNameValue, url);

        Assert.assertTrue(response.getBody().asString().contains(queueNameValue));
        int queueDepth = response.path("queueSize");
        basePage.compareIntAndInt(messagesInDepthValue, queueDepth);
        int consumersCount = response.path("consumersCount");
        basePage.compareIntAndInt(numberOfConsumersValue, consumersCount);
        int producersCount = response.path("producersCount");
        basePage.compareIntAndInt(numberOfProducersValue, producersCount);
        int enqueued = response.path("enqueued");
        basePage.compareIntAndInt(messagesInSentValue, enqueued);
        int dequeued = response.path("dequeued");
        basePage.compareIntAndInt(messagesInTakenValue, dequeued);
        String queueType = response.path("queueType");
        Assert.assertEquals(queueTypeValue, queueType);

        String channel = response.path("channel");
        if (chanalNameValue.equals("null")) {
            Assert.assertNull(channel);
        } else {
            Assert.assertEquals(chanalNameValue, channel);
        }
    }

    public void listOfMessagesInQueueShouldBeEmptyAPI(String Cookies, String queueNameValue, String url) {
        Response response = getListOfMessagesInQueue(Cookies, queueNameValue, url);
        basePage.compareStringAndString("[]", response.getBody().asString());
    }


    public void queueCheckSpecificParameterAPI(String Cookies, String queueNameValue, String specificParameterName, String specificParameterValue, String url) {
        String specificParameterResponse = returnSpecificParameterOfQueue(Cookies, queueNameValue, specificParameterName, url);
        basePage.compareStringAndString(specificParameterValue, specificParameterResponse);
    }

    public void queueShouldNotExist(String Cookies, String managerName, String queueNameValue, String url) {
        Response response = returnResponseWithParametersOfQueue(Cookies, managerName, queueNameValue, url);
        Assert.assertEquals(400, response.then().extract().statusCode());
        Assert.assertTrue(response.getBody().asString().contains("Ошибка на стороне сервера"));
    }

    public void queueShouldHaveRangeMessagesAPI(String Cookies, String queueNameValue, int messagesInDepthValueMin, int messagesInDepthValueMax, String url) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/queue/" + queueNameValue).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении характеристик очереди " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains(queueNameValue));

        int queueDepth = response.path("queueSize");
        System.out.println("Количество сообщений в очереди = " + queueDepth);
        Assert.assertTrue("Количество сообщений в очереди меньше необходимого", queueDepth > messagesInDepthValueMin);
        Assert.assertTrue("Количество сообщений в очереди больше необходимого", queueDepth < messagesInDepthValueMax);
    }


    public void sendMessgeInQueueAPI(String cookies, String nameQueue, String text, int sumMessages) {
//        String Body = "{\"_destination\":\"\",\"bodyType\":\"TEXT\",\"destType\":\"QUEUE\",\"dest\":\"" + nameQueue + "\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":\"" + sumMessages + "\",\"text\":\"" + text + "\",\"defaultText\":null,\"files\":[],\"properties\":[{\"type\":\"COUNTER_HEADER\",\"value\":\"counterHeader\"},{\"type\":\"PERSISTENT\",\"value\":false}]}";

        String Body = "{\"dest\":{\"module\":\"MQ\",\"destType\":\"QUEUE\",\"destination\":\"" + nameQueue + "\"},\"data\":{\"bodyType\":\"TEXT\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":" + sumMessages + ",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":true}]}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/sender/send").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после  отправки сообщений: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertFalse(response.getBody().asString().contains("Ошибка"));
        Assert.assertEquals(200, response.then().extract().statusCode());
        sleep(1000);
    }

    public void sendMessgeInQueueAPI(String cookies, String baseUrl, String nameQueue, String text, int sumMessages, boolean persistent, String priority) {
//        String Body = "{\"_destination\":\"\",\"bodyType\":\"TEXT\",\"destType\":\"QUEUE\",\"dest\":\"" + nameQueue + "\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":\"" + sumMessages + "\",\"text\":\"" + text + "\",\"defaultText\":null,\"files\":[],\"properties\":[{\"type\":\"COUNTER_HEADER\",\"value\":\"counterHeader\"},{\"type\":\"PERSISTENT\",\"value\":\"" + persistent + "\"},{\"type\":\"PRIORITY\",\"value\":\"" + priority + "\"}]}";

        String Body = "{\"dest\":{\"module\":\"MQ\",\"destType\":\"QUEUE\",\"destination\":\"" + nameQueue + "\"},\"data\":{\"bodyType\":\"TEXT\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":" + sumMessages + ",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":" + persistent + "},{\"type\":\"PRIORITY\",\"value\":\"" + priority + "\"}]}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/sender/send").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после  отправки сообщений: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertFalse(response.getBody().asString().contains("Ошибка"));
        Assert.assertEquals(200, response.then().extract().statusCode());
        sleep(1000);
    }

    public void sendMessgeInQueue199API(String cookies, String baseUrl, String nameQueue, String text, int sumMessages, boolean persistent, String priority) {
        String Body = "{\"_destination\":\"QUEUE:" + nameQueue + "\",\"bodyType\":\"TEXT\",\"destType\":\"QUEUE\",\"dest\":\"" + nameQueue + "\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":\"" + sumMessages + "\",\"text\":\"" + text + "\",\"defaultText\":null,\"files\":[],\"properties\":[{\"type\":\"COUNTER_HEADER\",\"value\":\"counterHeader\"},{\"type\":\"PERSISTENT\",\"value\":\"" + persistent + "\"},{\"type\":\"PRIORITY\",\"value\":\"" + priority + "\"}]}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/sender/send").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после  отправки сообщений: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(!response.getBody().asString().contains("Ошибка"));
    }

    public void sendMessgeInQueue6API(String cookies, String baseUrl, String nameQueue, String text, int sumMessages, boolean persistent, String priority) {
        String Body = "{\"_destination\":\"\",\"bodyType\":\"TEXT\",\"destType\":\"QUEUE\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":" + sumMessages + ",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":" + persistent + "},{\"type\":\"PRIORITY\",\"value\":\"" + priority + "\"}]}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(baseUrl).basePath("/manager/api/sender/send/QUEUE/" + nameQueue).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после  отправки сообщений: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(!response.getBody().asString().contains("Ошибка"));
    }

    public void createConnectorAPI(String Cookies, String connectorName, String connectorProtocol, String connecotrUri, String connectorPort,
                                   String[] connectorParameters, String url) {
        String body = "{\"name\":\"" + connectorName + "\",\"transport\":\"" + connectorProtocol + "\",\"overNIO\":false," +
                "\"overSSL\":false,\"address\":\"" + connecotrUri + "\",\"port\":\"" + connectorPort + "\",\"parametersList\":[" +
                "{\"name\":\"" + connectorParameters[0].split("\\|")[0] + "\"," +
                "\"value\":\"" + connectorParameters[0].split("\\|")[1] + "\"}," +
                "{\"name\":\"" + connectorParameters[1].split("\\|")[0] + "\"," +
                "\"value\":\"" + connectorParameters[1].split("\\|")[1] + "\"}]," +
                "\"connections\":[]}";

        String basePath = "/manager/api/mq/connector/transport/";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании приёмника: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    public Response getListOfConnectorAPI(String Cookies, String url) {
        String basePath = "/manager/api/mq/connector/transport/";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath(basePath).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ запросе списка приёмников: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public void deleteConnectorAPI(String Cookies, String connectorID, String url) {
        String Body = "{\"id\":\"" + connectorID + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/mq/connector/transport/").
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении приёмника: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void changeSettingQueueManagerAPI(String Cookies, String url, Boolean schedulerSupport, Boolean anonymousAccess, Boolean asyncNetworkConnectors, int jvmHeapUsage, String memoryLimit, String tempLimit) {
        String Body = "{\"schedulerSupport\":\"" + schedulerSupport + "\",\"anonymousAccess\":\"" + anonymousAccess + "\",\"asyncNetworkConnectors\":\"" + asyncNetworkConnectors + "\",\"systemUsage\":{\"jvmHeapUsage\":\"" + jvmHeapUsage + "\",\"memoryLimit\":\"" + memoryLimit + "\",\"tempLimit\":\"" + tempLimit + "\"}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/mq/config/saveMqConfig").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании настроек менеджера очередей: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

}


