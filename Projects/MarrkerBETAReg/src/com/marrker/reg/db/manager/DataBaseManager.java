package com.marrker.reg.db.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/pavankon_marrker_reg";
	//private static final String DB_URL = "jdbc:mysql://mysql1001.mochahost.com/pavankon_marrker_reg";

   //  Database credentials
   private static final String USER = "pavankon_app";
   private static final String PASS = "ppa_noknavap";
   
   private static Connection conn = null;
   
   private DataBaseManager(){}

	public static Connection getConnectionPoolerConnection() throws SQLException{
		Connection con=null;
        try{
	      Class.forName(JDBC_DRIVER);

	      System.out.println("Connecting to database..."+conn);
		   con = DriverManager.getConnection(DB_URL,USER,PASS);
	   }
	   catch(Exception ex){
		   System.out.print(ex);
		   ex.printStackTrace();
	   }
	   return con;
	}
	
	public static PreparedStatement startDatabaseOperation(String QUERY) throws SQLException{
		PreparedStatement pst = getConnectionPoolerConnection().prepareStatement(QUERY);
		return pst;
	}
	
	public static ResultSet getDatabaseOprationResult(PreparedStatement pst, String qry) throws SQLException{
		return pst.executeQuery();
	}
	
	public static  int getDatabaseOprationResult(PreparedStatement pst) throws SQLException{
		return pst.executeUpdate();
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		DataBaseManager.conn = conn;
	}
}
