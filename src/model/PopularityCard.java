package model;

public class PopularityCard extends Card {
	private int popularityPoints;
	private int healthCost;

	public PopularityCard(String name, String description, int popularityPoints, int healthCost) {
		super(name, description, CardType.POPULARITY);
		this.popularityPoints = popularityPoints;
		this.healthCost = healthCost;
	}

	public int getPopularityPoints() {
		return popularityPoints;
	}

	public int getHpCost() {
		return healthCost;
	}

	@Override
	public void applyEffect(Player currentPlayer, Player opponent) {
		currentPlayer.reduceHealth(healthCost);
		currentPlayer.increasePopularity(popularityPoints);
	}
}
