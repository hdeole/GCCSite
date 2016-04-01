package org.app.gcc.gccsite.model;

import java.io.Serializable;

public class TeamRosterItem implements Serializable {

	private long playerId;
	private String playerName;
	private String playerBattingStyle;
	private String playerBowlingStyle;
	private String playerOtherStyle;
	private String playerImageURL;
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
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
	public String getPlayerOtherStyle() {
		return playerOtherStyle;
	}
	public void setPlayerOtherStyle(String playerOtherStyle) {
		this.playerOtherStyle = playerOtherStyle;
	}
	public String getPlayerImageURL() {
		return playerImageURL;
	}
	public void setPlayerImageURL(String playerImageURL) {
		this.playerImageURL = playerImageURL;
	}
	
	
	
	
	
	
}
