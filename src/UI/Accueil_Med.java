/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import HL7.HL7_SIH;
import java.sql.Connection;
import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.getDPI;
import static database.RequetesBDDPI.getListeDPI;
import static database.RequetesBDDPI.getListeDPIService;
import static database.RequetesBDDPI.listeRendezVous;
import static database.RequetesBDProfessionnels.getListePH;
import static database.RequetesBDProfessionnels.getListePHService;
import database.SQLWarningsExceptions;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static nf.Checker.*;
import static nf.ComparaisonEvaluables.trierEvaluablesParDate;
import nf.DPI;
import nf.Evaluable;
import nf.PH;
import nf.RendezVous;
import nf.Service;

/**
 *
 * @author Audrey
 */
public class Accueil_Med extends javax.swing.JFrame {

    PH ph;
    Connection conn;
    List<PH> medecins;
    Vector medecinsS;
    List<DPI> dpis;
    Vector dpisS;
    List<RendezVous> rdvs;
    Vector rdvsS;
    Vector entetes;
    Vector entetes2;
    Vector entetesRDV;
    LocalDateTime current_date;

    /**
     * Creates new form Connexion
     */
    public Accueil_Med(Connection conn, PH ph) throws SQLException {
        this.conn = conn;
        this.ph = ph;
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
        jButton2.setIcon(iconeD);

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

        //infos identité
        prenom_medecin.setText(ph.getPrenomPH());
        nom_medecin.setText(ph.getNomPH());
        service.setText(ph.getService().toString());

        //TABLEAU PATIENTS
        dpisS = new Vector<>();
        dpis = getListeDPIService(conn, ph.getService().toString());
        dpis = trierDPI(dpis);
        dpisS = getVectorDPI(dpis); //vecteur tableau
        entetes = new Vector();
        entetes.add("Nom");
        entetes.add("Prénom");
        entetes.add("Date de naissance");
        entetes.add("Sexe");
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
        tab_medecins.setModel(tableModel2);
        tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));

        //PLANNING
        current_date = LocalDateTime.now();
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year,current_month, current_day);
        Date date_courante2 = new Date(current_year-1900,current_month-1, current_day);
        jLabel1.setText(convertirDatetoString(date_courante2));
        this.rdvs = new ArrayList<>();
        rdvsS = new Vector();
        this.rdvs = listeRendezVous(conn, date_courante,ph);
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        rdvsS = getVectorPHplanning(evs); //vecteur tableau
        entetesRDV = new Vector();
        entetesRDV.add("Horaire");
        entetesRDV.add("Patient");
        entetesRDV.add("Remarque");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        ScrollPane_Planning = new javax.swing.JScrollPane();
        tab_planning = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Label_Plannig = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        prenom_medecin.setText("prenom_medecin");

        nom_medecin.setText("nom_medecin");

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
                .addGap(18, 18, 18)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom_medecin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_BandeauLayout.setVerticalGroup(
            Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_icon_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_BandeauLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(prenom_medecin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nom_medecin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(service)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Panel_BandeauLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_actualiser1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TextField_Patient, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(342, 342, 342))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 202, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextField_Patient, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jButton_actualiser1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jButton_recherche_patient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Button_Selectionner, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton_actualiser_medecin1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TextField_Docteur, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton_actualiser_medecin1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_recherche_medecin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox_recherche_praticien, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                                .addGap(86, 86, 86)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                        .addGap(80, 80, 80))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(TextField_Docteur, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Praticien hospitalier", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        tab_planning.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        tab_planning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
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
                "Horaire", "Personne", "Remarque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_planning.setRowHeight(40);
        ScrollPane_Planning.setViewportView(tab_planning);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPane_Planning, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(ScrollPane_Planning, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(169, 206, 243));

        Label_Plannig.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N
        Label_Plannig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Plannig.setText("Mon planning");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_Plannig, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Plannig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(242, 242, 242))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(239, 239, 239))))
        );

        jTabbedPane1.addTab("Mon planning", jPanel6);

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
                //HL7_SIH hl = new HL7_SIH(conn,4445);
                //hl.recuperationDonnees();
                
                int index = Table_Vue_Generale1.getSelectedRow();
                DPI dpi = getDPI(conn, dpis.get(index).getIPP());
                Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
                //int longueur = tailleMoniteur.width;
                //int hauteur = tailleMoniteur.height;
                Vue_Patient_Med i = new Vue_Patient_Med(conn, dpi, ph);
                //i.setSize(longueur, hauteur);
                i.setVisible(true);
                dispose();

            } catch (SQLException ex) {
                Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
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
        //RECHERCHE PATIENT ENTREE
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String recherche = TextField_Patient.getText();
            try {
                if (!recherche.equals("")) {
                    dpis = getListeDPI(conn, recherche, ph.getService().toString());
                    dpis = trierDPI(dpis);
                    dpisS = getVectorDPI(dpis);
                    TableModel tableModel = new DefaultTableModel(dpisS, entetes);
                    Table_Vue_Generale1.setAutoCreateRowSorter(true);
                    Table_Vue_Generale1.setModel(tableModel);
                    Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

                }
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TextField_PatientKeyPressed

    private void jButton_actualiser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser1ActionPerformed
        try {
            //RECHARGER DPI
            dpis = getListeDPIService(conn, ph.getService().toString());
            dpis = trierDPI(dpis);
            dpisS = getVectorDPI(dpis);
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

            TextField_Patient.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser1ActionPerformed

    private void jButton_recherche_patientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_patientActionPerformed
        //RECHERCHE PATIENT
        String recherche = TextField_Patient.getText();
        try {
            dpis = getListeDPI(conn, recherche, ph.getService().toString());
            dpis = trierDPI(dpis);
            dpisS = getVectorDPI(dpis);
            Vector entetes = new Vector();
            entetes.add("Nom");
            entetes.add("Prénom");
            entetes.add("Date de naissance");
            entetes.add("Sexe");
            TableModel tableModel = new DefaultTableModel(dpisS, entetes);
            Table_Vue_Generale1.setModel(tableModel);
            Table_Vue_Generale1.setPreferredSize(new java.awt.Dimension(3000, 30 * Table_Vue_Generale1.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type_recherche.equals("Service")) {
            try {
                medecins = getListePHService(conn, recherche);
                medecinsS = getVectorPH(medecins); //vecteur tableau
                TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
                tab_medecins.setModel(tableModel2);
                tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
            } catch (SQLException ex) {
                Logger.getLogger(Accueil_SM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_recherche_medecinActionPerformed

    private void jButton_actualiser_medecin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualiser_medecin1ActionPerformed
        //RECHARGER MEDECINS
        try {
            medecins = getListePH(conn);
            medecins = trierPH(medecins); //tri par ordre alphabétique
            medecinsS = getVectorPH(medecins); //vecteur tableau
            TableModel tableModel2 = new DefaultTableModel(medecinsS, entetes2);
            tab_medecins.setModel(tableModel2);
            tab_medecins.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_medecins.getRowCount()));
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser_medecin1ActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //JOUR PRECEDENT
        current_date = current_date.minusDays(1);
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year,current_month, current_day);
        Date date_courante2 = new Date(current_year-1900,current_month-1, current_day);
        jLabel1.setText(convertirDatetoString(date_courante2));
        try {
            this.rdvs = listeRendezVous(conn,date_courante ,ph);
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        rdvsS = getVectorPHplanning(evs); //vecteur tableau
        TableModel tableModel3 = new DefaultTableModel(rdvsS, entetesRDV);
        tab_planning.setModel(tableModel3);
        tab_planning.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_planning.getRowCount()));
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //JOUR SUIVANT
        current_date = current_date.plusDays(1);
        int current_year = current_date.getYear();
        int current_month = current_date.getMonthValue();
        int current_day = current_date.getDayOfMonth();
        Date date_courante = new Date(current_year,current_month, current_day);
        Date date_courante2 = new Date(current_year-1900,current_month-1, current_day);
        jLabel1.setText(convertirDatetoString(date_courante2));
        try {
            this.rdvs = listeRendezVous(conn,date_courante ,ph);
        } catch (SQLException ex) {
            Logger.getLogger(Accueil_Med.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Evaluable> evs = new ArrayList<>();
        evs.addAll(rdvs);
        evs = trierEvaluablesParDate(evs);
        rdvsS = getVectorPHplanning(evs); //vecteur tableau
        TableModel tableModel3 = new DefaultTableModel(rdvsS, entetesRDV);
        tab_planning.setModel(tableModel3);
        tab_planning.setPreferredSize(new java.awt.Dimension(3000, 40 * tab_planning.getRowCount()));
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public class jTableRender extends DefaultTableCellRenderer {
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        
        Object o = table.getValueAt(row, 1);
        if (o != null && component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if(row==4){
                Color gris = new Color(240,240,240);
                component.setBackground(gris);
            }else if (row%2==0) {
                Color bleuclair = new Color(199,229,237);
                component.setBackground(bleuclair);
            }else if(row%2==1){
                Color bleuclair2 = new Color(188,213,220);
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
    private javax.swing.JLabel Label_Plannig;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JPanel Panel_Main;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JScrollPane ScrollPane_Planning;
    private javax.swing.JTable Table_Vue_Generale1;
    private javax.swing.JTextField TextField_Docteur;
    private javax.swing.JTextField TextField_Patient;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_actualiser1;
    private javax.swing.JButton jButton_actualiser_medecin1;
    private javax.swing.JButton jButton_recherche_medecin;
    private javax.swing.JButton jButton_recherche_patient;
    private javax.swing.JComboBox<String> jComboBox_recherche_praticien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel nom_medecin;
    private javax.swing.JLabel prenom_medecin;
    private javax.swing.JLabel service;
    private javax.swing.JTable tab_medecins;
    private javax.swing.JTable tab_planning;
    // End of variables declaration//GEN-END:variables

}
