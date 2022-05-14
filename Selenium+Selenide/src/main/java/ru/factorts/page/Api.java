package ru.factorts.page;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.junit.Assert;
import utils.ConfigProperties;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Api extends Base {

    public enum TypeAppender {SIZE, DAY}

    public String loginAPI(String username, String password) {
        Response response = RestAssured.given().log().all().
                contentType("application/x-www-form-urlencoded").
                given().
                param("username", username).
                param("password", password).
                baseUri(BaseUrl).basePath("/manager/login/").
                when().post().
                then().extract().response();

        Headers allHeaders = response.getHeaders();
        basePage.sout("allHeaders: " + allHeaders);

        Map<String, String> allCookies = response.getCookies();
        String Cookies = allCookies.toString().replaceAll("[{}]", "");
        basePage.sout("Полный ответ кук: " + allCookies.toString());
        basePage.sout("allCookies: " + Cookies);

        basePage.sout("Ответ при авторизации: " + response.getBody().asString());
        basePage.sout("Полученные куки: " + Cookies + "\n\n");
        Assert.assertNotEquals(" ", response.getBody().asString());
        return Cookies;
    }

    public String loginAPI(String baseUrl, String username, String password) {
        Response response = RestAssured.given().log().all().
                contentType("application/x-www-form-urlencoded").
                given().
                param("username", username).
                param("password", password).
                baseUri(baseUrl).basePath("/manager/login/").
                when().post().
                then().extract().response();

        Headers allHeaders = response.getHeaders();
        basePage.sout("allHeaders: " + allHeaders);

        Map<String, String> allCookies = response.getCookies();
        String Cookies = allCookies.toString().replaceAll("[{}]", "");
        basePage.sout("Полный ответ кук: " + allCookies.toString());
        basePage.sout("allCookies: " + Cookies);

        basePage.sout("Ответ при авторизации: " + response.getBody().asString());
        basePage.sout("Полученные куки: " + Cookies + "\n\n");
        Assert.assertNotEquals(" ", response.getBody().asString());
        return Cookies;
    }

    public void addRemoteServerAPI(String Cookies, String NameOfServer, String HostOfServer, String LoginOfServer, String PassworOfServer) {
        String Body = "{\"name\":\"" + NameOfServer + "\",\"url\":\"" + HostOfServer + "\"," +
                "\"username\":\"" + LoginOfServer + "\",\"password\":\"" + PassworOfServer + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/remote/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании удаленного сервера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void changeSettingAutorizationAPI(String Cookies, String NumTryEnter, String PasswordPolicy) {
        String Body = " {\"maxAttempts\":\"" + NumTryEnter + "\",\" policy\":\"" + PasswordPolicy + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/security/settings/").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после изменения настроек авторизации: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public String postMessageToHttpApi(String message, String path) {
        String[] pathEnd = path.split("/");
        String[] onlyIPOfBaseUrl = BaseUrl.split(":");

        Response response =
                given().log().all().
                        contentType(ContentType.JSON).
                        body(message).
                        baseUri(path).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после отправки сообщения на http:" + path + "/" + pathEnd[1] + ": " + response.getBody().asString() + "\n\n");
        return response.getBody().asString();
    }

    public void postMessageToHttpApi(String Cookies, String message, String port, String path, String login, String password) {
        String[] pathEnd = path.split("/");
        String[] onlyIPOfBaseUrl = BaseUrl.split(":");

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        auth().basic(login, password).
                        contentType(ContentType.JSON).
                        body(message).
                        baseUri("http:" + onlyIPOfBaseUrl[1] + port).basePath(pathEnd[1]).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после отправки сообщения на http:" + onlyIPOfBaseUrl[1] + port + "/" + pathEnd[1] + ": " + response.getBody().asString() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void postMessageToHttpApiWithCheckAnswer(String Cookies, String message, String port, String path, String login, String password, String answer) {
        String[] pathEnd = path.split("/");
        String[] onlyIPOfBaseUrl = BaseUrl.split(":");

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        auth().basic(login, password).
                        contentType(ContentType.JSON).
                        body(message).
                        baseUri("http:" + onlyIPOfBaseUrl[1] + port).basePath(pathEnd[1]).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после отправки сообщения на http:" + onlyIPOfBaseUrl[1] + port + "/" + pathEnd[1] + ": " + response.getBody().asString() + "\n\n");
        Assert.assertEquals(answer, response.getBody().asString());
    }

    public void createAppenderAPI(String Cookies, String name, TypeAppender typeAppender, String file, String fileSize, String maxHistory, String pattern, String totalFileSize) {
        String Body = null;
        if (typeAppender.equals(TypeAppender.SIZE)) {
            Body = "{\"name\":\"" + name + "\",\"type\":\"" + typeAppender + "\",\"file\":\"" + file + "\",\"fileSize\":\"" + fileSize + "\"," +
                    "\"maxHistory\":\"" + maxHistory + "\",\"pattern\":\"" + pattern + "\",\"appenderRefs\":[]}";
        } else {
            Body = "{\"name\":\"" + name + "\",\"type\":\"" + typeAppender + "\",\"file\":\"" + file + "\",\"fileSize\":\"" + fileSize + "\"," +
                    "\"totalFileSize\":\"" + totalFileSize + "\",\"maxHistory\":\"" + maxHistory + "\",\"pattern\":\"" + pattern + "\",\"appenderRefs\":[]}";
        }

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/settings/log/appender").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ создании аппендера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createLoggerAPI(String Cookies, String id, String nameAppender, String nameLogger, String level, boolean additivity) {
        String Body = "{\"id\":\"" + id + "\",\"name\":\"" + nameLogger + "\",\"level\":\"" + level + "\",\"additivity\":" + additivity + "," +
                "\"appenderRefs\":[{\"label\":\"" + nameAppender + "\",\"value\":\"" + nameAppender + "\",\"name\":\"" + nameAppender + "\"}]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/settings/log/logger").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ создании логгера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createInterceptionAPI(String Cookies, String id, String className, String regex, String replace) {
        String Body = "{\"id\":\"" + id + "\",\"className\":\"" + className + "\",\"regex\":\"" + regex + "\",\"replace\":\"" + replace + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/settings/log/filters").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ создании перехвата: " + response.getBody().asString() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void editInterceptionAPI(String Cookies) {
        String Body = "{\"id\":\"aslknzxclkznxck\",\"className\":\"\",\"regex\":\"\",\"replace\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/settings/log/filters").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании перехвата: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void checkStatusAllModulesAPI(String cook, String url, String factor_broker, String factor_qms, String factor_qme, String factor_dashboard, String factor_rest, String factor_store, String factor_transaction_monitor, String factor_webservices) {
//        String Cook = loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        RestAssured.baseURI = url + "/manager/api/module/info";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header("Cookie", cook).get("");

        JsonPath jsonPathEvaluator = response.jsonPath();

        List<Modules> modules = jsonPathEvaluator.getList("", Modules.class);
        for (Modules module : modules) {
            System.out.println(module.name + "-" + module.active + "-" + module.running);
        }

        for (Modules module : modules) {
            Assert.assertNotNull(module.active);
            Assert.assertNotNull(module.dependencies);
            Assert.assertNotNull(module.label);
            Assert.assertNotNull(module.module);
            Assert.assertNotNull(module.name);
            Assert.assertNotNull(module.progress);
            Assert.assertNotNull(module.running);

            basePage.sout("Проверяем состояние модуля: " + module.name);
            switch (module.name) {
//                case ("factor-mq"):
//                    basePage.compareStringAndString(factor_mq.split("\\|")[0], module.active);
//                    basePage.compareStringAndString(factor_mq.split("\\|")[1], module.running);
//                    break;
                case ("factor-broker"):
                    basePage.compareStringAndString(factor_broker.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_broker.split("\\|")[1], module.running);
                    break;
                case ("factor-qms"):
                    basePage.compareStringAndString(factor_qms.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_qms.split("\\|")[1], module.running);
                    break;
                case ("factor-qme"):
                    basePage.compareStringAndString(factor_qme.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_qme.split("\\|")[1], module.running);
                    break;
                case ("factor-dashboard"):
                    basePage.compareStringAndString(factor_dashboard.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_dashboard.split("\\|")[1], module.running);
                    break;
                case ("factor-rest"):
                    basePage.compareStringAndString(factor_rest.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_rest.split("\\|")[1], module.running);
                    break;
                case ("factor-store"):
                    basePage.compareStringAndString(factor_store.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_store.split("\\|")[1], module.running);
                    break;
                case ("factor-transaction-monitor"):
                    basePage.compareStringAndString(factor_transaction_monitor.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_transaction_monitor.split("\\|")[1], module.running);
                    break;
                case ("factor-webservices"):
                    basePage.compareStringAndString(factor_webservices.split("\\|")[0], module.active);
                    basePage.compareStringAndString(factor_webservices.split("\\|")[1], module.running);
                    break;
                default:
                    throw new AssertionError("Пропущена проверка состояния модулей");
            }
        }
    }

    public void switchModuleAPI(String Cookies, String url, String service, String action) {
        String Body = "";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/module/" + service + "/" + action).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при изменении состояния модуля: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("true", response.getBody().asString());
    }

    public void changeSettingWrapperAPI(String Cookies, String url, String maxMemory, String shutdownTimeout, String jvmExitTimeout, String pingTimeout, String pingInterval) {
        String Body = "{\"maxMemory\": \"" + maxMemory + "\",\"shutdownTimeout\": " + shutdownTimeout +
                ", \"jvmExitTimeout\": " + jvmExitTimeout + ", \"pingTimeout\": " + pingTimeout + ", \"pingInterval\": " + pingInterval + "}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/settings/wrapper").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании настроек враппера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void changeSettingWrapper199API(String Cookies, String url, String maxMemory) {
        String Body = "{\"maxmemory\": \"" + maxMemory + "\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/settings/wrapper").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при редактировании настроек враппера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createBdAPI(String Cookies, String nameBd, String portBd) {
        String Body = "{\"model\":{\"inMemoryMode\":false,\"partitions\":[{\"name\":\"default\",\"factorUser\":{\"username\":\"factor\",\"admin\":true,\"perms\":\"ALL\"},\"users\":[]}]},\"localValues\":{\"name\":\"" + nameBd + "\",\"useCustomLocation\":false,\"id\":\"\",\"port\":\"" + portBd + "\"},\"id\":\"\",\"name\":\"" + nameBd + "\",\"port\":\"" + portBd + "\",\"inMemoryMode\":false,\"useCustomLocation\":false,\"partitions\":[{\"name\":\"default\",\"factorUser\":{\"username\":\"factor\",\"admin\":true,\"perms\":\"ALL\"},\"users\":[]}]}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(BaseUrl).basePath("/manager/api/store/instance").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании БД: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    public void deleteQueueAPI(String url, String Cookies, String nameQueue) {
        String body = "{\"name\":\"" + nameQueue + "\"}";
        String basePath = "/manager/api/store/instance";
//        String waitingResponse = "id";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains(waitingResponse));
    }

    public void startAllModules(String url) {
        String Cookies = loginAPI(url, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        switchModuleAPI(Cookies, url, "factor-mq", "activate");
        switchModuleAPI(Cookies, url, "factor-qme", "activate");
        switchModuleAPI(Cookies, url, "factor-broker", "activate");
        switchModuleAPI(Cookies, url, "factor-dashboard", "activate");
        switchModuleAPI(Cookies, url, "factor-rest", "activate");
        switchModuleAPI(Cookies, url, "factor-store", "activate");
        switchModuleAPI(Cookies, url, "factor-transaction-monitor", "activate");
        switchModuleAPI(Cookies, url, "factor-webservices", "activate");
    }

    public String getVerFromGitlabAPI(String customer) {
        String token = "7mkJGk3wF8jZfZ6G_CPm";
        GitLabApi gitLabApi = new GitLabApi("http://192.168.57.230", token);
        RepositoryFile file = null;
        try {
            file = gitLabApi.getRepositoryFileApi().getFile("README.md", 52, "master");
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }
        String content = file.getDecodedContentAsString();
        String version = content.split(customer)[1].split(";")[0];
        return version.trim();
    }
}

