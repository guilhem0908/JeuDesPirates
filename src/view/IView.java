package view;

public interface IView {
    void displayWelcome();
    String getPlayerName(int playerNumber);
    void displayPlayerState(String playerName, int hp, int pop);
    // void displayPlayerHand(String playerName, String[] hand);
    // void displayWinner(String winnerName)
}
