package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

@Repository
@Transactional
public class AnnouncementDaoImpl implements AnnouncementDao {

	private static final Logger logger = LoggerFactory.getLogger(AnnouncementDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addAnnouncement(Announcement a, int userId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		User user = (User)session.load(User.class, new Integer(userId));
		a.setUser(user);

		try {
			session.persist(a);
			logger.info("Contest added successfully, Contest Details="+a);

		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Announcement> getAnnouncementList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Announcement> announcementList = session.createQuery(" from Announcement").list();
		for(Announcement announcement : announcementList){
			logger.info("Announcement List::"+announcement);
		}

		return announcementList;
	}

	@SuppressWarnings("unchecked")
	public Announcement getAnnouncementbyId(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Announcement> announcementList = session.createQuery(" FROM Announcement WHERE id="+id).list();

		if(announcementList.size()>0)
			return announcementList.get(0);
		else
			return null;
	}

	public Boolean deleteAnnouncement(Announcement a) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(a);
		} catch(Exception e) {
			System.out.println("delete announcement exception message"+e.getMessage());
			return false;
		}
		
		return true;
	}
}