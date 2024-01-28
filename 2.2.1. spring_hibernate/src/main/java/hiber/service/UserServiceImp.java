package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      if (user.getCar() != null) {
         userDao.addCar(user.getCar());
      }
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsersWithCars() {
      return userDao.listUsersWithCars();
   }

   @Transactional
   @Override
   public void addCar(Car car) {
      userDao.addCar(car);
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserByCarModelAndSeria(String model, String seria) {
      return userDao.getUserByCarModelAndSeria(model, seria);
   }
}
