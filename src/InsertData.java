/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arjemariel Requina
 */
import java.awt.Image;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class InsertData{
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql//localhost:5432/takirubi";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        Connection conn = null;
        Statement stmt = null;
        try{
           // Setup code like this should only be run once and placed inside a
           // class whose only purpose is to setup the database.
           
           //STEP 2: Register JDBC driver
           Class.forName("org.postgresql.Driver");
     
           // This should be abstracted from this class. You can just create 
           // a Factory class that exposes a getConnection method. The connection
           // can then be a class level property since this class should be a
           // singleton anyway.
           //STEP 3: Open a connection
           System.out.println("Connecting to a selected database...");
           conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/takirubi", Nothing.USER, Nothing.PASS);
           System.out.println("Connected database successfully...");

           // File manipulation and database manipulation shouldn't exist
           // in the same class. Consider creating a class that handles file
           // level duties that can fetch/parse values from a file.
           // Also create a class that handles database level duties that 
           // accept the values from the File Manipulation class. 
           
           //STEP 4: Execute a query
          // System.out.println("Inserting records into the table...");
           //stmt = conn.createStatement();
           /*File file = new File("D:\\spongebob.jpg");
           FileInputStream fis = new FileInputStream(file);
           PreparedStatement ps = conn.prepareStatement();
           ps.setString(1, file.getName());
           ps.setBinaryStream(2, fis, file.length());
           ps.executeUpdate();
           ps.close();
           fis.close();*/
          // ps = conn.prepareStatement("SELECT * FROM client");
          Statement sa = conn.createStatement();
           ResultSet rs =sa.executeQuery("select * from client");
           byte[] imgBytes = null;
            if (rs != null) {
                while(rs.next()) {
                    System.out.println(rs.getString("client_id"));
                    // use the stream in some way here
                }
                rs.close();
            }
           
           // ps.close();
            
            // UI should live in its own file and just get value from the database.
            // Ideally the user interacts with the UI and the UI will pass the 
            // values inputted by the user to the servlet/controller. This 
            // usually happens through the request object or the params. These
            // values will then be interpretted and naively validatd(null checks,
            // negative where positive is expected, etc.) and passed on to the
            // class that handles business level logic. Business level logic is
            // where the bulk of the processing occurs. Business rules are also
            // validated here. The business layer also makes use of the database
            // class to perform queries or insert data into the database. This
            // way each layer has a distinct role that it needs to do. Code
            // becomes easier to reuse and refactor.
            
            // User -> UI -> Servlet/Controller -> Business Layer -> DB Layer
            // User <- UI <- Servlet/Controller <- Business Layer <- DB Layer
            // Code should only communicate to the layer directly above and
            // below it.
            
           /*JLabel label = new JLabel();
           label.setIcon(InsertData.createImageIcon(imgBytes));
           label.setLocation(50, 100);
           //label.setText("Nothing");
           JPanel panel = new JPanel();
           //panel.setLayout(null);
           panel.add(label);
           panel.setBounds(0, 0,500, 500);
           JFrame frame = new JFrame();
           //frame.pack();
           frame.setBounds(30, 30, 500, 500);
           frame.add(panel);
           //frame.setLayout(null);
           //frame.pack();
           frame.setVisible(true);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
           
       }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           System.out.println("");
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 conn.close();
           }catch(SQLException se){
           }// do nothing
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
    private static BufferedImage createRGBImage(byte[] bytes, int width, int height) {
    DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
    ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]{8, 8, 8}, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
    return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, width, height, width * 3, 3, new int[]{0, 1, 2}, null), false, null);
   }
   public static ImageIcon createImageIcon(byte[] input){
     ImageIcon icon = new ImageIcon(input);
     Image img = icon.getImage();
     Image newimg = img.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH);
     icon = new ImageIcon(newimg);
     return icon;
  }
}
