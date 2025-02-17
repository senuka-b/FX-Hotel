package edu.icet.senuka.FXHotelManager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.entity.CustomerEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.CustomerDao;
import edu.icet.senuka.FXHotelManager.service.custom.CustomerService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private ModelMapper mapper = new ModelMapper();
    @Inject
    private CustomerDao dao;

    @Override
    public boolean addCustomer(Customer customer) {

        return dao.save(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return dao.update(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return dao.delete(mapper.map(customer, CustomerEntity.class));
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();

        dao.getAll().forEach(customerEntity -> customerList.add(
                mapper.map(customerEntity, Customer.class)
        ));

        return customerList;
    }

    @Override
    public List<Customer> getSearchResults(String searchQuery, Integer limit) {
        List<Customer> searchResults = new ArrayList<>();

        dao.getSearchResults(searchQuery, limit).forEach(
                customer -> searchResults.add(mapper.map(customer, Customer.class))
        );

        return searchResults;
    }
}
