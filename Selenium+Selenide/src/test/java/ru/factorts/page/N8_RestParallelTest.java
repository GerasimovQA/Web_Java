package ru.factorts.page;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N8_RestParallelTest extends Base {
    ClusterPage clusterPage = new ClusterPage();
    Api api = new Api();
    RestApi restApi = new RestApi();
    static Api staticApi = new Api();
    SOPSApi sopsApi = new SOPSApi();
    static SOPSApi staticSopsApi = new SOPSApi();
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();
    QueueManagerPage queueManagerPage;
    MessagePage messagePage = new MessagePage();
    ConfigurationServer configurationServer = new ConfigurationServer();
    static CommonComponents commonComponents = new CommonComponents();
    RestPage restPage = new RestPage();
    static BasePage staticBasePage = new BasePage();

    String nameDomainForRest = commonComponents.createIndividualName("domainForRest");
    String nameDomainForCancelEditRest = commonComponents.createIndividualName("domainForRest");
    String nameSopsForRest = commonComponents.createIndividualName("nameSopsForRest");
    String nameSopsForRest2 = commonComponents.createIndividualName("nameSopsForRest");

    String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLinkToSopsForRest2 = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLinkToSopsForCancelEditMethodRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLinkToSopsForCancelEditMethodRest2 = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLocalQoeueForRest = commonComponents.createIndividualName("nameLocalQoeueForRest");
    String transactionPolicy = "null";

    String serviceName = commonComponents.createIndividualName("serviceName");
    String servicePath = commonComponents.createIndividualName("servicePath");
    String serviceDescription = commonComponents.createIndividualName("serviceDescription");
    String serviceTypeDataRequest = "application/json";
    String serviceTypeDataAnswer = "";
    String methodTypeDataRequest = "application/json";
    String methodTypeDataAnswer = "application/json";
    String host = "0.0.0.0";
    String Cookies = null;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUpQueue() {
        Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void after() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
//        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
//            staticBasePage.executeBashCommand("sudo docker stop fesb-test-0-1");
//            staticBasePage.executeBashCommand("sudo docker stop fesb-test-0-2");
//        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void cancelCreateService() {
        String serviceTypeDataRequest = "application/json";
        String serviceTypeDataAnswer = "application/json";
        String bindingTypeOfService = "Без связывания";
        String domainName = testName.getMethodName();

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForEditService, false);
        restApi.startDomainRestAPI(Cookies, baseUrl(), domainID);

        restPage.cancelCreateService(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, bindingTypeOfService, serviceDescription);
        restPage.serviceShouldNotBeExist(domainName, serviceName);
        refresh();
        restPage.serviceShouldNotBeExist(domainName, serviceName);
    }

    @Test
    public void cancelDeleteService() {
        String domainName = testName.getMethodName();
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForCancelDeleteService, false);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.restartRest(Cookies);
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        restPage.cancelDeleteService(testName.getMethodName(), serviceName);
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        refresh();
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
    }

    @Test
    @Parameters(value = {
            "method_GeT_For_Cancel_Edit | GET | get/{Cancel_edit} | text/plain | | Описание для GET_Cancel_Edit | method_GeT_For_Cancel_Edit_New | GET | /get/{Cancel_edit}_New | application/json | Описание для GET_Cancel_Edit_New",
//            "method_PoST2_For_Edit | POST | post_edit | Описание для POST_Edit",
//            "method_PuT3_For_Edit | PUT | put_edit | Описание для PUT_Edit",
//            "method_DeLeTe_For_Edit | DELETE |    delete_edit | Описание для DELETE_Edit",
    })
    public void cancelEditMethod(String methodName, String method, String methodPath, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription, String methodNameNew, String methodNew, String methodPathNew, String methodTypeDataNew, String methodDescriptionNew) {
        String DomainIDForCancelEditpTest = sopsApi.createDomainAPI(Cookies, nameDomainForCancelEditRest);
        String domainName = testName.getMethodName();
//        String methodName_new = "";
//        String methodPath_new = "/get/{edit}_New";
//        String descriptionOfMethod_New = "";
//        String methodTypeDataNew = "application/json";
        String modelName1 = commonComponents.createIndividualName("modelName");
        String modelName2 = commonComponents.createIndividualName("modelName");
        String classlName1 = commonComponents.createIndividualName("ClasslName");
        String classlName2 = commonComponents.createIndividualName("ClasslName");
//        String[] point1 = null;
//        String[] point2 = null;
        String[] modelsData = null;
//        String bodyValue = null;
        String[] parameter1 = {};
        String[] parameter2 = {};
        String[] parameter1_New = {};
        String[] parameter2_New = {};
//        String parametrValue1 = null;
//        String parametrValue2 = null;
//        String nameBodyOfMethod = "Имя метода " + method;
        String descriptionParameterOfMethod = "Описание метода " + method;

        switch (method) {
            case "GET":
                String parameterName1 = "pararam1";
                String parameterName2 = "pararam2";
                String parameterType1 = "query";
                String parameterType2 = "path";
                String parameterDataType1 = "integer";
                String parameterDataType2 = "string";
                String parameterDescription1 = "Парарам1";
                String parameterDescription2 = "Парарам2";
                parameter1 = new String[]{parameterName1, parameterType1, parameterDataType1, parameterDescription1};
                parameter2 = new String[]{parameterName2, parameterType2, parameterDataType2, parameterDescription2};
                descriptionParameterOfMethod = "Описание метода " + method;
                String nameModelData1 = "Модель данных 1";


//                methodName_new = "method_GeT_For_Edit_New";
                String parameterName1_New = "pararam1_New";
                String parameterName2_New = "pararam2_New";
                String parameterType1_New = "path";
                String parameterType2_New = "query";
                String parameterDataType1_New = "string";
                String parameterDataType2_New = "integer";
                String parameterDescription1_New = "Парарам1_New";
                String parameterDescription2_New = "Парарам2_New";
                parameter1_New = new String[]{parameterName1_New, parameterType1_New, parameterDataType1_New, parameterDescription1_New};
                parameter2_New = new String[]{parameterName2_New, parameterType2_New, parameterDataType2_New, parameterDescription2_New};
//                descriptionOfMethod_New = "Описание метода " + method + "_New";

                break;
//            case "POST":
//            case "PUT":
//                bodyValue = "{\n" + "\"body\": \"" + method + "\"\n" + "}";
//                point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
//                point2 = new String[]{"Выход|Установить тело сообщения|" + method + " : ${body.getBody()}"};
//                modelsData = new String[]{classlName, modelName1, modelName2};
//                sopsPage = new SOPSPage();
//                sopsPage.createModelData(nameDomainForRest, modelName1, true, "Пример JSON файла", classlName, "/share/ModelsData/schemaForRest.json");
//                sopsPage.createModelData(nameDomainForRest, modelName2, true, "Пример JSON файла", classlName, "/share/ModelsData/schemaForRest.json");
//                break;
//            case "DELETE":
//                point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
//                point2 = new String[]{"Выход|Установить тело сообщения|" + method + " и что-нибудь еще"};
//                modelsData = new String[]{null, null};
//                break;
            default:
                throw new AssertionError("Пропущена подготовка тестовых данных");
        }

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

//        String idDomainForEdit = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainIDForCancelEditpTest, nameSopsForRest);
        String SopsID2 = sopsApi.generateSopsIdAPI(Cookies, DomainIDForCancelEditpTest, nameSopsForRest2);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, DomainIDForCancelEditpTest, SopsID, nameSopsForRest,
                nameLinkToSopsForCancelEditMethodRest, nameLocalQoeueForRest, transactionPolicy);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, DomainIDForCancelEditpTest, SopsID2, nameSopsForRest2,
                nameLinkToSopsForCancelEditMethodRest2, nameLocalQoeueForRest, transactionPolicy);

        String idFileModelData = sopsApi.uploadModeleAPI(Cookies, "/home/fesb/Stand/share/ModelsData/schemaForRest.json");
        sopsApi.createModelDataAPI(Cookies, DomainIDForCancelEditpTest, "JSON", modelName1, classlName1, true, idFileModelData);
        sopsApi.createModelDataAPI(Cookies, DomainIDForCancelEditpTest, "JSON", modelName2, classlName2, true, idFileModelData);
        sopsApi.startDomainAPI(Cookies, DomainIDForCancelEditpTest);
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForCancelEditMethod, false);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.createMethodGetWithTwoParameters(domainID, Cookies, methodName, serviceName, method, methodPath, methodDescription, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForCancelEditMethodRest, parameter1, parameter2);
        restApi.restartRest(Cookies);

        restPage.cancelEditMetod(domainName, serviceName, methodDescription, methodNameNew, method, methodPathNew, nameSopsForRest2,
                methodTypeDataNew, modelName2, methodDescriptionNew, parameter1_New, parameter2_New, modelsData);

        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataRequest, methodTypeDataAnswer, methodDescription);
        refresh();
        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataRequest, methodTypeDataAnswer, methodDescription);
        restPage.checkMethodParameters(domainName, serviceName, methodDescription, methodName, method, methodPath, nameSopsForRest,
                methodTypeDataRequest, modelName1, methodDescription, parameter1, parameter2, modelsData);
    }

    @Test
    public void cancelDeleteMethod() {
        String method = "GET";
        String methodName = "method_GeT_For_Delete";
        String methodPath = "get/{Delete}";
        String methodDescription = "Описание для GET_Delete";
        String methodTypeDataRequest = "text/plain";
        String methodTypeDataAnswer = "";
        String domainName = testName.getMethodName();
        String modelName1 = commonComponents.createIndividualName("modelName");
        String modelName2 = commonComponents.createIndividualName("modelName");
        String classlName1 = commonComponents.createIndividualName("ClasslName");
        String classlName2 = commonComponents.createIndividualName("ClasslName");
        String[] parameter1 = {};
        String[] parameter2 = {};
        String parameterName1 = "pararam1";
        String parameterName2 = "pararam2";
        String parameterType1 = "query";
        String parameterType2 = "path";
        String parameterDataType1 = "integer";
        String parameterDataType2 = "string";
        String parameterDescription1 = "Парарам1";
        String parameterDescription2 = "Парарам2";
        parameter1 = new String[]{parameterName1, parameterType1, parameterDataType1, parameterDescription1};
        parameter2 = new String[]{parameterName2, parameterType2, parameterDataType2, parameterDescription2};

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String idDomainForEdit = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest);
        String SopsID2 = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest2);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID, nameSopsForRest,
                nameLinkToSopsForRest, nameLocalQoeueForRest, transactionPolicy);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID2, nameSopsForRest2,
                nameLinkToSopsForRest2, nameLocalQoeueForRest, transactionPolicy);

        String idFileModelData = sopsApi.uploadModeleAPI(Cookies, "/home/fesb/Stand/share/ModelsData/schemaForRest.json");
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName1, classlName1, true, idFileModelData);
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName2, classlName2, true, idFileModelData);
        sopsApi.startDomainAPI(Cookies, idDomainForEdit);
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForCancelDeleteMethod, false);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.createMethodGetWithTwoParameters(domainID, Cookies, methodName, serviceName, method, methodPath, methodDescription, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest, parameter1, parameter2);
        restApi.restartRest(Cookies);

        restPage.cancelDeleteMehtod(domainName, serviceName, methodDescription);
        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataRequest, methodTypeDataAnswer, methodDescription);
        refresh();
        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataRequest, methodTypeDataAnswer, methodDescription);
    }

    @Test
    public void cancelCreateAddConfiguration() {
        String nameOfAdditionalConfiguration = "Cancel_Create_CoNfIg_For_HTTP_Auth";
        String typeOfAdditionalConfiguration = "Аутентификация HTTP (Локальные пользователи)";
        String[] user1 = {"name1", "password1", "role1"};
        String[][] usersAll = {user1};

        String[] role1 = {"путь", "true", "role1"};
        String[][] rolesAll = {role1};
        restPage.cancelCreateAddConfiguration(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);
        refresh();
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);
    }
}
