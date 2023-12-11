import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;

public class CheckboxesDemo {
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
        JFrame frame = new JFrame("Text fields: A new way of accepting input in java!");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        //A panel to store all the text fields
        JPanel checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new BoxLayout(checkboxesPanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(checkboxesPanel);


        JPanel basicCheckboxPanel = new JPanel();
        checkboxesPanel.add(basicCheckboxPanel);

        JLabel basicCheckboxLabel = new JLabel("Basic (default) checkbox:");
        basicCheckboxPanel.add(basicCheckboxLabel);

        //Create a new JCheckbox (checkbox)
        JCheckBox basicCheckbox = new JCheckBox();
        //Set the label for this checkbox
        basicCheckboxLabel.setLabelFor(basicCheckbox);
        basicCheckboxPanel.add(basicCheckbox);


        JPanel disabledCheckboxPanel = new JPanel();
        checkboxesPanel.add(disabledCheckboxPanel);

        JLabel disabledCheckboxLabel = new JLabel("Disabled checkbox:");
        disabledCheckboxPanel.add(disabledCheckboxLabel);

        //Create a new JCheckbox (checkbox)
        JCheckBox disabledCheckbox = new JCheckBox();
        //Set the label for this checkbox
        disabledCheckboxLabel.setLabelFor(disabledCheckbox);
        //Disable this checkbox
        disabledCheckbox.setEnabled(false);
        disabledCheckboxPanel.add(disabledCheckbox);


        JPanel actionCheckboxPanel = new JPanel();
        checkboxesPanel.add(actionCheckboxPanel);

        JLabel actionCheckboxLabel = new JLabel("Try enabling me...");
        actionCheckboxPanel.add(actionCheckboxLabel);

        //Create a new JCheckbox (checkbox)
        JCheckBox actionCheckbox = new JCheckBox();
        //Set the label for this checkbox
        actionCheckboxLabel.setLabelFor(actionCheckbox);
        actionCheckbox.addChangeListener(new ChangeListener() {
            //Code to be executed when the checkbox's state is changed (selected or deselected)
            public void stateChanged(ChangeEvent e) {
                //Check whether the checkbox is selected (to show the usage of isSelected())
                if (actionCheckbox.isSelected()) {
                    //Display an evil message of triumph
                    JOptionPane.showMessageDialog(frame, "HAHA!!! You can't select me! I have a ChangeListener that detects when I am selected and deselects me!", "MUAHAHAHAA!!!", JOptionPane.INFORMATION_MESSAGE);
                    //Set the actionCheckbox to deselected
                    actionCheckbox.setSelected(false);
                }
            }
        });
        actionCheckboxPanel.add(actionCheckbox);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
