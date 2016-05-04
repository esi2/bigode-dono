package main.java.com.bigode.utils;

import com.google.gson.Gson;
import main.java.com.bigode.actions.BigodeActions;
import main.java.com.bigode.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
class BigodeAPI {

    @Autowired
    private BigodeActions bigodeActions = null;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String teste123(){
        return "Funfou!";
    }

    @RequestMapping(path = "/test2", method = RequestMethod.GET)
    public String teste456(){
        return BigodeActions.getMysqlTableNames();
    }

    @RequestMapping(path = "/test3", method = RequestMethod.GET)
    public String teste789(){
        return BigodeActions.getPedidosTeste();
    }

}
