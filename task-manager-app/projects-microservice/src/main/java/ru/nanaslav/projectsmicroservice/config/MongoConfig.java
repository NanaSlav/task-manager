/**
 * Created on 20/05/2024
 */
package ru.nanaslav.projectsmicroservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

/**
 * Настройки соединения с БД
 *
 * @author nana
 */
@Configuration
@EnableMongoRepositories("ru.nanaslav.projectsmicroservice.repository")
public class MongoConfig  extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "projects";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/projects");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("ru.nanaslav.projectsmicroservice");
    }
}

