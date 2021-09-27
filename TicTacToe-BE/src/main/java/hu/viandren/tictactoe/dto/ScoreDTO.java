package hu.viandren.tictactoe.dto;

import lombok.Data;

@Data
public class ScoreDTO {
    private String player;
    private String opponent;
    private String result;
}
