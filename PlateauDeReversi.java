package fr.eni.ecole.reversi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlateauDeReversi {

	public static final int TAILLE = 8; 
	private Pion[][] plateau;
	private static Scanner s = new Scanner(System.in);
	/**
	 * @param plateau
	 */
	public PlateauDeReversi() {
		this.plateau = new Pion[TAILLE][TAILLE]; 
		
		for(int i = 0; i < TAILLE ; i++) {
			for(int j = 0; j < TAILLE ; j++) {
				plateau[i][j] = Pion.LIBRE;
			}
		}
		
		plateau[TAILLE / 2 - 1][TAILLE / 2 - 1] = Pion.BLANC;
		plateau[TAILLE / 2][TAILLE / 2] = Pion.BLANC;
		plateau[TAILLE / 2 - 1][TAILLE / 2] = Pion.NOIR;
		plateau[TAILLE / 2][TAILLE / 2 - 1] = Pion.NOIR;
		
	}
	
	public static String saisie(String message) {
		System.out.println(message);
		return s.nextLine();
		}
	
	public static int saisie(String message, int min, int max) {
		System.out.printf("%s ", message);
		int val = 0;
		boolean ok;
		do {
			try {
				val = s.nextInt();
				ok = val >= min && val <= max;
			} catch (InputMismatchException e) {
				ok = false;
			} finally {
				s.nextLine();
			}
			if (!ok)
				System.err.printf("La valeur doit être un entier compris entre %d et %d%nRessaisissez... ", min, max);
		} while (!ok);
		return val;
		}
	
	private void jouer() {
		Pion.NOIR.choixJoueur();
		Pion.BLANC.choixJoueur();
		Pion courant = Pion.NOIR;
		int nbPasseTour = 0;
		while (nbPasseTour < 2 && Pion.BLANC.getNbPions() + Pion.NOIR.getNbPions() < TAILLE * TAILLE) {
			System.out.printf("Au tour de %s (%s)...%n", courant.getJoueur().getNom(), courant.getSymbole());
			int nbRetournes = 0;
			boolean ok = false;
			do {
				this.afficher();
				if (this.peutJouer(courant)) {
					int[] coord = courant.getJoueur().jouer(this,courant);
					nbRetournes = this.tester(courant, coord[Joueur.LIGNE], coord[Joueur.COLONNE]);
					if (nbRetournes > 0) {
						this.poser(courant, coord[Joueur.LIGNE], coord[Joueur.COLONNE]);
						courant.gagne(nbRetournes);
						nbPasseTour = 0;
						ok = true;
					} else {
						System.err.println("Position illégale");
					}
				} else {
					System.out.printf("%s n'a aucune position où poser un de ses pions. Il passe son tour.%n", courant.getSymbole());
					nbPasseTour++;
					ok = true;
				}
			} while (!ok);
		// changement de joueur
		courant = courant.autrePion();
		}
		if (Pion.BLANC.getNbPions() > Pion.NOIR.getNbPions()) {
		System.out.printf("%s (%s) gagne !%n", Pion.BLANC.getJoueur().getNom(), Pion.BLANC.getSymbole());
		} else if (Pion.BLANC.getNbPions() < Pion.NOIR.getNbPions()) {
		System.out.printf("%s (%s) gagne !%n", Pion.NOIR.getJoueur().getNom(), Pion.NOIR.getSymbole());
		} else {
		System.out.println("Égalité !");
		}
		this.afficher();
		}
	
	public void afficher() {
		//affichage du score
		System.out.printf("%d %s%n%d %s%n" , Pion.NOIR.getNbPions(), Pion.NOIR.getSymbole(), Pion.BLANC.getNbPions(), Pion.BLANC.getSymbole());
		//affichage du plateau
		System.out.print("  ");
		for(int i = 0; i < TAILLE ; i++) {
			System.out.printf("%d ", i+1);
		}
		System.out.println();
		
		for(int j = 0 ; j < TAILLE ; j++) {
			System.out.printf("%d  ", j+1);
			for(int i = 0; i < TAILLE ; i++) {
				System.out.printf("%s  ", plateau[j][i].getSymbole());
			}
			System.out.println();
		}
		
	}
	
	public boolean peutJouer(Pion p) {
		boolean peutJouer = false; 
		int i = 0; 
		while (i < TAILLE && !peutJouer) {
			int j = 0;
			while(j < TAILLE && !peutJouer) {
				peutJouer = tester(p, i, j) > 0; 
				j++;
			}
			i++;
		}
		return peutJouer; 
	}
	
	public int tester(Pion p, int x, int y) {
		int nbPions = 0;
		if(plateau[x][y] == Pion.LIBRE) {
			for(int dx = -1; dx <= 1 ; dx++) {
				for(int dy = -1; dy <= 1; dy++) {
					if(dx !=0 || dy != 0) {
						nbPions += testerDirection(p, x, y, dx, dy);
					}
				}
			}
		}
		return nbPions;
	}
	
	private int testerDirection(Pion p, int x, int y, int dx, int dy) {
		Pion adverse = p.autrePion();
		int nbAutres = 0;
		int i = x + dx;
		int j = y + dy;
		while (0 <= i && i < TAILLE && 0 <= j && j < TAILLE && this.plateau[i][j] == adverse) {
			nbAutres++;
			i += dx;
			j += dy;
		}
		if (i < 0 || i >= TAILLE || j < 0 || j >= TAILLE || this.plateau[i][j] != p)
			nbAutres = 0;
		return nbAutres;
		}
	
	private void poser(Pion p, int x, int y) {
		this.plateau[x][y] = p;
		int nbPions;
		for (int dx = -1; dx <= 1; dx++)
			for (int dy = -1; dy <= 1; dy++) {
				nbPions = 0;
				if (dx != 0 || dy != 0) {
					nbPions += testerDirection(p, x, y, dx, dy);
					for (int k = 1; k <= nbPions; k++)
						this.plateau[x + dx * k][y + dy * k] = p;
					}
			}
		}
	
	public static void main(String[] args) {
		PlateauDeReversi p = new PlateauDeReversi();
		p.jouer();
		}
}
