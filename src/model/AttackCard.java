package model;

public class AttackCard extends Card {
	private int damage;

	public AttackCard(String name, String description, int damage) {
		super(name, description, CardType.ATTACK);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public void applyEffect(Player currentPlayer, Player opponent) {
		opponent.reduceHealth(damage);
	}
}
