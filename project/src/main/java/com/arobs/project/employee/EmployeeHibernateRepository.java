package com.arobs.project.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("employeeHibernateRepository")
@Transactional
public class EmployeeHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> findAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).getResultList();
    }

    public Employee insertEmployee(Employee employee) {
        String hql = "update Employee set employeePassword= MD5(:password) where employeeEmail= :email";
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
        session.createQuery(hql)
                .setParameter("password", employee.getEmployeePassword())
                .setParameter("email", employee.getEmployeeEmail())
                .executeUpdate();
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Employee set employeeEmail= :email, employeeName= :name, employeeRole= :role, employeePassword= MD5(:password) where id= :id";
        session.createQuery(hql)
                .setParameter("email", employee.getEmployeeEmail())
                .setParameter("name", employee.getEmployeeName())
                .setParameter("role", employee.getEmployeeRole())
                .setParameter("password", employee.getEmployeePassword())
                .setParameter("id", employee.getId())
                .executeUpdate();
        return employee;
    }

    public boolean deleteEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
        return true;
    }

    public Employee findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }
}
