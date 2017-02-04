/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 *
 * @author Arjemariel Requina
 */
public class Setup {
     static final String JDBC_DRIVER = "org.postgresql.Driver";  
     static final String DB_URL = "jdbc:postgresql//localhost:5432/takirubi";
     
     public static Connection getConnection() {
       	Properties prop = new Properties();
	      InputStream input = null;
        Connection conn = null;
        try {
          input = new FileInputStream("config.properties");
          prop.load(input);
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/takirubi", prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
          return conn;
        } catch(Exception e){
          System.out.println(e.getMessage());
        } 
        finally {
          if (input != null) {
            try {
              input.close();
            } catch (IOException e) {
            }
          }  
       }
       return null; 
     }
}
