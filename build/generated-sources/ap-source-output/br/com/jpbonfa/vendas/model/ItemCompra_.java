package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Compra;
import br.com.jpbonfa.vendas.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(ItemCompra.class)
public class ItemCompra_ { 

    public static volatile SingularAttribute<ItemCompra, Integer> idItemCompra;
    public static volatile SingularAttribute<ItemCompra, Integer> quantidadeProduto;
    public static volatile SingularAttribute<ItemCompra, Compra> compraIdCompra;
    public static volatile SingularAttribute<ItemCompra, Double> valorTotal;
    public static volatile SingularAttribute<ItemCompra, Produto> produtoIdProduto;

}