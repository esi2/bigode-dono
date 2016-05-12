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
        @JsonProperty private long item;
        @JsonProperty private long qtd;

        public ItemPedido(long item, long qtd){
            this.item = item;
            this.qtd = qtd;
        }
        public long getItem(){ return item; }
        public long getQtd(){ return qtd; }
        public void setItem(long item){ this.item = item; }
        public void setQtd(long qtd){ this.qtd = qtd; }
    }
}
