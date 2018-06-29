package trial.adidas.connections.model;

import java.util.List;

public class City {

    private String city;
    private List<CityConnection> connections;

    public City(String city, List<CityConnection> connections) {
        this.city = city;
        this.connections = connections;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CityConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<CityConnection> connections) {
        this.connections = connections;
    }
}
