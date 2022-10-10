package Dao;


import java.util.List;

/**
 * @title: Dao
 * @Author Xu
 * @Date: 2022/9/26 23:05
 * @Version 1.0
 */
@SuppressWarnings({"all"})
public interface Dao<E> {
    //插入
    public void insert(E e);

    //查询所有
    public List<E> selectAll();

    //通过Id查询
    public E selectById(int id);

    //通过Id删除
    public void delete(int id);
}