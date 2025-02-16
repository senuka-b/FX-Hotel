package edu.icet.senuka.FXHotelManager.util;

import com.google.inject.AbstractModule;
import edu.icet.senuka.FXHotelManager.repository.custom.UserDao;
import edu.icet.senuka.FXHotelManager.repository.custom.impl.UserDaoImpl;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;
import edu.icet.senuka.FXHotelManager.service.custom.impl.UserServiceImpl;


public class AppModule extends AbstractModule {

        @Override
        protected void configure(){
            // Service
            bind(UserService.class).to(UserServiceImpl.class);

            // Dao
            bind(UserDao.class).to(UserDaoImpl.class);
        }

}

