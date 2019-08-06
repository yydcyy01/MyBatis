package cn.yydcyy.mapper;


import java.util.List;

/**
 * Mybatis 动态代理实现Dao Demo
 */
import cn.yydcyy.pojo.User;

public interface UserMapper {
	public User findUserById(Integer id);
	
	//动态代理中, 若返回值为集合List, MyBatis生成的实现类会自动调用selectList方法
	public List<User> findUserByUserName(String userName);
	public void insertUser(User user);
}
