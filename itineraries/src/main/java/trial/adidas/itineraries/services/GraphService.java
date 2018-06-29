package trial.adidas.itineraries.services;

import trial.adidas.itineraries.model.Connection;
import trial.adidas.itineraries.model.Itinerary;
import trial.adidas.itineraries.model.graph.Graph;

import java.util.List;

public interface GraphService {

    Graph buildGraph(List<Connection> connections);

    Itinerary fasterItinerary(Graph graph, String source, String destination);

    Itinerary mostDirectItinerary(Graph graph, String source, String destination);
}
