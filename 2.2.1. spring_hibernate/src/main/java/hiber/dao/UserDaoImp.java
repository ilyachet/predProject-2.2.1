package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
   public User getUserByCar(Car car) {
      String HQL = "select u from User u INNER JOIN u.car car where car.model = :model and car.series = :series";
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL).setParameter("model", car.getModel()).setParameter("series", car.getSeries());
      List<User> list = sessionFactory.getCurrentSession().createQuery(HQL)
              .setParameter("model", car.getModel())
              .setParameter("series", car.getSeries())
              .getResultList();
      return list.get(0);
   }
}
