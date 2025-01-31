package Teco.Challenge.Source.Service;


import Teco.Challenge.Source.Entity.PuntoVenta;
import Teco.Challenge.Source.Repository.PuntoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntoVentaService {

    @Autowired
    private PuntoVentaRepository puntoVentaRepository;

    @Cacheable(value = "puntosVenta")
    public List<PuntoVenta> getAllPuntosVenta() {
        return (List<PuntoVenta>) puntoVentaRepository.findAll();
    }

    @Cacheable(value = "puntoVenta", key = "#id")
    public Optional<PuntoVenta> getPuntoVentaById(Long id) {
        return puntoVentaRepository.findById(id);
    }

    public PuntoVenta addPuntoVenta(PuntoVenta puntoVenta) {
        return puntoVentaRepository.save(puntoVenta);
    }

   public PuntoVenta updatePuntoVenta(Long id, PuntoVenta puntoVenta) {
        Optional<PuntoVenta> existingPuntoVenta = puntoVentaRepository.findById(id);
        if (existingPuntoVenta.isPresent()) {
            puntoVenta.setId(id);
            return puntoVentaRepository.save(puntoVenta);
        } else {
            return null;
        }
    }

    public void deletePuntoVenta(Long id) {
        puntoVentaRepository.deleteById(id);
    }
}