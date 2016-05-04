package main.java.com.bigode.actions;

import main.java.com.bigode.model.Mesa;
import main.java.com.bigode.model.Pedido;
import main.java.com.bigode.utils.JDBCConnection;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BigodeActions {
    private static final int idBar = 0;

    public static List<Mesa> getListaPedidos(){
        List<Mesa> response = new ArrayList<>();
        Statement statement;

        try {
            Connection conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT * FROM PEDIDOS WHERE STATUS LIKE \"PENDENTE\" AND ID_BAR="+ idBar;

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                response.add(new Mesa());
            }
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        }
        return response;
    }

    public static Mesa getListaPedidosMesa(Long numeroMesa){
        Mesa response = new Mesa();
        Statement statement;

        //TODO
        return response;
    }

    public static Pedido getDetalhesPedido(Long numeroMesa, Long numeroPedido){
        Pedido response = new Pedido();
        Statement statement;

        //TODO
        return response;
    }

    public static String getMysqlTableNames(){
        String response = "";
        Statement statement;

        try {
            Connection conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT table_name FROM information_schema.tables WHERE TABLE_SCHEMA LIKE 'BIGODE'";

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                response +=  resultSet.getNString(1) + "|";
            }

            conn.close();
        } catch (Exception e) {
            return "[Erro] " + e.toString();
        }

        return response;
    }

    public static String getPedidosTeste(){
        String response = "";
        Statement statement;

        try {
            Connection conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT * FROM PEDIDO";

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(!resultSet.next()){
                return "There were no available results to the specified query";
            }

            while (resultSet.next()){
                response +=  resultSet.getNString(1) + "\n";
            }

            conn.close();
        } catch (Exception e) {
            return "[Erro] " + e.toString();
        }

        return response;
    }
}
