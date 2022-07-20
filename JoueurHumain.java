package fr.eni.ecole.reversi;

public class JoueurHumain implements Joueur {
private String nom;


public JoueurHumain() {
this.nom = PlateauDeReversi.saisie("Nom du joueur ?");
}

@Override
public int[] jouer(PlateauDeReversi plateau, Pion p) {
int[] coordonnees = new int[2];
coordonnees[LIGNE] = PlateauDeReversi.saisie("ligne", 1, PlateauDeReversi.TAILLE)-1;
coordonnees[COLONNE]=PlateauDeReversi.saisie("colonne",1,PlateauDeReversi.TAILLE)-1;
return coordonnees;
}


@Override
public String getNom() {
return this.nom;
}
}