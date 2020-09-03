/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.LogUsuarioDAO;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.model.LogUsuario;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.ServiceUtil;

/**
 *
 * @author joaop
 */
public class LogUsuarioController {

    public LogUsuarioController() {
    }

    public void gerarLog(String operacao, String tabela, Funcionario funcionario) {
        LogUsuario log = new LogUsuario();
        log.setOperacao(operacao);
        log.setTabela(tabela);
        log.setFuncionarioIdFuncionario(funcionario);
        log.setTimestamp(ServiceUtil.dataCompleta());
        salvar(log);
    }

    public void salvar(LogUsuario logUsuario) {
        try {
            new LogUsuarioDAO().salvar(logUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarLogUsuario);
        }
    }
}
