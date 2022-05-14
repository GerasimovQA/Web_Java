package ru.factorts.page;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;

public class QueueManagerArtemisApi extends Base {

    CommonComponents commonComponents;

    public enum typeQueue {local, virtual, fragment, aggregate, TRANSPORT}

    public enum queueCharacteristic {queueSize, consumersCount, producersCount, enqueued, dequeued, queueType, channel}

    public void createQueueArtemisAPI(String Cookies, String url, Object[] queueSetting) {
        String nameQueue = queueSetting[0].toString().split("\\|")[0];
        boolean addressEqualsName = Boolean.parseBoolean(queueSetting[0].toString().split("\\|")[1]);
        String addresQueue = queueSetting[0].toString().split("\\|")[2];
        String typeRout = queueSetting[0].toString().split("\\|")[3];

        String body2 = "";
        String body1 = "{\"routingType\":\"" + typeRout + "\",\"name\":\"" + nameQueue + "\",\"address\":\"" + addresQueue + "\",\"isAddressMatchName\":" + addressEqualsName + ",\"parametersList\":[\n";

        if (queueSetting.length > 1) {
            for (int x = 0; x < queueSetting[1].toString().split("\\|").length; x++) {
                String param = "{\"disable\":false,\"deleteOff\":false,\"xmlKey\":\"" + queueSetting[1].toString().split("\\|")[x].split("/")[0] + "\",\"name\":\"" + queueSetting[1].toString().split("\\|")[x].split("/")[1] + "\",\"value\":" + queueSetting[1].toString().split("\\|")[x].split("/")[2] + "},";
                body2 = body2 + param;
            }
        }

        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/queues/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании очереди Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void clearQueueAPI(String Cookies, String queue) {
        String Body = "";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/qme/queues/" + queue + "/clear").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при очистке очереди Artemis" + queue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void sendMessageInQueueAPI(String Cookies, String url, String domainID, String queue, String message) {
        String Body = "{\"uri\":\"local2amq://" + queue + "\",\"message\":\"" + message + "\",\"headers\":[]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/route/" + domainID + "/exchange").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при отправке сообщения в очередь Artemis " + queue + " через СОПС : " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(response.getBody().asString(), message);
    }

    @Step
    public void queueCheckAllParametersAPI(String Cookies, String url, String queueNameValue, int messageCountValue, int consumerCountValue, int messagesAddedValue, int messagesAckedValue, Enum routingTypeValue) {
        sleep(1000);
        Response response = returnResponseWithParametersOfQueue(Cookies, url, queueNameValue);

        Assert.assertTrue(response.getBody().asString().contains(queueNameValue));
        int messageCount = response.path("messageCount");
        basePage.compareIntAndInt(messageCountValue, messageCount);
        int consumerCount = response.path("consumerCount");
        basePage.compareIntAndInt(consumerCountValue, consumerCount);
        int messagesAdded = response.path("messagesAdded");
        basePage.compareIntAndInt(messagesAddedValue, messagesAdded);
        int messagesAcked = response.path("messagesAcked");
        basePage.compareIntAndInt(messagesAckedValue, messagesAcked);
        String routingType = response.path("routingType");
        basePage.compareStringAndString(routingTypeValue.toString(), routingType);
    }

    public Response returnResponseWithParametersOfQueue(String Cookies, String url, String queueNameValue) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/qme/queues/" + queueNameValue).
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при получении характеристик очереди Artemis " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");

        return response;
    }

    public void createAddressArtemisAPI(String Cookies, Object[] addressSetting) {
        String nameAddress = addressSetting[0].toString().split("\\|")[0];
        String typeRout = addressSetting[0].toString().split("\\|")[1];

        String body2 = "";
        String body1 = "{\"routingTypes\":\"" + typeRout + "\",\"name\":\"" + nameAddress + "\",\"parametersList\":[\n";

        for (int x = 0; x < addressSetting[1].toString().split("\\|").length; x++) {
            String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + addressSetting[1].toString().split("\\|")[x].split("/")[0] + "\",\"value\":" + addressSetting[1].toString().split("\\|")[x].split("/")[1] + "},";
            body2 = body2 + param;
        }

        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/qme/address/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании адреса Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void createVirtualAddressArtemisAPI(String Cookies, Object[] addressSetting) {
        String nameVirtualAddress = addressSetting[0].toString().split("\\|")[0];
        String addressSender = addressSetting[0].toString().split("\\|")[1];
        String addressRecipient = addressSetting[0].toString().split("\\|")[2];
        String filter = addressSetting[0].toString().split("\\|")[3];
        boolean exclusive = Boolean.parseBoolean(addressSetting[0].toString().split("\\|")[4]);
        String typeRout = addressSetting[0].toString().split("\\|")[5];

        String body = "{\"id\":\"" + nameVirtualAddress + "\",\"name\":\"" + nameVirtualAddress + "\",\"configuration\":{\"name\":\"" + nameVirtualAddress + "\",\"address\":\"" + addressSender + "\",\"forwardingAddress\":\"" + addressRecipient + "\",\"filterString\":\"" + filter + "\",\"exclusive\":" + exclusive + ",\"routingType\":\"" + typeRout + "\"}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/qme/diverts/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании виртуального адреса Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void createReceiverArtemisAPI(String Cookies, Object[] addressSetting) {
        String receiverName = addressSetting[0].toString().split("\\|")[0];
        String receiverAddress = addressSetting[0].toString().split("\\|")[1];
        String receiverPort = addressSetting[0].toString().split("\\|")[2];
        String receiverProtocol = addressSetting[0].toString().split("\\|")[3];
        String body2 = "";
        String body1 = "{\"name\":\"" + receiverName + "\",\"id\":\"" + receiverName + "\",\"address\":\"" + receiverAddress + "\",\"port\":\"" + receiverPort + "\",\"scheme\":\"" + receiverProtocol + "\",\"parametersList\":[\n";
        for (int x = 0; x < addressSetting[1].toString().split("\\|").length; x++) {
            String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + addressSetting[1].toString().split("\\|")[x].split("~")[0] + "\",\"value\":" + addressSetting[1].toString().split("\\|")[x].split("~")[1] + "},";
            body2 = body2 + param;
        }
        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/qme/transport/acceptors/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании получателя Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void editReceiverArtemisAPI(String Cookies, String url, Object[] receiverOld, Object[] receiverNew) {
        String receiverNameOld = receiverOld[0].toString().split("\\|")[0];
        String receiverNameNew = receiverNew[0].toString().split("\\|")[0];
        String receiverAddressNew = receiverNew[0].toString().split("\\|")[1];
        String receiverPortNew = receiverNew[0].toString().split("\\|")[2];
        String receiverProtocolNew = receiverNew[0].toString().split("\\|")[3];
        String body2 = "";
        String body1 = "{\"oldId\":\"" + receiverNameOld + "\",\"name\":\"" + receiverNameNew + "\",\"id\":\"" + receiverNameNew + "\",\"address\":\"" + receiverAddressNew + "\",\"port\":\"" + receiverPortNew + "\",\"scheme\":\"" + receiverProtocolNew + "\",\"parametersList\":[\n";
        for (int x = 0; x < receiverNew[1].toString().split("\\|").length; x++) {
            String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + receiverNew[1].toString().split("\\|")[x].split("~")[0] + "\",\"value\":" + receiverNew[1].toString().split("\\|")[x].split("~")[1] + "},";
            body2 = body2 + param;
        }
        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/transport/acceptors/").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при редактировании получателя Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void createConnectorArtemisAPI(String Cookies, String url, Object[] connectorSetting) {
        String connectorlName = connectorSetting[0].toString().split("\\|")[0];
        String scheme = connectorSetting[0].toString().split("\\|")[1];
        String address = connectorSetting[0].toString().split("\\|")[2];
        String port = connectorSetting[0].toString().split("\\|")[3];


        String body2 = "";
        String body1 = " {\"id\":\"" + connectorlName + "\",\"name\":\"" + connectorlName + "\",\"address\":\"" + address + "\",\"port\":\"" + port + "\",\"scheme\":\"" + scheme.toLowerCase() + "\",\"parametersList\":[\n";
        if (connectorSetting.length > 1) {
            for (int x = 0; x < connectorSetting[1].toString().split("\\|").length; x++) {
                String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + connectorSetting[1].toString().split("\\|")[x].split("~")[0] + "\",\"value\":\"" + connectorSetting[1].toString().split("\\|")[x].split("~")[1] + "\"},";
                body2 = body2 + param;
            }
        }
        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/transport/connectors/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании коннектора Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void deleteConnectorArtemisAPI(String Cookies, String url, String[] connectorSetting) {
        String connectorName = connectorSetting[0].toString().split("\\|")[0];
        String scheme = connectorSetting[0].toString().split("\\|")[1];
        String address = connectorSetting[0].toString().split("\\|")[2];
        String port = connectorSetting[0].toString().split("\\|")[3];
        String body = "{\"name\":\"" + connectorName + "\"}";
//                String body = "{\"oldId\":\"qwe\",\"id\":\"qwe\",\"name\":\"qwe\",\"address\":\"192.168.57.240\",\"port\":3005,\"scheme\":\"tcp\",\"parametersList\":[]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/transport/connectors/").
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении коннектора Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void createChanneArtemisAPI(String Cookies, String url, Object[] channelSetting) {
        String channelName = channelSetting[0].toString().split("\\|")[0];
        String queueName = channelSetting[0].toString().split("\\|")[1];
        String redirectionAddres = channelSetting[0].toString().split("\\|")[2];
        String connector = channelSetting[0].toString().split("\\|")[3];
        String body2 = "";
        String body1 = " {\"id\":\"" + channelName + "\",\"name\":\"" + channelName + "\",\"forwardingAddress\":\"" + redirectionAddres + "\",\"queueName\":\"" + queueName + "\",\"staticConnectors\":[\"" + connector + "\"],\"parametersList\":[\n";
        for (int x = 0; x < channelSetting[1].toString().split("\\|").length; x++) {
            String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + channelSetting[1].toString().split("\\|")[x].split("~")[0] + "\",\"value\":\"" + channelSetting[1].toString().split("\\|")[x].split("~")[1] + "\"},";
            body2 = body2 + param;
        }
        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/bridges/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании канала Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void editChanneArtemisAPI(String Cookies, String url, String[] channelOld, String[] channelNew) {
        String channelNameOld = channelOld[0].split("\\|")[0];
        String channelNameNew = channelNew[0].split("\\|")[0];
        String queueName = channelNew[0].split("\\|")[1];
        String redirectionAddres = channelNew[0].split("\\|")[2];
        String connector = channelNew[0].split("\\|")[3];
        String body2 = "";
        String body1 = " {\"oldId\":\"" + channelNameOld + "\",\"id\":\"" + channelNameNew + "\",\"name\":\"" + channelNameNew + "\",\"forwardingAddress\":\"" + redirectionAddres + "\",\"queueName\":\"" + queueName + "\",\"staticConnectors\":[\"" + connector + "\"],\"isStarted\":true,\"parametersList\":[\n";
        for (int x = 0; x < channelNew[1].split("\\|").length; x++) {
            String param = "{\"disable\":false,\"deleteOff\":false,\"name\":\"" + channelNew[1].split("\\|")[x].split("~")[0] + "\",\"value\":\"" + channelNew[1].split("\\|")[x].split("~")[1] + "\"},";
            body2 = body2 + param;
        }
        body2 = body2.replaceAll(",$", "") + "]}";
        String body = body1 + body2;
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qme/bridges/").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при реактировании канала Artemis: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void createUserArtemisAPI(String cookies, String url, String userName, String userPassword, String userRoles) {
        String Body = "{\"username\":\"" + userName + "\",\"password\":\"" + userPassword + "\",\"roles\":[\"" + userRoles + "\"]}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/qme/security/user/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании юзера Artemis : " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }

    public void changeSettingConfigurationArtemisAPI(String cookies, String url, String maxDiskUsage, String globalMaxSize) {
        String Body = "{\"globalMaxSize\":\"" + globalMaxSize + "\",\"maxDiskUsage\":" + maxDiskUsage + "}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/qme/settings/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при изменении настроек конфигурации Artemis : " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("\"OK\"", response.getBody().asString());
    }
}


