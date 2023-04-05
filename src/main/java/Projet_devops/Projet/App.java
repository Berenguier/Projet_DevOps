package Projet_devops.Projet;

public class App {
  public static void main(String[] args) {
	Object[] arr1 ={1,2,3};
	Object[] arr2 = {4,5,6};
    Dataframe test = new Dataframe(arr1,arr2);
    test.print();
  }
}
