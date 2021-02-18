package crud.dao;

import crud.model.User;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            User foundUser = em.find(User.class, id,LockModeType.OPTIMISTIC);
            em.remove(foundUser);
            tr.commit();
        }catch(PessimisticLockException e){
            tr.rollback();
            System.out.println("lock failed");
        }
        em.close();
    }

    @Override
    public User getUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.createQuery("from User", User.class).getResultList();
        }finally{
            em.close();
        }
    }

    @Override
    public void cleanUsersTable() {

    }

    @Override
    public User getUserByLogin(String loginStr) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            TypedQuery<User> query =
                    em.createQuery("from User u where u.login=:loginStr", User.class);
            query.setParameter("loginStr", loginStr);
            return  query.getSingleResult();
        }finally{
            em.close();
        }
    }
}
