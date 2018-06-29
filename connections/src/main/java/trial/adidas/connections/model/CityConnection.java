package trial.adidas.connections.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class CityConnection {

    private String destination;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;
    @JsonFormat (pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    public CityConnection(Connection connection) {
        this.destination = connection.getDestination();
        this.departureTime = connection.getDepartureTime();
        this.arrivalTime = connection.getArrivalTime();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
