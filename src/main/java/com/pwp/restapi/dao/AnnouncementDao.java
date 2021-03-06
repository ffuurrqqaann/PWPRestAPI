package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;

public interface AnnouncementDao {
	 
    public Boolean addAnnouncement(Announcement a, int userId);
    public List<Announcement> getAnnouncementList();
    public Announcement getAnnouncementbyId(int id);
    public Boolean deleteAnnouncement(Announcement a);
    
}