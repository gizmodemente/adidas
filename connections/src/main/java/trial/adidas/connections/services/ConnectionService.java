package trial.adidas.connections.services;

import trial.adidas.connections.model.City;
import trial.adidas.connections.model.Connection;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;

public interface ConnectionService {

    Connection createAConnection(Connection connection) throws InstanceAlreadyExistsException;
    Connection getAConnection(String idConn) throws InstanceNotFoundException;
    List<Connection> getAllConnections();
    Connection modifyAConnection(Connection connection) throws InstanceNotFoundException;
    void deleteAConnection(String idConn) throws InstanceNotFoundException;
    City getCityInfo(String city);
}
