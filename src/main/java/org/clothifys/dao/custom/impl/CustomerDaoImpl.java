package org.clothifys.dao.custom.impl;

import org.clothifys.dao.custom.CustomerDao;
import org.clothifys.entity.Customer;
import org.clothifys.util.CrudUtil;
import org.clothifys.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return  true;


//        try {
//            String SQL = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)";
//            CrudUtil.execute(
//                    SQL,
//                    entity.getId(),
//                    entity.getTitle(),
//                    entity.getName(),
//                    entity.getDob(),
//                    entity.getNic(),
//                    entity.getAddress(),
//                    entity.getEmail(),
//                    entity.getContact(),
//                    entity.getBankName(),
//                    entity.getBankAccountNo()
//            );


//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
