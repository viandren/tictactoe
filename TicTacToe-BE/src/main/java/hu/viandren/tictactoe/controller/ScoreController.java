package hu.viandren.tictactoe.controller;

import hu.viandren.tictactoe.dto.ScoreDTO;
import hu.viandren.tictactoe.dto.ScoreTableDTO;
import hu.viandren.tictactoe.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @RequestMapping(path = "/saveScore", method = RequestMethod.POST)
    public String saveScore(@RequestBody ScoreDTO scoreDTO){
        scoreService.saveScore(scoreDTO);
        return "";
    }

    @RequestMapping(path = "/getScoreTable", method = RequestMethod.POST)
    public ScoreTableDTO getScoreTable(@RequestBody ScoreDTO scoreDTO){

        return scoreService.getScores(scoreDTO);
    }
}
