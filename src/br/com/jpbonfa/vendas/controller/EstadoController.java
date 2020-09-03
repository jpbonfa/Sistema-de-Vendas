package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.EstadoDAO;
import br.com.jpbonfa.vendas.model.Estado;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 * Classe responsavel por encapsular os metodos de controle de estado
 *
 * @author joaop
 * @since 09/07/2020
 */
public class EstadoController {

    public ArrayList<Estado> buscarTodos() {
        try {
            return new EstadoDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarEstados);
        }
        return null;
    }

}
