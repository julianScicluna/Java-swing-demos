import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;

public class ListenersDemo {
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
        JFrame frame = new JFrame("Event listeners demo");
        //Set the frame's dimensions to 500px by 500px
        frame.setSize(500, 500);

        //Create a main content JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        frame.getContentPane().add(mainPanel);

        //Create a new JPanel in the middle of the main panel to store the UI
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(contentPanel, gbc);

        
        //Create a new panel with the below message
        AreaPanel areaPanel = new AreaPanel("Click me, drag me, use your mouse!\nAfter that, press keys!\nP.S.: Look at the console");
        //Add a amouse listener which listens for various events with the mouse
        areaPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseExited(MouseEvent e) {
                //Executed when the mouse has moved out of the component's bounding box 
                System.out.println("The mouse has exited me");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                //Executed when the pressed mouse has been released on the component
                System.out.println("The mouse has been released on me");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //Executed when the component has been clicked - WARNING: A click refers to a mouse press followed by mouse release with NO mouse moving in between
                System.out.println("I have been clicked");
            }
            @Override
            public void mousePressed(MouseEvent e) {
                //Executed when the mouse has been pressed while on the component
                //Focus on the clicked component
                ((JComponent) e.getSource()).requestFocusInWindow();
                System.out.println("The mouse has been pressed down on me");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //Executed when the mouse has moved into the component's bounding box 
                System.out.println("The mouse has entered me");
            }
        });
        //Create a mouse motion listener - listens for events pertaining to the cursor's motion
        areaPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                //Executed when the cursor has been moved while on the component
                System.out.println("The cursor has been moved to [x: " + e.getX() + ", y: " + e.getY() + "]");
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                //Executed when the cursor has been dragged while on the component (i.e.: moved with the mouse pressed)
                System.out.println("The cursor has been dragged to [x: " + e.getX() + ", y: " + e.getY() + "]");
            }
        });
        //Create a mouse wheel listener - listens for movement in the mouse wheel
        areaPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //Executed when the mouse wheel has been moved (scrolled) while the cursor is on the component
                System.out.println("The mouse wheel has been moved on me by " + e.getScrollAmount() + " units!");
            }
        });
        //Create a key listener - listens for key events (NOTE: GENERALLY, KEY BINDINGS ARE USED FOR THIS)
        //N.B.: The key listener ONLY works if the component it listens for is the focused component
        areaPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                //This code is executed when a key is pressed
                System.out.println("A key has been pressed down {char: " + e.getKeyChar() + ", keyCode: " + e.getKeyCode() + "}");
            }
            @Override
            public void keyReleased(KeyEvent e) {
                //This code is executed when a key is released
                System.out.println("A key has been released {char: " + e.getKeyChar() + ", keyCode: " + e.getKeyCode() + "}");
            }
            @Override
            public void keyTyped(KeyEvent e) {
                //This code is executed when a key is typed, that is to say consecutively pressed and released
                System.out.println("A key has been typed {char: " + e.getKeyChar() + ", keyCode: " + e.getKeyCode() + "}");
            }
        });
        //Make the big panel and its grid allocate all remaining x and y space
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0f;
        gbc.weighty = 1.0f;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(areaPanel, gbc);


        //Create a button which does something on click
        JButton actionButton = new JButton("Click me for free chocolate!");
        //Listens for component-specific actions. For a button, it would be a click
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //When the button is clicked, show a message dialog
                JOptionPane.showMessageDialog(frame, "HAHA!!! No chocolate for you!", "I LIED!!!", JOptionPane.WARNING_MESSAGE);
            }
        });
        contentPanel.add(actionButton);


        //Create a checkbox
        JCheckBox optionBox = new JCheckBox();
        //Listen for changes (including programmatic changes through setSelected())
        optionBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                System.out.println("State changed: {Clicked: " + ((JCheckBox) e.getSource()).isSelected() + "}");
            }
        });
        //Set text associated with the option box
        optionBox.setText("Click me...");
        contentPanel.add(optionBox);


        //Create a text field
        JTextField textField = new JTextField();
        //Add a document listener to the text field's document object in order to listen for dynamic content insertion/removal/replacement
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                //This code is executed when contents of text field are changed
                System.out.println("The text field's content has been changed!");
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                //This code is executed when content is inserted into text field
                System.out.println("Content has been inserted into the text field!");
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                //This code is executed when content is removed from text field
                System.out.println("Content has been removed from the text field!");
            }
        });
        //Add an action listener to check for "submission" of the text field, so to speak (i.e.: pressing ENTER while it is in focus)
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ENTER has been pressed on the text field! [Submitting contents]");
            }
        });
        contentPanel.add(textField);


        //Make the main frame visible
        frame.setVisible(true);
    }


    //This is the class used for drawing the JPanel with blue border and blue, aligned text. Analyse it... if you dare
    private static class AreaPanel extends JPanel {
        //Method to draw text while respecting line breaks
        private static final int LEFT = 0, CENTER = 1, RIGHT = 2;
        private static void drawText(Graphics g, String text, int x, int y, int alignmentInBounds, int xAlignment) {
            String[] lines = text.split("\n");
            int[] widths = new int[lines.length];
            FontMetrics fm = g.getFontMetrics();
            int largestWidth = 0, textMaxHeight = fm.getAscent() + fm.getDescent();
            for (int i = 0; i < lines.length; i++) {
                widths[i] = fm.stringWidth(lines[i]);
                if (widths[i] > largestWidth) {
                    largestWidth = widths[i];
                }
            }
            int xOffset = 0;
            switch (xAlignment) {
                case LEFT:
                    xOffset = 0;
                    break;
                case CENTER:
                    xOffset = -largestWidth/2;
                    break;
                case RIGHT:
                    xOffset = -largestWidth;
                    break;
            }
            for (int i = 0; i < lines.length; i++) {
                switch (alignmentInBounds) {
                    case LEFT:
                        g.drawString(lines[i], x + xOffset, y + i * textMaxHeight);
                        break;
                    case CENTER:
                        g.drawString(lines[i], x + (largestWidth - widths[i])/2 + xOffset, y + i * textMaxHeight);
                        break;
                    case RIGHT:
                        g.drawString(lines[i], x + largestWidth - widths[i] + xOffset, y + i * textMaxHeight);
                        break;
                }
            }
        }
        private String text;
        public AreaPanel(String text) {
            super();
            this.text = text;
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLUE);
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            g2d.setFont(new Font("Consolas", Font.PLAIN, 20));

            //THIS IS A HELPER METHOD TO DRAW TEXT WITH LINES AND ALIGNMENT
            drawText(g2d, text, getWidth()/2, getHeight()/2, LEFT, CENTER);
        }
    }
}
