package trial.adidas.connections.repositories;

import org.springframework.data.repository.CrudRepository;
import trial.adidas.connections.model.Connection;

import java.util.List;

public interface ConnectionRepository extends CrudRepository<Connection, String> {

    List<Connection> findAll();

    List<Connection> findByCity(String city);
    Connection findTopByCityAndDestination(String city, String destination);
}
