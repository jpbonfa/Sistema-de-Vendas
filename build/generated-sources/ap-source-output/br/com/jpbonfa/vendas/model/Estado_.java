package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Cidade;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile ListAttribute<Estado, Cidade> cidadeList;
    public static volatile SingularAttribute<Estado, String> uf;
    public static volatile SingularAttribute<Estado, Integer> idEstado;
    public static volatile SingularAttribute<Estado, String> nome;

}