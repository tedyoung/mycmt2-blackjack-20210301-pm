package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

  @Test
  public void playerHitsAndGoesBustAndLoses() throws Exception {
    Deck playerGoesBustWhenHitDeck = new StubDeck(Rank.QUEEN, Rank.EIGHT,
                                                  Rank.TEN, Rank.FOUR,
                                                  Rank.TWO);
    Game game = new Game(playerGoesBustWhenHitDeck);
    game.initialDeal();

    game.playerHits();

    assertThat(game.determineOutcome())
        .isEqualTo("You Busted, so you lose.  💸");
  }

  @Test
  public void playerStandsAndHasUpperHandBeatsDealer() throws Exception {
    Deck playerBeatsDealerUponStandDeck = new StubDeck(Rank.QUEEN, Rank.EIGHT,
                                                       Rank.TEN, Rank.KING);
    Game game = new Game(playerBeatsDealerUponStandDeck);
    game.initialDeal();

    game.playerStands();
    game.dealerTurn();

    assertThat(game.determineOutcome())
        .isEqualTo("You beat the Dealer! 💵");
  }

}