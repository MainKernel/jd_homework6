package com.goit.homwork6.feature.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement create;
    private PreparedStatement getMaxID;
    private PreparedStatement getById;
    private PreparedStatement getByName;
    private PreparedStatement setName;
    private PreparedStatement deleteById;
    private PreparedStatement listAll;

    public ClientService(Connection connection) {
        try {
            this.create = connection.prepareStatement(
                    "INSERT INTO CLIENT (NAME) VALUES ( ? ) "
            );
            this.getById = connection.prepareStatement(
                    "SELECT NAME FROM CLIENT WHERE ID = ?"
            );
            this.setName = connection.prepareStatement(
                    "UPDATE CLIENT SET NAME = ? WHERE ID = ?"
            );
            this.deleteById = connection.prepareStatement(
                    "DELETE FROM CLIENT WHERE ID = ?"
            );
            this.listAll = connection.prepareStatement(
                    "SELECT ID, NAME from CLIENT"
            );
            this.getMaxID = connection.prepareStatement(
                    "SELECT MAX(ID) AS ID FROM CLIENT"
            );
            this.getByName = connection.prepareStatement(
                    "SELECT ID FROM CLIENT WHERE NAME = ?"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) throws SQLException {
        if (name.length() < 2 || name.length() > 1000) {
            throw new SQLException("Invalid name length");
        }
        try {
            create.setString(1, name);
            int i = create.executeUpdate();

            if (i == 1) {
                return getMaxId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Something went with database");
    }

    public String getById(long id) {
        try {
            getById.setLong(1, id);
            ResultSet name = getById.executeQuery();
            name.next();
            return name.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setName(long id, String name) {
        try {
            setName.setString(1, name);
            setName.setLong(2, id);
            setName.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            deleteById.setLong(1, id);
            deleteById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        ResultSet rs = listAll.executeQuery();
        while (rs.next()) {
            Client cl = new Client();

            cl.setId(rs.getLong("ID"));
            cl.setName(rs.getString("NAME"));
            clients.add(cl);
        }
        return clients;
    }

    public long getMaxId() throws SQLException {
        ResultSet set = getMaxID.executeQuery();
        set.next();
        return set.getLong("ID");
    }

    public long getIdByName(String name) throws SQLException {
        getByName.setString(1, name);
        ResultSet set = getByName.executeQuery();
        long id = 0;

        try {
            set.next();
            id = set.getLong("ID");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;

    }
}
