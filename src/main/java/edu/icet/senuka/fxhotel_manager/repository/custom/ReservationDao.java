package edu.icet.senuka.fxhotel_manager.repository.custom;

import edu.icet.senuka.fxhotel_manager.dto.ReservationChartData;
import edu.icet.senuka.fxhotel_manager.entity.ReservationEntity;
import edu.icet.senuka.fxhotel_manager.repository.CrudRepository;

import java.util.List;

public interface ReservationDao extends CrudRepository<ReservationEntity, Long> {
    public List<ReservationEntity> getCheckOutReservations();
    public List<ReservationChartData> getReservationChartData();
}
