package com.epf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class, DatabaseTestService.class);
        DatabaseTestService testService = context.getBean(DatabaseTestService.class);
        testService.testConnection();
    }
}
