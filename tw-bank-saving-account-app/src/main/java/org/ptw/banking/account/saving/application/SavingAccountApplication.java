package org.ptw.banking.account.saving.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.ptw.banking")
public class SavingAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SavingAccountApplication.class, args);
    }
}