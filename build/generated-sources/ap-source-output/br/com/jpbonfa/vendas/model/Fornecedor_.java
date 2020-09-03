package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Compra;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.PessoaJuridica;
import br.com.jpbonfa.vendas.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, Endereco> enderecoIdEndereco;
    public static volatile SingularAttribute<Fornecedor, Integer> idFornecedor;
    public static volatile SingularAttribute<Fornecedor, PessoaJuridica> pessoaJuridicaIdPessoaJuridica;
    public static volatile ListAttribute<Fornecedor, Produto> produtoList;
    public static volatile ListAttribute<Fornecedor, Compra> compraList;
    public static volatile SingularAttribute<Fornecedor, Contato> contatoIdContato;
    public static volatile SingularAttribute<Fornecedor, String> contato;

}