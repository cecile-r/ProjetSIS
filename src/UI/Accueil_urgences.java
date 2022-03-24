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
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static nf.Checker.*;
import nf.DPI;
import nf.PH;
import nf.Service;
import static database.RequetesBDDPI.getDPI;
import static database.RequetesBDDPI.getListeDPI;
import static database.RequetesBDDPI.getListeDPIService;
import static database.RequetesBDProfessionnels.getListePH;
import static database.RequetesBDProfessionnels.getListePHService;
import static database.RequetesBDDPI.*;
import static database.RequetesBDProfessionnels.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static nf.Cryptage.getIPPRandom;
import nf.*;
import static UI.Vector.*;
import static database.RequetesBDUrgences.IPPTempExistant;
import static database.RequetesBDUrgences.creerDPITemporaire;
import static database.RequetesBDUrgences.getDPITemp;
import static database.RequetesBDUrgences.getListeDPITemporaires;

/**
 *
 * @author Audrey
 */
public class Accueil_urgences extends javax.swing.JFrame {
    
    PH ph;
    Connection conn;
    List<PH> medecins;
    Vector medecinsS;
    List<DPITemporaire> dpis;
    Vector dpisS;
    Vector entetes;
    Vector entetes2;

    /**
     * Creates new form Connexion
     */
    public Accueil_urgences(Connection conn, PH ph) throws SQLException {
        this.conn = conn;
        this.ph = ph;
        initComponents();

        //boutons
        ImageIcon icone = new ImageIcon("src/image/actualise.png");
        java.awt.Image img5 = icone.getImage();
        icone = new ImageIcon(img5);
        jButton_actualiser1.setIcon(icone);
        jButton_actualiser_medecin1.setIcon(icone);
        ImageIcon icone_recherche = new ImageIcon("src/image/loupe2.png");
        java.awt.Image img_recherche = icone_recherche.getImage();
        icone_recherche = new ImageIcon(img_recherche);
        jButton_recherche_medecin.setIcon(icone_recherche);
        jButton_recherche_patient.setIcon(icone_recherche);
        ImageIcon icone_details = new ImageIcon("src/image/details.png");
        java.awt.Image img_details = icone_details.getImage();
        icone_details = new ImageIcon(img_details);
        Button_Selectionner.setIcon(icone_details);

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

        //infos identité
        prenom_medecin.setText(ph.getPrenomPH());
        nom_medecin.setText(ph.getNomPH());
        service.setText(ph.getService().toString());

        //TABLEAU PATIENTS
        dpisS = new Vector<>();
        dpis = getListeDPITemporaires(conn);
        dpis = trierDPITemp(dpis);
        dpisS = getVectorDPITemp(dpis); //vecteur tableau
        entetes = new Vector();
        entetes.add("Nom");
        entetes.add("Prénom");
        entetes.add("Date de naissance");
        TableModel tableModel = new DefaultTableModel(dpisS, entetes);
        Table_Vue_Generale1.setModel(tableModel);
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

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
        tab_medecins.setAutoCreateRowSorter(true);
        tab_medecins.setModel(tableModel2);
        tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        Button_Valider2 = new javax.swing.JButton();
        Panel_Main = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom_medecin = new javax.swing.JLabel();
        nom_medecin = new javax.swing.JLabel();
        service = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Vue_Generale1 = new javax.swing.JTable();
        TextField_Patient = new javax.swing.JTextField();
        Button_Selectionner = new javax.swing.JButton();
        jButton_actualiser1 = new javax.swing.JButton();
        jButton_recherche_patient = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        Label_Plannig4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        TextField_Docteur = new javax.swing.JTextField();
        jComboBox_recherche_praticien = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_medecins = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_actualiser_medecin1 = new javax.swing.JButton();
        jButton_recherche_medecin = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Label_Nom2 = new javax.swing.JLabel();
        Label_Prenom2 = new javax.swing.JLabel();
        jFormattedTextField_date_naissance2 = new javax.swing.JFormattedTextField();
        Label_Date_Naissance2 = new javax.swing.JLabel();
        TextField_Nom2 = new javax.swing.JTextField();
        jTextField_Prenom2 = new javax.swing.JTextField();
        Button_Valider3 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenu2.setText("jMenu2");

        jButton1.setBackground(new java.awt.Color(204, 102, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/se-deconnecter.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexa");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        Panel_Main.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        prenom_medecin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        prenom_medecin.setText("prenom_medecin");

        nom_medecin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nom_medecin.setText("nom_medecin");

        service.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        service.setText("service");

        jButton2.setBackground(new java.awt.Color(204, 102, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_BandeauLayout = new javax.swing.GroupLayout(Panel_Bandeau);
        Panel_Bandeau.setLayout(Panel_BandeauLayout);
        Panel_BandeauLayout.setHorizontalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                                .addComponent(prenom_medecin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nom_medecin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(service)
                                .addGap(19, 19, 19))))))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setAlignmentX(2);
        jTabbedPane1.setAlignmentY(2);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);
        jTabbedPane1.setName(""); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setFocusCycleRoot(true);

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
        Table_Vue_Generale1.setRowHeight(30);
        Table_Vue_Generale1.setDefaultEditor(Object.class, null);
        jScrollPane1.setViewportView(Table_Vue_Generale1);

        TextField_Patient.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        TextField_Patient.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Patient.setText("Patient");
        TextField_Patient.setToolTipText("Entrez le nom du patient recherché");
        TextField_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextField_PatientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TextField_PatientMouseEntered(evt);
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

        Button_Selectionner.setBackground(new java.awt.Color(204, 102, 255));
        Button_Selectionner.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        Button_Selectionner.setText("Détails");
        Button_Selectionner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SelectionnerActionPerformed(evt);
            }
        });

        jButton_actualiser1.setBackground(new java.awt.Color(204, 204, 255));
        jButton_actualiser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiser1ActionPerformed(evt);
            }
        });

        jButton_recherche_patient.setBackground(new java.awt.Color(204, 204, 255));
        jButton_recherche_patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_patientActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(169, 206, 243));

        Label_Plannig4.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        Label_Plannig4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Plannig4.setText("Patients de mon service");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Plannig4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Label_Plannig4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(433, 433, 433)
                        .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton_actualiser1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_actualiser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_Patient, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Patients", null, jPanel4, "");
        jTabbedPane1.setBackgroundAt(0, Color.RED);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setAutoscrolls(true);

        TextField_Docteur.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Docteur.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Docteur.setText("Praticien / Service");
        TextField_Docteur.setToolTipText("Entrez le nom du praticien recherché ou le service auquel il appartient");
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
        tab_medecins.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
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
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(153, 153, 255));
        jScrollPane2.setViewportView(jTextArea1);

        jButton_actualiser_medecin1.setBackground(new java.awt.Color(204, 204, 255));
        jButton_actualiser_medecin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualiser_medecin1ActionPerformed(evt);
            }
        });

        jButton_recherche_medecin.setBackground(new java.awt.Color(204, 204, 255));
        jButton_recherche_medecin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_medecinActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(169, 206, 243));

        jLabel6.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel6.setText("Praticiens hospitaliers");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(69, 69, 69))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton_actualiser_medecin1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Docteur, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(336, 336, 336))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_recherche_praticien)
                    .addComponent(TextField_Docteur)
                    .addComponent(jButton_actualiser_medecin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addGap(80, 80, 80))
        );

        jTabbedPane1.addTab("Praticien hospitalier", jPanel5);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        Label_Nom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Nom2.setText("Nom :");
        Label_Nom2.setDoubleBuffered(true);

        Label_Prenom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Prenom2.setText("Prénom :");

        try {
            jFormattedTextField_date_naissance2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_date_naissance2.setToolTipText("Format dd/MM/yyyy");

        Label_Date_Naissance2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Date_Naissance2.setText("Date de naissance :");

        Button_Valider3.setBackground(new java.awt.Color(204, 102, 255));
        Button_Valider3.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        Button_Valider3.setText("CREER LE PATIENT TEMPORAIREMENT");
        Button_Valider3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_Valider3Button_ValiderMouseClicked(evt);
            }
        });
        Button_Valider3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Valider3Button_ValiderActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(169, 206, 243));

        jLabel7.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel7.setText("Créer un patient temporaire");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Label_Date_Naissance2)
                    .addComponent(Label_Prenom2)
                    .addComponent(Label_Nom2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Prenom2)
                    .addComponent(TextField_Nom2)
                    .addComponent(jFormattedTextField_date_naissance2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
                .addGap(221, 221, 221))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Button_Valider3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(353, 353, 353))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextField_Nom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Nom2))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Date_Naissance2)
                            .addComponent(jFormattedTextField_date_naissance2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Prenom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Prenom2))))
                .addGap(82, 82, 82)
                .addComponent(Button_Valider3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dossier temporaire", jPanel1);

        javax.swing.GroupLayout Panel_MainLayout = new javax.swing.GroupLayout(Panel_Main);
        Panel_Main.setLayout(Panel_MainLayout);
        Panel_MainLayout.setHorizontalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Panel_MainLayout.setVerticalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void TextField_DocteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_DocteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_DocteurActionPerformed

    private void TextField_DocteurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_DocteurMouseClicked
        TextField_Docteur.setText("");
        TextField_Docteur.setForeground(Color.black);
    }//GEN-LAST:event_TextField_DocteurMouseClicked

    private void Button_SelectionnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SelectionnerActionPerformed
        //VISUALISATION D UN PATIENT PARTICULIER
        if (Table_Vue_Generale1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun patient n'est sélectionné dans la liste", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            
            try {
                
                int index = Table_Vue_Generale1.getSelectedRow();
                DPITemporaire dpiTemp;
                dpiTemp = getDPITemp(conn, dpis.get(index).getIPP());
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_Med_Urgences i = new Vue_Patient_Med_Urgences(conn, dpiTemp, ph);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_urgences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Button_SelectionnerActionPerformed

    private void TextField_PatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_PatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_PatientActionPerformed

    private void TextField_PatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_PatientMouseClicked
        TextField_Patient.setText("");
        TextField_Patient.setForeground(Color.black);
    }//GEN-LAST:event_TextField_PatientMouseClicked

    private void tab_medecinsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_medecinsMouseClicked
        //AFFICHER PH SELECTIONNE
        int index = tab_medecins.getSelectedRow();
        jTextArea1.setText(medecins.get(index).toStringDetail());
    }//GEN-LAST:event_tab_medecinsMouseClicked

    private void TextField_PatientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_PatientMouseEntered

    }//GEN-LAST:event_TextField_PatientMouseEntered

    private void TextField_PatientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_PatientKeyPressed
        /*if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String recherche = TextField_Patient.getText();
            if (!recherche.equals("")) {
                try {
                    dpis = getListeDPITemporaires(conn, recherche, ph.getService().toString());
                    dpis = trierDPITemp(dpis);
                    dpisS = getVectorDPITemp(dpis);
                    TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                    Table_Vue_Generale1.setAutoCreateRowSorter(true);
                    Table_Vue_Generale1.setModel(tableModel);
                    Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

                } catch (SQLException ex) {
                    Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }*/
    }//GEN-LAST:event_TextField_PatientKeyPressed

    private void jButton_actualiser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser1ActionPerformed
        try {
            //RECHARGER DPI
            dpis = getListeDPITemporaires(conn);
            dpis = trierDPITemp(dpis);
            dpisS = getVectorDPITemp(dpis);
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));
            
            TextField_Patient.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser1ActionPerformed

    private void jButton_recherche_patientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_patientActionPerformed
        //RECHERCHE PATIENT
        /*
        String recherche = TextField_Patient.getText();
        try {
            dpis = getListeDPITemporaires(conn, recherche);
            dpis = trierDPITemp(dpis);
            dpisS = getVectorDPITemp(dpis);
            Vector entetes = new Vector();
            entetes.add("Nom");
            entetes.add("Prénom");
            entetes.add("Date de naissance");
            entetes.add("Sexe");
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton_recherche_patientActionPerformed

    private void jButton_recherche_medecinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_medecinActionPerformed
        // VALIDATION DE LA RECHERCHE MEDECIN
        String type_recherche; //Nom ou Service
        type_recherche = jComboBox_recherche_praticien.getSelectedItem().toString();
        String recherche = TextField_Docteur.getText();
        
        if (type_recherche.equals("Nom")) {
            
            try {
                medecins = getListePH(conn, recherche);
                medecins = trierPH(medecins); //tri par ordre alphabétique
                medecinsS = getVectorPH(medecins); //vecteur tableau
                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (type_recherche.equals("Service")) {
            try {
                medecins = getListePHService(conn, recherche);
                medecinsS = getVectorPH(medecins); //vecteur tableau
                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_recherche_medecinActionPerformed

    private void jButton_actualiser_medecin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser_medecin1ActionPerformed
        //RECHARGER MEDECINS
        try {
            medecins = getListePH(conn);
            medecinsS = getVectorPH(medecins); //vecteur tableau
            TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
            tab_medecins.setModel(tableModel2);
            tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser_medecin1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Connexion i;
        try {
            i = new Connexion(conn);
            i.setLocationRelativeTo(null);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_Inf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Button_Valider2Button_ValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderMouseClicked

    }//GEN-LAST:event_Button_Valider2Button_ValiderMouseClicked

    private void Button_Valider2Button_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderActionPerformed
        /* try {
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
        }*/
    }//GEN-LAST:event_Button_Valider2Button_ValiderActionPerformed

    private void Button_Valider3Button_ValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_Valider3Button_ValiderMouseClicked

    }//GEN-LAST:event_Button_Valider3Button_ValiderMouseClicked

    private void Button_Valider3Button_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Valider3Button_ValiderActionPerformed
        try {

            ///CREATION D'UN PATIENT
            if (champsCorrects()) {
                String nom = TextField_Nom2.getText();
                String prenom = jTextField_Prenom2.getText();
                SimpleDateFormat formater = null;
                formater = new SimpleDateFormat("dd/MM/yyyy");
                Date d = formater.parse(jFormattedTextField_date_naissance2.getText());
                
                String message = "Etes-vous sûr de vouloir créer le patient temporaire suivant ?";
                message = message + "\n Nom : " + nom + "\n Prénom : " + prenom + "\n Date de naissance : " + jFormattedTextField_date_naissance2.getText();
                int retour = JOptionPane.showConfirmDialog(this, message, "Vérification des informations", JOptionPane.OK_CANCEL_OPTION);
                
                if (retour == 0) { //les informations sont correctes = validation
                    //tirer un IPP random qui n'existe pas
                    String IPP = getIPPRandom();
                    while (IPPTempExistant(conn, IPP)) {
                        IPP = getIPPRandom();
                    }
                    //création du patient
                    DPITemporaire dpitemp = new DPITemporaire(IPP, nom, prenom, d);
                    creerDPITemporaire(conn, dpitemp);

                    //mettre à jour la liste des patients
                    dpis = getListeDPITemporaires(conn);
                    dpis = trierDPITemp(dpis);
                    dpisS = getVectorDPITemp(dpis);
                    TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                    Table_Vue_Generale1.setModel(tableModel);
                    Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

                    //tout remettre à 0
                    TextField_Nom2.setText("");
                    jTextField_Prenom2.setText("");
                    jFormattedTextField_date_naissance2.setText("");
                    JOptionPane.showMessageDialog(this, "Le patient a été créé", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_urgences.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Accueil_urgences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Button_Valider3Button_ValiderActionPerformed
    
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
                    Accueil_Med i = new Accueil_Med(conn, new PH("1616161616", "Pan", "Peter", Service.Addictologie, "peterpan", "0456486756", "Biologie"));
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
    private javax.swing.JButton Button_Valider3;
    private javax.swing.JLabel Label_Date_Naissance2;
    private javax.swing.JLabel Label_Nom2;
    private javax.swing.JLabel Label_Plannig4;
    private javax.swing.JLabel Label_Prenom2;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JTable Table_Vue_Generale1;
    private javax.swing.JTextField TextField_Docteur;
    private javax.swing.JTextField TextField_Nom2;
    private javax.swing.JTextField TextField_Patient;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_actualiser1;
    private javax.swing.JButton jButton_actualiser_medecin1;
    private javax.swing.JButton jButton_recherche_medecin;
    private javax.swing.JButton jButton_recherche_patient;
    private javax.swing.JComboBox<String> jComboBox_recherche_praticien;
    private javax.swing.JFormattedTextField jFormattedTextField_date_naissance2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_Prenom2;
    private javax.swing.JLabel nom_medecin;
    private javax.swing.JLabel prenom_medecin;
    private javax.swing.JLabel service;
    private javax.swing.JTable tab_medecins;
    // End of variables declaration//GEN-END:variables

}
