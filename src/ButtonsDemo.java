import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class ButtonsDemo {
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
        JFrame frame = new JFrame("Button, buttons everywhere!");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        //A panel to store all the JButtons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(buttonsPanel);

        JButton basicButton = new JButton();
        basicButton.setText("Hey! I'm a button!");
        buttonsPanel.add(basicButton);

        //Specifying the button's text in the constructor instead of using colouredButton.setText(string)
        JButton colouredButton = new JButton("Wow! Font and colours!");
        //Set the text (foreground) colour to red
        colouredButton.setForeground(Color.RED);
        //Set the button background colour to blue
        colouredButton.setBackground(Color.BLUE);
        //Set the button text's font to Calibri, bold and size 20px
        colouredButton.setFont(new Font("Calibri", Font.BOLD, 20));
        buttonsPanel.add(colouredButton);

        //Create a new JButton and set its text to the string specified in the constructor
        JButton textIconButton = new JButton("Nice image, isn't it? 30px gap!");
        try {
            //Set the button's icon
            textIconButton.setIcon(new ImageIcon(new URL("https://p1.hiclipart.com/preview/905/302/386/the-ultimate-patrick-star-png-clipart.jpg")));
        } catch (MalformedURLException murle) {
            //Oops! Something went wrong! (Bad or invalid URL) - Print the exception stack trace
            murle.printStackTrace();
            //Dispose of the frame
            frame.dispose();
            //Terminate program execution
            return;
        }
        //Set the gap between the text and the icon to 20px
        textIconButton.setIconTextGap(20);
        //Add the button to the panel
        buttonsPanel.add(textIconButton);

        //Create a JButton with the text "Click for free money..."
        JButton actionButton = new JButton("Click for free money...");
        //Set the button's mnemonic - button will be triggered when ALT + F are pressed
        actionButton.setMnemonic(KeyEvent.VK_F);
        //Set the character to underline representing the mnemonic key
        actionButton.setDisplayedMnemonicIndex(10);
        //Set the button's tool tip - On hovering, the following text will be displayed
        actionButton.setToolTipText("You won't regret it! Just press ALT + F");
        //Make the button do something every time it is clicked
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hey! You clicked me!");
                //Display a popup with n information icon stating that the JButton was pressed
                JOptionPane.showMessageDialog(actionButton, "Oi, you clicked me!", "Message from button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Add the intial "Click for free money..." button to the frame's content pane
        buttonsPanel.add(actionButton);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
