package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

public interface UserSettingDao {
	 
    public Boolean addUserSetting(Setting u);
    public List<UserSetting> getUserSettings(User u);
    public Boolean updateUserSetting(UserSetting us);
    
}