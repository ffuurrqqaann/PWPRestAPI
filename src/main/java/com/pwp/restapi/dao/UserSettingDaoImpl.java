package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

@Repository
@Transactional
public class UserSettingDaoImpl implements UserSettingDao {

	private static final Logger logger = LoggerFactory.getLogger(UserSettingDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addUserSetting(Setting u) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserSetting> getUserSettings(User u) {
		// TODO Auto-generated method stub
		//get current session.
		Session session  = this.sessionFactory.getCurrentSession();
		
		//query for select.
		String queryStr = " from UserSetting usrstng where usrstng.user.id=:userId";
		
		//create query.
		Query query = session.createQuery(queryStr);
		query.setParameter("userId", u.getId());
		
		List<UserSetting> userSetting = query.list();
		
		return userSetting;
	}

	public Boolean updateUserSetting(UserSetting us) {
		// TODO Auto-generated method stub
		
		Session session  = this.sessionFactory.getCurrentSession();
		
		String queryStr = "update UserSetting usrstng set usrstng.status=:status where usrstng.user.id=:userId and usrstng.setting.id=:settingId";
		
		Query query = session.createQuery(queryStr);
		
		query.setParameter("status", us.getStatus());
		query.setParameter("userId", us.getUser().getId());
		query.setParameter("settingId", us.getSetting().getId());
		
		Boolean isUpdated = query.executeUpdate()>0?true:false;
		
		return isUpdated;
	}
}