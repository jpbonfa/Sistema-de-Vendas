package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Produto;
import br.com.jpbonfa.vendas.model.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(ItemVenda.class)
public class ItemVenda_ { 

    public static volatile SingularAttribute<ItemVenda, Integer> idItemVenda;
    public static volatile SingularAttribute<ItemVenda, Integer> quantidadeProduto;
    public static volatile SingularAttribute<ItemVenda, Double> descontoProduto;
    public static volatile SingularAttribute<ItemVenda, Double> valorTotal;
    public static volatile SingularAttribute<ItemVenda, Venda> vendaIdVenda;
    public static volatile SingularAttribute<ItemVenda, Produto> produtoIdProduto;

}