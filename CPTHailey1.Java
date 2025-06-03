public class CPTHailey1{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice Game", 1280, 720);
			
			con.setDrawColor(Color.WHITE);
			con.fillRect(0, 0, 1280, 720);
			
			con.setDrawColor(new Color(137, 207, 240));
			con.fillRoundRect(150, 200, 350, 100,  50, 50);
			con.fillRoundRect(150, 325, 350, 100,  50, 50);
			con.fillRoundRect(150, 450, 350, 100,  50, 50);
			con.fillRoundRect(150, 575, 350, 100,  50, 50);
			
			Font fntTitle = con.loadFont("TITLE.ttf", 100);
			Font fntOptions = con.loadFont("TITLE.ttf", 60);
			
			con.setDrawFont(fntTitle);
			con.setDrawColor(new Color(137, 207, 240));
			con.drawString("MULTIPLE CHOICE GAME", 125, 0);
			
			con.setDrawFont(fntOptions);
			con.setDrawColor(Color.WHITE);
			con.drawString("PLAY", 265, 183);
			con.drawString("LEADERBOARD", 150, 308);
			con.drawString("ADD QUIZ", 195, 433);
			con.drawString("QUIT", 265, 558);
			
			con.repaint();
			
			char chrChoice;
			
			chrChoice = con.getChar();
			
			if(chrChoice == 1){
				con.setDrawColor(Color.WHITE);
				con.fillRect(0, 0, 1280, 720);
			}
	}
}
