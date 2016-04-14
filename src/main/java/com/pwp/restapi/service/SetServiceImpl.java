package com.pwp.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.SetDao;
import com.pwp.restapi.model.Stringsets;

@Service("SetService")
public class SetServiceImpl implements SetService {

	@Autowired
	private SetDao setDAO;
	
	public void setSetDAO(SetDao setDAO) {
		this.setDAO = setDAO;
	}
	
	
	public Boolean addSet(Stringsets s) {
		// TODO Auto-generated method stub
		return this.setDAO.addSet(s);
	}

	public List<Stringsets> getStringSets(String str) {
		// TODO Auto-generated method stub
		
		List<Stringsets> temp = new ArrayList<Stringsets>();
		List<Stringsets> stringSets = this.setDAO.listStringSets();
		
		for( int i=0; i<stringSets.size(); i++ ) {
			if( stringSets.get(i).getStringset().contains(str) )
				temp.add(stringSets.get(i));
		}
		
		return temp;
	}
	
	public List<Stringsets> getAllStringSets() {
		// TODO Auto-generated method stub
		
		List<Stringsets> stringSets = this.setDAO.listStringSets();
		
		return stringSets;
	}
	
	public Boolean removeStringSet(String str) {
		return this.setDAO.removeSet(str);
	}
	
}