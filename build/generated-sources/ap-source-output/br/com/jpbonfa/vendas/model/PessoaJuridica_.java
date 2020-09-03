package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Fornecedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(PessoaJuridica.class)
public class PessoaJuridica_ { 

    public static volatile ListAttribute<PessoaJuridica, Cliente> clienteList;
    public static volatile SingularAttribute<PessoaJuridica, String> inscricaoEstadual;
    public static volatile ListAttribute<PessoaJuridica, Fornecedor> fornecedorList;
    public static volatile SingularAttribute<PessoaJuridica, String> cnpj;
    public static volatile SingularAttribute<PessoaJuridica, String> razaoSocial;
    public static volatile SingularAttribute<PessoaJuridica, Integer> idPessoaJuridica;
    public static volatile SingularAttribute<PessoaJuridica, String> dataFundacao;

}