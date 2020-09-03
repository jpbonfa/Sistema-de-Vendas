package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.CompraPrazoView;

/**
 *
 * @author joaop
 */
public class CompraPrazoController {

    private CompraPrazoView viewCompraPrazo;

    public CompraPrazoController() {
    }

    public CompraPrazoController(CompraPrazoView viewCompraPrazo) {
        this.viewCompraPrazo = viewCompraPrazo;
    }

    public void AcaoBotaoConfirmar() {
        if (valida()) {
            //procedimentos para salvar os dados

        }

    }

    private boolean valida() {
        if (Valida.verificaDataVazio(this.viewCompraPrazo.getTfAPagar().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewCompraPrazo.getTfAPagar().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewCompraPrazo.getTfAPagar().grabFocus();
            return false;
        }
        if (Valida.verificaRadioButtonSelecionado(this.viewCompraPrazo.getGrupoBotaoVencido())) {
            JOptionPaneUtil.erro(Mensagem.informeVencido);
            return false;

        }
        if (Valida.verificaDataVazio(this.viewCompraPrazo.getTfVencimento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewCompraPrazo.getTfVencimento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewCompraPrazo.getTfVencimento().grabFocus();
            return false;
        }

        if (Valida.verificaRadioButtonSelecionado(this.viewCompraPrazo.getGrupoBotaoPago())) {
            JOptionPaneUtil.erro(Mensagem.informePago);
            return false;

        }

        return true;
    }

}
