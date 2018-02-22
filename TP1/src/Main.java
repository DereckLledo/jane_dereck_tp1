
public class Main {

	public static void main(String[] args) {

		System.out.println("Bienvenue chez Barette!");
		System.out.println();
		System.out.println();

		CV cvJane = new CV("Menassa", "Jane-Mary", "Dipl\u00f4me d'\u00e9tude secondaire",
				"Pas d'exp\u00e9rience de travail",
				"Bonne \u00e9l\u00e8ve, ponctuel, travaillante, professionelle, sociable, d\u00e9vou\u00e9e",
				"Travailler dans un environement sain avec des coll\u00e8gues professionel.");
		
		CV cvDereck = new CV("Lledo", "Dereck", "Soutien Informatique", "Livraison en restaurant", "Ponctuel, travaillant","Professionnalisme");
		
		//test 2 d

		cvJane.afficher();
		cvDereck.afficher();
		
	}

}
