package com.mycompany.pi;


import java.awt.Graphics;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaLoginInicial extends javax.swing.JFrame {
    public TelaLoginInicial() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel(){
            Image background = new ImageIcon(getClass().getResource("/BackgroudLogin1.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Email = new javax.swing.JTextField();
        Senha = new javax.swing.JTextField();
        Button = new javax.swing.JButton() {
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(null);
        jPanel2.setForeground(null);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Email.setBackground(new java.awt.Color(255, 255, 255));
        Email.setToolTipText("");
        Email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 530, 40));

        Senha.setBackground(new java.awt.Color(255, 255, 255));
        Senha.setToolTipText("");
        Senha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.add(Senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 530, 40));

        Button.setBackground(new java.awt.Color(138, 166, 163));
        Button.setFont(new java.awt.Font("Forum", 1, 36)); // NOI18N
        Button.setForeground(new java.awt.Color(255, 255, 255));
        Button.setText("CONTINUAR");
        Button.setBorderPainted(false);
        Button.setContentAreaFilled(false);
        Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonActionPerformed(evt);
            }
        });
        jPanel2.add(Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 580, 280, 70));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Forum", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("criar conta");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 650, -1, 20));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonActionPerformed
        // TODO add your handling code here:
        Usuarios u = new Usuarios();
        Profissional p;
        Client c;
        
        u.setLogin(Email.getText());
        u.setSenha(Senha.getText());
        
        u= UsuarioDAO.validarUsuario(u);
        
        
        if(u != null){
            JOptionPane.showMessageDialog(null, "Olá "+ u.getLogin());
            ;
            if(u.getNivel().equals("profissional")){
               List<Profissional> profissionais = DAOProfissional.buscarPorId(u.getId());
                if (!profissionais.isEmpty()) {
                     Profissional profissional = profissionais.get(0);
                     TelaPrincipal t = new TelaPrincipal(u, profissional);
                     t.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "erro ao logar");
                }
            }else{
                List<Client> clientes = DAOClient.buscarPorId(u.getId());
                if (!clientes.isEmpty()) {
                     Client cliente = clientes.get(0);
                     TelaPrincipal t = new TelaPrincipal(u, cliente);
                     t.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "erro ao logar");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
        }
    }//GEN-LAST:event_ButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TelaEscolhaConta t = new TelaEscolhaConta();
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLoginInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLoginInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLoginInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLoginInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLoginInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Senha;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
