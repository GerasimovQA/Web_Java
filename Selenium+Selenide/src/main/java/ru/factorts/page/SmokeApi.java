package ru.factorts.page;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigProperties;

import java.util.List;

public class SmokeApi extends Base {

    Api api = new Api();

    public void startAllModules(String url) {
        String Cookies = api.loginAPI(url, ConfigProperties.getTestProperty("LoginRoot69"), ConfigProperties.getTestProperty("PasswordRoot69"));
//        api.switchModuleAPI(Cookies, url, "factor-mq", "activate");
        api.switchModuleAPI(Cookies, url, "factor-qms", "activate");
        api.switchModuleAPI(Cookies, url, "factor-qme", "activate");
        api.switchModuleAPI(Cookies, url, "factor-broker", "activate");
        api.switchModuleAPI(Cookies, url, "factor-dashboard", "activate");
        api.switchModuleAPI(Cookies, url, "factor-rest", "activate");
        api.switchModuleAPI(Cookies, url, "factor-store", "activate");
        api.switchModuleAPI(Cookies, url, "factor-transaction-monitor", "activate");
        api.switchModuleAPI(Cookies, url, "factor-webservices", "activate");
    }

    public void checkStatusAllModulesAPI() {
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot69"), ConfigProperties.getTestProperty("PasswordRoot69"));
        RestAssured.baseURI = BaseUrl + "/manager/api/module/info/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.header("Cookie", Cook).get("");

        JsonPath jsonPathEvaluator = response.jsonPath();
        basePage.sout("Ответ в Json  - " + response.getBody().asString());
        List<Modules> modules = jsonPathEvaluator.getList("", Modules.class);

        for (Modules module : modules) {
            basePage.sout("Проверяем состояние модуля: " + module.name);
            switch (module.name) {
                case ("factor-broker"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{}, module.dependencies);
                    basePage.compareStringAndString("Брокер", module.label);
                    basePage.compareStringAndString("ru.factorts.module.broker.FactorBrokerModule", module.module);
                    basePage.compareStringAndString("factor-broker", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-mq"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{}, module.dependencies);
                    basePage.compareStringAndString("Менеджер очередей", module.label);
                    basePage.compareStringAndString("ru.factorts.module.mq.FactorMQModule", module.module);
                    basePage.compareStringAndString("factor-mq", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-qms"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{}, module.dependencies);
                    basePage.compareStringAndString("Мультименеджер очередей", module.label);
                    basePage.compareStringAndString("ru.factorts.module.qms.FactorQmsModule", module.module);
                    basePage.compareStringAndString("factor-qms", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-qme"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{}, module.dependencies);
                    basePage.compareStringAndString("Расширенный Менеджер очередей", module.label);
                    basePage.compareStringAndString("ru.factorts.module.qme.FactorQmeModule", module.module);
                    basePage.compareStringAndString("factor-qme", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-dashboard"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{"factor-broker"}, module.dependencies);
                    basePage.compareStringAndString("Dashboard", module.label);
                    basePage.compareStringAndString("ru.factorts.module.dashboard.FactorDashboardModule", module.module);
                    basePage.compareStringAndString("factor-dashboard", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-rest"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{"factor-broker"}, module.dependencies);
                    basePage.compareStringAndString("REST", module.label);
                    basePage.compareStringAndString("ru.factorts.module.rest.FactorRestModule", module.module);
                    basePage.compareStringAndString("factor-rest", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-store"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{}, module.dependencies);
                    basePage.compareStringAndString("База данных", module.label);
                    basePage.compareStringAndString("ru.factorts.module.store.FactorStoreModule", module.module);
                    basePage.compareStringAndString("factor-store", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-transaction-monitor"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{"factor-store", "factor-broker"}, module.dependencies);
                    basePage.compareStringAndString("Монитор транзакций", module.label);
                    basePage.compareStringAndString("ru.factorts.module.transaction.FactorTransactionModule", module.module);
                    basePage.compareStringAndString("factor-transaction-monitor", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                case ("factor-webservices"):
                    basePage.compareStringAndString("true", module.active);
                    basePage.compareArrayAndArray(new String[]{"factor-broker"}, module.dependencies);
                    basePage.compareStringAndString("Веб-сервисы", module.label);
                    basePage.compareStringAndString("ru.factorts.module.webservices.FactorWebservicesModule", module.module);
                    basePage.compareStringAndString("factor-webservices", module.name);
                    basePage.compareStringAndString("false", module.progress);
                    basePage.compareStringAndString("true", module.running);
                    break;
                default:
                    throw new AssertionError("Пропущена проверка состояния модулей");
            }
        }
    }
}




