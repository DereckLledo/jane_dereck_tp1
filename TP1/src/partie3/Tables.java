package partie3;

import java.util.ArrayList;

public class Tables {
	

	
	private ArrayList<Clients> listeClients = new ArrayList<Clients>();
	
	private double montantAvantTaxes = 0;
	private double montantTotal = 0;


	private int numeroTable;


	private double frais = 0;

	private double montantTaxes = 0;
	

	public Tables(int noTable) {
		// TODO Auto-generated constructor stub
		this.numeroTable = noTable;
	}
	
	
	//permet d'ajouter un client à la liste 
	public void ajoutClient(Clients client) {
		
		listeClients.add(client);
		
	}

	//accesseur de listeClients
	public ArrayList<Clients> getListeClients() {
		return listeClients;
	}
	
	//accesseur de montantTotal
	public double getMontantTotal() {
		return montantTotal;
	}
	
	//accesseur de frais
	public double getFrais() {
		return frais;
	}
	
	//accesseur de taxes
	public double getMontantTaxes() {
		return montantTaxes;
	}

	//mutateur de montantTotal
	public void setMontantTotal(double montantAvantTaxes) {
		
		this.montantAvantTaxes = montantAvantTaxes;
		this.montantTaxes  = calculerTaxes(montantAvantTaxes);
		
		//on ajoute les taxes au prix
		this.montantTotal = montantAvantTaxes + montantTaxes;
		
		
		//si le montantTotal est plus grand que 100 ou qu'il y a plus de 2 clients, on ajoute des frais de 15% au prix avant les taxes
		if ( montantTotal > 100 || listeClients.size() >= 3 ) {
			
			this.frais  = montantAvantTaxes * 0.15;
			montantTotal += this.frais;
		}
		
		
		
		
	}

	//ajoute les taxes au prix
	public double calculerTaxes(double prix) {
		// TODO Auto-generated method stub
		double tauxTPS = 0.05;
		double tauxTVQ = 0.1;
		
		return ((prix * tauxTPS) + (prix * tauxTVQ));
	}



}
