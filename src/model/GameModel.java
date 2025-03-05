package model;

public class GameModel {
	private Player player1;
	private Player player2;
	private Draw draw = new Draw();
	private Player currentPlayer;
	private Player opponent;
	private Card attackZone;
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getOpponent() {
		return opponent;
	}

	public Card getAttackZone() {
		return attackZone;
	}

	public void initPlayers(String playerName1, String playerName2) {
		player1 = new Player(playerName1);
		player2 = new Player(playerName2);
		initializeHands();
		currentPlayer = player1;
		opponent = player2;
	}

	private void initializeHands() {
		for (int i = 0; i < 4; i++) {
			player1.getHand().add(draw.drawCard());
			player2.getHand().add(draw.drawCard());
		}
	}

	public Card draw() {
		Card newCard = draw.drawCard();
		currentPlayer.getHand().add(newCard);
		return newCard;
	}

	public void playCard(int index) {
		Card playedCard = currentPlayer.getHand().get(index);
		playedCard.applyEffect(currentPlayer, opponent);
		currentPlayer.getHand().remove(index);
		if (playedCard.getType() == CardType.POPULARITY) {
			currentPlayer.getPopularityZone().add(playedCard);
			attackZone = null;
		} else if (playedCard.getType() == CardType.ATTACK) {
			attackZone = playedCard;
		}
	}

	public void switchPlayer() {
		Player tmp = currentPlayer;
		currentPlayer = opponent;
		opponent = tmp;
	}

	public boolean isOver() {
		return player1.getPopularity() >= 5 || player2.getPopularity() >= 5 || player1.getHealth() <= 0
				|| player2.getHealth() <= 0;
	}

	public Player getWinner() {
		if (player1.getPopularity() >= 5) {
			return player1;
		} else if (player2.getPopularity() >= 5) {
			return player2;
		}
		if (player1.getHealth() <= 0) {
			return player2;
		} else if (player2.getHealth() <= 0) {
			return player1;
		}
		return null;
	}
}
