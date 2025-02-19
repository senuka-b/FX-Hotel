package edu.icet.senuka.FXHotelManager.repository.custom;

import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.repository.CrudRepository;

public interface CheckInOutDao extends CrudRepository<CheckInOutEntity, Long> {
    public CheckInOutEntity addEmptyCheckInOut(CheckInOutEntity entity);
}
