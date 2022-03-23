/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.IPPexistant;
import static database.RequetesBDDPI.creerLocalisationSA;
import static database.RequetesBDDPI.creerNouveauDPI;
import static database.RequetesBDDPI.getDPI;
import static database.RequetesBDDPI.getListeDPI;
import static database.RequetesBDDPI.getListeDPIFerme;
import static database.RequetesBDDPI.getListeDPIFermeNom;
import static database.RequetesBDProfessionnels.getListeMT;
import static database.RequetesBDProfessionnels.getListeMTNom;
import static database.RequetesBDProfessionnels.getListePH;
import static database.RequetesBDProfessionnels.getListePHService;
import database.SQLWarningsExceptions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static nf.Checker.*;
import static nf.Cryptage.getIPPRandom;
import nf.*;
import static UI.Vector.*;

/**
 *
 * @author Audrey
 */
public class Accueil_SA extends javax.swing.JFrame {

    Connection conn;
    SecretaireAdministrative sa;
    List<PH> medecins;
    Vector medecinsS;
    List<DPI> dpis;
    Vector dpisS;
    Vector entetes;
    Vector entetes2;
    List<DPI> dpisF;
    Vector dpisFS;
    Vector medecinsS_traitant;
    List<MedecinTraitant> medecins_traitant;

    /**
     * Creates new form Connexion
     */
    public Accueil_SA(Connection conn, SecretaireAdministrative sa) throws SQLException {
        this.conn = conn;
        this.sa = sa;
        initComponents();

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

        //boutons
        ImageIcon icone = new ImageIcon("src/image/actualise.png");
        java.awt.Image img5 = icone.getImage();
        icone = new ImageIcon(img5);
        jButton_actualiser.setIcon(icone);
        jButton_actualiser_medecin.setIcon(icone);
        jButton_actualiser_medecinT.setIcon(icone);
        jButton_actualiserOuvrirDPI.setIcon(icone);
        ImageIcon icone_recherche = new ImageIcon("src/image/loupe2.png");
        java.awt.Image img_recherche = icone_recherche.getImage();
        icone_recherche = new ImageIcon(img_recherche);
        jButton_recherche_medecin.setIcon(icone_recherche);
        jButton_recherche_patient.setIcon(icone_recherche);
        jButton_recherche_medecinT.setIcon(icone_recherche);
        jButton_recherche_patientOuvrirDPI.setIcon(icone_recherche);
        ImageIcon icone_details = new ImageIcon("src/image/details.png");
        java.awt.Image img_details = icone_details.getImage();
        icone_details = new ImageIcon(img_details);
        Button_Selectionner.setIcon(icone_details);

        //JcomboBox de localisation
        jPanel_localisation.setVisible(false);
        DefaultComboBoxModel dbm = new DefaultComboBoxModel(Service.values());
        jComboBox_serviceR.setModel(dbm);
        jComboBox_serviceR.setSelectedIndex(-1);

        //onglet désactivé
        jTabbedPane2.setEnabledAt(1, false);

        //infos identité
        prenom_SA.setText(sa.getPrenomSecretaireAd());
        nom_SA.setText(sa.getNomSecretaireAd());

        //TABLEAU PATIENTS
        dpisS = new Vector<>();
        dpis = getListeDPI(conn);
        dpis = trierDPI(dpis); //tri par ordre alphabétique
        dpisS = getVectorDPI(dpis); //vecteur tableau
        entetes = new Vector();
        entetes.add("Nom");
        entetes.add("Prénom");
        entetes.add("Date de naissance");
        entetes.add("Sexe");
        TableModel tableModel = new DefaultTableModel(dpisS, entetes);
        Table_Vue_Generale1.setModel(tableModel);
        Table_Vue_Generale1.setDefaultEditor(Object.class, null);
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_Vue_Generale1.getRowCount()));

        //TABLEAU PH
        medecinsS = new Vector();
        medecins = getListePH(conn);
        medecins = trierPH(medecins); //tri par ordre alphabétique
        medecinsS = getVectorPH(medecins); //vecteur tableau
        entetes2 = new Vector();
        entetes2.add("Nom");
        entetes2.add("Prénom");
        entetes2.add("Service");
        TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
        tab_medecins.setModel(tableModel2);
        tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
        tab_medecins.setDefaultEditor(Object.class, null);

        //TABLEAU PATIENTS DPI FERME
        dpisFS = new Vector();
        dpisF = getListeDPIFerme(conn);
        dpisF = trierDPI(dpisF);
        dpisFS = getVectorDPIFerme(dpisF);
        TableModel tableModel3 = new DefaultTableModel(dpisFS, entetes);
        Table_DPI_ferme.setModel(tableModel3);
        Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_DPI_ferme.getRowCount()));
        Table_DPI_ferme.setDefaultEditor(Object.class, null);

        //Medecins traitant
        medecinsS_traitant = new Vector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        Panel_Main = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom_SA = new javax.swing.JLabel();
        nom_SA = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Vue_Generale1 = new javax.swing.JTable();
        TextField_Patient = new javax.swing.JTextField();
        jButton_actualiser = new javax.swing.JButton();
        jButton_recherche_patient = new javax.swing.JButton();
        Button_Selectionner = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        TextField_Docteur1 = new javax.swing.JTextField();
        jComboBox_recherche_praticien = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_medecins = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_actualiser_medecin = new javax.swing.JButton();
        jButton_recherche_medecin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        Label_Nom2 = new javax.swing.JLabel();
        Label_Prenom2 = new javax.swing.JLabel();
        Label_Date_Naissance2 = new javax.swing.JLabel();
        Label_Adresse2 = new javax.swing.JLabel();
        Label_Commentaire2 = new javax.swing.JLabel();
        Label_Sexe2 = new javax.swing.JLabel();
        TextField_Nom2 = new javax.swing.JTextField();
        jTextField_Prenom2 = new javax.swing.JTextField();
        RadioButton_F2 = new javax.swing.JRadioButton();
        RadioButton_H2 = new javax.swing.JRadioButton();
        Button_Valider2 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jFormattedTextField_date_naissance2 = new javax.swing.JFormattedTextField();
        jFormattedTextField_telephone2 = new javax.swing.JFormattedTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_adresse2 = new javax.swing.JTextArea();
        jCheckBox_medecinsT = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton_recherche_medecinT = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TextField_Docteur = new javax.swing.JTextField();
        jButton_actualiser_medecinT = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab_medecinsT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table_DPI_ferme = new javax.swing.JTable();
        jButton_actualiserOuvrirDPI = new javax.swing.JButton();
        TextField_Patient1 = new javax.swing.JTextField();
        jButton_recherche_patientOuvrirDPI = new javax.swing.JButton();
        jPanel_localisation = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_serviceR = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setBackground(new java.awt.Color(153, 153, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));

        Panel_Main.setBackground(new java.awt.Color(204, 204, 255));
        Panel_Main.setPreferredSize(new java.awt.Dimension(832, 676));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        prenom_SA.setText("prenom_SA");

        nom_SA.setText("nom_SA");

        jButton4.setBackground(new java.awt.Color(204, 102, 255));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nom_SA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prenom_SA, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(prenom_SA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom_SA))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(Panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setMaximumSize(new java.awt.Dimension(300, 300));

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
        Table_Vue_Generale1.setMinimumSize(new java.awt.Dimension(300, 300));
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(300, 300));
        Table_Vue_Generale1.setRowHeight(20);
        Table_Vue_Generale1.setDefaultEditor(Object.class, null);
        jScrollPane3.setViewportView(Table_Vue_Generale1);

        TextField_Patient.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Patient.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Patient.setText("Patient");
        TextField_Patient.setToolTipText("Entrez le nom du patient recherché");
        TextField_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextField_PatientMouseClicked(evt);
            }
        });
        TextField_Patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_PatientActionPerformed(evt);
            }
        });
        TextField_Patient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextField_PatientKeyPressed(evt);
            }
        });

        jButton_actualiser.setBackground(new java.awt.Color(204, 204, 255));
        jButton_actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiserActionPerformed(evt);
            }
        });

        jButton_recherche_patient.setBackground(new java.awt.Color(204, 204, 255));
        jButton_recherche_patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_patientActionPerformed(evt);
            }
        });

        Button_Selectionner.setBackground(new java.awt.Color(204, 102, 255));
        Button_Selectionner.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        Button_Selectionner.setText("Détails");
        Button_Selectionner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SelectionnerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jButton_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextField_Patient)
                    .addComponent(jButton_actualiser, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Patients", jPanel1);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        TextField_Docteur1.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Docteur1.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Docteur1.setText("Praticien / Service");
        TextField_Docteur1.setToolTipText("Entrez le nom du praticien recherché ou le service auquel il appartient");
        TextField_Docteur1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextField_Docteur1MouseClicked(evt);
            }
        });
        TextField_Docteur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Docteur1ActionPerformed(evt);
            }
        });

        jComboBox_recherche_praticien.setBackground(new java.awt.Color(204, 204, 255));
        jComboBox_recherche_praticien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nom", "Service" }));

        jLabel2.setText("Rechercher par :");

        tab_medecins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nom", "Prénom", "Service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_medecins.setRowHeight(40);
        tab_medecins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_medecinsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_medecins);

        jTextArea1.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setCaretColor(new java.awt.Color(153, 153, 255));
        jScrollPane2.setViewportView(jTextArea1);

        jButton_actualiser_medecin.setBackground(new java.awt.Color(204, 204, 255));
        jButton_actualiser_medecin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiser_medecinActionPerformed(evt);
            }
        });

        jButton_recherche_medecin.setBackground(new java.awt.Color(204, 204, 255));
        jButton_recherche_medecin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_medecinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton_actualiser_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextField_Docteur1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_actualiser_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_Docteur1)
                    .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Praticiens hospitaliers", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane2.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));

        Label_Nom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Nom2.setText("Nom :");
        Label_Nom2.setDoubleBuffered(true);

        Label_Prenom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Prenom2.setText("Prénom :");

        Label_Date_Naissance2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Date_Naissance2.setText("Date de naissance :");

        Label_Adresse2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Adresse2.setText("Adresse :");

        Label_Commentaire2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Commentaire2.setText("Téléphone :");

        Label_Sexe2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Sexe2.setText("Sexe :");

        buttonGroup.add(RadioButton_F2);
        RadioButton_F2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        RadioButton_F2.setText("femme");
        RadioButton_F2.setBorder(null);
        RadioButton_F2.setOpaque(false);
        RadioButton_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_F2RadioButton_FActionPerformed(evt);
            }
        });

        buttonGroup.add(RadioButton_H2);
        RadioButton_H2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        RadioButton_H2.setText("homme");
        RadioButton_H2.setBorder(null);
        RadioButton_H2.setOpaque(false);

        Button_Valider2.setBackground(new java.awt.Color(204, 102, 255));
        Button_Valider2.setText("CREER LE PATIENT");
        Button_Valider2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_Valider2Button_ValiderMouseClicked(evt);
            }
        });
        Button_Valider2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Valider2Button_ValiderActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jRadioButton3.setText("autre");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3jRadioButton1ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField_date_naissance2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_date_naissance2.setToolTipText("Format dd/MM/yyyy");

        try {
            jFormattedTextField_telephone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## ## ## ## ##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTextArea_adresse2.setColumns(20);
        jTextArea_adresse2.setRows(5);
        jScrollPane6.setViewportView(jTextArea_adresse2);

        jCheckBox_medecinsT.setBackground(new java.awt.Color(204, 204, 255));
        jCheckBox_medecinsT.setEnabled(false);

        jButton3.setBackground(new java.awt.Color(153, 153, 255));
        jButton3.setText("Ajouter un medecin traitant");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox_medecinsT)
                    .addComponent(Label_Sexe2)
                    .addComponent(Label_Date_Naissance2)
                    .addComponent(Label_Commentaire2)
                    .addComponent(Label_Prenom2)
                    .addComponent(Label_Nom2)
                    .addComponent(Label_Adresse2))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(RadioButton_F2)
                                .addGap(18, 18, 18)
                                .addComponent(RadioButton_H2)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6)
                            .addComponent(jTextField_Prenom2)
                            .addComponent(TextField_Nom2)
                            .addComponent(jFormattedTextField_telephone2)
                            .addComponent(jFormattedTextField_date_naissance2))
                        .addGap(164, 164, 164))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Button_Valider2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(356, 356, 356))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextField_Nom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Nom2))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Date_Naissance2)
                            .addComponent(jFormattedTextField_date_naissance2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Prenom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Prenom2))))
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Sexe2)
                    .addComponent(RadioButton_F2)
                    .addComponent(RadioButton_H2)
                    .addComponent(jRadioButton3))
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Commentaire2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField_telephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Adresse2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox_medecinsT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(Button_Valider2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel10);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setEnabled(false);

        jButton_recherche_medecinT.setBackground(new java.awt.Color(153, 153, 255));
        jButton_recherche_medecinT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_medecinTActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 153, 255));
        jButton5.setText("Choisir ce médecin traitant");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel3.setText("Veillez choisir le médecin traitant");

        TextField_Docteur.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Docteur.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Docteur.setText("Nom médecin");
        TextField_Docteur.setToolTipText("Entrez le nom du médecin recherché");
        TextField_Docteur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextField_DocteurMouseClicked(evt);
            }
        });
        TextField_Docteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_DocteurActionPerformed(evt);
            }
        });

        jButton_actualiser_medecinT.setBackground(new java.awt.Color(153, 153, 255));
        jButton_actualiser_medecinT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiser_medecinTActionPerformed(evt);
            }
        });

        tab_medecinsT.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_medecinsT.setMinimumSize(new java.awt.Dimension(300, 300));
        tab_medecinsT.setPreferredSize(new java.awt.Dimension(300, 300));
        tab_medecinsT.setRowHeight(30);
        jScrollPane8.setViewportView(tab_medecinsT);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(372, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton_actualiser_medecinT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(TextField_Docteur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_medecinT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(373, 373, 373))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(104, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_actualiser_medecinT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_Docteur, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_medecinT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(164, 164, 164)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(122, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("", jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Créer un patient", jPanel3);

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        Table_DPI_ferme.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_DPI_ferme.setMinimumSize(new java.awt.Dimension(300, 300));
        Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(300, 300));
        Table_DPI_ferme.setRowHeight(20);
        Table_DPI_ferme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_DPI_fermeMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(Table_DPI_ferme);

        jButton_actualiserOuvrirDPI.setBackground(new java.awt.Color(204, 204, 255));
        jButton_actualiserOuvrirDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiserOuvrirDPIActionPerformed(evt);
            }
        });

        TextField_Patient1.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Patient1.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Patient1.setText("Patient");
        TextField_Patient1.setToolTipText("Entrez le nom du patient recherché");
        TextField_Patient1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextField_Patient1MouseClicked(evt);
            }
        });
        TextField_Patient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_Patient1ActionPerformed(evt);
            }
        });
        TextField_Patient1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextField_Patient1KeyPressed(evt);
            }
        });

        jButton_recherche_patientOuvrirDPI.setBackground(new java.awt.Color(204, 204, 255));
        jButton_recherche_patientOuvrirDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_patientOuvrirDPIActionPerformed(evt);
            }
        });

        jPanel_localisation.setBackground(new java.awt.Color(204, 204, 255));
        jPanel_localisation.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        jLabel1.setText("Choisir la localisation du patient");

        jComboBox_serviceR.setMaximumRowCount(30);
        jComboBox_serviceR.setMinimumSize(new java.awt.Dimension(30, 500));
        jComboBox_serviceR.setPreferredSize(new java.awt.Dimension(30, 300));
        jComboBox_serviceR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBox_serviceRMouseReleased(evt);
            }
        });
        jComboBox_serviceR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_serviceRActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(213, 123, 213));
        jButton1.setText("Ouvrir ce DPI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Service responsable");

        javax.swing.GroupLayout jPanel_localisationLayout = new javax.swing.GroupLayout(jPanel_localisation);
        jPanel_localisation.setLayout(jPanel_localisationLayout);
        jPanel_localisationLayout.setHorizontalGroup(
            jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_localisationLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_localisationLayout.createSequentialGroup()
                        .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_serviceR, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_localisationLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        jPanel_localisationLayout.setVerticalGroup(
            jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_localisationLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(93, 93, 93)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_serviceR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                .addGap(99, 99, 99)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButton_actualiserOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TextField_Patient1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_recherche_patientOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(jPanel_localisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(437, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_actualiserOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_recherche_patientOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextField_Patient1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_localisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(124, 124, 124)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Ouvrir DPI", jPanel7);

        javax.swing.GroupLayout Panel_MainLayout = new javax.swing.GroupLayout(Panel_Main);
        Panel_Main.setLayout(Panel_MainLayout);
        Panel_MainLayout.setHorizontalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        Panel_MainLayout.setVerticalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextField_PatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_PatientMouseClicked
        TextField_Patient.setText("");
        TextField_Patient.setForeground(Color.black);
    }//GEN-LAST:event_TextField_PatientMouseClicked

    private void TextField_PatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_PatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_PatientActionPerformed

    private void TextField_Docteur1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_Docteur1MouseClicked
        TextField_Docteur1.setText("");
        TextField_Docteur1.setForeground(Color.black);
    }//GEN-LAST:event_TextField_Docteur1MouseClicked

    private void TextField_Docteur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Docteur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Docteur1ActionPerformed

    private void tab_medecinsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_medecinsMouseClicked
        //AFFICHER PH SELECTIONNE
        int index = tab_medecins.getSelectedRow();
        jTextArea1.setText(medecins.get(index).toStringDetail());
    }//GEN-LAST:event_tab_medecinsMouseClicked

    private void jButton_actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiserActionPerformed
        try {
            //RECHARGER DPI
            dpis = getListeDPI(conn);
            dpisS = getVectorDPI(dpis); //vecteur tableau
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setAutoCreateRowSorter(true);
            Table_Vue_Generale1.setModel(tableModel);
            TextField_Patient.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiserActionPerformed

    private void TextField_PatientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_PatientKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String recherche = TextField_Patient.getText();
            try {
                if (!recherche.equals("")) {

                    dpis = getListeDPI(conn, recherche);
                    dpisS = getVectorDPI(dpis); //vecteur tableau
                    TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                    Table_Vue_Generale1.setAutoCreateRowSorter(true);
                    Table_Vue_Generale1.setModel(tableModel);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TextField_PatientKeyPressed

    private void jButton_actualiser_medecinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser_medecinActionPerformed
        //RECHARGER MEDECINS
        try {
            medecins = getListePH(conn);
            medecinsS = getVectorPH(medecins); //vecteur tableau
            TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
            tab_medecins.setAutoCreateRowSorter(true);
            tab_medecins.setModel(tableModel2);
            tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton_actualiser_medecinActionPerformed

    private void jButton_recherche_medecinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_medecinActionPerformed
        // VALIDATION DE LA RECHERCHE MEDECIN
        String type_recherche; //Nom ou Service
        type_recherche = jComboBox_recherche_praticien.getSelectedItem().toString();
        String recherche = TextField_Docteur1.getText();

        if (type_recherche.equals("Nom")) {

            try {
                medecins = getListePH(conn, recherche);
                medecinsS = getVectorPH(medecins); //vecteur tableau
                Vector entetes2 = new Vector();
                entetes2.add("Nom");
                entetes2.add("Prénom");
                entetes2.add("Service");

                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setAutoCreateRowSorter(true);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type_recherche.equals("Service")) {
            try {
                medecins = getListePHService(conn, recherche);
                medecinsS = getVectorPH(medecins); //vecteur tableau
                Vector entetes2 = new Vector();
                entetes2.add("Nom");
                entetes2.add("Prénom");
                entetes2.add("Service");

                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setAutoCreateRowSorter(true);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton_recherche_medecinActionPerformed

    private void jButton_recherche_patientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_patientActionPerformed
        //RECHERCHE PATIENT
        String recherche = TextField_Patient.getText();
        try {
            dpis = getListeDPI(conn, recherche);
            dpisS = getVectorDPI(dpis); //vecteur tableau
            Vector entetes = new Vector();
            entetes.add("Nom");
            entetes.add("Prénom");
            entetes.add("Date de naissance");
            entetes.add("Sexe");
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setAutoCreateRowSorter(true);
            Table_Vue_Generale1.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_recherche_patientActionPerformed

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

    private void jButton3jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3jButton1ActionPerformed
        //AJOUTER UN MEDECIN TRAITANT

        try {
            jTabbedPane2.setEnabledAt(1, true);
            jTabbedPane2.setSelectedIndex(1);

            medecins_traitant = getListeMT(conn);
            medecinsS_traitant = getVectorMT(medecins_traitant);
            Vector entetesM = new Vector();
            entetesM.add("Nom");
            entetesM.add("Prénom");
            entetesM.add("Téléphone");
            TableModel tableModelM = new DefaultTableModel(medecinsS_traitant, entetesM);
            tab_medecinsT.setAutoCreateRowSorter(true);
            tab_medecinsT.setModel(tableModelM);
            tab_medecinsT.setPreferredSize(new java.awt.Dimension(3000, 30 * tab_medecinsT.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3jButton1ActionPerformed

    private void jRadioButton3jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3jRadioButton1ActionPerformed

    private void Button_Valider2Button_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderActionPerformed
        try {
            ///CREATION D'UN PATIENT
            if (champsCorrects()) {
                String nom = TextField_Nom2.getText();
                String prenom = jTextField_Prenom2.getText();
                SimpleDateFormat formater = null;
                formater = new SimpleDateFormat("dd/MM/yyyy");

                Date d = formater.parse(jFormattedTextField_date_naissance2.getText());
                String adresse = jTextArea_adresse2.getText();
                String telephone = jFormattedTextField_telephone2.getText();
                telephone = telephone.replaceAll("\\s+", "");
                System.out.println(telephone);
                String sexe;
                if (RadioButton_F2.isSelected()) {
                    sexe = RadioButton_F2.getText();
                } else if (RadioButton_H2.isSelected()) {
                    sexe = RadioButton_H2.getText();
                } else if (jRadioButton3.isSelected()) {
                    sexe = jRadioButton3.getText();
                } else {
                    sexe = null;
                }
                int index = tab_medecinsT.getSelectedRow();
                MedecinTraitant mt = medecins_traitant.get(index);

                String message = "Etes-vous sûr de vouloir créer le patient suivant ?";
                message = message + "\n Nom : " + nom + "\n Prénom : " + prenom + "\n Date de naissance : " + jFormattedTextField_date_naissance2.getText() + "\n Sexe : " + sexe + "\n Téléphone : " + telephone + "\n Adresse : " + adresse + "\n Medecin traitant : " + mt.getNomMedecinTraitant() + " " + mt.getPrenomMedecinTraitant();
                int retour = JOptionPane.showConfirmDialog(this, message, "Vérification des informations", JOptionPane.OK_CANCEL_OPTION);

                if (retour == 0) { //les informations sont correctes = validation
                    //tirer un IPP random qui n'existe pas
                    String IPP = getIPPRandom();
                    while (IPPexistant(conn, IPP)) {
                        IPP = getIPPRandom();
                    }
                    //création du patient
                    creerNouveauDPI(conn, IPP, nom, prenom, d, sexe, telephone, adresse, mt);

                    //mettre à jour la liste des patients
                    dpis = getListeDPI(conn);
                    dpisS = getVectorDPI(dpis);
                    TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                    Table_Vue_Generale1.setModel(tableModel);
                    dpisF = getListeDPIFerme(conn);
                    dpisFS = getVectorDPIFerme(dpisF);
                    TableModel tableModel3 = new DefaultTableModel(dpisFS, entetes);
                    Table_DPI_ferme.setModel(tableModel3);

                    //tout remettre à 0
                    TextField_Nom2.setText("");
                    jTextField_Prenom2.setText("");
                    jFormattedTextField_date_naissance2.setText("");
                    jTextArea_adresse2.setText("");
                    jFormattedTextField_telephone2.setText("");
                    buttonGroup.clearSelection();
                    jCheckBox_medecinsT.setSelected(false);
                    tab_medecinsT.clearSelection();
                    JOptionPane.showMessageDialog(this, "Le patient a été créé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Button_Valider2Button_ValiderActionPerformed

    private void Button_Valider2Button_ValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderMouseClicked

    }//GEN-LAST:event_Button_Valider2Button_ValiderMouseClicked

    private void RadioButton_F2RadioButton_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_F2RadioButton_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton_F2RadioButton_FActionPerformed

    private void jButton_recherche_medecinTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_medecinTActionPerformed
        // VALIDATION DE LA RECHERCHE MEDECIN
        String recherche = TextField_Docteur.getText();

        try {
            medecins_traitant = getListeMTNom(conn, recherche);
            medecinsS_traitant = getVectorMT(medecins_traitant);
            TableModel tableModel2 = new DefaultTableModel(medecinsS_traitant, entetes);
            tab_medecinsT.setAutoCreateRowSorter(true);
            tab_medecinsT.setModel(tableModel2);
            tab_medecinsT.setPreferredSize(new java.awt.Dimension(3000, 30 * tab_medecinsT.getRowCount()));

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Accueil_SA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_recherche_medecinTActionPerformed

    private void TextField_DocteurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_DocteurMouseClicked
        TextField_Docteur.setText("");
        TextField_Docteur.setForeground(Color.black);
    }//GEN-LAST:event_TextField_DocteurMouseClicked

    private void TextField_DocteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_DocteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_DocteurActionPerformed

    private void jButton_actualiser_medecinTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser_medecinTActionPerformed
        try {
            //RECHARGER MEDECINS
            medecins_traitant = getListeMT(conn);
            medecinsS_traitant = getVectorMT(medecins_traitant);
            TableModel tableModel2 = new DefaultTableModel(medecinsS_traitant, entetes);
            tab_medecinsT.setAutoCreateRowSorter(true);
            tab_medecinsT.setModel(tableModel2);
            tab_medecinsT.setPreferredSize(new java.awt.Dimension(3000, 30 * tab_medecinsT.getRowCount()));

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Accueil_SA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser_medecinTActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //VALIDATION DU MEDECIN TRAITANT 

        if (tab_medecinsT.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun medecin traitant sélectionné", "Attention", JOptionPane.WARNING_MESSAGE);

        } else {
            jCheckBox_medecinsT.setSelected(true);
            jTabbedPane2.setSelectedIndex(0);
            jTabbedPane2.setEnabledAt(1, false);
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton_actualiserOuvrirDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiserOuvrirDPIActionPerformed
        //RECHARGER LES DPI FERMES
        try {
            dpisF = getListeDPIFerme(conn);
            dpisF = trierDPI(dpisF);
            dpisFS = getVectorDPIFerme(dpisF);
            TableModel tableModel3 = new DefaultTableModel(dpisFS, entetes);
            Table_DPI_ferme.setModel(tableModel3);
            Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_DPI_ferme.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_actualiserOuvrirDPIActionPerformed

    private void TextField_Patient1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_Patient1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Patient1MouseClicked

    private void TextField_Patient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Patient1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Patient1ActionPerformed

    private void TextField_Patient1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Patient1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Patient1KeyPressed

    private void jButton_recherche_patientOuvrirDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_patientOuvrirDPIActionPerformed
        //RECHERCHER DANS DPI FERME

        try {
            String recherche = TextField_Patient1.getText();
            dpisF = getListeDPIFermeNom(conn, recherche);
            dpisF = trierDPI(dpisF);
            dpisFS = getVectorDPIFerme(dpisF);
            TableModel tableModel3 = new DefaultTableModel(dpisFS, entetes);
            Table_DPI_ferme.setModel(tableModel3);
            Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_DPI_ferme.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_recherche_patientOuvrirDPIActionPerformed

    private void jComboBox_serviceRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_serviceRActionPerformed


    }//GEN-LAST:event_jComboBox_serviceRActionPerformed

    private void jComboBox_serviceRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_serviceRMouseReleased

    }//GEN-LAST:event_jComboBox_serviceRMouseReleased

    private void Table_DPI_fermeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DPI_fermeMouseClicked
        jPanel_localisation.setVisible(true);
    }//GEN-LAST:event_Table_DPI_fermeMouseClicked

    private void Button_SelectionnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SelectionnerActionPerformed

        //VISUALISATION D UN PATIENT PARTICULIER
        if (Table_Vue_Generale1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun patient n'est sélectionné dans la liste", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                int index = Table_Vue_Generale1.getSelectedRow();
                DPI dpi = getDPI(conn, dpis.get(index).getIPP());
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_SA i = new Vue_Patient_SA(conn, dpi, sa);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
                dispose();

            } catch (SQLException ex) {
                Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_Button_SelectionnerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //OUVRIR LE DPI

        if (jComboBox_serviceR.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un service responsable", "Attention", JOptionPane.WARNING_MESSAGE);

        } else {
            //recup informations
            Service service_responsable = (Service) jComboBox_serviceR.getSelectedItem();
            int indexSelected = Table_DPI_ferme.getSelectedRow();
            DPI dpi = (DPI) dpisF.get(indexSelected);

            //creer la localisation
            try {
                creerLocalisationSA(conn, dpi.getIPP(), service_responsable);
                JOptionPane.showMessageDialog(this, "Le patient a maintenant une localisation", "Information", JOptionPane.INFORMATION_MESSAGE);

                dpisF = getListeDPIFerme(conn);
                dpisF = trierDPI(dpisF);
                dpisFS = getVectorDPIFerme(dpisF);
                TableModel tableModel3 = new DefaultTableModel(dpisFS, entetes);
                Table_DPI_ferme.setModel(tableModel3);
                Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_DPI_ferme.getRowCount()));
                jComboBox_serviceR.setSelectedIndex(-1);

                //patients 
                dpis = getListeDPI(conn);
                dpis = trierDPI(dpis); //tri par ordre alphabétique
                dpisS = getVectorDPI(dpis); //vecteur tableau
                TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                Table_Vue_Generale1.setModel(tableModel);
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean champsCorrects() throws ParseException {
        boolean v = true;
        if (TextField_Nom2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un nom", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (jTextField_Prenom2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un prénom", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (jFormattedTextField_date_naissance2.getText().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer une date de naissance", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (!checkerDate(jFormattedTextField_date_naissance2.getText())) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un format de date correct", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (buttonGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer le sexe", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (jFormattedTextField_telephone2.getText().equals("              ")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un téléphone", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (jTextArea_adresse2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer une adresse", "Attention", JOptionPane.WARNING_MESSAGE);
            v = false;
        } else if (tab_medecinsT.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un médecin traitant", "Attention", JOptionPane.WARNING_MESSAGE);
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
            @Override
            public void run() {
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;

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
                    Accueil_SA i = new Accueil_SA(conn, new SecretaireAdministrative("1451251451", "Chevalier", "Karine", "Couscous"));
                    i.setSize(longueur, hauteur);
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
    private javax.swing.JButton Button_Selectionner;
    private javax.swing.JButton Button_Valider2;
    private javax.swing.JLabel Label_Adresse2;
    private javax.swing.JLabel Label_Commentaire2;
    private javax.swing.JLabel Label_Date_Naissance2;
    private javax.swing.JLabel Label_Nom2;
    private javax.swing.JLabel Label_Prenom2;
    private javax.swing.JLabel Label_Sexe2;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JRadioButton RadioButton_F2;
    private javax.swing.JRadioButton RadioButton_H2;
    private javax.swing.JTable Table_DPI_ferme;
    private javax.swing.JTable Table_Vue_Generale1;
    private javax.swing.JTextField TextField_Docteur;
    private javax.swing.JTextField TextField_Docteur1;
    private javax.swing.JTextField TextField_Nom2;
    private javax.swing.JTextField TextField_Patient;
    private javax.swing.JTextField TextField_Patient1;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton_actualiser;
    private javax.swing.JButton jButton_actualiserOuvrirDPI;
    private javax.swing.JButton jButton_actualiser_medecin;
    private javax.swing.JButton jButton_actualiser_medecinT;
    private javax.swing.JButton jButton_recherche_medecin;
    private javax.swing.JButton jButton_recherche_medecinT;
    private javax.swing.JButton jButton_recherche_patient;
    private javax.swing.JButton jButton_recherche_patientOuvrirDPI;
    private javax.swing.JCheckBox jCheckBox_medecinsT;
    private javax.swing.JComboBox<String> jComboBox_recherche_praticien;
    private javax.swing.JComboBox<String> jComboBox_serviceR;
    private javax.swing.JFormattedTextField jFormattedTextField_date_naissance2;
    private javax.swing.JFormattedTextField jFormattedTextField_telephone2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_localisation;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea_adresse2;
    private javax.swing.JTextField jTextField_Prenom2;
    private javax.swing.JLabel nom_SA;
    private javax.swing.JLabel prenom_SA;
    private javax.swing.JTable tab_medecins;
    private javax.swing.JTable tab_medecinsT;
    // End of variables declaration//GEN-END:variables

}
