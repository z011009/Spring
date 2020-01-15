package cn.wugou.dao;

import cn.wugou.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> getList();

    public int addUser(User user);

    public int delete(int id);
}
