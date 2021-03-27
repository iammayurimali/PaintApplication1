/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Amruta
 */
public class Canvas extends JComponent {
    private int X1, Y1, X2, Y2;
    private Image img;
    private Rectangle shape;
    private Graphics2D graphics;
    private MouseMotionListener motion;
    private MouseListener listener;
    	public Canvas(int height, int width) {
                setBackground(Color.WHITE);
		defaultListener();
	}
        
        
	public void defaultListener() {
            setDoubleBuffered(false);
            listener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
		X2 = e.getX();
		Y2 = e.getY();
                }
            };

	    motion = new MouseMotionAdapter() {
	    public void mouseDragged(MouseEvent e) {
		X1 = e.getX();
		Y1 = e.getY();

		if (graphics != null) {
                    graphics.drawLine(X2, Y2, X1, Y1);
                    repaint();
                    X2 = X1;
                    Y2 = Y1;
		}
            }
            };
		addMouseListener(listener);
		addMouseMotionListener(motion);
	}
        
        public void pencil() {
            removeMouseListener(listener);
            removeMouseMotionListener(motion);
            defaultListener();
		
	}
        
       	protected void paintComponent(Graphics g1) {
		if (img == null) {
			img = createImage(getSize().width-30, getSize().height-30);
			graphics = (Graphics2D) img.getGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
                        graphics.setPaint(getBackground());
			graphics.fillRect(10, 10, getSize().width-30, getSize().height-30);
			graphics.setPaint(Color.black);
			
		}
		g1.drawImage(img, 10, 10, null);
		
		if (shape != null) {
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.draw(shape);
		}

        }
      
}