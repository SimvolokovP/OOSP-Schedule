package service;

import dao.GroupDaoImpl;
import models.Group;
import utils.DBConnector;
import java.util.List;

public class GroupsServiceImpl implements GroupsService {
    private GroupDaoImpl groupDao;

    public GroupsServiceImpl() {
        this.groupDao = new GroupDaoImpl(new DBConnector().getConnection());
    }
    @Override
    public boolean createGroup(Group group) {
        return groupDao.add(group);
    }

    @Override
    public boolean updateGroup(int id, Group group) {
        return groupDao.edit(id, group);
    }

    @Override
    public boolean deleteGroup(int id) {
        return groupDao.remove(id);
    }

    @Override
    public List<Group> getGroups() {
        return groupDao.getList();
    }

    @Override
    public Group getGroupById(int id) {
        return groupDao.getById(id);
    }
}
