package blog.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

import blog.model.Auth;
import blog.model.Page;
import blog.model.User;
import blog.startup.MongoDBConnector;
import blog.startup.Tools;

@Repository
public class UserDao {

	/**
	 * 保存一个用户
	 * @param user
	 * @return
	 */
	public User save(User user)
	{
		if(user==null)
		{
			user=new User();
			user.setBM_DEL(-1);
			return user;
		}
		
		if(user.getUsername()==null||user.getUsername().isEmpty())
		{
			user=new User();
			user.setBM_DEL(-2);
			return user;
		}
		
		//////////检测username是不是重复/////////////
		long l=MongoDBConnector.datastore.createQuery(User.class).field("username").equal(user.getUsername()).count();
		if(l!=0L)
		{
			user=new User();
			user.setBM_DEL(-4);
			return user;
		}
		
		
		if(user.getPw()==null||user.getPw().isEmpty())
		{
			user=new User();
			user.setBM_DEL(-3);
			return user;
		}
		
		if(user.getNickname()==null)
		{
			user.setNickname("");
		}
		
		user.setPw(Tools.digestSha1(user.getPw()));
		
		MongoDBConnector.datastore.save(user);
		
		ObjectId id = user.getId();
		
		return MongoDBConnector.datastore.get(User.class,id);
	}
	
	
	/***********************************update****************************************************/
	public User setUserToAdmin(User user)
	{
		if(user==null)
		{
			user=new User();
			user.setBM_DEL(-1);
			return user;
		}
		
		if(user.getBM_ID()==null)
		{
			user=new User();
			user.setBM_DEL(-2);
			return user;
		}
		
		User u=MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(user.getBM_ID()).get();
		
		if(u==null)
		{
			user=new User();
			user.setBM_DEL(-3);
			return user;
		}
		if(user.getAdmin()!=null)
		{
			return user;
		}
		
		Auth admin=new Auth();
		admin.setPassword(u.getPw());
		u.setAdmin(admin);
		
		MongoDBConnector.datastore.updateFirst(MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID()), u,false);
		
		return u;
	}
	
	
	public User editUser(User user)
	{
		if(user==null)
		{
			user=new User();
			user.setBM_DEL(-1);
			return user;
		}
		
		if(user.getBM_ID()==null)
		{
			user=new User();
			user.setBM_DEL(-2);
			return user;
		}
		
		User u=MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(user.getBM_ID()).get();
		
		if(u==null)
		{
			user=new User();
			user.setBM_DEL(-3);
			return user;
		}
		if(user.getHeaderIcon()!=null&&!user.getHeaderIcon().isEmpty())
		{
			u.setHeaderIcon(user.getHeaderIcon());
		}
		if(user.getGender()!=null)
		{
			u.setGender(user.getGender());
		}
		if(user.getNickname()!=null&&!user.getNickname().isEmpty())
		{
			u.setNickname(user.getNickname());
		}
		if(user.getSign()!=null&&!user.getSign().isEmpty())
		{
			u.setSign(user.getSign());
		}
		if(user.getEmail()!=null&&!user.getEmail().isEmpty())
		{
			u.setEmail(user.getEmail());
		}
		
		
		MongoDBConnector.datastore.updateFirst(MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID()), u,false);
		
		return MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID()).get();
	}
	
	/**
	 * 权限编辑
	 * @param u
	 * @return
	 */
	public User editAuth(User u)
	{
		Query<User> updateQuery = MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID());
		UpdateResults re = MongoDBConnector.datastore.updateFirst(updateQuery, u,false);
		re.getUpdatedCount();
		return MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID()).get();
	}
	
	/**
	 * 修改用户余额, 在原基础上加
	 * @param u
	 * @return
	 */
	public int rechargeUserDeposit(User u)
	{
		if(u==null)
		{
			return -1;
		}
		if(u.getBM_ID()==null)
		{
			return -2;
		}
		if(u.getDeposit()==null)
		{
			return -3;
		}
		Query<User> updateQuery = MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(u.getBM_ID());
		UpdateOperations<User> ops=MongoDBConnector.datastore.createUpdateOperations(User.class);
		ops.inc("deposit",u.getDeposit());
		UpdateResults re = MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
	
	/**
	 * 修改用户余额, 在原基础上减
	 * @param user bmid
	 * @param des 减去的价值
	 * @return
	 */
	public int decUserDeposit(String bmid, Integer des)
	{

		if(bmid==null)
		{
			return -2;
		}
		if(des==null)
		{
			return -3;
		}
		Query<User> updateQuery = MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(bmid);
		UpdateOperations<User> ops=MongoDBConnector.datastore.createUpdateOperations(User.class);
		ops.dec("deposit",des);
		UpdateResults re = MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
	
	/***********************************find****************************************************/
	//验证用户
	public User findByUserName(String email)
	{
		if(email==null)
		{
			User u=new User();
			u.setBM_DEL(-1);
			return u;
		}
		
		return MongoDBConnector.datastore.createQuery(User.class).field("username").equal(email).get();
	}
	
	/**
	 * 根据账户去查找
	 * @param name
	 * @param nowPage
	 * @param numInPage
	 * @return
	 */
	public Page<User> findUsersByAccountWithPage(String name, int nowPage, int numInPage)
	{
		Query<User> query=MongoDBConnector.datastore.createQuery(User.class);
		
		Page<User> page=new Page<User>();
		
		page.setTotal(query.count());
		page.setNowPage(nowPage);
		page.setTotalInPage(numInPage);
		
		page.getPage();
		
		page.setList(query.field("username").containsIgnoreCase(name).asList(new FindOptions().skip(page.getSkip()).limit(numInPage)));
		
		return page;
	}
	
	/**
	 * 
	 * @param bmid
	 * @return
	 */
	public User findUserByBMID(String bmid)
	{
		return MongoDBConnector.datastore.createQuery(User.class).field("BM_ID").equal(bmid).get();
	}
}
