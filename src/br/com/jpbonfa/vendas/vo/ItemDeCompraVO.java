
package br.com.jpbonfa.vendas.vo;

import br.com.jpbonfa.vendas.model.Produto;

/**
 *
 * @author joaop
 */
public class ItemDeCompraVO {

    private int quantidade;
    private double valorTotal;
    private Produto produto;

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

}
