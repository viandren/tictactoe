package hu.viandren.tictactoe.controller;

import hu.viandren.tictactoe.dto.GameDTO;
import hu.viandren.tictactoe.dto.PointDTO;
import hu.viandren.tictactoe.service.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class GameController {

    private static final Logger LOGGER = LogManager.getLogger(GameController.class);

    @Autowired
    GameService gameService;

    @RequestMapping(path = "/getNextMove/{crossTurn}", method = RequestMethod.POST)
    public PointDTO getNextMove(@RequestBody String[][] board, @PathVariable boolean crossTurn){


        return gameService.getNextMove(board, crossTurn);
    }


    @RequestMapping(path = "/getLastGame", method = RequestMethod.GET)
    public GameDTO getLastGame(){
        return gameService.getLastGame();
    }

}
