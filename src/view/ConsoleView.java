package view;

import java.util.Scanner;

public class ConsoleView implements IView {
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void displayWelcome() {
		System.out.println("""
				╔══════════════════════════════════════════════════════════════════════╗
				║              BIENVENUE DANS LE TERRIBLE JEU DES PIRATES              ║
				╠══════════════════════════════════════════════════════════════════════╣
				║                          ~ RÈGLES DU JEU ~                           ║
				║                                                                      ║
				║  • 2 joueurs, 4 cartes en main et 5 points de vie chacun             ║
				║  • À CHAQUE TOUR :                                                   ║
				║    1) Vous piochez une carte                                         ║
				║    2) Vous jouez une carte de votre main :                           ║
				║       → Carte de Popularité : s'ajoute à votre zone de popularité    ║
				║       → Carte d'Attaque     : s'applique sur votre adversaire        ║
				║  • Objectif : Atteindre 5 de popularité OU être le dernier vivant    ║
				╚══════════════════════════════════════════════════════════════════════╝""");
	}

	@Override
	public String[] getPlayerNames() {
		String[] playerNames = new String[2];
		for (int i = 0; i < 2; i++) {
			System.out.print("\nEntrez le nom du joueur " + (i + 1) + " ➤ ");
			playerNames[i] = scanner.nextLine().trim();
		}
		System.out.println("");
		return playerNames;
	}

	@Override
	public void displayGameStart(String[] playerNames) {
		System.out.println("""
				╔══════════════════════════════════════════════════════════════════════╗
				║                  LE DUEL PEUT MAINTENANT COMMENCER                   ║
				╚══════════════════════════════════════════════════════════════════════╝""");
	}

	@Override
	public void displayGameState(String[] player1State, String[] player2State, String[][] player1PopularityZone,
			String[][] player2PopularityZone, String[] attackZone) {
		System.out.println("");
		System.out.println("""
				╔══════════════════════════════════════════════════════════════════════╗
				║                          ÉTAT ACTUEL DU JEU                          ║
				╚══════════════════════════════════════════════════════════════════════╝""");
		System.out.println("┌─── Informations Joueurs ─────────────────────────────────────────────┐");
		System.out.printf("│ Joueur 1 : %-20s | Vie : %-3s | Popularité : %-2s / 5    │%n", player1State[0],
				player1State[1], player1State[2]);
		System.out.printf("│ Joueur 2 : %-20s | Vie : %-3s | Popularité : %-2s / 5    │%n", player2State[0],
				player2State[1], player2State[2]);
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.println("┌─── Zone de Popularité du Joueur 1 ───────────────────────────────────┐");
		if (player1PopularityZone.length == 0) {
			System.out.println("│                          [ Aucune carte ]                            │");
		} else {
			for (int i = 0; i < player1PopularityZone.length; i++) {
				System.out.printf("│ Carte %d : %-21s | Popularité : %-2s | Coût : %-2s Vie    │%n", i + 1,
						player1PopularityZone[i][0], player1PopularityZone[i][3], player1PopularityZone[i][2]);
			}
		}
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.println("┌─── Zone de Popularité du Joueur 2 ───────────────────────────────────┐");
		if (player2PopularityZone.length == 0) {
			System.out.println("│                          [ Aucune carte ]                            │");
		} else {
			for (int i = 0; i < player2PopularityZone.length; i++) {
				System.out.printf("│ Carte %d : %-21s | Popularité : %-2s | Coût : %-2s Vie    │%n", i + 1,
						player2PopularityZone[i][0], player2PopularityZone[i][3], player2PopularityZone[i][2]);
			}
		}
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
		System.out.println();
		System.out.println("┌─── Zone d'Attaque ───────────────────────────────────────────────────┐");
		if ("".equals(attackZone[0])) {
			System.out.println("│                          [ Aucune carte ]                            │");
		} else {
			System.out.printf("│ Carte : %-20s | Dégâts : %-2s                           │%n", attackZone[0],
					attackZone[2]);
		}
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
		System.out.println();
	}

	@Override
	public void displayCurrentTurn(String playerName) {
		System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
		System.out.printf("║ >>> AU TOUR DE %-20s                                  ║%n", playerName.toUpperCase());
		System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
		System.out.println();
	}

	@Override
	public void displayDrawnPopularityCard(String[] card) {
		System.out.println("┌─── Carte de popularité piochée ──────────────────────────────────────┐");
		System.out.printf("│ Nom : %-40s                       │%n", card[0]);
		System.out.printf("│ > Popularité : %-2s             > Coût : -%-2s Vie                       │%n", card[3],
				card[2]);
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
	}

	@Override
	public void displayDrawnAttackCard(String[] card) {
		System.out.println("┌─── Carte d'attaque piochée ──────────────────────────────────────────┐");
		System.out.printf("│ Nom : %-40s                       │%n", card[0]);
		System.out.printf("│ > Dégâts : %-2svie                                                     │%n", card[2]);
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
	}

	@Override
	public void displayDrawnSpecialCard(String[] card) {
		System.out.println("┌─── Carte speciale piochée ───────────────────────────────────────────┐");
		System.out.printf("│ Nom : %-40s                       │%n", card[0]);
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
	}

	@Override
	public void displayHandPopularityCard(String[] card, int index) {
		if (!card[2].equals("0")) {
			System.out.printf("[%d] [Pop] %-25s -  Popularité : %-2s | Coût : -%s Vie%n", index, card[0], card[3],
					card[2]);
		} else {
			System.out.printf("[%d] [Pop] %-25s -  Popularité : %s%n", index, card[0], card[3]);
		}
	}

	@Override
	public void displayHandAttackCard(String[] card, int index) {
		System.out.printf("[%d] [Atk] %-25s -  Dégâts : -%s Vie%n", index, card[0], card[2]);
	}

	@Override
	public void displayHandSpecialCard(String[] card, int index) {
		System.out.printf("[%d] [Spc] %-25s -  %s%n", index, card[0], card[1]);
	}

	@Override
	public int getCardChoice() {
		System.out.printf("%nChoisissez une carte à jouer (1-5) : ");
		try {
			return Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public int getExchangeChoice() {
		System.out.printf("%nChoisissez une carte à échanger (1-5, hors Echange Furtif au RU) : ");
		try {
			return Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public void displayPlayedCard(String[] card) {
		System.out.printf("%n>>> %s%n", card[1]);
	}

	public void displayExchangeResult(String cardOut, String cardIn) {
		System.out.printf("%n>>> La carte \"%s\" a été échangée avec \"%s\".%n", cardOut, cardIn);
	}
	
	public void displayZeroResult(int damage) {
		System.out.printf("%n>>> Le dirac en 0 à infliger %d, quelle force!%n", damage);
	}

	@Override
	public void displayErrorChoice() {
		System.out.println("""
				╔══════════════════════════════════════════════════════════════════════╗
				║                  CHOIX INVALIDE. RÉESSAYEZ !                         ║
				╚══════════════════════════════════════════════════════════════════════╝""");
	}

	@Override
	public void displayWinner(String name) {
		System.out.println("""
				╔══════════════════════════════════════════════════════════════════════╗
				║                           FIN DE LA PARTIE                           ║
				╚══════════════════════════════════════════════════════════════════════╝""");
		System.out.println("Le grand vainqueur est : " + name + " !");
	}

	public static void main(String[] args) {
		//
	}
}
