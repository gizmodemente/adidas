package trial.adidas.itineraries.services;

import trial.adidas.itineraries.model.Itineraries;

import javax.management.InstanceNotFoundException;

public interface ItinerariesService {

    Itineraries getItineraries(String source, String destination) throws InstanceNotFoundException;
}
