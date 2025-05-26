package com.mycompany.pi;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends javax.swing.JFrame {

    Usuarios u;
    Profissional p;
    Client c;
    boolean solicitation;
    
    
    public TelaPrincipal() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        setVisible(true);
    }
     public TelaPrincipal(Usuarios u, Client c) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        solicitation = false;
        this.u =u;
        this.c =c;
        jTable1.setModel(montarTabelClient(DAOProfissional.listar()));
    }
      public TelaPrincipal(Usuarios u, Profissional p) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        this.p=p;
        jTable1.setModel(montarTabelaServicos( DAOSolicitation.listar(),DAOClient.listar(), DAOProfissional.listar(), this.p));
        this.u =u;
        

        
        
        
    }
       private DefaultTableModel montarTabelaServicos(List<Solicitation> lista, List<Client> list, List<Profissional> lis, Profissional profissionalLogado) {
    String[] colunas = {"Id", "Nome Profissional", "Nome Cliente", "Tipo Serviço"};
    DefaultTableModel tabela = new DefaultTableModel();
    tabela.setColumnIdentifiers(colunas);

    int tamanho = Math.min(lista.size(), Math.min(list.size(), lis.size()));

    for (int i = 0; i < tamanho; i++) {
        Solicitation solicitacao = lista.get(i);
        Client cliente = list.get(i);
        Profissional profissional = lis.get(i);

        if (profissional.getId() == profissionalLogado.getId()) {
            List<Service> servicos = DAOService.buscarPorId(profissional.getIdService());
            String tiposervico = "Não encontrado";

            if (!servicos.isEmpty()) {
                Service servico = servicos.get(0);
                tiposervico = servico.getTipo();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao selecionar serviço do profissional " + profissional.getNome());
            }

            String[] linha = {
                Integer.toString(solicitacao.getId()),
                profissional.getNome(),
                cliente.getNome(),
                tiposervico
            };

            tabela.addRow(linha);
        }
    }
    return tabela;
}
      
       private DefaultTableModel montarTabeServicosCliente(List<Solicitation> listaSolicitacoes, List<Client> listaClientes, List<Profissional> listaProfissionais) {
    String[] colunas = {"Id", "Nome Profissional", "Nome Cliente", "Tipo Serviço"};
    DefaultTableModel tabela = new DefaultTableModel();
    tabela.setColumnIdentifiers(colunas);

    for (Solicitation solicitacao : listaSolicitacoes) {
        if (solicitacao.getIdClient() == this.c.getId()) {

            Client cliente = null;
            for (Client cli : listaClientes) {
                if (cli.getId() == solicitacao.getIdClient()) {
                    cliente = cli;
                    break;
                }
            }

            Profissional profissional = null;
            for (Profissional prof : listaProfissionais) {
                if (prof.getId() == solicitacao.getIdProfissional()) {
                    profissional = prof;
                    break;
                }
            }
            

            if (profissional != null && cliente != null) {
                List<Service> servicos = DAOService.buscarPorId(profissional.getIdService());
                String tipoServico = (!servicos.isEmpty()) ? servicos.get(0).getTipo() : "N/A";

                String[] linha = {
                    Integer.toString(solicitacao.getId()),
                    profissional.getNome(),
                    cliente.getNome(),
                    tipoServico
                };
                tabela.addRow(linha);
            }
        }
    }
    return tabela;
}
       
       
       private DefaultTableModel montarTabelClient(List<Profissional> lista){
        String[] colunas = {"Id","Nome","Serviço"};
        DefaultTableModel tabela = new DefaultTableModel();
        tabela.setColumnIdentifiers(colunas);
        
        
        
        
        for(int i = 0; i<lista.size();i++){
            Profissional p = lista.get(i);
            List<Service> servicos = DAOService.buscarPorId(p.getIdService());
            String tiposervico=null;
            if (!servicos.isEmpty()) {
                Service servico = servicos.get(0);
                tiposervico = servico.getTipo();
            } else {
                JOptionPane.showMessageDialog(null, "erro ao selecionar usuario");
            }
            String[] linha ={
                Integer.toString(p.getId()),
                p.getNome(),
                tiposervico              
            };
            tabela.addRow(linha);
        }
        return tabela;
    }
      private String getIdItem(){
        int posicao = jTable1.getSelectedRow();
        if(posicao == -1){
            JOptionPane.showMessageDialog(null, "Por favor, selecione um item na tabela!");
            return null;
        }else{
        
        }
        return (String) jTable1.getValueAt(posicao, 0);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Raio de arredondamento
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Mesmo raio aqui
                g2.dispose();
            }
        };
        jButton3 = new javax.swing.JButton() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Raio de arredondamento
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Mesmo raio aqui
                g2.dispose();
            }
        };
        jButton4 = new javax.swing.JButton() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Raio de arredondamento
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Mesmo raio aqui
                g2.dispose();
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(221, 226, 226));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(138, 166, 163));
        jButton2.setFont(new java.awt.Font("Forum", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SOLICITAÇÃO");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(138, 166, 163));
        jButton3.setFont(new java.awt.Font("Forum", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("VISUALIZAR");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(138, 166, 163));
        jButton4.setFont(new java.awt.Font("Forum", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("SERVIÇOS");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 672, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            if(this.solicitation==false){
                String idStr = getIdItem();
                if (idStr != null) {
                int id = Integer.parseInt(idStr);
                List<Profissional> profissionals = DAOProfissional.buscarPorIdProprio(id);
                if (!profissionals.isEmpty()) {
                        Profissional profissional = profissionals.get(0);
                        TelaSocilitar t = new TelaSocilitar(this.c,this.u,profissional);;
                        t.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "erro ao selecionar profissional");
                    }
                }       
        }
        dispose();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(u.getNivel().equals("cliente")){
            if(solicitation==false){
                String idStr = getIdItem();
                if (idStr != null) {
                int id = Integer.parseInt(idStr);
                List<Profissional> profissionals = DAOProfissional.buscarPorIdProprio(id);
                if (!profissionals.isEmpty()) {
                        Profissional profissional = profissionals.get(0);
                        VizualizarCliente t = new VizualizarCliente(u, profissional);
                        t.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "erro ao selecionar usuario");
                    }
                }
            }else{
                String idStr = getIdItem();
                if (idStr != null) {
                int id = Integer.parseInt(idStr);
                List<Solicitation> solicitacoes = DAOSolicitation.buscarPorId(id);
                
                if (!solicitacoes.isEmpty()) {
                        Solicitation solicitacao = solicitacoes.get(0);
                        List<Client> clientes = DAOClient.buscarPorIdProprio(solicitacao.getIdClient());
                        List<Profissional> profissionais = DAOProfissional.buscarPorIdProprio(solicitacao.getIdProfissional());
                        
                        Client cliente = clientes.get(0);
                        Profissional profissional = profissionais.get(0);
                        
                        TelaVizualizarSolicitacao t = new TelaVizualizarSolicitacao(solicitacao, cliente, profissional, this.u);;
                        t.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "erro ao selecionar profissional");
                    }
                }
            }
        
        }else{
            String idStr = getIdItem();
                if (idStr != null) {
                int id = Integer.parseInt(idStr);
                List<Solicitation> solicitacoes = DAOSolicitation.buscarPorId(id);
                
                if (!solicitacoes.isEmpty()) {
                        Solicitation solicitacao = solicitacoes.get(0);
                        List<Client> clientes = DAOClient.buscarPorIdProprio(solicitacao.getIdClient());
                        List<Profissional> profissionais = DAOProfissional.buscarPorIdProprio(solicitacao.getIdProfissional());
                        
                        Client cliente = clientes.get(0);
                        Profissional profissional = profissionais.get(0);
                        
                        TelaVizualizarSolicitacao t = new TelaVizualizarSolicitacao(solicitacao, cliente, profissional, this.u);;
                        t.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "erro ao selecionar profissional");
                    }
                }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(solicitation == false){
             jTable1.setModel(montarTabeServicosCliente( DAOSolicitation.listar(),DAOClient.listar(), DAOProfissional.listar()));
             solicitation = true;
             jButton4.setText("PROFISSIONAIS");
             jButton2.setEnabled(false);
        }else{
            jTable1.setModel(montarTabelClient(DAOProfissional.listar()));
            solicitation = false;
            jButton4.setText("SERVIÇOS");
            jButton2.setEnabled(true);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
