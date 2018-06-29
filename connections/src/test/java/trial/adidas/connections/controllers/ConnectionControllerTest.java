package trial.adidas.connections.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import trial.adidas.connections.model.Connection;
import trial.adidas.connections.services.ConnectionService;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConnectionsController.class)
public class ConnectionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConnectionService connectionService;

    @Before
    public void setUp() throws InstanceNotFoundException, InstanceAlreadyExistsException {
        Connection connection = new Connection();
        connection.setId("1");
        connection.setCity("A");
        connection.setDestination("B");
        connection.setDepartureTime(LocalTime.MIDNIGHT);
        connection.setArrivalTime(LocalTime.of(10,30,00));

        given(this.connectionService.getAConnection("1")).willReturn(connection);
        given(this.connectionService.getAConnection("10")).willThrow(new InstanceNotFoundException());

        given(this.connectionService.getAllConnections()).willReturn(new ArrayList<>());

        given(this.connectionService.createAConnection(any(Connection.class))).willReturn(connection);

        given(this.connectionService.modifyAConnection(any(Connection.class))).willReturn(connection);
    }

    @Test
    public void getAConnection() throws Exception {
        this.mockMvc.perform(get("/connections/v1/1")).andExpect(status().isOk()).andExpect(content().json("{ id: '1'}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAInexistentConnection() throws Exception {
        this.mockMvc.perform(get("/connections/v1/10")).andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllConnection() throws Exception {
        this.mockMvc.perform(get("/connections/v1")).andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createAConnection() throws Exception {

        mockMvc.perform(post("/connections/v1").contentType(MediaType.APPLICATION_JSON).content
                ("{\"city\": \"A\",\"destination\": \"B\",\"departureTime\":\"00:00:00\",\"arrivalTime\":\"10:30:00\"}"))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createAIncompleteConnection() throws Exception {

        mockMvc.perform(post("/connections/v1").contentType(MediaType.APPLICATION_JSON).content
                ("{\"city\": \"A\",\"destination\": \"B\",\"departureTime\":\"00:00:00\"}"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyAConnection() throws Exception {

        mockMvc.perform(put("/connections/v1/1").contentType(MediaType.APPLICATION_JSON).content
                ("{\"city\": \"A\",\"destination\": \"B\",\"departureTime\":\"00:00:00\",\"arrivalTime\":\"10:30:00\"}"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void modifyAIncompleteConnection() throws Exception {

        mockMvc.perform(put("/connections/v1/1").contentType(MediaType.APPLICATION_JSON).content
                ("{\"city\": \"A\",\"destination\": \"B\",\"departureTime\":\"00:00:00\"}"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

}
