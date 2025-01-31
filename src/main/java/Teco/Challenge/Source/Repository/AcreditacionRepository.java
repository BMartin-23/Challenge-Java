package Teco.Challenge.Source.Repository;

import Teco.Challenge.Source.Entity.Acreditacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcreditacionRepository extends CrudRepository<Acreditacion, Long> {
}
