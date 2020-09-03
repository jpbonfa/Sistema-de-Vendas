package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cidade;
import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile ListAttribute<Endereco, Cliente> clienteList;
    public static volatile ListAttribute<Endereco, Funcionario> funcionarioList;
    public static volatile SingularAttribute<Endereco, String> complemento;
    public static volatile SingularAttribute<Endereco, String> endereco;
    public static volatile SingularAttribute<Endereco, Integer> numero;
    public static volatile SingularAttribute<Endereco, Integer> idEndereco;
    public static volatile SingularAttribute<Endereco, String> bairro;
    public static volatile ListAttribute<Endereco, Fornecedor> fornecedorList;
    public static volatile SingularAttribute<Endereco, Cidade> cidadeIdCidade;
    public static volatile SingularAttribute<Endereco, String> cep;

}