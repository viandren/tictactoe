package hu.viandren.tictactoe.repository;

import hu.viandren.tictactoe.entity.BoardData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDataRepository extends CrudRepository<BoardData,Long> {
}
