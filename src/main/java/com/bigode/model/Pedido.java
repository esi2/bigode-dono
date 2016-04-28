package main.java.com.bigode.model;

import java.util.List;

public class Pedido {
    private List<ItemPedido<String, Long>> itens;

    public Pedido(){}

    public Pedido(List<ItemPedido<String, Long>> itens){
        this.itens = itens;
    }

    public static class ItemPedido<Item,Qtd> {
        private Item item;
        private Qtd qtd;

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
