package Teco.Challenge.Source.Service;

import Teco.Challenge.Source.Dijkstra.Dijkstra;
import Teco.Challenge.Source.Dijkstra.Graph;
import Teco.Challenge.Source.Entity.Costo;
import Teco.Challenge.Source.Repository.CostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CostoService {

    @Autowired
    private CostoRepository costoRepository;

    Graph graph = new Graph();

    public Costo addCosto(Costo costo) {
        return costoRepository.save(costo);
    }

    public void deleteCosto(Long id) {
        costoRepository.deleteById(id);
    }

    public List<Costo> getCostosByPuntoVenta(Long idA) {
        return costoRepository.findByIdAOrIdB(idA);
    }

    public String getCostoMenor(String idA, String idB) {
        Dijkstra dijkstra = new Dijkstra(graph);
        Map<String, Integer> distances = dijkstra.shortestPath(idA);

        return "La distancia más corta desde " + idA + " hasta " + idB + " es: " + distances.get(idB);
    }

//   // public void setCosto(Costo costo) {
//        graph.addEdge(costo.getIdA(), costo.getIdB(), costo.getCosto());
//    }
}
