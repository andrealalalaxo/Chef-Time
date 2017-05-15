import java.awt.Image;

public class Chocolate extends Ingredient{
	public Chocolate() {
		Image img = loadImage("chocolate.png");
		super(img, 50, 50, 50, 50);
		
	}

}
