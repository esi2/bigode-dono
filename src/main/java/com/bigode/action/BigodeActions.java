package main.java.com.bigode.action;

import main.java.com.bigode.exception.RequestProblemException;
import main.java.com.bigode.model.Mesa;
import main.java.com.bigode.model.Pedido;
import main.java.com.bigode.util.JDBCConnection;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.text.NumberFormat;   

@Component
public class BigodeActions {
    private final int idBar = 1;
    private static Connection conn;

    public static List<Pedido> getListaPedidos() throws SQLException {
        List<Pedido> response = new ArrayList<>();
        Statement statement;

        long indicePedido = -1;
        long indiceMesa = -1;
        long indiceSessao = -1;
        String statusPedido = "";
        double calcTotal = 0;
        String total = "";
        DecimalFormat df = new DecimalFormat("#0.00");

        List<Pedido.ItemPedido> itemPedidoList = new ArrayList<>();

        try {
            conn = JDBCConnection.getJdbcInstance().connect();

            String query =
                    "SELECT (PRODUTO.PRECO_PRODUTO * PRODUTO_PEDIDO.QUANTIDADE) AS TOTAL,"+
                    "PEDIDO.ID_PEDIDO, PEDIDO.STATUS_PEDIDO, MESA.ID_BAR, MESA.ID_MESA, " +
                    "MESA.NUM_MESA, MESA.STATUS_MESA, SESSAO.ID_SESSAO, SESSAO.STATUS_SESSAO, " +
                    "PRODUTO_PEDIDO.ID_PRODUTO, PRODUTO_PEDIDO.QUANTIDADE, PRODUTO.NOME_PRODUTO, " +
                    "PRODUTO.PRECO_PRODUTO, PRODUTO.FOTO_PRODUTO FROM PEDIDO " +
                    "LEFT JOIN MESA ON PEDIDO.ID_MESA = MESA.ID_MESA " +
                    "LEFT JOIN SESSAO ON PEDIDO.ID_SESSAO = SESSAO.ID_SESSAO " +
                    "LEFT JOIN PRODUTO_PEDIDO ON PEDIDO.ID_PEDIDO = PRODUTO_PEDIDO.ID_PEDIDO " +
                    "LEFT JOIN PRODUTO ON PRODUTO_PEDIDO.ID_PRODUTO = PRODUTO.ID_PRODUTO " +

                    "WHERE STATUS_SESSAO = 'ATIVA' AND STATUS_PEDIDO != 'PAGO' AND MESA.ID_BAR = 1 " +
                    "ORDER BY PEDIDO.ID_PEDIDO DESC"
                    ;

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                long numPedidoAtual = Long.parseLong(resultSet.getString("ID_PEDIDO"));
                long numMesaAtual = Long.parseLong(resultSet.getString("ID_MESA"));
                long numSessaoAtual = Long.parseLong(resultSet.getString("ID_SESSAO"));
                String statusPedidoAtual = resultSet.getString("STATUS_PEDIDO");

                if(resultSet.getRow() == 1){
                    indicePedido = numPedidoAtual;
                    indiceMesa = numMesaAtual;
                    indiceSessao = numSessaoAtual;
                    statusPedido = statusPedidoAtual;
                }

                if(indicePedido != numPedidoAtual && itemPedidoList.size() > 0) {
                    total = df.format(calcTotal);
                    Pedido pedido = new Pedido(indicePedido, indiceMesa, indiceSessao, itemPedidoList, statusPedido, total);
                    response.add(pedido);
                    calcTotal = 0;
                    total = "";
                    itemPedidoList.clear();

                    indicePedido = numPedidoAtual;
                    indiceMesa = numMesaAtual;
                    indiceSessao = numSessaoAtual;
                    statusPedido = statusPedidoAtual;

                }


                Pedido.ItemPedido itemPedido =
                        new Pedido.ItemPedido(
                                Long.parseLong(resultSet.getString("ID_PRODUTO")),
                                resultSet.getString("NOME_PRODUTO"),
                                df.format(Double.parseDouble(resultSet.getString("PRECO_PRODUTO"))),
                                Long.parseLong(resultSet.getString("QUANTIDADE")));
                itemPedidoList.add(itemPedido);
                calcTotal += Double.parseDouble(resultSet.getString("TOTAL"));

            }

            //fechando ultima mesa
            if(itemPedidoList.size() > 0) {
                total = df.format(calcTotal);
                Pedido pedidoFinal = new Pedido(indicePedido, indiceMesa, indiceSessao, itemPedidoList, statusPedido, total);
                response.add(pedidoFinal);
                itemPedidoList.clear();

                indicePedido = -1;
                indiceMesa = -1;
                indiceSessao = -1;
                statusPedido = "";
                calcTotal = 0;
                total = "";
            }
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
        }
        return response;
    }

    public static List<Pedido> getSessoesAEncerrar() throws SQLException {
        List<Pedido> response = new ArrayList<>();
        Statement statement;

        long indicePedido = -1;
        long indiceMesa = -1;
        long indiceSessao = -1;
        String statusPedido = "";
        double calcTotal = 0;
        String total = "";
        DecimalFormat df = new DecimalFormat("#0.00");

        List<Pedido.ItemPedido> itemPedidoList = new ArrayList<>();

        try {
            conn = JDBCConnection.getJdbcInstance().connect();

            String query =
                    "SELECT (PRODUTO.PRECO_PRODUTO * PRODUTO_PEDIDO.QUANTIDADE) AS TOTAL,"+
                    "PEDIDO.ID_PEDIDO, PEDIDO.STATUS_PEDIDO, MESA.ID_BAR, MESA.ID_MESA, " +
                    "MESA.NUM_MESA, MESA.STATUS_MESA, SESSAO.ID_SESSAO, SESSAO.STATUS_SESSAO, " +
                    "PRODUTO_PEDIDO.ID_PRODUTO, PRODUTO_PEDIDO.QUANTIDADE, PRODUTO.NOME_PRODUTO, " +
                    "PRODUTO.PRECO_PRODUTO, PRODUTO.FOTO_PRODUTO FROM PEDIDO " +
                    "LEFT JOIN MESA ON PEDIDO.ID_MESA = MESA.ID_MESA " +
                    "LEFT JOIN SESSAO ON PEDIDO.ID_SESSAO = SESSAO.ID_SESSAO " +
                    "LEFT JOIN PRODUTO_PEDIDO ON PEDIDO.ID_PEDIDO = PRODUTO_PEDIDO.ID_PEDIDO " +
                    "LEFT JOIN PRODUTO ON PRODUTO_PEDIDO.ID_PRODUTO = PRODUTO.ID_PRODUTO " +

                    "WHERE STATUS_SESSAO = 'PAGAMENTO' AND STATUS_PEDIDO != 'PAGO' AND MESA.ID_BAR = 1 " +
                    "AND PRODUTO.ID_PRODUTO IS NOT NULL AND PRODUTO_PEDIDO.QUANTIDADE IS NOT NULL " +
                    "ORDER BY MESA.ID_MESA DESC, SESSAO.ID_SESSAO ASC"
                    ;

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                long numMesaAtual = Long.parseLong(resultSet.getString("ID_MESA"));
                long numSessaoAtual = Long.parseLong(resultSet.getString("ID_SESSAO"));
                String statusPedidoAtual = resultSet.getString("STATUS_PEDIDO");

                if(resultSet.getRow() == 1){
                    indiceMesa = numMesaAtual;
                    indiceSessao = numSessaoAtual;
                    statusPedido = statusPedidoAtual;
                }

                if(indiceMesa != numMesaAtual && itemPedidoList.size() > 0) {
                    total = df.format(calcTotal);
                    Pedido pedido = new Pedido(-1L, indiceMesa, indiceSessao, itemPedidoList, statusPedido, total);
                    response.add(pedido);
                    calcTotal = 0;
                    total = "";
                    itemPedidoList.clear();
                    
                    indiceMesa = numMesaAtual;
                    indiceSessao = numSessaoAtual;
                    statusPedido = statusPedidoAtual;

                }

                Pedido.ItemPedido itemPedido =
                        new Pedido.ItemPedido(
                                Long.parseLong(resultSet.getString("ID_PRODUTO")),
                                resultSet.getString("NOME_PRODUTO"),
                                df.format(Double.parseDouble(resultSet.getString("PRECO_PRODUTO"))),
                                Long.parseLong(resultSet.getString("QUANTIDADE")));
                itemPedidoList.add(itemPedido);
                calcTotal += Double.parseDouble(resultSet.getString("TOTAL"));

            }

            //fechando ultima mesa
            if(itemPedidoList.size() > 0) {
                total = df.format(calcTotal);
                Pedido pedidoFinal = new Pedido(-1L, indiceMesa, indiceSessao, itemPedidoList, statusPedido, total);
                response.add(pedidoFinal);
                itemPedidoList.clear();

                indicePedido = -1;
                indiceMesa = -1;
                indiceSessao = -1;
                statusPedido = "";
                calcTotal = 0;
                total = "";
            }
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
        }
        return response;
    }

    public static void setPedidoEntregue(Long idPedido) throws SQLException {
        Statement statement;

        try {
            conn = JDBCConnection.getJdbcInstance().connect();

            String query = "UPDATE PEDIDO " +
                    "SET PEDIDO.STATUS_PEDIDO = 'ENTREGUE'" +
                    "WHERE PEDIDO.ID_PEDIDO = " + idPedido;

            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
        }
    }

    public static void setSessaoPaga(Long idSessao) throws SQLException {
        Statement statement;

        try {
            conn = JDBCConnection.getJdbcInstance().connect();

            String query = "UPDATE PEDIDO " +
                    "SET PEDIDO.STATUS_PEDIDO = 'PAGO'" +
                    "WHERE PEDIDO.ID_SESSAO = " + idSessao;

            statement = conn.createStatement();
            statement.executeUpdate(query);

            String query2 = "UPDATE SESSAO " +
                    "SET SESSAO.STATUS_SESSAO = 'ENCERRADA'" +
                    "WHERE SESSAO.ID_SESSAO = " + idSessao;

            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("[Erro] " + e.toString());
        } finally {
            conn.close();
        }
    }

    public static Mesa getListaPedidosMesa(Long numeroMesa){
        Mesa response = new Mesa();
        Statement statement;

        //TODO
        return response;
    }

    public static Pedido getDetalhesPedido(Long numeroPedido){
        Pedido response = new Pedido();
        Statement statement;

        //TODO
        return response;
    }

    public static String getMysqlTableNames() throws SQLException {
        String response = "";
        Statement statement;

        try {
            Connection conn = JDBCConnection.getJdbcInstance().connect();

            String query = "SELECT table_name FROM information_schema.tables WHERE TABLE_SCHEMA LIKE 'BIGODE%'";

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                response +=  resultSet.getNString(1) + "|";
            }

            conn.close();
        } catch (Exception e) {
            return "[Erro] " + e.toString();
        } finally {
            conn.close();
        }

        return response;
    }

    public static String getPedidosTeste() throws SQLException {
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
        } finally {
            conn.close();
        }

        return response;
    }

    public static String getErrorTest(){
        throw new IllegalArgumentException();
    }

    public static String getCustomErrorTest() throws RequestProblemException{
        throw new RequestProblemException();
    }
}
