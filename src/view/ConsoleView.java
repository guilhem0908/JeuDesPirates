package view;

import java.util.Scanner;

public class ConsoleView implements IView {
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void displayWelcome() {
		System.out.println("""
				============================================
				     Bienvenue dans le jeu des Pirates
				============================================
				Règles du jeu:
				2 joueurs, 4 cartes et 5 pv chacun
				Chaque tour : pioche 1 carte, joue 1 carte:
				  - Popularité : ajoute-la à ta zone.
				  - Attaque : joue-la sur ton adversaire.
				Objectif : 5 popularités ou survivre.
				============================================""");
	}

	@Override
	public String getPlayerName(int playerNumber) {
		System.out.print("Entrez le nom du joueur " + playerNumber + " : ");
		return scanner.nextLine().trim();
	}

	@Override
	public void displayPlayerState(String playerName, int hp, int pop) {
		System.out.println(playerName + " a " + hp + " cœurs et " + pop + " de popularité.");
	}

	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		view.displayWelcome();
		String playerName = view.getPlayerName(1);
		view.displayPlayerState(playerName, 5, 0);
	}

}
