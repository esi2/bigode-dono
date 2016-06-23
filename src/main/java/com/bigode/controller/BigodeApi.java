package main.java.com.bigode.controller;

import main.java.com.bigode.action.BigodeActions;
import main.java.com.bigode.exception.RequestProblemException;
import main.java.com.bigode.model.Mesa;
import main.java.com.bigode.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BigodeApi {

    @Autowired
    private BigodeActions bigodeActions = null;

    @RequestMapping(path = "/mesas", method = RequestMethod.POST)
    public List<Pedido> getListaPedidos(@RequestParam(value="list", required=true) String list) throws SQLException{
        return BigodeActions.getListaPedidos(list);
    }

    @RequestMapping(path = "/mesas/{numeroMesa}/pedidos", method = RequestMethod.GET)
    public Mesa getListaPedidosMesa(
            @PathVariable Long numeroMesa
    ){
        //TODO: Retornar lista de pedidos de uma mesa especifica
        return BigodeActions.getListaPedidosMesa(numeroMesa);
    }

    @RequestMapping(path = "/pedidos/{numeroPedido}", method = RequestMethod.GET)
    public Pedido getDetalhesPedido(
            @PathVariable Long numeroPedido
    ){
        //TODO: Retornar detalhes de um pedido especifico
        return BigodeActions.getDetalhesPedido(numeroPedido);
    }
    
    @RequestMapping(path = "/pedidos/{numeroPedido}/entregue", method = RequestMethod.GET)
    public void setPedidoEntregue(
            @PathVariable Long numeroPedido
    ) throws SQLException {
        BigodeActions.setPedidoEntregue(numeroPedido);
    }
    
    @RequestMapping(path = "/pedidos/{numeroPedido}/cancelpedido", method = RequestMethod.GET)
    public void setPedidoCancel(
            @PathVariable Long numeroPedido
    ) throws SQLException {
        BigodeActions.setPedidoCancel(numeroPedido);
    }
    
    @RequestMapping(path = "/pedidos/{numeroItemPedido}/cancel", method = RequestMethod.GET)
    public void setItemPedidoCancel(
            @PathVariable Long numeroItemPedido
    ) throws SQLException {
        BigodeActions.setItemPedidoCancel(numeroItemPedido);
    }

   

    @RequestMapping(path = "/pedidos/{numeroPedido}/pago", method = RequestMethod.GET)
    public void setPedidoPago(
            @PathVariable Long numeroPedido
    ) throws SQLException {
        BigodeActions.setPedidoPago(numeroPedido);
    }
    
    @RequestMapping(path = "/responsePedidos", method = RequestMethod.POST)
    public void setResponsePedidos(@RequestParam(value="list", required=true) String list) throws SQLException{
        BigodeActions.setResponsePedidos(list);
    }


    // TEMP -----------------------------------------------------------------------
    @RequestMapping(path = "/util/tabelas", method = RequestMethod.GET)
    public String getNomesTabelasMysql() throws SQLException {
        return BigodeActions.getMysqlTableNames();
    }

    @RequestMapping(path = "/util/pedidos", method = RequestMethod.GET)
    public String getPedidosMysql() throws SQLException {
        return BigodeActions.getPedidosTeste();
    }

    @RequestMapping(path = "/util/400", method = RequestMethod.GET)
    public String getErrorTest(){
        return BigodeActions.getErrorTest();
    }

    @RequestMapping(path = "/util/400/custom", method = RequestMethod.GET)
    public String getCustomErrorTest() throws RequestProblemException{
        return BigodeActions.getCustomErrorTest();
    }

}
