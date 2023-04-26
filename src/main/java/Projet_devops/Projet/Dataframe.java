
package Projet_devops.Projet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Bibliothèque pour traiter des données de tout type
 * @author Lucas Bouchra
 *
 */
public class Dataframe{

	Object[][] tab;
	Object[] label ;
	Boolean changerdetype = false;
	/**
	 * créer un nouveau dataframe
	 * @param args un tableau par colonne, le premier élémetns de chaque tableau est le label de la colonne
	 */
	Dataframe(Object[]...args){
		tab = args;
		label = new Object[tab.length];
		for (int j = 0;j<tab.length;j++) {
			label[j] = tab[j][0];
		}
	}
	/**
	 * constructeur du dataframe avec un fichier
	 * @param f path du fichier
	 * @param c caractère séparateur dans le fichier
	 * @param charset type 
	 * @throws IOException
	 */
	Dataframe(String f , char c , Charset charset) throws IOException{
		Object[][] temp;
		temp = Files.lines(Paths.get(f),charset)
				.map(ligne-> ligne.split(String.valueOf(c)))
				.toArray(String[][]::new); 
		tab = new Object[temp[0].length][temp.length];
		for (int i =0;i<temp.length;i++) {
			for (int j = 0;j<temp[0].length;j++) {
				tab[j][i]=temp[i][j];
			}
		}
		label = new Object[tab.length];
		for (int j = 0;j<tab.length;j++) {
			label[j] = tab[j][0];
		}
	}
	/** 
	 * affiche le contenue de tout le dataframe
	 */
	public void print() { 

		int n = tab[0].length;
		for (int j=0;j<n;j++) {
			for (int i =0;i<tab.length;i++) {

				System.out.print(tab[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}

	} 
	/**
	 * affiche les nb premieres lignes du dataframe
	 * @param nb nombre de lignes à afficher
	 */
	public void afficher_premiere_lignes(int nb) {
		for (int i=1;i<=nb;i++) {
			for (int j=0;j<tab.length;j++) {
				System.out.print(tab[j][i]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		System.out.println("");

	} 
	/**
	 * affiche les dernieres lignes du dataframe
	 * @param nb nombre de lignes à afficher
	 **/
	public void afficher_derniere_lignes(int nb) {
		for (int i=nb;i>=1;i--) {
			for (int j=0;j<tab.length;j++){
				System.out.print(tab[j][tab[0].length-i]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	/**
	 * creer un nouveau dataframe à partir des indices de lignes donnés en arguments
	 * @param args un ou plusieurs indice de ligne
	 * @return nouveau dataframe avec les lignes choisies
	 */
	public Dataframe selectLignes(int...args) {
		Object[][] liste  = new Object[args.length+1][tab.length];
		Object[][] liste2  = new Object[tab.length][args.length+1];
		liste[0]= label;
		for (int i =0;i<args.length;i++) {
			liste[i+1] = this.getLigne(args[i]);    
		}
		for (int i =0;i<args.length+1;i++) {
			for (int j = 0;j<tab.length;j++) {
				liste2[j][i]=liste[i][j];
			}
		}
		Dataframe select = new Dataframe(liste2);
		return select;  
	}
	/**
	 * creer un nouveau dataframe à partir des indices de colonnes donnés en arguments
	 * @param args un ou plusieurs indice de colonne
	 * @return nouveau dataframe avec les colonnes choisies
	 **/
	public Dataframe selectColonne(int...args) {
		Object[][] liste  = new Object[args.length][tab[0].length];
		for (int i =0;i<args.length;i++) {
			liste[i] = this.getColonne(args[i]);
		}
		Dataframe select = new Dataframe(liste);
		return select;
	}

	/**
	 * creer un nouveau dataframe à partir des labels de colonnes 
	 * @param args une ou plusieurs string correspondant au label des colonnes
	 * @return nouveau dataframe avec les colonnes choisies
	 */
	public Dataframe select_colonne_label(String...args) {
		int[] indexes = new int[args.length];
		int idx = 0;
		for (int i = 0; i < tab.length; i++) {
			for (String label : args) {
				if (tab[i][0].equals(label)) {
					indexes[idx++] = i;
				}
			}
		}
		if (idx < args.length) {
			indexes = Arrays.copyOf(indexes, idx);
		}
		if (indexes.length!=0) {
			return selectColonne(indexes);	
		}
		else {
			System.out.println("label non valid");
			return null;
		}
	}
	/**
	 * retourne la ligne i
	 * @param i indice de la ligne la ligne 0 correspond au label
	 * @return Object[] contenant la ligne
	 */
	public Object[] getLigne(int i) {
		Object[] ligne = new Object[tab.length];
		for (int j = 0;j<tab.length;j++) {
			ligne[j] = tab[j][i];
		}
		return ligne;
	}
	/**
	 * retourne la colonne i
	 * @param i indice de la colonne
	 * @return Object[] contenant la colonne
	 */
	public Object[] getColonne(int i) {
		return tab[i];
	}	

	/**
	 * verifie si le type des elements du tableau sont des integer
	 * @param obj tableau de type object
	 * @return vrai si le type des elements du tableau sont des integer sinon faux
	 */
	public Boolean typeInteger(Object[]obj) {
		if (obj[1] instanceof Integer){
			return true;
		}
		else {
			if ((Integer.valueOf((String) obj[1])) instanceof Integer) {
				changerdetype =true;
				return true;
			}
			else {
				return false;
			}
		}
	}
	/**
	 * Cette fonction retourne un nouveau dataframe qui contient uniquement la colonne spécifiée par le label2, filtrée en fonction de l'opérateur et de la valeur donnée en entrée.
	 * @param label2 le label de la colonne à sélectionner dans le dataframe.(la colonne doit contenir que des entiers).
	 * @param op  l'opérateur de comparaison à utiliser pour filtrer les données de la colonne. Les opérateurs possibles sont "<", "<=", ">", ">=", "==", "!="
	 * @param valeur la valeur à utiliser pour filtrer les données de la colonne en fonction de l'opérateur spécifié.
	 */
	public Dataframe select_colonne_label2(String label2,String op,int valeur ) {
		int m = 0;
		int i =0;
		while( i < label.length && !(label[i].equals(label2))) {
			i++;
		}
		if(label[i].equals(label2)){
			Object[] colonne = this.getColonne(i);
			Object[] resultat = new Object[tab[0].length];
			boolean resultatop;
			if (typeInteger( colonne )) {
				int bInt;
				for (int j=1;j<tab[0].length;j++) {
					if(changerdetype) {
						bInt =  (Integer.valueOf((String) colonne[j]));
					}
					else {
						bInt = (int) colonne[j];
					}
					switch (op) {
					case "<":
						resultatop = bInt < valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					case "<=":
						resultatop  =bInt <= valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					case ">":
						resultatop  = bInt > valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					case ">=":
						resultatop  = bInt >= valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					case "==":
						resultatop  = bInt == valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					case "!=":
						resultatop  = bInt != valeur;
						if(resultatop) {
							resultat[m] = bInt;
							m++;
						}
						break;
					default:
						System.out.println("Opérateur invalide");
						break;
					}
				}
				if (m <resultat.length) {
					resultat = Arrays.copyOf(resultat, m);
				}
				Dataframe select = new Dataframe(resultat);
				return select;
			}
			else {
				System.out.println("type non valid");
				return null;
			}
		}
		else {
			System.out.println("label non valid");
			return null;
		}
	}
	/**
	 * Cette fonction calcule la moyenne des valeurs numériques de la colonne spécifiée par le label3 dans le dataframe, et retourne cette valeur moyenne en tant que double.
	 * @param label3  le label de la colonne à partir de laquelle calculer la moyenne des valeurs numériques(la colonne doit contenir que des entiers)
	 */
	public double calculMoyenne(String label3) {
		int i =0;
		int somme = 0;
		Object[] colonne = new Object[tab[0].length];
		while( i < label.length && !(label[i].equals(label3))) {
			i++;
		}
		if(label[i].equals(label3)){
			colonne = this.getColonne(i);
			if (typeInteger( colonne )) {
				int bInt;
				for (int j=1;j<tab[0].length;j++) {
					if(changerdetype) {
						bInt =  (Integer.valueOf((String) colonne[j]));
					}
					else {
						bInt = (int) colonne[j];
					}
					somme +=  bInt;
				}
				double moyenne = (double) somme /(tab[0].length-1);
				return moyenne;
			}
			else {
				System.out.println("type non valid");
				return 0;
			}
		}
		else {
			System.out.println("label non valid");
			return 0;	
		}
	}
	/**
	 * La fonction calcule la valeur minimale des valeurs numériques de la colonne spécifiée par label3 dans le dataframe.La fonction retourne cette valeur minimale en tant que int.
	 * @param label3 le label de la colonne à partir de laquelle calculer la valeur minimale.(la colonne doit contenir que des entiers).
	 */
	public int calculMinimum(String label3) {
		int i =0;
		int bInt;
		int min;
		Object[] colonne = new Object[tab[0].length];
		while( i < label.length && !(label[i].equals(label3))) {
			i++;
		}
		if(label[i].equals(label3)){
			colonne = this.getColonne(i);
			if(typeInteger( colonne )) {
				if(changerdetype) {
					min =  (Integer.valueOf((String) colonne[1]));
				}
				else {
					min = (int) colonne[1];
				}
				for (int j=1;j<tab[0].length;j++) {
					if(changerdetype) {
						bInt =  (Integer.valueOf((String) colonne[j]));
					}
					else {
						bInt = (int) colonne[j];
					}
					if ( bInt < min) {
						min =  bInt;
					}
				}
				return min;
			}
			else {
				System.out.println("type non valid");
				return 0;
			}
		}
		else {
			System.out.println("label non valid");
			return 0;	
		}
	}
	/**
	 * La fonction calcule la valeur maximale des valeurs numériques de la colonne spécifiée par label3 dans le dataframe.  La fonction retourne cette valeur maximale en tant que int.
	 * @param label3 le label de la colonne à partir de laquelle calculer la valeur maximale.(la colonne doit contenir que des entiers).
	 */
	public int calculMaximum(String label3) {
		int i =0;
		int bInt;
		int max;
		Object[] colonne = new Object[tab[0].length];
		while( i < label.length && !(label[i].equals(label3))) {
			i++;
		}
		if(label[i].equals(label3)){
			colonne = this.getColonne(i);
			if(typeInteger( colonne )) {
				if(changerdetype) {
					max =  (Integer.valueOf((String) colonne[1]));
				}
				else {
					max = (int) colonne[1];
				}
				for (int j=1;j<tab[0].length;j++) {
					if(changerdetype) {
						bInt =  (Integer.valueOf((String) colonne[j]));
					}
					else {
						bInt = (int) colonne[j];
					}
					if ( bInt > max) {
						max =  bInt;
					}
				}
				return max;
			}
			else {
				System.out.println("type non valid");
				return 0;
			}
		}
		else {
			System.out.println("label non valid");
			return 0;	
		}
	}
	/**
	 * La fonction supprime la première ligne du tableau arr et retourne un nouveau tableau qui ne contient plus la première ligne. Le type de retour de la fonction est un tableau d'objets (Object[]).
	 * @param arr un tableau d'objets.
	 */
	public static Object[] supprimePremiereLigne(Object[] arr) {
		Object[] result = new Object[arr.length - 1];
		for (int i = 1; i < arr.length; i++) {
			result[i - 1] = arr[i];
		}
		return result;
	}
	/**
	 * La fonction calcule la valeur médiane des valeurs numériques de la colonne spécifiée par label3 dans le dataframe.La fonction retourne cette valeur médiane en tant que double.
	 * @param label3 le label de la colonne à partir de laquelle calculer la valeur médiane. (la colonne doit contenir que des entiers).
	 */
	public double calculMedium(String label3) {
		int i =0;
		Object[] colonne = new Object[tab[0].length];
		while( i < label.length && !(label[i].equals(label3))) {
			i++;
		}
		if(label[i].equals(label3)){
			colonne = this.getColonne(i);
			if (typeInteger( colonne )) {
				colonne=supprimePremiereLigne(colonne);
				Arrays.sort(colonne);
				double mediane;
				int milieu = (colonne.length) / 2;
				if ((colonne.length) % 2 == 0) {
					if(changerdetype) {
						mediane = (double)(Integer.valueOf((String)colonne[milieu - 1]) + Integer.valueOf((String)colonne[milieu])) / 2;
					}
					else {
						double somme =(((Integer) (colonne[milieu - 1])).doubleValue())+(((Integer) (colonne[milieu ])).doubleValue()); 
						mediane = somme/2;
					}
				} else {
					if(changerdetype) {
						mediane = Integer.valueOf((String)colonne[milieu]);
					}
					else {
						mediane =	((Integer) (colonne[milieu ])).doubleValue();
					}
				}
				return mediane;
			}
			else {
				System.out.println("type non valid");
				return 0;
			}
		}
		else {
			System.out.println("label non valid");
			return 0;	
		}
	}
}