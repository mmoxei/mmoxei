
import java.util.*;
import javafx.scene.layout.*;


class Unit extends StackPane{
	private  int[] pos = new int[2];

	private  int shotDamage = 4;
	
	private  int moveDistance = 5;
	
	private  int distance = 4;
	
	private  int Health = 10;
	
	int[] getPos(){return Arrays.copyOf(pos, pos.length);}
	int getMoveDistance(){
	return moveDistance;	
	}
	int getShotDamage(){
	return shotDamage;	
	}
	int getShotDistance(){
	return distance;
	}
	int getHealth(){return Health;}
	void moveUnit(int x, int y){relocate(x * Game.gettileSize(), y * Game.gettileSize());}
	void setPos(int x, int y){
	pos[0] = x;	
	pos[1] = y;
	}
	void setHealth(int a){Health = a;}
	void setDistance(int b){
	if(b < 0){
	return;	
	}
	distance = b;	
	}
	void setMoveDistance(int b){
	if(b < 0){
	return;	
	}
	moveDistance = b;	
	}
	void setShotDamage(int s){
		shotDamage = s;
	}
}