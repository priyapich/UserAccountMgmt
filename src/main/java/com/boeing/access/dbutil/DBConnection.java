package com.boeing.access.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public  class DBConnection {

	final static Logger logger = Logger.getLogger(DBConnection.class);
	final static String CLASSNAME= "DBConnection";
	
	public Connection dbConnection() throws  SQLException,IOException{
		Connection connection = null;
			try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection = DriverManager.getConnection(prop.getProperty("URL"),prop.getProperty("USER"),prop.getProperty("PWD"));
			connection = DriverManager.getConnection("jdbc:sqlite:C:/software/SQLite/sqlite-tools-win32-x86-3220000/sqlite-tools-win32-x86-3220000/db/chinook.db");
			} catch (SQLException e) {
				throw e;
			} 
		return connection;
	}
	

}
