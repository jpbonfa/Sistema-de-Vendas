package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(ContasReceber.class)
public class ContasReceber_ { 

    public static volatile SingularAttribute<ContasReceber, String> dataPagamento;
    public static volatile SingularAttribute<ContasReceber, Integer> idContasReceber;
    public static volatile SingularAttribute<ContasReceber, String> dataVencimento;
    public static volatile SingularAttribute<ContasReceber, String> vencida;
    public static volatile SingularAttribute<ContasReceber, Venda> vendaIdVenda;
    public static volatile SingularAttribute<ContasReceber, String> pagamento;

}