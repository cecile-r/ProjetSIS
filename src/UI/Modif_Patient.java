/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.*;
import static database.RequetesBDProfessionnels.*;
import database.SQLWarningsExceptions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nf.Acte;
import nf.*;
import static UI.Vector.*;
import static nf.Date2.convertirDatetoString;

/**
 *
 * @author Audrey
 */
public class Modif_Patient extends javax.swing.JFrame {

    Connection conn;
    DPI dpi;
    SecretaireAdministrative sa;
    SecretaireMedicale sm;
    Vector medecinsS_traitant;
    List<MedecinTraitant> medecins_traitant;
    Vector entetes2;
    Vector entetesM;

    /**
     * Creates new form Modif_Patient
     */
    public Modif_Patient(Connection conn, SecretaireAdministrative sa, SecretaireMedicale sm, DPI dpi) throws SQLException {
        this.conn = conn;
        this.dpi = dpi;
        this.sa = sa;
        this.sm = sm;
        initComponents();

        //infos identité
        if (sa != null) {
            prenom.setText(sa.getPrenomSecretaireAd());
            nom.setText(sa.getNomSecretaireAd());
        } else {
            prenom.setText(sm.getPrenomSecretaireMed());
            nom.setText(sm.getNomSecretaireMed());
        }

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
        ImageIcon icone3 = new ImageIcon("src/image/retour.png");
        java.awt.Image img3 = icone3.getImage();
        icone3 = new ImageIcon(img3);
        jButton9.setIcon(icone3);

        //Jbutton
        ImageIcon icone = new ImageIcon("src/image/actualise.png");
        java.awt.Image img5 = icone.getImage();
        icone = new ImageIcon(img5);
        jButton_actualiser_medecinT.setIcon(icone);
        ImageIcon icone_recherche = new ImageIcon("src/image/loupe2.png");
        java.awt.Image img_recherche = icone_recherche.getImage();
        icone_recherche = new ImageIcon(img_recherche);
        jButton_recherche_medecinT.setIcon(icone_recherche);

        //ecrire les infos que l'on connait déjà
        jLabel_nom.setText(dpi.getNom());
        jLabel_prenom.setText(dpi.getPrenom());
        jLabel_date.setText(convertirDatetoString(dpi.getDateNaissance()));
        if (null == dpi.getSexe()) {
            jRadioButton3.setSelected(true);
        } else {
            switch (dpi.getSexe()) {
                case femme:
                    RadioButton_F2.setSelected(true);
                    break;
                case homme:
                    RadioButton_H2.setSelected(true);
                    break;
                default:
                    jRadioButton3.setSelected(true);
                    break;
            }
        }
        jTextArea_adresse2.setText(dpi.getAdresse());
        jFormattedTextField_telephone2.setText(dpi.getTelephone());
        jCheckBox_medecinsT.setSelected(dpi.getMedecin_traitant() != null);

        //medecins traitants
        entetes2 = new Vector();
        entetes2.add("Nom");
        entetes2.add("Prénom");
        entetes2.add("Service");
        medecinsS_traitant = new Vector();
        entetesM = new Vector();
        entetesM.add("Nom");
        entetesM.add("Prénom");
        entetesM.add("Téléphone");

        //onglet désactivé
        jTabbedPane1.setEnabledAt(1, false);

        //medecin traitant
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

        int i = 0;
        while (i < medecins_traitant.size() && !medecins_traitant.get(i).getTelephoneMedecinTraitant().equals(dpi.getMedecin_traitant().getTelephoneMedecinTraitant())) {
            i++;
        }
        tab_medecinsT.getSelectionModel().setSelectionInterval(i, i);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Panel_Bandeau = new javax.swing.JPanel();
        Panel_logo = new javax.swing.JLabel();
        Panel_icon_perso = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Button_Valider2 = new javax.swing.JButton();
        Label_Nom2 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        Label_Prenom2 = new javax.swing.JLabel();
        Label_Date_Naissance2 = new javax.swing.JLabel();
        jFormattedTextField_telephone2 = new javax.swing.JFormattedTextField();
        Label_Adresse2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_adresse2 = new javax.swing.JTextArea();
        Label_Commentaire2 = new javax.swing.JLabel();
        Label_Sexe2 = new javax.swing.JLabel();
        jCheckBox_medecinsT = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        RadioButton_F2 = new javax.swing.JRadioButton();
        RadioButton_H2 = new javax.swing.JRadioButton();
        jLabel_nom = new javax.swing.JLabel();
        jLabel_prenom = new javax.swing.JLabel();
        jLabel_date = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton_recherche_medecinT = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TextField_Docteur = new javax.swing.JTextField();
        jButton_actualiser_medecinT = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tab_medecinsT = new javax.swing.JTable();

        setTitle("Modifications");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        Panel_Bandeau.setBackground(new java.awt.Color(213, 123, 213));
        Panel_Bandeau.setRequestFocusEnabled(false);

        Panel_logo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        prenom.setText("prenomSA");

        nom.setText("nomSA");

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
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(Panel_icon_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_BandeauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        Button_Valider2.setBackground(new java.awt.Color(204, 102, 255));
        Button_Valider2.setFont(new java.awt.Font("Lucida Console", 1, 11)); // NOI18N
        Button_Valider2.setText("MODIFIER LE PATIENT");
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

        Label_Nom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Nom2.setText("Nom :");
        Label_Nom2.setDoubleBuffered(true);

        jRadioButton3.setBackground(new java.awt.Color(204, 204, 255));
        jRadioButton3.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jRadioButton3.setText("autre");
        jRadioButton3.setEnabled(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3jRadioButton1ActionPerformed(evt);
            }
        });

        Label_Prenom2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Prenom2.setText("Prénom :");

        Label_Date_Naissance2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Date_Naissance2.setText("Date de naissance :");

        try {
            jFormattedTextField_telephone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## ## ## ## ##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        Label_Adresse2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Adresse2.setText("Adresse :");

        jTextArea_adresse2.setColumns(20);
        jTextArea_adresse2.setRows(5);
        jScrollPane6.setViewportView(jTextArea_adresse2);

        Label_Commentaire2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Commentaire2.setText("Téléphone :");

        Label_Sexe2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        Label_Sexe2.setText("Sexe :");

        jCheckBox_medecinsT.setBackground(new java.awt.Color(204, 204, 255));
        jCheckBox_medecinsT.setEnabled(false);

        jButton3.setBackground(new java.awt.Color(153, 153, 255));
        jButton3.setText("Modifier le medecin traitant");
        jButton3.setActionCommand("Changer le medecin traitant");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3jButton1ActionPerformed(evt);
            }
        });

        RadioButton_F2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        RadioButton_F2.setText("femme");
        RadioButton_F2.setBorder(null);
        RadioButton_F2.setEnabled(false);
        RadioButton_F2.setOpaque(false);
        RadioButton_F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_F2RadioButton_FActionPerformed(evt);
            }
        });

        RadioButton_H2.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        RadioButton_H2.setText("homme");
        RadioButton_H2.setBorder(null);
        RadioButton_H2.setEnabled(false);
        RadioButton_H2.setOpaque(false);

        jLabel_nom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_nom.setText("nom");

        jLabel_prenom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_prenom.setText("prenom");

        jLabel_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_date.setText("date de naissance");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox_medecinsT)
                            .addComponent(Label_Sexe2)
                            .addComponent(Label_Date_Naissance2)
                            .addComponent(Label_Commentaire2)
                            .addComponent(Label_Prenom2)
                            .addComponent(Label_Nom2)
                            .addComponent(Label_Adresse2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(RadioButton_F2)
                                    .addGap(18, 18, 18)
                                    .addComponent(RadioButton_H2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton3))
                                .addComponent(jScrollPane6)
                                .addComponent(jFormattedTextField_telephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel_prenom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(Button_Valider2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Nom2)
                            .addComponent(jLabel_nom))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Date_Naissance2)
                            .addComponent(jLabel_date)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Prenom2)
                            .addComponent(jLabel_prenom))))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Sexe2)
                    .addComponent(RadioButton_F2)
                    .addComponent(RadioButton_H2)
                    .addComponent(jRadioButton3))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Commentaire2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField_telephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Adresse2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox_medecinsT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(Button_Valider2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel2);

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
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 288, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton_actualiser_medecinT, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextField_Docteur, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_recherche_medecinT, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_actualiser_medecinT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField_Docteur)
                    .addComponent(jButton_recherche_medecinT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Bandeau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_Bandeau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1))
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
            i.setLocationRelativeTo(null);
            i.setVisible(true);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        dispose();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width;
        int hauteur = tailleMoniteur.height;

        if (sa != null) {
            try {
                String IPP = dpi.getIPP();
                DPI dpi2;
                Vue_Patient_SA i;
                dpi2 = getDPI(conn, IPP);
                i = new Vue_Patient_SA(conn, dpi2, sa);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        } else { //sm!=null
            try {
                String IPP = dpi.getIPP();
                DPI dpi2;
                Vue_Patient_SM i;
                dpi2 = getDPI(conn, IPP);
                i = new Vue_Patient_SM(conn, dpi2, sm);
                i.setSize(longueur, hauteur);
                i.setVisible(true);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void Button_Valider2Button_ValiderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderMouseClicked

    }//GEN-LAST:event_Button_Valider2Button_ValiderMouseClicked

    private void Button_Valider2Button_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Valider2Button_ValiderActionPerformed
        try {
            ///CREATION D'UN PATIENT
            if (champsCorrects()) {

                String adresse = jTextArea_adresse2.getText();
                String telephone = jFormattedTextField_telephone2.getText();
                telephone = telephone.replaceAll("\\s+", "");
                int index = tab_medecinsT.getSelectedRow();
                MedecinTraitant mt = medecins_traitant.get(index);

                String message = "Etes-vous sûr de vouloir modifier le patient suivant ?";
                message = message + "\n Nom : " + dpi.getNom() + "\n Prénom : " + dpi.getPrenom() + "\n Date de naissance : " + dpi.getDateNaissance().toString() + "\n Sexe : " + dpi.getSexe().toString() + "\n Téléphone : " + telephone + "\n Adresse : " + adresse + "\n Medecin traitant : " + mt.getNomMedecinTraitant() + " " + mt.getPrenomMedecinTraitant();
                int retour = JOptionPane.showConfirmDialog(this, message, "Vérification des informations", JOptionPane.OK_CANCEL_OPTION);

                if (retour == 0) { //les informations sont validees
                    //modification du patient
                    modifierDPI(conn, dpi.getIPP(), telephone, adresse, mt);

                    //SORTIR A LA FIN
                    jButton9ActionPerformed(evt);
                }
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Button_Valider2Button_ValiderActionPerformed

    private void jRadioButton3jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3jRadioButton1ActionPerformed

    private void jButton3jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3jButton1ActionPerformed
        //AJOUTER UN MEDECIN TRAITANT

        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setSelectedIndex(1);


    }//GEN-LAST:event_jButton3jButton1ActionPerformed

    private void RadioButton_F2RadioButton_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_F2RadioButton_FActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RadioButton_F2RadioButton_FActionPerformed

    private void jButton_recherche_medecinTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_recherche_medecinTActionPerformed
        // VALIDATION DE LA RECHERCHE MEDECIN
        String recherche = TextField_Docteur.getText();

        try {
            medecins_traitant = getListeMTNom(conn, recherche);
            medecinsS_traitant = getVectorMT(medecinsS_traitant);
            TableModel tableModel2 = new DefaultTableModel(medecinsS_traitant, entetesM);
            tab_medecinsT.setAutoCreateRowSorter(true);
            tab_medecinsT.setModel(tableModel2);
            tab_medecinsT.setPreferredSize(new java.awt.Dimension(3000, 30 * tab_medecinsT.getRowCount()));

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Accueil_SA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_recherche_medecinTActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //VALIDATION DU MEDECIN TRAITANT

        if (tab_medecinsT.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun medecin traitant sélectionné", "Attention", JOptionPane.WARNING_MESSAGE);

        } else {
            jCheckBox_medecinsT.setSelected(true);
            jTabbedPane1.setSelectedIndex(0);
            jTabbedPane1.setEnabledAt(1, false);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            medecinsS_traitant = getVectorMT(medecinsS_traitant);;
            TableModel tableModel2 = new DefaultTableModel(medecinsS_traitant, entetesM);
            tab_medecinsT.setAutoCreateRowSorter(true);
            tab_medecinsT.setModel(tableModel2);
            tab_medecinsT.setPreferredSize(new java.awt.Dimension(3000, 30 * tab_medecinsT.getRowCount()));

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Accueil_SA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_actualiser_medecinTActionPerformed

    public boolean champsCorrects() throws ParseException {
        boolean v = true;
        if (jFormattedTextField_telephone2.getText().equals("              ")) {
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
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modif_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                DateHeure d18 = new DateHeure(2022, 03, 28, 9, 00); //date de RDV
                RendezVous rdv2 = new RendezVous(d18, "refaire des tests");
                rdv1.setpH(ph1);
                rdv2.setpH(ph1);
                dma1.ajouterRendezVous(rdv1);
                dma1.ajouterRendezVous(rdv2);
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
                ph1.ajouterRdv(rdv2);
                sq1.setDPI(dpi1);
                sq2.setDPI(dpi1);
                fs1.setDPI(dpi1);
                ls1.setDPI(dpi1);
                p1.setDPI(dpi1);
                e1.setDPI(dpi1);
                rdv1.setDPI(dpi1);
                rdv2.setDPI(dpi1);

                SecretaireAdministrative sa = new SecretaireAdministrative("5678452345", "Blabla", "car", "car");

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
                    Modif_Patient i = new Modif_Patient(conn, sa, null, dpi1);
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
    private javax.swing.JButton Button_Valider2;
    private javax.swing.JLabel Label_Adresse2;
    private javax.swing.JLabel Label_Commentaire2;
    private javax.swing.JLabel Label_Date_Naissance2;
    private javax.swing.JLabel Label_Nom2;
    private javax.swing.JLabel Label_Prenom2;
    private javax.swing.JLabel Label_Sexe2;
    private javax.swing.JPanel Panel_Bandeau;
    private javax.swing.JLabel Panel_icon_perso;
    private javax.swing.JLabel Panel_logo;
    private javax.swing.JRadioButton RadioButton_F2;
    private javax.swing.JRadioButton RadioButton_H2;
    private javax.swing.JTextField TextField_Docteur;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_actualiser_medecinT;
    private javax.swing.JButton jButton_recherche_medecinT;
    private javax.swing.JCheckBox jCheckBox_medecinsT;
    private javax.swing.JFormattedTextField jFormattedTextField_telephone2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_date;
    private javax.swing.JLabel jLabel_nom;
    private javax.swing.JLabel jLabel_prenom;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea_adresse2;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel prenom;
    private javax.swing.JTable tab_medecinsT;
    // End of variables declaration//GEN-END:variables

}
