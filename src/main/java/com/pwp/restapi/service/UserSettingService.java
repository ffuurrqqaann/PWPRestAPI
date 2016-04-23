package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

public interface UserSettingService {

	public Boolean updateUserSetting(UserSetting us);
	public List<UserSetting> getUserSettings(User u);
	
}
