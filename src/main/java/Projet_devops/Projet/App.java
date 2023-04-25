package Projet_devops.Projet;

public class App {
  public static void main(String[] args) {
	Object[] tab1 = {1,2,3};
	Object[] tab2 = {"test","test2",null};
	Object[] tab3 = {4,5,6};
	Object []tab4 = {7.5,8.5,9.5};
	Object []tab5 = {true,true,false};
    Dataframe test = new Dataframe(tab1,tab2,tab3,tab4,tab5);
    test.print();
  }
}
