
public class CV {

	private String nom;
	private String prenom;
	private String formation;
	private String experience;
	private String competences;
	private String attentes;

	public CV(String nom, String prenom, String formation, String experience, String competences, String attentes) {
		this.nom = nom;
		this.prenom = prenom;
		this.formation = formation;
		this.experience = experience;
		this.competences = competences;
		this.attentes = attentes;
	}
	
	public void afficher() {
		System.out.println("Voici mon CV :");
		System.out.println();
		System.out.println("Nom :\t\t\t" + nom);
		System.out.println("Pr\u00e9nom :\t\t" + prenom);
		System.out.println("Formation :\t\t" + formation);
		System.out.println("Exp\u00e9rience :\t\t" + experience);
		System.out.println("Comp\u00e9tences :\t\t" + competences);
		System.out.println("Attentes :\t\t" + attentes);
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
