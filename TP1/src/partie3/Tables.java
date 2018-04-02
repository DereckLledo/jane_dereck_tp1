package partie3;

import java.util.ArrayList;

public class Tables {
	

	
	private ArrayList<Clients> listeClients = new ArrayList<Clients>();
	

	private double montantTotal = 0;


	private int numeroTable;
	

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

	//mutateur de montantTotal
	public void setMontantTotal(double montantTotal) {
		this.montantTotal += montantTotal;
	}

}
