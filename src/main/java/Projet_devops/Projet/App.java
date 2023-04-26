package Projet_devops.Projet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
	 public static void main(String[] args) throws IOException {
		    Object[] tab1 = {"label1",1,2,3};
		    Object[] tab2 = {"label2","test","test2",null};
		    Object[] tab3 = {"label3",4,5,6};
		    Object []tab4 = {"label4",7.5,8.5,9.5};
		    Object []tab5 = {"label5",true,true,false};
		   //Dataframe test = new Dataframe(tab1,tab2,tab3,tab4,tab5);
		    //int[] rowIndexes = {1, 3}; 
		  //Dataframe selectedDataframe = test.selectLignes(rowIndexes);
		 //   selectedDataframe.print();
		Dataframe test = new Dataframe("src/main/java/Projet_devops/Projet/fichier.csv",',', StandardCharsets.UTF_8);
		//Dataframe selectedDataframe = test.select_colonne_label("Sexe","Prénom");
		//selectedDataframe.print();
		test.print();
		 Dataframe select = test.select_colonne_label("Sexe");
		 select.print();
	  }
}
