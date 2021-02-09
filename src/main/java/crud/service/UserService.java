package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
