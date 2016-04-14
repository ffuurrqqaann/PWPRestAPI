package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Announcement;

public interface AnnouncementService {
	
	public List<Announcement> announcementList();
	public Boolean addAnnouncement(Announcement a, int userId);
	
}
