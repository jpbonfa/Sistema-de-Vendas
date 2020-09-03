package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.PessoaFisica;
import br.com.jpbonfa.vendas.model.PessoaJuridica;
import br.com.jpbonfa.vendas.model.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Endereco> enderecoIdEndereco;
    public static volatile SingularAttribute<Cliente, String> tipoPessoa;
    public static volatile SingularAttribute<Cliente, PessoaJuridica> pessoaJuridicaIdPessoaJuridica;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile ListAttribute<Cliente, Venda> vendaList;
    public static volatile SingularAttribute<Cliente, Contato> contatoIdContato;
    public static volatile SingularAttribute<Cliente, PessoaFisica> pessoaFisicaIdPessoaFisica;

}