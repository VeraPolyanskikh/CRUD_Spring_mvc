package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
