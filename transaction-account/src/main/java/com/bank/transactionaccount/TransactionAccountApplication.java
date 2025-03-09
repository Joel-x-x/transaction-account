package com.bank.transactionaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TransactionAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionAccountApplication.class, args);
    }

}
