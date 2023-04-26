
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

public class Dataframe{
	Object[][] tab;
	Object[] label ;
	Dataframe(Object[]...args){
		tab = args;
		label = new Object[tab.length];
		for (int j = 0;j<tab.length;j++) {
            label[j] = tab[j][0];
        }
	}

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
	// affiche le contenue de tout le dataframe
	public void print() {
		int n = tab[0].length;
		for (int j=0;j<n;j++) {
			for (int i =0;i<tab.length;i++) {
				
				System.out.print(tab[i][j]);
				System.out.print(" ");
		}
			System.out.println("");
		}

	} //affiche les nb premieres lignes du dataframe
	public void afficher_premiere_lignes(int nb) {
		for (int i=1;i<=nb;i++) {
		for (int j=0;j<tab.length;j++) {
				System.out.print(tab[j][i]);
				System.out.print(" ");
			}
		System.out.println("");
		}
			System.out.println("");
	
	} //affiche les nb dernieres lignes du dataframe
	public void afficher_derniere_lignes(int nb) {
		for (int i=nb;i>=1;i--) {
			for (int j=0;j<tab.length;j++){
				System.out.print(tab[j][tab[0].length-i]);
				System.out.print(" ");
		}
			System.out.println("");
		}
		}

	//creer un nouveau dataframe à partir des indices de lignes donnés en arguments
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
	//creer un nouveau dataframe à partir des indices de colonnes donnés en arguments
	public Dataframe selectColonne(int...args) {
        Object[][] liste  = new Object[args.length][tab[0].length];
        for (int i =0;i<args.length;i++) {
            liste[i] = this.getColonne(args[i]);
        }
        Dataframe select = new Dataframe(liste);
        return select;
    }
	
	// creer un nouveau dataframe à partir des labels de colonnes donnés en arguments
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
	// retourne la ligne i
	public Object[] getLigne(int i) {
        Object[] ligne = new Object[tab.length];
        for (int j = 0;j<tab.length;j++) {
            ligne[j] = tab[j][i];
        }
        return ligne;
    }
	// retourne la colonne i
	public Object[] getColonne(int i) {
		return tab[i];
	}	
}