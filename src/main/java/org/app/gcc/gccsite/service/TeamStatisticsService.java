package org.app.gcc.gccsite.service;

import java.sql.SQLException;

public class TeamStatisticsService {

	
	private static final long TEAM_ID = 6724;
	
	public String getTeamStatistics() throws SQLException{
		
		return new DataService().getTeamStatisticsData(TEAM_ID);
		
	}

	
}
