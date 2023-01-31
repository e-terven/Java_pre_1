package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Katia", "Savenko", (byte) 1);
        userService.saveUser("Eric", "Reynolds", (byte) 2);
        userService.saveUser("Sandy", "Loreno", (byte) 3);
        userService.saveUser("Ayse", "Askar", (byte) 4);

        List<User> list = userService.getAllUsers();
        list.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
