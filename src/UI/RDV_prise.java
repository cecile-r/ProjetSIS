/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static database.RequetesBDProfessionnels.getListePHService;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import nf.*;
import static nf.Checker.getVectorPHRDV;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Audrey
 */
public class RDV_prise extends javax.swing.JFrame {

    Connection conn;
    SecretaireAdministrative sa;
    DPI dpi;

    /**
     * Creates new form Connexion
     */
    public RDV_prise(Connection conn, SecretaireAdministrative sa, DPI dpi) {

        initComponents();
        this.sa = sa;
        this.dpi = dpi;
        this.conn = conn;

        //cacher 
        jPanel_rdv.setVisible(false);
        jPanel_rdv2.setVisible(false);
        jLabel5.setVisible(false);
        jComboBox2.setVisible(false);

        //infos identité
        prenom.setText(sa.getPrenomSecretaireAd());
        nom.setText(sa.getNomSecretaireAd());

        //images
        ImageIcon iconeC = new ImageIcon("src/image/logo connexa-modified.png");
        java.awt.Image imgC = iconeC.getImage();
        iconeC = new ImageIcon(imgC);
        Panel_logo.setIcon(iconeC);
        ImageIcon iconeP = new ImageIcon("src/image/profil 2.png");
        java.awt.Image imgP = iconeP.getImage();
        iconeP = new ImageIcon(imgP);
        Panel_icon_perso.setIcon(iconeP);
        ImageIcon iconeD = new ImageIcon("src/image/se-deconnecter.png");
        java.awt.Image imgD = iconeD.getImage();
        iconeD = new ImageIcon(imgD);
        jButton4.setIcon(iconeD);
        ImageIcon iconeH = new ImageIcon("src/image/home.png");
        java.awt.Image imgH = iconeH.getImage();
        iconeH = new ImageIcon(imgH);
        jButton9.setIcon(iconeH);

        //Jbutton images
        ImageIcon icone3 = new ImageIcon("src/image/retour.png");
        java.awt.Image img3 = icone3.getImage();
        icone3 = new ImageIcon(img3);
        jButton9.setIcon(icone3);

        //Jcomcobox
        DefaultComboBoxModel dbm1 = new DefaultComboBoxModel(Service.values());
        jComboBox1.setModel(dbm1);

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
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_rdv = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel_rdv2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        prenom.setBackground(new java.awt.Color(204, 204, 255));
        prenom.setText("prenom");

        nom.setBackground(new java.awt.Color(204, 204, 255));
        nom.setText("nom");

        jButton4.setBackground(new java.awt.Color(204, 102, 255));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 102, 255));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(prenom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(169, 206, 243));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        jLabel1.setText("Prendre rendez-vous");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_rdv.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel_rdvLayout = new javax.swing.GroupLayout(jPanel_rdv);
        jPanel_rdv.setLayout(jPanel_rdvLayout);
        jPanel_rdvLayout.setHorizontalGroup(
            jPanel_rdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        jPanel_rdvLayout.setVerticalGroup(
            jPanel_rdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Service :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Professionnel :");

        jPanel_rdv2.setBackground(new java.awt.Color(169, 206, 243));

        jButton1.setBackground(new java.awt.Color(169, 206, 243));
        jButton1.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton1.setText("8H");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(169, 206, 243));
        jButton2.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton2.setText("9H");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(169, 206, 243));
        jButton3.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton3.setText("11H");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(169, 206, 243));
        jButton5.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton5.setText("14H");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(169, 206, 243));
        jButton6.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton6.setText("15H");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(169, 206, 243));
        jButton7.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton7.setText("16H");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(169, 206, 243));
        jButton8.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton8.setText("10H");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(169, 206, 243));
        jButton10.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jButton10.setText("13H");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_rdv2Layout = new javax.swing.GroupLayout(jPanel_rdv2);
        jPanel_rdv2.setLayout(jPanel_rdv2Layout);
        jPanel_rdv2Layout.setHorizontalGroup(
            jPanel_rdv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rdv2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel_rdv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel_rdv2Layout.setVerticalGroup(
            jPanel_rdv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rdv2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel3PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_rdv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_rdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel_rdv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_rdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Connexion i;
        try {
            i = new Connexion(conn);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Vue_Patient_SA i;
        i = new Vue_Patient_SA(conn, dpi, sa);
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel3PropertyChange
        //ON CLIQUE A DROITE 
        if (!jLabel3.getText().equals("")) {
            jPanel_rdv2.setVisible(true);
        }
    }//GEN-LAST:event_jLabel3PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //8H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = ch + dh.getJour() + "/" + dh.getMois() + "/" + dh.getAnnee() + " à 8h";
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //9H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(9);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //10H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(10);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //11H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(11);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //13H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(13);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //14H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(14);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //15H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(15);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //16H
        DateHeure dh = DateHeure.convertirStringtoDateHeure(jLabel3.getText());
        dh.setHeure(16);
        String ch = "";
        ch = "Confimez-vous le rendez-vous suivant ?\n";
        ch = DateHeure.convertirDateHeuretoString(dh);
        int retour = JOptionPane.showConfirmDialog(this, ch, "Vérification du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
        if (retour == 0) {
            //ajouter le rdv
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //SELECTION D'UN SERVICE
        jLabel5.setVisible(true);
        jComboBox2.setVisible(true);

        List<PH> phs;
        try {
            Service s = (Service) jComboBox1.getSelectedItem();
            System.out.println(s.toString());
            phs = getListePHService(conn, s.toString());
            Vector phsS = getVectorPHRDV(phs);
            DefaultComboBoxModel dbm2 = new DefaultComboBoxModel(phsS);
            jComboBox2.setModel(dbm2);
        } catch (SQLException ex) {
            Logger.getLogger(RDV_prise.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        //SELECTION D'UN PROFESSIONNEL
        //calendrier
        jPanel_rdv.setVisible(true);
        LocalDate current_date = LocalDate.now();
        int current_Year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        Calendrier calendrier = new Calendrier(100, 20, current_month, current_Year, jPanel_rdv, jLabel3);

    }//GEN-LAST:event_jComboBox2ActionPerformed

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
                /*
                Infirmier inf1 = new Infirmier("3587492736", "Lo", "Anna", Service.Biologie_clinique, "momodepasse");
                PH ph1 = new PH("1616161616", "Pan", "Peter", Service.Biologie_clinique, "peterpan", "0456486756", "Biologie");

                Acte a11 = new Acte("prise de sang", nf.Type.diagnostic, Code.CS, 2, "RAS");
                Acte a12 = new Acte("changement pansement", nf.Type.therapeutique, Code.FP, 1, "Propre");
                DateHeure d11 = new DateHeure(2022, 02, 10, 10, 00); //date Fiche de soins
                FicheDeSoins fs1 = new FicheDeSoins(d11);
                fs1.setpH(ph1);
                fs1.ajouterActe(a11);
                fs1.ajouterActe(a12);
                DateHeure d12 = new DateHeure(2022, 02, 10, 11, 00); //date presciption
                Prescription p1 = new Prescription(d12, "a prendre 2 fois par jour pendant 7 jours", null, "Doliprane");
                p1.setpH(ph1);
                DateHeure d13 = new DateHeure(2022, 02, 10, 14, 00); //date de sortie
                LettreDeSortie ls1 = new LettreDeSortie("Madame ... sort de l'hopital", d13);
                ls1.setPh(ph1);
                DateHeure d14 = new DateHeure(2022, 02, 10, 10, 30); //date de soin quotidien
                DateHeure d15 = new DateHeure(2022, 02, 10, 8, 30); //date de soin quotidien
                SoinsQuotidien sq1 = new SoinsQuotidien(37.5, 80, 12.8, "RSA", d14);
                SoinsQuotidien sq2 = new SoinsQuotidien(37.0, 80, 12.9, "RSA", d15);
                sq1.setInfirmier(inf1);
                sq2.setInfirmier(inf1);
                DateHeure d16 = new DateHeure(2022, 02, 10, 11, 30); //date de l'examen
                Examen e1 = new Examen(TypeExamen.biopsie, "cancer trouvé", d16);
                e1.setPh(ph1);
                DM dm1 = new DM();
                dm1.ajouterFicheDeSoins(fs1);
                dm1.ajouterLettreDeSortie(ls1);
                dm1.ajouterPrescription(p1);
                dm1.ajouterSoinsQuotidien(sq1);
                dm1.ajouterSoinsQuotidien(sq2);
                dm1.ajouterExamen(e1);
                Localisation l1 = new Localisation(Service.Biologie_clinique, Lit.F, 12, Service.Addictologie);
                DMA dma1 = new DMA(l1);
                dma1.ajouterFicheDeSoins(fs1);
                dma1.ajouterLettreDeSortie(ls1);
                dma1.ajouterExamen(e1);
                DateHeure d17 = new DateHeure(2022, 02, 15, 10, 00); //date de RDV
                RendezVous rdv1 = new RendezVous(d17, "reverifier les resultats");
                rdv1.setpH(ph1);
                dma1.ajouterRendezVous(rdv1);
                MedecinTraitant mt1 = new MedecinTraitant("id@gmail.com", "PAT", "PATROUILLE", "0467894567");
                Date dn1 = new Date(1997, 07, 13); //date de naissance
                DPI dpi1 = new DPI("1234567891", "Jones", "Jack", dn1, Sexe.homme, "3 rue Beranger, 45000 Tours", "0657985613", mt1, dma1, dm1);

                inf1.ajouterSoinsQuotidien(sq1);
                inf1.ajouterSoinsQuotidien(sq2);
                ph1.ajouterFicheDeSoins(fs1);
                ph1.ajouterLettresDeSorties(ls1);
                ph1.ajouterPrescriptions(p1);
                ph1.ajouterExamen(e1);
                ph1.ajouterRdv(rdv1);
                sq1.setDPI(dpi1);
                sq2.setDPI(dpi1);
                fs1.setDPI(dpi1);
                ls1.setDPI(dpi1);
                p1.setDPI(dpi1);
                e1.setDPI(dpi1);
                rdv1.setDPI(dpi1);
                PH ph = new PH("1616161616", "Pan", "Peter", Service.Addictologie, "peterpan", "0456486756", "Biologie");
                SecretaireAdministrative sa = new SecretaireAdministrative("5678452345", "Blabla", "car", "car");

                RDV_prise i = new RDV_prise(sa, dpi1);
                i.setVisible(true);
                 */
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_rdv;
    private javax.swing.JPanel jPanel_rdv2;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel prenom;
    // End of variables declaration//GEN-END:variables

}
