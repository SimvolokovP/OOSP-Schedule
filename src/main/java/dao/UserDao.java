package dao;


import models.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    boolean editUser(int id, User User);
    List<User> getUsers();
    User getUserById(int id);
    boolean removeUser (int id);
    User auth (String record, String password);
}
