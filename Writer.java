

import java.io.FileWriter;
import java.io .IOException;
import java.io.PrintWriter;

class Writer {

	static void main(String[] args, String name, int a) {
		try {
			FileWriter fw = new FileWriter("highscores");
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(name + "     " + a);
			
			pw.close();
		}	catch (IOException e) {
			System.out.println("ERROR!");
		}
		
		
		
	}
}
