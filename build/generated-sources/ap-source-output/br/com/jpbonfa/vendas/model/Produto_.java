package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Estoque;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.ItemCompra;
import br.com.jpbonfa.vendas.model.ItemVenda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile ListAttribute<Produto, ItemCompra> itemCompraList;
    public static volatile SingularAttribute<Produto, Fornecedor> fornecedorIdFornecedor;
    public static volatile ListAttribute<Produto, Estoque> estoqueList;
    public static volatile SingularAttribute<Produto, Double> valorCusto;
    public static volatile SingularAttribute<Produto, Integer> idProduto;
    public static volatile SingularAttribute<Produto, Double> valorVenda;
    public static volatile ListAttribute<Produto, ItemVenda> itemVendaList;
    public static volatile SingularAttribute<Produto, String> descricao;

}