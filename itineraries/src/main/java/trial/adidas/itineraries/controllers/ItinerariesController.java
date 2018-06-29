package trial.adidas.itineraries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import trial.adidas.itineraries.model.Itineraries;
import trial.adidas.itineraries.services.ItinerariesService;

import javax.management.InstanceNotFoundException;

@RestController
@RequestMapping("/itineraries/v1")
public class ItinerariesController {

    @Autowired
    private ItinerariesService itinerariesService;

    @RequestMapping(value = "/{source}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Itineraries getItineraries(@PathVariable("source") String source, @RequestParam("destination") String destination)
            throws InstanceNotFoundException {
        return itinerariesService.getItineraries(source, destination);
    }
}
