package Teco.Challenge.Source.Controller;


import Teco.Challenge.Source.Entity.Costo;
import Teco.Challenge.Source.Service.CostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costos")
public class CostoController {

    @Autowired
    private CostoService costoService;

    @PostMapping
    public Costo addCosto(@RequestBody Costo costo) {
        return costoService.addCosto(costo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCosto(@PathVariable Long id) {
        costoService.deleteCosto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/punto-venta/{id}")
    public List<Costo> getCostosByPuntoVenta(@PathVariable String id) {
        return costoService.getCostosByPuntoVenta(id);
    }

    @GetMapping("/menor/{idA}/{idB}")
    public String getCostoMenor(@PathVariable String idA, @PathVariable String idB) {
        return costoService.getCostoMenor(idA, idB);
    }

    @PostMapping("/cargar-costo")
    public ResponseEntity<Void> cargarCosto(@RequestBody Costo costo) {
        costoService.setCosto(costo);
        return ResponseEntity.status(200).build();
    }
}
