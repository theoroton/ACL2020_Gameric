package com.gameric.mazegame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gameric.mazegame.model.JeuLabyrinthe;
import com.gameric.mazegame.model.labyrinthe.Labyrinthe;
import com.gameric.mazegame.model.personnage.Archer;
import com.gameric.mazegame.model.personnage.Epeiste;
import com.gameric.mazegame.model.personnage.Personnage;

/**
 * Classe des tests sur le personnage
 * @author Théo Roton
 *
 */
public class TestPersonnage {

	/**
	 * Test du passage sur une case piégée.
	 * On teste les points de vie du personnage avant 
	 * et après le passage.
	 */
	@Test
	public void testCasePiegee01() {
		//Création du personnage
		Personnage personnage = new Epeiste();
		//Création du labyrinthe
		Labyrinthe labyrinthe = new Labyrinthe(personnage,"tests/test_cases_effets.txt");
		//Positionnement du personnage à côté d'une case piégée
		personnage.setPosition(6, 2);
		
		//On teste si le personnage a 30 points de vie au début
		assertEquals("Le personnage ne devrait pas encore avoir perdu de points de vie", 30, personnage.getPointsVie());
		
		//Déplacement du personnage sur la case piégée
		personnage.deplacer(1, 0);
		
		//On teste si le personnage a subi des dégâts et a 26 points de vie
		assertEquals("Le personnage devrait avoir perdu 4 points de vie", 26, personnage.getPointsVie());
	}
	
	
	/**
	 * Test du passage 2 fois sur la même case piégée.
	 * On teste les points de vie du personnage avant, au milieu et
	 * après les 2 passages.
	 */
	@Test
	public void testCasePiegee02() {
		//Création du personnage
		Personnage personnage = new Epeiste();
		//Création du labyrinthe
		Labyrinthe labyrinthe = new Labyrinthe(personnage,"tests/test_cases_effets.txt");
		//Positionnement du personnage à côté d'une case piégée
		personnage.setPosition(6, 2);
		
		//On teste si le personnage a 30 points de vie au début
		assertEquals("Le personnage ne devrait pas encore avoir perdu de points de vie", 30, personnage.getPointsVie());
		
		//Déplacement du personnage sur la case piégée
		personnage.deplacer(1, 0);
		//Déplacement du personnage sur la case à droite
		personnage.deplacer(1, 0);
		
		//On teste si le personnage a subi des dégâts et a 26 points de vie
		assertEquals("Le personnage devrait avoir perdu 4 points de vie", 26, personnage.getPointsVie());
		
		//Déplacement du personnage sur la case piégée
		personnage.deplacer(-1, 0);
		//Déplacement du personnage sur la case à gauche
		personnage.deplacer(-1, 0);
		
		//On teste si le personnage a subi des dégâts et a 22 points de vie
		assertEquals("Le personnage devrait avoir encore perdu 4 points de vie", 22, personnage.getPointsVie());
	}
	
	
	/**
	 * Test du passage sur la case piégée.
	 * On teste si le personnage meurt en passant sur la case
	 * piégée s'il lui reste moins de points de vie que les
	 * dégâts du piège.
	 */
	@Test
	public void testCasePiegee03() {
		//Création du personnage
		Personnage personnage = new Epeiste();
		//Création du labyrinthe
		Labyrinthe labyrinthe = new Labyrinthe(personnage,"tests/test_cases_effets.txt");
		//Positionnement du personnage à côté d'une case piégée
		personnage.setPosition(6, 2);
		//On met les points de vie du personnage à 2
		personnage.setPointsVie(2);
		
		//On teste si le personnage a bien 2 points de vie
		assertEquals("Le personnage devrait avoir 2 points de vie", 2, personnage.getPointsVie());
		//On teste si le personnage est vivant
		assertEquals("Le personnage ne devrait pas être mort", false, personnage.estMort());
		
		//Déplacement du personnage sur la case piégée
		personnage.deplacer(1, 0);
		
		//On teste si le personnage a subi des dégâts et n'a plus de points de vie
		assertEquals("Le personnage devrait ne plus avoir de points de vie", 0, personnage.getPointsVie());
		//On teste si le personnage est mort
		assertEquals("Le personnage devrait être mort", true, personnage.estMort());
	}
	
	
	/**
	 * Test du choix de la classe pour une classe existante
	 */
	@Test
	public void testChoixClasse01() {
		//Création du jeu
		JeuLabyrinthe jeu = new JeuLabyrinthe();
		//Choix de la classe
		jeu.choixClasse("archer");
		
		//La classe du joueur devrait être : Archer
		assertEquals("La classe du joueur devrait être un archer", Archer.class, jeu.getPersonnage().getClass());
	}
	
	
	/**
	 * Test du choix de la classe pour une classe inexistante
	 */
	@Test
	public void testChoixClasse02() {
		//Création du jeu
		JeuLabyrinthe jeu = new JeuLabyrinthe();
		//Choix de la classe
		jeu.choixClasse("barbare");
		
		//La classe du joueur devrait être la classe par défaut : Epeiste
		assertEquals("La classe du joueur devrait être un épeiste", Epeiste.class, jeu.getPersonnage().getClass());
	}
	/**
	 * Test sur la direction via la direction initiale du personnage
	 */
	@Test
	public void testDirectionInit(){
		//Création du jeu
		JeuLabyrinthe jeu = new JeuLabyrinthe();

		//La direction du joueur devrait être Est, la direction donnée initialement dans le constructeur
		assertEquals("La direction du joueur devrait être Est","Est",jeu.getPersonnage().getDirection());
	}

}
