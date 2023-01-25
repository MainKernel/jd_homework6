package com.goit.homwork6.feature.client;

import com.goit.homwork6.feature.database.MigrationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ClientServiceTests {

    private ClientService service;
    private Connection connection;

    @BeforeEach
    public void beforeEach() throws SQLException {
        String connectionUrl = "jdbc:h2:mem:testdb";
        connection = DriverManager
                .getConnection(connectionUrl);
        new MigrationService().initUpdate(connectionUrl);
        service = new ClientService(connection);
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    public void testThatCreateWorks() throws SQLException {
        //Given
        long client = service.create("TeamGroup");

        //Expected
        long saved = service.getMaxId();

        //Assertions
        Assertions.assertEquals(client, saved);
    }

    @Test
    public void testThatGetByIdWorks() throws SQLException {
        //Given
        long teamGroup = service.create("TeamGroup");
        String name = service.getById(teamGroup);

        //Expected
        String clientName = "TeamGroup";

        //Assertions
        Assertions.assertEquals(clientName, name);
    }

    @Test
    public void testThatSetNameWorks() throws SQLException {
        //Given
        long teamGroup = service.create("TeamGroup");
        service.setName(teamGroup, "GlobalFoundation");
        String name = service.getById(teamGroup);

        // Expected
        String clientName = "GlobalFoundation";

        //Assertion
        Assertions.assertEquals(clientName, name);

    }
    @Test
    public void testThatDeleteByIdWorks() throws SQLException {
        //Given
        long teamGroup = service.create("TeamGroup");
        service.deleteById(teamGroup);
        long maxId = service.getIdByName("TeamGroup");

        //Assertion
        Assertions.assertEquals(0, maxId);
    }

/*
    @Test
    public void testThatSelectAllWorks() throws SQLException{
        //Given
        String clientName = "TeamGroup";
        long teamGroup = service.create(clientName);

        //Expected
        List<Client> clients = service.listAll();
        Client client = clients.get(0);
        long id = client.getId();
        String name = client.getName();

        //Assertion
        Assertions.assertEquals(teamGroup,id);
        Assertions.assertEquals(name, clientName);
    }

 */
}