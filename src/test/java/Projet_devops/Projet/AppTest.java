package Projet_devops.Projet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AppTest {
    
  @Test
  public void CreerDataframe() {
	Object[][] tab = {{1,2,3},{"test","encore un test",null},{4,5,6},{7.5,8.5,9.5},{true,true,false}};
    Dataframe data = new Dataframe(tab[0],tab[1],tab[2],tab[3],tab[4]);
    boolean test = true;
    for (int i = 0;i<5;i++) {
    	if (tab[i]!=data.getColonne(i))
    		test =false;
    }
    assertTrue(test); 
  }

  @Test
  public void getcolonne() {
	Object[][] tab = {{1,2,3},{"test","encore un test",null},{4,5,6},{7.5,8.5,9.5},{true,true,false}};
    Dataframe data = new Dataframe(tab[0],tab[1],tab[2],tab[3],tab[4]);
    boolean test = true;
    	if (tab[2]!=data.getColonne(2))
    		test =false;
    
    assertTrue(test); 
  }
  
  @Test
  public void getligne() {
	Object[][] tab = {{1,2,3},{"test","encore un test",null},{4,5,6},{7.5,8.5,9.5},{true,true,false}};
    Dataframe data = new Dataframe(tab[0],tab[1],tab[2],tab[3],tab[4]);
    boolean test = true;
    Object[] ligne = data.getLigne(1);
    for (int i = 0;i<5;i++) {
    	if (tab[i][1]!=ligne[i])
    		test =false;
    }
    assertTrue(test); 
  }
  @Test
  public void selectligne() {
	  Object[][] tab = {{"label1",1,"1",true},{"label2",2,"2",false},{"label3",3,"3",true}};
	  Dataframe data = new Dataframe(tab[0],tab[1],tab[2]);
	  int indice = 1;
	  int indice2 = 2;
	  Dataframe select = data.selectLignes(indice,indice2);
	  boolean test = true;
	  for (int i = 0;i<3;i++) {
	    	if (tab[i][0]!=select.getColonne(i)[0]) //label
	    		test =false;
	    }
	  for (int i = 0;i<3;i++) {
	    	if (tab[i][indice]!=select.getColonne(i)[indice])
	    		test =false;
	    }
	  for (int i = 0;i<3;i++) {
	    	if (tab[i][indice2]!=select.getColonne(i)[indice2])
	    		test =false;
	    }

	  assertTrue(test); 
  }

  @Test
  public void selectcolonne() {
	  Object[][] tab = {{"label1",1,"1",true},{"label2",2,"2",false},{"label3",3,"3",true}};
	  Dataframe data = new Dataframe(tab[0],tab[1],tab[2]);
	  Dataframe select = data.select_colonne_label("label1","label3");
	  boolean test = true;

	  if (tab[0]!=select.getColonne(0))
		 test =false;
	  if (tab[2]!=select.getColonne(1))
		 test =false;
	    
	  assertTrue(test); 
  }
  @Test
  public void test_git() {
	  assertTrue(false);
  }
}
