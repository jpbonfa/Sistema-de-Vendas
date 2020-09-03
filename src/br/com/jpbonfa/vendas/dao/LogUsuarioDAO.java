/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.dao;

import br.com.jpbonfa.vendas.model.LogUsuario;
import br.com.jpbonfa.vendas.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class LogUsuarioDAO extends GenericDAO{

    public ArrayList<LogUsuario> buscarTodos() {

        ArrayList<LogUsuario> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(LogUsuario.class);
        criteria.addOrder(Order.asc("idLogUsuario"));
        listaRetorno = (ArrayList<LogUsuario>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

}
