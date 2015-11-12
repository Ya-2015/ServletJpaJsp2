package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import DBUtil.DBUtil;
import models.Lineitem;
import models.Product;
import models.Productreview;
import models.Shoppinguser;

public class ProductDB {
	
	public ArrayList<Product> getAllProducts(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Product> fd = null;
		
		try {
			String sql = "select p from Product p";
			TypedQuery<Product> q = em.createQuery(sql, Product.class);
			
			fd = q.getResultList();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return new ArrayList<Product>(fd);
	}
	
	public Product getProductById(int prodid){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Product fd = null;
		
		try {
			String sql = "select p from Product p where p.prodid = :prodid";
			TypedQuery<Product> q = em.createQuery(sql, Product.class);
			q.setParameter("prodid", prodid);
			
			fd = q.getSingleResult();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return fd;
	}
	
	public Lineitem getLineitemById(int purchaseno){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Lineitem fd = null;
		
		try {
			String sql = "select p from Lineitem p where p.purchaseno = :purchaseno";
			TypedQuery<Lineitem> q = em.createQuery(sql, Lineitem.class);
			q.setParameter("purchaseno", purchaseno);
			
			fd = q.getSingleResult();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return fd;
	}
	
	public boolean addNewLineitem(Lineitem item){
		boolean isSuccess = false;
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		
		try{
			em.persist(item);
			trans.commit();
			isSuccess = true;
		}catch(Exception e){
			System.out.println(e);
			trans.rollback();
		}finally{
			em.close();
		}
		
		return isSuccess;
	}
	
	public boolean updateLineitem(Lineitem item){
		boolean isSuccess = false;
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		
		try{
			em.merge(item);
			trans.commit();
			isSuccess = true;
		}catch(Exception e){
			System.out.println(e);
			trans.rollback();
		}finally{
			em.close();
		}
		
		return isSuccess;
	}
	
	public ArrayList<Lineitem> getLineitems(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Lineitem> fd = null;
		
		try {
			String sql = "select p from Lineitem p ORDER BY p.purchaseno asc";
			TypedQuery<Lineitem> q = em.createQuery(sql, Lineitem.class);
			
			fd = q.getResultList();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return new ArrayList<Lineitem>(fd);
	}
	
	public ArrayList<Lineitem> getLineitemsByUser(int userid){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Lineitem> fd = null;
		
		try {
			String sql = "select u from Lineitem u where u.userid = :userid";
			TypedQuery<Lineitem> q = em.createQuery(sql, Lineitem.class);
			q.setParameter("userid", userid);
			
			fd = q.getResultList();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return new ArrayList<Lineitem>(fd);
	}
	
	public Object getTotalCostByUser(int userid){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Object fd = null;
		
		try {
			String sql = "select sum(p.productcost) from Lineitem p where p.userid = :userid";
			TypedQuery<Lineitem> q = em.createQuery(sql, Lineitem.class);
			q.setParameter("userid", userid);
			
			fd = q.getSingleResult();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return fd;
	}
	
	public void removeLineitem(Lineitem item){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		try{
			em.remove(em.merge(item));
			trans.commit();
		}catch(Exception e){
			System.out.println(e);
			trans.rollback();
		}finally{
			em.close();
		}
	}
	
	public int checkUser(String username, String userpwd){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Shoppinguser fd = null;
		
		try {
			String sql = "select p from Shoppinguser p where p.username = :username and p.userpwd = :userpwd";
			TypedQuery<Shoppinguser> q = em.createQuery(sql, Shoppinguser.class);
			q.setParameter("username", username);
			q.setParameter("userpwd", userpwd);
			
			fd = q.getSingleResult();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		int userid = -1;
		if(fd!=null){
			userid = fd.getUserid();
		}
		
		return userid;
	}
	
	public int checkUserByName(String username){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Shoppinguser fd = null;
		
		try {
			String sql = "select p from Shoppinguser p where p.username = :username";
			TypedQuery<Shoppinguser> q = em.createQuery(sql, Shoppinguser.class);
			q.setParameter("username", username);
			
			fd = q.getSingleResult();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		int userid = -1;
		if(fd!=null){
			userid = fd.getUserid();
		}
		
		return userid;
	}
	
	
	public boolean addNewUser(Shoppinguser user){
		boolean isSuccess = false;
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		
		try{
			em.persist(user);
			trans.commit();
			isSuccess = true;
		}catch(Exception e){
			System.out.println(e);
			trans.rollback();
		}finally{
			em.close();
		}
		
		return isSuccess;
	}
	
	public ArrayList<Productreview> getReviewById(int productid){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Productreview> fd = null;
		
		try {
			String sql = "select u from Productreview u where u.productid = :productid order by u.postdate";
			TypedQuery<Productreview> q = em.createQuery(sql, Productreview.class);
			q.setParameter("productid", productid);
			
			fd = q.getResultList();
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		return new ArrayList<Productreview>(fd);
	}
	
	public boolean addPost(Productreview post){
		boolean isSuccess = false;
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		
		try{
			em.persist(post);
			trans.commit();
			isSuccess = true;
		}catch(Exception e){
			System.out.println(e);
			trans.rollback();
		}finally{
			em.close();
		}
		
		return isSuccess;
	}
	
}
