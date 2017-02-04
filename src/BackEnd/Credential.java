/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;
import java.sql.*;
/**
 *
 * @author RJ Requina
 */
public class Credential {
  
  private static Connection getConnection(){
    String JDBC_DRIVER = "org.postgresql.Driver"; 
    String DB_URL = "jdbc:postgresql//localhost:5432/credential";
    Connection conn = null;
    try{
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/credential","your username", "your password");
      return conn;
    } catch(Exception e){
      System.out.println(e.getMessage());  
    }
    return null;
  }
  
  public static int userProfile(String username, String password){
    int profile = 0;
    Statement smt = null;
    Connection conn = null;
    try {
      conn = Credential.getConnection();
      smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = smt.executeQuery("select profile_type from users where username = '" + username + "' and  password = crypt('" + password + "', password)");
      while(rs.next()){
        profile = rs.getInt("profile_type");
      }
      return profile;
    }catch(Exception e){}
     finally {
      try{
       if(smt!= null){
         smt.close();
       }  
      }catch(SQLException se2){
      }

      try{
       if(conn!=null){
        conn.close(); 
       }      
      }catch(SQLException se2){
      }
    }
  
    return 0;
  }
  
  public static boolean addUser(String username, String password, int profile_type){
    int profile = 0;
    Statement smt = null;
    Connection conn = null;
    try {
      conn = Credential.getConnection();
      smt = conn.createStatement();
      if(!usernameExists(username)){
        System.out.println(usernameExists(username));
        String sql = "insert into users values('" + username + "', crypt('" + password + "', gen_salt('md5'))," + profile_type + ")";
        smt.executeUpdate(sql);
        return true;
      } else {
        return false; 
      }
      
    }catch(Exception e){}
     finally {
      try{
       if(smt!= null){
         smt.close();
       }  
      }catch(SQLException se2){
      }

      try{
       if(conn!=null){
        conn.close(); 
       }      
      }catch(SQLException se2){
      }
    }
   return false;
  }
  
  private static boolean usernameExists(String username){
    int profile = 0;
    Statement smt = null;
    Connection conn = null;
    try {
      conn = Credential.getConnection();
      smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = smt.executeQuery("select * from users where username = '" + username + "'");
      while(rs.next()){
        String s = rs.getString("username");
        if(s.equals(username)){
          return true;
        }
      }
      return false;
    }catch(Exception e){}
     finally {
      try{
       if(smt!= null){
         smt.close();
       }  
      }catch(SQLException se2){
      }

      try{
       if(conn!=null){
        conn.close(); 
       }      
      }catch(SQLException se2){
      }
    }
   return false;
  }
}
