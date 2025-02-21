package view;

public interface IView {
    void displayWelcome();
    String[] getPlayerNames();
    void displayGameStart(String[] playerNames);
    void displayPlayerState(int hp, int pop);
    void displayPopZone(String[][] cards);
}
