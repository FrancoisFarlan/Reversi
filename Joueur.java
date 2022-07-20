package fr.eni.ecole.reversi;

public interface Joueur {
	
	static final int LIGNE = 0;
	static final int COLONNE = 1; 
	
	int[] jouer(PlateauDeReversi plateau, Pion p);
	String getNom(); 
	
}
	
