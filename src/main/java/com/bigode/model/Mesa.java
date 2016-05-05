package main.java.com.bigode.model;

import java.util.Collections;
import java.util.List;

public class Mesa {

    private int numeroMesa;
    private List<Pedido> pedidos;

    public Mesa(){
        pedidos = Collections.emptyList();
    }

    public Mesa(int numeroMesa, List<Pedido> pedidos){
        this.numeroMesa = numeroMesa;
        this.pedidos = pedidos;
    }
}
