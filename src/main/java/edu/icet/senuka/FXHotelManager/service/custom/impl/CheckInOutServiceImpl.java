package edu.icet.senuka.FXHotelManager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.CheckInOut;
import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.CheckInOutDao;
import edu.icet.senuka.FXHotelManager.service.custom.CheckInOutService;

public class CheckInOutServiceImpl implements CheckInOutService {

    @Inject
    private CheckInOutDao dao;

    @Override
    public CheckInOutEntity addEmptyCheckInOut() {
        return dao.addEmptyCheckInOut(
                CheckInOutEntity.builder().totalPrice(0d).build()
        );
    }

    @Override
    public boolean updateCheckInOut(CheckInOut checkInOut) {
        return false;
    }

    @Override
    public boolean deleteCheckInOut(CheckInOut checkInOut) {
        return false;
    }
}
