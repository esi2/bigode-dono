package main.java.com.bigode.model;

import java.util.Collections;
import java.util.List;

public class Mesa {

    private List<Pedido> pedidos;

    public Mesa(){
        pedidos = Collections.emptyList();
    }

    public Mesa(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }
}
