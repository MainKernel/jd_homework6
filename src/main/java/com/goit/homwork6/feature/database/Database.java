package com.goit.homwork6.feature.database;

import com.goit.homwork6.feature.pref.Preferences;

import java.sql.*;

public class Database {
    private static final Database instance = new Database();
    private Connection connection;

    private Database(){
        try {
            connection = DriverManager.getConnection(
                    new Preferences().getString(Preferences.DATABASE_URL)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public static Database getInstance(){
        return instance;
    }

    public boolean executeUpdate(String sql){
        try {
            Statement st = connection.createStatement();
            return st.executeUpdate(sql) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet executeQuery(String sql){
        try {
            Statement st = connection.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
