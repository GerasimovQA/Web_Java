package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RestPage extends BasePage {
    Base base = new Base();
    CommonComponents commonComponents = new CommonComponents();


    String methodPathInSwagger = ".//span[text()='%s']/../../following-sibling::div/span/div/div";
    String tryItOutButtonInSwagger = ".//span[@data-path='/%s']/../following-sibling::div//button[text()='Try it out ']";
    String methodInList = ".//div[@label=\"%s\"]";
    static String specificDomainButtonXpath = ".//a[text()='REST']/../..//a[text()='%s']";


    SelenideElement select = $x(".//select");
    public SelenideElement createRestDomainButton = $x(".//a[text()='REST']/../..//span[text()='Создать REST-домен']");
    public SelenideElement addressOfRestDomainActivate = $x(".//label[text()='Адрес']/../following-sibling::div");
    public SelenideElement addressOfRestDomainInput = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    public SelenideElement createDomainRestButton = $x(".//span[text()='Создать']");
    public SelenideElement nameDomainInHeader = $x(".//span[@class=\"ant-page-header-heading-title\"]");
    public SelenideElement startDomainButton = $x(".//span[text()='Запустить']/..");
    public SelenideElement stopDomainButton = $x(".//span[text()='Остановить']/..");
    public SelenideElement exportButton = $x(".//span[text()='Экспорт']/..");

    //    public SelenideElement servicesTab = $x(".//a[text()='Сервисы']");
    public SelenideElement addParameterButton = $x(".//span[text()='Добавить параметр']/..");
    public SelenideElement addGroupOfMethodsButton = $x(".//span[text()='Добавить группу методов']/..");
    public SelenideElement parameterNameActivate = $x(".//div[text()='Добавление параметра']/../following-sibling::div//div[@name=\"nameEn\"]");
    public SelenideElement parameterNameInput = $x(".//div[text()='Добавление параметра']/../following-sibling::div//div[@name=\"nameEn\"]//input");
    public SelenideElement parameterValueActivate = $x("//div[text()='Значение']/following-sibling::div//input/..");
    public SelenideElement parameterValueInput = $x("//div[text()='Значение']/following-sibling::div//input");
    public SelenideElement saveButton = $$x(".//span[text()='Сохранить']/..").find(visible);
    public SelenideElement oldSaveButton = $x(".//button[text()='Сохранить']");

    public SelenideElement nameServiceInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement pathServiceInput = $x(".//label[text()='Путь']/../following-sibling::div//input");
    public SelenideElement typeDataServiceRequestActivate = $x(".//label[text()='Тип данных запроса']/../following-sibling::div");
    public SelenideElement typeDataServiceRequestInput = $x(".//label[text()='Тип данных запроса']/../following-sibling::div//input");
    public SelenideElement typeDataServiceAnswerActivate = $x(".//label[text()='Тип данных ответа']/../following-sibling::div");
    public SelenideElement typeDataServiceAnswerInput = $x(".//label[text()='Тип данных ответа']/../following-sibling::div//input");
    public SelenideElement bindingTypeOfServiceActivate = $x(".//label[text()='Тип связывания']/../following-sibling::div");
    public SelenideElement bindingTypeOfServiceInput = $x(".//label[text()='Тип связывания']/../following-sibling::div//input");
    public SelenideElement descriptionServiceInput = $x(".//label[text()='Описание']/../following-sibling::div//input");

    public SelenideElement expandGroupOfMethodsButton = $x(".//td[@class=\"ant-table-cell ant-table-row-expand-icon-cell\"]/button");
    public SelenideElement afterSearchServiceName = $x(".//td[@class=\"ant-table-cell\"][1]");
    public SelenideElement afterSearchServicePath = $x(".//td[@class=\"ant-table-cell\"][2]");
    public SelenideElement afterSearchServiceTypeDataRequest = $x(".//td[@class=\"ant-table-cell\"][3]");
    public SelenideElement afterSearchServiceTypeDataAnswer = $x(".//td[@class=\"ant-table-cell\"][4]");
    public SelenideElement afterSearchBindingType = $x(".//td[@class=\"ant-table-cell\"][5]");
    public SelenideElement afterSearchServiceDescription = $x(".//td[@class=\"ant-table-cell\"][6]");


    public SelenideElement methodNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement methodActivate = $$x(".//label[text()='Метод']/../following-sibling::div").find(visible);
    public SelenideElement methodInput = $x(".//label[text()='Метод']/../following-sibling::div//input");
    public SelenideElement methodValue = $x(".//label[text()='Метод']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");
    public SelenideElement methodPathInput = $x(".//label[text()='Путь']/../following-sibling::div//input");
    public SelenideElement methodLinkToSopsActivate = $x(".//label[text()='СОПС']/../following-sibling::div");
    public SelenideElement methodLinkToSopsInput = $x(".//label[text()='СОПС']/../following-sibling::div//input");
    public SelenideElement methodLinkToSopsValue = $x(".//label[text()='СОПС']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");
    public SelenideElement modelRequestActivate = $x(".//label[text()='Модель запроса']/../following-sibling::div");
    public SelenideElement modelRequestInput = $x(".//label[text()='Модель запроса']/../following-sibling::div//input");
    public SelenideElement modelResponseActivate = $x(".//label[text()='Модель ответа']/../following-sibling::div");
    public SelenideElement modelResponseInput = $x(".//label[text()='Модель ответа']/../following-sibling::div//input");
    public SelenideElement modelResponseValue = $x(".//label[text()='Модель ответа']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");
    public SelenideElement outgoingTypeDataMethodRequestActivate = $x(".//label[text()='Тип данных запроса']/../following-sibling::div");
    public SelenideElement outgoingTypeDataMethodRequestInput = $x(".//label[text()='Тип данных запроса']/../following-sibling::div//input");
    public SelenideElement outgoingTypeDataMethodAnswerActivate = $x(".//label[text()='Тип данных ответа']/../following-sibling::div");
    public SelenideElement outgoingTypeDataMethodAnswerInput = $x(".//label[text()='Тип данных ответа']/../following-sibling::div//input");
    public SelenideElement outgoingTypeDataMethodValue = $x(".//label[text()='Тип данных запроса']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");
    public SelenideElement methodDescriptionInput = $x(".//label[text()='Описание']/../following-sibling::div//textarea");

    public SelenideElement addParametrOfMethodButton = $x(".//span[text()='Добавить']/..");
    public SelenideElement nameLastParameterOfMethodInput = $x(".//tr[last()]//input[@name=\"name\"]");
    public SelenideElement typeLastParameterOfMethodActivate = $x(".//tr[last()]//td[2]//input/../../..");
    public SelenideElement typeLastParameterOfMethodInput = $x(".//tr[last()]//td[2]//input");
    public SelenideElement typeLastDataOfMethodActivate = $x(".//tr[last()]//td[3]//input/../../..");
    public SelenideElement typeLastDataOfMethodInput = $x(".//tr[last()]//td[3]//input");
    public SelenideElement descriptionLastParameterOfMethodInput = $x(".//tr[last()]//input[@name=\"description\"]");
    public SelenideElement nameBodyOfMethodInput = $x(".//th[text()='Имя']/../../../tbody//td[1]//input");
    public SelenideElement descriptionBodyOfMethodInput = $x(".//th[text()='Имя']/../../../tbody//td[2]//input");
    public ElementsCollection listNameParameterOfMethodInput = $$x(".//div[text()='Параметры']/../../..//input[@name=\"name\"]");
    public ElementsCollection listTypeParameterOfMethodActivate = $$x(".//td[2]//input/../../..");
    public ElementsCollection listTypeParameterOfMethodInput = $$x(".//td[2]//input");
    public ElementsCollection listTypeValueParameterOfMethodInput = $$x(".//td[2]//input/../following-sibling::span");
    public ElementsCollection listTypeDataParameterOfMethodActivate = $$x(".//td[3]//input/../../..");
    public ElementsCollection listTypeDataParameterOfMethodInput = $$x(".//td[3]//input");
    public ElementsCollection listTypeDataValueParameterOfMethodInput = $$x(".//td[3]//input/../following-sibling::span");
    public ElementsCollection listDescriptionParameterOfMethodInput = $$x(".//input[@name=\"description\"]");

    public SelenideElement afterSearchMethodName = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][1]");
    public SelenideElement afterSearchethod = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][2]");
    public SelenideElement afterSearchMethodPath = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][3]");
    public SelenideElement afterSearchMethodLinkToSops = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][4]");
    public SelenideElement afterSearchMethodTypeDataRequest = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][5]");
    public SelenideElement afterSearchMethodTypeDataAnswer = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][6]");
    public SelenideElement afterSearchMethodDescription = $x(".//span[text()='Добавить метод']/../../../../../..//td[@class=\"ant-table-cell\"][7]");

    public SelenideElement restSettingTab = $x(".//a[text()='REST']/../..//a[text()='Настройки']");
    public SelenideElement groupOfMethodsTab = $x(".//div[text()='Группы методов']");
    public SelenideElement generalSettingsTab = $x(".//div[text()='Общие настройки']");
    public SelenideElement restGeneralConfigurationTab = $x(".//*[text()='Общие настройки']");
    public SelenideElement restPathInput = $x(".//label[text()='Путь']/../following-sibling::div//input[@name=\"contextPath\"]");
    public SelenideElement restAdressActivate = $x(".//label[text()='Адрес']/../following-sibling::div");
    public SelenideElement restAdressInput = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    public SelenideElement restPortInput = $x(".//label[text()='Порт']/../following-sibling::div//input");
    public SelenideElement pathSwaggerInput = $x(".//label[text()='Путь']/../following-sibling::div//input[@name=\"apiContextPath\"]");
    public SelenideElement versionSwaggerInput = $x(".//label[text()='Версия']/../following-sibling::div//input");
    public SelenideElement headerSwaggerInput = $x(".//label[text()='Заголовок']/../following-sibling::div//input");
    public SelenideElement swaggerUiLink = $x(".//a[text()='swagger-ui.html']");
    public SelenideElement httpsCheckBox = $x(".//span[text()='HTTPS']/..//input/..");
    public SelenideElement headersCorsActivate = $x(".//label[text()='Заголовки CORS в ответе HTTP']/../following-sibling::div");
    public SelenideElement headersCorsInput = $x(".//label[text()='Заголовки CORS в ответе HTTP']/../following-sibling::div//input");
    public SelenideElement autentificationActivate = $x(".//label[text()='Аутентификация']/../following-sibling::div");
    public SelenideElement autentificationInput = $x(".//label[text()='Аутентификация']/../following-sibling::div//input");
    public SelenideElement autentificationClearButton = $x(".//label[text()='Аутентификация']/../following-sibling::div//span[@aria-label=\"close-circle\"]");


    public SelenideElement restAdditionalConfigurationTab = $x(".//*[text()='Дополнительная конфигурация']");
    public SelenideElement addConfigurationButton = $x(".//span[text()='Добавить конфигурацию']/..");
    public SelenideElement typeOfObjectActivate = $x(".//label[text()='Тип объекта']/../following-sibling::div");
    public SelenideElement typeOfObjectInput = $x(".//label[text()='Тип объекта']/../following-sibling::div//input");
    public SelenideElement nameOfAddConfigurationInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement classOfAddConfigurationInput = $x(".//label[text()='Класс']/../following-sibling::div//input");
    public SelenideElement methodDestroyOfAddConfigurationInput = $x(".//label[text()='Метод уничтожения']/../following-sibling::div//input");
    public SelenideElement methodFabricOfAddConfigurationInput = $x(".//label[text()='Фабричный метод']/../following-sibling::div//input");
    public SelenideElement typeOfAutentificationActivate = $x(".//label[text()='Тип аутентификации']/../following-sibling::div");
    public SelenideElement typeOfAutentificationInput = $x(".//label[text()='Тип аутентификации']/../following-sibling::div//input");
    public SelenideElement addUserButton = $x(".//div[text()='Пользователи']/following-sibling::div//span[text()='Добавить']/..");
    public SelenideElement addRoleButton = $x(".//div[text()='Настройки путей']/following-sibling::div//span[text()='Добавить']/..");
    public SelenideElement expandRoleButton = $x(".//div[text()='Настройки путей']/following-sibling::div//button[@aria-label=\"Развернуть строку\"]");
    public SelenideElement rollupRoleButton = $x(".//div[text()='Настройки путей']/following-sibling::div//button[@aria-label=\"Свернуть строку\"]");

    public SelenideElement afterSearcheAdditionalConfigurationName = $x(".//td[@class=\"ant-table-cell\"][1]");
    public SelenideElement afterSearcheAdditionalConfigurationType = $x(".//td[@class=\"ant-table-cell\"][2]");


    public ElementsCollection loginOfUserInput = $$x(".//div[text()='Пользователи']/following-sibling::div//input[@name=\"login\"]");
    public ElementsCollection passwordOfUserInput = $$x(".//div[text()='Пользователи']/following-sibling::div//input[@name=\"password\"]");
    public ElementsCollection rolesOfUserInput = $$x(".//div[text()='Пользователи']/following-sibling::div//input[@name=\"roles\"]");
    public ElementsCollection deleteRoleButton = $$x(".//div[text()='Пользователи']/following-sibling::div//span[@aria-label=\"delete\"]/..");

    public ElementsCollection methodOfRoleActivate = $$x(".//div[text()='Настройки путей']/following-sibling::div//div[@name=\"method\"]").filter(visible);
    public ElementsCollection pathOfRoleInput = $$x(".//div[text()='Настройки путей']/following-sibling::div//input[@name=\"path\"]").filter(visible);
    public ElementsCollection autentificationOfRoleCheckBox = $$x(".//div[text()='Настройки путей']/following-sibling::div//input[@name=\"auth\"]/..").filterBy(visible);
    public ElementsCollection roleOfRolesInput = $$x(".//div[text()='Настройки путей']/following-sibling::div//input[@name=\"roles\"]").filterBy(visible);
    public ElementsCollection deletePathButton = $$x(".//div[text()='Настройки путей']/following-sibling::div//span[@aria-label=\"delete\"]/..").filterBy(visible);


    public SelenideElement addConstructorOfBinButton = $x(".//div[text()='Конструктор бина']/following-sibling::div//span[text()='Добавить']/..");
    public ElementsCollection typeConstructorOfBinActivate = $$x(".//div[text()='Конструктор бина']/following-sibling::div//div[@name=\"type\"]");
    public ElementsCollection typeConstructorOfBinInput = $$x("//div[text()='Конструктор бина']/following-sibling::div//div[@name=\"type\"]//input");
    public ElementsCollection valueConstructorOfBinInput = $$x("//div[text()='Конструктор бина']/following-sibling::div//input[@name=\"value\"]");

    public SelenideElement propertiesOfBinButton = $x(".//div[text()='Свойства бина']/following-sibling::div//span[text()='Добавить']/..");
    public ElementsCollection nameProperiesOfBin = $$x(".//div[text()='Свойства бина']/following-sibling::div//td[1]//input");
    public ElementsCollection typePropertiesOfBinActivate = $$x("//div[text()='Свойства бина']/following-sibling::div//td[2]//input/..");
    public ElementsCollection typePropertiesOfBinInput = $$x("//div[text()='Свойства бина']/following-sibling::div//td[2]//input");
    public ElementsCollection valuePropertiesOfBinInput = $$x("//div[text()='Свойства бина']/following-sibling::div//td[3]//input");

    public SelenideElement executeButtonInSwagger = $x(".//button[text()='Execute']");
    public SelenideElement serverBodyResponseSwagger = $x(".//h5[text()='Response body']/following-sibling::div//pre[@class=\" microlight\"]");
    public SelenideElement serverHeadersResponseSwagger = $x(".//h5[text()='Response headers']/following-sibling::pre[@class=\"microlight\"]");
    public SelenideElement responseDescriptionSwagger = $x(".//td[text()='Description']/../../following-sibling::tbody//div[@class=\"markdown\"]");
    public SelenideElement bodyInputInSwagger = $x(".//div[@data-param-name]/textarea");

    public ElementsCollection parametersInputInSwagger = $$(By.xpath(".//tr//input"));

    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));
    public ElementsCollection expandRoleButtonList = $$x(".//div[text()='Настройки путей']/following-sibling::div//button[@aria-label=\"Развернуть строку\"]");
    public ElementsCollection collapseRoleButtonList = $$x(".//div[text()='Настройки путей']/following-sibling::div//button[@aria-label=\"Свернуть строку\"]");

    public ElementsCollection selectedParametrRest = $$x(".//span[text()='Добавить параметр']/../../following-sibling::div//tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]");

    public enum TypeMethod {Get, Post, Put, Delete}

    public void createDomainWithoutSave(String domainName, String domainPath, String address, String port, boolean https) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }

        click(createRestDomainButton);
        val(nameServiceInput, domainName);
        val(pathServiceInput, "/" + domainPath);
        click(addressOfRestDomainActivate);
        valAndSelectElement(addressOfRestDomainInput, address);
        val(restPortInput, port);

        if (https && !httpsCheckBox.parent().isSelected()) {
            click(httpsCheckBox);
        }
        if (!https && httpsCheckBox.parent().isSelected()) {
            click(httpsCheckBox);
        }
    }

    @Step
    public void successfulCreateDomain(String domainName, String domainPath, String address, String port, boolean https) {
        createDomainWithoutSave(domainName, domainPath, address, port, https);
        click(createDomainRestButton);
    }

    @Step
    public void cancelCreateDomain(String domainName, String domainPath, String address, String port, boolean https) {
        createDomainWithoutSave(domainName, domainPath, address, port, https);
        closeForm();
    }

    @Step("Run {0}")
    public void runDomainRest(String domainName) {
        goToDomainRest(domainName);
        nameDomainInHeader.should(exactText("REST-домен: " + domainName));
        click(startDomainButton);
        elementShouldBeVisible(stopDomainButton);
        commonComponents.closeNotification();
    }

    @Step("Stop {0}")
    public void stopDomainRest(String domainName) {
        goToDomainRest(domainName);
        click(stopDomainButton);
        commonComponents.closeNotification();
    }

    public void createServiceWithoutSave(String domainName, String serviceName, String servicePath, String serviceTypeDataRequest, String serviceTypeDataAnswer, String bindingTypeOfService, String serviceDescription) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
        goToDomainRest(domainName);
//        click(servicesTab);
        click(addGroupOfMethodsButton);
        val(nameServiceInput, serviceName);
        val(pathServiceInput, "/" + servicePath);
        click(typeDataServiceRequestActivate);
        valAndSelectElement(typeDataServiceRequestInput, serviceTypeDataRequest);
        click(typeDataServiceAnswerActivate);
        valAndSelectElement(typeDataServiceAnswerInput, serviceTypeDataAnswer);


        click(bindingTypeOfServiceActivate);
        valAndSelectElement(bindingTypeOfServiceInput, bindingTypeOfService);


        val(descriptionServiceInput, serviceDescription);
    }

    @Step
    public void successfulCreateService(String domainName, String serviceName, String servicePath, String serviceTypeDataRequest, String serviceTypeDataAnswer, String bindingTypeOfService, String serviceDescription) {
        createServiceWithoutSave(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, bindingTypeOfService, serviceDescription);
        click(saveButton);
    }

    @Step
    public void cancelCreateService(String domainName, String serviceName, String servicePath, String serviceTypeDataRequest, String serviceTypeDataAnswer, String bindingTypeOfService, String serviceDescription) {
        createServiceWithoutSave(domainName, serviceName, servicePath, serviceTypeDataRequest, serviceTypeDataAnswer, bindingTypeOfService, serviceDescription);
        closeForm();
    }

    public void editServiceWithoutSave(String domainName, String serviceName, String serviceNameNew, String servicePathNew, String outgoingTypeDataNew, String serviceDescriptionNew) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
        goToDomainRest(domainName);
        searchService(domainName, serviceName);
        contextClick(afterSearchServiceName);
        click(editInContextMenu);
        val(nameServiceInput, serviceNameNew);
        val(pathServiceInput, "/" + servicePathNew);
        click(typeDataServiceRequestActivate);
        valAndSelectElement(typeDataServiceRequestInput, outgoingTypeDataNew);
        val(descriptionServiceInput, serviceDescriptionNew);
    }

    @Step
    public void successfulEditService(String domainName, String serviceName, String serviceNameNew, String servicePathNew, String outgoingTypeDataNew, String serviceDescriptionNew) {
        editServiceWithoutSave(domainName, serviceName, serviceNameNew, servicePathNew, outgoingTypeDataNew, serviceDescriptionNew);
        click(saveButton);
    }

    @Step
    public void cancelEditService(String domainName, String serviceName, String serviceNameNew, String servicePathNew, String outgoingTypeDataNew, String serviceDescriptionNew) {
        editServiceWithoutSave(domainName, serviceName, serviceNameNew, servicePathNew, outgoingTypeDataNew, serviceDescriptionNew);
        closeForm();
    }

    public void deleteServiceWithoutSave(String domainName, String serviceName) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
//        click(servicesTab);
        searchService(domainName, serviceName);
        contextClick(afterSearchServiceName);
        click(deleteInContextMenu);
    }

    @Step
    public void successfulDeleteService(String domainName, String serviceName) {
        deleteServiceWithoutSave(domainName, serviceName);
        click(confirmationDeleteButton);
    }

    @Step
    public void cancelDeleteService(String domainName, String serviceName) {
        deleteServiceWithoutSave(domainName, serviceName);
        closeForm();
    }

    @Step
    public void searchService(String domainName, String serviceName) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
        goToDomainRest(domainName);
        searchInput.clear();
        val(searchInput, serviceName);
    }

    @Step
    public void serviceCheckAllParameters(String domainName, String serviceName, String servicePath, String serviceTypeDataReuest, String serviceTypeDataAnswer, String BindingType, String serviceDescription) {
//        goToDomainRest(domainName);
        searchService(domainName, serviceName);
        compareStringAndElementText(serviceName, afterSearchServiceName);
        compareStringAndElementText("/" + servicePath, afterSearchServicePath);
        compareStringAndElementText(serviceTypeDataReuest, afterSearchServiceTypeDataRequest);
        compareStringAndElementText(serviceTypeDataAnswer, afterSearchServiceTypeDataAnswer);
        compareStringAndElementText(BindingType, afterSearchBindingType);
        compareStringAndElementText(serviceDescription, afterSearchServiceDescription);
    }

    @Step
    public void serviceShouldNotBeExist(String domainName, String serviceName) {
//        if (!servicesTab.attr("class").equals("active")) click(servicesTab);
        searchService(domainName, serviceName);
        rowAfterSearch.shouldNotBe(exist);
    }


    public void createMethodWithoutSave(String domainName, String serviceName, String methodName, String method, String methodPath, String methodLinkToSops, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription, String[] parameter1, String[] parameter2, String[] modelsData, String descriptionBodyOfMethod) {
//        restTab.shouldBe(enabled);
//        if (!restTab.attr("class").equals("active")) {
//            click(restTab);
//        }
//        click(servicesTab);
        goToDomainRest(domainName);
        click(groupOfMethodsTab);
        searchService(domainName, serviceName);
        click(expandGroupOfMethodsButton);
        click(addMethodButton);
        val(methodNameInput, methodName);
        click(methodActivate);
        valAndSelectElement(methodInput, method);
        val(methodPathInput, methodPath);
        click(methodLinkToSopsActivate);
        valAndSelectElement(methodLinkToSopsInput, methodLinkToSops);
        click(outgoingTypeDataMethodRequestActivate);
        valAndSelectElement(outgoingTypeDataMethodRequestInput, methodTypeDataRequest);
        click(outgoingTypeDataMethodAnswerActivate);
        valAndSelectElement(outgoingTypeDataMethodAnswerInput, methodTypeDataAnswer);
        val(methodDescriptionInput, methodDescription);

        switch (method) {
            case "GET":
                if (!(parameter1 == null)) {
                    addParametrInMethodGet(parameter1);
                }
                if (!(parameter2 == null)) {
                    addParametrInMethodGet(parameter2);
                }
                break;
            case "POST":
            case "PUT":
//                val(nameBodyOfMethodInput, nameBodyOfMethod);
//                val(descriptionBodyOfMethodInput, descriptionBodyOfMethod);
//                click(methodActivate);
//                valAndSelectElement(methodInput, method);
                click(modelRequestActivate);
                valAndSelectElement(modelRequestInput, " (" + modelsData[1] + ")");
                click(modelResponseActivate);
                valAndSelectElement(modelResponseInput, " (" + modelsData[2] + ")");
                break;
            case "DELETE":
                break;
            default:
                throw new AssertionError("Пропущен выбор параметров");
        }
    }

    @Step
    public void successfulCreateMethod(String domainName, String serviceName, String methodName, String method, String methodPath, String methodLinkToSops, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription, String[] parameter1, String[] parameter2, String[] modelsData, String escriptionParameterOfMethod) {
        createMethodWithoutSave(domainName, serviceName, methodName, method, methodPath, methodLinkToSops, methodTypeDataRequest, methodTypeDataAnswer, methodDescription, parameter1, parameter2, modelsData, escriptionParameterOfMethod);
        click(saveButton);
    }

    @Step
    public void cancelCreateMetod(String domainName, String serviceName, String methodName, String method, String methodPath, String methodLinkToSops, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription, String[] parameter1, String[] parameter2, String[] modelsData, String escriptionParameterOfMethod) {
        createMethodWithoutSave(domainName, serviceName, methodName, method, methodPath, methodLinkToSops, methodTypeDataRequest, methodTypeDataAnswer, methodDescription, parameter1, parameter2, modelsData, escriptionParameterOfMethod);
        closeForm();
    }

    public void editMethodWithoutSave(String domainName, String serviceName, String methodDescription, String methodNameNew, String method, String methodPathNew, String methodLinkToSopsNew, String methodTypeDataNew, String modelResponseNew, String methodDescriptionNew, String[] parameter1, String[] parameter2, String[] modelsData) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
//        click(servicesTab);
        searchMethod(domainName, serviceName, methodDescription);
        contextClick(afterSearchMethodName);
        click(editInContextMenu);

        val(methodNameInput, methodNameNew);
        click(methodActivate);
        valAndSelectElement(methodInput, method);
        val(methodPathInput, methodPathNew);
        click(methodLinkToSopsActivate);
        valAndSelectElement(methodLinkToSopsInput, methodLinkToSopsNew);
        click(outgoingTypeDataMethodRequestActivate);
        valAndSelectElement(outgoingTypeDataMethodRequestInput, methodTypeDataNew);
        click(modelResponseActivate);
        valAndSelectElement(modelResponseInput, " (" + modelResponseNew + ")");
        val(methodDescriptionInput, methodDescriptionNew);

        switch (method) {
            case "GET":
                if (!(parameter1 == null)) {
                    editParametrInMethodGet(0, parameter1[0], parameter1[1], parameter1[2], parameter1[3]);
                }
                if (!(parameter2 == null)) {
                    editParametrInMethodGet(1, parameter2[0], parameter2[1], parameter2[2], parameter2[3]);
                }
                break;
            case "POST":
            case "PUT":
//                val(nameBodyOfMethodInput, nameBodyOfMethod);
//                val(descriptionBodyOfMethodInput, descriptionBodyOfMethod);
//                click(methodActivate);
//                valAndSelectElement(methodInput, method);
                click(modelRequestActivate);
                valAndSelectElement(modelRequestInput, " (" + modelsData[1] + ")");
                click(modelResponseActivate);
                valAndSelectElement(modelResponseInput, " (" + modelsData[2] + ")");
                break;
            case "DELETE":
                break;
            default:
                throw new AssertionError("Пропущен выбор параметров");
        }
    }

    @Step
    public void successfulEditMethod(String domainName, String serviceName, String methodDescription, String methodNameNew, String method, String methodPathNew, String methodLinkToSopsNew, String methodTypeDataNew, String modelResponseNew, String methodDescriptionNew, String[] parameter1, String[] parameter2, String[] modelsData) {
        editMethodWithoutSave(domainName, serviceName, methodDescription, methodNameNew, method, methodPathNew, methodLinkToSopsNew, methodTypeDataNew, modelResponseNew, methodDescriptionNew, parameter1, parameter2, modelsData);
        click(saveButton);
    }

    @Step
    public void cancelEditMetod(String domainName, String serviceName, String methodDescription, String methodNameNew, String method, String methodPathNew, String methodLinkToSopsNew, String methodTypeDataNew, String modelResponseNew, String methodDescriptionNew, String[] parameter1, String[] parameter2, String[] modelsData) {
        editMethodWithoutSave(domainName, serviceName, methodDescription, methodNameNew, method, methodPathNew, methodLinkToSopsNew, methodTypeDataNew, modelResponseNew, methodDescriptionNew, parameter1, parameter2, modelsData);
        closeForm();
    }

    public void deleteMethodWithoutSave(String domainName, String serviceName, String methodDescription) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
//        click(servicesTab);
        searchMethod(domainName, serviceName, methodDescription);
        contextClick(afterSearchMethodName);
        click(deleteInContextMenu);
    }

    @Step
    public void successfulDeleteMethod(String domainName, String serviceName, String methodDescription) {
        deleteMethodWithoutSave(domainName, serviceName, methodDescription);
        click(confirmationDeleteButton);
    }

    @Step
    public void cancelDeleteMehtod(String domainName, String serviceName, String methodDescription) {
        deleteMethodWithoutSave(domainName, serviceName, methodDescription);
        closeForm();
    }


    public void checkMethodParameters(String domainName, String serviceName, String methodDescription, String methodNameNew, String method, String methodPathNew, String methodLinkToSopsNew, String methodTypeDataNew, String modelResponseNew, String methodDescriptionNew, String[] parameter1, String[] parameter2, String[] modelsData) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
//        click(servicesTab);
        searchMethod(domainName, serviceName, methodDescription);
        contextClick(afterSearchMethodName);
        click(editInContextMenu);

        compareStringAndElementValue(methodNameNew, methodNameInput);
        compareStringAndElementText(method, methodValue);
        compareStringAndElementValue(methodPathNew, methodPathInput);
        compareStringAndElementText(methodLinkToSopsNew, methodLinkToSopsValue);
        compareStringAndElementText(methodTypeDataNew, outgoingTypeDataMethodValue);
//        compareStringAndElementText(methodTypeDataNew, outgoingTypeDataMethodInput);
        if (methodNameNew.contains("Cancel")) {
            modelResponseValue.shouldNotBe(exist);
        } else {
            sout(modelResponseValue.getText());
            Assert.assertTrue("Модель данных битая", modelResponseValue.getText().contains(" (" + modelResponseNew + ")"));
        }
        compareStringAndElementText(methodDescriptionNew, methodDescriptionInput);

        switch (method) {
            case "GET":
                if (!(parameter1 == null)) {
                    compareStringAndElementValue(parameter1[0], listNameParameterOfMethodInput.get(0));
                    compareStringAndElementText(parameter1[1], listTypeValueParameterOfMethodInput.get(0));
                    compareStringAndElementText(parameter1[2], listTypeDataValueParameterOfMethodInput.get(0));
                    compareStringAndElementValue(parameter1[3], listDescriptionParameterOfMethodInput.get(0));
                }
                if (!(parameter2 == null)) {
                    compareStringAndElementValue(parameter2[0], listNameParameterOfMethodInput.get(1));
                    compareStringAndElementText(parameter2[1], listTypeValueParameterOfMethodInput.get(1));
                    compareStringAndElementText(parameter2[2], listTypeDataValueParameterOfMethodInput.get(1));
                    compareStringAndElementValue(parameter2[3], listDescriptionParameterOfMethodInput.get(1));
                }
                break;
//            case "POST":
//            case "PUT":
////                val(nameBodyOfMethodInput, nameBodyOfMethod);
////                val(descriptionBodyOfMethodInput, descriptionBodyOfMethod);
////                click(methodActivate);
////                valAndSelectElement(methodInput, method);
//                click(modelRequestActivate);
//                valAndSelectElement(modelRequestInput, " (" + modelsData[1] + ")");
//                click(modelResponseActivate);
//                valAndSelectElement(modelResponseInput, " (" + modelsData[2] + ")");
//                break;
//            case "DELETE":
//                break;
            default:
                throw new AssertionError("Пропущен выбор параметров");
        }
    }


    @Step
    public void searchMethod(String domainName, String serviceName, String methodDescription) {
//        if (!restTab.attr("class").equals("active")) {
//            click(restTab);
//            click(servicesTab);
//        }
//        if (url().equals(base.BaseUrl + "/manager/#/rest/service")) {
//            val(searchInput, serviceName);
//            click(afterSearchServicePath);
//        }
        searchService(domainName, serviceName);
        if (expandGroupOfMethodsButton.attr("aria-label").equals("Развернуть строку"))
            click(expandGroupOfMethodsButton);
        val(searchInput, methodDescription);
    }

    @Step
    public void methodCheckAllParameters(String domainName, String serviceName, String methodName, String method, String methodPath, String nameSopsForRest, String methodTypeDataRequest, String methodTypeDataAnswer, String methodDescription) {
//        if (!servicesTab.attr("class").equals("active")) click(servicesTab);
        searchMethod(domainName, serviceName, methodDescription);

        compareStringAndElementText(methodName, afterSearchMethodName);
        compareStringAndElementText(method, afterSearchethod);
        compareStringAndElementText("/" + methodPath, afterSearchMethodPath);
        compareStringAndElementText(nameSopsForRest, afterSearchMethodLinkToSops);
        compareStringAndElementText(methodTypeDataRequest, afterSearchMethodTypeDataRequest);
        compareStringAndElementText(methodTypeDataAnswer, afterSearchMethodTypeDataAnswer);
        compareStringAndElementText(methodDescription, afterSearchMethodDescription);
    }

    @Step
    public void methodShouldNotBeExist(String domainName, String serviceName, String methodName) {
//        if (!servicesTab.attr("class").equals("active")) click(servicesTab);
        searchMethod(domainName, serviceName, methodName);
        rowAfterSearch.shouldNotBe(exist);
    }

    @Step
    public void addParametrInMethodGet(String[] parameter) {
        click(addParametrOfMethodButton);
        if (parameter[0].equals("a1")) {
            val(nameLastParameterOfMethodInput, parameter[0]);
            click(typeLastParameterOfMethodActivate);
            valAndSelectElement(typeLastParameterOfMethodInput, parameter[1]);
            click(typeLastDataOfMethodActivate);
            valAndSelectElement(typeLastDataOfMethodInput, parameter[2]);
            val(descriptionLastParameterOfMethodInput, parameter[3]);
        } else {
            val(nameLastParameterOfMethodInput, parameter[0]);
            val(descriptionLastParameterOfMethodInput, parameter[3]);
        }
    }

    @Step
    public void editParametrInMethodGet(int numParameter, String nameParameter, String typeParameter, String typeDataParameter, String descriptionParameter) {
        val(listNameParameterOfMethodInput.get(numParameter), nameParameter);
        click(listTypeParameterOfMethodActivate.get(numParameter));
        valAndSelectElement(listTypeParameterOfMethodInput.get(numParameter), typeParameter);
        click(listTypeDataParameterOfMethodActivate.get(numParameter));
        valAndSelectElement(listTypeDataParameterOfMethodInput.get(numParameter), typeDataParameter);
        val(listDescriptionParameterOfMethodInput.get(numParameter), descriptionParameter);
    }

    public void checkSwagger(String domainName, String serviceName, String servicePath, String method, String methodPath, String methodTypeDataReqest, String methodTypeDataAnswer, String parametrValue1, String parametrValue2, String bodyValue) {
//        click(restTab);
        goToDomainRest(domainName);
        click(generalSettingsTab);
        click(swaggerUiLink);
        changeWindow();
        click($x(String.format(methodPathInSwagger, serviceName)));
        click($x(String.format(tryItOutButtonInSwagger, servicePath + "/" + methodPath)));
        switch (method) {
            case "GET":
                val(parametersInputInSwagger.get(0), parametrValue1);
                val(parametersInputInSwagger.get(1), parametrValue2);
                click(executeButtonInSwagger);
                compareStringAndElementText(method + " :  " + parametrValue1 + " " + parametrValue2, serverBodyResponseSwagger);
                compareStringAndElementText(" content-type: " + methodTypeDataAnswer + " ", serverHeadersResponseSwagger);
                compareStringAndElementText("OK", responseDescriptionSwagger);
                break;
            case "POST":
            case "PUT":
                val(bodyInputInSwagger, bodyValue);
                click(executeButtonInSwagger);
                compareStringAndElementText(method + " : " + method, serverBodyResponseSwagger);
                compareStringAndElementText("OK", responseDescriptionSwagger);
                break;
            case "DELETE":
                click(executeButtonInSwagger);
                compareStringAndElementText(method + " и что-нибудь еще", serverBodyResponseSwagger);
//                compareStringAndElementText(" content-type: " + methodTypeData + " ", serverHeadersResponseSwagger);
                serverHeadersResponseSwagger.shouldHave(text("content-type: " + methodTypeDataAnswer));
                compareStringAndElementText("OK", responseDescriptionSwagger);
                break;
            default:
                throw new AssertionError("Пропущен выбор параметров");
        }
    }

    //  Дополнительная конфигурация Rest "Аутентификация HTTP (Локальные пользователи)"
    @Step
    public void createAddConfigurationWithoutSave(String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String[][] users, String[][] roles) {
        click(restTab);
        click(restAdditionalConfigurationTab);
        click(addConfigurationButton);
        click(typeOfObjectActivate);
        valAndSelectElement(typeOfObjectInput, typeOfAdditionalConfiguration);
        val(nameOfAddConfigurationInput, nameOfAdditionalConfiguration);
        click(typeOfAutentificationActivate);
        valAndSelectElement(typeOfAutentificationInput, "Basic");

        for (int x = 0; x < users.length; x++) {
            click(addUserButton);
            val(loginOfUserInput.get(x), users[x][0]);
            val(passwordOfUserInput.get(x), users[x][1]);
            val(rolesOfUserInput.get(x), users[x][2]);
        }

        for (int x = 0; x < roles.length; x++) {
            click(addRoleButton);
            click(expandRoleButton);
            val(pathOfRoleInput.get(x), roles[x][0]);
            val(roleOfRolesInput.get(x), roles[x][2]);
            if (roles[x][1].equals("true") && autentificationOfRoleCheckBox.get(x).isSelected()) {
                click(autentificationOfRoleCheckBox.get(x));
            }
            if (roles[x][1].equals("false") && !autentificationOfRoleCheckBox.get(x).isSelected()) {
                click(autentificationOfRoleCheckBox.get(x));
            }
        }
    }

    @Step
    public void successCreateAddConfiguration(String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String[][] users, String[][] roles) {
        createAddConfigurationWithoutSave(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, users, roles);
        click(saveButton);
    }

    @Step
    public void cancelCreateAddConfiguration(String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String[][] users, String[][] roles) {
        createAddConfigurationWithoutSave(nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, users, roles);
        closeForm();
    }

    //  Дополнительная конфигурация Rest "Пользовательский"
    @Step
    public void createAddConfigurationWithoutSave(String domainName, String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String className, String methodDestroy, String methodFabric, String[][] constructor, String[][] properties) {
        goToDomainRest(domainName);
        click(restAdditionalConfigurationTab);
        click(addConfigurationButton);
        click(typeOfObjectActivate);
        valAndSelectElement(typeOfObjectInput, typeOfAdditionalConfiguration);
        val(nameOfAddConfigurationInput, nameOfAdditionalConfiguration);
        val(classOfAddConfigurationInput, className);
        val(methodDestroyOfAddConfigurationInput, methodDestroy);
        val(methodFabricOfAddConfigurationInput, methodFabric);


        if (constructor != null) {
            for (int x = 0; x < constructor.length; x++) {
                click(addConstructorOfBinButton);
                click(typeConstructorOfBinActivate.get(x));
                valAndSelectElement(typeConstructorOfBinInput.get(x), constructor[x][0]);
                val(valueConstructorOfBinInput.get(x), constructor[x][1]);
            }
        }

        if (properties != null) {
            for (int x = 0; x < properties.length; x++) {
                click(propertiesOfBinButton);
                val(nameProperiesOfBin.get(x), properties[x][0]);
                click(typePropertiesOfBinActivate.get(x));
                valAndSelectElement(typePropertiesOfBinInput.get(x), properties[x][1]);
                val(valuePropertiesOfBinInput.get(x), properties[x][2]);
            }
        }
    }

    @Step
    public void successCreateAddConfiguration(String domainName, String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String className, String methodDestroy, String methodFabric, String[][] constructorsAll, String[][] propertiesAll) {
        createAddConfigurationWithoutSave(domainName, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, className, methodDestroy, methodFabric, constructorsAll, propertiesAll);
        click(saveButton);
    }

    @Step
    public void cancelCreateAddConfiguration(String domainName, String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration, String className, String methodDestroy, String methodFabric, String[][] constructorsAll, String[][] propertiesAll) {
        createAddConfigurationWithoutSave(domainName, nameOfAdditionalConfiguration, typeOfAdditionalConfiguration, className, methodDestroy, methodFabric, constructorsAll, propertiesAll);
        closeForm();
    }

    @Step
    public void addConfigurationCheckAllParameters(String nameOfAdditionalConfiguration, String typeOfAdditionalConfiguration) {
//        if (!servicesTab.attr("class").equals("active")) click(restSettingTab);
        click(restAdditionalConfigurationTab);
        searchAddConfiguration(nameOfAdditionalConfiguration);
        compareStringAndElementText(nameOfAdditionalConfiguration, afterSearcheAdditionalConfigurationName);
        if (typeOfAdditionalConfiguration.equals("LOCAL_USERS")) {
            typeOfAdditionalConfiguration = "Аутентификация HTTP (Локальные пользователи)";
        }
        compareStringAndElementText(typeOfAdditionalConfiguration, afterSearcheAdditionalConfigurationType);
    }

    @Step
    public void addConfigurationShouldBeNotExist(String nameOfAdditionalConfiguration) {
//        if (!servicesTab.attr("class").equals("active")) click(restSettingTab);
        click(restAdditionalConfigurationTab);
        searchAddConfiguration(nameOfAdditionalConfiguration);
        compareStringAndElementText("Нет данных", afterSearcheAdditionalConfigurationName);
    }

    @Step
    public void searchAddConfiguration(String nameOfAdditionalConfiguration) {
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
        click(restAdditionalConfigurationTab);
        val(searchInput, nameOfAdditionalConfiguration);
    }

//    @Step
//    public void addConfigurationShouldNotBeExist(String nameOfAdditionalConfiguration) {
//        if (!restTab.attr("class").contains("active")) click(restTab);
//        click(restAdditionalConfigurationTab);
//        searchAddConfiguration(nameOfAdditionalConfiguration);
//        rowAfterSearch.shouldNotBe(exist);
//    }

    @Step
    public void editAddConfigurationWithoutSave(String domainName, String nameOfAdditionalConfigurationOld, String nameOfAdditionalConfigurationNew, String typeOfAutentificationNew, String[][] users, String[][] roles) {
        click(restTab);
        goToDomainRest(domainName);
        click(restAdditionalConfigurationTab);
        searchAddСonfiguration(domainName, nameOfAdditionalConfigurationOld);
        contextClick(afterSearcheAdditionalConfigurationName);
        click(editInContextMenu);

        val(nameOfAddConfigurationInput, nameOfAdditionalConfigurationNew);
        click(typeOfAutentificationActivate);
        valAndSelectElement(typeOfAutentificationInput, typeOfAutentificationNew);
        click(typeOfAutentificationActivate);
        valAndSelectElement(typeOfAutentificationInput, "Basic");

        for (int x = 0; x < users.length; x++) {
            val(loginOfUserInput.get(x), users[x][0]);
            val(passwordOfUserInput.get(x), users[x][1]);
            val(rolesOfUserInput.get(x), users[x][2]);
        }
        click(deleteRoleButton.get(0));

        for (int x = 0; x < roles.length; x++) {
            click(expandRoleButtonList.get(x));
            click(methodActivate);
            click($$x(String.format(methodInList, roles[x][0])).find(visible));
            val(pathOfRoleInput.get(0), roles[x][1]);

            sout(autentificationOfRoleCheckBox.get(0).parent().getAttribute("class"));
            if (roles[x][2].equals("true") && !autentificationOfRoleCheckBox.get(0).getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(autentificationOfRoleCheckBox.get(0));
            }
            if (roles[x][2].equals("false") && autentificationOfRoleCheckBox.get(0).getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(autentificationOfRoleCheckBox.get(0));
            }

            if (roleOfRolesInput.get(0).exists()) {
                val(roleOfRolesInput.get(0), roles[x][3]);
            }
            click(collapseRoleButtonList.get(0));
        }
        click(deletePathButton.get(0));

    }

    @Step
    public void successEditAddConfiguration(String domainName, String nameOfAdditionalConfigurationOld, String nameOfAdditionalConfigurationNew, String typeOfAutentificationNew, String[][] users, String[][] roles) {
        editAddConfigurationWithoutSave(domainName, nameOfAdditionalConfigurationOld, nameOfAdditionalConfigurationNew, typeOfAutentificationNew, users, roles);
        click(saveButton);
    }

    @Step
    public void cancelEditAddConfiguration(String domainName, String nameOfAdditionalConfigurationOld, String nameOfAdditionalConfigurationNew, String typeOfAutentificationNew, String[][] users, String[][] roles) {
        editAddConfigurationWithoutSave(domainName, nameOfAdditionalConfigurationOld, nameOfAdditionalConfigurationNew, typeOfAutentificationNew, users, roles);
        closeForm();
    }

    @Step
    public void deleteAddConfigurationWithoutSave(String domainName, String nameOfAdditionalConfiguration) {
        click(restTab);
        click(restAdditionalConfigurationTab);
        searchAddСonfiguration(domainName, nameOfAdditionalConfiguration);
        contextClick(afterSearcheAdditionalConfigurationName);
        click(deleteInContextMenu);
    }

    @Step
    public void successDeleteAddConfiguration(String domainName, String nameOfAdditionalConfiguration) {
        deleteAddConfigurationWithoutSave(domainName, nameOfAdditionalConfiguration);
        click(confirmationDeleteButton);
    }

    @Step
    public void cancelDeleteAddConfiguration(String domainName, String nameOfAdditionalConfiguration) {
        deleteAddConfigurationWithoutSave(domainName, nameOfAdditionalConfiguration);
        closeForm();
    }

    @Step
    public void searchAddСonfiguration(String domainName, String serviceName) {
        restTab.shouldBe(enabled);
        if (!restTab.attr("class").equals("active")) {
            click(restTab);
        }
        goToDomainRest(domainName);
        click(restAdditionalConfigurationTab);
        searchInput.clear();
        val(searchInput, serviceName);
    }

    @Step
    public void clearAutentificationInRestSetting(String domainName) {
        goToDomainRest(domainName);
        click(restGeneralConfigurationTab);
        click(autentificationActivate);
        if (autentificationClearButton.isDisplayed())
            click(autentificationClearButton);
        else {
            click(bodyPage);
        }
        click(saveButton);
    }

    @Step
    public void setAutentificationInRestSetting(String domainName, String nameOfAdditionalConfiguration) {
        click(restTab);
        goToDomainRest(domainName);
        click(restGeneralConfigurationTab);
        click(autentificationActivate);
        valAndSelectElement(autentificationInput, nameOfAdditionalConfiguration);
        click(saveButton);
    }

    @Step
    public void checkSettingRest(String domainName, String RestPath) {
        goToDomainRest(domainName);
        click(generalSettingsTab);
        compareStringAndElementValue(RestPath, restPathInput);
    }

    public void goToDomainRest(String domainName) {
        if (!nameDomainInHeader.exists() || !nameDomainInHeader.getText().equals("REST-домен: " + domainName)) {
            restTab.shouldBe(enabled);
            if (!restTab.attr("class").equals("active")) {
                click(restTab);
                if ($x(String.format(specificDomainButtonXpath, domainName)).is(not(visible))) {
                    refresh();
                }
                click($x(String.format(specificDomainButtonXpath, domainName)));
            }
        }
    }

    public void settingRestDomain(String domainName, String staticHost) {
        goToDomainRest("REST");
        click(generalSettingsTab);
        click(restAdressActivate);
        valAndSelectElement(restAdressInput, staticHost);
        click(headersCorsActivate);
        valAndSelectElement(headersCorsInput, "Включить для всех групп методов");
        click(autentificationActivate);
        if (autentificationClearButton.isDisplayed())
            click(autentificationClearButton);
        else {
            click(bodyPage);
        }
        click(saveButton);
        stopDomainRest(domainName);
        runDomainRest(domainName);

    }
}



