package Teco.Challenge.Source.Controller;


import Teco.Challenge.Source.Entity.PuntoVenta;
import Teco.Challenge.Source.Service.PuntoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos-venta")
public class VentaController {

    @Autowired
    private PuntoVentaService puntoVentaService;

    @GetMapping
    public List<PuntoVenta> getAllPuntosVenta() {
        return puntoVentaService.getAllPuntosVenta();
    }

    @PostMapping
    public PuntoVenta addPuntoVenta(@RequestBody PuntoVenta puntoVenta) {
        return puntoVentaService.addPuntoVenta(puntoVenta);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PuntoVenta> updatePuntoVenta(@PathVariable Long id, @RequestBody PuntoVenta puntoVenta) {
//        PuntoVenta updatedPuntoVenta = puntoVentaService.updatePuntoVenta(id, puntoVenta);
//        if (updatedPuntoVenta != null) {
//            return ResponseEntity.ok(updatedPuntoVenta);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntoVenta(@PathVariable Long id) {
        puntoVentaService.deletePuntoVenta(id);
        return ResponseEntity.noContent().build();
    }
}