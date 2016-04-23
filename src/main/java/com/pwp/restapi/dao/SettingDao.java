package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

public interface SettingDao {
	 
    public Boolean addSetting(Setting s);
    public List<Setting> getSettingList();
    public Setting getSetting(Setting s);
    
}