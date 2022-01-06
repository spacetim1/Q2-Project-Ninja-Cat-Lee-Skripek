import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.net.URL;
public class Bullet {
	public int x, y; //position of the bullet
	public int w=10, h=10;
	private Image img; 	
	private AffineTransform tx;
	public Rectangle hitbox; //bullet hitbox
	public boolean mouseClicked;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		mouseClicked = false;
		img = getImage("/imgs/bullet_sprite.png"); //load the image for the bullet
		hitbox = new Rectangle(x, y, w, h);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g, int mx, int my) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actual picture location
		update(mx, my);
		g2.drawImage(img, tx, null);

		

	}
	
	public void moveBullet(int mx, int my) {
		int smaller, deltax, deltay, incx, incy, minspeed = 7;
		

		//calculate x (horizontal) distance from the original bullet location to the mouse click
		deltax =  mx - x;
		//calculate y (vertical) distance from the original bullet location to the mouse click
		deltay =  my - y;
		//check which distance is smaller
		//y can be negative while x is always positive
		if (deltax < Math.abs(deltay)) {
			smaller = deltax;
		}
		else {
			smaller = Math.abs(deltay);  // smaller has to be a positive value
		}
		//determine increment for x, y position for the bullet by dividing deltax and deltay by whichever is smaller
		incx = deltax/smaller;  
		incy = deltay/smaller;
		
		if (incx < minspeed) {  //if x increment is too small (too slow), increase speed
			
			if(incy < minspeed && incy > -minspeed) { //if increment y is too small (absolute value of incy is less than minspeed)
				incx *= minspeed;
				incy *= minspeed;
			} 

		}
		
		//the x & y positions of the bullet are moving incrementally based on the calculations above
		x += incx;
		y += incy;
		
		/*
		if (deltay > 0 && (x<mx) && (y<my)) { // mouse click target (bear) is below the original bullet location (final y > initial y)
			x += incx;
			y += incy;
		}
		else if((x<mx) && (y>my)) { // final y < initial y (if bear is above the bullet)
			x += incx;
			y += incy;
		}
		*/
		
		//when bullet reaches the mouse pointer's position, adjust the bullet's position as it might go over a bit and then reset
		if(x >= mx) {
			x=mx;
			y=my;
			mouseClicked = false;
		}

	}
	
	/* update the picture variable location */
	private void update(int mx, int my) {
		//if mouse button is clicked, call the move bullet method to move it towards the mouse position
		if(mouseClicked) {
			moveBullet(mx, my);
		}
		
		tx.setToTranslation(x, y);
		tx.scale(0.02, 0.02);
	}
	
	//scale bullet
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Bear.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
