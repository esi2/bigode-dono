package main.java.com.bigode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Pedido {
    @JsonProperty private long idPedido;
    @JsonProperty private long idMesa;
    @JsonProperty private long idSessao;
    @JsonProperty private List<ItemPedido> itens;
    @JsonProperty private String status;
    @JsonProperty private long total;

    public Pedido(){}

    public Pedido(Long idPedido, Long idMesa, Long idSessao, List<ItemPedido> itens, String status, long total){
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idSessao = idSessao;
        this.itens = new ArrayList(itens);
        this.status = status;
        this.total = total;
    }

    public long getIdPedido() { return idPedido; }

    public long getIdMesa() { return idMesa; }

    public long getIdSessao() { return idSessao; }

    public String getStatus() {return status;}

    public List<ItemPedido> getItens() {
        return itens;
    }

    public String getTotal() {return total;}

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static class ItemPedido {
        @JsonProperty private long id;
        @JsonProperty private String nome;
        @JsonProperty private double preco;
        @JsonProperty private long qtd;

        public ItemPedido(long id, String nome, double preco, long qtd) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.qtd = qtd;
        }

        public long getId() {return id;}

        public String getNome() {return nome;}

        public double getPreco() {return preco;}

        public long getQtd() {return qtd;}
    }
}
