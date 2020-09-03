/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpbonfa.vendas.view;

import br.com.jpbonfa.vendas.controller.VendasController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author joaop
 */
public class VendasView extends javax.swing.JFrame {

    VendasController vendasController = new VendasController(VendasView.this);

    public VendasView() {
        initComponents();
        setLocationRelativeTo(null);
        this.vendasController.carregarFuncionarios();
        this.vendasController.carregarClientes();
        this.vendasController.listarProdutos();
        this.vendasController.bloquearCampos();
        this.tbVendas.setDefaultEditor(Object.class, null);
        this.tbFormaPagamento.setDefaultEditor(Object.class, null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbFuncionario = new javax.swing.JComboBox();
        btIniciar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfDescontoProduto = new javax.swing.JTextField();
        btAdicionarProduto = new javax.swing.JButton();
        btExcluirProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cbPagamento = new javax.swing.JComboBox();
        btIncluirPagamento = new javax.swing.JButton();
        lbDesconto = new javax.swing.JLabel();
        tfDescontoPagamento = new javax.swing.JTextField();
        btExcluirPagamento = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbValorTotal = new javax.swing.JLabel();
        btConfirmar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbFormaPagamento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Venda");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Cliente:");

        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Funcionário:");

        cbFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncionarioActionPerformed(evt);
            }
        });

        btIniciar.setText("Iniciar");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(btIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel3.setText("Descrição:");

        jLabel4.setText("Quantidade:");

        jLabel5.setText("Desconto:");

        btAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpbonfa/vendas/img/salvar.png"))); // NOI18N
        btAdicionarProduto.setText("Adicionar Produto");
        btAdicionarProduto.setMaximumSize(new java.awt.Dimension(200, 40));
        btAdicionarProduto.setMinimumSize(new java.awt.Dimension(200, 40));
        btAdicionarProduto.setPreferredSize(new java.awt.Dimension(200, 40));
        btAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarProdutoActionPerformed(evt);
            }
        });

        btExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpbonfa/vendas/img/excluir.png"))); // NOI18N
        btExcluirProduto.setText("Excluir Produto");
        btExcluirProduto.setMaximumSize(new java.awt.Dimension(200, 40));
        btExcluirProduto.setMinimumSize(new java.awt.Dimension(200, 40));
        btExcluirProduto.setPreferredSize(new java.awt.Dimension(200, 40));
        btExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tfDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade", "Valor Unitário", "Desconto", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(tbVendas);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cbPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Selecione uma forma de pagamento -", "À Vista", "À Prazo", "Cartão Débito", "Cartão Credito" }));
        cbPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPagamentoItemStateChanged(evt);
            }
        });

        btIncluirPagamento.setText("Incluir");
        btIncluirPagamento.setMaximumSize(new java.awt.Dimension(63, 23));
        btIncluirPagamento.setMinimumSize(new java.awt.Dimension(63, 23));
        btIncluirPagamento.setPreferredSize(new java.awt.Dimension(63, 23));
        btIncluirPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirPagamentoActionPerformed(evt);
            }
        });

        lbDesconto.setText("Desconto:");

        tfDescontoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDescontoPagamentoActionPerformed(evt);
            }
        });

        btExcluirPagamento.setText("Excluir");
        btExcluirPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btIncluirPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(lbDesconto)
                .addGap(18, 18, 18)
                .addComponent(tfDescontoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDesconto)
                            .addComponent(tfDescontoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btIncluirPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluirPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbValorTotal)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbValorTotal)
                .addGap(60, 60, 60))
        );

        btConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpbonfa/vendas/img/salvar.png"))); // NOI18N
        btConfirmar.setText("Confirmar");
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpbonfa/vendas/img/cancelar.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpbonfa/vendas/img/sair.png"))); // NOI18N
        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        tbFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forma de Pagamento", "Desconto"
            }
        ));
        jScrollPane2.setViewportView(tbFormaPagamento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfDescontoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDescontoPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescontoPagamentoActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.vendasController.acaoBotaoSair();
    }//GEN-LAST:event_btSairActionPerformed

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed
        this.vendasController.acaoBotaoIniciarVenda();
    }//GEN-LAST:event_btIniciarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.vendasController.acaoBotaoCancelar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        this.vendasController.desbloqueioIniciar();
    }//GEN-LAST:event_cbClienteActionPerformed

    private void cbFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncionarioActionPerformed
        this.vendasController.desbloqueioIniciar();
    }//GEN-LAST:event_cbFuncionarioActionPerformed

    private void btAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarProdutoActionPerformed
        this.vendasController.acaoBotaoAdcionarProduto();
    }//GEN-LAST:event_btAdicionarProdutoActionPerformed

    private void btExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirProdutoActionPerformed
        this.vendasController.acaoBotaoExcluirProduto();
    }//GEN-LAST:event_btExcluirProdutoActionPerformed

    private void btExcluirPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirPagamentoActionPerformed
        this.vendasController.acaoBotaoExcluirPagamento();
    }//GEN-LAST:event_btExcluirPagamentoActionPerformed

    private void btIncluirPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirPagamentoActionPerformed
        this.vendasController.acaoBotaoAdcionarPagamento();
    }//GEN-LAST:event_btIncluirPagamentoActionPerformed

    private void btConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmarActionPerformed
        this.vendasController.acaoBotaoConfirmar();
    }//GEN-LAST:event_btConfirmarActionPerformed

    private void cbPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPagamentoItemStateChanged
        this.vendasController.desbloquearDesconto();
    }//GEN-LAST:event_cbPagamentoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionarProduto;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConfirmar;
    private javax.swing.JButton btExcluirPagamento;
    private javax.swing.JButton btExcluirProduto;
    private javax.swing.JButton btIncluirPagamento;
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox cbCliente;
    private javax.swing.JComboBox cbFuncionario;
    private javax.swing.JComboBox cbPagamento;
    private javax.swing.JComboBox cbProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDesconto;
    private javax.swing.JLabel lbValorTotal;
    private javax.swing.JTable tbFormaPagamento;
    private javax.swing.JTable tbVendas;
    private javax.swing.JTextField tfDescontoPagamento;
    private javax.swing.JTextField tfDescontoProduto;
    private javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables

    public JButton getBtAdicionarProduto() {
        return btAdicionarProduto;
    }

    public void setBtAdicionarProduto(JButton btAdicionarProduto) {
        this.btAdicionarProduto = btAdicionarProduto;
    }

    public JButton getBtCancelar() {
        return btCancelar;
    }

    public void setBtCancelar(JButton btCancelar) {
        this.btCancelar = btCancelar;
    }

    public JButton getBtConfirmar() {
        return btConfirmar;
    }

    public void setBtConfirmar(JButton btConfirmar) {
        this.btConfirmar = btConfirmar;
    }

    public JButton getBtExcluirPagamento() {
        return btExcluirPagamento;
    }

    public void setBtExcluirPagamento(JButton btExcluirPagamento) {
        this.btExcluirPagamento = btExcluirPagamento;
    }

    public JButton getBtExcluirProduto() {
        return btExcluirProduto;
    }

    public void setBtExcluirProduto(JButton btExcluirProduto) {
        this.btExcluirProduto = btExcluirProduto;
    }

    public JButton getBtIniciar() {
        return btIniciar;
    }

    public void setBtIniciar(JButton btIniciar) {
        this.btIniciar = btIniciar;
    }

    public JButton getBtSair() {
        return btSair;
    }

    public void setBtSair(JButton btSair) {
        this.btSair = btSair;
    }

    public JComboBox getCbCliente() {
        return cbCliente;
    }

    public void setCbCliente(JComboBox cbCliente) {
        this.cbCliente = cbCliente;
    }

    public JComboBox getCbFuncionario() {
        return cbFuncionario;
    }

    public void setCbFuncionario(JComboBox cbFuncionario) {
        this.cbFuncionario = cbFuncionario;
    }

    public JComboBox getCbPagamento() {
        return cbPagamento;
    }

    public void setCbPagamento(JComboBox cbPagamento) {
        this.cbPagamento = cbPagamento;
    }

    public JComboBox getCbProduto() {
        return cbProduto;
    }

    public void setCbProduto(JComboBox cbProduto) {
        this.cbProduto = cbProduto;
    }

    public JTable getTbFormaPagamento() {
        return tbFormaPagamento;
    }

    public void setTbFormaPagamento(JTable tbFormaPagamento) {
        this.tbFormaPagamento = tbFormaPagamento;
    }

    public JTable getTbVendas() {
        return tbVendas;
    }

    public void setTbVendas(JTable tbVendas) {
        this.tbVendas = tbVendas;
    }

    public JTextField getTfDescontoPagamento() {
        return tfDescontoPagamento;
    }

    public void setTfDescontoPagamento(JTextField tfDescontoPagamento) {
        this.tfDescontoPagamento = tfDescontoPagamento;
    }

    public JTextField getTfDescontoProduto() {
        return tfDescontoProduto;
    }

    public void setTfDescontoProduto(JTextField tfDescontoProduto) {
        this.tfDescontoProduto = tfDescontoProduto;
    }

    public JTextField getTfQuantidade() {
        return tfQuantidade;
    }

    public void setTfQuantidade(JTextField tfQuantidade) {
        this.tfQuantidade = tfQuantidade;
    }

    public JButton getBtIncluirPagamento() {
        return btIncluirPagamento;
    }

    public void setBtIncluirPagamento(JButton btIncluirPagamento) {
        this.btIncluirPagamento = btIncluirPagamento;
    }

    public JLabel getLbValorTotal() {
        return lbValorTotal;
    }

    public void setLbValorTotal(JLabel lbValorTotal) {
        this.lbValorTotal = lbValorTotal;
    }

    public JLabel getLbDesconto() {
        return lbDesconto;
    }

    public void setLbDesconto(JLabel lbDesconto) {
        this.lbDesconto = lbDesconto;
    }

}