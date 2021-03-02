package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Hand;
import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleGame {

  private final Game game;

  public ConsoleGame(Game game) {
    this.game = game;
  }

  // Assembler/Bootstrap/App Startup
  public static void main(String[] args) {
    Game game = new Game();
    ConsoleGame consoleGame = new ConsoleGame(game);
    consoleGame.start();
  }

  public static void resetScreen() {
    System.out.println(ansi().reset());
  }

  public static void displayWelcomeScreen() {
    System.out.println(ansi()
                           .bgBright(Ansi.Color.WHITE)
                           .eraseScreen()
                           .cursor(1, 1)
                           .fgGreen().a("Welcome to")
                           .fgRed().a(" Jitterted's")
                           .fgBlack().a(" BlackJack"));
  }

  public static String inputFromPlayer() {
    System.out.println("[H]it or [S]tand?");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public static void displayBackOfCard() {
    System.out.print(
        ansi()
            .cursorUp(7)
            .cursorRight(12)
            .a("┌─────────┐").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("└─────────┘"));
  }

  public static void displayGameState(Game game) {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
    System.out.println("Dealer has: ");
    System.out.println(ConsoleHand.displayFirstCard(game.dealerHand())); // first card is Face Up

    // second card is the hole card, which is hidden
    displayBackOfCard();

    System.out.println();
    System.out.println("Player has: ");
    Hand playerHand = game.playerHand();
    System.out.println(ConsoleHand.cardsAsString(playerHand));
    System.out.println(" (" + playerHand.value() + ")");
  }

  public static void displayFinalGameState(Game game) {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
    System.out.println("Dealer has: ");
    System.out.println(ConsoleHand.cardsAsString(game.dealerHand()));
    System.out.println(" (" + game.dealerHand().value() + ")");

    System.out.println();
    System.out.println("Player has: ");
    System.out.println(ConsoleHand.cardsAsString(game.playerHand()));
    System.out.println(" (" + game.playerHand().value() + ")");
  }

  public void start() {
    displayWelcomeScreen();

    game.initialDeal();

    playerPlays();

    game.dealerTurn();

    displayFinalGameState(game);

    game.determineOutcome();

    resetScreen();
  }

  public void playerPlays() {
    while (!game.isPlayerDone()) {
      displayGameState(game);
      String command = inputFromPlayer();
      handle(command);
    }
  }

  public void handle(String command) {
    if (command.toLowerCase().startsWith("h")) {
      game.playerHits();
    } else if (command.toLowerCase().startsWith("s")) {
      game.playerStands();
    }
  }

}
