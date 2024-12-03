package service;

import dao.UserDaoImpl;
import models.User;
import utils.DBConnector;

import java.util.List;

public class UsersServiceImpl implements UsersService{
    private UserDaoImpl userDao;

    public UsersServiceImpl() {
        this.userDao = new UserDaoImpl(new DBConnector().getConnection());
    }
    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(int id, User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User login(String record, String password) {
        return userDao.auth(record, password);
    }
}
