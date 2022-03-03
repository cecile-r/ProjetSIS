/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Audrey
 */
public class Vue_Patient_Medicaments_Inf extends javax.swing.JFrame {

    /** Creates new form Connexion */
    public Vue_Patient_Medicaments_Inf() {
        initComponents();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - getWidth()) /2);
        int y = (int) ((screen.getHeight() -getHeight()) /2);
        setLocation(x, y);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Main = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        Identite = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TextField_Patient = new javax.swing.JTextField();
        Label_Loupe_Patient = new javax.swing.JLabel();
        Label_Plus_Patient = new javax.swing.JLabel();
        Label_Home = new javax.swing.JLabel();
        Panel_Hospit = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Label_Vue_CR = new javax.swing.JLabel();
        Panel_Actes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Label_Plus = new javax.swing.JLabel();
        Panel_Soins = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_Soins = new javax.swing.JTable();
        Panel_Gauche = new javax.swing.JPanel();
        Label_Icon_Patient = new javax.swing.JLabel();
        Label_Identite = new javax.swing.JLabel();
        Label_Modifications = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setMinimumSize(new java.awt.Dimension(800, 600));

        Panel_Main.setBackground(new java.awt.Color(153, 153, 255));
        Panel_Main.setPreferredSize(new java.awt.Dimension(834, 602));

        Panel_Bandeau.setBackground(new java.awt.Color(204, 102, 255));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Panel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo connexa-modified.png"))); // NOI18N

        Panel_icon_perso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil 2.png"))); // NOI18N

        Identite.setBackground(new java.awt.Color(204, 204, 255));
        Identite.setText("Identité");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/se-deconnecter.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(Panel_icon_perso)
                .addGap(28, 28, 28)
                .addComponent(Identite, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(Panel_logo)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_icon_perso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Panel_logo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Identite, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TextField_Patient.setText("Patient");
        TextField_Patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_PatientActionPerformed(evt);
            }
        });

        Label_Loupe_Patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loupe.png"))); // NOI18N
        Label_Loupe_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Loupe_PatientMouseClicked(evt);
            }
        });

        Label_Plus_Patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        Label_Plus_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Plus_PatientMouseClicked(evt);
            }
        });

        Label_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        Label_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_HomeMouseClicked(evt);
            }
        });

        Panel_Hospit.setBackground(new java.awt.Color(153, 153, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Nom", "Chambre"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        Label_Vue_CR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/files-and-folders.png"))); // NOI18N
        Label_Vue_CR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_Vue_CRMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_HospitLayout = new javax.swing.GroupLayout(Panel_Hospit);
        Panel_Hospit.setLayout(Panel_HospitLayout);
        Panel_HospitLayout.setHorizontalGroup(
            Panel_HospitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_HospitLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(Panel_HospitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_HospitLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_HospitLayout.createSequentialGroup()
                        .addComponent(Label_Vue_CR)
                        .addGap(115, 115, 115))))
        );
        Panel_HospitLayout.setVerticalGroup(
            Panel_HospitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_HospitLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Label_Vue_CR)
                .addContainerGap())
        );

        Panel_Actes.setBackground(new java.awt.Color(153, 153, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Motif", "Prestation", "Editeur", "Date", "Resumé", "Documents"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        Label_Plus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        Label_Plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_PlusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_ActesLayout = new javax.swing.GroupLayout(Panel_Actes);
        Panel_Actes.setLayout(Panel_ActesLayout);
        Panel_ActesLayout.setHorizontalGroup(
            Panel_ActesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ActesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_Plus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_ActesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel_ActesLayout.setVerticalGroup(
            Panel_ActesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ActesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(Label_Plus)
                .addContainerGap())
        );

        Panel_Soins.setBackground(new java.awt.Color(153, 153, 255));

        Table_Soins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Soins", null, null, null, null, null, null},
                {"Documents", null, null, null, null, null, null}
            },
            new String [] {
                "Heure", "8h", "9h", "10h", "11h", "12h", "13h"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(Table_Soins);

        javax.swing.GroupLayout Panel_SoinsLayout = new javax.swing.GroupLayout(Panel_Soins);
        Panel_Soins.setLayout(Panel_SoinsLayout);
        Panel_SoinsLayout.setHorizontalGroup(
            Panel_SoinsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_SoinsLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(158, 158, 158))
        );
        Panel_SoinsLayout.setVerticalGroup(
            Panel_SoinsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SoinsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_Gauche.setBackground(new java.awt.Color(153, 153, 255));
        Panel_Gauche.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Label_Icon_Patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil.png"))); // NOI18N

        Label_Identite.setText("Identité");

        Label_Modifications.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/editer.png"))); // NOI18N
        Label_Modifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_ModificationsMouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Arch.");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_GaucheLayout = new javax.swing.GroupLayout(Panel_Gauche);
        Panel_Gauche.setLayout(Panel_GaucheLayout);
        Panel_GaucheLayout.setHorizontalGroup(
            Panel_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GaucheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Icon_Patient)
                .addGap(13, 13, 13)
                .addComponent(Label_Identite, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(6, 6, 6)
                .addComponent(Label_Modifications)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_GaucheLayout.setVerticalGroup(
            Panel_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GaucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Modifications)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_GaucheLayout.createSequentialGroup()
                .addGroup(Panel_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label_Identite, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_Icon_Patient))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_MainLayout = new javax.swing.GroupLayout(Panel_Main);
        Panel_Main.setLayout(Panel_MainLayout);
        Panel_MainLayout.setHorizontalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_MainLayout.createSequentialGroup()
                .addGroup(Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_MainLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_Loupe_Patient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Label_Plus_Patient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 517, Short.MAX_VALUE)
                        .addComponent(Label_Home))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_MainLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Panel_Gauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(Panel_Hospit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Actes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(Panel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Soins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Panel_MainLayout.setVerticalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Home)
                    .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_Loupe_Patient)
                    .addComponent(Label_Plus_Patient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_MainLayout.createSequentialGroup()
                        .addComponent(Panel_Hospit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                        .addComponent(Panel_Gauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addComponent(Panel_Soins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_Actes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextField_PatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_PatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_PatientActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Label_Loupe_PatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Loupe_PatientMouseClicked
        Recherche_Patient i = new Recherche_Patient();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_Label_Loupe_PatientMouseClicked

    private void Label_Plus_PatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Plus_PatientMouseClicked
        New_Patient i = new New_Patient();
        i.setVisible(true);
    }//GEN-LAST:event_Label_Plus_PatientMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Connexion i = new Connexion();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void Label_HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_HomeMouseClicked
        Vue_Medicaments i = new Vue_Medicaments();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_Label_HomeMouseClicked

    private void Label_ModificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_ModificationsMouseClicked
        Modif_Patient i = new Modif_Patient();
        i.setVisible(true);
    }//GEN-LAST:event_Label_ModificationsMouseClicked

    private void Label_Vue_CRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_Vue_CRMouseClicked
        Vue_Dossier_Patient_Med i = new Vue_Dossier_Patient_Med();
        i.setVisible(true);
    }//GEN-LAST:event_Label_Vue_CRMouseClicked

    private void Label_PlusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_PlusMouseClicked
        Ajout_FS i = new Ajout_FS(); // ajout acte tout court pas forcement SM
        i.setVisible(true);
    }//GEN-LAST:event_Label_PlusMouseClicked

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
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vue_Patient_Medicaments_Inf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Identite;
    private javax.swing.JLabel Label_Home;
    private javax.swing.JLabel Label_Icon_Patient;
    private javax.swing.JLabel Label_Identite;
    private javax.swing.JLabel Label_Loupe_Patient;
    private javax.swing.JLabel Label_Modifications;
    private javax.swing.JLabel Label_Plus;
    private javax.swing.JLabel Label_Plus_Patient;
    private javax.swing.JLabel Label_Vue_CR;
    private javax.swing.JPanel Panel_Actes;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Gauche;
    private javax.swing.JPanel Panel_Hospit;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JPanel Panel_Soins;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JTable Table_Soins;
    private javax.swing.JTextField TextField_Patient;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
    
   
    
}
