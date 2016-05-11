package main.java.com.bigode.action;

import com.fasterxml.jackson.annotation.JsonFormat;
import main.java.com.bigode.exception.RequestProblemException;
import main.java.com.bigode.model.Mesa;
import main.java.com.bigode.model.Pedido;
import main.java.com.bigode.util.JDBCConnection;
import org.junit.runner.Result;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BigodeActions {
    private static final int idBar = 0;
    private static Connection conn;

    public static long checkQuery() throws SQLException {
        Statement statement;

        try{
            conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT * FROM PEDIDO";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.last();
            return (long)resultSet.getRow();
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
        }

        return -1L;
    }

    public static List<Mesa> getListaPedidos() throws SQLException {
        List<Mesa> response = new ArrayList<>();
        Statement statement;

        int indiceMesa = -1;
        List<Pedido> pedidoLista = new ArrayList<>();

        try {
            conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT * FROM PEDIDO WHERE PEDIDO.STATUS_PEDIDO LIKE 'ativo'";

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int numMesaAtual = Integer.parseInt(resultSet.getString("NUM_MESA"));

                if(resultSet.getRow() == 1){
                    indiceMesa = numMesaAtual;
                }

                if(indiceMesa != numMesaAtual && pedidoLista.size() > 0) {
                    response.add(new Mesa(indiceMesa, pedidoLista));
                    pedidoLista.clear();
                    indiceMesa = numMesaAtual;
                }

                List<Pedido.ItemPedido> listaItem = new ArrayList<>();
                Pedido.ItemPedido itemPedido = new Pedido.ItemPedido(Long.parseLong(resultSet.getString("ID_PRODUTO")),
                        Long.parseLong(resultSet.getString("QUANTIDADE")));
                listaItem.add(itemPedido);

                Pedido pedido = new Pedido(listaItem);
                pedidoLista.add(pedido);

            }

            //fechando ultima mesa
            response.add(new Mesa(indiceMesa, pedidoLista));
            pedidoLista.clear();
            indiceMesa = -1;
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
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

    public static void criaPedido(Long numeroMesa){
        Statement statement;

        try {
            Connection conn = JDBCConnection.getJdbcInstance().connect();
            String query = "INSERT INTO PEDIDO(ID_PEDIDO, NUM_MESA, ID_PRODUTO, QUANTIDADE, STATUS_PEDIDO)" +
                    "VALUES (NULL, "+numeroMesa+", 1, 3, 'PENDENTE')";

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        }
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

    public static String getErrorTest(){
        throw new IllegalArgumentException("damn");
    }

    public static String getCustomErrorTest() throws RequestProblemException{
        throw new RequestProblemException("custom damn");
    }
}
