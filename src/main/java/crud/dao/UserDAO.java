package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
