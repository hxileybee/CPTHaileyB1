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
				con.drawString("IF NEEDED LOOK AT THE HELP SCREEN FIRST TO UNDERSTAND THE GAME & CONTROLS", 195, 420);
				con.drawString("PRESS (H) FOR HELP", 520, 440);
				con.drawString("WHAT WOULD YOU LIKE TO DO?", 472, 520);
				con.drawString("1 - Play, 2 - Leaderboard, 3 - Add, 4 - Quit", 365, 560);
				
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
				}else if(chrInput == 'h' || chrInput == 'H'){
					con.setDrawColor(Color.BLACK);
					con.fillRect(0, 0, 1280, 720);
					con.repaint();
					
					con.setDrawColor(Color.WHITE);
					con.drawString("LOADING HELP...", 544, 360);
					con.repaint();
					con.sleep(1000);
					
					help(con);
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
		Color neonGreen = new Color(57, 255, 20);
		
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
		
		boolean blnBrat = false;
		
		if(strname.equalsIgnoreCase("brat")){
			blnBrat = true;
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
		
		if(blnBrat){
			intScore += 3.65;
		}
		
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
			
			if(blnBrat){
				con.setDrawColor(neonGreen);
			}else{
				con.setDrawColor(Color.WHITE);
			}
			
			con.drawString(strHeader, 450, 40);
			con.setDrawColor(Color.WHITE);
			
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
			con.drawString("TYPE THE TEXT AFTER THE COLON OR THE CORRESPONDING LETTER AS YOUR ANSWER", 100, 550);
			con.drawString("ENSURE YOUR TYPED ANSWER MATCHES THE ANSWER LISTED, OR IT WILL BE MARKED AS WRONG", 100, 600);
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
			String strLetter = "";
			int intColon = -1;
			boolean blnColon = false;
			
			for(int intNum3 = 0; intNum3 < strCorrectAnswer.length() && !blnColon; intNum3++){
				if(strCorrectAnswer.charAt(intNum3) == ':' && intNum3 + 2 < strCorrectAnswer.length()){
					intColon = intNum3;
					
					strFull = strCorrectAnswer.substring((intNum3 + 2), (strCorrectAnswer.length() - 3));					
					blnColon = true;
				}
			}
			
			strLetter = strCorrectAnswer.substring(strCorrectAnswer.length() - 1);
			
			if(strAnswer.equalsIgnoreCase(strFull) || strAnswer.equalsIgnoreCase(strLetter)){
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
		con.drawString("EXAMPLE NAMES: Movies, Sports, etc", 425, 250);
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
			int intStartx = 620 - (intTotalwidth / 2);
			
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
		
		//open quiz file to write questions
		//and ask if they have a question to add
		
		TextOutputFile quizFile2 = new TextOutputFile(strquizname, true);
		
		boolean blnAnswer = true;
		
		while(blnAnswer){
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.setDrawColor(Color.WHITE);
			con.drawString("DO YOU HAVE A QUESTION TO ADD?", 442, 240);
			con.drawString("PRESS 'Y' FOR YES, PRESS 'N' FOR NO", 415, 480);
			con.repaint();
			
			char chrChoice = con.getChar();
			
			if(chrChoice == 'y' || chrChoice == 'Y'){
				String strQuestion = "";
				String strA = "";
				String strB = "";
				String strC = "";
				String strD = "";
				String strCorrect = "";
				char chrInput;
				boolean blnTyping = true;
				
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 1280, 720);
				
				con.setDrawColor(Color.WHITE);
				con.drawString("ADD THE LETTER AFTER YOUR ANSWER, EXAMPLE: ANSWER: purple, A/B/C/D", 100, 100);
				con.drawString("IF YOU DO NOT ADD THE LETTER, ALL YOUR ANSWERS WHEN PLAYING WILL COME OUT INCORRECT", 100, 120);
				con.drawString("QUESTION: ", 100, 160);
				con.drawString("A: ", 100, 200);
				con.drawString("B: ", 100, 240);
				con.drawString("C: ", 100, 280);
				con.drawString("D: ", 100, 320);
				con.drawString("ANSWER: ", 100, 360);
				con.repaint();
				
				//get question input
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strQuestion.length() > 0){
							strQuestion = strQuestion.substring(0, strQuestion.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strQuestion += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(300, 160, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strQuestion, 300, 160);
					con.repaint();
				}
				
				//get strA input
				
				blnTyping = true;
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strA.length() > 0){
							strA = strA.substring(0, strA.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strA += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(160, 200, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strA, 160, 200);
					con.repaint();
				}
				
				//get strB input
				
				blnTyping = true;
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strB.length() > 0){
							strB = strB.substring(0, strB.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strB += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(160, 240, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strB, 160, 240);
					con.repaint();
				}
				
				//get strC input
				
				blnTyping = true;
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strC.length() > 0){
							strC = strC.substring(0, strC.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strC += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(160, 280, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strC, 160, 280);
					con.repaint();
				}
				
				//get strD input
				
				blnTyping = true;
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strD.length() > 0){
							strD = strD.substring(0, strD.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strD += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(160, 320, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strD, 160, 320);
					con.repaint();
				}
				
				//get real answer input
				
				blnTyping = true;
				while(blnTyping){
					chrInput = con.getChar();
					
					if(chrInput == '\n'){
						blnTyping = false;
					}else if(chrInput == '\b'){
						if(strCorrect.length() > 0){
							strCorrect = strCorrect.substring(0, strCorrect.length() - 1);
						}
					}else if(chrInput >= 32 && chrInput <= 126){
						strCorrect += chrInput;
					}
					
					con.setDrawColor(Color.BLACK);
					con.fillRect(200, 360, 900, 30);
					con.setDrawColor(Color.WHITE);
					con.drawString(strCorrect, 200, 360);
					con.repaint();
				}
				
				quizFile2.println("Question: " +strQuestion);
				quizFile2.println("A: " +strA);
				quizFile2.println("B: " +strB);
				quizFile2.println("C: " +strC);
				quizFile2.println("D: " +strD);
				quizFile2.println("Answer: " +strCorrect);
				
			}else if(chrChoice == 'n' || chrChoice == 'N'){
				blnAnswer = false;
				
				con.setDrawColor(Color.BLACK);
				con.fillRect(0, 0, 1280, 720);
				con.setDrawColor(Color.WHITE);
				con.drawString("QUIZ SAVED SUCCESSFULLY", 460, 300);
				con.drawString("PRESS ENTER TO RETURN TO THE MAIN MENU", 370, 350);
				con.repaint();
				
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
		}
	}
	public static void help(Console con){
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
		con.repaint();
		
		con.setDrawColor(Color.WHITE);
		
		//Load text
		con.drawString("HELP CENTRE", 574, 30);
		
		con.drawString("OBJECTIVE:", 50, 70);
		con.drawString("Answer quiz questions correctly to get the highest score", 50, 100);
	
		con.drawString("HOW TO PLAY:", 50, 130);
		con.drawString("Select a quiz from the main menu", 50, 160);
		con.drawString("Answer multiple-choice questions by typing the full answer or A/B/C/D (respectively)", 50, 190);
		con.drawString("Create your own quiz using the 'ADD QUIZ' option, follow the instructions for best gameplay", 50, 220);
	
		con.drawString("CONTROLS: ", 50, 250);
		con.drawString("Use the keyboard to type answers", 50, 280);
		con.drawString("Press ENTER to submit/confirm an answer", 50, 310);
		con.drawString("Press BACKSPACE to erase characters", 50, 340);
		con.drawString("Press 1, 2, 3, .. or Y/N when applicable to respond to prompts", 50, 370);
	
		con.drawString("SCORING:", 50, 400);
		con.drawString("You earn points for each correct answer", 50, 430);
		con.drawString("Your final score is shown at the end and saved to the leaderboard", 50, 460);
		
		con.drawString("NAVIGATION:", 50, 490);
		con.drawString("Press enter to return to the main menu when prompted", 50, 520);
		
		con.drawString("NOTE: '.txt' will be added to your quiz name, you do not need to type it yourself", 50, 550);
		con.drawString("TIP: Enter Charli XCX's most popular album name for bonus points!!", 50, 580);
		
		con.drawString("PRESS ENTER TO RETURN TO THE MAIN MENU", 394, 650);
		con.repaint();
				
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
}
