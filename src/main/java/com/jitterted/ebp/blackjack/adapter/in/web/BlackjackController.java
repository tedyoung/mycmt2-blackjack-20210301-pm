package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

  private final Game game;

  @Autowired
  public BlackjackController(Game game) {
    this.game = game;
  }

  @PostMapping("/start-game")
  public String startGame() {
    // gameService.createNewGame() --> Game
    game.initialDeal();
    return "redirect:/game";
  }

  @GetMapping("/game")
  public String viewGame(Model model) {
    model.addAttribute("gameView", GameView.of(game));
    return "blackjack";
  }

  @PostMapping("/hit")
  public String hitCommand() {
    // gameService.playerHits(gameId, playerId)
    game.playerHits();
    if (game.isPlayerDone()) {
      return "redirect:/done";
    }
    return "redirect:/game";
    // game.currentState() -> GameStateEnum: GAME_OVER, GAME_IN_PROGRESS, GAME_SPLITTABLE (Similar to HATEOS/Hypermedia)
  }

  @PostMapping("/stand")
  public String standCommand() {
    // gameService.playerStands(gameId, playerId)
    game.playerStands();

    return "redirect:/done";
  }

  @GetMapping("/done")
  public String viewDone(Model model) {
    model.addAttribute("gameView", GameView.of(game));
    model.addAttribute("outcome", game.determineOutcome().toString());
    return "done";
  }
}
