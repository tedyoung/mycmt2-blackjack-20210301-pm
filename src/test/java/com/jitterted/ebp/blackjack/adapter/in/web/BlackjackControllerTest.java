package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackjackControllerTest {
  
  @Test
  public void startGameThenInitialCardsDealt() throws Exception {
    Game game = new Game(new Deck());
    BlackjackController blackjackController = new BlackjackController(game);

    String page = blackjackController.startGame();

    assertThat(page)
        .isEqualTo("redirect:/game");

    assertThat(game.playerHand().cards())
        .hasSize(2);
  }

}