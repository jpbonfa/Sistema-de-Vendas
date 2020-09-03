package br.com.jpbonfa.vendas.vo;

import br.com.jpbonfa.vendas.model.Produto;
import br.com.jpbonfa.vendas.model.Venda;

/**
 *
 * @author joaop
 */
public class ItemDeVendaVO {

    private int quantidade;
    private double valorTotal;
    private Produto produto;
    private double desconto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

}
