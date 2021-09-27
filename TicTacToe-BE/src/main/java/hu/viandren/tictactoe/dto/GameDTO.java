package hu.viandren.tictactoe.dto;

import lombok.Data;

@Data
public class GameDTO {

    private String[][] boardData;
    private String nextPlayer;
}
