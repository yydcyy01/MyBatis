package cn.yydcyy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.yydcyy.mapper.UserMapper;
import cn.yydcyy.pojo.User;

public class UserMapperTest {
	private SqlSessionFactory factory;
	
	@Before
	public void setUp() throws IOException{
		String resource = "config/SqlMapConfig.xml";
		//通过流读取核心文件
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 通过核心配置文件,创建会话工厂
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testFindUserById(){
		SqlSession openSession = factory.openSession();
		//动态代理通过 getMapper 方法实例化接口
		UserMapper mapper = openSession.getMapper(UserMapper.class); 
		User user =mapper.findUserById(29);
		System.out.println(user);
	}
	@Test
	public void testFindUserByUserName(){
		SqlSession openSession = factory.openSession();
		
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByUserName("h");
		System.out.println(list);
	}
	@Test
	public void testInsertUser() throws Exception{
		SqlSession openSession = factory.openSession();
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		
		User user = new User();
		user.setUsername("朱莉2");
		user.setSex("2");
		user.setBirthday(new Date());
		user.setAddress("火星");
		
		mapper.insertUser(user);
		openSession.commit();
	}
	
	
}
