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
public class Bear {
	public int x, y; //position of the bear
	public int w = 45, h = 45;
	private Image img; 	
	private int vx = 0; //velocity in x
	private AffineTransform tx;
	private Rectangle hitbox; //bear hitbox

	//add sound effect
	Music musicSpawn = new Music("beardead.wav", false);

	
	public Bear(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/imgs/bear_sprite.png"); //load the image for bear

		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actual picture location
		update();
		g2.drawImage(img, tx, null);

		

	}
	
	/* update the picture variable location */
	private void update() {
		//update y location based on velocity in y
		x += vx; //velocity in x affects x location
		//movement to the left
		vx = -3;
		
		tx.setToTranslation(x, y);
		tx.scale(0.1	, 0.1);
	}
	
	public boolean lost() {
		if(x<-10) {
			System.out.println("Lost");
			return true;
			//broadcast losing
		}
		return false;
	}
	
	public boolean ifCollided(int x, int y) { //collision method
		Rectangle bHitbox = new Rectangle(x, y, 10, 10);
		hitbox = new Rectangle(this.x,this.y,w,h);
		if(hitbox.intersects(bHitbox)) {
			System.out.println("hit");
			// add sound for hitting bear
			musicSpawn.play(); //ask music to play
			this.x = (int) ((Math.random() * (1000-650))+650); //reset position (back to the right of the screen)
			return true;
		}
		return false;
	}
	
	//bear scale
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
