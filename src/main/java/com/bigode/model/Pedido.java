package main.java.com.bigode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Pedido {
    @JsonProperty private List<ItemPedido> itens;

    public Pedido(){}

    public Pedido(List<ItemPedido> itens){
        this.itens = itens;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static class ItemPedido {
        @JsonProperty private long id;
        @JsonProperty private String nome;
        @JsonProperty private double preco;
        @JsonProperty private String foto;
        @JsonProperty private long qtd;

        public ItemPedido(long id, String nome, double preco, String foto, long qtd) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.foto = foto;
            this.qtd = qtd;
        }

        public long getId() {return id;}

        public String getNome() {return nome;}

        public double getPreco() {return preco;}

        public String getFoto() {return foto;}

        public long getQtd() {return qtd;}

    }
}
