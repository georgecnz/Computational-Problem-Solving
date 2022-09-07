/* 
* Etude3 COSC326.
* @author George Churton.
* Interactive Koch Snowflake.
* This file uses java gui classes to create an interface allowing Koch fractals to be seen.
*/
import java.awt.*;
import javax.print.attribute.standard.Sides;
import javax.swing.*;
import java.awt.event.*;
import java.lang.System.Logger.Level;
import java.math.*;
import java.util.EventListener;
import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.Dimension;

/*KochPanel extends the JPanel, java gui class.
*/

public class KochPanel extends JPanel{ // extends J PaneL
 boolean flag1 = false;
   static int input;
   static Dimension size;
    static double width, height;
    //double heightDraw, widthDraw;

 /*
   * The method getInput calls upon the input in the Buttons class.
   * here variables from ButtonListner can be accessed for computation.
   * @param
   */

  public static void getInput(int phyiscalInput){
    input = phyiscalInput;
  }

  public static double getFrame(double w, double h){
    width = w;
    height = h;
    return h;
  }
 
   /*
   * The contructor KochPanel constructs the layout of the JPanel.
   * here variables are initalised and in some cases decleared.
   */

  public KochPanel(){
      setPreferredSize (new Dimension (500, 500));
      setBackground (Color.blue);
      
  }

  
    
   /**
   * The paintComponent method does the actual drawing for the drawing panel
   * Here calaculations are made regarding the specifications of an equalatiral triangle.
   * Once points are calculated they are passed on to the KochDraw method.
   * @param g - representing graphical paramaters
   * I have approached the scaling issue through focusing on the actual drawn shpes.
   */

  public void paintComponent(Graphics g){ 
    double width = getWidth();
    double height = getHeight();
    setBackground(Color.blue);

    int a = (int)(width/1.66666666667);
    int b = (int)(height/2);

     if(width > height){
      setSize((int) height, (int)height);
      repaint();

    }else if(height > width){
     setSize((int)width, (int)width);
     repaint();

   }else{
     setSize((int)width, (int)height);
     repaint();
   }


    Double topTri = Math.sqrt((a*a) + (b*b));
    int topTriInt = (int)Math.round(topTri);
    int sides = (3 * (int)Math.pow(4, (input - 1)));
    double widthDraw = 0;
    double heightDraw = 0;

    int length = 300;
    double angle = 60 * Math.PI / 180;
    int startX = (int)(width/2);// 250
    int startY =  (int)(height/5); //100

     int endX = startX + (int)Math.cos(angle) * length;
     int endY = startY + (int)Math.sin(angle) * length;

     int startX2 = (int)(width/5);
     int startY2 = topTriInt;

      int endX2 = startX2+ (int)Math.cos(angle) * length;
      int endY2 = startY2 + (int)Math.sin(angle) * length;

      int startX3 = (int)(width/1.25);
      int startY3 = topTriInt;

      int endX3 = startX2+ (int)Math.cos(angle) * length;
      int endY3 = startY2 + (int)Math.sin(angle) * length;

      KochDraw(g, input, endX, endY, endX2, endY2);//left
      repaint();
      KochDraw(g, input, endX2, endY2, endY3, endY3);//base
      repaint();
      KochDraw(g, input, endY3, endY3, endX, endY);//right side
      repaint();
    }
  
     /**
      * The KochDraw method recursively constructs fractals for each line specified via input. 
      * @param g, input, x1, y1, x5, y5.
      */

      public void KochDraw(Graphics g, int input, int x1, int y1, int x5, int y5){
        int rangeX, rangeY, x2, y2, x3, y3, x4, y4;

        //Equilateral triangle
        if(input == 1){
          g.setColor(Color.white);
          g.drawLine(x1, y1, x5, y5);
          g.setColor(Color.white);
          repaint();

        } else if (input == 2 || input == 3 || input == 4 || input == 5 || input == 6 || input == 7 || input == 8 || input == 9){

          //Koch's logic
          //Replace the middle third of each side with two sides of an equilateral triangle.
              rangeX = x5 - x1;
              rangeY = y5 - y1;

               //breaking up side for new triangles creation...
              x2 = x1 + rangeX / 3;
              y2 = y1 + rangeY / 3; 
              int Xside = x1 + x5;
              int Yside = y1 + y5;
              
              double xConvert = ((0.5) * Xside + Math.sqrt(3) * (y1-y5)/6);
              double yConvert = ((0.5) * Yside + Math.sqrt(3) * (x5-x1)/6);
              x3 = (int)xConvert;
              y3 = (int)yConvert; 

              x4 = x1 + 2 * rangeX /3;
              y4 = y1 + 2 * rangeY /3;


              //Recursively draw passing parameters:
              KochDraw(g,input-1, x1, y1, x2, y2);
              repaint();
              KochDraw(g,input-1, x2, y2, x3, y3);
              repaint();
              KochDraw(g,input-1, x3, y3, x4, y4);
              repaint();
              KochDraw(g,input-1, x4, y4, x5, y5);
              repaint();
        }
      }
    }



