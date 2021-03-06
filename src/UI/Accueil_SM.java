/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.*;
import static database.RequetesBDProfessionnels.getListePH;
import static database.RequetesBDProfessionnels.getListePHService;
import database.SQLWarningsExceptions;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nf.PH;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import static nf.ComparaisonEvaluables.trierEvaluablesParDate;
import nf.*;
import static UI.Vector.*;
import static nf.Date2.convertirDatetoString;

/**
 *
 * @author Audrey
 */
public class Accueil_SM extends javax.swing.JFrame {

    Connection conn;
    SecretaireMedicale sm;
    List<PH> medecins;
    Vector medecinsS;
    List<DPI> dpis;
    Vector dpisS;
    List<DPI> dpisF;
    Vector dpisFS;
    Vector entetes;
    Vector entetes2;
    LocalDateTime current_date;
    List<RendezVous> rdvs;
    Vector rdvsS;
    Vector entetesRDV;

    /**
     * Creates new form Connexion
     */
    public Accueil_SM(Connection conn, SecretaireMedicale sm) throws SQLException {
        this.conn = conn;
        this.sm = sm;
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
        jButton_actualiserOuvrirDPI.setIcon(icone);
        ImageIcon icone_recherche = new ImageIcon("src/image/loupe2.png");
        java.awt.Image img_recherche = icone_recherche.getImage();
        icone_recherche = new ImageIcon(img_recherche);
        jButton_recherche_medecin.setIcon(icone_recherche);
        jButton_recherche_patient.setIcon(icone_recherche);
        jButton_recherche_patientOuvrirDPI.setIcon(icone_recherche);
        ImageIcon icone_details = new ImageIcon("src/image/details.png");
        java.awt.Image img_details = icone_details.getImage();
        icone_details = new ImageIcon(img_details);
        Button_Selectionner.setIcon(icone_details);

        //infos identit??
        prenom_SM.setText(sm.getPrenomSecretaireMed());
        nom_SM.setText(sm.getNomSecretaireMed());

        //TABLEAU PATIENTS
        dpisS = new Vector<>();
        dpis = getListeDPIService(conn, sm.getService().toString());
        dpis = trierDPI(dpis); //tri par ordre alphab??tique
        dpisS = getVectorDPI(dpis); //vecteur tableau
        entetes = new Vector();
        entetes.add("Nom");
        entetes.add("Pr??nom");
        entetes.add("Date de naissance");
        entetes.add("Sexe");
        TableModel tableModel = new DefaultTableModel(dpisS, entetes);
        Table_Vue_Generale1.setModel(tableModel);
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_Vue_Generale1.getRowCount()));

        //TABLEAU PH
        medecinsS = new Vector();
        medecins = getListePH(conn);
        medecins = trierPH(medecins); //tri par ordre alphab??tique
        medecinsS = getVectorPH(medecins); //vecteur tableau
        entetes2 = new Vector();
        entetes2.add("Nom");
        entetes2.add("Pr??nom");
        entetes2.add("Service");
        TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
        tab_medecins.setModel(tableModel2);
        tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));

        //TABLEAU DPI DEMI-OUVERTS
        dpisF = new ArrayList<>();
        dpisF = getListeDPIEntrantService(conn, sm.getService());
        dpisF = trierDPI(dpisF);
        dpisFS = getVectorDPI(dpisF);
        TableModel tableModel4 = new DefaultTableModel(dpisFS, entetes);
        Table_DPI_ferme.setModel(tableModel4);
        Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_DPI_ferme.getRowCount()));

        //selection localisation patient
        jPanel_localisation.setVisible(false);
        DefaultComboBoxModel dbm2 = new DefaultComboBoxModel(Service.values());
        jComboBox_serviceG.setModel(dbm2);
        DefaultComboBoxModel dbm3 = new DefaultComboBoxModel(Lit.values());
        jComboBoxLit.setModel(dbm3);

        //PLANNING
        current_date = LocalDateTime.now();
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year, current_month, current_day);
        Date date_courante2 = new Date(current_year - 1900, current_month - 1, current_day);
        jLabel3.setText(convertirDatetoString(date_courante2));
        this.rdvs = new ArrayList<>();
        rdvsS = new Vector();
        this.rdvs = listeRendezVous(conn, date_courante, sm.getService());
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        entetesRDV = new Vector();
        entetesRDV = getVectorHorairePlanning();
        rdvsS = getVectorSMplanning(evs); //vecteur tableau
        TableModel tableModel3 = new DefaultTableModel(rdvsS, entetesRDV);
        tab_planning.setModel(tableModel3);
        tab_planning.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_planning.getRowCount()));
        tab_planning.setDefaultEditor(Object.class, null);
        tab_planning.setDefaultRenderer(Object.class, new jTableRender());
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
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom_SM = new javax.swing.JLabel();
        nom_SM = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Panel_Hospitalisations = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Vue_Generale1 = new javax.swing.JTable();
        TextField_Patient = new javax.swing.JTextField();
        jButton_actualiser = new javax.swing.JButton();
        jButton_recherche_patient = new javax.swing.JButton();
        Button_Selectionner = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        Label_Plannig4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab_medecins = new javax.swing.JTable();
        TextField_Docteur1 = new javax.swing.JTextField();
        jComboBox_recherche_praticien = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_actualiser_medecin = new javax.swing.JButton();
        jButton_recherche_medecin = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        Label_Plannig3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        Label_Plannig1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_planning = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table_DPI_ferme = new javax.swing.JTable();
        jButton_actualiserOuvrirDPI = new javax.swing.JButton();
        TextField_Patient1 = new javax.swing.JTextField();
        jButton_recherche_patientOuvrirDPI = new javax.swing.JButton();
        jPanel_localisation = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_serviceG = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxLit = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
        Label_Plannig2 = new javax.swing.JLabel();

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

        prenom_SM.setBackground(new java.awt.Color(204, 204, 255));
        prenom_SM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        prenom_SM.setText("prenom");

        nom_SM.setBackground(new java.awt.Color(204, 204, 255));
        nom_SM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nom_SM.setText("nom");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenom_SM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom_SM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(prenom_SM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nom_SM)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BandeauLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addComponent(Panel_logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Panel_Hospitalisations.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane5.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        Table_Vue_Generale1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        Table_Vue_Generale1.setMinimumSize(new java.awt.Dimension(45, 500));
        Table_Vue_Generale1.setName(""); // NOI18N
        Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(300, 300));
        Table_Vue_Generale1.setRowHeight(20);
        Table_Vue_Generale1.setDefaultEditor(Object.class, null);
        jScrollPane3.setViewportView(Table_Vue_Generale1);
        Table_Vue_Generale1.getAccessibleContext().setAccessibleName("");

        TextField_Patient.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Patient.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Patient.setText("Patient");
        TextField_Patient.setToolTipText("Entrez le nom du patient recherch??");
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
        Button_Selectionner.setText("D??tails");
        Button_Selectionner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SelectionnerActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton_actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextField_Patient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton_actualiser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane5.addTab("Patients", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

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
                "Nom", "Pr??nom", "Service"
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

        TextField_Docteur1.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextField_Docteur1.setForeground(new java.awt.Color(153, 153, 153));
        TextField_Docteur1.setText("Praticien / Service");
        TextField_Docteur1.setToolTipText("Entrez le nom du praticien recherch?? ou le service auquel il appartient");
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

        jComboBox_recherche_praticien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nom", "Service" }));
        jComboBox_recherche_praticien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_recherche_praticienActionPerformed(evt);
            }
        });

        jLabel2.setText("Rechercher par :");

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton_actualiser_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Docteur1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(541, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_actualiser_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextField_Docteur1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addGap(78, 78, 78))
        );

        jPanel12.setBackground(new java.awt.Color(169, 206, 243));

        Label_Plannig3.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        Label_Plannig3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Plannig3.setText("Praticiens hospitaliers");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Plannig3, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Plannig3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(405, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(303, 303, 303))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 22, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 23, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 24, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1023, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane5.addTab("Praticiens hospitaliers", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jPanel10.setBackground(new java.awt.Color(169, 206, 243));

        Label_Plannig1.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        Label_Plannig1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Plannig1.setText("Planning du service");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Plannig1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Plannig1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_planning.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tab_planning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_planning.setRowHeight(40);
        jScrollPane1.setViewportView(tab_planning);

        jButton2.setBackground(new java.awt.Color(169, 206, 243));
        jButton2.setText(">>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(169, 206, 243));
        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Console", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Planning", jPanel5);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        Table_DPI_ferme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nom", "Pr??nom", "Date de naissance", "Sexe"
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
        Table_DPI_ferme.setRowHeight(30);
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
        TextField_Patient1.setToolTipText("Entrez le nom du patient recherch??");
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
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choisir la localisation du patient");

        jComboBox_serviceG.setMaximumRowCount(30);
        jComboBox_serviceG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_serviceG.setPreferredSize(new java.awt.Dimension(59, 300));

        jLabel4.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel4.setText("Num??ro de chambre :");

        jComboBoxLit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(213, 123, 213));
        jButton1.setText("Affecter la localisation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Service g??ographique");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout jPanel_localisationLayout = new javax.swing.GroupLayout(jPanel_localisation);
        jPanel_localisation.setLayout(jPanel_localisationLayout);
        jPanel_localisationLayout.setHorizontalGroup(
            jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_localisationLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_localisationLayout.createSequentialGroup()
                        .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxLit, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_localisationLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_localisationLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
            .addGroup(jPanel_localisationLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_serviceG, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_localisationLayout.setVerticalGroup(
            jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_localisationLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_serviceG, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel_localisationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jComboBoxLit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jPanel11.setBackground(new java.awt.Color(169, 206, 243));

        Label_Plannig2.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        Label_Plannig2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Plannig2.setText("Affecter une entr??e dans le service");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Plannig2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Plannig2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton_actualiserOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextField_Patient1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_patientOuvrirDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)))
                .addComponent(jPanel_localisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_actualiserOuvrirDPI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_patientOuvrirDPI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_Patient1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel_localisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1057, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        jTabbedPane5.addTab("G??rer les entr??es", jPanel1);

        javax.swing.GroupLayout Panel_HospitalisationsLayout = new javax.swing.GroupLayout(Panel_Hospitalisations);
        Panel_Hospitalisations.setLayout(Panel_HospitalisationsLayout);
        Panel_HospitalisationsLayout.setHorizontalGroup(
            Panel_HospitalisationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_HospitalisationsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_HospitalisationsLayout.setVerticalGroup(
            Panel_HospitalisationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 549, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Panel_MainLayout = new javax.swing.GroupLayout(Panel_Main);
        Panel_Main.setLayout(Panel_MainLayout);
        Panel_MainLayout.setHorizontalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Hospitalisations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        Panel_MainLayout.setVerticalGroup(
            Panel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_MainLayout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_Hospitalisations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Connexion i;
        try {
            i = new Connexion(conn);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formMouseClicked

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
            dpis = getListeDPIService(conn, sm.getService().toString());
            dpis = trierDPI(dpis); //tri par ordre alphab??tique
            dpisS = getVectorDPI(dpis); //vecteur tableau
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_Vue_Generale1.getRowCount()));

            TextField_Patient.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiserActionPerformed

    private void TextField_PatientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_PatientKeyPressed
        //RECHERCHE PATIENT ENTREE
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String recherche = TextField_Patient.getText();
            try {
                if (!recherche.equals("")) {
                    dpis = getListeDPI(conn, recherche, sm.getService().toString());
                    dpisS = getVectorDPI(dpis);
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
            medecins = trierPH(medecins); //tri par ordre alphab??tique
            medecinsS = getVectorPH(medecins); //vecteur tableau
            TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
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
                medecins = trierPH(medecins); //tri par ordre alphab??tique
                medecinsS = getVectorPH(medecins); //vecteur tableau
                Vector entetes2 = new Vector();
                entetes2.add("Nom");
                entetes2.add("Pr??nom");
                entetes2.add("Service");

                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type_recherche.equals("Service")) {
            try {
                medecins = getListePHService(conn, recherche);
                medecins = trierPH(medecins); //tri par ordre alphab??tique
                medecinsS = getVectorPH(medecins); //vecteur tableau
                Vector entetes2 = new Vector();
                entetes2.add("Nom");
                entetes2.add("Pr??nom");
                entetes2.add("Service");

                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
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
            dpis = trierDPI(dpis); //tri par ordre alphab??tique
            dpisS = getVectorDPI(dpis); //vecteur tableau
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 20 * Table_Vue_Generale1.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_recherche_patientActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Connexion i;
        try {
            i = new Connexion(conn);
            i.setLocationRelativeTo(null);
            i.setVisible(true);
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void Button_SelectionnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SelectionnerActionPerformed
        //VISUALISATION D UN PATIENT PARTICULIER
        if (Table_Vue_Generale1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun patient n'est s??lectionn?? dans la liste", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            try {


                int index = Table_Vue_Generale1.getSelectedRow();
                DPI dpi = getDPI(conn, dpis.get(index).getIPP());
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                int longueur = tailleMoniteur.width;
                int hauteur = tailleMoniteur.height;
                Vue_Patient_SM i = new Vue_Patient_SM(conn, dpi, sm);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
                dispose();

            } catch (SQLException ex) {
                Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Button_SelectionnerActionPerformed

    private void Table_DPI_fermeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DPI_fermeMouseClicked
        jPanel_localisation.setVisible(true);
    }//GEN-LAST:event_Table_DPI_fermeMouseClicked

    private void jButton_actualiserOuvrirDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiserOuvrirDPIActionPerformed
        try {
            //RECHARGER LES DPI FERMES
            dpisF = getListeDPIEntrantService(conn, sm.getService());
            dpisF = trierDPI(dpisF);
            dpisFS = getVectorDPI(dpisF);
            TableModel tableModel4 = new DefaultTableModel(dpisFS, entetes);
            Table_DPI_ferme.setModel(tableModel4);
            Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_DPI_ferme.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiserOuvrirDPIActionPerformed

    private void TextField_Patient1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextField_Patient1MouseClicked
        TextField_Patient1.setText("");
        TextField_Patient1.setForeground(Color.black);
    }//GEN-LAST:event_TextField_Patient1MouseClicked

    private void TextField_Patient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_Patient1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_Patient1ActionPerformed

    private void TextField_Patient1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_Patient1KeyPressed

    }//GEN-LAST:event_TextField_Patient1KeyPressed

    private void jButton_recherche_patientOuvrirDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_patientOuvrirDPIActionPerformed
        String recherche = TextField_Patient1.getText();
        try {
            dpisF = getListeDPIEntrantServiceNom(conn, sm.getService(), recherche);
            dpisF = trierDPI(dpisF);
            dpisFS = getVectorDPI(dpisF);
            TableModel tableModel = new DefaultTableModel(dpisFS, entetes);
            Table_DPI_ferme.setModel(tableModel);
            Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_DPI_ferme.getRowCount()));
            
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_recherche_patientOuvrirDPIActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //JOUR PREDECENT
        current_date = current_date.minusDays(1);
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year, current_month, current_day);
        Date date_courante2 = new Date(current_year - 1900, current_month - 1, current_day);
        jLabel3.setText(convertirDatetoString(date_courante2));
        try {
            this.rdvs = listeRendezVous(conn, date_courante, sm.getService());
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        entetesRDV = new Vector();
        entetesRDV = getVectorHorairePlanning();
        rdvsS = getVectorSMplanning(evs); //vecteur tableau
        TableModel tableModel3 = new DefaultTableModel(rdvsS, entetesRDV);
        tab_planning.setModel(tableModel3);
        tab_planning.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_planning.getRowCount()));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //JOUR SUIVANT
        current_date = current_date.plusDays(1);
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year, current_month, current_day);
        Date date_courante2 = new Date(current_year - 1900, current_month - 1, current_day);
        jLabel3.setText(convertirDatetoString(date_courante2));
        try {
            this.rdvs = listeRendezVous(conn, date_courante, sm.getService());
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        entetesRDV = new Vector();
        entetesRDV = getVectorHorairePlanning();
        rdvsS = getVectorSMplanning(evs); //vecteur tableau
        TableModel tableModel3 = new DefaultTableModel(rdvsS, entetesRDV);
        tab_planning.setModel(tableModel3);
        tab_planning.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_planning.getRowCount()));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // AFFECTER LA LOCALISATION
        if (jComboBox_serviceG.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un service responsable", "Attention", JOptionPane.WARNING_MESSAGE);

        } else if (jFormattedTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Merci d'entrer un numero de chambre", "Attention", JOptionPane.WARNING_MESSAGE);

        } else if (jComboBoxLit.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Merci de s??lectioner un lit", "Attention", JOptionPane.WARNING_MESSAGE);

        } else {
            //recup informations
            Service service_geo = (Service) jComboBox_serviceG.getSelectedItem();
            int indexSelected = Table_DPI_ferme.getSelectedRow();
            DPI dpi = (DPI) dpisF.get(indexSelected);
            int numero = Integer.parseInt(jFormattedTextField1.getText());
            Lit lit = (Lit) jComboBoxLit.getSelectedItem();
            //creer lalocalisation
            try {
                modifierLocalisationSM(conn, dpi.getIPP(), numero, lit, service_geo);
                dpisF = getListeDPIEntrantService(conn, sm.getService());
                dpisF = trierDPI(dpisF);
                dpisFS = getVectorDPI(dpisF);
                TableModel tableModel4 = new DefaultTableModel(dpisFS, entetes);
                Table_DPI_ferme.setModel(tableModel4);
                Table_DPI_ferme.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_DPI_ferme.getRowCount()));

                JOptionPane.showMessageDialog(this, "Le patient a maintenant une localisation dans le service", "Information", JOptionPane.INFORMATION_MESSAGE);
                jComboBox_serviceG.setSelectedIndex(-1);
                jComboBoxLit.setSelectedIndex(-1);
                jFormattedTextField1.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_recherche_praticienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_recherche_praticienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_recherche_praticienActionPerformed

    public class jTableRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Object o = table.getValueAt(row, 1);
            if (o != null && component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (row % 2 == 0) {
                    Color bleuclair = new Color(199, 229, 237);
                    component.setBackground(bleuclair);
                } else if (row % 2 == 1) {
                    Color bleuclair2 = new Color(188, 213, 220);
                    component.setBackground(bleuclair2);
                }

                label.setHorizontalAlignment(CENTER);
            }
            return component;
        }
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
                    Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                    int longueur = tailleMoniteur.width;
                    int hauteur = tailleMoniteur.height;
                    Accueil_SM i;
                    i = new Accueil_SM(conn, new SecretaireMedicale("1462354712", "Boss", "Pierre", Service.Biologie_clinique, "maison"));
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
    private javax.swing.JLabel Label_Plannig1;
    private javax.swing.JLabel Label_Plannig2;
    private javax.swing.JLabel Label_Plannig3;
    private javax.swing.JLabel Label_Plannig4;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Hospitalisations;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JTable Table_DPI_ferme;
    private javax.swing.JTable Table_Vue_Generale1;
    private javax.swing.JTextField TextField_Docteur1;
    private javax.swing.JTextField TextField_Patient;
    private javax.swing.JTextField TextField_Patient1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_actualiser;
    private javax.swing.JButton jButton_actualiserOuvrirDPI;
    private javax.swing.JButton jButton_actualiser_medecin;
    private javax.swing.JButton jButton_recherche_medecin;
    private javax.swing.JButton jButton_recherche_patient;
    private javax.swing.JButton jButton_recherche_patientOuvrirDPI;
    private javax.swing.JComboBox<String> jComboBoxLit;
    private javax.swing.JComboBox<String> jComboBox_recherche_praticien;
    private javax.swing.JComboBox<String> jComboBox_serviceG;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_localisation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel nom_SM;
    private javax.swing.JLabel prenom_SM;
    private javax.swing.JTable tab_medecins;
    private javax.swing.JTable tab_planning;
    // End of variables declaration//GEN-END:variables

}
