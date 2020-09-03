package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Compra;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.LogUsuario;
import br.com.jpbonfa.vendas.model.PessoaFisica;
import br.com.jpbonfa.vendas.model.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, Endereco> enderecoIdEndereco;
    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile ListAttribute<Funcionario, Venda> vendaList;
    public static volatile ListAttribute<Funcionario, LogUsuario> logUsuarioList;
    public static volatile SingularAttribute<Funcionario, Integer> idFuncionario;
    public static volatile SingularAttribute<Funcionario, String> login;
    public static volatile ListAttribute<Funcionario, Compra> compraList;
    public static volatile SingularAttribute<Funcionario, Contato> contatoIdContato;
    public static volatile SingularAttribute<Funcionario, PessoaFisica> pessoaFisicaIdPessoaFisica;

}