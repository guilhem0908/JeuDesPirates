package model;

public class ZeroCard extends Card {

    public ZeroCard(String name, String description) {
        super(name, description, CardType.SPECIAL);
    }

    public int executeSpecialEffect(Player currentPlayer, Player opponent) {
        // Si le joueur a 0 de popularité, infliger 3 dégâts à l'adversaire
    	int damage = 0;
        if (currentPlayer.getPopularity() <= 0) {
        	damage = 3;
            opponent.reduceHealth(3);
        }
        return damage;
    }
    
	@Override
	public void applyEffect(Player currentPlayer, Player opponent) {
		//
	}
}
