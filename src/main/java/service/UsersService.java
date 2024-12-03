package service;

import models.User;

import java.util.List;

public interface UsersService {
    boolean createUser(User user);
    boolean updateUser(int id, User user);
    boolean deleteUser(int id);
    List<User> getUsers();
    User getUserById(int id);
    User login(String record, String password);
}
