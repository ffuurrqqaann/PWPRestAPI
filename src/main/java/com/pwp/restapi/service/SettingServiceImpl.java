package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.AnnouncementDao;
import com.pwp.restapi.dao.SettingDao;
import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Setting;

@Service("SettingService")
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingDao settingDAO;
	
	public void setSettingDAO(SettingDao settingDAO) {
		this.settingDAO = settingDAO;
	}

	public List<Setting> getSettingList() {
		// TODO Auto-generated method stub
		List<Setting> setting = this.settingDAO.getSettingList();
		
		return setting;
	}

	public Boolean addSetting(Setting s) {
		// TODO Auto-generated method stub
		Boolean isSettingAdded = this.settingDAO.addSetting(s);
		
		return isSettingAdded;
	}

	public Setting getSettingById(Setting s) {
		// TODO Auto-generated method stub
		Setting setting = this.settingDAO.getSetting(s);
		return setting;
	}
	
}