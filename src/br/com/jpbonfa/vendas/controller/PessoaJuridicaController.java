/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.PessoaJuridicaDAO;
import br.com.jpbonfa.vendas.model.PessoaJuridica;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class PessoaJuridicaController {

   

    public PessoaJuridicaController() {
    }

    public ArrayList<PessoaJuridica> buscarTodos() {
        try {
            return new PessoaJuridicaDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarPj);
        }
        return null;
    }

    public void excluir(PessoaJuridica pj) {
        try {
            new PessoaJuridicaDAO().excluir(pj);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirPj);
        }

    }

    public void salvar(PessoaJuridica pj) {
        try {
            new PessoaJuridicaDAO().salvar(pj);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarPj);

        }

    }

}
