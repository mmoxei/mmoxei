
import javafx.scene.image.*;

class Tank extends Unit{

	private tileType type = tileType.TANK;
	private String tankName;
	private String tankImage = "tank_red.png";
	private ImageView i = new ImageView(new Image("tank_red.png"));

	void setType(tileType t){type = t;}
	tileType getType(){return type;}
	private void setTankImage(String s){tankImage = s;}
	private String getTankName(){return tankName;}
	void setI(String s){
		tankImage = s;
		getChildren().remove(i);
		i = new ImageView(new Image(tankImage));
		getChildren().add(i);
	}
	private void setTankName(String s){tankName = s;}
	private String getTankImage(){return tankImage;}
	
	Tank(String name, int x, int y){
		tankName = name;
		moveUnit(x,y);
		setPos(x,y);
		setMouseTransparent(true);
		getChildren().addAll(i);
	}
	
	Tank(Tank t){
		setTankImage(t.getTankImage());
		setI(t.getTankImage());
		setTankName(t.getTankName());
		setPos(t.getPos()[0],t.getPos()[1]);
		setHealth(t.getHealth());
		setDistance(t.getShotDistance());
		setMoveDistance(t.getMoveDistance());
		setShotDamage(t.getShotDamage());
		setType(t.getType());
	}
}