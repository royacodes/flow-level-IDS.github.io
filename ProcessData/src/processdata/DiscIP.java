

package processdata;

import com.mysql.jdbc.Connection;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscIP {
String file1;
int IProw;
 String [][] IP = null ; 
  
   public void DiscIP(String pass , String fna ,String num )throws ClassNotFoundException,
    SQLException {

        String username = "root"; // mySQL username
    	String password = pass; // mySQL password
    	String url = "jdbc:mysql://localhost"; // Connect to database IDS
    	Class.forName("com.mysql.jdbc.Driver");
        //String fn = "C://Users//public//out.txt";
        //String fn1 = "C://Users//public//dataIP.txt";
        String fn = "D://elmi//payan name//dataset//testdata 1999 4th week//wednesday//out.txt";
        String fn1 = "D://elmi//payan name//dataset//testdata 1999 4th week//wednesday//dataIP.txt";

    	 final Connection con = (Connection) DriverManager.getConnection(url, username, password); // Connect
    	final Statement stmt;
        stmt = con.createStatement(); // for Query

	final String tbl="dataset"; // Table name

        String query="CREATE DATABASE data";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
         query="use data";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}

query = "create table dataset(frameNum varchar(50) DEFAULT NULL,srcIp varchar(50) DEFAULT NULL,destIp varchar(50) DEFAULT NULL,Protocol varchar(50) DEFAULT NULL,pckTime varchar(50) DEFAULT NULL,stcPort varchar(50) DEFAULT NULL,destPort varchar(50) DEFAULT NULL);";

try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "LOAD DATA LOCAL INFILE"+ "'"+ fn+"'" +" INTO TABLE dataset FIELDS TERMINATED BY ',' lines terminated by '\r\n';";

try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "create table IP(ID int NOT NULL AUTO_INCREMENT primary key,IP varchar(50) DEFAULT NULL);";
try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "insert into IP(IP)select distinct a.IP from (select distinct srcIp as IP from dataset union select distinct destIp as IP from dataset)a;";
    try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "select * from IP into outfile "+ "'"+fn1+"'" + " fields terminated by ',' lines terminated by '\r\n';";
     try {
							stmt.executeQuery(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "drop database data";
try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
MapData mp = new MapData();
        mp.Map(fn  , fn1 , fna);
        mp.convert(Integer.parseInt(num));
}


}


