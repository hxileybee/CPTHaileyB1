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
				}else if(chrInput == '2'){//leaderboard
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
					
					con.setDrawColor(Color.WHITE);
					con.drawString("LOADING LEADERBOARD...", 500, 360);
					con.repaint();
					con.sleep(1000);
					
					leaderboard(con);
				}else if(chrInput == '3'){//add a quiz
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
					
					con.setDrawColor(Color.WHITE);
					con.drawString("TIME TO ADD A QUIZ!", 525, 360);
					con.repaint();
					con.sleep(1000);
					
					addquiz(con);
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
			}else if(chrLetter >= 32 && chrLetter <= 126){
				strname += chrLetter;
			}
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 340, 1280, 80);
			
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
		System.out.println("before" +strChoice);
		if(strChoice.charAt(strChoice.length() - 1) == '\n'){
			strChoice = strChoice.substring(0, strChoice.length() - 1);
		}
		System.out.println("after" +strChoice);
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
			
			int intPercent = 0;
			
			if(intDone > 0){
				intPercent = (intScore * 100) / intDone;
			}
			
			//Header (name, score, percent)
			String strHeader = strname +" - " +strChoice +", Score: " +intScore +"/" +intDone +" (" +intPercent +"%)";
			con.drawString(strHeader, 450, 40);
			
			//Showing/Drawing question and options
			int inty3 = 100;
			
			for(int intcount8 = 0; intcount8 < 5; intcount8++){
				con.drawString(strquizInfo[intcount7][intcount8], 100, inty3);
				inty3 += 40;
			}
			
			con.repaint();
			
			//Getting answer
			con.setDrawColor(Color.WHITE);
			con.drawString("YOUR ANSWER: ", 100, 300);
			con.drawString("TYPE THE TEXT AFTER THE COLON AS YOUR ANSWER", 100, 500);
			con.drawString("EXAMPLE - A: purple, red, you would type 'purple, red' only; INCLUDE PUNCTUATION", 100, 550);
			con.repaint();
			
			String strAnswer = "";
			char chrInput;
			boolean blnAnswered = false;
			
			while(!blnAnswered){
				chrInput = con.getChar();
				
				if(chrInput == '\n'){
					blnAnswered = true;
				}else if(chrInput == '\b'){
					if(strAnswer.length() > 0){
						strAnswer = strAnswer.substring(0, strAnswer.length() - 1);
					}
				}else if(chrInput >= 32 && chrInput <= 126){
					strAnswer += chrInput;
				}
				
				con.setDrawColor(Color.BLACK);
				con.fillRect(300, 300, 400, 40);
				con.setDrawColor(Color.WHITE);
				con.drawString(strAnswer, 300, 300);
				con.repaint();
			}
			
			//Comparing input to actual answer
			String strCorrectAnswer = strquizInfo[intcount7][5];
			
			String strFull = "";
			int intColon = -1;
			boolean blnColon = false;
			
			for(int intNum3 = 0; intNum3 < strCorrectAnswer.length() && !blnColon; intNum3++){
				if(strCorrectAnswer.charAt(intNum3) == ':' && intNum3 + 2 < strCorrectAnswer.length()){
					intColon = intNum3;
					strFull = strCorrectAnswer.substring(intNum3 + 2);
					blnColon = true;
				}
			}
		
			if(strAnswer.equalsIgnoreCase(strFull)){
				intScore++;
				con.setDrawColor(Color.WHITE);
				con.drawString("CORRECT", 100, 350);
			}else{
				con.setDrawColor(Color.WHITE);
				con.drawString("INCORRECT, " +strCorrectAnswer, 100, 350);
			}
			
			con.drawString("LOADING NEXT QUESTION...", 100, 400);
			intDone++;
			con.repaint();
			con.sleep(1500);
			con.setDrawColor(Color.BLACK);
			con.fillRect(300, 350, 600, 40);
		}
		
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
		
		//Final Percentage
		int intFinalPercent = 0;
		
		if(intDone > 0){
			intFinalPercent = (intScore * 100) / intDone;
		}
		
		//Adding to leaderboard (file)
		TextOutputFile leaderboard = new TextOutputFile("Leaderboard.txt", true);
		leaderboard.println(strname);
		leaderboard.println(strChoice);
		leaderboard.println(intFinalPercent);
		
		leaderboard.close();
		
		con.setDrawColor(Color.WHITE);
		con.drawString("QUIZ COMPLETE! YOUR SCORE: " +intScore +"/" +intDone, 460, 275);
		con.drawString("YOU HAVE BEEN ADDED TO THE LEADERBOARD", 424, 312);
		con.drawString("PRESS ENTER TO RETURN TO THE MAIN MENU", 424, 350);
		con.repaint();
		con.sleep(2000);
		
		//Back to main screen
		char chrLeave = con.getChar();
		
		if(chrLeave == '\n'){
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.setDrawColor(Color.WHITE);
			con.drawString("RETURNING TO MAIN MENU...", 500, 360);
			con.repaint();
			con.sleep(2000);
		}
	}
	public static void leaderboard(Console con){
		//clear screen
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
		
		//load leaderboard file into 2D array
		TextInputFile leaderboard2 = new TextInputFile("Leaderboard.txt");
		
		String strNames;
		String strQuizName;
		double dblPercentage;
		int intLeader = 0;
		
		while(leaderboard2.eof() == false){
			strNames = leaderboard2.readLine();
			strQuizName = leaderboard2.readLine();
			dblPercentage = leaderboard2.readDouble();
			intLeader++;
		}
		
		leaderboard2.close();
		
		String strLeaderboard[][] = new String[intLeader][2];
		double dblPercents[] = new double[intLeader];
		
		TextInputFile leaderboard3 = new TextInputFile("Leaderboard.txt");
		
		int intRow = 0;
		
		while(leaderboard3.eof() == false){
			String strNames2 = leaderboard3.readLine();
			String strQuizName2 = leaderboard3.readLine();
			double dblPercentage2 = leaderboard3.readDouble();
			
			strLeaderboard[intRow][0] = strNames2;
			strLeaderboard[intRow][1] = strQuizName2;
			dblPercents[intRow] = dblPercentage2;
			
			intRow++;
		}
		
		leaderboard3.close();
		
		//sort by highest percentage
		for(int intcount = 0; intcount < intLeader - 1; intcount++){
			for(int intcount2 = 0; intcount2 < intLeader - intcount - 1; intcount2++){
				if(dblPercents[intcount2] < dblPercents[intcount2 + 1]){
					double dblTemp = dblPercents[intcount2];
					dblPercents[intcount2] = dblPercents[intcount2 + 1];
					dblPercents[intcount2 + 1] = dblTemp;
					
					String strTemp[] = strLeaderboard[intcount2];
					strLeaderboard[intcount2] = strLeaderboard[intcount2 + 1];
					strLeaderboard[intcount2 + 1] = strTemp;
				}
			}
		}
		
		//display on screen (name - quiz - score)
		
		con.setDrawColor(Color.WHITE);
		con.drawString("LEADERBOARD", 565, 100);
		con.repaint();
	
		int inty = 150;
		
		for(int intcount3 = 0; intcount3 < intLeader; intcount3++){
			con.drawString(strLeaderboard[intcount3][0] +" - " +strLeaderboard[intcount3][1] +" - " +dblPercents[intcount3], 430, inty);
			inty += 30;
		}
		
		con.drawString("PRESS ENTER TO RETURN TO THE MAIN MENU", 405, 600);
		con.repaint();
		con.sleep(500);
		
		//Back to main screen
		char chrLeave = con.getChar();
		
		if(chrLeave == '\n'){
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.setDrawColor(Color.WHITE);
			con.drawString("RETURNING TO MAIN MENU...", 500, 360);
			con.repaint();
			con.sleep(2000);
		}
	}
	public static void addquiz(Console con){
		//code goes here :P
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
		
		con.setDrawColor(Color.WHITE);
		con.drawString("WHAT IS THE NAME OF YOUR QUIZ?", 450, 200);
		con.drawString("ADD '.txt' TO THE END OF YOUR NAME", 420, 250);
		con.drawString("EXAMPLE: Movies.txt", 505, 300);
		con.repaint();
		
		//entering quiz name code (center of screen-ish)
		//reuse code from above for reference :P
		String strquizname = "";
		char chrLetter;
		boolean blnName = true;
		
		while(blnName){
			chrLetter = con.getChar();
			
			if(chrLetter == '\n'){
				blnName = false;
			}else if(chrLetter == '\b'){//google says backspace is \b
				if(strquizname.length() > 0){
					strquizname = strquizname.substring(0, strquizname.length() - 1);
				}
			}else if(chrLetter >= 32 && chrLetter <= 126){
				strquizname += chrLetter;
			}
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 340, 1280, 80);
			
			con.setDrawColor(Color.WHITE);			
			int intwidth = 12;
			int intTotalwidth = strquizname.length() * intwidth;
			int intStartx = 640 - (intTotalwidth / 2);
			
			con.drawString(strquizname, intStartx, 370);
			con.repaint();
		}
		
		if(strquizname.length() < 4 || strquizname.substring(strquizname.length()-4, strquizname.length()) != ".txt"){
			strquizname = strquizname + ".txt";
		}
		
		//add that quiz name to Quizzes.txt
		TextOutputFile quizFile = new TextOutputFile("Quizzes.txt", true);
		
		quizFile.println(strquizname);
		
		quizFile.close();
		
		//then ask if they have a question to add
		
		//ask for the question, A, B, C, D 
		//and the real answer
		
		
		//then add that to the file with that name
		
		
		//clear screen + text	
		
		
	}
}

