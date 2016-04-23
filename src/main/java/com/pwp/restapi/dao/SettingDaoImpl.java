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
public class SettingDaoImpl implements SettingDao {

	private static final Logger logger = LoggerFactory.getLogger(SettingDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addSetting(Setting s) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.persist(s);
			logger.info("Setting added successfully, Setting Details="+s);

		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;

	}

	@SuppressWarnings("unchecked")
	public List<Setting> getSettingList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Setting> settingList = session.createQuery(" from Setting").list();
		for(Setting setting : settingList){
			logger.info("Setting List::"+setting);
		}

		return settingList;
	}

	@SuppressWarnings("unchecked")
	public Setting getSetting(Setting s) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Setting> settingList = session.createQuery(" from Setting WHERE id="+s.getId()).list();
		for(Setting setting : settingList){
			logger.info("Setting List::"+setting);
		}

		if(settingList.size()>0)
			return settingList.get(0);
		else
			return null;
	}
}