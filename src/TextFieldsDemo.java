import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class TextFieldsDemo {
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
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(fieldsPanel);

        JPanel basicTextFieldPanel = new JPanel();
        fieldsPanel.add(basicTextFieldPanel);

        JLabel basicTextFieldLabel = new JLabel("Basic (default) field:");
        basicTextFieldPanel.add(basicTextFieldLabel);

        //Create a new JTextField (text field)
        JTextField basicTextField = new JTextField();
        //Set the label for this input
        basicTextFieldLabel.setLabelFor(basicTextField);
        //Set the text field width
        basicTextField.setColumns(10);
        basicTextFieldPanel.add(basicTextField);

        JPanel formattedTextTextFieldPanel = new JPanel();
        fieldsPanel.add(formattedTextTextFieldPanel);

        JLabel formattedTextTextFieldLabel = new JLabel("Coloured text field:");
        formattedTextTextFieldPanel.add(formattedTextTextFieldLabel);

        //Create a new JTextField (text field)
        JTextField formattedTextTextField = new JTextField();
        //Set the label for this input
        formattedTextTextFieldLabel.setLabelFor(formattedTextTextField);
        //Set the text field width
        formattedTextTextField.setColumns(10);
        //Set the font of the text in the text field
        formattedTextTextField.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        //Set the colour of the text in the text field
        formattedTextTextField.setForeground(new Color(255, 0, 255));
        //Set the colour of the background of the text field
        formattedTextTextField.setBackground(new Color(0, 255, 0));
        formattedTextTextFieldPanel.add(formattedTextTextField);

        JPanel actionTextFieldPanel = new JPanel();
        fieldsPanel.add(actionTextFieldPanel);

        JLabel actionTextFieldLabel = new JLabel("Text field with action text: \"\"");
        actionTextFieldPanel.add(actionTextFieldLabel);

        //Create a new JTextField (text field)
        JTextField actionTextField = new JTextField();
        actionTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When this event is fired (pressing ENTER key), the content of the text field is written to the label
                actionTextFieldLabel.setText("Text field with action text: \"" + actionTextField.getText() + "\"");
            }
        });
        //Set the label for this input
        actionTextFieldLabel.setLabelFor(actionTextField);
        //Set the text field width
        actionTextField.setColumns(10);
        actionTextFieldPanel.add(actionTextField);


        JPanel disabledTextFieldPanel = new JPanel();
        fieldsPanel.add(disabledTextFieldPanel);

        JLabel disabledTextFieldLabel = new JLabel("Disabled (greyed out) field:");
        disabledTextFieldPanel.add(disabledTextFieldLabel);

        //Create a new JTextField (text field)
        JTextField disabledTextField = new JTextField();
        //Disable the text field
        disabledTextField.setEditable(false);
        //Set the label for this input
        disabledTextFieldLabel.setLabelFor(disabledTextField);
        //Set the text field width
        disabledTextField.setColumns(10);
        disabledTextFieldPanel.add(disabledTextField);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
