/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.PessoaFisicaDAO;
import br.com.jpbonfa.vendas.model.PessoaFisica;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class PessoaFisicaController {

   

    public PessoaFisicaController() {
    }

    public ArrayList<PessoaFisica> buscarTodos() {
        try {
            return new PessoaFisicaDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarPf);
        }
        return null;
    }

    public void excluir(PessoaFisica pf) {
        try {
            new PessoaFisicaDAO().excluir(pf);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirPf);
        }

    }

    public void salvar(PessoaFisica pf) {
        try {
            new PessoaFisicaDAO().salvar(pf);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarPf);

        }

    }

}
