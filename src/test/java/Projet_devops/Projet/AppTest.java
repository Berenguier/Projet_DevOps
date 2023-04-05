package Projet_devops.Projet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
