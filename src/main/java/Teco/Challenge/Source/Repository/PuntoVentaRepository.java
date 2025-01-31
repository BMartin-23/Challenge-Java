package Teco.Challenge.Source.Repository;


import Teco.Challenge.Source.Entity.PuntoVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVentaRepository extends CrudRepository<PuntoVenta, Long> {
}