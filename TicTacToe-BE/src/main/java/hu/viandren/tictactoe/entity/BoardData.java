package hu.viandren.tictactoe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "board")
@Data
public class BoardData implements Serializable {

    @Id
    private Long id;
    private String nextPlayer;
    private String boardData;
}
