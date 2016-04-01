package org.app.gcc.gccsite.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.app.gcc.gccsite.db.entities.PlayerBattingRecord;
import org.sql2o.Sql2o;

public class ConnectorService {

	private static final Logger LOGGER = Logger.getLogger(ConnectorService.class.getName());

	private static Sql2o sql2o;

	public static Connection getConnection() {

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			LOGGER.severe("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return null;

		}

		LOGGER.info("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/gccdb", "team_readwrite",
					"teamgcc");

		} catch (SQLException e) {

			LOGGER.severe("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
			LOGGER.info("You made it, take control your database now!");
		} else {
			LOGGER.severe("Failed to make connection!");
		}

		return connection;
	}


	public static void closeConnection(Connection connectionSource) {
		try {
			connectionSource.close();
		} catch (SQLException e) {
			LOGGER.severe("Failed to close connection!!! cause:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static org.sql2o.Connection  getSql2oConnection(){
		
		if(sql2o== null ){
			sql2o = new Sql2o("jdbc:postgresql://127.0.0.1:5432/gccdb", "team_readwrite", "teamgcc");
		}
		return sql2o.open();
	}

	public static void main(String[] args) throws SQLException {
			//ConnectorService.getConnectionToDataSource();
		 String stm = "INSERT INTO public.player_batting_record VALUES(21,21)";
		 String stm1 = "select * from gcc.player_batting_record";
//			Connection con = getConnection();
//			PreparedStatement pst  = con.prepareStatement(stm);
//			pst.executeUpdate(); 
//			  stm = "select * from public.player_batting_record ";
//			  pst  = con.prepareStatement(stm);
//			  ResultSet rs =pst.executeQuery();
//			  rs.next();
//			  LOGGER.info("Connection details :::"+rs.getString(1));
//			pst.close();
//			con.close();
			//LOGGER.info("Connection details :::"+con.);
		 
		 org.sql2o.Connection connection = getSql2oConnection();
		 LOGGER.info("Connection details :::"+connection.createQuery(stm1).addColumnMapping("player_id", "playerId")
				 .addColumnMapping("tournament_id", "tournamentId")
				 .executeAndFetch(PlayerBattingRecord.class));
		
	}

}
