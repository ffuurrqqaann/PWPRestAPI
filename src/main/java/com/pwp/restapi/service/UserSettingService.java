package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

public interface UserSettingService {

	public Boolean updateUserSetting(User user, Setting setting, int status);
	public List<UserSetting> getUserSettings(User u);
	
}
