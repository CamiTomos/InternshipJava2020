package com.arobs.project.hibernateTest;

import com.arobs.project.employee.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateMain {
    private static final Logger log = LoggerFactory.getLogger(HibernateMain.class);

    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();

        // Check database version
//        String sql = "select version()";
//
//        String result = (String) session.createNativeQuery(sql).getSingleResult();
//        System.out.println(result);
//
//        session.getTransaction().commit();

//        session.getTransaction().commit();
//        session.close();

        log.info("Simple log statement with inputs {}, {} and {}", 1, 2, 3);
        Transaction transaction = null;
//        Employee employee=new Employee();
//        employee.setEmployeeName("ana");
//        employee.setEmployeePassword("ana");
//        employee.setEmployeeRole("admin");
//        employee.setEmployeeEmail("ana@ana.com");

//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // save the student objects
//            session.save(employee);
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(e -> System.out.println(e.getEmployeeEmail()));
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
