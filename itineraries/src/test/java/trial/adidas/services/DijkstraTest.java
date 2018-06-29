package trial.adidas.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trial.adidas.itineraries.model.Connection;
import trial.adidas.itineraries.model.Itinerary;
import trial.adidas.itineraries.model.graph.Graph;
import trial.adidas.itineraries.services.GraphService;
import trial.adidas.itineraries.services.impl.GraphServiceDefault;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GraphServiceDefault.class})
public class DijkstraTest {

    @Autowired
    private GraphService graphService;

    private List<Connection> connections = new ArrayList<>();

    @Before
    public void setUp() {
        Connection con1 = new Connection();
        con1.setCity("Madrid");
        con1.setDestination("Zaragoza");
        con1.setDepartureTime(LocalTime.of(15,25));
        con1.setArrivalTime(LocalTime.of(18,45));

        Connection con2 = new Connection();
        con2.setCity("Zaragoza");
        con2.setDestination("Barcelona");
        con2.setDepartureTime(LocalTime.of(17,25));
        con2.setArrivalTime(LocalTime.of(18,45));

        Connection con3 = new Connection();
        con3.setCity("Madrid");
        con3.setDestination("Valencia");
        con3.setDepartureTime(LocalTime.of(16,25));
        con3.setArrivalTime(LocalTime.of(16,45));

        Connection con4 = new Connection();
        con4.setCity("Valencia");
        con4.setDestination("Teruel");
        con4.setDepartureTime(LocalTime.of(17,25));
        con4.setArrivalTime(LocalTime.of(17,30));

        Connection con5 = new Connection();
        con5.setCity("Teruel");
        con5.setDestination("Barcelona");
        con5.setDepartureTime(LocalTime.of(12,25));
        con5.setArrivalTime(LocalTime.of(13,15));

        Connection con6 = new Connection();
        con6.setCity("Valencia");
        con6.setDestination("Madrid");
        con6.setDepartureTime(LocalTime.of(16,25));
        con6.setArrivalTime(LocalTime.of(12,45));

        connections.add(con1);
        connections.add(con2);
        connections.add(con3);
        connections.add(con4);
        connections.add(con5);
        connections.add(con6);
    }

    @Test
    public void connectionGraphTest() {
        Graph graph = graphService.buildGraph(connections);

        Itinerary faster = graphService.fasterItinerary(graph, "Madrid", "Barcelona");
        assertThat(faster.getRoute().size()).isEqualTo(3);
        Itinerary direct = graphService.mostDirectItinerary(graph, "Madrid", "Barcelona");
        assertThat(direct.getRoute().size()).isEqualTo(2);
    }
}
