package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.ContasReceber;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.model.ItemVenda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, String> formaPagamento;
    public static volatile SingularAttribute<Venda, String> dataVenda;
    public static volatile SingularAttribute<Venda, Double> valorTotal;
    public static volatile SingularAttribute<Venda, Funcionario> funcionarioIdFuncionario;
    public static volatile SingularAttribute<Venda, Cliente> clienteIdCliente;
    public static volatile ListAttribute<Venda, ItemVenda> itemVendaList;
    public static volatile ListAttribute<Venda, ContasReceber> contasReceberList;
    public static volatile SingularAttribute<Venda, Integer> idVenda;

}