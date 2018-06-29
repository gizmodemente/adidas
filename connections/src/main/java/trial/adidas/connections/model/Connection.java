package trial.adidas.connections.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class Connection {

    @Id
    private String id;
    @NotNull
    private String city;
    @NotNull
    private String destination;
    @NotNull
    @JsonFormat (pattern = "HH:mm:ss")
    private LocalTime departureTime;
    @NotNull
    @JsonFormat (pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
