package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandDisplayTest {

  @Test
  public void displayFirstCard() throws Exception {
    Hand hand = new Hand(List.of(new Card(Suit.HEARTS, Rank.ACE)));

    assertThat(ConsoleHand.displayFirstCard(hand))
        .isEqualTo("[31m┌─────────┐[1B[11D│A        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        A│[1B[11D└─────────┘");
  }

  @Test
  public void displayHand() throws Exception {
    Hand hand = new Hand(List.of(new Card(Suit.HEARTS, Rank.SEVEN),
                                 new Card(Suit.CLUBS, Rank.QUEEN)));

    assertThat(hand.cardsAsString())
        .isEqualTo("[31m┌─────────┐[1B[11D│7        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        7│[1B[11D└─────────┘[6A[1C[30m┌─────────┐[1B[11D│Q        │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│        Q│[1B[11D└─────────┘");
  }

}
