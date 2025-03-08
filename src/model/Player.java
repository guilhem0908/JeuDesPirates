package model;

public class Player {
	private static final int MAX_HAND_SIZE = 5;
	private static final int MAX_POPULARITY_CARDS = 20;
	private String name;
	private int popularity = 0;
	private int health = 5;
	private Card[] hand = new Card[MAX_HAND_SIZE];
	private int handSize = 0;
	private Card[] popularityZone = new Card[MAX_POPULARITY_CARDS];;
	private int popularityZoneSize = 0;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getPopularity() {
		return popularity;
	}

	public void addCardToHand(Card card) {
		if (handSize < MAX_HAND_SIZE) {
			hand[handSize] = card;
			handSize++;
		}
	}

	public Card getHandCard(int index) {
		if (index >= 0 && index < handSize) {
			return hand[index];
		}
		return null;
	}

	public int getHandSize() {
		return handSize;
	}

	public Card removeCardFromHand(int index) {
		if (index >= 0 && index < handSize) {
			Card removed = hand[index];
			for (int i = index; i < handSize - 1; i++) {
				hand[i] = hand[i + 1];
			}
			hand[handSize - 1] = null;
			handSize--;
			return removed;
		}
		return null;
	}

	public void addCardToPopularityZone(Card card) {
		if (popularityZoneSize < MAX_POPULARITY_CARDS) {
			popularityZone[popularityZoneSize] = card;
			popularityZoneSize++;
		}
	}

	public Card getPopularityZoneCard(int index) {
		if (index >= 0 && index < popularityZoneSize) {
			return popularityZone[index];
		}
		return null;
	}

	public int getPopularityZoneSize() {
		return popularityZoneSize;
	}

	public Card[] getPopularityZone() {
		return popularityZone;
	}

	public void increasePopularity(int points) {
		this.popularity += points;
	}

	public void reduceHealth(int points) {
		this.health -= points;
		if (this.health < 0) {
			this.health = 0;
		}
	}
}
