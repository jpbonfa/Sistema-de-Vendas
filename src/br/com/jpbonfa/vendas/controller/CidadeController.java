package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.CidadeDAO;
import br.com.jpbonfa.vendas.model.Cidade;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 * Classe responsavel por encapsular os metodos de controle da Cidade
 *
 * @author joaop
 * @since 09/07/2020
 */
public class CidadeController {

    public ArrayList<Cidade> buscarTodos() {
        try {
            return new CidadeDAO().buscarTodos();
        } catch (Exception e) {
              e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarCidades);

        }
        return null;
    }

}
