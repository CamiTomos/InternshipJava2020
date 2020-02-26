package com.arobs.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
//        ApplicationContext applicationContext = SpringApplication.run(ProjectApplication.class, args);
//        EmployeeHibernateRepository repository= (EmployeeHibernateRepository) applicationContext.getBean("employeeHibernateRepository");
//        System.out.println("AICI"+" "+repository.getAllEmployees().size());
//        System.out.println(repository.getAllEmployees().get(0).toString());
//
//        EmployeeService service= (EmployeeServiceImpl) applicationContext.getBean("employeeServiceImpl");
//        System.out.println("AICI"+" "+service.getAllEmployees().size());
//        System.out.println(service.getAllEmployees().get(0).toString());
    }
}
