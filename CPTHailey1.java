import arc.*;
import java.awt.*;

public class CPTHailey1{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice Game", 1280, 720);
			
			boolean blnMenu = true;
			
			while(blnMenu){
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 1280, 720);
				
				con.setDrawColor(Color.WHITE);
				con.drawString("MULTIPLE CHOICE GAME", 510, 25);
				
				con.setDrawColor(Color.WHITE);
				con.drawString("PLAY", 600, 180);
				con.drawString("LEADERBOARD", 558, 220);
				con.drawString("ADD QUIZ", 575, 260);
				con.drawString("QUIT", 600, 300);
				con.drawString("WHAT WOULD YOU LIKE TO DO? (click 1, 2, 3, or 4)", 350, 480);
				con.drawString("1 - Play, 2 - Leaderboard, 3 - Add, 4 - Quit", 365, 500);
				
				con.repaint();
				
				char chrInput = con.getChar();
				
				if(chrInput == '1'){//play game
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
				
					con.setDrawColor(Color.WHITE);
					con.drawString("LOADING GAME...", 550, 360);
					con.repaint();
					con.sleep(1000);
					
					playgame(con);
					blnMenu = false;
				}else if(chrInput == '2'){//leaderboard
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
					
					con.setDrawColor(Color.WHITE);
					con.drawString("LOADING LEADERBOARD...", 500, 360);
					con.repaint();
					con.sleep(1000);
					
					blnMenu = false;
				}else if(chrInput == '3'){//add a quiz
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
					
					con.setDrawColor(Color.WHITE);
					con.drawString("TIME TO ADD A QUIZ!", 525, 360);
					con.repaint();
					con.sleep(1000);
					
					blnMenu = false;
				}else if(chrInput == '4'){//closes window
					con.closeConsole();
				}
			}
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.repaint();
	}
	public static void playgame(Console con){
		//ask for name
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);				
		con.repaint();
		
		con.setDrawColor(Color.WHITE);
		con.drawString("ENTER YOUR NAME", 550, 300);
		con.repaint();
		
		//entering name code (center of screen-ish)
		//try to fix overlap + streaking
		String strname = "";
		char chrLetter;
		boolean blnName = true;
		
		while(blnName){
			chrLetter = con.getChar();
			
			if(chrLetter == '\n'){
				blnName = false;
			}else if(chrLetter == '\b'){//google says backspace is \b
				if(strname.length() > 0){
					strname = strname.substring(0, strname.length() - 1);
				}
			}else{
				strname += chrLetter;
			}
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 340, 1280, 50);
			
			con.setDrawColor(Color.WHITE);			
			int intwidth = 12;
			int intTotalwidth = strname.length() * intwidth;
			int intStartx = 640 - (intTotalwidth / 2);
			
			con.drawString(strname, intStartx, 370);
			con.repaint();
		}
		
		//quiz selection
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
		con.repaint();
		
		con.setDrawColor(Color.WHITE);
		con.drawString("WHICH QUIZ WOULD YOU LIKE TO PLAY?", 430, 80);
		con.drawString("PRESS THE CORRESPONDING NUMBER FOR YOUR CHOSEN QUIZ", 335, 500);
		
		TextInputFile quizFile = new TextInputFile("Quizzes.txt");
		
		int intLines;
		intLines = 0;
		
		while(quizFile.eof() == false){
			quizFile.readLine();
			intLines++;
		}
		
		quizFile.close();
		
		String strquizNames[] = new String[intLines];
		TextInputFile quizFile2 = new TextInputFile("Quizzes.txt");
		
		int inty = 120;
		int intNum = 1;
		int intNum2 = 0;
		
		while(quizFile2.eof() == false){
			String strLine = quizFile2.readLine();
			strquizNames[intNum2] = strLine;
			
			con.setDrawColor(Color.WHITE);
			con.drawString(intNum +" - " +strLine, 500, inty);
			inty += 30;
			intNum++;
			intNum2++;
		}
		
		quizFile2.close();
		con.repaint();
		
		con.sleep(500);
		
		//quiz selection input
		boolean blnQuizchoice = false;
		int intPick = 0;
		
		while(blnQuizchoice == false){
			char chrQuiz = con.getChar();
			//google says char --> int, subtract '0' for digits 0-9
			intPick = chrQuiz - '0';
			
			if(intPick >= 1 && intPick <= strquizNames.length){
				String strChoice = strquizNames[intPick - 1];
			
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 1280, 720);
				con.setDrawColor(Color.WHITE);
			
				TextInputFile quiz = new TextInputFile(strChoice);
			
				int inty2 = 100;
				
				while(quiz.eof() == false){
					String strLine2 = quiz.readLine();
					con.drawString(strLine2, 100, inty2);
					inty2 += 30;
				}
				
				quiz.close();
				con.repaint();
				blnQuizchoice = true;
			
			}else{
				con.setDrawColor(Color.BLACK);
				con.fillRect(598, 600, 300, 40);
				con.setDrawColor(Color.WHITE);
				con.drawString("INVALID", 598, 600);
				con.repaint();
			}
		}
		
		String strChoice = strquizNames[intPick - 1];
		
		if(strChoice.charAt(strChoice.length() - 1) == '\n'){
			strChoice = strChoice.substring(0, strChoice.length() - 1);
		}
		
		//Quiz into array
		
		TextInputFile Loadquiz = new TextInputFile(strChoice);
		int intLineCount = 0;
		
		while(Loadquiz.eof() == false){
			Loadquiz.readLine();
			intLineCount++;
		}
		
		Loadquiz.close();
		
		int intQuestions = intLineCount / 6;
		String strquizInfo[][] = new String[intQuestions][7];
		
		TextInputFile Loadquiz2 = new TextInputFile(strChoice);
		
		for(int intcount2 = 0; intcount2 < intQuestions; intcount2++){
			for(int intcount3 = 0; intcount3 < 6; intcount3++){
				strquizInfo[intcount2][intcount3] = Loadquiz2.readLine();
			}
		}
		
		Loadquiz2.close();
		
		//random numbers in last column
		for(int intcount4 = 0; intcount4 < intQuestions; intcount4++){
			int intRandom = (int)(Math.random() * 100);
			strquizInfo[intcount4][6] = "" + intRandom;
		}
		
		//bubble sorting column 6
		for(int intcount5 = 0; intcount5 < intQuestions - 1; intcount5++){
			for(int intcount6 = 0; intcount6 < intQuestions - intcount5 - 1; intcount6++){
				int intNum3 = Integer.parseInt(strquizInfo[intcount6][6]);
				int intNum4 = Integer.parseInt(strquizInfo[intcount6 + 1][6]);
				
				if(intNum3 > intNum4){
					String strTemp[] = strquizInfo[intcount6];
					strquizInfo[intcount6] = strquizInfo[intcount6 + 1];
					strquizInfo[intcount6 + 1] = strTemp;
				}
			}
		}
		
		int intScore = 0;
		int intDone = 0;
		
		for(int intcount7 = 0; intcount7 < intQuestions; intcount7++){
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.setDrawColor(Color.WHITE);
			
			int inty3 = 100;
			
			for(int intcount8 = 0; intcount8 < 5; intcount8++){
				con.drawString(strquizInfo[intcount7][intcount8], 100, inty3);
				inty3 += 40;
			}
			
			con.repaint();
		}
	}
}
