package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Compra;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(ContasPagar.class)
public class ContasPagar_ { 

    public static volatile SingularAttribute<ContasPagar, String> dataPagamento;
    public static volatile SingularAttribute<ContasPagar, Compra> compraIdCompra;
    public static volatile SingularAttribute<ContasPagar, String> dataVencimento;
    public static volatile SingularAttribute<ContasPagar, String> vencida;
    public static volatile SingularAttribute<ContasPagar, Integer> idContasPagar;
    public static volatile SingularAttribute<ContasPagar, String> pagamento;

}