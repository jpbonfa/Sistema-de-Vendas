package br.com.jpbonfa.vendas.dao;

import br.com.jpbonfa.vendas.model.ContasReceber;
import br.com.jpbonfa.vendas.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class ContasReceberDAO extends GenericDAO {

    public ArrayList<ContasReceber> buscarTodos() {

        ArrayList<ContasReceber> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(ContasReceber.class);
        criteria.addOrder(Order.asc("idContasReceber"));
        listaRetorno = (ArrayList<ContasReceber>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

}
