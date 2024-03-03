package ru.sidey383.icgpaint.iteraction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class AboutInteraction implements ComponentInteraction {

    private final JDialog dialog;

    public AboutInteraction() {
        dialog = new JDialog();
        dialog.setTitle("About");
        dialog.setMinimumSize(new Dimension(400, 300));
        dialog.setPreferredSize(new Dimension(1000, 800));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("ICGPaint"));
        panel.add(new JLabel("Author: Dmitrii Ponomarev"));

        panel.add(new JLabel("""
                <html>
                <h1>Toolbar:</h1>
                </html>
                """));
        panel.add(loadImage("/icon/toolbar.png", 783, 45));
        panel.add(new JLabel("""
                <html>
                Toolbar contain 5 groups of items
                <h2>Fist:</h2>
                </html>
                """));
        panel.add(loadImage("/icon/group1.png"));
        panel.add(new JLabel("""
                <html>
                    Image and file interaction<br/>
                    Save button - save current image<br/>
                    Load button - load current image<br/>
                    Clear field = clear current image<br/>
                    Resize field - open dialog for resize window<br/>
                <h2>Second:</h2>
                </html>
                """));
        panel.add(loadImage("/icon/group2.png"));
        panel.add(new JLabel("""
                <html>
                    Available draw tools<br/>
                    First - line tool<br/>
                    Second - fill tool<br/>
                    Third - stamp tool<br/>
                    Displays the selected tool by pressing the button
                <h2>Third:</h2>
                </html>
                """));
        panel.add(loadImage("/icon/group3.png"));
        panel.add(new JLabel("""
                <html>
                    One item, open current tool settings<br/>
                <h2>Fourth</h2>
                </html>
                """));
        panel.add(loadImage("/icon/group4.png"));
        panel.add(new JLabel("""
                <html>
                    Select the suggested color, all colors are displayed on the buttons themselves
                <h2>Fifth</h2>
                </html>
                """));
        panel.add(loadImage("/icon/group5.png"));
        panel.add(new JLabel("""
                <html>
                    Color chooser<br/>
                    Show current draw color<br/>
                    When clicked, the color change menu opens<br/>
                </html>
                """));
        panel.add(new JLabel("""
                <html>
                <h1>Draw tools</h1>
                <h2>Line</h2>
                </html>
                """));
        panel.add(loadImage("/icon/brush.png", 100, 100));
        panel.add(new JLabel("""
                <html>
                Draw simple lines.<br/>
                It has 3 parameters. Line color, mode and size<br/>
                <h3>Available modes</h3>
                <h3>Two click</h3>
                Builds a line based on two points that you click on<br/>
                <h3>LMB+RMB</h3>
                Remembers the point where you clicked on the left mouse button and,<br/>
                when you click the right mouse button, builds a line to it.<br/>
                You can press the left mouse button several times in a row<br/>
                <h3>Two click</h3>
                Draws a line while you hold down the mouse button<br/>
                <h2>Fill</h2>
                </html>
                """));
        panel.add(loadImage("/icon/bucket.png", 100, 100));
        panel.add(new JLabel("""
                <html>
                Fills the entire four-connected area with one color<br/>
                Fill has one parameter - color and does not have separate settings.<br/>
                <h2>Span</h2>
                Draws a polygon of a certain shape.<br/>
                Span has 4 parameters type, number of vertices, size and angle.<br/>
                Always draws in black.<br/>
                <h3>Available types</h3>
                <h2>Regular polygon</h2>
                Draw a regular polygon.<br/>
                <h2>Start</h2>
                </html>
                """));
        panel.add(loadImage("/icon/star.png", 100, 100));
        panel.add(new JLabel("""
                <html>
                Draw a start.<br/>
                <h3>Number of vertices</h3>
                The number of vertices for the polygon<br/>
                The number of rays for a star<br/>
                <h3>Size</h3>
                The size sets the radius of the circle inside which the shape will be located<br/>
                <h3>Angle</h3>
                Rotation angle for that figure<br/>
                <br/><br/><br/>
                </html>
                """));



        dialog.add(new JScrollPane(panel));

        dialog.pack();

    }

    private JLabel loadImage(String path, int width, int height) {
        try {
            URL url = this.getClass().getResource(path);
            if (url == null)
                throw new RuntimeException("Can't found resource" + path);
            BufferedImage image = ImageIO.read(url);
            if (image == null)
                throw new RuntimeException("Can't load image from resource" + path);
            return new JLabel(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            System.out.println("Image load error " + path);
            return null;
        }
    }

    private JLabel loadImage(String path) {
        try {
            URL url = this.getClass().getResource(path);
            if (url == null)
                throw new RuntimeException("Can't found resource" + path);
            BufferedImage image = ImageIO.read(url);
            if (image == null)
                throw new RuntimeException("Can't load image from resource" + path);
            return new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            System.out.println("Image load error " + path);
            return null;
        }
    }

    @Override
    public void apply(Component component) {
        if (dialog.isActive())
            return;
        dialog.setVisible(true);
    }
}
