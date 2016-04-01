package org.app.gcc.gccsite.model;

import java.util.List;

import org.app.gcc.gccsite.db.entities.PlayerBattingRecord;
import org.app.gcc.gccsite.db.entities.PlayerBowlingRecord;

public class PlayerProfile {

	private String playerName;
	
	private Long playerId;
	
	private String playerBattingStyle;
	
	private String playerBowlingStyle;
	
	private String dateofBirth;
	
	private String pictureURL;
	
	private String funFacts;
	
	private List<PlayerBattingStats> playerBattingStats;
	
	private List<PlayerBowlingStats> playerBowlingStats;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerBattingStyle() {
		return playerBattingStyle;
	}

	public void setPlayerBattingStyle(String playerBattingStyle) {
		this.playerBattingStyle = playerBattingStyle;
	}

	public String getPlayerBowlingStyle() {
		return playerBowlingStyle;
	}

	public void setPlayerBowlingStyle(String playerBowlingStyle) {
		this.playerBowlingStyle = playerBowlingStyle;
	}

	public String getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	
 

	public String getFunFacts() {
		return funFacts;
	}

	public void setFunFacts(String funFacts) {
		this.funFacts = funFacts;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public List<PlayerBattingStats> getPlayerBattingStats() {
		return playerBattingStats;
	}

	public void setPlayerBattingStats(List<PlayerBattingStats> playerBattingStats) {
		this.playerBattingStats = playerBattingStats;
	}

	public List<PlayerBowlingStats> getPlayerBowlingStats() {
		return playerBowlingStats;
	}

	public void setPlayerBowlingStats(List<PlayerBowlingStats> playerBowlingStats) {
		this.playerBowlingStats = playerBowlingStats;
	}
	
	
	
}
