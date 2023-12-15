import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class FramesDemo {
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
        JFrame frame = new JFrame("Windows in Swing, or should I say Swing in Windows!");
        frame.setSize(500, 500);

        //A panel to store the JTabbedPane
        JPanel windowCreatorsPanel = new JPanel();
        //To make child components allocate all width and height
        windowCreatorsPanel.setLayout(new GridBagLayout());
        frame.getContentPane().add(windowCreatorsPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));

        //Align the content panel to the very middle of the page
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        windowCreatorsPanel.add(contentPanel, gbc);

        
        //Create and add a JLabel with text "This very frame is a default frame!"
        JLabel defaultFrameLabel = new JLabel("This very frame is a default frame!");
        contentPanel.add(defaultFrameLabel);


        //Create a button which opens an unresizable window on click
        JButton nonresizableFrameBtn = new JButton("Click me to open an unresizable frame");
        nonresizableFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("Try resize me! Oh wait...");
                //Set the window's size to 200px by 200px
                newFrame.setSize(350, 350);
                //Make the window unresizable
                newFrame.setResizable(false);
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(nonresizableFrameBtn);


        //Create a button which opens an window which is always on top (in the z-axis) on click
        JButton alwaysOnTopFrameBtn = new JButton("Click me to open a frame which is always above every other frame");
        alwaysOnTopFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("Try resize me! Oh wait...");
                //Set the window's size to 200px by 200px
                newFrame.setSize(350, 350);
                //Make the window always appear above every other window
                newFrame.setAlwaysOnTop(true);
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(alwaysOnTopFrameBtn);


        //Create a button which opens an undecorated window on click
        JButton undecoratedFrameBtn = new JButton("Click me to open an undecorated frame");
        undecoratedFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("Try resize me! Oh wait...");
                //Set the window's size to 200px by 200px
                newFrame.setSize(350, 350);
                //Make the window unresizable - MUST be invoked BEFORE the window is made visible
                newFrame.setUndecorated(true);
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(undecoratedFrameBtn);

        //Create a button which opens a window with a custom icon on click
        JButton customIconFrameBtn = new JButton("Click me to open a frame with a custom icon");
        customIconFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("How's my new icon?");
                try {
                    //Set the frame's icon to the image at the specified URL
                    newFrame.setIconImage(ImageIO.read(new URL("https://icon-library.com/images/small-icon-png/small-icon-png-6.jpg")));
                } catch (IOException ioe) {
                    //Something went wrong while fetching 
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Oh no! Something went wrong while fetching the frame's image icon!", "Error while creating frame", JOptionPane.ERROR_MESSAGE);
                }
                //Set the window's size to 200px by 200px
                newFrame.setSize(350, 350);
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(customIconFrameBtn);

        //Create a button which opens a maximised window
        JButton maximisedFrameBtn = new JButton("Click me to open a maximised frame");
        maximisedFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("How's my new icon?");
                //Set the frame's state to maximised on both axes
                newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //Set the window's size to 200px by 200px
                newFrame.setSize(350, 350);
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(maximisedFrameBtn);


        //Create a button which opens a window with listeners
        JButton listenerFrameBtn = new JButton("Click me to open frame with listeners");
        listenerFrameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create a new JFrame (window)
                JFrame newFrame = new JFrame();
                //Set the window's title
                newFrame.setTitle("I have got listeners and would respond like any human...");
                //Make the frame do nothing by default when the 'X' (close) button is pressed
                newFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                //Add a window listener to act on window state change
                newFrame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        //This code is executed when the window is supposed to close (i.e.: when the "X" button is pressed)
                        if (JOptionPane.showConfirmDialog(newFrame, "Please don't!!! I've got a wife and kids!!! Do you still want to close me?", "Window's plea for life", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                            newFrame.dispose();
                        }
                    }
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //This code is executed when the window is closed (i.e.: when the dispose() method is invoked on the frame)
                        System.out.println("I have been closed... dying...");
                    }
                    @Override
                    public void windowIconified(WindowEvent e) {
                        //This code is executed when the window is minimised
                        System.out.println("HEY!!! How dare you minimise ME? I need the spotlight!");
                    }
                    @Override
                    public void windowActivated(WindowEvent e) {
                        //This code is executed when the window is set as the active window
                        System.out.println("I have been activated (set to the active window)... Boy does it feel good to be back!");
                    }
                    @Override
                    public void windowOpened(WindowEvent e) {
                        //This code is executed when the window has been made visible for the first time through the setVisible() method
                        System.out.println("I have been made visible for the first time... I mean \"goo goo gaa gaa I am a baby window\"");
                    }
                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        //This code is executed when the window has been maximised
                        System.out.println("I am no longer minimised... I'm back in the window power game!");
                    }
                    @Override
                    public void windowDeactivated(WindowEvent e) {
                        //This code is executed when the window is no longer the active window
                        System.out.println("I have been deactivated (am no longer the active window)... I was once on the spotlight...");
                    }
                });
                //Set the window's size to 200px by 200px
                newFrame.setSize(500, 500);
                newFrame.setLocation(new Point(frame.getX() + 100, frame.getY() + 100));
                //Make the window visible
                newFrame.setVisible(true);
            }
        });
        contentPanel.add(listenerFrameBtn);


        //Make the main frame visible
        frame.setVisible(true);
    }
}
