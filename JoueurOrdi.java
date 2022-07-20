package fr.eni.ecole.reversi;

import java.util.Random;

public class JoueurOrdi implements Joueur {
private static Random r = new Random();

@Override
public int[] jouer(PlateauDeReversi plateau, Pion p) {
int[] coord = new int[2];
do {
coord[LIGNE] = r.nextInt(PlateauDeReversi.TAILLE);
coord[COLONNE] = r.nextInt(PlateauDeReversi.TAILLE);
} while(plateau.tester(p, coord[LIGNE], coord[COLONNE])==0);
return coord;
}

@Override
public String getNom() {
return "Ordinateur";
}
}