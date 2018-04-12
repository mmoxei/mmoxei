
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.scene.image.*;


class Tile extends Rectangle{
//instance variables 

private tileType type;
private Tank tank;

//getters and setters
tileType getType(){
return type;	
}

void setType(tileType t){
type = t;	
}
//method that checks if tank is on a tile 

Tank getTank(){return  new Tank(tank);}

void setTank(Tank t){
	if(t != null) {
		tank = new Tank(t);
		setType(t.getType());
	}
else {
		setType(null);
		tank = null;
	}
}
//constructor for tile class
Tile(int x, int y){
	Image i = new Image("snow3.png");

	setWidth(Game.gettileSize());
	setHeight(Game.gettileSize());
	relocate(x * Game.gettileSize(), y * Game.gettileSize());
//colour is green by default
	setFill(new ImagePattern(i));
//event handler for mouse pressed if tile without tank is pressed tank will move to tile, if tile with tank is pressed then the tank will shoot the tank on the tile  
	
}	

}