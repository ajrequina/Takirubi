/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

/**
 *
 * @author RJ Requina
 */
public class Delete {
   static DatabaseOperations database =  new DatabaseOperations(
                                          DbCredentials.HOST, DbCredentials.DBNAME,
                                          DbCredentials.USERNAME, DbCredentials.PASSWORD);
  public static boolean deleteService(String service_type){
   try {
     database.connect();
     int id = Retrieve.serviceID(service_type);
     if(id > 0){
       eMap where = new eMap();
       where.add("service_id", id);
       int i = database.delete("service", where);
       return i == 1;
     }
     return false;
   } catch(Exception e){
     
   }
   return false;
  }
  public static boolean deleteClient(String name){
    try {
      database.connect();
      int id = Retrieve.clientID(name);
      System.out.println("id = " + id);
      if( id > 0){
        eMap where = new eMap();
        where.add("client_id", id);
        int i = database.delete("client", where);
        return i == 1;
      }
      return false;
    } catch(Exception e){
    }
    return false;
  } 
}



