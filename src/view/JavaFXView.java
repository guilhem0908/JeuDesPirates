package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class JavaFXView implements IView {

    private Stage stage;
    private TextArea outputArea;
    private Label currentTurnLabel;

    // Dans le constructeur, on initialise le toolkit et la fenêtre JavaFX.
    public JavaFXView() {
        // Lancer le toolkit JavaFX s'il n'est pas déjà démarré.
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException ex) {
            // Le toolkit est déjà démarré.
        }

        // Créer et afficher la fenêtre sur le thread JavaFX.
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            stage = new Stage();
            BorderPane root = new BorderPane();

            // Zone en haut pour afficher le joueur courant.
            currentTurnLabel = new Label("Bienvenue dans le jeu des Pirates");
            currentTurnLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            root.setTop(currentTurnLabel);

            // Zone centrale pour afficher l'état du jeu.
            outputArea = new TextArea();
            outputArea.setEditable(false);
            root.setCenter(outputArea);

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Jeu des Pirates");
            stage.show();
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour ajouter du texte dans la zone d'affichage.
    private void appendOutput(String text) {
        Platform.runLater(() -> outputArea.appendText(text));
    }

    @Override
    public void displayWelcome() {
        appendOutput("****************************************\n");
        appendOutput("*  BIENVENUE DANS LE TERRIBLE JEU DES PIRATES  *\n");
        appendOutput("****************************************\n");
        appendOutput("Règles du jeu :\n • 2 joueurs, 4 cartes en main, 5 points de vie chacun\n"
                + " • À chaque tour, vous piochez une carte puis vous en jouez une.\n"
                + "   - Carte de Popularité : s'ajoute à votre zone de popularité\n"
                + "   - Carte d'Attaque : s'applique sur votre adversaire\n"
                + "Objectif : atteindre 5 points de popularité ou être le dernier vivant.\n\n");
    }

    @Override
    public String[] getPlayerNames() {
        final String[] names = new String[2];
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Noms des joueurs");
            dialog1.setHeaderText("Entrez le nom du Joueur 1");
            Optional<String> result1 = dialog1.showAndWait();
            names[0] = result1.orElse("Joueur1");

            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Noms des joueurs");
            dialog2.setHeaderText("Entrez le nom du Joueur 2");
            Optional<String> result2 = dialog2.showAndWait();
            names[1] = result2.orElse("Joueur2");

            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return names;
    }

    @Override
    public void displayGameStart(String[] playerNames) {
        appendOutput("Le duel entre " + playerNames[0] + " et " + playerNames[1] + " peut maintenant commencer !\n\n");
    }

    @Override
    public void displayGameState(String[] player1State, String[] player2State, String[][] player1popularityZone,
            String[][] player2popularityZone, String[] attackZone) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ÉTAT DU JEU ===\n");
        sb.append("Joueur 1 : ").append(player1State[0]).append(" | Vie : ").append(player1State[1])
                .append(" | Popularité : ").append(player1State[2]).append("\n");
        sb.append("Joueur 2 : ").append(player2State[0]).append(" | Vie : ").append(player2State[1])
                .append(" | Popularité : ").append(player2State[2]).append("\n");
        sb.append("Zone d'attaque : ");
        if (attackZone[0].isEmpty()) {
            sb.append("[Aucune carte]");
        } else {
            sb.append(attackZone[0]);
        }
        sb.append("\n");
        sb.append("Zone de popularité Joueur 1 :\n");
        for (int i = 0; i < player1popularityZone.length; i++) {
            sb.append("   Carte ").append(i + 1).append(" : ").append(player1popularityZone[i][0]).append("\n");
        }
        sb.append("Zone de popularité Joueur 2 :\n");
        for (int i = 0; i < player2popularityZone.length; i++) {
            sb.append("   Carte ").append(i + 1).append(" : ").append(player2popularityZone[i][0]).append("\n");
        }
        sb.append("\n");
        appendOutput(sb.toString());
    }

    @Override
    public void displayCurrentTurn(String playerName) {
        Platform.runLater(() -> currentTurnLabel.setText("Au tour de " + playerName));
        appendOutput(">>> AU TOUR DE " + playerName + "\n");
    }

    @Override
    public void displayDrawnPopularityCard(String[] card) {
        appendOutput("Carte de popularité piochée : " + card[0] + " - " + card[1] + "\n");
    }

    @Override
    public void displayDrawnAttackCard(String[] card) {
        appendOutput("Carte d'attaque piochée : " + card[0] + " - " + card[1] + "\n");
    }
    
    @Override
    public void displayDrawnSpecialCard(String[] card) {
        appendOutput("Carte spéciale piochée : " + card[0] + " - " + card[1] + "\n");
    }

    @Override
    public void displayHandPopularityCard(String[] card, int index) {
        appendOutput("Main [" + index + "] [Pop] " + card[0] + " - " + card[1] + "\n");
    }

    @Override
    public void displayHandAttackCard(String[] card, int index) {
        appendOutput("Main [" + index + "] [Atk] " + card[0] + " - " + card[1] + "\n");
    }
    
    @Override
    public void displayHandSpecialCard(String[] card, int index) {
        appendOutput("Main [" + index + "] [Spc] " + card[0] + " - " + card[1] + "\n");
    }

    @Override
    public int getCardChoice() {
        final int[] choice = new int[1];
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Choix de Carte");
            dialog.setHeaderText("Entrez le numéro de la carte à jouer :");
            Optional<String> result = dialog.showAndWait();
            try {
                choice[0] = Integer.parseInt(result.orElse("-1"));
            } catch (NumberFormatException e) {
                choice[0] = -1;
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return choice[0];
    }
    
    @Override
    public int getExchangeChoice() {
        final int[] choice = new int[1];
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Choix d'échange");
            dialog.setHeaderText("Entrez le numéro de la carte à échanger (hors Echange Furtif au RU) :");
            Optional<String> result = dialog.showAndWait();
            try {
                choice[0] = Integer.parseInt(result.orElse("-1"));
            } catch (NumberFormatException e) {
                choice[0] = -1;
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return choice[0];
    }

    @Override
    public void displayPlayedCard(String[] card) {
        appendOutput("Carte jouée : " + card[0] + " - " + card[1] + "\n");
    }
    
    @Override
    public void displayExchangeResult(String cardOut, String cardIn) {
        appendOutput(">>> La carte \"" + cardOut + "\" a été échangée avec \"" + cardIn + "\".\n");
    }

    @Override
    public void displayErrorChoice() {
        appendOutput("CHOIX INVALIDE. Veuillez réessayer.\n");
    }

    @Override
    public void displayWinner(String name) {
        appendOutput("****************************************\n");
        appendOutput("Le grand vainqueur est : " + name + " !\n");
        appendOutput("****************************************\n");
    }
}
