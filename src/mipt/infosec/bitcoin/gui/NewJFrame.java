package mipt.infosec.bitcoin.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Integer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mipt.infosec.bitcoin.network.Receiver;
import mipt.infosec.bitcoin.wallet.Wallet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author днс
 */
public class NewJFrame extends javax.swing.JFrame {

    public static Toolkit kit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = kit.getScreenSize();
    public static int screenWidth = (int)(screenSize.width);
    public static int screenHeight = (int)(screenSize.height);
    private BufferedImage iconimage;
    public NewJFrame() {
        initComponents();
        try {
            iconimage = ImageIO.read(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/bitcoin-16.png"));
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setIconImage(iconimage);
        setTitle("MiptСoin");
        setBackground(Color.WHITE);
        
        setSize((int)(screenWidth / 1.5), (int)(screenHeight / 1.5));
        setLocation((int)(screenWidth / 1.5) / 4, (int)(screenHeight / 1.5) / 4);
        HelloPanel panel = new HelloPanel();
        panel.setBackground(Color.WHITE);
        
        add(panel);
    }
    class HelloPanel extends JPanel {

        private BufferedImage image;
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("Здравствуй, пользователь!", screenWidth / 4, screenHeight / 4);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("У вас на счету: ");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
///////////////        
        Wallet wal = new Wallet();
        Integer x = wal.getSumm();
        jLabel2.setText(x.toString());
////////////////
        jMenu1.setText("Файл");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon_success_sml.gif"))); // NOI18N
        jMenuItem1.setText("Создать блок");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/disk-minus-icon-icon.png"))); // NOI18N
        jMenuItem2.setText("Сделать копию бумажника...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/folder-pencil-icon-icon.png"))); // NOI18N
        jMenuItem3.setText("Подписать сообщение...");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-info-icon.png"))); // NOI18N
        jMenuItem4.setText("Проверить сообщение...");
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-email-icon.png"))); // NOI18N
//////////////////////////////////
        jMenuItem5.setText("Отправить сообщение...");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
//////////////////////////////////        
        jMenu1.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-email-icon.png"))); // NOI18N
        jMenuItem6.setText("Адреса получения...");
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator2);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon_error_sml.gif"))); // NOI18N
        jMenuItem7.setText("Выход");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Настройки");

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-padlock-icon.png"))); // NOI18N
        jMenuItem8.setText("Зашифровать бумажник...");
        jMenu2.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-wand-icon.png"))); // NOI18N
        jMenuItem9.setText("Изменить пароль...");
        jMenu2.add(jMenuItem9);
        jMenu2.add(jSeparator3);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/drill-arrow-icon-icon.png"))); // NOI18N
        jMenuItem10.setText("Опции");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Помощь");

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/application-minus-icon-icon.png"))); // NOI18N
        jMenuItem11.setText("Окно отладки");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-info-icon.png"))); // NOI18N
        jMenuItem12.setText("Опции командной строки");
        jMenu3.add(jMenuItem12);
        jMenu3.add(jSeparator4);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-favourites-icon.png"))); // NOI18N
        jMenuItem13.setText("О программе");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    	BlockCreateFrame frame = new BlockCreateFrame();
        frame.setDefaultCloseOperation(WIDTH);
        frame.setVisible(true);
        frame.setTitle("Создать блок");
        frame.setLocation(screenWidth / 4, screenHeight / 4);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    	int result = JOptionPane.showConfirmDialog(
    			null,
    			"Вы уверены, что хотите выйти?",
    			"Выход",
    			JOptionPane.YES_NO_OPTION);
    			if (result == 0)
    			System.exit(0);
    			else 
    				System.out.println(screenWidth + "       " + screenHeight);

    }//GEN-LAST:event_jMenuItem7ActionPerformed
//////////////////////////////////////////////////////////
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    	SendFrame frame = new SendFrame();
        frame.setDefaultCloseOperation(WIDTH);
        frame.setVisible(true);
        frame.setTitle("Отравить сообщение");
        frame.setLocation(screenWidth / 4, screenHeight / 4);
    }
    
//////////////////////////////////////////////////////////  
  
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        SimpleFrame frame = new SimpleFrame();
        frame.setDefaultCloseOperation(WIDTH);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    class SimpleFrame extends JFrame {
       
        private BufferedImage iconimage;
        
        public SimpleFrame() {
            setTitle("О программе");
            setSize(screenWidth / 2, screenHeight / 2);
            setLocation(screenWidth / 4, screenHeight / 4);
            try {
                iconimage = ImageIO.read(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/icon-favourites-icon.png"));
                
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            setIconImage(iconimage);
            HiPanel panel = new HiPanel();
            panel.setBackground(Color.WHITE);
            panel.setName("О программе");
            
            add(panel);
            
        }

    }
    
    class HiPanel extends JPanel {

        private BufferedImage image;
        
        public void paintComponent(Graphics g) {
            try {
                image = ImageIO.read(getClass().getResource("/mipt/infosec/bitcoin/gui/icons/bitcoin.jpg"));
                
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            super.paintComponent(g);
            g.drawString("MiptСoin", screenWidth / 7, screenHeight / 20);
            g.drawString("Версия 1.0", screenWidth / 7, screenHeight / 15);
            g.drawString("Авторы:", screenWidth / 7, screenHeight / 7);
            g.drawString("Камалова Ирина, Мингазов Денис, Патушин Александр,", screenWidth / 7, screenHeight / 5);
            g.drawString("Самарин Юрий, Смирнов Николай", screenWidth / 7, screenHeight / 4);
            g.drawImage(image, screenWidth / 40, screenHeight / 32, this);
            
        }
        
    }

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Thread server = new Thread() {
        	@Override
        	public void run () {
        		Receiver receiver = new Receiver();
        		receiver.receive();
        	}

        };
//!server.start();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
