package trial.adidas.connections.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trial.adidas.connections.model.City;
import trial.adidas.connections.model.CityConnection;
import trial.adidas.connections.model.Connection;
import trial.adidas.connections.repositories.ConnectionRepository;
import trial.adidas.connections.services.ConnectionService;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionServiceDefault implements ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Override
    public Connection createAConnection(Connection connection) throws InstanceAlreadyExistsException {
        if(connectionRepository.findTopByCityAndDestination(connection.getCity(), connection.getDestination()) != null)
            throw new InstanceAlreadyExistsException("A connection with same source and destination already exists");
        return connectionRepository.save(connection);
    }

    @Override
    public Connection getAConnection(String idConn) throws InstanceNotFoundException {
        if(!connectionRepository.exists(idConn))
            throw new InstanceNotFoundException("Connection" + idConn + " does not exists");
        return connectionRepository.findOne(idConn);
    }

    @Override
    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    @Override
    public Connection modifyAConnection(Connection connection) throws InstanceNotFoundException {
        if (!connectionRepository.exists(connection.getId()))
            throw new InstanceNotFoundException("Connection" + connection.getId() + " does not exists");

        return connectionRepository.save(connection);
    }

    @Override
    public void deleteAConnection(String idConn) throws InstanceNotFoundException {
        if (connectionRepository.exists(idConn)) {
            connectionRepository.delete(idConn);
        } else
            throw new InstanceNotFoundException("Connection" + idConn + " does not exists");
    }

    @Override
    public City getCityInfo(String city) {

        List<Connection> cityConnections = connectionRepository.findByCity(city);

        return cityConnections != null ? new City(city, cityConnections.stream().
                map(CityConnection::new).collect(Collectors.toList())): null;
    }
}