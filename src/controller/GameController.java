package controller;

import model.GameModel;
import model.Player;
import model.Card;
import model.CardType;
import model.AttackCard;
import model.PopularityCard;
import model.ExchangeCard;
import model.ZeroCard;
import view.IView;

public class GameController {
	private final IView view;
	private final GameModel model;

	public GameController(IView view, GameModel model) {
		this.view = view;
		this.model = model;
	}

	public void start() {
		view.displayWelcome();
		String[] playerNames = view.getPlayerNames();
		model.initPlayers(playerNames[0], playerNames[1]);
		view.displayGameStart(playerNames);

		while (!model.isOver()) {
			updateGameState();

			Player currentPlayer = model.getCurrentPlayer();
			Player opponent = model.getOpponent();
			view.displayCurrentTurn(currentPlayer.getName());

			Card drawnCard = model.draw();
			displayDrawnCard(drawnCard);
			displayHandCards(currentPlayer);

			int cardChoice = getValidChoice(currentPlayer);
			Card playedCard = currentPlayer.getHandCard(cardChoice - 1);

			if (playedCard instanceof ExchangeCard exchangeCard) {
				int exchangeChoice = getValidExchangeChoice(currentPlayer);
				String[] exchangedNames = exchangeCard.executeSpecialEffect(currentPlayer, opponent, exchangeChoice - 1,
						model.getDraw());
				view.displayExchangeResult(exchangedNames[0], exchangedNames[1]);
				// Si la carte à échanger précède la carte d'échange, décaler l'indice de 1 (la
				// nouvelle carte étant ajoutée en fin).
				if (exchangeChoice < cardChoice) {
					cardChoice -= 1;
				}
			}
			if (playedCard instanceof ZeroCard zeroCard) {
				int damage = zeroCard.executeSpecialEffect(currentPlayer, opponent);
				view.displayZeroResult(damage);
			}
			if (playedCard.getType() != CardType.SPECIAL) {
				view.displayPlayedCard(createCardDTO(playedCard));
			}
			model.playCard(cardChoice - 1);
			model.switchPlayer();
		}
		view.displayWinner(model.getWinner().getName());
	}

	private void updateGameState() {
		String[] attackZoneDTO;
		if (model.getAttackZone() == null) {
			attackZoneDTO = new String[] { "" };
		} else {
			attackZoneDTO = createCardDTO(model.getAttackZone());
		}
		view.displayGameState(createPlayerStateDTO(model.getPlayer1()), createPlayerStateDTO(model.getPlayer2()),
				createCardsDTO(model.getPlayer1().getPopularityZone(), model.getPlayer1().getPopularityZoneSize()),
				createCardsDTO(model.getPlayer2().getPopularityZone(), model.getPlayer2().getPopularityZoneSize()),
				attackZoneDTO);
	}

	private void displayDrawnCard(Card card) {
		String[] cardDTO = createCardDTO(card);
		if (card.getType() == CardType.POPULARITY) {
			view.displayDrawnPopularityCard(cardDTO);
		} else if (card.getType() == CardType.ATTACK) {
			view.displayDrawnAttackCard(cardDTO);
		} else if (card.getType() == CardType.SPECIAL) {
			view.displayDrawnSpecialCard(cardDTO);
		}
	}

	private void displayHandCards(Player player) {
		for (int i = 0; i < player.getHandSize(); i++) {
			Card card = player.getHandCard(i);
			String[] cardDTO = createCardDTO(card);
			if (card.getType() == CardType.POPULARITY) {
				view.displayHandPopularityCard(cardDTO, i + 1);
			} else if (card.getType() == CardType.ATTACK) {
				view.displayHandAttackCard(cardDTO, i + 1);
			} else if (card.getType() == CardType.SPECIAL) {
				view.displayHandSpecialCard(cardDTO, i + 1);
			}
		}
	}

	private int getValidChoice(Player player) {
		int choice = view.getCardChoice();
		while (choice < 1 || choice > player.getHandSize()) {
			view.displayErrorChoice();
			choice = view.getCardChoice();
		}
		return choice;
	}

	private int getValidExchangeChoice(Player player) {
		int choice = view.getExchangeChoice();
		while (choice < 1 || choice > player.getHandSize()
				|| (player.getHandCard(choice - 1) instanceof ExchangeCard)) {
			view.displayErrorChoice();
			choice = view.getExchangeChoice();
		}
		return choice;
	}

	private String[][] createCardsDTO(Card[] cards, int count) {
		String[][] cardsDTO = new String[count][];
		for (int i = 0; i < count; i++) {
			cardsDTO[i] = createCardDTO(cards[i]);
		}
		return cardsDTO;
	}

	private String[] createCardDTO(Card card) {
		String[] cardDTO = new String[4];
		cardDTO[0] = card.getName();
		cardDTO[1] = card.getDescription();
		if (card.getType() == CardType.POPULARITY) {
			PopularityCard pCard = (PopularityCard) card;
			cardDTO[2] = String.valueOf(pCard.getHpCost());
			cardDTO[3] = String.valueOf(pCard.getPopularityPoints());
			if (cardDTO[3].length() == 1) {
				cardDTO[3] = "+" + cardDTO[3];
			}
		} else if (card.getType() == CardType.ATTACK) {
			AttackCard aCard = (AttackCard) card;
			cardDTO[2] = String.valueOf(aCard.getDamage());
			cardDTO[3] = "";
		}
		return cardDTO;
	}

	private String[] createPlayerStateDTO(Player player) {
		String[] stateDTO = new String[3];
		stateDTO[0] = player.getName();
		stateDTO[1] = String.valueOf(player.getHealth());
		stateDTO[2] = String.valueOf(player.getPopularity());
		return stateDTO;
	}
}
