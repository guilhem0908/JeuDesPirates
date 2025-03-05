package model;

import java.util.List;
import java.util.ArrayList;

public class Player {
	private String name;
	private int popularity = 0;
	private int health = 5;
	private List<Card> hand = new ArrayList<>();
	private List<Card> popularityZone = new ArrayList<>();

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

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getPopularityZone() {
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
