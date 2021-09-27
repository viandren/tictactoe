package hu.viandren.tictactoe.service;

import hu.viandren.tictactoe.dto.ScoreDTO;
import hu.viandren.tictactoe.dto.ScoreTableDTO;
import hu.viandren.tictactoe.entity.Scores;
import hu.viandren.tictactoe.enums.Result;
import hu.viandren.tictactoe.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    public void saveScore(ScoreDTO scoreDTO){

        Scores scores;

        var scoresOpt = scoreRepository.getScoresByPlayerNames(scoreDTO.getPlayer(), scoreDTO.getOpponent());


        Result result;
        if (scoreDTO.getResult() != null && !scoreDTO.getResult().isEmpty()) {
            result = Result.getByText(scoreDTO.getResult());
        } else {
            result = Result.EMPTY;
        }

        if (scoresOpt.isPresent()){
            scores = scoresOpt.get();
            if (scores.getPlayerName().equals(scoreDTO.getPlayer())){
                if (result.equals(Result.WIN)) {
                    scores.setPlayerScore(scores.getPlayerScore() + 1);
                } else if (result.equals(Result.DEFEAT)) {
                    scores.setOpponentScore(scores.getOpponentScore() + 1);
                } else if (result.equals(Result.DRAW)) {
                    scores.setDraw(scores.getDraw()+1);
                }
            } else {
                if (result.equals(Result.WIN)) {
                    scores.setOpponentScore(scores.getOpponentScore() + 1);
                } else if (result.equals(Result.DEFEAT)) {
                    scores.setPlayerScore(scores.getPlayerScore() + 1);
                } else if (result.equals(Result.DRAW)) {
                    scores.setDraw(scores.getDraw()+1);
                }
            }
        } else {
            scores = new Scores();
            scores.setPlayerName(scoreDTO.getPlayer());
            scores.setOpponentName(scoreDTO.getOpponent());
            scores.setPlayerScore(result.equals(Result.WIN)?1:0);
            scores.setOpponentScore(result.equals(Result.DEFEAT)?1:0);
            scores.setDraw(result.equals(Result.DRAW)?1:0);
        }

        scoreRepository.save(scores);
    }

    public ScoreTableDTO getScores(ScoreDTO scoreDTO){

        var scoresOpt = scoreRepository.getScoresByPlayerNames(scoreDTO.getPlayer(), scoreDTO.getOpponent());
        if (scoresOpt.isPresent()){
            Scores scores = scoresOpt.get();
            ScoreTableDTO scoreTableDTO = new ScoreTableDTO();
            if (scores.getPlayerName().equals(scoreDTO.getPlayer())){

                scoreTableDTO.setPlayer1(scores.getPlayerScore());
                scoreTableDTO.setPlayer2(scores.getOpponentScore());
                scoreTableDTO.setDraw(scores.getDraw());
            } else {
                scoreTableDTO.setPlayer1(scores.getPlayerScore());
                scoreTableDTO.setPlayer2(scores.getOpponentScore());
                scoreTableDTO.setDraw(scores.getDraw());
            }
            return scoreTableDTO;
        }
        return null;
    }
}
