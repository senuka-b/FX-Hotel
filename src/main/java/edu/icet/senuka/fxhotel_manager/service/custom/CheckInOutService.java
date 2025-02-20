package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.dto.CheckInOut;
import edu.icet.senuka.fxhotel_manager.dto.Room;
import edu.icet.senuka.fxhotel_manager.entity.CheckInOutEntity;
import edu.icet.senuka.fxhotel_manager.service.SuperService;

public interface CheckInOutService extends SuperService {
    public CheckInOutEntity addEmptyCheckInOut();
    public boolean markCheckIn(CheckInOut checkInOut, Room room);
    public boolean markCheckOut(CheckInOut checkInOut, Room room);
}
