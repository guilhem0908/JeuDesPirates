package controller;

import java.util.List;
import model.GameModel;
import model.Player;
import model.Card;
import model.CardType;
import model.AttackCard;
import model.PopularityCard;
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
			// Update game state display
			String[] attackZoneDTO;
			if (model.getAttackZone() == null) {
				attackZoneDTO = new String[] { "" };
			} else {
				attackZoneDTO = createCardDTO(model.getAttackZone());
			}
			view.displayGameState(createPlayerStateDTO(model.getPlayer1()), createPlayerStateDTO(model.getPlayer2()),
					createCardsDTO(model.getPlayer1().getPopularityZone()),
					createCardsDTO(model.getPlayer2().getPopularityZone()), attackZoneDTO);

			Player currentPlayer = model.getCurrentPlayer();
			view.displayCurrentTurn(currentPlayer.getName());

			// Draw card and display it
			Card drawnCard = model.draw();
			String[] drawnCardDTO = createCardDTO(drawnCard);
			switch (drawnCard.getType()) {
			case POPULARITY:
				view.displayDrawnPopularityCard(drawnCardDTO);
				break;
			case ATTACK:
				view.displayDrawnAttackCard(drawnCardDTO);
				break;
			default:
				break;
			}

			// Display hand cards (always 5 cards)
			for (int i = 0; i < 5; i++) {
				Card handCard = model.getCurrentPlayer().getHand().get(i);
				String[] handCardDTO = createCardDTO(handCard);
				switch (handCard.getType()) {
				case POPULARITY:
					view.displayHandPopularityCard(handCardDTO, i+1);
					break;
				case ATTACK:
					view.displayHandAttackCard(handCardDTO, i+1);
					break;
				default:
					break;
				}
			}

			int cardChoice = view.getCardChoice();
			while (cardChoice < 0 || cardChoice > 5) {
				view.displayErrorChoice();
				cardChoice = view.getCardChoice();
			}

			model.playCard(cardChoice - 1);
			model.switchPlayer();
		}

		Player winner = model.getWinner();
		view.displayWinner(winner.getName());
	}

	private String[][] createCardsDTO(List<Card> cards) {
		String[][] cardsDTO = new String[cards.size()][];
		for (int i = 0; i < cards.size(); i++) {
			cardsDTO[i] = createCardDTO(cards.get(i));
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
