package ru.sfedu.FatCalculator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import ru.sfedu.FatCalculator.Constants;
import ru.sfedu.FatCalculator.MongoDB.HistoryContent;

import java.io.IOException;

public class MongoManager {
    private static final Logger log = LogManager.getLogger(MongoManager.class);

    public static <T> MongoClient getConnectionToDB() throws IOException {
        log.trace("Connecting to mongoDB");
        log.info(ConfigurationUtil.getConfigurationEntry(Constants.MONGO_HOST_NAME));
        ConnectionString connectionString = new ConnectionString(ConfigurationUtil.getConfigurationEntry(Constants.MONGO_HOST_NAME));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(settings);
    }

    private static String beanToString(HistoryContent obj) throws JsonProcessingException {
        return new ObjectMapper().findAndRegisterModules().writeValueAsString(obj);
    }

    public static <T> boolean putBeanToMongoDB(HistoryContent history) throws IOException {
        log.trace("Inserting bean into collection");
        try {
            getConnectionToDB().getDatabase(ConfigurationUtil.getConfigurationEntry(Constants.MONGO_DB_NAME)).getCollection(history.getClass().getSimpleName())
                    .insertOne(Document.parse(beanToString(history)));

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public <T> HistoryContent generateMongoBean(String className, String method, T bean, String oldBean, String newBean) {
        HistoryContent storyBean = new HistoryContent();
        storyBean.setCreateDate();
        storyBean.setClassName(className);
        storyBean.setMethodName(method);
        storyBean.setObject(bean);
        storyBean.setActor(Constants.MONGO_ACTOR);
        storyBean.setOldBean(oldBean);
        storyBean.setNewBean(newBean);
        return storyBean;
    }
}
