package hu.viandren.tictactoe.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scores")
@Data
public class Scores implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private String opponentName;
    private int playerScore;
    private int opponentScore;
    private int draw;
}
