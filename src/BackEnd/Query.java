/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arjemariel Requina
 */
public class Query {
  DatabaseOperations database =  new DatabaseOperations(
                                          DbCredentials.HOST, DbCredentials.DBNAME,
                                          DbCredentials.USERNAME, DbCredentials.PASSWORD);
  
  private  ArrayList<ArrayList<Data>> getData(String query){
    ArrayList<ArrayList<Data>> ar;
    ArrayList<String> columnNeeded = new ArrayList<>();
    ar = new ArrayList<ArrayList<Data>>();
    ArrayList<String> columnName = new ArrayList<>();
    try {
      database.connect();
      ResultSet rs = database.execQuery(query);
      ResultSetMetaData rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      for(int i = 1; i <= count; i++){
        columnName.add(rsmd.getColumnName(i));
      }
       while(rs.next()){
        ar.add(getRowData(rs, columnName));
       }
      return ar;
    } catch(Exception e){
    }
    return null;
  }
  
  private ArrayList<Data> getRowData(ResultSet rs, ArrayList<String> columnName){
    ArrayList<Data> rowData = new ArrayList<>();
    for(String s : columnName){
        try {
            boolean isImage;
            isImage = s.equals("image");
            rowData.add(new Data(rs.getString(s), isImage));  
          
        } catch (SQLException ex) {
          Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    return rowData;
  }
  
  public ArrayList<ArrayList<Data>> getWeeklySchedule(String d){
    ArrayList<ArrayList<Data>> ar = new ArrayList<>();
    ar = getData("select date, location, time_start, time_end from view_contract_services where date >= '" + d + "'" + "and date < ('" + d +"'::date + '7 day'::interval) order by date");
    return ar;
  }
  public ArrayList<ArrayList<Data>> getPhAvailable(String firstname, String lastname, String date){
    ArrayList<ArrayList<Data>> ar = new ArrayList<>();
    ar = getData("select time_start, time_end from view_contract_photo, view_contract_services where view_contract_photo.contract_id = view_contract_services.contract_id and firstname = '" + firstname + "'" + " and lastname  = '" + lastname +"' and date = '" + date +"'" );
    System.out.println(printAll(ar));
    return getAvailability(ar);
  }
  
  public ArrayList<ArrayList<Data>> getAvailability(ArrayList<ArrayList<Data>> data){
   // System.out.println("Here");
    String Time = "00:00:00";
    String lastTime = "24:00:00";
    String prevTime = "";
    String last = "";
    ArrayList<ArrayList<Data>> newData = new ArrayList<>();
    ArrayList<Data> newRow;
    newRow = new ArrayList<Data>();
    newRow.add(new Data(Time, false));
   // System.out.println("Here3");
    for(ArrayList<Data> outer : data){   // System.out.println("Here2");
      for(Data inner : outer){
       newRow.add(inner);// System.out.println(inner.info());
       newData.add(newRow);
       newRow = new ArrayList<Data>();
       newRow.add(inner);
       last = inner.info();
      }
    }
    newRow =  new ArrayList<Data>();
    newRow.add(new Data(last, false));
    newRow.add(new Data(lastTime, false));
    newData.add(newRow);
    return newData;
  }

  
  public String printAll(ArrayList<ArrayList<Data>> arr ){
    String result = "";
    if(arr != null){
      for(ArrayList<Data> x  : arr){
        for(Data y : x){
          result += (y.isImage() ? y.image() : y.info()) + " ";
        }
        result += "\n\n";
      }
      return result;
    }    
    return "No Data Found";
  }
  
  public ArrayList<ArrayList<Data>> getAllPh(){
    ArrayList<ArrayList<Data>> ar = new ArrayList<>();
    ar = getData("select photographer_id, concat(lastname, ' ', firstname, ' ' , ,mi) from photographer order by lastname");
    return ar;
  }
  public String addDay(String date){
   SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
   java.util.Date d = new java.util.Date();
   return null;
  }
  public static void main(String[] args) {
   Query q = new Query();
   System.out.println(q.printAll(q.getAllPh()));
  }
}
