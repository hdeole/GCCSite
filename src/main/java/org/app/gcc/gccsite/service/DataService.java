package org.app.gcc.gccsite.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.app.gcc.gccsite.db.entities.PlayerBasicProfile;
import org.app.gcc.gccsite.db.entities.PlayerBattingRecord;
import org.app.gcc.gccsite.db.entities.PlayerBowlingRecord;
import org.app.gcc.gccsite.model.PlayerBattingStats;
import org.app.gcc.gccsite.model.PlayerBowlingStats;
import org.app.gcc.gccsite.model.PlayerProfile;
import org.app.gcc.gccsite.model.TeamRosterItem;
import org.app.gcc.gccsite.model.TeamRosterItemList;
import org.postgresql.util.PGobject;

import com.google.gson.Gson;

public class DataService {

	private PGobject dataObject;
	private PreparedStatement statement;
	private Connection connection = null;
	private Gson gson = new Gson();

	private static String SELECT_TEAM_RECORD_QRY = "select * from gcc.team_statistics where team_id = ? and tournament_id = ?";
	private static final Logger LOGGER = Logger.getLogger(DataService.class.getName());

	public String getTeamStatisticsData(long team_id) throws SQLException {

		StringBuffer response = new StringBuffer();
		try {
			LOGGER.info("saving player Bowling record to DB..");
			connection = ConnectorService.getConnection();

			statement = connection.prepareStatement(SELECT_TEAM_RECORD_QRY);
			statement.setLong(1, team_id);
			statement.setLong(2, 0);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				response.append(gson.toJson(result.getObject(3)));
			}
			LOGGER.info("got record..");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			statement.close();
			connection.close();
		}
		return response.toString();
	}

	public PlayerProfile getPlayerProfile(String playerId) {

		org.sql2o.Connection sql2oConnection = new ConnectorService().getSql2oConnection();
		PlayerProfile profile = new PlayerProfile();
		
		PlayerBasicProfile basicProfile = sql2oConnection
				.createQuery("select * from gcc.player_profile where player_id = :playerId")
				.addParameter("playerId", Long.parseLong(playerId))
				.addColumnMapping("player_id", "playerId")
				.executeAndFetchFirst(PlayerBasicProfile.class);
		if(basicProfile!=null){			
			profile = gson.fromJson(basicProfile.getData().getValue(), PlayerProfile.class);
		}
		
		List<PlayerBattingRecord> battingRecordList = sql2oConnection
				.createQuery("select * from gcc.player_batting_record where player_id = :playerId")
				.addParameter("playerId", Long.parseLong(playerId))
				.addColumnMapping("player_id", "playerId")
				.addColumnMapping("tournament_id", "tournamentId")
				.executeAndFetch(PlayerBattingRecord.class);
		List<PlayerBattingStats> recordList = new ArrayList<PlayerBattingStats>();
		for(PlayerBattingRecord record : battingRecordList ){
			PlayerBattingStats stats = gson.fromJson(record.getData().getValue(), PlayerBattingStats.class);
			recordList.add(stats);
		}
		
		List<PlayerBowlingRecord> bowlingRecordList = sql2oConnection
				.createQuery("select * from gcc.player_bowling_record where player_id = :playerId")
				.addParameter("playerId", Long.parseLong(playerId))
				.addColumnMapping("player_id", "playerId")
				.addColumnMapping("tournament_id", "tournamentId")
				.executeAndFetch(PlayerBowlingRecord.class);
		
		List<PlayerBowlingStats> bowlingRecords = new ArrayList<PlayerBowlingStats>();
		for(PlayerBowlingRecord record : bowlingRecordList ){
			PlayerBowlingStats stats = gson.fromJson(record.getData().getValue(), PlayerBowlingStats.class);
			bowlingRecords.add(stats);
		}
		
		profile.setPlayerBattingStats(recordList);
		profile.setPlayerBowlingStats(bowlingRecords);
		
		LOGGER.info("got records from DB.."+battingRecordList+",>>"+bowlingRecordList);
		return profile;
	}

	public TeamRosterItemList fetchTeamRosterData() {
		 
		org.sql2o.Connection sql2oConnection = new ConnectorService().getSql2oConnection();
		List<TeamRosterItem> roster = sql2oConnection
				.createQuery("select * from gcc.gcc_team_roster")				
				.addColumnMapping("player_id", "playerId")
				.addColumnMapping("player_name", "playerName")
				.addColumnMapping("player_batting_style", "playerBattingStyle")
				.addColumnMapping("player_bowling_style", "playerBowlingStyle")
				.addColumnMapping("player_other_style", "playerOtherStyle")
				.addColumnMapping("player_pic_url", "playerImageURL")
				.executeAndFetch(TeamRosterItem.class);
		TeamRosterItemList rosterList = new TeamRosterItemList();
		rosterList.setRosterItemList(roster);
		return rosterList;
	}

}
