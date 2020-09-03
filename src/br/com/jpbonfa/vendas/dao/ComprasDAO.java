package br.com.jpbonfa.vendas.dao;

import br.com.jpbonfa.vendas.model.Compra;
import br.com.jpbonfa.vendas.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class ComprasDAO extends GenericDAO {

    public ArrayList<Compra> buscarTodos() {

        ArrayList<Compra> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Compra.class);
        criteria.addOrder(Order.asc("idCompra"));
        listaRetorno = (ArrayList<Compra>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

}
