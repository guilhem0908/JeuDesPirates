package model;

public abstract class Card {
	private String name;
	private String description;
	private CardType type;

	protected Card(String name, String description, CardType type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public CardType getType() {
		return type;
	}

	public abstract void applyEffect(Player currentPlayer, Player opponent);
}
