package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.dto.Customer;
import edu.icet.senuka.fxhotel_manager.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public boolean addCustomer(Customer customer);
    public boolean updateCustomer(Customer customer);
    public boolean deleteCustomer(Customer customer);
    public List<Customer> getAll();

    public List<Customer> getSearchResults(String searchQuery, Integer limit);
}
