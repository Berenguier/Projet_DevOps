package Projet_devops.Projet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
	//Petit test : création et affichage de 2 dataframe
  public static void main(String[] args)throws IOException {
	Object[] tab1 = {1,2,3};
	Object[] tab2 = {"test","test2",null};
	Object[] tab3 = {4,5,6};
	Object []tab4 = {7.5,8.5,9.5};
	Object []tab5 = {true,true,false};
    Dataframe test = new Dataframe(tab1,tab2,tab3,tab4,tab5);
    test.print();
    Dataframe fichier = new Dataframe("src/main/java/Projet_devops/Projet/fichier.csv",',',StandardCharsets.UTF_8);
    fichier.print();
  }
}
