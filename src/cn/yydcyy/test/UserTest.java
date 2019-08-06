package cn.yydcyy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.yydcyy.pojo.User;

/**
 * Mybatis 增删改查.java + User.xml + User pojo的Demo 2019年8月6日03:48:37
 * @author Administrator
 *
 */
public class UserTest {

	public void testFindUserById() throws IOException{
		//记录配置文件地址
		String resource = "config/SqlMapConfig.xml"; 
		//通过流读取配置文件
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//通过"核心配置文件输入流"来创建工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
		SqlSession openSession = factory.openSession();
		
		User user = openSession.selectOne("test.findUserById", 1);
		
		System.out.println(user);
		openSession.close();
		
	}
	
	@Test
	public void testFindUserByUserName() throws IOException{
		String resource = "config/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = factory.openSession();
		
		List<User> list = openSession.selectList("test.findUserByUserName", "明");
		System.out.println(list);
		
	}
	
	@Test
	public void testInsertUser() throws IOException{
		String resource = "config/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = factory.openSession();
	
		User user = new User();
		user.setAddress("辽宁大连");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setUsername("环万峰2");
		
		//User.xml中, "空间名.id",类
		openSession.insert("test.insertUser", user);
		openSession.commit(); // 提交事务, 才会保存数据库
	}
	
	
	@Test
	public void testDelUserById() throws IOException{
		String resource = "config/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = factory.openSession();
	
		
		//User.xml中, "空间名.id",类
		openSession.delete("test.delUserById",30);
		openSession.commit(); // 提交事务, 才会保存数据库
	}
	
	@Test
	public void testUpdateUserById() throws IOException{
		String resource = "config/SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = factory.openSession();
	
		
		User user = openSession.selectOne("test.findUserById", 29); //看数据库,找一个存在的id
		//System.out.println(user);
		
		user.setAddress("曼哈顿博士_火星");
		user.setUsername("hhhhhhhh");
		//System.out.println(user);
		openSession.update("test.updateUserById",user);
		openSession.commit();
		
	}
}
