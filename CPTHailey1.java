import arc.*;
import java.awt.*;
import java.awt.Font;

public class CPTHailey1{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice Game", 1280, 720);
			
			/*con.setDrawColor(Color.WHITE);
			con.fillRect(0, 0, 1280, 720);
			
			con.setDrawColor(new Color(137, 207, 240));
			con.fillRoundRect(150, 200, 350, 100,  50, 50);
			con.fillRoundRect(150, 325, 350, 100,  50, 50);
			con.fillRoundRect(150, 450, 350, 100,  50, 50);
			con.fillRoundRect(150, 575, 350, 100,  50, 50);
			
			//font to be added because it is being weird
			//and not downloading and importing properly!!
			Font fntTitle = con.loadFont("SoulDaisy.otf", 100);
			Font fntOptions = con.loadFont("SoulDaisy.otf", 60);
			
			con.setDrawFont(fntTitle);
			con.setDrawColor(new Color(137, 207, 240));
			con.drawString("MULTIPLE CHOICE GAME", 125, 0);
			
			con.setDrawFont(fntOptions);
			con.setDrawColor(Color.WHITE);
			con.drawString("PLAY", 265, 183);
			con.drawString("LEADERBOARD", 150, 308);
			con.drawString("ADD QUIZ", 195, 433);
			con.drawString("QUIT", 265, 558);
			
			con.repaint();*/
		
			String strQuiz1[][];
			
			strQuiz = loadQuiz1();
	}
	public static String[][] loadQuiz1(){
		String strQuiz1[][];
		String strQuestion;
		String strA;
		String strB;
		String strC;
		String strD;
		String strAnswer;
		int intcount;
		
		strQuiz1 = new String[12][7];
		
		TextInputFile transform = new TextInputFile("transformers.txt");
		
		while(transform.eof() == false){
			strQuestion = transform.readLine();
			strA = transform.readLine();
			strB = transform.readLine();
			strC = transform.readLine();
			strD = transform.readLine();
			strAnswer = transform.readLine();
			
			for(intcount = 0; intcount <= 12; intcount++){
				String strChoice;
				int intpoints = 0;
				
				con.println("What is your answer?");
				strChoice = con.readLine();
				if(strAnswer = strChoice.equalsIgnoreCase(strAnswer)){
					intpoints++;
				}
			}
			
		}
		
		transform.close();
		
		strQuiz1 = new String[intcount][7];
		
		return strQuiz1;
		
		
	}
}

