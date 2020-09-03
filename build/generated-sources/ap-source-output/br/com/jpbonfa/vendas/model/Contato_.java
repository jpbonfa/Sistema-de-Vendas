package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Contato.class)
public class Contato_ { 

    public static volatile ListAttribute<Contato, Cliente> clienteList;
    public static volatile SingularAttribute<Contato, String> telefone;
    public static volatile ListAttribute<Contato, Funcionario> funcionarioList;
    public static volatile SingularAttribute<Contato, Integer> idContato;
    public static volatile SingularAttribute<Contato, String> celular;
    public static volatile ListAttribute<Contato, Fornecedor> fornecedorList;
    public static volatile SingularAttribute<Contato, String> email;

}