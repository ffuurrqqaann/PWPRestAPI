package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Setting;

public interface SettingService {
	
	public List<Setting> getSettingList();
	public Boolean addSetting(Setting s);
	public Setting getSettingById(Setting s);
	
}
