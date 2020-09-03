package br.com.jpbonfa.vendas.model;

import br.com.jpbonfa.vendas.model.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-03T19:53:38")
@StaticMetamodel(LogUsuario.class)
public class LogUsuario_ { 

    public static volatile SingularAttribute<LogUsuario, String> tabela;
    public static volatile SingularAttribute<LogUsuario, Integer> idLogUsuario;
    public static volatile SingularAttribute<LogUsuario, String> operacao;
    public static volatile SingularAttribute<LogUsuario, Funcionario> funcionarioIdFuncionario;
    public static volatile SingularAttribute<LogUsuario, String> timestamp;

}