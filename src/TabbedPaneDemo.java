import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;

public class TabbedPaneDemo {
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
        JFrame frame = new JFrame("Tabs and tabbed panes in Swing... no pun this time!");
        frame.setSize(700, 700);

        //A panel to store the JTabbedPane
        JPanel tabbedPanePanel = new JPanel();
        //To make child components allocate all width and height
        tabbedPanePanel.setLayout(new BorderLayout());
        frame.getContentPane().add(tabbedPanePanel);

        //Create the JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        //Make tabs wrap should there not be enough horizontal space to fit them all in one row
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

        tabbedPanePanel.add(tabbedPane, BorderLayout.CENTER);

        
        //Create a JPanel to store as a tab
        JPanel tab1Panel = new JPanel();
        //Add tab1Panel to the tabbed pane as a new tab
        tabbedPane.addTab("Chocolate Tab", tab1Panel);
        populateTab(tab1Panel, new String[] {
            "Do you like chocolate?",
            "YES!!!"
        });
        //Add a close button to the zeroth tab component
        addTabCloseButton(tabbedPane, 0);

        
        //Create another JPanel to store as another tab
        JPanel tab2Panel = new JPanel();
        //Add tab1Panel to the tabbed pane as a new tab
        tabbedPane.addTab("Java Fridays Tab", tab2Panel);
        populateTab(tab2Panel, new String[] {
            "Did you love Java Fridays?",
            "Definitely"
        });
        //Add a close button to the first tab component
        addTabCloseButton(tabbedPane, 1);

        
        //Create another JPanel to store as yet another tab
        JPanel tab3Panel = new JPanel();
        //Add tab1Panel to the tabbed pane as a new tab
        tabbedPane.addTab("Pizza Tab", tab3Panel);
        tab3Panel.add(new JLabel("How delicious do you find pizza?"));
        //Create a slider with ticks and labels every (1) unit (slider examples shown on this very github repository)
        JSlider slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setMinimum(0);
        slider.setMaximum(10);
        slider.setValue(10);
        tab3Panel.add(slider);
        //Add a close button to the first tab component
        addTabCloseButton(tabbedPane, 2);
        //Set the mnemonic of the THIRD tab to the "p" button
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_P);
        //When hovering over the THIRD tab, the tool tip text (second parameter) will be displayed
        tabbedPane.setToolTipTextAt(2, "Press \"ALT\" + \"p\" to open me...");


        //Make the main frame visible
        frame.setVisible(true);
    }

    //Method to set the close button on a tab
    public static void addTabCloseButton(JTabbedPane tabbedPane, int tabIndex) {
        //DO NOT store tabIndex in anonymos inner classes. Store the Component object instead
        //The component referenced to by the index may change when tabs are added/removed. The Component object, however, will not
        Component tabComponent = tabbedPane.getComponentAt(tabIndex);

        //Add a component in the place of the tab as the tab component (not the content mapping to the tab)
        JPanel tabComponentPanel = new JPanel();

        //A JLabel which always displays the title of the tab
        JLabel tabTitleLabel = new JLabel() {
            //Override method used internally to get/display text
            @Override
            public String getText() {
                int tabIndex = tabbedPane.indexOfComponent(tabComponent);
                if (tabIndex != -1) {
                    return tabbedPane.getTitleAt(tabIndex);
                }
                return null;
            }
        };
        tabComponentPanel.add(tabTitleLabel);

        //Create a button to close the tab
        CloseButton tabCloseBtn = new CloseButton();
        tabCloseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //On button press, get the tab's index
                int index = tabbedPane.indexOfComponent(tabComponent);
                //Remove the index previously obtained
                tabbedPane.removeTabAt(index);
            }
        });
        tabComponentPanel.add(tabCloseBtn);

        //Set the JComponent of the first tab -> tab component (tab indexing starts at zero). In this case, tabIndex can be used, seeing an nothing could have possibly changed in the duration of this method's execution, ASSUMING IT WAS EXECUTED ON THE EDT
        tabbedPane.setTabComponentAt(tabIndex, tabComponentPanel);
    }

    //Helper method to populate a JPanel - DRY -> Don't Repeat Yourself!
    public static void populateTab(JPanel tab, String[] data) {
        JLabel text = new JLabel(data[0]);
        tab.add(text);
        JButton button = new JButton(data[1]);
        tab.add(button);
    }

    //Class for close buttons
    public static class CloseButton extends JButton {
        private int defaultSize = 20;
        public CloseButton() {
            super();
            this.setPreferredSize(new Dimension(defaultSize, defaultSize));
        }
        public CloseButton(int size) {
            super();
            if (size < 0) {
                throw new IllegalArgumentException("Size must not be negative");
            }
            this.setPreferredSize(new Dimension(size, size));
        }
        @Override
        public void setPreferredSize(Dimension d) {
            if (d.getWidth() == d.getHeight()) {
                super.setPreferredSize(d);
            } else {
                throw new IllegalArgumentException("In the case of a CloseButton instance, the with and height of a preferredsize MUST be equal");
            }
        }
        @Override
        public void paintBorder(Graphics g) {
            //Case the graphics object to a Graphics2D object
            Graphics2D g2d = (Graphics2D) g;
            //Save the stroke
            Stroke savedStroke = g2d.getStroke();
            //Make the line 2.0 pixels thick
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.setColor(new Color(100, 100, 100));
            if (getModel().isPressed()) {
                g2d.drawLine(1, 1, 1, getHeight() - 1);
                g2d.drawLine(1, 1, getWidth() - 1, 1);
            } else {
                g2d.drawLine(getWidth() - 1, 1, getWidth() - 1, getHeight() - 1);
                g2d.drawLine(getWidth() - 1, getHeight() - 1, 1, getHeight() - 1);
            }
            //Revert the stroke
            g2d.setStroke(savedStroke);
        }
        @Override
        public void paintComponent(Graphics g) {
            //Invoke this method on the base class (JButton) BEFORE doing ANY painting - VERY IMPORTANT
            super.paintComponent(g);
            //Case the graphics object to a Graphics2D object
            Graphics2D g2d = (Graphics2D) g;
            //Check whether the button is pressed
            if (getModel().isPressed()) {
                //If it is, set the graphics context colour to magenta
                g2d.setColor(Color.MAGENTA);
            } else {
                //Otherwise, set the graphics context colour to red
                g2d.setColor(Color.RED);
            }
            //Save the current graphics context transform
            AffineTransform savedTransform = g2d.getTransform();
            //Translate the context to (what was) its middle
            g2d.translate(getWidth()/2, getHeight()/2);
            //Save the stroke
            Stroke savedStroke = g2d.getStroke();
            //Make the line 2.0 pixels thick
            g2d.setStroke(new BasicStroke(2.0f));
            //Draw the cross
            g2d.drawLine(-getWidth()/4, -getHeight()/4, getWidth()/4, getHeight()/4);
            g2d.drawLine(-getWidth()/4, getHeight()/4, getWidth()/4, -getHeight()/4);
            //Revert the graphics context transform to the original
            g2d.setTransform(savedTransform);
            //Revert the stroke
            g2d.setStroke(savedStroke);
        }
    }
}
