/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ItemVendaDAO;
import br.com.jpbonfa.vendas.model.ItemVenda;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class ItemVendaController {

    public ItemVendaController() {
    }

    public ArrayList<ItemVenda> buscarTodos() {
        try {
            return new ItemVendaDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarItemVenda);
        }
        return null;
    }

    public void excluir(ItemVenda itemVenda) {
        try {
            new ItemVendaDAO().excluir(itemVenda);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirItemVenda);
        }

    }

    public void salvar(ItemVenda itemVenda) {
        try {
            new ItemVendaDAO().salvar(itemVenda);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarItemVenda);
        }

    }

}
