package ru.factorts.page;

import com.codeborne.selenide.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N8_RestSingleTest extends Base {


    RestApi restApi = new RestApi();
    static Api staticApi = new Api();
    SOPSApi sopsApi = new SOPSApi();
    static CommonComponents commonComponents = new CommonComponents();
    RestPage restPage = new RestPage();
    static RestPage staticRestPage = new RestPage();
    SOPSPage sopsPage;
    CreationSOPSPage creationSOPSPage = new CreationSOPSPage("empty");
    static BasePage staticBasePage = new BasePage();

    String nameDomainForRest = commonComponents.createIndividualName("domainForRest");
    String nameSopsForRest = commonComponents.createIndividualName("nameSopsForRest");
    String nameSopsForRest2 = commonComponents.createIndividualName("nameSopsForRest");
    String getNameLinkToSopsForRest2 = commonComponents.createIndividualName("nameSopsForRest");
    String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLinkToSopsForRest2 = commonComponents.createIndividualName("nameLinkToSopsForRest");
    String nameLocalQoeueForRest = commonComponents.createIndividualName("nameLocalQoeueForRest");
    String transactionPolicy = "null";
    String serviceName = commonComponents.createIndividualName("serviceName");
    String methodName = commonComponents.createIndividualName("methodName");
    String servicePath = commonComponents.createIndividualName("servicePath");
    String serviceDescription = commonComponents.createIndividualName("serviceDescription");
    String bindingTypeOfService = "Без связывания";
    String serviceTypeDataRequest = "text/plain";
    String serviceTypeDataAnswer = "application/json";
    String methodTypeDataReqest = "text/plain";
    String methodTypeDataAnswer = "text/plain";
    String methodTypeDataRequest = "application/json";
    String Cookies = null;
    String host = baseUrl().split("//")[1].split(":")[0];
    static String staticHost = baseUrl().split("//")[1].split(":")[0];




    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        staticRestPage.settingRestDomain("REST", staticHost);
        basePage.logout();
        Selenide.closeWebDriver();
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        basePage.sout("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
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
        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook,baseUrl(),"true|true", "true|true", "false|false", "false|false", "true|true", "false|false", "false|false", "false|false");

        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void createService() {
        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainIDForStopTest = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainIDForStopTest, nameSopsForRest);
        String domainName = testName.getMethodName();

        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, DomainIDForStopTest, SopsID, nameSopsForRest, nameLinkToSopsForRest, nameLocalQoeueForRest, transactionPolicy);
        restPage.successfulCreateDomain(domainName, domainName, host, portForCreateServiceRest, false);
        restPage.runDomainRest(domainName);
        restPage.successfulCreateService(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, bindingTypeOfService, serviceDescription);
        restPage.serviceCheckAllParameters(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Без связывания", serviceDescription);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        refresh();
        restPage.serviceCheckAllParameters(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Без связывания", serviceDescription);
    }

    @Test
    public void editService() {
        String serviceNameNew = "serviceNameNew";
        String servicePathNew = "servicePathNew";
        String serviceDescriptionNew = "serviceDescriptionNew";
        String serviceTypeDataNew = "serviceTypeDataNew";
        String domainName = testName.getMethodName();

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForEditService, false);
        restApi.startDomainRestAPI(Cookies, baseUrl(), domainID);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.restartRest(Cookies);
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        restPage.successfulEditService(testName.getMethodName(), serviceName, serviceNameNew, servicePathNew, serviceTypeDataNew, serviceDescriptionNew);
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceNameNew, servicePathNew, serviceTypeDataNew, serviceTypeDataAnswer, "Автоматический", serviceDescriptionNew);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        refresh();
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceNameNew, servicePathNew, serviceTypeDataNew, serviceTypeDataAnswer, "Автоматический", serviceDescriptionNew);
    }

    @Test
    public void cancelEditService() {
        String serviceNameNew = "serviceNameNew";
        String servicePathNew = "servicePathNew";
        String serviceDescriptionNew = "serviceDescriptionNew";
        String serviceTypeDataNew = "serviceTypeDataNew";
        String domainName = testName.getMethodName();

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForCancelEditService, false);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.restartRest(Cookies);
        restPage.serviceCheckAllParameters(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        restPage.cancelEditService(domainName, serviceName, serviceNameNew, servicePathNew, serviceTypeDataNew, serviceDescriptionNew);
        restPage.serviceCheckAllParameters(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        refresh();
        restPage.serviceCheckAllParameters(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
    }

    @Test
    public void deleteService() {
        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainName = testName.getMethodName();

        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForDeleteService, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.restartRest(Cookies);
        restPage.serviceCheckAllParameters(testName.getMethodName(), serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, "Автоматический", serviceDescription);
        restPage.successfulDeleteService(testName.getMethodName(), serviceName);
        restPage.serviceShouldNotBeExist(testName.getMethodName(), serviceName);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.serviceShouldNotBeExist(testName.getMethodName(), serviceName);
    }

    @Test
    @Parameters(value = {
            "method_GeT1| GET | get/{a2}  | Описание для GET",
            "method_PoST2| POST | post | Описание для POST",
            "method_PuT3| PUT | put | Описание для PUT",
            "method_DeLeTe4| DELETE | delete | Описание для DELETE",
    })
    public void createMethodRest(String methodName, String method, String methodPath, String methodDescription) {
        String modelName1 = commonComponents.createIndividualName("modelName");
        String modelName2 = commonComponents.createIndividualName("modelName");
        String classlName = commonComponents.createIndividualName("ClasslName");
        String[] point1 = null;
        String[] point2 = null;
        String[] modelsData = null;
        String bodyValue = null;
        String[] parameter1 = {};
        String[] parameter2 = {};
        String parametrValue1 = null;
        String parametrValue2 = null;
        String nameBodyOfMethod = "Имя метода " + method;
        String descriptionParameterOfMethod = "Описание метода " + method;
        String domainName = commonComponents.createIndividualName(testName.getMethodName().split("\\|")[0]);

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        switch (method) {
            case "GET":
                String parametrName1 = "a1";
                String parametrName2 = "a2";
                parametrValue1 = "123";
                parametrValue2 = "Test 2";
                parameter1 = new String[]{parametrName1, "query", "integer", "description1"};
                parameter2 = new String[]{parametrName2, "path", "string", "description2"};
                descriptionParameterOfMethod = "Описание метода " + method;
                point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
                point2 = new String[]{"Выход|Установить тело сообщения|" + method + " :  ${headers." + parametrName1 + "} ${headers." + parametrName2 + "}"};
                modelsData = new String[]{null, null};
                break;
            case "POST":
            case "PUT":
                bodyValue = "{\n" + "\"body\": \"" + method + "\"\n" + "}";
                point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
                point2 = new String[]{"Выход|Установить тело сообщения|" + method + " : ${body.getBody()}"};
                modelsData = new String[]{classlName, modelName1, modelName2};
                sopsPage = new SOPSPage();
                sopsPage.createModelData(nameDomainForRest, modelName1, true, "Пример JSON файла", classlName, "/share/ModelsData/schemaForRest.json");
                sopsPage.createModelData(nameDomainForRest, modelName2, true, "Пример JSON файла", classlName, "/share/ModelsData/schemaForRest.json");
                break;
            case "DELETE":
                point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
                point2 = new String[]{"Выход|Установить тело сообщения|" + method + " и что-нибудь еще"};
                modelsData = new String[]{null, null};
                break;
            default:
                throw new AssertionError("Пропущена подготовка тестовых данных");
        }
        String[][] pointAll = {point1, point2};

        creationSOPSPage.createSOPS(nameDomainForRest, nameSopsForRest, pointAll);
        sopsPage = new SOPSPage();

        sopsPage.startDomain(nameDomainForRest);

        String domainID = null;
        switch (method) {
            case "GET":
                domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCreateMethodRestGet, false);
                break;
            case "POST":
                domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCreateMethodRestPost, false);
                break;
            case "PUT":
                domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCreateMethodRestPut, false);
                break;
            case "DELETE":
                domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCreateMethodRestDelete, false);
                break;
            default:
                throw new AssertionError("Пропущена подготовка тестовых данных");
        }
        restPage.runDomainRest(domainName);
        staticRestPage.settingRestDomain(domainName, staticHost);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.restartRest(Cookies);

        restPage.successfulCreateMethod(domainName, servicePath, methodName, method, methodPath, nameSopsForRest, methodTypeDataReqest, methodTypeDataAnswer, methodDescription, parameter1, parameter2, modelsData, descriptionParameterOfMethod);

        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataReqest, methodTypeDataAnswer, methodDescription);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.methodCheckAllParameters(domainName, serviceName, methodName, method, methodPath, nameSopsForRest, methodTypeDataReqest, methodTypeDataAnswer, methodDescription);

        restPage.checkSwagger(domainName, serviceName, servicePath, method, methodPath, methodTypeDataReqest, methodTypeDataAnswer, parametrValue1, parametrValue2, bodyValue);
    }

    @Test
    @Parameters(value = {"method_GeT_For_Edit | GET | get/{edit} | text/plain | application/json | Описание для GET_Edit | method_GeT_For_Edit_New | GET | get/{edit}_New |" + " application/json | Описание для GET_Edit_New",
//            "method_PoST2_For_Edit | POST | /post_edit | Описание для POST_Edit",
//            "method_PuT3_For_Edit | PUT | /put_edit | Описание для PUT_Edit",
//            "method_DeLeTe_For_Edit | DELETE | /delete_edit | Описание для DELETE_Edit",
    })
    public void editMethod(String methodName, String method, String methodPath, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription, String methodNameNew, String methodNew, String methodPathNew, String methodTypeDataNew, String methodDescriptionNew) {
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
        String domainName = commonComponents.createIndividualName(testName.getMethodName().split("\\|")[0]);

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

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String idDomainForEdit = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest);
        String SopsID2 = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest2);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID, nameSopsForRest, nameLinkToSopsForRest, nameLocalQoeueForRest, transactionPolicy);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID2, nameSopsForRest2, nameLinkToSopsForRest2, nameLocalQoeueForRest, transactionPolicy);

        String idFileModelData = sopsApi.uploadModeleAPI(Cookies, "/home/fesb/Stand/share/ModelsData/schemaForRest.json");
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName1, classlName1, true, idFileModelData);
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName2, classlName2, true, idFileModelData);
        sopsApi.startDomainAPI(Cookies, idDomainForEdit);

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForEditMethod, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.createMethodGetWithTwoParameters(domainID, Cookies, methodName, serviceName, method, methodPath, methodDescription, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest, parameter1, parameter2);
        restApi.restartRest(Cookies);

        restPage.successfulEditMethod(domainName, serviceName, methodDescription, methodNameNew, method, methodPathNew, nameSopsForRest2, methodTypeDataNew, modelName2, methodDescriptionNew, parameter1_New, parameter2_New, modelsData);

        restPage.methodCheckAllParameters(domainName, serviceName, methodNameNew, method, methodPathNew, nameSopsForRest2, methodTypeDataNew, methodTypeDataAnswer, methodDescriptionNew);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.methodCheckAllParameters(domainName, serviceName, methodNameNew, method, methodPathNew, nameSopsForRest2, methodTypeDataNew, methodTypeDataAnswer, methodDescriptionNew);
        restPage.checkMethodParameters(domainName, serviceName, methodDescription, methodNameNew, method, methodPathNew, nameSopsForRest2, methodTypeDataNew, modelName2, methodDescriptionNew, parameter1_New, parameter2_New, modelsData);
    }

    @Test
    public void deleteMethod() {
        String method = "GET";
        String methodName = "method_GeT_For_Delete";
        String methodPath = "/get/{Delete}";
        String methodDescription = "Описание для GET_Delete";
        String methodTypeDataRequest = "text/plain";
        String methodTypeDataAnswer = "application/json";
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
        String domainName = testName.getMethodName();
        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String idDomainForEdit = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest);
        String SopsID2 = sopsApi.generateSopsIdAPI(Cookies, idDomainForEdit, nameSopsForRest2);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID, nameSopsForRest, nameLinkToSopsForRest, nameLocalQoeueForRest, transactionPolicy);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, idDomainForEdit, SopsID2, nameSopsForRest2, nameLinkToSopsForRest2, nameLocalQoeueForRest, transactionPolicy);

        String idFileModelData = sopsApi.uploadModeleAPI(Cookies, "/home/fesb/Stand/share/ModelsData/schemaForRest.json");
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName1, classlName1, true, idFileModelData);
        sopsApi.createModelDataAPI(Cookies, idDomainForEdit, "JSON", modelName2, classlName2, true, idFileModelData);
        sopsApi.startDomainAPI(Cookies, idDomainForEdit);
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForDeleteMethod, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.createMethodGetWithTwoParameters(domainID, Cookies, methodName, serviceName, method, methodPath, methodDescription, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest, parameter1, parameter2);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.successfulDeleteMethod(domainName, serviceName, methodDescription);
        restPage.methodShouldNotBeExist(domainName, serviceName, methodName);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.methodShouldNotBeExist(domainName, serviceName, methodName);
    }

    @Test
    public void zCreateAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Create_HTTP_Auth";
        String typeOfAdditionalConfiguration = "Аутентификация HTTP (Локальные пользователи)";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"/" + domainName + "/" + servicePath + methodPath3, "false", "role3"};
        String[][] rolesAll = {role1, role2, role3};

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String[] point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
        String[] point2 = new String[]{"Выход|Установить тело сообщения|" + method + " test"};
        String[][] pointAll = {point1, point2};

        creationSOPSPage.createSOPS(nameDomainForRest, nameSopsForRest, pointAll);
        sopsPage = new SOPSPage();
        sopsPage.startDomain(nameDomainForRest);
        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCreateAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 4; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.restartRest(Cookies);
        restPage.successCreateAddConfiguration(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);

        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);

        restPage.setAutentificationInRestSetting(domainName, nameOfAdditionalConfiguration);
        restApi.restartRest(Cookies);

        //Проверка доступа к авторизированному URL без ввода логина/пароля
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareStringAndElementTextMustBeNotEqual("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL с вводом корректных логина/пароля (у пользователя одна роль)
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1, "", "name1", "password1");
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL с вводом НЕкорректных логина/пароля (у пользователя одна роль)
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2, "", "name1", "password1");
        basePage.compareStringAndElementText("HTTP ERROR 403 Forbidden\n" + "URI: /" + domainName + "/" + servicePath + methodPath2 + "\n" + "STATUS: 403\n" + "MESSAGE: Forbidden\n" + "SERVLET: -\n" + "Powered by Jetty:// 9.4.41.v20210516", basePage.bodyPage);

        //Проверка доступа к НЕавторизированному URL без ввода логина/пароля (у пользователя одна роль)
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL с вводом корректных логина/пароля (у пользователя несколько ролей)
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1, "", "name4", "password4");
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL с вводом НЕкорректных логина/пароля (у пользователя несколько ролей)
        open(baseUrl().replace(":8181", ":" + portForCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2, "", "name4", "password4");
        basePage.compareStringAndElementText("HTTP ERROR 403 Forbidden\n" + "URI: /" + domainName + "/" + servicePath + methodPath2 + "\n" + "STATUS: 403\n" + "MESSAGE: Forbidden\n" + "SERVLET: -\n" + "Powered by Jetty:// 9.4.41.v20210516", basePage.bodyPage);
    }

    @Test
    public void zCancelCreateAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Cancel_Create_HTTP_Auth";
        String typeOfAdditionalConfiguration = "Аутентификация HTTP (Локальные пользователи)";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"/" + domainName + "/" + servicePath + methodPath3, "false", "role3"};
        String[][] rolesAll = {role1, role2, role3};

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String[] point1 = new String[]{"Вход|Ссылка на СОПС|" + nameLinkToSopsForRest, "чек-бокс|Глобальная|вкл"};
        String[] point2 = new String[]{"Выход|Установить тело сообщения|" + method + " test"};
        String[][] pointAll = {point1, point2};

        creationSOPSPage.createSOPS(nameDomainForRest, nameSopsForRest, pointAll);
        sopsPage = new SOPSPage();
        sopsPage.startDomain(nameDomainForRest);
        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCancelCreateAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 4; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.restartRest(Cookies);
        restPage.cancelCreateAddConfiguration(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);

        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);
        refresh();
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);

//        basePage.click(basePage.restTab);
//        basePage.click(staticRestPage.restSettingTab);
//        basePage.click(staticRestPage.autentificationActivate);
//        basePage.click(staticRestPage.autentificationClearButton);
//        basePage.click(basePage.saveButton);
//        basePage.restartModule();

        open(baseUrl().replace(":8181", ":" + portForCancelCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelCreateAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
    }


    @Test
    public void zEditAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodPath4 = "/GET/get4";
        String methodPath5 = "/GET/get5";
        String methodPath6 = "/GET/get6";
        String methodPath7 = "/GET/get7";
        String methodPath8 = "/GET/get8";
        String methodPath9 = "/GET/get9";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Edit_HTTP_Auth";
        String nameOfAdditionalConfiguration_New = "CoNfIg_For_Edit_HTTP_Auth_New";
        String typeOfAdditionalConfiguration = "LOCAL_USERS";
        String typeOfAutentificationNew = "Digest";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"GET", "/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"POST", "/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"ANY", "/" + domainName + "/" + servicePath + methodPath3, "true", "role3"};
        String[] role4 = {"GET", "/" + domainName + "/" + servicePath + methodPath3, "false"};
        String[] role5 = {"GET", "/" + domainName + "/" + servicePath + methodPath4, "true", "role1"};
        String[][] rolesAll = {role1, role2, role3, role4, role5};


        String[] user1_New = {"name1_New", "password1_New", "role1_New"};
        String[] user2_New = {"name2_New", "password2_New", "role2_New"};
        String[] user3_New = {"name3_New", "password3_New", "role3_New"};
        String[] user4_New = {"name4_New", "password4_New", "role4_New, role5_New"};
        String[][] usersAll_New = {user1_New, user2_New, user3_New, user4_New};

        String[] role1_New = {"GET", "/" + domainName + "/" + servicePath + methodPath5, "true", "role1_New"};
        String[] role2_New = {"GET", "/" + domainName + "/" + servicePath + methodPath6, "false", "role2_New"};
        String[] role3_New = {"GET", "/" + domainName + "/" + servicePath + methodPath7, "true", "role3_New"};
        String[] role4_New = {"GET", "/" + domainName + "/" + servicePath + methodPath8, "true", "role3_New, role4_New"};
        String[] role5_New = {"GET", "/" + domainName + "/" + servicePath + methodPath9, "true", "role5_New"};
        String[][] rolesAll_New = {role1_New, role2_New, role3_New, role4_New, role5_New};

//        Selenide.closeWindow();
//        if (!System.getProperty("os.name").equals("Windows 10")) {
//            Selenide.closeWebDriver();
//            Configuration.proxyEnabled = true;
//            Configuration.fileDownload = FileDownloadMode.PROXY;
//            Configuration.proxyHost = ClientUtil.getConnectableAddress().getHostAddress();
//        }
//        openPage(baseUrl());

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String idDomainForEditConfiguration = sopsApi.createDomainAPI(Cookies, nameDomainForRest);

        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEditConfiguration, nameSopsForRest);
        String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
        String nameQueueForRest = commonComponents.createIndividualName("nameQueueForRest");
        String guid = commonComponents.createIndividualName("guid");
        String bodyMessage = "GET test";

        String pointIn1 = "{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameLinkToSopsForRest + "\",\"fullUri\":\"direct-vm://" + nameLinkToSopsForRest + "\",\"isGlobal\":true,\"routeName\":null,\"routeId\":null,\"domainGuid\":null,\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}";

        String pointOut1 = "{\"name\":\"Установить тело сообщения\",\"icon\":\"fa fa-cog\",\"group\":\"Обработка\",\"guid\":\"" + guid + "\",\"componentName\":\"SetBodyEndpoint\",\"properties\":[],\"domain\":\"" + idDomainForEditConfiguration + "\",\"customName\":\"Установить тело сообщения\",\"body\":\"" + bodyMessage + "\"}";

        String pointOut2 = "{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[{\"nameRu\":\"Отключить синхронный ответ JMS-получателю\",\"defaultValue\":false,\"description\":\"В значении true будет работать как InOnly обмен с исключением в виде того, что JMS адрес для ответа заголовок выслан и не запрещен как в случае InOnly . Производитель не ждет ответа. Потребитель с этим параметром будет работать как InOnly. Может использоваться для привязки InOut запросов одной очереди к другой очереди так что последняя очередь отправит ответ обратно к оригинальному JMS адрес для ответа .\",\"nameEn\":\"disableReplyTo\",\"type\":\"BOOL\",\"value\":true,\"configValues\":[]}],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameQueueForRest + "\",\"fullUri\":\"localmq://" + nameQueueForRest + "?disableReplyTo=true\",\"sla\":{},\"isWireTap\":false,\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\",\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"domain\":\"" + idDomainForEditConfiguration + "\"}";

        String settingDomain = "],\"revision\":null,\"newEndpointType\":\"PROCESS\",\"name\":\"" + nameSopsForRest + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}\"";
        String allPointsIn[] = {pointIn1};
        String allPointsOut[] = {pointOut1, pointOut2};

        sopsApi.createSopsAPI(Cookies, baseUrl(), idDomainForEditConfiguration, SopsID, nameSopsForRest, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(Cookies, idDomainForEditConfiguration);

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForEditAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 10; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.createAddConfiguration(Cookies, domainID, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);
        restApi.restartRest(Cookies);

        restPage.successEditAddConfiguration(domainName, nameOfAdditionalConfiguration, nameOfAdditionalConfiguration_New, typeOfAutentificationNew, usersAll_New, rolesAll_New);

        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration_New, typeOfAdditionalConfiguration);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration_New, typeOfAdditionalConfiguration);

        restPage.setAutentificationInRestSetting(domainName, nameOfAdditionalConfiguration_New);
        restApi.restartRest(Cookies);
        sleep(5000);
        //Проверка доступа к путям с удалнной аутентификацией
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath4);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath5);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath6);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL без ввода логина/пароля
        System.out.println(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath7);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath7);
        basePage.compareElementTextShouldNotContainsText("GET test", basePage.bodyPage);

        //Проверка доступа к авторизированному URL с вводом корректных логина/пароля (у пользователя одна роль, у пути 1 роль)
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath7, AuthenticationType.BASIC, user3_New[0], user3_New[1]);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        //Проверка доступа к авторизированному URL с вводом корректных логина/пароля (у пользователя одна роль, у пути 2 роли)
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath8, AuthenticationType.BASIC, user3_New[0], user3_New[1]);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        //Проверка доступа к авторизированному URL с вводом корректных логина/пароля (у пользователя две роли)
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath8, AuthenticationType.BASIC, user4_New[0], user4_New[1]);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath9, AuthenticationType.BASIC, user4_New[0], user4_New[1]);
        basePage.compareStringAndElementText("GET test", basePage.bodyPage);
    }

    @Test
    public void zCancelEditAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodPath4 = "/GET/get4";
        String methodPath5 = "/GET/get5";
        String methodPath6 = "/GET/get6";
        String methodPath7 = "/GET/get7";
        String methodPath8 = "/GET/get8";
        String methodPath9 = "/GET/get9";
        String methodPath10 = "/GET/get9";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Cancel_Edit_HTTP_Auth";
        String nameOfAdditionalConfiguration_New = "CoNfIg_For_Cancel_Edit_HTTP_Auth_New";
        String typeOfAdditionalConfiguration = "LOCAL_USERS";
        String typeOfAutentificationNew = "Digest";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"GET", "/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"GET", "/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"GET", "/" + domainName + "/" + servicePath + methodPath3, "true", "role3"};
        String[] role4 = {"GET", "/" + domainName + "/" + servicePath + methodPath4, "false"};
        String[] role5 = {"GET", "/" + domainName + "/" + servicePath + methodPath5, "true", "role1"};
        String[][] rolesAll = {role1, role2, role3, role4, role5};

        String[] user1_New = {"name1_New", "password1_New", "role1_New"};
        String[] user2_New = {"name2_New", "password2_New", "role2_New"};
        String[] user3_New = {"name3_New", "password3_New", "role3_New"};
        String[] user4_New = {"name4_New", "password4_New", "role4_New, role5_New"};
        String[][] usersAll_New = {user1_New, user2_New, user3_New, user4_New};

        String[] role1_New = {"GET", "/" + domainName + "/" + servicePath + methodPath6, "true", "role1_New"};
        String[] role2_New = {"GET", "/" + domainName + "/" + servicePath + methodPath7, "false", "role2_New"};
        String[] role3_New = {"GET", "/" + domainName + "/" + servicePath + methodPath8, "true", "role3_New"};
        String[] role4_New = {"GET", "/" + domainName + "/" + servicePath + methodPath9, "true", "role3_New, role4_New"};
        String[] role5_New = {"GET", "/" + domainName + "/" + servicePath + methodPath10, "true", "role5_New"};
        String[][] rolesAll_New = {role1_New, role2_New, role3_New, role4_New, role5_New};

//        Selenide.closeWindow();
//        if (!System.getProperty("os.name").equals("Windows 10")) {
//            Selenide.closeWebDriver();
//            Configuration.proxyEnabled = true;
//            Configuration.fileDownload = FileDownloadMode.PROXY;
//            Configuration.proxyHost = ClientUtil.getConnectableAddress().getHostAddress();
//        }
//        openPage(baseUrl());

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String idDomainForEditConfiguration = sopsApi.createDomainAPI(Cookies, nameDomainForRest);

        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEditConfiguration, nameSopsForRest);
        String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
        String nameQueueForRest = commonComponents.createIndividualName("nameQueueForRest");
        String guid = commonComponents.createIndividualName("guid");
        String bodyMessage = "GET test";

        String pointIn1 = "{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameLinkToSopsForRest + "\",\"fullUri\":\"direct-vm://" + nameLinkToSopsForRest + "\",\"isGlobal\":true,\"routeName\":null,\"routeId\":null,\"domainGuid\":null,\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}";

        String pointOut1 = "{\"name\":\"Установить тело сообщения\",\"icon\":\"fa fa-cog\",\"group\":\"Обработка\",\"guid\":\"" + guid + "\",\"componentName\":\"SetBodyEndpoint\",\"properties\":[],\"domain\":\"" + idDomainForEditConfiguration + "\",\"customName\":\"Установить тело сообщения\",\"body\":\"" + bodyMessage + "\"}";

        String pointOut2 = "{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[{\"nameRu\":\"Отключить синхронный ответ JMS-получателю\",\"defaultValue\":false,\"description\":\"В значении true будет работать как InOnly обмен с исключением в виде того, что JMS адрес для ответа заголовок выслан и не запрещен как в случае InOnly . Производитель не ждет ответа. Потребитель с этим параметром будет работать как InOnly. Может использоваться для привязки InOut запросов одной очереди к другой очереди так что последняя очередь отправит ответ обратно к оригинальному JMS адрес для ответа .\",\"nameEn\":\"disableReplyTo\",\"type\":\"BOOL\",\"value\":true,\"configValues\":[]}],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameQueueForRest + "\",\"fullUri\":\"localmq://" + nameQueueForRest + "?disableReplyTo=true\",\"sla\":{},\"isWireTap\":false,\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\",\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"domain\":\"" + idDomainForEditConfiguration + "\"}";

        String settingDomain = "],\"revision\":null,\"newEndpointType\":\"PROCESS\",\"name\":\"" + nameSopsForRest + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}\"";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1, pointOut2};

        sopsApi.createSopsAPI(Cookies, baseUrl(), idDomainForEditConfiguration, SopsID, nameSopsForRest, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(Cookies, idDomainForEditConfiguration);

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCancelEditAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 11; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.createAddConfiguration(Cookies, domainID, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);

        restPage.setAutentificationInRestSetting(domainName, nameOfAdditionalConfiguration);
        restApi.restartRest(Cookies);

        restPage.cancelEditAddConfiguration(domainName, nameOfAdditionalConfiguration, nameOfAdditionalConfiguration_New, typeOfAutentificationNew, usersAll_New, rolesAll_New);

        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration_New);
        refresh();
        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration_New);

        //Проверка доступа к путям с удалённой аутентификацией
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath4);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath5);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath6);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath7);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath8);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath9);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelEditAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath10);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
    }

    @Test
    public void zDeleteAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodPath4 = "/GET/get4";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Delete_HTTP_Auth";
        String typeOfAdditionalConfiguration = "LOCAL_USERS";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"GET", "/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"GET", "/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"GET", "/" + domainName + "/" + servicePath + methodPath3, "true", "role3"};
        String[] role4 = {"GET", "/" + domainName + "/" + servicePath + methodPath4, "false", "role1"};
        String[][] rolesAll = {role1, role2, role3, role4};

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String idDomainForEditConfiguration = sopsApi.createDomainAPI(Cookies, nameDomainForRest);

        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEditConfiguration, nameSopsForRest);
        String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
        String nameQueueForRest = commonComponents.createIndividualName("nameQueueForRest");
        String guid = commonComponents.createIndividualName("guid");
        String bodyMessage = "GET test";

        String pointIn1 = "{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameLinkToSopsForRest + "\",\"fullUri\":\"direct-vm://" + nameLinkToSopsForRest + "\",\"isGlobal\":true,\"routeName\":null,\"routeId\":null,\"domainGuid\":null,\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}";

        String pointOut1 = "{\"name\":\"Установить тело сообщения\",\"icon\":\"fa fa-cog\",\"group\":\"Обработка\",\"guid\":\"" + guid + "\",\"componentName\":\"SetBodyEndpoint\",\"properties\":[],\"domain\":\"" + idDomainForEditConfiguration + "\",\"customName\":\"Установить тело сообщения\",\"body\":\"" + bodyMessage + "\"}";

        String pointOut2 = "{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[{\"nameRu\":\"Отключить синхронный ответ JMS-получателю\",\"defaultValue\":false,\"description\":\"В значении true будет работать как InOnly обмен с исключением в виде того, что JMS адрес для ответа заголовок выслан и не запрещен как в случае InOnly . Производитель не ждет ответа. Потребитель с этим параметром будет работать как InOnly. Может использоваться для привязки InOut запросов одной очереди к другой очереди так что последняя очередь отправит ответ обратно к оригинальному JMS адрес для ответа .\",\"nameEn\":\"disableReplyTo\",\"type\":\"BOOL\",\"value\":true,\"configValues\":[]}],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameQueueForRest + "\",\"fullUri\":\"localmq://" + nameQueueForRest + "?disableReplyTo=true\",\"sla\":{},\"isWireTap\":false,\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\",\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"domain\":\"" + idDomainForEditConfiguration + "\"}";

        String settingDomain = "],\"revision\":null,\"newEndpointType\":\"PROCESS\",\"name\":\"" + nameSopsForRest + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}\"";
        String allPointsIn[] = {pointIn1};
        String allPointsOut[] = {pointOut1, pointOut2};

        sopsApi.createSopsAPI(Cookies, baseUrl(), idDomainForEditConfiguration, SopsID, nameSopsForRest, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(Cookies, idDomainForEditConfiguration);

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForDeleteAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 5; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.createAddConfiguration(Cookies, domainID, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);
        restApi.restartRest(Cookies);

        restPage.setAutentificationInRestSetting(domainName, nameOfAdditionalConfiguration);
        restApi.restartRest(Cookies);

        //Проверка доступа к путям конфигурации
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareStringAndElementText("", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareStringAndElementText("", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareStringAndElementText("", basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath4);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);

        open("/manager/");
        restPage.successDeleteAddConfiguration(domainName, nameOfAdditionalConfiguration);

        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);
        refresh();
        restPage.addConfigurationShouldBeNotExist(nameOfAdditionalConfiguration);

        restPage.clearAutentificationInRestSetting(domainName);
        restApi.restartRest(Cookies);
        sleep(5000);
        //Проверка доступа к путям из удаленной конфигурации
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath4);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
    }

    @Test
    public void zCancelDeleteAutentificationHttpLocalUsers() {
        String method = "GET";
        String methodPath = "/GET/get";
        String methodPath1 = "/GET/get1";
        String methodPath2 = "/GET/get2";
        String methodPath3 = "/GET/get3";
        String methodPath4 = "/GET/get4";
        String methodDescription = "нету";
        String methodTypeDataRequest = "text/plain";
        String nameOfAdditionalConfiguration = "CoNfIg_For_Cancel_Delete_HTTP_Auth";
        String typeOfAdditionalConfiguration = "LOCAL_USERS";
        String domainName = testName.getMethodName();

        String[] user1 = {"name1", "password1", "role1"};
        String[] user2 = {"name2", "password2", "role2"};
        String[] user3 = {"name3", "password3", "role3"};
        String[] user4 = {"name4", "password4", "role1, role3"};
        String[][] usersAll = {user1, user2, user3, user4};

        String[] role1 = {"GET", "/" + domainName + "/" + servicePath + methodPath1, "true", "role1"};
        String[] role2 = {"GET", "/" + domainName + "/" + servicePath + methodPath2, "true", "role2"};
        String[] role3 = {"GET", "/" + domainName + "/" + servicePath + methodPath3, "true", "role3"};
        String[] role4 = {"GET", "/" + domainName + "/" + servicePath + methodPath4, "false"};

        String[][] rolesAll = {role1, role2, role3, role4};

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String idDomainForEditConfiguration = sopsApi.createDomainAPI(Cookies, nameDomainForRest);

        String SopsID = sopsApi.generateSopsIdAPI(Cookies, idDomainForEditConfiguration, nameSopsForRest);
        String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
        String nameQueueForRest = commonComponents.createIndividualName("nameQueueForRest");
        String guid = commonComponents.createIndividualName("guid");
        String bodyMessage = "GET test";

        String pointIn1 = "{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameLinkToSopsForRest + "\",\"fullUri\":\"direct-vm://" + nameLinkToSopsForRest + "\",\"isGlobal\":true,\"routeName\":null,\"routeId\":null,\"domainGuid\":null,\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}";
        String pointOut1 = "{\"name\":\"Установить тело сообщения\",\"icon\":\"fa fa-cog\",\"group\":\"Обработка\",\"guid\":\"" + guid + "\",\"componentName\":\"SetBodyEndpoint\",\"properties\":[],\"domain\":\"" + idDomainForEditConfiguration + "\",\"customName\":\"Установить тело сообщения\",\"body\":\"" + bodyMessage + "\"}";
        String pointOut2 = "{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[{\"nameRu\":\"Отключить синхронный ответ JMS-получателю\",\"defaultValue\":false,\"description\":\"В значении true будет работать как InOnly обмен с исключением в виде того, что JMS адрес для ответа заголовок выслан и не запрещен как в случае InOnly . Производитель не ждет ответа. Потребитель с этим параметром будет работать как InOnly. Может использоваться для привязки InOut запросов одной очереди к другой очереди так что последняя очередь отправит ответ обратно к оригинальному JMS адрес для ответа .\",\"nameEn\":\"disableReplyTo\",\"type\":\"BOOL\",\"value\":true,\"configValues\":[]}],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameQueueForRest + "\",\"fullUri\":\"localmq://" + nameQueueForRest + "?disableReplyTo=true\",\"sla\":{},\"isWireTap\":false,\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\",\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"domain\":\"" + idDomainForEditConfiguration + "\"}";

        String settingDomain = "],\"revision\":null,\"newEndpointType\":\"PROCESS\",\"name\":\"" + nameSopsForRest + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}\"";
        String allPointsIn[] = {pointIn1};
        String allPointsOut[] = {pointOut1, pointOut2};

        sopsApi.createSopsAPI(Cookies, baseUrl(), idDomainForEditConfiguration, SopsID, nameSopsForRest, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(Cookies, idDomainForEditConfiguration);

        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForCancelDeleteAutentificationHttpLocalUsers, false);
        restPage.runDomainRest(domainName);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        for (int x = 1; x < 5; x++) {
            restApi.createMethod(domainID, Cookies, methodName + x, serviceName, method, methodPath + x, methodDescription + x, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        }
        restApi.createAddConfiguration(Cookies, domainID, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, usersAll, rolesAll);
        restApi.restartRest(Cookies);
        restPage.cancelDeleteAddConfiguration(domainName, nameOfAdditionalConfiguration);

        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);
        refresh();
        restPage.addConfigurationCheckAllParameters(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration);

        basePage.click(restPage.restGeneralConfigurationTab);
        basePage.click(staticRestPage.autentificationActivate);
        basePage.valAndSelectElement(staticRestPage.autentificationInput, nameOfAdditionalConfiguration);
        basePage.click(basePage.saveButton);
        restApi.restartRest(Cookies);
        sleep(5000);
        //Проверка доступа к путям с удалнной аутентификацией
        open(baseUrl().replace(":8181", ":" + portForCancelDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath1);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath2);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath3);
        basePage.compareElementTextShouldNotContainsText(bodyMessage, basePage.bodyPage);
        open(baseUrl().replace(":8181", ":" + portForCancelDeleteAutentificationHttpLocalUsers) + "/" + domainName + "/" + servicePath + methodPath4);
        basePage.compareStringAndElementText(bodyMessage, basePage.bodyPage);
    }

    @Test
    public void zzChangeSetting() {
        String method = "DELETE";
        String methodPath = "pathForSettingRest";
        String methodDescription = "DescriptionForSettingRest";
        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainIDForSettingRestTest = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainIDForSettingRestTest, nameSopsForRest);
        String restPath = "fesb-rest-NEW";
        String swaggerPath = "/swaggerNew";
        String swaggerPort = portForChangeSettingNew;
        String swaggerVersion = "1.0.NEW";
        String swaggerHeader = "REST API-NEW";
        String parameterName = "Ссылка на параметры SSL контекста";
        String domainName = testName.getMethodName();
        String methodTypeDataRequest = "text/plain";

        String bodyValue = commonComponents.createIndividualName(method);
        String nameOfAdditionalConfiguration1 = "RestSSLContextParameters";
        String nameOfAdditionalConfiguration2 = "RestKeyManagersParameters";
        String nameOfAdditionalConfiguration3 = "RestTrustManagersParameters";
        String nameOfAdditionalConfiguration4 = "RestKeyStoreParameters";
        String typeOfAdditionalConfiguration = "Пользовательский";
        String className1 = "org.apache.camel.util.jsse.SSLContextParameters";
        String className2 = "org.apache.camel.util.jsse.KeyManagersParameters";
        String className3 = "org.apache.camel.util.jsse.TrustManagersParameters";
        String className4 = "org.apache.camel.util.jsse.KeyStoreParameters";
        String methodDestroy = "";
        String methodFabric = "";
        String[] constructor1 = {};
        String[][] constructorsAll = null;
        String[] propertie1_1 = {"keyManagers", "ref", "RestKeyManagersParameters"};
        String[][] propertiesAll_1 = {propertie1_1};
        String[] propertie2_1 = {"keyStore", "ref", "RestKeyStoreParameters"};
        String[] propertie2_2 = {"keyPassword", "value", "123456"};
        String[][] propertiesAll_2 = {propertie2_1, propertie2_2};
        String[] propertie3_1 = {"keyStore", "ref", "RestKeyStoreParameters"};
        String[][] propertiesAll_3 = {propertie3_1};
        String[] propertie4_1 = {"Resource", "value", "./conf/ssl/fesb.jks"};
        String[] propertie4_2 = {"Password", "value", "123456"};
        String[][] propertiesAll_4 = {propertie4_1, propertie4_2};

        String nameLinkToSopsForRest = commonComponents.createIndividualName("nameLinkToSopsForRest");
        String nameQueueForRest = commonComponents.createIndividualName("nameQueueForRest");
        String guid = commonComponents.createIndividualName("guid");
        String bodyMessage = method + " и что-нибудь еще";

        String pointIn1 = "{\"componentName\":\"InDirectEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Ссылка на СОПС\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameLinkToSopsForRest + "\",\"fullUri\":\"direct-vm://" + nameLinkToSopsForRest + "\",\"isGlobal\":true,\"routeName\":null,\"routeId\":null,\"domainGuid\":null,\"prefix\":\"direct-vm\",\"endpointTag\":\"from\",\"name\":\"Ссылка на СОПС\",\"icon\":\"fas fa-link\"}";
        String pointOut1 = "{\"name\":\"Установить тело сообщения\",\"icon\":\"fa fa-cog\",\"group\":\"Обработка\",\"guid\":\"" + guid + "\",\"componentName\":\"SetBodyEndpoint\",\"properties\":[],\"domain\":\"" + DomainIDForSettingRestTest + "\",\"customName\":\"Установить тело сообщения\",\"body\":\"" + bodyMessage + "\"}";
        String pointOut2 = "{\"componentName\":\"OutLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"breakpoint\":false,\"suspended\":false,\"ssl\":false,\"debugData\":null,\"properties\":[{\"nameRu\":\"Отключить синхронный ответ JMS-получателю\",\"defaultValue\":false,\"description\":\"В значении true будет работать как InOnly обмен с исключением в виде того, что JMS адрес для ответа заголовок выслан и не запрещен как в случае InOnly . Производитель не ждет ответа. Потребитель с этим параметром будет работать как InOnly. Может использоваться для привязки InOut запросов одной очереди к другой очереди так что последняя очередь отправит ответ обратно к оригинальному JMS адрес для ответа .\",\"nameEn\":\"disableReplyTo\",\"type\":\"BOOL\",\"value\":true,\"configValues\":[]}],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + nameQueueForRest + "\",\"fullUri\":\"localmq://" + nameQueueForRest + "?disableReplyTo=true\",\"sla\":{},\"isWireTap\":false,\"useVariables\":false,\"isTransacted\":false,\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"to\",\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"domain\":\"" + DomainIDForSettingRestTest + "\"}";

        String settingDomain = "],\"revision\":null,\"newEndpointType\":\"PROCESS\",\"name\":\"" + nameSopsForRest + "\",\"transacted\":false,\"ref\":null,\"streamCache\":false,\"autoStartup\":true},\"comment\":\"\"}\"";
        String allPointsIn[] = {pointIn1};
        String allPointsOut[] = {pointOut1, pointOut2};

        sopsApi.createSopsAPI(Cookies, baseUrl(), DomainIDForSettingRestTest, SopsID, nameSopsForRest, allPointsIn, allPointsOut, settingDomain);
        sopsPage = new SOPSPage();
        sopsPage.startDomain(nameDomainForRest);
        String domainID = restApi.createDomainRest(Cookies, domainName, "/" + domainName, host, portForChangeSetting, false);
        restPage.runDomainRest(domainName);
        staticRestPage.settingRestDomain(domainName, staticHost);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        restApi.createMethod(domainID, Cookies, methodName, serviceName, method, methodPath, methodDescription, methodTypeDataRequest, methodTypeDataAnswer, nameLinkToSopsForRest);
        restApi.restartRest(Cookies);
        restPage.checkSwagger(domainName, serviceName, servicePath, method, methodPath, methodTypeDataReqest, methodTypeDataAnswer, null, null, bodyValue);
        Assert.assertFalse(url().contains("https"));
        basePage.changeWindow();

        restPage.successCreateAddConfiguration(domainName, nameOfAdditionalConfiguration1, typeOfAdditionalConfiguration, className1, methodDestroy, methodFabric, constructorsAll, propertiesAll_1);
        restPage.successCreateAddConfiguration(domainName, nameOfAdditionalConfiguration2, typeOfAdditionalConfiguration, className2, methodDestroy, methodFabric, constructorsAll, propertiesAll_2);
        restPage.successCreateAddConfiguration(domainName, nameOfAdditionalConfiguration3, typeOfAdditionalConfiguration, className3, methodDestroy, methodFabric, constructorsAll, propertiesAll_3);
        restPage.successCreateAddConfiguration(domainName, nameOfAdditionalConfiguration4, typeOfAdditionalConfiguration, className4, methodDestroy, methodFabric, constructorsAll, propertiesAll_4);

        restPage.goToDomainRest(domainName);
        basePage.click(restPage.generalSettingsTab);
//        basePage.click(staticRestPage.restSettingTab);
//        basePage.click(staticRestPage.restGeneralConfigurationTab);
        basePage.val(restPage.restPathInput, restPath);
        basePage.val(restPage.restPortInput, swaggerPort);
        basePage.val(restPage.pathSwaggerInput, swaggerPath);
        basePage.val(restPage.versionSwaggerInput, swaggerVersion);
        basePage.val(restPage.headerSwaggerInput, swaggerHeader);
        basePage.click(restPage.httpsCheckBox);
        basePage.click(restPage.addParameterButton);
        basePage.click(restPage.parameterNameActivate);
        basePage.valAndSelectElement(restPage.parameterNameInput, parameterName);
        basePage.click(restPage.parameterValueActivate);
        basePage.valAndSelectElement(restPage.parameterValueInput, nameOfAdditionalConfiguration1);
        basePage.click($x(".//div[text()='Добавление параметра']/../..//span[text()=\"Сохранить\"]/.."));
        basePage.click(basePage.saveButton);
        restPage.stopDomainRest(domainName);
        restPage.runDomainRest(domainName);
        sleep(3000);
        refresh();

        basePage.click(restPage.generalSettingsTab);
        basePage.compareStringAndElementValue(restPath, restPage.restPathInput);
        basePage.compareStringAndElementValue(swaggerPort, restPage.restPortInput);
        basePage.compareStringAndElementValue(swaggerPath, restPage.pathSwaggerInput);
        basePage.compareStringAndElementValue(swaggerVersion, restPage.versionSwaggerInput);
        basePage.compareStringAndElementValue(swaggerHeader, restPage.headerSwaggerInput);
        restPage.httpsCheckBox.shouldHave(Condition.attribute("class", "ant-checkbox ant-checkbox-checked"));
        basePage.compareStringAndElementText(parameterName + " #" + nameOfAdditionalConfiguration1, restPage.selectedParametrRest.get(0));
        restPage.checkSwagger(domainName, serviceName, servicePath, method, methodPath, methodTypeDataReqest, methodTypeDataAnswer, null, null, bodyValue);
        Assert.assertTrue(url().contains("https"));
    }

    @Test
    public void cancelCreateMethod() {
        String methodName = commonComponents.createIndividualName("GET");
        String method = "GET";
        String methodPath = "/getCancel";
        String methodDescription = "Описание для GET Cancel";
        String domainName = testName.getMethodName();

        String Cookies = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainID = sopsApi.createDomainAPI(Cookies, nameDomainForRest);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainID, nameSopsForRest);
        sopsApi.createSopsWithLinkToSopsAndLocalQueueAPI(Cookies, DomainID, SopsID, nameSopsForRest,
                nameLinkToSopsForRest, nameLocalQoeueForRest, transactionPolicy);
        String domainID = restApi.createDomainRest(Cookies, domainName, domainName, host, portForCancelCreateMetodth, false);
        restApi.createService(Cookies, domainID, serviceName, servicePath, serviceDescription, serviceTypeDataRequest, serviceTypeDataAnswer, "auto");
        sleep(1000);
        restApi.restartRest(Cookies);
        restPage.cancelCreateMetod(testName.getMethodName(), servicePath, methodName, method, methodPath, nameSopsForRest, methodTypeDataRequest, methodTypeDataAnswer, methodDescription, null, null, null, null);
        restPage.methodShouldNotBeExist(domainName, serviceName, methodDescription);
        refresh();
        restPage.methodShouldNotBeExist(domainName, serviceName, methodDescription);
    }


//    @Test
//    @Parameters(value = {
//            "method_GeT1$ | GET | /get/{a2}  | Описание для GET",
//
//    })
//    public void swagger(String methodName, String method, String methodPath, String methodDescription) {
//        String parametrName1 = "a1";
//        String parametrName2 = "a2";
//        String parametrValue1 = "123";
//        String parametrValue2 = "Testилище-2";
//
//
//        String servicePath = "/servicePath2709405";
//
//        openPage("http://192.168.57.240:8181/manager/webjars/swagger-ui/index.html?url=http://192.168.57.240:9090/swagger");
//        basePage.click($x(String.format(restPage.methodPathInSwagger, servicePath + methodPath)));
//        basePage.click($x(String.format(restPage.tryItOutButtonInSwagger, servicePath + methodPath)));
//        basePage.val(restPage.parametersInputInSwagger.get(0), parametrValue1);
//        basePage.val(restPage.parametersInputInSwagger.get(1), parametrValue2);
//        basePage.click(restPage.executeButtonInSwagger);
//        restPage.serverBodyResponseSwagger.shouldHave(Condition.exactText(parametrValue1 + " " + parametrValue2));
//        restPage.serverHeadersResponseSwagger.shouldHave(Condition.exactText(" content-type: " + methodTypeData));
//        restPage.responseDescriptionSwagger.shouldHave(Condition.exactText("OK"));
//
//        openPage("http://192.168.57.240:8181/manager/#/queue-manager/queues");
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queueCheckAllParameters("nameLocalQoeueForRest6454722", "1", "0", "1", "0", "Локальная", "");
//        queueManagerPage.contentSpecificQueue("nameLocalQoeueForRest6454722");
//        specificQueuePage.openFirstMessage();
//        specificMessagePage.checkParametersMessagePersistanceAndBody("Да", parametrValue1 + " " + parametrValue2);
//    }

//    @Test
//    public void asdf() {
//        System.out.println("asdf");
//
//
//    }

}