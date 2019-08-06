package cn.yydcyy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.yydcyy.pojo.User;

public class UserDaoImp implements UserDao {
	private SqlSessionFactory sqlSessionFactory;
	public UserDaoImp(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	public User findUserById(Integer id) {
		//sqlSesion是线程不安全的,所以它的最佳使用范围在方法体内
		SqlSession openSession = sqlSessionFactory.openSession();
		User user = openSession.selectOne("test.findUserById",29);
		return user;
	}

	public List<User> findUserByUserName(String userName) {
		SqlSession openSession = sqlSessionFactory.openSession();
		List<User> list = openSession.selectList("test.findUserByUserName", userName);
		return list;
	}

}
