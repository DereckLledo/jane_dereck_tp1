package partie3;

import java.util.ArrayList;

public class Tables {
	
	//permet d'avoir des tables avec des numéros différents
	
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


}
