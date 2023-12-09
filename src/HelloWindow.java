import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.Color;

import java.lang.reflect.InvocationTargetException;

public class HelloWindow {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                //WARNING: Swing is NOT thread-safe; execute all code involving swing components on the EDT to avoid a number of issues such as inconsistent GUI and object state and exceptions
                public void run() {
                    initialiseGUI();
                }
            });
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }
    }
    public static void initialiseGUI() {
        //Create a new window and set its size and layout
        JFrame frame = new JFrame("Hello window!");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        //Create a JLabel with the text "Hey, look at me! I am text!"
        JLabel text = new JLabel("Hey, look at me! I am text!");
        //Set the text's colour to red
        text.setForeground(Color.RED);
        //Add the label to the frame (window)'s content pane
        frame.getContentPane().add(text);
        
        //Create a JButton with the text "I am a swing button!". For now it does nothing
        JButton button = new JButton("I am a swing button!");
        frame.getContentPane().add(button);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
