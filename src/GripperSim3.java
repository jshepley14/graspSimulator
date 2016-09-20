import javax.swing.*;
import java.awt.*; 
import java.awt.geom.*;
import java.awt.event.*;

	public class GripperSim3 extends JPanel implements ActionListener, KeyListener {
		Timer t = new Timer (6, this);
		double LENGTHPROXIMAL = 150;
		double LENGTHDISTAL = LENGTHPROXIMAL;
		double RADIUSD = 0.5;
		//proximal set-up
		double R = 150;
		double RPROXANGLE = -(Math.PI/4); 
		double LPROXANGLE = -3*(Math.PI/4); 
		double DELTATHETAPROX = 0.005;
		//Distal ste-up
		double RDISANGLE = -Math.PI/2; 
		double LDISANGLE = -Math.PI/2; 
		double DELTATHETADIS = 0.005;
		
		//right proximal
		double x =400, y =400, xf= x+R*Math.cos(RPROXANGLE), yf = y+R*Math.sin(RPROXANGLE), velPR = 0;
		//left proximal
		double xL = x-(R/2), yL = y, xLf = xL + R*Math.cos(LPROXANGLE) , yLf = y + R*Math.sin(LPROXANGLE), velPL = 0; 
		//right distal
		double xRD = xf, yRD = yf, xRDf = xf + R*Math.cos(RDISANGLE), yRDf = yf+R*Math.sin(RDISANGLE), velDR = 0; 
		//left distal
		double xLD = xLf, yLD = yLf, xLDf = xLf + R*Math.cos(LDISANGLE), yLDf = yLf + R*Math.sin(LDISANGLE), velDL = 0; 
		
		public GripperSim3() {
			t.start();
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			setPreferredSize( new Dimension(800, 650));
		}
		
		public void paintComponent ( Graphics g) {
			super.paintComponent(g);
			
			g.drawString("Press and release spacebar to freeze movement.", 75, 50);
			g.drawString("Hold UP arrow key for parallel grasp.  Hold Right arrow key for enveloping grasp.", 75, 65);
			g.drawString("Release UP key or Right key to release grip.", 75, 80);
			//right proximal
			Graphics2D rightProximal = (Graphics2D) g;
			rightProximal.draw(new Line2D.Double(x, y, xf, yf));
			
			//left proximal
			Graphics2D leftProximal = (Graphics2D) g;
			leftProximal.draw(new Line2D.Double(xL, yL, xLf, yLf));
			
			
			//right distal
			Graphics2D rightDistal = (Graphics2D) g;
			rightDistal.draw(new Line2D.Double(xf, yf, xRDf, yRDf));
			
			//left distal
			Graphics2D leftDistal = (Graphics2D) g;
			leftDistal.draw(new Line2D.Double(xLf, yLf, xLDf, yLDf));
			
			//palm
			Graphics2D palm = (Graphics2D) g;
			palm.draw(new Line2D.Double(x, y, xL, yL));
			
			
			/*
			//right distal
			Graphics2D rightDistal = (Graphics2D) g;
			rightDistal.draw(new Line2D.Double(xf, yf, xf, yf - LENGTHDISTAL));
			
			//left distal
			Graphics2D leftDistal = (Graphics2D) g;
			leftDistal.draw(new Line2D.Double(xLf, yLf, xLf, yLf - LENGTHDISTAL));
			
			*/
		}
		
		public void actionPerformed(ActionEvent e) {
			repaint();
			//proximal
			RPROXANGLE += velPR;
			LPROXANGLE += velPL;
			//right proximal
			xf= x+R*Math.cos(RPROXANGLE);
			yf = y+R*Math.sin(RPROXANGLE);	
			//left proximal
			xLf = xL + R*Math.cos(LPROXANGLE);
			yLf = y + R*Math.sin(LPROXANGLE);
			//distal
			RDISANGLE += velDR;
			LDISANGLE += velDL;
			//left distal
			xRDf = xf + R*Math.cos(RDISANGLE);
			yRDf = yf+R*Math.sin(RDISANGLE);
			//left distal
			xLDf = xLf + R*Math.cos(LDISANGLE);
			yLDf = yLf + R*Math.sin(LDISANGLE);
			
			
		}
		
		public double getRightProximalX () {
			return xf;
		}
		
		public double getLeftProximalX () {
			return xLf;
		}
		
		public double getRightProximalY () {
			return yf;
		}
		
		public double getLeftProximalY () {
			return yLf;
		}
		
		public double getRightDistalX () {
			return xRDf;
		}
		
		public double getLeftDistalX () {
			return xLDf;
		}
		
		public double getRightDistalY () {
			return yRDf;
		}
		
		public double getLeftDistalY () {
			return yLDf;
		}
		
		public double getRPROXANGLE() {
			return RPROXANGLE;
		}
		
		public double getLPROXANGLE() {
			return LPROXANGLE;
		}
		
		public double getRDISANGLE() {
			return RDISANGLE;
		}
		
		public double getLDISANGLE() {
			return LDISANGLE;
		}
		
		
		public void upProximal() {
			if ( getRightProximalX () < getLeftProximalX () + 20 ) {
				stopProximal();
			} else {
				velPR = -DELTATHETAPROX;
				velPL = DELTATHETAPROX;
			}
		}
		
		public void downProximal() {
			velPR = DELTATHETAPROX;
			velPL = -DELTATHETAPROX;
		}
		
		public void upDistal() {			
				velDR =  DELTATHETADIS;
				velDL = -DELTATHETADIS;	
				velPR = DELTATHETAPROX;
				velPL = -DELTATHETAPROX;
			
		}
		
		public void downDistal() {			
			velDR =  -DELTATHETADIS;
			velDL = DELTATHETADIS;
			velPR = -DELTATHETAPROX;
			velPL = DELTATHETAPROX;
		}
		
		public void stopProximal() {
			velPR = 0;
			velPL = 0;
			velDR =  0;
			velDL = 0;
		}
		
		public void stopDistal() {
			velPR = 0;
			velPL = 0;
			velDR =  0;
			velDL = 0;
		}
		
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_UP) {
				upProximal();
			}
			if (code == KeyEvent.VK_SPACE) {
				stopProximal();
			}
			if (code == KeyEvent.VK_RIGHT) {
				downDistal();
			}		
		}
		
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_UP) {
				downProximal();
			}	
			
			if (code == KeyEvent.VK_RIGHT  ) {
				upDistal();
			}	
		}
			
		public void keyTyped(KeyEvent e) {}
		
	}

