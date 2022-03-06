/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import nf.*;

/**
 *
 * @author garci
 */
public class Calendrier extends JPanel implements ActionListener {

    JPanel container;
    JLabel jlabel;
    Vector btnVect = new Vector();
    Vector btnVect2 = new Vector();
    SButton suiv;
    SButton prec;
    List<RendezVous> rdvs;

    int x0, y0, mois, annee;//x0 et y0 sont les coordonnées pour setLocation 

    Calendrier(int x0, int y0, int iMois, int iAnnee, JPanel container, List<RendezVous> rdvs) {
        super();
        this.container = container;
        this.x0 = x0;
        this.y0 = y0;
        this.mois = iMois;
        this.annee = iAnnee;
        this.rdvs = rdvs;
        container.setLayout(null);
        setOpaque(false); //si vous utiliser une image comme fond 
        setBounds(x0, y0, 420, 360);
        setLayout(null);
        container.add(this);

        prec = new SButton("<<", 100, 0, 50, 28, true, this);
        new SLabel(string(iMois) + " " + iAnnee, 1, 14, 150, 0, 120, 30, this);
        suiv = new SButton(">>", 270, 0, 50, 28, true, this);
        prec.addActionListener(this);
        suiv.addActionListener(this);

        new SLabel("Lun", 1, 12, 60, 30, 60, 30, this);
        new SLabel("Mar", 1, 12, 120, 30, 60, 30, this);
        new SLabel("Mer", 1, 12, 180, 30, 60, 30, this);
        new SLabel("Jeu", 1, 12, 240, 30, 60, 30, this);
        new SLabel("Ven", 1, 12, 300, 30, 60, 30, this);
        new SLabel("Sam", 1, 12, 360, 30, 60, 30, this);
        new SLabel("Dim", 1, 12, 0, 30, 60, 30, this);

        remplirVect();
        afficherGrille(iMois, iAnnee, rdvs);
    }

    Calendrier(int x0, int y0, int iMois, int iAnnee, JPanel container, JLabel jlabel) {
        super();
        this.rdvs = null;
        this.container = container;
        this.jlabel = jlabel;
        this.x0 = x0;
        this.y0 = y0;
        this.mois = iMois;
        this.annee = iAnnee;
        container.setLayout(null);
        setOpaque(false); //si vous utiliser une image comme fond 
        setBounds(x0, y0, 420, 360);
        setLayout(null);
        container.add(this);

        prec = new SButton("<<", 100, 0, 50, 28, true, this);
        new SLabel(string(iMois) + " " + iAnnee, 1, 14, 150, 0, 120, 30, this);
        suiv = new SButton(">>", 270, 0, 50, 28, true, this);
        prec.addActionListener(this);
        suiv.addActionListener(this);

        new SLabel("Lun", 1, 12, 60, 30, 60, 30, this);
        new SLabel("Mar", 1, 12, 120, 30, 60, 30, this);
        new SLabel("Mer", 1, 12, 180, 30, 60, 30, this);
        new SLabel("Jeu", 1, 12, 240, 30, 60, 30, this);
        new SLabel("Ven", 1, 12, 300, 30, 60, 30, this);
        new SLabel("Sam", 1, 12, 360, 30, 60, 30, this);
        new SLabel("Dim", 1, 12, 0, 30, 60, 30, this);

        remplirVect();
        afficherGrille(iMois, iAnnee);
    }

    public void remplirVectAfficher(DateHeure dh) {
        int ligne = 0;
        while (ligne < 9) {

            JButton btn = new JButton();
            //btn.setBounds(colonne * 60, (ligne * 40) + 40, 100, 30);

            String jch = Integer.toString(ligne);
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    String ch = "";
                    ch = ch + "Confirmation du rendez-vous suivant :\nLe " + dh.getJour() + "à " + jch;
                    JOptionPane.showMessageDialog(container, "", "Confirmation du rendez-vous", JOptionPane.OK_CANCEL_OPTION);
                }
            });

            btn.setVisible(true);
            int debut = ligne + 8;
            int fin = ligne + 8 + 1;
            btn.setLabel(debut + "h - " + fin + "h");
            container.add(btn);
            btn.addActionListener(this);
            if (ligne + 8 == 12) {
                btn.setEnabled(false);
            }

        }
        ligne++;

    }

    //procedure de remplissage du vecteur par des boutton standard 
    public void remplirVect() {
        int ligne = 0;
        while (ligne < 6) {
            int colonne = 0;
            while (colonne < 7) {
                JButton btn = new JButton();
                btn.setBounds(colonne * 60, (ligne * 60) + 60, 60, 60);
                btn.setVisible(false);
                btnVect.addElement(btn);
                colonne++;
            }
            ligne++;
        }
    }

    //CONSULTATION
    public void afficherGrille(int iMois, int iAnnee, List<RendezVous> rdvs) {
        for (int i = jour(iAnnee, iMois, 1); i < (jour(iAnnee, iMois, 1) + nbreJour(iMois, iAnnee)); i++) {
            JButton b = new JButton();
            b = (JButton) btnVect.elementAt(i - 1);

            for (int j = 0; j < rdvs.size(); j++) {
                RendezVous rdv = rdvs.get(j);
                DateHeure dh = rdv.getDateHeure();
                if (dh.getAnnee() == iAnnee && dh.getMois() == iMois && dh.getJour() == i - 2) {
                    if (nf.DateHeure.estApresDateCourante(dh.getAnnee(), dh.getMois(), dh.getJour(), dh.getHeure(), dh.getMinutes())) {
                        b.setBackground(Color.BLUE);
                    } else {
                        b.setBackground(Color.ORANGE);
                    }
                    b.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            String ch = "Rendez-vous \n\nLe " + dh.getJour() + "/" + dh.getMois() + "/" + dh.getAnnee();
                            ch = ch + "\nA " + dh.getHeure() + ":" + dh.getMinutes();
                            ch = ch + "\nAvec ";
                            ch = ch + rdv.getpH().toString();
                            JOptionPane.showMessageDialog(container, ch, "Détails du rendez-vous", JOptionPane.INFORMATION_MESSAGE);

                        }
                    });
                }
            }

            b.setVisible(true);
            b.setLabel(new Integer(i - jour(iAnnee, iMois, 1) + 1).toString());
            add(b);
            b.addActionListener(this);
        }
    }

    //PRISE DE RDV// PANNEAU 1
    public void afficherGrille(int iMois, int iAnnee) {
        for (int i = jour(iAnnee, iMois, 1); i < (jour(iAnnee, iMois, 1) + nbreJour(iMois, iAnnee)); i++) {
            JButton b = new JButton();
            b = (JButton) btnVect.elementAt(i - 1);
            int j = i;

            b.setVisible(true);
            b.setLabel(new Integer(i - jour(iAnnee, iMois, 1) + 1).toString());
            add(b);

            int jo = Integer.parseInt(b.getText());
            b.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    //récupérer rdvs de la journee du medecin

                    DateHeure dh = new DateHeure(iAnnee, iMois, jo, 0, 0);
                    jlabel.setText(DateHeure.convertirDateHeuretoString(dh));
                }
            });
            b.addActionListener(this);
        }
    }

    public int jour(int annee, int mois, int jour) {
        int retour = 0;
        for (int i = 1900; i < annee; i++) {
            if (i % 4 == 0) {
                retour += 366;
            } else {
                retour += 365;
            }
        }
        for (int i = 1; i < mois; i++) {
            if ((i == 1) || (i == 3) || (i == 5) || (i == 7) || (i == 8) || (i == 10) || (i == 12)) {
                retour += 31;
            }
            if ((i == 4) || (i == 6) || (i == 9) || (i == 11)) {
                retour += 30;
            }
            if ((i == 2) && (annee % 4 == 0)) {
                retour += 29;
            }
            if ((i == 2) && (annee % 4 != 0)) {
                retour += 28;
            }
        }
        for (int i = 0; i < jour; i++) {
            retour += 1;
        }
        if (retour % 7 == 0) {
            retour = 7;
        } else {
            retour = retour % 7;
        }
        return (retour);
    }

    public String string(int i) {
        String retour = new String();

        switch (i) {
            case 1: {
                retour = "Janvier";
                break;
            }
            case 2: {
                retour = "Février";
                break;
            }
            case 3: {
                retour = "Mars";
                break;
            }
            case 4: {
                retour = "Avril";
                break;
            }
            case 5: {
                retour = "Mai";
                break;
            }
            case 6: {
                retour = "Juin";
                break;
            }
            case 7: {
                retour = "Juillet";
                break;
            }
            case 8: {
                retour = "Aout";
                break;
            }
            case 9: {
                retour = "Septembre";
                break;
            }
            case 10: {
                retour = "Octobre";
                break;
            }
            case 11: {
                retour = "Novembre";
                break;
            }
            case 12: {
                retour = "Décembre";
                break;
            }
        }
        return retour;
    }

    public int nbreJour(int mois, int annee) {
        int retour = 0;

        switch (mois) {
            case 1: {
                retour = 31;
                break;
            }
            case 2: {
                if (annee % 4 == 0) {
                    retour = 29;
                } else {
                    retour = 28;
                }
                break;
            }
            case 3: {
                retour = 31;
                break;
            }
            case 4: {
                retour = 30;
                break;
            }
            case 5: {
                retour = 31;
                break;
            }
            case 6: {
                retour = 30;
                break;
            }
            case 7: {
                retour = 31;
                break;
            }
            case 8: {
                retour = 31;
                break;
            }
            case 9: {
                retour = 30;
                break;
            }
            case 10: {
                retour = 31;
                break;
            }
            case 11: {
                retour = 30;
                break;
            }
            case 12: {
                retour = 31;
                break;
            }
        }
        return retour;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == suiv) {
            container.removeAll();
            container.repaint();
            int m, a;
            if (mois == 12) {
                m = 1;
                a = annee + 1;
            } else {
                m = mois + 1;
                a = annee;
            }
            if (rdvs != null) {
                container.add(new Calendrier(x0, y0, m, a, container, rdvs));
            } else {
                container.add(new Calendrier(x0, y0, m, a, container, jlabel));
            }
            container.repaint();
        }
        if (e.getSource() == prec) {
            container.removeAll();
            container.repaint();
            int m, a;
            if (mois == 1) {
                m = 12;
                a = annee - 1;
            } else {
                m = mois - 1;
                a = annee;
            }
            if (rdvs != null) {
                container.add(new Calendrier(x0, y0, m, a, container, rdvs));
            } else {
                container.add(new Calendrier(x0, y0, m, a, container, jlabel));
            }
            container.repaint();
        }
    }

// les deux classes qui suivent font partie de ma biblioth�que de composants person�lis�e 
// changer les si elles vous plaisent pa ;) 
//****************************************************************/ 
//label personalis� 
    /**
     * *************************************************************
     */
    class SLabel extends JLabel {

        public SLabel(String label, int horizental, int taillePolice, int x0, int y0, int x, int y, JPanel contentPane) {
            super(label);
            setFont(new java.awt.Font("Monospaced", 1, taillePolice));
            setBounds(x0, y0, x, y);
            if (horizental == 0) {
                setHorizontalAlignment(SwingConstants.LEFT);
            }
            if (horizental == 1) {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            if (horizental == 2) {
                setHorizontalAlignment(SwingConstants.RIGHT);
            }
            contentPane.add(this);

        }
    }

    /**
     * *************************************************************
     */
// Boutons personalis� 
    /**
     * ************************************************************
     */
    class SButton extends JButton {

        public SButton(String label, int x0, int y0, int x, int y, boolean visible, JPanel contentPane) {
            super(label);
            setFont(new java.awt.Font("Monospaced", 1, 14));
            setBounds(x0, y0, x, y);
            setVisible(visible);
            setOpaque(false);
            contentPane.add(this);
        }
    }

}
