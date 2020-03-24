package com.arobs.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableScheduling
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
//        Timestamp beginning = new Timestamp(myFormat.parse("2020-03-23 22:54:53").getTime());
//        Timestamp end = new Timestamp(myFormat.parse("2020-03-23 23:02:39").getTime());

//        beginning = new Timestamp(myFormat.parse("2020-03-23 22:07:49").getTime());
//        end = new Timestamp(myFormat.parse("2020-03-23 23:17:37").getTime());
    }
}
