/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.EnderecoDAO;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class EnderecoController {

    public EnderecoController() {
    }

    public ArrayList<Endereco> buscarTodos() {
        try {
            return new EnderecoDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarEndereco);
        }
        return null;
    }

    public void excluir(Endereco endereco) {
        try {
            new EnderecoDAO().excluir(endereco);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirEndereco);
        }
    }

    public void salvar(Endereco endereco) {
        try {
            new EnderecoDAO().salvar(endereco);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarEndereco);
        }

    }
}
