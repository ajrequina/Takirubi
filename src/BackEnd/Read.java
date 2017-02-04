/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BackEnd;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author RJ Requina
 */
public class Read {
  static DatabaseOperations database =  new DatabaseOperations(
                                          DbCredentials.HOST, DbCredentials.DBNAME,
                                          DbCredentials.USERNAME, DbCredentials.PASSWORD);
    public static ArrayList<String> getAllPhotographer(){
      try {
        database.connect();
        ArrayList<String> ph = new ArrayList<>();
        ResultSet rs = database.execQuery("select concat(firstname, ' ', mi, ' ', lastname) as name from photographer");
        while(rs.next()){
          ph.add(rs.getString("name"));
        }
        return ph;
      } catch(Exception e){}
      return null;
    }
    
    public static void main(String[] args) {
     ArrayList<String> ar = Read.getAllPhotographer();
     for(String s : ar){
       System.out.println(s);
     }
    }
}
