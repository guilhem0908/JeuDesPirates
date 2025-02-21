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
				2 joueurs, 4 cartes et 5 vies chacun
				Chaque tour : pioche 1 carte, joue 1 carte:
				  - Popularité : ajoute-la à ta zone
				  - Attaque : joue-la sur ton adversaire
				Objectif : 5 popularité ou survivre
				============================================""");
	}

	@Override
	public String[] getPlayerNames() {
		String[] playerNames = new String[2];
		for (int i = 0; i < 2; i++) {
			System.out.print("Entrez le nom du joueur " + (i + 1) + " : ");
			playerNames[i] = scanner.nextLine().trim();
		}
		return playerNames;
	}

	public void displayGameStart(String[] playerNames) {
		System.out.println("""
				============================================
				            Début de la partie!
				============================================""");
		System.out.println("Pirates: " + playerNames[0] + " VS " + playerNames[1]);
		System.out.println("    - 5 vies");
		System.out.println("    - 0 de popularité");
		System.out.println("    - 4 cartes en main");
		System.out.println("    - zone de popularité vide");
	}

	@Override
	public void displayPlayerState(int hp, int pop) {
		System.out.println(hp + " vie et " + pop + " de popularité.");
	}

	@Override
	public void displayPopZone(String[][] cards) {
		System.out.println("Zone de popularité :");
		for (int i = 0; i < cards.length; i++) {
			System.out.println("- " + cards[i][0]);
			System.out.println("    Popularité: " + cards[i][3]);
			System.out.println("    Vie: " + cards[i][2]);
		}
	}

	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		view.displayWelcome();
		String[] playerNames = view.getPlayerNames();
		view.displayGameStart(playerNames);
	}

}
