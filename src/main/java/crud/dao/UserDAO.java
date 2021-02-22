package crud.dao;

import crud.model.Role;
import crud.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void saveUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    List<Role> getAllRoles();


    User getUserByLogin(String login);
}
