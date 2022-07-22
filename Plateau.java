package fr.eni.ecole.reversi;

import java.util.ArrayList;
import java.util.List;

public class Plateau<T extends Affichable> {
	private int largeur;
	private int hauteur;
	private List<T> plateau;

	
	public Plateau(int largeur, int hauteur, T valeurInitiale) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.plateau = new ArrayList<>(hauteur * largeur); 
		for(int i = 0 ; i < hauteur * largeur ; i ++)
			this.plateau.add(valeurInitiale);
	}
	
	public void afficherPlateau() {
		System.out.println("  ");
		for(int i = 1; i <= this.largeur ; i++)
			System.out.printf("%d ", i + 1);
		System.out.println();
		for(int j = 0; j < this.hauteur ; j++) {
			System.out.printf("%d ", j+1);
			for(int k = 0; k < this.largeur ; k++)
				System.out.printf("%s ", this.get(j , k).getSymbole());
			System.out.println();
		}
	
	}
	
	public T get(int ligne, int colonne) {
		return this.plateau.get(ligne * this.hauteur + colonne);
	}
	
	public void set(int ligne, int colonne, T valeur) {
		this.plateau.set(ligne * this.hauteur + colonne, valeur);
	}
	
 
}