package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.AnnouncementDao;
import com.pwp.restapi.model.Announcement;

@Service("AnnouncementService")
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDAO;
	
	public void setAnnouncementDAO(AnnouncementDao announcementDAO) {
		this.announcementDAO = announcementDAO;
	}
	
	public List<Announcement> announcementList() {
		// TODO Auto-generated method stub
		List<Announcement> announcements = this.announcementDAO.getAnnouncementList();
		
		return announcements;
	}

	public Boolean addAnnouncement(Announcement a, int userId) {
		// TODO Auto-generated method stub
		Boolean isAnnoucementAdded = this.announcementDAO.addAnnouncement(a, userId);
		
		return isAnnoucementAdded;
	}

	public Announcement getAnnouncement(int aId) {
		// TODO Auto-generated method stub
		Announcement announcement = this.announcementDAO.getAnnouncementbyId(aId);
		
		return announcement;
	}

	public Boolean deleteAnnouncementById(Announcement a) {
		// TODO Auto-generated method stub
		Boolean isAnnouncementDeleted = this.announcementDAO.deleteAnnouncement(a);
		
		return isAnnouncementDeleted;
	}

}