package partie3;

public class Clients {
	
	private String nom;
	private double montantTotal;
	
	public Clients(String nom){
		
		this.setNom(nom);
		this.montantTotal = 0;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
	
	/* pour ajouter une commande
	public commande(String ){
		
	}*/

}
