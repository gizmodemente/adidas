package trial.adidas.itineraries.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class Connection {

    private String city;
    private String destination;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;
    @JsonFormat (pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
