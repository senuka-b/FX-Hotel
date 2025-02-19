package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.dto.CheckInOut;
import edu.icet.senuka.FXHotelManager.dto.Reservation;
import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.service.SuperService;

public interface CheckInOutService extends SuperService {
    public CheckInOutEntity addEmptyCheckInOut();
    public boolean updateCheckInOut(CheckInOut checkInOut);
    public boolean deleteCheckInOut(CheckInOut checkInOut);
}
