package Twitter;

import java.io.FileNotFoundException;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

import twitter4j.Status;

public class DbConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String DB_NAME = "twitter";
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	Connection conn = null;
	Statement stmt = null;
	
	public void insertkey (String place, String key, int pos, int neu, int neg) {
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL + DB_NAME,USER,PASS);
		      stmt = conn.createStatement();		      
		      stmt.executeUpdate("INSERT INTO dbanme values('"+place+"','" + key + "'," +
		    		  null + "," + pos + "," + neu + "," + neg + ");");
		
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		
	}
	public String[] getPrevious(String location, String key) {
		String[] prev= new String[1000];
		int i=0;
		try{ 
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL + DB_NAME,USER,PASS);
		      stmt = conn.createStatement();
		      ResultSet res = stmt.executeQuery("SELECT stime, pos, neu, neg FROM dbname WHERE place='" + location + "' AND keyword='" + key + "';");
		      while (res.next()) {
		    	  prev[i++] = res.getString("stime").substring(0,16);
		    	  prev[i++] = res.getString("pos");
		    	  prev[i++] = res.getString("neu");
		    	  prev[i++] = res.getString("neg");
		      }
		      
		      
	}catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	return prev;
	}
}
