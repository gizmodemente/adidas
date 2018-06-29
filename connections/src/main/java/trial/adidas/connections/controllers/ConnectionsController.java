package trial.adidas.connections.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import trial.adidas.connections.model.Connection;
import trial.adidas.connections.services.ConnectionService;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/connections/v1")
public class ConnectionsController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "/{idConn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Connection getAConnection(@PathVariable("idConn")String idConn) throws InstanceNotFoundException {
        return connectionService.getAConnection(idConn);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Connection> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Connection createAConnection(@Validated @RequestBody Connection connection) throws InstanceAlreadyExistsException {
        return connectionService.createAConnection(connection);
    }

    @RequestMapping(value = "/{idConn}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Connection modifyAConnection(@Validated @RequestBody Connection connection, @PathVariable("idConn") String idConn)
            throws IllegalAccessException, InstanceNotFoundException {

        if (connection.getId() != null && !idConn.equals(connection.getId()))
            throw new IllegalAccessException();
        else connection.setId(idConn);

        return connectionService.modifyAConnection(connection);
    }

    @RequestMapping(value = "/{idConn}", method = RequestMethod.DELETE)
    public void deleteAConnection(@PathVariable("idConn") String idConn) throws InstanceNotFoundException {
        connectionService.deleteAConnection(idConn);
    }
}
