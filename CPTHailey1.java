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
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);				
		con.repaint();
		
		con.setDrawColor(Color.WHITE);
		con.drawString("ENTER YOUR NAME", 540, 300);
		con.repaint();
		
		String strname = "";
		char chrLetter;
		boolean blnName = true;
		
		while(blnName == true){
			chrLetter = con.getChar();
			
			if(chrLetter == '\n'){
				blnName = false;
			}
			
			strname += chrLetter;
			
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 340, 1280, 50);
			con.setDrawColor(Color.WHITE);
			int intwidth = 20;
			int intTotalwidth = strname.length() * intwidth;
			int intStartx = 640 - (intTotalwidth / 2);
			
			con.drawString(strname, intStartx, 370);
			
			con.repaint();
		}
	}
}

