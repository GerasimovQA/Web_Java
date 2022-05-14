package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import utils.ConfigProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class perevod extends Base {

    BasePage basePage = new BasePage();
    PerevodPage perevodPage = new PerevodPage();
    Api api = new Api();
    QueueManagerApi queueManagerApi = new QueueManagerApi();

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName());
//        openPage(baseUrl());
    }


    @Test
    public void parsing() {
        ArrayList<String> files = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\Alexey\\Desktop\\factor-esb-autotest111\\src\\test\\java\\ru\\factorts\\page\\namePackage.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                files.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(files);

//        for (String nameClass : files) {
//         if (!findInMap(nameClass).equals("0"))  ;
//            basePage.sout(nameClass.split(":")[1] + ";" + nameClass.split(":")[2].trim() + ";" + findInMap(nameClass).substring(0, 1).toUpperCase() + findInMap(nameClass).substring(1));
//        }


        open("https://findjar.com");

        for (String oneClass : files) {
            String nameClass = oneClass.split(":")[1].trim();
//            basePage.sout(nameClass);
//            basePage.sout(findInMap(nameClass));
            if (!findInMap(nameClass).equals("0")) {
                basePage.sout(oneClass.split(":")[1] + ";" + oneClass.split(":")[2].trim() + ";" + findInMap(nameClass).substring(0, 1).toUpperCase() + findInMap(nameClass).substring(1));
                continue;
            }


            String finalName = oneClass.split(":")[1];
            perevodPage.nameClassInput.val(finalName);
//            basePage.val(perevodPage.nameClassInput, finalName);
            perevodPage.nameClassButton.click();
//            basePage.click(perevodPage.nameClassButton);


            try {
                perevodPage.resultList.get(0).waitUntil(Condition.visible, 5000);
            } catch (AssertionError e) {
                basePage.sout(oneClass.split(":")[1] + ";" + oneClass.split(":")[2] + ";Джарников нет");
                continue;
            }


            ArrayList<String> urls = new ArrayList<>();
            for (SelenideElement unit : perevodPage.resultList) {
                urls.add(unit.attr("href"));
            }
//            basePage.sout("Список вариантов: " + urls);
            String russanAnswer = null;
            for (String unit : urls) {
                russanAnswer = "null";
                open(unit);
                try {
                    perevodPage.nameResponse.waitUntil(Condition.visible, 2000);
                } catch (AssertionError e) {
//                    basePage.sout("Имя не найдено да и по фиг");
                }

                if (perevodPage.description.exists() && !perevodPage.description.getText().equals("")) {
                    String description = perevodPage.description.getText();
//                    basePage.sout(nameClass + "; "+description+"; " + nameClass.split(":")[2]);
//                    basePage.sout("\n\n\nОписание: " + description);
                    open("https://translate.google.ru/");
                    perevodPage.translaterInput.val(description);
//                    basePage.val(perevodPage.translaterInput, description);
                    sleep(1000);
                    russanAnswer = perevodPage.translaterOutput.getText();
                    basePage.sout(oneClass.split(":")[1] + ";" + oneClass.split(":")[2].trim() + ";" + russanAnswer.substring(0, 1).toUpperCase() + russanAnswer.substring(1));


                    open("https://findjar.com");
                    break;
                }
            }
            if (russanAnswer.equals("null")) {
                basePage.sout(oneClass.split(":")[1] + ";" + oneClass.split(":")[2].trim() + ";Нет описания");
            }

        }
    }


    public String findInMap(String key) {
        String[] names = {"org.springframework.integration/spring-integration-core", "junit/junit", "org.apache.camel/camel-core", "org.apache.camel/camel-jms", "org.apache.camel/camel-cache", "org.apache.camel/camel-ehcache", "org.apache.camel/camel-metrics", "org.apache.camel/camel-jetty9 ", "org.apache.camel/camel-script ", "org.apache.camel/camel-ftp ", "org.apache.camel/camel-mail ", "org.apache.camel/camel-sql ", "org.apache.camel/camel-ssh ", "org.apache.camel/camel-test ", "org.apache.camel/camel-test-spring", "org.apache.camel/camel-leveldb ", "org.apache.camel/camel-exec ", "org.apache.camel/camel-spring-ws ", "org.apache.camel/camel-jaxb ", "org.apache.camel/camel-xstream ", "org.apache.camel/camel-cxf ", "org.apache.camel/camel-syslog", "org.apache.camel/camel-mina2", "org.apache.camel/camel-xmljson", "org.apache.camel/camel-spark-rest", "org.apache.activemq/activemq-broker", "org.apache.activemq/activemq-camel", "org.slf4j/slf4j-simple", "org.slf4j/slf4j-api", "org.slf4j/jcl-over-slf4j", "commons-httpclient/commons-httpclient", "junit/junit", "org.mockito/mockito-all", "org.projectlombok/lombok", "org.slf4j/slf4j-api", "org.jenkins-ci.plugins/git", "xerces/xercesImpl", "com.google.guava/guava", "com.fasterxml.jackson.core/jackson-core", "com.fasterxml.jackson.core/jackson-databind", "com.fasterxml.jackson.core/jackson-annotations", "org.reflections/reflections", "org.jenkins-ci.main/jenkins-core", "commons-io/commons-io", "commons-lang/commons-lang", "net.lingala.zip4j/zip4j", "com.fasterxml.jackson.dataformat/jackson-dataformat-xml", "org.springframework.boot/spring-boot-starter-web", "org.springframework.boot/spring-boot-starter-test", "org.springframework.boot/spring-boot-starter-websocket", "javax.servlet/javax.servlet-api", "org.springframework/spring-aspects", "org.jooq/joox", "org.codehaus.plexus/plexus-archiver", "org.yaml/snakeyaml", "org.springframework.boot/spring-boot-starter-activemq", "org.springframework.boot/spring-boot-configuration-processor", "org.springframework.boot/spring-boot-starter-test", "junit/junit", "org.mockito/mockito-all", "org.apache.activemq/activemq-spring", "org.apache.activemq/activemq-kahadb-store", "org.apache.activemq/activemq-pool", "org.apache.activemq/activemq-stomp", "org.apache.activemq/activemq-mqtt", "org.apache.activemq/activemq-jaas", "org.apache.activemq/activemq-http", "org.apache.activemq/activemq-amqp", "org.springframework.boot/spring-boot-configuration-processor", "org.springframework.boot/spring-boot-starter-websocket", "junit/junit", "org.mockito/mockito-all", "org.springframework.ldap/spring-ldap-core", "commons-codec/commons-codec", "org.apache.camel/camel-http4", "org.apache.camel/camel-stream", "org.apache.camel/camel-sql", "org.apache.camel/camel-milo", "org.apache.camel/camel-telegram", "org.apache.camel/camel-websocket", "org.apache.camel/camel-barcode", "org.apache.camel/camel-milo", "org.apache.camel/camel-csv", "org.springframework/spring-test", "javax/javaee-api", "org.hibernate/hibernate-validator", "com.atomikos/transactions-jdbc", "com.atomikos/transactions-jms", "com.atomikos/transactions", "com.atomikos/transactions-jta", "org.apache.cxf/cxf-core", "org.springframework.boot/spring-boot-configuration-processor", "junit/junit ", "org.springframework.ldap/spring-ldap-core ", "org.apache.camel/camel-test ", "commons-io/commons-io", "org.springframework.boot/spring-boot-configuration-processor ", "org.springframework.boot/spring-boot-test-autoconfigure ", "junit/junit ", "org.mockito/mockito-all ", "org.apache.camel/camel-test ", "org.springframework/spring-test", "org.springframework.boot/spring-boot-configuration-processor ", "org.springframework.boot/spring-boot-starter-web ", "org.springframework.boot/spring-boot-starter-websocket ", "org.springframework.boot/spring-boot-starter-security ", "commons-io/commons-io", "org.springframework.boot/spring-boot-starter-thymeleaf ", "org.springframework.boot/spring-boot-starter-jetty ", "org.springframework.boot/spring-boot-actuator ", "org.springframework.boot/spring-boot-devtools ", "org.springframework.boot/spring-boot-configuration-processor ", "org.springframework.boot/spring-boot-starter-test ", "org.eclipse.jetty.websocket/javax-websocket-server-impl ", "org.eclipse.jetty.websocket/websocket-client", "org.eclipse.jetty.websocket/websocket-server", "junit/junit ", "org.eclipse.jetty/jetty-webapp ", "org.eclipse.jetty/jetty-annotations ", "org.eclipse.jetty/jetty-plus ", "org.springframework.security/spring-security-core ", "commons-lang/commons-lang", "org.jooq/jooq ", "org.jooq/jooq-meta ", "com.h2database/h2", "org.jooq/jooq ", "org.jooq/jooq-meta ", "org.apache.ignite/ignite-core ", "org.apache.ignite/ignite-spring ", "org.apache.ignite/ignite-indexing ", "org.jsoup/jsoup", "org.projectlombok/lombok ", "org.slf4j/jcl-over-slf4j ", "org.slf4j/slf4j-simple ", "org.slf4j/slf4j-api ", "org.hibernate/hibernate-validator ", "com.google.guava/guava ", "com.squareup.retrofit2/retrofit   ", "com.squareup.retrofit2/converter-gson", "commons-dbcp/commons-dbcp ", "com.google.code.gson/gson ", "commons-io/commons-io ", "commons-lang/commons-lang ", "org.springframework/spring-test ", "org.jooq/joox ", "org.codehaus.plexus/plexus-archiver ", "org.yaml/snakeyaml ", "junit/junit ", "org.apache.activemq/activemq-web ", "org.springframework.ldap/spring-ldap-core ", "org.rzo.yajsw/wrapper ", "org.springframework.security/spring-security-ldap ", "org.reflections/reflections ", "commons-beanutils/commons-beanutils ", "net.lingala.zip4j/zip4j ", "org.springframework.boot/spring-boot-starter-security ", "javax.servlet/javax.servlet-api ", "org.apache.camel/camel-leveldb ", "org.springframework.data/spring-data-commons", "commons-fileupload/commons-fileupload "};
        String[] values = {"Интеграционное ядро Spring", "Тестовый фреймворк для Java", "Ядро основного роутера Camel Java DSL", "Поддержка Camel JMS", "Поддержка Camel Cache на основе ehCache", "Поддержка Camel Ehcache", "Основной компонент мониторинга Camel Metrics", "Поддержка Camel Jetty9", "Поддержка Camel Script", "Поддержка Camel FTP", "Поддержка Camel Mail", "Поддержка Camel SQL", "Поддержка Camel SSH", "Модульное тестирование Camel", "Модульное тестирование Camel со Spring", "Использование LevelDB как EIP базу", "Поддержка Camel для выполнения системных команд", "Поддержка Camel для Spring Web Services", "Поддержка Camel JAXB", "Поддержка Camel XStream", "Поддержка Camel CXF", "Поддержка Camel Syslog", "Поддержка Camel MINA 2.x", "Camel XML JSON формат данных", "Поддержка Camel Spark (REST DSL)", "Реализация брокера сообщений", "Компонент для Camel", "Простое связывание", "Отвечает за запись событий в протоколы работы", "JCL 1.2 реализация над SLF4J", "http-клиент", "", "Тестовый фреймворк для Java", "Библиотека макетных объектов для java", "Автоматические создание геттеров, сеттеров и т.д.", "Интеграция Jenkins с GIT SCM", "XML парсер в семействе Apache Xerces", "Набор основных и расширенных библиотек", "Обработка абстракций, реализация для JSON", "Общие функции привязки данных для Jackson работают над API потоковой передачи данных", "Основные аннотации, используемые для типов значений", "Отражения - анализ метаданных во время выполнения Java", "Jenkins и файлы просмотра для визуализации HTML", "The Apache Commons IO library contains utility classes, stream implementations, file filters, file comparators, endian transformation classes, and much more", "Библиотека ввода-вывода Apache Commons содержит классы утилиты, потоковые реализации, фильтры файлов, файловые компараторы, классы преобразования и др.", "Java-библиотека с открытым исходным кодом для обработки zip-файлов", "Расширенный формат данных для Jackson", "Стартер для создания сети, включая RESTful, приложения, использующие Spring MVC. Использует Tomcat как встроенный контейнер по умолчанию", "Стартер для тестирования приложений Spring Boot с библиотеками, включая JUnit, Hamcrest и Mockito", "Стартер для создания приложений WebSocket с использованием поддержки WebSocket Spring Framework", "Java Servlet API", "Модуль Spring предназначенный для реализации аспектно-ориентированного программирования", "jOOX (JOOX Object Oriented XML) – это простая оболочка для пакета org.w3c.dom, позволяющая создавать и манипулировать XML-документами, где требуется DOM", "Предназначен для создания и извлечения архивов с унифицированным API Archiver / UnArchiver независимо от формата архива", "YAML 1.1 parser для Java", "Стартер для обмена сообщениями JMS с использованием Apache ActiveMQ", "Процессор конфигурации Spring Boot", "Стартер для тестирования приложений Spring Boot с библиотеками, включая JUnit, Hamcrest и Mockito", "Единый модуль тестирования Java", "Библиотека Mock-объектов для java", "Интеграция с ActiveMQ Spring", "Реализация базы данных ActiveMQ KahaDB", "О  твечает за реализацию пула потоков при потреблении сообщений из локального менеджера очередей", "Реализация протокола ActiveMQ STOMP", "Реализация протокола ActiveMQ MQTT", "Коллекция универсальных модулей входа JAAS", "Поддержка протокола HTTP ActiveMQ", "Реализует протокол обмена сообщениями AMQP", "Процессор конфигурации Spring Boot", "Стартер для создания приложений WebSocket с использованием поддержки WebSocket Spring Framework", "Единый модуль тестирования Java", "Библиотека Mock-объектов для java", "Spring ядро LDAP", "Пакет Apache Commons Codec содержит простые кодировщики и декодеры для различных форматов, таких как Base64 и Hexadecimal. Также поддерживает набор утилит фонетического кодирования", "Поддержка Camel HTTP (Apache HttpClient 4.x)", "Поддержка Camel Stream (System.in, System.out, System.err)", "Поддержка Camel SQL", "Поддержка Camel OPC UA", "Поддержка Camel Telegram", "Camel WebSocket с использованием Jetty", "Поддержка штрих-кода Camel (например, QRcode, PDF417, DataMatrix)", "Поддержка Camel OPC UA", "Поддержка формата данных Camel CSV", "Spring TestContext Framework", "API спецификаций Java (TM) EE 8", "Предназначен для валибации входных данных, полученных от пользователя, при работе со встроенной базой данных.", "Транзакции JDBC", "Транзакции JMS", "Транзакции Core", "Транзакции JTA", "Предназначен для реализации сервисов с использованием API-интерфейсов, таких как JAX-WS и JAX-RS", "Процессор конфигурации Spring Boot Boot", "Единый модуль тестирования Java", "Spring ядро LDAP", "Модульное тестирование Camel", "Библиотека ввода-вывода Apache Commons содержит классы утилиты, потоковые реализации, фильтры файлов, файловые компараторы, классы преобразования и др.", "Процессор конфигурации Spring Boot Boot", "Тест загрузки Spring Boot Auto-Configure", "Единый модуль тестирования Java", "Библиотека Mock-объектов для java", "Модульное тестирование Camel", "Предназначен для интеграционного тестирования", "Процессор конфигурации Spring Boot Boot", "Стартер для создания сети, включая RESTful, приложения, использующие Spring MVC. Использует Tomcat как встроенный контейнер по умолчанию", "Стартер для создания приложений WebSocket с использованием поддержки WebSocket Spring Framework", "Стартер для использования Spring Security", "Библиотека ввода-вывода Apache Commons содержит классы утилиты, потоковые реализации, фильтры файлов, файловые компараторы, классы преобразования endian и др.", "Стартер для создания веб-приложений MVC с использованием представлений Thymeleaf", "Стартер для использования Jetty в качестве встроенного контейнера сервлетов.", "Spring Boot Actuator", "Инструменты для загрузки Spring Boot", "Процессор конфигурации Spring Boot", "Стартер для тестирования приложений Spring Boot с библиотеками, включая JUnit, Hamcrest и Mockito", "Реализация Javax WebSocket сервера", "Клиент Jetty Websocket", "Сервер Jetty Websocket", "Единый модуль тестирования Java", "Поддержка веб-приложений Jetty", "Поддержка аннотаций для развертывания сервлетов", "Услуги Jetty JavaEE", "Ядро spring-security", "Commons Lang - пакет служебных классов Java для классов, которые находятся в иерархии java.lang", "JOOQ", "JOOQ Meta", "Двигатель базы данных H2", "JOOQ", "JOOQ Meta", "Java-промежуточное программное обеспечение для обработки больших данных в памяти в распределенной среде", "Java-промежуточное программное обеспечение для обработки больших данных в памяти в распределенной среде", "Java-промежуточное программное обеспечение для обработки больших данных в памяти в распределенной среде", "jsoup – это Java-библиотека для работы с реальным HTML. Предоставляет удобный API для извлечения и управления данными, используя DOM, CSS и jQuery-подобные методы. jsoup реализует спецификацию WHATWG HTML5 и анализирует HTML на ту же DOM, что и современные браузеры", "Автоматическое создание геттеров, сеттеров и т.д.", "JCL 1.2 реализован через SLF4J", "SLF4J Простая привязка", "Предназначен для логирования событий", "Hibernate Validator Engine Relocation Artifact", "Набор основных и расширенных библиотек, которые включают классы утилиты, коллекцию google, классы io и др.", "HTTP-клиент", "Конвертер", "Общий доступ к базе данных Commons", "Для работы с объектами типа Gson", "Библиотека ввода-вывода Apache Commons содержит классы утилиты, потоковые реализации, фильтры файлов, файловые компараторы, классы преобразования", "Commons Lang - пакет служебных классов Java для классов, которые находятся в иерархии java.lang", "Предназначен для интеграционного тестирования", "jOOX (JOOX Object Oriented XML) – это простая оболочка для пакета org.w3c.dom, позволяющая свободно создавать и манипулировать XML-документами, где требуется DOM", "Предназначен для создания и извлечения архивов с унифицированным API Archiver / UnArchiver независимо от формата архива", "Анализатор YAML 1.1 для Java", "Единый модуль тестирования Java", "Веб-коннектор для поддержки REST API и Streamlets", "Ядро Spring LDAP", "java-ориентированная реализация оболочки Java-сервиса tanuki (JSW). Обеспечивает переключение с JSW на YAJSW", "Предназначен для аутентификации в приложении с использованием LDAP сервера", "Отражения - анализ метаданных во время выполнения Java", "Apache Commons BeanUtils предоставляет инструменты, необходимые для работы с Java бинами", "Java-библиотека с открытым исходным кодом для обработки zip-файлов", "Стартер для использования Spring Security", "API-интерфейс Java Servlet", "Использование LevelDB в качестве постоянного хранилища EIP", "Ядро данных Spring", "Компонент Apache Commons FileUpload обеспечивает добавление поддержки многопользовательской загрузки файлов в сервлеты и веб-приложения"};
        HashMap<String, String> table = new HashMap<String, String>();
        for (int i = 0; i < names.length; i++) {
//            basePage.sout(names[i].split("/")[1]);
            table.put(names[i].split("/")[1], values[i]);
        }

//        basePage.sout(table.size());
//        basePage.sout(table.get(key));

        if (table.get(key) != null) {
            return table.get(key);
        }
        return "0";
    }

    @Test
    public void asds() {
        String cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerApi.listOfMessagesInQueueShouldBeEmptyAPI(cook1, "123", baseUrl());
    }
}
