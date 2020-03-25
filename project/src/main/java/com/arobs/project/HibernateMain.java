package com.arobs.project;

import com.arobs.project.configurations.HibernateUtil;
import com.arobs.project.employee.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateMain {
    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);

    public static void main(String[] args) {
        Transaction transaction = null;
        Employee employee = new Employee();
        employee.setEmployeeName("ana");
        employee.setEmployeePassword("ana");
        employee.setEmployeeRole("admin");
        employee.setEmployeeEmail("ana@ana.com");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(e -> {
                log.info("Employee name: {}", e.getEmployeeEmail());
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        HibernateUtil.shutdown();
    }
}
