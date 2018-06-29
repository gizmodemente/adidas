package trial.adidas.itineraries.model;

import java.util.List;

public class Itinerary {

    private List<String> route;
    private Integer time;
    private Integer connections;

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }
}
