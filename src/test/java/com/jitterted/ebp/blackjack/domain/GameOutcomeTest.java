package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

  @Test
  public void playerHitsAndGoesBustAndLoses() throws Exception {
    Deck playerGoesBustWhenHitDeck = StubDeck.createPlayerBustsWhenHit();
    Game game = new Game(playerGoesBustWhenHitDeck);
    game.initialDeal();

    game.playerHits();

    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_BUSTED);
  }

  @Test
  public void playerStandsAndHasUpperHandBeatsDealer() throws Exception {
    Deck playerBeatsDealerUponStandDeck = new StubDeck(Rank.QUEEN, Rank.EIGHT,
                                                       Rank.TEN, Rank.KING);
    Game game = new Game(playerBeatsDealerUponStandDeck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
  }

  @Test
  public void dealerDrawsAdditionalCardAfterPlayerStands() throws Exception {
    Deck dealerDrawsAdditionalCardDeck = new StubDeck(Rank.TEN, Rank.QUEEN,
                                                      Rank.EIGHT, Rank.FIVE,
                                                                  Rank.SIX);
    Game game = new Game(dealerDrawsAdditionalCardDeck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.dealerHand().cards())
        .hasSize(3);
  }

  @Test
  public void playerIsDealtTenValueAndAceCardsAndWinsBlackjack() throws Exception {
    Deck playerDealtBlackjackDeck = new StubDeck(Rank.ACE, Rank.EIGHT,
                                                 Rank.TEN, Rank.KING);
    Game game = new Game(playerDealtBlackjackDeck);

    game.initialDeal();

    assertThat(game.determineOutcome())
        .isEqualTo(GameOutcome.PLAYER_WINS_BLACKJACK);
  }

}