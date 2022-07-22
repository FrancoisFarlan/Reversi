package fr.eni.ecole.reversi;

public enum Pion implements Affichable {

	NOIR, BLANC, LIBRE;
	private int nbPions = 2;
	private Joueur joueur;
	
	public int getNbPions () {
		return this.nbPions;
	}
	
	public void setNbPions (int nbPions) {
		this.nbPions = nbPions; 
	}
	
	@Override
	public char getSymbole() {
		char symbole;
		switch (this) {
			case BLANC : 
				symbole = 'o';
				break;
			case NOIR : 
				symbole = '●';
				break;
			default : 
				symbole = '·';
				break;
		}
		return symbole;
	}

	public Pion autrePion() {
		Pion autrePion;
		switch (this) {
			case BLANC : 
				autrePion = Pion.NOIR;
				break;
			case NOIR : 
				autrePion = Pion.BLANC;
				break;
			default : 
				autrePion = Pion.LIBRE;
				break;
		}
		return autrePion;
	}
	
	public void gagne(int nbPions) {
		this.nbPions += nbPions + 1; 
		this.autrePion().nbPions -= nbPions; 
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void choixJoueur() {
		String m = String.format("Quel joueur pour les pions %s ?%n " +
		"1 - Humain%n 2 - Ordinateur", this.getSymbole());
		int c = PlateauDeReversi.saisie(m, 1, 2);
		if (c == 1)
		this.joueur = new JoueurHumain();
		else
		this.joueur = new JoueurOrdi();
		}
	
}
