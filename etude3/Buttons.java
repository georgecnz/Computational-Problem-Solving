/* 
* Etude3 COSC326.
* @author George Churton.
* Buttons.java
* This file calls upon the constructor Buttons.
* Here the JFrame Controller is established and listners initalised.
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.chrono.ThaiBuddhistChronology;
import javax.swing.JOptionPane;

/*KochPanel extends the JPanel, java gui class.
*/

public class Buttons extends JPanel{ // extends J Panel
  private JPanel controlPanel; //imprting packages
  public JButton compute;
  private JLabel entry, entry1;
  public JTextField orderN; 
  private boolean flag1 = false;
  public static int phyiscalInput;
  
  /**
   * The contructor KochPanel constructs the layout of the JPanel.
   * here variables are initalised and in some cases decleared.
   */

  public Buttons(){
    controlPanel =  new JPanel();
    entry = new JLabel("Please enter an order number");
    entry1 = new JLabel("ranging from 1-9");
    orderN = new JTextField(2);
    compute = new JButton("Execute");
  

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 0.5;
    c.weighty = 0.5;
    setLayout(gridbag);
  
    controlPanel.setPreferredSize(new Dimension (200, 200));
    controlPanel.setBackground (Color.gray);

    add (controlPanel, c);
    controlPanel.add(entry);
    controlPanel.add(entry1);
    controlPanel.add(orderN);
    controlPanel.add(compute);
  
    ButtonListener listener = new ButtonListener();
    orderN.addActionListener(listener);
    compute.addActionListener(listener);

  }

   /*
   * The class ButtonListener implements ActionListner classes.
   * Here actions perfromed by users in the controller JFrame are monitored.
   */

  public class ButtonListener implements ActionListener {

    /*
    *The actionPerformed method interprets user input into data for KochPanel draw operations.
    *@param event- is an ActionEvent, such paramter is passed by the ButtonListener if user input occurs.
    *This method will provide a java error dialog if input doesnt met the specifications seen in the README.MD file. 
    */

        public void actionPerformed (ActionEvent event){
        if(event.getSource() == compute){
            String inputString = orderN.getText();
           
            if(inputString.matches("[1-9]{1}")){
            phyiscalInput = Integer.parseInt(inputString);
            KochPanel.getInput(phyiscalInput);
            }else{
              JOptionPane.showMessageDialog(null, "Program Error, Order Number not supported", "SnowflakeApp Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      }
    }