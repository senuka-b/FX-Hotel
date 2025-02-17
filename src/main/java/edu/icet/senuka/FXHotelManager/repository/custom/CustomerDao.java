package edu.icet.senuka.FXHotelManager.repository.custom;

import edu.icet.senuka.FXHotelManager.entity.CustomerEntity;
import edu.icet.senuka.FXHotelManager.repository.CrudRepository;
import edu.icet.senuka.FXHotelManager.service.custom.CustomerService;

import java.util.List;

public interface CustomerDao extends CrudRepository<CustomerEntity, Long> {
    public List<CustomerEntity> getSearchResults(String searchQuery, Integer limit);

}
