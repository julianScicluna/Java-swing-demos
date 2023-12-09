import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class LabelsDemo {
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
        JFrame frame = new JFrame("Labels demo");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        //Create a JPanel to store all the JLabels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //Add the panel to the frame (its content pane)
        frame.getContentPane().add(panel);

        //Create a JLabel
        JLabel basicLabel = new JLabel();
        //Set the label's text
        basicLabel.setText("This is a basic label");
        //Add the label to the panel
        panel.add(basicLabel);

        //Create a JLabel
        JLabel colorLabel = new JLabel();
        //Set the label's text
        colorLabel.setText("I am red! Wow!");
        //Set the label's text colour to red
        colorLabel.setForeground(new Color(255 /*r*/, 0 /*g*/, 0 /*b*/));
        //Add the label to the panel
        panel.add(colorLabel);

        //Create a JLabel
        JLabel fontLabel = new JLabel();
        //Set the label's text
        fontLabel.setText("Wow! I'm in Algerian with font size 20px!");
        //Set the label's text font
        fontLabel.setFont(new Font("Algerian", Font.PLAIN, 20));
        //Add the label to the panel
        panel.add(fontLabel);

        //Create a JLabel
        JLabel italicBoldLabel = new JLabel();
        //Set the label's text
        italicBoldLabel.setText("Hey! I'm in Consolas (size: 15px) but bold AND italic!");
        //Set the label's font to Consolas, but in bold AND italic
        italicBoldLabel.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 15));
        //Add the label to the panel
        panel.add(italicBoldLabel);

        //Create a reference to an ImageIcon
        ImageIcon icon;
        try {
            //Fetch an image from the internet, turn it into an ImageIcon and then set it as the iconLabel's (JLabel) icon
            icon = new ImageIcon(new URL("https://cdn.iconscout.com/icon/free/png-64/java-25-226002.png"));
        } catch (MalformedURLException murle) {
            //Something went wrong while fetching the icon (bad URL)
            murle.printStackTrace();
            frame.dispose();
            return;
        }

        //Create a JLabel
        JLabel iconLabel = new JLabel();
        //Set the label's text
        iconLabel.setText("Hey!");
        //Set the label's icon
        iconLabel.setIcon(icon);
        //Add the label to the panel
        panel.add(iconLabel);

        //Create a JLabel
        JLabel iconTextReverseLabel = new JLabel();
        //Set the label's text
        iconTextReverseLabel.setText("I have an icon too, but it's on the other side!");
        //Set the label's icon
        iconTextReverseLabel.setIcon(icon);
        //Set the label's component orientation to right to left (display components the other way round)
        iconTextReverseLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //Add the label to the panel
        panel.add(iconTextReverseLabel);


        //Create a label containing text and an icon, separated
        JLabel iconTextGapLabel = new JLabel();
        //Set the label's text
        iconTextGapLabel.setText("I am a label with an icon and text, 20px apart!!");
        iconTextGapLabel.setIcon(icon);
        //Set the gap between the icon and text to 20px
        iconTextGapLabel.setIconTextGap(20);
        //Add the label to the panel
        panel.add(iconTextGapLabel);

        frame.setVisible(true);
    }
}
