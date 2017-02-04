/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author RJ Requina
 */
public class Update {
    static DatabaseOperations database =  new DatabaseOperations(
                                          DbCredentials.HOST, DbCredentials.DBNAME,
                                          DbCredentials.USERNAME, DbCredentials.PASSWORD);
    public static boolean updateClient(String name, String fname, String mi, String lastname, String contact_info, String client_type){
      try {
        database.connect();
        int id = Retrieve.clientID(name);
        eMap set  = new eMap();
        eMap where = new eMap();
        where.add("client_id", id);
        if(id > 0){
          if(!(fname.equals("") || fname == null)){
          set.add("firstname", fname);
          } if(!(mi.equals("") || mi == null)){
            set.add("mi", mi);
          } if(!(lastname.equals("") || lastname == null)){
            set.add("lastname", lastname);
          } if(!(contact_info.equals("") || contact_info == null)){
            set.add("contact_info",contact_info);
          } if(!(client_type.equals("") || client_type == null)){
            set.add("client_type",client_type);
          }
          int i = database.update("client",set, where);
          return i == 1;
        }
        return false;
        
      } catch(Exception e){
        System.out.println(e.getMessage());
      }    
      return false;
    }
    
    public static boolean updateService(String s_type, String new_type, String newPrice){
       try {
        database.connect();
        int id = Retrieve.serviceID(s_type);
        System.out.println(id);
        eMap set  = new eMap();
        eMap where = new eMap();
        where.add("service_id", id);
        if(id > 0){
          if(!(new_type.equals("") || new_type == null)){
            set.add("s_type", new_type);
          } if(!(newPrice.equals("") || newPrice == null)){
            set.add("price", newPrice);
          } 
          int i = database.update("service",set, where);
          return i == 1;
        }
        return false;
        
      } catch(Exception e){
        System.out.println(e.getMessage());
      }    
      return false;
    }
    public static void main(String[] args) {
      //Insert.addService("inhouse", 10000);
     Delete.deleteService("inhouse");
     Delete.deleteClient("RJ J Requina");
    // Insert.addClient("Arjemariel", "J", "Requina","899999", "individual");
     Delete.deleteClient("Arjemariel J Requina");
     
    }
}
