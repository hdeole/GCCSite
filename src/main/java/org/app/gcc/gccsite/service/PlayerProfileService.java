package org.app.gcc.gccsite.service;

import org.app.gcc.gccsite.model.PlayerProfile;

import com.google.gson.Gson;

public class PlayerProfileService {

	private Gson gson = new Gson();
	
	public String getPlayerProfile(String playerId) {
		PlayerProfile profile = new DataService().getPlayerProfile(playerId);
		return gson.toJson(profile);
	}
	
}
