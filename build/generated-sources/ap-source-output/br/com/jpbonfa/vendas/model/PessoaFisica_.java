package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(PessoaFisica.class)
public class PessoaFisica_ { 

    public static volatile ListAttribute<PessoaFisica, Cliente> clienteList;
    public static volatile ListAttribute<PessoaFisica, Funcionario> funcionarioList;
    public static volatile SingularAttribute<PessoaFisica, String> rg;
    public static volatile SingularAttribute<PessoaFisica, Integer> idPessoaFisica;
    public static volatile SingularAttribute<PessoaFisica, String> cpf;
    public static volatile SingularAttribute<PessoaFisica, String> nome;
    public static volatile SingularAttribute<PessoaFisica, String> dataNascimento;

}