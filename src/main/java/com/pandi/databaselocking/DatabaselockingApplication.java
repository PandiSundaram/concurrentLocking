package com.pandi.databaselocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DatabaselockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaselockingApplication.class, args);
	}

}
