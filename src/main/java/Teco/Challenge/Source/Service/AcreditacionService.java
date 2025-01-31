package Teco.Challenge.Source.Service;



import Teco.Challenge.Source.Entity.Acreditacion;
import Teco.Challenge.Source.Entity.PuntoVenta;
import Teco.Challenge.Source.Repository.AcreditacionRepository;
import Teco.Challenge.Source.Repository.PuntoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AcreditacionService {

    @Autowired(required=true)
    private AcreditacionRepository acreditacionRepository;

    @Autowired
    private PuntoVentaRepository puntoVentaRepository;

    public Acreditacion createAcreditacion(double importe, Long puntoVentaId) {
        Optional<PuntoVenta> puntoVenta = puntoVentaRepository.findById(puntoVentaId);
        if (puntoVenta.isPresent()) {
            Acreditacion acreditacion = new Acreditacion();
            acreditacion.setImporte(importe);
            acreditacion.setPuntoVentaId(puntoVentaId);
            acreditacion.setFechaRecepcion(LocalDateTime.now());
            acreditacion.setNombrePuntoVenta(puntoVenta.get().getNombre());
            return acreditacionRepository.save(acreditacion);
        } else {
            throw new IllegalArgumentException("Punto de venta no encontrado");
        }
    }

    public List<Acreditacion> getAllAcreditaciones() {
        return (List<Acreditacion>) acreditacionRepository.findAll();
    }
}
