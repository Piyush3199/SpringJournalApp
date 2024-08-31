package com.example.demo.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.client.MongoDatabase;

@SpringBootApplication
@EnableTransactionManagement //Enables the use of the annotation @Transactional, creates trasactional context for the methods to acheive atomicity
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public MongoTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}


}
//MongoDatabaseFactory -> Interface used to create MongoDatabase object from MongoClient.
//PlatformTransactionManager -> Interface
//MongoTrasactionManager -> Implementation of the interface, need to create a bean for it in the configuration class.