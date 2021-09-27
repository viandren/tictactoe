package hu.viandren.tictactoe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hu.viandren.tictactoe.dto.GameDTO;
import hu.viandren.tictactoe.dto.PointDTO;
import hu.viandren.tictactoe.aicomponent.Board;
import hu.viandren.tictactoe.aicomponent.Mark;
import hu.viandren.tictactoe.aicomponent.MiniMax;
import hu.viandren.tictactoe.entity.BoardData;
import hu.viandren.tictactoe.repository.BoardDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    BoardDataRepository boardRepository;

    private static final Logger LOGGER = LogManager.getLogger(GameService.class);

    public PointDTO getNextMove(String[][] arr, boolean crossTurn){
        try {



            Board board = fillBoard(arr, crossTurn);


            int[] step =  MiniMax.getBestMove(board);

            arr[step[0]][step[1]] = crossTurn? "x" : "o";

            saveBoardData(arr, crossTurn);

            return new PointDTO(step[0],step[1]);

        } catch(Exception ex){
            LOGGER.error("getNextMove", ex);
        }

        return new PointDTO(0,0);
    }

    private Board fillBoard(String[][] arr, boolean crossTurn){
        Board board = new Board() ;
        int x = 0;
        for (String[] strings : arr) {
            int y = 0;
            for (String string : strings) {
                if (string.equals("x")) {
                    if (crossTurn) {
                        board.setMarkAt(x, y, Mark.X);
                    } else {
                        board.setMarkAt(x, y, Mark.O);
                    }
                }

                if (string.equals("o")){
                    if (crossTurn) {
                        board.setMarkAt(x, y, Mark.O);
                    } else {
                        board.setMarkAt(x, y, Mark.X);
                    }
                }

                y++;
            }
            x++;
        }

        if (crossTurn) {
            board.setCrossTurn(true);
        } else {
            board.setCrossTurn(false);
        }
        return board;
    }

    private void saveBoardData(String[][] arr, boolean crossTurn){

        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(arr);

            BoardData boardData;
            Optional<BoardData> boardDataOptional = boardRepository.findById(1L);
            if (boardDataOptional.isPresent()) {
                boardData = boardDataOptional.get();
            } else {
                boardData = new BoardData();
                boardData.setId(1L);
            }
            boardData.setBoardData(json);
            boardData.setNextPlayer(crossTurn? "x" : "o");
            boardRepository.save(boardData);

        } catch(Exception ex){
            LOGGER.error("saveBoardData", ex);
        }
    }

    public GameDTO getLastGame() {

        Optional<BoardData> boardDataOptional = boardRepository.findById(1L);
        if(boardDataOptional.isPresent()){
            BoardData boardData = boardDataOptional.get();

            ObjectMapper mapper = new ObjectMapper();


            GameDTO gameDTO = new GameDTO();
            try {
                gameDTO.setBoardData(mapper.readValue(boardData.getBoardData(), String[][].class));
            } catch (JsonProcessingException ex) {
                LOGGER.error("getLastGame", ex);
            }
            gameDTO.setNextPlayer(boardData.getNextPlayer());
            return gameDTO;
        }
        return null;
    }
}
