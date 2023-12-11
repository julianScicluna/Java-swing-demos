import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

public class SlidersDemo {
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
        JFrame frame = new JFrame("Sliders in Swing! We're building a playground! Get it?");
        frame.setSize(700, 700);
        frame.setLayout(new FlowLayout());

        //A panel to store all the sliders
        JPanel slidersPanel = new JPanel();
        slidersPanel.setLayout(new BoxLayout(slidersPanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(slidersPanel);


        JPanel basicSliderPanel = new JPanel();
        slidersPanel.add(basicSliderPanel);

        JLabel basicSliderLabel = new JLabel("Basic slider");
        basicSliderPanel.add(basicSliderLabel);

        //Create a JSlider - the default bounds are 0 - 100 and the default value is 50
        JSlider basicSlider = new JSlider();
        basicSliderLabel.setLabelFor(basicSlider);
        basicSliderPanel.add(basicSlider);


        //Slider with bounds and default value without snapping (binding to closest value)
        JPanel boundedSliderPanel = new JPanel();
        slidersPanel.add(boundedSliderPanel);

        JLabel boundedSliderLabel = new JLabel("I have a minimum value of 5 and a maximum value of 15");
        boundedSliderPanel.add(boundedSliderLabel);

        JSlider boundedSlider = new JSlider();
        //Set the slider's minimum value to 5
        boundedSlider.setMinimum(5);
        //Set the slider's maximum to 15
        boundedSlider.setMaximum(15);
        //Set the slider's default value to 7
        boundedSlider.setValue(7);
        boundedSliderLabel.setLabelFor(boundedSlider);
        boundedSliderPanel.add(boundedSlider);


        //Slider with occasional major and minor marks
        JPanel markedSliderPanel = new JPanel();
        slidersPanel.add(markedSliderPanel);

        JLabel markedSliderLabel = new JLabel("This slider has occasional major and minor marks");
        markedSliderPanel.add(markedSliderLabel);

        JSlider markedSlider = new JSlider();
        //Apply marks to the slider to make it display a mark every ten units
        markedSlider.setMajorTickSpacing(10);
        //Apply minor marks every 5 units
        markedSlider.setMinorTickSpacing(5);
        //Enable the painting of the aforementioned marks, actually called 'ticks'
        markedSlider.setPaintTicks(true);
        //Enable the painting of numbers indicating the mark's position in the slider
        markedSlider.setPaintLabels(true);
        markedSliderLabel.setLabelFor(markedSlider);
        markedSliderPanel.add(markedSlider);


        //Coloured slider
        JPanel colouredSliderPanel = new JPanel();
        slidersPanel.add(colouredSliderPanel);

        JLabel colouredSliderLabel = new JLabel("Hey, this slider is coloured!");
        colouredSliderPanel.add(colouredSliderLabel);

        JSlider colouredSlider = new JSlider();
        //Set the JSlider's background colour to green
        colouredSlider.setBackground(Color.GREEN);

        //With the default Look and Feel, setForeground has no visible effects on JSliders
        //colouredSlider.setForeground(Color.GREEN);
        colouredSliderLabel.setLabelFor(colouredSlider);
        colouredSliderPanel.add(colouredSlider);

        
        //Specify a hashtable for text of marks for the VERTICAL JSlider
        JPanel customMarkedSliderPanel = new JPanel();
        slidersPanel.add(customMarkedSliderPanel);

        JLabel customMarkedSliderLabel = new JLabel("I too have marks, however they are mapped to text!");
        customMarkedSliderPanel.add(customMarkedSliderLabel);

        //Create the table of values
        Hashtable<Integer, JComponent> marksTable = new Hashtable<Integer, JComponent>();

        //Fill the table with entries
        marksTable.put(50, new JLabel("Super mega big"));
        marksTable.put(25, new JLabel("Medium"));
        marksTable.put(10, new JLabel("Tiny"));
        marksTable.put(0, new JLabel("Super mega nothing"));

        JSlider customMarkedSlider = new JSlider();
        //Make the slider vertical
        customMarkedSlider.setOrientation(JSlider.VERTICAL);
        customMarkedSlider.setMaximum(50);
        customMarkedSlider.setMinimum(0);
        //Apply marks to the slider to make it display a mark every 10 units
        customMarkedSlider.setMajorTickSpacing(10);
        //Apply marks to the slider to make it display a minor mark every 5 units
        customMarkedSlider.setMajorTickSpacing(5);
        //Enable the painting of the aforementioned marks, actually called 'ticks'
        customMarkedSlider.setPaintTicks(true);
        //Enable the painting of numbers indicating the mark's position in the slider
        customMarkedSlider.setPaintLabels(true);
        //Set the JSlider's label table
        customMarkedSlider.setLabelTable(marksTable);
        customMarkedSliderLabel.setLabelFor(customMarkedSlider);
        customMarkedSliderPanel.add(customMarkedSlider);


        //Slider with listener for change
        JPanel actionSliderPanel = new JPanel();
        slidersPanel.add(actionSliderPanel);

        JLabel actionSliderLabel = new JLabel();
        actionSliderPanel.add(actionSliderLabel);

        JSlider actionSlider = new JSlider();
        actionSliderLabel.setText("Change me (" + actionSlider.getValue() + ")...");
        actionSliderLabel.setLabelFor(actionSlider);
        //Create change listener
        actionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actionSliderLabel.setText("Change me (" + actionSlider.getValue() + ")...");
            }
        });
        actionSliderPanel.add(actionSlider);

        //Make the main frame visible
        frame.setVisible(true);
    }
}
