package Teco.Challenge.Source.Repository;

import Teco.Challenge.Source.Entity.Costo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostoRepository extends CrudRepository<Costo, Long> {
    List<Costo> findByIdAOrIdB(Long idA);
}
