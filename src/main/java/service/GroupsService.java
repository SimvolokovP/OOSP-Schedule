package service;


import models.Group;
import java.util.List;

public interface GroupsService {
    boolean createGroup(Group group);
    boolean updateGroup(int id, Group group);
    boolean deleteGroup(int id);
    List<Group> getGroups();
    Group getGroupById(int id);
}
