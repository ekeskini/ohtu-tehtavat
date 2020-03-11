package ohtu;

public class TennisGame {

	private int player1score = 0;
	private int player2score = 0;
	private String player1Name;
	private String player2Name;
	private String score = "";

	public TennisGame(String player1Name, String player2Name) {
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	public void wonPoint(String playerName) {
		if (playerName == "player1")
			player1score += 1;
		else
			player2score += 1;
	}

	public String getScore() {
		if (player1score == player2score) {
			scoreIsTied();
		} else if (player1score > 3 || player2score > 3) {
			scoreOver4();
		} else {
			scoreIsNotTied();
		}
		return score;
	}

	public void scoreIsTied() {
		if (player1score > 3) {
			score = "Deuce";
			return;
		}
		
		StringBuilder sb = new StringBuilder("");
		
		String tiedScoreString = playerScoreAsString(player1score);
		
		sb.append(tiedScoreString);
		sb.append("-All");
		
		score = sb.toString();
	}
	
	public void scoreOver4() {
		
		int difference = player1score-player2score;
		
		if (difference == 1) {
			score ="Advantage player1";
		}
		else if (difference == -1) {
			score ="Advantage player2";
		}
		else if (difference >= 2) {
			score = "Win for player1";
		}
		else score ="Win for player2";
	}

	public void scoreIsNotTied() {		
		StringBuilder sb = new StringBuilder("");

		String player1scoreString = playerScoreAsString(player1score);
		String player2scoreString = playerScoreAsString(player2score);

		sb.append(player1scoreString);
		sb.append("-");
		sb.append(player2scoreString);

		score = sb.toString();
	}

	public String playerScoreAsString (int playerscore) {
		String scstring = "";

		switch(playerscore) {
			case 0:
				scstring = "Love";
				break;
			case 1:
				scstring = "Fifteen";
				break;
			case 2:
				scstring = "Thirty";
				break;
			case 3:
				scstring = "Forty";
				break;
		}

		return scstring;
	}

}