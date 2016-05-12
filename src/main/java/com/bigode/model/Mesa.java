package main.java.com.bigode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Mesa {

    @JsonProperty private int numeroMesa;
    @JsonProperty private List<Pedido> pedidos;

    public Mesa(){
        pedidos = Collections.emptyList();
    }

    public Mesa(int numeroMesa, List<Pedido> pedidos){
        this.numeroMesa = numeroMesa;
        this.pedidos = pedidos;
    }

    public int getNumeroMesa(){
        return numeroMesa;
    }

    public List<Pedido> getPedidos(){
        return pedidos;
    }
}
