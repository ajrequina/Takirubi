package BackEnd;
import java.util.*;
import java.sql.*;


public class DatabaseOperations {
    private Connection connection;
    private String host;
    private String dbName;
    private String username;
    private String password;
    
    public DatabaseOperations(String host, String dbname, String username, String password){
        this.host = host;
        this.dbName = dbname;
        this.username = username;
        this.password = password;
    }
    
    public boolean connect() throws SQLException, ClassNotFoundException{
        if(host == null || dbName == null || username == null || password == null){
            throw new SQLException("Credentials incomplete");
        }
        
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(
                            this.host + this.dbName,
                            this.username, this.password);
        return true;
    }
 
    public ResultSet execQuery(String query) throws SQLException {
        return this.connection.createStatement().executeQuery(query);
    }
    
    public void execUpdate(String query) throws SQLException {
      int i = this.connection.createStatement().executeUpdate(query);
    }
    
    public int insert(String table, eMap values){
        StringBuilder columns = new StringBuilder();
        StringBuilder val = new StringBuilder();
        try{
            for(int i = 0; i < values.size(); i++){
                columns.append(values.get(i).key).append(",");
//                if(values.get(i).value instanceof String){
                    try{
                        val.append(Integer.parseInt((String)values.get(i).value)).append(",");
                    } catch(Exception e){
                        System.out.println("NOT INTEGER");
                        val.append("'").append(values.get(i).value).append("',");
                    }
                    
//                    val.append("'").append(values.get(i).value).append("',");
//                } else {
//                    val.append(values.get(i).value).append(",");
//                }
            }

            columns.setLength(columns.length() - 1);
            val.setLength(val.length() - 1);
            String query = String.format("INSERT INTO %s (%s) VALUES (%s)", 
                                            table, columns.toString(), val.toString());
            return this.connection.createStatement().executeUpdate(query);
        } catch(Exception e){
            System.out.println("EXCEPTION IN INSERT");
        }
        return 0;
    }
    
    public int update(String table, eMap set, eMap where){
        StringBuilder setCol = new StringBuilder();
        StringBuilder setVal = new StringBuilder();
        StringBuilder w = new StringBuilder();
        
        
        try{
            /*-- SET --*/
            for(int i = 0; i < set.size(); i++){
                setCol.append(set.get(i).key).append(",");
                try{
                    setVal.append(Integer.parseInt((String)set.get(i).value)).append(",");
                } catch(Exception e){
                    //System.out.println("NOT INTEGER");
                    setVal.append("'").append(set.get(i).value).append("',");
                }
//                if(set.get(i).value instanceof String){
//                    setVal.append("'").append(set.get(i).value).append("',");
//                } else {
//                    setVal.append(set.get(i).value);
//                }
            }

            /*-- WHERE --*/
            for(int i = 0; i < where.size(); i++){
                if(i == 0){
                    w.append(where.get(i).key).append(" = ");

                } else {
                    w.append(" AND ").append(where.get(i).key).append(" = ");

                }
                try{
                    w.append(Integer.parseInt((String)where.get(i).value)).append(",");
                } catch(Exception e){
                    //System.out.println("NOT INTEGER");
                    w.append("'").append(where.get(i).value).append("',");
                }
                
//                if(where.get(i).value instanceof String){
//                        w.append("'").append(where.get(i).value).append("'");
//                } else {
//                    w.append(where.get(i).value).append(",");
//                }
            }

            setCol.setLength(setCol.length() - 1);
            setVal.setLength(setVal.length() - 1);
            w.setLength(w.length() - 1);
           // System.out.printf("%s\n",setCol);
           // System.out.printf("%s\n",setVal);
            //System.out.printf("%s\n",w);
            String query = String.format("UPDATE %s SET (%s) = (%s)"
                                           + " WHERE %s", table, setCol.toString(), setVal.toString(), w.toString());
            //System.out.printf("%s\n",query);

            return this.connection.createStatement().executeUpdate(query);
        } catch(Exception e){
            System.out.println("EXCEPTION IN UPDATE");
        }
        return 0;
        
    }
    
    public int delete(String table, eMap where){
//        StringBuilder columns = new StringBuilder();
//        StringBuilder values = new StringBuilder();
        StringBuilder d = new StringBuilder();
        
        try{
            for(int i = 0; i < where.size(); i++){
                d.append(where.get(i).key).append(" = ");
                try{
                    d.append(Integer.parseInt((String)where.get(i).value)).append(",");
                } catch(Exception e){
                    System.out.println("NOT INTEGER");
                    d.append("'").append(where.get(i).value).append("',");
                }
                
//                if(where.get(i).value instanceof String){
//                    d.append("'").append(where.get(i).value).append("'");
//                } else {
//                    d.append(where.get(i).value).append(",");
//                }
            }
            
            d.setLength(d.length() - 1);
            String query = String.format("DELETE FROM %s WHERE %s", table, d);
            return this.connection.createStatement().executeUpdate(query);
        } catch(Exception e){
            System.out.println("EXCEPTION IN DELETE");
        }
        return 0;
    }
    
    public int getColumns(String table){
        try { 
            String query = "SELECT * FROM " + table;
            ResultSet columns = this.connection.createStatement().executeQuery(query);
            ResultSetMetaData r = columns.getMetaData();
            return r.getColumnCount();
        } catch(Exception e){
            System.out.println("EXCEPTION IN GET COLUMNS");
        }
        return 0;
    }
    
    public void displayALL(String table){
        int columnNum = 0;
        
        try{
            columnNum = getColumns(table);
            String query = ("SELECT * FROM " + table);
            ResultSet rs = execQuery(query);
            int i = 1;
            while(rs.next()){
                while(i <= columnNum){
                    System.out.print(rs.getObject(i).toString());
                    System.out.printf("\t");
                    i++;
                }
                i = 1;
                System.out.printf("\n");
            }
        } catch(Exception e){
            System.out.println("EXCEPTION IN DISPLAY");
        }
    }
}
