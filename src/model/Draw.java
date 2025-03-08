package model;

import java.util.Random;

public class Draw {
	private Card[] cards = new Card[22];
	private int cardIndex = 0;
	private Random random = new Random();

	public Draw() {
		initializeDraw();
	}

	// On initialise le deck avec 22 cartes et on le mélange
	private void initializeDraw() {
		cards = new Card[22];
		int idx = 0;
		cards[idx++] = new PopularityCard("Discours Inspirant", "Gagne en popularité, ne coûte pas de hp.", 1, 0);
		cards[idx++] = new PopularityCard("Révolte Organisée", "Gagne en popularité, ne coûte pas de hp.", 1, 0);
		cards[idx++] = new PopularityCard("Abordage Réussi", "Gagne beaucoup de popularité.", 2, 0);
		cards[idx++] = new PopularityCard("Poing de Fer", "Gagne en popularité mais coûte un peu de hp.", 2, 1);
		for (int i = 0; i < 3; i++) {
			cards[idx++] = new PopularityCard("Discours Inspirant", "Gagne en popularité, ne coûte pas de hp.", 1, 0);
			cards[idx++] = new PopularityCard("Révolte Organisée", "Gagne en popularité, ne coûte pas de hp.", 1, 0);
			cards[idx++] = new PopularityCard("Abordage Réussi", "Gagne beaucoup de popularité.", 2, 0);
			cards[idx++] = new PopularityCard("Poing de Fer", "Gagne en popularité mais coûte un peu de hp.", 2, 1);
		}
		cards[idx++] = new AttackCard("Frappe de Sabre", "Inflige des dégâts à l'adversaire.", 2);
		cards[idx++] = new AttackCard("Explosion de Canon", "Inflige de lourds dégâts à l'adversaire.", 3);
		for (int i = 0; i < 2; i++) {
			cards[idx++] = new AttackCard("Frappe de Sabre", "Inflige des dégâts à l'adversaire.", 2);
			cards[idx++] = new AttackCard("Explosion de Canon", "Inflige de lourds dégâts à l'adversaire.", 3);
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
