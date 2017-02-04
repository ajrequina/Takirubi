/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;
import java.sql.*;
import java.util.*;
/**
 *
 * @author RJ Requina
 */
public class Retrieve {
  static DatabaseOperations database =  new DatabaseOperations(
                                          DbCredentials.HOST, DbCredentials.DBNAME,
                                          DbCredentials.USERNAME, DbCredentials.PASSWORD);
  public static int clientID(String name){
    try{
      database.connect();
      name = name.replaceAll(" ", "").toLowerCase();
      ResultSet rs = database.execQuery("select client_id from client where lower(concat(firstname,mi,lastname)) = '" + name + "'");
      int id = -1;
      while(rs.next()){
        id = rs.getInt("client_id");
        return id;
      }
    } catch(Exception e){}
    return -1;
  }
  
  
  
  public static int serviceID(String s_type){
    try {
      database.connect();
      ResultSet rs = database.execQuery("select service_id from service where s_type = '" + s_type + "'");
      int id = -1;
      while(rs.next()){
        id = rs.getInt("service_id");    
      }
      return id;
    } catch(Exception e){}
    return -1;
  }
  
  
  public static int photoID(String name){
    try {
      database.connect();
      name = name.replaceAll(" ", "").toLowerCase();
      ResultSet rs = database.execQuery("select photographer_id from photographer where lower(concat(firstname, mi,lastname)) = '" + name + "'");
      int id = 0;
      while(rs.next()){
        id = rs.getInt("photographer_id");
      }
      return id;
    } catch(Exception e){
      
    }
    return -1;
  }
   public static ArrayList<String> getPhotographers() {
    ArrayList<String> list = new ArrayList<String>();
    try {
      database.connect();
      ResultSet rs = database.execQuery("select concat (firstname, ' ', mi, ' ', lastname) as photographer from photographer ");
      while(rs.next()){
        list.add(rs.getString("photographer"));
      }
    } catch(Exception e) {
    }
    return list;
  }
  public static ArrayList<String> getContracts() {
    ArrayList<String>list = new ArrayList<String>();
    try {
      database.connect();
      ResultSet rs = database.execQuery("select concat(view_contract.c_fname, ' ', view_contract.c_lname) as Client, view_contract.s_type as event, view_contract.location as place, concat (p_fname, ' ', p_lname) as photographer from view_contract");
      while(rs.next()){
        list.add(rs.getString("client"));
        list.add(rs.getString("event"));
        list.add(rs.getString("place"));
        list.add(rs.getString("photographer"));
      }
    } catch (Exception e) {
    }
    return list;
  }
  public static ArrayList<String>getPackage() {
    ArrayList<String> list = new ArrayList<String>();
    try {
      database.connect();
      ResultSet rs = database.execQuery("select description, price from package");
      while(rs.next()) {
        list.add(rs.getString("description"));
        list.add(rs.getString("price"));
      }
    }
    catch(Exception e) {
    }
    return list;
  }
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    String x = s.nextLine();
    System.out.println(Retrieve.clientID(x));
    x = s.nextLine();
    System.out.println(Retrieve.photoID(x));
  }
}
