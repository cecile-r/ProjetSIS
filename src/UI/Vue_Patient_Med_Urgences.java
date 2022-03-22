/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import HL7.HL7_SIH;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.sql.Connection;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nf.Acte;
import static nf.Checker.convertirDatetoString;
import nf.*;
import static nf.ComparaisonEvaluables.trierEvaluables;
import database.DatabaseAccessProperties;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import static nf.Checker.getVectorSoinsQuotidien;
import static nf.Checker.getVectorSoinsQuotidienPH;
import static nf.ComparaisonEvaluables.trierEvaluables;
import static nf.DateHeure.convertirDateHeuretoString;
import static nf.DateHeure.estApresDateCourante;

public class Vue_Patient_Med_Urgences extends javax.swing.JFrame {

    Connection conn;
    Vector entetesL;
    Vector localisation;
    Vector entetesD;
    Vector documents;
    DPITemporaire dpi;
    PH ph;
    Vector entetesC;
    List<Evaluable> constantes = new ArrayList<>();

    /**
     * Creates new form Connexion
     */
    public Vue_Patient_Med_Urgences(Connection conn, DPITemporaire dpi, PH ph) {
        initComponents();
        this.conn = conn;
        this.dpi = dpi;
        this.ph = ph;

        //infos identité connexion
        prenom_medecin1.setText(ph.getPrenomPH());
        nom_medecin.setText(ph.getNomPH());
        service.setText(ph.getService().toString());

        //infos du patient
        jLabel10.setText(dpi.getNom());
        jLabel11.setText(dpi.getPrenom());
        String dN = nf.Checker.convertirDatetoString(dpi.getDate_naissance());
        jLabel13.setText(dN);

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
        jButton2.setIcon(iconeD);
        ImageIcon iconeH = new ImageIcon("src/image/home.png");
        java.awt.Image imgH = iconeH.getImage();
        iconeH = new ImageIcon(imgH);
        jButton8.setIcon(iconeH);
        ImageIcon iconeP2 = new ImageIcon("src/image/profil.png");
        java.awt.Image imgP2 = iconeP2.getImage();
        iconeP2 = new ImageIcon(imgP2);
        Label_Icon_Patient.setIcon(iconeP2);
        

        //images des boutons
        ImageIcon icone1 = new ImageIcon("src/image/prescription.png");
        java.awt.Image img1 = icone1.getImage();
        icone1 = new ImageIcon(img1);
        jButton5.setIcon(icone1);
        ImageIcon icone2 = new ImageIcon("src/image/ficheDeSoins.png");
        java.awt.Image img2 = icone2.getImage();
        icone2 = new ImageIcon(img2);
        jButton4.setIcon(icone2);
        ImageIcon icone3 = new ImageIcon("src/image/examen.png");
        java.awt.Image img3 = icone3.getImage();
        icone3 = new ImageIcon(img3);
        jButton6.setIcon(icone3);
        
        ImageIcon icone6 = new ImageIcon("src/image/pdf.png");


        //Documents
        entetesD = new Vector();
        entetesD.add("Type de document");
        entetesD.add("Date");
        entetesD.add("Professionnel");
        entetesD.add("Document");
        documents = new Vector();
        List<Evaluable> document_temp = new ArrayList<Evaluable>();
        document_temp = new Vector<>();
        document_temp.addAll(this.dpi.getListe_f());
        document_temp.addAll(this.dpi.getListe_p());
        document_temp.addAll(this.dpi.getListe_e());
        document_temp = trierEvaluables(document_temp);
        Vector document1 = new Vector();
        for (int i = 0; i < document_temp.size(); i++) { //pour tous les documents
            Evaluable e = document_temp.get(i);
            document1.add(e.getTypeEvaluable());
            document1.add(convertirDateHeuretoString(e.getDateHeure()));
            document1.add(e.getProfessionnel().toString());
            document1.add(icone6);
            //document1.add(e.getContenu());
            //document1.add(e.getObservations());
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
        jTable2.setPreferredSize(new java.awt.Dimension(3000, 70 * jTable2.getRowCount()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prenom_medecin = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_Vue_Generale1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom_medecin1 = new javax.swing.JLabel();
        nom_medecin = new javax.swing.JLabel();
        service = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Panle_Gauche = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Label_Icon_Patient = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea_affichage = new javax.swing.JTextArea();

        prenom_medecin.setText("prenom_medecin");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        Table_Vue_Generale1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nom", "Prénom", "Date de naissance", "Sexe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(300, 300));
        Table_Vue_Generale1.setRowHeight(20);
        jScrollPane4.setViewportView(Table_Vue_Generale1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setBackground(new java.awt.Color(204, 204, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        prenom_medecin1.setText("prenom_medecin");

        nom_medecin.setText("nom_medecin");

        service.setText("service");

        jButton2.setBackground(new java.awt.Color(204, 102, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 102, 255));
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
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom_medecin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(prenom_medecin1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom_medecin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(service)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panle_Gauche.setBackground(new java.awt.Color(250, 247, 247));
        Panle_Gauche.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panle_Gauche.setAutoscrolls(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Nom :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("Prénom :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setText("Date de naissance :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("nom");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("prenom");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("date");

        javax.swing.GroupLayout Panle_GaucheLayout = new javax.swing.GroupLayout(Panle_Gauche);
        Panle_Gauche.setLayout(Panle_GaucheLayout);
        Panle_GaucheLayout.setHorizontalGroup(
            Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panle_GaucheLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Label_Icon_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );
        Panle_GaucheLayout.setVerticalGroup(
            Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panle_GaucheLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Icon_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panle_GaucheLayout.createSequentialGroup()
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(Panle_GaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AJOUTER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Console", 1, 11))); // NOI18N

        jButton5.setBackground(new java.awt.Color(169, 206, 243));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Prescription");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(169, 206, 243));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Fiche de soins");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(169, 206, 243));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Examen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jScrollPane5.setBackground(new java.awt.Color(204, 204, 255));

        JTextArea_affichage.setBackground(new java.awt.Color(204, 204, 255));
        JTextArea_affichage.setColumns(20);
        JTextArea_affichage.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        JTextArea_affichage.setRows(5);
        JTextArea_affichage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setViewportView(JTextArea_affichage);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(Panle_Gauche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Panle_Gauche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) throws IOException {
        //AFFICHAGE DU DOCUMENT SUR LA DROITE
        List<Evaluable> document_temp = new ArrayList<Evaluable>();
        document_temp = new Vector<>();
        document_temp.addAll(dpi.getListe_f());
        document_temp.addAll(dpi.getListe_p());
        document_temp.addAll(dpi.getListe_e());
        document_temp = trierEvaluables(document_temp);
        int i = jTable2.getSelectedRow();
        String ch = document_temp.get(i).toStringDM();
        JTextArea_affichage.setText(ch);

        if (jTable2.getSelectedColumn() == 3) { //si on clique sur l'image pdf
            Document document = new Document(PageSize.A4);
            try {
                PdfWriter.getInstance(document, new FileOutputStream("document.pdf"));
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

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked

    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //AJOUTER UNE FICHE DE SOINS 
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width;
        int hauteur = tailleMoniteur.height;
        Ajout_FS i;
        i = new Ajout_FS(conn, ph, null, null,dpi);
        i.setSize(longueur, hauteur);
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //AJOUT PRESCRIPTION
        Ajout_prescription i = new Ajout_prescription(conn, ph,null,dpi);
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Accueil_Med i;
        try {
            i = new Accueil_Med(conn, ph);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Vue_Patient_Med.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //AJOUT EXAMEN
        Ajout_examen i = new Ajout_examen(conn, ph,null,dpi);
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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

                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_Med i;
                PH ph = new PH("1616161616", "Pan", "Peter", Service.Addictologie, "peterpan", "0456486756", "Biologie");
                i = new Vue_Patient_Med(dpi1, ph);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
                 */
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTextArea_affichage;
    private javax.swing.JLabel Label_Icon_Patient;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JPanel Panle_Gauche;
    private javax.swing.JTable Table_Vue_Generale1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel nom_medecin;
    private javax.swing.JLabel prenom_medecin;
    private javax.swing.JLabel prenom_medecin1;
    private javax.swing.JLabel service;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable_constantes;
}
