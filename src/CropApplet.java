/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

/**
 *
 * @author merter
 */
public class CropApplet extends JApplet implements MouseListener, MouseMotionListener {

    int pointerX = 0, pointerY = 0;
    int pointerW = 20, pointerH = 20;
    String path = "";
    BufferedImage image;
    int clickCount = 0;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        setSize(800, 600);
        addMouseListener(this);
        addMouseMotionListener(this);

        setImage("/Users/merter/Desktop/Tensorflow Object Detection/Hucreler/1.jpg");
        setCounter(0);


    }

    public void setPointerSize(int size) {
        this.pointerW = pointerH = size;

    }

    public void setCounter(int c) {
        this.clickCount = c;
    }

    public void setImage(String f) {
        try {
            File file = new File(f);
            this.image = ImageIO.read(file);
            this.path = file.getParent();
            repaint();
        } catch (Exception e) {
        }
    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void paint(Graphics g) {
        //super.paint(g); //To change body of generated methods, choose Tools | Templates.

        try {

            g.drawImage(image, 0, 0, this);
            g.setColor(Color.red);
            g.drawRect(pointerX, pointerY, pointerW, pointerH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        BufferedImage cropped = image.getSubimage(pointerX, pointerY, pointerW, pointerH);
        try {

            File outputfile = new File(path + "/cropped_" + (clickCount++) + ".jpg");
            ImageIO.write(cropped, "jpg", outputfile);

        } catch (Exception ex) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pointerX = e.getX() - (int) (pointerW / 2);
        pointerY = e.getY() - (int) (pointerH / 2);
        repaint();
    }


}
