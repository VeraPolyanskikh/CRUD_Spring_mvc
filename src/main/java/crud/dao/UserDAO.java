package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
