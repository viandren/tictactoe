package hu.viandren.tictactoe.repository;

import hu.viandren.tictactoe.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Scores, Long> {

    @Query(value="Select s from Scores s where (s.playerName = :playerName and s.opponentName = :opponentName) " +
            "or (s.playerName = :opponentName and s.opponentName = :playerName)")
    public Optional<Scores> getScoresByPlayerNames(@Param("playerName") String playerName,
                                                   @Param("opponentName") String opponentName);
}
