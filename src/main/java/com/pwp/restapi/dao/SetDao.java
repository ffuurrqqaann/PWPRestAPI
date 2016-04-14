package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Stringsets;

public interface SetDao {
	 
    public Boolean addSet(Stringsets s);
    public List<Stringsets> listStringSets();
    public Boolean removeSet(String s);
}