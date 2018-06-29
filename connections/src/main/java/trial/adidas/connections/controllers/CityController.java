package trial.adidas.connections.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import trial.adidas.connections.model.City;
import trial.adidas.connections.services.ConnectionService;

@RestController
@RequestMapping("/cities/v1")
public class CityController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public City getCity(@PathVariable("city")String city) {
        return connectionService.getCityInfo(city);
    }
}
