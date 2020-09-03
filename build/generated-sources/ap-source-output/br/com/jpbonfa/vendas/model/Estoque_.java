package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Estoque.class)
public class Estoque_ { 

    public static volatile SingularAttribute<Estoque, Integer> idEstoque;
    public static volatile SingularAttribute<Estoque, Integer> quantidadeMinima;
    public static volatile SingularAttribute<Estoque, Integer> quantidadeEstoque;
    public static volatile SingularAttribute<Estoque, Produto> produtoIdProduto;

}