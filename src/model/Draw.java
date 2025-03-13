package model;

import java.util.Random;

public class Draw {
	private Card[] cards = new Card[46];
	private int cardIndex = 0;
	private Random random = new Random();

	public Random getRandom() {
		return random;
	}

	public Draw() {
		initializeDraw();
	}

	// On initialise le deck avec 34 cartes et on le mélange
	private void initializeDraw() {
		cards = new Card[46];
		int idx = 0;
		cards[idx++] = new PopularityCard("IntelliJ",
				"Vous utilisez le bateau IntelliJ et naviguez sur des eaux agitees, perdez 3 de popularite.", -3, 0);
		cards[idx++] = new PopularityCard("Eclipse",
				"Vous utilisez le bateau Eclipse et naviguez sur des eaux calmes, gagnez 3 de popularite.", 3, 0);
		for (int i = 0; i < 20; i++) {
			cards[idx++] = new PopularityCard("Repere Outils",
					"Vous reperez votre adversaire sans vous faire repere, et gagnez 1 de popularite.", 1, 0);
		}
		cards[idx++] = new AttackCard("Coup de Yaskawa",
				"Inflige 3 degats a l'adversaire a l'aide de son bras robotique industriel a haute precision.", 3);
		for (int i = 0; i < 10; i++) {
			cards[idx++] = new AttackCard("Coup de Kuka",
					"Inflige 1 degat a l'adversaire avec son bras robotique obsolete.", 1);
			cards[idx++] = new AttackCard("Coup de Staubli",
					"Inflige 2 degats a l'adversaire grace a son bras robotique de nouvelle technologie.", 2);
		}
		for (int i = 0; i < 3; i++) {
			cards[idx++] = new ExchangeCard("Echange Furtif au RU",
					"Échangez une carte contre une aléatoire adverse, puis piochez.");
		}
		// Mélange des cartes avec l'algorithme de Fisher-Yates
		for (int i = cards.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}

	public Card drawCard() {
		if (cardIndex < cards.length) {
			return cards[cardIndex++];
		}
		return null;
	}
}
