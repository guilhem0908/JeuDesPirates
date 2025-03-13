package model;

import java.util.Random;

public class ExchangeCard extends Card {

	public ExchangeCard(String name, String description) {
		super(name, description, CardType.SPECIAL);
	}

	public String[] executeSpecialEffect(Player currentPlayer, Player opponent, int chosenCardIndex, Draw draw) {
		Card chosenCard = currentPlayer.removeCardFromHand(chosenCardIndex);
		Random random = draw.getRandom();
		int opponentCardIndex = random.nextInt(opponent.getHandSize());
		Card opponentCard = opponent.removeCardFromHand(opponentCardIndex);
		currentPlayer.addCardToHand(opponentCard);
		opponent.addCardToHand(chosenCard);
		Card newCard = draw.drawCard();
		currentPlayer.addCardToHand(newCard);
		return new String[] { chosenCard.getName(), opponentCard.getName() };
	}

	@Override
	public void applyEffect(Player currentPlayer, Player opponent) {
		//
	}
}
