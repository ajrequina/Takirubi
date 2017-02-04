/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Arjemariel Requina
 */
public class Data {
  private String info;
  private boolean isImage;
  
  Data(String string, boolean isImage){
    this.isImage = isImage;
    info = string;
  }
  
  public ImageIcon image(){
    return new ImageIcon(info.getBytes());
  }
  
  public String info(){
    return info;
  }
  
  public boolean isImage(){
    return isImage;
  }
  
  public static void main(String[] args) {
    Data d = new Data("111111", true);
    System.out.println(d.isImage ? d.image() : d.info());
  }
}
