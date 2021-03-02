package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Hand;

import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

// Role of a DTO (domain -> console transformation), shouldn't be called outside of the Adapter package
// GOAL: this should be package access
public class ConsoleHand {

  public static String displayFirstCard(Hand hand) {
    return ConsoleCard.display(hand.cards().get(0));
  }

  public static String cardsAsString(Hand hand) {
    return hand.cards().stream()
               .map(ConsoleCard::display)
               .collect(Collectors.joining(
                      ansi().cursorUp(6).cursorRight(1).toString()));
  }
}
