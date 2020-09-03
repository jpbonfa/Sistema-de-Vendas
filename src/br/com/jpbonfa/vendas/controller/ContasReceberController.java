package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.ContasAReceberView;

/**
 *
 * @author joaop
 */
public class ContasReceberController {

    private ContasAReceberView viewContasAReceber;

    public ContasReceberController() {
    }

    public ContasReceberController(ContasAReceberView viewContasAReceber) {
        this.viewContasAReceber = viewContasAReceber;
    }

    public void acaoBotaoAlterar() {
        desbloquearCampos();
        this.viewContasAReceber.getBtSalvar().setEnabled(true);
        this.viewContasAReceber.getBtCancelar().setEnabled(true);
        this.viewContasAReceber.getBtAlterar().setEnabled(false);
        this.viewContasAReceber.getBtSair().setEnabled(false);

    }

    public void acaoBotaoSalvar() {
        if (validaAlterar()) {

        }
    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewContasAReceber.getBtSalvar().setEnabled(false);
        this.viewContasAReceber.getBtCancelar().setEnabled(false);
        this.viewContasAReceber.getBtAlterar().setEnabled(true);
        this.viewContasAReceber.getBtSair().setEnabled(true);
    }

    public void acaoBotaoSair() {
        this.viewContasAReceber.setVisible(false);

    }

    public void bloquearCampos() {
        this.viewContasAReceber.getTfDataPagamento().setEditable(false);
        this.viewContasAReceber.getTfDataVencimento().setEditable(false);
        this.viewContasAReceber.getBtSalvar().setEnabled(false);
        this.viewContasAReceber.getBtCancelar().setEnabled(false);
        this.viewContasAReceber.getRbPago().setEnabled(false);
        this.viewContasAReceber.getRbNaoPago().setEnabled(false);
    }

    public void desbloquearCampos() {
        this.viewContasAReceber.getTfDataPagamento().setEditable(true);
        this.viewContasAReceber.getTfDataVencimento().setEditable(true);
        this.viewContasAReceber.getBtSalvar().setEnabled(true);
        this.viewContasAReceber.getBtCancelar().setEnabled(true);
        this.viewContasAReceber.getRbPago().setEnabled(true);
        this.viewContasAReceber.getRbNaoPago().setEnabled(true);
    }

    public void limparCampos() {
        this.viewContasAReceber.getTfDataPagamento().setValue(null);
        this.viewContasAReceber.getTfDataVencimento().setValue(null);
        this.viewContasAReceber.getButtonGroup1().clearSelection();
    }

    private boolean validaAlterar() {

        if (Valida.verificaDataVazio(this.viewContasAReceber.getTfDataVencimento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            this.viewContasAReceber.getTfDataVencimento().grabFocus();
            return false;
        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewContasAReceber.getTfDataVencimento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewContasAReceber.getTfDataVencimento().grabFocus();
            return false;
        }
        if (Valida.verificaDataVazio(this.viewContasAReceber.getTfDataPagamento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            this.viewContasAReceber.getTfDataPagamento().grabFocus();
            return false;
        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewContasAReceber.getTfDataPagamento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewContasAReceber.getTfDataPagamento().grabFocus();
            return false;
        }
        if (Valida.verificaRadioButtonSelecionado(this.viewContasAReceber.getButtonGroup1())) {
            JOptionPaneUtil.erro(Mensagem.informePago);
            return false;
        }

        return true;
    }

}
