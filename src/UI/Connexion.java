/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import database.DatabaseAccessProperties;
import database.SQLWarningsExceptions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import database.*;
import nf.PH;
import nf.SecretaireAdministrative;
import nf.SecretaireMedicale;

/**
 *
 * @author Audrey et gwen le boss du game
 */
public class Connexion extends javax.swing.JFrame {

    Connection conn;

    /**
     * Creates new form Connexion
     */
    public Connexion(Connection conn) throws ClassNotFoundException, SQLException {

        this.conn = conn;
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Main = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Label_Main = new javax.swing.JLabel();
        Label_Identifiant = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        Button_Connexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(0, 0));

        Panel_Main.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo connexa-modified.png"))); // NOI18N

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap(684, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Label_Main.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Main.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil.png"))); // NOI18N

        Label_Identifiant.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        Label_Identifiant.setForeground(new java.awt.Color(153, 153, 153));
        Label_Identifiant.setText("Identifiant");
        Label_Identifiant.setToolTipText("Entrez votre identifiant");
        Label_Identifiant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Label_IdentifiantMouseClicked(evt);
            }
        });
        Label_Identifiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Label_IdentifiantActionPerformed(evt);
            }
        });

        jPasswordField1.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setToolTipText("Entrez votre mot de passe");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Button_Connexion.setBackground(new java.awt.Color(255, 255, 255));
        Button_Connexion.setText("Connexion");
        Button_Connexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ConnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Button_Connexion)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Button_Connexion)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_MainLayout = new javax.swing.GroupLayout(Panel_Main);
        Panel_Main.setLayout(Panel_MainLayout);
        Panel_MainLayout.setHorizontalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Label_Identifiant)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_MainLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(307, 307, 307))
        );
        Panel_MainLayout.setVerticalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(Label_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Label_Identifiant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_ConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ConnexionActionPerformed
        //recupération type de personne
        String CB_item;

        //recupération id et mdp
        String id = Label_Identifiant.getText();
        String mdp = new String(jPasswordField1.getPassword());
        
        //dimension fenetre
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width;
        int hauteur = tailleMoniteur.height;
        boolean c;
        try {
            CB_item = database.RequetesBD.getStatut(conn,id);
            System.out.println(CB_item);
            c = database.RequetesBD.verifyConnexion(conn, id, mdp, CB_item);
            if (c) {
                if (CB_item.equals("PH")) {
                    System.out.println("rentrer");
                    PH ph = database.RequetesBD.userPH(conn,id);
                    Accueil_Med i;
                    i = new Accueil_Med(conn,ph);
                    i.setSize(longueur, hauteur);
                    i.setVisible(true);
                    dispose();
                }
                if (CB_item.equals("SA")) {
                    SecretaireAdministrative sa =database.RequetesBD.userSA(conn,id);
                    Accueil_SA i;
                    i = new Accueil_SA(conn,sa);
                    i.setSize(longueur, hauteur);
                    i.setVisible(true);
                    dispose();
                }
                if (CB_item.equals("SM")) {
                    SecretaireMedicale sm =database.RequetesBD.userSM(conn,id);
                    Accueil_SM i;
                    i = new Accueil_SM(conn,sm);
                    i.setSize(longueur, hauteur);
                    i.setVisible(true);
                    dispose();

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        if (CB_item.equals("Inf")){
            //requete bd si id et mdp correct
            Vue_Medicaments i;
            i = new Vue_Medicaments(conn);
            i.setSize(longueur, hauteur);
            i.setVisible(true);
            dispose();
        }*/
    }//GEN-LAST:event_Button_ConnexionActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void Label_IdentifiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Label_IdentifiantActionPerformed
        Label_Identifiant.setText("");
    }//GEN-LAST:event_Label_IdentifiantActionPerformed

    private void Label_IdentifiantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Label_IdentifiantMouseClicked
        Label_Identifiant.setText("");
        Label_Identifiant.setForeground(Color.black);
    }//GEN-LAST:event_Label_IdentifiantMouseClicked

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
                try {
                    String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
                    String dbUrl = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima";
                    String username;
                    String password;

                    DatabaseAccessProperties dap = new DatabaseAccessProperties("src/database/BD.properties");
                    jdbcDriver = dap.getJdbcDriver();
                    dbUrl = dap.getDatabaseUrl();
                    username = dap.getUsername();
                    password = dap.getPassword();

                    // Load the database driver
                    Class.forName(jdbcDriver);

                    // Get a connection to the database
                    Connection conn = DriverManager.getConnection(dbUrl, username, password);
                    SQLWarningsExceptions.printWarnings(conn);

                    Connexion i;
                    i = new Connexion(conn);
                    i.setVisible(true);

                } catch (SQLException se) {
                    // Print information about SQL exceptions
                    SQLWarningsExceptions.printExceptions(se);
                    return;

                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                    e.printStackTrace();
                    return;
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Connexion;
    private javax.swing.JTextField Label_Identifiant;
    private javax.swing.JLabel Label_Main;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables

}
