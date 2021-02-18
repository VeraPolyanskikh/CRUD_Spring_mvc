package crud.service;

import crud.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    User getUser(long id);

    void updateUser(Long id, User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
