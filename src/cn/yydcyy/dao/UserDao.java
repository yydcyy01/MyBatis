package cn.yydcyy.dao;

import java.util.List;

import cn.yydcyy.pojo.User;

public interface UserDao {
	public User findUserById(Integer id);
	public List<User> findUserByUserName(String userName);

}
