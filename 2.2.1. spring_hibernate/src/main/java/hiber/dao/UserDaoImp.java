package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsersWithCars() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select distinct u from User u left join fetch u.car");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public User getUserByCarModelAndSeria(String model, String seria) {
      String hql = "SELECT u FROM User u LEFT JOIN FETCH u.car WHERE u.car.model = :model AND u.car.seria = :seria"; // жадный запрос
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("seria", seria);

      try {
         return query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }
}
