package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private Connection connection;

    public void connection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokedex", "root", "certantsa");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
