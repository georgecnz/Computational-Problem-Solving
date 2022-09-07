Etude 3- Interactive Koch Snowflake.
COSC326- Otago University.
@author George Churton.

This program is built on three key files.
These files include:
SnowflakeApp.java
KochPanel.java
Buttons.java

The SnowflakeApp.java file contains the main method and upon compiling is the file that will run.
Within the file two JFrames are created-- both calling on classes within the KochPanel.java and Buttons.java files

The KochPanel.java file draws Koch Snowflake using inputs gathered from the Buttons class which implements ActionListeners.
Each fractal of Koch's logic is determined with respect to the current dimensions to its frame.
This allows the drawing of Koch Snowflakes to be resized upon the movement of the JFrames dimentions.

The JButton.java file constructs the controller JFrame. It additionally listens for user input.

----Running the program----
Once all the files have compiled, run SnowflakeApp.java
Two JFrames will appear, one named "Interactive Koch Snowflake" and the other named "Controller"
The enter the order number (as a single integer) into the JTextField box and click compute for that order number of the KochSnowFlake to be drawn.
This process can be done as many times as desired-- provided the program is running.
The JFrame "Interactive Koch Snowflake" can be resized and the grapical interface, will be reized. i.e the snowflakes dimensions will change at SCALE. 

Note: the Controller frame cannot be resized- as there is no need for it to be reszied.
Note: errors will occur in the terminal if an invalid input such as a char value is entered, there will also be an error if the order exceeds the given range.
Note: closing either frames will terminate the program.

