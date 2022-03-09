/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static database.RequetesBDDPI.creerLettreSortie;
import static database.RequetesBDDPI.getDPI;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nf.*;
import static nf.Checker.getVectorActes;
import java.sql.Connection;

/**
 *
 * @author Audrey
 */
public class Ajout_lettreDeSortie extends javax.swing.JFrame {

    //Connection conn;
    PH ph;
    DPI dpi;
    Connection conn;
    List<Acte> actes;
    Vector actesS;
    Vector entetes;
    

    /**
     * Creates new form Modif_Patient
     */
    public Ajout_lettreDeSortie(Connection conn, PH ph, DPI dpi) {
        initComponents();
        actes = new Vector();
        this.ph = ph;
        this.dpi=dpi;
        this.conn=conn;

        //infos identité
        prenom.setText(ph.getPrenomPH());
        nom.setText(ph.getNomPH());

        //date courante
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateL = dtf.format(LocalDateTime.now());
        jLabel3.setText(dateL);
        
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
        ImageIcon icone2 = new ImageIcon("src/image/plus.png");
        java.awt.Image img2 = icone2.getImage();
        icone2 = new ImageIcon(img2);
        jButton2.setIcon(icone2);
        ImageIcon icone3 = new ImageIcon("src/image/retour.png");
        java.awt.Image img3 = icone3.getImage();
        icone3 = new ImageIcon(img3);
        jButton9.setIcon(icone3);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();

        jButton8.setBackground(new java.awt.Color(204, 102, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        setTitle("Modifications");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Date : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("date");

        jButton2.setBackground(new java.awt.Color(204, 102, 255));
        jButton2.setText("Ajouter la lettre de sortie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel1.setText("Nouvelle lettre de sortie");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane2.setViewportView(jTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(105, Short.MAX_VALUE))
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         try {
            Connexion i;
            i = new Connexion(conn);
            i.setVisible(true);
            dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ajout_examen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ajout_examen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        /*Connexion i;
        try {
            i = new Accueil_Med(conn);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
                String IPP = dpi.getIPP();
                DPI dpi2 = getDPI(conn, IPP);
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_Med i;
                i = new Vue_Patient_Med(conn, dpi2, ph);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Ajout_lettreDeSortie.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //CREER lettreDeSortie
        LocalDateTime ldt = LocalDateTime.now();
        DateHeure dh = new DateHeure(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute());
        
        if(champsCorrects()){
            String texte = jTextArea.getText();
            LettreDeSortie ls =new LettreDeSortie(texte,dh);
            ls.setDPI(dpi);
            ls.setPh(ph);
            
            ///AJOUT BD
            try {
                creerLettreSortie(conn,ls);
            } catch (SQLException ex) {
                Logger.getLogger(Ajout_lettreDeSortie.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ///REVENIR PAGE PRECEDENTE
            jButton9ActionPerformed(evt);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public boolean champsCorrects() {
        
        boolean v = true;
        if (jTextArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer la lettre de sortie", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        }else if (jTextArea.getText().length()>800) {
            JOptionPane.showMessageDialog(this, "Texte trop long", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        }
        return v;
    }

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
            java.util.logging.Logger.getLogger(Ajout_FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajout_FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajout_FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajout_FS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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

                PH ph = new PH("1616161616", "Pan", "Peter", Service.Biologie_clinique, "peterpan", "0456486756", "Biologie");
                Ajout_lettreDeSortie i = new Ajout_lettreDeSortie(ph, dpi1);
                i.setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel prenom;
    // End of variables declaration//GEN-END:variables

}