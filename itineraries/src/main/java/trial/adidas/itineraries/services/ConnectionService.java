package trial.adidas.itineraries.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trial.adidas.itineraries.model.Connection;

import java.util.List;

@FeignClient(value = "connections", path = "/connections/v1")
public interface ConnectionService {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Connection> getAllConnections();
}
