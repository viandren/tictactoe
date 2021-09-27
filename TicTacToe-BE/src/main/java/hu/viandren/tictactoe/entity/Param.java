package hu.viandren.tictactoe.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sys_param")
@Data
public class Param implements Serializable {
    @Id
    private Long id;

    private String code;
    private String value;
}
