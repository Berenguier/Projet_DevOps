package Projet_devops.Projet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dataframe{
	Object[][] tab;
	boolean fichier = false;
	Dataframe(Object[]...args){
		tab = args;
	}
	
	Dataframe(String f , char c , Charset charset) throws IOException{
		fichier= true;
		tab = Files.lines(Paths.get(f),charset)
			    .map(ligne-> ligne.split(String.valueOf(c)))
			    .toArray(String[][]::new);  
		
	}
	public void print() {
		int n = tab[0].length;
		if(!fichier ) {
		for (int j=0;j<n;j++) {
			for (int i =0;i<tab.length;i++) {
				System.out.print(tab[i][j]);
				System.out.print(" ");
		}
			System.out.println("");
		}
		}
		else {
		for (int i=1; i<tab.length; i++) {
			for (int j=0;j<n;j++) {
			 System.out.print(tab[i][j]);
			 System.out.print(" ");
		}
			System.out.println(" ");
		}
		}
	}
	public Object[] getColonne(int i) {
		return tab[i];
	}
}