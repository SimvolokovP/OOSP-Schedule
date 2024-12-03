package test;

import dao.GroupDaoImpl;
import dao.UserDaoImpl;
import models.*;
import utils.DBConnector;

public class Main {
    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnector();
        UserDaoImpl userDao = new UserDaoImpl(dbConnector.getConnection());
//        System.out.println(userDao.add(new Admin("admin", "admin", "2")));
    }
}
