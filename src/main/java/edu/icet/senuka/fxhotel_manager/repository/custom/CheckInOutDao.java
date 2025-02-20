package edu.icet.senuka.fxhotel_manager.repository.custom;

import edu.icet.senuka.fxhotel_manager.entity.CheckInOutEntity;
import edu.icet.senuka.fxhotel_manager.repository.CrudRepository;

public interface CheckInOutDao extends CrudRepository<CheckInOutEntity, Long> {
    public CheckInOutEntity addEmptyCheckInOut(CheckInOutEntity entity);
}
