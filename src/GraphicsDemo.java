import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class GraphicsDemo {
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

    //Utility method to get regular polygon from number of sides, position and radius
    public static Polygon getRegularPolygon(int x, int y, int radius, int numSides) {
        int[] xArr = new int[numSides], yArr = new int[numSides];
        for (int i = 0; i < numSides; i++) {
            xArr[i] = x + (int) (Math.cos((((double) i)/numSides * 360 - 90) * (Math.PI/180)) * radius);
            yArr[i] = y + (int) (Math.sin((((double) i)/numSides * 360 - 90) * (Math.PI/180)) * radius);
        }
        return new Polygon(xArr, yArr, numSides);
    }

    public static void initialiseGUI() {
        //Create a new window and set its size and layout
        JFrame frame = new JFrame("Larry's artistic genius");
        //Set the frame's dimensions to 500px by 500px
        frame.setSize(500, 500);

        DrawingPanel myArtisticBrilliance;
        try {
            //Create a JPanel with a custom painting method
            myArtisticBrilliance = new DrawingPanel(getRegularPolygon(75, 230, 25, 5));
        } catch (IOException ioe) {
            //Something went wrong whilst fetching/loading the image! Let the user know, dispose of the frame and terminate the main thread's execution
            System.out.println("Oh no! Something went wrong with fetching and/or loading the image!");
            frame.dispose();
            return;
        }

        frame.getContentPane().add(myArtisticBrilliance);

        //Make the main frame visible
        frame.setVisible(true);
    }
    private static class DrawingPanel extends JPanel {
        private BufferedImage image;
        private Polygon polygon;
        public DrawingPanel(Polygon polygon) throws IOException {
            //Load the image ONCE (it would be cached)
            this.image = ImageIO.read(new URL("https://th.bing.com/th/id/OIP.BXC4gIcdOhQjLe0UGPXjzwHaGH"));
            this.polygon = polygon;
        }
        @Override
        //Override the painting method for custom painting
        public void paintComponent(Graphics g) {
            //CRITICAL - MUST ALWAYS BE THE FIRST STATEMENT IN THE OVERRIDDEN METHOD (call this method in the context of the superclass)
            super.paintComponent(g);

            //Cast the graphics context to Graphics2D for more drawing capabilities
            Graphics2D g2d = (Graphics2D) g;

            //Set the graphics context's colour to blue
            g2d.setColor(Color.BLUE);

            //Draw a rectangle at (20, 30) 40px wide and 50px tall
            g2d.drawRect(20, 30, 40, 50);

            //Set the graphics context's colour to red
            g2d.setColor(Color.RED);

            //Fill the insides of an oval inside rectangle (x:60, y:75, width:80, height:50)
            g2d.fillOval(60, 75, 80, 50);

            //Save the current graphics context stroke
            Stroke oldStroke = g2d.getStroke();

            //Set the stroke line thickness to 5
            g2d.setStroke(new BasicStroke(5));

            //Set the graphics context's colour to green
            g2d.setColor(Color.GREEN);

            //Draw a line from (50, 50) to (70, 100)
            g2d.drawLine(50, 50, 70, 130);

            //Reset the graphics context stroke to the initial value
            g2d.setStroke(oldStroke);

            //Set the graphics context's font family to Calibri, font size 30px
            g2d.setFont(new Font("Calibri", Font.PLAIN, 30));

            //Save the default transform before any transformations in the graphics context
            AffineTransform oldTransform = g2d.getTransform();

            //Translate the graphics context to the middle of the JPanel and 50px down
            g2d.translate(this.getWidth()/2, 50);

            //Rotate the graphics context by 25 degrees clockwise
            g2d.rotate(25 * (Math.PI/180));

            //Set the graphics context's colour to orange
            g2d.setColor(Color.ORANGE);

            //Get special object to get dimensions of text
            FontMetrics fm = g2d.getFontMetrics();

            //Create a string with text to draw
            String text = "Larry's masterpiece";
            //Draw the text with the specified font and colour
            g2d.drawString(text, -fm.stringWidth(text)/2 /*Get the text's width in pixels based on the font and text*/, fm.getAscent() /*Get the height of the text*/);

            //Reset the graphics context to the old transformation matrix
            g2d.setTransform(oldTransform);

            //Draw the stored image
            g2d.drawImage(this.image, 120, 120, 200, 200, this);

            //Draw the polygon referred to by the class member
            g2d.draw(this.polygon);

            //ADVANCED: Dashed line with stroke thickness 3, 5px line, 5px gap, dash offset 0
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 5.0f, new float[] {5, 5}, 0));

            //Set the graphics context's colour to magenta
            g2d.setColor(Color.MAGENTA);

            //Draw a line from (50, 50) to (70, 100)
            g2d.drawLine(100, 35, 90, 150);

            //Reset the graphics context stroke to the initial value (NEVER modified)
            g2d.setStroke(oldStroke);
        }
    }
}
