package dao;

import java.util.List;

public interface Dao <T>{
    boolean add(T type);
    boolean edit(int id, T type);
    List<T> getList();
    T getById(int id);
    boolean remove (int id);
}
