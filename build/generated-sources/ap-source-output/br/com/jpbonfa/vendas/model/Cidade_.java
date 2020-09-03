package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile ListAttribute<Cidade, Endereco> enderecoList;
    public static volatile SingularAttribute<Cidade, Integer> idCidade;
    public static volatile SingularAttribute<Cidade, String> nome;
    public static volatile SingularAttribute<Cidade, Estado> estadoIdEstado;

}