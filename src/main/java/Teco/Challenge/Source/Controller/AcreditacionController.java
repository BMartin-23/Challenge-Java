package Teco.Challenge.Source.Controller;

import Teco.Challenge.Source.Entity.Acreditacion;
import Teco.Challenge.Source.Service.AcreditacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AcreditacionController {

    @Autowired
    private AcreditacionService acreditacionService;

    @PostMapping
    public ResponseEntity<Acreditacion> createAcreditacion(@RequestParam double importe, @RequestParam Long puntoVentaId) {
        try {
            Acreditacion acreditacion = acreditacionService.createAcreditacion(importe, puntoVentaId);
            return ResponseEntity.ok(acreditacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Acreditacion> getAllAcreditaciones() {
        return acreditacionService.getAllAcreditaciones();
    }
}
