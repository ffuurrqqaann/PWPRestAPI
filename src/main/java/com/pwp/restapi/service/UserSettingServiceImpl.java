package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.UserDao;
import com.pwp.restapi.dao.UserSettingDao;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService{
	
	@Autowired
	private UserSettingDao userSettingDAO;
	
	public List<UserSetting> getUserSettings(User u) {
		// TODO Auto-generated method stub
		
		List<UserSetting> settings = this.userSettingDAO.getUserSettings(u);
		
		return settings;
	}

	public Boolean updateUserSetting(UserSetting us) {
		// TODO Auto-generated method stub
		
		//updating the user setting.
		Boolean isUpdated = this.userSettingDAO.updateUserSetting(us);
		
		return isUpdated;
	}

	public UserSettingDao getUserSettingDAO() {
		return userSettingDAO;
	}
	public void setUserSettingDAO(UserSettingDao userSettingDAO) {
		this.userSettingDAO = userSettingDAO;
	}

}
