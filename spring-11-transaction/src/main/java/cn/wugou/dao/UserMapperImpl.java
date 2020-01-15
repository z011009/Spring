package cn.wugou.dao;

import cn.wugou.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public List<User> getList() {
        User user = new User(8, "kongli3", "123456");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        addUser(user);
        delete(5);
        return mapper.getList();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
