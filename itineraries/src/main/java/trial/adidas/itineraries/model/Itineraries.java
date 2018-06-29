package trial.adidas.itineraries.model;

public class Itineraries {

    private String citySource;
    private String destination;

    private Itinerary faster;
    private Itinerary direct;

    public String getCitySource() {
        return citySource;
    }

    public void setCitySource(String citySource) {
        this.citySource = citySource;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Itinerary getFaster() {
        return faster;
    }

    public void setFaster(Itinerary faster) {
        this.faster = faster;
    }

    public Itinerary getDirect() {
        return direct;
    }

    public void setDirect(Itinerary direct) {
        this.direct = direct;
    }
}
