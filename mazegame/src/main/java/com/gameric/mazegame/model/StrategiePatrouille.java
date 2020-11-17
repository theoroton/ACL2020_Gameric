package com.gameric.mazegame.model;

/**
 * 
 * @author Anna Sushko
 * Stratégie de deplacement aléatoire
 *
 */

public class StrategiePatrouille implements StrategieDeplacement{
	/**
	 * La direction de déplacement du monstre
	 */
	private String direction;	
	/**
	 * Méthode qui verifie si la case current n'est pas sur le bord du labyrinthe
	 * @param x
	 * @param y
	 * @param l
	 * @return true si la case current n'est pas sur le bord du labyrinthe, sinon false
	 */
	public boolean verifierBordures(int x, int y, Labyrinthe l) {
		boolean res = false;
		if ((x >= 0) && (x <= l.getLargeur()-1) && (y >= 0) && (y <= l.getHauteur()-1)){
			res = true;
		}
		return res;
	}
	
	/**
	 * Méthode qui gère le deplacement aleatoire d'un monstre
	 */
	@Override
	public void deplacer(Monstre monstre) {
		Personnage p = monstre.getLabyrinthe().getPersonnage_laby();
		String[] choix = {"UP", "RIGHT", "DOWN", "LEFT"};
		direction = choix[(int)(4 * Math.random())];
		int x = monstre.getPos_x();
		int y = monstre.getPos_y();
		
		switch (direction) {
			//Commande UP
			case "UP":
				//le monstre se déplace de 1 vers le haut (0,1)
				y--;
				break;
			//Commande DOWN
			case "DOWN":
				//le monstre se déplace de 1 vers le bas (0,-1)
				y++;
				break;
			//Commande LEFT
			case "LEFT":
				//le monstre se déplace de 1 vers la gauche (-1,0)
				x--;
				break;
			//Commande RIGHT
			case "RIGHT":
				//le monstre se déplace de 1 vers la droite (1,0)
				x++;
				break;
		}
		
		if (verifierBordures(x,y,monstre.getLabyrinthe())) {
			if(!(monstre.getLabyrinthe().getCase(x,y).getClass() == Mur.class) || monstre.peutTraverserMur()) {
				if(!(monstre.getLabyrinthe().estCaseOccupee(x, y))) {
					monstre.setPosition(x, y);
				} else if(monstre.getLabyrinthe().getCase(x,y) == p.getPosition()) {
					p.setPointsVie(p.getPointsVie() - monstre.getDegats());
				}
			}
		}
	}
}
