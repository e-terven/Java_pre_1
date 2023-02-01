package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {



    public void createUsersTable() {
        userServiceImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userServiceImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userServiceImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userServiceImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userServiceImpl.cleanUsersTable();
    }
}
