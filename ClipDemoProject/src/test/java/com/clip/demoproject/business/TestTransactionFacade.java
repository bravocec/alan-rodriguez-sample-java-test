/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.business;

import com.clip.demoproject.business.impl.TransactionBusinessImpl;
import com.clip.demoproject.business.impl.TransactionValidationBusinessImpl;
import com.clip.demoproject.config.Response;
import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionReportDTO;
import com.clip.demoproject.facade.TransactionFacade;
import com.clip.demoproject.repository.TransactionRepository;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author alan
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest(excludeAutoConfiguration = {EmbeddedMongoAutoConfiguration.class})
public class TestTransactionFacade {

    @Autowired
    private TransactionFacade transactionFacade;

    public TestTransactionFacade() {
    }

    @Test
    public void addTransaction() {
        Response<TransactionDTO> response = transactionFacade.add(getGenericTransaction());
        assertNotNull(response.getResponseBody().getTransaction_id());
    }
    
    @Test
    public void addWrongTransaction() {
        Response<TransactionDTO> response = transactionFacade.add(getGenericTransaction().addUser_id(-1));
        assertEquals(HttpStatus.NOT_ACCEPTABLE, HttpStatus.valueOf(response.getStatus()));
    }
    
    @Test
    public void showTransaction(){
        Response<TransactionDTO> addGenericTransactionDTO = this.transactionFacade.add(getGenericTransaction());
        Response<TransactionDTO> showGenericTransactionDTO = this.transactionFacade.show(addGenericTransactionDTO.getResponseBody().getUser_id(), addGenericTransactionDTO.getResponseBody().getTransaction_id());
        assertEquals(addGenericTransactionDTO.getResponseBody().getTransaction_id(), showGenericTransactionDTO.getResponseBody().getTransaction_id());
    }
    
    
    @Test
    public void showWrongTransaction(){
        Response<TransactionDTO> addGenericTransactionDTO = this.transactionFacade.add(getGenericTransaction());
        Response<TransactionDTO> showGenericTransactionDTO = this.transactionFacade.show(addGenericTransactionDTO.getResponseBody().addUser_id(0).getUser_id(), addGenericTransactionDTO.getResponseBody().getTransaction_id());
        assertNotEquals("Transaction not found", showGenericTransactionDTO.getMessage());
    }
    
    @Test
    public void listTransaction(){
        Integer expectedSize = 0;
        this.transactionFacade.add(getGenericTransaction());
        this.transactionFacade.add(getGenericTransaction().addAmount(200.00));
        Response<List<TransactionDTO>> list = this.transactionFacade.list(getGenericTransaction().getUser_id());
        assertNotEquals(expectedSize, list.getResponseBody().size());
    }
    
    @Test
    public void listTransactionUserNotExist(){
        Integer expectedSize = 0;
        this.transactionFacade.add(getGenericTransaction());
        this.transactionFacade.add(getGenericTransaction().addAmount(200.00));
        Response<List<TransactionDTO>> list = this.transactionFacade.list(getGenericTransaction().addUser_id(200).getUser_id());
        assertEquals(expectedSize, list.getResponseBody().size());
    }
    
    @Test
    public void sumTransaction(){
        Integer customUserId = 300;
        Double expectdAmount = 1000.00;
        TransactionDTO current = getGenericTransaction().addAmount(500.00).addUser_id(customUserId);
        this.transactionFacade.add(current);
        this.transactionFacade.add(current);
        Response<TransactionDTO> sumResponseDTO = this.transactionFacade.sum(customUserId);
        assertEquals(expectdAmount, sumResponseDTO.getResponseBody().getAmount());
    }
    
    @Test
    public void reportTransaction(){
        Integer userId = 499;
        Integer expectedSixe = 2;
        TransactionDTO reportDTO = getGenericTransaction().addUser_id(userId);
        this.transactionFacade.add(reportDTO.addDate("2020-01-27"));
        this.transactionFacade.add(reportDTO.addDate("2020-01-30"));
        Response<List<TransactionReportDTO>> reportListResponse = this.transactionFacade.report(userId);
        assertEquals(expectedSixe, reportListResponse.getResponseBody().stream().findFirst().get().getQuantity());
    }
    
    
    @Test
    public void reportTransactionJanuary2020LastDay(){
        Integer userId = 502;
        String date = "2020-01-31";
        TransactionDTO reportDTO = getGenericTransaction().addUser_id(userId);
        this.transactionFacade.add(reportDTO.addDate(date));
        Response<List<TransactionReportDTO>> reportListResponse = this.transactionFacade.report(userId);
        assertTrue(reportListResponse.getResponseBody().stream().findFirst().get().getWeekFinish().startsWith(date));
    }
    
    @Test
    public void randomTransaction(){
    
    }
    
    
    private TransactionDTO getGenericTransaction(){
        return new TransactionDTO().addAmount(1000.00).addUser_id(1).addDate("2020-01-01").addDescription("Generic Transaction");
    }
    

    @Configuration
    static class ContextConfiguration {

        @Bean
        @Primary
        public TransactionValidationBusiness transactionValidationBusiness() {
            TransactionValidationBusiness transactionValidationBusiness = new TransactionValidationBusinessImpl();
            return transactionValidationBusiness;
        }
        
        @Bean
        @Primary
        public TransactionBusiness transactionBusiness() {
            TransactionBusiness transactionBusiness = new TransactionBusinessImpl();
            return transactionBusiness;
        }
        
        @Bean
        public TransactionFacade transactionFacadeInit(){
            return new TransactionFacade();
        }
    }

    @Configuration
    static class MongoConfiguration implements InitializingBean, DisposableBean {

        MongodExecutable executable;

        @Override
        public void afterPropertiesSet() throws Exception {
            String host = "localhost";
            int port = 27019;

            IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                    .net(new Net(host, port, Network.localhostIsIPv6()))
                    .build();

            MongodStarter starter = MongodStarter.getDefaultInstance();
            executable = starter.prepare(mongodConfig);
            executable.start();
        }

        @Bean
        @Primary
        public MongoDbFactory factory() {
            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost:27019/test_db"));
            return mongoDbFactory;
        }

        @Bean
        @Primary
        public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
            MongoTemplate template = new MongoTemplate(mongoDbFactory);
            template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            return template;
        }

        @Bean
        @Primary
        public MongoRepositoryFactoryBean mongoFactoryRepositoryBean(MongoTemplate template) {
            MongoRepositoryFactoryBean mongoDbFactoryBean = new MongoRepositoryFactoryBean(TransactionRepository.class);
            mongoDbFactoryBean.setMongoOperations(template);

            return mongoDbFactoryBean;
        }

        @Override
        public void destroy() throws Exception {
            executable.stop();
        }
    }
}
