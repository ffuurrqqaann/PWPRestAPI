package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Stringsets;

public interface SetService {
	public Boolean addSet(Stringsets p);
	public List<Stringsets> getStringSets(String str);
	public Boolean removeStringSet(String str);
	public List<Stringsets> getAllStringSets();
}
