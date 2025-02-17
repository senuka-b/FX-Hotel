package edu.icet.senuka.FXHotelManager.repository.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.entity.CustomerEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.CustomerDao;
import edu.icet.senuka.FXHotelManager.util.HibernateConfig;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Inject
    private ModelMapper mapper;


    @Override
    public boolean save(CustomerEntity entity) {

        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(CustomerEntity entity) {

        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(CustomerEntity customerEntity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(customerEntity);
        transaction.commit();

        return true;
    }

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateConfig.getSession();
        return session.createQuery("FROM CustomerEntity", CustomerEntity.class).list();

    }

    @Override
    public List<CustomerEntity> getSearchResults(String searchQuery, Integer limit) {
        Session session = HibernateConfig.getSession();

        Transaction transaction = session.beginTransaction();

        List<CustomerEntity> searchResults = session.createQuery(
                        "FROM CustomerEntity WHERE fullName LIKE :query OR phoneNumber LIKE :query OR address LIKE :query",
                        CustomerEntity.class)
                .setParameter("query", "%" + searchQuery + "%")
                .setMaxResults(limit)
                .getResultList();

        transaction.commit();
        return searchResults;
    }
}
