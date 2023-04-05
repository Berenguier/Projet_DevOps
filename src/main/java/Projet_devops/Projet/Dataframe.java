package Projet_devops.Projet;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Arrays;

public class Dataframe{
	Object[][] tab;
	Dataframe(Object[]...args){
		tab = args;
	}
	Dataframe(FileInputStream f){}
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
	public Object[] getColonne(int i) {
		return tab[i];
	}
}