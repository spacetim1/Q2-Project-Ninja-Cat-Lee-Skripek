import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.MouseInfo;
//import java.awt.Point;

//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.lang.Math;


public class Driver extends JPanel implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	int score = 0, winscore = 20;
    //losing game variable
	public boolean lostGame = false;
	//random position's max and min variables
	int ymax = 500;
	int ymin = 100;
	int xmax = 1000;
	int xmin = 650;
	//mouse position
	private int mx;
	private int my;

	//int xr = (int) ((Math.random() * (xmax-xmin))+xmin);
	//int yr = (int) ((Math.random() * (ymax-ymin))+ymin);
	//objects instantiated
	Cat cat = new Cat(20, 200);
	// initial bullet position
	int initx = 80, inity = 240;
	Bullet bullet = new Bullet(initx, inity);
	//background
	Background 	b = new Background(0, 0);
	
	//sound effect
	Music musicSpawn2 = new Music("winning.wav", false);
	Music musicSpawn1 = new Music("losing.wav", false);
	
	//bear array objects
	//Bear bear = new Bear(xr, yr);
	Bear[] bearArray = new Bear[7];
	
	
	//mouse pointer x & y coords
	/*Point p = MouseInfo.getPointerInfo().getLocation();
	int mx = p.x;
	int my = p.y; */
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		
		
		//ask the objects to paint themselves
		//background
		b.paint(g);
				
		//multiple bears
		if(!lostGame && score < winscore) {
			for(int i = 0; i<7; i++) {
				bearArray[i].paint(g);
				if(bearArray[i].ifCollided(bullet.x, bullet.y)) { //collision
					bullet.x = initx;
					bullet.y = inity;
					bullet.mouseClicked =false;
					score++;
					System.out.println("Score: "+score);
				}
				//broadcast losing
				if(bearArray[i].lost()) {
					lostGame = true;
				}
				// print score
				g.setColor(Color.RED);
				g.setFont(new Font("Stencil", Font.PLAIN, 50));
				g.drawString("SCORE: " + score, 100, 100);

			}
			//paint bullet
			bullet.paint(g, mx, my);

		//cat		
		cat.paint(g);
		}
		else if (lostGame) {	
			//you lost font
			g.setColor(Color.RED);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("You Lost!", 300, 300);
			musicSpawn1.play();
			//System.out.println("You lost!");

		}
		if (score == winscore) {
			//you won font
			//System.out.println("You Won! Score = "+score);
			g.setColor(Color.RED);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("You Won!", 300, 300);
			musicSpawn2.play();
		}
	
		
		
	}
	
	public static void main(String[] arg) {
		Driver f = new Driver();	
	}
	
	
	public Driver() {
		//bear array object's random positions
		bearArray[0] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[1] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[2] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[3] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[4] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[5] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));
		bearArray[6] = new Bear((int) ((Math.random() * (xmax-xmin))+xmin), (int) ((Math.random() * (ymax-ymin))+ymin));

		//frame name and dimensions
		JFrame f = new JFrame("Math Ninja");
		f.setSize(new Dimension(800, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//get mouse position
		mx = arg0.getX()-10;
		my = arg0.getY()-25;
		//bullet position original
		bullet.x = initx;
		bullet.y = inity;
		bullet.mouseClicked = true;
		//bullet.moveBullet(mx, my);
		//bullet.x = mx;
		//bullet.y = my;
		
		/*
		int smaller, deltax, deltay, incx, incy;
		

		// calculate x (horizontal) distance from the original bullet location to the mouse click
		deltax =  mx - bullet.x;
		// calculate y (vertical) distance from the original bullet location to the mouse click
		deltay =  my - bullet.y;
		// check which distance is smaller
		// y can be negative while x is always positive
		if (deltax < Math.abs(deltay)) {
			smaller = deltax;
		}
		else {
			smaller = Math.abs(deltay);  // smaller has to be a positive value
		}
		// determine increment for x, y position for the bullet by dividing deltax and deltay by whichever is smaller
		incx = deltax/smaller;  
		incy = deltay/smaller;
		
		System.out.println(incx+" "+incy);
		
		
		if (deltay > 0) { // mouse click target (bear) is below the original bullet location (final y > initial y)
			while ((bullet.x<mx) && (bullet.y<my)) {
				bullet.x += incx;
				bullet.y += incy;
				bullet.paint(g);
			}
		}
		else {
			while ((bullet.x<mx) && (bullet.y>my)) {  // final y < initial y (if bear is above the bullet
				bullet.x += incx;
				bullet.y += incy;
				bullet.paint(g);
			}
		}
		*/
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 32) {
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
