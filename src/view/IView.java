package view;

public interface IView {
    void displayWelcome();

    String[] getPlayerNames();

    void displayGameStart(String[] playerNames);

    void displayGameState(String[] player1State, String[] player2State, String[][] player1popularityZone,
            String[][] player2popularityZone, String[] attackZone);

    void displayCurrentTurn(String playerName);

    void displayDrawnPopularityCard(String[] card);

    void displayDrawnAttackCard(String[] card);
    
    void displayDrawnSpecialCard(String[] card);

    void displayHandPopularityCard(String[] card, int index);

    void displayHandAttackCard(String[] card, int index);
    
    void displayHandSpecialCard(String[] card, int index);

    int getCardChoice();
    
    int getExchangeChoice();
    
    void displayPlayedCard(String[] card);
    
    void displayExchangeResult(String cardOut, String cardIn);
    
    void displayZeroResult(int damage);

    void displayErrorChoice();

    void displayWinner(String name);
}
