

import java.lang.Math;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.image.*;

abstract class Game extends Application{
// instances variables 
	private Text t = new Text("Turn: Red Tank");
	private Text tank1Health = new Text();
	private Text tank2Health = new Text();
	private Text Display = new Text("Display:");

	private Group tileGroup = new Group();
	private Group tankGroup = new Group();

	private Boolean multiPlayer = false;
	private Boolean haveShot = false;
	private Boolean gameOver = false;

// size of titles
    private final static int tileSize = 32;
	private final int gridSize = 20;
// list of the tanks objects on the map 
	private Tank[] tankList = new Tank[3];
//2d array of objects that going to be displayed  
	private Tile[][] Board = new Tile[gridSize][gridSize];


	private int c = 0;
	private int TurnCounter = 0;


//getters and setters

    int getScore(){return 1000/c + 100*tankList[0].getHealth();}
    public void setMultiPlayer(boolean b){multiPlayer = b;}
	static int gettileSize(){return tileSize;}

// sets the tank object to a certain tiles attribute
	private void setTanktoTile(int x, int y, Tank tank){Board[x][y].setTank(tank);}
	private Tile[][] getBoard(){return Board;}
	private void setBoardType(int x, int y, tileType t){Board[x][y].setType(t);}

	// converts mouse pixel to a coordinate size
	private int Convert(double pixel){return (int)(pixel + tileSize/4)/tileSize;}

// checks if user move is legal, if move is legal returns true, and move is not legal returns false	
	private	boolean tryTurn(Tank tank, turnType Type, int X, int Y){
		if(Type == turnType.MOVE){
			int dy = Y - tank.getPos()[1];
			int dx = X - tank.getPos()[0];
			
			
			System.out.println(dx);
			System.out.println(dy);
			if(Math.abs(dx) > tank.getMoveDistance() || Math.abs(dy) > tank.getMoveDistance()){
                if(tank.getType() == tileType.AI) {
                    Display.setText("Display:" + "move " + "There's something in the way!");
                }
			    return false;
			}
			if(Math.abs(dx) != 0 && Math.abs(dy) != 0){
                if(tank.getType() == tileType.AI) {
                    Display.setText("Display:" + "move " + "There's something in the way!");
                }
			    return false;
			}
			if(X - tank.getPos()[0] == 0){
				for(int count = 0; count < Math.abs(dy); count++){
					if(dy < 0){
						
						if(Board[X][Y + count].getType() != null){
                            if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "move " + "There's something in the way!");
                            }
						    return false;
						}
					}
					if(dy > 0){
						
						if(Board[X][Y  - count].getType() != null){
                            if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "move " + "There's something in the way!");
                            }
						    return false;
						}
					}
				}
			}
			if(Y - tank.getPos()[1] == 0){
				for(int count = 0; count < Math.abs(dx); count++){
					if(dx < 0){
						
						if(Board[X + count][Y].getType() != null){
                            if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "move " + "There's something in the way!");
                            }
							return false;
						}
					}
				
					if(dx > 0){
						
						if(Board[X - count][Y].getType() != null){
                            if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "move " + "There's something in the way!");
                            }
							return false;
							
						}
					}
				}
			}
		}
		if(Type == turnType.SHOOT){

			
			
			int dy = Y - tank.getPos()[1];
			int dx = X - tank.getPos()[0];
			System.out.println(dx + "," + dy);
			if(dy != 0 && dx == 0){
				for(int c = 0; c < Math.abs(dy); c++){
					if(dy > 0){
						if(Board[X][Y - c].getType() == tileType.TERRAIN){

						    if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "shoot " + "There's something in the way!");

                            }
						    return false;}
					}
					if(dy < 0){
						if(Board[X][Y + c].getType() == tileType.TERRAIN){

						    if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "shoot " + "There's something in the way!");
                            }
						    return false;}
					}
				}
				
				
			}
			if(dx != 0 && dy == 0){
				for(int c = 0; c < Math.abs(dx); c++){
					if(dx > 0){
						if(Board[X - c][Y].getType() == tileType.TERRAIN){

						    if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "shoot " + "There's something in the way!");
                            }
						    return false;}
					}
					if(dx < 0){
						if(Board[X + c][Y].getType() == tileType.TERRAIN){

						    if(tank.getType() == tileType.AI) {
                                Display.setText("Display:" + "shoot " + "There's something in the way!");
                            }
						    return false;}
					}
				}	
			}
			if(dy > 0 && dx > 0){
				for(int c = 0; c < dx; c++){
					
					if(Board[X - c][Y - c].getType() == tileType.TERRAIN){

					    if(c > Math.abs(dy)){break;}
					    if(tank.getType() == tileType.AI) {
                            Display.setText("Display:" + "shoot " + "There's something in the way!");
                        }
					    return false;}
					
				}
			}
			if(dy < 0 && dx < 0){
				for(int c = 0; c < Math.abs(dx); c++){

				    if(Board[X + c][Y + c].getType() == tileType.TERRAIN){

				        if(c > Math.abs(dy)){break;}
					    if(tank.getType() == tileType.AI) {
                            Display.setText("Display:" + "shoot " + "There's something in the way!");
                        }
					    return false;}
				}
			}
			if(dy < 0 && dx >0){

			    for(int c = 0; c < dx; c++){

			        if(Board[X - c][Y + c].getType() == tileType.TERRAIN){

			            if(c > Math.abs(dy)){break;}
					    if(tank.getType() == tileType.AI) {
                            Display.setText("Display:" + "shoot " + "There's something in the way!");
                        }
					    return false;}
				}
			}
			if(dy > 0 && dx < 0){
				for(int c = 0; c < Math.abs(dx);c++){
					if(Board[X + c][Y - c].getType() == tileType.TERRAIN){

                        if(c > Math.abs(dy)){break;}
					    if(tank.getType() == tileType.AI) {
                            Display.setText("Display:" + "shoot " + "There's something in the way!");
                        }
					    return false;}
				}
			}
			if(Math.abs(dy) + Math.abs(dx) > tank.getShotDistance()){
                Display.setText("Display:" + "shoot " + "That's too far away!");
			    return false;
			}
		
		
		} 
		return (Type != null);
		}

    private boolean AIShoot(Tank t){
        for(int count = 0 ; count < t.getShotDistance(); count++){

            for(int counter = 0; counter < t.getShotDistance() - count; counter++){
               if(counter == 0 && count == 0){continue;}
                System.out.println(count + "," + counter);
                if(t.getPos()[0] + count > gridSize || t.getPos()[1] + counter > gridSize){continue;}
                else {
                    if (Board[t.getPos()[0] + count][t.getPos()[1] + counter].getType() == tileType.TANK) {
                        if (tryTurn(t, turnType.SHOOT, t.getPos()[0] + count, t.getPos()[1] + counter)) {
                            Turn(t, turnType.SHOOT, t.getPos()[0] + count, t.getPos()[1] + counter);
                            return true;
                        }
                    }
                }
                if(t.getPos()[0] + count > gridSize || t.getPos()[1] - counter < 0){continue;}
                else {
                    if (Board[t.getPos()[0] + count][t.getPos()[1] - counter].getType() == tileType.TANK) {
                        if (tryTurn(t, turnType.SHOOT, t.getPos()[0] + count, t.getPos()[1] - counter)) {
                            Turn(t, turnType.SHOOT, t.getPos()[0] + count, t.getPos()[1] - counter);
                            return true;
                        }
                    }
                }
                if(t.getPos()[0] - count < 0 || t.getPos()[1] - counter < 0){continue;}
                else {
                    if (Board[t.getPos()[0] - count][t.getPos()[1] - counter].getType() == tileType.TANK) {
                        if (tryTurn(t, turnType.SHOOT, t.getPos()[0] - count, t.getPos()[1] - counter)) {
                            Turn(t, turnType.SHOOT, t.getPos()[0] - count, t.getPos()[1] - counter);
                            return true;
                        }
                    }
                }
                if(t.getPos()[0] - count < 0 || t.getPos()[1] + counter > gridSize){break;}
                else {
                    if (Board[t.getPos()[0] - count][t.getPos()[1] + counter].getType() == tileType.TANK) {
                        if (tryTurn(t, turnType.SHOOT, t.getPos()[0] - count, t.getPos()[1] + counter)) {
                            Turn(t, turnType.SHOOT, t.getPos()[0] - count, t.getPos()[1] + counter);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
	}

    private boolean AIMove(Tank t, Tank Player){
        Boolean closeC;
        closeC = (Math.abs(t.getPos()[0] - Player.getPos()[0]) > Math.abs(t.getPos()[1] - Player.getPos()[1]));
        if(closeC){
            int distance = t.getPos()[0] - Player.getPos()[0];
            for(int count = Math.abs(distance); count > 0 ; count--){
                if(distance > 0){
                    if(tryTurn(t, turnType.MOVE, t.getPos()[0] - count, t.getPos()[1])){
                        Turn(t, turnType.MOVE, t.getPos()[0] - count, t.getPos()[1]);
                        /*AIShoot(t);*/
                        return true;
                    }
                }
                if(distance < 0){
                    if(tryTurn(t, turnType.MOVE, t.getPos()[0] + count, t.getPos()[1])){
                        Turn(t, turnType.MOVE, t.getPos()[0] + count, t.getPos()[1]);
                        /*AIShoot(t);*/
                        return true;
                    }
                }
            }
        }
        if(!closeC){
            int distance = t.getPos()[1] - Player.getPos()[1];
            for(int count = Math.abs(distance); count > 0 ; count--){
                if(distance > 0){
                    if(tryTurn(t, turnType.MOVE, t.getPos()[0], t.getPos()[1] - count)){
                        Turn(t, turnType.MOVE, t.getPos()[0], t.getPos()[1] - count);
                        /*AIShoot(t);*/
                        return true;

                    }
                }
                if(distance < 0){
                    if(tryTurn(t, turnType.MOVE, t.getPos()[0], t.getPos()[1] + count)){
                        Turn(t, turnType.MOVE, t.getPos()[0], t.getPos()[1] + count);
                        /*AIShoot(t);*/
                        return true;
                    }
                }
            }
        }
        return false;
	}
	private void AI(Tank t, Tank Player){
        int count = 0;
        while(count < 2) {
            if(AIShoot(t)){return;}
            if(AIMove(t, Player)){count++;}
            else{count++;}
        }

    }

	private void makeTerrain(Tile t){
		if(t.getType() != null){return;}
		Image image = new Image("snowman.png");
		t.setFill(new ImagePattern(image));
		t.setType(tileType.TERRAIN);
	}
	
	// method that moves tank 	
	private void Turn(Tank tank, turnType Type, int X, int Y){
// if the turn type is move it will set tile that tank was on to null, and the tile that is pressed to tank		

		if(!tryTurn(tank, Type, X, Y)){return;}
		if(Type == turnType.MOVE){
            if(tank.getType() != tileType.AI){
                Display.setText("Display:" + " " + "You successfully moved!");
            }


			setBoardType(X,Y,tank.getType());
			setTanktoTile(X,Y,Board[tank.getPos()[0]][tank.getPos()[1]].getTank());

			setBoardType(tank.getPos()[0],tank.getPos()[1],null);
			setTanktoTile(tank.getPos()[0],tank.getPos()[1],null);
			
			tank.setPos(X,Y);
			tank.moveUnit(X,Y);
		}
		printGrid();
//tank shoots 		
		if(Type == turnType.SHOOT){

			if(getBoard()[X][Y].getType() == tileType.TANK || getBoard()[X][Y].getType() == tileType.AI) {

                Tank target = new Tank(Board[X][Y].getTank());
                System.out.println("Healthy of Target before is" + " " + target.getHealth());
                target.setHealth(target.getHealth() - tank.getShotDamage());
                System.out.println("Health of Target after is" + " " + target.getHealth());
                if (tank.getType() != tileType.AI) {
                    Display.setText("Display:" + " " + "You hit a tank!");
                } else {
                    Display.setText("Display:" + " " + "You were hit by the computer!");
                }
                setTanktoTile(X, Y, target);

                if (target.getHealth() <= 0) {
                    gameOver = true;
                    /*System.exit(0);*/
                }


			}
		}
	}

	private void printGrid(){
		for(int y = 0; y < Board[0].length;y++){
			for(int x=0; x < gridSize;x++){
				
				if(Board[x][y].getType()==null)
					System.out.print("-");
				else if(Board[x][y].getType().equals(tileType.TANK)){
					System.out.print("T");
				}
				else if(Board[x][y].getType().equals(tileType.TERRAIN)){System.out.print("S");}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
// puts tanks, tiles, margins, into groups so it can be displayed and returns root   		
	Pane createContent(){
		Pane root = new Pane();

		root.setPrefSize(gridSize * tileSize, gridSize * tileSize);
		root.getChildren().addAll(tileGroup, tankGroup,t,Display);
		t.setX(gridSize * tileSize + 40);
		t.setY(70);
		t.setFont(Font.font("Verdana",FontWeight.BOLD,(1600/tileSize)));
		Display.setX(gridSize * tileSize + 40);
		Display.setY(270);
        Display.setFont(Font.font("Verdana",FontWeight.BOLD,(1600/tileSize)));
		for(int count = 0; count < gridSize; count++){

			
			for(int counter = 0; counter < gridSize; counter++){
				
				double d = Math.random();
					
				Tile tile = new Tile(counter, count);
				if(d > 0.96){
					makeTerrain(tile);
				}
				Board[counter][count] = tile;
				
				tileGroup.getChildren().add(tile);
			}
		}
		Tank tank1 = new Tank("1",8,7);
		Tank tank2 = new Tank("2",4,3);
		Tank tank3 = new Tank("3", 12, 5);

		tank2.setI("tank_yellow.png");
	    tank3.setI("tank_red.png");
		tankGroup.getChildren().add(tank1);
		tankGroup.getChildren().add(tank2);


		tankList[0] = tank1;
		tankList[1] = tank2;


		tank1Health = new Text("Red Tank Health:" + " " + Integer.toString(tank1.getHealth()));
		tank2Health = new Text("Yellow Tank Health:" + " " + Integer.toString(tank2.getHealth()));
		tank1Health.setY(120);
		tank1Health.setX(gridSize * tileSize + 40);
		tank1Health.setFont(Font.font("Verdana",FontWeight.BOLD,1600/tileSize));
		tank2Health.setY(170);
		tank2Health.setX(gridSize * tileSize + 40);
		tank2Health.setFont(Font.font("Verdana",FontWeight.BOLD,1600/tileSize));
		tankGroup.getChildren().addAll(tank1Health,tank2Health);
		
		setBoardType(tank1.getPos()[0],tank1.getPos()[1],tileType.TANK);
		setTanktoTile(tank1.getPos()[0],tank1.getPos()[1],tank1);

		
		setBoardType(tank2.getPos()[0],tank2.getPos()[1],tileType.TANK);
		setTanktoTile(tank2.getPos()[0], tank2.getPos()[1], tank2);




		
		
	
		
		return root;
	}
	void useTile(Pane p, Stage primaryStage){
	p.setOnMouseClicked(e ->{
		int mxi = Convert(e.getSceneX());
		int myi = Convert(e.getSceneY());

		if(mxi >= gridSize || myi >= gridSize || mxi < 0 || myi < 0){return;}
		
		Display.setText("Display:");
		if(c % 2 == 0){
		t.setText("Turn: Yellow Tank");
		Display.setText("Display:");
		if(getBoard()[mxi][myi].getType() == tileType.TANK){
			Turn(tankList[0], turnType.SHOOT, mxi, myi);
		
			
		}
		if(getBoard()[mxi][myi].getType() == null){
			
			Turn(tankList[0], turnType.MOVE, mxi, myi);
			 
			
		}

		tank1Health.setText("Red Tank Health:" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
		tank2Health.setText("Yellow Tank Health:" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));
		}
		
		if(c % 2 != 0){
            Display.setText("Display:");
		    t.setText("Turn: Red Tank");
        if(getBoard()[mxi][myi].getType() == tileType.TANK){
        Turn(tankList[1], turnType.SHOOT, mxi, myi);
			
			
		}
		if(getBoard()[mxi][myi].getType() == null){
            Turn(tankList[1], turnType.MOVE, mxi, myi);
        }
		tank1Health.setText("Red Tank Health:" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
		tank2Health.setText("Yellow Tank Health:" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));
		}
		c++;
        if(gameOver) {
            primaryStage.close();
        }

	});
	
	}


    void useTileAI(Pane p , Stage primaryStage, String s){
        tankList[1].setType(tileType.AI);

        Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].setType(tileType.AI);

        t.setText("Turn:" + " " + "Player turn" + " " + (TurnCounter + 1));
        tank1Health.setText("Player Health (Red Tank):" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
        tank2Health.setText("AI Health (Yellow Tank):" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));

        p.setOnMouseClicked(e ->{
            Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].setType(tileType.AI);
            int mxi = Convert(e.getSceneX());
            int myi = Convert(e.getSceneY());


            if(mxi >= gridSize || myi >= gridSize || mxi < 0 || myi < 0){return;}

            Display.setText("Display:");





            t.setText("Turn:" + " " + "Player turn" + " " + (TurnCounter + 1));






                if(getBoard()[mxi][myi].getType() == tileType.AI){
                    System.out.println("h");
                    if(haveShot){
                        Display.setText("Display: You can only shoot once per turn.");
                        return;
                    }
                    if(!haveShot) {
                        Turn(tankList[0], turnType.SHOOT, mxi, myi);
                        haveShot = true;
                    }


                }
                if(getBoard()[mxi][myi].getType() == null){
                    Turn(tankList[0], turnType.MOVE, mxi, myi);

                }

                tank1Health.setText("Player Health (Red Tank):" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
                tank2Health.setText("AI Health (Yellow Tank):" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));


                TurnCounter++;
                if(TurnCounter > 1) {
                AI(tankList[1], tankList[0]);
                TurnCounter = 0;
                haveShot = false;
                t.setText("Turn:" + " " + "Player turn" + " " + (TurnCounter + 1));
            }

            tank1Health.setText("Player Health (Red Tank):" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
            tank2Health.setText("AI Health (Yellow Tank):" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));


            c++;
            if(gameOver) {
                System.out.println(getScore());
                Writer.main(null,s,getScore());
                primaryStage.close();
            }
        });

    }





	/*public void start(Stage primaryStage){
		if(multiPlayer) {
            Pane p = createContent();
            Scene scene = new Scene(p);
            primaryStage.setTitle("tanks");
            primaryStage.setScene(scene);
            primaryStage.show();


            useTile(p, primaryStage);
        }
        if(!multiPlayer){
            Pane p = createContent();
            Scene scene = new Scene(p);
            primaryStage.setTitle("tanks");
            primaryStage.setScene(scene);
            primaryStage.show();





            useTileAI(p, primaryStage);


		}
	}	*/
	public static void main(String[] args){
		launch(args);	
		
	}	
}