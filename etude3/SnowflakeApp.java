/* 
* Etude3 COSC326.
* @author George Churton.
* SnowflakeApp.java
* This file contains the main method for the program.
* Here classes are executed.
*/

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;


/* 
* class SnowflakeApp
* contains main method.
*/

public class SnowflakeApp{
  static double h,w;

/* 
* main method 
* @param String[]args
* Here the two JFrame components are created calling upon constructors from classes
* Buttons and KochPanel.
*/

  public static void main(String[]args){
    JFrame frame = new JFrame ("Interactive Koch Snowflake");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new KochPanel(), BorderLayout.CENTER);
    frame.setBackground(Color.blue);
    frame.setLocation(0, 0);
    frame.pack();
    frame.setVisible(true);
    KochPanel.getFrame(w, h);

  
    JFrame draw = new JFrame ("Controller");
    draw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    draw.getContentPane().add(new Buttons());
    draw.setBackground(Color.blue);
    draw.setLocation(555, 0);
    draw.pack();
    draw.setVisible(true);
    draw.setResizable(false);
  }
}
