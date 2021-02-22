package crud.dao;

import crud.model.Role;
import crud.model.User;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {


    @PersistenceContext(name="my.persistence", type=PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;

    public UserDAOImpl() {

    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        try {
            User foundUser = entityManager.find(User.class, id,LockModeType.PESSIMISTIC_WRITE);
            entityManager.remove(foundUser);
        }catch(PessimisticLockException e){
            System.out.println("lock failed");
        }

    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public User getUserByLogin(String loginStr) {
        TypedQuery<User> query =
                entityManager.createQuery("from User u where u.login=:loginStr", User.class);
        query.setParameter("loginStr", loginStr);
        return  query.getSingleResult();
    }
}
