package edu.icet.senuka.fxhotel_manager.repository.custom;

import edu.icet.senuka.fxhotel_manager.entity.CustomerEntity;
import edu.icet.senuka.fxhotel_manager.repository.CrudRepository;

import java.util.List;

public interface CustomerDao extends CrudRepository<CustomerEntity, Long> {
    public List<CustomerEntity> getSearchResults(String searchQuery, Integer limit);

}
