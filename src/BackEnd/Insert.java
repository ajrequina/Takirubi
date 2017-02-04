/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import static BackEnd.Retrieve.database;
import java.sql.ResultSet;

/**
 *
 * @author RJ Requina
 */
public class Insert {
  static DatabaseOperations database =  new DatabaseOperations(
                                            DbCredentials.HOST, DbCredentials.DBNAME,
                                            DbCredentials.USERNAME, DbCredentials.PASSWORD);
  public static boolean addClient(String firstname, String mi, String lastname, String contact_info, String client_type){
    try {
      database.connect();
      eMap values = new eMap();
      values.add("firstname", firstname);
      values.add("mi",mi);
      values.add("lastname", lastname);
      values.add("contact_info", contact_info);
      values.add("client_type", client_type);
      database.insert("client",values);
      return true;
    } catch(Exception e){
      System.out.println("Exception in Add Client");
    }
    return false;
  }
  
  public static boolean addService(String s_type, int price){
    try {
      
      database.connect();
      eMap values = new eMap();
      values.add("s_type", (String)(s_type));
      values.add("price", (Integer)(price));
      int i = database.insert("service", values);
      return i == 1;
    } catch(Exception e){
      System.out.println("Exception in Add Service");
    }
    return false;
  }
  
  public static boolean addPhotographer(String availability, String firstname, String lastname, String mi, String type) {
    try {
      database.connect();
      eMap values = new eMap();
      values.add("availability", availability);
      values.add("firstname", firstname);
      values.add("lastname", lastname);
      values.add("mi", mi);
      values.add("type", type);
      database.insert("photographer",values);
      return true;
    } catch (Exception e) {
      System.out.println("Exception in Add Photographer");
    }
    return false;
  }
  
  public static boolean addPackage (String description, int price, int price_add_on, int total) {
    try{
      database.connect();
      eMap values = new eMap();    
      values.add("description", description);
      values.add("price", (Integer)(price));
      values.add("price_add_on", (Integer)(price_add_on));
      values.add("total_photos", (Integer)(total));
      database.insert("package", values);
      return true;
    } catch(Exception e) {
      System.out.println("Exception in Add Package");
    }
    return false;
  }
  
}
