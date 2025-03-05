package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Draw {
	private List<Card> cards;
	private Random random;

	public Draw() {
		cards = new ArrayList<>();
		random = new Random();
		initializeDraw();
	}

	// This method creates the playable cards, adds them to the card list, and
	// shuffles them.
	private void initializeDraw() {
		cards.add(new PopularityCard("Discours Inspirant", "Gagne en popularité, ne coûte pas de hp.", 1, 0));
		cards.add(new PopularityCard("Révolte Organisée", "Gagne en popularité, ne coûte pas de hp.", 1, 0));
		cards.add(new PopularityCard("Abordage Réussi", "Gagne beaucoup de popularité.", 2, 0));
		cards.add(new PopularityCard("Poing de Fer", "Gagne en popularité mais coûte un peu de hp.", 2, 1));
		for (int i = 0; i < 3; i++) {
			cards.add(new PopularityCard("Discours Inspirant", "Gagne en popularité, ne coûte pas de hp.", 1, 0));
			cards.add(new PopularityCard("Révolte Organisée", "Gagne en popularité, ne coûte pas de hp.", 1, 0));
			cards.add(new PopularityCard("Abordage Réussi", "Gagne beaucoup de popularité.", 2, 0));
			cards.add(new PopularityCard("Poing de Fer", "Gagne en popularité mais coûte un peu de hp.", 2, 1));
		}
		cards.add(new AttackCard("Frappe de Sabre", "Inflige des dégâts à l'adversaire.", 2));
		cards.add(new AttackCard("Explosion de Canon", "Inflige de lourds dégâts à l'adversaire.", 3));
		for (int i = 0; i < 2; i++) {
			cards.add(new AttackCard("Frappe de Sabre", "Inflige des dégâts à l'adversaire.", 2));
			cards.add(new AttackCard("Explosion de Canon", "Inflige de lourds dégâts à l'adversaire.", 3));
		}
		Collections.shuffle(cards, random);
	}

	public Card drawCard() {
		return cards.remove(0);
	}
}
