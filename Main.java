
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.*;

class Main extends Game {
	
	private String playerName;
	private GridPane layout;
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage win) throws Exception {
		layout = new GridPane();
		scene = new Scene(layout, 300, 150);
		layout.setStyle("-fx-background-image: url('test.jpg')");
		
		
		Button start1 = new Button("Start PvP");
		Button start2 = new Button("Start PvE");
		Button quit = new Button("Exit");
		Button hs = new Button("High Scores");
		
		Label label1 = new Label("Name:");
		TextField textField = new TextField ();

		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		
		Label sc1 = new Label();
		Label sc2 = new Label();
		Label sc3 = new Label();
		
		start1.setOnAction(e -> {
			setMultiPlayer(true);
			Pane p = createContent();
			Scene scene = new Scene(p);
			win.setTitle("tanks");
			win.setScene(scene);
			win.show();


			useTile(p, win);
		});
		
		start2.setOnAction(e -> {  //Launch PvE, saves the players name as Playername
			playerName = textField.getText();


				Pane p = createContent();
				Scene scene = new Scene(p);
				win.setTitle("tanks");
				win.setScene(scene);
				win.show();

				useTileAI(p, win, playerName);




				System.out.println(playerName);
		});
		
		hs.setOnAction(e -> { // This displays the high scores, will need to take in from a file 
			try {
				FileReader fr = new FileReader("highscores");
				BufferedReader br = new BufferedReader(fr);

					
					sc1.setText(br.readLine()); //sets the highscores to whatever in the text file, currently not in order.
					sc2.setText(br.readLine());
					sc3.setText(br.readLine());
					
				br.close();
				
			    } catch (IOException e1) {
					System.out.print("File not found.");
				}
			
			
		}); 
		
		quit.setOnAction(e -> { //The button that exits the game
			win.close();
		});
		


		layout.add(hb, 1, 0);
		layout.add(start2, 1, 1);
		layout.add(start1, 1, 2);
		layout.add(hs, 1, 3);
		layout.add(quit, 1, 4);
		layout.add(sc1, 2, 1);
		layout.add(sc2, 2, 2);
		layout.add(sc3, 2, 3);
		win.setTitle("Tanks vs Wizards!");
		win.setScene(scene);
		win.show();
		
	}
	


}
