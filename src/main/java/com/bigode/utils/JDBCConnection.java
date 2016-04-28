package main.java.com.bigode.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private final String connUrl = "jdbc:mysql://localhost:3306/BIGODE";
    private final String username = "root";
    private final String password = "EACHesi2016!";
    private static JDBCConnection jdbcInstance = null;
    private Connection connection = null;

    private JDBCConnection(){}

    public static JDBCConnection getJdbcInstance() {
        if(jdbcInstance == null){
            jdbcInstance = new JDBCConnection();
        }

        return jdbcInstance;
    }

    public Connection connect(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connUrl, username, password);
        }
        catch (SQLException jdbcEx){
            jdbcEx.printStackTrace();
        }
        catch (ClassNotFoundException classEx){
            classEx.printStackTrace();
        }

        return connection;
    }

    public void close() {

        try {
            connection.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            connection = null;
        }
    }

    public Boolean isConnected(){
        return (connection != null);
    }
}
