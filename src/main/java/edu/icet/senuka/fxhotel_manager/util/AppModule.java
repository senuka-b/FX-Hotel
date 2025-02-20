package edu.icet.senuka.fxhotel_manager.util;

import com.google.inject.AbstractModule;
import edu.icet.senuka.fxhotel_manager.repository.custom.*;
import edu.icet.senuka.fxhotel_manager.repository.custom.impl.*;
import edu.icet.senuka.fxhotel_manager.service.custom.*;
import edu.icet.senuka.fxhotel_manager.service.custom.impl.*;


public class AppModule extends AbstractModule {

        @Override
        protected void configure(){
            // Service
            bind(UserService.class).to(UserServiceImpl.class);
            bind(CustomerService.class).to(CustomerServiceImpl.class);
            bind(RoomService.class).to(RoomServiceImpl.class);
            bind(ReservationService.class).to(ReservationServiceImpl.class);
            bind(CheckInOutService.class).to(CheckInOutServiceImpl.class);
            bind(PaymentService.class).to(PaymentServiceImpl.class);
            bind(ReportService.class).to(ReportServiceImpl.class);

            // Dao
            bind(UserDao.class).to(UserDaoImpl.class);
            bind(CustomerDao.class).to(CustomerDaoImpl.class);
            bind(RoomDao.class).to(RoomDaoImpl.class);
            bind(ReservationDao.class).to(ReservationDaoImpl.class);
            bind(CheckInOutDao.class).to(CheckInOutDaoImpl.class);
            bind(PaymentDao.class).to(PaymentDaoImpl.class);

        }

}

