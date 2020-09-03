/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ItemCompraDAO;
import br.com.jpbonfa.vendas.model.ItemCompra;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class ItemCompraController {

    public ItemCompraController() {
    }

    public ArrayList<ItemCompra> buscarTodos() {
        try {
            return new ItemCompraDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarPj);
        }
        return null;
    }

    public void excluir(ItemCompra itemCompra) {
        try {
            new ItemCompraDAO().excluir(itemCompra);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirPf);
        }

    }

    public void salvar(ItemCompra itemCompra) {
        try {
            new ItemCompraDAO().salvar(itemCompra);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarPf);
        }

    }

}
