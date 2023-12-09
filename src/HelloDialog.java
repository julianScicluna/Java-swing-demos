import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.InvocationTargetException;

public class HelloDialog {
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
        JFrame frame = new JFrame("totally not a prank window...");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        //Create a JButton with the text "Click for free money..."
        JButton button = new JButton("Click for free money...");
        //Make the button do something every time it is clicked
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JDialog - dialog frame
                JDialog dialog = new JDialog(frame, "PSYCH!!!", true);
                //Set the layout manager to a GridBagLayout, for centering of content
                dialog.getContentPane().setLayout(new GridBagLayout());
                //Set the dialog box's size and prevent the user from resizing it
                dialog.setSize(300, 140);
                dialog.setResizable(false);

                //Create main content panel to store dialog's contents
                JPanel mainPanel = new JPanel();
                //Set the main panel's layout to the BoxLayout with parameter BoxLayout.PAGE_AXIS to display contents vertically
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
                GridBagConstraints gbc = new GridBagConstraints();
                //Anchor the mainPanel to the horizontal and vertical centre of the JDialog
                gbc.anchor = GridBagConstraints.CENTER;
                //Add the mainPanel
                dialog.getContentPane().add(mainPanel, gbc);

                //Add a JLabel to tell the user that he has been tricked
                JLabel trickLabel = new JLabel("PSYYYYCH!!!! LOLLL!!!!");
                mainPanel.add(trickLabel);
                //Create a jbutton
                JButton btn = new JButton("OK");
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Close the dialog and free any resources associated with it
                        dialog.dispose();
                    }
                });
                //Add the button to the main panel
                mainPanel.add(btn);

                //Make the dialog visible
                dialog.setVisible(true);
            }
        });
        //Add the intial "Click for free money..." button to the frame's content pane
        frame.getContentPane().add(button);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
