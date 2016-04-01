package org.app.gcc.gccsite.GCCSite;

import static spark.Spark.*;

import org.app.gcc.gccsite.service.PlayerProfileService;
import org.app.gcc.gccsite.service.TeamRosterService;
import org.app.gcc.gccsite.service.TeamStatisticsService;

import spark.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
    {
    	port(9898);
    	get("/teamStats", (request, response) -> new TeamStatisticsService().getTeamStatistics());
    	get("/profile/:playerId" ,(request, response) -> new PlayerProfileService().
    															getPlayerProfile(request.params(":playerId")));
    	get("/gccRoster", (request, response) -> new TeamRosterService().getTeamRoster());
    	
    	
    }
}
