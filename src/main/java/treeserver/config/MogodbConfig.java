package treeserver.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * Created by kevinyin on 2017/10/15.
 */
@Configuration
public class MogodbConfig {

    @Resource
    private MongoClientOptions mongoClientOptions;
    @Resource
    private MongoClient mongoClient;

    @Bean
    public MongoClientOptions mongoClientOptions(){
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(20)
                .threadsAllowedToBlockForConnectionMultiplier(4)
                .connectTimeout(1000)
                .writeConcern(WriteConcern.SAFE)
                .maxWaitTime(1500)
                .socketTimeout(1500)
                .build();

        return options;
    }

    @Bean
    public MongoClientFactoryBean mongoClient(){
        MongoClientFactoryBean mongoClient = new MongoClientFactoryBean();
        mongoClient.setMongoClientOptions(mongoClientOptions);
        return mongoClient;
    }

    @Bean
    public MongoOperations mongoOperations(){
        return new MongoTemplate(mongoClient,"test");
    }


}
