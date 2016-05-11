package main.java.com.bigode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Pedido {
    @JsonProperty private List<ItemPedido<String, Long>> itens;

    public Pedido(){}

    public Pedido(List<ItemPedido<String, Long>> itens){
        this.itens = itens;
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static class ItemPedido<Item,Qtd> {
        @JsonProperty private Item item;
        @JsonProperty private Qtd qtd;

        public ItemPedido(Item item, Qtd qtd){
            this.item = item;
            this.qtd = qtd;
        }
        public Item getItem(){ return item; }
        public Qtd getQtd(){ return qtd; }
        public void setItem(Item item){ this.item = item; }
        public void setQtd(Qtd qtd){ this.qtd = qtd; }
    }
}
