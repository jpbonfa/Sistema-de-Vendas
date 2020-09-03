package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.ContasPagar;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.model.ItemCompra;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, String> formaPagamento;
    public static volatile SingularAttribute<Compra, Fornecedor> fornecedorIdFornecedor;
    public static volatile ListAttribute<Compra, ItemCompra> itemCompraList;
    public static volatile SingularAttribute<Compra, Integer> idCompra;
    public static volatile SingularAttribute<Compra, Double> valorTotal;
    public static volatile SingularAttribute<Compra, Funcionario> funcionarioIdFuncionario;
    public static volatile ListAttribute<Compra, ContasPagar> contasPagarList;
    public static volatile SingularAttribute<Compra, String> dataCompra;

}