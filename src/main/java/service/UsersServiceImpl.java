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
        return userDao.add(user);
    }

    @Override
    public boolean updateUser(int id, User user) {
        return userDao.edit(id, user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.remove(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getList();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User login(String record, String password) {
        return userDao.auth(record, password);
    }
}
