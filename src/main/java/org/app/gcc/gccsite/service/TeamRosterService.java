package org.app.gcc.gccsite.service;

import org.app.gcc.gccsite.model.TeamRosterItemList;

import com.google.gson.Gson;

public class TeamRosterService {

	private Gson gson = new Gson();
	
	public String getTeamRoster() {
		TeamRosterItemList roster =  new DataService().fetchTeamRosterData();
		return gson.toJson(roster);
	}

}
