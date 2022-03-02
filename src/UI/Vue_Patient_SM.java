/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import database.DatabaseAccessProperties;
import database.SQLWarningsExceptions;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nf.*;
import static nf.Checker.convertirDatetoString;
import static nf.ComparaisonEvaluables.trierEvaluables;
import static nf.DateHeure.convertirDatetoString;

/**
 *
 * @author Audrey
 */
public class Vue_Patient_SM extends javax.swing.JFrame {

    //Connection conn;
    SecretaireMedicale sm;
    DPI dpi;
    Vector entetesL;
    Vector localisation;
    Vector entetesD;
    Vector documents;

    /**
     * Creates new form Connexion
     */
    public Vue_Patient_SM(DPI dpi, SecretaireMedicale sm) {
        //this.conn = conn;
        this.sm = sm;
        this.dpi = dpi;
        initComponents();

        //infos identité
        prenom_SM.setText(sm.getPrenomSecretaireMed());
        nom_SM.setText(sm.getNomSecretaireMed());
        service2.setText(sm.getService().toString());
        
        //infos du patient
        jLabel10.setText(dpi.getNom());
        jLabel11.setText(dpi.getPrenom());
        jLabel12.setText(dpi.getSexe().toString());
        String dN = convertirDatetoString(dpi.getDateNaissance());
        jLabel13.setText(dN);
        
        //images button
        ImageIcon icone1 = new ImageIcon("src/image/consultation_rdv.png");
        java.awt.Image img1 = icone1.getImage();
        icone1 = new ImageIcon(img1);
        jButton_consultation.setIcon(icone1);
        ImageIcon icone2 = new ImageIcon("src/image/rdv.png");
        java.awt.Image img2 = icone2.getImage();
        icone2 = new ImageIcon(img2);
        jButton_prendreRDV.setIcon(icone2);
        ImageIcon icone3 = new ImageIcon("src/image/petit_crayon.png");
        java.awt.Image img3 = icone3.getImage();
        icone3 = new ImageIcon(img3);
        jButton_modifier.setIcon(icone3);
        ImageIcon icone4 = new ImageIcon("src/image/fermer.png");
        java.awt.Image img4 = icone4.getImage();
        icone4 = new ImageIcon(img4);
        jButton_fermeture.setIcon(icone4);
        
        ImageIcon icone6 = new ImageIcon("src/image/pdf.png");
        
        
        //localisation
        entetesL = new Vector();
        entetesL.add("Service");
        entetesL.add("Service géographique");
        entetesL.add("Chambre");
        entetesL.add("Lit");
        localisation = new Vector();
        Vector localisation1 = new Vector();
        localisation1.add(dpi.getdMA().getLocalisation().getService_responsable().toString());
        localisation1.add(dpi.getdMA().getLocalisation().getService_geographique().toString());
        localisation1.add(dpi.getdMA().getLocalisation().getNchambre());
        localisation1.add(dpi.getdMA().getLocalisation().getLit());
        localisation.add(localisation1);
        TableModel tableModelL = new DefaultTableModel(localisation, entetesL);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(tableModelL);

        //Documents
        entetesD = new Vector();
        entetesD.add("Type de document");
        entetesD.add("Date");
        entetesD.add("Professionnel");
        entetesD.add("Document");
        documents = new Vector();
        List<Evaluable> document_temp = new ArrayList<Evaluable>();
        document_temp = new Vector<>();
        document_temp.addAll(dpi.getdMA().getFicheDeSoins());
        document_temp.addAll(dpi.getdMA().getLettreDeSortie());
        document_temp.addAll(dpi.getdMA().getExamens());
        document_temp = trierEvaluables(document_temp);
        Vector document1 = new Vector();
        for (int i = 0; i < document_temp.size(); i++) { //pour tous les documents
            Evaluable e = document_temp.get(i);
            document1.add(e.getTypeEvaluable());
            document1.add(convertirDatetoString(e.getDateHeure()));
            document1.add(e.getProfessionnel().toString());
            document1.add(icone6);
            documents.add(document1);
            document1 = new Vector();
        }
        //TABLEAU DOCUMENTS
        TableModel tableModelD = new DefaultTableModel(documents, entetesD);
        jTable2 = new javax.swing.JTable(tableModelD) {
            /*détection automatique des types de données             
             *de toutes les colonnes    
             */
            public Class getColumnClass(int colonne) {
                return getValueAt(0, colonne).getClass();
            }
        };
        jTable2.setBackground(new java.awt.Color(225, 225, 249));
        jTable2.setGridColor(new java.awt.Color(153, 153, 153));
        jTable2.setPreferredSize(new java.awt.Dimension(300, 300));
        jTable2.setRowHeight(70);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTable2MouseClicked(evt);
                } catch (IOException ex) {
                    Logger.getLogger(Vue_Patient_Med.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jScrollPane5.setViewportView(jTable2);
        jTable2.setDefaultEditor(Object.class, null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Panel_icon_perso = new javax.swing.JLabel();
        Panel_icon_perso2 = new javax.swing.JLabel();
        service = new javax.swing.JLabel();
        prenom_medecin1 = new javax.swing.JLabel();
        nom_medecin = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso1 = new javax.swing.JLabel();
        prenom_SM = new javax.swing.JLabel();
        nom_SM = new javax.swing.JLabel();
        service2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Panle_Gauche = new javax.swing.JPanel();
        Label_Icon_Patient = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel_rendezvous = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_consultation = new javax.swing.JButton();
        jButton_prendreRDV = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea_affichage = new javax.swing.JTextArea();
        jButton_modifier = new javax.swing.JButton();
        jButton_fermeture = new javax.swing.JButton();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Service", "Service géographique ", "Chambre "
            }
        ));
        jTable3.setPreferredSize(new java.awt.Dimension(225, 30));
        jScrollPane3.setViewportView(jTable3);

        Panel_icon_perso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil 2.png"))); // NOI18N

        Panel_icon_perso2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil 2.png"))); // NOI18N

        service.setText("service");

        prenom_medecin1.setText("prenom_medecin");

        nom_medecin.setText("nom_medecin");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Panel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo connexa-modified.png"))); // NOI18N

        Panel_icon_perso1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil 2.png"))); // NOI18N

        prenom_SM.setText("prenom_medecin");

        nom_SM.setText("nom_medecin");

        service2.setText("service");

        jButton2.setBackground(new java.awt.Color(204, 102, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/se-deconnecter.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 102, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(Panel_icon_perso1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom_SM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom_SM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Panel_logo))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(prenom_SM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom_SM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(service2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panle_Gauche.setBackground(new java.awt.Color(250, 247, 247));
        Panle_Gauche.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panle_Gauche.setAutoscrolls(true);

        Label_Icon_Patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Nom :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Prénom :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setText("Sexe :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setText("Date de naissance :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("nom");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("prenom");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("sexe");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("date");

        javax.swing.GroupLayout Panle_GaucheLayout = new javax.swing.GroupLayout(Panle_Gauche);
        Panle_Gauche.setLayout(Panle_GaucheLayout);
        Panle_GaucheLayout.setHorizontalGroup(
            Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panle_GaucheLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Label_Icon_Patient)
                .addGap(18, 18, 18)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panle_GaucheLayout.setVerticalGroup(
            Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panle_GaucheLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Icon_Patient, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panle_GaucheLayout.createSequentialGroup()
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11)
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_rendezvous.setBackground(new java.awt.Color(169, 206, 243));
        jPanel_rendezvous.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendrier.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("Prochain rendez-vous :");

        jLabel3.setText("12/03/2022 à 12:00");

        jButton_consultation.setBackground(new java.awt.Color(169, 206, 243));
        jButton_consultation.setText("Consulter les rendez-vous");
        jButton_consultation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton_prendreRDV.setBackground(new java.awt.Color(169, 206, 243));
        jButton_prendreRDV.setText("Prendre rendez-vous");
        jButton_prendreRDV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton_prendreRDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_prendreRDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_rendezvousLayout = new javax.swing.GroupLayout(jPanel_rendezvous);
        jPanel_rendezvous.setLayout(jPanel_rendezvousLayout);
        jPanel_rendezvousLayout.setHorizontalGroup(
            jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rendezvousLayout.createSequentialGroup()
                .addGroup(jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_rendezvousLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_rendezvousLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_rendezvousLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)))
                .addGap(23, 23, 23)
                .addGroup(jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_prendreRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_consultation, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        jPanel_rendezvousLayout.setVerticalGroup(
            jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rendezvousLayout.createSequentialGroup()
                .addGroup(jPanel_rendezvousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_rendezvousLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel_rendezvousLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton_consultation, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_prendreRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Service", "Service géographique ", "Chambre "
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setEnabled(false);
        jTable1.setPreferredSize(new java.awt.Dimension(225, 27));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane5.setBackground(new java.awt.Color(204, 204, 255));

        JTextArea_affichage.setBackground(new java.awt.Color(204, 204, 255));
        JTextArea_affichage.setColumns(20);
        JTextArea_affichage.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        JTextArea_affichage.setRows(5);
        JTextArea_affichage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(JTextArea_affichage);

        jButton_modifier.setBackground(new java.awt.Color(204, 204, 255));
        jButton_modifier.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        jButton_modifier.setText("Modifier le patient");
        jButton_modifier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jButton_fermeture.setBackground(new java.awt.Color(204, 204, 255));
        jButton_fermeture.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        jButton_fermeture.setText("Fermer le dossier");
        jButton_fermeture.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Panle_Gauche, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modifier, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jButton_fermeture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_rendezvous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Panle_Gauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_fermeture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_rendezvous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(50, Short.MAX_VALUE))
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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) throws IOException {
        //AFFICHAGE DU DOCUMENT SUR LA DROITE
        List<Evaluable> document_temp = new ArrayList<Evaluable>();
        document_temp = new Vector<>();
        document_temp.addAll(dpi.getdMA().getFicheDeSoins());
        document_temp.addAll(dpi.getdMA().getLettreDeSortie());
        document_temp.addAll(dpi.getdMA().getExamens());
        int i = jTable2.getSelectedRow();
        String ch = document_temp.get(i).toStringDMA();
        JTextArea_affichage.setText(ch);

        if (jTable2.getSelectedColumn() == 3) { //si on clique sur l'image pdf
            Document document = new Document(PageSize.A4);
            try {
                PdfWriter.getInstance(document,
                        new FileOutputStream("document.pdf"));
                document.open();
                document.add(new Paragraph(ch));
            } catch (DocumentException de) {
                de.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            document.close();
            Desktop.getDesktop().open(new File("document.pdf"));
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*Connexion i;
        try {
            i = new Connexion(conn);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton_prendreRDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_prendreRDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_prendreRDVActionPerformed

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

                // Get a connection to the database
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_SM i;
                i = new Vue_Patient_SM(dpi1,new SecretaireMedicale("1462354712", "Boss", "Pierre", Service.Biologie_clinique, "maison"));
                i.setSize(longueur, hauteur);
                i.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTextArea_affichage;
    private javax.swing.JLabel Label_Icon_Patient;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_icon_perso1;
    private javax.swing.JLabel Panel_icon_perso2;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JPanel Panle_Gauche;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton_consultation;
    private javax.swing.JButton jButton_fermeture;
    private javax.swing.JButton jButton_modifier;
    private javax.swing.JButton jButton_prendreRDV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_rendezvous;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel nom_SM;
    private javax.swing.JLabel nom_medecin;
    private javax.swing.JLabel prenom_SM;
    private javax.swing.JLabel prenom_medecin1;
    private javax.swing.JLabel service;
    private javax.swing.JLabel service2;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTable jTable2;
}
