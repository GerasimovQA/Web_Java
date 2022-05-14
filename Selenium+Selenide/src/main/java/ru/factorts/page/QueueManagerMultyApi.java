package ru.factorts.page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.sleep;
import static io.restassured.RestAssured.given;

public class QueueManagerMultyApi extends Base {

    CommonComponents commonComponents;

    public enum typeQueue {local, virtual, fragment, aggregate, TRANSPORT}

    public enum queueCharacteristic {queueSize, consumersCount, producersCount, enqueued, dequeued, queueType, channel}

    public void createManagerAPI(String Cookies, String url, String managerName) {
        String body = "{\"id\":\"" + managerName + "\",\"type\":\"local\",\"start\":true,\"localAccessAllowed\":false,\"schedulerSupport\":false,\"networkConnectorStartAsync\":false,\"auth\":{\"localAccessAllowed\":true,\"anonymousAccessAllowed\":true,\"anonymousUser\":\"anonymous\",\"anonymousGroup\":\"anonymous\",\"ldapConfig\":{\"url\":\"\",\"baseDn\":\"\",\"bindDn\":\"\",\"bindPassword\":\"\",\"userBaseDn\":\"\",\"userIdAttribute\":\"\",\"userPasswordAttribute\":\"\",\"roleBaseDn\":\"\",\"roles\":\"\",\"enabled\":\"\",\"roleMap\":{}}},\"connectors\":[],\"users\":[],\"groupAcls\":[{\"id\":\"ALL_QUEUES\",\"queue\":\">\",\"admin\":\"admin, anonymous\",\"read\":\"read, anonymous\",\"write\":\"write, anonymous\"},{\"id\":\"ALL_TOPICS\",\"topic\":\">\",\"admin\":\"admin, anonymous\",\"read\":\"read, anonymous\",\"write\":\"write, anonymous\"},{\"id\":\"ADVISORY\",\"topic\":\"ActiveMQ.Advisory.>\",\"admin\":\"*\",\"read\":\"*\",\"write\":\"*\"}],\"policies\":[{\"type\":\"queue\",\"deadLetterStrategy\":{\"type\":\"individual\",\"processNonPersistent\":true,\"processExpired\":false,\"enableAudit\":true,\"expiration\":0,\"destinationPerDurableSubscriber\":false,\"maxAuditDepth\":2048,\"maxProducersToAudit\":64,\"queuePrefix\":\"DLQ.\",\"queueSuffix\":\"\",\"topicPrefix\":\"DLQ.Topic.\",\"topicSuffix\":\"\",\"useQueueForQueueMessages\":true,\"useQueueForTopicMessages\":true},\"messageGroupMapStrategy\":null,\"pendingQueuePolicy\":\"DEFAULT\",\"maxProducersToAudit\":64,\"maxAuditDepth\":10000,\"maxQueueAuditDepth\":10000,\"enableAudit\":true,\"producerFlowControl\":true,\"blockedProducerWarningInterval\":30000,\"maxPageSize\":200,\"maxBrowsePageSize\":400,\"useCache\":true,\"minimumMessageSize\":1024,\"expireMessagesPeriod\":30000,\"advisoryForSlowConsumers\":false,\"advisoryForFastProducers\":false,\"advisoryWhenFull\":false,\"advisoryForDelivery\":false,\"advisoryForConsumed\":false,\"includeBodyForAdvisory\":false,\"queueBrowserPrefetch\":500,\"usePrefetchExtension\":true,\"storeUsageHighWaterMark\":100,\"prioritizedMessages\":true,\"cursorMemoryHighWaterMark\":70,\"gcInactiveDestinations\":false,\"gcWithNetworkConsumers\":false,\"inactiveTimeoutBeforeGC\":60000,\"reduceMemoryFootprint\":true,\"networkBridgeFilterStrategy\":{\"type\":\"default\"},\"doOptimzeMessageStorage\":true,\"maxDestinations\":-1,\"useTopicSubscriptionInflightStats\":true,\"queue\":\">\",\"allConsumersExclusiveByDefault\":false,\"consumersBeforeDispatchStarts\":0,\"lazyDispatch\":false,\"maxExpirePageSize\":500,\"optimizedDispatch\":false,\"queuePrefetch\":1000,\"strictOrderDispatch\":false,\"timeBeforeDispatchStarts\":0,\"useConsumerPriority\":true},{\"type\":\"topic\",\"deadLetterStrategy\":{\"type\":\"individual\",\"processNonPersistent\":true,\"processExpired\":false,\"enableAudit\":true,\"expiration\":0,\"destinationPerDurableSubscriber\":false,\"maxAuditDepth\":2048,\"maxProducersToAudit\":64,\"queuePrefix\":\"DLQ.Topic.\",\"queueSuffix\":\"\",\"topicPrefix\":\"DLQ.Topic.\",\"topicSuffix\":\"\",\"useQueueForQueueMessages\":true,\"useQueueForTopicMessages\":true},\"messageGroupMapStrategy\":null,\"pendingQueuePolicy\":\"DEFAULT\",\"maxProducersToAudit\":64,\"maxAuditDepth\":10000,\"maxQueueAuditDepth\":10000,\"enableAudit\":true,\"producerFlowControl\":true,\"blockedProducerWarningInterval\":30000,\"maxPageSize\":200,\"maxBrowsePageSize\":400,\"useCache\":true,\"minimumMessageSize\":1024,\"expireMessagesPeriod\":30000,\"advisoryForSlowConsumers\":false,\"advisoryForFastProducers\":false,\"advisoryWhenFull\":false,\"advisoryForDelivery\":false,\"advisoryForConsumed\":false,\"includeBodyForAdvisory\":false,\"queueBrowserPrefetch\":500,\"usePrefetchExtension\":true,\"storeUsageHighWaterMark\":100,\"prioritizedMessages\":true,\"cursorMemoryHighWaterMark\":70,\"gcInactiveDestinations\":false,\"gcWithNetworkConsumers\":false,\"inactiveTimeoutBeforeGC\":60000,\"reduceMemoryFootprint\":true,\"networkBridgeFilterStrategy\":{\"type\":\"default\"},\"doOptimzeMessageStorage\":true,\"maxDestinations\":-1,\"useTopicSubscriptionInflightStats\":true,\"topic\":\">\",\"alwaysRetroactive\":false,\"topicPrefetch\":32676,\"durableTopicPrefetch\":100,\"advisoryForDiscardingMessages\":false}],\"limits\":null,\"store\":{\"type\":\"kahaDB\",\"directory\":\"${FESB_DATA}/qms/${FESB_MQ_NAME}\",\"journalDiskSyncInterval\":1000,\"checkpointInterval\":5000,\"cleanupInterval\":30000,\"journalMaxFileLength\":33554432,\"journalMaxWriteBatchSize\":4194304,\"enableIndexWriteAsync\":false,\"indexWriteBatchSize\":1000,\"preallocationScope\":\"ENTIRE_JOURNAL\",\"preallocationStrategy\":\"ZEROS\",\"journalDiskSyncStrategy\":\"NEVER\",\"maxAsyncJobs\":10000,\"ignoreMissingJournalfiles\":false,\"indexCacheSize\":10000,\"checkForCorruptJournalFiles\":false,\"checksumJournalFiles\":true,\"forceRecoverIndex\":false,\"archiveCorruptedIndex\":false,\"useIndexLFRUEviction\":false,\"indexLFUEvictionFactor\":0.2,\"enableIndexDiskSyncs\":true,\"enableIndexRecoveryFile\":true,\"enableIndexPageCaching\":true,\"enableAckCompaction\":true,\"compactAcksAfterNoGC\":10,\"compactAcksIgnoresStoreGrowth\":false,\"concurrentStoreAndDispatchQueues\":true,\"concurrentStoreAndDispatchTopics\":false,\"failoverProducersAuditDepth\":2048,\"maxFailoverProducersToTrack\":64,\"locker\":{\"type\":\"shared-file\",\"failIfLocked\":false,\"lockAcquireSleepInterval\":100}}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании менеджера очередей: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void createManagerAPI(String Cookies, String url, String managerName, Boolean autostart, String port) {
        String body = "{\"id\":\"" + managerName + "\",\"type\":\"local\",\"start\":" + autostart + ",\"localAccessAllowed\":false,\"schedulerSupport\":false,\"networkConnectorStartAsync\":false,\"auth\":{\"localAccessAllowed\":true,\"anonymousAccessAllowed\":true,\"anonymousUser\":\"anonymous\",\"anonymousGroup\":\"anonymous\",\"ldapConfig\":{\"url\":\"\",\"baseDn\":\"\",\"bindDn\":\"\",\"bindPassword\":\"\",\"userBaseDn\":\"\",\"userIdAttribute\":\"\",\"userPasswordAttribute\":\"\",\"roleBaseDn\":\"\",\"roles\":\"\",\"enabled\":\"\",\"roleMap\":{}}},\"connectors\":[{\"uri\":\"tcp://0.0.0.0:" + port + "?maximumConnections=1000&wireFormat.maxFrameSize=1048576000\",\"connections\":[],\"allowLinkStealing\":false,\"enableStatusMonitor\":false,\"updateClusterClients\":false,\"rebalanceClusterClients\":false,\"updateClusterClientsOnRemove\":false,\"auditNetworkProducers\":false,\"disableAsyncDispatch\":false,\"name\":\"openwire\"}],\"users\":[],\"groupAcls\":[{\"id\":\"ALL_QUEUES\",\"queue\":\">\",\"admin\":\"admin, anonymous\",\"read\":\"read, anonymous\",\"write\":\"write, anonymous\"},{\"id\":\"ALL_TOPICS\",\"topic\":\">\",\"admin\":\"admin, anonymous\",\"read\":\"read, anonymous\",\"write\":\"write, anonymous\"},{\"id\":\"ADVISORY\",\"topic\":\"ActiveMQ.Advisory.>\",\"admin\":\"*\",\"read\":\"*\",\"write\":\"*\"}],\"policies\":[{\"type\":\"queue\",\"deadLetterStrategy\":{\"type\":\"individual\",\"processNonPersistent\":true,\"processExpired\":false,\"enableAudit\":true,\"expiration\":0,\"destinationPerDurableSubscriber\":false,\"maxAuditDepth\":2048,\"maxProducersToAudit\":64,\"queuePrefix\":\"DLQ.\",\"queueSuffix\":\"\",\"topicPrefix\":\"DLQ.Topic.\",\"topicSuffix\":\"\",\"useQueueForQueueMessages\":true,\"useQueueForTopicMessages\":true},\"messageGroupMapStrategy\":null,\"pendingQueuePolicy\":\"DEFAULT\",\"maxProducersToAudit\":64,\"maxAuditDepth\":10000,\"maxQueueAuditDepth\":10000,\"enableAudit\":true,\"producerFlowControl\":true,\"blockedProducerWarningInterval\":30000,\"maxPageSize\":200,\"maxBrowsePageSize\":400,\"useCache\":true,\"minimumMessageSize\":1024,\"expireMessagesPeriod\":30000,\"advisoryForSlowConsumers\":false,\"advisoryForFastProducers\":false,\"advisoryWhenFull\":false,\"advisoryForDelivery\":false,\"advisoryForConsumed\":false,\"includeBodyForAdvisory\":false,\"queueBrowserPrefetch\":500,\"usePrefetchExtension\":true,\"storeUsageHighWaterMark\":100,\"prioritizedMessages\":true,\"cursorMemoryHighWaterMark\":70,\"gcInactiveDestinations\":false,\"gcWithNetworkConsumers\":false,\"inactiveTimeoutBeforeGC\":60000,\"reduceMemoryFootprint\":true,\"networkBridgeFilterStrategy\":{\"type\":\"default\"},\"doOptimzeMessageStorage\":true,\"maxDestinations\":-1,\"useTopicSubscriptionInflightStats\":true,\"queue\":\">\",\"allConsumersExclusiveByDefault\":false,\"consumersBeforeDispatchStarts\":0,\"lazyDispatch\":false,\"maxExpirePageSize\":500,\"optimizedDispatch\":false,\"queuePrefetch\":1000,\"strictOrderDispatch\":false,\"timeBeforeDispatchStarts\":0,\"useConsumerPriority\":true},{\"type\":\"topic\",\"deadLetterStrategy\":{\"type\":\"individual\",\"processNonPersistent\":true,\"processExpired\":false,\"enableAudit\":true,\"expiration\":0,\"destinationPerDurableSubscriber\":false,\"maxAuditDepth\":2048,\"maxProducersToAudit\":64,\"queuePrefix\":\"DLQ.Topic.\",\"queueSuffix\":\"\",\"topicPrefix\":\"DLQ.Topic.\",\"topicSuffix\":\"\",\"useQueueForQueueMessages\":true,\"useQueueForTopicMessages\":true},\"messageGroupMapStrategy\":null,\"pendingQueuePolicy\":\"DEFAULT\",\"maxProducersToAudit\":64,\"maxAuditDepth\":10000,\"maxQueueAuditDepth\":10000,\"enableAudit\":true,\"producerFlowControl\":true,\"blockedProducerWarningInterval\":30000,\"maxPageSize\":200,\"maxBrowsePageSize\":400,\"useCache\":true,\"minimumMessageSize\":1024,\"expireMessagesPeriod\":30000,\"advisoryForSlowConsumers\":false,\"advisoryForFastProducers\":false,\"advisoryWhenFull\":false,\"advisoryForDelivery\":false,\"advisoryForConsumed\":false,\"includeBodyForAdvisory\":false,\"queueBrowserPrefetch\":500,\"usePrefetchExtension\":true,\"storeUsageHighWaterMark\":100,\"prioritizedMessages\":true,\"cursorMemoryHighWaterMark\":70,\"gcInactiveDestinations\":false,\"gcWithNetworkConsumers\":false,\"inactiveTimeoutBeforeGC\":60000,\"reduceMemoryFootprint\":true,\"networkBridgeFilterStrategy\":{\"type\":\"default\"},\"doOptimzeMessageStorage\":true,\"maxDestinations\":-1,\"useTopicSubscriptionInflightStats\":true,\"topic\":\">\",\"alwaysRetroactive\":false,\"topicPrefetch\":32676,\"durableTopicPrefetch\":100,\"advisoryForDiscardingMessages\":false}],\"limits\":null,\"store\":{\"type\":\"kahaDB\",\"directory\":\"${FESB_DATA}/qms/${FESB_MQ_NAME}\",\"journalDiskSyncInterval\":1000,\"checkpointInterval\":5000,\"cleanupInterval\":30000,\"journalMaxFileLength\":33554432,\"journalMaxWriteBatchSize\":4194304,\"enableIndexWriteAsync\":false,\"indexWriteBatchSize\":1000,\"preallocationScope\":\"ENTIRE_JOURNAL\",\"preallocationStrategy\":\"ZEROS\",\"journalDiskSyncStrategy\":\"NEVER\",\"maxAsyncJobs\":10000,\"ignoreMissingJournalfiles\":false,\"indexCacheSize\":10000,\"checkForCorruptJournalFiles\":false,\"checksumJournalFiles\":true,\"forceRecoverIndex\":false,\"archiveCorruptedIndex\":false,\"useIndexLFRUEviction\":false,\"indexLFUEvictionFactor\":0.2,\"enableIndexDiskSyncs\":true,\"enableIndexRecoveryFile\":true,\"enableIndexPageCaching\":true,\"enableAckCompaction\":true,\"compactAcksAfterNoGC\":10,\"compactAcksIgnoresStoreGrowth\":false,\"concurrentStoreAndDispatchQueues\":true,\"concurrentStoreAndDispatchTopics\":false,\"failoverProducersAuditDepth\":2048,\"maxFailoverProducersToTrack\":64,\"locker\":{\"type\":\"shared-file\",\"failIfLocked\":false,\"lockAcquireSleepInterval\":100}}}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании менеджера очередей: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void startManagerAPI(String Cookies, String url, String managerName) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/start").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при старте менеджера очередей: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        sleep(3000);
    }

    public void deleteManagerAPI(String Cookies, String url, String managerName, Boolean deleteData) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        queryParam("shouldDeleteDb", deleteData).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении менеджера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void createQueueLocalAPI(String Cookies, String url, String managerName, typeQueue type, String nameQueue) {
        String body = "\"" + nameQueue + "\"";
        String basePath = "/manager/api/qms/local/brokers/" + managerName + "/queues";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании " + type + " очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertEquals(waitingResponse, response.getBody().asString());
        sleep(1000);
    }

    public void createChannelAPI(String Cookies, String managerName, typeQueue type, String nameQueue, String nameTopic, String nameCanal, boolean duplex, String url, String port, String userName, String userPassword) {
        String body = "{\"uri\":\"static:(tcp://" + url + ":" + port + ")?useExponentialBackOff=true&backOffMultiplier=2&initialReconnectDelay=10&maxReconnectDelay=30000\",\"activeBridges\":[],\"staticallyIncludedDestinations\":[{\"type\":\"queue\",\"name\":\"" + nameQueue + "\"},{\"type\":\"topic\",\"name\":\"" + nameTopic + "\"}],\"dynamicallyIncludedDestinations\":[],\"excludedDestinations\":[],\"durableDestinations\":[],\"conduitSubscriptions\":true,\"conduitNetworkQueueSubscriptions\":false,\"useVirtualDestSubs\":false,\"dynamicOnly\":false,\"syncDurableSubs\":false,\"dispatchAsync\":true,\"decreaseNetworkConsumerPriority\":false,\"consumerPriorityBase\":-5,\"duplex\":" + duplex + ",\"bridgeTempDestinations\":true,\"prefetchSize\":1000,\"advisoryPrefetchSize\":0,\"advisoryAckPercentage\":75,\"networkTTL\":1,\"consumerTTL\":1,\"messageTTL\":1,\"brokerName\":\"localhost\",\"brokerURL\":\"\",\"userName\":\"" + userName + "\",\"password\":\"" + userPassword + "\",\"name\":\"" + nameCanal + "\",\"clientIdToken\":\"_\",\"suppressDuplicateQueueSubscriptions\":false,\"suppressDuplicateTopicSubscriptions\":false,\"alwaysSyncSend\":true,\"staticBridge\":false,\"useCompression\":false,\"advisoryForFailedForward\":false,\"useBrokerNamesAsIdSeed\":true,\"gcDestinationViews\":true,\"gcSweepTime\":60000,\"checkDuplicateMessagesOnDuplex\":false}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(BaseUrl).basePath("/manager/api/qms/local/brokers/" + managerName + "/network-connectors").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании " + type + " очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains(waitingResponse));
    }

    public void createQueueVirtualSegmentationAggregationAPI(String Cookies, String managerName, typeQueue type, String queueName, String queueName2, String topicName2) {
        String body = null;
        String basePath = null;
        String waitingResponse = null;

        body = "\"" + queueName + "\"";
        basePath = "/manager/api/qms/local/brokers/" + managerName + "/queues";

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

        switch (type) {
            case virtual:
                body = "{\"type\":\"compositeQueue\",\"name\":\"" + queueName + "\",\"forwardTo\":[{\"type\":\"queue\",\"name\":\"" + queueName2 + "\"},{\"type\":\"topic\",\"name\":\"" + topicName2 + "\"}],\"forwardOnly\":true,\"concurrentSend\":false}";
                basePath = "/manager/api/qms/local/brokers/" + managerName + "/composite-queues";

                response =
                        given().log().all().
                                header("Cookie", Cookies).
                                contentType(ContentType.JSON).
                                body(body).
                                baseUri(BaseUrl).basePath(basePath).
                                when().post().
                                then().extract().response();

                basePage.sout("Ответ при конвернтации локальной очереди в  " + type + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
                Assert.assertEquals(200, response.then().extract().statusCode());
                break;
            case fragment:
                body = "{\"consumersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000},\"workersConfiguration\":{\"coreThreads\":1,\"maxThreads\":20,\"queueSize\":5000},\"queue\":\"" + queueName + "\",\"size\":100,\"target\":\"" + queueName2 + "\"}";
                basePath = "/manager/api/qms/local/brokers/" + managerName + "/fragmentation";

                response =
                        given().log().all().
                                header("Cookie", Cookies).
                                contentType(ContentType.JSON).
                                body(body).
                                baseUri(BaseUrl).basePath(basePath).
                                when().post().
                                then().extract().response();

                basePage.sout("Ответ при конвернтации локальной очереди в  " + type + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
                Assert.assertEquals(200, response.then().extract().statusCode());
                break;
            case aggregate:
                body = "{\"queue\":\"" + queueName + "\",\"target\":\"" + queueName2 + "\"}";
                basePath = "/manager/api/qms/local/brokers/" + managerName + "/aggregation";

                response =
                        given().log().all().
                                header("Cookie", Cookies).
                                contentType(ContentType.JSON).
                                body(body).
                                baseUri(BaseUrl).basePath(basePath).
                                when().post().
                                then().extract().response();

                basePage.sout("Ответ при конвернтации локальной очереди в  " + type + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
                Assert.assertEquals(200, response.then().extract().statusCode());
                break;
            default:
                throw new AssertionError("Настройка очереди пропущена");
        }
    }

    public void deleteQueueAPI(String Cookies, String url, String managerName, String nameQueue) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/queues/" + nameQueue).
                        when().delete().
                        then().extract().response();

        basePage.sout("Ответ при удалении очереди: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
    }

    public void createTopicAPI(String Cookies, String managerName, String nameTopic) {
        String body = "\"" + nameTopic + "\"";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/qms/local/brokers/" + managerName + "/topics").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании раздела:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains(nameTopic));
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public Response getListOfTopicAPI(String Cookies, String managerName, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/topics").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при запросе списков разделов:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
        return response;
    }

    public void createSubscraberAPI(String Cookies, String managerName, String nameTopic, String nameDescraber, String idOfDescraber) {
        String body = "{\"clientId\":\"" + idOfDescraber + "\",\"name\":\"" + nameDescraber + "\",\"topic\":\"" + nameTopic + "\",\"selector\":\"\"}";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(baseUrl()).basePath("/manager/api/qms/local/brokers/" + managerName + "/durable-subscribers").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании подписчика:" + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals(200, response.then().extract().statusCode());
    }

    public Response getListOfSubscriberAPI(String Cookies, String managerName, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/durable-subscribers").
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
                        baseUri(url).basePath("/manager/api/module/factor-qms/restart").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при перезапуске MQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("true", response.getBody().asString());
    }

    public void clearQueueAPI(String Cookies, String managerName, String queue) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(BaseUrl).basePath("/manager/api/qms/local/brokers/" + managerName + "/queues/" + queue + "/clear").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при очистке очереди " + queue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains(queue));
    }

    public void editConfigMainSettingsMqAPI(String cookies,String url,  String managerName, Boolean start, Boolean schedulerSupport, Boolean networkConnectorStartAsync) {
        String body = "{\"id\":\"" + managerName + "\",\"type\":\"local\",\"status\":\"RUNNING\",\"state\":\"SYNCED\",\"slave\":false,\"start\":" + start + ",\"localAccessAllowed\":false,\"schedulerSupport\":" + schedulerSupport + ",\"networkConnectorStartAsync\":" + networkConnectorStartAsync + "}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName).
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при конфигурации МультиMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void editConfigAutorisationSettingsMqAPI(String cookies, String url, String managerName, Boolean localAccessAllowed, Boolean anonymousAccessAllowed, String anonymousUser, String anonymousGroup, Boolean ldapConfig) {
        String body = "{\"localAccessAllowed\":" + localAccessAllowed + ",\"anonymousAccessAllowed\":" + anonymousAccessAllowed + ",\"anonymousUser\":\"" + anonymousUser + "\",\"anonymousGroup\":\"" + anonymousGroup + "\",\"ldapConfig\":{\"url\":\"\",\"baseDn\":\"\",\"bindDn\":\"\",\"bindPassword\":\"\",\"userBaseDn\":\"\",\"userIdAttribute\":\"\",\"userPasswordAttribute\":\"\",\"roleBaseDn\":\"\",\"roles\":\"\",\"enabled\":" + ldapConfig + ",\"roleMap\":{}}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/auth/config").
                        when().put().
                        then().extract().response();

        basePage.sout("Ответ при конфигурации МультиMQ: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertEquals("", response.getBody().asString());
    }

    public void createChanelAPI(String Cookies, String url, String managerName, String chanelName, String chanelProtocol, String chanelUri, String chanelPort, Boolean chanelDuplex, String queueName, String topicName, String userName, String userPassword) {
        String body = "{\"uri\":\"static:(" + chanelProtocol + "://" + chanelUri + ":" + chanelPort + ")?useExponentialBackOff=true&backOffMultiplier=2&initialReconnectDelay=10&maxReconnectDelay=30000\",\"activeBridges\":[],\"staticallyIncludedDestinations\":[{\"type\":\"queue\",\"name\":\"" + queueName + "\"},{\"type\":\"topic\",\"name\":\"" + topicName + "\"}],\"dynamicallyIncludedDestinations\":[],\"excludedDestinations\":[],\"durableDestinations\":[],\"conduitSubscriptions\":true,\"conduitNetworkQueueSubscriptions\":false,\"useVirtualDestSubs\":false,\"dynamicOnly\":false,\"syncDurableSubs\":false,\"dispatchAsync\":true,\"decreaseNetworkConsumerPriority\":false,\"consumerPriorityBase\":-5,\"duplex\":" + chanelDuplex + ",\"bridgeTempDestinations\":true,\"prefetchSize\":1000,\"advisoryPrefetchSize\":0,\"advisoryAckPercentage\":75,\"networkTTL\":1,\"consumerTTL\":1,\"messageTTL\":1,\"brokerName\":\"localhost\",\"brokerURL\":\"\",\"userName\":\"" + userName + "\",\"password\":\"" + userPassword + "\",\"name\":\"" + chanelName + "\",\"clientIdToken\":\"_\",\"suppressDuplicateQueueSubscriptions\":false,\"suppressDuplicateTopicSubscriptions\":false,\"alwaysSyncSend\":true,\"staticBridge\":false,\"useCompression\":false,\"advisoryForFailedForward\":false,\"useBrokerNamesAsIdSeed\":true,\"gcDestinationViews\":true,\"gcSweepTime\":60000,\"checkDuplicateMessagesOnDuplex\":false}";

        String basePath = "/manager/api/qms/local/brokers/" + managerName + "/network-connectors";

        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании канала: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains("id"));
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

    public Response getListOfChannelAPI(String Cookies, String managerName, String url) {
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/network-connectors").
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

    public Response getListOfMessagesInQueue(String Cookies, String managerName, String queueNameValue, String url) {
        String Body = "";

        if (url == null) url = baseUrl();
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/qms/local/brokers/" + managerName + "/queues/" + queueNameValue + "/messages").
                        when().get().
                        then().extract().response();

        basePage.sout("Ответ при просмотре очереди " + queueNameValue + ": " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        return response;
    }

    public String returnSpecificParameterOfQueue(String Cookies, String managerName, String queueNameValue, String specificParameterName, String url) {
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

        return response.path(specificParameterName).toString();
    }

    public void queueCheckAllParametersAPI(String Cookies, String url, String managerName, String queueNameValue, int messagesInDepthValue, int numberOfConsumersValue, int numberOfProducersValue, int messagesInSentValue, int messagesInTakenValue, String typeFragmentation, String typeAggregation, String connectorQueueu, String typecomposite) {
        sleep(2000);
        Response response = returnResponseWithParametersOfQueue(Cookies, managerName, queueNameValue, url);

        Assert.assertTrue(response.getBody().asString().contains(queueNameValue));
        int queueDepth = response.path("queueSize");
        basePage.compareIntAndInt(messagesInDepthValue, queueDepth);
        int consumersCount = response.path("consumerCount");
        basePage.compareIntAndInt(numberOfConsumersValue, consumersCount);
        int producersCount = response.path("producerCount");
        basePage.compareIntAndInt(numberOfProducersValue, producersCount);
        int enqueued = response.path("enqueueCount");
        basePage.compareIntAndInt(messagesInSentValue, enqueued);
        int dequeued = response.path("dequeueCount");
        basePage.compareIntAndInt(messagesInTakenValue, dequeued);
        String fragmentation = response.path("fragmentation");
        Assert.assertEquals(typeFragmentation, fragmentation);

        String aggregation;
        if (response.path("aggregation") != null) {
            aggregation = response.path("aggregation").toString();
        } else {
            aggregation = response.path("aggregation");
        }
        Assert.assertEquals(typeAggregation, aggregation);

        String connector;
        if (response.path("connector") != null) {
            connector = response.path("connector").toString();
//            Assert.assertTrue(connector.contains(connectorQueueu));
            basePage.compareStringAndStringShouldContainsText(connector, connectorQueueu);
        } else {
            connector = response.path("connector");
            Assert.assertEquals(connectorQueueu, connector);
        }


        String compositeQueue = response.path("compositeQueue");
        Assert.assertEquals(typecomposite, compositeQueue);

//        String channel = response.path("channel");
//        if (chanalNameValue.equals("null")) {
//            Assert.assertNull(channel);
//        } else {
//            Assert.assertEquals(chanalNameValue, channel);
//        }
    }

    public void listOfMessagesInQueueShouldBeEmptyAPI(String Cookies, String managerName, String queueNameValue, String url) {
        Response response = getListOfMessagesInQueue(Cookies, managerName, queueNameValue, url);
        basePage.compareStringAndString("[]", response.getBody().asString());
    }


    public void queueCheckSpecificParameterAPI(String Cookies, String managerName, String queueNameValue, String specificParameterName, String specificParameterValue, String url) {
        String specificParameterResponse = returnSpecificParameterOfQueue(Cookies, managerName, queueNameValue, specificParameterName, url);
        basePage.compareStringAndString(specificParameterValue, specificParameterResponse);
    }

    public void queueShouldNotExist(String Cookies, String url, String managerName, String queueNameValue) {
        Response response = returnResponseWithParametersOfQueue(Cookies, managerName, queueNameValue, url);
        Assert.assertEquals(400, response.then().extract().statusCode());
        Assert.assertTrue(response.getBody().asString().contains("Ошибка на стороне сервера"));
    }

    public void queueShouldHaveRangeMessagesAPI(String Cookies,String managerName, String queueNameValue, int messagesInDepthValueMin, int messagesInDepthValueMax, String url) {
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
        Assert.assertTrue(response.getBody().asString().contains(queueNameValue));

        int queueDepth = response.path("queueSize");
        System.out.println("Количество сообщений в очереди = " + queueDepth);
        Assert.assertTrue("Количество сообщений в очереди меньше необходимого", queueDepth > messagesInDepthValueMin);
        Assert.assertTrue("Количество сообщений в очереди больше необходимого", queueDepth < messagesInDepthValueMax);
    }


    public void sendMessgeInQueueAPI(String cookies, String managerName, String nameQueue, String text, int sumMessages) {
        String Body = "{\"dest\":{\"module\":\"QMS\",\"brokerId\":\"" + managerName + "\",\"destType\":\"QUEUE\",\"destination\":\"" + nameQueue + "\"},\"data\":{\"bodyType\":\"TEXT\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":\"" + sumMessages + "\",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":true}]}}";

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

    public void sendMessgeInQueueAPI(String cookies, String url, String managerName, String nameQueue, String text, int sumMessages, boolean persistent, String priority) {
        String Body = "{\"dest\":{\"module\":\"QMS\",\"brokerId\":\"" + managerName + "\",\"destType\":\"QUEUE\",\"destination\":\"" + nameQueue + "\"},\"data\":{\"bodyType\":\"TEXT\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":\"" + sumMessages + "\",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":" + persistent + "},{\"type\":\"PRIORITY\",\"value\":\"" + priority + "\"}]}}";

        Response response =
                given().log().all().
                        header("Cookie", cookies).
                        contentType(ContentType.JSON).
                        body(Body).
                        baseUri(url).basePath("/manager/api/sender/send").
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ после  отправки сообщений: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
        Assert.assertFalse(response.getBody().asString().contains("Ошибка"));
        Assert.assertEquals(200, response.then().extract().statusCode());
        sleep(1000);
    }

    public void sendMessgeInQueueArtemisAPI(String cookies, String nameQueue, String text, int sumMessages) {
        String Body = "{\"dest\":{\"module\":\"QME\",\"brokerId\":null,\"destType\":\"QUEUE\",\"destination\":\"" + nameQueue + "\"},\"data\":{\"bodyType\":\"TEXT\",\"generatedRange\":[{\"sizeString\":\"5kb\",\"size\":\"5120\",\"count\":\"100\"}],\"repeat\":" + sumMessages + ",\"text\":\"" + text + "\",\"defaultText\":\"\",\"files\":[],\"properties\":[{\"type\":\"PERSISTENT\",\"value\":false}]}}";

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


    public void createRecieverAPI(String Cookies, String url, String managerName, String recieverName, String recieverProtocol, String recieverUri, String recieverPort) {
        String body = "{\"uri\":\"" + recieverProtocol + "://" + recieverUri + ":" + recieverPort + "?wireFormat.tcpNoDelayEnabled=true&soWriteTimeout=0\",\"connections\":[],\"allowLinkStealing\":false,\"enableStatusMonitor\":false,\"updateClusterClients\":false,\"rebalanceClusterClients\":false,\"updateClusterClientsOnRemove\":false,\"auditNetworkProducers\":false,\"disableAsyncDispatch\":false,\"name\":\"" + recieverName + "\"}";

        String basePath = "/manager/api/qms/local/brokers/" + managerName + "/transport-connectors";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании приёмника: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    public Response getListOfConnectorAPI(String Cookies, String managerName, String url) {
        String basePath = "/manager/api/qms/local/brokers/" + managerName + "/transport-connectors";
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

    public void createUserAPI(String Cookies, String url, String managerName, String userName, String userPassword, String userGroups) {
        String body = "{\"username\":\"" + userName + "\",\"password\":\"" + userPassword + "\",\"groups\":" + userGroups + "}";

        String basePath = "/manager/api/qms/local/brokers/" + managerName + "/auth/users";
        Response response =
                given().log().all().
                        header("Cookie", Cookies).
                        contentType(ContentType.JSON).
                        body(body).
                        baseUri(url).basePath(basePath).
                        when().post().
                        then().extract().response();

        basePage.sout("Ответ при создании пользователя Мультименеджера: " + response.getBody().asString() + ", код: " + response.then().extract().statusCode() + "\n\n");
//        Assert.assertTrue(response.getBody().asString().contains("id"));
    }
}


