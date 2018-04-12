
// SHOULDNT NEED THIS ONE AS I MOVED THE READER METHOD INTO THE MAIN CLASS UNDER THE EVENT HANDLING FOR THE HIGH SCORE BUTTON
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	public static void main(String[] args, int rank) {
		try {
			FileReader fr = new FileReader("highscores");
			BufferedReader br = new BufferedReader(fr);
			
		/*	String str;
			while ((str = br.readLine()) != null) {
				System.out.print(str + "\n"); */
				
			for(int i = 0; i < (rank - 1); ++i)
				  br.readLine();			
			score = br.readLine();
			br.close();
		
			} catch (IOException e) {
				System.out.print("File not found.");
			}
	
			
		}
	
	static String score;
	public String score() {
		return score;
		
	}
}
