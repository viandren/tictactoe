package hu.viandren.tictactoe.repository;

import hu.viandren.tictactoe.entity.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamRepository extends CrudRepository<Param, Long> {
    @Query(value="select p.value from Param p")
    public String findByCode();
}
