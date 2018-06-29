package trial.adidas.itineraries.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trial.adidas.itineraries.model.Connection;
import trial.adidas.itineraries.model.Itineraries;
import trial.adidas.itineraries.model.Itinerary;
import trial.adidas.itineraries.model.graph.Graph;
import trial.adidas.itineraries.services.ConnectionService;
import trial.adidas.itineraries.services.GraphService;
import trial.adidas.itineraries.services.ItinerariesService;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItinerariesServiceDefault implements ItinerariesService {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private GraphService graphService;

    @Override
    public Itineraries getItineraries(String source, String destination) throws InstanceNotFoundException {

        List<Connection> connections = connectionService.getAllConnections();

        List<String> citiesSource = connections.stream().map(Connection::getCity).distinct().collect(Collectors.toList());
        List<String> citiesDestination = connections.stream().map(Connection::getDestination).distinct().collect(Collectors.toList());

        if(!citiesSource.contains(source))
            throw new InstanceNotFoundException("City " + source + " is not a valid source for calculating itineraries");

        if(!citiesDestination.contains(destination))
            throw new InstanceNotFoundException("City " + destination + " is not a valid destination for calculating itineraries");

        // Build the graph
        Graph graph = graphService.buildGraph(connections);

        // Calculate itineraries
        Itinerary fasterItinerary = graphService.fasterItinerary(graph, source, destination);
        Itinerary mostDirectItinerary = graphService.mostDirectItinerary(graph, source, destination);

        Itineraries itineraries = new Itineraries();
        itineraries.setCitySource(source);
        itineraries.setDestination(destination);
        itineraries.setDirect(mostDirectItinerary);
        itineraries.setFaster(fasterItinerary);

        return itineraries;
    }
}
