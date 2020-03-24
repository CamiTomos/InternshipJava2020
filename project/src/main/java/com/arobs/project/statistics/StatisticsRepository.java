package com.arobs.project.statistics;

import com.arobs.project.dtos.LateReturnEmployeeDTO;
import com.arobs.project.dtos.TopBookDTO;
import com.arobs.project.dtos.TopEmployeeDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("statisticsRepository")
public class StatisticsRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public StatisticsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<TopBookDTO> topXBooksRented(int x, Timestamp beginningDate, Timestamp endDate) {
        Session session = sessionFactory.getCurrentSession();
        String hqlGetTop = "SELECT new com.arobs.project.dtos.TopBookDTO(r.book.bookTitle, count(*)) "
                + "from BookRent r inner join r.book "
                + "where r.bookrentRentalDate between :begin and :end"
                + " group by r.book.bookTitle "
                + "order by count(*) desc";
        Query query = session.createQuery(hqlGetTop);
        query.setParameter("begin", beginningDate);
        query.setParameter("end", endDate);
        query.setMaxResults(x);
        return query.list();
    }

    public List<TopEmployeeDTO> topXEmployees(int x, Timestamp beginningDate, Timestamp endDate) {
        Session session = sessionFactory.getCurrentSession();
        String hqlGetTop = "SELECT new com.arobs.project.dtos.TopEmployeeDTO(r.employee.employeeName, count(*)) "
                + "from BookRent r inner join r.employee "
                + "where r.bookrentReturnDate between :begin and :end "
                + "and r.bookrentStatus='returned'"
                + "group by r.employee.employeeName "
                + "order by count(*) desc";
        Query query = session.createQuery(hqlGetTop);
        query.setParameter("begin", beginningDate);
        query.setParameter("end", endDate);
        query.setMaxResults(x);
        return query.list();
    }

    public List<LateReturnEmployeeDTO> getLateReturnEmployees() {
        Session session = sessionFactory.getCurrentSession();
        String hqlGetTop = "SELECT new com.arobs.project.dtos.LateReturnEmployeeDTO(r.employee.employeeName) "
                + "from BookRent r inner join r.employee "
                + "where r.bookrentStatus='late'";
        Query query = session.createQuery(hqlGetTop);
        return query.list();
    }
}